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
package org.gecko.weather.provider.dwd.mosmix;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Recorded DWD files under {@code test/fixtures}: the MOSMIX_L KMZ of station 10554 (Erfurt, issue
 * 2024-09-26 09:00 UTC, 247 time steps) and a station catalogue.
 */
final class Fixtures {

	static final String KMZ_10554 = "MOSMIX_L_LATEST_10554.kmz";
	static final String CATALOG = "mosmix_stationskatalog.cfg";

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

	static byte[] bytes(String name) {
		try (InputStream in = open(name)) {
			return in.readAllBytes();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
