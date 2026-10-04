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
package org.gecko.weather.outlook.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.outlook.model.outlook.DayOutlook;
import org.gecko.weather.outlook.model.outlook.HourOutlook;
import org.gecko.weather.outlook.model.outlook.Outlook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The picking rule on a hand-made report: two MOSMIX stations (the rank-0 one must win), an ICON-D2
 * cell covering only the first hours, a UV dataset, the solar dataset and day events. Values encode
 * their hour so that every assertion says which value went where.
 */
class OutlookBuilderTest {

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;
	private static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");
	/** 06:40 UTC = 08:40 in Berlin; the outlook starts at 06:00 UTC */
	private static final Instant NOW = Instant.parse("2026-10-04T06:40:00Z");
	private static final Instant T0 = Instant.parse("2026-10-04T06:00:00Z");
	private static final Duration H = Duration.ofHours(1);

	private Site site;
	private WeatherReport report;

	@BeforeEach
	void setUp() {
		site = F.createSite();
		site.setId("home");
		site.setName("Home roof");
		site.setPosition(F.createGeoPosition());
		site.getPosition().setLatitude(51.05);
		site.getPosition().setLongitude(13.74);
		site.setTimeZone("Europe/Berlin");
		site.getBindings().add(stationBinding("O457", 1));
		site.getBindings().add(stationBinding("10487", 0));

		report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(NOW);
		// the other station first in the report: picking must go by rank, not by order
		report.getDatasets().add(mosmix("O457", 100));
		report.getDatasets().add(mosmix("10487", 0));
		report.getDatasets().add(icon(6));
		report.getDatasets().add(uv());
		report.getDatasets().add(solar());
		report.getDays().add(day(LocalDate.of(2026, 10, 4), "2026-10-04T05:15:00Z", "2026-10-04T16:45:00Z"));
		report.getDays().add(day(LocalDate.of(2026, 10, 5), "2026-10-05T05:17:00Z", "2026-10-05T16:43:00Z"));
		report.getDays().add(day(LocalDate.of(2026, 10, 6), "2026-10-06T05:19:00Z", "2026-10-06T16:41:00Z"));
	}

	@Test
	void hoursPickTheRank0StationIconWhereItCoversAndPeriodValuesOfTheHourAhead() {
		Outlook o = new OutlookBuilder(24, 2).build(site, Optional.of(report), BERLIN, NOW);

		assertThat(o.getSiteId()).isEqualTo("home");
		assertThat(o.getSiteName()).isEqualTo("Home roof");
		assertThat(o.getTimeZone()).isEqualTo("Europe/Berlin");
		assertThat(o.getGeneratedAt()).isEqualTo(Date.from(NOW));
		assertThat(o.getHours()).hasSize(24);

		HourOutlook first = o.getHours().get(0);
		assertThat(first.getTime()).isEqualTo(Date.from(T0));
		assertThat(first.getTemperature()).as("10487 at 06:00, not O457").isEqualTo(6.0);
		assertThat(first.getDewPoint()).isEqualTo(2.0);
		assertThat(first.getWindSpeed()).isEqualTo(3.0);
		assertThat(first.getWindDirection()).isEqualTo(270.0);
		// period values: the ones ending 07:00
		assertThat(first.getPrecipitation()).isCloseTo(0.7, within(1e-9));
		assertThat(first.getPrecipitationProbability()).isEqualTo(70.0);
		assertThat(first.getWindGust()).isEqualTo(7.0);
		assertThat(first.getWeatherCode()).isEqualTo(61);
		// ICON-D2 covers 06:00..11:00: cloud from ICON (1000 + hour), radiation = direct + diffuse ending 07:00
		assertThat(first.getCloudCover()).isEqualTo(1006.0);
		assertThat(first.getGlobalRadiation()).isEqualTo(70.0 + 7.0);
		assertThat(first.getSunElevation()).isEqualTo(6.0);
		assertThat(first.isDaylight()).as("06:30 UTC is after sunrise 05:15").isTrue();

		HourOutlook noon = o.getHours().get(6); // 12:00 UTC: ICON no longer covers it
		assertThat(noon.getCloudCover()).as("MOSMIX N where ICON-D2 ends").isEqualTo(12.0);
		assertThat(noon.getGlobalRadiation()).as("MOSMIX Rad1h ending 13:00").isEqualTo(130.0);

		HourOutlook night = o.getHours().get(14); // 20:00 UTC
		assertThat(night.isDaylight()).isFalse();
		assertThat(night.isSetCloudCover()).isTrue();

		HourOutlook last = o.getHours().get(23);
		assertThat(last.getTime()).isEqualTo(Date.from(T0.plus(H.multipliedBy(23))));
	}

