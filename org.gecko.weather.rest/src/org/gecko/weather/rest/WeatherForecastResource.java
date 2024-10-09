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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gecko.weather.dwd.fc.WeatherReportSearch;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
@Component(name = WeatherForecastResource.COMPONENT_NAME, service = WeatherForecastResource.class, scope = ServiceScope.SINGLETON)
@Produces(MediaType.APPLICATION_JSON)
@Path("forecast")
public class WeatherForecastResource {
	private static final Logger LOGGER = System.getLogger(WeatherForecastResource.class.getName());

	public static final String COMPONENT_NAME = "WeatherForecastResouce";

	private Map<String, Configuration> configs = new HashMap<>();

	@Reference
	private ConfigurationAdmin configAdmin;

	private WeatherReportSearch reportSearch;

	@Reference(unbind = "unbind", cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void bind(WeatherReportSearch reportSearch) {
		LOGGER.log(Level.INFO, "report search incoming");
		this.reportSearch = reportSearch;
	}

	public void unbind(WeatherReportSearch reportSearch) {
		if (reportSearch == this.reportSearch) {
			this.reportSearch = null;
			LOGGER.log(Level.INFO, "report search removed");
		}
	}

	public void deactivate() {
		for (Configuration config : configs.values()) {
			try {
				config.delete();
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "Error removing config", e);
			}
		}
	}

	@GET
	@Path("hello")
	public String hello() {
		return "hello " + COMPONENT_NAME;
	}

	/**
	 * Start fetching weather forecast reports
	 * 
	 * @param id id of the station
	 * 
	 * @return
	 */
	@GET
	@Path("start/{stationId}")
	public Response start(@PathParam("stationId") String stationId) {
		requireNonNull(stationId);
		try {
			Configuration configuration = configs.get(stationId);
			if (isNull(configuration)) {
				configuration = configAdmin.getFactoryConfiguration("DWD-MOSMIX-Station", stationId, "?");
				configs.put(stationId, configuration);
			}
			Dictionary<String, String> dictionary = FrameworkUtil.asDictionary(Map.of("stationId", stationId));
			configuration.updateIfDifferent(dictionary);
			return Response.status(Status.OK).entity("fetching forecast for " + stationId + " started ").build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.OK)
					.entity("fetching forecast for " + stationId + " failed: " + e.getLocalizedMessage()).build();
		}

	}

	/**
	 * Stop fetching weather forecast reports
	 * 
	 * @param id id of the station
	 * 
	 * @return
	 */
	@GET
	@Path("stop/{stationId}")
	public Response stop(@PathParam("stationId") String stationId) {
		requireNonNull(stationId);
		try {
			Configuration configuration = configs.remove(stationId);
			if (nonNull(configuration)) {
				configuration.delete();
				return Response.status(Status.OK).entity("fetching forecast for " + stationId + " stopped.").build();
			}
			return Response.status(Status.OK).entity("no fetching forecast for " + stationId + ".").build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.OK)
					.entity("stop fetching forecast for " + stationId + " failed: " + e.getLocalizedMessage()).build();
		}
	}

	/**
	 * Getting weather forecast reports
	 * example request: http://localhost:8081/weather/rest/forecast/10554?startDate=Thu,%2010%20Oct%202024%2011:05:30%20CET
	 * 
	 * @param stationId
	 * @param startDate
	 * @param endDate
	 * @param timestamp
	 * @return Response with a list of weather reports
	 */
	@GET
	@Path("{stationId}")
	public Response getForecast(@PathParam("stationId") String stationId, @QueryParam("startDate") Date startDate,
			@QueryParam("endDate") Date endDate, @QueryParam("timestamp") Date timestamp) {
		requireNonNull(stationId);

		if (isNull(reportSearch)) {
			return Response.status(Status.OK).entity("fetching reports not started.").build();
		}
		List<WeatherReport> reports;
		if (nonNull(timestamp)) {
			reports = reportSearch.getReportsByTime(timestamp, stationId,
					WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
		} else {
			if (isNull(startDate)) {
				startDate = new Date();
			}
			if (nonNull(endDate)) {
				reports = reportSearch.getReportsByTimeRange(startDate, endDate, stationId,
						WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
			} else {
				reports = reportSearch.getReportsByStationFromNow(stationId, startDate,
						WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
			}
		}
		if (reports.isEmpty()) {
			return Response.status(Status.OK).entity("fetching reports for " + stationId + " not started.").build();
		}
		return Response.status(Status.OK).entity(reports.toArray(new WeatherReport[reports.size()])).build();
	}

}