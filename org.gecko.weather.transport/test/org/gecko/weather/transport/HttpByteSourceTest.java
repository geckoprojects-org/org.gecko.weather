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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import org.gecko.weather.api.spi.SourceState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpServer;

/**
 * Against an in-process JDK HTTP server: conditional headers out, 304 in, validators round-trip.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class HttpByteSourceTest {

	private static final String ETAG = "\"abc-1\"";
	private static final String LAST_MODIFIED = "Sat, 03 Oct 2026 06:00:00 GMT"; // two-digit day, as HTTP wants it

	private HttpServer server;
	private URI uri;
	private final AtomicReference<String> seenIfNoneMatch = new AtomicReference<>();
	private final AtomicReference<String> seenIfModifiedSince = new AtomicReference<>();

	@BeforeEach
	void start() throws IOException {
		server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
		server.createContext("/data.txt", exchange -> {
			seenIfNoneMatch.set(exchange.getRequestHeaders().getFirst("If-None-Match"));
			seenIfModifiedSince.set(exchange.getRequestHeaders().getFirst("If-Modified-Since"));
			if (ETAG.equals(seenIfNoneMatch.get())) {
				exchange.sendResponseHeaders(304, -1);
				exchange.close();
				return;
			}
			byte[] body = "hello".getBytes(StandardCharsets.UTF_8);
			exchange.getResponseHeaders().add("ETag", ETAG);
			exchange.getResponseHeaders().add("Last-Modified", LAST_MODIFIED);
			exchange.sendResponseHeaders(200, body.length);
			exchange.getResponseBody().write(body);
			exchange.close();
		});
		server.createContext("/missing", exchange -> {
			exchange.sendResponseHeaders(404, -1);
			exchange.close();
		});
		server.start();
		uri = URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/data.txt");
	}

	@AfterEach
	void stop() {
		server.stop(0);
	}

	@Test
	void plainFetchReturnsContentAndValidators() throws IOException {
		HttpByteSource source = new HttpByteSource();
		ByteSource.Result result = source.fetch(uri, Optional.empty());
		assertThat(result).isInstanceOf(ByteSource.Content.class);
		try (ByteSource.Content content = (ByteSource.Content) result) {
			assertThat(new String(content.data().readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("hello");
			assertThat(content.validators().etag()).contains(ETAG);
			assertThat(content.validators().lastModified()).contains(Instant.parse("2026-10-03T06:00:00Z"));
		}
		assertThat(seenIfNoneMatch.get()).isNull();
	}

	@Test
	void conditionalFetchSendsValidatorsAndUnderstands304() throws IOException {
		HttpByteSource source = new HttpByteSource();
		SourceState.Entity validators = new SourceState.Entity(Optional.of(ETAG), Optional.of(Instant.parse("2026-10-03T06:00:00Z")));
		ByteSource.Result result = source.fetch(uri, Optional.of(validators));
		assertThat(result).isInstanceOf(ByteSource.Unchanged.class);
		assertThat(seenIfNoneMatch.get()).isEqualTo(ETAG);
		assertThat(seenIfModifiedSince.get()).isEqualTo(LAST_MODIFIED);
	}

	@Test
	void notFoundIsItsOwnIOException() {
		HttpByteSource source = new HttpByteSource();
		URI missing = URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/missing");
		assertThatThrownBy(() -> source.fetch(missing, Optional.empty())).isInstanceOf(ByteSource.NotFoundException.class)
				.isInstanceOf(IOException.class).hasMessageContaining("404");
	}

	@Test
	void unwrapBzip2() throws IOException {
		ByteArrayOutputStream bz = new ByteArrayOutputStream();
		try (BZip2CompressorOutputStream out = new BZip2CompressorOutputStream(bz)) {
			out.write("GRIB".getBytes(StandardCharsets.UTF_8));
		}
		try (var in = Unwrap.byName("x.grib2.bz2", new ByteArrayInputStream(bz.toByteArray()))) {
			assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("GRIB");
		}
	}

	@Test
	void unwrapZipAndGzip() throws IOException {
		ByteArrayOutputStream zipBytes = new ByteArrayOutputStream();
		try (ZipOutputStream zip = new ZipOutputStream(zipBytes)) {
			zip.putNextEntry(new ZipEntry("folder/"));
			zip.closeEntry();
			zip.putNextEntry(new ZipEntry("folder/MOSMIX.kml"));
			zip.write("<kml/>".getBytes(StandardCharsets.UTF_8));
			zip.closeEntry();
		}
		try (var in = Unwrap.byName("x.kmz", new ByteArrayInputStream(zipBytes.toByteArray()))) {
			assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("<kml/>");
		}

		ByteArrayOutputStream gzBytes = new ByteArrayOutputStream();
		try (GZIPOutputStream gz = new GZIPOutputStream(gzBytes)) {
			gz.write("plain".getBytes(StandardCharsets.UTF_8));
		}
		try (var in = Unwrap.byName("x.txt.gz", new ByteArrayInputStream(gzBytes.toByteArray()))) {
			assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("plain");
		}
		try (var in = Unwrap.byName("x.txt", new ByteArrayInputStream("raw".getBytes(StandardCharsets.UTF_8)))) {
			assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("raw");
		}
		assertThatThrownBy(() -> Unwrap.zip(new ByteArrayInputStream(new byte[0]))).isInstanceOf(IOException.class);
	}

}
