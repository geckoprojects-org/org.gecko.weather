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
package org.gecko.weather.transport;

import static java.util.Objects.requireNonNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Archive unwrapping for the formats DWD Open Data uses. Streams through; nothing is buffered to
 * disk or memory beyond the decompressor's window.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class Unwrap {

	private Unwrap() {
	}

	/**
	 * The first file entry of a zip archive (a KMZ holds exactly one KML). Closing the returned
	 * stream closes the archive.
	 *
	 * @throws IOException if the archive has no file entry
	 */
	public static InputStream zip(InputStream archive) throws IOException {
		ZipInputStream zip = new ZipInputStream(new BufferedInputStream(requireNonNull(archive, "archive")));
		ZipEntry entry;
		while ((entry = zip.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				return zip;
			}
		}
		zip.close();
		throw new IOException("Zip archive contains no file entry");
	}

	/** A gzip stream. */
	public static InputStream gzip(InputStream compressed) throws IOException {
		return new GZIPInputStream(new BufferedInputStream(requireNonNull(compressed, "compressed")));
	}

	/**
	 * Picks the unwrapping by the file name's extension: {@code .kmz}/{@code .zip} → zip,
	 * {@code .gz} → gzip, anything else is returned as is.
	 */
	public static InputStream byName(String fileName, InputStream in) throws IOException {
		String lower = requireNonNull(fileName, "fileName").toLowerCase();
		if (lower.endsWith(".kmz") || lower.endsWith(".zip")) {
			return zip(in);
		}
		if (lower.endsWith(".gz")) {
			return gzip(in);
		}
		return in;
	}

}
