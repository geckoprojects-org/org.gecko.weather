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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.transport.ByteSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Both SIS providers against a fixture {@link ByteSource} that serves a folder listing and synthetic
 * files: analyses valued {@code minutes of day / 15} (so 12:00 → 48, 12:15 → 49 …), forecasts valued
 * {@code 10 · step}.
 */
class SisProvidersTest {

	private static final URI BASE = URI.create("https://opendata.dwd.de/weather/satellite/radiation/sis/");
	private static final Instant NOW = Instant.parse("2026-10-03T12:40:00Z");
	private static final Instant T1200 = Instant.parse("2026-10-03T12:00:00Z");
	private static final DateTimeFormatter MINUTES = DateTimeFormatter.ofPattern("yyyyMMddHHmm").withZone(ZoneOffset.UTC);
	private static final DateTimeFormatter HOURS = DateTimeFormatter.ofPattern("yyyyMMddHH").withZone(ZoneOffset.UTC);

	@TempDir
	Path tmp;

	private final TreeMap<Instant, String> analyses = new TreeMap<>();
	private final TreeMap<Instant, String> forecasts = new TreeMap<>();
	private final Map<String, byte[]> files = new HashMap<>();
	private final List<URI> requested = new ArrayList<>();
	private ByteSource source;
	private Site dresden;

	@BeforeEach
	void setUp() {
		for (int q = 0; q < 3; q++) {
			addAnalysis(T1200.plus(Duration.ofMinutes(15L * q)));
		}
		addForecast(Instant.parse("2026-10-03T11:00:00Z"));
		addForecast(T1200);
		source = (uri, validators) -> {
			requested.add(uri);
			if (uri.equals(BASE)) {
				StringBuilder html = new StringBuilder("<html><body>\n");
				analyses.values().forEach(n -> html.append("<a href=\"").append(n).append("\">").append(n).append("</a>\n"));
				forecasts.values().forEach(n -> html.append("<a href=\"").append(n).append("\">").append(n).append("</a>\n"));
				return new ByteSource.Content(new ByteArrayInputStream(html.append("</body></html>").toString().getBytes(StandardCharsets.UTF_8)),
						new SourceState.Entity(Optional.empty(), Optional.empty()));
			}
			String name = uri.toString().substring(BASE.toString().length());
			byte[] bytes = files.get(name);
			if (bytes == null) {
				throw new ByteSource.NotFoundException(uri, 404);
			}
			return new ByteSource.Content(new ByteArrayInputStream(bytes), new SourceState.Entity(Optional.of("\"" + name + "\""), Optional.empty()));
		};
		dresden = site("dresden", 51.0504, 13.7373);
	}

	private void addAnalysis(Instant time) {
		String name = "SISin" + MINUTES.format(time) + "DEv3.nc";
		analyses.put(time, name);
		int value = (int) (time.atOffset(ZoneOffset.UTC).toLocalTime().toSecondOfDay() / 900);
		files.put(name, SisTestFiles.analysis(tmp, time, (i, j) -> i == 0 && j == 180 ? -1 : value));
	}

	private void addForecast(Instant run) {
		String name = "SISfc" + HOURS.format(run) + "_fc%2B18h-DE.nc";
		forecasts.put(run, name);
		files.put(name, SisTestFiles.forecast(tmp, run, 18, (step, flat) -> 10 * step));
	}

