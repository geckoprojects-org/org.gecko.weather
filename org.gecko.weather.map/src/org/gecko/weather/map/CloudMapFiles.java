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
package org.gecko.weather.map;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.fennec.ogc.features.geo.GeoJsonFeatureImporter;
import org.eclipse.fennec.ogc.features.geo.GeometryJson;
import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapPackage;
import org.geojson.Geometry;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.json.JsonMapper;

/**
 * The cloud bands of a run as one GeoJSON FeatureCollection on disk, {@code clct-<yyyyMMddHH>.geojson}
 * in a folder: written when a run is complete, read on start so that a restart shows the map at
 * once. Plain RFC 7946 — any GeoJSON tool opens it — read back into the model by the importer of
 * Fennec OGC Features. Only the newest run is kept.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
public final class CloudMapFiles {

	private static final DateTimeFormatter RUN = DateTimeFormatter.ofPattern("yyyyMMddHH", Locale.ROOT).withZone(ZoneOffset.UTC);
	private static final JsonMapper MAPPER = JsonMapper.builder().build();

	private final Path folder;
	private final String parameter;

	public CloudMapFiles(Path folder, String parameter) {
		this.folder = requireNonNull(folder, "folder");
		this.parameter = requireNonNull(parameter, "parameter");
	}

	public Path file(Instant run) {
		return folder.resolve(parameter + "-" + RUN.format(run) + ".geojson");
	}

	/** Writes the run (through a temporary file, so a reader never sees half of it) and removes older runs. */
	public void write(Instant run, List<CloudBand> features) throws IOException {
		Files.createDirectories(folder);
		Path target = file(run);
		Path tmp = target.resolveSibling(target.getFileName() + ".tmp");
		try (OutputStream out = Files.newOutputStream(tmp); JsonGenerator g = MAPPER.createGenerator(out)) {
			g.writeStartObject();
			g.writeStringProperty("type", "FeatureCollection");
			g.writeArrayPropertyStart("features");
			for (CloudBand f : features) {
				writeFeature(g, f);
			}
			g.writeEndArray();
			g.writeEndObject();
		}
		Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
		for (Path older : runs()) {
			if (!older.equals(target)) {
				Files.deleteIfExists(older);
			}
		}
	}

	/** The newest run on disk, if there is one. */
	public Optional<List<CloudBand>> readNewest(GeoJsonFeatureImporter importer) throws IOException {
		List<Path> runs = runs();
		if (runs.isEmpty()) {
			return Optional.empty();
		}
		try (InputStream in = Files.newInputStream(runs.get(0))) {
			List<CloudBand> bands = new ArrayList<>();
			for (EObject o : importer.read(in, CloudmapPackage.Literals.CLOUD_BAND)) {
				bands.add((CloudBand) o);
			}
			return Optional.of(bands);
		}
	}

	/** The run files in the folder, newest first. */
	List<Path> runs() throws IOException {
		if (!Files.isDirectory(folder)) {
			return List.of();
		}
		String prefix = parameter + "-";
		try (Stream<Path> files = Files.list(folder)) {
			return files.filter(p -> {
				String name = p.getFileName().toString();
				return name.startsWith(prefix) && name.endsWith(".geojson");
			}).sorted(Comparator.comparing((Path p) -> p.getFileName().toString()).reversed()).toList();
		}
	}

	/** Every attribute but id and geometry as a property: numbers as numbers, dates as ISO instants. */
	private static void writeFeature(JsonGenerator g, CloudBand f) {
		g.writeStartObject();
		g.writeStringProperty("type", "Feature");
		g.writeStringProperty("id", f.getId());
		g.writeName("geometry");
		Geometry geometry = f.getGeometry();
		if (geometry == null) {
			g.writeNull();
		} else {
			GeometryJson.write(g, geometry);
		}
		g.writeObjectPropertyStart("properties");
		for (EAttribute a : f.eClass().getEAllAttributes()) {
			if (a == CloudmapPackage.Literals.CLOUD_BAND__ID || a == CloudmapPackage.Literals.CLOUD_BAND__GEOMETRY || !f.eIsSet(a)) {
				continue;
			}
			Object value = f.eGet(a);
			g.writeName(a.getName());
			if (value instanceof Date date) {
				g.writeString(date.toInstant().toString());
			} else if (value instanceof Integer i) {
				g.writeNumber(i);
			} else if (value instanceof Double d) {
				g.writeNumber(d);
			} else {
				g.writeString(EcoreUtil.convertToString(a.getEAttributeType(), value));
			}
		}
		g.writeEndObject();
		g.writeEndObject();
	}
}
