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

import java.util.Optional;

import org.gecko.weather.model.weather.WeatherReport;

/**
 * Simple handler to cache or store {@link WeatherReport}s
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
public interface WeatherReportStorageHandler {
	
	/**
	 * Saves or updated a {@link WeatherReport}
	 * @param report the report to save, must not be <code>null</code>
	 * @return the saved report that should contain an id, if not provided in the argument instance
	 */
	<R extends WeatherReport> R saveReport(R report);
	
	/**
	 * Deletes a {@link WeatherReport} as {@link Optional}
	 * @param reportId the report id, must not be <code>null</code>
	 * @return the {@link Optional} containing the deleted instance or an empty {@link Optional}
	 */
	<R extends WeatherReport> Optional<R> deleteReport(String reportId);
	
	/**
	 * Returns a {@link WeatherReport} as {@link Optional}
	 * @param reportId the report id, must not be <code>null</code>
	 * @return the {@link Optional} containing the result
	 */
	<R extends WeatherReport> Optional<R> getReport(String reportId);

}
