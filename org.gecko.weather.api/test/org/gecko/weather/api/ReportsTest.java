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
package org.gecko.weather.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.SortedMap;

import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The reading helpers and {@link ValueQuery} hand back every source's value and never drop one.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class ReportsTest {

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;

	private static final Instant T0 = Instant.parse("2026-10-03T12:00:00Z");
	private static final Instant MOSMIX_ISSUE = Instant.parse("2026-10-03T09:00:00Z");
	private static final Instant ICON_ISSUE = Instant.parse("2026-10-03T10:30:00Z");

	private WeatherReport report;
	private SourceDataset mosmix;
	private SourceDataset icon;

	@BeforeEach
	void buildReport() {
		report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(T0);

		// MOSMIX: hourly total cloud cover 12:00..15:00, temperature and UV at 12:00
		mosmix = dataset("MOSMIX_L", Origin.STATION, MOSMIX_ISSUE, Duration.ofHours(6));
		for (int h = 0; h < 4; h++) {
			mosmix.getValues().add(value(mosmix, MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, T0.plus(Duration.ofHours(h)), 70 + h));
		}
		mosmix.getValues().add(value(mosmix, MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, T0, 17.3));
		mosmix.getValues().add(value(mosmix, MeasurementKind.AIR_TEMPERATURE, Level.GROUND_5CM, T0, 15.1));
		report.getDatasets().add(mosmix);

		// ICON-D2: newer issue, total cloud 12:00 and 13:00, low cloud 12:00
		icon = dataset("ICON-D2", Origin.GRID_CELL, ICON_ISSUE, Duration.ofHours(3));
		icon.getValues().add(value(icon, MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, T0, 62.5));
		icon.getValues().add(value(icon, MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, T0.plus(Duration.ofHours(1)), 55));
		icon.getValues().add(value(icon, MeasurementKind.CLOUD_COVER, Level.CLOUD_LOW, T0, 40));
		report.getDatasets().add(icon);

		DayInfo day = F.createDayInfo();
		day.setDate(LocalDate.of(2026, 10, 3));
		report.getDays().add(day);
	}

	@Test
	void valuesOfOneKindComeFromEverySourceNewestIssueFirstPerInstant() {
		List<MeasuredValue> values = Reports.values(report, MeasurementKind.CLOUD_COVER);

		assertThat(values).hasSize(7);
		// 12:00 → both ICON values (newer issue) before MOSMIX
		assertThat(values.subList(0, 3)).allMatch(v -> v.getValidAt().equals(T0));
		assertThat(values.get(0).getProvenance().getProductId()).isEqualTo("ICON-D2");
		assertThat(values.get(1).getProvenance().getProductId()).isEqualTo("ICON-D2");
		assertThat(values.get(2).getProvenance().getProductId()).isEqualTo("MOSMIX_L");
		// 14:00 and 15:00 → MOSMIX only, in time order
		assertThat(values.get(5).getValidAt()).isEqualTo(T0.plus(Duration.ofHours(2)));
		assertThat(values.get(6).getValidAt()).isEqualTo(T0.plus(Duration.ofHours(3)));
	}

	@Test
	void queryNarrowsByLevelStatisticWindowAndProduct() {
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.AIR_TEMPERATURE))).hasSize(2);
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.AIR_TEMPERATURE).level(Level.GROUND_2M)))
				.singleElement().satisfies(v -> assertThat(v.getValue()).isEqualTo(17.3));
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.CLOUD_COVER).statistic(Statistic.MAX))).isEmpty();
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.CLOUD_COVER).products("ICON-D2"))).hasSize(3);
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.CLOUD_COVER).between(T0, T0.plus(Duration.ofHours(1)))))
				.hasSize(3).allMatch(v -> v.getValidAt().equals(T0));
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.CLOUD_COVER, MeasurementKind.AIR_TEMPERATURE))).hasSize(9);
		assertThat(Reports.values(report, ValueQuery.of(MeasurementKind.UV_INDEX))).isEmpty();
	}

	@Test
	void queryRejectsNonsense() {
		assertThatThrownBy(() -> ValueQuery.of(MeasurementKind.UV_INDEX).between(T0, T0)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> new ValueQuery(java.util.Set.of(), java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Set.of())).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void valuesAtReturnsOnePerCoveringSource() {
		List<MeasuredValue> at12 = Reports.valuesAt(report, MeasurementKind.CLOUD_COVER, T0);
		assertThat(at12).hasSize(3);
		assertThat(at12).extracting(v -> v.getProvenance().getProductId()).containsExactly("ICON-D2", "ICON-D2", "MOSMIX_L");

		List<MeasuredValue> at15 = Reports.valuesAt(report, MeasurementKind.CLOUD_COVER, T0.plus(Duration.ofHours(3)));
		assertThat(at15).singleElement().satisfies(v -> assertThat(v.getProvenance().getOrigin()).isEqualTo(Origin.STATION));
	}

	@Test
	void timelineGroupsByInstantAcrossSources() {
		SortedMap<Instant, List<MeasuredValue>> timeline = Reports.timeline(report,
				ValueQuery.of(MeasurementKind.CLOUD_COVER).level(Level.CLOUD_TOTAL));

		assertThat(timeline.keySet()).containsExactly(T0, T0.plus(Duration.ofHours(1)), T0.plus(Duration.ofHours(2)), T0.plus(Duration.ofHours(3)));
		assertThat(timeline.get(T0)).hasSize(2);
		assertThat(timeline.get(T0.plus(Duration.ofHours(1)))).hasSize(2);
		assertThat(timeline.get(T0.plus(Duration.ofHours(3)))).hasSize(1);
	}

	@Test
	void sourceKeyDistinguishesStationsAndCells() {
		SourceDataset other = dataset("MOSMIX_L", Origin.STATION, MOSMIX_ISSUE, Duration.ofHours(6));
		mosmix.setStationId("10487");
		other.setStationId("O457");
		assertThat(Reports.sameSource(mosmix, other)).isFalse();
		other.setStationId("10487");
		assertThat(Reports.sameSource(mosmix, other)).isTrue();
		assertThat(Reports.sourceKey(mosmix)).isEqualTo("dwd/MOSMIX_L@10487");
		org.gecko.weather.model.weather.GridCell cell = F.createGridCell();
		cell.setGridId("icon-d2");
		cell.setI(884);
		cell.setJ(394);
		icon.setCell(cell);
		assertThat(Reports.sourceKey(icon)).isEqualTo("dwd/ICON-D2@icon-d2:884,394");
		report.getDatasets().add(other);
		assertThat(Reports.datasets(report, "dwd", "MOSMIX_L")).hasSize(2);
		assertThat(Reports.dataset(report, "dwd", "MOSMIX_L", "O457")).isEmpty();
	}

	@Test
	void datasetLookupDayAndHorizon() {
		assertThat(Reports.dataset(report, "dwd", "ICON-D2")).contains(icon);
		assertThat(Reports.dataset(report, "dwd", "SIS")).isEmpty();
		assertThat(Reports.horizonEnd(report)).contains(MOSMIX_ISSUE.plus(Duration.ofHours(240)));
		assertThat(Reports.day(report, LocalDate.of(2026, 10, 3))).isPresent();
		assertThat(Reports.day(report, LocalDate.of(2026, 10, 4))).isEmpty();
	}

	@Test
	void stalenessIsJudgedAgainstExpectedRefresh() {
		assertThat(Reports.isStale(mosmix, MOSMIX_ISSUE.plus(Duration.ofHours(5)))).isFalse();
		assertThat(Reports.isStale(mosmix, MOSMIX_ISSUE.plus(Duration.ofHours(7)))).isTrue();
		assertThat(Reports.isStale(icon, ICON_ISSUE.plus(Duration.ofHours(4)))).isTrue();
		icon.unsetExpectedRefresh();
		assertThat(Reports.isStale(icon, ICON_ISSUE.plus(Duration.ofDays(30)))).isFalse();
	}

	// --- helpers -------------------------------------------------------------------------

	private static SourceDataset dataset(String productId, Origin origin, Instant issuedAt, Duration refresh) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(productId);
		d.setIssuedAt(issuedAt);
		d.setExpectedRefresh(refresh);
		d.setOrigin(origin);
		d.setHorizonStart(issuedAt);
		d.setHorizonEnd(issuedAt.plus("MOSMIX_L".equals(productId) ? Duration.ofHours(240) : Duration.ofHours(48)));
		return d;
	}

	private static MeasuredValue value(SourceDataset ds, MeasurementKind kind, Level level, Instant validAt, double v) {
		MeasuredValue mv = F.createMeasuredValue();
		mv.setKind(kind);
		mv.setLevel(level);
		mv.setValidAt(validAt);
		mv.setValue(v);
		mv.setUnit(kind == MeasurementKind.AIR_TEMPERATURE ? "Cel" : "%");
		Provenance p = F.createProvenance();
		p.setProviderId(ds.getProviderId());
		p.setProductId(ds.getProductId());
		p.setIssuedAt(ds.getIssuedAt());
		p.setOrigin(ds.getOrigin());
		mv.setProvenance(p);
		Uncertainty u = F.createUncertainty();
		u.setQuality(Quality.FORECAST);
		u.setLeadTime(Duration.between(ds.getIssuedAt(), validAt));
		mv.setUncertainty(u);
		return mv;
	}

}
