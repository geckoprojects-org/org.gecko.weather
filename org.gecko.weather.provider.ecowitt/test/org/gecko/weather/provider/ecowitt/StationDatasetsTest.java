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
package org.gecko.weather.provider.ecowitt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.junit.jupiter.api.Test;

/**
 * The recorded WS90 stream through the codec and the mapping.
 */
class StationDatasetsTest {

	private static final StationDatasets.Product WS90 = new StationDatasets.Product("ecowitt", "WS90", "private", "Eigene Wetterstation");
	private static final Instant AT = Instant.parse("2026-10-22T13:41:32Z");

	@Test
	void readsAWs90Telegram() throws IOException {
		Reading r = TestSupport.reading(TestSupport.lines("ws90-stream.jsonl").get(0));
		assertThat(r.getModel()).isEqualTo("Fineoffset-WS90");
		assertThat(r.getId()).isEqualTo(75459L);
		assertThat(r.getTemperature()).isEqualTo(14.4);
		assertThat(r.getHumidity()).isEqualTo(52.0);
		assertThat(r.getWindDirection()).isEqualTo(75.0);
		assertThat(r.getWindAverage()).isEqualTo(0.9);
		assertThat(r.getWindGust()).isEqualTo(1.4);
		assertThat(r.getUvIndex()).isEqualTo(1.0);
		assertThat(r.getIlluminance()).isEqualTo(15980.0);
		assertThat(r.getRainTotal()).isEqualTo(0.0);
		assertThat(r.getBatteryOk()).isEqualTo(1.0);
		assertThat(r.getBatteryMilliVolts()).isEqualTo(3100);
		assertThat(r.getSupercapVolts()).isEqualTo(0.2);
		assertThat(r.getFirmware()).isEqualTo(160);
		assertThat(r.getMic()).isEqualTo("CRC");
		assertThat(r.getRssi()).as("not recorded without -M level").isNull();
	}

	@Test
	void skipsMembersTheModelDoesNotKnow() throws IOException {
		Reading r = TestSupport.reading(TestSupport.lines("ws90-stream.jsonl").get(2));
		assertThat(r.getModel()).isEqualTo("Nexus-TH");
		assertThat(r.getId()).isEqualTo(42L);
		assertThat(r.getChannel()).isEqualTo(1);
		assertThat(r.getBatteryOk()).isEqualTo(1.0);
		assertThat(r.getWindAverage()).isNull();
	}

	@Test
	void rejectsWhatIsNotJson() {
		assertThatThrownBy(() -> TestSupport.reading("not json at all")).isInstanceOf(IOException.class);
	}

