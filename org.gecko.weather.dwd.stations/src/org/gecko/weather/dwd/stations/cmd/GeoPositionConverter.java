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
package org.gecko.weather.dwd.stations.cmd;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.apache.felix.service.command.Converter;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Converts the command input in the required type
 * 
 * @author Mark Hoffmann
 * @since 10.09.2024
 */
@Component(immediate = true)
public class GeoPositionConverter implements Converter {

	private static final Logger LOGGER = System.getLogger(GeoPositionConverter.class.getName());

	@Reference
	private WeatherFactory weatherFactory;

	@Override
	public Object convert(Class<?> desiredType, Object in) throws Exception {
		if (desiredType.isAssignableFrom(GeoPosition.class)) {
			String geoString = in.toString();
			String[] latLon = geoString.split(",");
			if (latLon.length != 2) {
				LOGGER.log(Level.WARNING, "The position parameter is expected to be in format: <lat-value>,<lon-value> but is {0}", geoString);
				return null;
			}
			if ("n/a".equals(latLon[0]) && "n/a".equals(latLon[1])) {
				LOGGER.log(Level.WARNING, "The position parameter is expected to be in format: <lat-value>,<lon-value> but is {0}", geoString);
				return null;
			}
			try {
				double lat = Double.parseDouble(latLon[0].trim());
				double lon = Double.parseDouble(latLon[1].trim());
				GeoPosition geoPosition = weatherFactory.createGeoPosition();
				geoPosition.setLatitude(lat);
				geoPosition.setLongitude(lon);
				return geoPosition;
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "The comma separated latitude and longitude values are exepected to be a number (e.g. 52.12,12.34) but are {0}", geoString, e);
				return null;
			}
		}
		return null;
	}

	@Override
	public CharSequence format(Object target, int level, Converter escape) throws Exception {
		if (target instanceof GeoPosition pos) {
			return switch (level) {
			case INSPECT -> String.format("Latitude: %s, Longitude: %s", pos.getLatitude(), pos.getLongitude());
			case LINE -> String.format("Lat: %s, Lon: %s", pos.getLatitude(), pos.getLongitude());
			case PART -> String.format("[%s, %s]", pos.getLatitude(), pos.getLongitude());
			default -> throw new IllegalArgumentException("Unexpected value: " + level);
			};
		}
		return null;
	}

}
