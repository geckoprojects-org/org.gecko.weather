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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.gecko.weather.api.IngestControl.IngestStatus;
import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.repository.file.XmiFolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * The scheduler actually runs jobs, reschedules them, and {@code runNow} works.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class IngestSchedulerTest {

	@TempDir
	Path tmp;

	private final IngestScheduler scheduler = new IngestScheduler();

	@AfterEach
	void stop() {
		scheduler.shutdown();
	}

	@Test
	void schedulesRepeatedRunsAndSupportsRunNow() throws InterruptedException {
		AtomicInteger fetches = new AtomicInteger();
		CountDownLatch twoRuns = new CountDownLatch(2);
		WeatherProvider provider = countingProvider(fetches, twoRuns);
		IngestSettings fast = new IngestSettings(Duration.ofMillis(50), Duration.ofMillis(10), Duration.ofMillis(10), Duration.ofMillis(20));
		ProviderJob job = new ProviderJob(provider, emptyRegistry(), noSink(), new XmiFolderRepository(tmp), fast, Clock.systemUTC());

		scheduler.add(job);
		assertThat(twoRuns.await(5, TimeUnit.SECONDS)).as("the job ran at least twice on the scheduler thread").isTrue();

		List<IngestStatus> status = scheduler.status();
		assertThat(status).singleElement().satisfies(s -> {
			assertThat(s.providerId()).isEqualTo("dwd");
			assertThat(s.lastRun()).isPresent();
		});
		int before = fetches.get();
		IngestStatus now = scheduler.runNow("dwd", "MOSMIX_L");
		assertThat(fetches.get()).isGreaterThan(before);
		assertThat(now.lastSuccess()).isPresent();
		assertThatThrownBy(() -> scheduler.runNow("x", "y")).isInstanceOf(IllegalArgumentException.class);

		scheduler.remove("dwd", "MOSMIX_L");
		assertThat(scheduler.status()).isEmpty();
	}

	// --- fixtures --------------------------------------------------------------------------

	private static WeatherProvider countingProvider(AtomicInteger fetches, CountDownLatch latch) {
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
				return Set.of();
			}

			@Override
			public String licence() {
				return "";
			}

			@Override
			public String attribution() {
				return "";
			}

			@Override
			public SiteBindingResolver bindingResolver() {
				throw new UnsupportedOperationException();
			}

			@Override
			public FetchResult fetch(FetchRequest request) {
				fetches.incrementAndGet();
				latch.countDown();
				return new FetchResult.Unchanged();
			}
		};
	}

	/** One bound site, so that the provider is actually asked. */
	private static SiteRegistry emptyRegistry() {
		Site site = org.gecko.weather.model.weather.WeatherFactory.eINSTANCE.createSite();
		site.setId("s");
		site.setActive(true);
		org.gecko.weather.model.weather.StationBinding b = org.gecko.weather.model.weather.WeatherFactory.eINSTANCE.createStationBinding();
		b.setProviderId("dwd");
		b.setProductId("MOSMIX_L");
		b.setStation(org.gecko.weather.model.weather.WeatherFactory.eINSTANCE.createStation());
		site.getBindings().add(b);
		return new SiteRegistry() {
			@Override
			public Site register(SiteRegistration registration) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Optional<Site> get(String siteId) {
				return Optional.of(site);
			}

			@Override
			public List<Site> list() {
				return List.of(site);
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
	}

	private static WeatherDataSink noSink() {
		return new WeatherDataSink() {
			@Override
			public void replace(String siteId, SourceDataset dataset) {
			}

			@Override
			public void append(String siteId, SourceDataset partial) {
			}
		};
	}

}
