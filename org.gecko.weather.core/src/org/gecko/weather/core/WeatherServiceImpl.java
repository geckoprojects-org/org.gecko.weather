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

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import org.gecko.weather.api.Reports;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.ValueQuery;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * {@link WeatherService} over the repository: loads the site's report and reads it with
 * {@link Reports}. Nothing is merged, nothing is cached — the repository is the truth and a report
 * load is one file.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository repository;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = requireNonNull(repository, "repository");
	}

	@Override
	public Optional<WeatherReport> report(String siteId) {
		requireSite(siteId);
		return repository.loadReport(siteId);
	}

	@Override
	public List<MeasuredValue> values(String siteId, MeasurementKind kind) {
		requireNonNull(kind, "kind");
		return report(siteId).map(r -> Reports.values(r, kind)).orElse(List.of());
	}

	@Override
	public List<MeasuredValue> values(String siteId, ValueQuery query) {
		requireNonNull(query, "query");
		return report(siteId).map(r -> Reports.values(r, query)).orElse(List.of());
	}

	@Override
	public SortedMap<Instant, List<MeasuredValue>> timeline(String siteId, ValueQuery query) {
		requireNonNull(query, "query");
		return report(siteId).map(r -> Reports.timeline(r, query)).orElse(Collections.emptySortedMap());
	}

	@Override
	public List<SourceDataset> archive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo) {
		requireSite(siteId);
		return repository.loadArchive(siteId, providerId, productId, issuedFrom, issuedTo);
	}

	private void requireSite(String siteId) {
		requireNonNull(siteId, "siteId");
		if (repository.loadSite(siteId).isEmpty()) {
			throw new UnknownSiteException(siteId);
		}
	}

}
