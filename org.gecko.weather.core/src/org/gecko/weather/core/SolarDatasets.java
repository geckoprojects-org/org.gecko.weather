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

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.solar.SolarPosition;
import org.gecko.weather.api.solar.SolarService;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Derivation;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * Builds the computed solar part of a report: the {@code gecko/solar} dataset with
 * {@code SUN_ELEVATION} and {@code SUN_AZIMUTH} per step over a time range, and one {@link DayInfo}
 * per civil date the range touches. Recomputable at any time, hence never archived.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class SolarDatasets {

	/** {@code Derivation.functionId} of per-step positions. */
	public static final String FUNCTION_POSITION = "solar.position";

	private static final String UNIT_DEGREES = "deg";

	private final SolarService solar;

	public SolarDatasets(SolarService solar) {
		this.solar = requireNonNull(solar, "solar");
	}

	/**
	 * Sun positions for the site at every {@code step} from {@code from} (inclusive) to {@code to}
	 * (inclusive when it falls on a step).
	 */
	public SourceDataset positions(Site site, Instant from, Instant to, Duration step, Instant now) {
		requireNonNull(site, "site");
		requireNonNull(from, "from");
		requireNonNull(to, "to");
		requireNonNull(step, "step");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		SourceDataset ds = f.createSourceDataset();
		ds.setProviderId(WeatherConstants.COMPUTED_PROVIDER_ID);
		ds.setProductId(WeatherConstants.SOLAR_PRODUCT_ID);
		ds.setIssuedAt(now);
		ds.setRetrievedAt(now);
		ds.setOrigin(Origin.COMPUTED);
		ds.setDistanceMeters(0);
		ds.setHorizonStart(from);
		ds.setHorizonEnd(to);
		Provenance template = positionProvenance(site, now);
		for (Instant t = from; !t.isAfter(to); t = t.plus(step)) {
			SolarPosition p = solar.positionAt(site.getPosition(), t);
			ds.getValues().add(value(MeasurementKind.SUN_ELEVATION, t, p.elevation(), template, now));
			ds.getValues().add(value(MeasurementKind.SUN_AZIMUTH, t, p.azimuth(), template, now));
		}
		return ds;
	}

	/** One {@link DayInfo} per civil date in the site's zone from {@code from} to {@code to}. */
	public List<DayInfo> days(Site site, Instant from, Instant to) {
		requireNonNull(site, "site");
		ZoneId zone = ZoneId.of(site.getTimeZone());
		LocalDate first = from.atZone(zone).toLocalDate();
		LocalDate last = to.atZone(zone).toLocalDate();
		List<DayInfo> days = new ArrayList<>();
		for (LocalDate d = first; !d.isAfter(last); d = d.plusDays(1)) {
			days.add(solar.dayInfo(site.getPosition(), d, zone));
		}
		return days;
	}

	private static Provenance positionProvenance(Site site, Instant now) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Provenance p = f.createProvenance();
		p.setProviderId(WeatherConstants.COMPUTED_PROVIDER_ID);
		p.setProductId(WeatherConstants.SOLAR_PRODUCT_ID);
		p.setIssuedAt(now);
		p.setRetrievedAt(now);
		p.setOrigin(Origin.COMPUTED);
		p.setDistanceMeters(0);
		Derivation d = f.createDerivation();
		d.setFunctionId(FUNCTION_POSITION);
		d.getInputs().add("site=" + site.getId());
		d.getInputs().add("lat=" + site.getPosition().getLatitude());
		d.getInputs().add("lon=" + site.getPosition().getLongitude());
		if (site.getPosition().isSetElevation()) {
			d.getInputs().add("elevation=" + site.getPosition().getElevation());
		}
		p.setDerivation(d);
		return p;
	}

	private static MeasuredValue value(MeasurementKind kind, Instant validAt, double v, Provenance template, Instant now) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		MeasuredValue mv = f.createMeasuredValue();
		mv.setKind(kind);
		mv.setValidAt(validAt);
		mv.setValue(v);
		mv.setUnit(UNIT_DEGREES);
		mv.setProvenance(EcoreUtil.copy(template));
		Uncertainty u = f.createUncertainty();
		u.setQuality(Quality.DERIVED);
		u.setSpatialMeters(0);
		u.setLeadTime(Duration.between(now, validAt));
		mv.setUncertainty(u);
		return mv;
	}

}
