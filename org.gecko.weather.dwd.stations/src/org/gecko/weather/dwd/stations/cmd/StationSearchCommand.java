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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.Parameter;
import org.apache.felix.service.command.annotations.GogoCommand;
import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.condition.Condition;

/**
 * Command to search for station by name or by location: stationByName jena
 * stationByLocation -p|--position 50.892691,12.267116 (-r|--radius 10) - the -p
 * or --position parameter is mandatory whereas the radius can be omitted and
 * defaults to 30 km
 * 
 * @author Mark Hoffmann
 * @since 10.09.2024
 */
@Component(service = Object.class, reference = { @Reference(name = "indexReady", service = Condition.class, target = "("
		+ Condition.CONDITION_ID + "=" + StationSearch.CONDITION_STATION_SEARCH + ")") })
@GogoCommand(scope = "dwd.station", function = { "stationByName", "stationByLocation" })
public class StationSearchCommand {

	@Reference
	private StationSearch stationSearch;

	@Descriptor("Search stations by a given name")
	public void stationByName(@Descriptor("The station name to search for") String name) {
		if (isNull(name)) {
			System.out.println("Command 'searchByName' requires a parameter with the name to search for!");
			return;
		}
		if (name.isBlank()) {
			System.out.println("Searching for a blank name returned no result!");
			return;
		}
		System.out.println(String.format("Searching for station with name '%s", name));
		List<Station> byName = stationSearch.searchStationByName(name, false);
		System.out.println(String.format("- Found %s results: ", byName.size()));
		byName.forEach(s -> {
			String lat, lon = lat = lon = "n/a";
			GeoPosition location = s.getLocation();
			if (nonNull(location)) {
				lat = Double.toString(location.getLatitude());
				lon = Double.toString(location.getLongitude());
			}
			System.out.println(
					String.format("[%s] Name: '%s', Latitude: %s, Longitude: %s", s.getId(), s.getName(), lat, lon));
		});
	}

	/**
	 * Search a station by location like this: <b>stationbylocation -p
	 * 50.892691,12.267116 -r 30</b> <b>stationbylocation --position
	 * 50.892691,12.267116</b>
	 * 
	 * @param position the geoposition @see {@link GeoPositionConverter}
	 * @param radius   the radius (optional)
	 */
	@Descriptor("Search stations by a given geo-position and radius")
	public void stationByLocation(@Parameter(names = { "-p",
			"--position" }, absentValue = "n/a,n/a") @Descriptor("Geo-position in 'lat, lon'") GeoPosition position,
			@Parameter(names = { "-r",
					"--radius" }, absentValue = "30") @Descriptor("The search radius in km (optional)") Integer radius) {
		if (isNull(position)) {
			System.out.println(
					"Command 'searchByLocation' requires a parameter with the comma separated lat und lon value (e.g. 53.12,12.34)");
			return;
		}
		if (radius < 1) {
			System.out.println("Command 'searchByLocation' requires a parameter radius that must be larger than 0km");
			return;
		}
		if (radius > 200) {
			System.out
					.println("Command 'searchByLocation' requires a parameter radius that must be smaller than 200km");
			return;
		}
		int meters = radius * 1000;

		System.out.println(
				String.format("Searching for stations near location 'lat: %s, lon: %s' within a radius of %s km",
						position.getLatitude(), position.getLongitude(), radius));
		List<Station> byLocation = stationSearch.searchStationNearLocation(position, meters);
		System.out.println(String.format("- Found %s results: ", byLocation.size()));
		byLocation.forEach(s -> {
			String lat = "n/a";
			String lon = "n/a";
			GeoPosition location = s.getLocation();
			if (nonNull(location)) {
				lat = Double.toString(location.getLatitude());
				lon = Double.toString(location.getLongitude());
			}
			System.out.println(
					String.format("[%s] Name: '%s', Latitude: %s, Longitude: %s", s.getId(), s.getName(), lat, lon));
		});
	}

}
