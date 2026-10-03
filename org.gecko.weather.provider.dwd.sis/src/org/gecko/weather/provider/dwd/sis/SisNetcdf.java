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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.RegularLatLonGrid.Cell;

import ucar.ma2.Array;
import ucar.ma2.DataType;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFiles;
import ucar.nc2.Variable;

/**
 * Reads a SIS NetCDF file (classic NetCDF-3, CF-1.6) from memory through netCDF-Java and returns
 * only the values at the wanted cells — one small slice per cell, never the whole field (the old
 * implementation's half a million objects per run, F-19). The grid, the variable and the time axis
 * are verified; a file that is not what its name promises is a {@link FetchException}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class SisNetcdf {

	public static final String VARIABLE = "SIS";
	static final String UNIT = "Watt m-2";

	/** The series of the wanted cells; a missing (fill) value is NaN. */
	public record Field(List<Instant> times, Map<Cell, double[]> values) {
		public Field {
			times = List.copyOf(requireNonNull(times, "times"));
			values = Map.copyOf(requireNonNull(values, "values"));
		}

		public double value(Cell cell, int t) {
			double[] series = values.get(cell);
			return series == null ? Double.NaN : series[t];
		}
	}

	private SisNetcdf() {
	}

	public static Field read(byte[] netcdf, Collection<Cell> cells) throws IOException {
		requireNonNull(netcdf, "netcdf");
		requireNonNull(cells, "cells");
		try (NetcdfFile nc = NetcdfFiles.openInMemory("sis.nc", netcdf)) {
			double[] lats = axis(nc, "lat");
			double[] lons = axis(nc, "lon");
			if (!SisGrid.matches(lats, lons)) {
				throw new FetchException("SIS file: " + lats.length + "x" + lons.length + " grid from " + lats[0] + "/" + lons[0]
						+ " is not " + SisGrid.GRID_ID);
			}
			Variable time = require(nc, "time");
			Udunits unit = Udunits.parse(attribute(time, "units"));
			double[] raw = axis(nc, "time");
			List<Instant> times = new ArrayList<>(raw.length);
			for (double v : raw) {
				times.add(unit.instant(v));
			}
			Variable sis = require(nc, VARIABLE);
			if (sis.getRank() != 3 || !"time".equals(sis.getDimension(0).getShortName())
					|| !"lat".equals(sis.getDimension(1).getShortName()) || !"lon".equals(sis.getDimension(2).getShortName())) {
				throw new FetchException("SIS file: variable " + VARIABLE + " is " + sis.getNameAndDimensions() + ", expected (time, lat, lon)");
			}
			String sisUnit = attribute(sis, "units");
			if (!UNIT.equals(sisUnit) && !"W m-2".equals(sisUnit) && !"W/m2".equals(sisUnit)) {
				throw new FetchException("SIS file: unit " + sisUnit + ", expected " + UNIT);
			}
			Attribute fillAttribute = sis.findAttribute("_FillValue");
			double fill = fillAttribute == null ? Double.NaN : fillAttribute.getNumericValue().doubleValue();
			Map<Cell, double[]> values = new LinkedHashMap<>();
			for (Cell cell : cells) {
				Array slice = sis.read(new int[] { 0, cell.j(), cell.i() }, new int[] { raw.length, 1, 1 });
				double[] series = new double[raw.length];
				for (int t = 0; t < series.length; t++) {
					double v = slice.getDouble(t);
					series[t] = v == fill ? Double.NaN : v;
				}
				values.put(cell, series);
			}
			return new Field(times, values);
		} catch (InvalidRangeException e) {
			throw new FetchException("SIS file: cell outside the data: " + e.getMessage(), e);
		}
	}

	private static Variable require(NetcdfFile nc, String name) {
		Variable v = nc.findVariable(name);
		if (v == null) {
			throw new FetchException("SIS file: no variable " + name);
		}
		return v;
	}

	private static double[] axis(NetcdfFile nc, String name) throws IOException {
		Variable v = require(nc, name);
		return (double[]) v.read().get1DJavaArray(DataType.DOUBLE);
	}

	private static String attribute(Variable v, String name) {
		Attribute a = v.findAttribute(name);
		if (a == null || a.getStringValue() == null) {
			throw new FetchException("SIS file: variable " + v.getShortName() + " has no attribute " + name);
		}
		return a.getStringValue();
	}

	/**
	 * A CF/udunits time unit, {@code "<unit> since <date> [<time>]"}. Own parser: the files write
	 * the base date without zero padding ({@code hours since 2026-10-3 00:00:00}).
	 */
	record Udunits(Duration unit, Instant base) {
		private static final Pattern FORMAT = Pattern.compile(
				"^\\s*(seconds?|minutes?|hours?|days?)\\s+since\\s+(\\d{4})-(\\d{1,2})-(\\d{1,2})(?:[ T](\\d{1,2}):(\\d{1,2})(?::(\\d{1,2})(?:\\.\\d+)?)?)?\\s*(?:Z|UTC)?\\s*$",
				Pattern.CASE_INSENSITIVE);

		static Udunits parse(String text) {
			Matcher m = FORMAT.matcher(requireNonNull(text, "text"));
			if (!m.matches()) {
				throw new FetchException("SIS file: time unit '" + text + "' not understood");
			}
			Duration unit = switch (m.group(1).toLowerCase().charAt(0)) {
				case 's' -> Duration.ofSeconds(1);
				case 'm' -> Duration.ofMinutes(1);
				case 'h' -> Duration.ofHours(1);
				default -> Duration.ofDays(1);
			};
			LocalDateTime base = LocalDateTime.of(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)),
					Integer.parseInt(m.group(4)), m.group(5) == null ? 0 : Integer.parseInt(m.group(5)),
					m.group(6) == null ? 0 : Integer.parseInt(m.group(6)), m.group(7) == null ? 0 : Integer.parseInt(m.group(7)));
			return new Udunits(unit, base.toInstant(ZoneOffset.UTC));
		}

		Instant instant(double value) {
			return base.plusMillis(Math.round(value * unit.toMillis()));
		}
	}
}
