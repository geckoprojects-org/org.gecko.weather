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

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.dwd.mosmix.MosmixElements.Mapping;
import org.gecko.weather.provider.dwd.mosmix.MosmixKmlParser.StationForecast;

/**
 * Turns one station's decoded forecast into the {@link SourceDataset} a site gets for that station:
 * every mapped element and time step with a value becomes a {@link MeasuredValue} with full
 * provenance ({@code STATION}, station id, distance from the site) and a {@code FORECAST} uncertainty
 * carrying lead time and the distance as spatial representativeness.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class MosmixDatasets {

	/** Facts about the product that go into every dataset and provenance. */
	public record ProductInfo(String providerId, String productId, Duration expectedRefresh, String licence,
			String attribution) {
		public ProductInfo {
			requireNonNull(providerId, "providerId");
			requireNonNull(productId, "productId");
			requireNonNull(expectedRefresh, "expectedRefresh");
		}
	}

	private MosmixDatasets() {
	}

	public static SourceDataset build(StationForecast forecast, StationBinding binding, ProductInfo product, Instant retrievedAt) {
		requireNonNull(forecast, "forecast");
		requireNonNull(binding, "binding");
		requireNonNull(product, "product");
		requireNonNull(retrievedAt, "retrievedAt");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		List<Instant> steps = forecast.header().timeSteps();
		Instant issuedAt = forecast.header().issuedAt();
		String stationId = forecast.stationId();
		double distance = binding.getDistanceMeters();

		SourceDataset ds = f.createSourceDataset();
		ds.setProviderId(product.providerId());
		ds.setProductId(product.productId());
		ds.setIssuedAt(issuedAt);
		forecast.header().modelRun().ifPresent(ds::setModelRun);
		ds.setRetrievedAt(retrievedAt);
		ds.setExpectedRefresh(product.expectedRefresh());
		ds.setHorizonStart(steps.get(0));
		ds.setHorizonEnd(steps.get(steps.size() - 1));
		ds.setOrigin(Origin.STATION);
		ds.setStationId(stationId);
		ds.setDistanceMeters(distance);
		ds.setLicence(product.licence());
		ds.setAttribution(product.attribution());

		for (Map.Entry<String, double[]> e : forecast.values().entrySet()) {
			Mapping m = MosmixElements.mapping(e.getKey()).orElse(null);
			if (m == null) {
				continue;
			}
			double[] raw = e.getValue();
			for (int i = 0; i < steps.size(); i++) {
				if (Double.isNaN(raw[i])) {
					continue;
				}
				ds.getValues().add(value(m, raw[i], steps.get(i), issuedAt, forecast, product, retrievedAt, distance));
			}
		}
		return ds;
	}

	private static MeasuredValue value(Mapping m, double raw, Instant validAt, Instant issuedAt, StationForecast forecast,
			ProductInfo product, Instant retrievedAt, double distance) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		MeasuredValue v = f.createMeasuredValue();
		v.setKind(m.kind());
		v.setLevel(m.level());
		v.setStatistic(m.statistic());
		if (m.period() != null) {
			v.setPeriod(m.period());
		}
		if (m.threshold() != null) {
			v.setThreshold(m.threshold());
			v.setThresholdUnit(m.thresholdUnit());
		}
		v.setValidAt(validAt);
		v.setUnit(m.unit());
		if (m.coded()) {
			v.setCode((int) Math.round(raw));
		} else {
			v.setValue(m.convert().applyAsDouble(raw));
		}

		Provenance p = f.createProvenance();
		p.setProviderId(product.providerId());
		p.setProductId(product.productId());
		p.setSourceElement(m.element());
		forecast.header().modelRun().ifPresent(p::setModelRun);
		p.setIssuedAt(issuedAt);
		p.setRetrievedAt(retrievedAt);
		p.setOrigin(Origin.STATION);
		p.setStationId(forecast.stationId());
		p.setDistanceMeters(distance);
		p.setLicence(product.licence());
		p.setAttribution(product.attribution());
		v.setProvenance(p);

		Uncertainty u = f.createUncertainty();
		u.setQuality(Quality.FORECAST);
		u.setSpatialMeters(distance);
		u.setLeadTime(Duration.between(issuedAt, validAt));
		v.setUncertainty(u);
		return v;
	}

}
