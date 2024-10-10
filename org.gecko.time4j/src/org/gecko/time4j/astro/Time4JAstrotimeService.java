/**
 * Copyright (c) 2012 - 2024 Data In Motion and others.
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
package org.gecko.time4j.astro;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.gecko.weather.api.AstrotimeService;
import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import net.time4j.PlainDate;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.calendar.astro.StdSolarCalculator;
import net.time4j.calendar.astro.Twilight;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

/**
 * Implementation of {@link AstrotimeService} using Time4J library
 * 
 * @author Mark Hoffmann
 * @since 06.10.2024
 */
@Component
public class Time4JAstrotimeService implements AstrotimeService {

	@Reference
	private WeatherFactory weatherFactory;

	public Date getSunset(GeoPosition position, LocalDate date) {
		return getSunTimes(position, date).getSunset();
	}

	public Date getSunrise(GeoPosition position, LocalDate date) {
		return getSunTimes(position, date).getSunset();
	}

	@Override
	public Astrotime getSunTimes(GeoPosition position, LocalDate date) {
		requireNonNull(position);
		requireNonNull(date);
		PlainDate plainDate = PlainDate.from(date);
		TZID tzid = Timezone.ofPlatform().getID();

		// high altitude implies earlier sunrise and later sunset
		SolarTime solartimes;
		if (position.eIsSet(WeatherPackage.eINSTANCE.getGeoPosition_Elevation())) {
			solartimes = SolarTime.ofLocation(position.getLatitude(), position.getLongitude(), position.getElevation(),
					StdSolarCalculator.TIME4J);
		} else {
			solartimes = SolarTime.ofLocation(position.getLatitude(), position.getLongitude());
		}

		LocalDateTime sunsetTime = plainDate.get(solartimes.sunset()).get().toZonalTimestamp(tzid).toTemporalAccessor();
		LocalDateTime sunsetTLTime = plainDate.get(solartimes.sunset(Twilight.CIVIL)).get().toZonalTimestamp(tzid)
				.toTemporalAccessor();
		LocalDateTime sunriseTime = plainDate.get(solartimes.sunrise()).get().toZonalTimestamp(tzid)
				.toTemporalAccessor();
		LocalDateTime sunriseTLTime = plainDate.get(solartimes.sunrise(Twilight.CIVIL)).get().toZonalTimestamp(tzid)
				.toTemporalAccessor();
		Astrotime at = weatherFactory.createAstrotime();
		at.setSunset(Date.from(sunsetTime.atZone(ZoneId.systemDefault()).toInstant()));
		at.setSunsetTwilight(Date.from(sunsetTLTime.atZone(ZoneId.systemDefault()).toInstant()));
		at.setSunrise(Date.from(sunriseTime.atZone(ZoneId.systemDefault()).toInstant()));
		at.setSunriseTwilight(Date.from(sunriseTLTime.atZone(ZoneId.systemDefault()).toInstant()));
		return at;
	}

	/**
	 * Used for testing
	 * 
	 * @param factory
	 */
	void setWeatherFactory(WeatherFactory factory) {
		this.weatherFactory = factory;
	}

}
