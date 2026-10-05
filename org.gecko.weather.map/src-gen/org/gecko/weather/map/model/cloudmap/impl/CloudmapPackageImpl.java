/**
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
package org.gecko.weather.map.model.cloudmap.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapFactory;
import org.gecko.weather.map.model.cloudmap.CloudmapPackage;

import org.geojson.Geometry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CloudmapPackageImpl extends EPackageImpl implements CloudmapPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudBandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType geoJsonGeometryEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CloudmapPackageImpl() {
		super(eNS_URI, CloudmapFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CloudmapPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CloudmapPackage init() {
		if (isInited) return (CloudmapPackage)EPackage.Registry.INSTANCE.getEPackage(CloudmapPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCloudmapPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CloudmapPackageImpl theCloudmapPackage = registeredCloudmapPackage instanceof CloudmapPackageImpl ? (CloudmapPackageImpl)registeredCloudmapPackage : new CloudmapPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCloudmapPackage.createPackageContents();

		// Initialize created meta-data
		theCloudmapPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCloudmapPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CloudmapPackage.eNS_URI, theCloudmapPackage);
		return theCloudmapPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCloudBand() {
		return cloudBandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Id() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Name() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Geometry() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_ValidTime() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Run() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_LeadHours() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Parameter() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Lower() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Upper() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Unit() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Color() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Source() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_Attribution() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_MinX() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_MinY() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_MaxX() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCloudBand_MaxY() {
		return (EAttribute)cloudBandEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getGeoJsonGeometry() {
		return geoJsonGeometryEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CloudmapFactory getCloudmapFactory() {
		return (CloudmapFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		cloudBandEClass = createEClass(CLOUD_BAND);
		createEAttribute(cloudBandEClass, CLOUD_BAND__ID);
		createEAttribute(cloudBandEClass, CLOUD_BAND__NAME);
		createEAttribute(cloudBandEClass, CLOUD_BAND__GEOMETRY);
		createEAttribute(cloudBandEClass, CLOUD_BAND__VALID_TIME);
		createEAttribute(cloudBandEClass, CLOUD_BAND__RUN);
		createEAttribute(cloudBandEClass, CLOUD_BAND__LEAD_HOURS);
		createEAttribute(cloudBandEClass, CLOUD_BAND__PARAMETER);
		createEAttribute(cloudBandEClass, CLOUD_BAND__LOWER);
		createEAttribute(cloudBandEClass, CLOUD_BAND__UPPER);
		createEAttribute(cloudBandEClass, CLOUD_BAND__UNIT);
		createEAttribute(cloudBandEClass, CLOUD_BAND__COLOR);
		createEAttribute(cloudBandEClass, CLOUD_BAND__SOURCE);
		createEAttribute(cloudBandEClass, CLOUD_BAND__ATTRIBUTION);
		createEAttribute(cloudBandEClass, CLOUD_BAND__MIN_X);
		createEAttribute(cloudBandEClass, CLOUD_BAND__MIN_Y);
		createEAttribute(cloudBandEClass, CLOUD_BAND__MAX_X);
		createEAttribute(cloudBandEClass, CLOUD_BAND__MAX_Y);

		// Create data types
		geoJsonGeometryEDataType = createEDataType(GEO_JSON_GEOMETRY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(cloudBandEClass, CloudBand.class, "CloudBand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCloudBand_Id(), ecorePackage.getEString(), "id", null, 1, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Name(), ecorePackage.getEString(), "name", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Geometry(), this.getGeoJsonGeometry(), "geometry", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_ValidTime(), ecorePackage.getEDate(), "validTime", null, 1, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Run(), ecorePackage.getEDate(), "run", null, 1, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_LeadHours(), ecorePackage.getEInt(), "leadHours", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Parameter(), ecorePackage.getEString(), "parameter", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Lower(), ecorePackage.getEDouble(), "lower", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Upper(), ecorePackage.getEDouble(), "upper", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Color(), ecorePackage.getEString(), "color", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Source(), ecorePackage.getEString(), "source", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_Attribution(), ecorePackage.getEString(), "attribution", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_MinX(), ecorePackage.getEDouble(), "minX", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_MinY(), ecorePackage.getEDouble(), "minY", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_MaxX(), ecorePackage.getEDouble(), "maxX", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCloudBand_MaxY(), ecorePackage.getEDouble(), "maxY", null, 0, 1, CloudBand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(geoJsonGeometryEDataType, Geometry.class, "GeoJsonGeometry", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// https://eclipse.org/fennec/ogc/features
		createFeaturesAnnotations();
	}

	/**
	 * Initializes the annotations for <b>https://eclipse.org/fennec/ogc/features</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createFeaturesAnnotations() {
		String source = "https://eclipse.org/fennec/ogc/features";
		addAnnotation
		  (cloudBandEClass,
		   source,
		   new String[] {
			   "collection", "true",
			   "id", "cloudcover",
			   "title", "Bew\u00f6lkung jetzt (ICON-D2)",
			   "description", "Gesamtbedeckungsgrad clct der aktuellen Stunde aus dem neuesten ICON-D2-Lauf, in Vierteln, rund um die Standorte. Datenbasis: Deutscher Wetterdienst.",
			   "geometry", "geometry",
			   "bbox", "minX,minY,maxX,maxY",
			   "temporal", "validTime",
			   "layerGroup", "Wetter",
			   "style", "{\"color\":\"#78909c\",\"fillOpacity\":0.45,\"labels\":false}"
		   });
	}

} //CloudmapPackageImpl
