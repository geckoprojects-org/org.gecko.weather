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

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.annotation.versioning.ProviderType;

/**
 * What is currently known for a site, by site id — the whole report, or just the values of the
 * quantities a consumer cares about. Nothing is merged: every method returns each source's values
 * with their provenance, newest issue first per instant ({@link Reports#TIMELINE_ORDER}).
 * <p>
 * Typical calls: {@code values("home", AIR_TEMPERATURE)} is the full temperature forecast from every
 * source that has one; {@code values("home", ValueQuery.of(UV_INDEX))} the UV forecast;
 * {@code timeline("home", CLOUD_COVER)} cloud cover grouped by instant with all sources side by side.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ProviderType
public interface WeatherService {

	/**
	 * The current report of a site, or empty if the site is registered but nothing has been ingested
	 * for it yet.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	Optional<WeatherReport> report(String siteId);

	/**
	 * All values of one kind for a site — the whole forecast of that quantity, every level, every
	 * statistic, every source. Empty if nothing is known.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	List<MeasuredValue> values(String siteId, MeasurementKind kind);

	/**
	 * The values matching a query, from every source the query admits.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	List<MeasuredValue> values(String siteId, ValueQuery query);

	/**
	 * The values matching a query grouped by {@code validAt}: one entry per instant any admitted
	 * source covers, each holding the values of all sources that cover it.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	SortedMap<Instant, List<MeasuredValue>> timeline(String siteId, ValueQuery query);

	/**
	 * Superseded datasets of one product for a site, newest first, with {@code issuedAt} in
	 * {@code [issuedFrom, issuedTo)}. This is what "what was predicted yesterday for today 14:00?" is
	 * answered from.
	 *
	 * @throws UnknownSiteException if no such site is registered
	 */
	List<SourceDataset> archive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo);

}
