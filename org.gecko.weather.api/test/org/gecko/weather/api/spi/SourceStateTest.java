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

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class SourceStateTest {

	private static final URI A = URI.create("https://opendata.dwd.de/a.kmz");
	private static final URI B = URI.create("https://opendata.dwd.de/b.kmz");

	@Test
	void derivesNextStateWithoutMutating() {
		SourceState s0 = SourceState.EMPTY;
		SourceState s1 = s0.with(A, Optional.of("\"etag-1\""), Optional.empty());
		SourceState s2 = s1.with(B, Optional.empty(), Optional.of(Instant.EPOCH));

		assertThat(s0.entities()).isEmpty();
		assertThat(s1.entity(A)).map(SourceState.Entity::etag).contains(Optional.of("\"etag-1\""));
		assertThat(s1.entity(B)).isEmpty();
		assertThat(s2.entities()).hasSize(2);
	}

	@Test
	void emptyEntityRemovesTheUri() {
		SourceState s = SourceState.EMPTY.with(A, Optional.of("x"), Optional.empty())
				.with(A, Optional.empty(), Optional.empty());
		assertThat(s.entities()).isEmpty();
	}

}
