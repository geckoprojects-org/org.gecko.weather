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
package org.gecko.weather.grib2;

import static java.util.Objects.requireNonNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.IntUnaryOperator;

import org.gecko.weather.api.spi.RegularLatLonGrid;

/**
 * Writes minimal but valid GRIB2 messages on a {@link RegularLatLonGrid}, for tests and tools: grid
 * definition template 0, product definition template 0 or 8 (statistically processed), data
 * representation template 0 with 16-bit integers and {@code R = 0, E = 0, D = 0} — the value is the
 * raw integer — plus a bitmap when a value is marked missing. Reading such a message back through
 * {@link Grib2Reader} cross-checks both against the recorded DWD files.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class Grib2Writer {

	/** Code table 4.5: no second surface. */
	public static final int NO_LEVEL = 255;

	/** Template 8 facts: the interval ends here, processed how, over which range. */
	public record Statistical(Instant intervalEnd, int statisticalProcess, int rangeUnit, int rangeLength) {
		public Statistical {
			requireNonNull(intervalEnd, "intervalEnd");
		}
	}

	/**
	 * What the sections say.
	 *
	 * @param timeUnit     code table 4.4 (0 minutes, 1 hours, 2 days)
	 * @param forecastTime in that unit
	 * @param statistical  present for product definition template 8, empty for template 0
	 */
	public record Spec(RegularLatLonGrid grid, int scanMode, Instant reference, int discipline, int category, int number,
			int levelType1, long levelValue1, int levelType2, long levelValue2, int timeUnit, int forecastTime,
			Optional<Statistical> statistical) {
		public Spec {
			requireNonNull(grid, "grid");
			requireNonNull(reference, "reference");
			requireNonNull(statistical, "statistical");
		}
	}

	private Grib2Writer() {
	}

	/**
	 * @param valueAt the value at a flat (row-major) index, 0..65535; a negative value marks the point
	 *                missing and puts a bitmap into the message
	 */
	public static byte[] write(Spec spec, IntUnaryOperator valueAt) {
		requireNonNull(spec, "spec");
		requireNonNull(valueAt, "valueAt");
		RegularLatLonGrid grid = spec.grid();
		int npts = grid.ni() * grid.nj();
		int[] values = new int[npts];
		boolean bitmap = false;
		int ndata = 0;
		for (int k = 0; k < npts; k++) {
			values[k] = valueAt.applyAsInt(k);
			if (values[k] < 0) {
				bitmap = true;
			} else {
				ndata++;
			}
		}
		try {
			ByteArrayOutputStream body = new ByteArrayOutputStream(2 * npts + 256);
			DataOutputStream out = new DataOutputStream(body);
			// section 1 — identification
			LocalDateTime ref = LocalDateTime.ofInstant(spec.reference(), ZoneOffset.UTC);
			out.writeInt(21);
			out.writeByte(1);
			out.writeShort(78); // DWD
			out.writeShort(255);
			out.writeByte(19);
			out.writeByte(1);
			out.writeByte(1); // start of forecast
			writeTime(out, ref);
			out.writeByte(0); // operational
			out.writeByte(1); // forecast products
			// section 3 — grid definition template 0
			out.writeInt(72);
			out.writeByte(3);
			out.writeByte(0);
			out.writeInt(npts);
			out.writeByte(0);
			out.writeByte(0);
			out.writeShort(0);
			out.writeByte(6); // shape of the earth: sphere 6371229 m
			out.writeByte(0);
			out.writeInt(0);
			out.writeByte(0);
			out.writeInt(0);
			out.writeByte(0);
			out.writeInt(0);
			out.writeInt(grid.ni());
			out.writeInt(grid.nj());
			out.writeInt(0); // basic angle
			out.writeInt(0xFFFFFFFF); // subdivisions: missing
			out.writeInt(micro(grid.la1()));
			out.writeInt(micro((grid.lo1() + 360) % 360));
			out.writeByte(0x30); // i and j increments given
			out.writeInt(micro(grid.latitude(grid.nj() - 1)));
			out.writeInt(micro((grid.longitude(grid.ni() - 1) + 360) % 360));
			out.writeInt(micro(grid.resolutionDegrees()));
			out.writeInt(micro(grid.resolutionDegrees()));
			out.writeByte(spec.scanMode());
			// section 4 — product definition template 0 or 8
			boolean processed = spec.statistical().isPresent();
			out.writeInt(processed ? 58 : 34);
			out.writeByte(4);
			out.writeShort(0);
			out.writeShort(processed ? 8 : 0);
			out.writeByte(spec.category());
			out.writeByte(spec.number());
			out.writeByte(2); // generating process: forecast
			out.writeByte(0);
			out.writeByte(11);
			out.writeShort(0);
			out.writeByte(0);
			out.writeByte(spec.timeUnit());
			out.writeInt(spec.forecastTime());
			out.writeByte(spec.levelType1());
			out.writeByte(0);
			out.writeInt((int) spec.levelValue1());
			out.writeByte(spec.levelType2());
			out.writeByte(spec.levelType2() == NO_LEVEL ? 0xFF : 0);
			out.writeInt(spec.levelType2() == NO_LEVEL ? 0xFFFFFFFF : (int) spec.levelValue2());
			if (processed) {
				Statistical st = spec.statistical().get();
				writeTime(out, LocalDateTime.ofInstant(st.intervalEnd(), ZoneOffset.UTC));
				out.writeByte(1); // one time range
				out.writeInt(0); // no missing values
				out.writeByte(st.statisticalProcess());
				out.writeByte(2); // increment type
				out.writeByte(st.rangeUnit());
				out.writeInt(st.rangeLength());
				out.writeByte(255);
				out.writeInt(0);
			}
			// section 5 — data representation template 0, 16 bit integers
			out.writeInt(21);
			out.writeByte(5);
			out.writeInt(ndata);
			out.writeShort(0);
			out.writeFloat(0f);
			out.writeShort(0);
			out.writeShort(0);
			out.writeByte(16);
			out.writeByte(0);
			// section 6 — bitmap
			if (!bitmap) {
				out.writeInt(6);
				out.writeByte(6);
				out.writeByte(255);
			} else {
				int bytes = (npts + 7) / 8;
				out.writeInt(6 + bytes);
				out.writeByte(6);
				out.writeByte(0);
				for (int b = 0; b < bytes; b++) {
					int bits = 0;
					for (int bit = 0; bit < 8; bit++) {
						int k = b * 8 + bit;
						if (k < npts && values[k] >= 0) {
							bits |= 0x80 >> bit;
						}
					}
					out.writeByte(bits);
				}
			}
			// section 7 — data
			out.writeInt(5 + 2 * ndata);
			out.writeByte(7);
			for (int k = 0; k < npts; k++) {
				if (values[k] >= 0) {
					out.writeShort(values[k]);
				}
			}
			out.flush();
			byte[] sections = body.toByteArray();
			// sections 0 and 8 around it
			ByteArrayOutputStream message = new ByteArrayOutputStream(sections.length + 20);
			DataOutputStream head = new DataOutputStream(message);
			head.writeBytes("GRIB");
			head.writeShort(0);
			head.writeByte(spec.discipline());
			head.writeByte(2);
			head.writeLong(16L + sections.length + 4);
			head.write(sections);
			head.writeBytes("7777");
			head.flush();
			return message.toByteArray();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/** Several messages in one file, as DWD's multi-record files are. */
	public static byte[] concat(byte[]... messages) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (byte[] m : messages) {
			out.writeBytes(m);
		}
		return out.toByteArray();
	}

	private static int micro(double degrees) {
		return (int) Math.round(degrees * 1_000_000);
	}

	private static void writeTime(DataOutputStream out, LocalDateTime t) throws IOException {
		out.writeShort(t.getYear());
		out.writeByte(t.getMonthValue());
		out.writeByte(t.getDayOfMonth());
		out.writeByte(t.getHour());
		out.writeByte(t.getMinute());
		out.writeByte(t.getSecond());
	}
}
