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
import java.time.LocalDate;
import java.time.ZoneId;

import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.GeoPosition;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Solar geometry, computed exactly for a position — the one source in this service with no spatial
 * error. Supersedes the old {@code AstrotimeService}, which offered day events only and evaluated them
 * in the platform's time zone.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ProviderType
public interface SolarService {

	/** Sun elevation and azimuth at an instant. */
	SolarPosition positionAt(GeoPosition position, Instant instant);

	/**
	 * Day events for a calendar date in the given zone: sunrise, sunset, civil and nautical twilight,
	 * solar noon, day length, maximum elevation. Events that do not occur (polar day or night) are left
	 * unset. The returned {@link DayInfo} carries a {@code COMPUTED} provenance naming the
	 * implementation.
	 */
	DayInfo dayInfo(GeoPosition position, LocalDate date, ZoneId zone);

}
