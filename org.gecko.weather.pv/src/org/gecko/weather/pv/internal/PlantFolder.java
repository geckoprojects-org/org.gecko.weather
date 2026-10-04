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
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * Plant profiles as XMI files in a folder, one per plant — {@code <folder>/<anything>.xmi} with a
 * {@code pv:Plant} root. Read on every call: a profile is edited by hand, and a changed file must
 * count without a restart. The folder is local data, not part of a repository — a profile names an
 * address.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class PlantFolder {

	private static final Logger LOG = System.getLogger(PlantFolder.class.getName());

	private final Path folder;
	private final Supplier<ResourceSet> resourceSets;

	public PlantFolder(Path folder) {
		this(folder, ResourceSetImpl::new);
	}

	public PlantFolder(Path folder, Supplier<ResourceSet> resourceSets) {
		this.folder = requireNonNull(folder, "folder").toAbsolutePath().normalize();
		this.resourceSets = requireNonNull(resourceSets, "resourceSets");
	}

	public Path folder() {
		return folder;
	}

	/** Every readable plant, sorted by id; a file that is not a plant is skipped and reported. */
	public List<Plant> plants() {
		List<Plant> plants = new ArrayList<>();
		if (!Files.isDirectory(folder)) {
			return plants;
		}
		try (DirectoryStream<Path> files = Files.newDirectoryStream(folder, "*.xmi")) {
			for (Path file : files) {
				try {
					load(file).ifPresent(plants::add);
				} catch (RuntimeException e) {
					LOG.log(Level.WARNING,
							"skipping plant profile " + file + ": " + e.getMessage());
				}
			}
		} catch (IOException e) {
			throw new IllegalStateException("Cannot list plant profiles in " + folder, e);
		}
		plants.sort(Comparator.comparing(Plant::getId, Comparator.nullsLast(Comparator.naturalOrder())));
		return plants;
	}

	public Optional<Plant> plant(String id) {
		requireNonNull(id, "id");
		return plants().stream().filter(p -> id.equals(p.getId())).findFirst();
	}

	/** Writes a profile as {@code <id>.xmi}, replacing one of the same name. */
	public void save(Plant plant) throws IOException {
		requireNonNull(plant.getId(), "plant.id");
		Files.createDirectories(folder);
		ResourceSet rs = resourceSet();
		synchronized (rs) {
			Resource resource = rs.createResource(URI.createFileURI(folder.resolve(plant.getId() + ".xmi").toString()));
			try {
				resource.getContents().add(plant);
				resource.save(null);
				resource.getContents().clear();
			} finally {
				rs.getResources().remove(resource);
			}
		}
	}

	private Optional<Plant> load(Path file) {
		ResourceSet rs = resourceSet();
		synchronized (rs) {
			Resource resource = rs.createResource(URI.createFileURI(file.toString()));
			try {
				resource.load(null);
				if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Plant plant)) {
					return Optional.empty();
				}
				resource.getContents().clear();
				return Optional.of(plant);
			} catch (IOException e) {
				throw new IllegalStateException(e.getMessage(), e);
			} finally {
				rs.getResources().remove(resource);
			}
		}
	}

	private ResourceSet resourceSet() {
		ResourceSet rs = resourceSets.get();
		rs.getPackageRegistry().putIfAbsent(PvPackage.eNS_URI, PvPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("xmi", new XMIResourceFactoryImpl());
		return rs;
	}
}
