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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse;

/**
 * Client for the local Fronius Solar API v1 of an inverter or data logger (document 42,0410,2012).
 * Reads {@code GetPowerFlowRealtimeData.fcgi}, the one request every platform answers — Datamanager,
 * Hybrid, GEN24 and Tauro — into the {@code solarapi} model with the Fennec JSON codec. Plain HTTP in
 * the local network, no credentials. On GEN24 the Solar API must be switched on in the web UI
 * (Communication → Solar API); a 404 means it is off.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class FroniusSolarApi {

	static final String POWER_FLOW = "solar_api/v1/GetPowerFlowRealtimeData.fcgi";

	private final HttpClient client;
	private final URI base;
	private final Duration timeout;
	private final ResourceSet resourceSet;

	/**
	 * A client with 5 s for the connection and the answer — the device is in the local network.
	 *
	 * @param resourceSet a resource set that reads {@code .json} with the Fennec codec
	 */
	public FroniusSolarApi(URI base, ResourceSet resourceSet) {
		this(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build(), base, Duration.ofSeconds(5), resourceSet);
	}

	public FroniusSolarApi(HttpClient client, URI base, Duration timeout, ResourceSet resourceSet) {
		this.client = requireNonNull(client, "client");
		this.base = normalize(requireNonNull(base, "base"));
		this.timeout = requireNonNull(timeout, "timeout");
		this.resourceSet = requireNonNull(resourceSet, "resourceSet");
	}

	public URI base() {
		return base;
	}

	/** The current power flow. */
	public PowerFlowResponse powerFlow() throws IOException {
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
			return PowerFlowReader.read(body, resourceSet);
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
