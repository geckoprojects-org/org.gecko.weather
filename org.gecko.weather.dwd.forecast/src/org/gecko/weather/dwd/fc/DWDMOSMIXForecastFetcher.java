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
package org.gecko.weather.dwd.fc;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMapUtil.FeatureEList;
import org.gecko.weather.dwd.fc.fetcher.DWDEMFFetcher;
import org.gecko.weather.dwd.fc.util.DWDUtils;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Reference;

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
 * Interpretation of the DMO data. The forecast is statistical smoothed. Forecast up to 240h / 10 days
 * @author Mark Hoffmann
 * @since 30.08.2024
 */
@CronExpression(name = "DWD-MOSMIX-Forecast", cron = {Constants.CRON_EXPRESSION_HOURLY, Constants.CRON_EXPRESSION_REBOOT})
//@Component(immediate = true)
public class DWDMOSMIXForecastFetcher extends DWDEMFFetcher<KmlType> implements CronJob {
	
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
	
	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.KMZFetcher#getModelFileExtension()
	 */
	@Override
	protected String getModelFileExtension() {
		return "kml";
	}
	
	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.KMZFetcher#getName()
	 */
	@Override
	protected String getName() {
		return "MOSMIX";
	}
	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.KMZFetcher#getFetchUrl()
	 */
	@Override
	protected String getFetchUrl() {
		return "file:///home/mark/tmp/MOSMIX_S_LATEST_240.kmz";
//		return DWDUtils.buildMOSMIXForecastUrl();
	}

	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.KMZFetcher#getResourceSet()
	 */
	@Override
	protected ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.DWDFetcher#get(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected KmlType get(EObject content) {
		return DWDUtils.getKML(content);
	}

	@SuppressWarnings("unchecked")
	public void doDecode(KmlType kml) {
		MOSMIXSWeatherReport[] reports = null;
		
		System.out.println("Decoding the MOSMIX KML data");
		DocumentType documentType = (DocumentType) kml.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Document(), true);
		FeatureEList<PlacemarkType> placemarkTypeList = (FeatureEList<PlacemarkType>) documentType.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Placemark(), true);
		PlacemarkType placemarkType = (PlacemarkType) placemarkTypeList.get(0); 
		System.out.println("MOSMIX Description: " + placemarkType.getDescription() + " (" + placemarkType.getName() + ")");
		Station s = weatherFactory.createStation();
		s.setName(placemarkType.getDescription());
		s.setId(placemarkType.getName());
		PointType pointType = (PointType) placemarkType.getAbstractGeometryGroupGroup().get(kmlPackage.getDocumentRoot_Point(), true);
		if (Objects.nonNull(pointType)) {
			GeoPosition location = weatherFactory.createGeoPosition();
			String[] loc = pointType.getCoordinates().get(0).split(",");
			location.setLatitude(Double.parseDouble(loc[0]));
			location.setLongitude(Double.parseDouble(loc[1]));
			location.setElevation((short)Double.parseDouble(loc[2]));
			s.setLocation(location);
			System.out.println("MOSMIX Coords: " + pointType.getCoordinates().get(0));
		}
		ExtendedDataType extendedData = documentType.getExtendedData();
		List<ProductDefinitionType> productDefinitions = (List<ProductDefinitionType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_ProductDefinition(), true);
		if (Objects.nonNull(productDefinitions) && !productDefinitions.isEmpty()) {
			EList<XMLGregorianCalendar> forecastTimeSteps = productDefinitions.get(0).getForecastTimeSteps().getTimeStep();
			System.out.println("MOSMIX Timesteps: " + forecastTimeSteps.size());
			reports = new MOSMIXSWeatherReport[forecastTimeSteps.size()];
			for (int i = 0; i < forecastTimeSteps.size(); i++) {
				MOSMIXSWeatherReport report = weatherFactory.createMOSMIXSWeatherReport();
				report.setStation(s);
				report.setTimestamp(forecastTimeSteps.get(i).toGregorianCalendar().getTime());
				reports[i] = report;
			}
		}
		extendedData = placemarkType.getExtendedData();
		List<ForecastType> forecasts = (List<ForecastType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_Forecast(), true);
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
						Object o = fc.getValue().getValue().get(i);
						System.err.println("[" + cnt + "] Error in Element: " + fc.getElementName() + ", Index: " + i + ", Value: '" + o + "' [" + o.getClass().getName() + "]");
					}
				}
				
			}
//			});
		}
		System.out.println("MOSMIX Forecast: " + reports.length);
		for (int i = 0; i < reports.length; i++) {
			MOSMIXSWeatherReport r = reports[i];
			System.out.println("R " + r.getStation().getId() + " - " + r.getTimestamp().toString() + " wind " + r.getWindDirection() + " grad " + r.getWindSpeed() + " m/s");
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see biz.aQute.scheduler.api.CronJob#run()
	 */
	@Override
	public void run() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		System.out.println("------------------------------------------------");
		System.out.println("Updating MOSMIX weather forecast - " + sdf.format(new Date()));
		long start = System.currentTimeMillis();
//		InputStream download = doDownload();
//		InputStream kmlData = doUnzip(download);
		FileInputStream fis = new FileInputStream("/home/mark/tmp/MOSMIX_S_LATEST_240.kml");
//		FileInputStream fis = new FileInputStream("/home/mark/tmp/MOSMIX_L_2024090909_01001.kml");
		KmlType kml = doLoad(fis);
//		KmlType kml = doLoad(kmlData);
		doDecode(kml);
		System.out.println(String.format("Updated MOSMIX within %s ms", (System.currentTimeMillis() - start)));
//		pf.submit(this::doDownload).
//				map(this::doUnzip).
//				map(this::doLoadKML).
//				onSuccess(this::doDecode).
//				onFailure(Throwable::printStackTrace);
	}
}
