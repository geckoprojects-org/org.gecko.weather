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

import java.util.List;

import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherStation;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.runtime.JakartarsServiceRuntime;
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
@Component(name = WeatherResource.COMPONENT_NAME, service = WeatherResource.class, scope = ServiceScope.PROTOTYPE)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class WeatherResource {
	public static final String COMPONENT_NAME = "WeatherResouce";
	@Reference
	private StationSearch stationSearch;

	@Reference
	private JakartarsServiceRuntime rt;

	@GET
	@Path("hello")
	public String hello() {
		return "hello";
	}

	/**
	 * Get the stations from an area around a geo location
	 * 
	 * @param lat Latitude
	 * @param lon Longitude
	 * @param radius
	 * @return
	 */
	@GET
	@Path("/station")
	public Response getStationByLocation(@QueryParam("latitude") Double lat, @QueryParam("longitude") Double lon,
			@QueryParam("radius") Integer radius) {
		GeoPosition geoPosition = WeatherFactory.eINSTANCE.createGeoPosition();
		geoPosition.setLatitude(lat);
		geoPosition.setLongitude(lon);

		List<WeatherStation> stations = stationSearch.searchStationNearLocation(geoPosition, radius);
		return Response.status(Status.OK).entity(stations.toArray(new WeatherStation[stations.size()])).build();
	}

}
