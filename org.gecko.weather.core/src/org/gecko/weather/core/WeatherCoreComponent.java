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
package org.gecko.weather.core;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.ValueQuery;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.solar.SolarService;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of the core: one component registered as {@link SiteRegistry}, {@link WeatherService}
 * and {@link WeatherDataSink}, over the repository and the solar service. Binding resolvers are
 * collected from two whiteboards — every {@link WeatherProvider} contributes its own, and push
 * sources that have no provider register a {@link SiteBindingResolver} directly.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(name = WeatherCoreComponent.PID)
@Designate(ocd = CoreConfig.class)
public class WeatherCoreComponent implements SiteRegistry, WeatherService, WeatherDataSink {

	public static final String PID = "org.gecko.weather.core";

	@Reference
	private WeatherRepository repository;

	@Reference
	private SolarService solarService;

	/*
	 * Dynamic multiple references are bound by method, into collections that exist from field
	 * initialisation on: DS may bind and unbind at any time, before, during or after activate, on
	 * another thread. Nothing in activate reads them; the registry asks for the current set per call.
	 */
	private final List<WeatherProvider> providers = new CopyOnWriteArrayList<>();
	private final List<SiteBindingResolver> resolvers = new CopyOnWriteArrayList<>();

	private SiteRegistryImpl registry;
	private WeatherServiceImpl weather;
	private ReportAssembler sink;

	@Activate
	void activate(CoreConfig config) {
		CoreSettings settings = CoreSettings.of(config);
		Clock clock = Clock.systemUTC();
		registry = new SiteRegistryImpl(repository, this::currentResolvers, settings, clock);
		weather = new WeatherServiceImpl(repository);
		sink = new ReportAssembler(repository, new SolarDatasets(solarService), settings, clock);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	void addProvider(WeatherProvider provider) {
		providers.add(provider);
	}

	void removeProvider(WeatherProvider provider) {
		providers.remove(provider);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	void addResolver(SiteBindingResolver resolver) {
		resolvers.add(resolver);
	}

	void removeResolver(SiteBindingResolver resolver) {
		resolvers.remove(resolver);
	}

	/** A snapshot of the resolvers present right now: every provider's own plus the standalone ones. */
	private List<SiteBindingResolver> currentResolvers() {
		List<SiteBindingResolver> all = new ArrayList<>();
		for (WeatherProvider p : providers) {
			all.add(p.bindingResolver());
		}
		all.addAll(resolvers);
		return all;
	}

	// --- SiteRegistry ----------------------------------------------------------------------

	@Override
	public Site register(SiteRegistration registration) {
		return registry.register(registration);
	}

	@Override
	public Optional<Site> get(String siteId) {
		return registry.get(siteId);
	}

	@Override
	public List<Site> list() {
		return registry.list();
	}

	@Override
	public Site rebind(String siteId) {
		return registry.rebind(siteId);
	}

	@Override
	public SourceBinding assign(String siteId, String providerId, String productId, String locationId) {
		return registry.assign(siteId, providerId, productId, locationId);
	}

	@Override
	public void deactivate(String siteId) {
		registry.deactivate(siteId);
	}

	@Override
	public void remove(String siteId) {
		registry.remove(siteId);
	}

	// --- WeatherService --------------------------------------------------------------------

	@Override
	public Optional<WeatherReport> report(String siteId) {
		return weather.report(siteId);
	}

	@Override
	public List<MeasuredValue> values(String siteId, MeasurementKind kind) {
		return weather.values(siteId, kind);
	}

	@Override
	public List<MeasuredValue> values(String siteId, ValueQuery query) {
		return weather.values(siteId, query);
	}

	@Override
	public SortedMap<Instant, List<MeasuredValue>> timeline(String siteId, ValueQuery query) {
		return weather.timeline(siteId, query);
	}

	@Override
	public List<SourceDataset> archive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo) {
		return weather.archive(siteId, providerId, productId, issuedFrom, issuedTo);
	}

	// --- WeatherDataSink -------------------------------------------------------------------

	@Override
	public void replace(String siteId, SourceDataset dataset) {
		sink.replace(siteId, dataset);
	}

	@Override
	public void append(String siteId, SourceDataset partial) {
		sink.append(siteId, partial);
	}

}
