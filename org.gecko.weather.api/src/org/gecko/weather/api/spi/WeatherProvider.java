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

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * One source product — MOSMIX_L, ICON-D2, SIS — as the ingest runtime sees it. Registered as an OSGi
 * service with the properties {@link org.gecko.weather.api.WeatherConstants#PROVIDER_ID} and
 * {@link org.gecko.weather.api.WeatherConstants#PRODUCT_ID}.
 * <p>
 * The contract is deliberately one call: {@link #fetch} takes every bound site and returns ready
 * datasets. How the provider gets there — HTTP with conditional requests, zip unwrapping, an XMI
 * load, a GRIB window read, the mapping of {@code TTT} to {@code AIR_TEMPERATURE} — is its own
 * business, structured as it sees fit and tested offline against recorded fixtures (DEV-6). What the
 * runtime owns is scheduling, retry with backoff, the persisted {@link SourceState}, report assembly
 * and the archive.
 * <p>
 * Efficiency rule: download what the source publishes per product <em>once</em> per run and extract
 * every site's stations or cells from it (ADR-0010). Per-station sources naturally fetch per
 * binding; even then, two sites bound to the same station share one download.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ConsumerType
public interface WeatherProvider {

	String providerId();

	String productId();

	/** {@code STATION} for point products, {@code GRID_CELL} for gridded ones. */
	Origin origin();

	/**
	 * How a product delivers — the two verbs of {@link WeatherDataSink}: an <b>issue</b> is a complete
	 * dataset that replaces the previous one; a <b>stream</b> is observations that are appended to a
	 * rolling dataset (a satellite analysis every 15 minutes, an own station every minute).
	 */
	enum Delivery {
		ISSUE, STREAM
	}

	/** {@link Delivery#ISSUE} unless the provider says otherwise. */
	default Delivery delivery() {
		return Delivery.ISSUE;
	}

	/** The product's publication interval; also the natural scheduling period. */
	Duration expectedRefresh();

	/** The canonical kinds this product can deliver, for documentation and binding decisions. */
	Set<MeasurementKind> provides();

	/** Licence identifier of the data, e.g. {@code DL-DE-BY-2.0}. */
	String licence();

	/** Attribution text the licence requires. */
	String attribution();

	/**
	 * Resolves bindings for this product; the registry calls it, the runtime passes the results back
	 * in {@link FetchRequest}.
	 */
	SiteBindingResolver bindingResolver();

	/**
	 * Runs one fetch for all given sites.
	 *
	 * @throws IOException    on a transport failure — the runtime retries with backoff
	 * @throws FetchException if the content is not what the provider expects — the runtime records the
	 *                        failure and does not retry until the next schedule
	 */
	FetchResult fetch(FetchRequest request) throws IOException;

}
