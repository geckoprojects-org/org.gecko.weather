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
package org.gecko.weather.map;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;

import org.gecko.weather.api.spi.GridField;
import org.gecko.weather.api.spi.RegularLatLonGrid;
import org.gecko.weather.model.weather.GeoPosition;

/**
 * A rectangle of a grid in cell indices, {@code [i0, i1) × [j0, j1)}: the part of a field a map
 * is wanted for — around the sites, not the whole model domain.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
public record GridWindow(int i0, int j0, int i1, int j1) {

	private static final double KM_PER_DEGREE = 111.32;

	public GridWindow {
		if (i0 < 0 || j0 < 0 || i1 <= i0 || j1 <= j0) {
			throw new IllegalArgumentException("empty or negative window " + i0 + "," + j0 + " – " + i1 + "," + j1);
		}
	}

	/**
	 * The smallest window holding a circle of {@code radiusKm} around every position, clipped to
	 * the grid; empty if no circle touches it.
	 */
	public static Optional<GridWindow> around(RegularLatLonGrid grid, Collection<GeoPosition> positions, double radiusKm) {
		requireNonNull(grid, "grid");
		requireNonNull(positions, "positions");
		double minLat = Double.MAX_VALUE;
		double maxLat = -Double.MAX_VALUE;
		double minLon = Double.MAX_VALUE;
		double maxLon = -Double.MAX_VALUE;
		for (GeoPosition p : positions) {
			double dLat = radiusKm / KM_PER_DEGREE;
			double dLon = radiusKm / (KM_PER_DEGREE * Math.max(0.01, Math.cos(Math.toRadians(p.getLatitude()))));
			minLat = Math.min(minLat, p.getLatitude() - dLat);
			maxLat = Math.max(maxLat, p.getLatitude() + dLat);
			minLon = Math.min(minLon, p.getLongitude() - dLon);
			maxLon = Math.max(maxLon, p.getLongitude() + dLon);
		}
		if (minLat > maxLat) {
			return Optional.empty();
		}
		double res = grid.resolutionDegrees();
		int i0 = Math.max(0, (int) Math.floor((minLon - grid.lo1()) / res));
		int i1 = Math.min(grid.ni(), (int) Math.ceil((maxLon - grid.lo1()) / res) + 1);
		int j0 = Math.max(0, (int) Math.floor((minLat - grid.la1()) / res));
		int j1 = Math.min(grid.nj(), (int) Math.ceil((maxLat - grid.la1()) / res) + 1);
		if (i1 <= i0 || j1 <= j0) {
			return Optional.empty();
		}
		return Optional.of(new GridWindow(i0, j0, i1, j1));
	}

	/** The part of the field inside the window, on a grid of its own (same id, shifted origin). */
	public GridField crop(GridField field) {
		requireNonNull(field, "field");
		RegularLatLonGrid grid = field.grid();
		if (i1 > grid.ni() || j1 > grid.nj()) {
			throw new IllegalArgumentException("window " + this + " outside " + grid.ni() + "x" + grid.nj());
		}
		int ni = i1 - i0;
		int nj = j1 - j0;
		float[] data = new float[ni * nj];
		for (int j = 0; j < nj; j++) {
			System.arraycopy(field.data(), (j0 + j) * grid.ni() + i0, data, j * ni, ni);
		}
		RegularLatLonGrid part = new RegularLatLonGrid(grid.gridId(), grid.latitude(j0), grid.longitude(i0), grid.resolutionDegrees(),
				ni, nj);
		return new GridField(field.providerId(), field.productId(), field.parameter(), field.unit(), field.run(), field.validAt(), part,
				data);
	}
}
