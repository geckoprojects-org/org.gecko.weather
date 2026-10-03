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
package org.gecko.weather.api;

import java.util.List;
import java.util.Optional;

import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Registers sites and keeps their source bindings resolved. Registration is the operational act that
 * makes a location exist for this service: from then on ingest supplies it and its values can be
 * requested by its id.
 * <p>
 * Bindings are resolved per product by the {@link org.gecko.weather.api.spi.SiteBindingResolver}s
 * present at the time; several stations per product may be bound, ranked by distance. A manual
 * assignment becomes rank 0 and is kept across {@link #rebind(String)}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ProviderType
public interface SiteRegistry {

	/**
	 * Registers a site, resolves its bindings against all present resolvers and persists it.
	 *
	 * @return the persisted site, with its (possibly generated) id and bindings
	 * @throws IllegalArgumentException if the given id is already registered
	 */
	Site register(SiteRegistration registration);

	Optional<Site> get(String siteId);

	/** All registered sites, active and inactive. */
	List<Site> list();

	/**
	 * Re-resolves the automatic bindings of a site — after a provider appeared, or a catalogue changed.
	 * Manual bindings are kept.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	Site rebind(String siteId);

	/**
	 * Assigns a source location by hand, overriding the automatic resolution for that product: the
	 * binding becomes rank 0 with origin {@code MANUAL}, automatic ones move down.
	 *
	 * @param locationId the provider's station id, or a grid cell key as the product's resolver
	 *                   documents it
	 * @return the new binding
	 * @throws UnknownSiteException     if no such site is registered
	 * @throws IllegalArgumentException if no resolver for the product is present or the location is
	 *                                  unknown to it
	 */
	SourceBinding assign(String siteId, String providerId, String productId, String locationId);

	/**
	 * Marks a site inactive: ingest stops supplying it, stored data is kept.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	void deactivate(String siteId);

	/**
	 * Removes a site and everything stored for it — report, archive. Irreversible.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	void remove(String siteId);

}
