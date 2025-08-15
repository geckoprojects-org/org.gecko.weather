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
package org.gecko.weather.dwd.fc.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.gecko.weather.dwd.fc.helper.ReportIndexHelper.REPORT_ACTION;
import static org.gecko.weather.dwd.fc.helper.ReportIndexHelper.REPORT_ID;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.LongRange;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.search.document.EObjectDocumentIndexObjectContext;
import org.gecko.search.IndexActionType;
import org.gecko.search.document.LuceneIndexService;
import org.gecko.weather.dwd.fc.WeatherReportIndex;
import org.gecko.weather.dwd.fc.WeatherReportStorageHandler;
import org.gecko.weather.dwd.fc.helper.ReportHelper;
import org.gecko.weather.dwd.fc.helper.ReportIndexHelper;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.model.weather.WeatherReports;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.annotations.RequireEventAdmin;

/**
 * Index service for weather reports which only indexes reports for next hour and then every 3 hours up to 72 hours
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
@Component(name = "WeatherReportIndexWithFilter", configurationPid = "WeatherReportIndexWithFilter", configurationPolicy = ConfigurationPolicy.OPTIONAL)
@RequireEventAdmin
public class WeatherReportIndexServiceWithFilter implements WeatherReportIndex {

	@Reference
	private WeatherReportStorageHandler<WeatherReports> cache;
	@Reference(target = "(id=dwd.forecast)")
	private LuceneIndexService<EObjectDocumentIndexObjectContext> reportIndex;
	@Reference
	private WeatherPackage weatherPackage;
	@Reference
	private ResourceSet resourceSet;
	@Reference
	private EventAdmin eventAdmin;

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportIndex#indexReport(org.gecko.weather.model.weather.WeatherReport)
	 */
	@Override
	public void indexReport(WeatherReport report) {
		requireNonNull(report);
		requireNonNull(report.getTimestamp());
		requireNonNull(report.getWeatherStation());
		requireNonNull(report.getWeatherStation().getId());
		String id = report.getId();
		if (isNull(id)) {
			id = ReportHelper.createReportId(report);
			report.setId(id);
		}
		if(isValid(Instant.now(), report.getTimestamp().toInstant(), ZoneId.systemDefault())) {
			cache.getReport(id).
			ifPresentOrElse(
					r->doIndexReport(report, IndexActionType.MODIFY), 
					()->doIndexReport(report, IndexActionType.ADD));
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportIndex#deleteReport(org.gecko.weather.model.weather.WeatherReport)
	 */
	@Override
	public void deleteReport(WeatherReport report) {
		doIndexReport(report, IndexActionType.REMOVE);	
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportIndex#deleteReport(java.lang.String, java.util.Date)
	 */
	@Override
	public void deleteReport(String stationId, Date toDate) {
		requireNonNull(stationId, "Cannot search Reports with station id!");
		requireNonNull(toDate, "Cannot search Reports with no end date!");
		stationId = stationId.toLowerCase();
		Query stationQuery = new TermQuery(new Term(ReportIndexHelper.STATION_ID, stationId));
		Query rangeQuery = LongRange.newWithinQuery(ReportIndexHelper.REPORT_TIMESTAMP_SORT, new long[] {0}, new long[] {toDate.getTime()});
		Query query = new BooleanQuery.Builder().
				add(stationQuery, Occur.MUST).
				add(rangeQuery, Occur.MUST).build();
		IndexSearcher searcher = null;
		try {
			searcher = reportIndex.aquireSearcher();
			List<String> reportsToDelete = ReportHelper.executeReportIdSearch(searcher, query, Integer.MAX_VALUE);
			reportIndex.getIndexWriter().deleteDocuments(query);
			reportIndex.commit();
			reportsToDelete.forEach(cache::deleteReport);
		} catch (IOException e) {
			System.err.println("Could not delete reports from index " + e);
		} finally {
			if (nonNull(searcher)) {
				reportIndex.releaseSearcher(searcher);
			}
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportIndex#resetIndex()
	 */
	@Override
	public void resetIndex() {
		try {
			reportIndex.getIndexWriter().deleteAll();
			reportIndex.commit();
		} catch (IOException e) {
			System.err.println("Could not delete Weather Report index " + e);
		}
	}

	/**
	 * Executes indexing and caching
	 * @param report the report to index, must not be <code>null</code>
	 * @param actionType the action type, must not be <code>null</code>
	 */
	private void doIndexReport(WeatherReport report, IndexActionType actionType) {
		requireNonNull(report);
		requireNonNull(actionType);
		EObjectDocumentIndexObjectContext context = ReportIndexHelper.mapReport(report, actionType);			
		reportIndex.handleContextSync(context);
		notifyEventAdmin(report, actionType);
	}
	
	

	/**
	 * Notifies event admin for report changes
	 * @param report the report to notify about, must not be <code>null</code>
	 * @param actionType the action type, must not be <code>null</code>
	 */
	private void notifyEventAdmin(WeatherReport report, IndexActionType actionType) {
		requireNonNull(report);
		requireNonNull(actionType);
		if (isNull(report.getId())) {
			System.out.println("Cannot notify for action: " + actionType.name() + " with no report id");
			return;
		}
		Map<String, Object> properties = Map.of(REPORT_ID, report.getId(), 
				REPORT_ACTION, actionType.name(),
				ReportIndexHelper.REPORT_CHANGE_TIMESTAMP, Instant.now().toEpochMilli());
		Event event = new Event(ReportIndexHelper.REPORT_TOPIC, properties);
		eventAdmin.postEvent(event);
	}



	private boolean isValid(Instant current, Instant other, ZoneId zoneId) {
		ZonedDateTime currentTime = current.atZone(zoneId);
		ZonedDateTime otherTime = other.atZone(zoneId);

		if (otherTime.isBefore(currentTime)) return false;

		// Start of the next hour block
		ZonedDateTime nextHourStart = currentTime.truncatedTo(ChronoUnit.HOURS).plusHours(1);
		ZonedDateTime nextHourEnd = nextHourStart.plusHours(1);

		// Case 1: In the next hour block
		if (!otherTime.isBefore(nextHourStart) && otherTime.isBefore(nextHourEnd)) {
			return true;
		}

		// Case 2: After next hour start, at 3-hour intervals within 72 hours
		Duration duration = Duration.between(nextHourStart, otherTime);
		long minutes = duration.toMinutes();

		return minutes >= 0 && minutes <= 72 * 60 && (duration.toHours() % 3 == 0);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.fc.WeatherReportIndex#indexReports(org.gecko.weather.model.weather.WeatherReport[])
	 */
	@Override
	public void indexReports(WeatherReport[] reports) {
		WeatherReports weatherReports = WeatherFactory.eINSTANCE.createWeatherReports();
		for(WeatherReport report : reports) {
			requireNonNull(report);
			requireNonNull(report.getTimestamp());
			if(isValid(Instant.now(), report.getTimestamp().toInstant(), ZoneId.systemDefault())) {
				requireNonNull(report.getWeatherStation());
				requireNonNull(report.getWeatherStation().getId());
				String id = report.getId();
				if (isNull(id)) {
					id = ReportHelper.createReportId(report);
					report.setId(id);
				}
				if(weatherReports.getId() == null) weatherReports.setId(report.getWeatherStation().getId());
				weatherReports.getReports().add(report);			
			}
		}
		cache.saveReport(weatherReports);
	}
}
