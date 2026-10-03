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

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gecko.weather.transport.Unwrap;

/**
 * Recorded ICON-D2 files under {@code test/fixtures}: run 2026-10-03 00 UTC, step +3 h — total cloud
 * cover (instantaneous, template 0, one record, 807 KB) and direct radiation (averaged, template 8,
 * four quarter-hour records; at night, so all zeros and 3 KB). Daytime radiation files are 4.5 MB each and are not recorded; the de-averaging
 * arithmetic is tested on synthetic fields instead.
 */
final class Fixtures {

	static final String CLCT_003 = "icon-d2_germany_regular-lat-lon_single-level_2026100300_003_2d_clct.grib2.bz2";
	static final String ASWDIR_S_003 = "icon-d2_germany_regular-lat-lon_single-level_2026100300_003_2d_aswdir_s.grib2.bz2";

	private Fixtures() {
	}

	static InputStream open(String name) {
		InputStream in = Fixtures.class.getClassLoader().getResourceAsStream("fixtures/" + name);
		if (in != null) {
			return in;
		}
		try {
			return Files.newInputStream(Path.of("test", "fixtures", name));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/** The decompressed GRIB2 message. */
	static byte[] grib2(String name) {
		try (InputStream in = Unwrap.bzip2(open(name))) {
			return in.readAllBytes();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
