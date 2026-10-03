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

import org.gecko.weather.model.weather.GeoPosition;

/**
 * Geodesy the providers share: great-circle distance on a spherical earth, which is what a
 * "distance to the station or cell centre" needs — a few metres of ellipsoid error do not matter
 * against kilometres of representativeness.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class Geo {

	/** Mean earth radius in metres. */
	public static final double EARTH_RADIUS_METERS = 6_371_000;

	private Geo() {
	}

	/** Haversine distance between two positions, in metres. */
	public static double distanceMeters(GeoPosition a, GeoPosition b) {
		requireNonNull(a, "a");
		requireNonNull(b, "b");
		return distanceMeters(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
	}

	/** Haversine distance between two WGS84 points given in degrees, in metres. */
	public static double distanceMeters(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double h = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		return 2 * EARTH_RADIUS_METERS * Math.asin(Math.min(1, Math.sqrt(h)));
	}
}
