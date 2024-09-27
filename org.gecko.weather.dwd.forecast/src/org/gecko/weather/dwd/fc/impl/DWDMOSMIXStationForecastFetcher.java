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

import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMapUtil.FeatureEList;
import org.gecko.weather.dwd.fc.MOSMIXStationConfig;
import org.gecko.weather.dwd.fc.WeatherReportIndex;
import org.gecko.weather.dwd.fc.fetcher.DWDEMFFetcher;
import org.gecko.weather.dwd.fc.util.DWDUtils;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import biz.aQute.scheduler.api.Constants;
import biz.aQute.scheduler.api.CronExpression;
import biz.aQute.scheduler.api.CronJob;
import de.dwd.cdc.forecast.pointforecast.ForecastType;
import de.dwd.cdc.forecast.pointforecast.PointforecastPackage;
import de.dwd.cdc.forecast.pointforecast.ProductDefinitionType;
import net.opengis.kml.DocumentType;
import net.opengis.kml.ExtendedDataType;
import net.opengis.kml.KMLFactory;
import net.opengis.kml.KMLPackage;
import net.opengis.kml.KmlType;
import net.opengis.kml.PlacemarkType;
import net.opengis.kml.PointType;

/**
 * Fetches the forecast MOSMIX (Model-Output-Statistics-MIX) data:
 * https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/met_verfahren_mosmix.html
 * Interpretation of the DMO data. The forecast is statistical smoothed.
 * Forecast up to 240h / 10 days
 * 
 * @author Mark Hoffmann
 * @since 30.08.2024
 */
@CronExpression(name = "DWD-MOSMIX-L-Station-Forecast", cron = { Constants.CRON_EXPRESSION_HOURLY,
		Constants.CRON_EXPRESSION_REBOOT })
