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

import static java.util.Objects.requireNonNull;

import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * What a consumer supplies to register a site. Everything but name and coordinates is optional: the
 * id is generated when absent, the time zone is resolved from the coordinates, the elevation may be
 * looked up by a provider later.
 *
 * @param id         stable id chosen by the consumer, or empty to have one generated
 * @param name       human-readable name
 * @param latitude   WGS84, decimal degrees, north positive
 * @param longitude  WGS84, decimal degrees, east positive
 * @param elevation  metres above mean sea level
 * @param timeZone   zone for day boundaries; resolved from the coordinates when empty
 * @param attributes free-form consumer data stored with the site, e.g. {@code pv.tilt}
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record SiteRegistration(Optional<String> id, String name, double latitude, double longitude,
		OptionalDouble elevation, Optional<ZoneId> timeZone, Map<String, String> attributes) {

	public SiteRegistration {
		requireNonNull(id, "id");
		requireNonNull(name, "name");
		requireNonNull(elevation, "elevation");
		requireNonNull(timeZone, "timeZone");
		attributes = Map.copyOf(requireNonNull(attributes, "attributes"));
		if (latitude < -90 || latitude > 90) {
			throw new IllegalArgumentException("latitude out of range: " + latitude);
		}
		if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException("longitude out of range: " + longitude);
		}
	}

	/** Minimal registration: generated id, no elevation, zone resolved from the coordinates. */
	public static SiteRegistration of(String name, double latitude, double longitude) {
		return new SiteRegistration(Optional.empty(), name, latitude, longitude, OptionalDouble.empty(),
				Optional.empty(), Map.of());
	}

	public SiteRegistration withId(String id) {
		return new SiteRegistration(Optional.of(id), name, latitude, longitude, elevation, timeZone, attributes);
	}

	public SiteRegistration withElevation(double metres) {
		return new SiteRegistration(id, name, latitude, longitude, OptionalDouble.of(metres), timeZone, attributes);
	}

	public SiteRegistration withTimeZone(ZoneId zone) {
		return new SiteRegistration(id, name, latitude, longitude, elevation, Optional.of(zone), attributes);
	}

	public SiteRegistration withAttributes(Map<String, String> attributes) {
		return new SiteRegistration(id, name, latitude, longitude, elevation, timeZone, attributes);
	}

}
