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
package org.gecko.weather.pv.fronius;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.fennec.codec.resource.CodecResourceFactory;
import org.eclipse.fennec.codec.util.MetadataServiceFactory;
import org.eclipse.fennec.emf.osgi.metadata.MetadataWhiteboard;

import org.gecko.weather.pv.fronius.model.solarapi.Inverter;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse;
import org.gecko.weather.pv.fronius.model.solarapi.Site;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;
import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpServer;

/**
 * Reading through the Fennec JSON codec, mapping and the HTTP round trip — against made-up answers in
 * the shape the devices give. The codec is set up as outside OSGi: a factory over a metadata
 * whiteboard that knows the solarapi package.
 */
class FroniusSolarApiTest {

	private HttpServer server;
	private final AtomicReference<String> answer = new AtomicReference<>();
	private final AtomicReference<String> requested = new AtomicReference<>();

	static ResourceSet resourceSet() {
		MetadataWhiteboard metadata = MetadataServiceFactory.create();
		metadata.registerPackage(SolarApiPackage.eINSTANCE);
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("json", new CodecResourceFactory(metadata));
		return rs;
	}

	static PowerFlowResponse read(String fixture) throws IOException {
		return PowerFlowReader.read(fixture(fixture), resourceSet());
	}

	static InputStream fixture(String name) {
		InputStream in = FroniusSolarApiTest.class.getClassLoader().getResourceAsStream("fixtures/" + name);
		assertThat(in).as(name).isNotNull();
		return in;
	}

	@BeforeEach
	void start() throws IOException {
		server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
		server.createContext("/solar_api/v1/GetPowerFlowRealtimeData.fcgi", exchange -> {
			requested.set(exchange.getRequestURI().getPath());
			byte[] body = answer.get().getBytes(StandardCharsets.UTF_8);
			exchange.getResponseHeaders().add("Content-Type", "application/json");
			exchange.sendResponseHeaders(200, body.length);
			exchange.getResponseBody().write(body);
			exchange.close();
		});
		server.start();
	}

	@AfterEach
	void stop() {
		server.stop(0);
	}

	private String base() {
		return "http://127.0.0.1:" + server.getAddress().getPort();
	}

	@Test
	void gen24WithBattery() throws IOException {
		PowerFlowResponse answer = read("powerflow-gen24-battery.json");
		assertThat(answer.getHead().getStatus().getCode()).isZero();
		assertThat(answer.getHead().getTimestamp()).isEqualTo("2026-10-04T11:15:02+00:00");
		PowerFlowData data = answer.getBody().getData();
		assertThat(data.getVersion()).isEqualTo("13");
		Site site = data.getSite();
		assertThat(site.getMode()).isEqualTo("bidirectional");
		assertThat(site.getPowerPv()).isEqualTo(1544.8);
		assertThat(site.getPowerLoad()).isEqualTo(-655.4);
		assertThat(site.getPowerGrid()).isEqualTo(-402.1);
		assertThat(site.getPowerBattery()).isEqualTo(-310.25);
		assertThat(site.getEnergyDay()).as("null on GEN24").isNull();
		assertThat(site.getEnergyTotal()).isEqualTo(512345.6);
		assertThat(site.getMeterLocation()).isEqualTo("grid");
		assertThat(data.getInverters()).hasSize(1);
		Inverter inverter = data.getInverters().get("1");
		assertThat(inverter.getDeviceType()).isEqualTo(1);
		assertThat(inverter.getPower()).isEqualTo(1210.5);
		assertThat(inverter.getStateOfCharge()).isEqualTo(42.5);
		assertThat(inverter.getBatteryMode()).isEqualTo("normal");

		PvMeasurement m = FroniusMeter.measurement(answer);
		assertThat(m.getPvPower()).isCloseTo(1.5448, within(1e-9));
		assertThat(m.getAcPower()).isCloseTo(1.2105, within(1e-9));
		assertThat(m.getLoadPower()).as("consumption positive").isCloseTo(0.6554, within(1e-9));
		assertThat(m.getGridPower()).as("feeding in is negative").isCloseTo(-0.4021, within(1e-9));
		assertThat(m.getBatteryPower()).as("charging is negative").isCloseTo(-0.31025, within(1e-9));
		assertThat(m.getStateOfCharge()).isEqualTo(42.5);
		assertThat(m.getEnergyTotal()).isCloseTo(512.3456, within(1e-9));
		assertThat(m.getTime()).as("the poller sets the time").isNull();
	}

