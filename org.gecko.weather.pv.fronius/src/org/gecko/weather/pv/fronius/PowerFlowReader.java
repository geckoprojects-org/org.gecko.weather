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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.fennec.codec.constants.CodecOptions;
import org.gecko.weather.pv.fronius.model.solarapi.Head;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;
import org.gecko.weather.pv.fronius.model.solarapi.Status;

/**
 * Reads a Solar API answer into the {@code solarapi} model through a resource set with the Fennec
 * JSON codec — the root type comes as a load option, the member names from the model's codec keys.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class PowerFlowReader {

	private PowerFlowReader() {
	}

	/**
	 * Reads a {@code GetPowerFlowRealtimeData} answer. Members the model does not know (Smartloads,
	 * SecondaryMeters, …) are skipped.
	 *
	 * @throws FroniusException if the common response header reports an error
	 * @throws IOException      if the answer is no JSON, or lacks the header or the site
	 */
	public static PowerFlowResponse read(InputStream in, ResourceSet resourceSet) throws IOException {
		PowerFlowResponse answer;
		synchronized (resourceSet) {
			Resource resource = resourceSet.createResource(URI.createURI("fronius-powerflow.json"));
			if (resource == null) {
				throw new IOException("No resource factory for JSON in the resource set — is the Fennec codec running?");
			}
			try {
				resource.load(in, Map.of(CodecOptions.CODEC_ROOT_TYPE, SolarApiPackage.Literals.POWER_FLOW_RESPONSE));
				if (!resource.getErrors().isEmpty()) {
					throw new IOException("Not a Solar API answer: " + resource.getErrors().get(0).getMessage());
				}
				if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof PowerFlowResponse r)) {
					throw new IOException("Not a Solar API answer: no content");
				}
				answer = r;
				resource.getContents().clear();
			} finally {
				resourceSet.getResources().remove(resource);
			}
		}
		Head head = answer.getHead();
		Status status = head == null ? null : head.getStatus();
		if (status == null) {
			throw new IOException("Solar API answer without Head.Status");
		}
		if (status.getCode() != 0) {
			throw new FroniusException(status.getCode(), status.getReason());
		}
		if (answer.getBody() == null || answer.getBody().getData() == null || answer.getBody().getData().getSite() == null) {
			throw new IOException("Power flow without Site — not a GetPowerFlowRealtimeData answer");
		}
		return answer;
	}

}
