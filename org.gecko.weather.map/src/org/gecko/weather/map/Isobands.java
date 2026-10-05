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
package org.gecko.weather.map;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gecko.weather.api.spi.RegularLatLonGrid;

/**
 * A gridded field as disjoint bands of polygons: the field is coarsened by block means
 * ({@code factor × factor} source cells, NaN-aware), every coarse cell is put into the band its
 * value falls into, and the outline of each band's cells is traced along the cell edges — outer
 * rings counter-clockwise, holes clockwise, as RFC 7946 asks — then thinned by Douglas–Peucker.
 * Masked cells belong to no band, so the area outside the model domain stays empty instead of
 * showing as "0 %".
 * <p>
 * The outlines follow cell edges: neighbouring bands share their boundary exactly before thinning,
 * and only approximately after it (each ring is thinned on its own). Good enough for a picture
 * and a query by band, not a topology.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
public final class Isobands {

	/**
	 * One polygon: the outer ring and its holes, each closed (first point repeated last), as
	 * {@code [lon, lat]} pairs.
	 */
	public record Polygon(double[][] outer, List<double[][]> holes) {
	}

	/** Every polygon of one band, values in {@code [lower, upper)} — the last band includes its upper. */
	public record Band(double lower, double upper, List<Polygon> polygons) {

		public int pointCount() {
			int n = 0;
			for (Polygon p : polygons) {
				n += p.outer().length;
				for (double[][] h : p.holes()) {
					n += h.length;
				}
			}
			return n;
		}
	}

	/**
	 * How the bands are made.
	 *
	 * @param breaks     ascending band limits, at least two: {@code 0,25,50,75,100} gives four bands
	 * @param factor     source cells per coarse cell along each axis, 1 for none
	 * @param tolerance  Douglas–Peucker tolerance in coarse cells (0 keeps every corner)
	 * @param minCells   rings enclosing fewer coarse cells are dropped (specks and pinholes)
	 */
	public record Settings(double[] breaks, int factor, double tolerance, double minCells) {

		public Settings {
			requireNonNull(breaks, "breaks");
			if (breaks.length < 2) {
				throw new IllegalArgumentException("at least two breaks");
			}
			for (int k = 1; k < breaks.length; k++) {
				if (!(breaks[k] > breaks[k - 1])) {
					throw new IllegalArgumentException("breaks must ascend: " + Arrays.toString(breaks));
				}
			}
			breaks = breaks.clone();
			if (factor < 1) {
				throw new IllegalArgumentException("factor must be at least 1");
			}
			if (tolerance < 0 || minCells < 0) {
				throw new IllegalArgumentException("tolerance and minCells must not be negative");
			}
		}

		/** Cloud cover in quarters, ICON-D2's 0.02° coarsened to 0.06°. */
		public static Settings cloudCover() {
			return new Settings(new double[] { 0, 25, 50, 75, 100 }, 3, 1.0, 8);
		}
	}

	private Isobands() {
	}

	/** The bands of a field, one per pair of breaks, in order; a band without cells has no polygons. */
	public static List<Band> of(RegularLatLonGrid grid, float[] data, Settings settings) {
		requireNonNull(grid, "grid");
		requireNonNull(data, "data");
		requireNonNull(settings, "settings");
		if (data.length != grid.ni() * grid.nj()) {
			throw new IllegalArgumentException("data has " + data.length + " points, grid " + grid.ni() * grid.nj());
		}
		Coarse coarse = Coarse.of(grid, data, settings.factor());
		int[] classes = classify(coarse.values, settings.breaks());
		List<Band> bands = new ArrayList<>();
		for (int k = 0; k + 1 < settings.breaks().length; k++) {
			List<int[]> rings = trace(classes, coarse.ni, coarse.nj, k);
			bands.add(new Band(settings.breaks()[k], settings.breaks()[k + 1], polygons(rings, coarse, settings)));
		}
		return bands;
	}

	// --- coarsening and classes -------------------------------------------------------------

	/** Block means; a block with more masked than valid cells is masked. */
	record Coarse(int ni, int nj, double lo1, double la1, double resolution, float[] values) {

		static Coarse of(RegularLatLonGrid grid, float[] data, int f) {
			int ni = (grid.ni() + f - 1) / f;
			int nj = (grid.nj() + f - 1) / f;
			float[] values = new float[ni * nj];
			for (int cj = 0; cj < nj; cj++) {
				for (int ci = 0; ci < ni; ci++) {
					double sum = 0;
					int valid = 0;
					int all = 0;
					for (int j = cj * f; j < Math.min(grid.nj(), (cj + 1) * f); j++) {
						for (int i = ci * f; i < Math.min(grid.ni(), (ci + 1) * f); i++) {
							float v = data[j * grid.ni() + i];
							all++;
							if (!Float.isNaN(v)) {
								sum += v;
								valid++;
							}
						}
					}
					values[cj * ni + ci] = valid * 2 > all ? (float) (sum / valid) : Float.NaN;
				}
			}
			// the centre of coarse cell 0 is the middle of its block of source cells
			double offset = (f - 1) / 2.0 * grid.resolutionDegrees();
			return new Coarse(ni, nj, grid.lo1() + offset, grid.la1() + offset, f * grid.resolutionDegrees(), values);
		}

		/** Longitude of cell corner column {@code x} (corner 0 is half a cell west of centre 0). */
		double cornerLon(int x) {
			return lo1 + (x - 0.5) * resolution;
		}

		double cornerLat(int y) {
			return la1 + (y - 0.5) * resolution;
		}
	}

	/** Band index per cell, -1 where masked or outside every band. */
	static int[] classify(float[] values, double[] breaks) {
		int[] out = new int[values.length];
		int last = breaks.length - 2;
		for (int n = 0; n < values.length; n++) {
			float v = values[n];
			int k = -1;
			if (!Float.isNaN(v) && v >= breaks[0] && v <= breaks[breaks.length - 1]) {
				k = 0;
				while (k < last && v >= breaks[k + 1]) {
					k++;
				}
			}
			out[n] = k;
		}
		return out;
	}

	// --- tracing ----------------------------------------------------------------------------

	private static final int EAST = 0;
	private static final int NORTH = 1;
	private static final int WEST = 2;
	private static final int SOUTH = 3;
	private static final int[] DX = { 1, 0, -1, 0 };
	private static final int[] DY = { 0, 1, 0, -1 };

	/**
	 * The boundary of the cells of band {@code k} as rings of corner indices ({@code y * (ni+1) + x}),
	 * closed, only the corners where the direction turns. Every boundary edge is directed with the
	 * band on its left, so outer rings run counter-clockwise and holes clockwise. Where two cells of
	 * the band touch only at a corner, the ring turns left: diagonal neighbours stay separate rings.
	 */
	static List<int[]> trace(int[] classes, int ni, int nj, int k) {
		int w = ni + 1;
		// out[corner * 4 + direction]: an unused edge leaving that corner in that direction
		boolean[] out = new boolean[w * (nj + 1) * 4];
		int edges = 0;
		for (int j = 0; j < nj; j++) {
			for (int i = 0; i < ni; i++) {
				if (classes[j * ni + i] != k) {
					continue;
				}
				if (!in(classes, ni, nj, i, j - 1, k)) {
					out[(j * w + i) * 4 + EAST] = true;
					edges++;
				}
				if (!in(classes, ni, nj, i + 1, j, k)) {
					out[(j * w + i + 1) * 4 + NORTH] = true;
					edges++;
				}
				if (!in(classes, ni, nj, i, j + 1, k)) {
					out[((j + 1) * w + i + 1) * 4 + WEST] = true;
					edges++;
				}
				if (!in(classes, ni, nj, i - 1, j, k)) {
					out[((j + 1) * w + i) * 4 + SOUTH] = true;
					edges++;
				}
			}
		}
		List<int[]> rings = new ArrayList<>();
		int[] buffer = new int[16];
		// a ring starts at a corner with a single edge left, so that coming back to it closes the ring —
		// a corner where the band touches itself diagonally can be passed twice by one ring
		// a pass can leave a ring whose every corner had two edges while another ring still shared them
		for (int pass = 0; edges > 0; pass++) {
			if (pass > 1) {
				throw new IllegalStateException(edges + " boundary edges left untraced");
			}
			for (int start = 0; start < out.length && edges > 0; start++) {
				if (!out[start] || outgoing(out, start / 4) != 1) {
					continue;
				}
				int startCorner = start / 4;
				int corner = startCorner;
				int dir = start % 4;
				int previous = -1;
				int n = 0;
				do {
					out[corner * 4 + dir] = false;
					edges--;
					if (dir != previous) {
						if (n == buffer.length) {
							buffer = Arrays.copyOf(buffer, n * 2);
						}
						buffer[n++] = corner;
					}
					previous = dir;
					corner += DY[dir] * w + DX[dir];
					if (corner == startCorner) {
						break;
					}
					dir = next(out, corner, dir);
					if (dir < 0) {
						throw new IllegalStateException("open boundary at corner " + corner);
					}
				} while (true);
				int[] ring = Arrays.copyOf(buffer, n + 1);
				ring[n] = ring[0];
				rings.add(ring);
			}
		}
		return rings;
	}

	private static int outgoing(boolean[] out, int corner) {
		int n = 0;
		for (int d = 0; d < 4; d++) {
			if (out[corner * 4 + d]) {
				n++;
			}
		}
		return n;
	}

	/**
	 * The edge to follow from {@code corner}, arriving in direction {@code dir}: left, straight,
	 * right. Left first keeps a ring around its own cell where two cells meet only at the corner.
	 */
	private static int next(boolean[] out, int corner, int dir) {
		int left = (dir + 1) % 4;
		if (out[corner * 4 + left]) {
			return left;
		}
		if (out[corner * 4 + dir]) {
			return dir;
		}
		int right = (dir + 3) % 4;
		if (out[corner * 4 + right]) {
			return right;
		}
		return -1;
	}

	private static boolean in(int[] classes, int ni, int nj, int i, int j, int k) {
		return i >= 0 && j >= 0 && i < ni && j < nj && classes[j * ni + i] == k;
	}

	// --- rings to polygons ------------------------------------------------------------------

	private record Ring(double[][] points, double area, double minX, double minY, double maxX, double maxY) {

		static Ring of(int[] corners, int w) {
			double[][] p = new double[corners.length][];
			double minX = Double.MAX_VALUE;
			double minY = Double.MAX_VALUE;
			double maxX = -Double.MAX_VALUE;
			double maxY = -Double.MAX_VALUE;
			for (int n = 0; n < corners.length; n++) {
				double x = corners[n] % w;
				double y = corners[n] / w;
				p[n] = new double[] { x, y };
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
			return new Ring(p, signedArea(p), minX, minY, maxX, maxY);
		}

		boolean contains(double x, double y) {
			if (x < minX || x > maxX || y < minY || y > maxY) {
				return false;
			}
			boolean inside = false;
			for (int a = 0, b = points.length - 1; a < points.length; b = a++) {
				double[] pa = points[a];
				double[] pb = points[b];
				if ((pa[1] > y) != (pb[1] > y) && x < (pb[0] - pa[0]) * (y - pa[1]) / (pb[1] - pa[1]) + pa[0]) {
					inside = !inside;
				}
			}
			return inside;
		}
	}

	private static List<Polygon> polygons(List<int[]> traced, Coarse coarse, Settings settings) {
		int w = coarse.ni + 1;
		List<Ring> outers = new ArrayList<>();
		List<Ring> holes = new ArrayList<>();
		for (int[] corners : traced) {
			Ring ring = Ring.of(corners, w);
			if (Math.abs(ring.area()) < settings.minCells()) {
				continue;
			}
			(ring.area() > 0 ? outers : holes).add(ring);
		}
		List<List<Ring>> holesOf = new ArrayList<>();
		outers.forEach(o -> holesOf.add(new ArrayList<>()));
		for (Ring hole : holes) {
			// a point just inside the hole's first edge — corners lie on both rings' boundaries
			double[] a = hole.points()[0];
			double[] b = hole.points()[1];
			double px = (a[0] + b[0]) / 2 + (b[1] - a[1]) * -0.25 / Math.max(1e-9, Math.hypot(b[0] - a[0], b[1] - a[1]));
			double py = (a[1] + b[1]) / 2 + (b[0] - a[0]) * 0.25 / Math.max(1e-9, Math.hypot(b[0] - a[0], b[1] - a[1]));
			int best = -1;
			for (int n = 0; n < outers.size(); n++) {
				Ring o = outers.get(n);
				if (o.area() > -hole.area() && o.contains(px, py) && (best < 0 || o.area() < outers.get(best).area())) {
					best = n;
				}
			}
			if (best >= 0) {
				holesOf.get(best).add(hole);
			}
		}
		List<Polygon> polygons = new ArrayList<>();
		for (int n = 0; n < outers.size(); n++) {
			double[][] outer = toLonLat(simplify(outers.get(n).points(), settings.tolerance()), coarse);
			if (outer.length < 4) {
				continue;
			}
			List<double[][]> inner = new ArrayList<>();
			for (Ring h : holesOf.get(n)) {
				double[][] hole = toLonLat(simplify(h.points(), settings.tolerance()), coarse);
				if (hole.length >= 4) {
					inner.add(hole);
				}
			}
			polygons.add(new Polygon(outer, List.copyOf(inner)));
		}
		return List.copyOf(polygons);
	}

	private static double[][] toLonLat(double[][] corners, Coarse coarse) {
		double[][] out = new double[corners.length][];
		for (int n = 0; n < corners.length; n++) {
			out[n] = new double[] { round(coarse.cornerLon((int) corners[n][0])), round(coarse.cornerLat((int) corners[n][1])) };
		}
		return out;
	}

	/** Five decimals: a metre, far below the 2 km of the grid, and a third of the text. */
	private static double round(double degrees) {
		return Math.round(degrees * 1e5) / 1e5;
	}

	/** Shoelace, in cell units: positive for counter-clockwise. */
	static double signedArea(double[][] ring) {
		double sum = 0;
		for (int n = 0; n + 1 < ring.length; n++) {
			sum += ring[n][0] * ring[n + 1][1] - ring[n + 1][0] * ring[n][1];
		}
		return sum / 2;
	}

	// --- thinning ---------------------------------------------------------------------------

	/**
	 * Douglas–Peucker on a closed ring: split at the first point and the point farthest from it,
	 * thin both halves. Keeps at least four points (a triangle, closed) when the ring had them.
	 */
	static double[][] simplify(double[][] ring, double tolerance) {
		if (tolerance <= 0 || ring.length <= 4) {
			return ring;
		}
		int last = ring.length - 1;
		int far = 0;
		double farthest = -1;
		for (int n = 1; n < last; n++) {
			double d = Math.hypot(ring[n][0] - ring[0][0], ring[n][1] - ring[0][1]);
			if (d > farthest) {
				farthest = d;
				far = n;
			}
		}
		boolean[] keep = new boolean[ring.length];
		keep[0] = true;
		keep[far] = true;
		keep[last] = true;
		thin(ring, 0, far, tolerance, keep);
		thin(ring, far, last, tolerance, keep);
		List<double[]> out = new ArrayList<>();
		for (int n = 0; n < ring.length; n++) {
			if (keep[n]) {
				out.add(ring[n]);
			}
		}
		if (out.size() < 4) {
			return ring;
		}
		return out.toArray(double[][]::new);
	}

	private static void thin(double[][] p, int from, int to, double tolerance, boolean[] keep) {
		if (to - from < 2) {
			return;
		}
		double ax = p[from][0];
		double ay = p[from][1];
		double bx = p[to][0];
		double by = p[to][1];
		double length = Math.hypot(bx - ax, by - ay);
		int index = -1;
		double max = tolerance;
		for (int n = from + 1; n < to; n++) {
			double d = length == 0 ? Math.hypot(p[n][0] - ax, p[n][1] - ay)
					: Math.abs((bx - ax) * (ay - p[n][1]) - (ax - p[n][0]) * (by - ay)) / length;
			if (d > max) {
				max = d;
				index = n;
			}
		}
		if (index >= 0) {
			keep[index] = true;
			thin(p, from, index, tolerance, keep);
			thin(p, index, to, tolerance, keep);
		}
	}
}