	@Test
	void daysSummariseTheLocalCalendarDaysFromTomorrow() {
		Outlook o = new OutlookBuilder(24, 2).build(site, Optional.of(report), BERLIN, NOW);
		assertThat(o.getDays()).extracting(DayOutlook::getDate).containsExactly("2026-10-05", "2026-10-06");
		DayOutlook tomorrow = o.getDays().get(0);
		// local 5 Oct = 4 Oct 22:00 UTC .. 5 Oct 22:00 UTC; temperature = hour of day UTC, so min 0, max 23
		assertThat(tomorrow.getTemperatureMin()).isEqualTo(0.0);
		assertThat(tomorrow.getTemperatureMax()).isEqualTo(23.0);
		// precipitation 0.1 * hour, periods ending 23:00 (4th) .. 22:00 (5th)
		double expected = 0.1 * (23 + 0) + 0.1 * (1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 13 + 14 + 15 + 16 + 17 + 18 + 19 + 20 + 21 + 22);
		assertThat(tomorrow.getPrecipitation()).isCloseTo(expected, within(1e-9));
		assertThat(tomorrow.getPrecipitationProbability()).isEqualTo(230.0);
		assertThat(tomorrow.getSunshineHours()).isCloseTo(24 * 600 / 3600.0, within(1e-9));
		assertThat(tomorrow.getWindGustMax()).isEqualTo(23.0);
		assertThat(tomorrow.getWeatherCode()).as("rain 61 is more significant than fog 45 or clouds 3").isEqualTo(61);
		assertThat(tomorrow.getUvIndexMax()).isEqualTo(2.7);
		assertThat(tomorrow.getSunrise()).isEqualTo(Date.from(Instant.parse("2026-10-05T05:17:00Z")));
		assertThat(tomorrow.getSunset()).isEqualTo(Date.from(Instant.parse("2026-10-05T16:43:00Z")));
		assertThat(tomorrow.getDaylightHours()).isCloseTo(11.0 + 26.0 / 60, within(1e-9));
		assertThat(o.getDays().get(1).getUvIndexMax()).isEqualTo(2.8);
	}

	@Test
	void sourcesNameWhatWasUsed() {
		Outlook o = new OutlookBuilder(24, 2).build(site, Optional.of(report), BERLIN, NOW);
		assertThat(o.getSources()).extracting(n -> n.getProductId()).containsExactly("MOSMIX_L", "ICON-D2", "UVI", "solar");
		assertThat(o.getSources().get(0).getLocation()).isEqualTo("Station 10487");
		assertThat(o.getSources().get(0).getDistanceMeters()).isEqualTo(466.0);
		assertThat(o.getSources().get(0).getQuantities()).contains("Bewölkung/Strahlung wo ICON-D2 fehlt");
		assertThat(o.getSources().get(1).getLocation()).isEqualTo("Zelle 884,393 (icon-d2-regular-lat-lon)");
		assertThat(o.getSources().get(1).getIssuedAt()).isEqualTo(Date.from(Instant.parse("2026-10-04T00:00:00Z")));
	}

	@Test
	void withoutReportOnlyTheHead() {
		Outlook o = new OutlookBuilder(24, 2).build(site, Optional.empty(), BERLIN, NOW);
		assertThat(o.getSiteId()).isEqualTo("home");
		assertThat(o.getHours()).isEmpty();
		assertThat(o.getDays()).isEmpty();
		assertThat(o.getSources()).isEmpty();
	}

	@Test
	void significanceOrdersWeather() {
		assertThat(OutlookBuilder.significance(95)).isGreaterThan(OutlookBuilder.significance(71));
		assertThat(OutlookBuilder.significance(71)).isGreaterThan(OutlookBuilder.significance(61));
		assertThat(OutlookBuilder.significance(86)).as("snow shower").isGreaterThan(OutlookBuilder.significance(81));
		assertThat(OutlookBuilder.significance(61)).isGreaterThan(OutlookBuilder.significance(53));
		assertThat(OutlookBuilder.significance(53)).isGreaterThan(OutlookBuilder.significance(45));
		assertThat(OutlookBuilder.significance(45)).isGreaterThan(OutlookBuilder.significance(3));
	}

	// --- fixtures ----------------------------------------------------------------------------

	private static StationBinding stationBinding(String id, int rank) {
		StationBinding b = F.createStationBinding();
		b.setProviderId("dwd");
		b.setProductId("MOSMIX_L");
		b.setRank(rank);
		Station s = F.createStation();
		s.setId(id);
		b.setStation(s);
		return b;
	}

