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
package org.gecko.weather.solar.time4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.gecko.weather.api.solar.SolarPosition;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.WeatherFactory;
import org.junit.jupiter.api.Test;

/**
 * Checked against geometry rather than against another library: the noon elevation is
 * {@code 90° − |φ − δ|} (latitude minus solar declination), the noon azimuth is south, day lengths
 * at the solstices are well known, and polar day and night are unambiguous.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class Time4jSolarServiceTest {

	private static final Instant NOW = Instant.parse("2026-10-03T06:00:00Z");
	private static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");

	/** Dresden: 51.05° N, 13.74° E, 118 m. */
	private static final GeoPosition DRESDEN = position(51.05, 13.74, 118);
	/** Longyearbyen: 78.22° N, 15.63° E. */
	private static final GeoPosition SVALBARD = position(78.22, 15.63, 0);
	/** Tokyo: 35.68° N, 139.69° E. */
	private static final GeoPosition TOKYO = position(35.68, 139.69, 40);
	/** Cape Town: 33.93° S, 18.42° E. */
	private static final GeoPosition CAPE_TOWN = position(-33.93, 18.42, 0);

	private final Time4jSolarService service = new Time4jSolarService(Clock.fixed(NOW, ZoneOffset.UTC));

	@Test
	void noonElevationFollowsDeclinationAndAzimuthIsSouth() {
		// 21 June: declination ≈ +23.44°
		DayInfo midsummer = service.dayInfo(DRESDEN, LocalDate.of(2026, 6, 21), BERLIN);
		assertThat(midsummer.getMaxSunElevation()).isCloseTo(90 - (51.05 - 23.44), within(0.3));
		SolarPosition atNoon = service.positionAt(DRESDEN, midsummer.getSolarNoon());
		assertThat(atNoon.elevation()).isCloseTo(midsummer.getMaxSunElevation(), within(0.05));
		assertThat(atNoon.azimuth()).isCloseTo(180, within(1.0));
		assertThat(atNoon.isUp()).isTrue();

		// 21 December: declination ≈ −23.44°
		DayInfo midwinter = service.dayInfo(DRESDEN, LocalDate.of(2026, 12, 21), BERLIN);
		assertThat(midwinter.getMaxSunElevation()).isCloseTo(90 - (51.05 + 23.44), within(0.3));
	}

	@Test
	void dayLengthsAtTheSolstices() {
		DayInfo midsummer = service.dayInfo(DRESDEN, LocalDate.of(2026, 6, 21), BERLIN);
		assertThat(midsummer.getDayLength()).isBetween(Duration.ofMinutes(16 * 60 + 15), Duration.ofMinutes(16 * 60 + 50));
		assertThat(Duration.between(midsummer.getSunrise(), midsummer.getSunset()))
				.isCloseTo(midsummer.getDayLength(), Duration.ofMinutes(2));

		DayInfo midwinter = service.dayInfo(DRESDEN, LocalDate.of(2026, 12, 21), BERLIN);
		assertThat(midwinter.getDayLength()).isBetween(Duration.ofMinutes(7 * 60 + 40), Duration.ofMinutes(8 * 60 + 10));
	}

	@Test
	void eventsAreOrderedAndFallOnTheRequestedCivilDate() {
		LocalDate date = LocalDate.of(2026, 10, 3);
		DayInfo day = service.dayInfo(DRESDEN, date, BERLIN);

		assertThat(day.getDate()).isEqualTo(date);
		assertThat(day.getNauticalDawn()).isBefore(day.getCivilDawn());
		assertThat(day.getCivilDawn()).isBefore(day.getSunrise());
		assertThat(day.getSunrise()).isBefore(day.getSolarNoon());
		assertThat(day.getSolarNoon()).isBefore(day.getSunset());
		assertThat(day.getSunset()).isBefore(day.getCivilDusk());
		assertThat(day.getCivilDusk()).isBefore(day.getNauticalDusk());
		for (Instant event : new Instant[] { day.getNauticalDawn(), day.getSunrise(), day.getSolarNoon(), day.getSunset(),
				day.getNauticalDusk() }) {
			assertThat(event.atZone(BERLIN).toLocalDate()).isEqualTo(date);
		}
		// early October in Dresden: sunrise around 07:10 CEST, sunset around 18:45 CEST
		assertThat(day.getSunrise().atZone(BERLIN).toLocalTime()).isBetween(LocalTime.of(6, 55), LocalTime.of(7, 25));
		assertThat(day.getSunset().atZone(BERLIN).toLocalTime()).isBetween(LocalTime.of(18, 30), LocalTime.of(19, 0));
	}

	@Test
	void observerZoneDecidesWhichDayIsMeant() {
		// Tokyo's 3 October begins at 2 October 15:00 UTC; a platform-zone implementation gets this wrong
		LocalDate date = LocalDate.of(2026, 10, 3);
		ZoneId tokyo = ZoneId.of("Asia/Tokyo");
		DayInfo day = service.dayInfo(TOKYO, date, tokyo);
		assertThat(day.getSunrise().atZone(tokyo).toLocalDate()).isEqualTo(date);
		assertThat(day.getSunrise().atZone(tokyo).toLocalTime()).isBetween(LocalTime.of(5, 25), LocalTime.of(5, 50));
		assertThat(day.getSunset().atZone(tokyo).toLocalDate()).isEqualTo(date);
	}

	@Test
	void southernHemisphereNoonIsNorth() {
		DayInfo day = service.dayInfo(CAPE_TOWN, LocalDate.of(2026, 6, 21), ZoneId.of("Africa/Johannesburg"));
		SolarPosition atNoon = service.positionAt(CAPE_TOWN, day.getSolarNoon());
		assertThat(atNoon.azimuth()).isCloseTo(0, within(1.0)); // or 360 — Time4J normalises to [0, 360)
		assertThat(day.getMaxSunElevation()).isCloseTo(90 - (33.93 + 23.44), within(0.3));
	}

	@Test
	void polarNightAndMidnightSunLeaveEventsUnset() {
		DayInfo night = service.dayInfo(SVALBARD, LocalDate.of(2026, 12, 21), ZoneId.of("Arctic/Longyearbyen"));
		assertThat(night.getSunrise()).isNull();
		assertThat(night.getSunset()).isNull();
		assertThat(night.getDayLength()).isEqualTo(Duration.ZERO);
		assertThat(night.getMaxSunElevation()).isNegative();
		assertThat(night.getSolarNoon()).isNotNull();

		DayInfo day = service.dayInfo(SVALBARD, LocalDate.of(2026, 6, 21), ZoneId.of("Arctic/Longyearbyen"));
		assertThat(day.getSunrise()).isNull();
		assertThat(day.getSunset()).isNull();
		assertThat(day.getDayLength()).isEqualTo(Duration.ofHours(24));
		assertThat(service.positionAt(SVALBARD, day.getSolarNoon().plus(Duration.ofHours(12))).isUp()).isTrue();
	}

	@Test
	void positionAtMidnightIsBelowTheHorizon() {
		SolarPosition midnight = service.positionAt(DRESDEN, Instant.parse("2026-10-03T00:00:00Z"));
		assertThat(midnight.elevation()).isLessThan(-30);
		assertThat(midnight.isUp()).isFalse();
		assertThat(midnight.azimuth()).isBetween(0.0, 360.0);
	}

	@Test
	void provenanceSaysComputedAndHowToRecompute() {
		DayInfo day = service.dayInfo(DRESDEN, LocalDate.of(2026, 10, 3), BERLIN);
		assertThat(day.getProvenance().getOrigin()).isEqualTo(Origin.COMPUTED);
		assertThat(day.getProvenance().getProviderId()).isEqualTo("gecko");
		assertThat(day.getProvenance().getProductId()).isEqualTo("solar");
		assertThat(day.getProvenance().getIssuedAt()).isEqualTo(NOW);
		assertThat(day.getProvenance().getDistanceMeters()).isZero();
		assertThat(day.getProvenance().getDerivation().getFunctionId()).isEqualTo(Time4jSolarService.FUNCTION_DAY_EVENTS);
		assertThat(day.getProvenance().getDerivation().getInputs())
				.containsExactly("lat=51.05", "lon=13.74", "elevation=118.0", "date=2026-10-03", "zone=Europe/Berlin");
	}

	private static GeoPosition position(double lat, double lon, double elevation) {
		GeoPosition p = WeatherFactory.eINSTANCE.createGeoPosition();
		p.setLatitude(lat);
		p.setLongitude(lon);
		p.setElevation(elevation);
		return p;
	}

}
