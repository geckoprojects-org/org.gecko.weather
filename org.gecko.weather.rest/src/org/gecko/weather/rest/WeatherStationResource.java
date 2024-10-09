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
package org.gecko.weather.rest;

import static java.util.Objects.requireNonNull;

import java.util.List;

import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherStation;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * 
 * @author grune
 * @since Oct 8, 2024
 */
@JakartarsResource
@Component(name = WeatherStationResource.COMPONENT_NAME, service = WeatherStationResource.class, scope = ServiceScope.PROTOTYPE)
@Produces(MediaType.APPLICATION_JSON)
@Path("station")
public class WeatherStationResource {
	public static final String COMPONENT_NAME = "WeatherStationResouce";
	@Reference
	private StationSearch stationSearch;

	@GET
	@Path("hello")
	public String hello() {
		return "hello";
	}

	/**
	 * Get the stations from an area around a geo location
	 * 
	 * @param lat       Latitude
	 * @param lon       Longitude
	 * @param radius    optional default is 10000
	 * @param maxResult optional default is 5
	 * @return
	 */
	@GET
	@Path("location")
	public Response getStationByLocation(@QueryParam("latitude") Double lat, @QueryParam("longitude") Double lon,
			@QueryParam("radius") Integer radius, @QueryParam("max") Integer maxResult) {
		requireNonNull(lat);
		requireNonNull(lon);

		if (radius == null) {
			radius = 10000;
		}

		if (maxResult == null || maxResult > 100) {
			maxResult = 5;
		}

		GeoPosition geoPosition = WeatherFactory.eINSTANCE.createGeoPosition();
		geoPosition.setLatitude(lat);
		geoPosition.setLongitude(lon);

		List<WeatherStation> stations = stationSearch.searchStationNearLocation(geoPosition, radius, maxResult);
		return Response.status(Status.OK).entity(stations.toArray(new WeatherStation[stations.size()])).build();
	}

	/**
	 * Get the stations by id
	 * 
	 * @param id id of the station
	 * 
	 * @return
	 */
	@GET
	@Path("id")
	public Response getStationById(@QueryParam("id") String id) {
		requireNonNull(id);

		List<WeatherStation> stations = stationSearch.searchStationsById(id);
		return Response.status(Status.OK).entity(stations.toArray(new WeatherStation[stations.size()])).build();
	}
	
	/**
	 * Get the stations by name
	 * 
	 * @param name name of the station
	 * @param maxResult optional default is 5
	 * 
	 * @return
	 */
	@GET
	@Path("name")
	public Response getStationByName(@QueryParam("name") String name, @QueryParam("max") Integer maxResult) {
		requireNonNull(name);
		if (maxResult == null || maxResult > 100) {
			maxResult = 5;
		}
		
		List<WeatherStation> stations = stationSearch.searchStationByName(name, maxResult);
		return Response.status(Status.OK).entity(stations.toArray(new WeatherStation[stations.size()])).build();
	}

}
