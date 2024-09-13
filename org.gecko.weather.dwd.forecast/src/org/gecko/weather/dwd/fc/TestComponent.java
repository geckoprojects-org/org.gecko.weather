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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.gecko.emf.osgi.annotation.require.RequireEMF;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

import net.opengis.gml.DirectPositionType;
import net.opengis.gml.EnvelopeType;
import net.opengis.gml.GMLFactory;
import net.opengis.ogc.BBOXType;
import net.opengis.ogc.BinaryComparisonOpType;
import net.opengis.ogc.BinaryLogicOpType;
import net.opengis.ogc.FilterType;
import net.opengis.ogc.LiteralType;
import net.opengis.ogc.OGCFactory;
import net.opengis.ogc.OGCPackage;
import net.opengis.ogc.PropertyNameType;
import net.opengis.wfs.DocumentRoot;
import net.opengis.wfs.GetFeatureType;
import net.opengis.wfs.QueryType;
import net.opengis.wfs.WFSFactory;
import net.opengis.wfs.WFSPackage;

/**
 * 
 * @author mark
 * @since 16.08.2024
 */
//@Component
@RequireEMF
public class TestComponent {
	
	@Reference
	private WFSFactory wfsFactory;
	@Reference
	private OGCFactory ogcFactory;
	@Reference
	private GMLFactory gmlFactory;
	@Reference
	private WFSPackage wfsPackage;
	@Reference
	private OGCPackage ogcPackage;
	@Reference
	private ResourceSet resourceSet;
	
	@Activate
	public void activate() {
		String srsName = "EPSG:4326";
		String propTimestamp = "ZEITSTEMPEL";
		DocumentRoot wfsRoot = wfsFactory.createDocumentRoot();
		GetFeatureType getFeature = wfsFactory.createGetFeatureType();
		wfsRoot.setGetFeature(getFeature);
		QueryType query = wfsFactory.createQueryType();
		getFeature.getQuery().add(query);
		QName qname = XMLTypeFactory.eINSTANCE.createQName("CDC", "OBS_DEU_PT1H_T2M");
		query.setTypeName(Collections.singletonList(qname));
		query.setSrsName(srsName);
		// creating filter
		FilterType filter = ogcFactory.createFilterType();
		query.setFilter(filter);
		// create first AND for the filter
		BinaryLogicOpType rootAnd = ogcFactory.createBinaryLogicOpType();
		filter.getLogicOpsGroup().add(ogcPackage.getDocumentRoot_And(), rootAnd);
		// create next AND in the rootAnd
		BinaryLogicOpType and = ogcFactory.createBinaryLogicOpType();
		// create GreaterThanEquals
		BinaryComparisonOpType gte = ogcFactory.createBinaryComparisonOpType();
		gte.setMatchCase(true);
		
		PropertyNameType gteProp = ogcFactory.createPropertyNameType();
		gteProp.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, propTimestamp);
		gte.getExpressionGroup().add(ogcPackage.getDocumentRoot_PropertyName(), gteProp);
		LiteralType gteLit = ogcFactory.createLiteralType();
		gteLit.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, "2024-08-15T22:00:00.000Z");
		gte.getExpressionGroup().add(ogcPackage.getDocumentRoot_Literal(), gteLit);
		
		and.getGroup().add(ogcPackage.getDocumentRoot_PropertyIsGreaterThanOrEqualTo(), gte);
		
		// create LowerThanEquals
		BinaryComparisonOpType lte = ogcFactory.createBinaryComparisonOpType();
		lte.setMatchCase(true);
		lte.getExpressionGroup().add(ogcPackage.getDocumentRoot_PropertyName(), EcoreUtil.copy(gteProp));
		LiteralType lteLit = ogcFactory.createLiteralType();
		lteLit.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, "2024-08-15T22:59:00.000Z");
		lte.getExpressionGroup().add(ogcPackage.getDocumentRoot_Literal(), lteLit);
		and.getGroup().add(ogcPackage.getDocumentRoot_PropertyIsLessThanOrEqualTo(), lte);
		// create BBOX in the bboxRoot
		BBOXType bbox = ogcFactory.createBBOXType();
		PropertyNameType bboxProp = ogcFactory.createPropertyNameType();
		bboxProp.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, "SDO_GEOM");
		bbox.setPropertyName(bboxProp);
		EnvelopeType envelope = gmlFactory.createEnvelopeType();
		envelope.setSrsName(srsName);
		DirectPositionType lower = gmlFactory.createDirectPositionType();
		lower.setValue(List.of(12.029342020703538, 50.79734489233678));
		envelope.setLowerCorner(lower);
		DirectPositionType upper = gmlFactory.createDirectPositionType();
		upper.setValue(List.of(12.46996380007253, 51.0866479327326));
		envelope.setUpperCorner(upper);
		bbox.getEnvelopeGroup().add(ogcPackage.getBBOXType_Envelope(), envelope);
//		bbox.setEnvelope(envelope);
		// set the sub AND and BBOX  to the root AND
		rootAnd.getLogicOpsGroup().add(ogcPackage.getDocumentRoot_And(), and);
		rootAnd.getLogicOpsGroup().add(ogcPackage.getDocumentRoot_BBOX(), bbox);
		
		Resource resource = resourceSet.createResource(URI.createURI("data/query_save.wfs"));
		resource.getContents().add(wfsRoot);
		try {
			URL dwd = new URL("https://cdc.dwd.de/geoserver/wfs");
//			URL dwd = new URL("https://cdc.dwd.de/geoserver/wfs?outputFormat=json");
			HttpURLConnection dwdCon = (HttpURLConnection) dwd.openConnection();
			dwdCon.addRequestProperty("Accept", "application/json");
			dwdCon.addRequestProperty("Content-Type", "text/plain");
			dwdCon.setDoInput(true);
			dwdCon.setDoOutput(true);
			dwdCon.setRequestMethod("POST");
			resource.save(dwdCon.getOutputStream(), null);
			int responseCode = dwdCon.getResponseCode();
			if (responseCode == 200 ) {
//				InputStream result = dwdCon.getInputStream();
				Resource responseResource = resourceSet.createResource(URI.createURI("reposnse.wfs"));
				responseResource.load(dwdCon.getInputStream(), null);
				DocumentRoot response = (DocumentRoot) responseResource.getContents().get(0);
				 System.out.println("Response: " + response.toString());
			} else {
				System.out.println("Error: unexpected response code for request: " + responseCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		net.opengis.fes.DocumentRoot fesRoot = fesFactory.createDocumentRoot();
//		fesRoot.set
//		getFeature.getAbstractQueryExpression().add(null)
		resource = resourceSet.createResource(URI.createURI("data/query.wfs"));
		try {
			resource.load(null);
			DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
			System.out.println("Root: " + root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
