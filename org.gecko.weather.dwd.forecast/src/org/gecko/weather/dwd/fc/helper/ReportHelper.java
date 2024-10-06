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
package org.gecko.weather.dwd.fc.helper;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * Helper class for {@link WeatherReport}
 * @author Mark Hoffmann
 * @since 15.09.2024
 */
public class ReportHelper {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	
	public static String createReportId(WeatherReport report) {
		requireNonNull(report);
		requireNonNull(report.getWeatherStation());
		requireNonNull(report.getWeatherStation().getId());
		requireNonNull(report.getTimestamp());
		String stationId = report.getWeatherStation().getId();
		return String.format("%s-%s", stationId, sdf.format(report.getTimestamp()));
	}
	
	public static List<String> executeReportIdSearch(IndexSearcher searcher, Query query, int maxResults) {
		requireNonNull(searcher);
		requireNonNull(query);
		if (maxResults < 1) {
			maxResults = 5;
		}
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
						Document d;
						try {
							d = indexReader.storedFields().document(id);
							return d;
						} catch (IOException e) {
							return null;
						}
					}).
					filter(Objects::nonNull).
					map(ReportIndexHelper::mapDocumentToReportsId).
					filter(Objects::nonNull).
					collect(Collectors.toList());
		} catch (Exception e) {
			System.err.println("Exception while search for Station with query " + query);
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
