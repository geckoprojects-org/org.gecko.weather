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
package org.gecko.weather.dwd.forecast.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

import org.gecko.weather.dwd.fc.WeatherReportSearch;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.model.weather.WeatherStation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithConfiguration;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import biz.aQute.scheduler.api.CronJob;

@RequireConfigurationAdmin
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
@ExtendWith(BundleContextExtension.class)
@WithFactoryConfiguration(factoryPid = "SimpleStorage~dwd-report", location = "?", name = "storage", properties = {})
@WithFactoryConfiguration(factoryPid = "EMFLuceneIndex", location = "?", name = "forecast-index", properties = {
		@Property(key = "id", value = "dwd.forecast"), @Property(key = "directory.type", value = "ByteBuffer") })
@WithFactoryConfiguration(factoryPid = "DWD-MOSMIX-Station", location = "?", name = "10554", properties = {
		@Property(key = "stationId", value = "10554"), @Property(key = "latitude", value = "0.0"),
		@Property(key = "longitude", value = "0.0") })
@WithConfiguration(pid = "org.gecko.weather.dwd.fc.util.DWDUtils", properties = {
		@Property(key = "dwdBaseUrl", value = "data/") })
public class DWDWeatherReportSearchTest {

	@BeforeAll
	public static void beforeAll(
			@InjectService(cardinality = 0, filter = "(component.name=DWD-MOSMIX-Station)") ServiceAware<CronJob> mosAware)
			throws Exception {
		CronJob mos = mosAware.waitForService(1000);
		assertThat(mos).isNotNull();

		mos.run();
	}

	@Test
	public void testSearchByStation(@InjectService(cardinality = 0) ServiceAware<WeatherReportSearch> siAware)
			throws InterruptedException {

		WeatherReportSearch weaterReportSearch = siAware.waitForService(1000);
		List<WeatherReport> result = weaterReportSearch.getReportsByStation("10554",
				WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
		assertThat(result) //
				.hasSize(5) //
				.extracting(WeatherReport::getWeatherStation) //
				.extracting(WeatherStation::getId) //
				.contains("10554");
	}

	@Test
	public void testSearchByStationFromNow(@InjectService(cardinality = 0) ServiceAware<WeatherReportSearch> siAware)
			throws InterruptedException {
		WeatherReportSearch weaterReportSearch = siAware.waitForService(1000);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, 8, 26, 8, 0);
		List<WeatherReport> result = weaterReportSearch.getReportsByStationFromNow("10554", calendar.getTime(),
				WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
		assertThat(result) //
				.hasSize(5) //
				.extracting(WeatherReport::getTimestamp) //
				.allMatch(d -> d.after(calendar.getTime()));
	}

	@Test
	public void testSearchByTimeRange(@InjectService(cardinality = 0) ServiceAware<WeatherReportSearch> siAware)
			throws InterruptedException {
		WeatherReportSearch weaterReportSearch = siAware.waitForService(1000);
		Calendar start = Calendar.getInstance();
		start.set(2024, 8, 26, 8, 0);
		Calendar end = Calendar.getInstance();
		end.set(2024, 9, 26, 8, 0);
		List<WeatherReport> result = weaterReportSearch.getReportsByTimeRange(start.getTime(), end.getTime(), "10554",
				WeatherPackage.eINSTANCE.getMOSMIXSWeatherReport());
		assertThat(result) //
				.hasSize(10) //
				.extracting(WeatherReport::getTimestamp) //
				.allMatch(d -> d.after(start.getTime()) && d.before(end.getTime()));
	}

}
