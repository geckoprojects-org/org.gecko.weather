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

import static org.gecko.weather.model.weather.Level.CLOUD_BELOW_500FT;
import static org.gecko.weather.model.weather.Level.CLOUD_EFFECTIVE;
import static org.gecko.weather.model.weather.Level.CLOUD_HIGH;
import static org.gecko.weather.model.weather.Level.CLOUD_LOW;
import static org.gecko.weather.model.weather.Level.CLOUD_MID;
import static org.gecko.weather.model.weather.Level.CLOUD_TOTAL;
import static org.gecko.weather.model.weather.Level.GROUND_10M;
import static org.gecko.weather.model.weather.Level.GROUND_2M;
import static org.gecko.weather.model.weather.Level.GROUND_5CM;
import static org.gecko.weather.model.weather.Level.MEAN_SEA_LEVEL;
import static org.gecko.weather.model.weather.Level.SURFACE;
import static org.gecko.weather.model.weather.Level.UNSPECIFIED;
import static org.gecko.weather.model.weather.MeasurementKind.AIR_TEMPERATURE;
import static org.gecko.weather.model.weather.MeasurementKind.CLOUD_COVER;
import static org.gecko.weather.model.weather.MeasurementKind.DEW_POINT;
import static org.gecko.weather.model.weather.MeasurementKind.FOG;
import static org.gecko.weather.model.weather.MeasurementKind.GLOBAL_RADIATION;
import static org.gecko.weather.model.weather.MeasurementKind.PRECIPITATION;
import static org.gecko.weather.model.weather.MeasurementKind.SIGNIFICANT_WEATHER;
import static org.gecko.weather.model.weather.MeasurementKind.SNOW_WATER_EQUIVALENT;
import static org.gecko.weather.model.weather.MeasurementKind.SUNSHINE_DURATION;
import static org.gecko.weather.model.weather.MeasurementKind.SURFACE_PRESSURE;
import static org.gecko.weather.model.weather.MeasurementKind.VISIBILITY;
import static org.gecko.weather.model.weather.MeasurementKind.WIND_DIRECTION;
import static org.gecko.weather.model.weather.MeasurementKind.WIND_GUST;
import static org.gecko.weather.model.weather.MeasurementKind.WIND_SPEED;
import static org.gecko.weather.model.weather.Statistic.ACCUMULATED;
import static org.gecko.weather.model.weather.Statistic.INSTANT;
import static org.gecko.weather.model.weather.Statistic.MAX;
import static org.gecko.weather.model.weather.Statistic.MEAN;
import static org.gecko.weather.model.weather.Statistic.MIN;
import static org.gecko.weather.model.weather.Statistic.PROBABILITY;

import java.time.Duration;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Statistic;

