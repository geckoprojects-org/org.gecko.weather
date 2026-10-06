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
package org.gecko.weather.pv.internal;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.gecko.weather.api.solar.SolarPosition;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.solar.SpaSolarService;
import org.junit.jupiter.api.Test;

/**
 * Replays clear days through the real {@link PlantForecaster}: measured hourly direct and diffuse
 * radiation, air temperature and wind (prepared CSVs, e.g. a DWD CDC station) stand in for ICON-D2
 * and the station, the plant comes from a plants folder. Writes the hourly DC and AC power next to
 * the input, for a comparison with the plant's meter outside this test.
 * <p>
 * Runs only with {@code REPLAY_DIR} (input: {@code replay_weather.csv}, {@code replay_station.csv};
 * output: {@code replay_model.csv}) and {@code REPLAY_PLANTS} / {@code REPLAY_PLANT} /
 * {@code REPLAY_SITE_FILE} set — local data, never in CI.
 */
class ClearDayReplayTest {

	private static final WeatherFactory W = WeatherFactory.eINSTANCE;
	private static final Duration H = Duration.ofHours(1);
	private static final ZoneId ZONE = ZoneId.of("Europe/Berlin");

	@Test
	void replay() throws IOException {
		String dir = System.getenv("REPLAY_DIR");
		assumeTrue(dir != null && Files.isDirectory(Path.of(dir)), "REPLAY_DIR not set");
		Path folder = Path.of(dir);
		Plant plant = new PlantFolder(Path.of(System.getenv("REPLAY_PLANTS"))).plant(System.getenv("REPLAY_PLANT")).orElseThrow();
		Site site = site(Path.of(System.getenv("REPLAY_SITE_FILE")));

		WeatherReport report = W.createWeatherReport();
		SourceDataset icon = dataset("ICON-D2");
		SourceDataset station = dataset("MOSMIX_L");
		station.setStationId("X");
		TreeSet<LocalDate> days = new TreeSet<>();
		for (String[] row : rows(folder.resolve("replay_weather.csv"))) {
			Instant start = OffsetDateTime.parse(row[0]).toInstant();
			// a mean over the hour is valid at its end
			PlantForecasterTest.add(icon, MeasurementKind.DIRECT_RADIATION, Level.SURFACE, Statistic.MEAN, H, start.plus(H),
					Double.parseDouble(row[1]));
			PlantForecasterTest.add(icon, MeasurementKind.DIFFUSE_RADIATION, Level.SURFACE, Statistic.MEAN, H, start.plus(H),
					Double.parseDouble(row[2]));
			days.add(LocalDate.ofInstant(start, ZONE));
		}
		for (String[] row : rows(folder.resolve("replay_station.csv"))) {
			Instant at = OffsetDateTime.parse(row[0]).toInstant();
			PlantForecasterTest.add(station, MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, null, at,
					Double.parseDouble(row[1]));
			PlantForecasterTest.add(station, MeasurementKind.WIND_SPEED, Level.GROUND_10M, Statistic.INSTANT, null, at,
					Double.parseDouble(row[2]));
		}
		report.getDatasets().add(icon);
		report.getDatasets().add(station);

		SpaSolarService solar = new SpaSolarService();
		PlantForecaster forecaster = new PlantForecaster(24, 1, (lat, lon, t) -> {
			GeoPosition p = W.createGeoPosition();
			p.setLatitude(lat);
			p.setLongitude(lon);
			SolarPosition s = solar.positionAt(p, t);
			return new double[] { s.elevation(), s.azimuth() };
		});
		try (Writer out = Files.newBufferedWriter(folder.resolve("replay_model.csv"), StandardCharsets.UTF_8)) {
			out.write("hourStartUtc,dcW,acW,planeIrradiance,cellTemperature,sunElevation,sunAzimuth,shaded\n");
			for (LocalDate day : days) {
				Instant midnight = day.atStartOfDay(ZONE).toInstant();
				PvOutlook o = forecaster.forecast(plant, site, Optional.of(report), ZONE, midnight);
				for (PvHour h : o.getHours()) {
					if (LocalDate.ofInstant(h.getTime().toInstant(), ZONE).equals(day)) {
						out.write(String.format(Locale.ROOT, "%s,%.1f,%.1f,%.1f,%.1f,%.2f,%.2f,%s%n", h.getTime().toInstant(),
								h.getDcPower() * 1000, h.getPower() * 1000, h.getPlaneIrradiance(), h.getCellTemperature(),
								h.getSunElevation(), h.getSunAzimuth(), h.isShaded()));
					}
				}
			}
		}
	}

	private static SourceDataset dataset(String product) {
		SourceDataset d = W.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(product);
		d.setIssuedAt(Instant.parse("2026-01-01T00:00:00Z"));
		return d;
	}

	/** The site's position from its stored XMI (gzip), bound to the replay's station "X". */
	private static Site site(Path file) throws IOException {
		String xmi;
		try (InputStream in = new GZIPInputStream(Files.newInputStream(file))) {
			xmi = new String(in.readAllBytes(), StandardCharsets.UTF_8);
		}
		Site s = W.createSite();
		s.setId("replay");
		s.setPosition(W.createGeoPosition());
		s.getPosition().setLatitude(attribute(xmi, "latitude"));
		s.getPosition().setLongitude(attribute(xmi, "longitude"));
		StationBinding b = W.createStationBinding();
		b.setProviderId("dwd");
		b.setProductId("MOSMIX_L");
		b.setRank(0);
		Station st = W.createStation();
		st.setId("X");
		b.setStation(st);
		s.getBindings().add(b);
		return s;
	}

	private static double attribute(String xmi, String name) {
		Matcher m = Pattern.compile(name + "=\"([^\"]+)\"").matcher(xmi);
		if (!m.find()) {
			throw new IllegalArgumentException("no " + name + " in the site file");
		}
		return Double.parseDouble(m.group(1));
	}

	private static List<String[]> rows(Path csv) throws IOException {
		return Files.readAllLines(csv).stream().skip(1).filter(l -> !l.isBlank()).map(l -> l.split(",")).toList();
	}
}
