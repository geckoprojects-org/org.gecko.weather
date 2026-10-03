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

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.util.List;

import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;

/**
 * One ingest run of a product for every site bound to it. The provider downloads what it needs
 * <em>once</em> — an all-stations file, a grid field — and extracts the stations and cells named in
 * the bindings for each site; it never fetches per site what the source publishes per product.
 *
 * @param sites       the active sites with their bindings for this product (only this product's
 *                    bindings, rank order), never empty
 * @param state       change-detection state from the previous run of this product
 * @param now         the run's reference instant, for {@code retrievedAt} and horizon decisions
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record FetchRequest(List<SiteBindings> sites, SourceState state, Instant now) {

	/** A site and the bindings it has for the product being fetched. */
	public record SiteBindings(Site site, List<SourceBinding> bindings) {
		public SiteBindings {
			requireNonNull(site, "site");
			bindings = List.copyOf(requireNonNull(bindings, "bindings"));
			if (bindings.isEmpty()) {
				throw new IllegalArgumentException("site " + site.getId() + " has no bindings for this product");
			}
		}
	}

	public FetchRequest {
		sites = List.copyOf(requireNonNull(sites, "sites"));
		if (sites.isEmpty()) {
			throw new IllegalArgumentException("a fetch needs at least one site");
		}
		requireNonNull(state, "state");
		requireNonNull(now, "now");
	}

}