	/** 48 h from 4 Oct 00:00 UTC; every value encodes the UTC hour (plus an offset for the other station) */
	private static SourceDataset mosmix(String station, int offset) {
		SourceDataset ds = dataset("dwd", "MOSMIX_L", Instant.parse("2026-10-04T03:00:00Z"));
		ds.setStationId(station);
		ds.setDistanceMeters("10487".equals(station) ? 466 : 4149);
		Instant start = Instant.parse("2026-10-04T00:00:00Z");
		for (int i = 0; i <= 72; i++) {
			Instant t = start.plus(H.multipliedBy(i));
			int hour = (i % 24);
			add(ds, MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, null, t, hour + offset);
			add(ds, MeasurementKind.DEW_POINT, Level.GROUND_2M, Statistic.INSTANT, null, t, 2 + offset);
			add(ds, MeasurementKind.WIND_SPEED, Level.GROUND_10M, Statistic.INSTANT, null, t, 3 + offset);
			add(ds, MeasurementKind.WIND_DIRECTION, Level.GROUND_10M, Statistic.INSTANT, null, t, 270 + offset);
			add(ds, MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, Statistic.INSTANT, null, t, hour + offset);
			add(ds, MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.ACCUMULATED, H, t, 0.1 * hour + offset);
			MeasuredValue p = add(ds, MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.PROBABILITY, H, t, 10 * hour + offset);
			p.setThreshold(0.1);
			MeasuredValue p5 = add(ds, MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.PROBABILITY, H, t, 999);
			p5.setThreshold(5.0);
			add(ds, MeasurementKind.WIND_GUST, Level.GROUND_10M, Statistic.MAX, H, t, hour + offset);
			add(ds, MeasurementKind.GLOBAL_RADIATION, Level.SURFACE, Statistic.MEAN, H, t, 10 * hour + offset);
			add(ds, MeasurementKind.SUNSHINE_DURATION, Level.SURFACE, Statistic.ACCUMULATED, H, t, 600 + offset);
			MeasuredValue ww = F.createMeasuredValue();
			ww.setKind(MeasurementKind.SIGNIFICANT_WEATHER);
			ww.setLevel(Level.UNSPECIFIED);
			ww.setStatistic(Statistic.INSTANT);
			ww.setValidAt(t);
			ww.setCode(hour == 7 ? 61 : hour == 3 ? 45 : 3);
			ds.getValues().add(ww);
		}
		return ds;
	}

	private static SourceDataset icon(int hours) {
		SourceDataset ds = dataset("dwd", "ICON-D2", Instant.parse("2026-10-04T00:00:00Z"));
		GridCell cell = F.createGridCell();
		cell.setGridId("icon-d2-regular-lat-lon");
		cell.setI(884);
		cell.setJ(393);
		ds.setCell(cell);
		ds.setDistanceMeters(1112);
		for (int i = 0; i < hours; i++) {
			Instant t = T0.plus(H.multipliedBy(i));
			add(ds, MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, Statistic.INSTANT, null, t, 1000 + 6 + i);
			add(ds, MeasurementKind.DIRECT_RADIATION, Level.SURFACE, Statistic.MEAN, H, t.plus(H), 10.0 * (7 + i));
			add(ds, MeasurementKind.DIFFUSE_RADIATION, Level.SURFACE, Statistic.MEAN, H, t.plus(H), 7 + i);
		}
		return ds;
	}

	private static SourceDataset uv() {
		SourceDataset ds = dataset("dwd", "UVI", Instant.parse("2026-10-04T04:27:38Z"));
		add(ds, MeasurementKind.UV_INDEX, Level.SURFACE, Statistic.MAX, Duration.ofDays(1), Instant.parse("2026-10-05T00:00:00Z"), 2.0);
		add(ds, MeasurementKind.UV_INDEX, Level.SURFACE, Statistic.MAX, Duration.ofDays(1), Instant.parse("2026-10-06T00:00:00Z"), 2.7);
		add(ds, MeasurementKind.UV_INDEX, Level.SURFACE, Statistic.MAX, Duration.ofDays(1), Instant.parse("2026-10-07T00:00:00Z"), 2.8);
		return ds;
	}

	private static SourceDataset solar() {
		SourceDataset ds = dataset(WeatherConstants.COMPUTED_PROVIDER_ID, WeatherConstants.SOLAR_PRODUCT_ID, NOW);
		for (int i = 0; i < 48; i++) {
			Instant t = Instant.parse("2026-10-04T00:00:00Z").plus(H.multipliedBy(i));
			add(ds, MeasurementKind.SUN_ELEVATION, Level.UNSPECIFIED, Statistic.INSTANT, null, t, i % 24);
		}
		return ds;
	}

	private static DayInfo day(LocalDate date, String sunrise, String sunset) {
		DayInfo d = F.createDayInfo();
		d.setDate(date);
		d.setSunrise(Instant.parse(sunrise));
		d.setSunset(Instant.parse(sunset));
		d.setDayLength(Duration.between(Instant.parse(sunrise), Instant.parse(sunset)));
		return d;
	}

	private static SourceDataset dataset(String provider, String product, Instant issued) {
		SourceDataset ds = F.createSourceDataset();
		ds.setProviderId(provider);
		ds.setProductId(product);
		ds.setIssuedAt(issued);
		return ds;
	}

	private static MeasuredValue add(SourceDataset ds, MeasurementKind kind, Level level, Statistic statistic, Duration period,
			Instant at, double value) {
		MeasuredValue v = F.createMeasuredValue();
		v.setKind(kind);
		v.setLevel(level);
		v.setStatistic(statistic);
		if (period != null) {
			v.setPeriod(period);
		}
		v.setValidAt(at);
		v.setValue(value);
		ds.getValues().add(v);
		return v;
	}
}
