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
package org.gecko.weather.ingest;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import org.gecko.weather.api.IngestControl;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.api.spi.WeatherProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of the ingest runtime: a {@link ProviderJob} per {@link WeatherProvider} that comes
 * and goes with the service, all on one {@link IngestScheduler}, exposed as {@link IngestControl}.
 * <p>
 * DS binds the provider whiteboard before {@code activate}, so providers seen until then are kept
 * and scheduled once the runtime is configured; providers appearing later are scheduled on bind.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(name = IngestRuntimeComponent.PID)
@Designate(ocd = IngestConfig.class)
public class IngestRuntimeComponent implements IngestControl {

	public static final String PID = "org.gecko.weather.ingest";

	@Reference
	private SiteRegistry sites;

	@Reference
	private WeatherDataSink sink;

	@Reference
	private WeatherRepository repository;

	private final Clock clock = Clock.systemUTC();
	private final List<WeatherProvider> boundBeforeActivate = new ArrayList<>();
	private IngestSettings settings;
	private IngestScheduler scheduler;

	@Activate
	synchronized void activate(IngestConfig config) {
		settings = IngestSettings.of(config);
		scheduler = new IngestScheduler();
		boundBeforeActivate.forEach(this::schedule);
		boundBeforeActivate.clear();
	}

	@Deactivate
	synchronized void deactivate() {
		scheduler.shutdown();
		scheduler = null;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	synchronized void addProvider(WeatherProvider provider) {
		if (scheduler == null) {
			boundBeforeActivate.add(provider);
		} else {
			schedule(provider);
		}
	}

	synchronized void removeProvider(WeatherProvider provider) {
		boundBeforeActivate.remove(provider);
		if (scheduler != null) {
			scheduler.remove(provider.providerId(), provider.productId());
		}
	}

	private void schedule(WeatherProvider provider) {
		scheduler.add(new ProviderJob(provider, sites, sink, repository, settings, clock));
	}

	@Override
	public List<IngestStatus> status() {
		return scheduler.status();
	}

	@Override
	public IngestStatus runNow(String providerId, String productId) {
		return scheduler.runNow(providerId, productId);
	}

}
