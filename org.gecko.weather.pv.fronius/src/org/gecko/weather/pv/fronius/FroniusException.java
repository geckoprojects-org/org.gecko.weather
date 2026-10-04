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
import java.util.Map;

/**
 * The Solar API answered, but its common response header reports an error — the device behind a
 * Datamanager is not available, the request is not supported, and so on.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public class FroniusException extends IOException {

	private static final long serialVersionUID = 1L;

	/** The status names of the Solar API v1 error code table. */
	private static final Map<Integer, String> NAMES = Map.ofEntries(Map.entry(1, "NotImplemented"),
			Map.entry(2, "Uninitialized"), Map.entry(3, "Initialized"), Map.entry(4, "Running"), Map.entry(5, "Timeout"),
			Map.entry(6, "Argument Error"), Map.entry(7, "LNRequestError"), Map.entry(8, "LNRequestTimeout"),
			Map.entry(9, "LNParseError"), Map.entry(10, "ConfigIOError"), Map.entry(11, "NotSupported"),
			Map.entry(12, "DeviceNotAvailable"), Map.entry(255, "UnknownError"));

	private final int code;

	public FroniusException(int code, String reason) {
		super("Solar API status " + code + " (" + NAMES.getOrDefault(code, "?") + ")"
				+ (reason == null || reason.isBlank() ? "" : ": " + reason));
		this.code = code;
	}

	public int code() {
		return code;
	}
}
