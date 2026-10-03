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
package org.gecko.weather.core;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.ZoneId;

/**
 * The parsed, framework-free form of {@link CoreConfig}, so the plain classes never see the
 * annotation.
 *
 * @param defaultTimeZone     zone for sites registered without one
 * @param maxStationBindings  stations per point product, nearest first
 * @param solarStep           spacing of computed sun positions
 * @param streamWindow        how much of a streaming source's past the report keeps
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public record CoreSettings(ZoneId defaultTimeZone, int maxStationBindings, Duration solarStep, Duration streamWindow) {

	public static final CoreSettings DEFAULTS = new CoreSettings(ZoneId.of("Europe/Berlin"), 3, Duration.ofHours(1),
			Duration.ofDays(7));

	public CoreSettings {
		requireNonNull(defaultTimeZone, "defaultTimeZone");
		requireNonNull(solarStep, "solarStep");
		requireNonNull(streamWindow, "streamWindow");
		if (maxStationBindings < 1) {
			throw new IllegalArgumentException("maxStationBindings must be at least 1");
		}
		if (solarStep.isZero() || solarStep.isNegative()) {
			throw new IllegalArgumentException("solarStep must be positive");
		}
		if (streamWindow.isZero() || streamWindow.isNegative()) {
			throw new IllegalArgumentException("streamWindow must be positive");
		}
	}

	static CoreSettings of(CoreConfig config) {
		return new CoreSettings(ZoneId.of(config.defaultTimeZone()), config.maxStationBindings(),
				Duration.parse(config.solarStep()), Duration.parse(config.streamWindow()));
	}

}
