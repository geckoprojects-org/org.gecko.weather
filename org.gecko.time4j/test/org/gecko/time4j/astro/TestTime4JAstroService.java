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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author mark
 * @since 06.10.2024
 */
public class TestTime4JAstroService {
	
	@Test
	public void testAstroServiceNoArgs() {
		Time4JAstrotimeService as = new Time4JAstrotimeService();
		as.setWeatherFactory(WeatherFactory.eINSTANCE);
		assertThrows(NullPointerException.class, ()->as.getSunTimes(null, null));
		assertThrows(NullPointerException.class, ()->as.getSunTimes(null, LocalDate.now()));
		GeoPosition loc = WeatherFactory.eINSTANCE.createGeoPosition();
		assertThrows(NullPointerException.class, ()->as.getSunTimes(loc, null));
	}
	
	@Test
	public void testAstroServiceXMas() {
		Time4JAstrotimeService as = new Time4JAstrotimeService();
		as.setWeatherFactory(WeatherFactory.eINSTANCE);
		LocalDate xmas = LocalDate.of(2024, 12, 24);
		GeoPosition loc = WeatherFactory.eINSTANCE.createGeoPosition();
		// 50.8891299,12.0859321,114m
		loc.setLatitude(50.8891299);
		loc.setLongitude(12.0859321);
		Astrotime sunTimes = as.getSunTimes(loc, xmas);
		assertNotNull(sunTimes);
		LocalTime srTL = LocalDateTime.ofInstant(sunTimes.getSunriseTwilight().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(7, srTL.getHour());
		assertEquals(33, srTL.getMinute());
		LocalTime sr = LocalDateTime.ofInstant(sunTimes.getSunrise().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(8, sr.getHour());
		assertEquals(13, sr.getMinute());
		LocalTime ss = LocalDateTime.ofInstant(sunTimes.getSunset().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(16, ss.getHour());
		assertEquals(9, ss.getMinute());
		LocalTime ssTL = LocalDateTime.ofInstant(sunTimes.getSunsetTwilight().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(16, ssTL.getHour());
		assertEquals(49, ssTL.getMinute());
	}
	
	@Test
	public void testAstroServiceXMasElevation() {
		Time4JAstrotimeService as = new Time4JAstrotimeService();
		as.setWeatherFactory(WeatherFactory.eINSTANCE);
		LocalDate xmas = LocalDate.of(2024, 12, 24);
		GeoPosition loc = WeatherFactory.eINSTANCE.createGeoPosition();
		// 50.8891299,12.0859321,114m
		loc.setLatitude(50.8891299);
		loc.setLongitude(12.0859321);
		loc.setElevation(216);
		Astrotime sunTimes = as.getSunTimes(loc, xmas);
		assertNotNull(sunTimes);
		LocalTime srTL = LocalDateTime.ofInstant(sunTimes.getSunriseTwilight().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(7, srTL.getHour());
		assertEquals(30, srTL.getMinute());
		LocalTime sr = LocalDateTime.ofInstant(sunTimes.getSunrise().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(8, sr.getHour());
		assertEquals(9, sr.getMinute());
		LocalTime ss = LocalDateTime.ofInstant(sunTimes.getSunset().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(16, ss.getHour());
		assertEquals(13, ss.getMinute());
		LocalTime ssTL = LocalDateTime.ofInstant(sunTimes.getSunsetTwilight().toInstant(), ZoneId.systemDefault()).toLocalTime();
		assertEquals(16, ssTL.getHour());
		assertEquals(52, ssTL.getMinute());
	}
	

}
