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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * Freezes the forecast of every plant at fixed local hours — the outlook changes with every model
 * run, and a comparison with what was measured needs the forecast as it stood before. One XMI file
 * per plant and hour: {@code <folder>/<plantId>/<yyyy-MM-dd>T<HH>.xmi}. Driven by {@link #tick};
 * a file that exists is not written again, so a restart in the same hour does nothing twice.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class ForecastSnapshots {

	private static final Logger LOG = System.getLogger(ForecastSnapshots.class.getName());
	private static final DateTimeFormatter NAME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH");

	private final Path folder;
	private final Set<Integer> hours;
	private final Supplier<List<Plant>> plants;
	private final Function<Plant, ZoneId> zones;
	private final Function<String, PvOutlook> forecasts;
	private final Supplier<ResourceSet> resourceSets;
	private final Map<String, String> lastError = new HashMap<>();

	/**
	 * @param hours     local hours (0–23) at which to freeze; 6 for the day itself, 18 for the day
	 *                  ahead
	 * @param forecasts the forecast of a plant by id — what {@code PvForecast.forecast} answers
	 */
	public ForecastSnapshots(Path folder, Set<Integer> hours, Supplier<List<Plant>> plants, Function<Plant, ZoneId> zones,
			Function<String, PvOutlook> forecasts) {
		this(folder, hours, plants, zones, forecasts, ResourceSetImpl::new);
	}

	ForecastSnapshots(Path folder, Set<Integer> hours, Supplier<List<Plant>> plants, Function<Plant, ZoneId> zones,
			Function<String, PvOutlook> forecasts, Supplier<ResourceSet> resourceSets) {
		this.folder = requireNonNull(folder, "folder").toAbsolutePath().normalize();
		this.hours = Set.copyOf(requireNonNull(hours, "hours"));
		this.plants = requireNonNull(plants, "plants");
		this.zones = requireNonNull(zones, "zones");
		this.forecasts = requireNonNull(forecasts, "forecasts");
		this.resourceSets = requireNonNull(resourceSets, "resourceSets");
	}

	/** The hours from a configuration string such as {@code "6,18"}; blank means none. */
	public static Set<Integer> hours(String configured) {
		if (configured == null || configured.isBlank()) {
			return Set.of();
		}
		return Set.copyOf(Arrays.stream(configured.split("[,; ]+")).filter(s -> !s.isBlank()).map(Integer::parseInt)
				.filter(h -> h >= 0 && h < 24).toList());
	}

	/** The file a snapshot of this plant taken at this instant has. */
	public Path file(Plant plant, Instant now) {
		ZonedDateTime local = now.atZone(zones.apply(plant));
		return folder.resolve(plant.getId()).resolve(NAME.format(local) + ".xmi");
	}

	/** Freezes what is due now. Never throws: one broken plant must not stop the others. */
	public synchronized void tick(Instant now) {
		List<Plant> all;
		try {
			all = plants.get();
		} catch (RuntimeException e) {
			LOG.log(Level.WARNING, "cannot list plant profiles: " + e.getMessage());
			return;
		}
		for (Plant plant : all) {
			if (plant.getId() == null || !hours.contains(now.atZone(zones.apply(plant)).getHour())) {
				continue;
			}
			Path file = file(plant, now);
			if (Files.exists(file)) {
				continue;
			}
			try {
				write(forecasts.apply(plant.getId()), file);
				if (lastError.remove(plant.getId()) != null) {
					LOG.log(Level.INFO, "forecast of plant " + plant.getId() + " frozen again");
				}
			} catch (IOException | RuntimeException e) {
				String error = e.getClass().getSimpleName() + ": " + e.getMessage();
				if (!Objects.equals(lastError.put(plant.getId(), error), error)) {
					LOG.log(Level.WARNING, "cannot freeze the forecast of plant " + plant.getId() + ": " + error);
				}
			}
		}
	}

	/**
	 * The frozen hours of a local day: from every snapshot of that day and of the evening before,
	 * oldest first so that a newer snapshot overrides an older one. Empty without snapshots. Used to
	 * fill the hours of today that the current model runs no longer cover.
	 */
	public synchronized Map<Instant, PvHour> frozenHours(Plant plant, LocalDate date) {
		Map<Instant, PvHour> hours = new TreeMap<>();
		Path dir = folder.resolve(plant.getId());
		if (plant.getId() == null || !Files.isDirectory(dir)) {
			return hours;
		}
		String today = date.toString();
		String yesterday = date.minusDays(1).toString();
		List<Path> files;
		try (var listing = Files.list(dir)) {
			files = listing.filter(f -> f.getFileName().toString().endsWith(".xmi"))
					.filter(f -> f.getFileName().toString().startsWith(today) || f.getFileName().toString().startsWith(yesterday))
					.sorted().toList();
		} catch (IOException e) {
			LOG.log(Level.WARNING, "cannot list snapshots in " + dir + ": " + e.getMessage());
			return hours;
		}
		for (Path file : files) {
			try {
				for (PvHour h : read(file).getHours()) {
					if (h.getTime() != null) {
						hours.put(h.getTime().toInstant(), h);
					}
				}
			} catch (IOException | RuntimeException e) {
				LOG.log(Level.WARNING, "skipping snapshot " + file + ": " + e.getMessage());
			}
		}
		return hours;
	}

	private PvOutlook read(Path file) throws IOException {
		ResourceSet rs = resourceSets.get();
		rs.getPackageRegistry().putIfAbsent(PvPackage.eNS_URI, PvPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("xmi", new XMIResourceFactoryImpl());
		Resource resource = rs.createResource(URI.createFileURI(file.toString()));
		try {
			resource.load(null);
			if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof PvOutlook outlook)) {
				throw new IOException("not a PvOutlook");
			}
			resource.getContents().clear();
			return outlook;
		} finally {
			rs.getResources().remove(resource);
		}
	}

	private void write(PvOutlook outlook, Path file) throws IOException {
		Files.createDirectories(file.getParent());
		Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
		ResourceSet rs = resourceSets.get();
		rs.getPackageRegistry().putIfAbsent(PvPackage.eNS_URI, PvPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("tmp", new XMIResourceFactoryImpl());
		Resource resource = rs.createResource(URI.createFileURI(tmp.toString()));
		try {
			resource.getContents().add(outlook);
			resource.save(null);
		} finally {
			resource.getContents().clear();
			rs.getResources().remove(resource);
		}
		Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
	}
}
