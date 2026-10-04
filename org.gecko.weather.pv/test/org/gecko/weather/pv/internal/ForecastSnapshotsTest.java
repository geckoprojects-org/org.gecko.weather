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

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Freezing at the configured local hours, once per hour, surviving a repeated tick. */
class ForecastSnapshotsTest {

	private static final PvFactory F = PvFactory.eINSTANCE;
	private static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");

	@TempDir
	Path tmp;

	static Plant plant(String id) {
		Plant p = F.createPlant();
		p.setId(id);
		return p;
	}

	@Test
	void parsesHours() {
		assertThat(ForecastSnapshots.hours("6,18")).containsExactlyInAnyOrder(6, 18);
		assertThat(ForecastSnapshots.hours(" 6; 18 ,25")).containsExactlyInAnyOrder(6, 18);
		assertThat(ForecastSnapshots.hours("")).isEmpty();
		assertThat(ForecastSnapshots.hours(null)).isEmpty();
	}

	@Test
	void freezesOncePerPlantAndHourInLocalTime() {
		AtomicInteger calls = new AtomicInteger();
		ForecastSnapshots s = new ForecastSnapshots(tmp, Set.of(6, 18), () -> List.of(plant("a"), plant("b")), p -> BERLIN, id -> {
			calls.incrementAndGet();
			PvOutlook o = F.createPvOutlook();
			o.setPlantId(id);
			return o;
		});
		// 05:30 Berlin: nothing due
		s.tick(Instant.parse("2026-10-05T03:30:00Z"));
		assertThat(calls).hasValue(0);
		// 06:01 Berlin (04:01 UTC): both plants
		s.tick(Instant.parse("2026-10-05T04:01:00Z"));
		assertThat(calls).hasValue(2);
		assertThat(tmp.resolve("a/2026-10-05T06.xmi")).isRegularFile();
		assertThat(tmp.resolve("b/2026-10-05T06.xmi")).isRegularFile();
		// same hour again: files exist, nothing written
		s.tick(Instant.parse("2026-10-05T04:40:00Z"));
		assertThat(calls).hasValue(2);
		// 18:05 Berlin
		s.tick(Instant.parse("2026-10-05T16:05:00Z"));
		assertThat(calls).hasValue(4);
		assertThat(tmp.resolve("a/2026-10-05T18.xmi")).isRegularFile();
	}

	@Test
	void frozenHoursOfADayComeFromItsSnapshotsNewerOverOlder() {
		AtomicInteger issue = new AtomicInteger();
		ForecastSnapshots s = new ForecastSnapshots(tmp, Set.of(6, 18), () -> List.of(plant("a")), p -> BERLIN, id -> {
			PvOutlook o = F.createPvOutlook();
			o.setPlantId(id);
			int n = issue.incrementAndGet();
			for (int h = 0; h < 3; h++) {
				PvHour hour = F.createPvHour();
				hour.setTime(Date.from(Instant.parse("2026-10-05T0" + (6 + h) + ":00:00Z")));
				hour.setPower(n); // which snapshot an hour came from
				hour.setSource("ICON-D2");
				o.getHours().add(hour);
			}
			return o;
		});
		s.tick(Instant.parse("2026-10-04T16:05:00Z")); // 18:05 the evening before: issue 1
		s.tick(Instant.parse("2026-10-05T04:05:00Z")); // 06:05 that morning: issue 2
		s.tick(Instant.parse("2026-10-06T04:05:00Z")); // the next day, not ours: issue 3
		var frozen = s.frozenHours(plant("a"), LocalDate.of(2026, 10, 5));
		assertThat(frozen).hasSize(3);
		assertThat(frozen.get(Instant.parse("2026-10-05T07:00:00Z")).getPower()).as("the morning snapshot wins").isEqualTo(2.0);
		assertThat(s.frozenHours(plant("nobody"), LocalDate.of(2026, 10, 5))).isEmpty();
	}

	@Test
	void aMissedHourIsCaughtUpUnderTheRealHour() {
		AtomicInteger calls = new AtomicInteger();
		ForecastSnapshots s = new ForecastSnapshots(tmp, Set.of(6, 18), () -> List.of(plant("a")), p -> BERLIN, id -> {
			calls.incrementAndGet();
			PvOutlook o = F.createPvOutlook();
			o.setPlantId(id);
			return o;
		});
		// the runtime starts at 09:20 Berlin: the 06:00 snapshot never happened
		s.tick(Instant.parse("2026-10-05T07:20:00Z"));
		assertThat(calls).hasValue(1);
		assertThat(tmp.resolve("a/2026-10-05T09.xmi")).isRegularFile();
		// later the same morning: the catch-up counts, nothing more until 18
		s.tick(Instant.parse("2026-10-05T09:00:00Z"));
		assertThat(calls).hasValue(1);
		// 18:30: the regular slot
		s.tick(Instant.parse("2026-10-05T16:30:00Z"));
		assertThat(calls).hasValue(2);
		// 20:00 after a restart: 18 exists, nothing to catch up
		s.tick(Instant.parse("2026-10-05T18:00:00Z"));
		assertThat(calls).hasValue(2);
		// 03:00 the next night: before the first slot, nothing due
		s.tick(Instant.parse("2026-10-06T01:00:00Z"));
		assertThat(calls).hasValue(2);
	}

	@Test
	void aFailingPlantDoesNotStopTheOthers() throws Exception {
		ForecastSnapshots s = new ForecastSnapshots(tmp, Set.of(6), () -> List.of(plant("broken"), plant("ok")), p -> BERLIN, id -> {
			if (id.equals("broken")) {
				throw new IllegalStateException("no site");
			}
			PvOutlook o = F.createPvOutlook();
			o.setPlantId(id);
			return o;
		});
		s.tick(Instant.parse("2026-10-05T04:10:00Z"));
		assertThat(tmp.resolve("ok/2026-10-05T06.xmi")).isRegularFile();
		assertThat(Files.exists(tmp.resolve("broken"))).isFalse();
		assertThat(Files.readString(tmp.resolve("ok/2026-10-05T06.xmi"))).contains("plantId=\"ok\"");
	}
}
