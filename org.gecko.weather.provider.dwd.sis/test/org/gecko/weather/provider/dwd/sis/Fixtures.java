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
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gecko.weather.transport.Unwrap;

/**
 * Recorded SIS files under {@code test/fixtures}: the 12:00 UTC analysis of 2026-10-03 (100 KB,
 * daylight) and the 18 UTC forecast run of the same day (3.5 MB, bzip2 to 1.3 MB).
 */
final class Fixtures {

	static final String ANALYSIS_1200 = "SISin202610031200DEv3.nc";
	static final String FORECAST_18 = "SISfc2026100318_fc+18h-DE.nc.bz2";

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

	/** The NetCDF bytes, unwrapped by name. */
	static byte[] bytes(String name) {
		try (InputStream in = Unwrap.byName(name, open(name))) {
			return in.readAllBytes();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
