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

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * {@link SiteRegistry} over the repository and whatever {@link SiteBindingResolver}s are present at
 * the time of a call. Plain Java; the resolvers come through a supplier so that the OSGi layer can
 * hand in the live whiteboard list.
 * <p>
 * Binding rules: per product, up to {@code maxStationBindings} stations ranked by distance (grid
 * products yield one cell); a manual assignment is rank 0 and pushes the automatic ones down;
 * {@link #rebind} replaces the automatic bindings and keeps the manual ones.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class SiteRegistryImpl implements SiteRegistry {

	private final WeatherRepository repository;
	private final Supplier<? extends Collection<SiteBindingResolver>> resolvers;
	private final CoreSettings settings;
	private final Clock clock;

	public SiteRegistryImpl(WeatherRepository repository, Supplier<? extends Collection<SiteBindingResolver>> resolvers,
			CoreSettings settings, Clock clock) {
		this.repository = requireNonNull(repository, "repository");
		this.resolvers = requireNonNull(resolvers, "resolvers");
		this.settings = requireNonNull(settings, "settings");
		this.clock = requireNonNull(clock, "clock");
	}

	@Override
	public Site register(SiteRegistration registration) {
		requireNonNull(registration, "registration");
		String id = registration.id().orElseGet(() -> UUID.randomUUID().toString());
		if (repository.loadSite(id).isPresent()) {
			throw new IllegalArgumentException("Site already registered: " + id);
		}
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Site site = f.createSite();
		site.setId(id);
		site.setName(registration.name());
		GeoPosition position = f.createGeoPosition();
		position.setLatitude(registration.latitude());
		position.setLongitude(registration.longitude());
		registration.elevation().ifPresent(position::setElevation);
		site.setPosition(position);
		site.setTimeZone(registration.timeZone().orElse(settings.defaultTimeZone()).getId());
		site.setRegisteredAt(clock.instant());
		site.setActive(true);
		registration.attributes().forEach((k, v) -> site.getAttributes().put(k, v));
		site.getBindings().addAll(resolveAutomatic(site));
		renumber(site);
		repository.saveSite(site);
		return site;
	}

	@Override
	public Optional<Site> get(String siteId) {
		return repository.loadSite(requireNonNull(siteId, "siteId"));
	}

	@Override
	public List<Site> list() {
		return repository.loadSites();
	}

	@Override
	public Site rebind(String siteId) {
		Site site = require(siteId);
		List<SourceBinding> manual = site.getBindings().stream()
				.filter(b -> b.getOrigin() == BindingOrigin.MANUAL).toList();
		List<SourceBinding> automatic = resolveAutomatic(site);
		site.getBindings().clear();
		site.getBindings().addAll(manual);
		site.getBindings().addAll(automatic);
		renumber(site);
		repository.saveSite(site);
		return site;
	}

	@Override
	public SourceBinding assign(String siteId, String providerId, String productId, String locationId) {
		requireNonNull(providerId, "providerId");
		requireNonNull(productId, "productId");
		requireNonNull(locationId, "locationId");
		Site site = require(siteId);
		SiteBindingResolver resolver = resolvers.get().stream()
				.filter(r -> providerId.equals(r.providerId()) && productId.equals(r.productId())).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						"No binding resolver for " + providerId + "/" + productId + " is present"));
		SourceBinding binding = resolver.bind(site, locationId).orElseThrow(() -> new IllegalArgumentException(
				"Location " + locationId + " is unknown to " + providerId + "/" + productId));
		binding.setOrigin(BindingOrigin.MANUAL);
		binding.setResolvedAt(clock.instant());
		// a previous manual binding of the same product is replaced, not stacked
		site.getBindings().removeIf(b -> sameProduct(b, binding) && b.getOrigin() == BindingOrigin.MANUAL);
		site.getBindings().add(0, binding);
		renumber(site);
		repository.saveSite(site);
		return binding;
	}

	@Override
	public void deactivate(String siteId) {
		Site site = require(siteId);
		site.setActive(false);
		repository.saveSite(site);
	}

	@Override
	public void remove(String siteId) {
		require(siteId);
		repository.deleteSite(siteId);
	}

	// --- internals -------------------------------------------------------------------------

	private Site require(String siteId) {
		return repository.loadSite(requireNonNull(siteId, "siteId")).orElseThrow(() -> new UnknownSiteException(siteId));
	}

	private List<SourceBinding> resolveAutomatic(Site site) {
		List<SourceBinding> result = new ArrayList<>();
		for (SiteBindingResolver resolver : resolvers.get()) {
			List<SourceBinding> bindings = resolver.resolve(site, settings.maxStationBindings());
			for (SourceBinding b : bindings) {
				b.setOrigin(BindingOrigin.AUTOMATIC);
				if (b.getResolvedAt() == null) {
					b.setResolvedAt(clock.instant());
				}
				result.add(b);
			}
		}
		return result;
	}

	/**
	 * Ranks per product: manual first, then by distance. Only station bindings are ranked against
	 * each other; a grid product has one cell and gets rank 0.
	 */
	private static void renumber(Site site) {
		List<SourceBinding> ordered = new ArrayList<>(site.getBindings());
		ordered.sort(Comparator.comparing(SourceBinding::getProviderId).thenComparing(SourceBinding::getProductId)
				.thenComparing(b -> b.getOrigin() == BindingOrigin.MANUAL ? 0 : 1)
				.thenComparingDouble(SourceBinding::getDistanceMeters));
		String lastProduct = null;
		int rank = 0;
		for (SourceBinding b : ordered) {
			String product = b.getProviderId() + "/" + b.getProductId();
			if (!product.equals(lastProduct)) {
				rank = 0;
				lastProduct = product;
			}
			b.setRank(rank++);
		}
		// keep the model's list in the same order, so the file reads naturally
		site.getBindings().clear();
		site.getBindings().addAll(ordered);
	}

	private static boolean sameProduct(SourceBinding a, SourceBinding b) {
		return a.getProviderId().equals(b.getProviderId()) && a.getProductId().equals(b.getProductId());
	}

	/** Visible for the OSGi layer: which station ids a site is bound to for a product. */
	static List<String> stationIds(Site site, String providerId, String productId) {
		return site.getBindings().stream().filter(b -> b instanceof StationBinding)
				.filter(b -> providerId.equals(b.getProviderId()) && productId.equals(b.getProductId()))
				.map(b -> ((StationBinding) b).getStation().getId()).toList();
	}

}
