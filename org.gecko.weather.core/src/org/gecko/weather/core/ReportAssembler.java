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
package org.gecko.weather.core;

import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.WeatherConstants;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * {@link WeatherDataSink}: the one place a report changes. Loads the site's report, applies the
 * change, refreshes the computed solar part over the new horizon, saves. Per site, changes are
 * serialised; one file per site makes that enough.
 * <p>
 * {@code replace} archives the dataset it replaces. {@code append} keeps a rolling window per
 * streaming product and drops what falls out of it <em>without</em> archiving — a file per minute
 * would be noise, and the forecast history {@code INT-17} asks for is in the replaced issues. Noted
 * as an open point in the plan.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class ReportAssembler implements WeatherDataSink {

	private final WeatherRepository repository;
	private final SolarDatasets solar;
	private final CoreSettings settings;
	private final Clock clock;
	private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

	public ReportAssembler(WeatherRepository repository, SolarDatasets solar, CoreSettings settings, Clock clock) {
		this.repository = requireNonNull(repository, "repository");
		this.solar = requireNonNull(solar, "solar");
		this.settings = requireNonNull(settings, "settings");
		this.clock = requireNonNull(clock, "clock");
	}

	@Override
	public void replace(String siteId, SourceDataset dataset) {
		requireDataset(dataset);
		synchronized (lock(siteId)) {
			Site site = requireSite(siteId);
			WeatherReport report = loadOrCreate(siteId);
			find(report, dataset).ifPresent(old -> {
				report.getDatasets().remove(old);
				repository.archive(siteId, old);
			});
			report.getDatasets().add(EcoreUtil.copy(dataset));
			finish(site, report);
		}
	}

	@Override
	public void append(String siteId, SourceDataset partial) {
		requireDataset(partial);
		synchronized (lock(siteId)) {
			Site site = requireSite(siteId);
			WeatherReport report = loadOrCreate(siteId);
			SourceDataset target = find(report, partial).orElseGet(() -> {
				SourceDataset created = EcoreUtil.copy(partial);
				created.getValues().clear();
				report.getDatasets().add(created);
				return created;
			});
			for (MeasuredValue v : partial.getValues()) {
				target.getValues().add(EcoreUtil.copy(v));
			}
			// header moves forward with the newest piece
			if (target.getIssuedAt() == null || partial.getIssuedAt().isAfter(target.getIssuedAt())) {
				target.setIssuedAt(partial.getIssuedAt());
			}
			if (partial.getRetrievedAt() != null) {
				target.setRetrievedAt(partial.getRetrievedAt());
			}
			target.getValues().stream().map(MeasuredValue::getValidAt).max(Comparator.naturalOrder())
					.ifPresent(target::setHorizonEnd);
			Instant cutoff = clock.instant().minus(settings.streamWindow());
			target.getValues().removeIf(v -> v.getValidAt().isBefore(cutoff));
			target.getValues().stream().map(MeasuredValue::getValidAt).min(Comparator.naturalOrder())
					.ifPresent(target::setHorizonStart);
			finish(site, report);
		}
	}

	// --- internals -------------------------------------------------------------------------

	private void finish(Site site, WeatherReport report) {
		Instant now = clock.instant();
		refreshSolar(site, report, now);
		report.setGeneratedAt(now);
		repository.saveReport(report);
	}

	/**
	 * The solar dataset covers the union of all other datasets' horizons, on the configured step;
	 * {@code DayInfo} covers every civil date in that range. Recomputed on every change, never archived.
	 */
	private void refreshSolar(Site site, WeatherReport report, Instant now) {
		report.getDatasets().removeIf(ReportAssembler::isSolar);
		report.getDays().clear();
		Optional<Instant> from = report.getDatasets().stream().map(SourceDataset::getHorizonStart).filter(i -> i != null)
				.min(Comparator.naturalOrder());
		Optional<Instant> to = report.getDatasets().stream().map(SourceDataset::getHorizonEnd).filter(i -> i != null)
				.max(Comparator.naturalOrder());
		if (from.isEmpty() || to.isEmpty()) {
			return;
		}
		Instant start = from.get().truncatedTo(java.time.temporal.ChronoUnit.HOURS);
		report.getDatasets().add(solar.positions(site, start, to.get(), settings.solarStep(), now));
		report.getDays().addAll(solar.days(site, start, to.get()));
	}

	private static boolean isSolar(SourceDataset d) {
		return WeatherConstants.COMPUTED_PROVIDER_ID.equals(d.getProviderId())
				&& WeatherConstants.SOLAR_PRODUCT_ID.equals(d.getProductId());
	}

	private WeatherReport loadOrCreate(String siteId) {
		return repository.loadReport(siteId).orElseGet(() -> {
			WeatherReport r = WeatherFactory.eINSTANCE.createWeatherReport();
			r.setSiteId(siteId);
			r.setGeneratedAt(clock.instant());
			return r;
		});
	}

	private static Optional<SourceDataset> find(WeatherReport report, SourceDataset like) {
		return report.getDatasets().stream().filter(d -> d.getProviderId().equals(like.getProviderId())
				&& d.getProductId().equals(like.getProductId())).findFirst();
	}

	private Site requireSite(String siteId) {
		return repository.loadSite(requireNonNull(siteId, "siteId")).orElseThrow(() -> new UnknownSiteException(siteId));
	}

	private static void requireDataset(SourceDataset dataset) {
		requireNonNull(dataset, "dataset");
		if (dataset.getProviderId() == null || dataset.getProductId() == null || dataset.getIssuedAt() == null) {
			throw new IllegalArgumentException("A dataset needs providerId, productId and issuedAt");
		}
		if (isSolar(dataset)) {
			throw new IllegalArgumentException("The solar dataset is computed by the service, not submitted");
		}
	}

	private Object lock(String siteId) {
		return locks.computeIfAbsent(siteId, k -> new Object());
	}

}