	@Test
	void sleepingInverterProducesZeroAndLeavesTheRestUnset() throws IOException {
		PowerFlowResponse answer = read("powerflow-night-produce-only.json");
		assertThat(answer.getBody().getData().getSite().getPowerPv()).isNull();
		assertThat(answer.getBody().getData().getInverters().get("1").getPower()).isNull();
		PvMeasurement m = FroniusMeter.measurement(answer);
		assertThat(m.isSetPvPower()).isTrue();
		assertThat(m.getPvPower()).isZero();
		assertThat(m.isSetAcPower()).isFalse();
		assertThat(m.isSetLoadPower()).isFalse();
		assertThat(m.isSetGridPower()).isFalse();
		assertThat(m.isSetBatteryPower()).isFalse();
		assertThat(m.isSetStateOfCharge()).isFalse();
		assertThat(m.isSetEnergyTotal()).isFalse();
	}

	@Test
	void inverterPowerIsSummedOverAllInverters() throws IOException {
		PowerFlowResponse answer = read("powerflow-two-inverters.json");
		assertThat(answer.getBody().getData().getInverters().keySet()).containsExactlyInAnyOrder("1", "2");
		PvMeasurement m = FroniusMeter.measurement(answer);
		assertThat(m.getAcPower()).isCloseTo(0.75, within(1e-9));
		assertThat(m.isSetStateOfCharge()).isFalse();
	}

	@Test
	void statusCodeIsAnError() {
		assertThatThrownBy(() -> read("status-device-not-available.json"))
				.isInstanceOf(FroniusException.class).hasMessageContaining("12").hasMessageContaining("DeviceNotAvailable")
				.satisfies(e -> assertThat(((FroniusException) e).code()).isEqualTo(12));
	}

	@Test
	void garbageIsAnIOException() {
		assertThatThrownBy(() -> PowerFlowReader.read(new ByteArrayInputStream("<html>".getBytes(StandardCharsets.UTF_8)), resourceSet()))
				.isInstanceOf(IOException.class);
		assertThatThrownBy(() -> PowerFlowReader.read(new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8)), resourceSet()))
				.isInstanceOf(IOException.class).hasMessageContaining("Head");
	}

	@Test
	void readsThroughHttpWithOrWithoutTrailingSlash() throws IOException {
		answer.set(new String(fixture("powerflow-gen24-battery.json").readAllBytes(), StandardCharsets.UTF_8));
		for (String url : new String[] { base(), base() + "/" }) {
			Meter meter = PvFactory.eINSTANCE.createMeter();
			meter.setType(FroniusMeter.TYPE);
			meter.setUrl(url);
			PvMeasurement m = new FroniusMeter(resourceSet()).read(meter);
			assertThat(m.getPvPower()).isCloseTo(1.5448, within(1e-9));
			assertThat(requested.get()).isEqualTo("/solar_api/v1/GetPowerFlowRealtimeData.fcgi");
		}
	}

	@Test
	void notFoundHintsAtTheSwitchedOffApi() {
		FroniusSolarApi api = new FroniusSolarApi(HttpClient.newHttpClient(), URI.create(base() + "/elsewhere/"), Duration.ofSeconds(2), resourceSet());
		assertThatThrownBy(api::powerFlow).isInstanceOf(IOException.class).hasMessageContaining("404")
				.hasMessageContaining("Solar API");
	}

	@Test
	void unreachableDeviceIsAnIOException() throws IOException {
		int port;
		try (ServerSocket socket = new ServerSocket(0)) {
			port = socket.getLocalPort();
		}
		Meter meter = PvFactory.eINSTANCE.createMeter();
		meter.setUrl("http://127.0.0.1:" + port);
		assertThatThrownBy(() -> new FroniusMeter(resourceSet()).read(meter)).isInstanceOf(IOException.class);
		meter.setUrl("ftp://inverter");
		assertThatThrownBy(() -> new FroniusMeter(resourceSet()).read(meter)).isInstanceOf(IOException.class).hasMessageContaining("URL");
	}
}
