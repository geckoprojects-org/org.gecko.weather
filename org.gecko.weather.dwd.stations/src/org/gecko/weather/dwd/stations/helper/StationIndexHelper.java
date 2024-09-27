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
package org.gecko.weather.dwd.stations.helper;

import static java.util.Objects.nonNull;

import java.util.Collections;
import java.util.Optional;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LatLonPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.gecko.emf.search.document.EObjectContextObjectBuilder;
import org.gecko.emf.search.document.EObjectDocumentIndexObjectContext;
import org.gecko.search.IndexActionType;
import org.gecko.search.document.context.ObjectContextObject;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;

/**
 * This is an Index Helper class where the actual document to be indexed is created.
 */
public class StationIndexHelper {
	
	public static final String STATION_ID = "station_id";
	public static final String STATION_NAME = "station_name";
	public static final String STATION_NAME_LC = "station_name_lc";
	public static final String STATION_ICAO = "station_icao";
	public static final String STATION_POSITION = "station_position";
	public static final String STATION_LAT = "station_lat";
	public static final String STATION_LON = "station_lon";

	private StationIndexHelper() {}
	
	public static EObjectDocumentIndexObjectContext mapStationNew(Station station) {		
		return mapStation(station, IndexActionType.ADD);
	}

	public static EObjectDocumentIndexObjectContext mapStationUpdate(Station station) {
		return mapStation(station, IndexActionType.MODIFY);
	}
	
	/**
	 * Maps a given {@link Station} to {@link ObjectContextObject} with a given {@link IndexActionType}
	 * @param station the person to index
	 * @param indexAction the index action (ADD, UPDATE, REMOVE)
	 * @return the {@link ObjectContextObject}
	 */
	public static EObjectDocumentIndexObjectContext mapStation(Station station, IndexActionType indexAction) {
		
		Document doc = new Document();
		
		doc.add(new StringField(STATION_ID, station.getId(), Store.YES));

		if(nonNull(station.getId())) {
			doc.add(new StringField(STATION_ID, station.getId(), Store.YES));
		}
		if(nonNull(station.getIcaoCode())) {
			doc.add(new StringField(STATION_ICAO, station.getIcaoCode(), Store.YES));
		}
		if(nonNull(station.getName())) {
			doc.add(new StringField(STATION_NAME, station.getName(), Store.YES));
			doc.add(new StringField(STATION_NAME_LC, station.getName().toLowerCase(), Store.NO));
		}
		if(nonNull(station.getLocation())) {
			GeoPosition location = station.getLocation();
			doc.add(new LatLonPoint(STATION_POSITION, location.getLatitude(), location.getLongitude()));
			doc.add(new DoubleField(STATION_LAT, location.getLatitude(), Store.YES));
			doc.add(new DoubleField(STATION_LON, location.getLongitude(), Store.YES));
		}
		EObjectContextObjectBuilder builder = (EObjectContextObjectBuilder) EObjectContextObjectBuilder.create().
				withDocuments(Collections.singletonList(doc)).
				withSourceObject(station).
				withIndexActionType(indexAction);

		if (IndexActionType.MODIFY.equals(indexAction) || IndexActionType.REMOVE.equals(indexAction)) {
			builder.withIdentifyingTerm(new Term(STATION_ID, station.getId()));
		}

		return builder.build();
	}
	
	public static Station mapDocument(Document document) {
		Station s = WeatherFactory.eINSTANCE.createStation();
		GeoPosition l = WeatherFactory.eINSTANCE.createGeoPosition();
		getStringValue(document, STATION_ID).ifPresent(s::setId);
		getStringValue(document, STATION_ICAO).ifPresent(s::setIcaoCode);
		getStringValue(document, STATION_NAME).ifPresent(s::setName);
		getValue(document, STATION_LAT).map(Double.class::cast).ifPresent(l::setLatitude);
		getValue(document, STATION_LON).map(Double.class::cast).ifPresent(l::setLongitude);
		s.setLocation(l);
		return s;
	}
	
	private static Optional<Object> getValue(Document doc, String fieldName) {
		IndexableField field = doc.getField(fieldName);
		if (field instanceof StoredField) {
			if (field.numericValue() != null) {
				Number value = field.numericValue();
				if (value instanceof Double) {
					return Optional.of((double)value);
				} else if (value instanceof Float) {
					return Optional.of((float)value);
				} else {
					return Optional.of((int)value);
				}
			} else {
				return Optional.of(field.stringValue());
			}
		}
		return Optional.empty();
	}
	
	private static Optional<String> getStringValue(Document doc, String fieldName) {
		return getValue(doc, fieldName).map(String.class::cast);
	}
}