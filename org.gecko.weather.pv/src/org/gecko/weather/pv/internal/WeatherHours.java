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
package org.gecko.weather.pv.internal;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * What the PV computation needs from a site's report, per hour: the radiation of the hour —
 * direct and diffuse from the ICON-D2 cell where it covers (48 h), else MOSMIX global radiation of
 * the rank-0 station, to be split by Erbs — and air temperature and wind in the middle of the hour
 * from the same station. The PV add-on picks here, the weather service never does (ADR-0013).
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class WeatherHours {

	static final String DWD = "dwd";
	static final String ICON = "ICON-D2";
	static final String MOSMIX = "MOSMIX_L";
	static final Duration HOUR = Duration.ofHours(1);

	/**
	 * The weather of the hour starting at {@code start}.
	 *
	 * @param direct  direct horizontal W/m², null when the source does not split
	 * @param diffuse diffuse horizontal W/m², null when the source does not split
	 */
	public record HourWeather(double global, Double direct, Double diffuse, double airTemperature, double windSpeed,
			String source) {
		public boolean split() {
			return direct != null && diffuse != null;
		}
	}

	private final Map<String, MeasuredValue> icon = new HashMap<>();
	private final Map<String, MeasuredValue> mosmix = new HashMap<>();

	public WeatherHours(Site site, WeatherReport report) {
		dataset(report, DWD, ICON).ifPresent(d -> index(d, icon));
		primaryStation(site, report).ifPresent(d -> index(d, mosmix));
	}

	private static Optional<SourceDataset> dataset(WeatherReport report, String provider, String product) {
		return report.getDatasets().stream().filter(d -> provider.equals(d.getProviderId()) && product.equals(d.getProductId()))
				.findFirst();
	}

	/** MOSMIX of the station bound at rank 0, else the first MOSMIX dataset. */
	static Optional<SourceDataset> primaryStation(Site site, WeatherReport report) {
		Optional<String> station = site.getBindings().stream()
				.filter(b -> DWD.equals(b.getProviderId()) && MOSMIX.equals(b.getProductId()) && b instanceof StationBinding)
				.min(Comparator.comparingInt(SourceBinding::getRank)).map(b -> ((StationBinding) b).getStation().getId());
		List<SourceDataset> all = report.getDatasets().stream()
				.filter(d -> DWD.equals(d.getProviderId()) && MOSMIX.equals(d.getProductId())).toList();
		return station.flatMap(id -> all.stream().filter(d -> id.equals(d.getStationId())).findFirst()).or(() -> all.stream().findFirst());
	}

	private static void index(SourceDataset ds, Map<String, MeasuredValue> into) {
		for (MeasuredValue v : ds.getValues()) {
			into.putIfAbsent(key(v.getKind(), v.getLevel(), v.getStatistic(), v.isSetPeriod() ? v.getPeriod() : null, v.getValidAt()), v);
		}
	}

	private static String key(MeasurementKind kind, Level level, Statistic statistic, Duration period, Instant at) {
		return kind + "|" + level + "|" + statistic + "|" + period + "|" + at;
	}

	private static Optional<Double> number(Map<String, MeasuredValue> index, MeasurementKind kind, Level level, Statistic statistic,
			Duration period, Instant at) {
		MeasuredValue v = index.get(key(kind, level, statistic, period, at));
		return v != null && v.isSetValue() ? Optional.of(v.getValue()) : Optional.empty();
	}

	/** The hour's weather, if a source has its radiation and the station its temperature. */
	public Optional<HourWeather> hour(Instant start) {
		Instant end = start.plus(HOUR);
		Optional<Double> temperature = mid(MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, start);
		if (temperature.isEmpty()) {
			return Optional.empty();
		}
		double wind = mid(MeasurementKind.WIND_SPEED, Level.GROUND_10M, start).orElse(2.0);
		Optional<Double> direct = number(icon, MeasurementKind.DIRECT_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end);
		Optional<Double> diffuse = number(icon, MeasurementKind.DIFFUSE_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end);
		if (direct.isPresent() && diffuse.isPresent()) {
			double b = Math.max(0, direct.get());
			double d = Math.max(0, diffuse.get());
			return Optional.of(new HourWeather(b + d, b, d, temperature.get(), wind, ICON));
		}
		return number(mosmix, MeasurementKind.GLOBAL_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end)
				.map(g -> new HourWeather(Math.max(0, g), null, null, temperature.get(), wind, MOSMIX));
	}

	/** An instant quantity in the middle of the hour: mean of its start and end, or whichever exists. */
	private Optional<Double> mid(MeasurementKind kind, Level level, Instant start) {
		Optional<Double> a = number(mosmix, kind, level, Statistic.INSTANT, null, start);
		Optional<Double> b = number(mosmix, kind, level, Statistic.INSTANT, null, start.plus(HOUR));
		if (a.isPresent() && b.isPresent()) {
			return Optional.of((a.get() + b.get()) / 2);
		}
		return a.or(() -> b);
	}
}
