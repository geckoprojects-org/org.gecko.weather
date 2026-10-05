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
package org.gecko.weather.provider.dwd.icon;

import java.io.IOException;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.GridField;
import org.gecko.weather.api.spi.GridFieldListener;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.transport.HttpByteSource;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of {@link IconProvider}: one {@link WeatherProvider} service with the properties
 * {@code weather.provider.id=dwd} and {@code weather.product.id=ICON-D2}. Configuration is
 * <b>required</b>: a run is hundreds of megabytes from a third-party server, so which runs a
 * deployment takes is the operator's decision, made in configuration.
 * <p>
 * Every {@link GridFieldListener} service is told about the whole fields read, narrowed by its
 * {@value GridFieldListener#PARAMETERS} property.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(name = IconProviderComponent.PID, configurationPolicy = ConfigurationPolicy.REQUIRE, property = {
		WeatherConstants.PROVIDER_ID + "=" + IconProvider.PROVIDER_ID,
		WeatherConstants.PRODUCT_ID + "=" + IconProvider.PRODUCT_ID })
@Designate(ocd = IconConfig.class)
public class IconProviderComponent implements WeatherProvider {

	public static final String PID = "org.gecko.weather.provider.dwd.icon";

	private final Map<GridFieldListener, Set<String>> listeners = new ConcurrentHashMap<>();
	private IconProvider delegate;

	@Activate
	void activate(IconConfig config) {
		IconProvider.Settings settings = new IconProvider.Settings(URI.create(config.baseUrl()), runHours(config.runs()),
				config.horizonHours(), IconParameters.parse(config.parameters()), config.licence(), config.attribution());
		delegate = new IconProvider(settings, new HttpByteSource(), Clock.systemUTC(), new Listeners());
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	void addFieldListener(GridFieldListener listener, Map<String, Object> properties) {
		listeners.put(listener, parameters(properties.get(GridFieldListener.PARAMETERS)));
	}

	void removeFieldListener(GridFieldListener listener) {
		listeners.remove(listener);
	}

	/** The wanted parameter names; empty means all. */
	static Set<String> parameters(Object property) {
		if (property == null) {
			return Set.of();
		}
		List<String> raw = property instanceof String[] array ? Arrays.asList(array)
				: property instanceof Iterable<?> it ? toStrings(it) : List.of(property.toString());
		return raw.stream().flatMap(r -> Arrays.stream(r.split(","))).map(String::trim).filter(n -> !n.isEmpty())
				.collect(Collectors.toUnmodifiableSet());
	}

	private static List<String> toStrings(Iterable<?> values) {
		List<String> out = new ArrayList<>();
		values.forEach(v -> out.add(String.valueOf(v)));
		return out;
	}

	/** The registered listeners as one, each given only the parameters it asked for. */
	private final class Listeners implements GridFieldListener {

		@Override
		public void runStarted(String providerId, String productId, Instant run) {
			listeners.keySet().forEach(l -> l.runStarted(providerId, productId, run));
		}

		@Override
		public void field(GridField field) {
			listeners.forEach((l, wanted) -> {
				if (wanted.isEmpty() || wanted.contains(field.parameter())) {
					l.field(field);
				}
			});
		}

		@Override
		public void runComplete(String providerId, String productId, Instant run) {
			listeners.keySet().forEach(l -> l.runComplete(providerId, productId, run));
		}
	}

	static List<Integer> runHours(String runs) {
		List<Integer> hours = new ArrayList<>();
		for (String raw : runs.split(",")) {
			String h = raw.trim();
			if (!h.isEmpty()) {
				hours.add(Integer.parseInt(h));
			}
		}
		return hours;
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
