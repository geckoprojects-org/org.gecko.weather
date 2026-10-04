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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

/**
 * Client for the local Fronius Solar API v1 of an inverter or data logger (document 42,0410,2012).
 * Reads {@code GetPowerFlowRealtimeData.fcgi}, the one request every platform answers — Datamanager,
 * Hybrid, GEN24 and Tauro. Plain HTTP in the local network, no credentials. On GEN24 the Solar API
 * must be switched on in the web UI (Communication → Solar API); a 404 means it is off.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class FroniusSolarApi {

	static final String POWER_FLOW = "solar_api/v1/GetPowerFlowRealtimeData.fcgi";

	private final HttpClient client;
	private final URI base;
	private final Duration timeout;

	/** A client with 5 s for the connection and the answer — the device is in the local network. */
	public FroniusSolarApi(URI base) {
		this(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build(), base, Duration.ofSeconds(5));
	}

	public FroniusSolarApi(HttpClient client, URI base, Duration timeout) {
		this.client = requireNonNull(client, "client");
		this.base = normalize(requireNonNull(base, "base"));
		this.timeout = requireNonNull(timeout, "timeout");
	}

	public URI base() {
		return base;
	}

	/** The current power flow. */
	public PowerFlow powerFlow() throws IOException {
		URI uri = base.resolve(POWER_FLOW);
		HttpRequest request = HttpRequest.newBuilder(uri).GET().timeout(timeout).header("Accept", "application/json").build();
		HttpResponse<InputStream> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
		} catch (HttpTimeoutException e) {
			throw new IOException("No answer from " + uri + " within " + timeout.toSeconds() + " s", e);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new IOException("Interrupted while reading " + uri, e);
		}
		try (InputStream body = response.body()) {
			if (response.statusCode() == 404) {
				throw new IOException("HTTP 404 for " + uri + " — not a Fronius device, or its Solar API is switched off (GEN24: Communication → Solar API)");
			}
			if (response.statusCode() != 200) {
				throw new IOException("HTTP " + response.statusCode() + " for " + uri);
			}
			return parsePowerFlow(body);
		}
	}

	/**
	 * Parses a {@code GetPowerFlowRealtimeData} answer.
	 *
	 * @throws FroniusException if the common response header reports an error
	 * @throws IOException      if the answer is no JSON or lacks the site object
	 */
	public static PowerFlow parsePowerFlow(InputStream in) throws IOException {
		JsonObject root;
		try (JsonReader reader = Json.createReader(in)) {
			root = reader.readObject();
		} catch (JsonException | ClassCastException e) {
			throw new IOException("Not a Solar API answer: " + e.getMessage(), e);
		}
		JsonObject head = object(root, "Head").orElseThrow(() -> new IOException("Solar API answer without Head"));
		JsonObject status = object(head, "Status").orElseThrow(() -> new IOException("Solar API answer without Head.Status"));
		int code = number(status, "Code").map(Double::intValue).orElse(255);
		if (code != 0) {
			throw new FroniusException(code, text(status, "Reason").orElse(""));
		}
		Optional<OffsetDateTime> timestamp = text(head, "Timestamp").flatMap(FroniusSolarApi::timestamp);
		JsonObject data = object(root, "Body").flatMap(b -> object(b, "Data"))
				.orElseThrow(() -> new IOException("Solar API answer without Body.Data"));
		JsonObject site = object(data, "Site").orElseThrow(() -> new IOException("Power flow without Site — not a GetPowerFlowRealtimeData answer"));

		Double inverterPower = null;
		Double stateOfCharge = null;
		int inverters = 0;
		Optional<JsonObject> all = object(data, "Inverters");
		if (all.isPresent()) {
			for (String key : all.get().keySet()) {
				Optional<JsonObject> inverter = object(all.get(), key);
				if (inverter.isEmpty()) {
					continue;
				}
				inverters++;
				Optional<Double> p = number(inverter.get(), "P");
				if (p.isPresent()) {
					inverterPower = (inverterPower == null ? 0 : inverterPower) + p.get();
				}
				if (stateOfCharge == null) {
					stateOfCharge = number(inverter.get(), "SOC").orElse(null);
				}
			}
		}
		return new PowerFlow(timestamp, text(data, "Version").orElse(null), text(site, "Mode").orElse(null),
				number(site, "P_PV").orElse(null), number(site, "P_Load").orElse(null), number(site, "P_Grid").orElse(null),
				number(site, "P_Akku").orElse(null), inverterPower, stateOfCharge, number(site, "E_Total").orElse(null), inverters);
	}

	private static Optional<JsonObject> object(JsonObject parent, String name) {
		JsonValue v = parent.get(name);
		return v != null && v.getValueType() == JsonValue.ValueType.OBJECT ? Optional.of(v.asJsonObject()) : Optional.empty();
	}

	private static Optional<Double> number(JsonObject parent, String name) {
		JsonValue v = parent.get(name);
		return v instanceof JsonNumber n ? Optional.of(n.doubleValue()) : Optional.empty();
	}

	/** A string, or a number written as one — the version comes as "12" on some firmware and as 12 on others. */
	private static Optional<String> text(JsonObject parent, String name) {
		JsonValue v = parent.get(name);
		if (v == null) {
			return Optional.empty();
		}
		return switch (v.getValueType()) {
			case STRING -> Optional.of(parent.getString(name));
			case NUMBER -> Optional.of(v.toString());
			default -> Optional.empty();
		};
	}

	private static Optional<OffsetDateTime> timestamp(String value) {
		try {
			return Optional.of(OffsetDateTime.parse(value.strip()));
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}

	private static URI normalize(URI base) {
		if (!"http".equalsIgnoreCase(base.getScheme()) && !"https".equalsIgnoreCase(base.getScheme())) {
			throw new IllegalArgumentException("Not an http(s) URL: " + base);
		}
		String s = base.toString();
		return s.endsWith("/") ? base : URI.create(s + "/");
	}
}
