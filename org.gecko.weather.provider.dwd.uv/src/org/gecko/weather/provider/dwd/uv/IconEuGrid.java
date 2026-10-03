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
package org.gecko.weather.provider.dwd.uv;

import static java.util.Objects.requireNonNull;

import org.gecko.weather.api.spi.RegularLatLonGrid;
import org.gecko.weather.grib2.Grib2Field.GridDefinition;

/**
 * The ICON-EU regular latitude/longitude grid DWD's health forecasts use (measured from the UV
 * files, docs/09-source-inventory.md): template 0, 1377 × 657 points at 0.0625°, first point
 * 29.5° N / 23.5° W, west→east and south→north, no bitmap.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class IconEuGrid {

	public static final String GRID_ID = "icon-eu-regular-lat-lon";
	public static final double LA1 = 29.5;
	public static final double LO1 = -23.5;
	public static final double RESOLUTION_DEGREES = 0.0625;
	public static final int NI = 1377;
	public static final int NJ = 657;
	public static final int SCAN_MODE = 0x40;
	public static final RegularLatLonGrid GRID = new RegularLatLonGrid(GRID_ID, LA1, LO1, RESOLUTION_DEGREES, NI, NJ);
	public static final GridDefinition DEFINITION = new GridDefinition(0, LA1, LO1, RESOLUTION_DEGREES, RESOLUTION_DEGREES,
			NI, NJ, SCAN_MODE);

	static final double TOLERANCE = 1e-4;

	private IconEuGrid() {
	}

	public static boolean matches(GridDefinition grid) {
		return DEFINITION.nearlyEquals(requireNonNull(grid, "grid"), TOLERANCE);
	}
}
