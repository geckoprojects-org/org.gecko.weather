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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gecko.weather.api.Geo;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.dwd.icon.IconD2Grid.Cell;
import org.gecko.weather.provider.dwd.icon.IconParameters.Parameter;
import org.gecko.weather.provider.dwd.icon.IconProvider.Settings;
import org.gecko.weather.transport.ByteSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The provider against a fixture {@link ByteSource} that serves synthetic ICON-D2 runs: run 00 is
 * complete, run 12 lacks one file until a test says otherwise. Values: cloud cover
 * {@code 10·step + 1}, radiation mean since run {@code 5·step}, so the hourly mean is {@code 5·(2·step − 1)}.
 */
class IconProviderTest {

	private static final URI BASE = URI.create("https://opendata.dwd.de/weather/nwp/icon-d2/grib/");
	private static final Instant NOW = Instant.parse("2026-10-03T14:30:00Z");
	private static final Instant RUN_00 = Instant.parse("2026-10-03T00:00:00Z");
	private static final Instant RUN_12 = Instant.parse("2026-10-03T12:00:00Z");
	private static final Parameter CLCT = IconParameters.byName("clct").orElseThrow();
	private static final Parameter ASWDIR_S = IconParameters.byName("aswdir_s").orElseThrow();
	private static final Settings SETTINGS = new Settings(BASE, List.of(0, 12), 3, List.of(CLCT, ASWDIR_S), "GeoNutzV",
			"Datenbasis: Deutscher Wetterdienst");
	private static final Pattern FILE = Pattern.compile("_(\\d{10})_(\\d{3})_2d_(\\w+)\\.grib2\\.bz2$");
	private static final Cell DRESDEN = new Cell(884, 394);

	private final List<URI> requested = new ArrayList<>();
	private final Map<String, byte[]> files = new HashMap<>();
	private boolean run12Complete;
	private IntPredicate present;
	private IconProvider provider;
	private Site dresden;
	private GridBinding binding;

