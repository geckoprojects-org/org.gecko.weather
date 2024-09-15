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
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * Weather report search service
 * @author Mark Hoffmann
 * @since 14.09.2024
 */
public interface WeatherReportSearch {
	
	/**
	 * Returns the report with the given if or an empty {@link Optional}
	 * @param <R> the report type
	 * @param reportId the report id, must not be <code>null</code>
	 * @return the {@link Optional}
	 */
	<R extends WeatherReport> Optional<R> getReportById(String reportId);
	
	/**
	 * Returns all reports for a given station
	 * @param <R> the report type
	 * @param stationId the station id, must not be <code>null</code>
	 * @return the list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByStation(String stationId);
	
	/**
	 * Returns all future reports for a given station, from now on
	 * @param <R> the report type
	 * @param stationId the station id, must not be <code>null</code>
	 * @return the list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByStationFromNow(String stationId);
	
	/**
	 * Returns all future reports for a given station, from the given date on
	 * @param <R> the report type
	 * @param stationId the station id, must not be <code>null</code>
	 * @param startDate the start date, if <code>null</code>, the current date is taken
	 * @return the list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByStationFromNow(String stationId, Date startDate);
	
	/**
	 * Returns 2 reports around the given timestamp, the next before and the next after this timestamp
	 * @param <R> the report type
	 * @param timestamp the timestamp to search around
	 * @param stationId the station id
	 * @param type the weather {@link EClass}
	 * @return a list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByTime(Date timestamp, String stationId, EClass type);
	
	/**
	 * Returns reports within the given timestamp range. The max resulting list is limited to 10
	 * @param <R> the report type
	 * @param fromTimestamp the timestamp to start the range
	 * @param toTimestamp the timestamp to end the range
	 * @param stationId the station id
	 * @param type the weather {@link EClass}
	 * @return a list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByTimeRange(Date fromTimestamp, Date toTimestamp, String stationId, EClass type);
	
	/**
	 * Returns reports within the given timestamp range
	 * @param <R> the report type
	 * @param fromTimestamp the timestamp to start the range
	 * @param toTimestamp the timestamp to end the range
	 * @param stationId the station id
	 * @param type the weather {@link EClass}
	 * @param maxResults number of max results
	 * @return a list of reports or an empty list
	 */
	<R extends WeatherReport> List<R> getReportsByTimeRange(Date fromTimestamp, Date toTimestamp, String stationId, EClass type, int maxResults);

}
