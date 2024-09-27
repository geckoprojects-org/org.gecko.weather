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
package org.gecko.weather.dwd.fc.cache;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.gecko.weather.dwd.fc.WeatherReportStorageHandler;
import org.gecko.weather.dwd.fc.helper.ReportHelper;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * Caches the weather report instances
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
@Component(name = "SimpleStorage", scope = ServiceScope.SINGLETON, configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class SimpleWeatherReportStorage implements WeatherReportStorageHandler {

	private final Map<String, WeatherReport> cache = new ConcurrentHashMap<>();

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportStorageHandler#saveReport(org.gecko.weather.model.weather.WeatherReport)
	 */
	@Override
	public <R extends WeatherReport> R saveReport(R report) {
		requireNonNull(report);
		if (isNull(report.getId())) {
			String id = ReportHelper.createReportId(report);
			report.setId(id);
		}
		cache.put(report.getId(), report);
		return report;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportStorageHandler#deleteReport(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R extends WeatherReport> Optional<R> deleteReport(String reportId) {
		requireNonNull(reportId);
		R report = (R) cache.remove(reportId);
		return Optional.ofNullable(report);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportStorageHandler#getReport(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R extends WeatherReport> Optional<R> getReport(String reportId) {
		requireNonNull(reportId);
		R report = (R) cache.get(reportId);
		return Optional.ofNullable(report);
	}

}
