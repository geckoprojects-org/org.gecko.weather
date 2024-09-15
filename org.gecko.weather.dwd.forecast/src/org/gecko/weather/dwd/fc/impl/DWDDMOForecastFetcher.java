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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * Fetches the DMO (Direct-Model-Output) forecast data:
 * https://www.dwd.de/DE/leistungen/met_verfahren_ptp_dmo/met_verfahren_ptp_dmo.html
 * @author Mark Hoffmann
 * @since 30.08.2024
 */
@CronExpression(name = "DWD-DMO-Forecast", cron = {DWDDMOForecastFetcher.CRON_NIGHTLY, DWDDMOForecastFetcher.CRON_AFTERNOON, Constants.CRON_EXPRESSION_REBOOT})
//@Component(immediate = true)
public class DWDDMOForecastFetcher extends DWDEMFFetcher<KmlType> implements CronJob {
	
	// run at 5 in the morning, the data at dwd arrive after 4am
	static final String CRON_NIGHTLY = "5 0 5 * * ?";
	// run at 4 p.m. in the afternoon, the second data of day arrive after 3 p.m.
	static final String CRON_AFTERNOON= "5 0 16 * * ?";

	@Reference
	private KMLFactory kmlFactory;
	@Reference
	private KMLPackage kmlPackage;
	@Reference
	private PointforecastPackage forecastPackage;
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
		return "DMO";
	}

	/* 
	 * (non-Javadoc)
	 * @see com.dimc.pm.dwd.service.KMZFetcher#getFetchUrl()
	 */
	@Override
	protected String getFetchUrl() {
		return DWDUtils.buildDMOForecastUrl(Calendar.getInstance(), true);
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
		System.out.println("Decoding the DMO KML data");
		DocumentType documentType = (DocumentType) kml.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Document(), true);
		FeatureEList<PlacemarkType> placemarkTypeList = (FeatureEList<PlacemarkType>) documentType.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Placemark(), true);
		PlacemarkType placemarkType = (PlacemarkType) placemarkTypeList.get(0); 
		System.out.println("DMO Description: " + placemarkType.getDescription() + " (" + placemarkType.getName() + ")");
		PointType pointType = (PointType) placemarkType.getAbstractGeometryGroupGroup().get(kmlPackage.getDocumentRoot_Point(), true);
		if (Objects.nonNull(pointType)) {
			System.out.println("DMO Coords: " + pointType.getCoordinates().get(0));
		}
		ExtendedDataType extendedData = documentType.getExtendedData();
		List<ProductDefinitionType> productDefinitions = (List<ProductDefinitionType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_ProductDefinition(), true);
		if (Objects.nonNull(productDefinitions) && !productDefinitions.isEmpty()) {
			EList<XMLGregorianCalendar> forecastTimeSteps = productDefinitions.get(0).getForecastTimeSteps().getTimeStep();
			System.out.println("DMO Timesteps: " + forecastTimeSteps.size());
		}
		extendedData = placemarkType.getExtendedData();
		List<ForecastType> forecasts = (List<ForecastType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_Forecast(), true);
		if (Objects.nonNull(forecasts)) {
			forecasts.forEach(f->{
				System.out.println("DMO Forecast: " + f.getElementName() + " - " + f.getValue().getValue().size());
			});
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
		System.out.println("Updating DMO weather forecast - " + sdf.format(new Date()));
		long start = System.currentTimeMillis();
		InputStream download = doDownload();
		InputStream kmlData = doUnzip(download);
		KmlType kml = doLoad(kmlData);
		doDecode(kml);
		System.out.println(String.format("Updated DMO within %s ms", (System.currentTimeMillis() - start)));
//		pf.submit(this::doDownload).
//				map(this::doUnzip).
//				map(this::doLoadKML).
//				onSuccess(this::doDecode).
//				onFailure(Throwable::printStackTrace);
	}
}
