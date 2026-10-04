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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

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
import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.Mounting;
import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * A made-up plant — two roof faces east and west on one inverter — against a hand-made report: ICON
 * radiation for the first day, MOSMIX global for the rest, and a sun that rises at 06 and sets at 18
 * UTC, due south at noon.
 */
class PlantForecasterTest {

	private static final WeatherFactory W = WeatherFactory.eINSTANCE;
	private static final PvFactory F = PvFactory.eINSTANCE;
	private static final ZoneId UTC = ZoneId.of("UTC");
	private static final Instant DAY = Instant.parse("2026-06-21T00:00:00Z");
	private static final Instant NOW = DAY.plus(Duration.ofMinutes(5));
	private static final Duration H = Duration.ofHours(1);

	@TempDir
	Path tmp;

	/** Elevation 60·sin(π (t−6)/12) between 06 and 18 UTC, azimuth 90 → 270 linearly */
	static final PlantForecaster.Sun SUN = (lat, lon, t) -> {
		double hour = (t.getEpochSecond() % 86_400) / 3600.0;
		double e = hour > 6 && hour < 18 ? 60 * Math.sin(Math.PI * (hour - 6) / 12) : -10;
		return new double[] { e, 90 + 15 * (hour - 6) };
	};

	static Plant plant() {
		Plant p = F.createPlant();
		p.setId("demo");
		p.setName("Beispieldach");
		p.setSiteId("home");
		p.setSystemLosses(10);
		Inverter inv = F.createInverter();
		inv.setName("WR");
		inv.setAcPower(6);
		inv.setEfficiency(0.96);
		p.getInverters().add(inv);
		p.getArrays().add(array("Ost", 90, 30, 4, inv));
		p.getArrays().add(array("West", 270, 30, 5, inv));
		return p;
	}

	static PvArray array(String name, double azimuth, double tilt, double kwp, Inverter inv) {
		PvArray a = F.createPvArray();
		a.setName(name);
		a.setAzimuth(azimuth);
		a.setTilt(tilt);
		a.setPeakPower(kwp);
		a.setMounting(Mounting.ROOF_MOUNTED);
		a.setInverter(inv);
		return a;
	}

