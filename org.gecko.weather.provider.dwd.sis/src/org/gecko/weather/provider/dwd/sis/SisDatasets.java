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
package org.gecko.weather.provider.dwd.sis;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.model.weather.GridBinding;
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

/**
 * Turns a SIS series at one cell into the {@link SourceDataset} a site gets for that cell —
 * {@code GLOBAL_RADIATION} at the surface, W/m², every value with provenance ({@code GRID_CELL},
 * the cell, distance, {@code SIS} as source element) and an uncertainty: {@code ANALYSIS} for the
 * satellite estimate, {@code FORECAST} with lead time for the forecast.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class SisDatasets {

	public static final String UNIT = "W/m2";

	/** Facts about the product that go into every dataset and provenance. */
	public record ProductInfo(String providerId, String productId, Duration expectedRefresh, Statistic statistic,
			Duration period, Quality quality, String licence, String attribution) {
		public ProductInfo {
			requireNonNull(providerId, "providerId");
			requireNonNull(productId, "productId");
			requireNonNull(expectedRefresh, "expectedRefresh");
			requireNonNull(statistic, "statistic");
			requireNonNull(quality, "quality");
		}
	}

	private SisDatasets() {
	}

	/**
	 * @param issuedAt the issue time of the dataset: the run for a forecast, the newest valid time
	 *                 for a stream of analyses
	 * @param modelRun the run, for a forecast
	 * @param values   valid time → W/m²
	 */
	public static SourceDataset build(GridBinding binding, ProductInfo product, Instant issuedAt, Optional<Instant> modelRun,
			SortedMap<Instant, Double> values, Instant retrievedAt) {
		requireNonNull(binding, "binding");
		requireNonNull(product, "product");
		requireNonNull(issuedAt, "issuedAt");
		requireNonNull(modelRun, "modelRun");
		requireNonNull(values, "values");
		requireNonNull(retrievedAt, "retrievedAt");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		SourceDataset ds = f.createSourceDataset();
		ds.setProviderId(product.providerId());
		ds.setProductId(product.productId());
		ds.setIssuedAt(issuedAt);
		modelRun.ifPresent(ds::setModelRun);
		ds.setRetrievedAt(retrievedAt);
		ds.setExpectedRefresh(product.expectedRefresh());
		ds.setOrigin(Origin.GRID_CELL);
		ds.setCell(EcoreUtil.copy(binding.getCell()));
		ds.setDistanceMeters(binding.getDistanceMeters());
		ds.setLicence(product.licence());
		ds.setAttribution(product.attribution());
		if (!values.isEmpty()) {
			ds.setHorizonStart(values.firstKey());
			ds.setHorizonEnd(values.lastKey());
		}
		for (Map.Entry<Instant, Double> e : values.entrySet()) {
			ds.getValues().add(value(e.getKey(), e.getValue(), issuedAt, modelRun, binding, product, retrievedAt));
		}
		return ds;
	}

	private static MeasuredValue value(Instant validAt, double raw, Instant issuedAt, Optional<Instant> modelRun,
			GridBinding binding, ProductInfo product, Instant retrievedAt) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		MeasuredValue v = f.createMeasuredValue();
		v.setKind(MeasurementKind.GLOBAL_RADIATION);
		v.setLevel(Level.SURFACE);
		v.setStatistic(product.statistic());
		if (product.period() != null) {
			v.setPeriod(product.period());
		}
		v.setValidAt(validAt);
		v.setUnit(UNIT);
		v.setValue(raw);

		Provenance p = f.createProvenance();
		p.setProviderId(product.providerId());
		p.setProductId(product.productId());
		p.setSourceElement(SisNetcdf.VARIABLE);
		modelRun.ifPresent(p::setModelRun);
		p.setIssuedAt(issuedAt);
		p.setRetrievedAt(retrievedAt);
		p.setOrigin(Origin.GRID_CELL);
		p.setCell(EcoreUtil.copy(binding.getCell()));
		p.setDistanceMeters(binding.getDistanceMeters());
		p.setLicence(product.licence());
		p.setAttribution(product.attribution());
		v.setProvenance(p);

		Uncertainty u = f.createUncertainty();
		u.setQuality(product.quality());
		u.setSpatialMeters(binding.getDistanceMeters());
		if (product.quality() == Quality.FORECAST) {
			u.setLeadTime(Duration.between(issuedAt, validAt));
		}
		v.setUncertainty(u);
		return v;
	}
}