@Designate(ocd = MOSMIXStationConfig.class)
@Component(name = "DWD-MOSMIX-Station", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class DWDMOSMIXStationForecastFetcher extends DWDEMFFetcher<KmlType> implements CronJob {

	private static final Logger LOGGER = System.getLogger(DWDMOSMIXStationForecastFetcher.class.getName());

	@Reference
	private KMLFactory kmlFactory;
	@Reference
	private KMLPackage kmlPackage;
	@Reference
	private PointforecastPackage forecastPackage;
	@Reference
	private WeatherFactory weatherFactory;
	@Reference
	private ResourceSet resourceSet;
	@Reference
	private WeatherReportIndex reportIndex;
	@Reference
	private DWDUtils dwdUtils;
	private MOSMIXStationConfig config;

	@Activate
	public void activate(MOSMIXStationConfig config) {
		this.config = config;
	}

	@Modified
	public void modified(MOSMIXStationConfig config) {
		this.config = config;
	}

	@Override
	protected String getModelFileExtension() {
		return "kml";
	}

	@Override
	protected String getName() {
		return "MOSMIX-SINGLE";
	}

	@Override
	protected String getFetchUrl() {
		return dwdUtils.buildMOSMIXSingleForecastUrl(config.stationId());
	}

	@Override
	protected ResourceSet getResourceSet() {
		return resourceSet;
	}

	@Override
	protected KmlType get(EObject content) {
		return DWDUtils.getKML(content);
	}

	@SuppressWarnings("unchecked")
	public void doDecode(KmlType kml) {
		MOSMIXSWeatherReport[] reports = null;

		LOGGER.log(Level.DEBUG, "Decoding the MOSMIX KML data");
		DocumentType documentType = (DocumentType) kml.getAbstractFeatureGroupGroup()
				.get(kmlPackage.getDocumentRoot_Document(), true);
		FeatureEList<PlacemarkType> placemarkTypeList = (FeatureEList<PlacemarkType>) documentType
				.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Placemark(), true);
		PlacemarkType placemarkType = placemarkTypeList.get(0);
		LOGGER.log(Level.DEBUG,
				"MOSMIX Description: " + placemarkType.getDescription() + " (" + placemarkType.getName() + ")");
		Station s = weatherFactory.createStation();
		s.setName(placemarkType.getDescription());
		s.setId(placemarkType.getName());
		PointType pointType = (PointType) placemarkType.getAbstractGeometryGroupGroup()
				.get(kmlPackage.getDocumentRoot_Point(), true);
		if (Objects.nonNull(pointType)) {
			GeoPosition location = weatherFactory.createGeoPosition();
			String[] loc = pointType.getCoordinates().get(0).split(",");
			location.setLatitude(Double.parseDouble(loc[0]));
			location.setLongitude(Double.parseDouble(loc[1]));
			location.setElevation((short) Double.parseDouble(loc[2]));
			s.setLocation(location);
			LOGGER.log(Level.DEBUG, "MOSMIX Coords: " + pointType.getCoordinates().get(0));
		}
		ExtendedDataType extendedData = documentType.getExtendedData();
		List<ProductDefinitionType> productDefinitions = (List<ProductDefinitionType>) extendedData.getAny()
				.get(forecastPackage.getDocumentRoot_ProductDefinition(), true);
		if (Objects.nonNull(productDefinitions) && !productDefinitions.isEmpty()) {
			EList<XMLGregorianCalendar> forecastTimeSteps = productDefinitions.get(0).getForecastTimeSteps()
					.getTimeStep();
			LOGGER.log(Level.DEBUG, "MOSMIX Timesteps: " + forecastTimeSteps.size());
			reports = new MOSMIXSWeatherReport[forecastTimeSteps.size()];
			for (int i = 0; i < forecastTimeSteps.size(); i++) {
				MOSMIXSWeatherReport report = weatherFactory.createMOSMIXSWeatherReport();
				report.setStation(s);
				XMLGregorianCalendar xmlC = forecastTimeSteps.get(i);
				GregorianCalendar c = xmlC.toGregorianCalendar();
				report.setTimestamp(c.getTime());
				reports[i] = report;
			}
		}
		extendedData = placemarkType.getExtendedData();
		List<ForecastType> forecasts = (List<ForecastType>) extendedData.getAny()
				.get(forecastPackage.getDocumentRoot_Forecast(), true);
		if (Objects.nonNull(forecasts)) {
			int cnt = 0;
			for (ForecastType fc : forecasts) {
//			forecasts.forEach(f->{
				for (int i = 0; i < fc.getValue().getValue().size(); i++) {
					MOSMIXSWeatherReport report = reports[i];
					Object value = fc.getValue().getValue().get(i);

					try {
						DWDUtils.setMOSMIXMeasurement(report, fc.getElementName(), value);
						cnt++;
					} catch (Exception e) {
						LOGGER.log(Level.ERROR, "[{0}] Error in Element: {1} Index: {2}, Value: {3} [{4}]", cnt,
								fc.getElementName(), i, value, value.getClass().getName());
					}
				}

			}
//			});
		}
		LOGGER.log(Level.DEBUG, "Indexing MOSMIX Forecast: ...");
		for (int i = 0; i < reports.length; i++) {
			MOSMIXSWeatherReport r = reports[i];
			reportIndex.indexReport(r);
		}
		LOGGER.log(Level.DEBUG, "Indexed MOSMIX Forecast: " + reports.length);
	}

	@Override
	public void run() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		LOGGER.log(Level.DEBUG, "[{0}] Updating MOSMIX weather forecast - {1}", config.stationId(),
				sdf.format(new Date()));
		long start = System.currentTimeMillis();
		InputStream download = doDownload();
		InputStream kmlData = doUnzip(download);
		KmlType kml = doLoad(kmlData);
		doDecode(kml);
		LOGGER.log(Level.DEBUG, "[{0}] Updated MOSMIX within {1} ms", config.stationId(),
				System.currentTimeMillis() - start);
	}
}
