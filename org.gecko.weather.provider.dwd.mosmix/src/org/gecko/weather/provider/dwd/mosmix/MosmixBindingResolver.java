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

import java.time.Clock;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * Nearest MOSMIX stations for a site, by great-circle distance over the catalogue. The catalogue
 * comes through a supplier so that the provider can refresh it without the resolver noticing.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class MosmixBindingResolver implements SiteBindingResolver {

	/** Beyond this a station says nothing useful about a site; the resolver returns fewer or none. */
	public static final double MAX_DISTANCE_METERS = 150_000;

	private final String providerId;
	private final String productId;
	private final Supplier<StationCatalog> catalog;
	private final Clock clock;

	public MosmixBindingResolver(String providerId, String productId, Supplier<StationCatalog> catalog, Clock clock) {
		this.providerId = requireNonNull(providerId, "providerId");
		this.productId = requireNonNull(productId, "productId");
		this.catalog = requireNonNull(catalog, "catalog");
		this.clock = requireNonNull(clock, "clock");
	}

	@Override
	public String providerId() {
		return providerId;
	}

	@Override
	public String productId() {
		return productId;
	}

	@Override
	public List<SourceBinding> resolve(Site site, int max) {
		requireNonNull(site, "site");
		StationCatalog stations = catalog.get();
		if (stations == null) {
			return List.of();
		}
		GeoPosition here = site.getPosition();
		return stations.getStations().stream()
				.map(s -> new Candidate(s, distanceMeters(here, s.getPosition())))
				.filter(c -> c.distance <= MAX_DISTANCE_METERS)
				.sorted(Comparator.comparingDouble(c -> c.distance))
				.limit(Math.max(0, max))
				.map(c -> binding(site, c.station, c.distance))
				.toList();
	}

	@Override
	public Optional<SourceBinding> bind(Site site, String locationId) {
		requireNonNull(site, "site");
		requireNonNull(locationId, "locationId");
		StationCatalog stations = catalog.get();
		if (stations == null) {
			return Optional.empty();
		}
		return stations.getStations().stream().filter(s -> locationId.equals(s.getId())).findFirst()
				.map(s -> binding(site, s, distanceMeters(site.getPosition(), s.getPosition())));
	}

	private SourceBinding binding(Site site, Station station, double distance) {
		StationBinding b = WeatherFactory.eINSTANCE.createStationBinding();
		b.setProviderId(providerId);
		b.setProductId(productId);
		b.setOrigin(BindingOrigin.AUTOMATIC);
		b.setStation(EcoreUtil.copy(station));
		b.setDistanceMeters(distance);
		if (site.getPosition().isSetElevation() && station.getPosition().isSetElevation()) {
			b.setElevationDeltaMeters(station.getPosition().getElevation() - site.getPosition().getElevation());
		}
		b.setResolvedAt(clock.instant());
		return b;
	}

	/** Haversine on a 6371 km sphere; metres. */
	public static double distanceMeters(GeoPosition a, GeoPosition b) {
		double r = 6_371_000;
		double dLat = Math.toRadians(b.getLatitude() - a.getLatitude());
		double dLon = Math.toRadians(b.getLongitude() - a.getLongitude());
		double h = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(a.getLatitude()))
				* Math.cos(Math.toRadians(b.getLatitude())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		return 2 * r * Math.asin(Math.min(1, Math.sqrt(h)));
	}

	private record Candidate(Station station, double distance) {
	}

}
