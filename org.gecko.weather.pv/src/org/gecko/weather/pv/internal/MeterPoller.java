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
package org.gecko.weather.pv.internal;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.spi.PvMeter;

/**
 * Reads the meters of all plants that have one, each at its own interval, and appends the readings
 * to the {@link MeasurementStore}. Driven by {@link #tick()} — the component calls it every few
 * seconds; a test calls it with a fixed clock. A failing device is logged when its error changes, not
 * on every attempt, and does not hold up the other plants.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class MeterPoller {

	private static final Logger LOG = System.getLogger(MeterPoller.class.getName());

	/** Fronius allows a realtime request every 4 s; nothing reads faster than this. */
	static final Duration MIN_INTERVAL = Duration.ofSeconds(5);
	static final Duration PLANT_RELOAD = Duration.ofMinutes(1);

	private final Supplier<List<Plant>> plants;
	private final MeasurementStore store;
	private final Function<String, Optional<PvMeter>> meters;
	private final Function<Plant, ZoneId> zones;
	private final Clock clock;

	private List<Plant> cached = List.of();
	private Instant cachedAt = Instant.MIN;
	private final Map<String, Instant> lastRead = new HashMap<>();
	private final Map<String, String> lastError = new HashMap<>();

	public MeterPoller(Supplier<List<Plant>> plants, MeasurementStore store, Function<String, Optional<PvMeter>> meters,
			Function<Plant, ZoneId> zones, Clock clock) {
		this.plants = requireNonNull(plants, "plants");
		this.store = requireNonNull(store, "store");
		this.meters = requireNonNull(meters, "meters");
		this.zones = requireNonNull(zones, "zones");
		this.clock = requireNonNull(clock, "clock");
	}

	/** Reads every meter that is due. Never throws: one broken plant must not stop the others. */
	public synchronized void tick() {
		Instant now = clock.instant();
		if (Duration.between(cachedAt, now).compareTo(PLANT_RELOAD) >= 0 || cachedAt.isAfter(now)) {
			try {
				cached = plants.get();
			} catch (RuntimeException e) {
				LOG.log(Level.WARNING, "cannot list plant profiles: " + e.getMessage());
			}
			cachedAt = now;
		}
		for (Plant plant : cached) {
			Meter meter = plant.getMeter();
			if (meter == null || !meter.isEnabled() || meter.getType() == null || plant.getId() == null) {
				continue;
			}
			Duration interval = Duration.ofSeconds(Math.max(meter.getInterval(), MIN_INTERVAL.toSeconds()));
			Instant last = lastRead.get(plant.getId());
			if (last != null && now.isBefore(last.plus(interval))) {
				continue;
			}
			lastRead.put(plant.getId(), now);
			read(plant, meter, now);
		}
	}

	private void read(Plant plant, Meter meter, Instant now) {
		Optional<PvMeter> reader = meters.apply(meter.getType());
		if (reader.isEmpty()) {
			report(plant, "no reader for meter type " + meter.getType());
			return;
		}
		try {
			PvMeasurement m = reader.get().read(meter);
			if (m == null) {
				report(plant, "reader returned nothing");
				return;
			}
			m.setTime(Date.from(now));
			store.append(plant.getId(), meter.getType(), now.atZone(zones.apply(plant)).toLocalDate(), m);
			if (lastError.remove(plant.getId()) != null) {
				LOG.log(Level.INFO, "meter of plant " + plant.getId() + " readable again");
			}
		} catch (IOException | RuntimeException e) {
			report(plant, e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}

	private void report(Plant plant, String error) {
		if (!Objects.equals(lastError.put(plant.getId(), error), error)) {
			LOG.log(Level.WARNING, "meter of plant " + plant.getId() + ": " + error);
		}
	}
}
