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
package org.gecko.weather.outlook.internal;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.outlook.model.outlook.DayOutlook;
import org.gecko.weather.outlook.model.outlook.HourOutlook;
import org.gecko.weather.outlook.model.outlook.Outlook;
import org.gecko.weather.outlook.model.outlook.OutlookFactory;
import org.gecko.weather.outlook.model.outlook.SourceNote;

/**
 * Builds an {@link Outlook} from a site's report: the hours, today and the days after it. The report keeps every source apart (ADR-0013);
 * a weather page needs one number per quantity and hour, so this consumer picks — by a fixed rule,
 * and the outlook names the datasets it picked from:
 * <ul>
 * <li>temperature, dew point, wind, gusts, precipitation and its probability, weather code,
 * sunshine: MOSMIX_L of the site's rank-0 station (the nearest unless assigned by hand);</li>
 * <li>cloud cover and global radiation: the ICON-D2 cell where it covers the hour (2.2 km beats a
 * station kilometres away), MOSMIX otherwise — radiation from ICON-D2 is direct + diffuse;</li>
 * <li>UV index: the daily maximum of the UV product;</li>
 * <li>sun elevation, sunrise, sunset: the computed solar dataset and day events.</li>
 * </ul>
 * Instant quantities are read at the hour's start, period quantities from the value that ends one
 * hour later — the hour from 14:00 shows the rain that falls until 15:00.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class OutlookBuilder {

	static final String DWD = "dwd";
	static final String MOSMIX = "MOSMIX_L";
	static final String ICON = "ICON-D2";
	static final String UV = "UVI";
	static final Duration HOUR = Duration.ofHours(1);
	/** Sun below this is night — the upper limb at the horizon with refraction. */
	static final double HORIZON_DEGREES = -0.833;

	private final int hours;
	private final int days;

	public OutlookBuilder(int hours, int days) {
		if (hours < 1 || days < 0) {
			throw new IllegalArgumentException("hours must be positive, days not negative");
		}
		this.hours = hours;
		this.days = days;
	}

	public Outlook build(Site site, Optional<WeatherReport> report, ZoneId zone, Instant now) {
		requireNonNull(site, "site");
		requireNonNull(report, "report");
		requireNonNull(zone, "zone");
		requireNonNull(now, "now");
		OutlookFactory f = OutlookFactory.eINSTANCE;
		Outlook outlook = f.createOutlook();
		outlook.setSiteId(site.getId());
		outlook.setSiteName(site.getName());
		if (site.getPosition() != null) {
			outlook.setLatitude(site.getPosition().getLatitude());
			outlook.setLongitude(site.getPosition().getLongitude());
		}
		outlook.setTimeZone(zone.getId());
		outlook.setGeneratedAt(Date.from(now));
		if (report.isEmpty()) {
			return outlook;
		}
		Sources s = new Sources(site, report.get());
		Instant start = now.truncatedTo(ChronoUnit.HOURS);
		for (int h = 0; h < hours; h++) {
			outlook.getHours().add(hour(s, start.plus(HOUR.multipliedBy(h)), report.get()));
		}
		LocalDate today = LocalDate.ofInstant(now, zone);
		outlook.setToday(day(s, today, zone, report.get()));
		for (int d = 1; d <= days; d++) {
			outlook.getDays().add(day(s, today.plusDays(d), zone, report.get()));
		}
		s.notes(outlook);
		return outlook;
	}

	// --- hours -------------------------------------------------------------------------------

	private HourOutlook hour(Sources s, Instant t, WeatherReport report) {
		HourOutlook h = OutlookFactory.eINSTANCE.createHourOutlook();
		h.setTime(Date.from(t));
		Instant end = t.plus(HOUR);
		s.mosmix(MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, null, t).ifPresent(h::setTemperature);
		s.mosmix(MeasurementKind.DEW_POINT, Level.GROUND_2M, Statistic.INSTANT, null, t).ifPresent(h::setDewPoint);
		s.cloudCover(t).ifPresent(h::setCloudCover);
		s.mosmix(MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.ACCUMULATED, HOUR, end).ifPresent(h::setPrecipitation);
		s.precipitationProbability(end).ifPresent(h::setPrecipitationProbability);
		s.mosmix(MeasurementKind.WIND_SPEED, Level.GROUND_10M, Statistic.INSTANT, null, t).ifPresent(h::setWindSpeed);
		s.mosmix(MeasurementKind.WIND_DIRECTION, Level.GROUND_10M, Statistic.INSTANT, null, t).ifPresent(h::setWindDirection);
		s.mosmix(MeasurementKind.WIND_GUST, Level.GROUND_10M, Statistic.MAX, HOUR, end).ifPresent(h::setWindGust);
		s.globalRadiation(end).ifPresent(h::setGlobalRadiation);
		s.icon(MeasurementKind.DIRECT_RADIATION, end).ifPresent(h::setDirectRadiation);
		s.icon(MeasurementKind.DIFFUSE_RADIATION, end).ifPresent(h::setDiffuseRadiation);
		s.weatherCode(end).ifPresent(h::setWeatherCode);
		s.sunAzimuth(t).ifPresent(h::setSunAzimuth);
		OptionalDouble elevation = s.sunElevation(t);
		elevation.ifPresent(h::setSunElevation);
		h.setDaylight(daylight(report, t.plus(Duration.ofMinutes(30)), elevation));
		return h;
	}

	/** Between sunrise and sunset of a day event, or — without one — the sun above the horizon. */
	static boolean daylight(WeatherReport report, Instant mid, OptionalDouble elevation) {
		for (DayInfo d : report.getDays()) {
			if (d.getSunrise() != null && d.getSunset() != null && !mid.isBefore(d.getSunrise()) && mid.isBefore(d.getSunset())) {
				return true;
			}
		}
		boolean polarOrUnknown = report.getDays().stream().noneMatch(d -> d.getSunrise() != null);
		return polarOrUnknown && elevation.isPresent() && elevation.getAsDouble() > HORIZON_DEGREES;
	}

	// --- days --------------------------------------------------------------------------------

	private DayOutlook day(Sources s, LocalDate date, ZoneId zone, WeatherReport report) {
		DayOutlook d = OutlookFactory.eINSTANCE.createDayOutlook();
		d.setDate(date.toString());
		Instant from = date.atStartOfDay(zone).toInstant();
		Instant to = date.plusDays(1).atStartOfDay(zone).toInstant();
		Predicate<Instant> instantIn = t -> !t.isBefore(from) && t.isBefore(to);
		Predicate<Instant> periodEndIn = t -> t.isAfter(from) && !t.isAfter(to);

		List<Double> temps = s.mosmixSeries(MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, Statistic.INSTANT, null, instantIn);
		temps.stream().min(Comparator.naturalOrder()).ifPresent(d::setTemperatureMin);
		temps.stream().max(Comparator.naturalOrder()).ifPresent(d::setTemperatureMax);
		List<Double> rain = s.mosmixSeries(MeasurementKind.PRECIPITATION, Level.SURFACE, Statistic.ACCUMULATED, HOUR, periodEndIn);
		if (!rain.isEmpty()) {
			d.setPrecipitation(rain.stream().mapToDouble(Double::doubleValue).sum());
		}
		s.mosmixValues(v -> v.getKind() == MeasurementKind.PRECIPITATION && v.getStatistic() == Statistic.PROBABILITY
				&& HOUR.equals(v.getPeriod()) && v.isSetThreshold() && Math.abs(v.getThreshold() - 0.1) < 1e-9
				&& periodEndIn.test(v.getValidAt())).stream().max(Comparator.naturalOrder()).ifPresent(d::setPrecipitationProbability);
		List<Double> sun = s.mosmixSeries(MeasurementKind.SUNSHINE_DURATION, Level.SURFACE, Statistic.ACCUMULATED, HOUR, periodEndIn);
		if (!sun.isEmpty()) {
			d.setSunshineHours(sun.stream().mapToDouble(Double::doubleValue).sum() / 3600.0);
		}
		// Wh/m² per hour = mean W/m² over the hour; summed and divided by 1000 → kWh/m²
		List<Double> radiation = new ArrayList<>();
		for (Instant t = from; t.isBefore(to); t = t.plus(HOUR)) {
			s.globalRadiation(t.plus(HOUR)).ifPresent(radiation::add);
		}
		if (!radiation.isEmpty()) {
			d.setInsolation(radiation.stream().mapToDouble(Double::doubleValue).sum() / 1000.0);
		}
		List<Double> clouds = new ArrayList<>();
		for (Instant t = from; t.isBefore(to); t = t.plus(HOUR)) {
			s.cloudCover(t).ifPresent(clouds::add);
		}
		if (!clouds.isEmpty()) {
			d.setCloudCoverMean(clouds.stream().mapToDouble(Double::doubleValue).average().orElseThrow());
		}
		s.mosmixSeries(MeasurementKind.WIND_GUST, Level.GROUND_10M, Statistic.MAX, HOUR, periodEndIn).stream()
				.max(Comparator.naturalOrder()).ifPresent(d::setWindGustMax);
		s.mosmixCodes(periodEndIn).stream().max(Comparator.comparingInt(OutlookBuilder::significance))
				.ifPresent(d::setWeatherCode);
		s.uvMax(date).ifPresent(d::setUvIndexMax);
		report.getDays().stream().filter(i -> date.equals(i.getDate())).findFirst().ifPresent(i -> {
			if (i.getSunrise() != null) {
				d.setSunrise(Date.from(i.getSunrise()));
			}
			if (i.getSunset() != null) {
				d.setSunset(Date.from(i.getSunset()));
			}
			if (i.getSolarNoon() != null) {
				d.setSolarNoon(Date.from(i.getSolarNoon()));
			}
			if (i.getDayLength() != null) {
				d.setDaylightHours(i.getDayLength().toSeconds() / 3600.0);
			}
		});
		return d;
	}

	/**
	 * How much a ww code says about the day: thunder over snow over rain over drizzle over fog over
	 * the cloud codes 0–3. The WMO numbers almost sort this way themselves; fog (45, 49) is lifted
	 * below precipitation, which the raw numbers would not do.
	 */
	static int significance(int ww) {
		if (ww >= 95) {
			return 600 + ww;
		}
		if (ww >= 70 && ww <= 79 || ww == 85 || ww == 86) {
			return 500 + ww;
		}
		if (ww >= 60 && ww <= 69 || ww >= 80 && ww <= 94) {
			return 400 + ww;
		}
		if (ww >= 50 && ww <= 59) {
			return 300 + ww;
		}
		if (ww == 45 || ww == 49) {
			return 200 + ww;
		}
		return ww;
	}

	// --- the datasets and the picking rule ---------------------------------------------------

	/** The datasets the rule reads, indexed by value identity and valid time. */
	static final class Sources {
		private final SourceDataset mosmix;
		private final SourceDataset icon;
		private final SourceDataset uv;
		private final SourceDataset solar;
		private final Map<String, MeasuredValue> mosmixIndex = new HashMap<>();
		private final Map<String, MeasuredValue> iconIndex = new HashMap<>();
		private final Map<String, MeasuredValue> solarIndex = new HashMap<>();
		private boolean iconUsed;
		private boolean mosmixCloudUsed;

		Sources(Site site, WeatherReport report) {
			this.mosmix = primaryStation(site, report).orElse(null);
			this.icon = first(report, DWD, ICON).orElse(null);
			this.uv = first(report, DWD, UV).orElse(null);
			this.solar = first(report, WeatherConstants.COMPUTED_PROVIDER_ID, WeatherConstants.SOLAR_PRODUCT_ID).orElse(null);
			index(mosmix, mosmixIndex);
			index(icon, iconIndex);
			index(solar, solarIndex);
		}

		/** MOSMIX of the station bound at rank 0, else the first MOSMIX dataset. */
		static Optional<SourceDataset> primaryStation(Site site, WeatherReport report) {
			Optional<String> station = site.getBindings().stream()
					.filter(b -> DWD.equals(b.getProviderId()) && MOSMIX.equals(b.getProductId()) && b instanceof StationBinding)
					.min(Comparator.comparingInt(SourceBinding::getRank))
					.map(b -> ((StationBinding) b).getStation().getId());
			List<SourceDataset> all = report.getDatasets().stream()
					.filter(d -> DWD.equals(d.getProviderId()) && MOSMIX.equals(d.getProductId())).toList();
			return station.flatMap(id -> all.stream().filter(d -> id.equals(d.getStationId())).findFirst())
					.or(() -> all.stream().findFirst());
		}

		static Optional<SourceDataset> first(WeatherReport report, String provider, String product) {
			return report.getDatasets().stream().filter(d -> provider.equals(d.getProviderId()) && product.equals(d.getProductId()))
					.findFirst();
		}

		private static void index(SourceDataset ds, Map<String, MeasuredValue> into) {
			if (ds == null) {
				return;
			}
			for (MeasuredValue v : ds.getValues()) {
				into.putIfAbsent(key(v.getKind(), v.getLevel(), v.getStatistic(), v.getPeriod(), v.getValidAt()), v);
			}
		}

		private static String key(MeasurementKind kind, Level level, Statistic statistic, Duration period, Instant at) {
			return kind + "|" + level + "|" + statistic + "|" + period + "|" + at;
		}

		private static Optional<Double> number(MeasuredValue v) {
			return v != null && v.isSetValue() ? Optional.of(v.getValue()) : Optional.empty();
		}

		Optional<Double> mosmix(MeasurementKind kind, Level level, Statistic statistic, Duration period, Instant at) {
			return number(mosmixIndex.get(key(kind, level, statistic, period, at)));
		}

		Optional<Double> cloudCover(Instant at) {
			Optional<Double> fromIcon = number(iconIndex.get(key(MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, Statistic.INSTANT, null, at)));
			if (fromIcon.isPresent()) {
				iconUsed = true;
				return fromIcon;
			}
			Optional<Double> fromMosmix = mosmix(MeasurementKind.CLOUD_COVER, Level.CLOUD_TOTAL, Statistic.INSTANT, null, at);
			fromMosmix.ifPresent(v -> mosmixCloudUsed = true);
			return fromMosmix;
		}

		Optional<Double> globalRadiation(Instant end) {
			Optional<Double> direct = number(iconIndex.get(key(MeasurementKind.DIRECT_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end)));
			Optional<Double> diffuse = number(iconIndex.get(key(MeasurementKind.DIFFUSE_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end)));
			if (direct.isPresent() && diffuse.isPresent()) {
				iconUsed = true;
				return Optional.of(direct.get() + diffuse.get());
			}
			Optional<Double> fromMosmix = mosmix(MeasurementKind.GLOBAL_RADIATION, Level.SURFACE, Statistic.MEAN, HOUR, end);
			fromMosmix.ifPresent(v -> mosmixCloudUsed = true);
			return fromMosmix;
		}

		/** A radiation component of ICON-D2, the hourly mean ending at {@code end}. */
		Optional<Double> icon(MeasurementKind kind, Instant end) {
			Optional<Double> v = number(iconIndex.get(key(kind, Level.SURFACE, Statistic.MEAN, HOUR, end)));
			v.ifPresent(x -> iconUsed = true);
			return v;
		}

		OptionalDouble sunAzimuth(Instant at) {
			MeasuredValue v = solarIndex.get(key(MeasurementKind.SUN_AZIMUTH, Level.UNSPECIFIED, Statistic.INSTANT, null, at));
			return v != null && v.isSetValue() ? OptionalDouble.of(v.getValue()) : OptionalDouble.empty();
		}

		Optional<Double> precipitationProbability(Instant end) {
			return mosmixValues(v -> v.getKind() == MeasurementKind.PRECIPITATION && v.getStatistic() == Statistic.PROBABILITY
					&& HOUR.equals(v.getPeriod()) && v.isSetThreshold() && Math.abs(v.getThreshold() - 0.1) < 1e-9
					&& end.equals(v.getValidAt())).stream().findFirst();
		}

		Optional<Integer> weatherCode(Instant end) {
			MeasuredValue v = mosmixIndex.get(key(MeasurementKind.SIGNIFICANT_WEATHER, Level.UNSPECIFIED, Statistic.INSTANT, null, end));
			return v != null && v.isSetCode() ? Optional.of(v.getCode()) : Optional.empty();
		}

		OptionalDouble sunElevation(Instant at) {
			MeasuredValue v = solarIndex.get(key(MeasurementKind.SUN_ELEVATION, Level.UNSPECIFIED, Statistic.INSTANT, null, at));
			if (v == null) {
				v = solar == null ? null
						: solar.getValues().stream().filter(x -> x.getKind() == MeasurementKind.SUN_ELEVATION && at.equals(x.getValidAt()))
								.findFirst().orElse(null);
			}
			return v != null && v.isSetValue() ? OptionalDouble.of(v.getValue()) : OptionalDouble.empty();
		}

		List<Double> mosmixSeries(MeasurementKind kind, Level level, Statistic statistic, Duration period, Predicate<Instant> when) {
			return mosmixValues(v -> v.getKind() == kind && v.getLevel() == level && v.getStatistic() == statistic
					&& (period == null ? !v.isSetPeriod() : period.equals(v.getPeriod())) && when.test(v.getValidAt()));
		}

		List<Double> mosmixValues(Predicate<MeasuredValue> filter) {
			if (mosmix == null) {
				return List.of();
			}
			return mosmix.getValues().stream().filter(filter).filter(MeasuredValue::isSetValue).map(MeasuredValue::getValue).toList();
		}

		List<Integer> mosmixCodes(Predicate<Instant> when) {
			if (mosmix == null) {
				return List.of();
			}
			return mosmix.getValues().stream().filter(v -> v.getKind() == MeasurementKind.SIGNIFICANT_WEATHER && v.isSetCode()
					&& when.test(v.getValidAt())).map(MeasuredValue::getCode).toList();
		}

		/** The UV product's daily maximum ends at UTC midnight after the day; that is the local day closest to it. */
		Optional<Double> uvMax(LocalDate date) {
			if (uv == null) {
				return Optional.empty();
			}
			Instant end = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
			return uv.getValues().stream().filter(v -> v.getKind() == MeasurementKind.UV_INDEX && end.equals(v.getValidAt()))
					.filter(MeasuredValue::isSetValue).map(MeasuredValue::getValue).findFirst();
		}

		void notes(Outlook outlook) {
			Map<SourceDataset, String> used = new LinkedHashMap<>();
			if (mosmix != null) {
				used.put(mosmix, mosmixCloudUsed ? "Temperatur, Wind, Niederschlag, Wetter, Sonnenschein, Bewölkung/Strahlung wo ICON-D2 fehlt"
						: "Temperatur, Wind, Niederschlag, Wetter, Sonnenschein");
			}
			if (icon != null && iconUsed) {
				used.put(icon, "Bewölkung, Strahlung direkt und diffus");
			}
			if (uv != null && !outlook.getDays().stream().noneMatch(DayOutlook::isSetUvIndexMax)) {
				used.put(uv, "UV-Index (Tagesmaximum)");
			}
			if (solar != null) {
				used.put(solar, "Sonnenstand, Sonnenauf- und -untergang");
			}
			for (Map.Entry<SourceDataset, String> e : used.entrySet()) {
				SourceDataset d = e.getKey();
				SourceNote n = OutlookFactory.eINSTANCE.createSourceNote();
				n.setQuantities(e.getValue());
				n.setProviderId(d.getProviderId());
				n.setProductId(d.getProductId());
				n.setLocation(d.getStationId() != null ? "Station " + d.getStationId()
						: d.getCell() != null ? "Zelle " + d.getCell().getI() + "," + d.getCell().getJ() + " (" + d.getCell().getGridId() + ")"
						: "berechnet");
				if (d.isSetDistanceMeters()) {
					n.setDistanceMeters(d.getDistanceMeters());
				}
				if (d.getIssuedAt() != null) {
					n.setIssuedAt(Date.from(d.getIssuedAt()));
				}
				outlook.getSources().add(n);
			}
		}
	}
}
