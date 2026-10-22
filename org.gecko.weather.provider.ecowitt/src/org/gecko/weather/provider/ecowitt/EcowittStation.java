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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.spi.SiteBindingResolver;
import org.gecko.weather.api.spi.WeatherDataSink;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;
import org.osgi.service.metatype.annotations.Designate;

/**
 * One configured Ecowitt station: a push source. Telegrams arrive over the configured transport,
 * are decoded into {@link Reading}s, filtered to the configured model and device, sampled at the
 * reporting interval (the gust as the maximum over it), mapped by {@link StationDatasets} and
 * appended to the report of every site bound to the device.
 * <p>
 * Registered as a {@link SiteBindingResolver} so that sites can be bound to the device — by hand
 * ({@code weather:assign <site> ecowitt WS90 <deviceId>}) or automatically for the site named in
 * the configuration. Not a {@code WeatherProvider}: there is nothing to fetch.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
@Component(configurationPid = EcowittStation.PID, configurationPolicy = ConfigurationPolicy.REQUIRE, service = SiteBindingResolver.class)
@Designate(ocd = EcowittConfig.class, factory = true)
public class EcowittStation implements SiteBindingResolver {

	public static final String PID = "org.gecko.weather.provider.ecowitt";
	public static final String PROVIDER_ID = "ecowitt";

	private static final Logger LOG = System.getLogger(EcowittStation.class.getName());
	private static final Duration SITE_CACHE = Duration.ofSeconds(30);

	private final WeatherDataSink sink;
	private final SiteRegistry sites;
	private final ResourceSet resourceSet;
	private final Clock clock;

	private volatile EcowittConfig config;
	private volatile StationDatasets.Product product;
	private volatile Duration interval = Duration.ofMinutes(1);
	private volatile Rtl433Stream stream;

	private final Object sampling = new Object();
	private Instant lastAppendAt = Instant.EPOCH;
	private Double gustMax;

	private volatile List<Site> cachedSites = List.of();
	private volatile Instant cachedAt = Instant.EPOCH;
	private final Set<String> unboundLogged = ConcurrentHashMap.newKeySet();
	private final Set<String> foreignLogged = ConcurrentHashMap.newKeySet();
	private volatile Instant batteryWarnedAt = Instant.EPOCH;

	@Activate
	public EcowittStation(@Reference WeatherDataSink sink, @Reference SiteRegistry sites,
			@Reference(target = "(emf.name=rtl433)", scope = ReferenceScope.PROTOTYPE) ResourceSet resourceSet,
			EcowittConfig config) {
		this(sink, sites, resourceSet, Clock.systemUTC(), config);
		start();
	}

	EcowittStation(WeatherDataSink sink, SiteRegistry sites, ResourceSet resourceSet, Clock clock, EcowittConfig config) {
		this.sink = requireNonNull(sink, "sink");
		this.sites = requireNonNull(sites, "sites");
		this.resourceSet = requireNonNull(resourceSet, "resourceSet");
		this.clock = requireNonNull(clock, "clock");
		configure(config);
	}

	@Modified
	void modified(EcowittConfig config) {
		stop();
		configure(config);
		start();
	}

	@Deactivate
	void deactivate() {
		stop();
	}

	private void configure(EcowittConfig config) {
		this.config = requireNonNull(config, "config");
		this.product = new StationDatasets.Product(PROVIDER_ID, config.station().name(), config.licence(), config.attribution());
		this.interval = parseInterval(config.interval());
	}

	private static Duration parseInterval(String text) {
		if (text == null || text.isBlank()) {
			return Duration.ofMinutes(1);
		}
		try {
			Duration d = Duration.parse(text.strip());
			return d.isNegative() ? Duration.ZERO : d;
		} catch (java.time.format.DateTimeParseException e) {
			LOG.log(Level.WARNING, "[ecowitt] interval {0} is not an ISO-8601 duration; using PT1M", text);
			return Duration.ofMinutes(1);
		}
	}

	private void start() {
		EcowittConfig c = config;
		if (c.transport() == Transport.SDR) {
			Rtl433Stream s = new Rtl433Stream(URI.create(c.url().strip()), this::onLine);
			stream = s;
			s.start();
			LOG.log(Level.INFO, "[ecowitt/{0}] device {1}: listening to rtl_433 at {2}", c.station(),
					deviceId().isEmpty() ? "any" : deviceId(), s.uri());
		}
	}

	private void stop() {
		Rtl433Stream s = stream;
		stream = null;
		if (s != null) {
			s.stop();
		}
	}

	private String deviceId() {
		return config.deviceId() == null ? "" : config.deviceId().strip();
	}

	/** One line of the stream — any device on the band; only the configured one gets through. */
	void onLine(String line) {
		Reading reading;
		try {
			reading = Rtl433Reader.read(line, resourceSet);
		} catch (IOException e) {
			LOG.log(Level.DEBUG, "unreadable line: {0}", e.getMessage());
			return;
		}
		handle(reading);
	}

	void handle(Reading reading) {
		EcowittConfig c = config;
		if (!c.station().rtl433Model().equals(reading.getModel()) || reading.getId() == null) {
			return;
		}
		String id = String.valueOf(reading.getId());
		String wanted = deviceId();
		if (!wanted.isEmpty() && !wanted.equals(id)) {
			if (foreignLogged.add(id)) {
				LOG.log(Level.INFO, "[ecowitt/{0}] another device {1} is sending; this station is {2}, ignored", c.station(), id, wanted);
			}
			return;
		}
		Instant now = clock.instant();
		warnBattery(reading, id, now);
		SourceDataset dataset;
		synchronized (sampling) {
			if (reading.getWindGust() != null && (gustMax == null || reading.getWindGust() > gustMax)) {
				gustMax = reading.getWindGust();
			}
			if (now.isBefore(lastAppendAt.plus(interval))) {
				return; // within the reporting interval: only the gust and the battery watch see it
			}
			dataset = StationDatasets.observation(reading, now, product, id, gustMax, interval);
			gustMax = null;
			lastAppendAt = now;
		}
		if (dataset.getValues().isEmpty()) {
			return;
		}
		List<String> targets = boundSites(id, now);
		if (targets.isEmpty()) {
			if (unboundLogged.add(id)) {
				LOG.log(Level.INFO, "[ecowitt/{0}] device {1} is sending but no site is bound to it: weather:assign <site> ecowitt {0} {1}",
						c.station(), id);
			}
			return;
		}
		for (String siteId : targets) {
			try {
				sink.append(siteId, dataset);
			} catch (UnknownSiteException gone) {
				cachedAt = Instant.EPOCH;
			}
		}
	}

	private void warnBattery(Reading reading, String id, Instant now) {
		if (reading.getBatteryOk() != null && reading.getBatteryOk() < 1.0 && now.isAfter(batteryWarnedAt.plus(Duration.ofHours(1)))) {
			batteryWarnedAt = now;
			LOG.log(Level.WARNING, "[ecowitt/{0}] device {1} reports a weak battery ({2})", config.station(), id, reading.getBatteryOk());
		}
	}

	/** The active sites bound to the device: the registry's list, cached for a short while. */
	List<String> boundSites(String id, Instant now) {
		if (now.isAfter(cachedAt.plus(SITE_CACHE))) {
			cachedSites = sites.list();
			cachedAt = now;
		}
		String productId = product.productId();
		return cachedSites.stream().filter(Site::isActive)
				.filter(site -> site.getBindings().stream().anyMatch(b -> boundTo(b, productId, id)))
				.map(Site::getId).toList();
	}

	private static boolean boundTo(SourceBinding b, String productId, String id) {
		return b instanceof StationBinding sb && PROVIDER_ID.equals(sb.getProviderId()) && productId.equals(sb.getProductId())
				&& sb.getStation() != null && id.equals(sb.getStation().getId());
	}

	// --- SiteBindingResolver ---------------------------------------------------------------

	@Override
	public String providerId() {
		return PROVIDER_ID;
	}

	@Override
	public String productId() {
		return product.productId();
	}

	/** Automatic binding only for the configured site, and only when the device is known. */
	@Override
	public List<SourceBinding> resolve(Site site, int max) {
		requireNonNull(site, "site");
		String siteId = config.siteId() == null ? "" : config.siteId().strip();
		if (max <= 0 || siteId.isEmpty() || !siteId.equals(site.getId()) || deviceId().isEmpty()) {
			return List.of();
		}
		return List.of(binding(site, deviceId(), BindingOrigin.AUTOMATIC));
	}

	/** Manual binding to the configured device, or to any device id when none is configured. */
	@Override
	public Optional<SourceBinding> bind(Site site, String locationId) {
		requireNonNull(site, "site");
		requireNonNull(locationId, "locationId");
		String id = locationId.strip();
		if (id.isEmpty() || (!deviceId().isEmpty() && !deviceId().equals(id))) {
			return Optional.empty();
		}
		return Optional.of(binding(site, id, BindingOrigin.MANUAL));
	}

	private SourceBinding binding(Site site, String id, BindingOrigin origin) {
		WeatherFactory f = WeatherFactory.eINSTANCE;
		Station station = f.createStation();
		station.setId(id);
		station.setName("Ecowitt " + config.station() + " " + id);
		if (site.getPosition() != null) {
			station.setPosition(EcoreUtil.copy(site.getPosition()));
		}
		StationBinding b = f.createStationBinding();
		b.setProviderId(PROVIDER_ID);
		b.setProductId(product.productId());
		b.setOrigin(origin);
		b.setStation(station);
		b.setDistanceMeters(0);
		b.setElevationDeltaMeters(0);
		b.setResolvedAt(clock.instant());
		return b;
	}
}
