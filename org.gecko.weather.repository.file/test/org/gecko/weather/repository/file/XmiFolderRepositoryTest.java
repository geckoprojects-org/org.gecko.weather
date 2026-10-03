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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.repository.RepositoryException;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * The folder repository against a temp folder, plain EMF, no OSGi.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class XmiFolderRepositoryTest {

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;
	private static final Instant T0 = Instant.parse("2026-10-03T06:00:00Z");

	@TempDir
	Path tmp;

	private XmiFolderRepository repo;

	@BeforeEach
	void setUp() {
		repo = new XmiFolderRepository(tmp.resolve("weather"));
	}

	@Test
	void createsLayoutUnderRoot() {
		assertThat(tmp.resolve("weather/sites")).isDirectory();
		assertThat(tmp.resolve("weather/reports")).isDirectory();
		assertThat(tmp.resolve("weather/archive")).isDirectory();
		assertThat(tmp.resolve("weather/catalogs")).isDirectory();
	}

	@Test
	void siteRoundTripsAndLoadsDetached() {
		Site site = site("home");
		repo.saveSite(site);

		assertThat(tmp.resolve("weather/sites/home.xmi")).isRegularFile();
		assertThat(Files.exists(tmp.resolve("weather/sites/home.xmi.tmp"))).isFalse();

		Optional<Site> loaded = repo.loadSite("home");
		assertThat(loaded).isPresent();
		assertThat(EcoreUtil.equals(site, loaded.get())).isTrue();
		assertThat(loaded.get().eResource()).isNull();
		assertThat(site.eResource()).as("the caller's object is not moved into our resource").isNull();

		// mutating the loaded copy does not change the store
		loaded.get().setName("changed");
		assertThat(repo.loadSite("home").get().getName()).isEqualTo("Home roof");

		// save replaces whole
		site.setName("renamed");
		site.getBindings().clear();
		repo.saveSite(site);
		Site again = repo.loadSite("home").get();
		assertThat(again.getName()).isEqualTo("renamed");
		assertThat(again.getBindings()).isEmpty();
	}

	@Test
	void unknownSiteIsEmptyAndListIsSorted() {
		assertThat(repo.loadSite("nope")).isEmpty();
		assertThat(repo.loadSites()).isEmpty();
		repo.saveSite(site("zulu"));
		repo.saveSite(site("alpha"));
		repo.saveSite(site("Berlin/Mitte"));
		assertThat(repo.loadSites()).extracting(Site::getId).containsExactly("Berlin/Mitte", "alpha", "zulu");
		assertThat(tmp.resolve("weather/sites/Berlin%2FMitte.xmi")).isRegularFile();
	}

	@Test
	void reportRoundTrips() {
		WeatherReport report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(T0);
		report.getDatasets().add(dataset("MOSMIX_L", T0));
		repo.saveReport(report);

		WeatherReport loaded = repo.loadReport("home").orElseThrow();
		assertThat(loaded.getGeneratedAt()).isEqualTo(T0);
		assertThat(loaded.getDatasets()).hasSize(1);
		assertThat(loaded.getDatasets().get(0).getValues()).hasSize(2);
		assertThat(loaded.getDatasets().get(0).getValues().get(0).getUncertainty().getLeadTime()).isEqualTo(Duration.ofHours(8));
		assertThat(EcoreUtil.equals(report, loaded)).isTrue();
		assertThat(repo.loadReport("other")).isEmpty();
	}

	@Test
	void archiveIsAppendOnlyWindowedAndNewestFirst() {
		Instant i1 = T0, i2 = T0.plus(Duration.ofHours(6)), i3 = T0.plus(Duration.ofHours(12));
		repo.archive("home", dataset("MOSMIX_L", i2));
		repo.archive("home", dataset("MOSMIX_L", i1));
		repo.archive("home", dataset("MOSMIX_L", i3));
		repo.archive("home", dataset("ICON-D2", i2));
		// same issue second twice: both kept
		repo.archive("home", dataset("MOSMIX_L", i1));

		assertThat(tmp.resolve("weather/archive/home/dwd/MOSMIX_L/20261003T060000Z.xmi")).isRegularFile();
		assertThat(tmp.resolve("weather/archive/home/dwd/MOSMIX_L/20261003T060000Z-1.xmi")).isRegularFile();

		List<SourceDataset> all = repo.loadArchive("home", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX);
		assertThat(all).extracting(SourceDataset::getIssuedAt).containsExactly(i3, i2, i1, i1);

		List<SourceDataset> window = repo.loadArchive("home", "dwd", "MOSMIX_L", i1, i3);
		assertThat(window).extracting(SourceDataset::getIssuedAt).containsExactly(i2, i1, i1);

		assertThat(repo.loadArchive("home", "dwd", "ICON-D2", Instant.EPOCH, Instant.MAX)).hasSize(1);
		assertThat(repo.loadArchive("home", "dwd", "SIS", Instant.EPOCH, Instant.MAX)).isEmpty();
		assertThat(repo.loadArchive("nobody", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX)).isEmpty();
	}

	@Test
	void evictionRemovesOlderIssuesAcrossProducts() {
		Instant i1 = T0, i2 = T0.plus(Duration.ofHours(6)), i3 = T0.plus(Duration.ofHours(12));
		repo.archive("home", dataset("MOSMIX_L", i1));
		repo.archive("home", dataset("MOSMIX_L", i2));
		repo.archive("home", dataset("ICON-D2", i1));
		repo.archive("home", dataset("ICON-D2", i3));

		assertThat(repo.evictArchive("home", i2)).isEqualTo(2);
		assertThat(repo.loadArchive("home", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX)).extracting(SourceDataset::getIssuedAt).containsExactly(i2);
		assertThat(repo.loadArchive("home", "dwd", "ICON-D2", Instant.EPOCH, Instant.MAX)).extracting(SourceDataset::getIssuedAt).containsExactly(i3);
		assertThat(repo.evictArchive("home", i2)).isZero();
		assertThat(repo.evictArchive("nobody", Instant.MAX)).isZero();
	}

	@Test
	void deleteSiteRemovesEverythingOfTheSite() {
		repo.saveSite(site("home"));
		repo.saveSite(site("other"));
		WeatherReport report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(T0);
		repo.saveReport(report);
		repo.archive("home", dataset("MOSMIX_L", T0));
		repo.archive("other", dataset("MOSMIX_L", T0));

		repo.deleteSite("home");

		assertThat(repo.loadSite("home")).isEmpty();
		assertThat(repo.loadReport("home")).isEmpty();
		assertThat(tmp.resolve("weather/archive/home")).doesNotExist();
		assertThat(repo.loadSite("other")).isPresent();
		assertThat(repo.loadArchive("other", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX)).hasSize(1);
		repo.deleteSite("home"); // no-op
	}

	@Test
	void catalogRoundTrips() {
		StationCatalog catalog = F.createStationCatalog();
		catalog.setProviderId("dwd");
		catalog.setProductId("MOSMIX_L");
		catalog.setRetrievedAt(T0);
		catalog.getStations().add(station("10488", 51.13, 13.75));
		catalog.getStations().add(station("10385", 52.47, 13.40));
		repo.saveCatalog(catalog);

		assertThat(tmp.resolve("weather/catalogs/dwd/MOSMIX_L.xmi")).isRegularFile();
		StationCatalog loaded = repo.loadCatalog("dwd", "MOSMIX_L").orElseThrow();
		assertThat(loaded.getStations()).extracting(Station::getId).containsExactly("10488", "10385");
		assertThat(repo.loadCatalog("dwd", "SIS")).isEmpty();
	}

	@Test
	void corruptFileIsReportedNotSwallowed() throws IOException {
		Files.writeString(tmp.resolve("weather/sites/bad.xmi"), "<not xmi");
		assertThatThrownBy(() -> repo.loadSite("bad")).isInstanceOf(RepositoryException.class).hasMessageContaining("bad.xmi");
		Files.writeString(tmp.resolve("weather/sites/wrong.xmi"),
				"<?xml version=\"1.0\"?><weather:WeatherReport xmlns:weather=\"https://geckoprojects.org/weather/1.0\" siteId=\"x\" generatedAt=\"2026-10-03T06:00:00Z\"/>");
		assertThatThrownBy(() -> repo.loadSite("wrong")).isInstanceOf(RepositoryException.class).hasMessageContaining("Expected one Site");
	}

	@Test
	void rejectsMissingIds() {
		Site noId = F.createSite();
		assertThatThrownBy(() -> repo.saveSite(noId)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> repo.loadSite(" ")).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void sharedResourceSetStaysClean() {
		org.eclipse.emf.ecore.resource.ResourceSet shared = new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		XmiFolderRepository sharing = new XmiFolderRepository(tmp.resolve("shared"), () -> shared);
		sharing.saveSite(site("home"));
		sharing.saveSite(site("other"));
		Site loaded = sharing.loadSite("home").orElseThrow();
		sharing.loadSites();
		sharing.archive("home", dataset("MOSMIX_L", T0));
		sharing.loadArchive("home", "dwd", "MOSMIX_L", Instant.EPOCH, Instant.MAX);

		assertThat(shared.getResources()).as("every operation removes its resource again").isEmpty();
		assertThat(loaded.eResource()).isNull();
		assertThat(EcoreUtil.equals(loaded, site("home"))).isTrue();
	}

	// --- helpers -------------------------------------------------------------------------

	private static Site site(String id) {
		Site site = F.createSite();
		site.setId(id);
		site.setName("Home roof");
		site.setPosition(position(51.05, 13.74));
		site.setTimeZone("Europe/Berlin");
		site.setRegisteredAt(T0);
		site.getAttributes().put("pv.tilt", "35");
		StationBinding sb = F.createStationBinding();
		sb.setProviderId("dwd");
		sb.setProductId("MOSMIX_L");
		sb.setOrigin(BindingOrigin.AUTOMATIC);
		sb.setDistanceMeters(8900);
		sb.setResolvedAt(T0);
		sb.setStation(station("10488", 51.13, 13.75));
		site.getBindings().add(sb);
		return site;
	}

	private static Station station(String id, double lat, double lon) {
		Station s = F.createStation();
		s.setId(id);
		s.setName("Station " + id);
		s.setPosition(position(lat, lon));
		return s;
	}

	private static GeoPosition position(double lat, double lon) {
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(lat);
		p.setLongitude(lon);
		return p;
	}

	private static SourceDataset dataset(String productId, Instant issuedAt) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(productId);
		d.setIssuedAt(issuedAt);
		d.setRetrievedAt(issuedAt.plusSeconds(60));
		d.setExpectedRefresh(Duration.ofHours(6));
		d.setOrigin(Origin.STATION);
		d.setStationId("10488");
		d.setHorizonStart(issuedAt);
		d.setHorizonEnd(issuedAt.plus(Duration.ofHours(240)));
		for (int h = 8; h < 10; h++) {
			MeasuredValue v = F.createMeasuredValue();
			v.setKind(MeasurementKind.AIR_TEMPERATURE);
			v.setLevel(Level.GROUND_2M);
			v.setValidAt(issuedAt.plus(Duration.ofHours(h)));
			v.setValue(10 + h);
			v.setUnit("Cel");
			Provenance p = F.createProvenance();
			p.setProviderId("dwd");
			p.setProductId(productId);
			p.setIssuedAt(issuedAt);
			p.setOrigin(Origin.STATION);
			p.setStationId("10488");
			v.setProvenance(p);
			Uncertainty u = F.createUncertainty();
			u.setQuality(Quality.FORECAST);
			u.setLeadTime(Duration.ofHours(h));
			v.setUncertainty(u);
			d.getValues().add(v);
		}
		return d;
	}

}
