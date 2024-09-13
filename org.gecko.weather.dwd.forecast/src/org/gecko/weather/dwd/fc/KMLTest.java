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

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMapUtil.FeatureEList;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

import de.dwd.cdc.forecast.pointforecast.ForecastType;
import de.dwd.cdc.forecast.pointforecast.PointforecastPackage;
import de.dwd.cdc.forecast.pointforecast.ProductDefinitionType;
import net.opengis.kml.DocumentRoot;
import net.opengis.kml.DocumentType;
import net.opengis.kml.ExtendedDataType;
import net.opengis.kml.KMLFactory;
import net.opengis.kml.KMLPackage;
import net.opengis.kml.KmlType;
import net.opengis.kml.PlacemarkType;
import net.opengis.kml.PointType;

/**
 * 
 * @author mark
 * @since 16.08.2024
 */
//@Component
public class KMLTest {

	@Reference
	private KMLFactory kmlFactory;
	@Reference
	private KMLPackage kmlPackage;
	@Reference
	private PointforecastPackage forecastPackage;
	@Reference
	private ResourceSet resourceSet;

	@SuppressWarnings("unchecked")
	@Activate
	public void activate() {
//		Resource resource = resourceSet.createResource(URI.createURI("data/20240816-allstations.kml"));
		long start = System.currentTimeMillis();
		Resource resource = resourceSet.createResource(URI.createURI("data/168_19.kml"));
		try {
			resource.load(null);
			DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
			System.out.println("Root: took " + ((System.currentTimeMillis() - start) / 1000) + " sec");
			KmlType kmlType = root.getKml();
			DocumentType documentType = (DocumentType) kmlType.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Document(), true);
			FeatureEList<PlacemarkType> placemarkTypeList = (FeatureEList<PlacemarkType>) documentType.getAbstractFeatureGroupGroup().get(kmlPackage.getDocumentRoot_Placemark(), true);
			PlacemarkType placemarkType = (PlacemarkType) placemarkTypeList.get(0); 
			System.out.println("Description: " + placemarkType.getDescription() + " (" + placemarkType.getName() + ")");
			PointType pointType = (PointType) placemarkType.getAbstractGeometryGroupGroup().get(kmlPackage.getDocumentRoot_Point(), true);
			if (Objects.nonNull(pointType)) {
				System.out.println("Coords: " + pointType.getCoordinates().get(0));
			}
			ExtendedDataType extendedData = documentType.getExtendedData();
			List<ProductDefinitionType> productDefinitions = (List<ProductDefinitionType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_ProductDefinition(), true);
			if (Objects.nonNull(productDefinitions) && !productDefinitions.isEmpty()) {
				EList<XMLGregorianCalendar> forecastTimeSteps = productDefinitions.get(0).getForecastTimeSteps().getTimeStep();
				System.out.println("Timesteps: " + forecastTimeSteps.size());
			}
			extendedData = placemarkType.getExtendedData();
			List<ForecastType> forecasts = (List<ForecastType>) extendedData.getAny().get(forecastPackage.getDocumentRoot_Forecast(), true);
			if (Objects.nonNull(forecasts)) {
				forecasts.forEach(f->{
					System.out.println("Forecast: " + f.getElementName() + " - " + f.getValue().getValue().size());
				});
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
