/**
 * Copyright (c) 2012 - 2026 Data In Motion and others.
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
package org.gecko.weather.pv.internal;

import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.DateTimeException;
import java.time.ZoneId;

import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.api.solar.SolarPosition;
import org.gecko.weather.api.solar.SolarService;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.pv.PvForecast;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PlantDirectory;
import org.gecko.weather.pv.model.pv.PlantEntry;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * {@link PvForecast} over the weather and solar services, exported as a remote service through
 * the Remote Service Admin of Fennec Services. Configuration is required.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Designate(ocd = PvForecastComponent.Config.class)
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE, property = {
		"service.exported.interfaces=*",
		"service.exported.configs=fennec.rest",
		"ddsr.provider.name=gecko-weather-pv" })
public class PvForecastComponent implements PvForecast {

	@ObjectClassDefinition(name = "Gecko Weather PV forecast", description = "Expected output of PV plants from the weather service")
	public @interface Config {
		@AttributeDefinition(description = "Folder with one plant profile (pv:Plant XMI) per file. Local data — profiles name addresses.")
		String plantsFolder() default "data/weather/plants";

		@AttributeDefinition(description = "Hours from the current full hour on.")
		int hours() default 48;

		@AttributeDefinition(description = "Days from today on.")
		int days() default 7;

		@AttributeDefinition(description = "Time zone for a site that names none.")
		String defaultTimeZone() default "Europe/Berlin";
	}

	@Reference
	private WeatherService weather;

	@Reference
	private SiteRegistry sites;

	@Reference
	private SolarService solar;

	/** Holds the export until the PV model is registered with Fennec EMF. */
	@Reference
	private PvFactory factory;

	private PlantFolder folder;
	private PlantForecaster forecaster;
	private ZoneId defaultZone;
	private final Clock clock = Clock.systemUTC();

	@Activate
	void activate(Config config) {
		folder = new PlantFolder(Path.of(config.plantsFolder()));
		forecaster = new PlantForecaster(config.hours(), config.days(), this::sun);
		defaultZone = ZoneId.of(config.defaultTimeZone());
	}

	private double[] sun(double latitude, double longitude, Instant instant) {
		GeoPosition p = WeatherFactory.eINSTANCE.createGeoPosition();
		p.setLatitude(latitude);
		p.setLongitude(longitude);
		SolarPosition s = solar.positionAt(p, instant);
		return new double[] { s.elevation(), s.azimuth() };
	}

	@Override
	public PlantDirectory plants() {
		PlantDirectory directory = factory.createPlantDirectory();
		for (Plant plant : folder.plants()) {
			PlantEntry e = factory.createPlantEntry();
			e.setId(plant.getId());
			e.setName(plant.getName());
			e.setSiteId(plant.getSiteId());
			e.setPeakPower(plant.getArrays().stream().mapToDouble(PvArray::getPeakPower).sum());
			e.setTimeZone(sites.get(plant.getSiteId()).map(this::zone).orElse(defaultZone).getId());
			directory.getPlants().add(e);
		}
		return directory;
	}

	@Override
	public PvOutlook forecast(String plantId) {
		Plant plant = folder.plant(plantId).orElseThrow(() -> new IllegalArgumentException("No plant profile " + plantId + " in " + folder.folder()));
		Site site = sites.get(plant.getSiteId()).orElseThrow(() -> new UnknownSiteException(plant.getSiteId()));
		return forecaster.forecast(plant, site, weather.report(site.getId()), zone(site), clock.instant());
	}

	private ZoneId zone(Site site) {
		try {
			return site.getTimeZone() == null || site.getTimeZone().isBlank() ? defaultZone : ZoneId.of(site.getTimeZone());
		} catch (DateTimeException e) {
			return defaultZone;
		}
	}
}
