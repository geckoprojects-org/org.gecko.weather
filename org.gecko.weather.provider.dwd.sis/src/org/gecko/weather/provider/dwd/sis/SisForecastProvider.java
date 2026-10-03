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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
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
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.provider.dwd.sis.SisAnalysisProvider.Target;
import org.gecko.weather.provider.dwd.sis.SisDatasets.ProductInfo;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for the DWD SIS <em>forecast</em>: hourly means of global radiation at
 * 0.05° for Germany, 18 hours ahead, one 3.5 MB file per hourly run. The first hour is the mean of
 * the last four satellite analyses, the rest comes from ICON-D2 radiation — so beyond +1 h this
 * product is a smoothed, coarser cousin of the ICON-D2 dataset, which is why both are kept as they
 * are (ADR-0013). An <b>issue</b>: the newest run the folder lists replaces the previous one.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class SisForecastProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String PRODUCT_ID = "SISfc";
	public static final Duration EXPECTED_REFRESH = Duration.ofHours(1);
	static final URI DEFAULT_BASE = URI.create("https://opendata.dwd.de/weather/satellite/radiation/sis/");

	private static final Logger LOG = System.getLogger(SisForecastProvider.class.getName());

	/** Everything that differs between deployments. */
	public record Settings(URI baseUri, String licence, String attribution) {
		public Settings {
			requireNonNull(baseUri, "baseUri");
			if (!baseUri.toString().endsWith("/")) {
				baseUri = URI.create(baseUri + "/");
			}
		}

		public static Settings defaults() {
			return new Settings(DEFAULT_BASE, "GeoNutzV", "Datenbasis: Deutscher Wetterdienst");
		}
	}

	private final Settings settings;
	private final ByteSource source;
	private final Clock clock;
	private final SiteBindingResolver resolver;

	public SisForecastProvider(Settings settings, ByteSource source, Clock clock) {
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
		Map<Cell, List<Target>> targets = targets(request.sites(), PRODUCT_ID);
		if (targets.isEmpty()) {
			return new FetchResult.Unchanged();
		}
		boolean everythingAgain = request.isUnconditional(
				targets.values().stream().flatMap(List::stream).map(Target::siteId).toList());

		SortedMap<Instant, String> runs = SisListing.forecasts(SisListing.read(source, settings.baseUri()));
		if (runs.isEmpty()) {
			throw new FetchException("no SIS forecast listed under " + settings.baseUri());
		}
		Instant run = runs.lastKey();
		URI uri = SisListing.uri(settings.baseUri(), runs.get(run));
		if (request.state().entity(uri).isPresent() && !everythingAgain) {
			return new FetchResult.Unchanged();
		}

		ByteSource.Result result;
		try {
			result = source.fetch(uri, Optional.empty());
		} catch (ByteSource.NotFoundException gone) {
			throw new FetchException("SIS forecast " + uri + " is listed but not there (yet)", gone);
		}
		if (!(result instanceof ByteSource.Content content)) {
			throw new FetchException("unconditional fetch of " + uri + " answered 'unchanged'");
		}
		SisNetcdf.Field field;
		try (content; InputStream in = Unwrap.byName(uri.getPath(), content.data())) {
			field = SisNetcdf.read(in.readAllBytes(), targets.keySet());
		}
		if (field.times().isEmpty() || !field.times().get(0).equals(run)) {
			throw new FetchException("SIS forecast " + uri + " starts at " + (field.times().isEmpty() ? "nothing" : field.times().get(0))
					+ ", its name says run " + run);
		}
		LOG.log(Level.INFO, "[{0}/{1}] run {2}: {3} steps, {4} cell(s)", PROVIDER_ID, PRODUCT_ID, run, field.times().size(),
				targets.size());

		ProductInfo product = new ProductInfo(PROVIDER_ID, PRODUCT_ID, EXPECTED_REFRESH, Statistic.MEAN, Duration.ofHours(1),
				Quality.FORECAST, settings.licence(), settings.attribution());
		Instant now = clock.instant();
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		Map<String, Integer> skipped = new HashMap<>();
		for (Map.Entry<Cell, List<Target>> e : targets.entrySet()) {
			TreeMap<Instant, Double> values = new TreeMap<>();
			for (int t = 0; t < field.times().size(); t++) {
				double v = field.value(e.getKey(), t);
				if (!Double.isNaN(v)) {
					values.put(field.times().get(t), v);
				}
			}
			for (Target target : e.getValue()) {
				if (values.isEmpty()) {
					SisAnalysisProvider.count(skipped, "cell-without-data", 1);
					continue;
				}
				datasets.computeIfAbsent(target.siteId(), k -> new ArrayList<>())
						.add(SisDatasets.build(target.binding(), product, run, Optional.of(run), values, now));
			}
		}
		return new FetchResult.Fetched(datasets, new SourceState(Map.of(uri, content.validators())), skipped);
	}

	/** The sites per SIS cell, for the given product. */
	static Map<Cell, List<Target>> targets(List<SiteBindings> sites, String productId) {
		Map<Cell, List<Target>> targets = new TreeMap<>();
		for (SiteBindings sb : sites) {
			for (SourceBinding b : sb.bindings()) {
				if (b instanceof GridBinding grid && PROVIDER_ID.equals(b.getProviderId()) && productId.equals(b.getProductId())
						&& grid.getCell() != null) {
					SisGrid.GRID.cellOf(grid.getCell())
							.ifPresent(cell -> targets.computeIfAbsent(cell, k -> new ArrayList<>()).add(new Target(sb.site().getId(), grid)));
				}
			}
		}
		return targets;
	}
}
