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
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.model.pv.PvMeasurementLog;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.pv.spi.PvMeter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Store, aggregation and poller of the meter readings — with a fake meter and a hand-driven clock. */
class MeteringTest {

	private static final PvFactory F = PvFactory.eINSTANCE;
	private static final ZoneId UTC = ZoneOffset.UTC;
	private static final Instant T0 = Instant.parse("2026-06-21T10:00:00Z");

	@TempDir
	Path tmp;

	static PvMeasurement reading(Instant t, double kW) {
		PvMeasurement m = F.createPvMeasurement();
		m.setTime(Date.from(t));
		m.setPvPower(kW);
		return m;
	}

	@Test
	void storeAppendsPerDayAndReadsBackAfterRestart() throws IOException {
		MeasurementStore store = new MeasurementStore(tmp);
		LocalDate day = LocalDate.of(2026, 6, 21);
		store.append("demo", "fake", day, reading(T0, 1.0));
		store.append("demo", "fake", day, reading(T0.plusSeconds(60), 2.0));
		store.append("demo", "fake", day.plusDays(1), reading(T0.plus(Duration.ofDays(1)), 3.0));

		assertThat(Files.isRegularFile(tmp.resolve("demo/2026-06-21.xmi"))).isTrue();
		assertThat(Files.isRegularFile(tmp.resolve("demo/2026-06-22.xmi"))).isTrue();

		PvMeasurementLog log = new MeasurementStore(tmp).log("demo", day).orElseThrow();
		assertThat(log.getPlantId()).isEqualTo("demo");
		assertThat(log.getMeterType()).isEqualTo("fake");
		assertThat(log.getMeasurements()).extracting(PvMeasurement::getPvPower).containsExactly(1.0, 2.0);
		assertThat(log.getMeasurements().get(0).isSetLoadPower()).isFalse();
		assertThat(store.log("demo", day.minusDays(1))).isEmpty();
	}

	@Test
	void storeRejectsPathsAsPlantIds() {
		MeasurementStore store = new MeasurementStore(tmp);
		assertThatThrownBy(() -> store.log("../etc", LocalDate.now())).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> store.log("a/b", LocalDate.now())).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void meanAndEnergySkipLongGaps() {
		List<PvMeasurement> r = List.of(reading(T0, 1.0), reading(T0.plus(Duration.ofMinutes(10)), 3.0),
				reading(T0.plus(Duration.ofMinutes(20)), 5.0),
				// two hours without readings — the inverter slept or the link was down
				reading(T0.plus(Duration.ofMinutes(180)), 5.0), reading(T0.plus(Duration.ofMinutes(190)), 5.0));
		assertThat(Measured.meanPower(r, T0, T0.plus(Duration.ofHours(1))).getAsDouble()).isCloseTo(3.0, within(1e-9));
		assertThat(Measured.meanPower(r, T0.minus(Duration.ofHours(1)), T0)).isEmpty();
		// 1/6 h × 2 kW + 1/6 h × 4 kW + (gap of 160 min skipped) + 1/6 h × 5 kW
		assertThat(Measured.energy(r, T0, T0.plus(Duration.ofHours(5))).getAsDouble()).isCloseTo((2.0 + 4.0 + 5.0) / 6,
				within(1e-9));
		assertThat(Measured.energy(r.subList(0, 1), T0, T0.plus(Duration.ofHours(5)))).isEmpty();
	}

	/** A clock the test moves. */
	static final class StepClock extends Clock {
		Instant now = T0;

		@Override
		public ZoneId getZone() {
			return UTC;
		}

		@Override
		public Clock withZone(ZoneId zone) {
			return this;
		}

		@Override
		public Instant instant() {
			return now;
		}
	}

	static Plant plant(String id, String type, int interval) {
		Plant p = F.createPlant();
		p.setId(id);
		Meter m = F.createMeter();
		m.setType(type);
		m.setUrl("http://inverter.invalid");
		m.setInterval(interval);
		p.setMeter(m);
		return p;
	}

	@Test
	void pollerReadsEachMeterAtItsIntervalAndSurvivesFailures() {
		StepClock clock = new StepClock();
		MeasurementStore store = new MeasurementStore(tmp);
		AtomicInteger calls = new AtomicInteger();
		PvMeter good = meter -> {
			calls.incrementAndGet();
			PvMeasurement m = F.createPvMeasurement();
			m.setPvPower(0.7);
			return m;
		};
		PvMeter broken = meter -> {
			throw new IOException("connection refused");
		};
		Plant noMeter = F.createPlant();
		noMeter.setId("plain");
		Plant disabled = plant("off", "good", 10);
		disabled.getMeter().setEnabled(false);
		List<Plant> plants = new ArrayList<>(List.of(plant("a", "good", 10), plant("b", "broken", 10), plant("c", "unknown", 10),
				noMeter, disabled));
		MeterPoller poller = new MeterPoller(() -> plants, store,
				type -> Optional.ofNullable(switch (type) {
					case "good" -> good;
					case "broken" -> broken;
					default -> null;
				}), p -> UTC, clock);

		poller.tick(); // reads a
		clock.now = T0.plusSeconds(5);
		poller.tick(); // not due
		clock.now = T0.plusSeconds(10);
		poller.tick(); // due again
		assertThat(calls).hasValue(2);

		PvMeasurementLog log = store.log("a", LocalDate.of(2026, 6, 21)).orElseThrow();
		assertThat(log.getMeasurements()).extracting(m -> m.getTime().toInstant()).containsExactly(T0, T0.plusSeconds(10));
		assertThat(store.log("b", LocalDate.of(2026, 6, 21))).isEmpty();
		assertThat(store.log("off", LocalDate.of(2026, 6, 21))).isEmpty();
	}

	@Test
	void pollerNeverReadsFasterThanTheMinimum() {
		StepClock clock = new StepClock();
		AtomicInteger calls = new AtomicInteger();
		PvMeter good = meter -> {
			calls.incrementAndGet();
			return F.createPvMeasurement();
		};
		MeterPoller poller = new MeterPoller(() -> List.of(plant("a", "good", 1)), new MeasurementStore(tmp),
				type -> Optional.of(good), p -> UTC, clock);
		poller.tick();
		clock.now = T0.plusSeconds(4);
		poller.tick();
		assertThat(calls).hasValue(1);
	}

	@Test
	void outlookGetsMeasuredPowerForBegunHoursAndTodaysEnergy() {
		PvOutlook outlook = F.createPvOutlook();
		for (int i = 0; i < 3; i++) {
			PvHour h = F.createPvHour();
			h.setTime(Date.from(T0.plus(Duration.ofHours(i))));
			outlook.getHours().add(h);
		}
		PvDay today = F.createPvDay();
		today.setDate("2026-06-21");
		PvDay tomorrow = F.createPvDay();
		tomorrow.setDate("2026-06-22");
		outlook.getDays().addAll(List.of(today, tomorrow));
		List<PvMeasurement> r = List.of(reading(T0.plus(Duration.ofMinutes(10)), 2.0), reading(T0.plus(Duration.ofMinutes(20)), 4.0));

		PvForecastComponent.addMeasured(outlook, r, UTC, T0.plus(Duration.ofMinutes(25)));

		assertThat(outlook.getHours().get(0).getMeasuredPower()).isCloseTo(3.0, within(1e-9));
		assertThat(outlook.getHours().get(1).isSetMeasuredPower()).isFalse();
		assertThat(today.getMeasuredEnergy()).isCloseTo(0.5, within(1e-9));
		assertThat(tomorrow.isSetMeasuredEnergy()).isFalse();
	}
}
