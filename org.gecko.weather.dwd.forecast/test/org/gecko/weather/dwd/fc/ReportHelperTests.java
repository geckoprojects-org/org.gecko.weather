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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.gecko.weather.dwd.fc.helper.ReportHelper;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherReport;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author mark
 * @since 15.09.2024
 */
public class ReportHelperTests {
	
	@Test
	public void testCreateReportId() {
		assertThrows(NullPointerException.class, ()->ReportHelper.createReportId(null));
		WeatherReport report = WeatherFactory.eINSTANCE.createWeatherReport();
		assertThrows(NullPointerException.class, ()->ReportHelper.createReportId(report));
		report.setTimestamp(new Date(0));
		assertThrows(NullPointerException.class, ()->ReportHelper.createReportId(report));
		Station station = WeatherFactory.eINSTANCE.createStation();
		report.setStation(station);
		assertThrows(NullPointerException.class, ()->ReportHelper.createReportId(report));
		station.setId("ABC123");
		assertEquals("ABC123-1970010101", ReportHelper.createReportId(report));
		
		long refTs = 1726394721073l;
		station.setId("TEST");
		report.setTimestamp(new Date(refTs));
		assertEquals("TEST-2024091512", ReportHelper.createReportId(report));
	}

}
