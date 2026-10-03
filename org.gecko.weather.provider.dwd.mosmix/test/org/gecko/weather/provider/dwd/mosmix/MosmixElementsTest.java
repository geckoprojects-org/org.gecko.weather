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
package org.gecko.weather.provider.dwd.mosmix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.Duration;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.provider.dwd.mosmix.MosmixElements.Mapping;
import org.junit.jupiter.api.Test;

/**
 * Every mapping has a known expected value (QR-6).
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class MosmixElementsTest {

	@Test
	void temperatureKelvinToCelsius() {
		Mapping ttt = MosmixElements.mapping("TTT").orElseThrow();
		assertThat(ttt.kind()).isEqualTo(MeasurementKind.AIR_TEMPERATURE);
		assertThat(ttt.level()).isEqualTo(Level.GROUND_2M);
		assertThat(ttt.statistic()).isEqualTo(Statistic.INSTANT);
		assertThat(ttt.unit()).isEqualTo("Cel");
		assertThat(ttt.convert().applyAsDouble(289.25)).isCloseTo(16.1, within(1e-9));
		assertThat(MosmixElements.mapping("T5cm").orElseThrow().level()).isEqualTo(Level.GROUND_5CM);
		assertThat(MosmixElements.mapping("Td").orElseThrow().kind()).isEqualTo(MeasurementKind.DEW_POINT);
		Mapping tx = MosmixElements.mapping("TX").orElseThrow();
		assertThat(tx.statistic()).isEqualTo(Statistic.MAX);
		assertThat(tx.period()).isEqualTo(Duration.ofHours(12));
	}

	@Test
	void radiationKilojoulePerHourToWatt() {
		Mapping rad = MosmixElements.mapping("Rad1h").orElseThrow();
		assertThat(rad.kind()).isEqualTo(MeasurementKind.GLOBAL_RADIATION);
		assertThat(rad.statistic()).isEqualTo(Statistic.MEAN);
		assertThat(rad.period()).isEqualTo(Duration.ofHours(1));
		assertThat(rad.unit()).isEqualTo("W/m2");
		assertThat(rad.convert().applyAsDouble(3600)).isCloseTo(1000, within(1e-9));
		assertThat(rad.convert().applyAsDouble(630)).isCloseTo(175, within(1e-9));
	}

	@Test
	void gustProbabilityKeepsTheSourceThreshold() {
		Mapping p = MosmixElements.mapping("FXh25").orElseThrow();
		assertThat(p.kind()).isEqualTo(MeasurementKind.WIND_GUST);
		assertThat(p.level()).isEqualTo(Level.GROUND_10M);
		assertThat(p.statistic()).isEqualTo(Statistic.PROBABILITY);
		assertThat(p.period()).isEqualTo(Duration.ofHours(12));
		assertThat(p.threshold()).isEqualTo(25.0);
		assertThat(p.thresholdUnit()).isEqualTo("[kn_i]");
		assertThat(p.unit()).isEqualTo("%");
	}

	@Test
	void precipitationFamily() {
		assertThat(MosmixElements.mapping("RR1c").orElseThrow()).satisfies(m -> {
			assertThat(m.kind()).isEqualTo(MeasurementKind.PRECIPITATION);
			assertThat(m.statistic()).isEqualTo(Statistic.ACCUMULATED);
			assertThat(m.unit()).isEqualTo("mm");
		});
		assertThat(MosmixElements.mapping("R602").orElseThrow()).satisfies(m -> {
			assertThat(m.statistic()).isEqualTo(Statistic.PROBABILITY);
			assertThat(m.period()).isEqualTo(Duration.ofHours(6));
			assertThat(m.threshold()).isEqualTo(0.2);
			assertThat(m.thresholdUnit()).isEqualTo("mm");
		});
		assertThat(MosmixElements.mapping("Rd50").orElseThrow().period()).isEqualTo(Duration.ofDays(1));
		assertThat(MosmixElements.mapping("RRS1c").orElseThrow().kind()).isEqualTo(MeasurementKind.SNOW_WATER_EQUIVALENT);
	}

	@Test
	void cloudLayersFogAndCode() {
		assertThat(MosmixElements.mapping("N").orElseThrow().level()).isEqualTo(Level.CLOUD_TOTAL);
		assertThat(MosmixElements.mapping("Nl").orElseThrow().level()).isEqualTo(Level.CLOUD_LOW);
		assertThat(MosmixElements.mapping("N05").orElseThrow().level()).isEqualTo(Level.CLOUD_BELOW_500FT);
		assertThat(MosmixElements.mapping("wwM6").orElseThrow()).satisfies(m -> {
			assertThat(m.kind()).isEqualTo(MeasurementKind.FOG);
			assertThat(m.statistic()).isEqualTo(Statistic.PROBABILITY);
			assertThat(m.threshold()).isNull();
		});
		Mapping ww = MosmixElements.mapping("ww").orElseThrow();
		assertThat(ww.coded()).isTrue();
		assertThat(ww.kind()).isEqualTo(MeasurementKind.SIGNIFICANT_WEATHER);
		assertThat(MosmixElements.mapping("PPPP").orElseThrow().level()).isEqualTo(Level.MEAN_SEA_LEVEL);
	}

	@Test
	void unmappedElementsAndCoverage() {
		assertThat(MosmixElements.isMapped("E_TTT")).isFalse();
		assertThat(MosmixElements.isMapped("W1W2")).isFalse();
		assertThat(MosmixElements.mapping("wwP")).isEmpty();
		assertThat(MosmixElements.elements()).hasSizeGreaterThan(50);
		assertThat(MosmixElements.kinds()).contains(MeasurementKind.AIR_TEMPERATURE, MeasurementKind.CLOUD_COVER,
				MeasurementKind.GLOBAL_RADIATION, MeasurementKind.SIGNIFICANT_WEATHER)
				.doesNotContain(MeasurementKind.UV_INDEX, MeasurementKind.DIRECT_RADIATION);
	}

}
