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
package org.gecko.weather.solar;

import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

import net.e175.klaus.solarpositioning.DeltaT;
import net.e175.klaus.solarpositioning.SolarEvents;
import net.e175.klaus.solarpositioning.SolarEvents.Day;
import net.e175.klaus.solarpositioning.SolarEvents.Horizon;
import net.e175.klaus.solarpositioning.SolarEvents.HorizonState;
import net.e175.klaus.solarpositioning.SolarPositions;

/**
 * {@link SolarService} over the NREL Solar Position Algorithm as implemented by
 * {@code net.e175.klaus:solarpositioning} (MIT, ±0.0003°).
 * <p>
 * Day events are evaluated for the calendar date <em>in the given zone</em>: "sunrise on 3 October"
 * is the sunrise that falls on 3 October where the site is, not on the platform's date. Sunrise and
 * sunset use the conventional −50′ horizon (refraction plus semidiameter); twilight the −6° and −12°
 * horizons; elevation and azimuth are geometric for the site's height, without an atmosphere model.
 * Plain Java; the DS annotation is the only OSGi dependency.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component
public class SpaSolarService implements SolarService {

	/** Recorded in {@code Derivation.functionId} of positions. */
	public static final String FUNCTION_POSITION = "solar.position/spa";

	/** Recorded in {@code Derivation.functionId} of day events. */
	public static final String FUNCTION_DAY_EVENTS = "solar.day-events/spa";

	private static final SolarPositions POSITIONS = SolarPositions.spa();
	private static final SolarEvents EVENTS = SolarEvents.spa();

	private final Clock clock;

	public SpaSolarService() {
		this(Clock.systemUTC());
	}

	/** @param clock supplies {@code issuedAt} of the computed provenance; injectable for tests */
	public SpaSolarService(Clock clock) {
		this.clock = requireNonNull(clock, "clock");
	}

	@Override
	public SolarPosition positionAt(GeoPosition position, Instant instant) {
		requireNonNull(position, "position");
		requireNonNull(instant, "instant");
		double deltaT = DeltaT.estimate(instant.atZone(ZoneOffset.UTC).toLocalDate());
		net.e175.klaus.solarpositioning.SolarPosition sp = POSITIONS.forTime(instant, deltaT)
				.at(position.getLatitude(), position.getLongitude(), height(position));
		return new SolarPosition(instant, sp.elevation(), sp.azimuth());
	}

	@Override
	public DayInfo dayInfo(GeoPosition position, LocalDate date, ZoneId zone) {
		requireNonNull(position, "position");
		requireNonNull(date, "date");
		requireNonNull(zone, "zone");
		double lat = position.getLatitude();
		double lon = position.getLongitude();
		double deltaT = DeltaT.estimate(date);
		Map<Horizon, Day> days = EVENTS.forDateMultiple(date, zone, lat, lon, deltaT, Horizon.SUNRISE_SUNSET,
				Horizon.CIVIL_TWILIGHT, Horizon.NAUTICAL_TWILIGHT);

		DayInfo info = WeatherFactory.eINSTANCE.createDayInfo();
		info.setDate(date);
		Day sun = days.get(Horizon.SUNRISE_SUNSET);
		first(sun.rises()).ifPresent(info::setSunrise);
		last(sun.sets()).ifPresent(info::setSunset);
		Day civil = days.get(Horizon.CIVIL_TWILIGHT);
		first(civil.rises()).ifPresent(info::setCivilDawn);
		last(civil.sets()).ifPresent(info::setCivilDusk);
		Day nautical = days.get(Horizon.NAUTICAL_TWILIGHT);
		first(nautical.rises()).ifPresent(info::setNauticalDawn);
		last(nautical.sets()).ifPresent(info::setNauticalDusk);

		Optional<Instant> noon = first(sun.transits());
		noon.ifPresent(info::setSolarNoon);
		noon.ifPresent(t -> info.setMaxSunElevation(positionAt(position, t).elevation()));
		info.setDayLength(aboveHorizon(sun));

		info.setProvenance(provenance(FUNCTION_DAY_EVENTS, position, "date=" + date, "zone=" + zone.getId()));
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

	// --- internals -------------------------------------------------------------------------

	private static double height(GeoPosition position) {
		return position.isSetElevation() ? Math.max(0, position.getElevation()) : 0;
	}

	/**
	 * Time the sun's centre is above the horizon within the civil day: walks the rise and set events
	 * from the state at the day's start. 24 h under the midnight sun, 0 in polar night.
	 */
	private static Duration aboveHorizon(Day day) {
		record Event(Instant at, boolean rise) {
		}
		List<Event> events = new ArrayList<>();
		day.rises().forEach(r -> events.add(new Event(r.toInstant(), true)));
		day.sets().forEach(s -> events.add(new Event(s.toInstant(), false)));
		events.sort(Comparator.comparing(Event::at));
		boolean above = day.stateAtStart() == HorizonState.ABOVE;
		Instant cursor = day.start().toInstant();
		Duration total = Duration.ZERO;
		for (Event e : events) {
			if (above) {
				total = total.plus(Duration.between(cursor, e.at()));
			}
			above = e.rise();
			cursor = e.at();
		}
		if (above) {
			total = total.plus(Duration.between(cursor, day.end().toInstant()));
		}
		return total;
	}

	private static Optional<Instant> first(List<ZonedDateTime> times) {
		return times.isEmpty() ? Optional.empty() : Optional.of(times.get(0).toInstant());
	}

	private static Optional<Instant> last(List<ZonedDateTime> times) {
		return times.isEmpty() ? Optional.empty() : Optional.of(times.get(times.size() - 1).toInstant());
	}

}
