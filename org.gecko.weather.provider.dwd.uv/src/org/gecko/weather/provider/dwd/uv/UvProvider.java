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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.RegularGridBindingResolver;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.grib2.Grib2Field;
import org.gecko.weather.grib2.Grib2Reader;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.transport.ByteSource;
import org.gecko.weather.transport.Unwrap;

/**
 * {@link WeatherProvider} for DWD's UV index forecast ({@code UVI_MAX_CL}: daily maximum under the
 * forecast cloud cover) on the ICON-EU grid: one 5.4 MB GRIB2 file a day, published around
 * 04:30 UTC, holding the maxima of today, tomorrow and the day after. The folder listing names the
 * file (its name carries the processing time); the newest run replaces the previous dataset. The
 * hour of the maximum ({@code uvh}) and the global file are not read.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public class UvProvider implements WeatherProvider {

	public static final String PROVIDER_ID = "dwd";
	public static final String PRODUCT_ID = "UVI";
	public static final Duration EXPECTED_REFRESH = Duration.ofDays(1);
	/** UCUM for a dimensionless index. */
	public static final String UNIT = "1";
	static final URI DEFAULT_BASE = URI.create("https://opendata.dwd.de/climate_environment/health/forecasts/");

	/** GRIB2: discipline 0, category 4 (short-wave radiation), number 51 (UV index), template 8, maximum, height above ground. */
	static final int CATEGORY = 4;
	static final int NUMBER = 51;
	static final int TEMPLATE = 8;
	static final int MAXIMUM = 2;
	static final int HEIGHT_ABOVE_GROUND = 103;

	/** {@code Z__C_EDZW_<yyyyMMddHHmmss>_grb02,icreu_uvi_icreu__000048_999999_<yyMMddHH>00_HPC.bin}; the comma is URL-encoded in the listing. */
	static final Pattern FILE = Pattern.compile(
			"^Z__C_EDZW_(\\d{14})_grb02(?:%2C|,)icreu_uvi_icreu__000048_999999_(\\d{8})00_HPC\\.bin$");
	private static final Pattern HREF = Pattern.compile("href=\"([^\"?#]+\\.bin)\"");
	private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter RUN = DateTimeFormatter.ofPattern("uuMMddHH");
	private static final Logger LOG = System.getLogger(UvProvider.class.getName());

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

	/** One file in the listing: the model run it is for, when it was produced, its name as linked. */
	public record Issue(Instant run, Instant processedAt, String name) {
	}

	private final Settings settings;
	private final ByteSource source;
	private final Clock clock;
	private final SiteBindingResolver resolver;

	public UvProvider(Settings settings, ByteSource source, Clock clock) {
		this.settings = requireNonNull(settings, "settings");
		this.source = requireNonNull(source, "source");
		this.clock = requireNonNull(clock, "clock");
		this.resolver = new RegularGridBindingResolver(IconEuGrid.GRID, PROVIDER_ID, PRODUCT_ID, clock);
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
		return Set.of(MeasurementKind.UV_INDEX);
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

		SortedMap<Instant, Issue> issues = issues(listing());
		if (issues.isEmpty()) {
			throw new FetchException("no UV index file listed under " + settings.baseUri());
		}
		Issue newest = issues.get(issues.lastKey());
		URI uri = settings.baseUri().resolve(newest.name());
		if (request.state().entity(uri).isPresent() && !everythingAgain) {
			return new FetchResult.Unchanged();
		}

		ByteSource.Result result;
		try {
			result = source.fetch(uri, Optional.empty());
		} catch (ByteSource.NotFoundException gone) {
			throw new FetchException("UV index file " + uri + " is listed but not there", gone);
		}
		if (!(result instanceof ByteSource.Content content)) {
			throw new FetchException("unconditional fetch of " + uri + " answered 'unchanged'");
		}
		List<Grib2Field> fields;
		try (content; InputStream in = Unwrap.byName(uri.getPath(), content.data())) {
			fields = Grib2Reader.readAll(in.readAllBytes());
		}
		for (Grib2Field f : fields) {
			verify(f, newest.run(), uri);
		}
		LOG.log(Level.INFO, "[{0}/{1}] run {2} processed {3}: {4} daily maxima, {5} cell(s)", PROVIDER_ID, PRODUCT_ID, newest.run(),
				newest.processedAt(), fields.size(), targets.size());

		Instant now = clock.instant();
		Map<String, List<SourceDataset>> datasets = new HashMap<>();
		Map<String, Integer> skipped = new HashMap<>();
		for (Map.Entry<Cell, List<Target>> e : targets.entrySet()) {
			TreeMap<Instant, Double> values = new TreeMap<>();
			for (Grib2Field f : fields) {
				float v = f.valueAt(e.getKey().i(), e.getKey().j());
				if (!Float.isNaN(v)) {
					values.put(dayEnd(f), (double) v);
				}
			}
			for (Target t : e.getValue()) {
				if (values.isEmpty()) {
					skipped.merge("cell-without-data", 1, Integer::sum);
					continue;
				}
				datasets.computeIfAbsent(t.siteId(), k -> new ArrayList<>()).add(dataset(t.binding(), newest, values, now));
			}
		}
		return new FetchResult.Fetched(datasets, new SourceState(Map.of(uri, content.validators())), skipped);
	}

	// --- listing -----------------------------------------------------------------------------

	private String listing() throws IOException {
		ByteSource.Result result = source.fetch(settings.baseUri(), Optional.empty());
		if (!(result instanceof ByteSource.Content content)) {
			throw new FetchException("listing " + settings.baseUri() + " answered 'unchanged' to an unconditional request");
		}
		try (content; InputStream in = content.data()) {
			return new String(in.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	/** The UV index files a listing page links to, by run; several files for one run → the latest processed. */
	public static SortedMap<Instant, Issue> issues(String html) {
		SortedMap<Instant, Issue> out = new TreeMap<>();
		Matcher links = HREF.matcher(requireNonNull(html, "html"));
		while (links.find()) {
			String href = links.group(1);
			String name = href.substring(href.lastIndexOf('/') + 1);
			Matcher m = FILE.matcher(name);
			if (!m.matches()) {
				continue;
			}
			Instant processed = LocalDateTime.parse(m.group(1), STAMP).toInstant(ZoneOffset.UTC);
			Instant run = LocalDateTime.parse(m.group(2), RUN).toInstant(ZoneOffset.UTC);
			Issue issue = new Issue(run, processed, name);
			out.merge(run, issue, (a, b) -> a.processedAt().isAfter(b.processedAt()) ? a : b);
		}
		return out;
	}

	// --- content -----------------------------------------------------------------------------

	/**
	 * The day a record is the maximum of: DWD writes forecast time {@code k} days and an interval
	 * <em>end</em> equal to that very instant (range length 0) — the start of day {@code k}, not its end
	 * as the GRIB convention would have it. The README beside the files says {@code stepRange 0-24} for
	 * {@code k = 0}. So the value is valid for the day beginning at {@code reference + k days}, and
	 * carries its end as {@code validAt}, like every other aggregate here.
	 */
	static Instant dayEnd(Grib2Field f) {
		return f.interval().map(Grib2Field.Interval::start).orElse(f.validAt()).plus(Duration.ofDays(1));
	}

	private static void verify(Grib2Field f, Instant run, URI uri) {
		if (!IconEuGrid.matches(f.grid())) {
			throw new FetchException("UV index " + uri + ": grid " + f.grid() + " is not " + IconEuGrid.DEFINITION);
		}
		if (f.category() != CATEGORY || f.number() != NUMBER) {
			throw new FetchException("UV index " + uri + ": parameter " + f.category() + "/" + f.number() + ", expected " + CATEGORY + "/" + NUMBER);
		}
		if (f.pdsTemplate() != TEMPLATE || f.interval().isEmpty() || f.interval().get().statisticalProcess() != MAXIMUM) {
			throw new FetchException("UV index " + uri + ": not a daily maximum (template " + f.pdsTemplate() + ", process "
					+ f.interval().map(Grib2Field.Interval::statisticalProcess).orElse(-1) + ")");
		}
		if (f.levelType1() != HEIGHT_ABOVE_GROUND) {
			throw new FetchException("UV index " + uri + ": level type " + f.levelType1() + ", expected " + HEIGHT_ABOVE_GROUND);
		}
		if (!f.referenceTime().equals(run)) {
			throw new FetchException("UV index " + uri + ": file of run " + f.referenceTime() + ", its name says " + run);
		}
		Instant dayStart = f.interval().get().start();
		if (dayStart.isBefore(run) || Duration.between(run, dayStart).toHours() % 24 != 0) {
			throw new FetchException("UV index " + uri + ": record for " + dayStart + " is not a whole day after run " + run);
		}
	}

	/** Each value is the day's maximum, valid at the end of that day (midnight UTC), period one day. */
	private SourceDataset dataset(GridBinding binding, Issue issue, TreeMap<Instant, Double> values, Instant retrievedAt) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		SourceDataset ds = f.createSourceDataset();
		ds.setProviderId(PROVIDER_ID);
		ds.setProductId(PRODUCT_ID);
		ds.setIssuedAt(issue.processedAt());
		ds.setModelRun(issue.run());
		ds.setRetrievedAt(retrievedAt);
		ds.setExpectedRefresh(EXPECTED_REFRESH);
		ds.setHorizonStart(values.firstKey().minus(Duration.ofDays(1)));
		ds.setHorizonEnd(values.lastKey());
		ds.setOrigin(Origin.GRID_CELL);
		ds.setCell(EcoreUtil.copy(binding.getCell()));
		ds.setDistanceMeters(binding.getDistanceMeters());
		ds.setLicence(settings.licence());
		ds.setAttribution(settings.attribution());
		for (Map.Entry<Instant, Double> e : values.entrySet()) {
			MeasuredValue v = f.createMeasuredValue();
			v.setKind(MeasurementKind.UV_INDEX);
			v.setLevel(org.gecko.weather.model.weather.Level.SURFACE);
			v.setStatistic(Statistic.MAX);
			v.setPeriod(Duration.ofDays(1));
			v.setValidAt(e.getKey());
			v.setUnit(UNIT);
			v.setValue(e.getValue());
			Provenance p = f.createProvenance();
			p.setProviderId(PROVIDER_ID);
			p.setProductId(PRODUCT_ID);
			p.setSourceElement("UVI_MAX_CL");
			p.setModelRun(issue.run());
			p.setIssuedAt(issue.processedAt());
			p.setRetrievedAt(retrievedAt);
			p.setOrigin(Origin.GRID_CELL);
			p.setCell(EcoreUtil.copy(binding.getCell()));
			p.setDistanceMeters(binding.getDistanceMeters());
			p.setLicence(settings.licence());
			p.setAttribution(settings.attribution());
			v.setProvenance(p);
			Uncertainty u = f.createUncertainty();
			u.setQuality(Quality.FORECAST);
			u.setSpatialMeters(binding.getDistanceMeters());
			u.setLeadTime(Duration.between(issue.run(), e.getKey()));
			v.setUncertainty(u);
			ds.getValues().add(v);
		}
		return ds;
	}

	private static Map<Cell, List<Target>> targets(List<SiteBindings> sites) {
		Map<Cell, List<Target>> targets = new TreeMap<>();
		for (SiteBindings sb : sites) {
			for (SourceBinding b : sb.bindings()) {
				if (b instanceof GridBinding grid && PROVIDER_ID.equals(b.getProviderId()) && PRODUCT_ID.equals(b.getProductId())
						&& grid.getCell() != null) {
					IconEuGrid.GRID.cellOf(grid.getCell())
							.ifPresent(cell -> targets.computeIfAbsent(cell, k -> new ArrayList<>()).add(new Target(sb.site().getId(), grid)));
				}
			}
		}
		return targets;
	}

	private record Target(String siteId, GridBinding binding) {
	}
}
