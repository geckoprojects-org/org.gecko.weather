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

import static java.util.Objects.requireNonNull;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * A long-lived GET on rtl_433's HTTP line stream ({@code -F http}, path {@code /stream}): every
 * line is one decoded telegram and goes to the consumer as it arrives. The connection is kept
 * alive as long as the stream runs; when it drops, or rtl_433 is not up, the reader reconnects
 * with a backoff that grows from {@code initialBackoff} to {@code maxBackoff} and resets on the
 * first line received. One daemon thread; nothing blocks the caller.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
public final class Rtl433Stream {

	private static final Logger LOG = System.getLogger(Rtl433Stream.class.getName());

	private final HttpClient client;
	private final URI uri;
	private final Consumer<String> lines;
	private final Duration initialBackoff;
	private final Duration maxBackoff;
	private final ScheduledExecutorService executor;

	private volatile boolean running;
	private volatile Flow.Subscription subscription;
	private volatile Duration backoff;
	private volatile boolean failureLogged;
	private final AtomicInteger connections = new AtomicInteger();
	private final AtomicInteger received = new AtomicInteger();

	public Rtl433Stream(URI uri, Consumer<String> lines) {
		this(uri, lines, Duration.ofSeconds(5), Duration.ofMinutes(1));
	}

	public Rtl433Stream(URI uri, Consumer<String> lines, Duration initialBackoff, Duration maxBackoff) {
		this.uri = requireNonNull(uri, "uri");
		this.lines = requireNonNull(lines, "lines");
		this.initialBackoff = requireNonNull(initialBackoff, "initialBackoff");
		this.maxBackoff = requireNonNull(maxBackoff, "maxBackoff");
		this.backoff = initialBackoff;
		this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
			Thread t = new Thread(r, "ecowitt-rtl433");
			t.setDaemon(true);
			return t;
		});
		this.client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10))
				.executor(executor).build();
	}

	public URI uri() {
		return uri;
	}

	/** Connections opened so far, successful or not. */
	public int connections() {
		return connections.get();
	}

	/** Lines handed to the consumer so far. */
	public int received() {
		return received.get();
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		executor.execute(this::connect);
	}

	public synchronized void stop() {
		running = false;
		Flow.Subscription s = subscription;
		if (s != null) {
			s.cancel();
		}
		executor.shutdownNow();
		client.shutdownNow();
	}

	private void connect() {
		if (!running) {
			return;
		}
		connections.incrementAndGet();
		HttpRequest request = HttpRequest.newBuilder(uri).GET().header("Accept", "application/json").build();
		client.sendAsync(request, info -> info.statusCode() == 200
				? BodySubscribers.fromLineSubscriber(new LineSubscriber())
				: BodySubscribers.replacing(null)).whenComplete(this::finished);
	}

	private void finished(HttpResponse<Void> response, Throwable error) {
		if (!running) {
			return;
		}
		if (error != null) {
			Throwable cause = error.getCause() != null ? error.getCause() : error;
			if (!failureLogged) {
				LOG.log(Level.WARNING, "rtl_433 stream at {0} not reachable ({1}); retrying, up to every {2} s", uri,
						cause.toString(), maxBackoff.toSeconds());
				failureLogged = true;
			}
		} else if (response.statusCode() != 200) {
			if (!failureLogged) {
				LOG.log(Level.WARNING, "rtl_433 stream at {0} answers HTTP {1} — is it rtl_433 -F http, path /stream?", uri,
						response.statusCode());
				failureLogged = true;
			}
		} else {
			LOG.log(Level.INFO, "rtl_433 stream at {0} ended; reconnecting in {1} s", uri, backoff.toSeconds());
		}
		subscription = null;
		Duration wait = backoff;
		backoff = min(backoff.multipliedBy(2), maxBackoff);
		try {
			executor.schedule(this::connect, wait.toMillis(), TimeUnit.MILLISECONDS);
		} catch (java.util.concurrent.RejectedExecutionException stopped) {
			// stop() shut the executor down
		}
	}

	private static Duration min(Duration a, Duration b) {
		return a.compareTo(b) <= 0 ? a : b;
	}

	private final class LineSubscriber implements Flow.Subscriber<String> {

		@Override
		public void onSubscribe(Flow.Subscription s) {
			subscription = s;
			if (failureLogged || connections.get() == 1) {
				LOG.log(Level.INFO, "connected to rtl_433 stream at {0}", uri);
			}
			failureLogged = false;
			s.request(Long.MAX_VALUE);
		}

		@Override
		public void onNext(String line) {
			if (line == null || line.isBlank()) {
				return;
			}
			backoff = initialBackoff;
			received.incrementAndGet();
			try {
				lines.accept(line);
			} catch (RuntimeException e) {
				LOG.log(Level.WARNING, "telegram not handled: " + e, e);
			}
		}

		@Override
		public void onError(Throwable throwable) {
			// the response future completes exceptionally; finished() reconnects
		}

		@Override
		public void onComplete() {
			// the response future completes; finished() reconnects
		}
	}

}
