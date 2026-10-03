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
package org.gecko.weather.api;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * A look at, and a hand on, the ingest runtime: what each product last did, and a way to run one
 * now instead of waiting for its schedule. The status is what a health endpoint will serve later
 * (OPS-3); for the MVP it is read from a shell or a test.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ProviderType
public interface IngestControl {

	/**
	 * The state of one product's ingest.
	 *
	 * @param providerId          the provider
	 * @param productId           the product
	 * @param lastRun             when the job last ran, successfully or not
	 * @param lastSuccess         when it last completed without error
	 * @param lastChange          when it last brought new data
	 * @param nextRun             when it will run next
	 * @param consecutiveFailures failures since the last success
	 * @param lastError           the last failure's message, cleared on success
	 * @param running             whether a run is in progress
	 */
	record IngestStatus(String providerId, String productId, Optional<Instant> lastRun, Optional<Instant> lastSuccess,
			Optional<Instant> lastChange, Optional<Instant> nextRun, int consecutiveFailures, Optional<String> lastError,
			boolean running) {
	}

	/** Status of every product the runtime knows, in provider/product order. */
	List<IngestStatus> status();

	/**
	 * Runs one product's ingest now, on the runtime's thread, and returns when it is done. Resets a
	 * pending backoff.
	 *
	 * @throws IllegalArgumentException if no such product is registered
	 */
	IngestStatus runNow(String providerId, String productId);

}
