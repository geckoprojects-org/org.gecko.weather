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
package org.gecko.weather.provider.dwd.mosmix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.io.IOException;
import java.io.InputStream;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Catalogue parsing — including the degrees-and-minutes trap — and nearest-station resolution.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class StationCatalogTest {

	private static final Instant NOW = Instant.parse("2026-10-03T09:00:00Z");
	private static StationCatalog catalog;

	@BeforeAll
	static void parse() throws IOException {
		try (InputStream in = Fixtures.open(Fixtures.CATALOG)) {
			catalog = StationCatalogParser.parse(in, "dwd", "MOSMIX", NOW);
		}
	}

	@Test
	void parsesEveryStationLine() {
		assertThat(catalog.getStations()).hasSize(5995); // 5997 lines minus two header lines
		assertThat(catalog.getRetrievedAt()).isEqualTo(NOW);
		Station erfurt = station("10554");
		assertThat(erfurt.getName()).isEqualTo("ERFURT");
		assertThat(erfurt.getIcaoCode()).isEqualTo("EDDE");
		assertThat(erfurt.getPosition().getElevation()).isEqualTo(315);
		assertThat(station("10487").getIcaoCode()).as("---- means no ICAO code").isNull();
	}

	@Test
	void coordinatesAreDegreesAndMinutesNotDecimals() {
		// catalogue says 50.59 / 10.58; the KML of the same station says 50.98 / 10.97
		GeoPosition erfurt = station("10554").getPosition();
		assertThat(erfurt.getLatitude()).isCloseTo(50.983, within(0.001));
		assertThat(erfurt.getLongitude()).isCloseTo(10.967, within(0.001));
		assertThat(StationCatalogParser.degreesMinutes("-8.40")).isCloseTo(-8.667, within(0.001));
		assertThat(StationCatalogParser.degreesMinutes("70.56")).isCloseTo(70.933, within(0.001));
		assertThat(StationCatalogParser.degreesMinutes("13.5")).as("a single digit is tens of minutes").isCloseTo(13.833, within(0.001));
		assertThat(StationCatalogParser.degreesMinutes("13")).isEqualTo(13.0);
	}

	@Test
	void resolvesNearestStationsRankedByDistance() {
		MosmixBindingResolver resolver = new MosmixBindingResolver("dwd", "MOSMIX_L", () -> catalog, Clock.fixed(NOW, ZoneOffset.UTC));
		Site dresden = site(51.05, 13.74, 118);

		List<SourceBinding> bindings = resolver.resolve(dresden, 3);
		assertThat(bindings).hasSize(3).allMatch(b -> b instanceof StationBinding);
		List<String> ids = bindings.stream().map(b -> ((StationBinding) b).getStation().getId()).toList();
		// the catalogue is dense: automatic stations sit closer to the city than the airport (10488, ~9 km)
		assertThat(ids).doesNotContain("10385");
		assertThat(bindings).allMatch(b -> b.getDistanceMeters() < 20_000);
		assertThat(bindings.get(0).getDistanceMeters()).isLessThan(bindings.get(1).getDistanceMeters());
		assertThat(bindings.get(1).getDistanceMeters()).isLessThan(bindings.get(2).getDistanceMeters());
		assertThat(resolver.resolve(dresden, 10).stream().map(b -> ((StationBinding) b).getStation().getId())).contains("10488");
		assertThat(bindings.get(0).getOrigin()).isEqualTo(BindingOrigin.AUTOMATIC);
		assertThat(bindings.get(0).getResolvedAt()).isEqualTo(NOW);
		assertThat(bindings.get(0).isSetElevationDeltaMeters()).isTrue();
		assertThat(bindings.get(0).getProductId()).isEqualTo("MOSMIX_L");

		assertThat(resolver.resolve(dresden, 1)).hasSize(1);
		assertThat(resolver.resolve(site(0.0, -30.0, 0), 3)).as("mid-Atlantic: nothing within 150 km").isEmpty();
	}

	@Test
	void bindsAnExplicitStation() {
		MosmixBindingResolver resolver = new MosmixBindingResolver("dwd", "MOSMIX_L", () -> catalog, Clock.systemUTC());
		Site dresden = site(51.05, 13.74, 118);
		Optional<SourceBinding> berlin = resolver.bind(dresden, "10385");
		assertThat(berlin).isPresent();
		assertThat(berlin.get().getDistanceMeters()).isBetween(145_000.0, 152_000.0); // 52°23′ N 13°31′ E
		assertThat(((StationBinding) berlin.get()).getStation().getName()).isEqualTo("BERLIN-BRANDENBURG");
		assertThat(resolver.bind(dresden, "99999")).isEmpty();
	}

	private static Station station(String id) {
		return catalog.getStations().stream().filter(s -> id.equals(s.getId())).findFirst().orElseThrow();
	}

	static Site site(double lat, double lon, double elevation) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Site site = f.createSite();
		site.setId("site");
		GeoPosition p = f.createGeoPosition();
		p.setLatitude(lat);
		p.setLongitude(lon);
		p.setElevation(elevation);
		site.setPosition(p);
		site.setTimeZone("Europe/Berlin");
		return site;
	}

}
