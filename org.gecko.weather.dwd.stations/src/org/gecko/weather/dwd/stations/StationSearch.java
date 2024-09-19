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
package org.gecko.weather.dwd.stations;

import java.util.List;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;

/**
 * Station search service to search for DWD weather stations around a location
 * @author Mark Hoffmann
 * @since 09.09.2024
 */
public interface StationSearch {
	
	public static final String CONDITION_STATION_SEARCH = "dwd.station";
	
	/**
	 * Executes a search by station name
	 * @param stationName the station name to look for
	 * @param exactMatch <code>true</code>, if the given station name should match exactly as given
	 * @return the matched documents
	 */
	public List<Station> searchStationByName(String stationName, boolean exactMatch);
	
	/**
	 * Executes a search by station name
	 * @param stationName the station name to look for
	 * @param maxResults number of max results
	 * @return the matched documents
	 */
	public List<Station> searchStationByName(String stationName, int maxResults);
	
	/**
	 * Executes a search by station id
	 * @param id the station id to search for
	 * @return the matched documents
	 */
	public List<Station> searchStationsById(String id);
	
	/**
	 * Search for stations near the given location within a given radius
	 * @param location the location to do a nearby search
	 * @param radius the radius in meter around this location to do a nearby search
	 * @return the matched documents
	 */
	public List<Station>searchStationNearLocation(GeoPosition location, int radius);
	
	/**
	 * Search for stations near the given location within a given radius
	 * @param location the location to do a nearby search
	 * @param radius the radius around this location to do a nearby search
	 * @param maxResults the maximum number of results
	 * @return the matched documents
	 */
	public List<Station>searchStationNearLocation(GeoPosition location, int radius, int maxResults);

}
