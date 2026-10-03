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
package org.gecko.weather.api.spi;

import java.util.List;
import java.util.Optional;

import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Answers "where is this product read for this site?" — nearest stations for a point product, the
 * cell by index arithmetic for a gridded one. Called by the site registry at registration and on
 * rebind; the result is persisted with the site and later handed back to the provider in
 * {@link FetchRequest}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ConsumerType
public interface SiteBindingResolver {

	String providerId();

	String productId();

	/**
	 * Resolves up to {@code max} bindings for the site, nearest first, with {@code rank} 0..n-1,
	 * {@code distanceMeters} and, where known, {@code elevationDeltaMeters} filled in and {@code origin}
	 * set to {@code AUTOMATIC}. Empty if the product does not cover the site — outside the grid, no
	 * station within reach — never a nonsensical nearest.
	 */
	List<SourceBinding> resolve(Site site, int max);

	/**
	 * Builds a binding for an explicitly named location: a station id for a point product, a cell key
	 * ({@code "i,j"}) for a gridded one. Distance is computed from the site as for automatic bindings;
	 * {@code origin} is set to {@code MANUAL}. Empty if the location is unknown to the product.
	 */
	Optional<SourceBinding> bind(Site site, String locationId);

}
