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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * Reading helpers over a {@link WeatherReport}: what {@link WeatherService} does, as pure functions a
 * consumer holding a report can call itself. No state, no OSGi.
 * <p>
 * They read <em>across</em> datasets and hand back every matching value with its provenance; they
 * never pick one source over another (ADR-0013). Ordering is by {@code validAt}, then by newest issue
 * first, so that a consumer iterating a timeline meets the freshest value of each instant first — but
 * still meets all of them.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class Reports {

	/** validAt ascending, then issuedAt descending (newest issue first), then product id for stability. */
	public static final Comparator<MeasuredValue> TIMELINE_ORDER = Comparator
			.comparing(MeasuredValue::getValidAt)
			.thenComparing(v -> v.getProvenance().getIssuedAt(), Comparator.reverseOrder())
			.thenComparing(v -> v.getProvenance().getProductId());

	private Reports() {
	}

	/**
	 * What makes a dataset distinct within a report: provider, product and the location it was read
	 * at — a point product bound to three stations yields three datasets. A refresh replaces the
	 * dataset with the same key.
	 */
	public static String sourceKey(SourceDataset dataset) {
		requireNonNull(dataset, "dataset");
		String location = dataset.getStationId() != null ? dataset.getStationId()
				: dataset.getCell() != null ? dataset.getCell().getGridId() + ":" + dataset.getCell().getI() + "," + dataset.getCell().getJ()
				: "";
		return dataset.getProviderId() + "/" + dataset.getProductId() + "@" + location;
	}

	/** Whether two datasets are the same source: same provider, product and location. */
	public static boolean sameSource(SourceDataset a, SourceDataset b) {
		return sourceKey(a).equals(sourceKey(b));
	}

	/** The datasets of one product — one per bound station or cell — in rank order of the report. */
	public static List<SourceDataset> datasets(WeatherReport report, String providerId, String productId) {
		requireNonNull(report, "report");
		return report.getDatasets().stream()
				.filter(d -> providerId.equals(d.getProviderId()) && productId.equals(d.getProductId())).toList();
	}

	/** The first dataset of one product, if the report has one. For several stations use {@link #datasets}. */
	public static Optional<SourceDataset> dataset(WeatherReport report, String providerId, String productId) {
		return datasets(report, providerId, productId).stream().findFirst();
	}

	/** The dataset of one product read at one station. */
	public static Optional<SourceDataset> dataset(WeatherReport report, String providerId, String productId, String stationId) {
		requireNonNull(stationId, "stationId");
		return datasets(report, providerId, productId).stream().filter(d -> stationId.equals(d.getStationId())).findFirst();
	}

	/** Every value of a kind, from every dataset, in {@link #TIMELINE_ORDER}. */
	public static List<MeasuredValue> values(WeatherReport report, MeasurementKind kind) {
		requireNonNull(kind, "kind");
		return values(report, v -> v.getKind() == kind);
	}

	/** Every value matching the predicate (a {@link ValueQuery}, usually), in {@link #TIMELINE_ORDER}. */
	public static List<MeasuredValue> values(WeatherReport report, Predicate<MeasuredValue> filter) {
		requireNonNull(report, "report");
		requireNonNull(filter, "filter");
		return report.getDatasets().stream().flatMap(d -> d.getValues().stream()).filter(filter)
				.sorted(TIMELINE_ORDER).toList();
	}

	/** Values of a kind valid exactly at the instant — typically one per source that covers it. */
	public static List<MeasuredValue> valuesAt(WeatherReport report, MeasurementKind kind, Instant validAt) {
		requireNonNull(validAt, "validAt");
		return values(report, v -> v.getKind() == kind && validAt.equals(v.getValidAt()));
	}

	/**
	 * The values matching the predicate grouped by {@code validAt}: one entry per instant any source
	 * covers, each holding the values of all sources that cover it, newest issue first.
	 */
	public static SortedMap<Instant, List<MeasuredValue>> timeline(WeatherReport report, Predicate<MeasuredValue> filter) {
		return values(report, filter).stream()
				.collect(Collectors.groupingBy(MeasuredValue::getValidAt, TreeMap::new, Collectors.toList()));
	}

	/** The day events for a date, if the report has them. */
	public static Optional<DayInfo> day(WeatherReport report, LocalDate date) {
		requireNonNull(report, "report");
		requireNonNull(date, "date");
		return report.getDays().stream().filter(d -> date.equals(d.getDate())).findFirst();
	}

	/** The latest {@code horizonEnd} over all datasets — how far ahead anything is known. */
	public static Optional<Instant> horizonEnd(WeatherReport report) {
		requireNonNull(report, "report");
		return report.getDatasets().stream().map(SourceDataset::getHorizonEnd).filter(i -> i != null)
				.max(Comparator.naturalOrder());
	}

	/**
	 * Whether a dataset is older than its product's refresh interval at the given instant — i.e. a
	 * newer issue should have existed and either was not published or not yet fetched. False when the
	 * product declares no refresh interval.
	 */
	public static boolean isStale(SourceDataset dataset, Instant now) {
		requireNonNull(dataset, "dataset");
		requireNonNull(now, "now");
		if (!dataset.isSetExpectedRefresh() || dataset.getIssuedAt() == null) {
			return false;
		}
		Duration age = Duration.between(dataset.getIssuedAt(), now);
		return age.compareTo(dataset.getExpectedRefresh()) > 0;
	}

}
