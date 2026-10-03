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
package org.gecko.weather.runtime;

import java.time.Duration;
import java.time.Instant;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.gecko.weather.api.IngestControl;
import org.gecko.weather.api.IngestControl.IngestStatus;
import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The end-to-end proof against the real DWD, run from {@code smoke.bndrun}: waits for the MOSMIX
 * ingest job to exist (the provider fetches its catalogue on activation, which takes a moment),
 * registers a site, ingests now, prints what came back. Exit code 0 only if temperature values, sun
 * elevations and day events exist for the site afterwards.
 * <p>
 * The verdict reaches the launcher the bnd way: a {@code Callable<Integer>} service with
 * {@code main.thread=true}; the launcher calls it, exits with its result and stops the framework.
 * Active only when the system property {@code weather.smoke} is set.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(immediate = true)
public class SmokeRun {

	static final String PROPERTY = "weather.smoke";
	static final String SITE_ID = "smoke";
	static final Duration PROVIDER_WAIT = Duration.ofSeconds(90);

	@Reference
	private SiteRegistry sites;

	@Reference
	private WeatherService weather;

	@Reference
	private IngestControl ingest;

	private final CountDownLatch done = new CountDownLatch(1);
	private volatile int exit = 1;
	private ServiceRegistration<?> mainThread;

	@Activate
	void activate(BundleContext context) {
		if (System.getProperty(PROPERTY) == null) {
			return;
		}
		Hashtable<String, Object> props = new Hashtable<>();
		props.put("main.thread", Boolean.TRUE);
		// a named class, not a lambda: the launcher checks by reflection that call() returns Integer
		mainThread = context.registerService(Callable.class, new Verdict(), props);
		Thread t = new Thread(this::run, "weather-smoke");
		t.setDaemon(true);
		t.start();
	}

	@Deactivate
	void deactivate() {
		done.countDown();
		if (mainThread != null) {
			try {
				mainThread.unregister();
			} catch (IllegalStateException alreadyGone) {
				// framework is stopping
			}
		}
	}

	private void run() {
		try {
			if (!waitForJob("dwd", "MOSMIX_L")) {
				System.out.println("[smoke] FAILED: no ingest job for dwd/MOSMIX_L after " + PROVIDER_WAIT);
				return;
			}
			System.out.println("[smoke] registering site " + SITE_ID);
			sites.get(SITE_ID).ifPresent(s -> sites.remove(SITE_ID));
			Site site = sites.register(SiteRegistration.of("Smoke test, Dresden", 51.05, 13.74).withId(SITE_ID).withElevation(118));
			out("site %s at %.4f,%.4f with %d binding(s)", site.getId(), site.getPosition().getLatitude(),
					site.getPosition().getLongitude(), site.getBindings().size());
			site.getBindings().forEach(b -> out("  %s/%s rank %d %s @ %.1f km", b.getProviderId(), b.getProductId(), b.getRank(),
					b instanceof StationBinding sb ? sb.getStation().getId() + " " + sb.getStation().getName() : "cell",
					b.getDistanceMeters() / 1000));

			System.out.println("[smoke] running dwd/MOSMIX_L now");
			IngestStatus status = ingest.runNow("dwd", "MOSMIX_L");
			out("ingest: last success %s, last change %s, failures %d%s", status.lastSuccess().orElse(null),
					status.lastChange().orElse(null), status.consecutiveFailures(), status.lastError().map(e -> ", error " + e).orElse(""));

			WeatherReport report = weather.report(SITE_ID).orElse(null);
			if (report == null) {
				System.out.println("[smoke] FAILED: no report");
				return;
			}
			report.getDatasets().forEach(d -> out("dataset %s/%s issued %s, %d values, station %s", d.getProviderId(),
					d.getProductId(), d.getIssuedAt(), d.getValues().size(), d.getStationId()));
			List<MeasuredValue> temps = weather.values(SITE_ID, MeasurementKind.AIR_TEMPERATURE);
			List<MeasuredValue> sun = weather.values(SITE_ID, MeasurementKind.SUN_ELEVATION);
			out("%d temperature values, %d sun elevations, %d days", temps.size(), sun.size(), report.getDays().size());
			temps.stream().limit(6).forEach(v -> out("  %s %s %.1f %s from %s (%s)", v.getValidAt(), v.getLevel(), v.getValue(),
					v.getUnit(), v.getProvenance().getProductId(), v.getProvenance().getStationId()));
			if (!temps.isEmpty() && !sun.isEmpty() && !report.getDays().isEmpty()) {
				System.out.println("[smoke] OK");
				exit = 0;
			} else {
				System.out.println("[smoke] FAILED: report incomplete");
			}
		} catch (RuntimeException e) {
			System.out.println("[smoke] FAILED: " + e);
			e.printStackTrace(System.out);
		} finally {
			done.countDown();
		}
	}

	private boolean waitForJob(String providerId, String productId) {
		Instant deadline = Instant.now().plus(PROVIDER_WAIT);
		while (Instant.now().isBefore(deadline)) {
			boolean present = ingest.status().stream()
					.anyMatch(s -> providerId.equals(s.providerId()) && productId.equals(s.productId()));
			if (present) {
				return true;
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return false;
			}
		}
		return false;
	}

	/** Blocks the launcher's main thread until the run is done, then hands it the exit code. */
	private final class Verdict implements Callable<Integer> {
		@Override
		public Integer call() throws InterruptedException {
			done.await();
			return exit;
		}
	}

	private static void out(String format, Object... args) {
		System.out.println("[smoke] " + String.format(Locale.ROOT, format, args));
	}

}
