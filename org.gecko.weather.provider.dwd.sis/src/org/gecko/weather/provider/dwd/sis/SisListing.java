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
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.transport.ByteSource;

/**
 * The directory listing of DWD's {@code satellite/radiation/sis/} folder: which analyses and
 * forecast runs exist right now. One small HTML page answers what otherwise would take a probe per
 * candidate file — the folder keeps about ten hours of 15-minute analyses and ten hourly runs.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class SisListing {

	private static final Pattern HREF = Pattern.compile("href=\"([^\"?#]+\\.nc)\"");
	/** {@code SISin202610031800DEv3.nc} — 15-minute analysis for Germany, version 3. */
	static final Pattern ANALYSIS = Pattern.compile("^SISin(\\d{12})DEv3\\.nc$");
	/** {@code SISfc2026100318_fc%2B18h-DE.nc} — hourly run, +18 h, Germany; the plus is URL-encoded in the listing. */
	static final Pattern FORECAST = Pattern.compile("^SISfc(\\d{10})_fc(?:%2B|\\+)18h-DE\\.nc$");
	private static final DateTimeFormatter MINUTES = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
	private static final DateTimeFormatter HOURS = DateTimeFormatter.ofPattern("yyyyMMddHH");

	private SisListing() {
	}

	/** Fetches the listing; always unconditionally, it is small and has no validators worth keeping. */
	public static String read(ByteSource source, URI folder) throws IOException {
		ByteSource.Result result = source.fetch(requireNonNull(folder, "folder"), Optional.empty());
		if (!(result instanceof ByteSource.Content content)) {
			throw new FetchException("listing " + folder + " answered 'unchanged' to an unconditional request");
		}
		try (content; InputStream in = content.data()) {
			return new String(in.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	/** Every {@code .nc} file name the page links to, in page order. */
	public static List<String> names(String html) {
		List<String> names = new ArrayList<>();
		Matcher m = HREF.matcher(requireNonNull(html, "html"));
		while (m.find()) {
			String href = m.group(1);
			names.add(href.substring(href.lastIndexOf('/') + 1));
		}
		return names;
	}

	/** The analyses on the page, valid time → file name, oldest first. */
	public static SortedMap<Instant, String> analyses(String html) {
		SortedMap<Instant, String> out = new TreeMap<>();
		for (String name : names(html)) {
			Matcher m = ANALYSIS.matcher(name);
			if (m.matches()) {
				out.put(LocalDateTime.parse(m.group(1), MINUTES).toInstant(ZoneOffset.UTC), name);
			}
		}
		return out;
	}

	/** The forecast runs on the page, run time → file name, oldest first. */
	public static SortedMap<Instant, String> forecasts(String html) {
		SortedMap<Instant, String> out = new TreeMap<>();
		for (String name : names(html)) {
			Matcher m = FORECAST.matcher(name);
			if (m.matches()) {
				out.put(LocalDateTime.parse(m.group(1) + "00", MINUTES).toInstant(ZoneOffset.UTC), name);
			}
		}
		return out;
	}

	/** The file's URI under the folder; the name is taken as the listing wrote it (already URL-encoded). */
	public static URI uri(URI folder, String name) {
		return folder.resolve(name);
	}

	static String hours(Instant run) {
		return HOURS.withZone(ZoneOffset.UTC).format(run);
	}
}
