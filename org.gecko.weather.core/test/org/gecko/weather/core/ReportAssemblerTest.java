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
package org.gecko.weather.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.gecko.weather.api.Reports;
import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.ValueQuery;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.repository.file.XmiFolderRepository;
import org.gecko.weather.solar.SpaSolarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Report assembly against the real file repository and the real solar service.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class ReportAssemblerTest {

	private static final Instant NOW = TestData.NOW;
	private static final Instant ISSUE_1 = Instant.parse("2026-10-03T03:00:00Z");
	private static final Instant ISSUE_2 = Instant.parse("2026-10-03T09:00:00Z");

	@TempDir
	Path tmp;

	private XmiFolderRepository repo;
	private ReportAssembler sink;
	private WeatherServiceImpl weather;

	@BeforeEach
	void setUp() {
		repo = new XmiFolderRepository(tmp);
		Clock clock = Clock.fixed(NOW, ZoneOffset.UTC);
		new SiteRegistryImpl(repo, List::of, CoreSettings.DEFAULTS, clock)
				.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home").withElevation(118));
		sink = new ReportAssembler(repo, new SolarDatasets(new SpaSolarService(clock)), CoreSettings.DEFAULTS, clock);
		weather = new WeatherServiceImpl(repo);
	}

	@Test
	void replaceCreatesTheReportWithSolarPartOverTheHorizon() {
		sink.replace("home", TestData.forecast("MOSMIX_L", ISSUE_1, 48, MeasurementKind.AIR_TEMPERATURE, 10));

		WeatherReport report = repo.loadReport("home").orElseThrow();
		assertThat(report.getGeneratedAt()).isEqualTo(NOW);
		assertThat(report.getDatasets()).extracting(SourceDataset::getProductId).containsExactly("MOSMIX_L", "solar");

		SourceDataset solar = Reports.dataset(report, "gecko", "solar").orElseThrow();
		assertThat(solar.getOrigin()).isEqualTo(Origin.COMPUTED);
		assertThat(solar.getHorizonStart()).isEqualTo(ISSUE_1);
		assertThat(solar.getHorizonEnd()).isEqualTo(ISSUE_1.plus(Duration.ofHours(48)));
		assertThat(solar.getValues()).hasSize(2 * 49); // elevation + azimuth per hourly step, both ends inclusive
		MeasuredValue first = solar.getValues().get(0);
		assertThat(first.getKind()).isEqualTo(MeasurementKind.SUN_ELEVATION);
		assertThat(first.getUnit()).isEqualTo("deg");
		assertThat(first.getUncertainty().getQuality()).isEqualTo(Quality.DERIVED);
		assertThat(first.getProvenance().getDerivation().getFunctionId()).isEqualTo(SolarDatasets.FUNCTION_POSITION);

		// three civil days in Europe/Berlin: 3, 4, 5 October
		assertThat(report.getDays()).extracting(d -> d.getDate()).containsExactly(LocalDate.of(2026, 10, 3),
				LocalDate.of(2026, 10, 4), LocalDate.of(2026, 10, 5));
		assertThat(report.getDays().get(0).getSunrise()).isNotNull();
	}

	@Test
	void replaceSwapsTheProductAndArchivesThePreviousIssue() {
		sink.replace("home", TestData.forecast("MOSMIX_L", ISSUE_1, 48, MeasurementKind.AIR_TEMPERATURE, 10));
		sink.replace("home", TestData.forecast("ICON-D2", ISSUE_1, 24, MeasurementKind.CLOUD_COVER, 60));
		sink.replace("home", TestData.forecast("MOSMIX_L", ISSUE_2, 48, MeasurementKind.AIR_TEMPERATURE, 12));

		WeatherReport report = repo.loadReport("home").orElseThrow();
		assertThat(report.getDatasets()).extracting(SourceDataset::getProductId).containsExactlyInAnyOrder("MOSMIX_L", "ICON-D2", "solar");
		assertThat(Reports.dataset(report, "dwd", "MOSMIX_L").orElseThrow().getIssuedAt()).isEqualTo(ISSUE_2);
		assertThat(Reports.dataset(report, "dwd", "ICON-D2").orElseThrow().getIssuedAt()).isEqualTo(ISSUE_1);

		List<SourceDataset> archived = weather.archive("home", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX);
		assertThat(archived).singleElement().satisfies(d -> assertThat(d.getIssuedAt()).isEqualTo(ISSUE_1));
		assertThat(weather.archive("home", "dwd", "ICON-D2", Instant.EPOCH, Instant.MAX)).isEmpty();
		assertThat(weather.archive("home", "gecko", "solar", Instant.EPOCH, Instant.MAX)).as("solar is never archived").isEmpty();

		// the solar horizon follows the union: MOSMIX now reaches ISSUE_2 + 48 h
		SourceDataset solar = Reports.dataset(report, "gecko", "solar").orElseThrow();
		assertThat(solar.getHorizonStart()).isEqualTo(ISSUE_1);
		assertThat(solar.getHorizonEnd()).isEqualTo(ISSUE_2.plus(Duration.ofHours(48)));
	}

	@Test
	void valuesAcrossSourcesStayPerSource() {
		sink.replace("home", TestData.forecast("MOSMIX_L", ISSUE_1, 48, MeasurementKind.AIR_TEMPERATURE, 10));
		sink.append("home", TestData.observation(ISSUE_1.plus(Duration.ofHours(6)), 15.5));

		Instant at = ISSUE_1.plus(Duration.ofHours(6));
		List<MeasuredValue> temps = weather.values("home", ValueQuery.of(MeasurementKind.AIR_TEMPERATURE).between(at, at.plusSeconds(1)));
		assertThat(temps).hasSize(2);
		assertThat(temps).extracting(v -> v.getProvenance().getOrigin()).containsExactly(Origin.LOCAL_STATION, Origin.STATION);
		assertThat(temps.get(0).getValue()).isEqualTo(15.5);
		assertThat(temps.get(1).getValue()).isEqualTo(16.0);
		assertThat(weather.values("home", MeasurementKind.SUN_ELEVATION)).isNotEmpty();
		assertThat(weather.values("home", MeasurementKind.UV_INDEX)).isEmpty();
	}

	@Test
	void appendAccumulatesAStreamAndKeepsAWindow() {
		Instant start = NOW.minus(Duration.ofDays(8));
		for (int i = 0; i <= 8 * 24; i += 6) {
			sink.append("home", TestData.observation(start.plus(Duration.ofHours(i)), 10 + i % 10));
		}

		WeatherReport report = repo.loadReport("home").orElseThrow();
		SourceDataset own = Reports.dataset(report, "ecowitt", "GW1100").orElseThrow();
		Instant cutoff = NOW.minus(CoreSettings.DEFAULTS.streamWindow());
		assertThat(own.getValues()).isNotEmpty().allMatch(v -> !v.getValidAt().isBefore(cutoff));
		assertThat(own.getValues()).hasSize(29); // 7 days × 4 per day, both ends inclusive
		assertThat(own.getIssuedAt()).isEqualTo(start.plus(Duration.ofHours(192)));
		assertThat(own.getHorizonStart()).isEqualTo(own.getValues().get(0).getValidAt());
		assertThat(own.getHorizonEnd()).isEqualTo(start.plus(Duration.ofHours(192)));
		assertThat(own.getOrigin()).isEqualTo(Origin.LOCAL_STATION);
		assertThat(weather.archive("home", "ecowitt", "GW1100", Instant.EPOCH, Instant.MAX)).as("streams are not archived").isEmpty();

		// the solar part spans the observation window, days cover it
		assertThat(report.getDays()).hasSizeBetween(7, 9);
	}

	@Test
	void rejectsUnknownSiteIncompleteAndSolarDatasets() {
		SourceDataset ok = TestData.forecast("MOSMIX_L", ISSUE_1, 1, MeasurementKind.AIR_TEMPERATURE, 10);
		assertThatThrownBy(() -> sink.replace("nope", ok)).isInstanceOf(UnknownSiteException.class);

		SourceDataset noIssue = TestData.forecast("MOSMIX_L", ISSUE_1, 1, MeasurementKind.AIR_TEMPERATURE, 10);
		noIssue.setIssuedAt(null);
		assertThatThrownBy(() -> sink.replace("home", noIssue)).isInstanceOf(IllegalArgumentException.class);

		SourceDataset solar = TestData.forecast("solar", ISSUE_1, 1, MeasurementKind.SUN_ELEVATION, 0);
		solar.setProviderId("gecko");
		assertThatThrownBy(() -> sink.replace("home", solar)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void callerObjectsAreCopiedNotCaptured() {
		SourceDataset ds = TestData.forecast("MOSMIX_L", ISSUE_1, 2, MeasurementKind.AIR_TEMPERATURE, 10);
		sink.replace("home", ds);
		ds.getValues().clear();
		assertThat(Reports.dataset(repo.loadReport("home").orElseThrow(), "dwd", "MOSMIX_L").orElseThrow().getValues()).hasSize(3);
	}

}
