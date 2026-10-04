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
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.spi.PvMeter;
import org.osgi.service.component.annotations.Component;

/**
 * {@link PvMeter} for Fronius devices: a plant whose meter has the type {@value #TYPE} and the
 * device's base URL — {@code http://<inverter address>} — is read through its Solar API. Fronius'
 * watts become kilowatts, its signs those of {@link PvMeasurement}: consumption positive, grid
 * positive when drawing, battery positive when discharging.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Component(property = PvMeter.TYPE + "=" + FroniusMeter.TYPE)
public class FroniusMeter implements PvMeter {

	public static final String TYPE = "fronius-solar-api";

	private final Map<String, FroniusSolarApi> clients = new ConcurrentHashMap<>();

	@Override
	public PvMeasurement read(Meter meter) throws IOException {
		if (meter.getUrl() == null || meter.getUrl().isBlank()) {
			throw new IOException("Fronius meter without URL");
		}
		FroniusSolarApi api;
		try {
			api = clients.computeIfAbsent(meter.getUrl().strip(), url -> new FroniusSolarApi(URI.create(url)));
		} catch (IllegalArgumentException e) {
			throw new IOException("Not a usable Fronius URL: " + meter.getUrl(), e);
		}
		return measurement(api.powerFlow());
	}

	/** Fronius units and signs → the PV add-on's. A sleeping inverter produces nothing, not "unknown". */
	static PvMeasurement measurement(PowerFlow flow) {
		PvMeasurement m = PvFactory.eINSTANCE.createPvMeasurement();
		m.setPvPower(flow.pv() == null ? 0.0 : kilo(flow.pv()));
		if (flow.inverter() != null) {
			m.setAcPower(kilo(flow.inverter()));
		}
		if (flow.load() != null) {
			m.setLoadPower(-kilo(flow.load()));
		}
		if (flow.grid() != null) {
			m.setGridPower(kilo(flow.grid()));
		}
		if (flow.battery() != null) {
			m.setBatteryPower(kilo(flow.battery()));
		}
		if (flow.stateOfCharge() != null) {
			m.setStateOfCharge(flow.stateOfCharge());
		}
		if (flow.energyTotal() != null) {
			m.setEnergyTotal(kilo(flow.energyTotal()));
		}
		return m;
	}

	private static double kilo(double value) {
		return value / 1000.0;
	}
}
