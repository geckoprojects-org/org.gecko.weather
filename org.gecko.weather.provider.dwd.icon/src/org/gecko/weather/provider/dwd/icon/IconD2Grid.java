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

import org.gecko.weather.api.spi.RegularLatLonGrid;
import org.gecko.weather.provider.dwd.icon.Grib2Field.GridDefinition;

/**
 * The ICON-D2 regular latitude/longitude grid as DWD publishes it (measured from the files, see
 * docs/09-source-inventory.md): grid definition template 0, 1215 × 746 points at 0.02°, first point
 * 43.18° N / 3.94° W, west→east and south→north. Cell arithmetic is {@link #GRID}'s; a quarter of
 * the rectangle carries no data (bitmap), which shows as NaN when reading, not here.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class IconD2Grid {

	/** The {@code gridId} every binding and dataset of this product carries. */
	public static final String GRID_ID = "icon-d2-regular-lat-lon";
	public static final double LA1 = 43.18;
	public static final double LO1 = -3.94;
	public static final double RESOLUTION_DEGREES = 0.02;
	public static final int NI = 1215;
	public static final int NJ = 746;
	public static final int SCAN_MODE = 0x40;
	public static final RegularLatLonGrid GRID = new RegularLatLonGrid(GRID_ID, LA1, LO1, RESOLUTION_DEGREES, NI, NJ);
	public static final GridDefinition DEFINITION = new GridDefinition(0, LA1, LO1, RESOLUTION_DEGREES, RESOLUTION_DEGREES,
			NI, NJ, SCAN_MODE);

	/** Tolerance for comparing a file's grid against the definition — GRIB stores micro-degrees, the library floats. */
	static final double TOLERANCE = 1e-4;

	private IconD2Grid() {
	}

	/** Whether a file's grid is this grid. */
	public static boolean matches(GridDefinition grid) {
		return DEFINITION.nearlyEquals(requireNonNull(grid, "grid"), TOLERANCE);
	}
}
