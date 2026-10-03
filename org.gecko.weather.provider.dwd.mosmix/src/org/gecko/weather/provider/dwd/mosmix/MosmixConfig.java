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
package org.gecko.weather.provider.dwd.mosmix;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the MOSMIX provider, PID {@value MosmixProviderComponent#PID}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather DWD MOSMIX", description = "DWD MOSMIX point forecasts as a weather provider")
public @interface MosmixConfig {

	@AttributeDefinition(name = "Product", description = "MOSMIX_L (per station, 6-hourly, ~115 elements) or MOSMIX_S (all stations in one file, hourly, ~40 elements).", options = {
			@org.osgi.service.metatype.annotations.Option(label = "MOSMIX_L", value = "MOSMIX_L"),
			@org.osgi.service.metatype.annotations.Option(label = "MOSMIX_S", value = "MOSMIX_S") })
	String product() default MosmixProvider.MOSMIX_L;

	@AttributeDefinition(name = "Base URL", description = "DWD Open Data folder holding MOSMIX_L/ and MOSMIX_S/.")
	String baseUrl() default "https://opendata.dwd.de/weather/local_forecasts/mos/";

	@AttributeDefinition(name = "Station catalogue URL", description = "The mosmix_stationskatalog.cfg to resolve bindings from.")
	String catalogUrl() default "https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/mosmix_stationskatalog.cfg?view=nasPublication&nn=16102";

	@AttributeDefinition(name = "Catalogue max age", description = "ISO-8601 duration after which a stored catalogue is fetched again.")
	String catalogMaxAge() default "P30D";

	@AttributeDefinition(name = "Licence", description = "Licence identifier carried with every value.")
	String licence() default "GeoNutzV";

	@AttributeDefinition(name = "Attribution", description = "Attribution text carried with every value.")
	String attribution() default "Datenbasis: Deutscher Wetterdienst";

}
