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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.gecko.weather.api.util.DWDUtils;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author mark
 * @since 30.08.2024
 */
public class DWDNameUtilTests {
	
	@Test
	public void testFileNameInvalid() {
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.buildDMOForecastFile(24, -12, false));
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.buildDMOForecastFile(24, 30, false));
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.buildDMOForecastFile(-12, 10, false));
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.buildDMOForecastFile(32, 10, false));
	}
	
	@Test
	public void testBasicFileName() {
		assertEquals("ptp_gdmog_168_3_240000.kmz", DWDUtils.buildDMOForecastFile(24, 12, false));
		assertEquals("ptp_gdmog_078_1_240000.kmz", DWDUtils.buildDMOForecastFile(24, 12, true));
		assertEquals("ptp_gdmog_168_3_241200.kmz", DWDUtils.buildDMOForecastFile(25, 0, false));
		assertEquals("ptp_gdmog_078_1_241200.kmz", DWDUtils.buildDMOForecastFile(25, 0, true));
		assertEquals("ptp_gdmog_168_3_251200.kmz", DWDUtils.buildDMOForecastFile(25, 18, false));
		assertEquals("ptp_gdmog_078_1_251200.kmz", DWDUtils.buildDMOForecastFile(25, 18, true));
	}
	
	@Test
	public void testIsNight() {
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.isNight(-12));
		assertThrows(IllegalArgumentException.class, ()->DWDUtils.isNight(30));
		assertTrue(DWDUtils.isNight(4));
		assertTrue(DWDUtils.isNight(15));
		assertFalse(DWDUtils.isNight(0));
		assertFalse(DWDUtils.isNight(17));
		assertFalse(DWDUtils.isNight(23));
	}
	
	@Test
	public void testCreateFileNameByDate() {
		long refTime = 1725473471372l;//2024-09-04T20:00
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(refTime);
		assertThrows(NullPointerException.class, ()->DWDUtils.buildDMOForecastFile(null, true, now));
		assertThrows(NullPointerException.class, ()->DWDUtils.buildDMOForecastFile(now, true, null));
		Calendar fetch = Calendar.getInstance();
		fetch.setTimeInMillis(refTime);
		// return oldest value
		fetch.set(Calendar.HOUR_OF_DAY, 10);
		fetch.add(Calendar.DAY_OF_YEAR, -3);
		assertEquals("ptp_gdmog_168_3_030000.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_030000.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
		// return newest value
		fetch.setTimeInMillis(refTime);
		assertEquals("ptp_gdmog_168_3_041200.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_041200.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
		// return second newest value
		fetch.setTimeInMillis(refTime);
		fetch.add(Calendar.HOUR_OF_DAY, -10);
		assertEquals("ptp_gdmog_168_3_040000.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_040000.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
		// return third newest value
		fetch.setTimeInMillis(refTime);
		fetch.add(Calendar.DAY_OF_YEAR, -1);
		assertEquals("ptp_gdmog_168_3_031200.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_031200.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
		// return last value
		fetch.setTimeInMillis(refTime);
		fetch.add(Calendar.HOUR_OF_DAY, -10);
		fetch.add(Calendar.DAY_OF_YEAR, -1);
		assertEquals("ptp_gdmog_168_3_030000.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_030000.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
		// return first value for future fetch date
		fetch.setTimeInMillis(refTime);
		fetch.add(Calendar.DAY_OF_YEAR, 2);
		assertEquals("ptp_gdmog_168_3_041200.kmz", DWDUtils.buildDMOForecastFile(fetch, false, now));
		assertEquals("ptp_gdmog_078_1_041200.kmz", DWDUtils.buildDMOForecastFile(fetch, true, now));
	}

}
