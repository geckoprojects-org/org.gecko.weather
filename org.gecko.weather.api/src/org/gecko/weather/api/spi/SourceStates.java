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

import org.gecko.weather.model.weather.SourceEntity;
import org.gecko.weather.model.weather.SourceStateRecord;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * Converts between the in-memory {@link SourceState} providers work with and the persisted
 * {@link SourceStateRecord}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class SourceStates {

	private SourceStates() {
	}

	public static SourceState fromRecord(SourceStateRecord record) {
		requireNonNull(record, "record");
		Map<URI, SourceState.Entity> entities = new HashMap<>();
		for (SourceEntity e : record.getEntities()) {
			if (e.getUri() == null) {
				continue;
			}
			SourceState.Entity entity = new SourceState.Entity(Optional.ofNullable(e.getEtag()),
					Optional.ofNullable(e.getLastModified()));
			if (!entity.isEmpty()) {
				entities.put(URI.create(e.getUri()), entity);
			}
		}
		return new SourceState(entities);
	}

	public static SourceStateRecord toRecord(String providerId, String productId, SourceState state, Instant updatedAt) {
		requireNonNull(providerId, "providerId");
		requireNonNull(productId, "productId");
		requireNonNull(state, "state");
		WeatherFactory f = WeatherFactory.eINSTANCE;
		SourceStateRecord record = f.createSourceStateRecord();
		record.setProviderId(providerId);
		record.setProductId(productId);
		record.setUpdatedAt(updatedAt);
		state.entities().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e -> {
			SourceEntity entity = f.createSourceEntity();
			entity.setUri(e.getKey().toString());
			e.getValue().etag().ifPresent(entity::setEtag);
			e.getValue().lastModified().ifPresent(entity::setLastModified);
			record.getEntities().add(entity);
		});
		return record;
	}

}
