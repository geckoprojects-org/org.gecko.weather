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
package org.gecko.weather.provider.ecowitt;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpServer;

/**
 * The line stream client against a server that behaves like rtl_433 -F http: chunked lines, then
 * the connection ends when rtl_433 stops.
 */
class Rtl433StreamTest {

	private HttpServer server;
	private final AtomicInteger requests = new AtomicInteger();
	private final List<String> received = new CopyOnWriteArrayList<>();
	private Rtl433Stream stream;

	@BeforeEach
	void start() throws IOException {
		server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
		server.createContext("/stream", exchange -> {
			requests.incrementAndGet();
			exchange.getResponseHeaders().add("Content-Type", "application/json");
			exchange.sendResponseHeaders(200, 0);
			try (OutputStream out = exchange.getResponseBody()) {
				out.write("{\"model\":\"Fineoffset-WS90\",\"id\":1}\n".getBytes(StandardCharsets.UTF_8));
				out.flush();
				out.write("\n{\"model\":\"Fineoffset-WS90\",\"id\":2}\n".getBytes(StandardCharsets.UTF_8));
			}
		});
		server.createContext("/missing", exchange -> {
			requests.incrementAndGet();
			exchange.sendResponseHeaders(404, -1);
			exchange.close();
		});
		server.start();
	}

	@AfterEach
	void stop() {
		if (stream != null) {
			stream.stop();
		}
		server.stop(0);
	}

	private URI uri(String path) {
		return URI.create("http://127.0.0.1:" + server.getAddress().getPort() + path);
	}

	@Test
	void deliversLinesAndReconnectsWhenTheStreamEnds() throws InterruptedException {
		stream = new Rtl433Stream(uri("/stream"), received::add, Duration.ofMillis(50), Duration.ofMillis(100));
		stream.start();
		await(() -> received.size() >= 2);
		assertThat(received.get(0)).contains("\"id\":1");
		assertThat(received.get(1)).contains("\"id\":2");
		await(() -> requests.get() >= 2);
		await(() -> received.size() >= 4);
	}

	@Test
	void keepsTryingWhenTheServerAnswersWrong() throws InterruptedException {
		stream = new Rtl433Stream(uri("/missing"), received::add, Duration.ofMillis(50), Duration.ofMillis(100));
		stream.start();
		await(() -> requests.get() >= 3);
		assertThat(received).isEmpty();
	}

	@Test
	void keepsTryingWhenNothingListens() throws InterruptedException {
		server.stop(0);
		stream = new Rtl433Stream(uri("/stream"), received::add, Duration.ofMillis(50), Duration.ofMillis(100));
		stream.start();
		await(() -> stream.connections() >= 3);
	}

	@Test
	void stopEndsTheConnectionAttempts() throws InterruptedException {
		stream = new Rtl433Stream(uri("/stream"), received::add, Duration.ofMillis(50), Duration.ofMillis(100));
		stream.start();
		await(() -> received.size() >= 2);
		stream.stop();
		int after = stream.connections();
		Thread.sleep(300);
		assertThat(stream.connections()).isEqualTo(after);
	}

	private static void await(java.util.function.BooleanSupplier condition) throws InterruptedException {
		long deadline = System.nanoTime() + Duration.ofSeconds(5).toNanos();
		while (!condition.getAsBoolean()) {
			if (System.nanoTime() > deadline) {
				throw new AssertionError("condition not met within 5 s");
			}
			Thread.sleep(20);
		}
	}
}
