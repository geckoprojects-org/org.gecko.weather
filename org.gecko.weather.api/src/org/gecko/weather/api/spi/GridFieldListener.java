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

import java.time.Instant;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * Told about every whole field a gridded provider reads, while it reads it — a map maker, not a
 * site. Registered as an OSGi service; the service property {@value #PARAMETERS} narrows which
 * parameters it is given (comma-separated source names; absent means all).
 * <p>
 * Called on the provider's fetch thread, one field at a time: whatever is kept must be derived
 * before returning, the array is not the listener's. An exception is logged by the provider and
 * does not stop the fetch.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
@ConsumerType
public interface GridFieldListener {

	/** Service property: the source parameter names wanted, e.g. {@code clct}. */
	String PARAMETERS = "weather.field.parameters";

	/**
	 * A run is about to be read: its fields follow, then {@link #runComplete}. A run cut short (a
	 * file not published yet, a transport error) is never completed — what a listener derived from
	 * it counts only once {@link #runComplete} came.
	 */
	default void runStarted(String providerId, String productId, Instant run) {
	}

	/** One field of the run. */
	void field(GridField field);

	/** Every field of the run was handed over. */
	default void runComplete(String providerId, String productId, Instant run) {
	}
}
