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
package org.gecko.weather.repository.file;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the file repository, PID {@value FileWeatherRepositoryComponent#PID}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather File Repository", description = "Folder of XMI files holding sites, reports, archive and catalogues")
public @interface FileRepositoryConfig {

	@AttributeDefinition(name = "Root folder", description = "Absolute path, or relative to the working directory. Created if missing. Mount this as the durable volume.")
	String root() default "data/weather";

	@AttributeDefinition(name = "Compress", description = "Write gzip-compressed XMI (.xmi.gz, ~50x smaller for reports). Plain .xmi files are always read; on the next save they are replaced.")
	boolean compress() default true;

}
