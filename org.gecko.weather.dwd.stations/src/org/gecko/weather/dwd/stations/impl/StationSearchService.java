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
package org.gecko.weather.dwd.stations.impl;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.lucene.document.LatLonPoint;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.dwd.stations.helper.StationIndexHelper;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherPackage;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * This is a sample Search Service to retrieve the objects from the index
 */
@Component
public class StationSearchService implements StationSearch {

	private static final Logger LOGGER = System.getLogger(StationSearchService.class.getName());

	@Reference(target = "(id=dwd.station)")
	private ComponentServiceObjects<IndexSearcher> searcherSO;

	@Reference
	private WeatherPackage weatcherPackage;
	@Reference
	private ResourceSet resourceSet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gecko.weather.dwd.stations.StationSearch#searchStationByName(java.lang.
	 * String, boolean)
	 */
	@Override
	public List<Station> searchStationByName(String stationName, boolean exactMatch) {
		return searchStationByName(stationName, exactMatch, 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gecko.weather.dwd.stations.StationSearch#searchStationByName(java.lang.
	 * String, int)
	 */
	@Override
	public List<Station> searchStationByName(String stationName, int maxResults) {
		return searchStationByName(stationName, false, maxResults);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gecko.weather.dwd.stations.StationSearch#searchStationsById(java.lang.
	 * String)
	 */
	@Override
	public List<Station> searchStationsById(String id) {
		requireNonNull(id, "Cannot search Station by null id!");
		return executeTermSearch(new TermQuery(new Term(StationIndexHelper.STATION_ID, id)), 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gecko.weather.dwd.stations.StationSearch#searchStationNearLocation(org.
	 * gecko.weather.modelweather.GeoPosition, int)
	 */
	@Override
	public List<Station> searchStationNearLocation(GeoPosition location, int radius) {
		requireNonNull(location);
		return executeTermSearch(LatLonPoint.newDistanceQuery(StationIndexHelper.STATION_POSITION,
				location.getLatitude(), location.getLongitude(), radius), 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gecko.weather.dwd.stations.StationSearch#searchStationNearLocation(org.
	 * gecko.weather.modelweather.GeoPosition, int, int)
	 */
	@Override
	public List<Station> searchStationNearLocation(GeoPosition location, int radius, int maxresults) {
		requireNonNull(location);
		return executeTermSearch(LatLonPoint.newDistanceQuery(StationIndexHelper.STATION_POSITION,
				location.getLatitude(), location.getLongitude(), radius), maxresults);
	}

	/**
	 * Executes the Lucene search
	 * 
	 * @param query the {@link Query}
	 * @return the list of documents
	 */
	@SuppressWarnings("unused")
	private List<Station> executeTermSearch(Query query) {
		return executeTermSearch(query, Integer.MAX_VALUE);
	}

	/**
	 * Search a station by name
	 * 
	 * @param name       the station name to search for
	 * @param exactMatch <code>true</code> for an exact match
	 * @param maxResults number of results, must be larger that 0
	 * @return the list of matching stations
	 */
	private List<Station> searchStationByName(String name, boolean exactMatch, int maxResults) {
		requireNonNull(name, "Cannot search Person by null firstName!");
		if (maxResults < 1) {
			maxResults = 5;
		}
		Query query;
		if (exactMatch) {
			query = new TermQuery(new Term(StationIndexHelper.STATION_NAME, name));

		} else {
			name = name.toLowerCase();
			Query q1 = new WildcardQuery(new Term(StationIndexHelper.STATION_NAME_LC, name + "*"));
			Query q2 = new WildcardQuery(new Term(StationIndexHelper.STATION_NAME_LC, "*" + name));
			Query q3 = new WildcardQuery(new Term(StationIndexHelper.STATION_NAME_LC, "*" + name + "*"));
			Query q4 = new FuzzyQuery(new Term(StationIndexHelper.STATION_NAME_LC, name));
			query = new BooleanQuery.Builder().add(new BoostQuery(q1, 1.8f), Occur.SHOULD)
					.add(new BoostQuery(q2, 1.8f), Occur.SHOULD).add(new BoostQuery(q3, 1.2f), Occur.SHOULD)
					.add(new BoostQuery(q4, 0.5f), Occur.SHOULD).build();
		}
		return executeTermSearch(query, maxResults);
	}

	/**
	 * Executes the Lucene search
	 * 
	 * @param query      the {@link Query}
	 * @param maxResults the number of maximum results
	 * @return the list of documents
	 */
	private List<Station> executeTermSearch(Query query, int maxResults) {

		IndexSearcher searcher = searcherSO.getService();
		try {
			TopDocs topDocs = searcher.search(query, maxResults);
			if (topDocs.scoreDocs.length == 0) {
				return Collections.emptyList();
			}
			IndexReader indexReader = searcher.getIndexReader();
			return Arrays.asList(topDocs.scoreDocs).stream()//
					.map(sd -> sd.doc)//
					.map(id -> {
						try {
							return indexReader.storedFields().document(id);
						} catch (IOException e) {
							return null;
						}
					}).filter(Objects::nonNull).map(StationIndexHelper::mapDocument).toList();
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, "Exception while search for Station with query {0}", query, e);
			return Collections.emptyList();
		} finally {
			searcherSO.ungetService(searcher);
		}
	}

}