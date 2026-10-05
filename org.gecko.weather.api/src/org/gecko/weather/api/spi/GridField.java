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

/**
 * One whole gridded field as a provider read it: which product and parameter, which run, when it
 * is valid, the grid and the values row-major ({@code j * ni + i}, rows northwards), {@code NaN}
 * where the source masks. What a provider takes the bound cells from — handed on so that maps
 * can be made from it without a second download. The values are what the file holds: a quantity
 * the source averages since model start (ICON-D2 radiation) arrives averaged, not hourly.
 * <p>
 * The array belongs to the provider and is shared with every listener: read it, never write it.
 *
 * @param providerId the provider, e.g. {@code dwd}
 * @param productId  the product, e.g. {@code ICON-D2}
 * @param parameter  the source's own parameter name, e.g. {@code clct}
 * @param unit       the unit of the values, e.g. {@code %}
 * @param run        the model run (issue time)
 * @param validAt    the instant the field is valid for
 * @param grid       the grid the values sit on
 * @param data       {@code grid.ni() * grid.nj()} values
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
public record GridField(String providerId, String productId, String parameter, String unit, Instant run, Instant validAt,
		RegularLatLonGrid grid, float[] data) {

	public GridField {
		requireNonNull(providerId, "providerId");
		requireNonNull(productId, "productId");
		requireNonNull(parameter, "parameter");
		requireNonNull(unit, "unit");
		requireNonNull(run, "run");
		requireNonNull(validAt, "validAt");
		requireNonNull(grid, "grid");
		requireNonNull(data, "data");
		if (data.length != grid.ni() * grid.nj()) {
			throw new IllegalArgumentException("data has " + data.length + " points, grid " + grid.ni() * grid.nj());
		}
	}

	/** The value at column {@code i} and row {@code j}; NaN when masked. */
	public float valueAt(int i, int j) {
		return data[j * grid.ni() + i];
	}
}
