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

import org.apache.felix.service.command.Converter;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Converts the command input in the required type
 * @author Mark Hoffmann
 * @since 10.09.2024
 */
@Component(immediate = true)
public class GeoPositionConverter implements Converter {

	@Reference
	private WeatherFactory weatherFactory;

	/* 
	 * (non-Javadoc)
	 * @see org.apache.felix.service.command.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@Override
	public Object convert(Class<?> desiredType, Object in) throws Exception {
		if (desiredType.isAssignableFrom(GeoPosition.class)) {
			String geoString = in.toString();
			String[] latLon = geoString.split(",");
			if (latLon.length != 2) {
				System.out.println("The position parameter is expected to be in format: <lat-value>,<lon-value>");
				return null;
			}
			if ("n/a".equals(latLon[0]) && "n/a".equals(latLon[1])) {
				System.out.println("The position parameter is expected to be in format: <lat-value>,<lon-value>");
				return null;
			}
			try {
				double lat = Double.parseDouble(latLon[0].trim());
				double lon = Double.parseDouble(latLon[1].trim());
				GeoPosition geoPosition = weatherFactory.createGeoPosition();
				geoPosition.setLatitude(lat);
				geoPosition.setLongitude(lon);
				return geoPosition;
			} catch(Exception e) {
				System.out.println("The comma separated latitude and longitude values are exepected to be a number (e.g. 52.12,12.34)");
				return null;
			}
		}
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.apache.felix.service.command.Converter#format(java.lang.Object, int, org.apache.felix.service.command.Converter)
	 */
	@Override
	public CharSequence format(Object target, int level, Converter escape) throws Exception {
		if (target instanceof GeoPosition) {
			GeoPosition position = (GeoPosition)target;
			return switch (level) {
				case INSPECT -> {
					yield String.format("Latitude: %s, Longitude: %s", position.getLatitude(), position.getLongitude());
				}
				case LINE -> { 
					yield String.format("Lat: %s, Lon: %s", position.getLatitude(), position.getLongitude());
				}
				case PART -> {
					yield String.format("[%s, %s]", position.getLatitude(), position.getLongitude());
				}
				default-> { 
					throw new IllegalArgumentException("Unexpected value: " + level);
				}
			};
		}
		return null;
	}
	
}
