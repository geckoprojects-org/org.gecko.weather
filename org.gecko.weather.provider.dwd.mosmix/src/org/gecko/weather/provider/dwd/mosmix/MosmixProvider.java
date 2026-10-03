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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;

import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.provider.dwd.mosmix.MosmixDatasets.ProductInfo;
import org.gecko.weather.provider.dwd.mosmix.MosmixKmlParser.StationForecast;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for DWD MOSMIX, plain Java. Two products share the code:
 * <ul>
 * <li><b>MOSMIX_L</b> — one KMZ per station, ~115 elements, every six hours. One download per
 * distinct bound station; every site bound to that station gets its dataset from the same bytes.</li>
 * <li><b>MOSMIX_S</b> — one KMZ with all ~5 400 stations, ~40 elements, hourly. One download per
 * run; the streaming parser emits only the bound stations.</li>
 * </ul>
 * Change detection is per URL through the {@link SourceState} the runtime hands in; unchanged files
 * are neither parsed nor reported.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class MosmixProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String MOSMIX_L = "MOSMIX_L";
	public static final String MOSMIX_S = "MOSMIX_S";

	/** Everything that differs between deployments and between the two products. */
	public record Settings(String productId, URI baseUri, String licence, String attribution) {
		public Settings {
			requireNonNull(productId, "productId");
			requireNonNull(baseUri, "baseUri");
			if (!MOSMIX_L.equals(productId) && !MOSMIX_S.equals(productId)) {
				throw new IllegalArgumentException("Unknown MOSMIX product: " + productId);
			}
		}

		public static Settings defaults(String productId) {
			return new Settings(productId, URI.create("https://opendata.dwd.de/weather/local_forecasts/mos/"),
					"GeoNutzV", "Datenbasis: Deutscher Wetterdienst");
		}

		Duration expectedRefresh() {
			return MOSMIX_S.equals(productId) ? Duration.ofHours(1) : Duration.ofHours(6);
		}

		/** The KMZ of a station (MOSMIX_L) or of all stations (MOSMIX_S). */
		URI kmzUri(String stationId) {
			if (MOSMIX_S.equals(productId)) {
				return baseUri.resolve("MOSMIX_S/all_stations/kml/MOSMIX_S_LATEST_240.kmz");
			}
			return baseUri.resolve("MOSMIX_L/single_stations/" + stationId + "/kml/MOSMIX_L_LATEST_" + stationId + ".kmz");
		}
	}

	private final Settings settings;
	private final ByteSource source;
	private final MosmixBindingResolver resolver;
	private final Clock clock;

	public MosmixProvider(Settings settings, ByteSource source, Supplier<StationCatalog> catalog, Clock clock) {
		this.settings = requireNonNull(settings, "settings");
		this.source = requireNonNull(source, "source");
		this.clock = requireNonNull(clock, "clock");
		this.resolver = new MosmixBindingResolver(PROVIDER_ID, settings.productId(), requireNonNull(catalog, "catalog"), clock);
	}

	@Override
	public String providerId() {
		return PROVIDER_ID;
	}

	@Override
	public String productId() {
		return settings.productId();
	}

	@Override
	public Origin origin() {
		return Origin.STATION;
	}

	@Override
	public Duration expectedRefresh() {
		return settings.expectedRefresh();
	}

	@Override
	public Set<MeasurementKind> provides() {
		return MosmixElements.kinds();
	}

	@Override
	public String licence() {
		return settings.licence();
	}

	@Override
	public String attribution() {
		return settings.attribution();
	}

	@Override
	public SiteBindingResolver bindingResolver() {
		return resolver;
	}

	@Override
	public FetchResult fetch(FetchRequest request) throws IOException {
		requireNonNull(request, "request");
		// station id → the (site, binding) pairs that read it; TreeMap for a stable download order
		Map<String, List<Target>> targets = new TreeMap<>();
		for (SiteBindings sb : request.sites()) {
			for (SourceBinding b : sb.bindings()) {
				if (b instanceof StationBinding station && PROVIDER_ID.equals(b.getProviderId())
						&& settings.productId().equals(b.getProductId())) {
					targets.computeIfAbsent(station.getStation().getId(), k -> new ArrayList<>())
							.add(new Target(sb.site().getId(), station));
				}
			}
		}
		if (targets.isEmpty()) {
			return new FetchResult.Unchanged();
		}
		// URL → stations served by it: one per station for MOSMIX_L, one for all for MOSMIX_S
		Map<URI, Set<String>> downloads = new LinkedHashMap<>();
		for (String stationId : targets.keySet()) {
			downloads.computeIfAbsent(settings.kmzUri(stationId), k -> new java.util.TreeSet<>()).add(stationId);
		}

		SourceState state = request.state();
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		Map<String, Integer> skipped = new HashMap<>();
		boolean anyChange = false;
		ProductInfo product = new ProductInfo(PROVIDER_ID, settings.productId(), settings.expectedRefresh(),
				settings.licence(), settings.attribution());

		for (Map.Entry<URI, Set<String>> d : downloads.entrySet()) {
			URI uri = d.getKey();
			ByteSource.Result result = source.fetch(uri, state.entity(uri));
			if (result instanceof ByteSource.Unchanged) {
				continue;
			}
			anyChange = true;
			Set<String> wanted = d.getValue();
			Set<String> seen = new java.util.HashSet<>();
			try (ByteSource.Content content = (ByteSource.Content) result; InputStream kml = Unwrap.zip(content.data())) {
				MosmixKmlParser.parse(kml, wanted, forecast -> {
					seen.add(forecast.stationId());
					count(skipped, "rejected-elements", forecast.rejectedElements().size());
					for (Target t : targets.getOrDefault(forecast.stationId(), List.of())) {
						datasets.computeIfAbsent(t.siteId(), k -> new ArrayList<>())
								.add(MosmixDatasets.build(forecast, t.binding(), product, clock.instant()));
					}
				});
				state = state.with(uri, content.validators());
			}
			for (String stationId : wanted) {
				if (!seen.contains(stationId)) {
					count(skipped, "station-not-in-file", 1);
				}
			}
		}
		return anyChange ? new FetchResult.Fetched(datasets, state, skipped) : new FetchResult.Unchanged();
	}

	private static void count(Map<String, Integer> counts, String reason, int by) {
		if (by > 0) {
			counts.merge(reason, by, Integer::sum);
		}
	}

	private record Target(String siteId, StationBinding binding) {
	}

	/** The parsed station forecast of one station from one KMZ — for tools and tests. */
	public static List<StationForecast> decode(InputStream kmz, Set<String> wanted) throws IOException {
		List<StationForecast> out = new ArrayList<>();
		try (InputStream kml = Unwrap.zip(kmz)) {
			MosmixKmlParser.parse(kml, wanted, out::add);
		}
		return out;
	}

}