	@Test
	void mapsATelegramToOneObservationDataset() throws IOException {
		Reading r = TestSupport.reading(TestSupport.lines("ws90-stream.jsonl").get(0));
		SourceDataset d = StationDatasets.observation(r, AT, WS90, "75459");

		assertThat(d.getProviderId()).isEqualTo("ecowitt");
		assertThat(d.getProductId()).isEqualTo("WS90");
		assertThat(d.getOrigin()).isEqualTo(Origin.LOCAL_STATION);
		assertThat(d.getStationId()).isEqualTo("75459");
		assertThat(d.getIssuedAt()).isEqualTo(AT);
		assertThat(d.getRetrievedAt()).isEqualTo(AT);
		assertThat(d.getHorizonStart()).isEqualTo(AT);
		assertThat(d.getHorizonEnd()).isEqualTo(AT);
		assertThat(d.getExpectedRefresh()).isEqualTo(Duration.ofMinutes(1));
		assertThat(d.getAttribution()).isEqualTo("Eigene Wetterstation");

		assertThat(d.getValues()).extracting(MeasuredValue::getKind).containsExactly(
				MeasurementKind.AIR_TEMPERATURE, MeasurementKind.RELATIVE_HUMIDITY, MeasurementKind.WIND_SPEED,
				MeasurementKind.WIND_GUST, MeasurementKind.WIND_DIRECTION, MeasurementKind.UV_INDEX,
				MeasurementKind.GLOBAL_RADIATION, MeasurementKind.PRECIPITATION, MeasurementKind.BATTERY_LEVEL,
				MeasurementKind.BATTERY_VOLTAGE, MeasurementKind.CAPACITOR_VOLTAGE);

		MeasuredValue t = value(d, MeasurementKind.AIR_TEMPERATURE);
		assertThat(t.getValue()).isEqualTo(14.4);
		assertThat(t.getUnit()).isEqualTo("Cel");
		assertThat(t.getLevel()).isEqualTo(Level.GROUND_2M);
		assertThat(t.getStatistic()).isEqualTo(Statistic.INSTANT);
		assertThat(t.getValidAt()).isEqualTo(AT);
		assertThat(t.getProvenance().getOrigin()).isEqualTo(Origin.LOCAL_STATION);
		assertThat(t.getProvenance().getStationId()).isEqualTo("75459");
		assertThat(t.getProvenance().getSourceElement()).isEqualTo("temperature_C");
		assertThat(t.getProvenance().getIssuedAt()).isEqualTo(AT);
		assertThat(t.getUncertainty().getQuality()).isEqualTo(Quality.OBSERVED);
		assertThat(t.getUncertainty().getLeadTime()).isEqualTo(Duration.ZERO);

		MeasuredValue wind = value(d, MeasurementKind.WIND_SPEED);
		assertThat(wind.getLevel()).isEqualTo(Level.UNSPECIFIED);
		assertThat(wind.getUncertainty().getNote()).isEqualTo(StationDatasets.NOTE_MAST);
		assertThat(value(d, MeasurementKind.WIND_GUST).getStatistic()).isEqualTo(Statistic.MAX);
		assertThat(value(d, MeasurementKind.WIND_DIRECTION).getUnit()).isEqualTo("deg");

		MeasuredValue rad = value(d, MeasurementKind.GLOBAL_RADIATION);
		assertThat(rad.getValue()).isCloseTo(15980.0 / 126.7, within(0.01));
		assertThat(rad.getUnit()).isEqualTo("W/m2");
		assertThat(rad.getUncertainty().getNote()).isEqualTo(StationDatasets.NOTE_RADIATION);

		MeasuredValue rain = value(d, MeasurementKind.PRECIPITATION);
		assertThat(rain.getStatistic()).isEqualTo(Statistic.ACCUMULATED);
		assertThat(rain.isSetPeriod()).isFalse();
		assertThat(rain.getUncertainty().getNote()).isEqualTo(StationDatasets.NOTE_RAIN);

		assertThat(value(d, MeasurementKind.BATTERY_LEVEL).getValue()).isEqualTo(1.0);
		MeasuredValue battery = value(d, MeasurementKind.BATTERY_VOLTAGE);
		assertThat(battery.getValue()).isEqualTo(3.1);
		assertThat(battery.getUnit()).isEqualTo("V");
		assertThat(value(d, MeasurementKind.CAPACITOR_VOLTAGE).getValue()).isEqualTo(0.2);
	}

	@Test
	void carriesSignalLevelsWhenRecorded() throws IOException {
		Reading r = TestSupport.reading("{\"model\":\"Fineoffset-WS90\",\"id\":75459,\"rssi\":-7.049,\"snr\":23.054,\"noise\":-30.103}");
		SourceDataset d = StationDatasets.observation(r, AT, WS90, "75459");
		assertThat(d.getValues()).extracting(MeasuredValue::getKind)
				.containsExactly(MeasurementKind.SIGNAL_STRENGTH, MeasurementKind.SIGNAL_NOISE_RATIO);
		assertThat(value(d, MeasurementKind.SIGNAL_STRENGTH).getValue()).isEqualTo(-7.049);
	}

	@Test
	void aTelegramWithoutMeasurementsGivesNoValues() throws IOException {
		Reading r = TestSupport.reading("{\"model\":\"Fineoffset-WS90\",\"id\":1}");
		assertThat(StationDatasets.observation(r, AT, WS90, "1").getValues()).isEmpty();
	}

	private static MeasuredValue value(SourceDataset d, MeasurementKind kind) {
		Optional<MeasuredValue> v = d.getValues().stream().filter(x -> x.getKind() == kind).findFirst();
		assertThat(v).as(kind.toString()).isPresent();
		return v.get();
	}

	@SuppressWarnings("unused")
	private static List<MeasuredValue> all(SourceDataset d) {
		return d.getValues();
	}
}
