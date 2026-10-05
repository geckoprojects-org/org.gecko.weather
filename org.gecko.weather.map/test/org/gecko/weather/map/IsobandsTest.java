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
package org.gecko.weather.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.gecko.weather.api.spi.RegularLatLonGrid;
import org.gecko.weather.grib2.Grib2Field;
import org.gecko.weather.grib2.Grib2Reader;
import org.gecko.weather.map.Isobands.Band;
import org.gecko.weather.map.Isobands.Polygon;
import org.gecko.weather.map.Isobands.Settings;
import org.gecko.weather.transport.Unwrap;
import org.junit.jupiter.api.Test;

class IsobandsTest {

	/** 1° cells from 0/0, so corners are whole degrees minus a half. */
	private static RegularLatLonGrid grid(int ni, int nj) {
		return new RegularLatLonGrid("test", 0, 0, 1, ni, nj);
	}

	private static final Settings TWO_BANDS = new Settings(new double[] { 0, 50, 100 }, 1, 0, 0);

	/** Rows given north first, as one reads them. */
	private static float[] field(String... rows) {
		int nj = rows.length;
		int ni = rows[0].length();
		float[] data = new float[ni * nj];
		for (int r = 0; r < nj; r++) {
			int j = nj - 1 - r;
			for (int i = 0; i < ni; i++) {
				char c = rows[r].charAt(i);
				data[j * ni + i] = c == '.' ? Float.NaN : c == '#' ? 90 : 10;
			}
		}
		return data;
	}

	@Test
	void aBlockIsOneCounterClockwiseSquare() {
		List<Band> bands = Isobands.of(grid(4, 4), field(
				"    ",
				" ## ",
				" ## ",
				"    "), TWO_BANDS);
		Band high = bands.get(1);
		assertThat(high.lower()).isEqualTo(50);
		assertThat(high.polygons()).hasSize(1);
		double[][] outer = high.polygons().get(0).outer();
		assertThat(outer).hasNumberOfRows(5);
		assertThat(outer[0]).isEqualTo(outer[4]);
		assertThat(Isobands.signedArea(outer)).isEqualTo(4.0);
		assertThat(Arrays.asList(outer)).contains(new double[] { 0.5, 0.5 }, new double[] { 2.5, 2.5 });
	}

	@Test
	void theLowBandAroundItHasTheBlockAsHole() {
		Band low = Isobands.of(grid(4, 4), field(
				"    ",
				" ## ",
				" ## ",
				"    "), TWO_BANDS).get(0);
		assertThat(low.polygons()).hasSize(1);
		Polygon p = low.polygons().get(0);
		assertThat(Isobands.signedArea(p.outer())).isEqualTo(16.0);
		assertThat(p.holes()).hasSize(1);
		assertThat(Isobands.signedArea(p.holes().get(0))).isEqualTo(-4.0);
	}

	@Test
	void diagonalNeighboursAreTwoPolygons() {
		Band high = Isobands.of(grid(2, 2), field(
				" #",
				"# "), TWO_BANDS).get(1);
		assertThat(high.polygons()).hasSize(2);
		high.polygons().forEach(p -> assertThat(Isobands.signedArea(p.outer())).isEqualTo(1.0));
	}

	@Test
	void aRingTouchingItselfAtACornerIsTraced() {
		// the high cells enclose (1,1) but for the corner at its top right, where (1,2) and (2,1) touch diagonally
		Band high = Isobands.of(grid(3, 3), field(
				"## ",
				"# #",
				"###"), TWO_BANDS).get(1);
		double cells = high.polygons().stream()
				.mapToDouble(p -> Isobands.signedArea(p.outer()) + p.holes().stream().mapToDouble(Isobands::signedArea).sum())
				.sum();
		assertThat(cells).isEqualTo(7.0);
	}

	@Test
	void maskedCellsBelongToNoBand() {
		List<Band> bands = Isobands.of(grid(3, 1), field("..#"), TWO_BANDS);
		assertThat(bands.get(0).polygons()).isEmpty();
		assertThat(bands.get(1).polygons()).hasSize(1);
	}

	@Test
	void coarseningAveragesBlocksAndMasksMostlyEmptyOnes() {
		float[] data = { 10, 30, Float.NaN, Float.NaN, //
				20, 40, Float.NaN, 80 };
		Isobands.Coarse c = Isobands.Coarse.of(new RegularLatLonGrid("t", 50, 10, 0.02, 4, 2), data, 2);
		assertThat(c.ni()).isEqualTo(2);
		assertThat(c.nj()).isEqualTo(1);
		assertThat(c.values()[0]).isEqualTo(25f);
		assertThat(c.values()[1]).isNaN();
		assertThat(c.lo1()).isCloseTo(10.01, within(1e-9));
		assertThat(c.resolution()).isCloseTo(0.04, within(1e-12));
	}

	@Test
	void thinningKeepsTheCornersOfAStaircaseApart() {
		double[][] stairs = { { 0, 0 }, { 4, 0 }, { 4, 1 }, { 3, 1 }, { 3, 2 }, { 2, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 }, { 0, 4 },
				{ 0, 0 } };
		double[][] thin = Isobands.simplify(stairs, 0.75);
		assertThat(thin.length).isLessThan(stairs.length);
		assertThat(thin[0]).isEqualTo(thin[thin.length - 1]);
	}

	/** The recorded field: total cloud cover, run 2026-10-03 00 UTC, +3 h. */
	@Test
	void theRecordedCloudFieldMakesFourBandsOfModestSize() throws IOException {
		Path file = Path.of("..", "org.gecko.weather.provider.dwd.icon", "test", "fixtures",
				"icon-d2_germany_regular-lat-lon_single-level_2026100300_003_2d_clct.grib2.bz2");
		Grib2Field field;
		try (InputStream in = Unwrap.bzip2(Files.newInputStream(file))) {
			field = Grib2Reader.read(in.readAllBytes());
		}
		RegularLatLonGrid grid = new RegularLatLonGrid("icon-d2-regular-lat-lon", 43.18, -3.94, 0.02, 1215, 746);
		long t0 = System.nanoTime();
		List<Band> bands = Isobands.of(grid, field.data(), Settings.cloudCover());
		long ms = (System.nanoTime() - t0) / 1_000_000;
		assertThat(bands).hasSize(4);
		int points = bands.stream().mapToInt(Band::pointCount).sum();
		System.out.printf("clct +3h: %d ms, %s polygons, %d points (%s)%n", ms,
				Arrays.toString(bands.stream().mapToInt(b -> b.polygons().size()).toArray()), points,
				Arrays.toString(bands.stream().mapToInt(Band::pointCount).toArray()));
		assertThat(bands.get(3).polygons()).isNotEmpty();
		assertThat(points).isLessThan(200_000);
		assertThat(field.validAt()).isEqualTo(Instant.parse("2026-10-03T03:00:00Z"));
	}
}
