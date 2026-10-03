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

import java.util.Optional;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * A plain latitude/longitude grid: equal steps along both axes, first point at the south-west
 * corner, columns {@code i} eastwards, rows {@code j} northwards. What DWD's ICON-D2 regular-lat-lon
 * and SIS products are; the cell of a position is index arithmetic.
 *
 * @param gridId            the name bindings and datasets carry ({@code GridCell.gridId})
 * @param la1               latitude of row 0
 * @param lo1               longitude of column 0, in [-180, 180)
 * @param resolutionDegrees step along both axes
 * @param ni                columns
 * @param nj                rows
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record RegularLatLonGrid(String gridId, double la1, double lo1, double resolutionDegrees, int ni, int nj) {

	/** Column (longitude index) and row (latitude index) of one cell; ordered row-major. */
	public record Cell(int i, int j) implements Comparable<Cell> {
		@Override
		public int compareTo(Cell o) {
			return j != o.j ? Integer.compare(j, o.j) : Integer.compare(i, o.i);
		}

		@Override
		public String toString() {
			return i + "," + j;
		}
	}

	public RegularLatLonGrid {
		requireNonNull(gridId, "gridId");
		if (gridId.isBlank()) {
			throw new IllegalArgumentException("gridId is required");
		}
		if (!(resolutionDegrees > 0) || ni < 1 || nj < 1) {
			throw new IllegalArgumentException("a grid needs a positive resolution and at least one cell");
		}
	}

	public boolean contains(int i, int j) {
		return i >= 0 && i < ni && j >= 0 && j < nj;
	}

	/** Row-major index, {@code j * ni + i} — the order a GRIB2 field with scan mode 0x40 stores its points. */
	public int flatIndex(Cell cell) {
		requireNonNull(cell, "cell");
		if (!contains(cell.i(), cell.j())) {
			throw new IllegalArgumentException("cell " + cell + " outside " + gridId);
		}
		return cell.j() * ni + cell.i();
	}

	public double latitude(int j) {
		return la1 + j * resolutionDegrees;
	}

	public double longitude(int i) {
		return lo1 + i * resolutionDegrees;
	}

	/** The cell whose centre is nearest to the position, if the position lies on the grid. */
	public Optional<Cell> cellFor(double latitude, double longitude) {
		double dLon = ((longitude - lo1) % 360 + 360) % 360;
		long i = Math.round(dLon / resolutionDegrees);
		long j = Math.round((latitude - la1) / resolutionDegrees);
		if (i < 0 || i >= ni || j < 0 || j >= nj) {
			return Optional.empty();
		}
		return Optional.of(new Cell((int) i, (int) j));
	}

	public Optional<Cell> cellFor(GeoPosition position) {
		requireNonNull(position, "position");
		return cellFor(position.getLatitude(), position.getLongitude());
	}

	/** {@code "i,j"} as {@link Cell#toString()} writes it and a manual assignment names it. */
	public Optional<Cell> parse(String text) {
		requireNonNull(text, "text");
		String[] parts = text.trim().split("\\s*,\\s*");
		if (parts.length != 2) {
			return Optional.empty();
		}
		try {
			int i = Integer.parseInt(parts[0]);
			int j = Integer.parseInt(parts[1]);
			return contains(i, j) ? Optional.of(new Cell(i, j)) : Optional.empty();
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public GeoPosition center(Cell cell) {
		requireNonNull(cell, "cell");
		GeoPosition p = WeatherFactory.eINSTANCE.createGeoPosition();
		p.setLatitude(latitude(cell.j()));
		p.setLongitude(longitude(cell.i()));
		return p;
	}

	/** A fresh model object for the cell, with centre and resolution. */
	public GridCell gridCell(Cell cell) {
		GridCell c = WeatherFactory.eINSTANCE.createGridCell();
		c.setGridId(gridId);
		c.setI(cell.i());
		c.setJ(cell.j());
		c.setCenter(center(cell));
		c.setResolutionDegrees(resolutionDegrees);
		return c;
	}

	/** The cell a model object names, if it is on this grid. */
	public Optional<Cell> cellOf(GridCell cell) {
		requireNonNull(cell, "cell");
		if (!gridId.equals(cell.getGridId()) || !contains(cell.getI(), cell.getJ())) {
			return Optional.empty();
		}
		return Optional.of(new Cell(cell.getI(), cell.getJ()));
	}

	/** Whether a grid read from a file is this grid, within a tolerance for stored precision. */
	public boolean nearlyEquals(double otherLa1, double otherLo1, double di, double dj, int otherNi, int otherNj,
			double tolerance) {
		return ni == otherNi && nj == otherNj && Math.abs(la1 - otherLa1) <= tolerance && Math.abs(lo1 - otherLo1) <= tolerance
				&& Math.abs(resolutionDegrees - di) <= tolerance && Math.abs(resolutionDegrees - dj) <= tolerance;
	}
}
