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
package org.gecko.weather.api.spi;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Map;

import org.gecko.weather.model.weather.SourceDataset;

/**
 * What a run produced. {@link Unchanged} is a first-class outcome so that a provider without change
 * detection is visibly incomplete (OPS-6), not quietly wasteful.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public sealed interface FetchResult {

	/** Nothing changed at the source since the state in the request; no datasets, state unchanged. */
	record Unchanged() implements FetchResult {
	}

	/**
	 * New data. One dataset per site and binding the provider could serve — a site may get several
	 * (one per bound station), or none if the source had nothing for its locations. Datasets are
	 * complete: every value carries provenance and uncertainty, headers carry issue time, refresh
	 * interval, horizon, licence.
	 *
	 * @param datasets by site id
	 * @param state    the change-detection state to use for the next run
	 * @param skipped  per-value problems that did not fail the run, counted by reason — for the log
	 *                 and health, never silently dropped (ADR-0007)
	 */
	record Fetched(Map<String, List<SourceDataset>> datasets, SourceState state, Map<String, Integer> skipped)
			implements FetchResult {

		public Fetched {
			datasets = Map.copyOf(requireNonNull(datasets, "datasets"));
			requireNonNull(state, "state");
			skipped = Map.copyOf(requireNonNull(skipped, "skipped"));
		}

		public Fetched(Map<String, List<SourceDataset>> datasets, SourceState state) {
			this(datasets, state, Map.of());
		}

		public int datasetCount() {
			return datasets.values().stream().mapToInt(List::size).sum();
		}
	}

}
