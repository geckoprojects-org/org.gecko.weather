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
package org.gecko.weather.map;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of {@link CloudMapComponent}; every key has a default, so the map runs without one.
 *
 * @author Mark Hoffmann
 * @since 05.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather cloud cover map")
public @interface CloudMapConfig {

	@AttributeDefinition(description = "Folder of the run files, relative to the working directory")
	String folder() default "data/weather/maps";

	@AttributeDefinition(description = "Ascending band limits in %")
	String breaks() default "0,25,50,75,100";

	@AttributeDefinition(description = "Source cells per map cell along each axis (3: ICON-D2's 0.02° to 0.06°)")
	int factor() default 3;

	@AttributeDefinition(description = "Douglas-Peucker tolerance in map cells")
	double tolerance() default 1.0;

	@AttributeDefinition(description = "Polygons and holes of fewer map cells are dropped")
	double minCells() default 8;

	@AttributeDefinition(description = "Map only this far around the active sites, in km; 0 for the whole field")
	double regionRadiusKm() default 150;

	String attribution() default "Datenbasis: Deutscher Wetterdienst";
}
