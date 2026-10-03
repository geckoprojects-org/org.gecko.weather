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
import java.time.ZoneOffset;
import java.util.List;

import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.ValueQuery;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.repository.file.XmiFolderRepository;
import org.gecko.weather.solar.SpaSolarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class WeatherServiceImplTest {

	private static final Instant ISSUE = Instant.parse("2026-10-03T03:00:00Z");

	@TempDir
	Path tmp;

	private WeatherServiceImpl weather;
	private ReportAssembler sink;

	@BeforeEach
	void setUp() {
		XmiFolderRepository repo = new XmiFolderRepository(tmp);
		Clock clock = Clock.fixed(TestData.NOW, ZoneOffset.UTC);
		new SiteRegistryImpl(repo, List::of, CoreSettings.DEFAULTS, clock)
				.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home"));
		sink = new ReportAssembler(repo, new SolarDatasets(new SpaSolarService(clock)), CoreSettings.DEFAULTS, clock);
		weather = new WeatherServiceImpl(repo);
	}

	@Test
	void registeredSiteWithoutDataIsEmptyNotAnError() {
		assertThat(weather.report("home")).isEmpty();
		assertThat(weather.values("home", MeasurementKind.AIR_TEMPERATURE)).isEmpty();
		assertThat(weather.timeline("home", ValueQuery.of(MeasurementKind.AIR_TEMPERATURE))).isEmpty();
	}

	@Test
	void unknownSiteIsAnError() {
		assertThatThrownBy(() -> weather.report("nope")).isInstanceOf(UnknownSiteException.class);
		assertThatThrownBy(() -> weather.values("nope", MeasurementKind.UV_INDEX)).isInstanceOf(UnknownSiteException.class);
		assertThatThrownBy(() -> weather.archive("nope", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX))
				.isInstanceOf(UnknownSiteException.class);
	}

	@Test
	void onlyTemperatureOnlyUv() {
		sink.replace("home", TestData.forecast("MOSMIX_L", ISSUE, 48, MeasurementKind.AIR_TEMPERATURE, 10));
		sink.replace("home", TestData.forecast("ICON-D2", ISSUE, 24, MeasurementKind.CLOUD_COVER, 60));

		assertThat(weather.values("home", MeasurementKind.AIR_TEMPERATURE)).hasSize(49)
				.allMatch(v -> v.getProvenance().getProductId().equals("MOSMIX_L"));
		assertThat(weather.values("home", MeasurementKind.UV_INDEX)).isEmpty();
		assertThat(weather.values("home", ValueQuery.of(MeasurementKind.CLOUD_COVER).level(Level.CLOUD_TOTAL)
				.between(ISSUE, ISSUE.plus(Duration.ofHours(6))))).hasSize(6);
		assertThat(weather.timeline("home", ValueQuery.of(MeasurementKind.SUN_ELEVATION, MeasurementKind.SUN_AZIMUTH)))
				.hasSize(49).allSatisfy((t, values) -> assertThat(values).hasSize(2));
	}

}
