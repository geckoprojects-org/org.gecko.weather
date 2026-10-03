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

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.IntBinaryOperator;

import ucar.ma2.Array;
import ucar.ma2.DataType;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Attribute;
import ucar.nc2.write.NetcdfFileFormat;
import ucar.nc2.write.NetcdfFormatWriter;

/**
 * Writes SIS-shaped NetCDF-3 files for tests through netCDF-Java: the DE grid, CF time axis as the
 * real files have it ({@code seconds since <day> 00:00:00} for an analysis, {@code hours since} for a
 * forecast, day without zero padding as CDO writes it), {@code SIS(time, lat, lon)} with fill −1.
 */
final class SisTestFiles {

	private SisTestFiles() {
	}

	/** One analysis instant; {@code value(i, j)} gives the W/m² of a cell, a negative value marks it missing. */
	static byte[] analysis(Path dir, Instant time, IntBinaryOperator value) {
		LocalDate day = time.atOffset(ZoneOffset.UTC).toLocalDate();
		double seconds = Duration.between(day.atStartOfDay().toInstant(ZoneOffset.UTC), time).toSeconds();
		return write(dir, "seconds since " + day + " 00:00:00", new double[] { seconds }, DataType.SHORT, value);
	}

	/** A forecast run with {@code steps} hourly means from the run hour; {@code value(t, flat)}. */
	static byte[] forecast(Path dir, Instant run, int steps, IntBinaryOperator value) {
		LocalDate day = run.atOffset(ZoneOffset.UTC).toLocalDate();
		double first = Duration.between(day.atStartOfDay().toInstant(ZoneOffset.UTC), run).toHours();
		double[] hours = new double[steps];
		for (int t = 0; t < steps; t++) {
			hours[t] = first + t;
		}
		String units = "hours since " + day.getYear() + "-" + day.getMonthValue() + "-" + day.getDayOfMonth() + " 00:00:00";
		return write(dir, units, hours, DataType.FLOAT, value);
	}

	private static byte[] write(Path dir, String timeUnits, double[] times, DataType type, IntBinaryOperator value) {
		try {
			Path file = Files.createTempFile(dir, "sis", ".nc");
			NetcdfFormatWriter.Builder b = NetcdfFormatWriter.builder().setNewFile(true).setFormat(NetcdfFileFormat.NETCDF3)
					.setLocation(file.toString());
			b.addUnlimitedDimension("time");
			b.addDimension("lat", SisGrid.NJ);
			b.addDimension("lon", SisGrid.NI);
			b.addVariable("time", DataType.DOUBLE, "time").addAttribute(new Attribute("units", timeUnits))
					.addAttribute(new Attribute("calendar", "proleptic_gregorian"));
			b.addVariable("lat", DataType.FLOAT, "lat").addAttribute(new Attribute("units", "degrees"));
			b.addVariable("lon", DataType.FLOAT, "lon").addAttribute(new Attribute("units", "degrees"));
			b.addVariable("SIS", type, "time lat lon").addAttribute(new Attribute("units", "Watt m-2"))
					.addAttribute(new Attribute("_FillValue", type == DataType.SHORT ? Short.valueOf((short) -1) : Float.valueOf(-1f)));
			float[] lats = new float[SisGrid.NJ];
			for (int j = 0; j < lats.length; j++) {
				lats[j] = (float) SisGrid.GRID.latitude(j);
			}
			float[] lons = new float[SisGrid.NI];
			for (int i = 0; i < lons.length; i++) {
				lons[i] = (float) SisGrid.GRID.longitude(i);
			}
			int n = times.length * SisGrid.NJ * SisGrid.NI;
			Object data = type == DataType.SHORT ? new short[n] : new float[n];
			for (int t = 0; t < times.length; t++) {
				for (int flat = 0; flat < SisGrid.NJ * SisGrid.NI; flat++) {
					int v = type == DataType.SHORT ? value.applyAsInt(flat % SisGrid.NI, flat / SisGrid.NI) : value.applyAsInt(t, flat);
					int k = t * SisGrid.NJ * SisGrid.NI + flat;
					if (type == DataType.SHORT) {
						((short[]) data)[k] = (short) v;
					} else {
						((float[]) data)[k] = v;
					}
				}
			}
			try (NetcdfFormatWriter w = b.build()) {
				w.write("time", Array.factory(DataType.DOUBLE, new int[] { times.length }, times));
				w.write("lat", Array.factory(DataType.FLOAT, new int[] { SisGrid.NJ }, lats));
				w.write("lon", Array.factory(DataType.FLOAT, new int[] { SisGrid.NI }, lons));
				w.write("SIS", Array.factory(type, new int[] { times.length, SisGrid.NJ, SisGrid.NI }, data));
			}
			byte[] bytes = Files.readAllBytes(file);
			Files.delete(file);
			return bytes;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InvalidRangeException e) {
			throw new IllegalStateException(e);
		}
	}
}
