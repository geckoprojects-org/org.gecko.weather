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

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.pv.internal.PvPhysics.AcPower;
import org.gecko.weather.pv.internal.PvPhysics.HeatLoss;
import org.gecko.weather.pv.internal.PvPhysics.PlaneIrradiance;
import org.gecko.weather.pv.internal.WeatherHours.HourWeather;
import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.Mounting;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvArrayInfo;
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvOutlook;

/**
 * A plant's expected output from a weather report. Per hour: the sun in the middle of the hour,
 * the hour's radiation (split by Erbs where the source does not split it), onto each array's plane
 * with the plant's horizon, cell temperature, DC power, then per inverter efficiency and ceiling.
 * Radiation values are hourly means, so the mean power of an hour in kW is its energy in kWh.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class PlantForecaster {

	/** Sun position for a place and an instant: {elevation, azimuth} in degrees. */
	@FunctionalInterface
	public interface Sun {
		double[] at(double latitude, double longitude, Instant instant);
	}

	static final Duration HOUR = Duration.ofHours(1);
	static final double DEFAULT_EFFICIENCY = 0.96;

	private final int hours;
	private final int days;
	private final Sun sun;

	public PlantForecaster(int hours, int days, Sun sun) {
		this.hours = hours;
		this.days = days;
		this.sun = requireNonNull(sun, "sun");
	}

	static HeatLoss heatLoss(Mounting mounting) {
		return switch (mounting == null ? Mounting.ROOF_MOUNTED : mounting) {
			case OPEN_RACK -> new HeatLoss(25.0, 6.84);
			case ROOF_INTEGRATED -> new HeatLoss(15.0, 3.0);
			default -> new HeatLoss(20.0, 4.5);
		};
	}

	/** The result of one hour, before it becomes a model object. */
	record HourResult(Instant start, double ac, double dc, double[] arrayDc, double plane, double cell, double global,
			double sunElevation, double sunAzimuth, boolean shaded, boolean clipped, String source) {
	}

	public PvOutlook forecast(Plant plant, Site site, Optional<WeatherReport> report, ZoneId zone, Instant now) {
		requireNonNull(plant, "plant");
		requireNonNull(site, "site");
		PvFactory f = PvFactory.eINSTANCE;
		PvOutlook out = f.createPvOutlook();
		out.setPlantId(plant.getId());
		out.setPlantName(plant.getName());
		out.setSiteId(plant.getSiteId());
		out.setTimeZone(zone.getId());
		out.setGeneratedAt(Date.from(now));
		double peak = plant.getArrays().stream().mapToDouble(PvArray::getPeakPower).sum();
		out.setPeakPower(peak);
		for (PvArray a : plant.getArrays()) {
			PvArrayInfo info = f.createPvArrayInfo();
			info.setName(a.getName());
			info.setAzimuth(a.getAzimuth());
			info.setTilt(a.getTilt());
			info.setPeakPower(a.getPeakPower());
			out.getArrays().add(info);
		}
		if (report.isEmpty() || plant.getArrays().isEmpty()) {
			return out;
		}
		double lat = plant.isSetLatitude() ? plant.getLatitude() : site.getPosition().getLatitude();
		double lon = plant.isSetLongitude() ? plant.getLongitude() : site.getPosition().getLongitude();
		WeatherHours weather = new WeatherHours(site, report.get());
		Horizon horizon = Horizon.of(plant);
		double skyLoss = horizon.skyViewLoss();

		LocalDate today = LocalDate.ofInstant(now, zone);
		Instant from = today.atStartOfDay(zone).toInstant();
		Instant to = today.plusDays(days).atStartOfDay(zone).toInstant();
		Instant firstHour = now.truncatedTo(ChronoUnit.HOURS);
		Map<Instant, HourResult> results = new LinkedHashMap<>();
		for (Instant t = from; t.isBefore(to) || t.isBefore(firstHour.plus(HOUR.multipliedBy(hours))); t = t.plus(HOUR)) {
			double[] s = sun.at(lat, lon, t.plus(Duration.ofMinutes(30)));
			Optional<HourWeather> w = weather.hour(t);
			if (w.isPresent()) {
				results.put(t, hour(plant, t, s[0], s[1], w.get(), horizon, skyLoss));
			} else if (s[0] <= 0) {
				// night needs no weather to be known: nothing comes from the modules
				results.put(t, new HourResult(t, 0, 0, new double[plant.getArrays().size()], 0, Double.NaN, 0, s[0], s[1], false,
						false, null));
			}
		}

		for (int h = 0; h < hours; h++) {
			HourResult r = results.get(firstHour.plus(HOUR.multipliedBy(h)));
			if (r != null) {
				out.getHours().add(toModel(r));
			}
		}
		for (int d = 0; d < days; d++) {
			out.getDays().add(day(today.plusDays(d), zone, results, peak));
		}
		return out;
	}

	private HourResult hour(Plant plant, Instant t, double elevation, double azimuth, HourWeather w, Horizon horizon, double skyLoss) {
		int doy = t.atZone(ZoneOffset.UTC).getDayOfYear();
		double i0 = PvPhysics.extraterrestrial(doy);
		double direct;
		double diffuse;
		if (w.split()) {
			direct = w.direct();
			diffuse = w.diffuse();
		} else {
			diffuse = PvPhysics.erbsDiffuse(w.global(), elevation, i0);
			direct = Math.max(0, w.global() - diffuse);
		}
		boolean blocked = elevation > 0 && horizon.hides(elevation, azimuth);
		List<PvArray> arrays = plant.getArrays();
		double[] arrayDc = new double[arrays.size()];
		Map<Inverter, Double> perInverter = new LinkedHashMap<>();
		double defaultDc = 0;
		int largest = largest(arrays);
		double plane = 0;
		double cell = Double.NaN;
		for (int k = 0; k < arrays.size(); k++) {
			PvArray a = arrays.get(k);
			PlaneIrradiance p = PvPhysics.plane(direct, diffuse, elevation, azimuth, a.getTilt(), a.getAzimuth(), plant.getAlbedo(), i0,
					blocked, skyLoss);
			double tc = PvPhysics.cellTemperature(w.airTemperature(), p.total(), w.windSpeed(), heatLoss(a.getMounting()));
			arrayDc[k] = PvPhysics.dcPower(a.getPeakPower(), p.total(), tc, a.getTemperatureCoefficient(), plant.getSystemLosses());
			if (a.getInverter() != null) {
				perInverter.merge(a.getInverter(), arrayDc[k], Double::sum);
			} else {
				defaultDc += arrayDc[k];
			}
			if (k == largest) {
				plane = p.total();
				cell = tc;
			}
		}
		double ac = PvPhysics.acPower(defaultDc, DEFAULT_EFFICIENCY, 0).power();
		boolean clipped = false;
		for (Map.Entry<Inverter, Double> e : perInverter.entrySet()) {
			AcPower p = PvPhysics.acPower(e.getValue(), e.getKey().getEfficiency(), e.getKey().getAcPower());
			ac += p.power();
			clipped |= p.clipped();
		}
		double dc = 0;
		for (double v : arrayDc) {
			dc += v;
		}
		return new HourResult(t, ac, dc, arrayDc, plane, cell, w.global(), elevation, azimuth, blocked, clipped, w.source());
	}

	private static PvHour toModel(HourResult r) {
		PvHour h = PvFactory.eINSTANCE.createPvHour();
		h.setTime(Date.from(r.start()));
		h.setPower(r.ac());
		h.setDcPower(r.dc());
		for (double v : r.arrayDc()) {
			h.getArrayPower().add(v);
		}
		h.setGlobalRadiation(r.global());
		h.setPlaneIrradiance(r.plane());
		if (!Double.isNaN(r.cell())) {
			h.setCellTemperature(r.cell());
		}
		h.setSunElevation(r.sunElevation());
		h.setSunAzimuth(r.sunAzimuth());
		h.setShaded(r.shaded());
		h.setClipped(r.clipped());
		if (r.source() != null) {
			h.setSource(r.source());
		}
		return h;
	}

	private static PvDay day(LocalDate date, ZoneId zone, Map<Instant, HourResult> results, double peak) {
		PvDay d = PvFactory.eINSTANCE.createPvDay();
		d.setDate(date.toString());
		Instant from = date.atStartOfDay(zone).toInstant();
		Instant to = date.plusDays(1).atStartOfDay(zone).toInstant();
		double energy = 0;
		int covered = 0;
		HourResult best = null;
		TreeSet<String> sources = new TreeSet<>();
		boolean any = false;
		for (Instant t = from; t.isBefore(to); t = t.plus(HOUR)) {
			HourResult r = results.get(t);
			if (r == null) {
				continue;
			}
			any = true;
			energy += r.ac();
			if (r.source() != null) {
				covered++;
				sources.add(r.source());
			}
			if (best == null || r.ac() > best.ac()) {
				best = r;
			}
		}
		d.setHoursCovered(covered);
		if (!any || covered == 0) {
			return d;
		}
		d.setEnergy(energy);
		if (peak > 0) {
			d.setSpecificYield(energy / peak);
		}
		if (best != null && best.ac() > 0) {
			d.setPeakPower(best.ac());
			d.setPeakTime(Date.from(best.start()));
		}
		d.setSource(String.join(" + ", sources.descendingSet()));
		return d;
	}

	/** Index of the array with the highest peak power — the one PvHour reports plane and cell temperature of. */
	static int largest(List<PvArray> arrays) {
		int best = 0;
		for (int k = 1; k < arrays.size(); k++) {
			if (arrays.get(k).getPeakPower() > arrays.get(best).getPeakPower()) {
				best = k;
			}
		}
		return best;
	}
}
