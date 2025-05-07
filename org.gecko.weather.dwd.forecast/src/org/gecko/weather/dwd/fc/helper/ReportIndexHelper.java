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
package org.gecko.weather.dwd.fc.helper;

import static java.util.Objects.nonNull;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.emf.search.document.EObjectContextObjectBuilder;
import org.gecko.emf.search.document.EObjectDocumentIndexObjectContext;
import org.gecko.search.IndexActionType;
import org.gecko.search.document.context.ObjectContextObject;
import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.model.weather.WeatherStation;

/**
 * This is an Index Helper class where the actual document to be indexed is
 * created.
 */
public class ReportIndexHelper {

	public static final String REPORT_ID = "report_id";
	public static final String REPORT_ACTION = "report_action";
	public static final String REPORT_TOPIC = "weatherreport/changes";
	public static final String REPORT_CHANGE_TIMESTAMP = "report_change_timestamp";
	public static final String REPORT_TYPE = "report_type";
	public static final String REPORT_TIMESTAMP = "report_timestamp";
	public static final String REPORT_TIMESTAMP_SORT = "report_timestamp_sort";
	public static final String STATION_ID = "station_id";
	public static final String STATION_NAME = "station_name";
	public static final String STATION_NAME_LC = "station_name_lc";
	private static final String SUNSET = "SUNSET";
	private static final String SUNSET_TL = "SUNSET_TL";
	private static final String SUNRISE = "SUNRISE";
	private static final String SUNRISE_TL = "SUNRISE_TL";

	public static EObjectDocumentIndexObjectContext mapReportNew(WeatherReport report) {
		return mapReport(report, IndexActionType.ADD);
	}

	public static EObjectDocumentIndexObjectContext mapReportUpdate(WeatherReport report) {
		return mapReport(report, IndexActionType.MODIFY);
	}

	/**
	 * Maps a given {@link WeatherReport} to {@link ObjectContextObject} with a
	 * given {@link IndexActionType}
	 * 
	 * @param report      the weather report to index
	 * @param indexAction the index action (ADD, UPDATE, REMOVE)
	 * @return the {@link ObjectContextObject}
	 */
	public static EObjectDocumentIndexObjectContext mapReport(WeatherReport report, IndexActionType indexAction) {
		Document doc = new Document();

		doc.add(new StringField(REPORT_ID, report.getId(), Store.YES));
		doc.add(new StringField(REPORT_TYPE, EcoreUtil.getURI(report.eClass()).toString(), Store.YES));

		if (nonNull(report.getId())) {
			doc.add(new StringField(REPORT_ID, report.getId(), Store.YES));
		}
		if (nonNull(report.getTimestamp())) {
			long ts = report.getTimestamp().getTime();
			doc.add(new LongField(REPORT_TIMESTAMP, ts, Store.YES));
			doc.add(new SortedNumericDocValuesField(REPORT_TIMESTAMP_SORT, ts));
		}
		if (nonNull(report.getAstrotime())) {
			Date sunset = report.getAstrotime().getSunset();
			Date sunsetTwilight = report.getAstrotime().getSunriseTwilight();
			Date sunrise = report.getAstrotime().getSunrise();
			Date sunriseTwilight = report.getAstrotime().getSunriseTwilight();
			doc.add(new LongField(SUNSET, sunset.getTime(), Store.YES));
			doc.add(new LongField(SUNSET_TL, sunsetTwilight.getTime(), Store.YES));
			doc.add(new LongField(SUNRISE, sunrise.getTime(), Store.YES));
			doc.add(new LongField(SUNRISE_TL, sunriseTwilight.getTime(), Store.YES));
		}
		WeatherStation s = report.getWeatherStation();
		if (nonNull(s)) {
			if (nonNull(s.getId())) {
				doc.add(new StringField(STATION_ID, s.getId(), Store.YES));
			}
			if (nonNull(s.getName())) {
				doc.add(new StringField(STATION_NAME, s.getName(), Store.YES));
				doc.add(new StringField(STATION_NAME_LC, s.getName().toLowerCase(), Store.NO));
			}
		}
		EList<EAttribute> reportAttributes = WeatherPackage.Literals.WEATHER_REPORT.getEAttributes();
		if (report instanceof MOSMIXSWeatherReport) {
			reportAttributes = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT.getEAttributes();
		}
		reportAttributes.stream().filter(a -> a.getEAttributeType().getInstanceClass() == Float.class).forEach(a -> {
			Object o = report.eGet(a);
			if (nonNull(o)) {
				doc.add(new FloatField(a.getName(), (Float) o, Store.YES));
			}
		});
		EObjectContextObjectBuilder builder = (EObjectContextObjectBuilder) EObjectContextObjectBuilder.create()
				.withDocuments(Collections.singletonList(doc)).withSourceObject(report)
				.withIndexActionType(indexAction);

		if (IndexActionType.MODIFY.equals(indexAction) || IndexActionType.REMOVE.equals(indexAction)) {
			builder.withIdentifyingTerm(new Term(REPORT_ID, report.getId()));
		}

		return builder.build();
	}

	public static WeatherReport mapDocument(Document document, ResourceSet rs) {
		final WeatherReport r = createInstance(document, rs);
		getStringValue(document, REPORT_ID).ifPresent(r::setId);
		getValue(document, REPORT_TIMESTAMP).map(Long.class::cast).map(Date::new).ifPresent(r::setTimestamp);
		WeatherStation s = WeatherFactory.eINSTANCE.createWeatherStation();
		r.setStation(s);
		r.setWeatherStation(s);
		getStringValue(document, STATION_ID).ifPresent(s::setId);
		getStringValue(document, STATION_NAME).ifPresent(s::setName);
		EList<EAttribute> reportAttributes = r.eClass().getEAttributes();
		reportAttributes.forEach(a -> {
			getValue(document, a.getName()).map(Float.class::cast).ifPresent(fv -> r.eSet(a, fv));
		});
		Astrotime as = WeatherFactory.eINSTANCE.createAstrotime();
		r.setAstrotime(as);
		getValue(document, SUNSET).map(Long.class::cast).map(Date::new).ifPresent(as::setSunset);
		getValue(document, SUNSET_TL).map(Long.class::cast).map(Date::new).ifPresent(as::setSunsetTwilight);
		getValue(document, SUNRISE).map(Long.class::cast).map(Date::new).ifPresent(as::setSunrise);
		getValue(document, SUNRISE_TL).map(Long.class::cast).map(Date::new).ifPresent(as::setSunriseTwilight);
		return r;
	}

	public static String mapDocumentToReportsId(Document document) {
		return getStringValue(document, REPORT_ID).orElse(null);
	}

	private static WeatherReport createInstance(Document document, ResourceSet rs) {
		String eclassUri = getStringValue(document, REPORT_TYPE).orElse(null);
		EClass ec = (EClass) rs.getEObject(URI.createURI(eclassUri), true);
		return (WeatherReport) EcoreUtil.create(ec);
	}

	private static Optional<Object> getValue(Document doc, String fieldName) {
		IndexableField field = doc.getField(fieldName);
		if (field != null && field instanceof StoredField) {
			if (field.numericValue() != null) {
				Number value = field.numericValue();
				if (value instanceof Double) {
					return Optional.of((double) value);
				} else if (value instanceof Float) {
					return Optional.of((float) value);
				} else if (value instanceof Long) {
					return Optional.of((long) value);
				} else {
					return Optional.of((int) value);
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