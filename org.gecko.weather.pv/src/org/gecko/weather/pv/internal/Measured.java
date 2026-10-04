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

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.OptionalDouble;

import org.gecko.weather.pv.model.pv.PvMeasurement;

/**
 * Aggregates meter readings for the comparison with the forecast: mean power per hour, energy per
 * day. Only readings with a PV power count.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class Measured {

	/** A gap between two readings longer than this counts as no production, not as a straight line. */
	public static final Duration MAX_GAP = Duration.ofMinutes(15);

	private Measured() {
	}

	/** Mean PV power in kW of the readings in {@code [from, to)}; empty without any. */
	public static OptionalDouble meanPower(List<PvMeasurement> readings, Instant from, Instant to) {
		return readings.stream().filter(m -> m.isSetPvPower() && m.getTime() != null)
				.filter(m -> !m.getTime().toInstant().isBefore(from) && m.getTime().toInstant().isBefore(to))
				.mapToDouble(PvMeasurement::getPvPower).average();
	}

	/**
	 * PV energy in kWh between {@code from} and {@code to}: trapezoids between consecutive readings
	 * (sorted by time), skipping gaps longer than {@link #MAX_GAP}. Empty without two usable readings.
	 */
	public static OptionalDouble energy(List<PvMeasurement> readings, Instant from, Instant to) {
		List<PvMeasurement> usable = readings.stream().filter(m -> m.isSetPvPower() && m.getTime() != null)
				.filter(m -> !m.getTime().toInstant().isBefore(from) && m.getTime().toInstant().isBefore(to))
				.sorted((a, b) -> a.getTime().compareTo(b.getTime())).toList();
		if (usable.size() < 2) {
			return OptionalDouble.empty();
		}
		double kWh = 0;
		for (int i = 1; i < usable.size(); i++) {
			PvMeasurement a = usable.get(i - 1);
			PvMeasurement b = usable.get(i);
			Duration gap = Duration.between(a.getTime().toInstant(), b.getTime().toInstant());
			if (gap.compareTo(MAX_GAP) <= 0) {
				kWh += (a.getPvPower() + b.getPvPower()) / 2 * gap.toMillis() / 3_600_000.0;
			}
		}
		return OptionalDouble.of(kWh);
	}
}
