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
package org.gecko.weather.provider.dwd.sis;

import org.gecko.weather.api.spi.RegularLatLonGrid;

/**
 * The grid of DWD's SIS "DE" products as measured from the files (docs/09-source-inventory.md):
 * 221 × 221 points at 0.05° from 46° N / 5° E to 57° N / 16° E, latitude and longitude axes
 * ascending, variable {@code SIS(time, lat, lon)} in W/m², fill value −1.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class SisGrid {

	/** The {@code gridId} every binding and dataset of the SIS products carries. */
	public static final String GRID_ID = "sis-de-v3";
	public static final double LA1 = 46.0;
	public static final double LO1 = 5.0;
	public static final double RESOLUTION_DEGREES = 0.05;
	public static final int NI = 221;
	public static final int NJ = 221;
	public static final RegularLatLonGrid GRID = new RegularLatLonGrid(GRID_ID, LA1, LO1, RESOLUTION_DEGREES, NI, NJ);

	/** The axes are stored as floats (5.050003 for 5.05). */
	static final double TOLERANCE = 1e-3;

	private SisGrid() {
	}

	/** Whether a file's latitude and longitude axes describe this grid. */
	public static boolean matches(double[] latitudes, double[] longitudes) {
		if (latitudes.length != NJ || longitudes.length != NI) {
			return false;
		}
		double dLat = (latitudes[NJ - 1] - latitudes[0]) / (NJ - 1);
		double dLon = (longitudes[NI - 1] - longitudes[0]) / (NI - 1);
		return GRID.nearlyEquals(latitudes[0], longitudes[0], dLon, dLat, NI, NJ, TOLERANCE);
	}
}
