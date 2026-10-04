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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.model.pv.PvMeasurementLog;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * Meter readings as XMI files, one per plant and local day: {@code <folder>/<plantId>/<date>.xmi}.
 * The day being written stays in memory and is saved after every append — a reading a minute keeps
 * the file small enough for that, and a crash loses at most the reading in flight. Local data, like
 * the plant profiles.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class MeasurementStore {

	private static final Pattern SAFE_ID = Pattern.compile("[A-Za-z0-9._-]+");

	private final Path folder;
	private final Supplier<ResourceSet> resourceSets;
	private final Map<String, PvMeasurementLog> open = new HashMap<>();

	public MeasurementStore(Path folder) {
		this(folder, ResourceSetImpl::new);
	}

	public MeasurementStore(Path folder, Supplier<ResourceSet> resourceSets) {
		this.folder = requireNonNull(folder, "folder").toAbsolutePath().normalize();
		this.resourceSets = requireNonNull(resourceSets, "resourceSets");
	}

	public Path folder() {
		return folder;
	}

	/** Adds a reading to the plant's log of that day and saves the log. */
	public synchronized void append(String plantId, String meterType, LocalDate date, PvMeasurement measurement) throws IOException {
		requireNonNull(measurement, "measurement");
		PvMeasurementLog log = open.get(plantId);
		if (log == null || !log.getDate().equals(date.toString())) {
			log = read(plantId, date).orElseGet(() -> {
				PvMeasurementLog created = PvFactory.eINSTANCE.createPvMeasurementLog();
				created.setPlantId(plantId);
				created.setDate(date.toString());
				return created;
			});
			open.put(plantId, log);
		}
		log.setMeterType(meterType);
		log.getMeasurements().add(measurement);
		write(log, file(plantId, date));
	}

	/** A copy of the plant's log of that day, if one exists. */
	public synchronized Optional<PvMeasurementLog> log(String plantId, LocalDate date) {
		PvMeasurementLog log = open.get(plantId);
		if (log != null && log.getDate().equals(date.toString())) {
			return Optional.of(EcoreUtil.copy(log));
		}
		return read(plantId, date);
	}

	Path file(String plantId, LocalDate date) {
		if (plantId == null || !SAFE_ID.matcher(plantId).matches() || plantId.startsWith(".")) {
			throw new IllegalArgumentException("Plant id not usable as a folder name: " + plantId);
		}
		return folder.resolve(plantId).resolve(date + ".xmi");
	}

	private Optional<PvMeasurementLog> read(String plantId, LocalDate date) {
		Path file = file(plantId, date);
		if (!Files.isRegularFile(file)) {
			return Optional.empty();
		}
		ResourceSet rs = resourceSet();
		Resource resource = rs.createResource(URI.createFileURI(file.toString()));
		try {
			resource.load(null);
			if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof PvMeasurementLog log) {
				resource.getContents().clear();
				return Optional.of(log);
			}
			return Optional.empty();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot read " + file + ": " + e.getMessage(), e);
		} finally {
			rs.getResources().remove(resource);
		}
	}

	private void write(PvMeasurementLog log, Path file) throws IOException {
		Files.createDirectories(file.getParent());
		Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
		ResourceSet rs = resourceSet();
		Resource resource = rs.createResource(URI.createFileURI(tmp.toString()));
		try {
			resource.getContents().add(log);
			resource.save(null);
		} finally {
			resource.getContents().clear();
			rs.getResources().remove(resource);
		}
		Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
	}

	private ResourceSet resourceSet() {
		ResourceSet rs = resourceSets.get();
		rs.getPackageRegistry().putIfAbsent(PvPackage.eNS_URI, PvPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("xmi", new XMIResourceFactoryImpl());
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("tmp", new XMIResourceFactoryImpl());
		return rs;
	}
}
