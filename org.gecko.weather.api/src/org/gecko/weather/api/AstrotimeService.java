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
package org.gecko.weather.api;

import java.time.LocalDate;
import java.util.Date;

import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.GeoPosition;

/**
 * Returns {@link Astrotime} instance
 * @author Mark Hoffmann
 * @since 06.10.2024
 */
public interface AstrotimeService {
	
	/**
	 * Return {@link Astrotime} instance with sunset and sunrise information for the given {@link GeoPosition} and {@link LocalDate}
	 * @param position the position where to get sunrise and sunset time
	 * @param date the day for which the sunset and sunrise times are wanted
	 * @return {@link Astrotime} instance
	 */
	Astrotime getSunTimes(GeoPosition position, LocalDate date);
	
	/**
	 * Returns the sunset {@link Date}
	 * @param position the position where to get sunset time
	 * @param date the day for which the sunset time is wanted
	 * @return the sunset {@link Date}
	 */
	Date getSunset(GeoPosition position, LocalDate date);
	
	/**
	 * Returns the sunrise {@link Date}
	 * @param position the position where to get sunrise time
	 * @param date the day for which the sunrise time is wanted
	 * @return the sunrise {@link Date}
	 */
	Date getSunrise(GeoPosition position, LocalDate date);

}
