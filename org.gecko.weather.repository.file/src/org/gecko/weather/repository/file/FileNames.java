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
package org.gecko.weather.repository.file;

import static java.util.Objects.requireNonNull;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Turns identifiers and instants into file names that are safe on every file system and back.
 * <p>
 * Identifiers keep {@code A–Z a–z 0–9 . _ -}; every other byte of the UTF-8 form is percent-encoded,
 * so {@code Berlin/Mitte} becomes {@code Berlin%2FMitte} and round-trips. Instants become
 * {@code 20261003T060000Z} — no colons, sortable as text, UTC.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
final class FileNames {

	static final String XMI = ".xmi";

	private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'")
			.withZone(ZoneOffset.UTC);

	private FileNames() {
	}

	static String encode(String id) {
		requireNonNull(id, "id");
		if (id.isEmpty()) {
			throw new IllegalArgumentException("empty identifier");
		}
		StringBuilder sb = new StringBuilder(id.length());
		for (byte b : id.getBytes(StandardCharsets.UTF_8)) {
			int c = b & 0xff;
			if (isSafe(c)) {
				sb.append((char) c);
			} else {
				sb.append('%').append(Character.toUpperCase(Character.forDigit(c >> 4, 16)))
						.append(Character.toUpperCase(Character.forDigit(c & 0xf, 16)));
			}
		}
		// "." and ".." are not file names; a leading dot would hide the file on Unix
		if (sb.charAt(0) == '.') {
			sb.replace(0, 1, "%2E");
		}
		return sb.toString();
	}

	static String decode(String fileName) {
		requireNonNull(fileName, "fileName");
		byte[] out = new byte[fileName.length()];
		int n = 0;
		for (int i = 0; i < fileName.length(); i++) {
			char c = fileName.charAt(i);
			if (c == '%' && i + 2 < fileName.length() + 0 && i + 2 <= fileName.length() - 1) {
				out[n++] = (byte) Integer.parseInt(fileName.substring(i + 1, i + 3), 16);
				i += 2;
			} else {
				out[n++] = (byte) c;
			}
		}
		return new String(out, 0, n, StandardCharsets.UTF_8);
	}

	/** File name (without extension) of an id. */
	static String of(String id) {
		return encode(id);
	}

	/** The id of a file name, extension stripped. */
	static String idOf(String fileName) {
		String base = fileName.endsWith(XMI) ? fileName.substring(0, fileName.length() - XMI.length()) : fileName;
		return decode(base);
	}

	static String stamp(Instant instant) {
		return STAMP.format(requireNonNull(instant, "instant").truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
	}

	/** Parses the stamp at the start of an archive file name; empty for anything that is not one. */
	static Optional<Instant> stampOf(String fileName) {
		if (!fileName.endsWith(XMI) || fileName.length() < 16 + XMI.length()) {
			return Optional.empty();
		}
		try {
			return Optional.of(Instant.from(STAMP.parse(fileName.substring(0, 16))));
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}

	private static boolean isSafe(int c) {
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.' || c == '_'
				|| c == '-';
	}

}
