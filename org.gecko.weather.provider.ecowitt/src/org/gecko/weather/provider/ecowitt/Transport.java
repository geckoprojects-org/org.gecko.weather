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
 * How a station's telegrams reach the runtime.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
public enum Transport {

	/**
	 * Software-defined radio: rtl_433 on a DVB-T/RTL-SDR stick decodes the 868 MHz telegrams and
	 * serves them as a JSON line stream over HTTP ({@code rtl_433 -F http}, {@code /stream}).
	 */
	SDR
}
