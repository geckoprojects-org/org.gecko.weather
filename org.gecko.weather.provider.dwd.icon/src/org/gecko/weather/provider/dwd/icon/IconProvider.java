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

import static java.util.Objects.requireNonNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.provider.dwd.icon.IconD2Grid.Cell;
import org.gecko.weather.provider.dwd.icon.IconDatasets.ProductInfo;
import org.gecko.weather.provider.dwd.icon.IconParameters.Parameter;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for DWD ICON-D2, plain Java. A model run is a set of immutable files, one
 * per parameter and forecast step (the radiation files hold four quarter-hour records of which the
 * full hour is read), published step by step over about forty minutes; the newest
 * complete run among the configured run hours is fetched whole — every file once, unconditionally —
 * and the values at all bound cells are taken from each field before the next one is read. The
 * {@link SourceState} the runtime hands back holds the URIs of the run last fetched: a run whose
 * files are all known is not fetched again, so a poll between runs costs at most the probe of a
 * run that is still being published.
 * <p>
 * Volume: a run is {@code parameters × (horizon + 1)} files, 0.8 MB (cloud) to 4.5 MB (radiation by
 * day) each — measured 515 MB over the wire for the default six parameters over 48 h. Which runs a
 * deployment takes is therefore configuration, defaulting to four a day.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class IconProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String PRODUCT_ID = "ICON-D2";

	private static final Logger LOG = System.getLogger(IconProvider.class.getName());
	private static final DateTimeFormatter RUN_STAMP = DateTimeFormatter.ofPattern("yyyyMMddHH", Locale.ROOT).withZone(ZoneOffset.UTC);
	/** How many run times back to look for a complete run before giving up. */
	static final int RUNS_TO_TRY = 3;

	/** Everything that differs between deployments. */
	public record Settings(URI baseUri, List<Integer> runHours, int horizonHours, List<Parameter> parameters, String licence,
			String attribution) {

		public Settings {
			requireNonNull(baseUri, "baseUri");
			if (!baseUri.toString().endsWith("/")) {
				baseUri = URI.create(baseUri + "/");
			}
			requireNonNull(runHours, "runHours");
			runHours = List.copyOf(new TreeSet<>(runHours));
			if (runHours.isEmpty() || runHours.stream().anyMatch(h -> h < 0 || h > 23)) {
				throw new IllegalArgumentException("run hours must be a non-empty set of 0..23, got " + runHours);
			}
			if (horizonHours < 1 || horizonHours > 48) {
				throw new IllegalArgumentException("ICON-D2 horizon is 1..48 h, got " + horizonHours);
			}
			parameters = List.copyOf(requireNonNull(parameters, "parameters"));
			if (parameters.isEmpty()) {
				throw new IllegalArgumentException("at least one parameter");
			}
		}

		public static Settings defaults() {
			return new Settings(URI.create("https://opendata.dwd.de/weather/nwp/icon-d2/grib/"), List.of(0, 6, 12, 18), 48,
					IconParameters.all(), "GeoNutzV", "Datenbasis: Deutscher Wetterdienst");
		}

		/** The longest gap between two configured runs — how stale a dataset may legitimately be. */
		public Duration expectedRefresh() {
			int max = 0;
			for (int k = 0; k < runHours.size(); k++) {
				int from = runHours.get(k);
				int to = k + 1 < runHours.size() ? runHours.get(k + 1) : runHours.get(0) + 24;
				max = Math.max(max, to - from);
			}
			return Duration.ofHours(max);
		}

		/** {@code <HH>/<param>/icon-d2_germany_regular-lat-lon_single-level_<yyyyMMddHH>_<step>_2d_<param>.grib2.bz2} */
		public URI fileUri(Instant run, Parameter parameter, int step) {
			String stamp = RUN_STAMP.format(run);
			return baseUri.resolve(String.format(Locale.ROOT,
					"%s/%s/icon-d2_germany_regular-lat-lon_single-level_%s_%03d_2d_%s.grib2.bz2", stamp.substring(8),
					parameter.name(), stamp, step, parameter.name()));
		}

		/** Forecast steps 0..horizon. */
		public List<Integer> steps() {
			return IntStream.rangeClosed(0, horizonHours).boxed().toList();
		}

		/** Every file of a run, in the order it is fetched: the last step of each parameter first (the probe), then the rest. */
		public List<FileRef> files(Instant run) {
			List<FileRef> files = new ArrayList<>();
			for (Parameter p : parameters) {
				files.add(new FileRef(p, horizonHours, fileUri(run, p, horizonHours)));
			}
			for (Parameter p : parameters) {
				for (int step = 0; step < horizonHours; step++) {
					files.add(new FileRef(p, step, fileUri(run, p, step)));
				}
			}
			return files;
		}

		/** Run times at or before {@code now} among the configured hours, newest first, {@value #RUNS_TO_TRY} of them. */
		public List<Instant> candidateRuns(Instant now) {
			List<Instant> runs = new ArrayList<>();
			LocalDate day = LocalDateTime.ofInstant(now, ZoneOffset.UTC).toLocalDate();
			for (int back = 0; runs.size() < RUNS_TO_TRY; back++) {
				for (int k = runHours.size() - 1; k >= 0 && runs.size() < RUNS_TO_TRY; k--) {
					Instant run = day.minusDays(back).atTime(runHours.get(k), 0).toInstant(ZoneOffset.UTC);
					if (!run.isAfter(now)) {
						runs.add(run);
					}
				}
			}
			return runs;
		}
	}

	/** One file of a run. */
	public record FileRef(Parameter parameter, int step, URI uri) {
	}

	private final Settings settings;
	private final ByteSource source;
	private final Clock clock;
	private final IconBindingResolver resolver;

	public IconProvider(Settings settings, ByteSource source, Clock clock) {
		this.settings = requireNonNull(settings, "settings");
		this.source = requireNonNull(source, "source");
		this.clock = requireNonNull(clock, "clock");
		this.resolver = new IconBindingResolver(PROVIDER_ID, PRODUCT_ID, clock);
	}

	public Settings settings() {
		return settings;
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
		return settings.expectedRefresh();
	}

	@Override
	public Set<MeasurementKind> provides() {
		return IconParameters.kinds(settings.parameters());
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
		Map<Cell, List<Target>> targets = targets(request.sites());
		if (targets.isEmpty()) {
			return new FetchResult.Unchanged();
		}
		boolean everythingAgain = request.isUnconditional(
				targets.values().stream().flatMap(List::stream).map(Target::siteId).toList());
		Map<String, Integer> skipped = new HashMap<>();
		List<Instant> candidates = settings.candidateRuns(request.now());
		for (Instant run : candidates) {
			List<FileRef> files = settings.files(run);
			boolean known = files.stream().allMatch(f -> request.state().entity(f.uri()).isPresent());
			if (known && !everythingAgain) {
				return new FetchResult.Unchanged();
			}
			Optional<RunData> data = download(run, files, targets.keySet(), skipped);
			if (data.isEmpty()) {
				continue;
			}
			// retrievedAt is when the run was in hand, not when the poll started — a run takes a minute
			return datasets(run, data.get(), targets, skipped, clock.instant());
		}
		throw new FetchException("no complete ICON-D2 run among " + candidates + " under " + settings.baseUri());
	}

	// --- one run -----------------------------------------------------------------------------

	/** The raw series per cell and parameter, and the validators of every file. */
	private record RunData(Map<Cell, Map<Parameter, TreeMap<Instant, Double>>> raw, Map<URI, SourceState.Entity> validators) {
	}

	/**
	 * Fetches every file of the run. Empty if a file is not published (yet) — the run is incomplete
	 * and the caller falls back to an older one; nothing of it is kept.
	 */
	private Optional<RunData> download(Instant run, List<FileRef> files, Collection<Cell> cells, Map<String, Integer> skipped)
			throws IOException {
		Map<Cell, Map<Parameter, TreeMap<Instant, Double>>> raw = new TreeMap<>();
		Map<URI, SourceState.Entity> validators = new LinkedHashMap<>();
		long transferred = 0;
		long decoded = 0;
		for (FileRef file : files) {
			ByteSource.Result result;
			try {
				result = source.fetch(file.uri(), Optional.empty());
			} catch (ByteSource.NotFoundException e) {
				LOG.log(Level.INFO, "[{0}/{1}] run {2} not complete, {3} missing", PROVIDER_ID, PRODUCT_ID, run, file.uri());
				count(skipped, "run-incomplete", 1);
				return Optional.empty();
			}
			if (!(result instanceof ByteSource.Content content)) {
				throw new FetchException("unconditional fetch of " + file.uri() + " answered 'unchanged'");
			}
			Grib2Field field;
			Instant validAt = run.plus(Duration.ofHours(file.step()));
			try (content; Counting wire = new Counting(content.data()); InputStream grib = Unwrap.byName(file.uri().getPath(), wire)) {
				byte[] message = grib.readAllBytes();
				transferred += wire.count;
				decoded += message.length;
				// the radiation files hold four records per step (:00, :15, :30, :45); the full hour is the one wanted
				field = Grib2Reader.read(message, validAt);
			}
			verify(file, run, field);
			for (Cell cell : cells) {
				float value = field.valueAt(cell.i(), cell.j());
				if (!Float.isNaN(value)) {
					raw.computeIfAbsent(cell, c -> new LinkedHashMap<>()).computeIfAbsent(file.parameter(), p -> new TreeMap<>())
							.put(field.validAt(), (double) value);
				}
			}
			validators.put(file.uri(), content.validators());
		}
		LOG.log(Level.INFO, "[{0}/{1}] run {2}: {3} files, {4} MB transferred, {5} MB decoded, {6} cell(s)", PROVIDER_ID,
				PRODUCT_ID, run, files.size(), transferred / (1024 * 1024), decoded / (1024 * 1024), cells.size());
		return Optional.of(new RunData(raw, validators));
	}

	/** The file must be what its name says: this grid, this parameter, this run and step. */
	private void verify(FileRef file, Instant run, Grib2Field field) {
		if (!IconD2Grid.matches(field.grid())) {
			throw new FetchException("ICON-D2 " + file.parameter().name() + ": grid " + field.grid() + " is not " + IconD2Grid.DEFINITION);
		}
		IconParameters.check(file.parameter(), field);
		if (!field.referenceTime().equals(run)) {
			throw new FetchException("ICON-D2 " + file.parameter().name() + ": file of run " + field.referenceTime() + " under " + file.uri());
		}
		Instant expected = run.plus(Duration.ofHours(file.step()));
		if (!field.validAt().equals(expected)) {
			throw new FetchException("ICON-D2 " + file.parameter().name() + ": step " + file.step() + " valid at " + field.validAt()
					+ ", expected " + expected);
		}
	}

	private FetchResult datasets(Instant run, RunData data, Map<Cell, List<Target>> targets, Map<String, Integer> skipped,
			Instant retrievedAt) {
		ProductInfo product = new ProductInfo(PROVIDER_ID, PRODUCT_ID, settings.expectedRefresh(), settings.licence(),
				settings.attribution());
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		for (Map.Entry<Cell, List<Target>> e : targets.entrySet()) {
			Map<Parameter, TreeMap<Instant, Double>> series = hourly(run, data.raw().getOrDefault(e.getKey(), Map.of()));
			for (Target t : e.getValue()) {
				SourceDataset ds = IconDatasets.build(t.binding(), product, run, series, retrievedAt);
				if (ds.getValues().isEmpty()) {
					count(skipped, "cell-without-data", 1);
					continue;
				}
				datasets.computeIfAbsent(t.siteId(), k -> new ArrayList<>()).add(ds);
			}
		}
		return new FetchResult.Fetched(datasets, new SourceState(data.validators()), skipped);
	}

	/**
	 * Averages since model start become hourly means; everything else passes through. The
	 * difference of two rounded means can come out a hair below zero at night; radiation is not
	 * negative, so anything above −1 W/m² is 0.
	 */
	static Map<Parameter, TreeMap<Instant, Double>> hourly(Instant run, Map<Parameter, TreeMap<Instant, Double>> raw) {
		Map<Parameter, TreeMap<Instant, Double>> out = new LinkedHashMap<>();
		for (Map.Entry<Parameter, TreeMap<Instant, Double>> e : raw.entrySet()) {
			if (!e.getKey().averagedSinceStart()) {
				out.put(e.getKey(), e.getValue());
				continue;
			}
			TreeMap<Instant, Double> means = new TreeMap<>();
			for (Map.Entry<Instant, Double> step : e.getValue().entrySet()) {
				Instant end = step.getKey();
				Instant start = end.minus(Duration.ofHours(1));
				if (!start.isAfter(run)) {
					if (end.isAfter(run)) {
						means.put(end, nonNegative(IconParameters.hourly(step.getValue(), Duration.between(run, end), 0, Duration.ZERO)));
					}
					continue; // step 0 covers no interval
				}
				Double previous = e.getValue().get(start);
				if (previous != null) {
					means.put(end, nonNegative(
							IconParameters.hourly(step.getValue(), Duration.between(run, end), previous, Duration.between(run, start))));
				}
			}
			out.put(e.getKey(), means);
		}
		return out;
	}

	// --- helpers -----------------------------------------------------------------------------

	private static double nonNegative(double value) {
		return value < 0 && value > -1 ? 0.0 : value;
	}

	private static Map<Cell, List<Target>> targets(List<SiteBindings> sites) {
		Map<Cell, List<Target>> targets = new TreeMap<>();
		for (SiteBindings sb : sites) {
			for (SourceBinding b : sb.bindings()) {
				if (b instanceof GridBinding grid && PROVIDER_ID.equals(b.getProviderId()) && PRODUCT_ID.equals(b.getProductId())
						&& grid.getCell() != null) {
					IconD2Grid.cellOf(grid.getCell())
							.ifPresent(cell -> targets.computeIfAbsent(cell, k -> new ArrayList<>()).add(new Target(sb.site().getId(), grid)));
				}
			}
		}
		return targets;
	}

	private static void count(Map<String, Integer> counts, String reason, int by) {
		if (by > 0) {
			counts.merge(reason, by, Integer::sum);
		}
	}

	private record Target(String siteId, GridBinding binding) {
	}

	/** Counts the bytes that went over the wire, for the volume log (R-11). */
	private static final class Counting extends FilterInputStream {
		long count;

		Counting(InputStream in) {
			super(in);
		}

		@Override
		public int read() throws IOException {
			int b = super.read();
			if (b >= 0) {
				count++;
			}
			return b;
		}

		@Override
		public int read(byte[] buffer, int off, int len) throws IOException {
			int n = super.read(buffer, off, len);
			if (n > 0) {
				count += n;
			}
			return n;
		}
	}
}
