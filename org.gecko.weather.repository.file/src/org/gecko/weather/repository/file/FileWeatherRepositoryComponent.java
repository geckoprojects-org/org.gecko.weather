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
package org.gecko.weather.repository.file;

import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.fennec.emf.osgi.constants.EMFNamespaces;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.SourceStateRecord;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;
import org.osgi.service.metatype.annotations.Designate;

/**
 * The OSGi face of {@link XmiFolderRepository}: one {@link WeatherRepository} service over the
 * configured root folder. The {@link ResourceSet} is the prototype-scoped service Fennec EMF
 * publishes per registered model, targeted at the weather model, so the component only appears once
 * the model is there and gets a resource set that already knows it.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(name = FileWeatherRepositoryComponent.PID, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = FileRepositoryConfig.class)
public class FileWeatherRepositoryComponent implements WeatherRepository {

	public static final String PID = "org.gecko.weather.repository.file";

	/** Target on the model name: the {@code emf.name} of {@code weather.ecore}. */
	static final String MODEL_TARGET = "(" + EMFNamespaces.EMF_NAME + "=weather)";

	@Reference(target = MODEL_TARGET, scope = ReferenceScope.PROTOTYPE)
	private ResourceSet resourceSet;
	private XmiFolderRepository delegate;

	@Activate
	void activate(FileRepositoryConfig config) {
		// one prototype instance for this component; the core clears its resources after each use
		delegate = new XmiFolderRepository(Path.of(config.root()), () -> resourceSet, config.compress());
	}

	public Path getRoot() {
		return delegate.getRoot();
	}

	@Override
	public Optional<Site> loadSite(String siteId) {
		return delegate.loadSite(siteId);
	}

	@Override
	public List<Site> loadSites() {
		return delegate.loadSites();
	}

	@Override
	public void saveSite(Site site) {
		delegate.saveSite(site);
	}

	@Override
	public void deleteSite(String siteId) {
		delegate.deleteSite(siteId);
	}

	@Override
	public Optional<WeatherReport> loadReport(String siteId) {
		return delegate.loadReport(siteId);
	}

	@Override
	public void saveReport(WeatherReport report) {
		delegate.saveReport(report);
	}

	@Override
	public void archive(String siteId, SourceDataset dataset) {
		delegate.archive(siteId, dataset);
	}

	@Override
	public List<SourceDataset> loadArchive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo) {
		return delegate.loadArchive(siteId, providerId, productId, issuedFrom, issuedTo);
	}

	@Override
	public int evictArchive(String siteId, Instant issuedBefore) {
		return delegate.evictArchive(siteId, issuedBefore);
	}

	@Override
	public Optional<StationCatalog> loadCatalog(String providerId, String productId) {
		return delegate.loadCatalog(providerId, productId);
	}

	@Override
	public void saveCatalog(StationCatalog catalog) {
		delegate.saveCatalog(catalog);
	}

	@Override
	public Optional<SourceStateRecord> loadSourceState(String providerId, String productId) {
		return delegate.loadSourceState(providerId, productId);
	}

	@Override
	public void saveSourceState(SourceStateRecord state) {
		delegate.saveSourceState(state);
	}

}
