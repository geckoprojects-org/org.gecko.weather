/*
 * Copyright (c) 2012 - 2026 Data In Motion and others.
 * All rights reserved.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.weather.map.model.cloudmap;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;

import org.eclipse.fennec.emf.osgi.annotation.provide.EPackage;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Maps made from whole gridded fields, as OGC API Features collections: the total cloud cover of a model run as disjoint bands of polygons, one feature per band and hour. Derived from the field, not a source of its own — the values per site stay in the report.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.map.model.cloudmap.CloudmapFactory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = CloudmapPackage.eNS_URI, fingerprint = "fp1:5fb9d334f075a75494be5f586017314eebbe0eaf7d9bb18687894278d17a612d", genModel = "/model/cloudmap.genmodel", genModelSourceLocations = {"model/cloudmap.genmodel","org.gecko.weather.map/model/cloudmap.genmodel"}, ecore = "/model/cloudmap.ecore", ecoreSourceLocations = "/model/cloudmap.ecore")
public interface CloudmapPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cloudmap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/weather/cloudmap/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cloudmap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CloudmapPackage eINSTANCE = org.gecko.weather.map.model.cloudmap.impl.CloudmapPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl <em>Cloud Band</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl
	 * @see org.gecko.weather.map.model.cloudmap.impl.CloudmapPackageImpl#getCloudBand()
	 * @generated
	 */
	int CLOUD_BAND = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__NAME = 1;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__GEOMETRY = 2;

	/**
	 * The feature id for the '<em><b>Valid Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__VALID_TIME = 3;

	/**
	 * The feature id for the '<em><b>Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__RUN = 4;

	/**
	 * The feature id for the '<em><b>Lead Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__LEAD_HOURS = 5;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__PARAMETER = 6;

	/**
	 * The feature id for the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__LOWER = 7;

	/**
	 * The feature id for the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__UPPER = 8;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__UNIT = 9;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__COLOR = 10;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__SOURCE = 11;

	/**
	 * The feature id for the '<em><b>Attribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__ATTRIBUTION = 12;

	/**
	 * The feature id for the '<em><b>Min X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__MIN_X = 13;

	/**
	 * The feature id for the '<em><b>Min Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__MIN_Y = 14;

	/**
	 * The feature id for the '<em><b>Max X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__MAX_X = 15;

	/**
	 * The feature id for the '<em><b>Max Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND__MAX_Y = 16;

	/**
	 * The number of structural features of the '<em>Cloud Band</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Cloud Band</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_BAND_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Geo Json Geometry</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geojson.Geometry
	 * @see org.gecko.weather.map.model.cloudmap.impl.CloudmapPackageImpl#getGeoJsonGeometry()
	 * @generated
	 */
	int GEO_JSON_GEOMETRY = 1;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.map.model.cloudmap.CloudBand <em>Cloud Band</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cloud Band</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand
	 * @generated
	 */
	EClass getCloudBand();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getId()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getName()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Geometry</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getGeometry()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Geometry();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getValidTime <em>Valid Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Time</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getValidTime()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_ValidTime();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getRun <em>Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Run</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getRun()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Run();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLeadHours <em>Lead Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lead Hours</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getLeadHours()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_LeadHours();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getParameter()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLower <em>Lower</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getLower()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Lower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUpper <em>Upper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getUpper()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Upper();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getUnit()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Unit();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getColor()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Color();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getSource()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Source();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getAttribution <em>Attribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribution</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getAttribution()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_Attribution();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinX <em>Min X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min X</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getMinX()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_MinX();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinY <em>Min Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Y</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getMinY()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_MinY();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxX <em>Max X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max X</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getMaxX()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_MaxX();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxY <em>Max Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Y</em>'.
	 * @see org.gecko.weather.map.model.cloudmap.CloudBand#getMaxY()
	 * @see #getCloudBand()
	 * @generated
	 */
	EAttribute getCloudBand_MaxY();

	/**
	 * Returns the meta object for data type '{@link org.geojson.Geometry <em>Geo Json Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A geometry of the GeoJSON model (org.geojson.model), as the OGC API Features runtime of Fennec expects it.
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Geo Json Geometry</em>'.
	 * @see org.geojson.Geometry
	 * @model instanceClass="org.geojson.Geometry"
	 * @generated
	 */
	EDataType getGeoJsonGeometry();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CloudmapFactory getCloudmapFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl <em>Cloud Band</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl
		 * @see org.gecko.weather.map.model.cloudmap.impl.CloudmapPackageImpl#getCloudBand()
		 * @generated
		 */
		EClass CLOUD_BAND = eINSTANCE.getCloudBand();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__ID = eINSTANCE.getCloudBand_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__NAME = eINSTANCE.getCloudBand_Name();

		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__GEOMETRY = eINSTANCE.getCloudBand_Geometry();

		/**
		 * The meta object literal for the '<em><b>Valid Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__VALID_TIME = eINSTANCE.getCloudBand_ValidTime();

		/**
		 * The meta object literal for the '<em><b>Run</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__RUN = eINSTANCE.getCloudBand_Run();

		/**
		 * The meta object literal for the '<em><b>Lead Hours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__LEAD_HOURS = eINSTANCE.getCloudBand_LeadHours();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__PARAMETER = eINSTANCE.getCloudBand_Parameter();

		/**
		 * The meta object literal for the '<em><b>Lower</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__LOWER = eINSTANCE.getCloudBand_Lower();

		/**
		 * The meta object literal for the '<em><b>Upper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__UPPER = eINSTANCE.getCloudBand_Upper();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__UNIT = eINSTANCE.getCloudBand_Unit();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__COLOR = eINSTANCE.getCloudBand_Color();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__SOURCE = eINSTANCE.getCloudBand_Source();

		/**
		 * The meta object literal for the '<em><b>Attribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__ATTRIBUTION = eINSTANCE.getCloudBand_Attribution();

		/**
		 * The meta object literal for the '<em><b>Min X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__MIN_X = eINSTANCE.getCloudBand_MinX();

		/**
		 * The meta object literal for the '<em><b>Min Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__MIN_Y = eINSTANCE.getCloudBand_MinY();

		/**
		 * The meta object literal for the '<em><b>Max X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__MAX_X = eINSTANCE.getCloudBand_MaxX();

		/**
		 * The meta object literal for the '<em><b>Max Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_BAND__MAX_Y = eINSTANCE.getCloudBand_MaxY();

		/**
		 * The meta object literal for the '<em>Geo Json Geometry</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geojson.Geometry
		 * @see org.gecko.weather.map.model.cloudmap.impl.CloudmapPackageImpl#getGeoJsonGeometry()
		 * @generated
		 */
		EDataType GEO_JSON_GEOMETRY = eINSTANCE.getGeoJsonGeometry();

	}

} //CloudmapPackage
