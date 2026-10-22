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
package org.gecko.weather.provider.ecowitt;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherFactory;
import org.junit.jupiter.api.Test;

/**
 * The station component as a push source: filtering, dispatch to bound sites, bindings.
 */
class EcowittStationTest {

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;
	private static final Instant NOW = Instant.parse("2026-10-22T13:41:32Z");

	private final List<String> appended = new ArrayList<>();
	private final List<SourceDataset> datasets = new ArrayList<>();
	private final List<Site> sites = new ArrayList<>();

	private final WeatherDataSink sink = new WeatherDataSink() {
		@Override
		public void replace(String siteId, SourceDataset dataset) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void append(String siteId, SourceDataset partial) {
			appended.add(siteId);
			datasets.add(partial);
		}
	};

	private final SiteRegistry registry = new SiteRegistry() {
		@Override
		public Site register(SiteRegistration registration) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Optional<Site> get(String siteId) {
			return sites.stream().filter(s -> siteId.equals(s.getId())).findFirst();
		}

		@Override
		public List<Site> list() {
			return List.copyOf(sites);
		}

		@Override
		public Site rebind(String siteId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public SourceBinding assign(String siteId, String providerId, String productId, String locationId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deactivate(String siteId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove(String siteId) {
			throw new UnsupportedOperationException();
		}
	};

	private EcowittStation station(String deviceId, String siteId) {
		return new EcowittStation(sink, registry, TestSupport.resourceSet(), Clock.fixed(NOW, ZoneOffset.UTC),
				TestSupport.config(StationModel.WS90, deviceId, siteId));
	}

	/** A clock the test moves by hand. */
	private static final class StepClock extends Clock {
		Instant now = NOW;

		@Override
		public ZoneId getZone() {
			return ZoneOffset.UTC;
		}

		@Override
		public Clock withZone(ZoneId zone) {
			return this;
		}

		@Override
		public Instant instant() {
			return now;
		}
	}

	@Test
	void samplesAtTheReportingIntervalWithTheGustAsItsMaximum() throws IOException {
		site("home", true, "75459");
		StepClock clock = new StepClock();
		EcowittStation s = new EcowittStation(sink, registry, TestSupport.resourceSet(), clock,
				TestSupport.config(StationModel.WS90, "75459", "", "PT1M"));
		List<String> lines = TestSupport.lines("ws90-stream.jsonl");
		s.onLine(lines.get(0)); // gust 1.4 — appended, starts the interval
		clock.now = NOW.plusSeconds(9);
		s.onLine(lines.get(1).replace("\"wind_max_m_s\":1.3", "\"wind_max_m_s\":7.5")); // inside: only the gust is kept
		clock.now = NOW.plusSeconds(18);
		s.onLine(lines.get(1)); // inside
		assertThat(appended).containsExactly("home");
		clock.now = NOW.plusSeconds(60);
		s.onLine(lines.get(1)); // the first telegram after the interval
		assertThat(appended).containsExactly("home", "home");
		MeasuredValue gust = datasets.get(1).getValues().stream().filter(v -> v.getKind() == MeasurementKind.WIND_GUST).findFirst().orElseThrow();
		assertThat(gust.getValue()).isEqualTo(7.5);
		assertThat(gust.getPeriod()).isEqualTo(Duration.ofMinutes(1));
		assertThat(datasets.get(1).getIssuedAt()).isEqualTo(NOW.plusSeconds(60));
		MeasuredValue first = datasets.get(0).getValues().stream().filter(v -> v.getKind() == MeasurementKind.WIND_GUST).findFirst().orElseThrow();
		assertThat(first.getValue()).isEqualTo(1.4);
	}

	@Test
	void aZeroIntervalAppendsEveryTelegram() throws IOException {
		site("home", true, "75459");
		EcowittStation s = new EcowittStation(sink, registry, TestSupport.resourceSet(), Clock.fixed(NOW, ZoneOffset.UTC),
				TestSupport.config(StationModel.WS90, "75459", "", "PT0S"));
		List<String> lines = TestSupport.lines("ws90-stream.jsonl");
		s.onLine(lines.get(0));
		s.onLine(lines.get(1));
		assertThat(appended).hasSize(2);
		assertThat(datasets.get(1).getValues().stream().filter(v -> v.getKind() == MeasurementKind.WIND_GUST).findFirst().orElseThrow().isSetPeriod()).isFalse();
	}

	private Site site(String id, boolean active, String... boundDevices) {
		Site s = F.createSite();
		s.setId(id);
		s.setName(id);
		s.setActive(active);
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(51.05);
		p.setLongitude(13.74);
		s.setPosition(p);
		for (String device : boundDevices) {
			StationBinding b = F.createStationBinding();
			b.setProviderId("ecowitt");
			b.setProductId("WS90");
			b.setOrigin(BindingOrigin.MANUAL);
			b.setStation(F.createStation());
			b.getStation().setId(device);
			s.getBindings().add(b);
		}
		sites.add(s);
		return s;
	}

	@Test
	void appendsToEverySiteBoundToTheDevice() throws IOException {
		site("home", true, "75459");
		site("eltern", true, "99999");
		site("old", false, "75459");
		EcowittStation s = station("75459", "");
		s.onLine(TestSupport.lines("ws90-stream.jsonl").get(0));
		assertThat(appended).containsExactly("home");
		assertThat(datasets.get(0).getValues()).extracting(MeasuredValue::getKind).contains(MeasurementKind.AIR_TEMPERATURE,
				MeasurementKind.CAPACITOR_VOLTAGE);
		assertThat(datasets.get(0).getIssuedAt()).isEqualTo(NOW);
	}

	@Test
	void ignoresOtherModelsAndOtherDevices() throws IOException {
		site("home", true, "75459", "42");
		EcowittStation s = station("75459", "");
		List<String> lines = TestSupport.lines("ws90-stream.jsonl");
		s.onLine(lines.get(2)); // Nexus-TH
		s.onLine(lines.get(0).replace("75459", "42")); // a second WS90
		s.onLine("garbage");
		assertThat(appended).isEmpty();
		s.onLine(lines.get(1));
		assertThat(appended).containsExactly("home");
	}

	@Test
	void acceptsAnyDeviceWhenNoneIsConfigured() throws IOException {
		site("home", true, "42");
		EcowittStation s = station("", "");
		s.onLine(TestSupport.lines("ws90-stream.jsonl").get(0).replace("75459", "42"));
		assertThat(appended).containsExactly("home");
	}

	@Test
	void anUnboundDeviceGoesNowhere() throws IOException {
		site("home", true);
		station("75459", "").onLine(TestSupport.lines("ws90-stream.jsonl").get(0));
		assertThat(appended).isEmpty();
	}

	@Test
	void bindsTheConfiguredSiteAutomatically() {
		Site home = site("home", true);
		Site other = site("eltern", true);
		EcowittStation s = station("75459", "home");
		assertThat(s.providerId()).isEqualTo("ecowitt");
		assertThat(s.productId()).isEqualTo("WS90");
		List<SourceBinding> bindings = s.resolve(home, 3);
		assertThat(bindings).hasSize(1);
		StationBinding b = (StationBinding) bindings.get(0);
		assertThat(b.getOrigin()).isEqualTo(BindingOrigin.AUTOMATIC);
		assertThat(b.getStation().getId()).isEqualTo("75459");
		assertThat(b.getStation().getName()).isEqualTo("Ecowitt WS90 75459");
		assertThat(b.getStation().getPosition().getLatitude()).isEqualTo(51.05);
		assertThat(b.getDistanceMeters()).isEqualTo(0.0);
		assertThat(b.getResolvedAt()).isEqualTo(NOW);
		assertThat(s.resolve(other, 3)).isEmpty();
		assertThat(s.resolve(home, 0)).isEmpty();
	}

	@Test
	void noAutomaticBindingWithoutSiteOrDevice() {
		Site home = site("home", true);
		assertThat(station("75459", "").resolve(home, 3)).isEmpty();
		assertThat(station("", "home").resolve(home, 3)).isEmpty();
	}

	@Test
	void bindsByHandToTheConfiguredDeviceOnly() {
		Site home = site("home", true);
		EcowittStation s = station("75459", "");
		assertThat(s.bind(home, "75459")).isPresent().get().satisfies(b -> assertThat(b.getOrigin()).isEqualTo(BindingOrigin.MANUAL));
		assertThat(s.bind(home, "42")).isEmpty();
		assertThat(station("", "").bind(home, "42")).isPresent();
		assertThat(station("", "").bind(home, " ")).isEmpty();
	}
}
