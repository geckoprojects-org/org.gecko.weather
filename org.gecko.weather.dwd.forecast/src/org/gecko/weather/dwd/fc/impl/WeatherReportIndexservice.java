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
import java.time.Instant;
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
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.annotations.RequireEventAdmin;

/**
 * Index service for weather reports
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
@Component
@RequireEventAdmin
public class WeatherReportIndexservice implements WeatherReportIndex {

	@Reference
	private WeatherReportStorageHandler cache;
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
		requireNonNull(report.getStation());
		requireNonNull(report.getStation().getId());
		String id = report.getId();
		if (isNull(id)) {
			id = ReportHelper.createReportId(report);
			report.setId(id);
		}
		cache.getReport(id).
		ifPresentOrElse(
				r->doIndexReport(report, IndexActionType.MODIFY), 
				()->doIndexReport(report, IndexActionType.ADD));
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
		switch (actionType) {
		case ADD: 
		case MODIFY:
			cache.saveReport(report);
			break;
		case REMOVE:
			if (nonNull(report.getId())) {
				cache.deleteReport(report.getId());
			}
		default:
			break;
		}
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

}
