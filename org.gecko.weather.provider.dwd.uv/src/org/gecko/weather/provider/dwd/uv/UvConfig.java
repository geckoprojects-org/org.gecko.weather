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
package org.gecko.weather.provider.dwd.uv;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the UV index provider, PID {@value UvProviderComponent#PID}.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather DWD UV index", description = "DWD daily UV index forecast (clouded sky, daily maximum, ICON-EU grid) as a weather provider")
public @interface UvConfig {

	@AttributeDefinition(name = "Base URL", description = "DWD Open Data folder holding the health forecasts (Z__C_EDZW_*icreu_uvi_icreu*.bin).")
	String baseUrl() default "https://opendata.dwd.de/climate_environment/health/forecasts/";

	@AttributeDefinition(name = "Licence", description = "Licence identifier carried with every value.")
	String licence() default "GeoNutzV";

	@AttributeDefinition(name = "Attribution", description = "Attribution text carried with every value.")
	String attribution() default "Datenbasis: Deutscher Wetterdienst";
}
