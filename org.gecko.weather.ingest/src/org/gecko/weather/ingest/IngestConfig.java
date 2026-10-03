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
package org.gecko.weather.ingest;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration of the ingest runtime, PID {@value IngestRuntimeComponent#PID}. All values have
 * workable defaults; the runtime itself causes no traffic — only configured providers do.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@ObjectClassDefinition(name = "Gecko Weather Ingest", description = "Polling, backoff and state persistence for weather providers")
public @interface IngestConfig {

	@AttributeDefinition(name = "Poll interval", description = "ISO-8601 duration between polls of a product. Polls are conditional requests, so a short interval costs little.")
	String pollInterval() default "PT20M";

	@AttributeDefinition(name = "Initial delay", description = "ISO-8601 duration before the first poll after a provider appears.")
	String initialDelay() default "PT10S";

	@AttributeDefinition(name = "Backoff initial", description = "ISO-8601 duration to wait after the first transport failure; doubles per consecutive failure.")
	String backoffInitial() default "PT1M";

	@AttributeDefinition(name = "Backoff max", description = "ISO-8601 duration: the longest wait after transport failures.")
	String backoffMax() default "PT1H";

}
