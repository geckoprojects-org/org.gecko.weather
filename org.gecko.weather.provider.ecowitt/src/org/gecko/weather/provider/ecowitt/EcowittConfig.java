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

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * One own Ecowitt station: which model, which device, how it is attached and where it stands.
 * Factory configuration, PID {@value EcowittStation#PID}; one instance per station.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather Ecowitt station", description = "An own Ecowitt weather station as a local source (Origin.LOCAL_STATION): which model, which device, how its telegrams arrive and at which site it stands. One configuration per station.")
public @interface EcowittConfig {

	@AttributeDefinition(name = "Station model", description = "Which Ecowitt station: WS90 (7-in-1 ultrasonic with rain) or WS80. Becomes the product id and selects the rtl_433 decoder model.")
	StationModel station() default StationModel.WS90;

	@AttributeDefinition(name = "Device id", description = "The sensor id as rtl_433 prints it (member \"id\"), e.g. 75459 — the station id in bindings and provenance. Empty: every device of the model is accepted and its id logged; bind it afterwards.")
	String deviceId() default "";

	@AttributeDefinition(name = "Transport", description = "How the telegrams arrive. SDR: rtl_433 on a DVB-T/RTL-SDR stick serving its decoded output as a JSON line stream (rtl_433 -F http).")
	Transport transport() default Transport.SDR;

	@AttributeDefinition(name = "Stream URL", description = "The rtl_433 HTTP line stream (SDR transport).")
	String url() default "http://127.0.0.1:8433/stream";

	@AttributeDefinition(name = "Reporting interval", description = "How often a telegram is appended to the site's report, ISO-8601 duration. The first telegram after the interval is taken; in between, telegrams only feed the gust maximum and the battery watch. PT0S appends every telegram (~9 s, which grows the report by about 125,000 values a day).")
	String interval() default "PT1M";

	@AttributeDefinition(name = "Site", description = "Id of the registered site the station stands at: the site gets bound to the device automatically (weather:register, rebind). Empty: bind by hand with weather:assign <site> ecowitt <model> <deviceId>.")
	String siteId() default "";

	@AttributeDefinition(name = "Attribution", description = "Attribution text carried with every value.")
	String attribution() default "Eigene Wetterstation";

	@AttributeDefinition(name = "Licence", description = "Licence identifier carried with every value.")
	String licence() default "private";
}
