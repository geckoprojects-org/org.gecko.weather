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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.transport.ByteSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The provider end to end against the recorded KMZ: binding → one download per station → datasets
 * for every site bound to it → conditional state.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class MosmixProviderTest {

	private static final Instant NOW = Instant.parse("2026-10-03T09:00:00Z");
	private static final Instant ISSUED = Instant.parse("2024-09-26T09:00:00Z");
	private static final URI ERFURT_URI = URI.create(
			"https://opendata.dwd.de/weather/local_forecasts/mos/MOSMIX_L/single_stations/10554/kml/MOSMIX_L_LATEST_10554.kmz");

	private static StationCatalog catalog;

	private final List<URI> requested = new ArrayList<>();
	private MosmixProvider provider;

	@BeforeAll
	static void loadCatalog() throws IOException {
		try (InputStream in = Fixtures.open(Fixtures.CATALOG)) {
			catalog = StationCatalogParser.parse(in, "dwd", "MOSMIX", NOW);
		}
	}

	@BeforeEach
	void setUp() {
		ByteSource fixtures = (uri, validators) -> {
			requested.add(uri);
			if (!ERFURT_URI.equals(uri)) {
				throw new IOException("HTTP 404 for " + uri);
			}
			if (validators.flatMap(SourceState.Entity::etag).filter("\"v1\""::equals).isPresent()) {
				return new ByteSource.Unchanged();
			}
			return new ByteSource.Content(new ByteArrayInputStream(Fixtures.bytes(Fixtures.KMZ_10554)),
					new SourceState.Entity(Optional.of("\"v1\""), Optional.empty()));
		};
		provider = new MosmixProvider(MosmixProvider.Settings.defaults(), fixtures, MosmixKmlDecoder::plainResourceSet,
				() -> catalog, Clock.fixed(NOW, ZoneOffset.UTC));
	}

	@Test
	void describesItself() {
		assertThat(provider.providerId()).isEqualTo("dwd");
		assertThat(provider.productId()).isEqualTo("MOSMIX_L");
		assertThat(provider.origin()).isEqualTo(Origin.STATION);
		assertThat(provider.expectedRefresh()).isEqualTo(Duration.ofHours(6));
		assertThat(provider.provides()).contains(MeasurementKind.AIR_TEMPERATURE, MeasurementKind.CLOUD_COVER);
		assertThat(provider.bindingResolver().productId()).isEqualTo("MOSMIX_L");
		assertThat(MosmixProvider.Settings.defaults().kmzUri("10554")).isEqualTo(ERFURT_URI);
	}

	@Test
	void oneDownloadServesEverySiteBoundToTheStation() throws IOException {
		Site weimar = StationCatalogTest.site(50.98, 11.33, 208);
		weimar.setId("weimar");
		Site gotha = StationCatalogTest.site(50.95, 10.70, 300);
		gotha.setId("gotha");
		SourceBinding weimarErfurt = provider.bindingResolver().bind(weimar, "10554").orElseThrow();
		SourceBinding gothaErfurt = provider.bindingResolver().bind(gotha, "10554").orElseThrow();

		FetchRequest request = new FetchRequest(List.of(new SiteBindings(weimar, List.of(weimarErfurt)),
				new SiteBindings(gotha, List.of(gothaErfurt))), SourceState.EMPTY, NOW);
		FetchResult result = provider.fetch(request);

		assertThat(requested).containsExactly(ERFURT_URI);
		assertThat(result).isInstanceOf(FetchResult.Fetched.class);
		FetchResult.Fetched fetched = (FetchResult.Fetched) result;
		assertThat(fetched.datasets()).containsOnlyKeys("weimar", "gotha");
		assertThat(fetched.datasetCount()).isEqualTo(2);
		assertThat(fetched.state().entity(ERFURT_URI)).map(SourceState.Entity::etag).contains(Optional.of("\"v1\""));
		assertThat(fetched.skipped()).isEmpty();

		SourceDataset ds = fetched.datasets().get("weimar").get(0);
		assertThat(ds.getProviderId()).isEqualTo("dwd");
		assertThat(ds.getProductId()).isEqualTo("MOSMIX_L");
		assertThat(ds.getIssuedAt()).isEqualTo(ISSUED);
		assertThat(ds.getModelRun()).isEqualTo(Instant.parse("2024-09-26T00:00:00Z"));
		assertThat(ds.getRetrievedAt()).isEqualTo(NOW);
		assertThat(ds.getExpectedRefresh()).isEqualTo(Duration.ofHours(6));
		assertThat(ds.getHorizonStart()).isEqualTo(Instant.parse("2024-09-26T10:00:00Z"));
		assertThat(ds.getHorizonEnd()).isEqualTo(ISSUED.plus(Duration.ofHours(247)));
		assertThat(ds.getOrigin()).isEqualTo(Origin.STATION);
		assertThat(ds.getStationId()).isEqualTo("10554");
		assertThat(ds.getDistanceMeters()).isEqualTo(weimarErfurt.getDistanceMeters());
		assertThat(ds.getLicence()).isEqualTo("GeoNutzV");

		// the two sites get the same values with their own distances
		SourceDataset gothaDs = fetched.datasets().get("gotha").get(0);
		assertThat(gothaDs.getDistanceMeters()).isNotEqualTo(ds.getDistanceMeters());
		assertThat(gothaDs.getValues()).hasSameSizeAs(ds.getValues());
	}

	@Test
	void valuesAreCanonicalWithFullProvenance() throws IOException {
		Site weimar = StationCatalogTest.site(50.98, 11.33, 208);
		weimar.setId("weimar");
		SourceBinding binding = provider.bindingResolver().bind(weimar, "10554").orElseThrow();
		FetchResult.Fetched fetched = (FetchResult.Fetched) provider
				.fetch(new FetchRequest(List.of(new SiteBindings(weimar, List.of(binding))), SourceState.EMPTY, NOW));
		SourceDataset ds = fetched.datasets().get("weimar").get(0);

		List<MeasuredValue> temps = ds.getValues().stream()
				.filter(v -> v.getKind() == MeasurementKind.AIR_TEMPERATURE && v.getLevel() == Level.GROUND_2M
						&& v.getStatistic() == Statistic.INSTANT)
				.toList();
		assertThat(temps).hasSize(247);
		MeasuredValue first = temps.get(0);
		assertThat(first.getValidAt()).isEqualTo(Instant.parse("2024-09-26T10:00:00Z"));
		assertThat(first.getValue()).isCloseTo(16.1, within(1e-4)); // 289.25 K, published as a float
		assertThat(first.getUnit()).isEqualTo("Cel");
		assertThat(first.getProvenance().getSourceElement()).isEqualTo("TTT");
		assertThat(first.getProvenance().getStationId()).isEqualTo("10554");
		assertThat(first.getProvenance().getOrigin()).isEqualTo(Origin.STATION);
		assertThat(first.getProvenance().getIssuedAt()).isEqualTo(ISSUED);
		assertThat(first.getProvenance().getModelRun()).isEqualTo(Instant.parse("2024-09-26T00:00:00Z"));
		assertThat(first.getProvenance().getDistanceMeters()).isEqualTo(binding.getDistanceMeters());
		assertThat(first.getProvenance().getAttribution()).isEqualTo("Datenbasis: Deutscher Wetterdienst");
		assertThat(first.getUncertainty().getQuality()).isEqualTo(Quality.FORECAST);
		assertThat(first.getUncertainty().getLeadTime()).isEqualTo(Duration.ofHours(1));
		assertThat(first.getUncertainty().getSpatialMeters()).isEqualTo(binding.getDistanceMeters());

		MeasuredValue radiation = ds.getValues().stream().filter(v -> v.getKind() == MeasurementKind.GLOBAL_RADIATION).findFirst().orElseThrow();
		assertThat(radiation.getValue()).isCloseTo(175.0, within(1e-4)); // 630 kJ/m² per hour
		assertThat(radiation.getStatistic()).isEqualTo(Statistic.MEAN);
		assertThat(radiation.getPeriod()).isEqualTo(Duration.ofHours(1));

		MeasuredValue ww = ds.getValues().stream().filter(v -> v.getKind() == MeasurementKind.SIGNIFICANT_WEATHER).findFirst().orElseThrow();
		assertThat(ww.isSetValue()).isFalse();
		assertThat(ww.getCode()).isEqualTo(61);

		// FXh25 has 247 slots but values in 12-hour windows only; missing ones are not values
		List<MeasuredValue> gustProb = ds.getValues().stream()
				.filter(v -> "FXh25".equals(v.getProvenance().getSourceElement())).toList();
		assertThat(gustProb).hasSizeLessThan(247).isNotEmpty();
		assertThat(gustProb.get(0).getValue()).isCloseTo(77.0, within(1e-4));
		assertThat(gustProb.get(0).getThreshold()).isEqualTo(25.0);
		assertThat(gustProb.get(0).getThresholdUnit()).isEqualTo("[kn_i]");

		// only mapped elements leak through, and most of the mapping is exercised by the recorded file
		assertThat(ds.getValues()).allMatch(v -> MosmixElements.isMapped(v.getProvenance().getSourceElement()));
		assertThat(ds.getValues().stream().map(v -> v.getProvenance().getSourceElement()).distinct().count())
				.isGreaterThan(40);
	}

	@Test
	void unchangedWhenValidatorsMatch() throws IOException {
		Site weimar = StationCatalogTest.site(50.98, 11.33, 208);
		weimar.setId("weimar");
		SourceBinding binding = provider.bindingResolver().bind(weimar, "10554").orElseThrow();
		SourceState known = SourceState.EMPTY.with(ERFURT_URI, Optional.of("\"v1\""), Optional.empty());

		FetchResult result = provider.fetch(new FetchRequest(List.of(new SiteBindings(weimar, List.of(binding))), known, NOW));

		assertThat(result).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).containsExactly(ERFURT_URI);

		// a site named unconditional is served even though the validators would say 304
		FetchResult again = provider.fetch(new FetchRequest(List.of(new SiteBindings(weimar, List.of(binding))), known, NOW, java.util.Set.of("weimar")));
		assertThat(again).isInstanceOf(FetchResult.Fetched.class);
	}

	@Test
	void sitesWithoutMosmixBindingsAreIgnored() throws IOException {
		Site site = StationCatalogTest.site(50.98, 11.33, 208);
		SourceBinding other = provider.bindingResolver().bind(site, "10554").orElseThrow();
		other.setProductId("SOMETHING_ELSE");
		FetchResult result = provider.fetch(new FetchRequest(List.of(new SiteBindings(site, List.of(other))), SourceState.EMPTY, NOW));
		assertThat(result).isInstanceOf(FetchResult.Unchanged.class);
		assertThat(requested).isEmpty();
	}

}
