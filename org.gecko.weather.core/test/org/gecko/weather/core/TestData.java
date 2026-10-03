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

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * Fixtures shared by the core tests: a fake station resolver with three stations around Dresden, a
 * fake grid resolver, dataset builders.
 */
final class TestData {

	static final WeatherFactory F = WeatherFactory.eINSTANCE;
	static final Instant NOW = Instant.parse("2026-10-03T09:30:00Z");

	private TestData() {
	}

	/** Stations by id → (lat, lon); distances from Dresden (51.05, 13.74) ascend 10488 < 10487 < 10385. */
	static final Map<String, double[]> STATIONS = Map.of(
			"10488", new double[] { 51.13, 13.75 },   // Dresden-Klotzsche, ~9 km
			"10487", new double[] { 50.98, 13.95 },   // ~17 km
			"10385", new double[] { 52.47, 13.40 });  // Berlin, ~160 km

	static SiteBindingResolver stationResolver(String productId) {
		return new SiteBindingResolver() {
			@Override
			public String providerId() {
				return "dwd";
			}

			@Override
			public String productId() {
				return productId;
			}

			@Override
			public List<SourceBinding> resolve(Site site, int max) {
				List<SourceBinding> all = new ArrayList<>();
				STATIONS.forEach((id, pos) -> all.add(stationBinding(site, productId, id, pos)));
				all.sort((a, b) -> Double.compare(a.getDistanceMeters(), b.getDistanceMeters()));
				return all.subList(0, Math.min(max, all.size()));
			}

			@Override
			public Optional<SourceBinding> bind(Site site, String locationId) {
				double[] pos = STATIONS.get(locationId);
				return pos == null ? Optional.empty() : Optional.of(stationBinding(site, productId, locationId, pos));
			}
		};
	}

	static SiteBindingResolver gridResolver() {
		return new SiteBindingResolver() {
			@Override
			public String providerId() {
				return "dwd";
			}

			@Override
			public String productId() {
				return "ICON-D2";
			}

			@Override
			public List<SourceBinding> resolve(Site site, int max) {
				GridBinding b = F.createGridBinding();
				b.setProviderId("dwd");
				b.setProductId("ICON-D2");
				GridCell cell = F.createGridCell();
				cell.setGridId("icon-d2-regular-lat-lon");
				cell.setI((int) Math.round((site.getPosition().getLongitude() + 3.94) / 0.02));
				cell.setJ((int) Math.round((site.getPosition().getLatitude() - 43.18) / 0.02));
				b.setCell(cell);
				b.setDistanceMeters(640);
				return List.of(b);
			}

			@Override
			public Optional<SourceBinding> bind(Site site, String locationId) {
				return Optional.empty();
			}
		};
	}

	static StationBinding stationBinding(Site site, String productId, String id, double[] pos) {
		StationBinding b = F.createStationBinding();
		b.setProviderId("dwd");
		b.setProductId(productId);
		b.setOrigin(BindingOrigin.AUTOMATIC);
		Station s = F.createStation();
		s.setId(id);
		s.setName("Station " + id);
		s.setPosition(position(pos[0], pos[1]));
		b.setStation(s);
		b.setDistanceMeters(haversine(site.getPosition(), s.getPosition()));
		return b;
	}

	static GeoPosition position(double lat, double lon) {
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(lat);
		p.setLongitude(lon);
		return p;
	}

	static double haversine(GeoPosition a, GeoPosition b) {
		double r = 6_371_000;
		double dLat = Math.toRadians(b.getLatitude() - a.getLatitude());
		double dLon = Math.toRadians(b.getLongitude() - a.getLongitude());
		double h = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(a.getLatitude()))
				* Math.cos(Math.toRadians(b.getLatitude())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		return 2 * r * Math.asin(Math.sqrt(h));
	}

	/** A forecast dataset of one kind with hourly values from {@code issuedAt} for {@code hours}, station 10488. */
	static SourceDataset forecast(String productId, Instant issuedAt, int hours, MeasurementKind kind, double base) {
		return forecast(productId, issuedAt, hours, kind, base, "10488");
	}

	static SourceDataset forecast(String productId, Instant issuedAt, int hours, MeasurementKind kind, double base, String stationId) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(productId);
		d.setIssuedAt(issuedAt);
		d.setRetrievedAt(issuedAt.plusSeconds(120));
		d.setExpectedRefresh(Duration.ofHours(6));
		d.setOrigin(Origin.STATION);
		d.setStationId(stationId);
		d.setHorizonStart(issuedAt);
		d.setHorizonEnd(issuedAt.plus(Duration.ofHours(hours)));
		for (int h = 0; h <= hours; h++) {
			d.getValues().add(value(d, kind, issuedAt.plus(Duration.ofHours(h)), base + h, Quality.FORECAST));
		}
		return d;
	}

	/** An observation piece from an own station: one value per kind at {@code at}. */
	static SourceDataset observation(Instant at, double temperature) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("ecowitt");
		d.setProductId("GW1100");
		d.setIssuedAt(at);
		d.setRetrievedAt(at);
		d.setExpectedRefresh(Duration.ofMinutes(1));
		d.setOrigin(Origin.LOCAL_STATION);
		d.setStationId("GW1100-roof");
		d.setHorizonStart(at);
		d.setHorizonEnd(at);
		d.getValues().add(value(d, MeasurementKind.AIR_TEMPERATURE, at, temperature, Quality.OBSERVED));
		return d;
	}

	static MeasuredValue value(SourceDataset ds, MeasurementKind kind, Instant validAt, double v, Quality quality) {
		MeasuredValue mv = F.createMeasuredValue();
		mv.setKind(kind);
		mv.setLevel(kind == MeasurementKind.AIR_TEMPERATURE ? Level.GROUND_2M : Level.CLOUD_TOTAL);
		mv.setValidAt(validAt);
		mv.setValue(v);
		mv.setUnit(kind == MeasurementKind.AIR_TEMPERATURE ? "Cel" : "%");
		Provenance p = F.createProvenance();
		p.setProviderId(ds.getProviderId());
		p.setProductId(ds.getProductId());
		p.setIssuedAt(ds.getIssuedAt());
		p.setOrigin(ds.getOrigin());
		p.setStationId(ds.getStationId());
		mv.setProvenance(p);
		Uncertainty u = F.createUncertainty();
		u.setQuality(quality);
		u.setLeadTime(Duration.between(ds.getIssuedAt(), validAt));
		mv.setUncertainty(u);
		return mv;
	}

}
