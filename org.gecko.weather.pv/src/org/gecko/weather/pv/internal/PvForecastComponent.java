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
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.model.pv.PvMeasurementLog;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.pv.spi.PvMeter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * {@link PvForecast} over the weather and solar services, exported as a remote service through
 * the Remote Service Admin of Fennec Services. Configuration is required. Plants with a meter are
 * read through the {@link PvMeter} service of the meter's type; the readings are stored per plant and
 * day and returned beside the forecast.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Designate(ocd = PvForecastComponent.Config.class)
@Component(immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE, property = {
		"service.exported.interfaces=*",
		"service.exported.configs=fennec.rest",
		"ddsr.provider.name=gecko-weather-pv" })
public class PvForecastComponent implements PvForecast {

	@ObjectClassDefinition(name = "Gecko Weather PV forecast", description = "Expected output of PV plants from the weather service")
	public @interface Config {
		@AttributeDefinition(description = "Folder with one plant profile (pv:Plant XMI) per file. Local data — profiles name addresses.")
		String plantsFolder() default "data/weather/plants";

		@AttributeDefinition(description = "Hours past the current full hour; the outlook starts at the beginning of today.")
		int hours() default 48;

		@AttributeDefinition(description = "Days from today on.")
		int days() default 7;

		@AttributeDefinition(description = "Time zone for a site that names none.")
		String defaultTimeZone() default "Europe/Berlin";

		@AttributeDefinition(description = "Folder for meter readings, one subfolder per plant, one XMI file per day. Local data.")
		String measurementsFolder() default "data/weather/pv-measurements";

		@AttributeDefinition(description = "Read the meters of plants that have one.")
		boolean metering() default true;
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

	private final Map<String, PvMeter> meters = new ConcurrentHashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	void addMeter(PvMeter meter, Map<String, Object> properties) {
		if (properties.get(PvMeter.TYPE) instanceof String type) {
			meters.put(type, meter);
		}
	}

	void removeMeter(PvMeter meter, Map<String, Object> properties) {
		if (properties.get(PvMeter.TYPE) instanceof String type) {
			meters.remove(type, meter);
		}
	}

	private PlantFolder folder;
	private MeasurementStore store;
	private ScheduledExecutorService scheduler;
	private PlantForecaster forecaster;
	private ZoneId defaultZone;
	private final Clock clock = Clock.systemUTC();

	@Activate
	void activate(Config config) {
		folder = new PlantFolder(Path.of(config.plantsFolder()));
		forecaster = new PlantForecaster(config.hours(), config.days(), this::sun);
		defaultZone = ZoneId.of(config.defaultTimeZone());
		store = new MeasurementStore(Path.of(config.measurementsFolder()));
		if (config.metering()) {
			MeterPoller poller = new MeterPoller(folder::plants, store, type -> Optional.ofNullable(meters.get(type)), this::zone,
					clock);
			scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
				Thread t = new Thread(r, "gecko-weather-pv-meters");
				t.setDaemon(true);
				return t;
			});
			long tick = MeterPoller.MIN_INTERVAL.toMillis();
			scheduler.scheduleWithFixedDelay(poller::tick, tick, tick, TimeUnit.MILLISECONDS);
		}
	}

	@Deactivate
	void deactivate() {
		if (scheduler != null) {
			scheduler.shutdownNow();
		}
	}

	private ZoneId zone(Plant plant) {
		return sites.get(plant.getSiteId()).map(this::zone).orElse(defaultZone);
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
		Instant now = clock.instant();
		ZoneId zone = zone(site);
		PvOutlook outlook = forecaster.forecast(plant, site, weather.report(site.getId()), zone, now);
		if (plant.getMeter() != null) {
			store.log(plant.getId(), LocalDate.ofInstant(now, zone)).ifPresent(log -> addMeasured(outlook, log.getMeasurements(), zone, now));
		}
		return outlook;
	}

	/** Measured power for the hours that have begun, measured energy for today. */
	static void addMeasured(PvOutlook outlook, List<PvMeasurement> readings, ZoneId zone, Instant now) {
		for (PvHour h : outlook.getHours()) {
			Instant from = h.getTime().toInstant();
			if (!from.isAfter(now)) {
				Measured.meanPower(readings, from, from.plus(Duration.ofHours(1))).ifPresent(h::setMeasuredPower);
			}
		}
		String today = LocalDate.ofInstant(now, zone).toString();
		for (PvDay d : outlook.getDays()) {
			if (today.equals(d.getDate())) {
				LocalDate date = LocalDate.parse(d.getDate());
				Measured.energy(readings, date.atStartOfDay(zone).toInstant(), date.plusDays(1).atStartOfDay(zone).toInstant())
						.ifPresent(d::setMeasuredEnergy);
			}
		}
	}

	@Override
	public PvMeasurementLog measurements(String plantId, String date) {
		Plant plant = folder.plant(plantId).orElseThrow(() -> new IllegalArgumentException("No plant profile " + plantId + " in " + folder.folder()));
		ZoneId zone = zone(plant);
		LocalDate day;
		try {
			day = date == null || date.isBlank() ? LocalDate.ofInstant(clock.instant(), zone) : LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Not an ISO date: " + date, e);
		}
		return store.log(plant.getId(), day).orElseGet(() -> {
			PvMeasurementLog empty = factory.createPvMeasurementLog();
			empty.setPlantId(plant.getId());
			empty.setDate(day.toString());
			if (plant.getMeter() != null) {
				empty.setMeterType(plant.getMeter().getType());
			}
			return empty;
		});
	}

	private ZoneId zone(Site site) {
		try {
			return site.getTimeZone() == null || site.getTimeZone().isBlank() ? defaultZone : ZoneId.of(site.getTimeZone());
		} catch (DateTimeException e) {
			return defaultZone;
		}
	}
}