	static Site site() {
		Site s = W.createSite();
		s.setId("home");
		s.setPosition(W.createGeoPosition());
		s.getPosition().setLatitude(50.9);
		s.getPosition().setLongitude(12.3);
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

	/** Clear-sky-like radiation: global = 900·sin(e), on day one split 80/20 by ICON, from MOSMIX after */
	static WeatherReport report() {
		WeatherReport r = W.createWeatherReport();
		SourceDataset mosmix = dataset("MOSMIX_L");
		mosmix.setStationId("X");
		SourceDataset icon = dataset("ICON-D2");
		for (int i = 0; i <= 24 * 7; i++) {
			Instant t = DAY.plus(H.multipliedBy(i));
			add(mosmix, MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, null, t, 20);
			add(mosmix, MeasurementKind.WIND_SPEED, Level.GROUND_10M, Statistic.INSTANT, null, t, 2);
			double e = SUN.at(0, 0, t.minus(Duration.ofMinutes(30)))[0];
			double global = e > 0 ? 900 * Math.sin(Math.toRadians(e)) : 0;
			add(mosmix, MeasurementKind.GLOBAL_RADIATION, Level.SURFACE, Statistic.MEAN, H, t, global);
			if (i <= 24) {
				add(icon, MeasurementKind.DIRECT_RADIATION, Level.SURFACE, Statistic.MEAN, H, t, 0.8 * global);
				add(icon, MeasurementKind.DIFFUSE_RADIATION, Level.SURFACE, Statistic.MEAN, H, t, 0.2 * global);
			}
		}
		r.getDatasets().add(mosmix);
		r.getDatasets().add(icon);
		return r;
	}

	static SourceDataset dataset(String product) {
		SourceDataset d = W.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(product);
		d.setIssuedAt(DAY);
		return d;
	}

	static void add(SourceDataset ds, MeasurementKind kind, Level level, Statistic statistic, Duration period, Instant at, double value) {
		MeasuredValue v = W.createMeasuredValue();
		v.setKind(kind);
		v.setLevel(level);
		v.setStatistic(statistic);
		if (period != null) {
			v.setPeriod(period);
		}
		v.setValidAt(at);
		v.setValue(value);
		ds.getValues().add(v);
	}

	@Test
	void hoursFollowTheSunAndTheSourceChangesAfterIcon() {
		PvOutlook o = new PlantForecaster(48, 7, SUN).forecast(plant(), site(), Optional.of(report()), UTC, NOW);
		assertThat(o.getPeakPower()).isEqualTo(9.0);
		assertThat(o.getArrays()).extracting(a -> a.getName()).containsExactly("Ost", "West");
		assertThat(o.getHours()).hasSize(48);
		PvHour midnight = o.getHours().get(0);
		assertThat(midnight.getTime()).isEqualTo(Date.from(DAY));
		assertThat(midnight.getPower()).isZero();
		PvHour morning = o.getHours().get(8); // 08–09, sun in the east
		assertThat(morning.getArrayPower().get(0)).as("east beats west in the morning").isGreaterThan(morning.getArrayPower().get(1));
		PvHour evening = o.getHours().get(15);
		assertThat(evening.getArrayPower().get(1)).isGreaterThan(evening.getArrayPower().get(0));
		assertThat(morning.getSource()).isEqualTo("ICON-D2");
		assertThat(o.getHours().get(32).getSource()).as("day two: MOSMIX, split by Erbs").isEqualTo("MOSMIX_L");
		assertThat(morning.getPower()).isCloseTo(morning.getDcPower() * 0.96, within(1e-9));
		assertThat(morning.getCellTemperature()).isGreaterThan(20);
	}

	@Test
	void anInverterCeilingClips() {
		Plant small = plant();
		small.getInverters().get(0).setAcPower(2.5);
		PvOutlook o = new PlantForecaster(48, 7, SUN).forecast(small, site(), Optional.of(report()), UTC, NOW);
		PvHour noon = o.getHours().get(11);
		assertThat(noon.getPower()).isEqualTo(2.5);
		assertThat(noon.isClipped()).isTrue();
		assertThat(o.getHours().stream().mapToDouble(PvHour::getPower).max().orElseThrow()).isEqualTo(2.5);
	}

	@Test
	void daysSumTheHoursAndSayWhatTheyAreBasedOn() {
		PvOutlook o = new PlantForecaster(48, 7, SUN).forecast(plant(), site(), Optional.of(report()), UTC, NOW);
		assertThat(o.getDays()).hasSize(7);
		PvDay first = o.getDays().get(0);
		double sum = o.getHours().subList(0, 24).stream().mapToDouble(PvHour::getPower).sum();
		assertThat(first.getEnergy()).isCloseTo(sum, within(1e-9));
		assertThat(first.getSpecificYield()).isCloseTo(sum / 9.0, within(1e-9));
		assertThat(first.getSource()).isEqualTo("ICON-D2");
		assertThat(first.getHoursCovered()).isEqualTo(24);
		assertThat(first.getPeakTime()).isNotNull();
		assertThat(o.getDays().get(2).getSource()).isEqualTo("MOSMIX_L");
		assertThat(o.getDays().get(2).getEnergy()).as("a split by Erbs gives a similar day").isCloseTo(first.getEnergy(), within(first.getEnergy() * 0.3));
		assertThat(first.getEnergy() / 9.0).as("a clear midsummer day on east/west: 4–8 kWh/kWp").isBetween(4.0, 8.0);
	}

	@Test
	void aForestInTheWestCostsTheEvening() {
		Plant shaded = plant();
		shaded.setMountingHeight(5);
		Obstacle forest = F.createObstacle();
		forest.setAzimuthFrom(230);
		forest.setAzimuthTo(300);
		forest.setDistance(30);
		forest.setHeight(30);
		shaded.getObstacles().add(forest);
		PvOutlook open = new PlantForecaster(48, 7, SUN).forecast(plant(), site(), Optional.of(report()), UTC, NOW);
		PvOutlook dark = new PlantForecaster(48, 7, SUN).forecast(shaded, site(), Optional.of(report()), UTC, NOW);
		assertThat(dark.getHours().get(16).isShaded()).isTrue();
		assertThat(dark.getHours().get(16).getPower()).isLessThan(open.getHours().get(16).getPower() * 0.6);
		assertThat(dark.getHours().get(9).getPower()).as("the morning is untouched but for the sky the forest hides")
				.isCloseTo(open.getHours().get(9).getPower(), within(open.getHours().get(9).getPower() * 0.05));
		assertThat(dark.getDays().get(0).getEnergy()).isLessThan(open.getDays().get(0).getEnergy());
	}

	@Test
	void withoutReportOnlyTheHead() {
		PvOutlook o = new PlantForecaster(48, 7, SUN).forecast(plant(), site(), Optional.empty(), UTC, NOW);
		assertThat(o.getPlantId()).isEqualTo("demo");
		assertThat(o.getHours()).isEmpty();
		assertThat(o.getDays()).isEmpty();
	}

	@Test
	void frozenHoursFillWhatTheReportNoLongerCovers() {
		// a report that begins at noon, as the afternoon runs do
		WeatherReport late = report();
		late.getDatasets().forEach(d -> d.getValues().removeIf(v -> v.getValidAt() != null && v.getValidAt().isBefore(DAY.plus(Duration.ofHours(12)))));
		PlantForecaster f = new PlantForecaster(48, 2, SUN);
		PvOutlook bare = f.forecast(plant(), site(), Optional.of(late), UTC, DAY.plus(Duration.ofHours(14)));
		assertThat(bare.getHours().stream().map(h -> h.getTime().toInstant())).as("the morning is gone")
				.doesNotContain(DAY.plus(Duration.ofHours(9)));

		PvHour frozenNine = PvFactory.eINSTANCE.createPvHour();
		frozenNine.setTime(Date.from(DAY.plus(Duration.ofHours(9))));
		frozenNine.setPower(1.5);
		frozenNine.setDcPower(1.6);
		frozenNine.setSunElevation(40);
		frozenNine.setSunAzimuth(135);
		frozenNine.setSource("ICON-D2");
		PvOutlook filled = f.forecast(plant(), site(), Optional.of(late), UTC, DAY.plus(Duration.ofHours(14)),
				Map.of(DAY.plus(Duration.ofHours(9)), frozenNine));
		PvHour nine = filled.getHours().stream().filter(h -> h.getTime().toInstant().equals(DAY.plus(Duration.ofHours(9)))).findFirst().orElseThrow();
		assertThat(nine.getPower()).isEqualTo(1.5);
		assertThat(nine.getSource()).isEqualTo("ICON-D2 " + PlantForecaster.FROZEN);
		assertThat(nine.getSunAzimuth()).isEqualTo(135);
		assertThat(filled.getDays().get(0).getEnergy()).as("the frozen hour counts for the day").isGreaterThan(bare.getDays().get(0).getEnergy() + 1.4);
	}

	@Test
	void profilesRoundTripThroughTheFolder() throws IOException {
		PlantFolder folder = new PlantFolder(tmp.resolve("plants"));
		assertThat(folder.plants()).isEmpty();
		folder.save(plant());
		assertThat(tmp.resolve("plants/demo.xmi")).isRegularFile();
		Files.writeString(tmp.resolve("plants/broken.xmi"), "<not xmi");
		Plant back = folder.plant("demo").orElseThrow();
		assertThat(back.getArrays()).hasSize(2);
		assertThat(back.getArrays().get(1).getInverter()).as("the cross reference survives").isSameAs(back.getInverters().get(0));
		assertThat(back.getArrays().get(0).getTemperatureCoefficient()).isEqualTo(-0.37);
		assertThat(folder.plants()).hasSize(1);

		Plant renamed = plant();
		renamed.setId("../escape");
		assertThatThrownBy(() -> folder.save(renamed)).isInstanceOf(IllegalArgumentException.class);
		renamed.setId(null);
		assertThatThrownBy(() -> folder.save(renamed)).isInstanceOf(IllegalArgumentException.class);
		renamed.setId("balkon.zuhause");
		folder.save(renamed);
		assertThat(folder.plants()).hasSize(2);
	}
}
