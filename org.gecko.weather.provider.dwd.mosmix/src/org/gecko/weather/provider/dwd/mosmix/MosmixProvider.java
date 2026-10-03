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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.resource.ResourceSet;
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
import org.gecko.weather.provider.dwd.mosmix.MosmixKmlDecoder.StationForecast;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for DWD MOSMIX_L, plain Java: one KMZ per station, ~115 elements, issued
 * every six hours. One download per distinct bound station; every site bound to that station gets
 * its dataset from the same bytes. Change detection is per URL through the {@link SourceState} the
 * runtime hands in; unchanged files are neither decoded nor reported.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class MosmixProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String PRODUCT_ID = "MOSMIX_L";
	public static final Duration EXPECTED_REFRESH = Duration.ofHours(6);

	/** Everything that differs between deployments. */
	public record Settings(URI baseUri, String licence, String attribution) {
		public Settings {
			requireNonNull(baseUri, "baseUri");
		}

		public static Settings defaults() {
			return new Settings(URI.create("https://opendata.dwd.de/weather/local_forecasts/mos/"), "GeoNutzV",
					"Datenbasis: Deutscher Wetterdienst");
		}

		/** The KMZ of one station. */
		URI kmzUri(String stationId) {
			return baseUri.resolve("MOSMIX_L/single_stations/" + stationId + "/kml/MOSMIX_L_LATEST_" + stationId + ".kmz");
		}
	}

	private final Settings settings;
	private final ByteSource source;
	private final Supplier<ResourceSet> resourceSets;
	private final MosmixBindingResolver resolver;
	private final Clock clock;

	/**
	 * @param resourceSets supplies the resource set to decode with — a fresh one each time or the same
	 *                     one every time; the decoder synchronises on it and cleans up after itself
	 */
	public MosmixProvider(Settings settings, ByteSource source, Supplier<ResourceSet> resourceSets,
			Supplier<StationCatalog> catalog, Clock clock) {
		this.settings = requireNonNull(settings, "settings");
		this.source = requireNonNull(source, "source");
		this.resourceSets = requireNonNull(resourceSets, "resourceSets");
		this.clock = requireNonNull(clock, "clock");
		this.resolver = new MosmixBindingResolver(PROVIDER_ID, PRODUCT_ID, requireNonNull(catalog, "catalog"), clock);
	}

	@Override
	public String providerId() {
		return PROVIDER_ID;
	}

	@Override
	public String productId() {
		return PRODUCT_ID;
	}

	@Override
	public Origin origin() {
		return Origin.STATION;
	}

	@Override
	public Duration expectedRefresh() {
		return EXPECTED_REFRESH;
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
						&& PRODUCT_ID.equals(b.getProductId())) {
					targets.computeIfAbsent(station.getStation().getId(), k -> new ArrayList<>())
							.add(new Target(sb.site().getId(), station));
				}
			}
		}
		if (targets.isEmpty()) {
			return new FetchResult.Unchanged();
		}

		SourceState state = request.state();
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		Map<String, Integer> skipped = new HashMap<>();
		boolean anyChange = false;
		ProductInfo product = new ProductInfo(PROVIDER_ID, PRODUCT_ID, EXPECTED_REFRESH, settings.licence(), settings.attribution());

		for (Map.Entry<String, List<Target>> e : targets.entrySet()) {
			String stationId = e.getKey();
			URI uri = settings.kmzUri(stationId);
			// a site without data for this station needs the file even if nothing changed
			boolean unconditional = request.isUnconditional(e.getValue().stream().map(Target::siteId).toList());
			ByteSource.Result result = source.fetch(uri, unconditional ? java.util.Optional.empty() : state.entity(uri));
			if (result instanceof ByteSource.Unchanged) {
				continue;
			}
			anyChange = true;
			Set<String> seen = new HashSet<>();
			try (ByteSource.Content content = (ByteSource.Content) result; InputStream kml = Unwrap.zip(content.data())) {
				MosmixKmlDecoder.decode(kml, Set.of(stationId), forecast -> {
					seen.add(forecast.stationId());
					count(skipped, "rejected-elements", forecast.rejectedElements().size());
					for (Target t : e.getValue()) {
						datasets.computeIfAbsent(t.siteId(), k -> new ArrayList<>())
								.add(MosmixDatasets.build(forecast, t.binding(), product, clock.instant()));
					}
				}, resourceSets.get());
				state = state.with(uri, content.validators());
			}
			if (!seen.contains(stationId)) {
				count(skipped, "station-not-in-file", 1);
			}
		}
		return anyChange ? new FetchResult.Fetched(datasets, state, skipped) : new FetchResult.Unchanged();
	}

	/** The station forecasts in one KMZ — for tools and tests. */
	public List<StationForecast> decode(InputStream kmz, Set<String> wanted) throws IOException {
		List<StationForecast> out = new ArrayList<>();
		try (InputStream kml = Unwrap.zip(kmz)) {
			MosmixKmlDecoder.decode(kml, wanted, out::add, resourceSets.get());
		}
		return out;
	}

	private static void count(Map<String, Integer> counts, String reason, int by) {
		if (by > 0) {
			counts.merge(reason, by, Integer::sum);
		}
	}

	private record Target(String siteId, StationBinding binding) {
	}

}
