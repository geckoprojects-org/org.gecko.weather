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
package org.gecko.weather.provider.dwd.sis;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.RegularGridBindingResolver;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.provider.dwd.sis.SisDatasets.ProductInfo;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for the DWD SIS satellite <em>analysis</em>: global radiation at 0.05°
 * for Germany every 15 minutes, one 100 KB file per instant, kept on the server for about ten
 * hours. A <b>stream</b>: each run reads the folder listing, fetches the analyses it has not seen
 * and delivers their values as a partial dataset per site and cell, which the runtime appends to
 * the product's rolling dataset. The {@link SourceState} holds the URIs already taken, pruned to
 * what the listing still shows. A site without data yet gets the last {@code backfillSteps}
 * analyses again; the others are not given those values twice.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class SisAnalysisProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String PRODUCT_ID = "SIS";
	public static final Duration EXPECTED_REFRESH = Duration.ofMinutes(15);

	private static final Logger LOG = System.getLogger(SisAnalysisProvider.class.getName());

	/** Everything that differs between deployments. */
	public record Settings(URI baseUri, int backfillSteps, String licence, String attribution) {
		public Settings {
			requireNonNull(baseUri, "baseUri");
			if (!baseUri.toString().endsWith("/")) {
				baseUri = URI.create(baseUri + "/");
			}
			if (backfillSteps < 0) {
				throw new IllegalArgumentException("backfillSteps must not be negative");
			}
		}

		public static Settings defaults() {
			return new Settings(SisForecastProvider.DEFAULT_BASE, 8, "GeoNutzV", "Datenbasis: Deutscher Wetterdienst");
		}
	}

	private final Settings settings;
	private final ByteSource source;
	private final Clock clock;
	private final SiteBindingResolver resolver;

	public SisAnalysisProvider(Settings settings, ByteSource source, Clock clock) {
		this.settings = requireNonNull(settings, "settings");
		this.source = requireNonNull(source, "source");
		this.clock = requireNonNull(clock, "clock");
		this.resolver = new RegularGridBindingResolver(SisGrid.GRID, PROVIDER_ID, PRODUCT_ID, clock);
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
		return Origin.GRID_CELL;
	}

	@Override
	public Delivery delivery() {
		return Delivery.STREAM;
	}

	@Override
	public Duration expectedRefresh() {
		return EXPECTED_REFRESH;
	}

	@Override
	public Set<MeasurementKind> provides() {
		return Set.of(MeasurementKind.GLOBAL_RADIATION);
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
		Map<Cell, List<Target>> targets = SisForecastProvider.targets(request.sites(), PRODUCT_ID);
		if (targets.isEmpty()) {
			return new FetchResult.Unchanged();
		}
		Set<String> fresh = new java.util.HashSet<>();
		targets.values().forEach(list -> list.forEach(t -> {
			if (request.unconditional().contains(t.siteId())) {
				fresh.add(t.siteId());
			}
		}));

		SortedMap<Instant, String> listed = SisListing.analyses(SisListing.read(source, settings.baseUri()));
		List<Instant> backfill = new ArrayList<>(listed.keySet());
		backfill = backfill.subList(Math.max(0, backfill.size() - settings.backfillSteps()), backfill.size());

		// what to fetch, oldest first: everything new, plus the backfill for sites that have nothing yet
		Map<Instant, Boolean> wanted = new LinkedHashMap<>(); // valid time → is new (for everyone)
		for (Map.Entry<Instant, String> e : listed.entrySet()) {
			boolean isNew = request.state().entity(SisListing.uri(settings.baseUri(), e.getValue())).isEmpty();
			if (isNew || (!fresh.isEmpty() && backfill.contains(e.getKey()))) {
				wanted.put(e.getKey(), isNew);
			}
		}
		SourceState state = prune(request.state(), listed);
		if (wanted.isEmpty()) {
			return state.equals(request.state()) ? new FetchResult.Unchanged() : new FetchResult.Fetched(Map.of(), state);
		}

		Map<Cell, TreeMap<Instant, Double>> forEveryone = new TreeMap<>();
		Map<Cell, TreeMap<Instant, Double>> forFreshOnly = new TreeMap<>();
		Map<String, Integer> skipped = new HashMap<>();
		int files = 0;
		for (Map.Entry<Instant, Boolean> e : wanted.entrySet()) {
			URI uri = SisListing.uri(settings.baseUri(), listed.get(e.getKey()));
			ByteSource.Result result;
			try {
				result = source.fetch(uri, Optional.empty());
			} catch (ByteSource.NotFoundException gone) {
				count(skipped, "vanished", 1); // listed a moment ago, rolled out since
				continue;
			}
			if (!(result instanceof ByteSource.Content content)) {
				throw new FetchException("unconditional fetch of " + uri + " answered 'unchanged'");
			}
			SisNetcdf.Field field;
			try (content; InputStream in = Unwrap.byName(uri.getPath(), content.data())) {
				field = SisNetcdf.read(in.readAllBytes(), targets.keySet());
			}
			if (field.times().size() != 1 || !field.times().get(0).equals(e.getKey())) {
				throw new FetchException("SIS analysis " + uri + " is valid at " + field.times() + ", its name says " + e.getKey());
			}
			files++;
			Map<Cell, TreeMap<Instant, Double>> into = e.getValue() ? forEveryone : forFreshOnly;
			for (Cell cell : targets.keySet()) {
				double v = field.value(cell, 0);
				if (!Double.isNaN(v)) {
					into.computeIfAbsent(cell, c -> new TreeMap<>()).put(e.getKey(), v);
				}
			}
			state = state.with(uri, content.validators());
		}
		LOG.log(Level.INFO, "[{0}/{1}] {2} analyses read, {3} listed", PROVIDER_ID, PRODUCT_ID, files, listed.size());

		ProductInfo product = new ProductInfo(PROVIDER_ID, PRODUCT_ID, EXPECTED_REFRESH, Statistic.INSTANT, null,
				Quality.ANALYSIS, settings.licence(), settings.attribution());
		Instant now = clock.instant();
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		for (Map.Entry<Cell, List<Target>> e : targets.entrySet()) {
			for (Target t : e.getValue()) {
				TreeMap<Instant, Double> values = new TreeMap<>();
				if (fresh.contains(t.siteId())) {
					values.putAll(forFreshOnly.getOrDefault(e.getKey(), new TreeMap<>()));
				}
				values.putAll(forEveryone.getOrDefault(e.getKey(), new TreeMap<>()));
				if (values.isEmpty()) {
					continue;
				}
				datasets.computeIfAbsent(t.siteId(), k -> new ArrayList<>())
						.add(SisDatasets.build(t.binding(), product, values.lastKey(), Optional.empty(), values, now));
			}
		}
		return new FetchResult.Fetched(datasets, state, skipped);
	}

	/** The state without URIs the listing no longer shows — the folder is a rolling window, the state follows it. */
	private SourceState prune(SourceState state, SortedMap<Instant, String> listed) {
		Set<URI> current = new java.util.HashSet<>();
		listed.values().forEach(name -> current.add(SisListing.uri(settings.baseUri(), name)));
		Map<URI, SourceState.Entity> kept = new HashMap<>();
		state.entities().forEach((uri, entity) -> {
			if (current.contains(uri)) {
				kept.put(uri, entity);
			}
		});
		return new SourceState(kept);
	}

	static void count(Map<String, Integer> counts, String reason, int by) {
		if (by > 0) {
			counts.merge(reason, by, Integer::sum);
		}
	}

	/** A site and its binding to one cell. */
	record Target(String siteId, GridBinding binding) {
	}
}
