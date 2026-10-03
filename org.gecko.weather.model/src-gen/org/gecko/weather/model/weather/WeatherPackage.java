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
package org.gecko.weather.model.weather;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;

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
 * Provider-neutral weather model. A registered Site carries its position and the source bindings (stations, grid cells) resolved for it. A WeatherReport per site holds one SourceDataset per source product; every MeasuredValue inside carries its own provenance and uncertainty. Values are kept per source — nothing is merged in the model, consumers decide how to combine sources.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.model.weather.WeatherFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore conversionDelegates='java.time'"
 * @generated
 */
@ProviderType
@EPackage(uri = WeatherPackage.eNS_URI, fingerprint = "fp1:f5ad257535e4fef6530743ee331d9fc9daa14370521fb8d8602b11e3dfeab3be", genModel = "/model/weather.genmodel", genModelSourceLocations = {"model/weather.genmodel","org.gecko.weather.model/model/weather.genmodel"}, ecore = "/model/weather.ecore", ecoreSourceLocations = "/model/weather.ecore")
public interface WeatherPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "weather";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/weather/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "weather";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WeatherPackage eINSTANCE = org.gecko.weather.model.weather.impl.WeatherPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.GeoPositionImpl <em>Geo Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.GeoPositionImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGeoPosition()
	 * @generated
	 */
	int GEO_POSITION = 0;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION__LATITUDE = 0;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION__LONGITUDE = 1;

	/**
	 * The feature id for the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION__ELEVATION = 2;

	/**
	 * The number of structural features of the '<em>Geo Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Geo Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.SiteImpl <em>Site</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.SiteImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSite()
	 * @generated
	 */
	int SITE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POSITION = 2;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__TIME_ZONE = 3;

	/**
	 * The feature id for the '<em><b>Registered At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__REGISTERED_AT = 4;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ACTIVE = 5;

	/**
	 * The feature id for the '<em><b>Data Complete From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__DATA_COMPLETE_FROM = 6;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ATTRIBUTES = 7;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__BINDINGS = 8;

	/**
	 * The number of structural features of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.SiteAttributeImpl <em>Site Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.SiteAttributeImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSiteAttribute()
	 * @generated
	 */
	int SITE_ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ATTRIBUTE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ATTRIBUTE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Site Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Site Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.SourceBindingImpl <em>Source Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.SourceBindingImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSourceBinding()
	 * @generated
	 */
	int SOURCE_BINDING = 3;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__PROVIDER_ID = 0;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__PRODUCT_ID = 1;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__ORIGIN = 2;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__RANK = 3;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__DISTANCE_METERS = 4;

	/**
	 * The feature id for the '<em><b>Elevation Delta Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__ELEVATION_DELTA_METERS = 5;

	/**
	 * The feature id for the '<em><b>Resolved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING__RESOLVED_AT = 6;

	/**
	 * The number of structural features of the '<em>Source Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Source Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_BINDING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.StationBindingImpl <em>Station Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.StationBindingImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStationBinding()
	 * @generated
	 */
	int STATION_BINDING = 4;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__PROVIDER_ID = SOURCE_BINDING__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__PRODUCT_ID = SOURCE_BINDING__PRODUCT_ID;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__ORIGIN = SOURCE_BINDING__ORIGIN;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__RANK = SOURCE_BINDING__RANK;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__DISTANCE_METERS = SOURCE_BINDING__DISTANCE_METERS;

	/**
	 * The feature id for the '<em><b>Elevation Delta Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__ELEVATION_DELTA_METERS = SOURCE_BINDING__ELEVATION_DELTA_METERS;

	/**
	 * The feature id for the '<em><b>Resolved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__RESOLVED_AT = SOURCE_BINDING__RESOLVED_AT;

	/**
	 * The feature id for the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING__STATION = SOURCE_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Station Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING_FEATURE_COUNT = SOURCE_BINDING_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Station Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_BINDING_OPERATION_COUNT = SOURCE_BINDING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.GridBindingImpl <em>Grid Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.GridBindingImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGridBinding()
	 * @generated
	 */
	int GRID_BINDING = 5;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__PROVIDER_ID = SOURCE_BINDING__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__PRODUCT_ID = SOURCE_BINDING__PRODUCT_ID;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__ORIGIN = SOURCE_BINDING__ORIGIN;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__RANK = SOURCE_BINDING__RANK;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__DISTANCE_METERS = SOURCE_BINDING__DISTANCE_METERS;

	/**
	 * The feature id for the '<em><b>Elevation Delta Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__ELEVATION_DELTA_METERS = SOURCE_BINDING__ELEVATION_DELTA_METERS;

	/**
	 * The feature id for the '<em><b>Resolved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__RESOLVED_AT = SOURCE_BINDING__RESOLVED_AT;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING__CELL = SOURCE_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Grid Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING_FEATURE_COUNT = SOURCE_BINDING_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Grid Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_BINDING_OPERATION_COUNT = SOURCE_BINDING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.StationImpl <em>Station</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.StationImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStation()
	 * @generated
	 */
	int STATION = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Icao Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__ICAO_CODE = 2;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__COUNTRY = 3;

	/**
	 * The feature id for the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__POSITION = 4;

	/**
	 * The number of structural features of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.StationCatalogImpl <em>Station Catalog</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.StationCatalogImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStationCatalog()
	 * @generated
	 */
	int STATION_CATALOG = 7;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG__PROVIDER_ID = 0;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG__PRODUCT_ID = 1;

	/**
	 * The feature id for the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG__RETRIEVED_AT = 2;

	/**
	 * The feature id for the '<em><b>Stations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG__STATIONS = 3;

	/**
	 * The number of structural features of the '<em>Station Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Station Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_CATALOG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.GridCellImpl <em>Grid Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.GridCellImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGridCell()
	 * @generated
	 */
	int GRID_CELL = 8;

	/**
	 * The feature id for the '<em><b>Grid Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL__GRID_ID = 0;

	/**
	 * The feature id for the '<em><b>I</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL__I = 1;

	/**
	 * The feature id for the '<em><b>J</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL__J = 2;

	/**
	 * The feature id for the '<em><b>Center</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL__CENTER = 3;

	/**
	 * The feature id for the '<em><b>Resolution Degrees</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL__RESOLUTION_DEGREES = 4;

	/**
	 * The number of structural features of the '<em>Grid Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Grid Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_CELL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl <em>Measured Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.MeasuredValueImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasuredValue()
	 * @generated
	 */
	int MEASURED_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__KIND = 0;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__LEVEL = 1;

	/**
	 * The feature id for the '<em><b>Statistic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__STATISTIC = 2;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__PERIOD = 3;

	/**
	 * The feature id for the '<em><b>Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__THRESHOLD = 4;

	/**
	 * The feature id for the '<em><b>Threshold Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__THRESHOLD_UNIT = 5;

	/**
	 * The feature id for the '<em><b>Valid At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__VALID_AT = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__VALUE = 7;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__CODE = 8;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__UNIT = 9;

	/**
	 * The feature id for the '<em><b>Provenance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__PROVENANCE = 10;

	/**
	 * The feature id for the '<em><b>Uncertainty</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE__UNCERTAINTY = 11;

	/**
	 * The number of structural features of the '<em>Measured Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Measured Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.ProvenanceImpl <em>Provenance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.ProvenanceImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getProvenance()
	 * @generated
	 */
	int PROVENANCE = 10;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__PROVIDER_ID = 0;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__PRODUCT_ID = 1;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__SOURCE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Model Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__MODEL_RUN = 3;

	/**
	 * The feature id for the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__ISSUED_AT = 4;

	/**
	 * The feature id for the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__RETRIEVED_AT = 5;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__ORIGIN = 6;

	/**
	 * The feature id for the '<em><b>Station Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__STATION_ID = 7;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__CELL = 8;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__DISTANCE_METERS = 9;

	/**
	 * The feature id for the '<em><b>Licence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__LICENCE = 10;

	/**
	 * The feature id for the '<em><b>Attribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__ATTRIBUTION = 11;

	/**
	 * The feature id for the '<em><b>Derivation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE__DERIVATION = 12;

	/**
	 * The number of structural features of the '<em>Provenance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE_FEATURE_COUNT = 13;

	/**
	 * The number of operations of the '<em>Provenance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVENANCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.DerivationImpl <em>Derivation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.DerivationImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDerivation()
	 * @generated
	 */
	int DERIVATION = 11;

	/**
	 * The feature id for the '<em><b>Function Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVATION__FUNCTION_ID = 0;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVATION__INPUTS = 1;

	/**
	 * The number of structural features of the '<em>Derivation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Derivation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.UncertaintyImpl <em>Uncertainty</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.UncertaintyImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getUncertainty()
	 * @generated
	 */
	int UNCERTAINTY = 12;

	/**
	 * The feature id for the '<em><b>Quality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__QUALITY = 0;

	/**
	 * The feature id for the '<em><b>Spatial Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__SPATIAL_METERS = 1;

	/**
	 * The feature id for the '<em><b>Temporal Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__TEMPORAL_OFFSET = 2;

	/**
	 * The feature id for the '<em><b>Lead Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__LEAD_TIME = 3;

	/**
	 * The feature id for the '<em><b>Stale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__STALE = 4;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY__NOTE = 5;

	/**
	 * The number of structural features of the '<em>Uncertainty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Uncertainty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNCERTAINTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.WeatherReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.WeatherReportImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getWeatherReport()
	 * @generated
	 */
	int WEATHER_REPORT = 13;

	/**
	 * The feature id for the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__SITE_ID = 0;

	/**
	 * The feature id for the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__GENERATED_AT = 1;

	/**
	 * The feature id for the '<em><b>Datasets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__DATASETS = 2;

	/**
	 * The feature id for the '<em><b>Days</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__DAYS = 3;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.SourceDatasetImpl <em>Source Dataset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.SourceDatasetImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSourceDataset()
	 * @generated
	 */
	int SOURCE_DATASET = 14;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__PROVIDER_ID = 0;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__PRODUCT_ID = 1;

	/**
	 * The feature id for the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__ISSUED_AT = 2;

	/**
	 * The feature id for the '<em><b>Model Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__MODEL_RUN = 3;

	/**
	 * The feature id for the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__RETRIEVED_AT = 4;

	/**
	 * The feature id for the '<em><b>Expected Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__EXPECTED_REFRESH = 5;

	/**
	 * The feature id for the '<em><b>Horizon Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__HORIZON_START = 6;

	/**
	 * The feature id for the '<em><b>Horizon End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__HORIZON_END = 7;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__ORIGIN = 8;

	/**
	 * The feature id for the '<em><b>Station Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__STATION_ID = 9;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__CELL = 10;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__DISTANCE_METERS = 11;

	/**
	 * The feature id for the '<em><b>Licence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__LICENCE = 12;

	/**
	 * The feature id for the '<em><b>Attribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__ATTRIBUTION = 13;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET__VALUES = 14;

	/**
	 * The number of structural features of the '<em>Source Dataset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET_FEATURE_COUNT = 15;

	/**
	 * The number of operations of the '<em>Source Dataset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_DATASET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.DayInfoImpl <em>Day Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.DayInfoImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDayInfo()
	 * @generated
	 */
	int DAY_INFO = 15;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__DATE = 0;

	/**
	 * The feature id for the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__SUNRISE = 1;

	/**
	 * The feature id for the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__SUNSET = 2;

	/**
	 * The feature id for the '<em><b>Civil Dawn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__CIVIL_DAWN = 3;

	/**
	 * The feature id for the '<em><b>Civil Dusk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__CIVIL_DUSK = 4;

	/**
	 * The feature id for the '<em><b>Nautical Dawn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__NAUTICAL_DAWN = 5;

	/**
	 * The feature id for the '<em><b>Nautical Dusk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__NAUTICAL_DUSK = 6;

	/**
	 * The feature id for the '<em><b>Solar Noon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__SOLAR_NOON = 7;

	/**
	 * The feature id for the '<em><b>Day Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__DAY_LENGTH = 8;

	/**
	 * The feature id for the '<em><b>Max Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__MAX_SUN_ELEVATION = 9;

	/**
	 * The feature id for the '<em><b>Provenance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO__PROVENANCE = 10;

	/**
	 * The number of structural features of the '<em>Day Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Day Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.BindingOrigin <em>Binding Origin</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.BindingOrigin
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBindingOrigin()
	 * @generated
	 */
	int BINDING_ORIGIN = 16;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.MeasurementKind <em>Measurement Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.MeasurementKind
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurementKind()
	 * @generated
	 */
	int MEASUREMENT_KIND = 17;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.Level <em>Level</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.Level
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getLevel()
	 * @generated
	 */
	int LEVEL = 18;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.Statistic <em>Statistic</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.Statistic
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStatistic()
	 * @generated
	 */
	int STATISTIC = 19;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.Origin <em>Origin</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.Origin
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getOrigin()
	 * @generated
	 */
	int ORIGIN = 20;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.Quality <em>Quality</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.Quality
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getQuality()
	 * @generated
	 */
	int QUALITY = 21;

	/**
	 * The meta object id for the '<em>Instant</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Instant
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getInstant()
	 * @generated
	 */
	int INSTANT = 22;

	/**
	 * The meta object id for the '<em>Duration</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Duration
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDuration()
	 * @generated
	 */
	int DURATION = 23;

	/**
	 * The meta object id for the '<em>Local Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.LocalDate
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getLocalDate()
	 * @generated
	 */
	int LOCAL_DATE = 24;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.GeoPosition <em>Geo Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geo Position</em>'.
	 * @see org.gecko.weather.model.weather.GeoPosition
	 * @generated
	 */
	EClass getGeoPosition();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GeoPosition#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude</em>'.
	 * @see org.gecko.weather.model.weather.GeoPosition#getLatitude()
	 * @see #getGeoPosition()
	 * @generated
	 */
	EAttribute getGeoPosition_Latitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GeoPosition#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude</em>'.
	 * @see org.gecko.weather.model.weather.GeoPosition#getLongitude()
	 * @see #getGeoPosition()
	 * @generated
	 */
	EAttribute getGeoPosition_Longitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Elevation</em>'.
	 * @see org.gecko.weather.model.weather.GeoPosition#getElevation()
	 * @see #getGeoPosition()
	 * @generated
	 */
	EAttribute getGeoPosition_Elevation();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Site <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site</em>'.
	 * @see org.gecko.weather.model.weather.Site
	 * @generated
	 */
	EClass getSite();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.model.weather.Site#getId()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.model.weather.Site#getName()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.Site#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Position</em>'.
	 * @see org.gecko.weather.model.weather.Site#getPosition()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Position();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#getTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.gecko.weather.model.weather.Site#getTimeZone()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_TimeZone();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#getRegisteredAt <em>Registered At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Registered At</em>'.
	 * @see org.gecko.weather.model.weather.Site#getRegisteredAt()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_RegisteredAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see org.gecko.weather.model.weather.Site#isActive()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_Active();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Site#getDataCompleteFrom <em>Data Complete From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Complete From</em>'.
	 * @see org.gecko.weather.model.weather.Site#getDataCompleteFrom()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_DataCompleteFrom();

	/**
	 * Returns the meta object for the map '{@link org.gecko.weather.model.weather.Site#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see org.gecko.weather.model.weather.Site#getAttributes()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.Site#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bindings</em>'.
	 * @see org.gecko.weather.model.weather.Site#getBindings()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Bindings();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Site Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site Attribute</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getSiteAttribute();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSiteAttribute()
	 * @generated
	 */
	EAttribute getSiteAttribute_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSiteAttribute()
	 * @generated
	 */
	EAttribute getSiteAttribute_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.SourceBinding <em>Source Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Binding</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding
	 * @generated
	 */
	EClass getSourceBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getProviderId()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getProductId <em>Product Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Id</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getProductId()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_ProductId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getOrigin()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_Origin();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getRank()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_Rank();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getDistanceMeters <em>Distance Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance Meters</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getDistanceMeters()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_DistanceMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters <em>Elevation Delta Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Elevation Delta Meters</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_ElevationDeltaMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceBinding#getResolvedAt <em>Resolved At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolved At</em>'.
	 * @see org.gecko.weather.model.weather.SourceBinding#getResolvedAt()
	 * @see #getSourceBinding()
	 * @generated
	 */
	EAttribute getSourceBinding_ResolvedAt();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.StationBinding <em>Station Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Station Binding</em>'.
	 * @see org.gecko.weather.model.weather.StationBinding
	 * @generated
	 */
	EClass getStationBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.StationBinding#getStation <em>Station</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Station</em>'.
	 * @see org.gecko.weather.model.weather.StationBinding#getStation()
	 * @see #getStationBinding()
	 * @generated
	 */
	EReference getStationBinding_Station();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.GridBinding <em>Grid Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Binding</em>'.
	 * @see org.gecko.weather.model.weather.GridBinding
	 * @generated
	 */
	EClass getGridBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.GridBinding#getCell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cell</em>'.
	 * @see org.gecko.weather.model.weather.GridBinding#getCell()
	 * @see #getGridBinding()
	 * @generated
	 */
	EReference getGridBinding_Cell();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Station <em>Station</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Station</em>'.
	 * @see org.gecko.weather.model.weather.Station
	 * @generated
	 */
	EClass getStation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Station#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.model.weather.Station#getId()
	 * @see #getStation()
	 * @generated
	 */
	EAttribute getStation_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Station#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.model.weather.Station#getName()
	 * @see #getStation()
	 * @generated
	 */
	EAttribute getStation_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Station#getIcaoCode <em>Icao Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icao Code</em>'.
	 * @see org.gecko.weather.model.weather.Station#getIcaoCode()
	 * @see #getStation()
	 * @generated
	 */
	EAttribute getStation_IcaoCode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Station#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.gecko.weather.model.weather.Station#getCountry()
	 * @see #getStation()
	 * @generated
	 */
	EAttribute getStation_Country();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.Station#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Position</em>'.
	 * @see org.gecko.weather.model.weather.Station#getPosition()
	 * @see #getStation()
	 * @generated
	 */
	EReference getStation_Position();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.StationCatalog <em>Station Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Station Catalog</em>'.
	 * @see org.gecko.weather.model.weather.StationCatalog
	 * @generated
	 */
	EClass getStationCatalog();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.StationCatalog#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see org.gecko.weather.model.weather.StationCatalog#getProviderId()
	 * @see #getStationCatalog()
	 * @generated
	 */
	EAttribute getStationCatalog_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.StationCatalog#getProductId <em>Product Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Id</em>'.
	 * @see org.gecko.weather.model.weather.StationCatalog#getProductId()
	 * @see #getStationCatalog()
	 * @generated
	 */
	EAttribute getStationCatalog_ProductId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.StationCatalog#getRetrievedAt <em>Retrieved At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Retrieved At</em>'.
	 * @see org.gecko.weather.model.weather.StationCatalog#getRetrievedAt()
	 * @see #getStationCatalog()
	 * @generated
	 */
	EAttribute getStationCatalog_RetrievedAt();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.StationCatalog#getStations <em>Stations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stations</em>'.
	 * @see org.gecko.weather.model.weather.StationCatalog#getStations()
	 * @see #getStationCatalog()
	 * @generated
	 */
	EReference getStationCatalog_Stations();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.GridCell <em>Grid Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Cell</em>'.
	 * @see org.gecko.weather.model.weather.GridCell
	 * @generated
	 */
	EClass getGridCell();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GridCell#getGridId <em>Grid Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Id</em>'.
	 * @see org.gecko.weather.model.weather.GridCell#getGridId()
	 * @see #getGridCell()
	 * @generated
	 */
	EAttribute getGridCell_GridId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GridCell#getI <em>I</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>I</em>'.
	 * @see org.gecko.weather.model.weather.GridCell#getI()
	 * @see #getGridCell()
	 * @generated
	 */
	EAttribute getGridCell_I();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GridCell#getJ <em>J</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>J</em>'.
	 * @see org.gecko.weather.model.weather.GridCell#getJ()
	 * @see #getGridCell()
	 * @generated
	 */
	EAttribute getGridCell_J();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.GridCell#getCenter <em>Center</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Center</em>'.
	 * @see org.gecko.weather.model.weather.GridCell#getCenter()
	 * @see #getGridCell()
	 * @generated
	 */
	EReference getGridCell_Center();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GridCell#getResolutionDegrees <em>Resolution Degrees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolution Degrees</em>'.
	 * @see org.gecko.weather.model.weather.GridCell#getResolutionDegrees()
	 * @see #getGridCell()
	 * @generated
	 */
	EAttribute getGridCell_ResolutionDegrees();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.MeasuredValue <em>Measured Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measured Value</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue
	 * @generated
	 */
	EClass getMeasuredValue();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getKind()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getLevel()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Level();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getStatistic <em>Statistic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statistic</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getStatistic()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Statistic();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getPeriod()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Period();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getThreshold <em>Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Threshold</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getThreshold()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Threshold();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getThresholdUnit <em>Threshold Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Threshold Unit</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getThresholdUnit()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_ThresholdUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getValidAt <em>Valid At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid At</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getValidAt()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_ValidAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getValue()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getCode()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Code();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MeasuredValue#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getUnit()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EAttribute getMeasuredValue_Unit();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.MeasuredValue#getProvenance <em>Provenance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Provenance</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getProvenance()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EReference getMeasuredValue_Provenance();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.MeasuredValue#getUncertainty <em>Uncertainty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Uncertainty</em>'.
	 * @see org.gecko.weather.model.weather.MeasuredValue#getUncertainty()
	 * @see #getMeasuredValue()
	 * @generated
	 */
	EReference getMeasuredValue_Uncertainty();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Provenance <em>Provenance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provenance</em>'.
	 * @see org.gecko.weather.model.weather.Provenance
	 * @generated
	 */
	EClass getProvenance();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getProviderId()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getProductId <em>Product Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Id</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getProductId()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_ProductId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getSourceElement <em>Source Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Element</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getSourceElement()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_SourceElement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getModelRun <em>Model Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Run</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getModelRun()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_ModelRun();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getIssuedAt <em>Issued At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issued At</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getIssuedAt()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_IssuedAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getRetrievedAt <em>Retrieved At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Retrieved At</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getRetrievedAt()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_RetrievedAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getOrigin()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_Origin();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getStationId <em>Station Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Station Id</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getStationId()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_StationId();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.Provenance#getCell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cell</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getCell()
	 * @see #getProvenance()
	 * @generated
	 */
	EReference getProvenance_Cell();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getDistanceMeters <em>Distance Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance Meters</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getDistanceMeters()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_DistanceMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getLicence <em>Licence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Licence</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getLicence()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_Licence();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Provenance#getAttribution <em>Attribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribution</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getAttribution()
	 * @see #getProvenance()
	 * @generated
	 */
	EAttribute getProvenance_Attribution();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.Provenance#getDerivation <em>Derivation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Derivation</em>'.
	 * @see org.gecko.weather.model.weather.Provenance#getDerivation()
	 * @see #getProvenance()
	 * @generated
	 */
	EReference getProvenance_Derivation();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Derivation <em>Derivation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derivation</em>'.
	 * @see org.gecko.weather.model.weather.Derivation
	 * @generated
	 */
	EClass getDerivation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Derivation#getFunctionId <em>Function Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Id</em>'.
	 * @see org.gecko.weather.model.weather.Derivation#getFunctionId()
	 * @see #getDerivation()
	 * @generated
	 */
	EAttribute getDerivation_FunctionId();

	/**
	 * Returns the meta object for the attribute list '{@link org.gecko.weather.model.weather.Derivation#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Inputs</em>'.
	 * @see org.gecko.weather.model.weather.Derivation#getInputs()
	 * @see #getDerivation()
	 * @generated
	 */
	EAttribute getDerivation_Inputs();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Uncertainty <em>Uncertainty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uncertainty</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty
	 * @generated
	 */
	EClass getUncertainty();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#getQuality <em>Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quality</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#getQuality()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_Quality();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#getSpatialMeters <em>Spatial Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spatial Meters</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#getSpatialMeters()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_SpatialMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#getTemporalOffset <em>Temporal Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temporal Offset</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#getTemporalOffset()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_TemporalOffset();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#getLeadTime <em>Lead Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lead Time</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#getLeadTime()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_LeadTime();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#isStale <em>Stale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stale</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#isStale()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_Stale();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Uncertainty#getNote <em>Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Note</em>'.
	 * @see org.gecko.weather.model.weather.Uncertainty#getNote()
	 * @see #getUncertainty()
	 * @generated
	 */
	EAttribute getUncertainty_Note();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.WeatherReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport
	 * @generated
	 */
	EClass getWeatherReport();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherReport#getSiteId <em>Site Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Id</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getSiteId()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EAttribute getWeatherReport_SiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherReport#getGeneratedAt <em>Generated At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generated At</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getGeneratedAt()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EAttribute getWeatherReport_GeneratedAt();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.WeatherReport#getDatasets <em>Datasets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Datasets</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getDatasets()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EReference getWeatherReport_Datasets();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.WeatherReport#getDays <em>Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Days</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getDays()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EReference getWeatherReport_Days();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.SourceDataset <em>Source Dataset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Dataset</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset
	 * @generated
	 */
	EClass getSourceDataset();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getProviderId()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getProductId <em>Product Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Id</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getProductId()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_ProductId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getIssuedAt <em>Issued At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issued At</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getIssuedAt()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_IssuedAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getModelRun <em>Model Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Run</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getModelRun()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_ModelRun();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getRetrievedAt <em>Retrieved At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Retrieved At</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getRetrievedAt()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_RetrievedAt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh <em>Expected Refresh</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expected Refresh</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_ExpectedRefresh();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getHorizonStart <em>Horizon Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizon Start</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getHorizonStart()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_HorizonStart();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getHorizonEnd <em>Horizon End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizon End</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getHorizonEnd()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_HorizonEnd();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getOrigin()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_Origin();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getStationId <em>Station Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Station Id</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getStationId()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_StationId();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.SourceDataset#getCell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cell</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getCell()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EReference getSourceDataset_Cell();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getDistanceMeters <em>Distance Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance Meters</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getDistanceMeters()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_DistanceMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getLicence <em>Licence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Licence</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getLicence()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_Licence();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.SourceDataset#getAttribution <em>Attribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribution</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getAttribution()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EAttribute getSourceDataset_Attribution();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.SourceDataset#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see org.gecko.weather.model.weather.SourceDataset#getValues()
	 * @see #getSourceDataset()
	 * @generated
	 */
	EReference getSourceDataset_Values();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.DayInfo <em>Day Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Day Info</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo
	 * @generated
	 */
	EClass getDayInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getDate()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_Date();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getSunrise <em>Sunrise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunrise</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getSunrise()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_Sunrise();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getSunset <em>Sunset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunset</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getSunset()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_Sunset();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getCivilDawn <em>Civil Dawn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Civil Dawn</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getCivilDawn()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_CivilDawn();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getCivilDusk <em>Civil Dusk</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Civil Dusk</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getCivilDusk()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_CivilDusk();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getNauticalDawn <em>Nautical Dawn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nautical Dawn</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getNauticalDawn()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_NauticalDawn();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getNauticalDusk <em>Nautical Dusk</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nautical Dusk</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getNauticalDusk()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_NauticalDusk();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getSolarNoon <em>Solar Noon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solar Noon</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getSolarNoon()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_SolarNoon();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getDayLength <em>Day Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day Length</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getDayLength()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_DayLength();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DayInfo#getMaxSunElevation <em>Max Sun Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Sun Elevation</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getMaxSunElevation()
	 * @see #getDayInfo()
	 * @generated
	 */
	EAttribute getDayInfo_MaxSunElevation();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.DayInfo#getProvenance <em>Provenance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Provenance</em>'.
	 * @see org.gecko.weather.model.weather.DayInfo#getProvenance()
	 * @see #getDayInfo()
	 * @generated
	 */
	EReference getDayInfo_Provenance();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.BindingOrigin <em>Binding Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binding Origin</em>'.
	 * @see org.gecko.weather.model.weather.BindingOrigin
	 * @generated
	 */
	EEnum getBindingOrigin();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.MeasurementKind <em>Measurement Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Measurement Kind</em>'.
	 * @see org.gecko.weather.model.weather.MeasurementKind
	 * @generated
	 */
	EEnum getMeasurementKind();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.Level <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Level</em>'.
	 * @see org.gecko.weather.model.weather.Level
	 * @generated
	 */
	EEnum getLevel();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.Statistic <em>Statistic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Statistic</em>'.
	 * @see org.gecko.weather.model.weather.Statistic
	 * @generated
	 */
	EEnum getStatistic();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.Origin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Origin</em>'.
	 * @see org.gecko.weather.model.weather.Origin
	 * @generated
	 */
	EEnum getOrigin();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.model.weather.Quality <em>Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Quality</em>'.
	 * @see org.gecko.weather.model.weather.Quality
	 * @generated
	 */
	EEnum getQuality();

	/**
	 * Returns the meta object for data type '{@link java.time.Instant <em>Instant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Point on the UTC time line. Serialised in ISO-8601 (java.time.Instant#toString) through the java.time conversion delegate shipped with this bundle.
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Instant</em>'.
	 * @see java.time.Instant
	 * @model instanceClass="java.time.Instant"
	 *        annotation="java.time"
	 * @generated
	 */
	EDataType getInstant();

	/**
	 * Returns the meta object for data type '{@link java.time.Duration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Length of time. Serialised in ISO-8601 (PT1H, PT6H, ...) through the java.time conversion delegate.
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Duration</em>'.
	 * @see java.time.Duration
	 * @model instanceClass="java.time.Duration"
	 *        annotation="java.time"
	 * @generated
	 */
	EDataType getDuration();

	/**
	 * Returns the meta object for data type '{@link java.time.LocalDate <em>Local Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Calendar date without a zone, interpreted in the site's time zone. Serialised in ISO-8601 (2026-10-03).
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Local Date</em>'.
	 * @see java.time.LocalDate
	 * @model instanceClass="java.time.LocalDate"
	 *        annotation="java.time"
	 * @generated
	 */
	EDataType getLocalDate();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WeatherFactory getWeatherFactory();

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
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.GeoPositionImpl <em>Geo Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.GeoPositionImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGeoPosition()
		 * @generated
		 */
		EClass GEO_POSITION = eINSTANCE.getGeoPosition();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_POSITION__LATITUDE = eINSTANCE.getGeoPosition_Latitude();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_POSITION__LONGITUDE = eINSTANCE.getGeoPosition_Longitude();

		/**
		 * The meta object literal for the '<em><b>Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_POSITION__ELEVATION = eINSTANCE.getGeoPosition_Elevation();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.SiteImpl <em>Site</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.SiteImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSite()
		 * @generated
		 */
		EClass SITE = eINSTANCE.getSite();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__ID = eINSTANCE.getSite_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__NAME = eINSTANCE.getSite_Name();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__POSITION = eINSTANCE.getSite_Position();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__TIME_ZONE = eINSTANCE.getSite_TimeZone();

		/**
		 * The meta object literal for the '<em><b>Registered At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__REGISTERED_AT = eINSTANCE.getSite_RegisteredAt();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__ACTIVE = eINSTANCE.getSite_Active();

		/**
		 * The meta object literal for the '<em><b>Data Complete From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__DATA_COMPLETE_FROM = eINSTANCE.getSite_DataCompleteFrom();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__ATTRIBUTES = eINSTANCE.getSite_Attributes();

		/**
		 * The meta object literal for the '<em><b>Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__BINDINGS = eINSTANCE.getSite_Bindings();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.SiteAttributeImpl <em>Site Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.SiteAttributeImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSiteAttribute()
		 * @generated
		 */
		EClass SITE_ATTRIBUTE = eINSTANCE.getSiteAttribute();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ATTRIBUTE__KEY = eINSTANCE.getSiteAttribute_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ATTRIBUTE__VALUE = eINSTANCE.getSiteAttribute_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.SourceBindingImpl <em>Source Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.SourceBindingImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSourceBinding()
		 * @generated
		 */
		EClass SOURCE_BINDING = eINSTANCE.getSourceBinding();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__PROVIDER_ID = eINSTANCE.getSourceBinding_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Product Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__PRODUCT_ID = eINSTANCE.getSourceBinding_ProductId();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__ORIGIN = eINSTANCE.getSourceBinding_Origin();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__RANK = eINSTANCE.getSourceBinding_Rank();

		/**
		 * The meta object literal for the '<em><b>Distance Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__DISTANCE_METERS = eINSTANCE.getSourceBinding_DistanceMeters();

		/**
		 * The meta object literal for the '<em><b>Elevation Delta Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__ELEVATION_DELTA_METERS = eINSTANCE.getSourceBinding_ElevationDeltaMeters();

		/**
		 * The meta object literal for the '<em><b>Resolved At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_BINDING__RESOLVED_AT = eINSTANCE.getSourceBinding_ResolvedAt();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.StationBindingImpl <em>Station Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.StationBindingImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStationBinding()
		 * @generated
		 */
		EClass STATION_BINDING = eINSTANCE.getStationBinding();

		/**
		 * The meta object literal for the '<em><b>Station</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATION_BINDING__STATION = eINSTANCE.getStationBinding_Station();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.GridBindingImpl <em>Grid Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.GridBindingImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGridBinding()
		 * @generated
		 */
		EClass GRID_BINDING = eINSTANCE.getGridBinding();

		/**
		 * The meta object literal for the '<em><b>Cell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID_BINDING__CELL = eINSTANCE.getGridBinding_Cell();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.StationImpl <em>Station</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.StationImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStation()
		 * @generated
		 */
		EClass STATION = eINSTANCE.getStation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__ID = eINSTANCE.getStation_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__NAME = eINSTANCE.getStation_Name();

		/**
		 * The meta object literal for the '<em><b>Icao Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__ICAO_CODE = eINSTANCE.getStation_IcaoCode();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__COUNTRY = eINSTANCE.getStation_Country();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATION__POSITION = eINSTANCE.getStation_Position();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.StationCatalogImpl <em>Station Catalog</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.StationCatalogImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStationCatalog()
		 * @generated
		 */
		EClass STATION_CATALOG = eINSTANCE.getStationCatalog();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION_CATALOG__PROVIDER_ID = eINSTANCE.getStationCatalog_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Product Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION_CATALOG__PRODUCT_ID = eINSTANCE.getStationCatalog_ProductId();

		/**
		 * The meta object literal for the '<em><b>Retrieved At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION_CATALOG__RETRIEVED_AT = eINSTANCE.getStationCatalog_RetrievedAt();

		/**
		 * The meta object literal for the '<em><b>Stations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATION_CATALOG__STATIONS = eINSTANCE.getStationCatalog_Stations();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.GridCellImpl <em>Grid Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.GridCellImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGridCell()
		 * @generated
		 */
		EClass GRID_CELL = eINSTANCE.getGridCell();

		/**
		 * The meta object literal for the '<em><b>Grid Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_CELL__GRID_ID = eINSTANCE.getGridCell_GridId();

		/**
		 * The meta object literal for the '<em><b>I</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_CELL__I = eINSTANCE.getGridCell_I();

		/**
		 * The meta object literal for the '<em><b>J</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_CELL__J = eINSTANCE.getGridCell_J();

		/**
		 * The meta object literal for the '<em><b>Center</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID_CELL__CENTER = eINSTANCE.getGridCell_Center();

		/**
		 * The meta object literal for the '<em><b>Resolution Degrees</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_CELL__RESOLUTION_DEGREES = eINSTANCE.getGridCell_ResolutionDegrees();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl <em>Measured Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.MeasuredValueImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasuredValue()
		 * @generated
		 */
		EClass MEASURED_VALUE = eINSTANCE.getMeasuredValue();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__KIND = eINSTANCE.getMeasuredValue_Kind();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__LEVEL = eINSTANCE.getMeasuredValue_Level();

		/**
		 * The meta object literal for the '<em><b>Statistic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__STATISTIC = eINSTANCE.getMeasuredValue_Statistic();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__PERIOD = eINSTANCE.getMeasuredValue_Period();

		/**
		 * The meta object literal for the '<em><b>Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__THRESHOLD = eINSTANCE.getMeasuredValue_Threshold();

		/**
		 * The meta object literal for the '<em><b>Threshold Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__THRESHOLD_UNIT = eINSTANCE.getMeasuredValue_ThresholdUnit();

		/**
		 * The meta object literal for the '<em><b>Valid At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__VALID_AT = eINSTANCE.getMeasuredValue_ValidAt();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__VALUE = eINSTANCE.getMeasuredValue_Value();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__CODE = eINSTANCE.getMeasuredValue_Code();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_VALUE__UNIT = eINSTANCE.getMeasuredValue_Unit();

		/**
		 * The meta object literal for the '<em><b>Provenance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURED_VALUE__PROVENANCE = eINSTANCE.getMeasuredValue_Provenance();

		/**
		 * The meta object literal for the '<em><b>Uncertainty</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURED_VALUE__UNCERTAINTY = eINSTANCE.getMeasuredValue_Uncertainty();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.ProvenanceImpl <em>Provenance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.ProvenanceImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getProvenance()
		 * @generated
		 */
		EClass PROVENANCE = eINSTANCE.getProvenance();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__PROVIDER_ID = eINSTANCE.getProvenance_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Product Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__PRODUCT_ID = eINSTANCE.getProvenance_ProductId();

		/**
		 * The meta object literal for the '<em><b>Source Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__SOURCE_ELEMENT = eINSTANCE.getProvenance_SourceElement();

		/**
		 * The meta object literal for the '<em><b>Model Run</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__MODEL_RUN = eINSTANCE.getProvenance_ModelRun();

		/**
		 * The meta object literal for the '<em><b>Issued At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__ISSUED_AT = eINSTANCE.getProvenance_IssuedAt();

		/**
		 * The meta object literal for the '<em><b>Retrieved At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__RETRIEVED_AT = eINSTANCE.getProvenance_RetrievedAt();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__ORIGIN = eINSTANCE.getProvenance_Origin();

		/**
		 * The meta object literal for the '<em><b>Station Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__STATION_ID = eINSTANCE.getProvenance_StationId();

		/**
		 * The meta object literal for the '<em><b>Cell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVENANCE__CELL = eINSTANCE.getProvenance_Cell();

		/**
		 * The meta object literal for the '<em><b>Distance Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__DISTANCE_METERS = eINSTANCE.getProvenance_DistanceMeters();

		/**
		 * The meta object literal for the '<em><b>Licence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__LICENCE = eINSTANCE.getProvenance_Licence();

		/**
		 * The meta object literal for the '<em><b>Attribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVENANCE__ATTRIBUTION = eINSTANCE.getProvenance_Attribution();

		/**
		 * The meta object literal for the '<em><b>Derivation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVENANCE__DERIVATION = eINSTANCE.getProvenance_Derivation();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.DerivationImpl <em>Derivation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.DerivationImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDerivation()
		 * @generated
		 */
		EClass DERIVATION = eINSTANCE.getDerivation();

		/**
		 * The meta object literal for the '<em><b>Function Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DERIVATION__FUNCTION_ID = eINSTANCE.getDerivation_FunctionId();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DERIVATION__INPUTS = eINSTANCE.getDerivation_Inputs();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.UncertaintyImpl <em>Uncertainty</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.UncertaintyImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getUncertainty()
		 * @generated
		 */
		EClass UNCERTAINTY = eINSTANCE.getUncertainty();

		/**
		 * The meta object literal for the '<em><b>Quality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__QUALITY = eINSTANCE.getUncertainty_Quality();

		/**
		 * The meta object literal for the '<em><b>Spatial Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__SPATIAL_METERS = eINSTANCE.getUncertainty_SpatialMeters();

		/**
		 * The meta object literal for the '<em><b>Temporal Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__TEMPORAL_OFFSET = eINSTANCE.getUncertainty_TemporalOffset();

		/**
		 * The meta object literal for the '<em><b>Lead Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__LEAD_TIME = eINSTANCE.getUncertainty_LeadTime();

		/**
		 * The meta object literal for the '<em><b>Stale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__STALE = eINSTANCE.getUncertainty_Stale();

		/**
		 * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNCERTAINTY__NOTE = eINSTANCE.getUncertainty_Note();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.WeatherReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.WeatherReportImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getWeatherReport()
		 * @generated
		 */
		EClass WEATHER_REPORT = eINSTANCE.getWeatherReport();

		/**
		 * The meta object literal for the '<em><b>Site Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_REPORT__SITE_ID = eINSTANCE.getWeatherReport_SiteId();

		/**
		 * The meta object literal for the '<em><b>Generated At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_REPORT__GENERATED_AT = eINSTANCE.getWeatherReport_GeneratedAt();

		/**
		 * The meta object literal for the '<em><b>Datasets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEATHER_REPORT__DATASETS = eINSTANCE.getWeatherReport_Datasets();

		/**
		 * The meta object literal for the '<em><b>Days</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEATHER_REPORT__DAYS = eINSTANCE.getWeatherReport_Days();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.SourceDatasetImpl <em>Source Dataset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.SourceDatasetImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getSourceDataset()
		 * @generated
		 */
		EClass SOURCE_DATASET = eINSTANCE.getSourceDataset();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__PROVIDER_ID = eINSTANCE.getSourceDataset_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Product Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__PRODUCT_ID = eINSTANCE.getSourceDataset_ProductId();

		/**
		 * The meta object literal for the '<em><b>Issued At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__ISSUED_AT = eINSTANCE.getSourceDataset_IssuedAt();

		/**
		 * The meta object literal for the '<em><b>Model Run</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__MODEL_RUN = eINSTANCE.getSourceDataset_ModelRun();

		/**
		 * The meta object literal for the '<em><b>Retrieved At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__RETRIEVED_AT = eINSTANCE.getSourceDataset_RetrievedAt();

		/**
		 * The meta object literal for the '<em><b>Expected Refresh</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__EXPECTED_REFRESH = eINSTANCE.getSourceDataset_ExpectedRefresh();

		/**
		 * The meta object literal for the '<em><b>Horizon Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__HORIZON_START = eINSTANCE.getSourceDataset_HorizonStart();

		/**
		 * The meta object literal for the '<em><b>Horizon End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__HORIZON_END = eINSTANCE.getSourceDataset_HorizonEnd();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__ORIGIN = eINSTANCE.getSourceDataset_Origin();

		/**
		 * The meta object literal for the '<em><b>Station Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__STATION_ID = eINSTANCE.getSourceDataset_StationId();

		/**
		 * The meta object literal for the '<em><b>Cell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_DATASET__CELL = eINSTANCE.getSourceDataset_Cell();

		/**
		 * The meta object literal for the '<em><b>Distance Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__DISTANCE_METERS = eINSTANCE.getSourceDataset_DistanceMeters();

		/**
		 * The meta object literal for the '<em><b>Licence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__LICENCE = eINSTANCE.getSourceDataset_Licence();

		/**
		 * The meta object literal for the '<em><b>Attribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_DATASET__ATTRIBUTION = eINSTANCE.getSourceDataset_Attribution();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_DATASET__VALUES = eINSTANCE.getSourceDataset_Values();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.DayInfoImpl <em>Day Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.DayInfoImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDayInfo()
		 * @generated
		 */
		EClass DAY_INFO = eINSTANCE.getDayInfo();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__DATE = eINSTANCE.getDayInfo_Date();

		/**
		 * The meta object literal for the '<em><b>Sunrise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__SUNRISE = eINSTANCE.getDayInfo_Sunrise();

		/**
		 * The meta object literal for the '<em><b>Sunset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__SUNSET = eINSTANCE.getDayInfo_Sunset();

		/**
		 * The meta object literal for the '<em><b>Civil Dawn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__CIVIL_DAWN = eINSTANCE.getDayInfo_CivilDawn();

		/**
		 * The meta object literal for the '<em><b>Civil Dusk</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__CIVIL_DUSK = eINSTANCE.getDayInfo_CivilDusk();

		/**
		 * The meta object literal for the '<em><b>Nautical Dawn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__NAUTICAL_DAWN = eINSTANCE.getDayInfo_NauticalDawn();

		/**
		 * The meta object literal for the '<em><b>Nautical Dusk</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__NAUTICAL_DUSK = eINSTANCE.getDayInfo_NauticalDusk();

		/**
		 * The meta object literal for the '<em><b>Solar Noon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__SOLAR_NOON = eINSTANCE.getDayInfo_SolarNoon();

		/**
		 * The meta object literal for the '<em><b>Day Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__DAY_LENGTH = eINSTANCE.getDayInfo_DayLength();

		/**
		 * The meta object literal for the '<em><b>Max Sun Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_INFO__MAX_SUN_ELEVATION = eINSTANCE.getDayInfo_MaxSunElevation();

		/**
		 * The meta object literal for the '<em><b>Provenance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DAY_INFO__PROVENANCE = eINSTANCE.getDayInfo_Provenance();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.BindingOrigin <em>Binding Origin</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.BindingOrigin
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBindingOrigin()
		 * @generated
		 */
		EEnum BINDING_ORIGIN = eINSTANCE.getBindingOrigin();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.MeasurementKind <em>Measurement Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.MeasurementKind
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurementKind()
		 * @generated
		 */
		EEnum MEASUREMENT_KIND = eINSTANCE.getMeasurementKind();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.Level <em>Level</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.Level
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getLevel()
		 * @generated
		 */
		EEnum LEVEL = eINSTANCE.getLevel();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.Statistic <em>Statistic</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.Statistic
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStatistic()
		 * @generated
		 */
		EEnum STATISTIC = eINSTANCE.getStatistic();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.Origin <em>Origin</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.Origin
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getOrigin()
		 * @generated
		 */
		EEnum ORIGIN = eINSTANCE.getOrigin();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.Quality <em>Quality</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.Quality
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getQuality()
		 * @generated
		 */
		EEnum QUALITY = eINSTANCE.getQuality();

		/**
		 * The meta object literal for the '<em>Instant</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.time.Instant
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getInstant()
		 * @generated
		 */
		EDataType INSTANT = eINSTANCE.getInstant();

		/**
		 * The meta object literal for the '<em>Duration</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.time.Duration
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDuration()
		 * @generated
		 */
		EDataType DURATION = eINSTANCE.getDuration();

		/**
		 * The meta object literal for the '<em>Local Date</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.time.LocalDate
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getLocalDate()
		 * @generated
		 */
		EDataType LOCAL_DATE = eINSTANCE.getLocalDate();

	}

} //WeatherPackage
