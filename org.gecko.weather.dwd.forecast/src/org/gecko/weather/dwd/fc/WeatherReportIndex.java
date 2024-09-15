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

import java.util.Date;

import org.gecko.weather.model.weather.WeatherReport;

/**
 * Index service to store an 
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
public interface WeatherReportIndex {

	/**
	 * Indexes a weather report 
	 * @param report the the weather report to be added
	 */
	void indexReport(WeatherReport report);

	/**
	 * Deletes a weather report from the index
	 * @param report the weather report to delete
	 */
	void deleteReport(WeatherReport report);
	
	/**
	 * Deletes a weather report from the index until the given date
	 * @param stationId the stationId for the reports to be deleteds
	 * @param toDate the date until all reports will be deleted, if <code>null</code>, the current date is taken
	 */
	void deleteReport(String stationId, Date toDate);

	/**
	 * Resets the whole index
	 */
	void resetIndex();

}
