/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
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
package org.gecko.weather.dwd.stations.impl.example;

import java.util.List;

import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherStation;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

/**
 * Example Component
 */
//@Component(reference = {
//		@Reference(name = "indexReady", service = Condition.class, target = "(" + Condition.CONDITION_ID + "=" + StationSearch.CONDITION_STATION_SEARCH + ")")
//})
public class StationSearchComponent {
	
	@Reference
	private StationSearch sss;
	@Reference
	private WeatherFactory weatherFactory;
	
	/**
	 * Index and search
	 * @param ctx
	 */
	@Activate
	public void activate(BundleContext ctx) {
		
		System.out.println("Search Station 'Leumnitz' ...");
		List<WeatherStation> byName = sss.searchStationByName("Leumnitz", false);
		byName.forEach(s->{
			System.out.println("[" + s.getId() + "] " + s.getName() + " [" + s.getLocation().getLatitude() + ", " + s.getLocation().getLongitude() + "]");
		});
		System.out.println("Search Station 'Jena' ...");
		byName = sss.searchStationByName("Jena", false);
		byName.forEach(s->{
			System.out.println("[" + s.getId() + "] " + s.getName() + " [" + s.getLocation().getLatitude() + ", " + s.getLocation().getLongitude() + "]");
		});
		System.out.println("Search Station 'Gera' ...");
		byName = sss.searchStationByName("Gera", false);
		byName.forEach(s->{
			System.out.println("[" + s.getId() + "] " + s.getName() + " [" + s.getLocation().getLatitude() + ", " + s.getLocation().getLongitude() + "]");
		});
		System.out.println("Search Station near 'Löbichau' (50.892690, 12.267132)");
		GeoPosition location = weatherFactory.createGeoPosition();
		location.setLatitude(50.892690);
		location.setLongitude(12.267132);
		List<WeatherStation> byLocation = sss.searchStationNearLocation(location, 30000);
		byLocation.forEach(s->{
			System.out.println("[" + s.getId() + "] " + s.getName() + " [" + s.getLocation().getLatitude() + ", " + s.getLocation().getLongitude() + "]");
		});
		System.out.println("Search Station near 'DIMC Headquarter' (50.921308, 11.580446)");
		// DIM HQ 50.921308, 11.580446
		location = weatherFactory.createGeoPosition();
		location.setLatitude(50.921308);
		location.setLongitude(11.580446);
		byLocation = sss.searchStationNearLocation(location, 50000);
		byLocation.forEach(s->{
			System.out.println("[" + s.getId() + "] " + s.getName() + " [" + s.getLocation().getLatitude() + ", " + s.getLocation().getLongitude() + "]");
		});
	}
	
	
}
