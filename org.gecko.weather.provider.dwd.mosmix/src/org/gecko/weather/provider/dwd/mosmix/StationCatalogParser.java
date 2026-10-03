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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * Parses DWD's {@code mosmix_stationskatalog.cfg}: fixed-width text, ISO-8859-1, two header lines.
 *
 * <pre>
 * ID    ICAO NAME                 LAT    LON     ELEV
 * ----- ---- -------------------- -----  ------- -----
 * 10554 EDDE ERFURT               50.59   10.58   315
 * </pre>
 *
 * <b>The coordinates are degrees and minutes written as {@code DD.MM}</b>, not decimal degrees:
 * {@code 50.59} is 50° 59′ = 50.983°. The KML of the same station says {@code 50.98}. Read as a
 * decimal, every station would sit up to 0.4° (≈ 45 km) off — which is what the previous
 * implementation did.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class StationCatalogParser {

	private StationCatalogParser() {
	}

	public static StationCatalog parse(InputStream in, String providerId, String productId, Instant retrievedAt)
			throws IOException {
		requireNonNull(in, "in");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		StationCatalog catalog = f.createStationCatalog();
		catalog.setProviderId(providerId);
		catalog.setProductId(productId);
		catalog.setRetrievedAt(retrievedAt);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));
		String line;
		int lineNo = 0;
		while ((line = reader.readLine()) != null) {
			lineNo++;
			if (lineNo <= 2 || line.isBlank()) {
				continue; // header and separator
			}
			Station station = parseLine(line);
			if (station != null) {
				catalog.getStations().add(station);
			}
		}
		return catalog;
	}

	/** One catalogue line, or {@code null} for a line that is not a station. */
	static Station parseLine(String line) {
		if (line.length() < 52) {
			return null;
		}
		try {
			WeatherFactory f = WeatherFactory.eINSTANCE;
			Station s = f.createStation();
			s.setId(line.substring(0, 5).strip());
			String icao = line.substring(6, 10).strip();
			if (!icao.isEmpty() && !"----".equals(icao)) {
				s.setIcaoCode(icao);
			}
			s.setName(line.substring(11, 32).strip());
			GeoPosition p = f.createGeoPosition();
			p.setLatitude(degreesMinutes(line.substring(32, 39).strip()));
			p.setLongitude(degreesMinutes(line.substring(39, 47).strip()));
			p.setElevation(Integer.parseInt(line.substring(47, 52).strip()));
			s.setPosition(p);
			return s.getId().isEmpty() ? null : s;
		} catch (NumberFormatException | StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	/** {@code DD.MM} (sign on the degrees) → decimal degrees. */
	static double degreesMinutes(String ddmm) {
		int dot = ddmm.indexOf('.');
		if (dot < 0) {
			return Double.parseDouble(ddmm);
		}
		boolean negative = ddmm.startsWith("-");
		double degrees = Math.abs(Double.parseDouble(ddmm.substring(0, dot)));
		String minuteText = ddmm.substring(dot + 1);
		double minutes = Double.parseDouble(minuteText) * (minuteText.length() == 1 ? 10 : 1);
		double decimal = degrees + minutes / 60.0;
		return negative ? -decimal : decimal;
	}

}
