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
package org.gecko.weather.api.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.SourceStateRecord;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Durable storage for sites, their current reports, the archive of superseded datasets and provider
 * catalogues. The first implementation is a folder of XMI files; the interface is cut so that a
 * Fennec persistence backend or an index can be put behind it without touching callers.
 * <p>
 * Semantics every implementation must keep:
 * <ul>
 * <li>{@code save*} replaces whole: the stored site or report is afterwards equal to the argument.</li>
 * <li>{@link #archive} is append-only; nothing archived is ever overwritten by this interface.
 * Retention is a separate, explicit operation.</li>
 * <li>Loads return detached copies; mutating a loaded object does not change the store until it is
 * saved again.</li>
 * <li>All failures surface as {@link RepositoryException}.</li>
 * </ul>
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ConsumerType
public interface WeatherRepository {

	// --- sites ---------------------------------------------------------------------------

	Optional<Site> loadSite(String siteId);

	List<Site> loadSites();

	void saveSite(Site site);

	/** Removes the site, its report and its archive. No-op for an unknown id. */
	void deleteSite(String siteId);

	// --- reports -------------------------------------------------------------------------

	Optional<WeatherReport> loadReport(String siteId);

	void saveReport(WeatherReport report);

	// --- archive -------------------------------------------------------------------------

	/** Appends a superseded dataset to the site's archive. */
	void archive(String siteId, SourceDataset dataset);

	/**
	 * Archived datasets of one product, newest issue first, with {@code issuedAt} in
	 * {@code [issuedFrom, issuedTo)}.
	 */
	List<SourceDataset> loadArchive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo);

	/**
	 * Retention: removes archived datasets of a site issued before the cutoff.
	 *
	 * @return how many were removed
	 */
	int evictArchive(String siteId, Instant issuedBefore);

	// --- catalogues ----------------------------------------------------------------------

	Optional<StationCatalog> loadCatalog(String providerId, String productId);

	void saveCatalog(StationCatalog catalog);

	// --- ingest state --------------------------------------------------------------------

	/** The change-detection state the ingest runtime last saved for a product. */
	Optional<SourceStateRecord> loadSourceState(String providerId, String productId);

	/** Replaces the change-detection state of a product. */
	void saveSourceState(SourceStateRecord state);

}
