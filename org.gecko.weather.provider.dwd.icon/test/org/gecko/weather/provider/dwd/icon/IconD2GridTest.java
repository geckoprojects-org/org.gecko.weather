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

import java.time.Duration;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;
import org.junit.jupiter.api.Test;

class IconD2GridTest {

	@Test
	void cellsByIndexArithmetic() {
		assertThat(IconD2Grid.GRID.cellFor(51.0504, 13.7373)).contains(new Cell(884, 394));
		assertThat(IconD2Grid.GRID.cellFor(43.18, -3.94)).contains(new Cell(0, 0));
		assertThat(IconD2Grid.GRID.cellFor(58.08, 20.34)).contains(new Cell(1214, 745));
		assertThat(IconD2Grid.GRID.cellFor(53.55, 9.99)).contains(new Cell(697, 518));
		assertThat(IconD2Grid.GRID.cellFor(40.0, 10.0)).isEmpty();
		assertThat(IconD2Grid.GRID.cellFor(50.0, 25.0)).isEmpty();
		assertThat(IconD2Grid.GRID.cellFor(50.0, -3.0)).contains(new Cell(47, 341)); // on the rectangle, masked in the files
		assertThat(IconD2Grid.GRID.cellFor(51.0, 356.06 + 17.68 + 0.001)).contains(new Cell(884, 391)); // longitudes east of 180 wrap
		assertThat(IconD2Grid.GRID.flatIndex(new Cell(884, 394))).isEqualTo(479594);
	}

	@Test
	void centreAndModelObject() {
		GeoPosition c = IconD2Grid.GRID.center(new Cell(884, 394));
		assertThat(c.getLatitude()).isCloseTo(51.06, within(1e-9));
		assertThat(c.getLongitude()).isCloseTo(13.74, within(1e-9));
		GridCell cell = IconD2Grid.GRID.gridCell(new Cell(884, 394));
		assertThat(cell.getGridId()).isEqualTo("icon-d2-regular-lat-lon");
		assertThat(cell.getI()).isEqualTo(884);
		assertThat(cell.getJ()).isEqualTo(394);
		assertThat(cell.getResolutionDegrees()).isEqualTo(0.02);
		assertThat(IconD2Grid.GRID.cellOf(cell)).contains(new Cell(884, 394));
		cell.setGridId("sis-de-v3");
		assertThat(IconD2Grid.GRID.cellOf(cell)).isEmpty();
	}

	@Test
	void parseAndBounds() {
		assertThat(IconD2Grid.GRID.parse("884,394")).contains(new Cell(884, 394));
		assertThat(IconD2Grid.GRID.parse(" 884 , 394 ")).contains(new Cell(884, 394));
		assertThat(IconD2Grid.GRID.parse("1215,0")).isEmpty();
		assertThat(IconD2Grid.GRID.parse("x")).isEmpty();
		assertThat(IconD2Grid.GRID.parse("1,2,3")).isEmpty();
		assertThatThrownBy(() -> IconD2Grid.GRID.flatIndex(new Cell(-1, 0))).isInstanceOf(IllegalArgumentException.class);
		assertThat(IconD2Grid.GRID.contains(1214, 745)).isTrue();
		assertThat(IconD2Grid.GRID.contains(1215, 745)).isFalse();
		assertThat(IconD2Grid.matches(IconD2Grid.DEFINITION)).isTrue();
	}

	@Test
	void parametersAndDeAveraging() {
		assertThat(IconParameters.all()).hasSize(6);
		assertThat(IconParameters.parse("clct, aswdir_s")).extracting(IconParameters.Parameter::name).containsExactly("clct", "aswdir_s");
		assertThatThrownBy(() -> IconParameters.parse("clct,tmax")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("tmax");
		assertThatThrownBy(() -> IconParameters.parse(" , ")).isInstanceOf(IllegalArgumentException.class);
		assertThat(IconParameters.kinds(IconParameters.all())).containsExactlyInAnyOrder(MeasurementKind.CLOUD_COVER,
				MeasurementKind.DIRECT_RADIATION, MeasurementKind.DIFFUSE_RADIATION);
		IconParameters.Parameter clch = IconParameters.byName("clch").orElseThrow();
		assertThat(clch.kind()).isEqualTo(MeasurementKind.CLOUD_COVER);
		assertThat(clch.level()).isEqualTo(Level.CLOUD_HIGH);
		assertThat(clch.statistic()).isEqualTo(Statistic.INSTANT);
		assertThat(clch.unit()).isEqualTo("%");
		IconParameters.Parameter dir = IconParameters.byName("aswdir_s").orElseThrow();
		assertThat(dir.statistic()).isEqualTo(Statistic.MEAN);
		assertThat(dir.period()).isEqualTo(Duration.ofHours(1));
		assertThat(dir.unit()).isEqualTo("W/m2");

		// measured 2026-10-03 at cell (884,394): mean since 00 UTC 33.023438 W/m² at +10 h, 52.273438 at +11 h
		double hourly = IconParameters.hourly(52.273438, Duration.ofHours(11), 33.023438, Duration.ofHours(10));
		assertThat(hourly).isCloseTo(11 * 52.273438 - 10 * 33.023438, within(1e-9)); // 244.77 W/m² for 10–11 UTC
		assertThat(hourly).isCloseTo(244.773438, within(1e-5));
		assertThat(IconParameters.hourly(7.5, Duration.ofHours(1), 123, Duration.ZERO)).isEqualTo(7.5);
		assertThatThrownBy(() -> IconParameters.hourly(1, Duration.ofHours(1), 1, Duration.ofHours(1)))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
