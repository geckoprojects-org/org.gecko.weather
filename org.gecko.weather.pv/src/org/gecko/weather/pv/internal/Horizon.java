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

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
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
 * A deciduous obstacle lets part of the direct sun through while it is leafless
 * ({@link Obstacle#getLeafOffTransmittance()}, mid-November to the end of April). Single crowns above
 * an obstacle's edge ({@link Obstacle#getCrownHeight()}) make it soft: the share of the sun that
 * passes rises linearly from the edge to the top of the crowns.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class Horizon {

	/** Nothing in the way */
	public static final Horizon FLAT = new Horizon(new double[360], new double[360], List.of());

	/** First leafless day: beech and oak have dropped their leaves. */
	static final MonthDay LEAF_OFF_FROM = MonthDay.of(Month.NOVEMBER, 15);
	/** Last leafless day: beech leafs out early in May, oak a little later. */
	static final MonthDay LEAF_OFF_TO = MonthDay.of(Month.APRIL, 30);

	/**
	 * An obstacle as angles per azimuth (0 where it is not): its closed edge and the top of the
	 * crowns above it, with what passes while it is leafless.
	 */
	private record Edge(double[] edge, double[] crown, double leafOff) {

		/** Share of the direct sun at this position that passes this obstacle. */
		double share(double sunElevation, int a, boolean leafless) {
			if (sunElevation <= edge[a]) {
				return leafless ? leafOff : 0;
			}
			if (sunElevation < crown[a]) {
				double gaps = (sunElevation - edge[a]) / (crown[a] - edge[a]);
				return leafless ? gaps + (1 - gaps) * leafOff : gaps;
			}
			return 1;
		}
	}

	private final double[] elevation;
	private final double[] line;
	private final List<Edge> edges;

	private Horizon(double[] elevation, double[] line, List<Edge> edges) {
		this.elevation = elevation;
		this.line = line;
		this.edges = edges;
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
		double[] line = e.clone();
		List<Edge> edges = new ArrayList<>();
		double height = plant.isSetMountingHeight() ? plant.getMountingHeight() : 0;
		for (Obstacle o : plant.getObstacles()) {
			if (o.getDistance() <= 0) {
				continue;
			}
			double angle = Math.toDegrees(Math.atan2(o.getHeight() - height, o.getDistance()));
			double top = Math.toDegrees(Math.atan2(o.getHeight() + Math.max(0, o.getCrownHeight()) - height, o.getDistance()));
			if (top <= 0) {
				continue;
			}
			double[] edge = new double[360];
			double[] crown = new double[360];
			for (int a = 0; a < 360; a++) {
				if (within(a, o.getAzimuthFrom(), o.getAzimuthTo())) {
					edge[a] = Math.max(0, angle);
					crown[a] = top;
					e[a] = Math.max(e[a], edge[a]);
				}
			}
			edges.add(new Edge(edge, crown, Math.max(0, Math.min(1, o.getLeafOffTransmittance()))));
		}
		return new Horizon(e, line, List.copyOf(edges));
	}

	/** Whether deciduous obstacles are leafless on that day. */
	static boolean leafOff(LocalDate date) {
		MonthDay d = MonthDay.from(date);
		return !d.isBefore(LEAF_OFF_FROM) || !d.isAfter(LEAF_OFF_TO);
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

	/**
	 * Share of the direct sun that reaches the plant: 1 above the horizon and every crown, 0 behind the
	 * horizon line or something opaque; behind leafless deciduous obstacles and among single crowns
	 * the product of what each obstacle lets through.
	 */
	public double beamShare(double sunElevation, double sunAzimuth, LocalDate date) {
		int a = (int) Math.round(norm(sunAzimuth)) % 360;
		if (sunElevation <= line[a]) {
			return 0;
		}
		boolean leafless = leafOff(date);
		double share = 1;
		for (Edge edge : edges) {
			share *= edge.share(sunElevation, a, leafless);
		}
		return share;
	}

	/** Whether the sun at this position is behind a closed edge — leafy or not; the crowns above it do not count. */
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
