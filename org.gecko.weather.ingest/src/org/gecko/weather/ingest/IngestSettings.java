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

/**
 * How the runtime paces itself. Conditional requests make a poll that finds nothing new cost one
 * round trip, so polling more often than a product refreshes is the right default; the product's
 * {@code expectedRefresh} stays what staleness is judged against.
 *
 * @param pollInterval   time between polls of a product when all is well
 * @param initialDelay   time before the first poll after a provider appears
 * @param backoffInitial first wait after a transport failure; doubles per consecutive failure
 * @param backoffMax     the longest wait after transport failures
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record IngestSettings(Duration pollInterval, Duration initialDelay, Duration backoffInitial, Duration backoffMax) {

	public static final IngestSettings DEFAULTS = new IngestSettings(Duration.ofMinutes(20), Duration.ofSeconds(10),
			Duration.ofMinutes(1), Duration.ofHours(1));

	public IngestSettings {
		requireNonNull(pollInterval, "pollInterval");
		requireNonNull(initialDelay, "initialDelay");
		requireNonNull(backoffInitial, "backoffInitial");
		requireNonNull(backoffMax, "backoffMax");
		if (pollInterval.isZero() || pollInterval.isNegative()) {
			throw new IllegalArgumentException("pollInterval must be positive");
		}
		if (initialDelay.isNegative()) {
			throw new IllegalArgumentException("initialDelay must not be negative");
		}
		if (backoffInitial.isZero() || backoffInitial.isNegative() || backoffMax.compareTo(backoffInitial) < 0) {
			throw new IllegalArgumentException("backoff must be positive and backoffMax >= backoffInitial");
		}
	}

	/** Wait after the n-th consecutive transport failure (n ≥ 1): initial × 2^(n−1), capped. */
	public Duration backoff(int consecutiveFailures) {
		if (consecutiveFailures <= 0) {
			return pollInterval;
		}
		Duration wait = backoffInitial;
		for (int i = 1; i < consecutiveFailures && wait.compareTo(backoffMax) < 0; i++) {
			wait = wait.multipliedBy(2);
		}
		return wait.compareTo(backoffMax) > 0 ? backoffMax : wait;
	}

	static IngestSettings of(IngestConfig config) {
		return new IngestSettings(Duration.parse(config.pollInterval()), Duration.parse(config.initialDelay()),
				Duration.parse(config.backoffInitial()), Duration.parse(config.backoffMax()));
	}

}
