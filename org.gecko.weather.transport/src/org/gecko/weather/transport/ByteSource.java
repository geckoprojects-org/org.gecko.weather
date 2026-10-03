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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.gecko.weather.api.spi.SourceState;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Obtains the bytes behind a URI, conditionally. The one abstraction a provider needs to be testable
 * offline: production uses {@link HttpByteSource}, a test hands in a lambda that serves fixtures.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ConsumerType
@FunctionalInterface
public interface ByteSource {

	/** Outcome of a fetch. */
	sealed interface Result {
	}

	/**
	 * The source has nothing under the URI (HTTP 404 or 410). Distinct from other failures because a
	 * provider may probe for a file that is not published yet — a DWD model run appears step by step.
	 */
	class NotFoundException extends IOException {
		private static final long serialVersionUID = 1L;
		private final int status;

		public NotFoundException(java.net.URI uri, int status) {
			super("HTTP " + status + " for " + uri);
			this.status = status;
		}

		public int status() {
			return status;
		}
	}

	/** The source reports no change against the validators given (HTTP 304). */
	record Unchanged() implements Result {
	}

	/**
	 * New content.
	 *
	 * @param data       the payload, as delivered (not unwrapped); the caller closes it
	 * @param validators what to send next time; empty entity if the source gave nothing
	 */
	record Content(InputStream data, SourceState.Entity validators) implements Result, AutoCloseable {
		public Content {
			requireNonNull(data, "data");
			requireNonNull(validators, "validators");
		}

		@Override
		public void close() throws IOException {
			data.close();
		}
	}

	/**
	 * Fetches the URI. With validators present the request is conditional; without, it is plain.
	 *
	 * @throws IOException on any transport failure — the caller decides about retries
	 */
	Result fetch(java.net.URI uri, Optional<SourceState.Entity> validators) throws IOException;

}
