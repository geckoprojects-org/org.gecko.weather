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

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * One {@code GetPowerFlowRealtimeData} answer in Fronius' own units and signs: powers in W, energy
 * in Wh; {@code load} negative for consumption, {@code grid} positive when drawing from the grid,
 * {@code battery} positive when discharging. A value is null when the device did not report it — on
 * GEN24 {@code pv} is null while the inverter sleeps, {@code grid} and {@code load} without a meter.
 *
 * @param timestamp     when the device answered, in its local time; not when it measured
 * @param version       PowerFlowVersion, which fields to expect (12, 13, …)
 * @param mode          produce-only, meter, vague-meter, bidirectional or ac-coupled
 * @param pv            PV generator power (DC side on GEN24 and Hybrid, AC on SnapInverter)
 * @param load          household load
 * @param grid          grid exchange
 * @param battery       battery power
 * @param inverter      AC output summed over all inverters
 * @param stateOfCharge battery state of charge in %, from the first inverter that reports one
 * @param energyTotal   lifetime AC energy of the site; on GEN24 updated every 5 minutes only
 * @param inverters     how many inverters answered
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public record PowerFlow(Optional<OffsetDateTime> timestamp, String version, String mode, Double pv, Double load, Double grid,
		Double battery, Double inverter, Double stateOfCharge, Double energyTotal, int inverters) {
}
