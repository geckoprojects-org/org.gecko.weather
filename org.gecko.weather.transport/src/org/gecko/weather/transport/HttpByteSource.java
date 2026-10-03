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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

import org.gecko.weather.api.spi.SourceState;

/**
 * {@link ByteSource} over {@link HttpClient}: sends {@code If-None-Match} / {@code If-Modified-Since}
 * when validators are known, treats 304 as {@link Unchanged}, returns {@code ETag} and
 * {@code Last-Modified} of a 200 as the next validators. Follows redirects; anything else than 200
 * or 304 is an {@link IOException} with the status in the message.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class HttpByteSource implements ByteSource {

	private static final String USER_AGENT = "gecko-weather/1.0 (+https://github.com/geckoprojects-org/org.gecko.weather)";

	/**
	 * HTTP dates (RFC 7231) have a two-digit day; {@link DateTimeFormatter#RFC_1123_DATE_TIME} formats
	 * {@code 3 Oct}, which some servers reject. Parsing accepts both, so that one stays for reading.
	 */
	private static final DateTimeFormatter HTTP_DATE = DateTimeFormatter
			.ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH).withZone(ZoneOffset.UTC);

	private final HttpClient client;
	private final Duration requestTimeout;

	/** Default client: 20 s connect, 120 s per request, redirects followed. */
	public HttpByteSource() {
		this(HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).connectTimeout(Duration.ofSeconds(20))
				.build(), Duration.ofSeconds(120));
	}

	public HttpByteSource(HttpClient client, Duration requestTimeout) {
		this.client = requireNonNull(client, "client");
		this.requestTimeout = requireNonNull(requestTimeout, "requestTimeout");
	}

	@Override
	public Result fetch(URI uri, Optional<SourceState.Entity> validators) throws IOException {
		requireNonNull(uri, "uri");
		HttpRequest.Builder request = HttpRequest.newBuilder(uri).GET().timeout(requestTimeout)
				.header("User-Agent", USER_AGENT).header("Accept-Encoding", "identity");
		validators.flatMap(SourceState.Entity::etag).ifPresent(etag -> request.header("If-None-Match", etag));
		validators.flatMap(SourceState.Entity::lastModified)
				.ifPresent(lm -> request.header("If-Modified-Since", HTTP_DATE.format(lm)));
		HttpResponse<InputStream> response;
		try {
			response = client.send(request.build(), HttpResponse.BodyHandlers.ofInputStream());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new IOException("Interrupted while fetching " + uri, e);
		}
		int status = response.statusCode();
		if (status == 304) {
			response.body().close();
			return new Unchanged();
		}
		if (status != 200) {
			response.body().close();
			throw new IOException("HTTP " + status + " for " + uri);
		}
		Optional<String> etag = response.headers().firstValue("ETag");
		Optional<Instant> lastModified = response.headers().firstValue("Last-Modified").flatMap(HttpByteSource::parseHttpDate);
		return new Content(response.body(), new SourceState.Entity(etag, lastModified));
	}

	static Optional<Instant> parseHttpDate(String value) {
		try {
			return Optional.of(Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(value.strip())));
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}

}
