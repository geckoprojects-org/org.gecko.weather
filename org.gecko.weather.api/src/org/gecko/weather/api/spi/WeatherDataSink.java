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
package org.gecko.weather.api.spi;

import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.model.weather.SourceDataset;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The way data gets into a site's report — for <em>every</em> source. The ingest runtime feeds
 * {@link WeatherProvider#fetch} results through it; push sources call it directly: a local weather
 * station gateway (Ecowitt, Bresser) receiving the device's uploads, an MQTT subscriber, a webhook.
 * A personal station is thereby just another source with its own dataset, provenance and
 * {@code Origin.LOCAL_STATION}, and consumers read it like any other.
 * <p>
 * Two verbs, because sources behave in two ways:
 * <ul>
 * <li><b>Issues</b> — a forecast product publishes a complete new dataset every few hours. The new
 * one <em>replaces</em> the product's dataset in the report; the previous one goes to the archive.</li>
 * <li><b>Streams</b> — a station reports a few values every minute. They are <em>appended</em> to the
 * product's current dataset, which thereby holds a rolling window of observations; what falls out
 * of the window is archived in buckets, not lost.</li>
 * </ul>
 * Implemented by the runtime, registered as one OSGi service.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ProviderType
public interface WeatherDataSink {

	/**
	 * A fresh issue of a product for a site. Replaces the dataset with the same provider and product
	 * in the site's report and archives the one it replaced. The dataset must be complete: header,
	 * and provenance and uncertainty on every value.
	 *
	 * @throws UnknownSiteException     if the site is not registered
	 * @throws IllegalArgumentException if the dataset lacks provider, product or issue time
	 */
	void replace(String siteId, SourceDataset dataset);

	/**
	 * Observations trickling in from a streaming source. The values are appended to the product's
	 * current dataset in the site's report (created from the given header on first call); the header
	 * fields {@code issuedAt}, {@code retrievedAt} and {@code horizonEnd} move forward with the newest
	 * value. The report keeps a rolling window per streaming product; older values are archived.
	 *
	 * @param partial a dataset carrying the header and only the new values
	 * @throws UnknownSiteException     if the site is not registered
	 * @throws IllegalArgumentException if the dataset lacks provider, product or issue time
	 */
	void append(String siteId, SourceDataset partial);

}
