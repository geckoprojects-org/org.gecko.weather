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

import java.time.Clock;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.api.Geo;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.dwd.icon.IconD2Grid.Cell;

/**
 * The ICON-D2 cell a site lies in — a grid product has one binding per site, at rank 0. Needs no
 * catalogue: the grid is fixed and known. {@code bind} takes {@code "i,j"}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class IconBindingResolver implements SiteBindingResolver {

	private final String providerId;
	private final String productId;
	private final Clock clock;

	public IconBindingResolver(String providerId, String productId, Clock clock) {
		this.providerId = requireNonNull(providerId, "providerId");
		this.productId = requireNonNull(productId, "productId");
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
		if (max < 1 || site.getPosition() == null) {
			return List.of();
		}
		return IconD2Grid.cellFor(site.getPosition()).map(c -> List.<SourceBinding>of(binding(site, c))).orElse(List.of());
	}

	@Override
	public Optional<SourceBinding> bind(Site site, String locationId) {
		requireNonNull(site, "site");
		requireNonNull(locationId, "locationId");
		return IconD2Grid.parse(locationId).map(c -> binding(site, c));
	}

	private GridBinding binding(Site site, Cell cell) {
		GridBinding b = WeatherFactory.eINSTANCE.createGridBinding();
		b.setProviderId(providerId);
		b.setProductId(productId);
		b.setOrigin(BindingOrigin.AUTOMATIC);
		b.setCell(IconD2Grid.gridCell(cell));
		b.setDistanceMeters(Geo.distanceMeters(site.getPosition(), b.getCell().getCenter()));
		b.setResolvedAt(clock.instant());
		return b;
	}
}
