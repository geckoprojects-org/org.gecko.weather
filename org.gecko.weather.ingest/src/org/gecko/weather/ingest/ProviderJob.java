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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.gecko.weather.api.IngestControl.IngestStatus;
import org.gecko.weather.api.Reports;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.api.spi.FetchRequest;
import org.gecko.weather.api.spi.FetchRequest.SiteBindings;
import org.gecko.weather.api.spi.FetchResult;
import org.gecko.weather.api.spi.SourceState;
import org.gecko.weather.api.spi.SourceStates;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.api.spi.WeatherProvider;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * One ingest run of one product, and the bookkeeping between runs. Plain Java, no threads: the
 * scheduler calls {@link #run()} and asks {@link #nextDelay()} when to call again.
 * <p>
 * A run collects the active sites' bindings for the product, hands them with the persisted
 * {@link SourceState} to {@link WeatherProvider#fetch}, pushes every returned dataset through
 * {@link WeatherDataSink#replace} and saves the new state. An {@link IOException} is a transport
 * problem and backs off exponentially; a {@link FetchException} (content not as expected) is
 * logged and waits for the next poll — retrying would not make the file better.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class ProviderJob {

	private static final Logger LOG = System.getLogger(ProviderJob.class.getName());

	private final WeatherProvider provider;
	private final SiteRegistry sites;
	private final WeatherDataSink sink;
	private final WeatherRepository repository;
	private final IngestSettings settings;
	private final Clock clock;

	private SourceState state;
	private Optional<Instant> lastRun = Optional.empty();
	private Optional<Instant> lastSuccess = Optional.empty();
	private Optional<Instant> lastChange = Optional.empty();
	private Optional<Instant> nextRun = Optional.empty();
	private Optional<String> lastError = Optional.empty();
	private int consecutiveFailures;
	private volatile boolean running;

	public ProviderJob(WeatherProvider provider, SiteRegistry sites, WeatherDataSink sink, WeatherRepository repository,
			IngestSettings settings, Clock clock) {
		this.provider = requireNonNull(provider, "provider");
		this.sites = requireNonNull(sites, "sites");
		this.sink = requireNonNull(sink, "sink");
		this.repository = requireNonNull(repository, "repository");
		this.settings = requireNonNull(settings, "settings");
		this.clock = requireNonNull(clock, "clock");
		this.state = repository.loadSourceState(provider.providerId(), provider.productId())
				.map(SourceStates::fromRecord).orElse(SourceState.EMPTY);
		this.nextRun = Optional.of(clock.instant().plus(settings.initialDelay()));
	}

	public String providerId() {
		return provider.providerId();
	}

	public String productId() {
		return provider.productId();
	}

	/** Runs once; never throws. The outcome is in {@link #status()}. */
	public synchronized void run() {
		Instant now = clock.instant();
		running = true;
		lastRun = Optional.of(now);
		try {
			Outcome outcome = fetchOnce(now);
			consecutiveFailures = 0;
			lastError = Optional.empty();
			lastSuccess = Optional.of(now);
			if (outcome == Outcome.CHANGED) {
				lastChange = Optional.of(now);
			}
			nextRun = Optional.of(now.plus(settings.pollInterval()));
		} catch (IOException e) {
			consecutiveFailures++;
			lastError = Optional.of("transport: " + e.getMessage());
			Duration wait = settings.backoff(consecutiveFailures);
			nextRun = Optional.of(now.plus(wait));
			LOG.log(Level.WARNING, "[{0}/{1}] fetch failed ({2} in a row), next try in {3}: {4}", providerId(), productId(),
					consecutiveFailures, wait, e.getMessage());
		} catch (FetchException e) {
			consecutiveFailures++;
			lastError = Optional.of("content: " + e.getMessage());
			nextRun = Optional.of(now.plus(settings.pollInterval()));
			LOG.log(Level.ERROR, "[{0}/{1}] source content not as expected, waiting for the next poll: {2}", providerId(),
					productId(), e.getMessage());
		} catch (RuntimeException e) {
			consecutiveFailures++;
			lastError = Optional.of("internal: " + e);
			nextRun = Optional.of(now.plus(settings.pollInterval()));
			LOG.log(Level.ERROR, "[" + providerId() + "/" + productId() + "] run failed", e);
		} finally {
			running = false;
		}
	}

	/** How long the scheduler should wait before the next {@link #run()}. */
	public synchronized Duration nextDelay() {
		Instant next = nextRun.orElse(clock.instant());
		Duration d = Duration.between(clock.instant(), next);
		return d.isNegative() ? Duration.ZERO : d;
	}

	/** Forgets a pending backoff so that the next run happens right away. */
	public synchronized void resetBackoff() {
		consecutiveFailures = 0;
		nextRun = Optional.of(clock.instant());
	}

	public synchronized IngestStatus status() {
		return new IngestStatus(providerId(), productId(), lastRun, lastSuccess, lastChange, nextRun, consecutiveFailures,
				lastError, running);
	}

	// --- one run ---------------------------------------------------------------------------

	private enum Outcome {
		NOTHING_TO_DO, UNCHANGED, CHANGED
	}

	private Outcome fetchOnce(Instant now) throws IOException {
		List<SiteBindings> bound = boundSites();
		if (bound.isEmpty()) {
			LOG.log(Level.DEBUG, "[{0}/{1}] no active site is bound to this product", providerId(), productId());
			return Outcome.NOTHING_TO_DO;
		}
		Set<String> unconditional = sitesWithoutData(bound);
		FetchResult result = provider.fetch(new FetchRequest(bound, state, now, unconditional));
		if (result instanceof FetchResult.Unchanged) {
			LOG.log(Level.DEBUG, "[{0}/{1}] unchanged", providerId(), productId());
			return Outcome.UNCHANGED;
		}
		FetchResult.Fetched fetched = (FetchResult.Fetched) result;
		int applied = 0;
		for (Map.Entry<String, List<SourceDataset>> e : fetched.datasets().entrySet()) {
			for (SourceDataset dataset : e.getValue()) {
				try {
					sink.replace(e.getKey(), dataset);
					applied++;
				} catch (UnknownSiteException gone) {
					LOG.log(Level.INFO, "[{0}/{1}] site {2} disappeared during the run", providerId(), productId(), e.getKey());
				}
			}
		}
		state = fetched.state();
		repository.saveSourceState(SourceStates.toRecord(providerId(), productId(), state, now));
		if (!fetched.skipped().isEmpty()) {
			LOG.log(Level.WARNING, "[{0}/{1}] skipped during decode: {2}", providerId(), productId(), fetched.skipped());
		}
		LOG.log(Level.INFO, "[{0}/{1}] {2} dataset(s) for {3} site(s)", providerId(), productId(), applied,
				fetched.datasets().size());
		return Outcome.CHANGED;
	}

	/**
	 * Active sites with their bindings for this product. A site without one — registered before
	 * this provider existed, or before its catalogue was loaded — is rebound once per run, so the
	 * system heals itself instead of waiting for an operator; a site the product does not cover
	 * stays unbound and is left out.
	 */
	private List<SiteBindings> boundSites() {
		List<SiteBindings> result = new ArrayList<>();
		for (Site site : sites.list()) {
			if (!site.isActive()) {
				continue;
			}
			List<SourceBinding> bindings = bindingsFor(site);
			if (bindings.isEmpty()) {
				try {
					site = sites.rebind(site.getId());
					bindings = bindingsFor(site);
					if (!bindings.isEmpty()) {
						LOG.log(Level.INFO, "[{0}/{1}] bound site {2} on first sight", providerId(), productId(), site.getId());
					}
				} catch (RuntimeException e) {
					LOG.log(Level.WARNING, "[{0}/{1}] cannot rebind site {2}: {3}", providerId(), productId(), site.getId(), e.toString());
				}
			}
			if (!bindings.isEmpty()) {
				result.add(new SiteBindings(site, bindings));
			}
		}
		return result;
	}

	/**
	 * Sites that lack a dataset for at least one of their bindings of this product — their sources
	 * must be fetched even if unchanged, or they would wait for the next publication.
	 */
	private Set<String> sitesWithoutData(List<SiteBindings> bound) {
		Set<String> result = new HashSet<>();
		for (SiteBindings sb : bound) {
			Optional<WeatherReport> report = repository.loadReport(sb.site().getId());
			for (SourceBinding b : sb.bindings()) {
				boolean covered = report.isPresent() && Reports.datasets(report.get(), providerId(), productId()).stream()
						.anyMatch(d -> coveredBy(d, b));
				if (!covered) {
					result.add(sb.site().getId());
					break;
				}
			}
		}
		return result;
	}

	private static boolean coveredBy(SourceDataset dataset, SourceBinding binding) {
		if (binding instanceof StationBinding s) {
			return s.getStation().getId().equals(dataset.getStationId());
		}
		if (binding instanceof GridBinding g && dataset.getCell() != null) {
			return g.getCell().getGridId().equals(dataset.getCell().getGridId()) && g.getCell().getI() == dataset.getCell().getI()
					&& g.getCell().getJ() == dataset.getCell().getJ();
		}
		return false;
	}

	private List<SourceBinding> bindingsFor(Site site) {
		return site.getBindings().stream()
				.filter(b -> providerId().equals(b.getProviderId()) && productId().equals(b.getProductId())).toList();
	}

}
