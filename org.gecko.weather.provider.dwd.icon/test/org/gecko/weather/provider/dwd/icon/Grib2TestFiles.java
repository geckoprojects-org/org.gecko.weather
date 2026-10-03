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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.IntPredicate;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.gecko.weather.provider.dwd.icon.IconParameters.Parameter;

/**
 * Writes minimal but valid GRIB2 messages on the ICON-D2 grid for tests: grid definition template 0,
 * product definition template 0 or 8 (average since the run), data representation template 0 with
 * 16-bit integers (R = 0, E = 0, D = 0, so the value is the raw integer), an optional bitmap. Reading
 * them back through netCDF-Java cross-checks the writer against the recorded DWD files.
 */
final class Grib2TestFiles {

	private Grib2TestFiles() {
	}

	/**
	 * @param parameter which product definition to write (category, number, levels, template)
	 * @param run       reference time
	 * @param step      forecast hours; for an averaged parameter the interval is [run, run + step]
	 * @param value     the value at every present point, an integer 0..65535
	 * @param present   which flat indices carry data; null for all
	 */
	static byte[] encode(Parameter parameter, Instant run, int step, int value, IntPredicate present) {
		int npts = IconD2Grid.NI * IconD2Grid.NJ;
		try {
			ByteArrayOutputStream body = new ByteArrayOutputStream(2 * npts + 256);
			DataOutputStream out = new DataOutputStream(body);
			// section 1 — identification
			LocalDateTime ref = LocalDateTime.ofInstant(run, ZoneOffset.UTC);
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
			out.writeInt(IconD2Grid.NI);
			out.writeInt(IconD2Grid.NJ);
			out.writeInt(0); // basic angle
			out.writeInt(0xFFFFFFFF); // subdivisions: missing
			out.writeInt(micro(IconD2Grid.LA1));
			out.writeInt(micro(IconD2Grid.LO1 + 360));
			out.writeByte(0x30); // i and j increments given
			out.writeInt(micro(IconD2Grid.LA1 + (IconD2Grid.NJ - 1) * IconD2Grid.RESOLUTION_DEGREES));
			out.writeInt(micro(IconD2Grid.LO1 + (IconD2Grid.NI - 1) * IconD2Grid.RESOLUTION_DEGREES));
			out.writeInt(micro(IconD2Grid.RESOLUTION_DEGREES));
			out.writeInt(micro(IconD2Grid.RESOLUTION_DEGREES));
			out.writeByte(IconD2Grid.SCAN_MODE);
			// section 4 — product definition template 0 or 8
			IconParameters.Signature s = parameter.signature();
			boolean averaged = parameter.averagedSinceStart();
			out.writeInt(averaged ? 58 : 34);
			out.writeByte(4);
			out.writeShort(0);
			out.writeShort(averaged ? 8 : 0);
			out.writeByte(s.category());
			out.writeByte(s.number());
			out.writeByte(2); // generating process: forecast
			out.writeByte(0);
			out.writeByte(11);
			out.writeShort(0);
			out.writeByte(0);
			out.writeByte(1); // time unit: hours (the real files use minutes — both paths get exercised)
			out.writeInt(averaged ? 0 : step);
			out.writeByte(s.levelType1());
			out.writeByte(0);
			out.writeInt((int) s.levelValue1());
			out.writeByte(s.levelType2());
			out.writeByte(s.levelType2() == IconParameters.NO_LEVEL ? 0xFF : 0);
			out.writeInt(s.levelType2() == IconParameters.NO_LEVEL ? 0xFFFFFFFF : (int) s.levelValue2());
			if (averaged) {
				writeTime(out, ref.plus(Duration.ofHours(step)));
				out.writeByte(1); // one time range
				out.writeInt(0); // no missing values
				out.writeByte(0); // statistical process: average
				out.writeByte(2); // increment type
				out.writeByte(1); // range unit: hours
				out.writeInt(step);
				out.writeByte(255);
				out.writeInt(0);
			}
			// section 5 — data representation template 0, 16 bit integers
			int ndata = 0;
			for (int k = 0; k < npts; k++) {
				if (present == null || present.test(k)) {
					ndata++;
				}
			}
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
			if (present == null) {
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
						if (k < npts && present.test(k)) {
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
				if (present == null || present.test(k)) {
					out.writeShort(value);
				}
			}
			out.flush();
			byte[] sections = body.toByteArray();
			// section 0 and 8 around it
			ByteArrayOutputStream message = new ByteArrayOutputStream(sections.length + 20);
			DataOutputStream head = new DataOutputStream(message);
			head.writeBytes("GRIB");
			head.writeShort(0);
			head.writeByte(0); // discipline: meteorological
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

	static byte[] bzip2(byte[] plain) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (BZip2CompressorOutputStream bz = new BZip2CompressorOutputStream(out)) {
			bz.write(plain);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
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
