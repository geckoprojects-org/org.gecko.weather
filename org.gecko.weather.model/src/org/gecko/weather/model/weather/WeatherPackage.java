/*
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
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.gecko.emf.osgi.annotation.provide.EPackage;

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
 * @see org.gecko.weather.model.weather.WeatherFactory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = WeatherPackage.eNS_URI, genModel = "/model/dwd-weather.genmodel", genModelSourceLocations = {"model/dwd-weather.genmodel","org.gecko.weather.model/model/dwd-weather.genmodel"}, ecore="/model/dwd-weather.ecore", ecoreSourceLocations="/model/dwd-weather.ecore")
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
	String eNS_URI = "http://cdc.dwd.de/common/weather";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dwdweather";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WeatherPackage eINSTANCE = org.gecko.weather.model.weather.impl.WeatherPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.StationImpl <em>Station</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.StationImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStation()
	 * @generated
	 */
	int STATION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION__COUNTRY = 2;

	/**
	 * The number of structural features of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.WeatherReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.WeatherReportImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getWeatherReport()
	 * @generated
	 */
	int WEATHER_REPORT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__ID = 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__TIMESTAMP = 1;

	/**
	 * The feature id for the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__STATION = 2;

	/**
	 * The feature id for the '<em><b>Astrotime</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__ASTROTIME = 3;

	/**
	 * The feature id for the '<em><b>Weather Station</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT__WEATHER_STATION = 4;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.MeasurementImpl <em>Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.MeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurement()
	 * @generated
	 */
	int MEASUREMENT = 2;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__UNIT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__TIMESTAMP = 2;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__RAW_VALUE = 3;

	/**
	 * The number of structural features of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.IntMeasurementImpl <em>Int Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.IntMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getIntMeasurement()
	 * @generated
	 */
	int INT_MEASUREMENT = 3;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT__UNIT = MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT__NAME = MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT__TIMESTAMP = MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT__RAW_VALUE = MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Int Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MEASUREMENT_OPERATION_COUNT = MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.FloatMeasurementImpl <em>Float Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.FloatMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getFloatMeasurement()
	 * @generated
	 */
	int FLOAT_MEASUREMENT = 4;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT__UNIT = MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT__NAME = MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT__TIMESTAMP = MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT__RAW_VALUE = MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_MEASUREMENT_OPERATION_COUNT = MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.DoubleMeasurementImpl <em>Double Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.DoubleMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDoubleMeasurement()
	 * @generated
	 */
	int DOUBLE_MEASUREMENT = 5;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT__UNIT = MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT__NAME = MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT__TIMESTAMP = MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT__RAW_VALUE = MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MEASUREMENT_OPERATION_COUNT = MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.BasicMeasurementImpl <em>Basic Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.BasicMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBasicMeasurement()
	 * @generated
	 */
	int BASIC_MEASUREMENT = 6;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT__UNIT = MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT__NAME = MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT__TIMESTAMP = MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT__RAW_VALUE = MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Basic Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Basic Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_MEASUREMENT_OPERATION_COUNT = MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.BoolMeasurementImpl <em>Bool Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.BoolMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBoolMeasurement()
	 * @generated
	 */
	int BOOL_MEASUREMENT = 7;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT__UNIT = MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT__NAME = MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT__TIMESTAMP = MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT__RAW_VALUE = MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bool Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Bool Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOL_MEASUREMENT_OPERATION_COUNT = MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.GeoAreaImpl <em>Geo Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.GeoAreaImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGeoArea()
	 * @generated
	 */
	int GEO_AREA = 8;

	/**
	 * The feature id for the '<em><b>Top Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_AREA__TOP_LEFT = 0;

	/**
	 * The feature id for the '<em><b>XDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_AREA__XDIM = 1;

	/**
	 * The feature id for the '<em><b>YDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_AREA__YDIM = 2;

	/**
	 * The number of structural features of the '<em>Geo Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_AREA_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Geo Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_AREA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.GeoPositionImpl <em>Geo Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.GeoPositionImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGeoPosition()
	 * @generated
	 */
	int GEO_POSITION = 9;

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
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.UVRadiationMeasurementImpl <em>UV Radiation Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.UVRadiationMeasurementImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getUVRadiationMeasurement()
	 * @generated
	 */
	int UV_RADIATION_MEASUREMENT = 10;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__UNIT = FLOAT_MEASUREMENT__UNIT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__NAME = FLOAT_MEASUREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__TIMESTAMP = FLOAT_MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__RAW_VALUE = FLOAT_MEASUREMENT__RAW_VALUE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__VALUE = FLOAT_MEASUREMENT__VALUE;

	/**
	 * The feature id for the '<em><b>Area</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT__AREA = FLOAT_MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>UV Radiation Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT_FEATURE_COUNT = FLOAT_MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>UV Radiation Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UV_RADIATION_MEASUREMENT_OPERATION_COUNT = FLOAT_MEASUREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl <em>MOSMIXS Weather Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMOSMIXSWeatherReport()
	 * @generated
	 */
	int MOSMIXS_WEATHER_REPORT = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__ID = WEATHER_REPORT__ID;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TIMESTAMP = WEATHER_REPORT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__STATION = WEATHER_REPORT__STATION;

	/**
	 * The feature id for the '<em><b>Astrotime</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__ASTROTIME = WEATHER_REPORT__ASTROTIME;

	/**
	 * The feature id for the '<em><b>Weather Station</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WEATHER_STATION = WEATHER_REPORT__WEATHER_STATION;

	/**
	 * The feature id for the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_DIRECTION = WEATHER_REPORT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Wind Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_SPEED = WEATHER_REPORT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Wind Gust Last Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR = WEATHER_REPORT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Wind Gust Last Three Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS = WEATHER_REPORT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Wind Gust Max Last12 Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS = WEATHER_REPORT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Wind Gust Prob25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25 = WEATHER_REPORT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Wind Gust Prob40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40 = WEATHER_REPORT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Wind Gust Prob55</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55 = WEATHER_REPORT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Cloud Cover Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL = WEATHER_REPORT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Cloud Cover Below500</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500 = WEATHER_REPORT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Cloud Cover Effective</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE = WEATHER_REPORT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Cloud Cover High</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH = WEATHER_REPORT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Cloud Cover Mid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID = WEATHER_REPORT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Cloud Cover Low</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW = WEATHER_REPORT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Ir Radiance Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL = WEATHER_REPORT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Surface Pressure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE = WEATHER_REPORT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Precipitation Larger02 Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6 = WEATHER_REPORT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Precipitation Larger50 Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6 = WEATHER_REPORT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Precipitation Larger02 Last Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY = WEATHER_REPORT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Precipitation Larger50 Last Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY = WEATHER_REPORT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Precipitation Larger00 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Precipitation Larger02 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Precipitation Larger10 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Precipitation Larger50 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Precipitation Significant Weather Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL = WEATHER_REPORT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Precipitation Significant Weather Last3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3 = WEATHER_REPORT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Snow Rain Eq Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1 = WEATHER_REPORT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Snow Rain Eq Last3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3 = WEATHER_REPORT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Sunshine Duration Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1 = WEATHER_REPORT_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>Temp Above Surface5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5 = WEATHER_REPORT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Temp Above Surface200</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200 = WEATHER_REPORT_FEATURE_COUNT + 30;

	/**
	 * The feature id for the '<em><b>Temp Dewpoint Above Surface200</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200 = WEATHER_REPORT_FEATURE_COUNT + 31;

	/**
	 * The feature id for the '<em><b>Temp Min Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 32;

	/**
	 * The feature id for the '<em><b>Temp Max Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 33;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__VISIBILITY = WEATHER_REPORT_FEATURE_COUNT + 34;

	/**
	 * The feature id for the '<em><b>Past Weather</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__PAST_WEATHER = WEATHER_REPORT_FEATURE_COUNT + 35;

	/**
	 * The feature id for the '<em><b>Significant Weather</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER = WEATHER_REPORT_FEATURE_COUNT + 36;

	/**
	 * The feature id for the '<em><b>Fog Prop Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1 = WEATHER_REPORT_FEATURE_COUNT + 37;

	/**
	 * The feature id for the '<em><b>Fog Prop Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6 = WEATHER_REPORT_FEATURE_COUNT + 38;

	/**
	 * The feature id for the '<em><b>Fog Prop Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12 = WEATHER_REPORT_FEATURE_COUNT + 39;

	/**
	 * The number of structural features of the '<em>MOSMIXS Weather Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT_FEATURE_COUNT = WEATHER_REPORT_FEATURE_COUNT + 40;

	/**
	 * The number of operations of the '<em>MOSMIXS Weather Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOSMIXS_WEATHER_REPORT_OPERATION_COUNT = WEATHER_REPORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.MeasurementWeatherReportImpl <em>Measurement Weather Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.MeasurementWeatherReportImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurementWeatherReport()
	 * @generated
	 */
	int MEASUREMENT_WEATHER_REPORT = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__ID = WEATHER_REPORT__ID;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__TIMESTAMP = WEATHER_REPORT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__STATION = WEATHER_REPORT__STATION;

	/**
	 * The feature id for the '<em><b>Astrotime</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__ASTROTIME = WEATHER_REPORT__ASTROTIME;

	/**
	 * The feature id for the '<em><b>Weather Station</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__WEATHER_STATION = WEATHER_REPORT__WEATHER_STATION;

	/**
	 * The feature id for the '<em><b>Measurements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT__MEASUREMENTS = WEATHER_REPORT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Measurement Weather Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT_FEATURE_COUNT = WEATHER_REPORT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Measurement Weather Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_WEATHER_REPORT_OPERATION_COUNT = WEATHER_REPORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.AstrotimeImpl <em>Astrotime</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.AstrotimeImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getAstrotime()
	 * @generated
	 */
	int ASTROTIME = 13;

	/**
	 * The feature id for the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME__SUNSET = 0;

	/**
	 * The feature id for the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME__SUNRISE = 1;

	/**
	 * The feature id for the '<em><b>Sunset Twilight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME__SUNSET_TWILIGHT = 2;

	/**
	 * The feature id for the '<em><b>Sunrise Twilight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME__SUNRISE_TWILIGHT = 3;

	/**
	 * The number of structural features of the '<em>Astrotime</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Astrotime</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASTROTIME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.model.weather.impl.WeatherStationImpl <em>Station</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.model.weather.impl.WeatherStationImpl
	 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getWeatherStation()
	 * @generated
	 */
	int WEATHER_STATION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION__NAME = STATION__NAME;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION__LOCATION = STATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION__COUNTRY = STATION__COUNTRY;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION__ID = STATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Icao Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION__ICAO_CODE = STATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION_FEATURE_COUNT = STATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Station</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEATHER_STATION_OPERATION_COUNT = STATION_OPERATION_COUNT + 0;


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
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.Station#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Location</em>'.
	 * @see org.gecko.weather.model.weather.Station#getLocation()
	 * @see #getStation()
	 * @generated
	 */
	EReference getStation_Location();

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
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.WeatherReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport
	 * @generated
	 */
	EClass getWeatherReport();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherReport#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getId()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EAttribute getWeatherReport_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherReport#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getTimestamp()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EAttribute getWeatherReport_Timestamp();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.WeatherReport#getStation <em>Station</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Station</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getStation()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EReference getWeatherReport_Station();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.WeatherReport#getAstrotime <em>Astrotime</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Astrotime</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getAstrotime()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EReference getWeatherReport_Astrotime();

	/**
	 * Returns the meta object for the reference '{@link org.gecko.weather.model.weather.WeatherReport#getWeatherStation <em>Weather Station</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Weather Station</em>'.
	 * @see org.gecko.weather.model.weather.WeatherReport#getWeatherStation()
	 * @see #getWeatherReport()
	 * @generated
	 */
	EReference getWeatherReport_WeatherStation();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement</em>'.
	 * @see org.gecko.weather.model.weather.Measurement
	 * @generated
	 */
	EClass getMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Measurement#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see org.gecko.weather.model.weather.Measurement#getUnit()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Unit();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Measurement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.model.weather.Measurement#getName()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Measurement#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.gecko.weather.model.weather.Measurement#getTimestamp()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Measurement#getRawValue <em>Raw Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Raw Value</em>'.
	 * @see org.gecko.weather.model.weather.Measurement#getRawValue()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_RawValue();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.IntMeasurement <em>Int Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Measurement</em>'.
	 * @see org.gecko.weather.model.weather.IntMeasurement
	 * @generated
	 */
	EClass getIntMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.IntMeasurement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.IntMeasurement#getValue()
	 * @see #getIntMeasurement()
	 * @generated
	 */
	EAttribute getIntMeasurement_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.FloatMeasurement <em>Float Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Measurement</em>'.
	 * @see org.gecko.weather.model.weather.FloatMeasurement
	 * @generated
	 */
	EClass getFloatMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.FloatMeasurement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.FloatMeasurement#getValue()
	 * @see #getFloatMeasurement()
	 * @generated
	 */
	EAttribute getFloatMeasurement_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.DoubleMeasurement <em>Double Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Measurement</em>'.
	 * @see org.gecko.weather.model.weather.DoubleMeasurement
	 * @generated
	 */
	EClass getDoubleMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.DoubleMeasurement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.DoubleMeasurement#getValue()
	 * @see #getDoubleMeasurement()
	 * @generated
	 */
	EAttribute getDoubleMeasurement_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.BasicMeasurement <em>Basic Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Measurement</em>'.
	 * @see org.gecko.weather.model.weather.BasicMeasurement
	 * @generated
	 */
	EClass getBasicMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.BasicMeasurement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.BasicMeasurement#getValue()
	 * @see #getBasicMeasurement()
	 * @generated
	 */
	EAttribute getBasicMeasurement_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.BoolMeasurement <em>Bool Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bool Measurement</em>'.
	 * @see org.gecko.weather.model.weather.BoolMeasurement
	 * @generated
	 */
	EClass getBoolMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.BoolMeasurement#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.gecko.weather.model.weather.BoolMeasurement#isValue()
	 * @see #getBoolMeasurement()
	 * @generated
	 */
	EAttribute getBoolMeasurement_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.GeoArea <em>Geo Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geo Area</em>'.
	 * @see org.gecko.weather.model.weather.GeoArea
	 * @generated
	 */
	EClass getGeoArea();

	/**
	 * Returns the meta object for the reference '{@link org.gecko.weather.model.weather.GeoArea#getTopLeft <em>Top Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Top Left</em>'.
	 * @see org.gecko.weather.model.weather.GeoArea#getTopLeft()
	 * @see #getGeoArea()
	 * @generated
	 */
	EReference getGeoArea_TopLeft();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GeoArea#getXDim <em>XDim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XDim</em>'.
	 * @see org.gecko.weather.model.weather.GeoArea#getXDim()
	 * @see #getGeoArea()
	 * @generated
	 */
	EAttribute getGeoArea_XDim();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.GeoArea#getYDim <em>YDim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YDim</em>'.
	 * @see org.gecko.weather.model.weather.GeoArea#getYDim()
	 * @see #getGeoArea()
	 * @generated
	 */
	EAttribute getGeoArea_YDim();

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
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.UVRadiationMeasurement <em>UV Radiation Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UV Radiation Measurement</em>'.
	 * @see org.gecko.weather.model.weather.UVRadiationMeasurement
	 * @generated
	 */
	EClass getUVRadiationMeasurement();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.model.weather.UVRadiationMeasurement#getArea <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Area</em>'.
	 * @see org.gecko.weather.model.weather.UVRadiationMeasurement#getArea()
	 * @see #getUVRadiationMeasurement()
	 * @generated
	 */
	EReference getUVRadiationMeasurement_Area();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport <em>MOSMIXS Weather Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MOSMIXS Weather Report</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport
	 * @generated
	 */
	EClass getMOSMIXSWeatherReport();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindDirection <em>Wind Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Direction</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindDirection()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindDirection();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindSpeed <em>Wind Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Speed</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindSpeed()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindSpeed();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastHour <em>Wind Gust Last Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Last Hour</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastHour()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustLastHour();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastThreeHours <em>Wind Gust Last Three Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Last Three Hours</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastThreeHours()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustLastThreeHours();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustMaxLast12Hours <em>Wind Gust Max Last12 Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Max Last12 Hours</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustMaxLast12Hours()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustMaxLast12Hours();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb25 <em>Wind Gust Prob25</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Prob25</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb25()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustProb25();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb40 <em>Wind Gust Prob40</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Prob40</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb40()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustProb40();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb55 <em>Wind Gust Prob55</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Prob55</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb55()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_WindGustProb55();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverTotal <em>Cloud Cover Total</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Total</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverTotal()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverTotal();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverBelow500 <em>Cloud Cover Below500</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Below500</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverBelow500()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverBelow500();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverEffective <em>Cloud Cover Effective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Effective</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverEffective()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverEffective();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverHigh <em>Cloud Cover High</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover High</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverHigh()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverHigh();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverMid <em>Cloud Cover Mid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Mid</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverMid()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverMid();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverLow <em>Cloud Cover Low</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Low</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverLow()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_CloudCoverLow();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getIrRadianceGlobal <em>Ir Radiance Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ir Radiance Global</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getIrRadianceGlobal()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_IrRadianceGlobal();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSurfacePressure <em>Surface Pressure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Surface Pressure</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSurfacePressure()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_SurfacePressure();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last6 <em>Precipitation Larger02 Last6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger02 Last6</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last6()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02Last6();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last6 <em>Precipitation Larger50 Last6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger50 Last6</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last6()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50Last6();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02LastDay <em>Precipitation Larger02 Last Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger02 Last Day</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02LastDay()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02LastDay();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50LastDay <em>Precipitation Larger50 Last Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger50 Last Day</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50LastDay()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50LastDay();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger00Last12 <em>Precipitation Larger00 Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger00 Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger00Last12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger00Last12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last12 <em>Precipitation Larger02 Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger02 Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02Last12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger10Last12 <em>Precipitation Larger10 Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger10 Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger10Last12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger10Last12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last12 <em>Precipitation Larger50 Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Larger50 Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50Last12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherTotal <em>Precipitation Significant Weather Total</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Significant Weather Total</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherTotal()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherLast3 <em>Precipitation Significant Weather Last3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Significant Weather Last3</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherLast3()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast1 <em>Snow Rain Eq Last1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snow Rain Eq Last1</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast1()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_SnowRainEqLast1();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast3 <em>Snow Rain Eq Last3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snow Rain Eq Last3</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast3()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_SnowRainEqLast3();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSunshineDurationLast1 <em>Sunshine Duration Last1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunshine Duration Last1</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSunshineDurationLast1()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_SunshineDurationLast1();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface5 <em>Temp Above Surface5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp Above Surface5</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface5()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_TempAboveSurface5();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface200 <em>Temp Above Surface200</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp Above Surface200</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface200()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_TempAboveSurface200();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempDewpointAboveSurface200 <em>Temp Dewpoint Above Surface200</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp Dewpoint Above Surface200</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempDewpointAboveSurface200()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_TempDewpointAboveSurface200();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMinLast12 <em>Temp Min Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp Min Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMinLast12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_TempMinLast12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMaxLast12 <em>Temp Max Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp Max Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMaxLast12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_TempMaxLast12();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getVisibility()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_Visibility();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPastWeather <em>Past Weather</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Past Weather</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPastWeather()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_PastWeather();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSignificantWeather <em>Significant Weather</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Significant Weather</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSignificantWeather()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_SignificantWeather();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast1 <em>Fog Prop Last1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fog Prop Last1</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast1()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_FogPropLast1();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast6 <em>Fog Prop Last6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fog Prop Last6</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast6()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_FogPropLast6();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast12 <em>Fog Prop Last12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fog Prop Last12</em>'.
	 * @see org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast12()
	 * @see #getMOSMIXSWeatherReport()
	 * @generated
	 */
	EAttribute getMOSMIXSWeatherReport_FogPropLast12();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.MeasurementWeatherReport <em>Measurement Weather Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement Weather Report</em>'.
	 * @see org.gecko.weather.model.weather.MeasurementWeatherReport
	 * @generated
	 */
	EClass getMeasurementWeatherReport();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.model.weather.MeasurementWeatherReport#getMeasurements <em>Measurements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurements</em>'.
	 * @see org.gecko.weather.model.weather.MeasurementWeatherReport#getMeasurements()
	 * @see #getMeasurementWeatherReport()
	 * @generated
	 */
	EReference getMeasurementWeatherReport_Measurements();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.Astrotime <em>Astrotime</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Astrotime</em>'.
	 * @see org.gecko.weather.model.weather.Astrotime
	 * @generated
	 */
	EClass getAstrotime();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Astrotime#getSunset <em>Sunset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunset</em>'.
	 * @see org.gecko.weather.model.weather.Astrotime#getSunset()
	 * @see #getAstrotime()
	 * @generated
	 */
	EAttribute getAstrotime_Sunset();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Astrotime#getSunrise <em>Sunrise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunrise</em>'.
	 * @see org.gecko.weather.model.weather.Astrotime#getSunrise()
	 * @see #getAstrotime()
	 * @generated
	 */
	EAttribute getAstrotime_Sunrise();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Astrotime#getSunsetTwilight <em>Sunset Twilight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunset Twilight</em>'.
	 * @see org.gecko.weather.model.weather.Astrotime#getSunsetTwilight()
	 * @see #getAstrotime()
	 * @generated
	 */
	EAttribute getAstrotime_SunsetTwilight();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.Astrotime#getSunriseTwilight <em>Sunrise Twilight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunrise Twilight</em>'.
	 * @see org.gecko.weather.model.weather.Astrotime#getSunriseTwilight()
	 * @see #getAstrotime()
	 * @generated
	 */
	EAttribute getAstrotime_SunriseTwilight();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.model.weather.WeatherStation <em>Station</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Station</em>'.
	 * @see org.gecko.weather.model.weather.WeatherStation
	 * @generated
	 */
	EClass getWeatherStation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherStation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.model.weather.WeatherStation#getId()
	 * @see #getWeatherStation()
	 * @generated
	 */
	EAttribute getWeatherStation_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.model.weather.WeatherStation#getIcaoCode <em>Icao Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icao Code</em>'.
	 * @see org.gecko.weather.model.weather.WeatherStation#getIcaoCode()
	 * @see #getWeatherStation()
	 * @generated
	 */
	EAttribute getWeatherStation_IcaoCode();

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
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.StationImpl <em>Station</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.StationImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getStation()
		 * @generated
		 */
		EClass STATION = eINSTANCE.getStation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__NAME = eINSTANCE.getStation_Name();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATION__LOCATION = eINSTANCE.getStation_Location();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATION__COUNTRY = eINSTANCE.getStation_Country();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_REPORT__ID = eINSTANCE.getWeatherReport_Id();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_REPORT__TIMESTAMP = eINSTANCE.getWeatherReport_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Station</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEATHER_REPORT__STATION = eINSTANCE.getWeatherReport_Station();

		/**
		 * The meta object literal for the '<em><b>Astrotime</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEATHER_REPORT__ASTROTIME = eINSTANCE.getWeatherReport_Astrotime();

		/**
		 * The meta object literal for the '<em><b>Weather Station</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEATHER_REPORT__WEATHER_STATION = eINSTANCE.getWeatherReport_WeatherStation();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.MeasurementImpl <em>Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.MeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurement()
		 * @generated
		 */
		EClass MEASUREMENT = eINSTANCE.getMeasurement();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__UNIT = eINSTANCE.getMeasurement_Unit();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__NAME = eINSTANCE.getMeasurement_Name();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__TIMESTAMP = eINSTANCE.getMeasurement_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Raw Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__RAW_VALUE = eINSTANCE.getMeasurement_RawValue();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.IntMeasurementImpl <em>Int Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.IntMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getIntMeasurement()
		 * @generated
		 */
		EClass INT_MEASUREMENT = eINSTANCE.getIntMeasurement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_MEASUREMENT__VALUE = eINSTANCE.getIntMeasurement_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.FloatMeasurementImpl <em>Float Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.FloatMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getFloatMeasurement()
		 * @generated
		 */
		EClass FLOAT_MEASUREMENT = eINSTANCE.getFloatMeasurement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_MEASUREMENT__VALUE = eINSTANCE.getFloatMeasurement_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.DoubleMeasurementImpl <em>Double Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.DoubleMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getDoubleMeasurement()
		 * @generated
		 */
		EClass DOUBLE_MEASUREMENT = eINSTANCE.getDoubleMeasurement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_MEASUREMENT__VALUE = eINSTANCE.getDoubleMeasurement_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.BasicMeasurementImpl <em>Basic Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.BasicMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBasicMeasurement()
		 * @generated
		 */
		EClass BASIC_MEASUREMENT = eINSTANCE.getBasicMeasurement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_MEASUREMENT__VALUE = eINSTANCE.getBasicMeasurement_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.BoolMeasurementImpl <em>Bool Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.BoolMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getBoolMeasurement()
		 * @generated
		 */
		EClass BOOL_MEASUREMENT = eINSTANCE.getBoolMeasurement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOL_MEASUREMENT__VALUE = eINSTANCE.getBoolMeasurement_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.GeoAreaImpl <em>Geo Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.GeoAreaImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getGeoArea()
		 * @generated
		 */
		EClass GEO_AREA = eINSTANCE.getGeoArea();

		/**
		 * The meta object literal for the '<em><b>Top Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_AREA__TOP_LEFT = eINSTANCE.getGeoArea_TopLeft();

		/**
		 * The meta object literal for the '<em><b>XDim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_AREA__XDIM = eINSTANCE.getGeoArea_XDim();

		/**
		 * The meta object literal for the '<em><b>YDim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_AREA__YDIM = eINSTANCE.getGeoArea_YDim();

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
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.UVRadiationMeasurementImpl <em>UV Radiation Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.UVRadiationMeasurementImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getUVRadiationMeasurement()
		 * @generated
		 */
		EClass UV_RADIATION_MEASUREMENT = eINSTANCE.getUVRadiationMeasurement();

		/**
		 * The meta object literal for the '<em><b>Area</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UV_RADIATION_MEASUREMENT__AREA = eINSTANCE.getUVRadiationMeasurement_Area();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl <em>MOSMIXS Weather Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMOSMIXSWeatherReport()
		 * @generated
		 */
		EClass MOSMIXS_WEATHER_REPORT = eINSTANCE.getMOSMIXSWeatherReport();

		/**
		 * The meta object literal for the '<em><b>Wind Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_DIRECTION = eINSTANCE.getMOSMIXSWeatherReport_WindDirection();

		/**
		 * The meta object literal for the '<em><b>Wind Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_SPEED = eINSTANCE.getMOSMIXSWeatherReport_WindSpeed();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Last Hour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR = eINSTANCE.getMOSMIXSWeatherReport_WindGustLastHour();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Last Three Hours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS = eINSTANCE.getMOSMIXSWeatherReport_WindGustLastThreeHours();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Max Last12 Hours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS = eINSTANCE.getMOSMIXSWeatherReport_WindGustMaxLast12Hours();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Prob25</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25 = eINSTANCE.getMOSMIXSWeatherReport_WindGustProb25();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Prob40</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40 = eINSTANCE.getMOSMIXSWeatherReport_WindGustProb40();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Prob55</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55 = eINSTANCE.getMOSMIXSWeatherReport_WindGustProb55();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Total</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverTotal();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Below500</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500 = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverBelow500();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Effective</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverEffective();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover High</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverHigh();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Mid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverMid();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Low</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW = eINSTANCE.getMOSMIXSWeatherReport_CloudCoverLow();

		/**
		 * The meta object literal for the '<em><b>Ir Radiance Global</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL = eINSTANCE.getMOSMIXSWeatherReport_IrRadianceGlobal();

		/**
		 * The meta object literal for the '<em><b>Surface Pressure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE = eINSTANCE.getMOSMIXSWeatherReport_SurfacePressure();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger02 Last6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger02Last6();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger50 Last6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger50Last6();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger02 Last Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger02LastDay();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger50 Last Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger50LastDay();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger00 Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger00Last12();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger02 Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger02Last12();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger10 Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger10Last12();

		/**
		 * The meta object literal for the '<em><b>Precipitation Larger50 Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationLarger50Last12();

		/**
		 * The meta object literal for the '<em><b>Precipitation Significant Weather Total</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal();

		/**
		 * The meta object literal for the '<em><b>Precipitation Significant Weather Last3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3 = eINSTANCE.getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3();

		/**
		 * The meta object literal for the '<em><b>Snow Rain Eq Last1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1 = eINSTANCE.getMOSMIXSWeatherReport_SnowRainEqLast1();

		/**
		 * The meta object literal for the '<em><b>Snow Rain Eq Last3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3 = eINSTANCE.getMOSMIXSWeatherReport_SnowRainEqLast3();

		/**
		 * The meta object literal for the '<em><b>Sunshine Duration Last1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1 = eINSTANCE.getMOSMIXSWeatherReport_SunshineDurationLast1();

		/**
		 * The meta object literal for the '<em><b>Temp Above Surface5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5 = eINSTANCE.getMOSMIXSWeatherReport_TempAboveSurface5();

		/**
		 * The meta object literal for the '<em><b>Temp Above Surface200</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200 = eINSTANCE.getMOSMIXSWeatherReport_TempAboveSurface200();

		/**
		 * The meta object literal for the '<em><b>Temp Dewpoint Above Surface200</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200 = eINSTANCE.getMOSMIXSWeatherReport_TempDewpointAboveSurface200();

		/**
		 * The meta object literal for the '<em><b>Temp Min Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_TempMinLast12();

		/**
		 * The meta object literal for the '<em><b>Temp Max Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_TempMaxLast12();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__VISIBILITY = eINSTANCE.getMOSMIXSWeatherReport_Visibility();

		/**
		 * The meta object literal for the '<em><b>Past Weather</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__PAST_WEATHER = eINSTANCE.getMOSMIXSWeatherReport_PastWeather();

		/**
		 * The meta object literal for the '<em><b>Significant Weather</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER = eINSTANCE.getMOSMIXSWeatherReport_SignificantWeather();

		/**
		 * The meta object literal for the '<em><b>Fog Prop Last1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1 = eINSTANCE.getMOSMIXSWeatherReport_FogPropLast1();

		/**
		 * The meta object literal for the '<em><b>Fog Prop Last6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6 = eINSTANCE.getMOSMIXSWeatherReport_FogPropLast6();

		/**
		 * The meta object literal for the '<em><b>Fog Prop Last12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12 = eINSTANCE.getMOSMIXSWeatherReport_FogPropLast12();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.MeasurementWeatherReportImpl <em>Measurement Weather Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.MeasurementWeatherReportImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getMeasurementWeatherReport()
		 * @generated
		 */
		EClass MEASUREMENT_WEATHER_REPORT = eINSTANCE.getMeasurementWeatherReport();

		/**
		 * The meta object literal for the '<em><b>Measurements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_WEATHER_REPORT__MEASUREMENTS = eINSTANCE.getMeasurementWeatherReport_Measurements();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.AstrotimeImpl <em>Astrotime</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.AstrotimeImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getAstrotime()
		 * @generated
		 */
		EClass ASTROTIME = eINSTANCE.getAstrotime();

		/**
		 * The meta object literal for the '<em><b>Sunset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASTROTIME__SUNSET = eINSTANCE.getAstrotime_Sunset();

		/**
		 * The meta object literal for the '<em><b>Sunrise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASTROTIME__SUNRISE = eINSTANCE.getAstrotime_Sunrise();

		/**
		 * The meta object literal for the '<em><b>Sunset Twilight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASTROTIME__SUNSET_TWILIGHT = eINSTANCE.getAstrotime_SunsetTwilight();

		/**
		 * The meta object literal for the '<em><b>Sunrise Twilight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASTROTIME__SUNRISE_TWILIGHT = eINSTANCE.getAstrotime_SunriseTwilight();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.model.weather.impl.WeatherStationImpl <em>Station</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.model.weather.impl.WeatherStationImpl
		 * @see org.gecko.weather.model.weather.impl.WeatherPackageImpl#getWeatherStation()
		 * @generated
		 */
		EClass WEATHER_STATION = eINSTANCE.getWeatherStation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_STATION__ID = eINSTANCE.getWeatherStation_Id();

		/**
		 * The meta object literal for the '<em><b>Icao Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEATHER_STATION__ICAO_CODE = eINSTANCE.getWeatherStation_IcaoCode();

	}

} //WeatherPackage
