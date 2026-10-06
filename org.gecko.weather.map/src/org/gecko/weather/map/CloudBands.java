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
package org.gecko.weather.map;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.gecko.weather.api.spi.GridField;
import org.gecko.weather.map.Isobands.Band;
import org.gecko.weather.map.Isobands.Polygon;
import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapFactory;
import org.geojson.GeoJsonFactory;
import org.geojson.MultiPolygon;
import org.geojson.util.GeoJsonHelper;

/**
 * A cloud cover field as {@link CloudBand} features: one per band of {@link Isobands}, with its
 * colour, a name for a popup and the bounding box. A band without polygons yields no feature.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
public final class CloudBands {

	private static final DateTimeFormatter RUN = DateTimeFormatter.ofPattern("yyyyMMddHH", Locale.ROOT).withZone(ZoneOffset.UTC);
	private static final DateTimeFormatter VALID = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm'Z'", Locale.ROOT)
			.withZone(ZoneOffset.UTC);

	/**
	 * Three steps of blue-grey by the band's lower limit — broken, mostly cloudy, overcast; darker
	 * means less sun. The viewer of emf.ogc.features fills with a feature's color.
	 */
	static String color(double lower) {
		return lower >= 75 ? "#546e7a" : lower >= 50 ? "#90a4ae" : "#cfd8dc";
	}

	private CloudBands() {
	}

	public static List<CloudBand> of(GridField field, Isobands.Settings settings, String attribution) {
		requireNonNull(field, "field");
		List<Band> bands = Isobands.of(field.grid(), field.data(), settings);
		List<CloudBand> out = new ArrayList<>();
		for (Band band : bands) {
			if (!band.polygons().isEmpty()) {
				out.add(feature(field, band, color(band.lower()), attribution));
			}
		}
		return out;
	}

	static CloudBand feature(GridField field, Band band, String color, String attribution) {
		CloudBand f = CloudmapFactory.eINSTANCE.createCloudBand();
		f.setId(id(field.parameter(), field.run(), field.validAt(), band.lower()));
		f.setName(String.format(Locale.ROOT, "Bewölkung %s–%s %s", number(band.lower()), number(band.upper()), field.unit()));
		f.setParameter(field.parameter());
		f.setUnit(field.unit());
		f.setLower(band.lower());
		f.setUpper(band.upper());
		f.setRun(Date.from(field.run()));
		f.setValidTime(Date.from(field.validAt()));
		f.setLeadHours((int) Duration.between(field.run(), field.validAt()).toHours());
		f.setColor(color);
		f.setSource(field.providerId() + "/" + field.productId());
		f.setAttribution(attribution);
		f.setGeometry(multiPolygon(band.polygons()));
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = -Double.MAX_VALUE;
		double maxY = -Double.MAX_VALUE;
		for (Polygon p : band.polygons()) {
			for (double[] c : p.outer()) {
				minX = Math.min(minX, c[0]);
				maxX = Math.max(maxX, c[0]);
				minY = Math.min(minY, c[1]);
				maxY = Math.max(maxY, c[1]);
			}
		}
		f.setMinX(minX);
		f.setMinY(minY);
		f.setMaxX(maxX);
		f.setMaxY(maxY);
		return f;
	}

	/** {@code clct-2026100300-20261003T0300Z-75} */
	static String id(String parameter, Instant run, Instant validAt, double lower) {
		return parameter + "-" + RUN.format(run) + "-" + VALID.format(validAt) + "-" + number(lower);
	}

	private static MultiPolygon multiPolygon(List<Polygon> polygons) {
		double[][][][] data = new double[polygons.size()][][][];
		for (int n = 0; n < polygons.size(); n++) {
			Polygon p = polygons.get(n);
			double[][][] rings = new double[1 + p.holes().size()][][];
			rings[0] = p.outer();
			for (int h = 0; h < p.holes().size(); h++) {
				rings[h + 1] = p.holes().get(h);
			}
			data[n] = rings;
		}
		MultiPolygon m = GeoJsonFactory.eINSTANCE.createMultiPolygon();
		GeoJsonHelper.setMultiPolygonData(m, data);
		return m;
	}

	private static String number(double value) {
		return value == Math.rint(value) ? Long.toString((long) value) : Double.toString(value);
	}
}
