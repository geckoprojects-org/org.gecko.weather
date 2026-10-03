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
import static org.assertj.core.api.Assertions.within;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * The recorded DWD files through netCDF-Java, checked against values an independent NetCDF-3 reader
 * produced for the same cells (QR-6), plus the grid, the listing parser and the synthetic writer.
 */
class SisNetcdfTest {

	/** Dresden, 51.0504° N 13.7373° E → cell (175, 101), centre 51.05° N 13.75° E. */
	static final Cell DRESDEN = new Cell(175, 101);
	static final Cell NORTH_SEA = new Cell(0, 180);
	static final Cell CORNER = new Cell(0, 0);

	@TempDir
	Path tmp;

	@Test
	void grid() {
		assertThat(SisGrid.GRID.cellFor(51.0504, 13.7373)).contains(DRESDEN);
		assertThat(SisGrid.GRID.center(DRESDEN).getLatitude()).isCloseTo(51.05, within(1e-9));
		assertThat(SisGrid.GRID.center(DRESDEN).getLongitude()).isCloseTo(13.75, within(1e-9));
		assertThat(SisGrid.GRID.cellFor(46.0, 5.0)).contains(CORNER);
		assertThat(SisGrid.GRID.cellFor(57.0, 16.0)).contains(new Cell(220, 220));
		assertThat(SisGrid.GRID.cellFor(55.0, 5.0)).contains(NORTH_SEA);
		assertThat(SisGrid.GRID.cellFor(43.0, 10.0)).isEmpty(); // south of the box
		assertThat(SisGrid.GRID.cellFor(50.0, 4.0)).isEmpty(); // west of it
		assertThat(SisGrid.GRID.gridCell(DRESDEN).getGridId()).isEqualTo("sis-de-v3");
		double[] lats = new double[221];
		double[] lons = new double[221];
		for (int k = 0; k < 221; k++) {
			lats[k] = (float) (46.0 + k * 0.05); // as the file stores them
			lons[k] = (float) (5.0 + k * 0.05);
		}
		assertThat(SisGrid.matches(lats, lons)).isTrue();
		lats[0] = 46.5;
		assertThat(SisGrid.matches(lats, lons)).isFalse();
		assertThat(SisGrid.matches(new double[10], new double[221])).isFalse();
	}

	@Test
	void recordedAnalysis() throws IOException {
		SisNetcdf.Field f = SisNetcdf.read(Fixtures.bytes(Fixtures.ANALYSIS_1200), List.of(DRESDEN, NORTH_SEA, CORNER));
		assertThat(f.times()).containsExactly(Instant.parse("2026-10-03T12:00:00Z"));
		assertThat(f.value(DRESDEN, 0)).isEqualTo(539.0);
		assertThat(f.value(NORTH_SEA, 0)).isEqualTo(286.0);
		assertThat(f.value(CORNER, 0)).isEqualTo(274.0);
		assertThat(f.value(new Cell(1, 1), 0)).as("a cell not asked for").isNaN();
	}

	@Test
	void recordedForecast() throws IOException {
		SisNetcdf.Field f = SisNetcdf.read(Fixtures.bytes(Fixtures.FORECAST_18), List.of(DRESDEN, NORTH_SEA));
		assertThat(f.times()).hasSize(18);
		assertThat(f.times().get(0)).isEqualTo(Instant.parse("2026-10-03T18:00:00Z"));
		assertThat(f.times().get(17)).isEqualTo(Instant.parse("2026-10-04T11:00:00Z"));
		assertThat(f.value(DRESDEN, 0)).isEqualTo(0.0);
		assertThat(f.value(DRESDEN, 12)).isCloseTo(12.563965, within(1e-5)); // 06 UTC next day
		assertThat(f.value(DRESDEN, 17)).isCloseTo(313.156494, within(1e-5)); // 11 UTC
		assertThat(f.value(NORTH_SEA, 13)).isCloseTo(42.534668, within(1e-5));
	}

