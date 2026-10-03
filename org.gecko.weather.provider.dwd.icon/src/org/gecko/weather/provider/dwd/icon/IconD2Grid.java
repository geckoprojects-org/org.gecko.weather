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

import java.util.Optional;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.dwd.icon.Grib2Field.GridDefinition;

/**
 * The ICON-D2 regular latitude/longitude grid as DWD publishes it (measured from the files, see
 * docs/09-source-inventory.md): grid definition template 0, 1215 × 746 points at 0.02°, first point
 * 43.18° N / 3.94° W, west→east and south→north. Cell resolution is index arithmetic; a quarter of
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
	public static final GridDefinition DEFINITION = new GridDefinition(0, LA1, LO1, RESOLUTION_DEGREES, RESOLUTION_DEGREES,
			NI, NJ, SCAN_MODE);

	/** Tolerance for comparing a file's grid against the definition — GRIB stores micro-degrees, the library floats. */
	static final double TOLERANCE = 1e-4;

	/** Column (longitude index) and row (latitude index) of one cell. */
	public record Cell(int i, int j) implements Comparable<Cell> {
		public Cell {
			if (i < 0 || i >= NI || j < 0 || j >= NJ) {
				throw new IllegalArgumentException("cell (" + i + "," + j + ") outside the ICON-D2 grid");
			}
		}

		public int flatIndex() {
			return j * NI + i;
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(flatIndex(), o.flatIndex());
		}

		@Override
		public String toString() {
			return i + "," + j;
		}
	}

	private IconD2Grid() {
	}

	/** The cell whose centre is nearest to the position, if the position lies on the grid. */
	public static Optional<Cell> cellFor(double latitude, double longitude) {
		double dLon = ((longitude - LO1) % 360 + 360) % 360;
		long i = Math.round(dLon / RESOLUTION_DEGREES);
		long j = Math.round((latitude - LA1) / RESOLUTION_DEGREES);
		if (i < 0 || i >= NI || j < 0 || j >= NJ) {
			return Optional.empty();
		}
		return Optional.of(new Cell((int) i, (int) j));
	}

	public static Optional<Cell> cellFor(GeoPosition position) {
		requireNonNull(position, "position");
		return cellFor(position.getLatitude(), position.getLongitude());
	}

	/** {@code "i,j"} as {@link Cell#toString()} writes it and {@code weather:assign} takes it. */
	public static Optional<Cell> parse(String text) {
		requireNonNull(text, "text");
		String[] parts = text.trim().split("\\s*,\\s*");
		if (parts.length != 2) {
			return Optional.empty();
		}
		try {
			int i = Integer.parseInt(parts[0]);
			int j = Integer.parseInt(parts[1]);
			if (i < 0 || i >= NI || j < 0 || j >= NJ) {
				return Optional.empty();
			}
			return Optional.of(new Cell(i, j));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static GeoPosition center(Cell cell) {
		requireNonNull(cell, "cell");
		GeoPosition p = WeatherFactory.eINSTANCE.createGeoPosition();
		p.setLatitude(LA1 + cell.j() * RESOLUTION_DEGREES);
		p.setLongitude(LO1 + cell.i() * RESOLUTION_DEGREES);
		return p;
	}

	/** A fresh model object for the cell, with centre and resolution. */
	public static GridCell gridCell(Cell cell) {
		GridCell c = WeatherFactory.eINSTANCE.createGridCell();
		c.setGridId(GRID_ID);
		c.setI(cell.i());
		c.setJ(cell.j());
		c.setCenter(center(cell));
		c.setResolutionDegrees(RESOLUTION_DEGREES);
		return c;
	}

	/** The cell a model object names, if it is on this grid. */
	public static Optional<Cell> cellOf(GridCell cell) {
		requireNonNull(cell, "cell");
		if (!GRID_ID.equals(cell.getGridId()) || cell.getI() < 0 || cell.getI() >= NI || cell.getJ() < 0 || cell.getJ() >= NJ) {
			return Optional.empty();
		}
		return Optional.of(new Cell(cell.getI(), cell.getJ()));
	}

	/** Whether a file's grid is this grid. */
	public static boolean matches(GridDefinition grid) {
		return DEFINITION.nearlyEquals(requireNonNull(grid, "grid"), TOLERANCE);
	}
}
