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
package org.gecko.weather.provider.ecowitt;

/**
 * The Ecowitt station models this bundle knows, and the device model name rtl_433's decoder gives
 * their telegrams. The enum name is the product id of the station's datasets.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
public enum StationModel {

	/** WS90 "Wittboy": 7-in-1 ultrasonic station with piezo rain gauge, 868 MHz in Europe. */
	WS90("Fineoffset-WS90"),
	/** WS80: 6-in-1 ultrasonic station without rain. */
	WS80("Fineoffset-WS80");

	private final String rtl433Model;

	StationModel(String rtl433Model) {
		this.rtl433Model = rtl433Model;
	}

	/** The {@code model} member rtl_433 writes into the telegram's JSON. */
	public String rtl433Model() {
		return rtl433Model;
	}
}
