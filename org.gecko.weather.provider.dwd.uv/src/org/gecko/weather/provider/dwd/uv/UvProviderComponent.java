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
package org.gecko.weather.provider.dwd.uv;

import java.io.IOException;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.util.Set;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.transport.HttpByteSource;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of {@link UvProvider}: one {@link WeatherProvider} service with the properties
 * {@code weather.provider.id=dwd} and {@code weather.product.id=UVI}. Configuration is required.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Component(name = UvProviderComponent.PID, configurationPolicy = ConfigurationPolicy.REQUIRE, property = {
		WeatherConstants.PROVIDER_ID + "=" + UvProvider.PROVIDER_ID,
		WeatherConstants.PRODUCT_ID + "=" + UvProvider.PRODUCT_ID })
@Designate(ocd = UvConfig.class)
public class UvProviderComponent implements WeatherProvider {

	public static final String PID = "org.gecko.weather.provider.dwd.uv";

	private UvProvider delegate;

	@Activate
	void activate(UvConfig config) {
		delegate = new UvProvider(new UvProvider.Settings(URI.create(config.baseUrl()), config.licence(), config.attribution()),
				new HttpByteSource(), Clock.systemUTC());
	}

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