	@BeforeEach
	void setUp() {
		ByteSource fixtures = (uri, validators) -> {
			requested.add(uri);
			Matcher m = FILE.matcher(uri.getPath());
			if (!m.find()) {
				throw new ByteSource.NotFoundException(uri, 404);
			}
			String run = m.group(1);
			int step = Integer.parseInt(m.group(2));
			String name = m.group(3);
			if ("2026100312".equals(run) && !run12Complete && "aswdir_s".equals(name) && step == 3) {
				throw new ByteSource.NotFoundException(uri, 404);
			}
			if (!"2026100300".equals(run) && !"2026100312".equals(run)) {
				throw new ByteSource.NotFoundException(uri, 404);
			}
			byte[] bz2 = files.computeIfAbsent(run + "/" + name + "/" + step, k -> {
				Parameter p = IconParameters.byName(name).orElseThrow();
				int value = p.averagedSinceStart() ? 5 * step : 10 * step + 1;
				Instant reference = "2026100300".equals(run) ? RUN_00 : RUN_12;
				return Grib2TestFiles.bzip2(Grib2TestFiles.encode(p, reference, step, value, present));
			});
			return new ByteSource.Content(new ByteArrayInputStream(bz2),
					new SourceState.Entity(Optional.of("\"" + run + "-" + name + "-" + step + "\""), Optional.empty()));
		};
		provider = new IconProvider(SETTINGS, fixtures, Clock.fixed(NOW, ZoneOffset.UTC));
		dresden = site("dresden", 51.0504, 13.7373);
		binding = (GridBinding) provider.bindingResolver().resolve(dresden, 3).get(0);
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

	private FetchRequest request(SourceState state, String... unconditional) {
		return new FetchRequest(List.of(new SiteBindings(dresden, List.of(binding))), state, NOW, java.util.Set.of(unconditional));
	}

	@Test
	void describesItself() {
		assertThat(provider.providerId()).isEqualTo("dwd");
		assertThat(provider.productId()).isEqualTo("ICON-D2");
		assertThat(provider.origin()).isEqualTo(Origin.GRID_CELL);
		assertThat(provider.expectedRefresh()).isEqualTo(Duration.ofHours(12));
		assertThat(Settings.defaults().expectedRefresh()).isEqualTo(Duration.ofHours(6));
		assertThat(new Settings(BASE, List.of(0, 3, 6, 9, 12, 15, 18, 21), 48, IconParameters.all(), "", "").expectedRefresh())
				.isEqualTo(Duration.ofHours(3));
		assertThat(provider.provides()).containsExactlyInAnyOrder(MeasurementKind.CLOUD_COVER, MeasurementKind.DIRECT_RADIATION);
		assertThat(provider.bindingResolver().productId()).isEqualTo("ICON-D2");
		assertThat(SETTINGS.fileUri(RUN_00, CLCT, 3)).isEqualTo(URI.create(
				"https://opendata.dwd.de/weather/nwp/icon-d2/grib/00/clct/icon-d2_germany_regular-lat-lon_single-level_2026100300_003_2d_clct.grib2.bz2"));
		assertThat(SETTINGS.fileUri(RUN_12, ASWDIR_S, 48).getPath()).endsWith("/12/aswdir_s/icon-d2_germany_regular-lat-lon_single-level_2026100312_048_2d_aswdir_s.grib2.bz2");
		assertThat(SETTINGS.candidateRuns(NOW)).containsExactly(RUN_12, RUN_00, Instant.parse("2026-10-02T12:00:00Z"));
		assertThat(Settings.defaults().candidateRuns(Instant.parse("2026-10-03T02:00:00Z"))).containsExactly(RUN_00,
				Instant.parse("2026-10-02T18:00:00Z"), Instant.parse("2026-10-02T12:00:00Z"));
		assertThat(SETTINGS.files(RUN_00)).hasSize(8).first().satisfies(f -> {
			assertThat(f.parameter()).isEqualTo(CLCT);
			assertThat(f.step()).isEqualTo(3);
		});
		assertThatThrownBy(() -> new Settings(BASE, List.of(), 48, IconParameters.all(), "", "")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> new Settings(BASE, List.of(0), 49, IconParameters.all(), "", "")).isInstanceOf(IllegalArgumentException.class);
		assertThat(new Settings(URI.create("http://x/y"), List.of(0), 1, IconParameters.all(), "", "").baseUri()).hasToString("http://x/y/");
	}

	@Test
	void bindingIsTheContainingCell() {
		assertThat(binding.getProviderId()).isEqualTo("dwd");
		assertThat(binding.getProductId()).isEqualTo("ICON-D2");
		assertThat(binding.getCell().getGridId()).isEqualTo("icon-d2-regular-lat-lon");
		assertThat(binding.getCell().getI()).isEqualTo(884);
		assertThat(binding.getCell().getJ()).isEqualTo(394);
		assertThat(binding.getDistanceMeters()).isCloseTo(Geo.distanceMeters(51.0504, 13.7373, 51.06, 13.74), within(1e-6));
		assertThat(binding.getDistanceMeters()).isBetween(1000.0, 1200.0);
		assertThat(binding.getResolvedAt()).isEqualTo(NOW);
		assertThat(provider.bindingResolver().resolve(site("madrid", 40.4, -3.7), 3)).isEmpty();
		assertThat(provider.bindingResolver().resolve(dresden, 0)).isEmpty();
		Optional<SourceBinding> manual = provider.bindingResolver().bind(dresden, "700,500");
		assertThat(manual).hasValueSatisfying(b -> assertThat(((GridBinding) b).getCell().getI()).isEqualTo(700));
		assertThat(provider.bindingResolver().bind(dresden, "nowhere")).isEmpty();
	}

	@Test
	void fallsBackToTheLastCompleteRunAndDeAverages() throws IOException {
		FetchResult result = provider.fetch(request(SourceState.EMPTY));

		// probes of the newest run first: clct +3 is there, aswdir_s +3 is not → run 00, all eight files
		assertThat(requested).hasSize(10);
		assertThat(requested.get(0).getPath()).endsWith("2026100312_003_2d_clct.grib2.bz2");
		assertThat(requested.get(1).getPath()).endsWith("2026100312_003_2d_aswdir_s.grib2.bz2");
		assertThat(requested.subList(2, 10)).allSatisfy(u -> assertThat(u.getPath()).contains("_2026100300_"));

		FetchResult.Fetched fetched = (FetchResult.Fetched) result;
		assertThat(fetched.skipped()).containsEntry("run-incomplete", 1).hasSize(1);
		assertThat(fetched.state().entities()).hasSize(8).allSatisfy((uri, e) -> {
			assertThat(uri.getPath()).contains("_2026100300_");
			assertThat(e.etag()).isPresent();
		});
		assertThat(fetched.datasets()).containsOnlyKeys("dresden");
		SourceDataset ds = fetched.datasets().get("dresden").get(0);
		assertThat(ds.getProviderId()).isEqualTo("dwd");
		assertThat(ds.getProductId()).isEqualTo("ICON-D2");
		assertThat(ds.getOrigin()).isEqualTo(Origin.GRID_CELL);
		assertThat(ds.getIssuedAt()).isEqualTo(RUN_00);
		assertThat(ds.getModelRun()).isEqualTo(RUN_00);
		assertThat(ds.getRetrievedAt()).isEqualTo(NOW);
		assertThat(ds.getExpectedRefresh()).isEqualTo(Duration.ofHours(12));
		assertThat(ds.getHorizonStart()).isEqualTo(RUN_00);
		assertThat(ds.getHorizonEnd()).isEqualTo(RUN_00.plus(Duration.ofHours(3)));
		assertThat(ds.getCell().getI()).isEqualTo(884);
		assertThat(ds.getCell().getJ()).isEqualTo(394);
		assertThat(ds.getStationId()).isNull();
		assertThat(ds.getDistanceMeters()).isEqualTo(binding.getDistanceMeters());
		assertThat(ds.getLicence()).isEqualTo("GeoNutzV");

		List<MeasuredValue> clouds = ds.getValues().stream().filter(v -> v.getKind() == MeasurementKind.CLOUD_COVER).toList();
		assertThat(clouds).hasSize(4);
		assertThat(clouds).extracting(MeasuredValue::getValue).containsExactly(1.0, 11.0, 21.0, 31.0);
		assertThat(clouds).extracting(MeasuredValue::getValidAt).containsExactly(RUN_00, RUN_00.plus(Duration.ofHours(1)),
				RUN_00.plus(Duration.ofHours(2)), RUN_00.plus(Duration.ofHours(3)));
		assertThat(clouds.get(0).getLevel()).isEqualTo(Level.CLOUD_TOTAL);
		assertThat(clouds.get(0).getStatistic()).isEqualTo(Statistic.INSTANT);
		assertThat(clouds.get(0).getUnit()).isEqualTo("%");
		assertThat(clouds.get(0).isSetPeriod()).isFalse();

		List<MeasuredValue> direct = ds.getValues().stream().filter(v -> v.getKind() == MeasurementKind.DIRECT_RADIATION).toList();
		assertThat(direct).hasSize(3); // step 0 covers no interval
		assertThat(direct).extracting(MeasuredValue::getValue).containsExactly(5.0, 15.0, 25.0);
		assertThat(direct).extracting(MeasuredValue::getValidAt).containsExactly(RUN_00.plus(Duration.ofHours(1)),
				RUN_00.plus(Duration.ofHours(2)), RUN_00.plus(Duration.ofHours(3)));
		MeasuredValue last = direct.get(2);
		assertThat(last.getStatistic()).isEqualTo(Statistic.MEAN);
		assertThat(last.getPeriod()).isEqualTo(Duration.ofHours(1));
		assertThat(last.getUnit()).isEqualTo("W/m2");
		assertThat(last.getLevel()).isEqualTo(Level.SURFACE);
		assertThat(last.getProvenance().getSourceElement()).isEqualTo("aswdir_s");
		assertThat(last.getProvenance().getOrigin()).isEqualTo(Origin.GRID_CELL);
		assertThat(last.getProvenance().getCell().getI()).isEqualTo(884);
		assertThat(last.getProvenance().getModelRun()).isEqualTo(RUN_00);
		assertThat(last.getProvenance().getIssuedAt()).isEqualTo(RUN_00);
		assertThat(last.getProvenance().getDistanceMeters()).isEqualTo(binding.getDistanceMeters());
		assertThat(last.getProvenance().getAttribution()).isEqualTo("Datenbasis: Deutscher Wetterdienst");
		assertThat(last.getUncertainty().getQuality()).isEqualTo(Quality.FORECAST);
		assertThat(last.getUncertainty().getLeadTime()).isEqualTo(Duration.ofHours(3));
		assertThat(last.getUncertainty().getSpatialMeters()).isEqualTo(binding.getDistanceMeters());
	}

	@Test
	void aKnownRunIsNotFetchedAgain() throws IOException {
		SourceState state = ((FetchResult.Fetched) provider.fetch(request(SourceState.EMPTY))).state();
		requested.clear();

		assertThat(provider.fetch(request(state))).isInstanceOf(FetchResult.Unchanged.class);
		// only the probe of the still incomplete run 12 went out
		assertThat(requested).hasSize(2);
		assertThat(requested).allSatisfy(u -> assertThat(u.getPath()).contains("_2026100312_003_"));
		requested.clear();

		// a site without data forces the known run again
		FetchResult again = provider.fetch(request(state, "dresden"));
		assertThat(again).isInstanceOf(FetchResult.Fetched.class);
		assertThat(requested).hasSize(10);
		requested.clear();

		// once run 12 is complete it replaces run 00, and the state forgets run 00
		run12Complete = true;
		FetchResult.Fetched newer = (FetchResult.Fetched) provider.fetch(request(state));
		assertThat(requested).hasSize(8);
		assertThat(newer.state().entities()).hasSize(8).allSatisfy((uri, e) -> assertThat(uri.getPath()).contains("_2026100312_"));
		assertThat(newer.datasets().get("dresden").get(0).getModelRun()).isEqualTo(RUN_12);
		assertThat(newer.skipped()).isEmpty();
		requested.clear();
		assertThat(provider.fetch(request(newer.state()))).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).isEmpty();
	}

