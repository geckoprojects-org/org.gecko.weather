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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
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

import org.gecko.weather.api.Geo;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.grib2.Grib2Field;
import org.gecko.weather.grib2.Grib2Reader;
import org.gecko.weather.grib2.Grib2Writer;
import org.gecko.weather.grib2.Grib2Writer.Spec;
import org.gecko.weather.grib2.Grib2Writer.Statistical;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.transport.ByteSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The UV provider against a fixture listing and synthetic three-record files written through the
 * shared {@link Grib2Writer} the way DWD writes them (template 8, maximum over a day, time unit days,
 * level 103). Day {@code d} of run {@code r} is valued {@code 10·d + (day of month of r)}.
 */
class UvProviderTest {

	private static final URI BASE = URI.create("https://opendata.dwd.de/climate_environment/health/forecasts/");
	private static final Instant NOW = Instant.parse("2026-10-03T14:30:00Z");
	private static final Instant RUN_02 = Instant.parse("2026-10-02T00:00:00Z");
	private static final Instant RUN_03 = Instant.parse("2026-10-03T00:00:00Z");
	private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneOffset.UTC);
	private static final DateTimeFormatter RUN = DateTimeFormatter.ofPattern("yyMMddHH").withZone(ZoneOffset.UTC);
	/** Dresden on ICON-EU: round((13.7373 + 23.5) / 0.0625) = 596, round((51.0504 − 29.5) / 0.0625) = 345. */
	private static final Cell DRESDEN = new Cell(596, 345);

	private final TreeMap<Instant, String> listed = new TreeMap<>();
	private final Map<String, byte[]> files = new HashMap<>();
	private final List<URI> requested = new ArrayList<>();
	private UvProvider provider;
	private Site dresden;

	@BeforeEach
	void setUp() {
		addRun(RUN_02, RUN_02.plus(Duration.ofHours(4).plusMinutes(41).plusSeconds(32)));
		addRun(RUN_03, RUN_03.plus(Duration.ofHours(4).plusMinutes(28).plusSeconds(42)));
		ByteSource source = (uri, validators) -> {
			requested.add(uri);
			if (uri.equals(BASE)) {
				StringBuilder html = new StringBuilder("<html><body>\n<a href=\"../\">../</a>\n<a href=\"heat/\">heat/</a>\n");
				listed.values().forEach(n -> html.append("<a href=\"").append(n).append("\">").append(n.replace("%2C", ",")).append("</a>\n"));
				html.append("<a href=\"Z__C_EDZW_20261003042842_grb02%2Cicreu_uvh_icreu__000048_999999_2610030000_HPC.bin\">uvh</a>\n");
				html.append("<a href=\"Z__C_EDZW_20261003042842_grb02%2Cgmi_uvi_global__000048_999999_2610030000_HPC.bin\">global</a>\n");
				return new ByteSource.Content(new ByteArrayInputStream(html.append("</body></html>").toString().getBytes(StandardCharsets.UTF_8)),
						new SourceState.Entity(Optional.empty(), Optional.empty()));
			}
			byte[] bytes = files.get(uri.toString().substring(BASE.toString().length()));
			if (bytes == null) {
				throw new ByteSource.NotFoundException(uri, 404);
			}
			return new ByteSource.Content(new ByteArrayInputStream(bytes), new SourceState.Entity(Optional.of("\"" + bytes.length + "\""), Optional.empty()));
		};
		provider = new UvProvider(UvProvider.Settings.defaults(), source, Clock.fixed(NOW, ZoneOffset.UTC));
		dresden = site("dresden", 51.0504, 13.7373);
	}

	private void addRun(Instant run, Instant processed) {
		String name = "Z__C_EDZW_" + STAMP.format(processed) + "_grb02%2Cicreu_uvi_icreu__000048_999999_" + RUN.format(run) + "00_HPC.bin";
		listed.put(run, name);
		files.put(name, file(run, 3, (day, flat) -> 10 * day + run.atOffset(ZoneOffset.UTC).getDayOfMonth()));
	}

	/**
	 * Three daily maxima as DWD writes them: template 8, process 2 (maximum), time unit days, forecast
	 * time {@code d} and — DWD's quirk — the interval end equal to the start of day {@code d}, range 0.
	 */
	static byte[] file(Instant run, int days, java.util.function.IntBinaryOperator value) {
		byte[][] messages = new byte[days][];
		for (int d = 0; d < days; d++) {
			Spec spec = new Spec(IconEuGrid.GRID, IconEuGrid.SCAN_MODE, run, 0, UvProvider.CATEGORY, UvProvider.NUMBER,
					UvProvider.HEIGHT_ABOVE_GROUND, 0, Grib2Writer.NO_LEVEL, 0, 2, d,
					Optional.of(new Statistical(run.plus(Duration.ofDays(d)), UvProvider.MAXIMUM, 1, 0)));
			int day = d;
			messages[d] = Grib2Writer.write(spec, flat -> value.applyAsInt(day, flat));
		}
		return Grib2Writer.concat(messages);
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
		return new FetchRequest(List.of(new SiteBindings(dresden, List.of(provider.bindingResolver().resolve(dresden, 3).get(0)))),
				state, NOW, Set.of(unconditional));
	}

	@Test
	void gridAndListing() {
		assertThat(IconEuGrid.GRID.cellFor(51.0504, 13.7373)).contains(DRESDEN);
		assertThat(IconEuGrid.GRID.center(DRESDEN).getLatitude()).isCloseTo(51.0625, within(1e-9));
		assertThat(IconEuGrid.GRID.center(DRESDEN).getLongitude()).isCloseTo(13.75, within(1e-9));
		assertThat(IconEuGrid.GRID.cellFor(29.5, -23.5)).contains(new Cell(0, 0));
		assertThat(IconEuGrid.GRID.cellFor(70.5, 62.5)).contains(new Cell(1376, 656));
		assertThat(IconEuGrid.GRID.cellFor(38.7, -9.1)).contains(new Cell(230, 147)); // Lisbon
		assertThat(IconEuGrid.GRID.cellFor(20.0, 0.0)).isEmpty();
		assertThat(IconEuGrid.matches(IconEuGrid.DEFINITION)).isTrue();

		String html = """
				<a href="Z__C_EDZW_20261002044132_grb02%2Cicreu_uvi_icreu__000048_999999_2610020000_HPC.bin">x</a>
				<a href="Z__C_EDZW_20261003042842_grb02%2Cicreu_uvh_icreu__000048_999999_2610030000_HPC.bin">x</a>
				<a href="Z__C_EDZW_20261003042842_grb02%2Cicreu_uvi_icreu__000048_999999_2610030000_HPC.bin">x</a>
				<a href="Z__C_EDZW_20261003052000_grb02%2Cicreu_uvi_icreu__000048_999999_2610030000_HPC.bin">rerun</a>
				<a href="Z__C_EDZW_20261003042842_grb02%2Cgmi_uvi_global__000048_999999_2610030000_HPC.bin">x</a>
				""";
		var issues = UvProvider.issues(html);
		assertThat(issues).hasSize(2);
		UvProvider.Issue newest = issues.get(issues.lastKey());
		assertThat(newest.run()).isEqualTo(RUN_03);
		assertThat(newest.processedAt()).isEqualTo(Instant.parse("2026-10-03T05:20:00Z"));
		assertThat(newest.name()).startsWith("Z__C_EDZW_20261003052000");
		assertThat(BASE.resolve(newest.name()).getPath()).endsWith("grb02,icreu_uvi_icreu__000048_999999_2610030000_HPC.bin");
	}

	@Test
	void syntheticFileIsWhatDwdWrites() throws IOException {
		List<Grib2Field> fields = Grib2Reader.readAll(file(RUN_03, 3, (d, flat) -> 10 * d + 3));
		assertThat(fields).hasSize(3);
		Grib2Field first = fields.get(0);
		assertThat(IconEuGrid.matches(first.grid())).isTrue();
		assertThat(first.category()).isEqualTo(4);
		assertThat(first.number()).isEqualTo(51);
		assertThat(first.pdsTemplate()).isEqualTo(8);
		assertThat(first.levelType1()).isEqualTo(103);
		assertThat(first.referenceTime()).isEqualTo(RUN_03);
		assertThat(first.validAt()).as("the file's interval end is the start of the day").isEqualTo(RUN_03);
		assertThat(first.interval()).hasValueSatisfying(i -> assertThat(i.statisticalProcess()).isEqualTo(2));
		assertThat(UvProvider.dayEnd(first)).isEqualTo(RUN_03.plus(Duration.ofDays(1)));
		assertThat(fields.get(2).validAt()).isEqualTo(RUN_03.plus(Duration.ofDays(2)));
		assertThat(UvProvider.dayEnd(fields.get(2))).isEqualTo(RUN_03.plus(Duration.ofDays(3)));
		assertThat(fields.get(2).valueAt(DRESDEN.i(), DRESDEN.j())).isEqualTo(23f);
	}

	@Test
	void newestRunReplacesAndIsRemembered() throws IOException {
		assertThat(provider.productId()).isEqualTo("UVI");
		assertThat(provider.origin()).isEqualTo(Origin.GRID_CELL);
		assertThat(provider.expectedRefresh()).isEqualTo(Duration.ofDays(1));
		assertThat(provider.provides()).containsExactly(MeasurementKind.UV_INDEX);
		GridBinding binding = (GridBinding) provider.bindingResolver().resolve(dresden, 3).get(0);
		assertThat(binding.getCell().getGridId()).isEqualTo("icon-eu-regular-lat-lon");
		assertThat(binding.getCell().getI()).isEqualTo(596);
		assertThat(binding.getCell().getJ()).isEqualTo(345);
		assertThat(binding.getDistanceMeters()).isCloseTo(Geo.distanceMeters(51.0504, 13.7373, 51.0625, 13.75), within(1e-6));

		FetchResult.Fetched fetched = (FetchResult.Fetched) provider.fetch(request(SourceState.EMPTY, "dresden"));
		assertThat(requested).hasSize(2);
		assertThat(requested.get(1).getPath()).contains("20261003042842").endsWith("_2610030000_HPC.bin");
		SourceDataset ds = fetched.datasets().get("dresden").get(0);
		assertThat(ds.getIssuedAt()).isEqualTo(Instant.parse("2026-10-03T04:28:42Z"));
		assertThat(ds.getModelRun()).isEqualTo(RUN_03);
		assertThat(ds.getExpectedRefresh()).isEqualTo(Duration.ofDays(1));
		assertThat(ds.getHorizonStart()).isEqualTo(RUN_03);
		assertThat(ds.getHorizonEnd()).isEqualTo(RUN_03.plus(Duration.ofDays(3)));
		assertThat(ds.getCell().getI()).isEqualTo(596);
		assertThat(ds.getValues()).hasSize(3);
		assertThat(ds.getValues()).extracting(MeasuredValue::getValue).containsExactly(3.0, 13.0, 23.0);
		assertThat(ds.getValues()).extracting(MeasuredValue::getValidAt).containsExactly(RUN_03.plus(Duration.ofDays(1)),
				RUN_03.plus(Duration.ofDays(2)), RUN_03.plus(Duration.ofDays(3)));
		MeasuredValue today = ds.getValues().get(0);
		assertThat(today.getKind()).isEqualTo(MeasurementKind.UV_INDEX);
		assertThat(today.getStatistic()).isEqualTo(Statistic.MAX);
		assertThat(today.getPeriod()).isEqualTo(Duration.ofDays(1));
		assertThat(today.getUnit()).isEqualTo("1");
		assertThat(today.getProvenance().getSourceElement()).isEqualTo("UVI_MAX_CL");
		assertThat(today.getProvenance().getModelRun()).isEqualTo(RUN_03);
		assertThat(today.getProvenance().getIssuedAt()).isEqualTo(Instant.parse("2026-10-03T04:28:42Z"));
		assertThat(today.getUncertainty().getQuality()).isEqualTo(Quality.FORECAST);
		assertThat(today.getUncertainty().getLeadTime()).isEqualTo(Duration.ofDays(1));
		assertThat(fetched.state().entities()).hasSize(1);
		requested.clear();

		assertThat(provider.fetch(request(fetched.state()))).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).containsExactly(BASE);
		requested.clear();

		Instant run04 = Instant.parse("2026-10-04T00:00:00Z");
		addRun(run04, run04.plus(Duration.ofHours(4).plusMinutes(30)));
		FetchResult.Fetched newer = (FetchResult.Fetched) provider.fetch(request(fetched.state()));
		assertThat(newer.datasets().get("dresden").get(0).getModelRun()).isEqualTo(run04);
		assertThat(newer.datasets().get("dresden").get(0).getValues()).extracting(MeasuredValue::getValue).containsExactly(4.0, 14.0, 24.0);
		assertThat(newer.state().entities().keySet()).allSatisfy(u -> assertThat(u.getPath()).contains("_2610040000_"));
	}

	@Test
	void contentErrors() {
		files.put(listed.get(RUN_03), file(RUN_02, 3, (d, flat) -> 1)); // a file of another run under the name
		assertThatThrownBy(() -> provider.fetch(request(SourceState.EMPTY))).isInstanceOf(FetchException.class)
				.hasMessageContaining("its name says");
		listed.clear();
		assertThatThrownBy(() -> provider.fetch(request(SourceState.EMPTY))).isInstanceOf(FetchException.class)
				.hasMessageContaining("no UV index file listed");
	}

	@Test
	void sitesWithoutAGridBindingAreNothingToDo() throws IOException {
		assertThat(provider.fetch(new FetchRequest(List.of(new SiteBindings(dresden, List.of(WeatherFactory.eINSTANCE.createStationBinding()))),
				SourceState.EMPTY, NOW))).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).isEmpty();
	}
}
