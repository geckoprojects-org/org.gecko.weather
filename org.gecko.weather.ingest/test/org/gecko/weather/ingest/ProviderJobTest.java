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
package org.gecko.weather.ingest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.gecko.weather.api.IngestControl.IngestStatus;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.SourceStates;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.repository.file.XmiFolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * One job against a scripted provider, a recording sink and the real file repository for the state.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class ProviderJobTest {

	private static final Instant T0 = Instant.parse("2026-10-03T09:00:00Z");
	private static final URI URI_A = URI.create("https://example.org/a.kmz");
	private static final WeatherFactory F = WeatherFactory.eINSTANCE;

	@TempDir
	Path tmp;

	private XmiFolderRepository repo;
	private MutableClock clock;
	private final List<String> replaced = new ArrayList<>();
	private final List<Site> sites = new ArrayList<>();

	private final SiteRegistry registry = new SiteRegistry() {
		@Override
		public Site register(SiteRegistration registration) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Optional<Site> get(String siteId) {
			return sites.stream().filter(s -> s.getId().equals(siteId)).findFirst();
		}

		@Override
		public List<Site> list() {
			return List.copyOf(sites);
		}

		@Override
		public Site rebind(String siteId) {
			rebound.add(siteId);
			Site site = get(siteId).orElseThrow();
			if (rebindable.contains(siteId)) {
				site.getBindings().add(binding());
			}
			return site;
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

	private final List<String> rebound = new ArrayList<>();
	private final Set<String> rebindable = new java.util.HashSet<>();

	private final WeatherDataSink sink = new WeatherDataSink() {
		@Override
		public void replace(String siteId, SourceDataset dataset) {
			if ("gone".equals(siteId)) {
				throw new UnknownSiteException(siteId);
			}
			replaced.add(siteId + ":" + dataset.getProductId() + "@" + dataset.getIssuedAt());
		}

		@Override
		public void append(String siteId, SourceDataset partial) {
			appended.add(siteId + ":" + partial.getProductId() + "@" + partial.getIssuedAt());
		}
	};
	private final List<String> appended = new ArrayList<>();

	@BeforeEach
	void setUp() {
		repo = new XmiFolderRepository(tmp);
		clock = new MutableClock(T0);
		sites.add(site("home", true, true));
		sites.add(site("inactive", false, true));
		sites.add(site("unbound", true, false));
	}

	@Test
	void successfulRunAppliesDatasetsAndPersistsState() {
		List<FetchRequest> requests = new ArrayList<>();
		WeatherProvider provider = provider(request -> {
			requests.add(request);
			SourceDataset ds = dataset(T0.minusSeconds(3600));
			return new FetchResult.Fetched(Map.of("home", List.of(ds)),
					request.state().with(URI_A, Optional.of("\"v1\""), Optional.empty()), Map.of("rejected-elements", 2));
		});
		ProviderJob job = new ProviderJob(provider, registry, sink, repo, IngestSettings.DEFAULTS, clock);
		assertThat(job.nextDelay()).isEqualTo(IngestSettings.DEFAULTS.initialDelay());

		job.run();

		assertThat(requests).hasSize(1);
		assertThat(requests.get(0).sites()).extracting(sb -> sb.site().getId()).containsExactly("home");
		assertThat(requests.get(0).unconditional()).as("no report yet → must be fetched regardless of state").containsExactly("home");
		assertThat(rebound).as("the unbound active site was offered a rebind, the inactive one not").containsExactly("unbound");
		assertThat(requests.get(0).state()).isEqualTo(SourceState.EMPTY);
		assertThat(replaced).containsExactly("home:MOSMIX_L@" + T0.minusSeconds(3600));
		IngestStatus status = job.status();
		assertThat(status.lastRun()).contains(T0);
		assertThat(status.lastSuccess()).contains(T0);
		assertThat(status.lastChange()).contains(T0);
		assertThat(status.consecutiveFailures()).isZero();
		assertThat(status.lastError()).isEmpty();
		assertThat(status.nextRun()).contains(T0.plus(IngestSettings.DEFAULTS.pollInterval()));
		assertThat(status.running()).isFalse();

		// the state survived into the repository and a new job starts from it
		assertThat(repo.loadSourceState("dwd", "MOSMIX_L")).isPresent();
		assertThat(SourceStates.fromRecord(repo.loadSourceState("dwd", "MOSMIX_L").orElseThrow()).entity(URI_A))
				.map(SourceState.Entity::etag).contains(Optional.of("\"v1\""));
		// the site still has no report in the repository (the recording sink stores nothing) → still unconditional
		ProviderJob restarted = new ProviderJob(provider, registry, sink, repo, IngestSettings.DEFAULTS, clock);
		restarted.run();
		assertThat(requests.get(1).state().entity(URI_A)).isPresent();
		assertThat(requests.get(1).unconditional()).containsExactly("home");

		// once the report holds a dataset for the bound station, the fetch may be conditional
		org.gecko.weather.model.weather.WeatherReport report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(T0);
		report.getDatasets().add(withStation(dataset(T0), "10488"));
		repo.saveReport(report);
		restarted.run();
		assertThat(requests.get(2).unconditional()).isEmpty();
	}

	@Test
	void aStreamProviderIsAppendedNotReplaced() {
		WeatherProvider stream = new WeatherProvider() {
			private final WeatherProvider base = provider(request -> new FetchResult.Fetched(
					Map.of("home", List.of(dataset(T0.minusSeconds(900)))), request.state()));

			@Override
			public String providerId() {
				return base.providerId();
			}

			@Override
			public String productId() {
				return base.productId();
			}

			@Override
			public org.gecko.weather.model.weather.Origin origin() {
				return base.origin();
			}

			@Override
			public Delivery delivery() {
				return Delivery.STREAM;
			}

			@Override
			public java.time.Duration expectedRefresh() {
				return base.expectedRefresh();
			}

			@Override
			public java.util.Set<org.gecko.weather.model.weather.MeasurementKind> provides() {
				return base.provides();
			}

			@Override
			public String licence() {
				return base.licence();
			}

			@Override
			public String attribution() {
				return base.attribution();
			}

			@Override
			public org.gecko.weather.api.spi.SiteBindingResolver bindingResolver() {
				return base.bindingResolver();
			}

			@Override
			public FetchResult fetch(FetchRequest request) throws java.io.IOException {
				return base.fetch(request);
			}
		};
		new ProviderJob(stream, registry, sink, repo, IngestSettings.DEFAULTS, clock).run();
		assertThat(appended).containsExactly("home:MOSMIX_L@" + T0.minusSeconds(900));
		assertThat(replaced).isEmpty();
	}

	@Test
	void unchangedIsASuccessWithoutChange() {
		ProviderJob job = new ProviderJob(provider(r -> new FetchResult.Unchanged()), registry, sink, repo,
				IngestSettings.DEFAULTS, clock);
		job.run();
		assertThat(replaced).isEmpty();
		assertThat(job.status().lastSuccess()).contains(T0);
		assertThat(job.status().lastChange()).isEmpty();
		assertThat(repo.loadSourceState("dwd", "MOSMIX_L")).isEmpty();
	}

	@Test
	void transportFailureBacksOffExponentiallyAndRecovers() {
		int[] failures = { 3 };
		WeatherProvider provider = provider(r -> {
			if (failures[0]-- > 0) {
				throw new IOException("HTTP 503");
			}
			return new FetchResult.Unchanged();
		});
		IngestSettings settings = new IngestSettings(Duration.ofMinutes(20), Duration.ZERO, Duration.ofMinutes(1), Duration.ofMinutes(3));
		ProviderJob job = new ProviderJob(provider, registry, sink, repo, settings, clock);

		job.run();
		assertThat(job.status().consecutiveFailures()).isEqualTo(1);
		assertThat(job.status().lastError()).contains("transport: HTTP 503");
		assertThat(job.nextDelay()).isEqualTo(Duration.ofMinutes(1));
		clock.advance(Duration.ofMinutes(1));
		job.run();
		assertThat(job.nextDelay()).isEqualTo(Duration.ofMinutes(2));
		clock.advance(Duration.ofMinutes(2));
		job.run();
		assertThat(job.status().consecutiveFailures()).isEqualTo(3);
		assertThat(job.nextDelay()).as("capped at backoffMax").isEqualTo(Duration.ofMinutes(3));
		clock.advance(Duration.ofMinutes(3));
		job.run();
		assertThat(job.status().consecutiveFailures()).isZero();
		assertThat(job.status().lastError()).isEmpty();
		assertThat(job.nextDelay()).isEqualTo(Duration.ofMinutes(20));
	}

	@Test
	void contentFailureWaitsForTheNextPollNotABackoff() {
		ProviderJob job = new ProviderJob(provider(r -> {
			throw new FetchException("not KML");
		}), registry, sink, repo, IngestSettings.DEFAULTS, clock);
		job.run();
		assertThat(job.status().lastError()).contains("content: not KML");
		assertThat(job.status().consecutiveFailures()).isEqualTo(1);
		assertThat(job.nextDelay()).isEqualTo(IngestSettings.DEFAULTS.pollInterval());
	}

	@Test
	void noBoundSiteMeansNoFetch() {
		sites.clear();
		sites.add(site("unbound", true, false));
		List<FetchRequest> requests = new ArrayList<>();
		ProviderJob job = new ProviderJob(provider(r -> {
			requests.add(r);
			return new FetchResult.Unchanged();
		}), registry, sink, repo, IngestSettings.DEFAULTS, clock);
		job.run();
		assertThat(requests).isEmpty();
		assertThat(rebound).containsExactly("unbound");
		assertThat(job.status().lastSuccess()).contains(T0);
	}

	@Test
	void aSiteRegisteredBeforeTheProviderIsBoundOnFirstSight() {
		sites.clear();
		sites.add(site("late", true, false));
		rebindable.add("late");
		List<FetchRequest> requests = new ArrayList<>();
		ProviderJob job = new ProviderJob(provider(r -> {
			requests.add(r);
			return new FetchResult.Unchanged();
		}), registry, sink, repo, IngestSettings.DEFAULTS, clock);
		job.run();
		assertThat(requests).singleElement().satisfies(r -> assertThat(r.sites()).extracting(sb -> sb.site().getId()).containsExactly("late"));
	}

	@Test
	void aSiteRemovedDuringTheRunDoesNotFailTheRun() {
		sites.add(site("gone", true, true));
		ProviderJob job = new ProviderJob(provider(r -> new FetchResult.Fetched(
				Map.of("home", List.of(dataset(T0)), "gone", List.of(dataset(T0))), r.state())), registry, sink, repo,
				IngestSettings.DEFAULTS, clock);
		job.run();
		assertThat(replaced).hasSize(1);
		assertThat(job.status().lastError()).isEmpty();
	}

	@Test
	void backoffTable() {
		IngestSettings s = new IngestSettings(Duration.ofMinutes(20), Duration.ZERO, Duration.ofMinutes(1), Duration.ofHours(1));
		assertThat(s.backoff(0)).isEqualTo(Duration.ofMinutes(20));
		assertThat(s.backoff(1)).isEqualTo(Duration.ofMinutes(1));
		assertThat(s.backoff(2)).isEqualTo(Duration.ofMinutes(2));
		assertThat(s.backoff(5)).isEqualTo(Duration.ofMinutes(16));
		assertThat(s.backoff(7)).isEqualTo(Duration.ofHours(1));
		assertThat(s.backoff(50)).isEqualTo(Duration.ofHours(1));
	}

	// --- fixtures --------------------------------------------------------------------------

	private static WeatherProvider provider(Fetch fetch) {
		return new WeatherProvider() {
			@Override
			public String providerId() {
				return "dwd";
			}

			@Override
			public String productId() {
				return "MOSMIX_L";
			}

			@Override
			public Origin origin() {
				return Origin.STATION;
			}

			@Override
			public Duration expectedRefresh() {
				return Duration.ofHours(6);
			}

			@Override
			public Set<MeasurementKind> provides() {
				return Set.of(MeasurementKind.AIR_TEMPERATURE);
			}

			@Override
			public String licence() {
				return "GeoNutzV";
			}

			@Override
			public String attribution() {
				return "DWD";
			}

			@Override
			public SiteBindingResolver bindingResolver() {
				throw new UnsupportedOperationException();
			}

			@Override
			public FetchResult fetch(FetchRequest request) throws IOException {
				return fetch.apply(request);
			}
		};
	}

	@FunctionalInterface
	private interface Fetch {
		FetchResult apply(FetchRequest request) throws IOException;
	}

	private static Site site(String id, boolean active, boolean bound) {
		Site site = F.createSite();
		site.setId(id);
		site.setActive(active);
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(51.05);
		p.setLongitude(13.74);
		site.setPosition(p);
		site.setTimeZone("Europe/Berlin");
		if (bound) {
			site.getBindings().add(binding());
		}
		return site;
	}

	private static StationBinding binding() {
		StationBinding b = F.createStationBinding();
		b.setProviderId("dwd");
		b.setProductId("MOSMIX_L");
		b.setOrigin(BindingOrigin.AUTOMATIC);
		Station s = F.createStation();
		s.setId("10488");
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(51.13);
		p.setLongitude(13.75);
		s.setPosition(p);
		b.setStation(s);
		return b;
	}

	private static SourceDataset withStation(SourceDataset d, String stationId) {
		d.setStationId(stationId);
		return d;
	}

	private static SourceDataset dataset(Instant issuedAt) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId("MOSMIX_L");
		d.setIssuedAt(issuedAt);
		d.setOrigin(Origin.STATION);
		return d;
	}

	/** A clock the test moves by hand. */
	private static final class MutableClock extends Clock {
		private Instant now;

		MutableClock(Instant start) {
			this.now = start;
		}

		void advance(Duration d) {
			now = now.plus(d);
		}

		@Override
		public ZoneOffset getZone() {
			return ZoneOffset.UTC;
		}

		@Override
		public Clock withZone(java.time.ZoneId zone) {
			return this;
		}

		@Override
		public Instant instant() {
			return now;
		}
	}

	@SuppressWarnings("unused")
	private static <T> Function<T, T> identity() {
		return t -> t;
	}

}