	@Test
	void roundingNoiseBelowZeroIsZero() {
		java.util.TreeMap<Instant, Double> means = new java.util.TreeMap<>();
		means.put(RUN_00.plus(Duration.ofHours(1)), 2.0000001);
		means.put(RUN_00.plus(Duration.ofHours(2)), 1.0); // 2·1.0 − 1·2.0000001 = −0.0000001
		means.put(RUN_00.plus(Duration.ofHours(3)), 0.0); // 0 − 2·1.0 = −2: a real inconsistency stays visible
		Map<Parameter, java.util.TreeMap<Instant, Double>> hourly = IconProvider.hourly(RUN_00, Map.of(ASWDIR_S, means));
		assertThat(hourly.get(ASWDIR_S).get(RUN_00.plus(Duration.ofHours(1)))).isEqualTo(2.0000001);
		assertThat(Double.compare(hourly.get(ASWDIR_S).get(RUN_00.plus(Duration.ofHours(2))), 0.0)).isZero();
		assertThat(hourly.get(ASWDIR_S).get(RUN_00.plus(Duration.ofHours(3)))).isEqualTo(-2.0);
	}

	@Test
	void aMaskedCellYieldsNoDatasetButTheRunIsRemembered() throws IOException {
		present = k -> k != DRESDEN.flatIndex();
		FetchResult.Fetched fetched = (FetchResult.Fetched) provider.fetch(request(SourceState.EMPTY));
		assertThat(fetched.datasets()).isEmpty();
		assertThat(fetched.skipped()).containsEntry("cell-without-data", 1);
		assertThat(fetched.state().entities()).hasSize(8);
	}

