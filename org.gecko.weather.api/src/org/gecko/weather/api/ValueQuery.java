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

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Statistic;

/**
 * Which values of a site a consumer wants: one or more kinds, optionally narrowed by level,
 * statistic, validity window and source products. "Only the temperature forecast" is
 * {@code ValueQuery.of(AIR_TEMPERATURE)}; "only low cloud from the grid, next 24 h" is
 * {@code of(CLOUD_COVER).level(CLOUD_LOW).products("ICON-D2").between(now, now + 24h)}.
 * <p>
 * A query narrows; it never merges. Every source that still matches contributes all its values.
 *
 * @param kinds     at least one kind
 * @param level     vertical level, or empty for all
 * @param statistic statistic, or empty for all
 * @param from      inclusive lower bound on {@code validAt}, or empty
 * @param to        exclusive upper bound on {@code validAt}, or empty
 * @param products  product ids to include, or empty for all
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record ValueQuery(Set<MeasurementKind> kinds, Optional<Level> level, Optional<Statistic> statistic,
		Optional<Instant> from, Optional<Instant> to, Set<String> products) implements Predicate<MeasuredValue> {

	public ValueQuery {
		kinds = Set.copyOf(requireNonNull(kinds, "kinds"));
		if (kinds.isEmpty()) {
			throw new IllegalArgumentException("at least one kind is required");
		}
		requireNonNull(level, "level");
		requireNonNull(statistic, "statistic");
		requireNonNull(from, "from");
		requireNonNull(to, "to");
		products = Set.copyOf(requireNonNull(products, "products"));
		if (from.isPresent() && to.isPresent() && !from.get().isBefore(to.get())) {
			throw new IllegalArgumentException("from must be before to");
		}
	}

	public static ValueQuery of(MeasurementKind kind, MeasurementKind... more) {
		Set<MeasurementKind> kinds = new java.util.HashSet<>();
		kinds.add(requireNonNull(kind, "kind"));
		kinds.addAll(Set.of(more));
		return new ValueQuery(kinds, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Set.of());
	}

	public ValueQuery level(Level level) {
		return new ValueQuery(kinds, Optional.of(level), statistic, from, to, products);
	}

	public ValueQuery statistic(Statistic statistic) {
		return new ValueQuery(kinds, level, Optional.of(statistic), from, to, products);
	}

	/** Validity window {@code [from, to)}. */
	public ValueQuery between(Instant from, Instant to) {
		return new ValueQuery(kinds, level, statistic, Optional.of(from), Optional.of(to), products);
	}

	public ValueQuery validFrom(Instant from) {
		return new ValueQuery(kinds, level, statistic, Optional.of(from), to, products);
	}

	public ValueQuery products(String... productIds) {
		return new ValueQuery(kinds, level, statistic, from, to, Set.of(productIds));
	}

	@Override
	public boolean test(MeasuredValue v) {
		if (!kinds.contains(v.getKind())) {
			return false;
		}
		if (level.isPresent() && v.getLevel() != level.get()) {
			return false;
		}
		if (statistic.isPresent() && v.getStatistic() != statistic.get()) {
			return false;
		}
		Instant validAt = v.getValidAt();
		if (from.isPresent() && validAt.isBefore(from.get())) {
			return false;
		}
		if (to.isPresent() && !validAt.isBefore(to.get())) {
			return false;
		}
		return products.isEmpty() || products.contains(v.getProvenance().getProductId());
	}

}
