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

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.IntPredicate;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.gecko.weather.grib2.Grib2Writer;
import org.gecko.weather.grib2.Grib2Writer.Spec;
import org.gecko.weather.grib2.Grib2Writer.Statistical;
import org.gecko.weather.provider.dwd.icon.IconParameters.Parameter;

/**
 * ICON-D2-shaped GRIB2 messages for tests, through the shared {@link Grib2Writer}: the parameter's
 * signature, template 0 or 8 (average since the run), time unit hours — the real files use minutes,
 * so both paths get exercised.
 */
final class Grib2TestFiles {

	private Grib2TestFiles() {
	}

	/**
	 * @param value   the value at every present point, an integer 0..65535
	 * @param present which flat indices carry data; null for all
	 */
	static byte[] encode(Parameter parameter, Instant run, int step, int value, IntPredicate present) {
		IconParameters.Signature s = parameter.signature();
		boolean averaged = parameter.averagedSinceStart();
		Spec spec = new Spec(IconD2Grid.GRID, IconD2Grid.SCAN_MODE, run, 0, s.category(), s.number(), s.levelType1(),
				(long) s.levelValue1(), s.levelType2(), (long) s.levelValue2(), 1, averaged ? 0 : step,
				averaged ? Optional.of(new Statistical(run.plus(Duration.ofHours(step)), 0, 1, step)) : Optional.empty());
		return Grib2Writer.write(spec, flat -> present == null || present.test(flat) ? value : -1);
	}

	static byte[] bzip2(byte[] plain) {
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		try (BZip2CompressorOutputStream bz = new BZip2CompressorOutputStream(out)) {
			bz.write(plain);
		} catch (java.io.IOException e) {
			throw new java.io.UncheckedIOException(e);
		}
		return out.toByteArray();
	}
}
