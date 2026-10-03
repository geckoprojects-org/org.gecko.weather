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

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Change-detection state per source URI — what the last successful fetch returned as {@code ETag}
 * and {@code Last-Modified}, so the next fetch can be conditional (OPS-6). Owned and persisted by the
 * ingest runtime; handed to the provider with every {@link FetchRequest} and returned updated in
 * {@link FetchResult.Fetched}. Immutable; {@link #with} derives the next state.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record SourceState(Map<URI, Entity> entities) {

	/** Validators of one URI as the source last reported them. */
	public record Entity(Optional<String> etag, Optional<Instant> lastModified) {
		public Entity {
			requireNonNull(etag, "etag");
			requireNonNull(lastModified, "lastModified");
		}

		public boolean isEmpty() {
			return etag.isEmpty() && lastModified.isEmpty();
		}
	}

	public static final SourceState EMPTY = new SourceState(Map.of());

	public SourceState {
		entities = Map.copyOf(requireNonNull(entities, "entities"));
	}

	public Optional<Entity> entity(URI uri) {
		return Optional.ofNullable(entities.get(uri));
	}

	/** The state with the validators of one URI replaced (or removed, if the entity is empty). */
	public SourceState with(URI uri, Entity entity) {
		Map<URI, Entity> next = new HashMap<>(entities);
		if (entity.isEmpty()) {
			next.remove(uri);
		} else {
			next.put(uri, entity);
		}
		return new SourceState(next);
	}

	public SourceState with(URI uri, Optional<String> etag, Optional<Instant> lastModified) {
		return with(uri, new Entity(etag, lastModified));
	}

}
