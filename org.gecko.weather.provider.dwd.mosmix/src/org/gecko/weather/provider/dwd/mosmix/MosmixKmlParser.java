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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.gecko.weather.api.spi.FetchException;

/**
 * Streaming parser for MOSMIX KML (the DWD point-forecast extension). Reads the document header —
 * issue time, referenced model runs, the forecast time steps — then every {@code Placemark} whose
 * station id is wanted, and hands each to a consumer as soon as it is complete. Constant memory per
 * station, which is what makes the 40 MB all-stations file (MOSMIX_S) as feasible as a single
 * station's.
 * <p>
 * Values arrive as whitespace-separated text per element, one per time step, {@code -} for missing.
 * Missing becomes {@link Double#NaN}; an element whose count does not match the time steps is
 * reported in {@link StationForecast#rejectedElements()} and left out.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class MosmixKmlParser {

	static final String DWD_NS = "https://opendata.dwd.de/weather/lib/pointforecast_dwd_extension_V1_0.xsd";

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
	 * @param stationId       the placemark name, e.g. {@code 10554}
	 * @param description     the placemark description, the station's name
	 * @param latitude        from the placemark point
	 * @param longitude       from the placemark point
	 * @param elevation       from the placemark point, metres
	 * @param values          source element → values per time step, {@code NaN} where missing
	 * @param rejectedElements elements whose value count did not match the time steps
	 */
	public record StationForecast(Header header, String stationId, String description, double latitude, double longitude,
			double elevation, Map<String, double[]> values, List<String> rejectedElements) {
	}

	private MosmixKmlParser() {
	}

	/**
	 * Parses the KML stream, handing every wanted station to the consumer.
	 *
	 * @param kml            the KML (already unzipped)
	 * @param wantedStations station ids to emit; empty means every station in the file
	 * @return the header, so that a caller can tell "station not in file" from "file unchanged"
	 * @throws IOException    if the stream cannot be read
	 * @throws FetchException if the document is not MOSMIX KML as expected
	 */
	public static Header parse(InputStream kml, Set<String> wantedStations, Consumer<StationForecast> consumer)
			throws IOException {
		requireNonNull(kml, "kml");
		requireNonNull(wantedStations, "wantedStations");
		requireNonNull(consumer, "consumer");
		XMLInputFactory factory = XMLInputFactory.newFactory();
		factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
		factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
		factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		try {
			XMLStreamReader r = factory.createXMLStreamReader(kml);
			Header header = null;
			HeaderBuilder hb = new HeaderBuilder();
			while (r.hasNext()) {
				int event = r.next();
				if (event != XMLStreamConstants.START_ELEMENT) {
					continue;
				}
				String name = r.getLocalName();
				if (header == null) {
					switch (name) {
					case "IssueTime" -> hb.issuedAt = instant(r.getElementText());
					case "Model" -> {
						if (hb.modelRun == null) {
							String ref = r.getAttributeValue(DWD_NS, "referenceTime");
							if (ref == null) {
								ref = r.getAttributeValue(null, "referenceTime");
							}
							if (ref != null) {
								hb.modelRun = instant(ref);
							}
						}
					}
					case "TimeStep" -> hb.timeSteps.add(instant(r.getElementText()));
					case "Placemark" -> {
						header = hb.build();
						readPlacemark(r, header, wantedStations, consumer);
					}
					default -> {
						// not part of the header we need
					}
				}
				} else if ("Placemark".equals(name)) {
					readPlacemark(r, header, wantedStations, consumer);
				}
			}
			if (header == null) {
				header = hb.build(); // a file without placemarks still has a header
			}
			return header;
		} catch (XMLStreamException e) {
			throw new FetchException("Not a readable MOSMIX KML: " + e.getMessage(), e);
		}
	}

	private static void readPlacemark(XMLStreamReader r, Header header, Set<String> wanted, Consumer<StationForecast> consumer)
			throws XMLStreamException {
		String stationId = null;
		String description = null;
		double lat = Double.NaN, lon = Double.NaN, elev = Double.NaN;
		Map<String, double[]> values = new LinkedHashMap<>();
		List<String> rejected = new ArrayList<>();
		boolean skip = false;
		int depth = 1;
		while (r.hasNext() && depth > 0) {
			int event = r.next();
			if (event == XMLStreamConstants.END_ELEMENT) {
				depth--;
				continue;
			}
			if (event != XMLStreamConstants.START_ELEMENT) {
				continue;
			}
			depth++;
			String name = r.getLocalName();
			switch (name) {
			case "name" -> {
				stationId = r.getElementText().strip();
				depth--;
				skip = !wanted.isEmpty() && !wanted.contains(stationId);
			}
			case "description" -> {
				description = r.getElementText().strip();
				depth--;
			}
			case "coordinates" -> {
				// KML order is longitude,latitude,altitude
				String[] parts = r.getElementText().strip().split(",");
				depth--;
				if (parts.length >= 2) {
					lon = Double.parseDouble(parts[0]);
					lat = Double.parseDouble(parts[1]);
					elev = parts.length > 2 ? Double.parseDouble(parts[2]) : Double.NaN;
				}
			}
			case "Forecast" -> {
				String element = r.getAttributeValue(DWD_NS, "elementName");
				if (element == null) {
					element = r.getAttributeValue(null, "elementName");
				}
				String text = readForecastValue(r);
				depth--;
				if (skip || element == null) {
					continue;
				}
				double[] parsed = parseValues(text, header.timeSteps().size());
				if (parsed == null) {
					rejected.add(element);
				} else {
					values.put(element, parsed);
				}
			}
			default -> {
				// container elements: ExtendedData, Point, ...
			}
			}
		}
		if (!skip && stationId != null) {
			consumer.accept(new StationForecast(header, stationId, description, lat, lon, elev, values, rejected));
		}
	}

	/** Positioned on {@code <dwd:Forecast>}: returns the text of its {@code <dwd:value>}, consuming the element. */
	private static String readForecastValue(XMLStreamReader r) throws XMLStreamException {
		String text = "";
		int depth = 1;
		while (r.hasNext() && depth > 0) {
			int event = r.next();
			if (event == XMLStreamConstants.START_ELEMENT) {
				if ("value".equals(r.getLocalName())) {
					text = r.getElementText();
				} else {
					depth++;
				}
			} else if (event == XMLStreamConstants.END_ELEMENT) {
				depth--;
			}
		}
		return text;
	}

	/** Whitespace-separated numbers, {@code -} → NaN; {@code null} if the count does not match. */
	static double[] parseValues(String text, int expected) {
		String[] tokens = text.strip().split("\\s+");
		if (text.isBlank() || tokens.length != expected) {
			return null;
		}
		double[] out = new double[expected];
		for (int i = 0; i < expected; i++) {
			String t = tokens[i];
			if ("-".equals(t)) {
				out[i] = Double.NaN;
			} else {
				try {
					out[i] = Double.parseDouble(t);
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		return out;
	}

	private static Instant instant(String text) {
		return OffsetDateTime.parse(text.strip()).toInstant();
	}

	private static final class HeaderBuilder {
		Instant issuedAt;
		Instant modelRun;
		final List<Instant> timeSteps = new ArrayList<>();

		Header build() {
			if (issuedAt == null || timeSteps.isEmpty()) {
				throw new FetchException("MOSMIX KML without IssueTime or ForecastTimeSteps");
			}
			return new Header(issuedAt, Optional.ofNullable(modelRun), timeSteps);
		}
	}

}
