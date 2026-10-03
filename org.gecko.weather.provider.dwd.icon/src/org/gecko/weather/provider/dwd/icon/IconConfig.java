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
package org.gecko.weather.provider.dwd.icon;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the ICON-D2 provider, PID {@value IconProviderComponent#PID}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather DWD ICON-D2", description = "DWD ICON-D2 gridded forecasts (regular lat/lon, single level) as a weather provider")
public @interface IconConfig {

	@AttributeDefinition(name = "Base URL", description = "DWD Open Data folder holding the run folders 00/ .. 21/.")
	String baseUrl() default "https://opendata.dwd.de/weather/nwp/icon-d2/grib/";

	@AttributeDefinition(name = "Runs", description = "Comma-separated UTC run hours to ingest; ICON-D2 publishes 00,03,06,09,12,15,18,21. Each run is ~500 MB for the default parameters.")
	String runs() default "00,06,12,18";

	@AttributeDefinition(name = "Horizon hours", description = "Forecast steps to fetch, 1..48.")
	int horizonHours() default 48;

	@AttributeDefinition(name = "Parameters", description = "Comma-separated DWD parameter names: clct, clcl, clcm, clch, aswdir_s, aswdifd_s.")
	String parameters() default "clct,clcl,clcm,clch,aswdir_s,aswdifd_s";

	@AttributeDefinition(name = "Licence", description = "Licence identifier carried with every value.")
	String licence() default "GeoNutzV";

	@AttributeDefinition(name = "Attribution", description = "Attribution text carried with every value.")
	String attribution() default "Datenbasis: Deutscher Wetterdienst";
}
