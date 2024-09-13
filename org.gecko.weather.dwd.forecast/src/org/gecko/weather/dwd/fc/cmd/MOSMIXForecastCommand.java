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
package org.gecko.weather.dwd.fc.cmd;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.annotations.GogoCommand;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Command to trigger a download and update of forecast data for a certain station
 * @author Mark Hoffmann
 * @since 10.09.2024
 */
@Component (service = Object.class)
@GogoCommand(scope = "dwd.forecast", function = {"forecastByStation", "forecastStop"})
@RequireConfigurationAdmin
public class MOSMIXForecastCommand {
	
	@Reference
	private ConfigurationAdmin configAdmin;
	
	@Descriptor("Updates a forcast for the given station")
	public void forecastByStation(@Descriptor("The station id") String stationId) {
		if (isNull(stationId) || stationId.isBlank()) {
			System.out.println("Command 'forecastByStation' requires a parameter with the station id to search for!");
			return;
		}
		System.out.println(String.format("Trigger update forecast for station with id '%s", stationId));
		Configuration configuration;
		try {
			System.out.println(String.format("Created/found configuration for station '%s'", stationId));
			configuration = configAdmin.getFactoryConfiguration("DWD-MOSMIX-Station", stationId, "?");
			Dictionary<String,String> dictionary = FrameworkUtil.asDictionary(Map.of("stationId", stationId));
			configuration.updateIfDifferent(dictionary);
			System.out.println(String.format("Updated configuration for station '%s'", stationId));
		} catch (IOException e) {
			System.out.println(String.format("Error getting/creating configuration for station '%s': %s", stationId, e.getMessage()));
		}
	}
	
	@Descriptor("Stopps the forecast cron for a given station")
	public void forecastStop(@Descriptor("The station id") String stationId) {
		if (isNull(stationId) || stationId.isBlank()) {
			System.out.println("Command 'forecastStop' requires a parameter with the station id!");
			return;
		}
		Configuration configuration;
		try {
			configuration = configAdmin.getFactoryConfiguration("DWD-MOSMIX-Station", stationId, "?");
			if (nonNull(configuration.getProperties())) {
				System.out.println(String.format("Found configuration for station '%s'", stationId));
				configuration.delete();
				System.out.println(String.format("Deleted configuration for station '%s'", stationId));
			}
		} catch (IOException e) {
			System.out.println(String.format("Error getting/creating configuration for station '%s': %s", stationId, e.getMessage()));
		}
	}

}