	static Site site(String id, double lat, double lon) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Site s = f.createSite();
		s.setId(id);
		s.setName(id);
		s.setPosition(f.createGeoPosition());
		s.getPosition().setLatitude(lat);
		s.getPosition().setLongitude(lon);
		s.setTimeZone("Europe/Berlin");
		s.setActive(true);
		return s;
	}

	private static FetchRequest request(SourceState state, List<SiteBindings> sites, String... unconditional) {
		return new FetchRequest(sites, state, NOW, Set.of(unconditional));
	}

	private static SiteBindings bound(WeatherProvider provider, Site site) {
		return new SiteBindings(site, List.of(provider.bindingResolver().resolve(site, 3).get(0)));
	}

	private static List<Double> values(SourceDataset ds) {
		return ds.getValues().stream().map(MeasuredValue::getValue).toList();
	}

	// --- analysis ----------------------------------------------------------------------------

	@Test
	void analysisStreamsNewFilesOnly() throws IOException {
		SisAnalysisProvider provider = new SisAnalysisProvider(new SisAnalysisProvider.Settings(BASE, 2, "GeoNutzV", "DWD"), source,
				Clock.fixed(NOW, ZoneOffset.UTC));
		assertThat(provider.delivery()).isEqualTo(WeatherProvider.Delivery.STREAM);
		assertThat(provider.productId()).isEqualTo("SIS");
		assertThat(provider.expectedRefresh()).isEqualTo(Duration.ofMinutes(15));
		assertThat(provider.provides()).containsExactly(MeasurementKind.GLOBAL_RADIATION);
		SiteBindings home = bound(provider, dresden);
		GridBinding binding = (GridBinding) home.bindings().get(0);
		assertThat(binding.getCell().getGridId()).isEqualTo("sis-de-v3");
		assertThat(binding.getCell().getI()).isEqualTo(175);
		assertThat(binding.getCell().getJ()).isEqualTo(101);
		assertThat(binding.getDistanceMeters()).isBetween(800.0, 1000.0);

		// first run: everything listed is new
		FetchResult.Fetched first = (FetchResult.Fetched) provider.fetch(request(SourceState.EMPTY, List.of(home), "dresden"));
		assertThat(requested).hasSize(4).first().isEqualTo(BASE);
		assertThat(first.datasets()).containsOnlyKeys("dresden");
		SourceDataset ds = first.datasets().get("dresden").get(0);
		assertThat(values(ds)).containsExactly(48.0, 49.0, 50.0);
		assertThat(ds.getIssuedAt()).isEqualTo(T1200.plus(Duration.ofMinutes(30)));
		assertThat(ds.getModelRun()).isNull();
		assertThat(ds.getRetrievedAt()).isEqualTo(NOW);
		assertThat(ds.getExpectedRefresh()).isEqualTo(Duration.ofMinutes(15));
		assertThat(ds.getHorizonStart()).isEqualTo(T1200);
		assertThat(ds.getHorizonEnd()).isEqualTo(T1200.plus(Duration.ofMinutes(30)));
		assertThat(ds.getOrigin()).isEqualTo(Origin.GRID_CELL);
		assertThat(ds.getCell().getI()).isEqualTo(175);
		MeasuredValue v = ds.getValues().get(0);
		assertThat(v.getKind()).isEqualTo(MeasurementKind.GLOBAL_RADIATION);
		assertThat(v.getStatistic()).isEqualTo(Statistic.INSTANT);
		assertThat(v.getUnit()).isEqualTo("W/m2");
		assertThat(v.getValidAt()).isEqualTo(T1200);
		assertThat(v.getProvenance().getIssuedAt()).isEqualTo(T1200.plus(Duration.ofMinutes(30)));
		assertThat(v.getProvenance().getSourceElement()).isEqualTo("SIS");
		assertThat(v.getProvenance().getCell().getJ()).isEqualTo(101);
		assertThat(v.getUncertainty().getQuality()).isEqualTo(Quality.ANALYSIS);
		assertThat(v.getUncertainty().isSetLeadTime()).isFalse();
		assertThat(v.getUncertainty().getSpatialMeters()).isEqualTo(binding.getDistanceMeters());
		assertThat(first.state().entities()).hasSize(3);
		requested.clear();

		// nothing new: only the listing is read
		assertThat(provider.fetch(request(first.state(), List.of(home)))).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).containsExactly(BASE);
		requested.clear();

		// one new analysis: one file, one value
		addAnalysis(T1200.plus(Duration.ofMinutes(45)));
		FetchResult.Fetched second = (FetchResult.Fetched) provider.fetch(request(first.state(), List.of(home)));
		assertThat(requested).hasSize(2);
		assertThat(values(second.datasets().get("dresden").get(0))).containsExactly(51.0);
		assertThat(second.state().entities()).hasSize(4);
		requested.clear();

		// a site without data gets the last two again; the site that has them is not given them twice
		Site neighbour = site("neighbour", 51.06, 13.76); // same cell
		SiteBindings fresh = bound(provider, neighbour);
		FetchResult.Fetched third = (FetchResult.Fetched) provider.fetch(request(second.state(), List.of(home, fresh), "neighbour"));
		assertThat(requested).hasSize(3); // listing + two backfill files
		assertThat(third.datasets()).containsOnlyKeys("neighbour");
		assertThat(values(third.datasets().get("neighbour").get(0))).containsExactly(50.0, 51.0);
		requested.clear();

		// the folder rolls: a vanished file leaves the state, nothing is fetched
		analyses.pollFirstEntry();
		FetchResult.Fetched pruned = (FetchResult.Fetched) provider.fetch(request(third.state(), List.of(home)));
		assertThat(pruned.datasets()).isEmpty();
		assertThat(pruned.state().entities()).hasSize(3);
		assertThat(requested).containsExactly(BASE);
	}

	@Test
	void analysisSkipsMaskedCellsAndVanishedFiles() throws IOException {
		SisAnalysisProvider provider = new SisAnalysisProvider(SisAnalysisProvider.Settings.defaults(), source, Clock.fixed(NOW, ZoneOffset.UTC));
		Site sea = site("sea", 55.0, 5.0); // cell (0,180) is masked in the synthetic files
		files.remove(analyses.get(T1200)); // listed but gone
		FetchResult.Fetched fetched = (FetchResult.Fetched) provider.fetch(
				request(SourceState.EMPTY, List.of(bound(provider, dresden), bound(provider, sea)), "dresden", "sea"));
		assertThat(fetched.datasets()).containsOnlyKeys("dresden");
		assertThat(values(fetched.datasets().get("dresden").get(0))).containsExactly(49.0, 50.0);
		assertThat(fetched.skipped()).containsEntry("vanished", 1);
		assertThat(fetched.state().entities()).hasSize(2);
	}

	@Test
	void analysisWithoutGridBindingIsNothingToDo() throws IOException {
		SisAnalysisProvider provider = new SisAnalysisProvider(SisAnalysisProvider.Settings.defaults(), source, Clock.fixed(NOW, ZoneOffset.UTC));
		SourceBinding foreign = WeatherFactory.eINSTANCE.createStationBinding();
		foreign.setProviderId("dwd");
		foreign.setProductId("MOSMIX_L");
		assertThat(provider.fetch(request(SourceState.EMPTY, List.of(new SiteBindings(dresden, List.of(foreign))))))
				.isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).isEmpty();
		assertThat(provider.bindingResolver().resolve(site("madrid", 40.4, -3.7), 3)).isEmpty();
	}

	// --- forecast ----------------------------------------------------------------------------

	@Test
	void forecastTakesTheNewestRunAndRemembersIt() throws IOException {
		SisForecastProvider provider = new SisForecastProvider(SisForecastProvider.Settings.defaults(), source, Clock.fixed(NOW, ZoneOffset.UTC));
		assertThat(provider.delivery()).isEqualTo(WeatherProvider.Delivery.ISSUE);
		assertThat(provider.productId()).isEqualTo("SISfc");
		assertThat(provider.expectedRefresh()).isEqualTo(Duration.ofHours(1));
		SiteBindings home = bound(provider, dresden);
		assertThat(home.bindings().get(0).getProductId()).isEqualTo("SISfc");

		FetchResult.Fetched fetched = (FetchResult.Fetched) provider.fetch(request(SourceState.EMPTY, List.of(home), "dresden"));
		assertThat(requested).hasSize(2);
		assertThat(requested.get(1).getPath()).endsWith("SISfc2026100312_fc+18h-DE.nc");
		SourceDataset ds = fetched.datasets().get("dresden").get(0);
		assertThat(ds.getValues()).hasSize(18);
		assertThat(values(ds)).startsWith(0.0, 10.0, 20.0).endsWith(170.0);
		assertThat(ds.getIssuedAt()).isEqualTo(T1200);
		assertThat(ds.getModelRun()).isEqualTo(T1200);
		assertThat(ds.getHorizonStart()).isEqualTo(T1200);
		assertThat(ds.getHorizonEnd()).isEqualTo(T1200.plus(Duration.ofHours(17)));
		assertThat(ds.getExpectedRefresh()).isEqualTo(Duration.ofHours(1));
		MeasuredValue last = ds.getValues().get(17);
		assertThat(last.getStatistic()).isEqualTo(Statistic.MEAN);
		assertThat(last.getPeriod()).isEqualTo(Duration.ofHours(1));
		assertThat(last.getUncertainty().getQuality()).isEqualTo(Quality.FORECAST);
		assertThat(last.getUncertainty().getLeadTime()).isEqualTo(Duration.ofHours(17));
		assertThat(last.getProvenance().getModelRun()).isEqualTo(T1200);
		assertThat(fetched.state().entities()).hasSize(1);
		requested.clear();

		assertThat(provider.fetch(request(fetched.state(), List.of(home)))).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).containsExactly(BASE);
		requested.clear();

		addForecast(Instant.parse("2026-10-03T13:00:00Z"));
		FetchResult.Fetched newer = (FetchResult.Fetched) provider.fetch(request(fetched.state(), List.of(home)));
		assertThat(newer.datasets().get("dresden").get(0).getModelRun()).isEqualTo(Instant.parse("2026-10-03T13:00:00Z"));
		assertThat(newer.state().entities()).hasSize(1).allSatisfy((uri, e) -> assertThat(uri.getPath()).contains("2026100313"));
	}

	@Test
	void forecastContentErrors() {
		Clock clock = Clock.fixed(NOW, ZoneOffset.UTC);
		forecasts.clear();
		SisForecastProvider none = new SisForecastProvider(SisForecastProvider.Settings.defaults(), source, clock);
		assertThatThrownBy(() -> none.fetch(request(SourceState.EMPTY, List.of(bound(none, dresden)))))
				.isInstanceOf(FetchException.class).hasMessageContaining("no SIS forecast listed");

		addForecast(T1200);
		files.put(forecasts.get(T1200), SisTestFiles.forecast(tmp, Instant.parse("2026-10-03T09:00:00Z"), 18, (t, k) -> 1));
		SisForecastProvider liar = new SisForecastProvider(SisForecastProvider.Settings.defaults(), source, clock);
		assertThatThrownBy(() -> liar.fetch(request(SourceState.EMPTY, List.of(bound(liar, dresden)))))
				.isInstanceOf(FetchException.class).hasMessageContaining("its name says run");
	}
}
