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
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.weather.pv.fronius.model.solarapi.Inverter;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse;
import org.gecko.weather.pv.fronius.model.solarapi.Site;
import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.spi.PvMeter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * {@link PvMeter} for Fronius devices: a plant whose meter has the type {@value #TYPE} and the
 * device's base URL — {@code http://<inverter address>} — is read through its Solar API. Fronius'
 * watts become kilowatts, its signs those of {@link PvMeasurement}: consumption positive, grid
 * positive when drawing, battery positive when discharging. With several inverters the AC power is
 * their sum and the state of charge that of the lowest device number reporting one.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Component(property = PvMeter.TYPE + "=" + FroniusMeter.TYPE)
public class FroniusMeter implements PvMeter {

	public static final String TYPE = "fronius-solar-api";

	private final ResourceSet resourceSet;
	private final Map<String, FroniusSolarApi> clients = new ConcurrentHashMap<>();

	/** The solarapi model's own resource set; with the Fennec codec running it reads JSON. */
	@Activate
	public FroniusMeter(@Reference(target = "(emf.name=solarapi)", scope = ReferenceScope.PROTOTYPE) ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
	public PvMeasurement read(Meter meter) throws IOException {
		if (meter.getUrl() == null || meter.getUrl().isBlank()) {
			throw new IOException("Fronius meter without URL");
		}
		FroniusSolarApi api;
		try {
			api = clients.computeIfAbsent(meter.getUrl().strip(), url -> new FroniusSolarApi(URI.create(url), resourceSet));
		} catch (IllegalArgumentException e) {
			throw new IOException("Not a usable Fronius URL: " + meter.getUrl(), e);
		}
		return measurement(api.powerFlow());
	}

	/** Fronius units and signs → the PV add-on's. A sleeping inverter produces nothing, not "unknown". */
	static PvMeasurement measurement(PowerFlowResponse answer) {
		PowerFlowData data = answer.getBody().getData();
		Site site = data.getSite();
		PvMeasurement m = PvFactory.eINSTANCE.createPvMeasurement();
		m.setPvPower(site.getPowerPv() == null ? 0.0 : kilo(site.getPowerPv()));
		Double inverterPower = null;
		Double stateOfCharge = null;
		for (String key : new TreeMap<>(data.getInverters().map()).keySet()) {
			Inverter inverter = data.getInverters().get(key);
			if (inverter == null) {
				continue;
			}
			if (inverter.getPower() != null) {
				inverterPower = (inverterPower == null ? 0 : inverterPower) + inverter.getPower();
			}
			if (stateOfCharge == null) {
				stateOfCharge = inverter.getStateOfCharge();
			}
		}
		if (inverterPower != null) {
			m.setAcPower(kilo(inverterPower));
		}
		if (site.getPowerLoad() != null) {
			m.setLoadPower(-kilo(site.getPowerLoad()));
		}
		if (site.getPowerGrid() != null) {
			m.setGridPower(kilo(site.getPowerGrid()));
		}
		if (site.getPowerBattery() != null) {
			m.setBatteryPower(kilo(site.getPowerBattery()));
		}
		if (stateOfCharge != null) {
			m.setStateOfCharge(stateOfCharge);
		}
		if (site.getEnergyTotal() != null) {
			m.setEnergyTotal(kilo(site.getEnergyTotal()));
		}
		return m;
	}

	private static double kilo(double value) {
		return value / 1000.0;
	}
}
