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
import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicReference;

import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpServer;

/** Parsing, mapping and the HTTP round trip — against made-up answers in the shape the devices give. */
class FroniusSolarApiTest {

	private HttpServer server;
	private final AtomicReference<String> answer = new AtomicReference<>();
	private final AtomicReference<String> requested = new AtomicReference<>();

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
		PowerFlow flow = FroniusSolarApi.parsePowerFlow(fixture("powerflow-gen24-battery.json"));
		assertThat(flow.version()).isEqualTo("13");
		assertThat(flow.mode()).isEqualTo("bidirectional");
		assertThat(flow.pv()).isEqualTo(1544.8);
		assertThat(flow.load()).isEqualTo(-655.4);
		assertThat(flow.grid()).isEqualTo(-402.1);
		assertThat(flow.battery()).isEqualTo(-310.25);
		assertThat(flow.inverter()).isEqualTo(1210.5);
		assertThat(flow.stateOfCharge()).isEqualTo(42.5);
		assertThat(flow.energyTotal()).isEqualTo(512345.6);
		assertThat(flow.inverters()).isEqualTo(1);
		assertThat(flow.timestamp()).contains(OffsetDateTime.parse("2026-10-04T11:15:02Z"));

		PvMeasurement m = FroniusMeter.measurement(flow);
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
		PowerFlow flow = FroniusSolarApi.parsePowerFlow(fixture("powerflow-night-produce-only.json"));
		assertThat(flow.pv()).isNull();
		assertThat(flow.inverter()).isNull();
		PvMeasurement m = FroniusMeter.measurement(flow);
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
		PowerFlow flow = FroniusSolarApi.parsePowerFlow(fixture("powerflow-two-inverters.json"));
		assertThat(flow.inverters()).isEqualTo(2);
		assertThat(flow.inverter()).isEqualTo(750.0);
		assertThat(flow.stateOfCharge()).isNull();
	}

	@Test
	void statusCodeIsAnError() {
		assertThatThrownBy(() -> FroniusSolarApi.parsePowerFlow(fixture("status-device-not-available.json")))
				.isInstanceOf(FroniusException.class).hasMessageContaining("12").hasMessageContaining("DeviceNotAvailable")
				.satisfies(e -> assertThat(((FroniusException) e).code()).isEqualTo(12));
	}

	@Test
	void garbageIsAnIOException() {
		assertThatThrownBy(() -> FroniusSolarApi.parsePowerFlow(new ByteArrayInputStream("<html>".getBytes(StandardCharsets.UTF_8))))
				.isInstanceOf(IOException.class);
		assertThatThrownBy(() -> FroniusSolarApi.parsePowerFlow(new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8))))
				.isInstanceOf(IOException.class).hasMessageContaining("Head");
	}

	@Test
	void readsThroughHttpWithOrWithoutTrailingSlash() throws IOException {
		answer.set(new String(fixture("powerflow-gen24-battery.json").readAllBytes(), StandardCharsets.UTF_8));
		for (String url : new String[] { base(), base() + "/" }) {
			Meter meter = PvFactory.eINSTANCE.createMeter();
			meter.setType(FroniusMeter.TYPE);
			meter.setUrl(url);
			PvMeasurement m = new FroniusMeter().read(meter);
			assertThat(m.getPvPower()).isCloseTo(1.5448, within(1e-9));
			assertThat(requested.get()).isEqualTo("/solar_api/v1/GetPowerFlowRealtimeData.fcgi");
		}
	}

	@Test
	void notFoundHintsAtTheSwitchedOffApi() {
		FroniusSolarApi api = new FroniusSolarApi(HttpClient.newHttpClient(), URI.create(base() + "/elsewhere/"), Duration.ofSeconds(2));
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
		assertThatThrownBy(() -> new FroniusMeter().read(meter)).isInstanceOf(IOException.class);
		meter.setUrl("ftp://inverter");
		assertThatThrownBy(() -> new FroniusMeter().read(meter)).isInstanceOf(IOException.class).hasMessageContaining("URL");
	}
}