	@Test
	void timeUnits() {
		SisNetcdf.Udunits seconds = SisNetcdf.Udunits.parse("seconds since 2026-10-03 00:00:00");
		assertThat(seconds.instant(43200)).isEqualTo(Instant.parse("2026-10-03T12:00:00Z"));
		SisNetcdf.Udunits hours = SisNetcdf.Udunits.parse("hours since 2026-10-3 00:00:00");
		assertThat(hours.instant(35)).isEqualTo(Instant.parse("2026-10-04T11:00:00Z"));
		assertThat(SisNetcdf.Udunits.parse("days since 1970-01-01").instant(1.5)).isEqualTo(Instant.parse("1970-01-02T12:00:00Z"));
		assertThat(SisNetcdf.Udunits.parse("minutes since 2026-1-1T06:30:00Z").instant(90)).isEqualTo(Instant.parse("2026-01-01T08:00:00Z"));
		assertThatThrownBy(() -> SisNetcdf.Udunits.parse("fortnights since 2026")).isInstanceOf(FetchException.class);
	}

	@Test
	void syntheticFilesReadBackAndMissingIsNaN() throws IOException {
		Instant t = Instant.parse("2026-10-03T12:15:00Z");
		byte[] analysis = SisTestFiles.analysis(tmp, t, (i, j) -> i == 175 && j == 101 ? -1 : i + j);
		SisNetcdf.Field a = SisNetcdf.read(analysis, List.of(DRESDEN, NORTH_SEA));
		assertThat(a.times()).containsExactly(t);
		assertThat(a.value(DRESDEN, 0)).isNaN();
		assertThat(a.value(NORTH_SEA, 0)).isEqualTo(180.0);

		Instant run = Instant.parse("2026-10-03T18:00:00Z");
		byte[] forecast = SisTestFiles.forecast(tmp, run, 18, (step, flat) -> 10 * step);
		SisNetcdf.Field f = SisNetcdf.read(forecast, Set.of(DRESDEN));
		assertThat(f.times()).hasSize(18).first().isEqualTo(run);
		assertThat(f.times().get(5)).isEqualTo(run.plus(Duration.ofHours(5)));
		assertThat(f.value(DRESDEN, 5)).isEqualTo(50.0);

		assertThatThrownBy(() -> SisNetcdf.read(new byte[] { 1, 2, 3 }, Set.of(DRESDEN))).isInstanceOf(IOException.class);
	}

	@Test
	void listing() {
		String html = """
				<html><head><title>Index of /weather/satellite/radiation/sis/</title></head><body>
				<a href="../">../</a>
				<a href="CALhr202610031810EUv4.nc">CALhr202610031810EUv4.nc</a>  03-Oct-2026 18:21  16M
				<a href="SISfc2026100317_fc%2B18h-DE.nc">SISfc2026100317_fc+18h-DE.nc</a>  03-Oct-2026 17:10  3.4M
				<a href="SISfc2026100318_fc%2B18h-DE.nc">SISfc2026100318_fc+18h-DE.nc</a>  03-Oct-2026 18:10  3.4M
				<a href="SISin202610031800DEv3.nc">SISin202610031800DEv3.nc</a>  03-Oct-2026 18:18  98K
				<a href="SISin202610031800DEv3.nc.bz2">SISin202610031800DEv3.nc.bz2</a>  03-Oct-2026 18:18  2K
				<a href="SISin202610031745DEv3.nc">SISin202610031745DEv3.nc</a>  03-Oct-2026 18:03  98K
				<a href="SISin202610031800EAv4.nc">SISin202610031800EAv4.nc</a>  03-Oct-2026 18:19  50M
				</body></html>
				""";
		assertThat(SisListing.names(html)).hasSize(6).doesNotContain("SISin202610031800DEv3.nc.bz2");
		assertThat(SisListing.analyses(html)).containsExactly(
				java.util.Map.entry(Instant.parse("2026-10-03T17:45:00Z"), "SISin202610031745DEv3.nc"),
				java.util.Map.entry(Instant.parse("2026-10-03T18:00:00Z"), "SISin202610031800DEv3.nc"));
		assertThat(SisListing.forecasts(html).lastKey()).isEqualTo(Instant.parse("2026-10-03T18:00:00Z"));
		assertThat(SisListing.forecasts(html).get(Instant.parse("2026-10-03T18:00:00Z"))).isEqualTo("SISfc2026100318_fc%2B18h-DE.nc");
		URI base = URI.create("https://opendata.dwd.de/weather/satellite/radiation/sis/");
		assertThat(SisListing.uri(base, "SISfc2026100318_fc%2B18h-DE.nc")).hasToString(
				"https://opendata.dwd.de/weather/satellite/radiation/sis/SISfc2026100318_fc%2B18h-DE.nc");
		assertThat(SisListing.uri(base, "SISfc2026100318_fc%2B18h-DE.nc").getPath()).endsWith("SISfc2026100318_fc+18h-DE.nc");
	}
}
