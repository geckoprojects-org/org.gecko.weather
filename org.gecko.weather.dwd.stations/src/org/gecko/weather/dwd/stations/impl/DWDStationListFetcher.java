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
package org.gecko.weather.dwd.stations.impl;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.gecko.weather.dwd.fc.fetcher.DWDFetcher;
import org.gecko.weather.dwd.stations.StationIndex;
import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.dwd.stations.config.StationConfig;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.condition.Condition;
import org.osgi.service.metatype.annotations.Designate;

import biz.aQute.scheduler.api.Constants;
import biz.aQute.scheduler.api.CronExpression;
import biz.aQute.scheduler.api.CronJob;

/**
 * Fetches the latest station lists:
 * https://www.dwd.de/DE/leistungen/cdc/cdc_ueberblick-klimadaten.html Appends
 * two station lists. The first is taken with higher priority. All MOSMIX
 * stations that are nit in the first list are appended.
 * 
 * @author Mark Hoffmann
 * @since 05.09.2024
 */
@CronExpression(name = "DWD-Stations", cron = Constants.CRON_EXPRESSION_REBOOT)
@Designate(ocd = StationConfig.class)
@Component(immediate = true, configurationPid = "DWDStationFetcher", configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class DWDStationListFetcher extends DWDFetcher implements CronJob {

	private static final Logger LOGGER = System.getLogger(DWDStationListFetcher.class.getName());

	@Reference
	private WeatherFactory weatherFactory;
	@Reference
	private StationIndex sis;
	private volatile boolean mosmixDownload = false;
	private BundleContext ctx;
	private StationConfig config;
	private ServiceRegistration<Condition> loadedCondition = null;

	@Activate
	public void activate(BundleContext ctx, StationConfig config) {
		this.ctx = ctx;
		this.config = config;
	}

	@Deactivate
	public void deactivate() {
		if (nonNull(loadedCondition)) {
			loadedCondition.unregister();
		}
	}

	@Override
	public void run() throws Exception {
		LOGGER.log(Level.DEBUG, "Running all station list ...");
		List<String> ids = new ArrayList<>();
		InputStream data = doDownload();
		try (InputStreamReader isr = new InputStreamReader(data); BufferedReader reader = new BufferedReader(isr)) {
			reader.lines() //
					.skip(1) //
					.map(this::mapStationAll) //
					.filter(Objects::nonNull).forEach(s -> {
						ids.add(s.getId());
						indexStation(s);
					});
		}

		LOGGER.log(Level.DEBUG, "Running MOSMIX station list ...");
		data = doDownload();
		try (InputStreamReader isr = new InputStreamReader(data); BufferedReader reader = new BufferedReader(isr)) {
			reader.lines() //
					.skip(2) //
					.map(this::mapStationMOSMIX) //
					.filter(s -> !ids.remove(s.getId())).filter(Objects::nonNull) //
					.forEach(this::indexStation);
		}
		LOGGER.log(Level.DEBUG, "Indexed all stations");
		loadedCondition = ctx.registerService(Condition.class, Condition.INSTANCE,
				FrameworkUtil.asDictionary(Map.of(Condition.CONDITION_ID, StationSearch.CONDITION_STATION_SEARCH)));

	}

	private void indexStation(Station s) {
		try {
			sis.indexStation(s, true);
			if (LOGGER.isLoggable(Level.DEBUG)) {
				LOGGER.log(Level.DEBUG, "DWD Station - ID: {0}, Name: {1}, Lat: {2}, Lon: {3}, Alt: {4}", s.getId(),
						s.getName(), s.getLocation().getLatitude(), s.getLocation().getLongitude(),
						s.getLocation().getElevation());
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error indexing station {0}", s, e);
		}
	}

	@Override
	protected String getFetchUrl() {
		if (mosmixDownload) {
			mosmixDownload = false;
			return config.stationMosmixUrl();
		}
		mosmixDownload = true;
		return config.stationUrl();
	}

	@Override
	protected String getName() {
		return "Stations";
	}

	private Station mapStationAll(String line) {
		requireNonNull(line);
		String[] columns = line.split(";");
		try {
			if (columns.length != 6) {
				throw new IllegalArgumentException("Line is expected to have 6 columns");
			}
			Station station = weatherFactory.createStation();
			if (Objects.isNull(columns[0])) {
				return null;
			}
			station.setId(columns[0].trim());
			if (nonNull(columns[1])) {
				station.setName(columns[1].trim());
			}
			station.setName(columns[1].trim());
			if (nonNull(columns[5])) {
				station.setCountry(columns[5].trim());
			}
			String latString = columns[2];
			String lonString = columns[3];
			if (nonNull(latString) && nonNull(lonString) && !latString.isBlank() && !lonString.isBlank()) {
				GeoPosition location = weatherFactory.createGeoPosition();
				double lat = Double.parseDouble(latString.trim());
				location.setLatitude(lat);
				double lon = Double.parseDouble(lonString.trim());
				location.setLongitude(lon);
				String altString = columns[4];
				if (nonNull(altString) && !altString.isBlank()) {
					short alt = Short.parseShort(altString.trim());
					location.setElevation(alt);
				}
				station.setLocation(location);
			} else {
				return null;
			}
			return station;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error mapping station \"{0}\"", line, e);
			return null;
		}
	}

	private Station mapStationMOSMIX(String line) {
		requireNonNull(line);
		if (line.length() != 52) {
			throw new IllegalArgumentException("Line is expected to have a length of 52");
		}
		Station station = weatherFactory.createStation();
		station.setId(line.substring(0, 5).trim());
		String icao = line.substring(6, 10).trim();
		if (!"----".equals(icao)) {
			station.setIcaoCode(line.substring(6, 10).trim());
		}
		station.setName(line.substring(11, 32).trim());
		GeoPosition location = weatherFactory.createGeoPosition();
		String latString = line.substring(32, 39).trim();
		double lat = Double.parseDouble(latString);
		location.setLatitude(lat);
		String lonString = line.substring(39, 47).trim();
		double lon = Double.parseDouble(lonString);
		location.setLongitude(lon);
		String altString = line.substring(47, 52).trim();
		short alt = Short.parseShort(altString);
		location.setElevation(alt);
		station.setLocation(location);
		return station;
	}

}
