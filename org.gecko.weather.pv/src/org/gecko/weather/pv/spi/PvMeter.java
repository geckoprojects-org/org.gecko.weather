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
package org.gecko.weather.pv.spi;

import java.io.IOException;

import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Reads the current power flow of a plant from a device — an inverter, a data logger, a meter.
 * Registered as a service with the property {@value #TYPE}; a plant whose {@link Meter#getType()}
 * matches is read through it. The PV add-on polls, stores and compares; a reader only talks to the
 * device.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@ConsumerType
public interface PvMeter {

	/** Service property: the meter type this reader handles, e.g. {@code fronius-solar-api}. */
	String TYPE = "pv.meter.type";

	/**
	 * One reading now. The time is left to the caller.
	 *
	 * @throws IOException if the device cannot be reached or reports an error
	 */
	PvMeasurement read(Meter meter) throws IOException;
}