/**
 * The MOSMIX vocabulary → canonical kinds, qualifiers, units and conversions. The one place in the
 * code base that knows what {@code TTT} means. Documented as a table in {@code docs/10-model.md};
 * this is that table as data.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class MosmixElements {

	/**
	 * How one source element maps.
	 *
	 * @param element       MOSMIX element name
	 * @param kind          canonical kind
	 * @param level         vertical reference
	 * @param statistic     relation to the period
	 * @param period        aggregation window, or {@code null} for {@code INSTANT}
	 * @param threshold     for probabilities: the threshold in {@code thresholdUnit}
	 * @param thresholdUnit UCUM unit of the threshold
	 * @param unit          canonical UCUM unit of the mapped value
	 * @param convert       source unit → canonical unit
	 * @param coded         the value is a code, stored in {@code MeasuredValue.code}
	 */
	public record Mapping(String element, MeasurementKind kind, Level level, Statistic statistic, Duration period,
			Double threshold, String thresholdUnit, String unit, DoubleUnaryOperator convert, boolean coded) {
	}

	private static final DoubleUnaryOperator IDENTITY = v -> v;
	private static final DoubleUnaryOperator KELVIN_TO_CELSIUS = v -> v - 273.15;
	/** kJ/m² over one hour → mean W/m²: 1000 J / 3600 s. */
	private static final DoubleUnaryOperator KJ_PER_HOUR_TO_WATT = v -> v / 3.6;

	private static final Duration H1 = Duration.ofHours(1);
	private static final Duration H3 = Duration.ofHours(3);
	private static final Duration H6 = Duration.ofHours(6);
	private static final Duration H12 = Duration.ofHours(12);
	private static final Duration D1 = Duration.ofDays(1);

	private static final Map<String, Mapping> TABLE;

	static {
		Map<String, Mapping> t = new LinkedHashMap<>();
		// temperature — MOSMIX publishes Kelvin
		instant(t, "TTT", AIR_TEMPERATURE, GROUND_2M, "Cel", KELVIN_TO_CELSIUS);
		instant(t, "T5cm", AIR_TEMPERATURE, GROUND_5CM, "Cel", KELVIN_TO_CELSIUS);
		instant(t, "Td", DEW_POINT, GROUND_2M, "Cel", KELVIN_TO_CELSIUS);
		aggregated(t, "TN", AIR_TEMPERATURE, GROUND_2M, MIN, H12, "Cel", KELVIN_TO_CELSIUS);
		aggregated(t, "TX", AIR_TEMPERATURE, GROUND_2M, MAX, H12, "Cel", KELVIN_TO_CELSIUS);
		// pressure
		instant(t, "PPPP", SURFACE_PRESSURE, MEAN_SEA_LEVEL, "Pa", IDENTITY);
		// wind
		instant(t, "DD", WIND_DIRECTION, GROUND_10M, "deg", IDENTITY);
		instant(t, "FF", WIND_SPEED, GROUND_10M, "m/s", IDENTITY);
		aggregated(t, "FX1", WIND_GUST, GROUND_10M, MAX, H1, "m/s", IDENTITY);
		aggregated(t, "FX3", WIND_GUST, GROUND_10M, MAX, H3, "m/s", IDENTITY);
		aggregated(t, "FXh", WIND_GUST, GROUND_10M, MAX, H12, "m/s", IDENTITY);
		probability(t, "FXh25", WIND_GUST, GROUND_10M, H12, 25.0, "[kn_i]");
		probability(t, "FXh40", WIND_GUST, GROUND_10M, H12, 40.0, "[kn_i]");
		probability(t, "FXh55", WIND_GUST, GROUND_10M, H12, 55.0, "[kn_i]");
		// cloud cover by layer
		instant(t, "N", CLOUD_COVER, CLOUD_TOTAL, "%", IDENTITY);
		instant(t, "Neff", CLOUD_COVER, CLOUD_EFFECTIVE, "%", IDENTITY);
		instant(t, "Nl", CLOUD_COVER, CLOUD_LOW, "%", IDENTITY);
		instant(t, "Nm", CLOUD_COVER, CLOUD_MID, "%", IDENTITY);
		instant(t, "Nh", CLOUD_COVER, CLOUD_HIGH, "%", IDENTITY);
		instant(t, "N05", CLOUD_COVER, CLOUD_BELOW_500FT, "%", IDENTITY);
		// radiation and sunshine
		aggregated(t, "Rad1h", GLOBAL_RADIATION, SURFACE, MEAN, H1, "W/m2", KJ_PER_HOUR_TO_WATT);
		aggregated(t, "SunD1", SUNSHINE_DURATION, SURFACE, ACCUMULATED, H1, "s", IDENTITY);
		aggregated(t, "SunD3", SUNSHINE_DURATION, SURFACE, ACCUMULATED, H3, "s", IDENTITY);
		// precipitation amounts (kg/m² ≡ mm)
		aggregated(t, "RR1c", PRECIPITATION, SURFACE, ACCUMULATED, H1, "mm", IDENTITY);
		aggregated(t, "RR3c", PRECIPITATION, SURFACE, ACCUMULATED, H3, "mm", IDENTITY);
		aggregated(t, "RR6c", PRECIPITATION, SURFACE, ACCUMULATED, H6, "mm", IDENTITY);
		aggregated(t, "RRhc", PRECIPITATION, SURFACE, ACCUMULATED, H12, "mm", IDENTITY);
		aggregated(t, "RRdc", PRECIPITATION, SURFACE, ACCUMULATED, D1, "mm", IDENTITY);
		aggregated(t, "RRS1c", SNOW_WATER_EQUIVALENT, SURFACE, ACCUMULATED, H1, "mm", IDENTITY);
		aggregated(t, "RRS3c", SNOW_WATER_EQUIVALENT, SURFACE, ACCUMULATED, H3, "mm", IDENTITY);
		// precipitation probabilities
		probability(t, "R101", PRECIPITATION, SURFACE, H1, 0.1, "mm");
		probability(t, "R102", PRECIPITATION, SURFACE, H1, 0.2, "mm");
		probability(t, "R103", PRECIPITATION, SURFACE, H1, 0.3, "mm");
		probability(t, "R105", PRECIPITATION, SURFACE, H1, 0.5, "mm");
		probability(t, "R107", PRECIPITATION, SURFACE, H1, 0.7, "mm");
		probability(t, "R110", PRECIPITATION, SURFACE, H1, 1.0, "mm");
		probability(t, "R120", PRECIPITATION, SURFACE, H1, 2.0, "mm");
		probability(t, "R130", PRECIPITATION, SURFACE, H1, 3.0, "mm");
		probability(t, "R150", PRECIPITATION, SURFACE, H1, 5.0, "mm");
		probability(t, "R600", PRECIPITATION, SURFACE, H6, 0.0, "mm");
		probability(t, "R602", PRECIPITATION, SURFACE, H6, 0.2, "mm");
		probability(t, "R610", PRECIPITATION, SURFACE, H6, 1.0, "mm");
		probability(t, "R650", PRECIPITATION, SURFACE, H6, 5.0, "mm");
		probability(t, "Rh00", PRECIPITATION, SURFACE, H12, 0.0, "mm");
		probability(t, "Rh02", PRECIPITATION, SURFACE, H12, 0.2, "mm");
		probability(t, "Rh10", PRECIPITATION, SURFACE, H12, 1.0, "mm");
		probability(t, "Rh50", PRECIPITATION, SURFACE, H12, 5.0, "mm");
		probability(t, "Rd00", PRECIPITATION, SURFACE, D1, 0.0, "mm");
		probability(t, "Rd02", PRECIPITATION, SURFACE, D1, 0.2, "mm");
		probability(t, "Rd10", PRECIPITATION, SURFACE, D1, 1.0, "mm");
		probability(t, "Rd50", PRECIPITATION, SURFACE, D1, 5.0, "mm");
		// visibility, fog, significant weather
		instant(t, "VV", VISIBILITY, SURFACE, "m", IDENTITY);
		probability(t, "wwM", FOG, SURFACE, H1, null, null);
		probability(t, "wwM6", FOG, SURFACE, H6, null, null);
		probability(t, "wwMh", FOG, SURFACE, H12, null, null);
		t.put("ww", new Mapping("ww", SIGNIFICANT_WEATHER, UNSPECIFIED, INSTANT, null, null, null, "1", IDENTITY, true));
		TABLE = Collections.unmodifiableMap(t);
	}

	private MosmixElements() {
	}

	public static Optional<Mapping> mapping(String element) {
		return Optional.ofNullable(TABLE.get(element));
	}

	public static boolean isMapped(String element) {
		return TABLE.containsKey(element);
	}

	public static Set<String> elements() {
		return TABLE.keySet();
	}

	/** Every canonical kind this vocabulary can deliver. */
	public static Set<MeasurementKind> kinds() {
		EnumSet<MeasurementKind> kinds = EnumSet.noneOf(MeasurementKind.class);
		TABLE.values().forEach(m -> kinds.add(m.kind()));
		return kinds;
	}

	private static void instant(Map<String, Mapping> t, String element, MeasurementKind kind, Level level, String unit,
			DoubleUnaryOperator convert) {
		t.put(element, new Mapping(element, kind, level, INSTANT, null, null, null, unit, convert, false));
	}

	private static void aggregated(Map<String, Mapping> t, String element, MeasurementKind kind, Level level,
			Statistic statistic, Duration period, String unit, DoubleUnaryOperator convert) {
		t.put(element, new Mapping(element, kind, level, statistic, period, null, null, unit, convert, false));
	}

	private static void probability(Map<String, Mapping> t, String element, MeasurementKind kind, Level level,
			Duration period, Double threshold, String thresholdUnit) {
		t.put(element, new Mapping(element, kind, level, PROBABILITY, period, threshold, thresholdUnit, "%", IDENTITY, false));
	}

}
