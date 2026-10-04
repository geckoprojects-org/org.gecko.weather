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
package org.gecko.weather.pv.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.gecko.weather.pv.model.pv.HorizonPoint;
import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.Plant;

/**
 * The horizon a plant sees: per azimuth the elevation under which the sky begins, from a measured
 * horizon line and from obstacles (a forest at 40 m with 25 m trees, modules at 6 m: atan(19/40) =
 * 25.4°). Sampled every degree; the higher of both wins. Also how much of the isotropic sky it hides.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class Horizon {

	/** Nothing in the way */
	public static final Horizon FLAT = new Horizon(new double[360]);

	private final double[] elevation;

	private Horizon(double[] elevation) {
		this.elevation = elevation;
	}

	public static Horizon of(Plant plant) {
		double[] e = new double[360];
		List<HorizonPoint> points = new ArrayList<>(plant.getHorizon());
		points.sort(Comparator.comparingDouble(HorizonPoint::getAzimuth));
		if (!points.isEmpty()) {
			for (int a = 0; a < 360; a++) {
				e[a] = Math.max(0, interpolate(points, a));
			}
		}
		double height = plant.isSetMountingHeight() ? plant.getMountingHeight() : 0;
		for (Obstacle o : plant.getObstacles()) {
			if (o.getDistance() <= 0) {
				continue;
			}
			double angle = Math.toDegrees(Math.atan2(o.getHeight() - height, o.getDistance()));
			if (angle <= 0) {
				continue;
			}
			for (int a = 0; a < 360; a++) {
				if (within(a, o.getAzimuthFrom(), o.getAzimuthTo())) {
					e[a] = Math.max(e[a], angle);
				}
			}
		}
		return new Horizon(e);
	}

	/** Whether an azimuth lies in the range from → to clockwise; a range may cross north. */
	static boolean within(double azimuth, double from, double to) {
		double a = norm(azimuth);
		double f = norm(from);
		double t = norm(to);
		return f <= t ? a >= f && a <= t : a >= f || a <= t;
	}

	private static double norm(double a) {
		return ((a % 360) + 360) % 360;
	}

	private static double interpolate(List<HorizonPoint> points, double azimuth) {
		if (points.size() == 1) {
			return points.get(0).getElevation();
		}
		for (int k = 0; k < points.size(); k++) {
			HorizonPoint p = points.get(k);
			HorizonPoint q = points.get((k + 1) % points.size());
			double from = p.getAzimuth();
			double to = q.getAzimuth() + (k + 1 == points.size() ? 360 : 0);
			double a = azimuth < from ? azimuth + 360 : azimuth;
			if (a >= from && a <= to) {
				double f = to == from ? 0 : (a - from) / (to - from);
				return p.getElevation() + f * (q.getElevation() - p.getElevation());
			}
		}
		return points.get(0).getElevation();
	}

	/** The horizon elevation at an azimuth. */
	public double at(double azimuth) {
		int a = (int) Math.round(norm(azimuth)) % 360;
		return elevation[a];
	}

	/** Whether the sun at this position is hidden. */
	public boolean hides(double sunElevation, double sunAzimuth) {
		return sunElevation <= at(sunAzimuth);
	}

	/**
	 * Share of the isotropic sky radiance on a horizontal surface that the horizon hides: the mean of
	 * sin²(h) over all azimuths — a band of elevation h removes that much of the cosine-weighted dome.
	 * A tilted plane looks partly away from it; ignoring that slightly overstates the loss, the safe
	 * side for a forecast.
	 */
	public double skyViewLoss() {
		double sum = 0;
		for (double h : elevation) {
			double s = Math.sin(Math.toRadians(h));
			sum += s * s;
		}
		return sum / elevation.length;
	}
}
