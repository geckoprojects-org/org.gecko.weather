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

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * One decoded GRIB2 record: what identifies the field, when it is valid, the grid it sits on and
 * the data in file order (row-major, index {@code j * ni + i}); masked points are {@code NaN}. A
 * header-only record (scanned, not decoded) has {@link #NO_DATA}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record Grib2Field(int discipline, int category, int number, int pdsTemplate, int levelType1, double levelValue1,
		int levelType2, double levelValue2, Instant referenceTime, Instant validAt, Optional<Interval> interval,
		GridDefinition grid, float[] data) {

	/** A statistically processed field: the interval and code table 4.10 process (0 = average). */
	public record Interval(Instant start, Instant end, int statisticalProcess) {
		public Interval {
			requireNonNull(start, "start");
			requireNonNull(end, "end");
		}
	}

	/** Grid definition template 0 facts; longitudes in [-180, 180). */
	public record GridDefinition(int template, double la1, double lo1, double di, double dj, int ni, int nj, int scanMode) {
		public boolean nearlyEquals(GridDefinition other, double tolerance) {
			return template == other.template && ni == other.ni && nj == other.nj && scanMode == other.scanMode
					&& Math.abs(la1 - other.la1) <= tolerance && Math.abs(lo1 - other.lo1) <= tolerance
					&& Math.abs(di - other.di) <= tolerance && Math.abs(dj - other.dj) <= tolerance;
		}
	}

	/** Marker of a record whose data was not decoded. */
	public static final float[] NO_DATA = new float[0];

	public Grib2Field {
		requireNonNull(referenceTime, "referenceTime");
		requireNonNull(validAt, "validAt");
		requireNonNull(interval, "interval");
		requireNonNull(grid, "grid");
		requireNonNull(data, "data");
		if (data.length != 0 && data.length != grid.ni() * grid.nj()) {
			throw new IllegalArgumentException("data has " + data.length + " points, grid " + grid.ni() * grid.nj());
		}
	}

	public boolean hasData() {
		return data.length != 0;
	}

	/** The same record with its data. */
	public Grib2Field withData(float[] values) {
		return new Grib2Field(discipline, category, number, pdsTemplate, levelType1, levelValue1, levelType2, levelValue2,
				referenceTime, validAt, interval, grid, values);
	}

	/**
	 * The value at column {@code i} (longitude index) and row {@code j} (latitude index); NaN when masked.
	 *
	 * @throws IllegalStateException on a header-only record
	 */
	public float valueAt(int i, int j) {
		if (!hasData()) {
			throw new IllegalStateException("record was scanned without its data");
		}
		if (i < 0 || i >= grid.ni() || j < 0 || j >= grid.nj()) {
			throw new IndexOutOfBoundsException("cell (" + i + "," + j + ") outside " + grid.ni() + "x" + grid.nj());
		}
		return data[j * grid.ni() + i];
	}

	public Duration leadTime() {
		return Duration.between(referenceTime, validAt);
	}
}
