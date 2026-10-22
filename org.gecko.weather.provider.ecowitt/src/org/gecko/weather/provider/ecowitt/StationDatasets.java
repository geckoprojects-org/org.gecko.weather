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

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;

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
import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;

/**
 * The station's vocabulary → canonical kinds. One telegram becomes one partial
 * {@link SourceDataset} for {@code WeatherDataSink.append}: the header names the station, every
 * value is {@code Quality.OBSERVED} from {@code Origin.LOCAL_STATION}, valid at the receive time.
 * <p>
 * Levels: temperature and humidity at {@code GROUND_2M}, where such a station usually hangs; wind
 * {@code UNSPECIFIED}, because the mast height is whatever the installation is and not the 10 m of
 * the forecasts. Radiation is Ecowitt's own derivation from the light sensor (lux / 126.7). The rain
 * value is the device's running counter, flagged as such in the uncertainty note. Station health
 * (battery, supercapacitor, signal) travels as values too, so a screen for the own station reads
 * one timeline like any other.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
public final class StationDatasets {

	/** Identity carried in every dataset and value. */
	public record Product(String providerId, String productId, String licence, String attribution) {
		public Product {
			requireNonNull(providerId, "providerId");
			requireNonNull(productId, "productId");
		}
	}

	/** Ecowitt's conversion from illuminance to solar irradiance: W/m² = lux / 126.7. */
	public static final double LUX_PER_WATT = 126.7;

	/** A telegram every ~9 s; a minute covers a few lost ones before the dataset counts as late. */
	public static final Duration EXPECTED_REFRESH = Duration.ofMinutes(1);

	static final String NOTE_MAST = "at the station's mounting height, not 10 m";
	static final String NOTE_RADIATION = "from illuminance: lux / 126.7 (Ecowitt convention)";
	static final String NOTE_RAIN = "running counter since the device was last reset, not a period sum";
	static final String NOTE_SIGNAL = "as seen by the rtl_433 receiver (-M level)";

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;

	private StationDatasets() {
	}

	/**
	 * @param reading   the decoded telegram
	 * @param at        receive time; becomes {@code issuedAt}, {@code retrievedAt} and {@code validAt}
	 * @param product   provider, product, licence, attribution
	 * @param stationId the device id as a string
	 * @return a dataset with one value per member the telegram carries; may have no values
	 */
	public static SourceDataset observation(Reading reading, Instant at, Product product, String stationId) {
		return observation(reading, at, product, stationId, null, null);
	}

	/**
	 * As {@link #observation(Reading, Instant, Product, String)}, with the gust replaced by the maximum
	 * seen over a reporting interval that ends at {@code at}.
	 *
	 * @param gustMax  the strongest gust of the telegrams in the interval, or {@code null} to take the
	 *                 telegram's own
	 * @param interval the interval, becomes the gust value's period; {@code null} or zero for none
	 */
	public static SourceDataset observation(Reading reading, Instant at, Product product, String stationId, Double gustMax,
			Duration interval) {
		requireNonNull(reading, "reading");
		requireNonNull(at, "at");
		requireNonNull(product, "product");
		requireNonNull(stationId, "stationId");
		SourceDataset d = F.createSourceDataset();
		d.setProviderId(product.providerId());
		d.setProductId(product.productId());
		d.setIssuedAt(at);
		d.setRetrievedAt(at);
		d.setExpectedRefresh(EXPECTED_REFRESH);
		d.setOrigin(Origin.LOCAL_STATION);
		d.setStationId(stationId);
		d.setDistanceMeters(0);
		d.setLicence(product.licence());
		d.setAttribution(product.attribution());
		d.setHorizonStart(at);
		d.setHorizonEnd(at);

		add(d, reading.getTemperature(), "temperature_C", MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, "Cel", null);
		add(d, reading.getHumidity(), "humidity", MeasurementKind.RELATIVE_HUMIDITY, Level.GROUND_2M, Statistic.INSTANT, "%", null);
		add(d, reading.getWindAverage(), "wind_avg_m_s", MeasurementKind.WIND_SPEED, Level.UNSPECIFIED, Statistic.INSTANT, "m/s", NOTE_MAST);
		MeasuredValue gust = add(d, gustMax != null ? gustMax : reading.getWindGust(), "wind_max_m_s", MeasurementKind.WIND_GUST,
				Level.UNSPECIFIED, Statistic.MAX, "m/s", NOTE_MAST);
		if (gust != null && interval != null && !interval.isZero()) {
			gust.setPeriod(interval);
		}
		add(d, reading.getWindDirection(), "wind_dir_deg", MeasurementKind.WIND_DIRECTION, Level.UNSPECIFIED, Statistic.INSTANT, "deg", NOTE_MAST);
		add(d, reading.getUvIndex(), "uvi", MeasurementKind.UV_INDEX, Level.SURFACE, Statistic.INSTANT, "1", null);
		if (reading.getIlluminance() != null) {
			add(d, reading.getIlluminance() / LUX_PER_WATT, "light_lux", MeasurementKind.GLOBAL_RADIATION, Level.SURFACE, Statistic.INSTANT, "W/m2", NOTE_RADIATION);
		}
		add(d, reading.getRainTotal(), "rain_mm", MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.ACCUMULATED, "mm", NOTE_RAIN);
		// station health, for the station's own screen
		add(d, reading.getBatteryOk(), "battery_ok", MeasurementKind.BATTERY_LEVEL, Level.UNSPECIFIED, Statistic.INSTANT, "1", null);
		if (reading.getBatteryMilliVolts() != null) {
			add(d, reading.getBatteryMilliVolts() / 1000.0, "battery_mV", MeasurementKind.BATTERY_VOLTAGE, Level.UNSPECIFIED, Statistic.INSTANT, "V", null);
		}
		add(d, reading.getSupercapVolts(), "supercap_V", MeasurementKind.CAPACITOR_VOLTAGE, Level.UNSPECIFIED, Statistic.INSTANT, "V", null);
		add(d, reading.getRssi(), "rssi", MeasurementKind.SIGNAL_STRENGTH, Level.UNSPECIFIED, Statistic.INSTANT, "dB", NOTE_SIGNAL);
		add(d, reading.getSnr(), "snr", MeasurementKind.SIGNAL_NOISE_RATIO, Level.UNSPECIFIED, Statistic.INSTANT, "dB", NOTE_SIGNAL);
		return d;
	}

	private static MeasuredValue add(SourceDataset d, Double raw, String element, MeasurementKind kind, Level level,
			Statistic statistic, String unit, String note) {
		if (raw == null) {
			return null;
		}
		MeasuredValue v = F.createMeasuredValue();
		v.setKind(kind);
		v.setLevel(level);
		v.setStatistic(statistic);
		v.setValidAt(d.getIssuedAt());
		v.setValue(raw);
		v.setUnit(unit);

		Provenance p = F.createProvenance();
		p.setProviderId(d.getProviderId());
		p.setProductId(d.getProductId());
		p.setSourceElement(element);
		p.setIssuedAt(d.getIssuedAt());
		p.setRetrievedAt(d.getRetrievedAt());
		p.setOrigin(Origin.LOCAL_STATION);
		p.setStationId(d.getStationId());
		p.setDistanceMeters(0);
		p.setLicence(d.getLicence());
		p.setAttribution(d.getAttribution());
		v.setProvenance(p);

		Uncertainty u = F.createUncertainty();
		u.setQuality(Quality.OBSERVED);
		u.setSpatialMeters(0);
		u.setLeadTime(Duration.ZERO);
		if (note != null) {
			u.setNote(note);
		}
		v.setUncertainty(u);
		d.getValues().add(v);
		return v;
	}
}
