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

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.junit.jupiter.api.Test;

/**
 * The recorded DWD files through netCDF-Java, checked against values an independent, hand-written
 * GRIB2 section parser produced for the same cells (QR-6: a known expected value per path).
 */
class Grib2ReaderTest {

	static final Instant RUN = Instant.parse("2026-10-03T00:00:00Z");
	/** Dresden, 51.0504° N 13.7373° E → cell (884, 394), centre 51.06° N 13.74° E. */
	static final Cell DRESDEN = new Cell(884, 394);

	@Test
	void instantaneousCloudCover() throws IOException {
		Grib2Field f = Grib2Reader.read(Fixtures.grib2(Fixtures.CLCT_003));
		assertThat(f.discipline()).isZero();
		assertThat(f.category()).isEqualTo(6);
		assertThat(f.number()).isEqualTo(1);
		assertThat(f.pdsTemplate()).isZero();
		assertThat(f.levelType1()).isEqualTo(IconParameters.SURFACE);
		assertThat(f.referenceTime()).isEqualTo(RUN);
		assertThat(f.validAt()).isEqualTo(RUN.plus(Duration.ofHours(3)));
		assertThat(f.leadTime()).isEqualTo(Duration.ofHours(3));
		assertThat(f.interval()).isEmpty();
		assertThat(IconD2Grid.matches(f.grid())).as("grid %s", f.grid()).isTrue();
		assertThat(f.grid().lo1()).isCloseTo(-3.94, within(1e-4));

		assertThat(f.valueAt(DRESDEN.i(), DRESDEN.j())).isCloseTo(99.103516f, within(1e-3f));
		assertThat(f.valueAt(697, 518)).isCloseTo(100.0f, within(1e-3f)); // Hamburg
		assertThat(f.valueAt(447, 591)).isCloseTo(2.964844f, within(1e-3f)); // North Sea, 55° N 5° E — on the domain
		assertThat(f.valueAt(47, 341)).isNaN(); // 50° N 3° W — bitmap: outside the model domain
		assertThat(f.valueAt(0, 0)).isNaN();
		assertThat(f.valueAt(IconD2Grid.NI - 1, IconD2Grid.NJ - 1)).isNaN();
		IconParameters.check(IconParameters.byName("clct").orElseThrow(), f);
	}

	@Test
	void averagedRadiationCarriesItsIntervalAndComesInQuarterHours() throws IOException {
		byte[] message = Fixtures.grib2(Fixtures.ASWDIR_S_003);
		// four records per step: the averages since the run ending at :00, :15, :30 and :45
		List<Grib2Field> all = Grib2Reader.readAll(message);
		assertThat(all).hasSize(4).allSatisfy(Grib2Field::hasData);
		assertThat(all).extracting(Grib2Field::validAt).containsExactly(RUN.plus(Duration.ofHours(3)),
				RUN.plus(Duration.ofMinutes(195)), RUN.plus(Duration.ofMinutes(210)), RUN.plus(Duration.ofMinutes(225)));
		assertThatThrownBy(() -> Grib2Reader.read(message)).isInstanceOf(FetchException.class).hasMessageContaining("found 4");
		assertThatThrownBy(() -> Grib2Reader.read(message, RUN.plus(Duration.ofHours(4)))).isInstanceOf(FetchException.class)
				.hasMessageContaining("03:45");

		Grib2Field f = Grib2Reader.read(message, RUN.plus(Duration.ofHours(3)));
		assertThat(f.hasData()).isTrue();
		assertThat(f.category()).isEqualTo(4);
		assertThat(f.number()).isEqualTo(198);
		assertThat(f.pdsTemplate()).isEqualTo(8);
		assertThat(f.referenceTime()).isEqualTo(RUN);
		assertThat(f.validAt()).isEqualTo(RUN.plus(Duration.ofHours(3)));
		assertThat(f.interval()).hasValueSatisfying(i -> {
			assertThat(i.start()).isEqualTo(RUN);
			assertThat(i.end()).isEqualTo(RUN.plus(Duration.ofHours(3)));
			assertThat(i.statisticalProcess()).isZero();
		});
		assertThat(f.valueAt(DRESDEN.i(), DRESDEN.j())).isZero(); // night
		IconParameters.check(IconParameters.byName("aswdir_s").orElseThrow(), f);
		// the same file is not the diffuse one
		assertThatThrownBy(() -> IconParameters.check(IconParameters.byName("aswdifd_s").orElseThrow(), f))
				.isInstanceOf(FetchException.class).hasMessageContaining("4/198");
	}

	@Test
	void syntheticFilesReadBackWhatWasWritten() throws IOException {
		IconParameters.Parameter clcm = IconParameters.byName("clcm").orElseThrow();
		Grib2Field f = Grib2Reader.read(Grib2TestFiles.encode(clcm, RUN, 7, 42, k -> k != IconD2Grid.GRID.flatIndex(DRESDEN)));
		assertThat(IconD2Grid.matches(f.grid())).isTrue();
		assertThat(f.validAt()).isEqualTo(RUN.plus(Duration.ofHours(7)));
		assertThat(f.levelType1()).isEqualTo(IconParameters.ISOBARIC);
		assertThat(f.levelValue1()).isEqualTo(40_000);
		assertThat(f.levelValue2()).isEqualTo(80_000);
		assertThat(f.valueAt(0, 0)).isEqualTo(42f);
		assertThat(f.valueAt(DRESDEN.i(), DRESDEN.j())).isNaN();
		IconParameters.check(clcm, f);

		IconParameters.Parameter aswdifd = IconParameters.byName("aswdifd_s").orElseThrow();
		Grib2Field g = Grib2Reader.read(Grib2TestFiles.encode(aswdifd, RUN, 11, 66, null));
		assertThat(g.interval()).hasValueSatisfying(i -> assertThat(i.end()).isEqualTo(RUN.plus(Duration.ofHours(11))));
		assertThat(g.validAt()).isEqualTo(RUN.plus(Duration.ofHours(11)));
		assertThat(g.valueAt(DRESDEN.i(), DRESDEN.j())).isEqualTo(66f);
		IconParameters.check(aswdifd, g);
	}

	@Test
	void garbageIsAContentError() {
		assertThatThrownBy(() -> Grib2Reader.read(new byte[] { 1, 2, 3 })).isInstanceOf(FetchException.class);
		assertThat(Grib2Reader.duration(0, 180)).isEqualTo(Duration.ofHours(3));
		assertThat(Grib2Reader.duration(1, 2)).isEqualTo(Duration.ofHours(2));
		assertThat(Grib2Reader.duration(10, 2)).isEqualTo(Duration.ofHours(6));
		assertThatThrownBy(() -> Grib2Reader.duration(7, 1)).isInstanceOf(FetchException.class);
		assertThat(Grib2Reader.normalizeLongitude(356.06)).isCloseTo(-3.94, within(1e-9));
		assertThat(Grib2Reader.normalizeLongitude(20.34)).isCloseTo(20.34, within(1e-9));
	}
}
