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
package org.gecko.weather.map;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.fennec.ogc.features.api.CollectionDescriptor;
import org.eclipse.fennec.ogc.features.api.Envelope;
import org.eclipse.fennec.ogc.features.api.FeatureQuery;
import org.eclipse.fennec.ogc.features.api.FeatureResult;
import org.eclipse.fennec.ogc.features.api.FeatureSource;
import org.eclipse.fennec.ogc.features.geo.GeoJsonFeatureImporter;
import org.eclipse.fennec.ogc.features.geo.GeoJsonText;
import org.eclipse.fennec.ogc.features.source.memory.MemoryFeatureSource;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.spi.GridField;
import org.gecko.weather.api.spi.GridFieldListener;
import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapPackage;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The cloud cover map: told about every {@code clct} field the ICON-D2 provider reads, it turns
 * each into {@link CloudBand} features, and when the run is complete they replace the previous run —
 * in memory and on disk, from where they are read again on start. A run cut short is dropped.
 * <p>
 * Only the part of the field around the active sites is mapped ({@code regionRadiusKm}; the whole
 * field without sites), and the OGC API Features collection {@code cloudcover} shows only the
 * <b>current hour</b> of the run — the newest valid time not after now, before the first one the
 * first. The whole run stays in the file.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
@Component(service = { GridFieldListener.class, FeatureSource.class }, configurationPid = CloudMapComponent.PID,
		configurationPolicy = ConfigurationPolicy.OPTIONAL, property = GridFieldListener.PARAMETERS + "=" + CloudMapComponent.PARAMETER)
@Designate(ocd = CloudMapConfig.class)
public class CloudMapComponent implements GridFieldListener, FeatureSource {

	public static final String PID = "org.gecko.weather.map.cloud";
	static final String PARAMETER = "clct";

	private static final Logger LOG = System.getLogger(CloudMapComponent.class.getName());

	private final CloudMapFiles files;
	private final Isobands.Settings settings;
	private final String attribution;
	private final MemoryFeatureSource source;
	private final SiteRegistry sites;
	private final double regionRadiusKm;
	private final Clock clock;

	/** The whole newest complete run. */
	private volatile List<CloudBand> current = List.of();
	private Instant pendingRun;
	private List<CloudBand> pending;

	@Activate
	public CloudMapComponent(CloudMapConfig config, @Reference(target = "(emf.configuratorName=geojson)") Resource.Factory geojson,
			@Reference(cardinality = ReferenceCardinality.OPTIONAL) SiteRegistry sites) {
		this(config, geojson, sites, Clock.systemUTC());
	}

	CloudMapComponent(CloudMapConfig config, Resource.Factory geojson, SiteRegistry sites, Clock clock) {
		this.sites = sites;
		this.clock = clock;
		regionRadiusKm = config.regionRadiusKm();
		files = new CloudMapFiles(Path.of(config.folder()), PARAMETER);
		settings = new Isobands.Settings(breaks(config.breaks()), config.factor(), config.tolerance(), config.minCells());
		attribution = config.attribution();
		source = new MemoryFeatureSource(Set.of(CloudmapPackage.eNS_URI), this::currentHour);
		try {
			Optional<List<CloudBand>> stored = files
					.readNewest(new GeoJsonFeatureImporter(new GeoJsonText(geojson), CloudmapPackage.eINSTANCE, null));
			// a run written under other breaks keeps only the bands the configuration still has
			double first = settings.breaks()[0];
			stored.map(bands -> bands.stream().filter(b -> b.getLower() >= first).toList()).ifPresent(bands -> {
				current = List.copyOf(bands);
				LOG.log(Level.INFO, "[map] {0} cloud bands of the last run read from {1}", bands.size(), config.folder());
			});
		} catch (IOException | RuntimeException e) {
			LOG.log(Level.WARNING, "[map] stored cloud bands not readable, waiting for the next run", e);
		}
	}

	static double[] breaks(String text) {
		return Arrays.stream(text.split(",")).map(String::trim).filter(s -> !s.isEmpty()).mapToDouble(Double::parseDouble).toArray();
	}

	// --- GridFieldListener -------------------------------------------------------------------

	@Override
	public synchronized void runStarted(String providerId, String productId, Instant run) {
		pendingRun = run;
		pending = new ArrayList<>();
	}

	@Override
	public synchronized void field(GridField field) {
		if (!PARAMETER.equals(field.parameter())) {
			return;
		}
		if (pending == null || !field.run().equals(pendingRun)) {
			// a field without its run announced — start over with this run
			runStarted(field.providerId(), field.productId(), field.run());
		}
		long t0 = System.nanoTime();
		GridField part = region(field).map(w -> w.crop(field)).orElse(field);
		List<CloudBand> bands = CloudBands.of(part, settings, attribution);
		pending.addAll(bands);
		LOG.log(Level.DEBUG, "[map] {0} +{1} h: {2} bands in {3} ms", field.parameter(),
				Duration.between(field.run(), field.validAt()).toHours(), bands.size(), (System.nanoTime() - t0) / 1_000_000);
	}

	@Override
	public synchronized void runComplete(String providerId, String productId, Instant run) {
		if (pending == null || !run.equals(pendingRun) || pending.isEmpty()) {
			return;
		}
		List<CloudBand> complete = List.copyOf(pending);
		pending = null;
		current = complete;
		LOG.log(Level.INFO, "[map] cloud cover of run {0}: {1} bands", run, complete.size());
		try {
			files.write(run, complete);
		} catch (IOException e) {
			LOG.log(Level.WARNING, "[map] cloud bands of run " + run + " not written", e);
		}
	}

	/** The window around the active sites, none without sites or when the radius is 0. */
	private Optional<GridWindow> region(GridField field) {
		if (sites == null || regionRadiusKm <= 0) {
			return Optional.empty();
		}
		List<GeoPosition> positions = sites.list().stream().filter(Site::isActive).map(Site::getPosition).filter(Objects::nonNull)
				.toList();
		return GridWindow.around(field.grid(), positions, regionRadiusKm);
	}

	/** The bands of the newest valid time not after now; before the run's first hour, those of the first. */
	List<CloudBand> currentHour() {
		List<CloudBand> run = current;
		if (run.isEmpty()) {
			return run;
		}
		Date now = Date.from(clock.instant());
		Date chosen = null;
		Date first = null;
		for (CloudBand b : run) {
			Date t = b.getValidTime();
			if (first == null || t.before(first)) {
				first = t;
			}
			if (!t.after(now) && (chosen == null || t.after(chosen))) {
				chosen = t;
			}
		}
		Date hour = chosen != null ? chosen : first;
		return run.stream().filter(b -> b.getValidTime().equals(hour)).toList();
	}

	// --- FeatureSource -----------------------------------------------------------------------

	@Override
	public boolean supports(EClass type) {
		return source.supports(type);
	}

	@Override
	public FeatureResult query(FeatureQuery query) {
		return source.query(query);
	}

	@Override
	public Optional<EObject> get(CollectionDescriptor collection, String id) {
		return source.get(collection, id);
	}

	/** From the bbox attributes, without a query through the filter evaluation. */
	@Override
	public Optional<Envelope> extent(CollectionDescriptor collection) {
		return currentHour().stream().map(b -> new Envelope(b.getMinX(), b.getMinY(), b.getMaxX(), b.getMaxY())).reduce(Envelope::union);
	}
}
