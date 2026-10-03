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
package org.gecko.weather.provider.dwd.sis;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the SIS analysis provider, PID {@value SisAnalysisComponent#PID}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather DWD SIS analysis", description = "DWD SIS satellite global radiation analysis (Germany, 0.05°, every 15 minutes) as a streaming weather provider")
public @interface SisAnalysisConfig {

	@AttributeDefinition(name = "Base URL", description = "DWD Open Data folder holding the SISin*DEv3.nc files.")
	String baseUrl() default "https://opendata.dwd.de/weather/satellite/radiation/sis/";

	@AttributeDefinition(name = "Backfill steps", description = "How many of the newest analyses a site without data gets on first sight (15-minute steps; the server keeps about 40).")
	int backfillSteps() default 8;

	@AttributeDefinition(name = "Licence", description = "Licence identifier carried with every value.")
	String licence() default "GeoNutzV";

	@AttributeDefinition(name = "Attribution", description = "Attribution text carried with every value.")
	String attribution() default "Datenbasis: Deutscher Wetterdienst";
}
