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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import org.eclipse.fennec.codec.geojson.GeoJsonResourceFactoryImpl;
import org.eclipse.fennec.codec.util.MetadataServiceFactory;
import org.eclipse.fennec.emf.osgi.metadata.MetadataWhiteboard;
import org.eclipse.fennec.ogc.features.api.CollectionDescriptor;
import org.eclipse.fennec.ogc.features.api.FeatureQuery;
import org.eclipse.fennec.ogc.features.geo.GeoJsonFeatureImporter;
import org.eclipse.fennec.ogc.features.geo.GeoJsonText;
import org.gecko.weather.api.spi.GridField;
import org.gecko.weather.api.spi.RegularLatLonGrid;
import org.gecko.weather.grib2.Grib2Field;
import org.gecko.weather.grib2.Grib2Reader;
import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapPackage;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherFactory;
import org.geojson.GeoJsonPackage;
import org.geojson.MultiPolygon;
import org.gecko.weather.transport.Unwrap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CloudMapTest {

	private static final RegularLatLonGrid ICON_D2 = new RegularLatLonGrid("icon-d2-regular-lat-lon", 43.18, -3.94, 0.02, 1215, 746);
	private static GridField field;
	private static GeoJsonResourceFactoryImpl geojson;

	/** Two hours after the recorded field's valid time. */
	private static final Clock AT_FIVE = Clock.fixed(Instant.parse("2026-10-03T05:00:00Z"), ZoneOffset.UTC);

	@TempDir
	Path folder;

	@BeforeAll
	static void read() throws IOException {
		Path file = Path.of("..", "org.gecko.weather.provider.dwd.icon", "test", "fixtures",
				"icon-d2_germany_regular-lat-lon_single-level_2026100300_003_2d_clct.grib2.bz2");
		Grib2Field grib;
		try (InputStream in = Unwrap.bzip2(Files.newInputStream(file))) {
			grib = Grib2Reader.read(in.readAllBytes());
		}
		field = new GridField("dwd", "ICON-D2", "clct", "%", grib.referenceTime(), grib.validAt(), ICON_D2, grib.data());
		MetadataWhiteboard metadata = MetadataServiceFactory.create();
		metadata.registerPackage(GeoJsonPackage.eINSTANCE);
		geojson = new GeoJsonResourceFactoryImpl(metadata);
	}

	@Test
	void aFieldBecomesOneFeaturePerBand() {
		List<CloudBand> bands = CloudBands.of(field, Isobands.Settings.cloudCover(), "Datenbasis: Deutscher Wetterdienst");
		assertThat(bands).hasSize(4);
		CloudBand overcast = bands.get(3);
		assertThat(overcast.getId()).isEqualTo("clct-2026100300-20261003T0300Z-75");
		assertThat(overcast.getName()).isEqualTo("Bewölkung 75–100 %");
		assertThat(overcast.getLeadHours()).isEqualTo(3);
		assertThat(overcast.getValidTime().toInstant()).isEqualTo(Instant.parse("2026-10-03T03:00:00Z"));
		assertThat(overcast.getGeometry()).isInstanceOf(MultiPolygon.class);
		// Dresden (13.74° E, 51.05° N) lay under 99 % cloud cover that night
		assertThat(overcast.getMinX()).isLessThan(13.74);
		assertThat(overcast.getMaxX()).isGreaterThan(13.74);
	}

	@Test
	void aRunGoesToDiskAndComesBack() throws IOException {
		List<CloudBand> bands = CloudBands.of(field, settings(), "Datenbasis: Deutscher Wetterdienst");
		CloudMapFiles files = new CloudMapFiles(folder, "clct");
		files.write(field.run(), bands);
		Path written = files.file(field.run());
		System.out.printf("clct +3h as GeoJSON: %d KB%n", Files.size(written) / 1024);

		List<CloudBand> read = files.readNewest(new GeoJsonFeatureImporter(new GeoJsonText(geojson), CloudmapPackage.eINSTANCE, null))
				.orElseThrow();
		assertThat(read).extracting(CloudBand::getId).containsExactlyElementsOf(bands.stream().map(CloudBand::getId).toList());
		CloudBand a = bands.get(2);
		CloudBand b = read.get(2);
		assertThat(b.getValidTime()).isEqualTo(a.getValidTime());
		assertThat(b.getRun()).isEqualTo(a.getRun());
		assertThat(b.getLower()).isEqualTo(a.getLower());
		assertThat(b.getColor()).isEqualTo(a.getColor());
		assertThat(((MultiPolygon) b.getGeometry()).getPolygons()).hasSameSizeAs(((MultiPolygon) a.getGeometry()).getPolygons());
	}

	@Test
	void onlyTheNewestRunIsKept() throws IOException {
		CloudMapFiles files = new CloudMapFiles(folder, "clct");
		List<CloudBand> bands = CloudBands.of(field, settings(), "x");
		files.write(field.run().minus(Duration.ofHours(6)), bands);
		files.write(field.run(), bands);
		assertThat(files.runs()).containsExactly(files.file(field.run()));
	}

	@Test
	void aCompleteRunReplacesTheMapAndSurvivesARestart() {
		CloudMapComponent map = new CloudMapComponent(config(folder), geojson, null, AT_FIVE);
		CollectionDescriptor collection = CollectionDescriptor.of(CloudmapPackage.Literals.CLOUD_BAND).orElseThrow();
		assertThat(collection.id()).isEqualTo("cloudcover");
		assertThat(map.supports(CloudmapPackage.Literals.CLOUD_BAND)).isTrue();
		assertThat(map.query(FeatureQuery.builder(collection).build()).features()).isEmpty();

		map.runStarted("dwd", "ICON-D2", field.run());
		map.field(field);
		assertThat(map.query(FeatureQuery.builder(collection).build()).features()).as("not before the run is complete").isEmpty();
		map.runComplete("dwd", "ICON-D2", field.run());
		assertThat(map.query(FeatureQuery.builder(collection).build()).features()).hasSize(4);
		assertThat(map.extent(collection)).isPresent();

		CloudMapComponent restarted = new CloudMapComponent(config(folder), geojson, null, AT_FIVE);
		assertThat(restarted.query(FeatureQuery.builder(collection).build()).features()).hasSize(4);
		assertThat(restarted.get(collection, "clct-2026100300-20261003T0300Z-0")).isPresent();
	}

	@Test
	void aRunCutShortIsDropped() {
		CloudMapComponent map = new CloudMapComponent(config(folder), geojson, null, AT_FIVE);
		CollectionDescriptor collection = CollectionDescriptor.of(CloudmapPackage.Literals.CLOUD_BAND).orElseThrow();
		map.runStarted("dwd", "ICON-D2", field.run());
		map.field(field);
		map.runStarted("dwd", "ICON-D2", field.run().minus(Duration.ofHours(6)));
		map.runComplete("dwd", "ICON-D2", field.run());
		assertThat(map.query(FeatureQuery.builder(collection).build()).features()).isEmpty();
	}

	@Test
	void onlyTheCurrentHourIsShown() {
		CloudMapComponent map = new CloudMapComponent(config(folder), geojson, null, AT_FIVE);
		CollectionDescriptor collection = CollectionDescriptor.of(CloudmapPackage.Literals.CLOUD_BAND).orElseThrow();
		GridField later = new GridField(field.providerId(), field.productId(), field.parameter(), field.unit(), field.run(),
				field.validAt().plus(Duration.ofHours(3)), field.grid(), field.data());
		map.runStarted("dwd", "ICON-D2", field.run());
		map.field(field);
		map.field(later);
		map.runComplete("dwd", "ICON-D2", field.run());
		assertThat(map.query(FeatureQuery.builder(collection).build()).features()).hasSize(4)
				.allSatisfy(f -> assertThat(((CloudBand) f).getValidTime().toInstant()).isEqualTo(field.validAt()));

		CloudMapComponent early = new CloudMapComponent(config(folder), geojson, null,
				Clock.fixed(Instant.parse("2026-10-03T00:00:00Z"), ZoneOffset.UTC));
		assertThat(early.query(FeatureQuery.builder(collection).build()).features()).as("before the first hour: the first")
				.allSatisfy(f -> assertThat(((CloudBand) f).getValidTime().toInstant()).isEqualTo(field.validAt()));
	}

	@Test
	void theWindowAroundDresdenIsCutOutOfTheField() {
		GeoPosition dresden = WeatherFactory.eINSTANCE.createGeoPosition();
		dresden.setLatitude(51.05);
		dresden.setLongitude(13.74);
		GridWindow w = GridWindow.around(ICON_D2, List.of(dresden), 150).orElseThrow();
		GridField part = w.crop(field);
		// 150 km is 1.35° of latitude and 2.14° of longitude at 51° N: 67 and 107 cells of 0.02° each way
		assertThat(part.grid().nj()).isBetween(135, 139);
		assertThat(part.grid().ni()).isBetween(214, 218);
		assertThat(part.valueAt(10, 20)).isEqualTo(field.valueAt(w.i0() + 10, w.j0() + 20));
		assertThat(part.grid().latitude(0)).isEqualTo(ICON_D2.latitude(w.j0()));

		List<CloudBand> bands = CloudBands.of(part, settings(), "x");
		assertThat(bands).isNotEmpty().allSatisfy(b -> assertThat(b.getMinX()).isGreaterThan(11.5));
	}

	private static Isobands.Settings settings() {
		return new Isobands.Settings(new double[] { 0, 25, 50, 75, 100 }, 3, 1.0, 8);
	}

	private static CloudMapConfig config(Path folder) {
		return new CloudMapConfig() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return CloudMapConfig.class;
			}

			@Override
			public String folder() {
				return folder.toString();
			}

			@Override
			public String breaks() {
				return "0,25,50,75,100";
			}

			@Override
			public int factor() {
				return 3;
			}

			@Override
			public double tolerance() {
				return 1.0;
			}

			@Override
			public double minCells() {
				return 8;
			}

			@Override
			public double regionRadiusKm() {
				return 150;
			}

			@Override
			public String attribution() {
				return "Datenbasis: Deutscher Wetterdienst";
			}
		};
	}
}
