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
package org.gecko.weather.provider.dwd.mosmix;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.HttpByteSource;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of {@link MosmixProvider}: one {@link WeatherProvider} service per configuration,
 * with the service properties {@code weather.provider.id=dwd} and {@code weather.product.id} set
 * from the product. The station catalogue is taken from the repository when fresh enough, otherwise
 * fetched and stored — the one thing a provider writes to the repository, and only a catalogue.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(name = MosmixProviderComponent.PID, configurationPolicy = ConfigurationPolicy.OPTIONAL, property = {
		WeatherConstants.PROVIDER_ID + "=" + MosmixProvider.PROVIDER_ID })
@Designate(ocd = MosmixConfig.class)
public class MosmixProviderComponent implements WeatherProvider {

	public static final String PID = "org.gecko.weather.provider.dwd.mosmix";

	/** The catalogue is one for both products; stored under this product id. */
	static final String CATALOG_PRODUCT = "MOSMIX";

	@Reference
	private WeatherRepository repository;

	private final AtomicReference<StationCatalog> catalog = new AtomicReference<>();
	private MosmixProvider delegate;
	private ByteSource source;
	private URI catalogUri;
	private Duration catalogMaxAge;

	@Activate
	void activate(MosmixConfig config) throws IOException {
		Clock clock = Clock.systemUTC();
		source = new HttpByteSource();
		catalogUri = URI.create(config.catalogUrl());
		catalogMaxAge = Duration.parse(config.catalogMaxAge());
		MosmixProvider.Settings settings = new MosmixProvider.Settings(config.product(), URI.create(config.baseUrl()),
				config.licence(), config.attribution());
		delegate = new MosmixProvider(settings, source, this::catalog, clock);
		loadCatalog(clock.instant());
	}

	private StationCatalog catalog() {
		return catalog.get();
	}

	/** Repository first; fetch when absent or older than {@code catalogMaxAge}. A fetch failure keeps a stale catalogue. */
	private void loadCatalog(Instant now) throws IOException {
		Optional<StationCatalog> stored = repository.loadCatalog(MosmixProvider.PROVIDER_ID, CATALOG_PRODUCT);
		boolean fresh = stored.map(StationCatalog::getRetrievedAt).map(t -> t.plus(catalogMaxAge).isAfter(now)).orElse(false);
		if (fresh) {
			catalog.set(stored.get());
			return;
		}
		try {
			ByteSource.Result result = source.fetch(catalogUri, Optional.empty());
			if (result instanceof ByteSource.Content content) {
				try (content; InputStream in = content.data()) {
					StationCatalog fetched = StationCatalogParser.parse(in, MosmixProvider.PROVIDER_ID, CATALOG_PRODUCT, now);
					repository.saveCatalog(fetched);
					catalog.set(fetched);
					return;
				}
			}
		} catch (IOException e) {
			if (stored.isEmpty()) {
				throw e; // nothing to fall back to: the component does not come up
			}
		}
		catalog.set(stored.orElseThrow());
	}

	// --- WeatherProvider -------------------------------------------------------------------

	@Override
	public String providerId() {
		return delegate.providerId();
	}

	@Override
	public String productId() {
		return delegate.productId();
	}

	@Override
	public Origin origin() {
		return delegate.origin();
	}

	@Override
	public Duration expectedRefresh() {
		return delegate.expectedRefresh();
	}

	@Override
	public Set<MeasurementKind> provides() {
		return delegate.provides();
	}

	@Override
	public String licence() {
		return delegate.licence();
	}

	@Override
	public String attribution() {
		return delegate.attribution();
	}

	@Override
	public SiteBindingResolver bindingResolver() {
		return delegate.bindingResolver();
	}

	@Override
	public FetchResult fetch(FetchRequest request) throws IOException {
		return delegate.fetch(request);
	}

}
