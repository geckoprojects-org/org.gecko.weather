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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.repository.file.XmiFolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class SiteRegistryImplTest {

	@TempDir
	Path tmp;

	private XmiFolderRepository repo;
	private final List<SiteBindingResolver> resolvers = new ArrayList<>();
	private SiteRegistryImpl registry;

	@BeforeEach
	void setUp() {
		repo = new XmiFolderRepository(tmp);
		resolvers.add(TestData.stationResolver("MOSMIX_L"));
		resolvers.add(TestData.gridResolver());
		registry = new SiteRegistryImpl(repo, () -> resolvers, CoreSettings.DEFAULTS, Clock.fixed(TestData.NOW, ZoneOffset.UTC));
	}

	@Test
	void registerResolvesRankedBindingsAndPersists() {
		Site site = registry.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home").withElevation(118)
				.withAttributes(Map.of("pv.tilt", "35")));

		assertThat(site.getId()).isEqualTo("home");
		assertThat(site.getTimeZone()).isEqualTo("Europe/Berlin");
		assertThat(site.getRegisteredAt()).isEqualTo(TestData.NOW);
		assertThat(site.isActive()).isTrue();
		assertThat(site.getAttributes().get("pv.tilt")).isEqualTo("35");

		List<StationBinding> stations = site.getBindings().stream().filter(StationBinding.class::isInstance)
				.map(StationBinding.class::cast).toList();
		assertThat(stations).extracting(b -> b.getStation().getId()).containsExactly("10488", "10487", "10385");
		assertThat(stations).extracting(SourceBinding::getRank).containsExactly(0, 1, 2);
		assertThat(stations).allMatch(b -> b.getOrigin() == BindingOrigin.AUTOMATIC);
		assertThat(stations.get(0).getDistanceMeters()).isBetween(8_000.0, 10_000.0);
		assertThat(site.getBindings().stream().filter(GridBinding.class::isInstance)).singleElement()
				.satisfies(b -> assertThat(((GridBinding) b).getCell().getI()).isEqualTo(884));

		assertThat(repo.loadSite("home")).isPresent();
		assertThat(registry.list()).extracting(Site::getId).containsExactly("home");
	}

	@Test
	void generatedIdAndExplicitZone() {
		Site site = registry.register(SiteRegistration.of("Somewhere", 48.1, 11.6).withTimeZone(ZoneId.of("Europe/Vienna")));
		assertThat(site.getId()).isNotBlank();
		assertThat(site.getTimeZone()).isEqualTo("Europe/Vienna");
		assertThat(registry.get(site.getId())).isPresent();
	}

	@Test
	void duplicateIdIsRejected() {
		registry.register(SiteRegistration.of("A", 51.05, 13.74).withId("home"));
		assertThatThrownBy(() -> registry.register(SiteRegistration.of("B", 51.05, 13.74).withId("home")))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void maxStationBindingsIsHonoured() {
		SiteRegistryImpl one = new SiteRegistryImpl(repo, () -> resolvers,
				new CoreSettings(ZoneId.of("UTC"), 1, CoreSettings.DEFAULTS.solarStep(), CoreSettings.DEFAULTS.streamWindow()),
				Clock.systemUTC());
		Site site = one.register(SiteRegistration.of("One", 51.05, 13.74).withId("one"));
		assertThat(site.getBindings().stream().filter(StationBinding.class::isInstance)).hasSize(1);
	}

	@Test
	void manualAssignmentBecomesRankZeroAndSurvivesRebind() {
		registry.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home"));

		SourceBinding manual = registry.assign("home", "dwd", "MOSMIX_L", "10385");
		assertThat(manual.getOrigin()).isEqualTo(BindingOrigin.MANUAL);
		assertThat(manual.getRank()).isZero();

		Site site = repo.loadSite("home").orElseThrow();
		List<StationBinding> stations = site.getBindings().stream().filter(StationBinding.class::isInstance)
				.map(StationBinding.class::cast).toList();
		assertThat(stations).extracting(b -> b.getStation().getId()).containsExactly("10385", "10488", "10487", "10385");
		assertThat(stations).extracting(SourceBinding::getRank).containsExactly(0, 1, 2, 3);

		// a second manual assignment replaces the first
		registry.assign("home", "dwd", "MOSMIX_L", "10487");
		site = repo.loadSite("home").orElseThrow();
		assertThat(site.getBindings().stream().filter(b -> b.getOrigin() == BindingOrigin.MANUAL)).hasSize(1);

		// rebind keeps the manual one, re-resolves the rest
		Site rebound = registry.rebind("home");
		List<StationBinding> after = rebound.getBindings().stream().filter(StationBinding.class::isInstance)
				.map(StationBinding.class::cast).toList();
		assertThat(after.get(0).getOrigin()).isEqualTo(BindingOrigin.MANUAL);
		assertThat(after.get(0).getStation().getId()).isEqualTo("10487");
		assertThat(after).extracting(SourceBinding::getRank).containsExactly(0, 1, 2, 3);
	}

	@Test
	void assignRejectsUnknownProductOrLocation() {
		registry.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home"));
		assertThatThrownBy(() -> registry.assign("home", "dwd", "SIS", "x")).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("No binding resolver");
		assertThatThrownBy(() -> registry.assign("home", "dwd", "MOSMIX_L", "99999")).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("unknown");
		assertThatThrownBy(() -> registry.assign("nope", "dwd", "MOSMIX_L", "10488")).isInstanceOf(UnknownSiteException.class);
	}

	@Test
	void rebindPicksUpResolversThatAppearedLater() {
		resolvers.clear();
		Site site = registry.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home"));
		assertThat(site.getBindings()).isEmpty();

		resolvers.add(TestData.gridResolver());
		Site rebound = registry.rebind("home");
		assertThat(rebound.getBindings()).hasSize(1);
	}

	@Test
	void deactivateAndRemove() {
		registry.register(SiteRegistration.of("Home roof", 51.05, 13.74).withId("home"));
		registry.deactivate("home");
		assertThat(repo.loadSite("home").orElseThrow().isActive()).isFalse();
		registry.remove("home");
		assertThat(repo.loadSite("home")).isEmpty();
		assertThatThrownBy(() -> registry.remove("home")).isInstanceOf(UnknownSiteException.class);
		assertThatThrownBy(() -> registry.deactivate("home")).isInstanceOf(UnknownSiteException.class);
	}

}
