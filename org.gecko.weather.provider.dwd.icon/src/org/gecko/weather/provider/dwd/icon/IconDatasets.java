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
package org.gecko.weather.provider.dwd.icon;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.SortedMap;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.dwd.icon.IconParameters.Parameter;

/**
 * Turns the series read at one cell into the {@link SourceDataset} a site gets for that cell: every
 * value with full provenance ({@code GRID_CELL}, the cell, distance of the site to its centre, the
 * DWD parameter name as source element, the model run as issue time) and a {@code FORECAST}
 * uncertainty with lead time and the distance as spatial representativeness.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class IconDatasets {

	/** Facts about the product that go into every dataset and provenance. */
	public record ProductInfo(String providerId, String productId, Duration expectedRefresh, String licence,
			String attribution) {
		public ProductInfo {
			requireNonNull(providerId, "providerId");
			requireNonNull(productId, "productId");
			requireNonNull(expectedRefresh, "expectedRefresh");
		}
	}

	private IconDatasets() {
	}

	/**
	 * @param series per parameter the values by valid time, already hourly (de-averaged) where the
	 *               parameter needs it
	 * @return the dataset; without values if every series is empty — the caller decides what an
	 *         empty cell means
	 */
	public static SourceDataset build(GridBinding binding, ProductInfo product, Instant run,
			Map<Parameter, ? extends SortedMap<Instant, Double>> series, Instant retrievedAt) {
		requireNonNull(binding, "binding");
		requireNonNull(product, "product");
		requireNonNull(run, "run");
		requireNonNull(series, "series");
		requireNonNull(retrievedAt, "retrievedAt");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		double distance = binding.getDistanceMeters();

		SourceDataset ds = f.createSourceDataset();
		ds.setProviderId(product.providerId());
		ds.setProductId(product.productId());
		ds.setIssuedAt(run);
		ds.setModelRun(run);
		ds.setRetrievedAt(retrievedAt);
		ds.setExpectedRefresh(product.expectedRefresh());
		ds.setOrigin(Origin.GRID_CELL);
		ds.setCell(EcoreUtil.copy(binding.getCell()));
		ds.setDistanceMeters(distance);
		ds.setLicence(product.licence());
		ds.setAttribution(product.attribution());

		Instant first = null;
		Instant last = null;
		for (Map.Entry<Parameter, ? extends SortedMap<Instant, Double>> e : series.entrySet()) {
			for (Map.Entry<Instant, Double> v : e.getValue().entrySet()) {
				ds.getValues().add(value(e.getKey(), v.getValue(), v.getKey(), run, binding, product, retrievedAt));
				first = first == null || v.getKey().isBefore(first) ? v.getKey() : first;
				last = last == null || v.getKey().isAfter(last) ? v.getKey() : last;
			}
		}
		if (first != null) {
			ds.setHorizonStart(first);
			ds.setHorizonEnd(last);
		}
		return ds;
	}

	private static MeasuredValue value(Parameter p, double raw, Instant validAt, Instant run, GridBinding binding,
			ProductInfo product, Instant retrievedAt) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		MeasuredValue v = f.createMeasuredValue();
		v.setKind(p.kind());
		v.setLevel(p.level());
		v.setStatistic(p.statistic());
		if (p.period() != null) {
			v.setPeriod(p.period());
		}
		v.setValidAt(validAt);
		v.setUnit(p.unit());
		v.setValue(raw);

		Provenance prov = f.createProvenance();
		prov.setProviderId(product.providerId());
		prov.setProductId(product.productId());
		prov.setSourceElement(p.name());
		prov.setModelRun(run);
		prov.setIssuedAt(run);
		prov.setRetrievedAt(retrievedAt);
		prov.setOrigin(Origin.GRID_CELL);
		prov.setCell(EcoreUtil.copy(binding.getCell()));
		prov.setDistanceMeters(binding.getDistanceMeters());
		prov.setLicence(product.licence());
		prov.setAttribution(product.attribution());
		v.setProvenance(prov);

		Uncertainty u = f.createUncertainty();
		u.setQuality(Quality.FORECAST);
		u.setSpatialMeters(binding.getDistanceMeters());
		u.setLeadTime(Duration.between(run, validAt));
		v.setUncertainty(u);
		return v;
	}
}
