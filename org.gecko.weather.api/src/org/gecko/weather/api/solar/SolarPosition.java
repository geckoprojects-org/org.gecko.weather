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
package org.gecko.weather.api.solar;

import java.time.Instant;

/**
 * Where the sun is, as seen from a position at an instant.
 *
 * @param instant   the instant
 * @param elevation degrees above the horizon; negative below it
 * @param azimuth   degrees from north, clockwise, {@code [0, 360)}
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record SolarPosition(Instant instant, double elevation, double azimuth) {

	/** Whether the sun's centre is above the horizon (no refraction correction implied). */
	public boolean isUp() {
		return elevation > 0;
	}

}
