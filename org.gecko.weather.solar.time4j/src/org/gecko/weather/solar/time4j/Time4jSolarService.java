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

import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.solar.SolarPosition;
import org.gecko.weather.api.solar.SolarService;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Derivation;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Component;

import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.TemporalType;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.calendar.astro.StdSolarCalculator;
import net.time4j.calendar.astro.SunPosition;
import net.time4j.calendar.astro.Twilight;
import net.time4j.tz.TZID;

/**
 * {@link SolarService} over Time4J's astronomical module.
 * <p>
 * Day events are evaluated for the calendar date <em>in the given zone</em> — the observer zone is
 * handed to Time4J, so "sunrise on 3 October" means the sunrise that falls on 3 October where the
 * site is, not on the platform's or on local mean solar time. Elevation and azimuth come from
 * {@link SunPosition} (topocentric, with the site's altitude). Plain Java; the DS annotation is the
 * only OSGi dependency.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component
public class Time4jSolarService implements SolarService {

	/** Recorded in {@code Derivation.functionId} of positions. */
	public static final String FUNCTION_POSITION = "solar.position/time4j-5.9";

	/** Recorded in {@code Derivation.functionId} of day events. */
	public static final String FUNCTION_DAY_EVENTS = "solar.day-events/time4j-5.9";

	private final Clock clock;

	public Time4jSolarService() {
		this(Clock.systemUTC());
	}

	/** @param clock supplies {@code issuedAt} of the computed provenance; injectable for tests */
	public Time4jSolarService(Clock clock) {
		this.clock = requireNonNull(clock, "clock");
	}

	@Override
	public SolarPosition positionAt(GeoPosition position, Instant instant) {
		requireNonNull(position, "position");
		requireNonNull(instant, "instant");
		SunPosition sun = SunPosition.at(moment(instant), solarTime(position, Optional.empty()));
		return new SolarPosition(instant, sun.getElevation(), sun.getAzimuth());
	}

	@Override
	public DayInfo dayInfo(GeoPosition position, LocalDate date, ZoneId zone) {
		requireNonNull(position, "position");
		requireNonNull(date, "date");
		requireNonNull(zone, "zone");
		TZID tzid = zone::getId;
		SolarTime location = solarTime(position, Optional.of(tzid));
		PlainDate day = PlainDate.from(date);

		DayInfo info = WeatherFactory.eINSTANCE.createDayInfo();
		info.setDate(date);
		day.get(location.sunrise()).map(Time4jSolarService::instant).ifPresent(info::setSunrise);
		day.get(location.sunset()).map(Time4jSolarService::instant).ifPresent(info::setSunset);
		day.get(location.sunrise(Twilight.CIVIL)).map(Time4jSolarService::instant).ifPresent(info::setCivilDawn);
		day.get(location.sunset(Twilight.CIVIL)).map(Time4jSolarService::instant).ifPresent(info::setCivilDusk);
		day.get(location.sunrise(Twilight.NAUTICAL)).map(Time4jSolarService::instant).ifPresent(info::setNauticalDawn);
		day.get(location.sunset(Twilight.NAUTICAL)).map(Time4jSolarService::instant).ifPresent(info::setNauticalDusk);

		Moment noon = day.get(location.transitAtNoon());
		info.setSolarNoon(instant(noon));
		info.setMaxSunElevation(SunPosition.at(noon, location).getElevation());
		// seconds of sunshine on that civil day: 0 in polar night, 86400 under the midnight sun
		info.setDayLength(Duration.ofSeconds(day.get(location.sunshine(tzid)).length()));

		info.setProvenance(provenance(FUNCTION_DAY_EVENTS, position,
				"date=" + date, "zone=" + zone.getId()));
		return info;
	}

	/** The provenance a computed solar value carries; shared with report assembly for position values. */
	public Provenance provenance(String functionId, GeoPosition position, String... furtherInputs) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Provenance p = f.createProvenance();
		p.setProviderId(WeatherConstants.COMPUTED_PROVIDER_ID);
		p.setProductId(WeatherConstants.SOLAR_PRODUCT_ID);
		p.setIssuedAt(clock.instant());
		p.setOrigin(Origin.COMPUTED);
		p.setDistanceMeters(0);
		Derivation d = f.createDerivation();
		d.setFunctionId(functionId);
		d.getInputs().add("lat=" + position.getLatitude());
		d.getInputs().add("lon=" + position.getLongitude());
		if (position.isSetElevation()) {
			d.getInputs().add("elevation=" + position.getElevation());
		}
		for (String input : furtherInputs) {
			d.getInputs().add(input);
		}
		p.setDerivation(d);
		return p;
	}

	// --- Time4J glue -----------------------------------------------------------------------

	private static SolarTime solarTime(GeoPosition position, Optional<TZID> observerZone) {
		double lat = position.getLatitude();
		double lon = position.getLongitude();
		// high altitude implies an earlier sunrise and a later sunset
		int altitude = position.isSetElevation() ? (int) Math.round(position.getElevation()) : 0;
		// the builder is the only way to set the observer zone, and it takes degrees/minutes/seconds
		int[] latDms = dms(lat);
		int[] lonDms = dms(lon);
		SolarTime.Builder builder = SolarTime.ofLocation()
				.atAltitude(Math.max(0, altitude))
				.usingCalculator(StdSolarCalculator.TIME4J);
		builder = lat >= 0 ? builder.northernLatitude(latDms[0], latDms[1], seconds(lat))
				: builder.southernLatitude(latDms[0], latDms[1], seconds(lat));
		builder = lon >= 0 ? builder.easternLongitude(lonDms[0], lonDms[1], seconds(lon))
				: builder.westernLongitude(lonDms[0], lonDms[1], seconds(lon));
		if (observerZone.isPresent()) {
			builder = builder.inTimezone(observerZone.get());
		}
		return builder.build();
	}

	/** Whole degrees and whole minutes of the absolute value. */
	private static int[] dms(double decimalDegrees) {
		double abs = Math.abs(decimalDegrees);
		int degrees = (int) abs;
		int minutes = (int) ((abs - degrees) * 60);
		return new int[] { degrees, minutes };
	}

	/** Remaining seconds of the absolute value, as a double so nothing is lost. */
	private static double seconds(double decimalDegrees) {
		double abs = Math.abs(decimalDegrees);
		int degrees = (int) abs;
		int minutes = (int) ((abs - degrees) * 60);
		return (abs - degrees - minutes / 60.0) * 3600.0;
	}

	private static Moment moment(Instant instant) {
		return TemporalType.INSTANT.translate(instant);
	}

	private static Instant instant(Moment moment) {
		return TemporalType.INSTANT.from(moment);
	}

}
