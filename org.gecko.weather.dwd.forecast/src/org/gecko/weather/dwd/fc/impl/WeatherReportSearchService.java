/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
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
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.lucene.document.LongField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.weather.dwd.fc.WeatherReportSearch;
import org.gecko.weather.dwd.fc.WeatherReportStorageHandler;
import org.gecko.weather.dwd.fc.helper.ReportIndexHelper;
import org.gecko.weather.dwd.fc.util.DWDUtils;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * This is a sample Search Service to retrieve the objects from the index
 */
@Component(service = WeatherReportSearch.class)
public class WeatherReportSearchService implements WeatherReportSearch {

	private static final Logger LOGGER = System.getLogger(WeatherReportSearchService.class.getName());

	@Reference(target = "(id=dwd.forecast)")
	private ComponentServiceObjects<IndexSearcher> searcherSO;
	@Reference
	private WeatherReportStorageHandler cache;
	@Reference
	private WeatherPackage weatherPackage;
	@Reference
	private ResourceSet resourceSet;
	@Reference
	private WeatherFactory factory;

	@Override
	public <R extends WeatherReport> Optional<R> getReportById(String reportId) {
		return cache.getReport(reportId);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByStation(String stationId, EClass type) {
		requireNonNull(stationId, "Cannot search Reports without station id!");
		if (isNull(type)) {
			type = weatherPackage.getWeatherReport();
		}
		String typeUri = EcoreUtil.getURI(type).toString();
		stationId = stationId.toLowerCase();
		Query typeQuery = new TermQuery(new Term(ReportIndexHelper.REPORT_TYPE, typeUri));
		Query stationQuery = new TermQuery(new Term(ReportIndexHelper.STATION_ID, stationId));
		Query query = new BooleanQuery.Builder().
				add(typeQuery, Occur.MUST).
				add(stationQuery, Occur.MUST).build();
		return executeTermSearch(query, 5);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByStationFromNow(String stationId, EClass type) {
		return getReportsByStationFromNow(stationId, new Date(), type);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByStationFromNow(String stationId, Date startDate, EClass type) {
		requireNonNull(stationId, "Cannot search Reports without station id!");
		if (isNull(type)) {
			type = weatherPackage.getWeatherReport();
		}
		String typeUri = EcoreUtil.getURI(type).toString();
		stationId = stationId.toLowerCase();
		Query typeQuery = new TermQuery(new Term(ReportIndexHelper.REPORT_TYPE, typeUri));
		Query stationQuery = new TermQuery(new Term(ReportIndexHelper.STATION_ID, stationId));
		Query rangeQuery = LongField.newRangeQuery(ReportIndexHelper.REPORT_TIMESTAMP, startDate.getTime(), Long.MAX_VALUE);
		Query query = new BooleanQuery.Builder().
				add(typeQuery, Occur.MUST).
				add(rangeQuery, Occur.MUST).
				add(stationQuery, Occur.MUST).
				build();
		return executeTermSearch(query, 5);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByTime(Date timestamp, String stationId, EClass type) {
		return searchReportsForTime(stationId, timestamp, type);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByTimeRange(Date fromTimestamp, Date toTimestamp,
			String stationId, EClass type) {
		return getReportsByTimeRange(fromTimestamp, toTimestamp, stationId, type, 10);
	}

	@Override
	public <R extends WeatherReport> List<R> getReportsByTimeRange(Date fromTimestamp, Date toTimestamp,
			String stationId, EClass type, int maxResults) {
		return searchReportsForTimeRange(stationId, fromTimestamp, toTimestamp, type, maxResults);
	}
	
	/**
	 * Search a report by station id and time range
	 * @param stationId the station id to search for
	 * @param time the time range
	 * @param type the {@link EClass} type
	 * @param maxResults number of results, must be larger that 0
	 * @return the list of matching reports
	 */
	private <R extends WeatherReport> List<R> searchReportsForTime(String stationId, Date time, EClass type) {
		requireNonNull(stationId, "Cannot search Reports with station id!");
		requireNonNull(time, "Cannot search Reports with no  date!");
		Calendar sc = Calendar.getInstance();
		sc.setTime(time);
		DWDUtils.normalizeToFullHours(sc);
		sc.add(Calendar.MINUTE, -1);
		sc.add(Calendar.HOUR_OF_DAY, -1);
		Calendar ec = Calendar.getInstance();
		ec.setTime(time);
		ec.add(Calendar.MINUTE, 1);
		ec.add(Calendar.HOUR_OF_DAY, 1);
		DWDUtils.normalizeToFullHours(sc);
		Date startTime = sc.getTime();
		Date endTime = ec.getTime();
		return searchReportsForTimeRange(stationId, startTime, endTime, type, 5);
	}

	/**
	 * Search a report by station id and time range
	 * @param stationId the station id to search for
	 * @param startTime the start range
	 * @param endTime the end range
	 * @param type the {@link EClass} type
	 * @param maxResults number of results, must be larger that 0
	 * @return the list of matching reports
	 */
	private <R extends WeatherReport> List<R> searchReportsForTimeRange(String stationId, Date startTime, Date endTime, EClass type, int maxResults) {
		requireNonNull(stationId, "Cannot search Reports with station id!");
		requireNonNull(startTime, "Cannot search Reports with no start date!");
		requireNonNull(endTime, "Cannot search Reports with no end date!");
		if (maxResults < 1) {
			maxResults = 5;
		}
		if (isNull(type)) {
			type = weatherPackage.getWeatherReport();
		}
		String typeUri = EcoreUtil.getURI(type).toString();
		stationId = stationId.toLowerCase();
		Query typeQuery = new TermQuery(new Term(ReportIndexHelper.REPORT_TYPE, typeUri));
		Query stationQuery = new TermQuery(new Term(ReportIndexHelper.STATION_ID, stationId));
		Query rangeQuery = LongField.newRangeQuery(ReportIndexHelper.REPORT_TIMESTAMP, startTime.getTime(), endTime.getTime());
		Query query = new BooleanQuery.Builder().
				add(typeQuery, Occur.MUST).
				add(stationQuery, Occur.MUST).
				add(rangeQuery, Occur.MUST).build();
		return executeTermSearch(query, maxResults);
	}

	/**
	 * Executes the Lucene search
	 * @param query the {@link Query}
	 * @param maxResults the number of maximum results
	 * @return the list of documents
	 */
	@SuppressWarnings("unchecked")
	private <R extends WeatherReport> List<R> executeTermSearch(Query query, int maxResults) {

		IndexSearcher searcher = searcherSO.getService();
		try {
			TopDocs topDocs = searcher.search(query, maxResults);
			if (topDocs.scoreDocs.length == 0) {
				return Collections.emptyList();
			}
			IndexReader indexReader = searcher.getIndexReader();
			return Arrays.asList(topDocs.scoreDocs).
					stream().
					map(sd -> sd.doc).
					map(id -> {
						try {
							return indexReader.storedFields().document(id);
						} catch (IOException e) {
							return null;
						}
					}).
					filter(Objects::nonNull).
					map(d->(R)ReportIndexHelper.mapDocument(d, resourceSet)).
					toList();
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, "Exception while search for Station with query {0}", query, e);
			return Collections.emptyList();
		} finally {
			searcherSO.ungetService(searcher);
		}
	}

}