	@Test
	void aFileThatIsNotWhatItsNameSaysIsAContentError() {
		ByteSource liar = (uri, validators) -> new ByteSource.Content(
				new ByteArrayInputStream(Grib2TestFiles.bzip2(Grib2TestFiles.encode(CLCT, RUN_12, 3, 1, null))),
				new SourceState.Entity(Optional.empty(), Optional.empty()));
		IconProvider lied = new IconProvider(SETTINGS, liar, Clock.fixed(NOW, ZoneOffset.UTC));
		assertThatThrownBy(() -> lied.fetch(request(SourceState.EMPTY))).isInstanceOf(FetchException.class)
				.hasMessageContaining("aswdir_s");
	}

	@Test
	void noRunAtAllIsAContentError() {
		ByteSource empty = (uri, validators) -> {
			throw new ByteSource.NotFoundException(uri, 404);
		};
		IconProvider nothing = new IconProvider(SETTINGS, empty, Clock.fixed(NOW, ZoneOffset.UTC));
		assertThatThrownBy(() -> nothing.fetch(request(SourceState.EMPTY))).isInstanceOf(FetchException.class)
				.hasMessageContaining("no complete ICON-D2 run");
	}

	@Test
	void sitesWithoutAGridBindingAreNothingToDo() throws IOException {
		Site other = site("other", 51.0, 13.0);
		SourceBinding foreign = WeatherFactory.eINSTANCE.createStationBinding();
		foreign.setProviderId("dwd");
		foreign.setProductId("MOSMIX_L");
		FetchRequest request = new FetchRequest(List.of(new SiteBindings(other, List.of(foreign))), SourceState.EMPTY, NOW);
		assertThat(provider.fetch(request)).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).isEmpty();
	}
}
