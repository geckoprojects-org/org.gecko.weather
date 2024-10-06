/**
 * Copyright (c) 2012 - 2024 Data In Motion and others.
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
package org.gecko.weather.dwd.fc;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * COnfiguration fir the MOSMIX Station Fetcher
 * @author Mark Hoffmann
 * @since 09.09.2024
 */
@ObjectClassDefinition(name = "MOSMIX Station Configuration")
public @interface MOSMIXStationConfig {
	
	String stationId() default "NONE";
	String name() default "NONE";
	double latitude();
	double longitude();

}
