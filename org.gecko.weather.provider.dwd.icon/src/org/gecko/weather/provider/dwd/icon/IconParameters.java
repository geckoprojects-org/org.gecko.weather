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
package org.gecko.weather.provider.dwd.icon;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Statistic;

/**
 * The ICON-D2 single-level parameters this provider reads → canonical kinds, qualifiers and units,
 * plus what the GRIB2 product definition of each must say. The cloud layers share parameter 6/22
 * and differ only by their pressure bounds, so the check includes the levels. The surface radiation
 * fields are averages since model start (product template 8, statistical process 0) and are turned
 * into hourly means by {@link #hourly}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class IconParameters {

	/** Code table 4.5 level types. */
	static final int SURFACE = 1;
	static final int ISOBARIC = 100;
	static final int NO_LEVEL = 255;

	/** What the product definition section of a parameter's file has to contain. */
	public record Signature(int category, int number, int pdsTemplate, int levelType1, double levelValue1, int levelType2,
			double levelValue2) {
	}

	/**
	 * @param averagedSinceStart whether the file holds the mean over [run, validAt] rather than an
	 *                           instantaneous or hourly value
	 */
	public record Parameter(String name, MeasurementKind kind, Level level, Statistic statistic, Duration period, String unit,
			boolean averagedSinceStart, Signature signature) {
		public Parameter {
			requireNonNull(name, "name");
			requireNonNull(kind, "kind");
			requireNonNull(level, "level");
			requireNonNull(statistic, "statistic");
			requireNonNull(unit, "unit");
			requireNonNull(signature, "signature");
		}
	}

	private static final Map<String, Parameter> BY_NAME = new LinkedHashMap<>();

	static {
		cloud("clct", Level.CLOUD_TOTAL, new Signature(6, 1, 0, SURFACE, 0, NO_LEVEL, 0));
		cloud("clcl", Level.CLOUD_LOW, new Signature(6, 22, 0, ISOBARIC, 80_000, SURFACE, 0));
		cloud("clcm", Level.CLOUD_MID, new Signature(6, 22, 0, ISOBARIC, 40_000, ISOBARIC, 80_000));
		cloud("clch", Level.CLOUD_HIGH, new Signature(6, 22, 0, ISOBARIC, 0, ISOBARIC, 40_000));
		radiation("aswdir_s", MeasurementKind.DIRECT_RADIATION, 198);
		radiation("aswdifd_s", MeasurementKind.DIFFUSE_RADIATION, 199);
	}

	private IconParameters() {
	}

	private static void cloud(String name, Level level, Signature signature) {
		BY_NAME.put(name, new Parameter(name, MeasurementKind.CLOUD_COVER, level, Statistic.INSTANT, null, "%", false, signature));
	}

	private static void radiation(String name, MeasurementKind kind, int number) {
		BY_NAME.put(name, new Parameter(name, kind, Level.SURFACE, Statistic.MEAN, Duration.ofHours(1), "W/m2", true,
				new Signature(4, number, 8, SURFACE, 0, NO_LEVEL, 0)));
	}

	public static Optional<Parameter> byName(String name) {
		return Optional.ofNullable(BY_NAME.get(requireNonNull(name, "name")));
	}

	public static List<Parameter> all() {
		return List.copyOf(BY_NAME.values());
	}

	/**
	 * A comma-separated list of parameter names, as configuration gives it.
	 *
	 * @throws IllegalArgumentException for an unknown name or an empty list
	 */
	public static List<Parameter> parse(String names) {
		requireNonNull(names, "names");
		List<Parameter> result = new java.util.ArrayList<>();
		for (String raw : names.split(",")) {
			String name = raw.trim();
			if (name.isEmpty()) {
				continue;
			}
			result.add(byName(name).orElseThrow(() -> new IllegalArgumentException(
					"unknown ICON-D2 parameter '" + name + "', known: " + BY_NAME.keySet())));
		}
		if (result.isEmpty()) {
			throw new IllegalArgumentException("no ICON-D2 parameter configured");
		}
		return List.copyOf(result);
	}

	public static Set<MeasurementKind> kinds(Collection<Parameter> parameters) {
		Set<MeasurementKind> kinds = EnumSet.noneOf(MeasurementKind.class);
		parameters.forEach(p -> kinds.add(p.kind()));
		return kinds;
	}

	/**
	 * Verifies that a decoded field is what the parameter name promised — DWD changing a product
	 * must fail loudly, not produce plausible wrong numbers.
	 *
	 * @throws FetchException on any mismatch
	 */
	public static void check(Parameter parameter, Grib2Field field) {
		Signature s = parameter.signature();
		if (field.category() != s.category() || field.number() != s.number()) {
			throw mismatch(parameter, "parameter " + field.category() + "/" + field.number(), s.category() + "/" + s.number());
		}
		if (field.pdsTemplate() != s.pdsTemplate()) {
			throw mismatch(parameter, "product definition template " + field.pdsTemplate(), String.valueOf(s.pdsTemplate()));
		}
		if (field.levelType1() != s.levelType1() || Math.abs(field.levelValue1() - s.levelValue1()) > 0.5) {
			throw mismatch(parameter, "first level " + field.levelType1() + "@" + field.levelValue1(),
					s.levelType1() + "@" + s.levelValue1());
		}
		if (s.levelType2() != NO_LEVEL
				&& (field.levelType2() != s.levelType2() || Math.abs(field.levelValue2() - s.levelValue2()) > 0.5)) {
			throw mismatch(parameter, "second level " + field.levelType2() + "@" + field.levelValue2(),
					s.levelType2() + "@" + s.levelValue2());
		}
		if (parameter.averagedSinceStart()) {
			Grib2Field.Interval interval = field.interval()
					.orElseThrow(() -> mismatch(parameter, "an instantaneous field", "a time-averaged one"));
			if (interval.statisticalProcess() != 0) {
				throw mismatch(parameter, "statistical process " + interval.statisticalProcess(), "0 (average)");
			}
			if (!interval.start().equals(field.referenceTime())) {
				throw mismatch(parameter, "an interval starting " + interval.start(), "one starting at the model run");
			}
		}
	}

	private static FetchException mismatch(Parameter p, String found, String expected) {
		return new FetchException("ICON-D2 " + p.name() + ": file carries " + found + ", expected " + expected);
	}

	/**
	 * The mean over the last interval from two means since model start: with {@code M(t)} the mean
	 * over [0, t], the mean over [t₁, t₂] is {@code (t₂·M(t₂) − t₁·M(t₁)) / (t₂ − t₁)}. For
	 * {@code t₁ = 0} the first term alone remains, whatever the file says for the empty interval.
	 */
	public static double hourly(double meanToEnd, Duration sinceRunToEnd, double meanToStart, Duration sinceRunToStart) {
		double t2 = sinceRunToEnd.toSeconds() / 3600.0;
		double t1 = sinceRunToStart.toSeconds() / 3600.0;
		if (t2 <= t1) {
			throw new IllegalArgumentException("interval end " + sinceRunToEnd + " not after start " + sinceRunToStart);
		}
		return (t2 * meanToEnd - t1 * meanToStart) / (t2 - t1);
	}
}
