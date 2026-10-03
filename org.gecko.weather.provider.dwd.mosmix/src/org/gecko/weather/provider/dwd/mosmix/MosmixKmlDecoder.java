/**
 * Copyright (c) 2012 - 2026 Data In Motion and others.
 * All rights reserved.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.weather.provider.dwd.mosmix;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.gecko.weather.api.spi.FetchException;

import de.dwd.cdc.forecast.pointforecast.ForecastType;
import de.dwd.cdc.forecast.pointforecast.ModelType;
import de.dwd.cdc.forecast.pointforecast.PointforecastPackage;
import de.dwd.cdc.forecast.pointforecast.ProductDefinitionType;
import net.opengis.kml.DocumentRoot;
import net.opengis.kml.DocumentType;
import net.opengis.kml.KMLPackage;
import net.opengis.kml.KmlType;
import net.opengis.kml.PlacemarkType;
import net.opengis.kml.PointType;
import net.opengis.kml.util.KMLResourceFactoryImpl;

/**
 * Decodes MOSMIX KML through the Fennec EMF models {@code net.opengis.kml.model} and
 * {@code de.dwd.cdc.forecast.model} (the DWD point-forecast extension). The document is loaded as an
 * EMF resource and navigated: product definition in the document's extended data — issue time,
 * referenced model run, time steps — then every wanted {@code Placemark} with its forecasts.
 * <p>
 * A single-station file (MOSMIX_L, ~350 KB) is small enough to load whole; that is why this
 * provider serves MOSMIX_L only — the 40 MB all-stations file would be the {@code F-19} failure mode.
 * Values arrive as a list of {@code Float} or the string {@code -} for missing; missing becomes
 * {@link Double#NaN}, and an element whose count does not match the time steps is reported in
 * {@link StationForecast#rejectedElements()} and left out.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class MosmixKmlDecoder {

	/** The document header every station forecast refers to. */
	public record Header(Instant issuedAt, Optional<Instant> modelRun, List<Instant> timeSteps) {
		public Header {
			requireNonNull(issuedAt, "issuedAt");
			requireNonNull(modelRun, "modelRun");
			timeSteps = List.copyOf(requireNonNull(timeSteps, "timeSteps"));
		}
	}

	/**
	 * One station's forecast: the header, the station as the file describes it, and the raw values
	 * per source element aligned with {@code header.timeSteps()}.
	 *
	 * @param stationId        the placemark name, e.g. {@code 10554}
	 * @param description      the placemark description, the station's name
	 * @param latitude         from the placemark point
	 * @param longitude        from the placemark point
	 * @param elevation        from the placemark point, metres; {@code NaN} when absent
	 * @param values           source element → values per time step, {@code NaN} where missing
	 * @param rejectedElements elements whose value count did not match the time steps
	 */
	public record StationForecast(Header header, String stationId, String description, double latitude, double longitude,
			double elevation, Map<String, double[]> values, List<String> rejectedElements) {
	}

	private static final String KML_EXTENSION = "kml";

	private MosmixKmlDecoder() {
	}

	/** A resource set that knows the two models and the KML resource factory — for tests and tools. */
	public static ResourceSet plainResourceSet() {
		return prepare(new ResourceSetImpl());
	}

	/**
	 * Makes sure a resource set can load MOSMIX KML: both packages registered, the KML factory bound
	 * to the {@code kml} extension. A Fennec resource set targeted at the models already has it; a
	 * plain one gets it here. Idempotent.
	 */
	public static ResourceSet prepare(ResourceSet rs) {
		requireNonNull(rs, "rs");
		rs.getPackageRegistry().putIfAbsent(KMLPackage.eNS_URI, KMLPackage.eINSTANCE);
		rs.getPackageRegistry().putIfAbsent(PointforecastPackage.eNS_URI, PointforecastPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent(KML_EXTENSION, new KMLResourceFactoryImpl());
		return rs;
	}

	/**
	 * Decodes the KML stream, handing every wanted station to the consumer. Synchronises on the
	 * resource set and removes its resource afterwards, so a shared (prototype-scoped) resource set
	 * stays clean.
	 *
	 * @param kml            the KML (already unzipped)
	 * @param wantedStations station ids to emit; empty means every station in the file
	 * @param rs             the resource set to load with, see {@link #prepare(ResourceSet)}
	 * @return the header, so that a caller can tell "station not in file" from "file unchanged"
	 * @throws IOException    if the stream cannot be read
	 * @throws FetchException if the document is not MOSMIX KML as expected
	 */
	public static Header decode(InputStream kml, Set<String> wantedStations, Consumer<StationForecast> consumer,
			ResourceSet rs) throws IOException {
		requireNonNull(kml, "kml");
		requireNonNull(wantedStations, "wantedStations");
		requireNonNull(consumer, "consumer");
		prepare(rs);
		synchronized (rs) {
			Resource resource = rs.createResource(URI.createURI("mosmix-" + System.nanoTime() + "." + KML_EXTENSION));
			try {
				try {
					resource.load(kml, null);
				} catch (Resource.IOWrappedException e) {
					throw new FetchException("Not a readable MOSMIX KML: " + e.getMessage(), e);
				}
				KmlType root = root(resource);
				DocumentType document = (DocumentType) root.getAbstractFeatureGroupGroup()
						.get(KMLPackage.eINSTANCE.getDocumentRoot_Document(), true);
				if (document == null || document.getExtendedData() == null) {
					throw new FetchException("MOSMIX KML without a Document or its ExtendedData");
				}
				Header header = header(document);
				for (PlacemarkType placemark : placemarks(document)) {
					String stationId = placemark.getName() == null ? null : placemark.getName().strip();
					if (stationId == null || (!wantedStations.isEmpty() && !wantedStations.contains(stationId))) {
						continue;
					}
					consumer.accept(forecast(header, placemark, stationId));
				}
				return header;
			} finally {
				resource.unload();
				rs.getResources().remove(resource);
			}
		}
	}

	// --- navigation ------------------------------------------------------------------------

	private static KmlType root(Resource resource) {
		if (!resource.getErrors().isEmpty()) {
			throw new FetchException("Not a readable MOSMIX KML: " + resource.getErrors().get(0).getMessage());
		}
		if (resource.getContents().isEmpty()) {
			throw new FetchException("Empty MOSMIX KML");
		}
		EObject first = resource.getContents().get(0);
		KmlType kml = first instanceof DocumentRoot dr ? dr.getKml() : first instanceof KmlType k ? k : null;
		if (kml == null) {
			throw new FetchException("Not a KML document: root is " + first.eClass().getName());
		}
		return kml;
	}

	@SuppressWarnings("unchecked")
	private static Header header(DocumentType document) {
		List<ProductDefinitionType> definitions = (List<ProductDefinitionType>) document.getExtendedData().getAny()
				.get(PointforecastPackage.eINSTANCE.getDocumentRoot_ProductDefinition(), true);
		if (definitions == null || definitions.isEmpty()) {
			throw new FetchException("MOSMIX KML without ProductDefinition");
		}
		ProductDefinitionType pd = definitions.get(0);
		if (pd.getIssueTime() == null || pd.getForecastTimeSteps() == null || pd.getForecastTimeSteps().getTimeStep().isEmpty()) {
			throw new FetchException("MOSMIX KML without IssueTime or ForecastTimeSteps");
		}
		Optional<Instant> modelRun = Optional.empty();
		if (pd.getReferencedModel() != null) {
			for (ModelType model : pd.getReferencedModel().getModel()) {
				if (model.getReferenceTime() != null) {
					modelRun = Optional.of(instant(model.getReferenceTime()));
					break;
				}
			}
		}
		List<Instant> steps = new ArrayList<>();
		for (XMLGregorianCalendar step : pd.getForecastTimeSteps().getTimeStep()) {
			steps.add(instant(step));
		}
		return new Header(instant(pd.getIssueTime()), modelRun, steps);
	}

	@SuppressWarnings("unchecked")
	private static List<PlacemarkType> placemarks(DocumentType document) {
		List<PlacemarkType> placemarks = (List<PlacemarkType>) document.getAbstractFeatureGroupGroup()
				.get(KMLPackage.eINSTANCE.getDocumentRoot_Placemark(), true);
		return placemarks == null ? List.of() : placemarks;
	}

	@SuppressWarnings("unchecked")
	private static StationForecast forecast(Header header, PlacemarkType placemark, String stationId) {
		double lat = Double.NaN, lon = Double.NaN, elev = Double.NaN;
		PointType point = (PointType) placemark.getAbstractGeometryGroupGroup().get(KMLPackage.eINSTANCE.getDocumentRoot_Point(), true);
		if (point != null && point.getCoordinates() != null && !point.getCoordinates().isEmpty()) {
			// KML order is longitude,latitude,altitude
			String[] parts = point.getCoordinates().get(0).strip().split(",");
			if (parts.length >= 2) {
				lon = Double.parseDouble(parts[0]);
				lat = Double.parseDouble(parts[1]);
				elev = parts.length > 2 ? Double.parseDouble(parts[2]) : Double.NaN;
			}
		}
		Map<String, double[]> values = new LinkedHashMap<>();
		List<String> rejected = new ArrayList<>();
		if (placemark.getExtendedData() != null) {
			List<ForecastType> forecasts = (List<ForecastType>) placemark.getExtendedData().getAny()
					.get(PointforecastPackage.eINSTANCE.getDocumentRoot_Forecast(), true);
			for (ForecastType fc : forecasts == null ? List.<ForecastType>of() : forecasts) {
				String element = fc.getElementName();
				if (element == null) {
					continue;
				}
				double[] parsed = toDoubles(fc.getValue() == null ? null : fc.getValue().getValue(), header.timeSteps().size());
				if (parsed == null) {
					rejected.add(element);
				} else {
					values.put(element, parsed);
				}
			}
		}
		String description = placemark.getDescription() == null ? null : placemark.getDescription().strip();
		return new StationForecast(header, stationId, description, lat, lon, elev, values, rejected);
	}

	/**
	 * The model's value list — {@code Float} per step, the string {@code -} for missing — as doubles
	 * with {@code NaN} for missing; {@code null} if the count does not match the time steps.
	 */
	static double[] toDoubles(List<Object> raw, int expected) {
		if (raw == null || raw.size() != expected) {
			return null;
		}
		double[] out = new double[expected];
		for (int i = 0; i < expected; i++) {
			Object item = raw.get(i);
			if (item instanceof Number n) {
				out[i] = n.doubleValue();
			} else if (item == null || "-".equals(item.toString().strip())) {
				out[i] = Double.NaN;
			} else {
				try {
					out[i] = Double.parseDouble(item.toString().strip());
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		return out;
	}

	private static Instant instant(XMLGregorianCalendar calendar) {
		return calendar.toGregorianCalendar().toInstant();
	}

}
