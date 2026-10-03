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
package org.gecko.weather.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.weather.model.conversion.JavaTimeConversionDelegateFactory;
import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Plain-JUnit round trip of the model through XMI, without an OSGi framework. Verifies that the
 * generated model loads and that the {@code java.time} data types serialise through the
 * conversion delegate.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class WeatherModelXmiTest {

	private static final WeatherFactory F = WeatherFactory.eINSTANCE;

	private static final Instant ISSUED = Instant.parse("2026-10-03T06:00:00Z");
	private static final Instant VALID = Instant.parse("2026-10-03T14:00:00Z");

	@BeforeAll
	static void registerDelegates() {
		JavaTimeConversionDelegateFactory.register();
	}

	@Test
	void siteWithBindingsRoundTripsThroughXmi() throws IOException {
		Site site = F.createSite();
		site.setId("home");
		site.setName("Home roof");
		site.setPosition(position(51.05, 13.74, 118));
		site.setTimeZone("Europe/Berlin");
		site.setRegisteredAt(ISSUED);
		site.getAttributes().put("pv.tilt", "35");

		Station station = F.createStation();
		station.setId("10488");
		station.setName("Dresden-Klotzsche");
		station.setPosition(position(51.13, 13.75, 227));
		StationBinding sb = F.createStationBinding();
		sb.setProviderId("dwd");
		sb.setProductId("MOSMIX_L");
		sb.setOrigin(BindingOrigin.AUTOMATIC);
		sb.setDistanceMeters(8900);
		sb.setElevationDeltaMeters(109);
		sb.setResolvedAt(ISSUED);
		sb.setStation(station);
		site.getBindings().add(sb);

		GridBinding gb = F.createGridBinding();
		gb.setProviderId("dwd");
		gb.setProductId("ICON-D2");
		gb.setOrigin(BindingOrigin.MANUAL);
		gb.setDistanceMeters(640);
		gb.setCell(cell("icon-d2-regular-lat-lon", 884, 394, 0.02));
		site.getBindings().add(gb);

		Site loaded = roundTrip(site, Site.class);

		assertThat(loaded.getId()).isEqualTo("home");
		assertThat(loaded.getRegisteredAt()).isEqualTo(ISSUED);
		assertThat(loaded.getAttributes().map()).containsExactly(Map.entry("pv.tilt", "35"));
		assertThat(loaded.getBindings()).hasSize(2);
		assertThat(loaded.getBindings().get(0)).isInstanceOfSatisfying(StationBinding.class, b -> {
			assertThat(b.getStation().getId()).isEqualTo("10488");
			assertThat(b.isSetElevationDeltaMeters()).isTrue();
			assertThat(b.getElevationDeltaMeters()).isEqualTo(109);
		});
		assertThat(loaded.getBindings().get(1)).isInstanceOfSatisfying(GridBinding.class, b -> {
			assertThat(b.getOrigin()).isEqualTo(BindingOrigin.MANUAL);
			assertThat(b.getCell().getI()).isEqualTo(884);
			assertThat(b.getCell().getResolutionDegrees()).isEqualTo(0.02);
		});
		assertThat(EcoreUtil.equals(site, loaded)).isTrue();
	}

	@Test
	void reportWithDatasetsRoundTripsThroughXmi() throws IOException {
		WeatherReport report = F.createWeatherReport();
		report.setSiteId("home");
		report.setGeneratedAt(ISSUED.plusSeconds(90));

		SourceDataset mosmix = dataset("MOSMIX_L", Origin.STATION, Duration.ofHours(6));
		mosmix.setStationId("10488");
		mosmix.setDistanceMeters(8900);
		mosmix.getValues().add(value(MeasurementKind.AIR_TEMPERATURE, Level.GROUND_2M, 17.3, "Cel", mosmix));
		MeasuredValue gustProb = value(MeasurementKind.WIND_GUST, Level.GROUND_10M, 35, "%", mosmix);
		gustProb.setStatistic(Statistic.PROBABILITY);
		gustProb.setPeriod(Duration.ofHours(12));
		gustProb.setThreshold(25);
		gustProb.setThresholdUnit("[kn_i]");
		mosmix.getValues().add(gustProb);
		MeasuredValue ww = value(MeasurementKind.SIGNIFICANT_WEATHER, Level.UNSPECIFIED, Double.NaN, "1", mosmix);
		ww.unsetValue();
		ww.setCode(61);
		mosmix.getValues().add(ww);
		report.getDatasets().add(mosmix);

		SourceDataset icon = dataset("ICON-D2", Origin.GRID_CELL, Duration.ofHours(3));
		icon.setModelRun(ISSUED.minusSeconds(3 * 3600));
		icon.setCell(cell("icon-d2-regular-lat-lon", 884, 394, 0.02));
		MeasuredValue clcl = value(MeasurementKind.CLOUD_COVER, Level.CLOUD_LOW, 62.5, "%", icon);
		clcl.getProvenance().setCell(cell("icon-d2-regular-lat-lon", 884, 394, 0.02));
		clcl.getProvenance().setModelRun(icon.getModelRun());
		icon.getValues().add(clcl);
		MeasuredValue dir = value(MeasurementKind.DIRECT_RADIATION, Level.SURFACE, 412.0, "W/m2", icon);
		dir.setStatistic(Statistic.MEAN);
		dir.setPeriod(Duration.ofHours(1));
		icon.getValues().add(dir);
		report.getDatasets().add(icon);

		DayInfo day = F.createDayInfo();
		day.setDate(LocalDate.of(2026, 10, 3));
		day.setSunrise(Instant.parse("2026-10-03T05:14:00Z"));
		day.setSunset(Instant.parse("2026-10-03T16:40:00Z"));
		day.setDayLength(Duration.ofMinutes(686));
		day.setMaxSunElevation(34.8);
		day.setProvenance(computedProvenance());
		report.getDays().add(day);

		String xml = toXmi(report);
		assertThat(xml)
				.contains("validAt=\"2026-10-03T14:00:00Z\"")
				.contains("period=\"PT12H\"")
				.contains("date=\"2026-10-03\"");

		WeatherReport loaded = roundTrip(report, WeatherReport.class);

		assertThat(loaded.getDatasets()).hasSize(2);
		SourceDataset loadedMosmix = loaded.getDatasets().get(0);
		assertThat(loadedMosmix.getExpectedRefresh()).isEqualTo(Duration.ofHours(6));
		assertThat(loadedMosmix.getValues()).hasSize(3);
		MeasuredValue loadedGust = loadedMosmix.getValues().get(1);
		assertThat(loadedGust.getStatistic()).isEqualTo(Statistic.PROBABILITY);
		assertThat(loadedGust.getPeriod()).isEqualTo(Duration.ofHours(12));
		assertThat(loadedGust.getThreshold()).isEqualTo(25);
		MeasuredValue loadedWw = loadedMosmix.getValues().get(2);
		assertThat(loadedWw.isSetValue()).isFalse();
		assertThat(loadedWw.getCode()).isEqualTo(61);

		MeasuredValue loadedClcl = loaded.getDatasets().get(1).getValues().get(0);
		assertThat(loadedClcl.getProvenance().getOrigin()).isEqualTo(Origin.GRID_CELL);
		assertThat(loadedClcl.getProvenance().getCell().getJ()).isEqualTo(394);
		assertThat(loadedClcl.getProvenance().getModelRun()).isEqualTo(ISSUED.minusSeconds(3 * 3600));
		assertThat(loadedClcl.getUncertainty().getLeadTime()).isEqualTo(Duration.ofHours(8));

		assertThat(loaded.getDays().get(0).getDate()).isEqualTo(LocalDate.of(2026, 10, 3));
		assertThat(loaded.getDays().get(0).getDayLength()).isEqualTo(Duration.ofMinutes(686));
		assertThat(EcoreUtil.equals(report, loaded)).isTrue();
	}

	@Test
	void unsetOptionalsStayUnsetAfterRoundTrip() throws IOException {
		Site site = F.createSite();
		site.setId("bare");
		site.setPosition(position(50.0, 10.0, 0));
		site.getPosition().unsetElevation();

		Site loaded = roundTrip(site, Site.class);

		assertThat(loaded.getPosition().isSetElevation()).isFalse();
		assertThat(loaded.getRegisteredAt()).isNull();
		assertThat(loaded.isActive()).isTrue();
	}

	// --- helpers -------------------------------------------------------------------------

	private static GeoPosition position(double lat, double lon, double elevation) {
		GeoPosition p = F.createGeoPosition();
		p.setLatitude(lat);
		p.setLongitude(lon);
		p.setElevation(elevation);
		return p;
	}

	private static GridCell cell(String gridId, int i, int j, double resolution) {
		GridCell c = F.createGridCell();
		c.setGridId(gridId);
		c.setI(i);
		c.setJ(j);
		c.setResolutionDegrees(resolution);
		c.setCenter(position(51.05, 13.74, 0));
		c.getCenter().unsetElevation();
		return c;
	}

	private static SourceDataset dataset(String productId, Origin origin, Duration refresh) {
		SourceDataset d = F.createSourceDataset();
		d.setProviderId("dwd");
		d.setProductId(productId);
		d.setIssuedAt(ISSUED);
		d.setRetrievedAt(ISSUED.plusSeconds(60));
		d.setExpectedRefresh(refresh);
		d.setOrigin(origin);
		d.setHorizonStart(ISSUED);
		d.setHorizonEnd(ISSUED.plusSeconds(48 * 3600));
		d.setLicence("DL-DE-BY-2.0");
		d.setAttribution("Deutscher Wetterdienst");
		return d;
	}

	private static MeasuredValue value(MeasurementKind kind, Level level, double v, String unit, SourceDataset ds) {
		MeasuredValue mv = F.createMeasuredValue();
		mv.setKind(kind);
		mv.setLevel(level);
		mv.setValidAt(VALID);
		mv.setValue(v);
		mv.setUnit(unit);
		Provenance p = F.createProvenance();
		p.setProviderId(ds.getProviderId());
		p.setProductId(ds.getProductId());
		p.setIssuedAt(ds.getIssuedAt());
		p.setRetrievedAt(ds.getRetrievedAt());
		p.setOrigin(ds.getOrigin());
		p.setStationId(ds.getStationId());
		if (ds.isSetDistanceMeters()) {
			p.setDistanceMeters(ds.getDistanceMeters());
		}
		p.setLicence(ds.getLicence());
		mv.setProvenance(p);
		Uncertainty u = F.createUncertainty();
		u.setQuality(Quality.FORECAST);
		u.setLeadTime(Duration.between(ds.getIssuedAt(), VALID));
		mv.setUncertainty(u);
		return mv;
	}

	private static Provenance computedProvenance() {
		Provenance p = F.createProvenance();
		p.setProviderId("gecko");
		p.setProductId("solar");
		p.setIssuedAt(ISSUED);
		p.setOrigin(Origin.COMPUTED);
		p.setDistanceMeters(0);
		p.setDerivation(F.createDerivation());
		p.getDerivation().setFunctionId("solar.day-events/time4j");
		p.getDerivation().getInputs().add("site:home");
		return p;
	}

	private static ResourceSet resourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		rs.getPackageRegistry().put(WeatherPackage.eNS_URI, WeatherPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		return rs;
	}

	private static String toXmi(EObject root) throws IOException {
		Resource out = resourceSet().createResource(URI.createURI("memory:/out.xmi"));
		out.getContents().add(root);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		out.save(bytes, null);
		return bytes.toString(StandardCharsets.UTF_8);
	}

	private static <T extends EObject> T roundTrip(T root, Class<T> type) throws IOException {
		String xml = toXmi(root);
		Resource in = resourceSet().createResource(URI.createURI("memory:/in.xmi"));
		in.load(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), null);
		assertThat(in.getErrors()).isEmpty();
		assertThat(in.getContents()).hasSize(1);
		return type.cast(in.getContents().get(0));
	}

}
