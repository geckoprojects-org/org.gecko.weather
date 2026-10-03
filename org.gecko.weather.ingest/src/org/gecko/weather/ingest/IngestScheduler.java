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
package org.gecko.weather.ingest;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.gecko.weather.api.IngestControl;

/**
 * Runs {@link ProviderJob}s on one daemon thread, each rescheduling itself by its own
 * {@link ProviderJob#nextDelay()}. One thread is deliberate: providers share the network and the
 * repository, and a slow source must not fan out into parallel downloads.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class IngestScheduler implements IngestControl {

	private final ScheduledExecutorService executor;
	private final Map<String, Scheduled> jobs = new ConcurrentHashMap<>();

	public IngestScheduler() {
		this(Executors.newSingleThreadScheduledExecutor(r -> {
			Thread t = new Thread(r, "weather-ingest");
			t.setDaemon(true);
			return t;
		}));
	}

	public IngestScheduler(ScheduledExecutorService executor) {
		this.executor = requireNonNull(executor, "executor");
	}

	/** Adds a job and schedules its first run after its initial delay. Replaces a job with the same key. */
	public void add(ProviderJob job) {
		requireNonNull(job, "job");
		String key = key(job.providerId(), job.productId());
		Scheduled previous = jobs.put(key, new Scheduled(job));
		if (previous != null) {
			previous.cancel();
		}
		schedule(key);
	}

	public void remove(String providerId, String productId) {
		Scheduled s = jobs.remove(key(providerId, productId));
		if (s != null) {
			s.cancel();
		}
	}

	public void shutdown() {
		jobs.values().forEach(Scheduled::cancel);
		jobs.clear();
		executor.shutdownNow();
	}

	@Override
	public List<IngestStatus> status() {
		List<IngestStatus> list = new ArrayList<>();
		jobs.values().forEach(s -> list.add(s.job.status()));
		list.sort(Comparator.comparing(IngestStatus::providerId).thenComparing(IngestStatus::productId));
		return list;
	}

	@Override
	public IngestStatus runNow(String providerId, String productId) {
		String key = key(providerId, productId);
		Scheduled s = jobs.get(key);
		if (s == null) {
			throw new IllegalArgumentException("No ingest job for " + providerId + "/" + productId);
		}
		s.cancel();
		s.job.resetBackoff();
		s.job.run();
		schedule(key);
		return s.job.status();
	}

	private void schedule(String key) {
		Scheduled s = jobs.get(key);
		if (s == null || executor.isShutdown()) {
			return;
		}
		Duration delay = s.job.nextDelay();
		s.future = executor.schedule(() -> {
			Scheduled current = jobs.get(key);
			if (current != s) {
				return; // replaced or removed meanwhile
			}
			s.job.run();
			schedule(key);
		}, delay.toMillis(), TimeUnit.MILLISECONDS);
	}

	static String key(String providerId, String productId) {
		return providerId + "/" + productId;
	}

	private static final class Scheduled {
		final ProviderJob job;
		volatile ScheduledFuture<?> future;

		Scheduled(ProviderJob job) {
			this.job = job;
		}

		void cancel() {
			ScheduledFuture<?> f = future;
			if (f != null) {
				f.cancel(false);
			}
		}
	}

}
