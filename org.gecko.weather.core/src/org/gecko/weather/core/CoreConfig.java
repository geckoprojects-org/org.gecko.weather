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

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the core, PID {@value WeatherCoreComponent#PID}. All values have workable
 * defaults, so the core runs unconfigured.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather Core", description = "Site registry, weather service and report assembly")
public @interface CoreConfig {

	@AttributeDefinition(name = "Default time zone", description = "IANA zone id used for sites registered without one. Day boundaries of DayInfo depend on it.")
	String defaultTimeZone() default "Europe/Berlin";

	@AttributeDefinition(name = "Stations per product", description = "How many stations a site is bound to per point product, nearest first (rank 0..n-1).")
	int maxStationBindings() default 3;

	@AttributeDefinition(name = "Solar step", description = "ISO-8601 duration between computed sun positions over the report's horizon.")
	String solarStep() default "PT1H";

	@AttributeDefinition(name = "Stream window", description = "ISO-8601 duration of observations a streaming source (own weather station) keeps in the report. Older values are dropped.")
	String streamWindow() default "P7D";

}
