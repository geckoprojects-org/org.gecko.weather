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
package org.gecko.weather.pv.model.pv;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * Photovoltaic plants and their expected output — the PV add-on of the weather backend (M-15). A Plant profile says what is installed where and what shades it; a PvOutlook is what the plant is expected to produce, hour by hour and per day, computed from a weather site's report. Angles in degrees: azimuth clockwise from north (south = 180), elevation and tilt from the horizontal. Plain data types only, so that a TypeScript client reads it.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.pv.model.pv.PvFactory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = PvPackage.eNS_URI, fingerprint = "fp1:e0ada57fb21762ef3e30784aabafd4c741b0a802eba0cc8b2923331254478164", genModel = "/model/pv.genmodel", genModelSourceLocations = {"model/pv.genmodel","org.gecko.weather.pv/model/pv.genmodel"}, ecore = "/model/pv.ecore", ecoreSourceLocations = "/model/pv.ecore")
public interface PvPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pv";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/weather/pv/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pv";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PvPackage eINSTANCE = org.gecko.weather.pv.model.pv.impl.PvPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PlantImpl <em>Plant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PlantImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlant()
	 * @generated
	 */
	int PLANT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__SITE_ID = 2;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__LATITUDE = 3;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__LONGITUDE = 4;

	/**
	 * The feature id for the '<em><b>Mounting Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__MOUNTING_HEIGHT = 5;

	/**
	 * The feature id for the '<em><b>Albedo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__ALBEDO = 6;

	/**
	 * The feature id for the '<em><b>System Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__SYSTEM_LOSSES = 7;

	/**
	 * The feature id for the '<em><b>Arrays</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__ARRAYS = 8;

	/**
	 * The feature id for the '<em><b>Inverters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__INVERTERS = 9;

	/**
	 * The feature id for the '<em><b>Obstacles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__OBSTACLES = 10;

	/**
	 * The feature id for the '<em><b>Horizon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT__HORIZON = 11;

	/**
	 * The number of structural features of the '<em>Plant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Plant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PvArrayImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvArray()
	 * @generated
	 */
	int PV_ARRAY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__AZIMUTH = 1;

	/**
	 * The feature id for the '<em><b>Tilt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__TILT = 2;

	/**
	 * The feature id for the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__PEAK_POWER = 3;

	/**
	 * The feature id for the '<em><b>Module Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__MODULE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Temperature Coefficient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__TEMPERATURE_COEFFICIENT = 5;

	/**
	 * The feature id for the '<em><b>Mounting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__MOUNTING = 6;

	/**
	 * The feature id for the '<em><b>Inverter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY__INVERTER = 7;

	/**
	 * The number of structural features of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.InverterImpl <em>Inverter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.InverterImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getInverter()
	 * @generated
	 */
	int INVERTER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Ac Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__AC_POWER = 1;

	/**
	 * The feature id for the '<em><b>Efficiency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__EFFICIENCY = 2;

	/**
	 * The number of structural features of the '<em>Inverter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Inverter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl <em>Obstacle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.ObstacleImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getObstacle()
	 * @generated
	 */
	int OBSTACLE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Azimuth From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE__AZIMUTH_FROM = 1;

	/**
	 * The feature id for the '<em><b>Azimuth To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE__AZIMUTH_TO = 2;

	/**
	 * The feature id for the '<em><b>Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE__DISTANCE = 3;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE__HEIGHT = 4;

	/**
	 * The number of structural features of the '<em>Obstacle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Obstacle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSTACLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.HorizonPointImpl <em>Horizon Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.HorizonPointImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getHorizonPoint()
	 * @generated
	 */
	int HORIZON_POINT = 4;

	/**
	 * The feature id for the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZON_POINT__AZIMUTH = 0;

	/**
	 * The feature id for the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZON_POINT__ELEVATION = 1;

	/**
	 * The number of structural features of the '<em>Horizon Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZON_POINT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Horizon Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZON_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PlantDirectoryImpl <em>Plant Directory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PlantDirectoryImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlantDirectory()
	 * @generated
	 */
	int PLANT_DIRECTORY = 5;

	/**
	 * The feature id for the '<em><b>Plants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_DIRECTORY__PLANTS = 0;

	/**
	 * The number of structural features of the '<em>Plant Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_DIRECTORY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Plant Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_DIRECTORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl <em>Plant Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PlantEntryImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlantEntry()
	 * @generated
	 */
	int PLANT_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY__SITE_ID = 2;

	/**
	 * The feature id for the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY__PEAK_POWER = 3;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY__TIME_ZONE = 4;

	/**
	 * The number of structural features of the '<em>Plant Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Plant Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl <em>Outlook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PvOutlookImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvOutlook()
	 * @generated
	 */
	int PV_OUTLOOK = 7;

	/**
	 * The feature id for the '<em><b>Plant Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__PLANT_ID = 0;

	/**
	 * The feature id for the '<em><b>Plant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__PLANT_NAME = 1;

	/**
	 * The feature id for the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__SITE_ID = 2;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__TIME_ZONE = 3;

	/**
	 * The feature id for the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__PEAK_POWER = 4;

	/**
	 * The feature id for the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__GENERATED_AT = 5;

	/**
	 * The feature id for the '<em><b>Hours</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__HOURS = 6;

	/**
	 * The feature id for the '<em><b>Days</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__DAYS = 7;

	/**
	 * The feature id for the '<em><b>Arrays</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK__ARRAYS = 8;

	/**
	 * The number of structural features of the '<em>Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_OUTLOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl <em>Array Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvArrayInfo()
	 * @generated
	 */
	int PV_ARRAY_INFO = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO__AZIMUTH = 1;

	/**
	 * The feature id for the '<em><b>Tilt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO__TILT = 2;

	/**
	 * The feature id for the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO__PEAK_POWER = 3;

	/**
	 * The number of structural features of the '<em>Array Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Array Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_ARRAY_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl <em>Hour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PvHourImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvHour()
	 * @generated
	 */
	int PV_HOUR = 9;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__TIME = 0;

	/**
	 * The feature id for the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__POWER = 1;

	/**
	 * The feature id for the '<em><b>Dc Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__DC_POWER = 2;

	/**
	 * The feature id for the '<em><b>Array Power</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__ARRAY_POWER = 3;

	/**
	 * The feature id for the '<em><b>Plane Irradiance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__PLANE_IRRADIANCE = 4;

	/**
	 * The feature id for the '<em><b>Global Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__GLOBAL_RADIATION = 5;

	/**
	 * The feature id for the '<em><b>Cell Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__CELL_TEMPERATURE = 6;

	/**
	 * The feature id for the '<em><b>Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__SUN_ELEVATION = 7;

	/**
	 * The feature id for the '<em><b>Sun Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__SUN_AZIMUTH = 8;

	/**
	 * The feature id for the '<em><b>Shaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__SHADED = 9;

	/**
	 * The feature id for the '<em><b>Clipped</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__CLIPPED = 10;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR__SOURCE = 11;

	/**
	 * The number of structural features of the '<em>Hour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Hour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_HOUR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl <em>Day</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.impl.PvDayImpl
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvDay()
	 * @generated
	 */
	int PV_DAY = 10;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__DATE = 0;

	/**
	 * The feature id for the '<em><b>Energy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__ENERGY = 1;

	/**
	 * The feature id for the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__PEAK_POWER = 2;

	/**
	 * The feature id for the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__PEAK_TIME = 3;

	/**
	 * The feature id for the '<em><b>Specific Yield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__SPECIFIC_YIELD = 4;

	/**
	 * The feature id for the '<em><b>Hours Covered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__HOURS_COVERED = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY__SOURCE = 6;

	/**
	 * The number of structural features of the '<em>Day</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Day</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PV_DAY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.model.pv.Mounting <em>Mounting</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.model.pv.Mounting
	 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getMounting()
	 * @generated
	 */
	int MOUNTING = 11;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.Plant <em>Plant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plant</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant
	 * @generated
	 */
	EClass getPlant();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getId()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getName()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getSiteId <em>Site Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getSiteId()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_SiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getLatitude()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_Latitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getLongitude()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_Longitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getMountingHeight <em>Mounting Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mounting Height</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getMountingHeight()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_MountingHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getAlbedo <em>Albedo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Albedo</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getAlbedo()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_Albedo();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Plant#getSystemLosses <em>System Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Losses</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getSystemLosses()
	 * @see #getPlant()
	 * @generated
	 */
	EAttribute getPlant_SystemLosses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.Plant#getArrays <em>Arrays</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arrays</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getArrays()
	 * @see #getPlant()
	 * @generated
	 */
	EReference getPlant_Arrays();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.Plant#getInverters <em>Inverters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inverters</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getInverters()
	 * @see #getPlant()
	 * @generated
	 */
	EReference getPlant_Inverters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.Plant#getObstacles <em>Obstacles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Obstacles</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getObstacles()
	 * @see #getPlant()
	 * @generated
	 */
	EReference getPlant_Obstacles();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.Plant#getHorizon <em>Horizon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Horizon</em>'.
	 * @see org.gecko.weather.pv.model.pv.Plant#getHorizon()
	 * @see #getPlant()
	 * @generated
	 */
	EReference getPlant_Horizon();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PvArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray
	 * @generated
	 */
	EClass getPvArray();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getName()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getAzimuth <em>Azimuth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getAzimuth()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_Azimuth();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getTilt <em>Tilt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tilt</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getTilt()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_Tilt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getPeakPower <em>Peak Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getPeakPower()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_PeakPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getModuleCount <em>Module Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Count</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getModuleCount()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_ModuleCount();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getTemperatureCoefficient <em>Temperature Coefficient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature Coefficient</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getTemperatureCoefficient()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_TemperatureCoefficient();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArray#getMounting <em>Mounting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mounting</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getMounting()
	 * @see #getPvArray()
	 * @generated
	 */
	EAttribute getPvArray_Mounting();

	/**
	 * Returns the meta object for the reference '{@link org.gecko.weather.pv.model.pv.PvArray#getInverter <em>Inverter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inverter</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArray#getInverter()
	 * @see #getPvArray()
	 * @generated
	 */
	EReference getPvArray_Inverter();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.Inverter <em>Inverter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inverter</em>'.
	 * @see org.gecko.weather.pv.model.pv.Inverter
	 * @generated
	 */
	EClass getInverter();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Inverter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.Inverter#getName()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Inverter#getAcPower <em>Ac Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ac Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.Inverter#getAcPower()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_AcPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Inverter#getEfficiency <em>Efficiency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Efficiency</em>'.
	 * @see org.gecko.weather.pv.model.pv.Inverter#getEfficiency()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_Efficiency();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.Obstacle <em>Obstacle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Obstacle</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle
	 * @generated
	 */
	EClass getObstacle();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Obstacle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle#getName()
	 * @see #getObstacle()
	 * @generated
	 */
	EAttribute getObstacle_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthFrom <em>Azimuth From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth From</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle#getAzimuthFrom()
	 * @see #getObstacle()
	 * @generated
	 */
	EAttribute getObstacle_AzimuthFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthTo <em>Azimuth To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth To</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle#getAzimuthTo()
	 * @see #getObstacle()
	 * @generated
	 */
	EAttribute getObstacle_AzimuthTo();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Obstacle#getDistance <em>Distance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle#getDistance()
	 * @see #getObstacle()
	 * @generated
	 */
	EAttribute getObstacle_Distance();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.Obstacle#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.gecko.weather.pv.model.pv.Obstacle#getHeight()
	 * @see #getObstacle()
	 * @generated
	 */
	EAttribute getObstacle_Height();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.HorizonPoint <em>Horizon Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Horizon Point</em>'.
	 * @see org.gecko.weather.pv.model.pv.HorizonPoint
	 * @generated
	 */
	EClass getHorizonPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.HorizonPoint#getAzimuth <em>Azimuth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth</em>'.
	 * @see org.gecko.weather.pv.model.pv.HorizonPoint#getAzimuth()
	 * @see #getHorizonPoint()
	 * @generated
	 */
	EAttribute getHorizonPoint_Azimuth();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.HorizonPoint#getElevation <em>Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Elevation</em>'.
	 * @see org.gecko.weather.pv.model.pv.HorizonPoint#getElevation()
	 * @see #getHorizonPoint()
	 * @generated
	 */
	EAttribute getHorizonPoint_Elevation();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PlantDirectory <em>Plant Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plant Directory</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantDirectory
	 * @generated
	 */
	EClass getPlantDirectory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.PlantDirectory#getPlants <em>Plants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Plants</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantDirectory#getPlants()
	 * @see #getPlantDirectory()
	 * @generated
	 */
	EReference getPlantDirectory_Plants();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PlantEntry <em>Plant Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plant Entry</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry
	 * @generated
	 */
	EClass getPlantEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PlantEntry#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry#getId()
	 * @see #getPlantEntry()
	 * @generated
	 */
	EAttribute getPlantEntry_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PlantEntry#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry#getName()
	 * @see #getPlantEntry()
	 * @generated
	 */
	EAttribute getPlantEntry_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PlantEntry#getSiteId <em>Site Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry#getSiteId()
	 * @see #getPlantEntry()
	 * @generated
	 */
	EAttribute getPlantEntry_SiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PlantEntry#getPeakPower <em>Peak Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry#getPeakPower()
	 * @see #getPlantEntry()
	 * @generated
	 */
	EAttribute getPlantEntry_PeakPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PlantEntry#getTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry#getTimeZone()
	 * @see #getPlantEntry()
	 * @generated
	 */
	EAttribute getPlantEntry_TimeZone();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PvOutlook <em>Outlook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outlook</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook
	 * @generated
	 */
	EClass getPvOutlook();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantId <em>Plant Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plant Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getPlantId()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_PlantId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantName <em>Plant Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plant Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getPlantName()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_PlantName();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getSiteId <em>Site Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Id</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getSiteId()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_SiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getTimeZone()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_TimeZone();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPeakPower <em>Peak Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getPeakPower()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_PeakPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvOutlook#getGeneratedAt <em>Generated At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generated At</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getGeneratedAt()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EAttribute getPvOutlook_GeneratedAt();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.PvOutlook#getHours <em>Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hours</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getHours()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EReference getPvOutlook_Hours();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.PvOutlook#getDays <em>Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Days</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getDays()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EReference getPvOutlook_Days();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.pv.model.pv.PvOutlook#getArrays <em>Arrays</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arrays</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook#getArrays()
	 * @see #getPvOutlook()
	 * @generated
	 */
	EReference getPvOutlook_Arrays();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PvArrayInfo <em>Array Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Info</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo
	 * @generated
	 */
	EClass getPvArrayInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo#getName()
	 * @see #getPvArrayInfo()
	 * @generated
	 */
	EAttribute getPvArrayInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getAzimuth <em>Azimuth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo#getAzimuth()
	 * @see #getPvArrayInfo()
	 * @generated
	 */
	EAttribute getPvArrayInfo_Azimuth();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getTilt <em>Tilt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tilt</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo#getTilt()
	 * @see #getPvArrayInfo()
	 * @generated
	 */
	EAttribute getPvArrayInfo_Tilt();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getPeakPower <em>Peak Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo#getPeakPower()
	 * @see #getPvArrayInfo()
	 * @generated
	 */
	EAttribute getPvArrayInfo_PeakPower();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PvHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hour</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour
	 * @generated
	 */
	EClass getPvHour();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getTime()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_Time();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getPower <em>Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getPower()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_Power();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getDcPower <em>Dc Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dc Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getDcPower()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_DcPower();

	/**
	 * Returns the meta object for the attribute list '{@link org.gecko.weather.pv.model.pv.PvHour#getArrayPower <em>Array Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Array Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getArrayPower()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_ArrayPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance <em>Plane Irradiance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plane Irradiance</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_PlaneIrradiance();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation <em>Global Radiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global Radiation</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_GlobalRadiation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getCellTemperature <em>Cell Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell Temperature</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getCellTemperature()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_CellTemperature();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getSunElevation <em>Sun Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sun Elevation</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getSunElevation()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_SunElevation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth <em>Sun Azimuth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sun Azimuth</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_SunAzimuth();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#isShaded <em>Shaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shaded</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#isShaded()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_Shaded();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#isClipped <em>Clipped</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clipped</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#isClipped()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_Clipped();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvHour#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvHour#getSource()
	 * @see #getPvHour()
	 * @generated
	 */
	EAttribute getPvHour_Source();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.model.pv.PvDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Day</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay
	 * @generated
	 */
	EClass getPvDay();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getDate()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_Date();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getEnergy <em>Energy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getEnergy()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_Energy();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakPower <em>Peak Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Power</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getPeakPower()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_PeakPower();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakTime <em>Peak Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Time</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getPeakTime()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_PeakTime();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getSpecificYield <em>Specific Yield</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specific Yield</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getSpecificYield()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_SpecificYield();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getHoursCovered <em>Hours Covered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hours Covered</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getHoursCovered()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_HoursCovered();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.model.pv.PvDay#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.gecko.weather.pv.model.pv.PvDay#getSource()
	 * @see #getPvDay()
	 * @generated
	 */
	EAttribute getPvDay_Source();

	/**
	 * Returns the meta object for enum '{@link org.gecko.weather.pv.model.pv.Mounting <em>Mounting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Mounting</em>'.
	 * @see org.gecko.weather.pv.model.pv.Mounting
	 * @generated
	 */
	EEnum getMounting();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PvFactory getPvFactory();

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
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PlantImpl <em>Plant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PlantImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlant()
		 * @generated
		 */
		EClass PLANT = eINSTANCE.getPlant();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__ID = eINSTANCE.getPlant_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__NAME = eINSTANCE.getPlant_Name();

		/**
		 * The meta object literal for the '<em><b>Site Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__SITE_ID = eINSTANCE.getPlant_SiteId();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__LATITUDE = eINSTANCE.getPlant_Latitude();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__LONGITUDE = eINSTANCE.getPlant_Longitude();

		/**
		 * The meta object literal for the '<em><b>Mounting Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__MOUNTING_HEIGHT = eINSTANCE.getPlant_MountingHeight();

		/**
		 * The meta object literal for the '<em><b>Albedo</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__ALBEDO = eINSTANCE.getPlant_Albedo();

		/**
		 * The meta object literal for the '<em><b>System Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT__SYSTEM_LOSSES = eINSTANCE.getPlant_SystemLosses();

		/**
		 * The meta object literal for the '<em><b>Arrays</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLANT__ARRAYS = eINSTANCE.getPlant_Arrays();

		/**
		 * The meta object literal for the '<em><b>Inverters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLANT__INVERTERS = eINSTANCE.getPlant_Inverters();

		/**
		 * The meta object literal for the '<em><b>Obstacles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLANT__OBSTACLES = eINSTANCE.getPlant_Obstacles();

		/**
		 * The meta object literal for the '<em><b>Horizon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLANT__HORIZON = eINSTANCE.getPlant_Horizon();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PvArrayImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvArray()
		 * @generated
		 */
		EClass PV_ARRAY = eINSTANCE.getPvArray();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__NAME = eINSTANCE.getPvArray_Name();

		/**
		 * The meta object literal for the '<em><b>Azimuth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__AZIMUTH = eINSTANCE.getPvArray_Azimuth();

		/**
		 * The meta object literal for the '<em><b>Tilt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__TILT = eINSTANCE.getPvArray_Tilt();

		/**
		 * The meta object literal for the '<em><b>Peak Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__PEAK_POWER = eINSTANCE.getPvArray_PeakPower();

		/**
		 * The meta object literal for the '<em><b>Module Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__MODULE_COUNT = eINSTANCE.getPvArray_ModuleCount();

		/**
		 * The meta object literal for the '<em><b>Temperature Coefficient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__TEMPERATURE_COEFFICIENT = eINSTANCE.getPvArray_TemperatureCoefficient();

		/**
		 * The meta object literal for the '<em><b>Mounting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY__MOUNTING = eINSTANCE.getPvArray_Mounting();

		/**
		 * The meta object literal for the '<em><b>Inverter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PV_ARRAY__INVERTER = eINSTANCE.getPvArray_Inverter();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.InverterImpl <em>Inverter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.InverterImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getInverter()
		 * @generated
		 */
		EClass INVERTER = eINSTANCE.getInverter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__NAME = eINSTANCE.getInverter_Name();

		/**
		 * The meta object literal for the '<em><b>Ac Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__AC_POWER = eINSTANCE.getInverter_AcPower();

		/**
		 * The meta object literal for the '<em><b>Efficiency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__EFFICIENCY = eINSTANCE.getInverter_Efficiency();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl <em>Obstacle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.ObstacleImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getObstacle()
		 * @generated
		 */
		EClass OBSTACLE = eINSTANCE.getObstacle();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBSTACLE__NAME = eINSTANCE.getObstacle_Name();

		/**
		 * The meta object literal for the '<em><b>Azimuth From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBSTACLE__AZIMUTH_FROM = eINSTANCE.getObstacle_AzimuthFrom();

		/**
		 * The meta object literal for the '<em><b>Azimuth To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBSTACLE__AZIMUTH_TO = eINSTANCE.getObstacle_AzimuthTo();

		/**
		 * The meta object literal for the '<em><b>Distance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBSTACLE__DISTANCE = eINSTANCE.getObstacle_Distance();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBSTACLE__HEIGHT = eINSTANCE.getObstacle_Height();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.HorizonPointImpl <em>Horizon Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.HorizonPointImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getHorizonPoint()
		 * @generated
		 */
		EClass HORIZON_POINT = eINSTANCE.getHorizonPoint();

		/**
		 * The meta object literal for the '<em><b>Azimuth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HORIZON_POINT__AZIMUTH = eINSTANCE.getHorizonPoint_Azimuth();

		/**
		 * The meta object literal for the '<em><b>Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HORIZON_POINT__ELEVATION = eINSTANCE.getHorizonPoint_Elevation();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PlantDirectoryImpl <em>Plant Directory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PlantDirectoryImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlantDirectory()
		 * @generated
		 */
		EClass PLANT_DIRECTORY = eINSTANCE.getPlantDirectory();

		/**
		 * The meta object literal for the '<em><b>Plants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLANT_DIRECTORY__PLANTS = eINSTANCE.getPlantDirectory_Plants();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl <em>Plant Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PlantEntryImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPlantEntry()
		 * @generated
		 */
		EClass PLANT_ENTRY = eINSTANCE.getPlantEntry();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT_ENTRY__ID = eINSTANCE.getPlantEntry_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT_ENTRY__NAME = eINSTANCE.getPlantEntry_Name();

		/**
		 * The meta object literal for the '<em><b>Site Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT_ENTRY__SITE_ID = eINSTANCE.getPlantEntry_SiteId();

		/**
		 * The meta object literal for the '<em><b>Peak Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT_ENTRY__PEAK_POWER = eINSTANCE.getPlantEntry_PeakPower();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANT_ENTRY__TIME_ZONE = eINSTANCE.getPlantEntry_TimeZone();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl <em>Outlook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PvOutlookImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvOutlook()
		 * @generated
		 */
		EClass PV_OUTLOOK = eINSTANCE.getPvOutlook();

		/**
		 * The meta object literal for the '<em><b>Plant Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__PLANT_ID = eINSTANCE.getPvOutlook_PlantId();

		/**
		 * The meta object literal for the '<em><b>Plant Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__PLANT_NAME = eINSTANCE.getPvOutlook_PlantName();

		/**
		 * The meta object literal for the '<em><b>Site Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__SITE_ID = eINSTANCE.getPvOutlook_SiteId();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__TIME_ZONE = eINSTANCE.getPvOutlook_TimeZone();

		/**
		 * The meta object literal for the '<em><b>Peak Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__PEAK_POWER = eINSTANCE.getPvOutlook_PeakPower();

		/**
		 * The meta object literal for the '<em><b>Generated At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_OUTLOOK__GENERATED_AT = eINSTANCE.getPvOutlook_GeneratedAt();

		/**
		 * The meta object literal for the '<em><b>Hours</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PV_OUTLOOK__HOURS = eINSTANCE.getPvOutlook_Hours();

		/**
		 * The meta object literal for the '<em><b>Days</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PV_OUTLOOK__DAYS = eINSTANCE.getPvOutlook_Days();

		/**
		 * The meta object literal for the '<em><b>Arrays</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PV_OUTLOOK__ARRAYS = eINSTANCE.getPvOutlook_Arrays();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl <em>Array Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvArrayInfo()
		 * @generated
		 */
		EClass PV_ARRAY_INFO = eINSTANCE.getPvArrayInfo();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY_INFO__NAME = eINSTANCE.getPvArrayInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Azimuth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY_INFO__AZIMUTH = eINSTANCE.getPvArrayInfo_Azimuth();

		/**
		 * The meta object literal for the '<em><b>Tilt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY_INFO__TILT = eINSTANCE.getPvArrayInfo_Tilt();

		/**
		 * The meta object literal for the '<em><b>Peak Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_ARRAY_INFO__PEAK_POWER = eINSTANCE.getPvArrayInfo_PeakPower();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl <em>Hour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PvHourImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvHour()
		 * @generated
		 */
		EClass PV_HOUR = eINSTANCE.getPvHour();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__TIME = eINSTANCE.getPvHour_Time();

		/**
		 * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__POWER = eINSTANCE.getPvHour_Power();

		/**
		 * The meta object literal for the '<em><b>Dc Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__DC_POWER = eINSTANCE.getPvHour_DcPower();

		/**
		 * The meta object literal for the '<em><b>Array Power</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__ARRAY_POWER = eINSTANCE.getPvHour_ArrayPower();

		/**
		 * The meta object literal for the '<em><b>Plane Irradiance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__PLANE_IRRADIANCE = eINSTANCE.getPvHour_PlaneIrradiance();

		/**
		 * The meta object literal for the '<em><b>Global Radiation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__GLOBAL_RADIATION = eINSTANCE.getPvHour_GlobalRadiation();

		/**
		 * The meta object literal for the '<em><b>Cell Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__CELL_TEMPERATURE = eINSTANCE.getPvHour_CellTemperature();

		/**
		 * The meta object literal for the '<em><b>Sun Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__SUN_ELEVATION = eINSTANCE.getPvHour_SunElevation();

		/**
		 * The meta object literal for the '<em><b>Sun Azimuth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__SUN_AZIMUTH = eINSTANCE.getPvHour_SunAzimuth();

		/**
		 * The meta object literal for the '<em><b>Shaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__SHADED = eINSTANCE.getPvHour_Shaded();

		/**
		 * The meta object literal for the '<em><b>Clipped</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__CLIPPED = eINSTANCE.getPvHour_Clipped();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_HOUR__SOURCE = eINSTANCE.getPvHour_Source();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl <em>Day</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.impl.PvDayImpl
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getPvDay()
		 * @generated
		 */
		EClass PV_DAY = eINSTANCE.getPvDay();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__DATE = eINSTANCE.getPvDay_Date();

		/**
		 * The meta object literal for the '<em><b>Energy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__ENERGY = eINSTANCE.getPvDay_Energy();

		/**
		 * The meta object literal for the '<em><b>Peak Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__PEAK_POWER = eINSTANCE.getPvDay_PeakPower();

		/**
		 * The meta object literal for the '<em><b>Peak Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__PEAK_TIME = eINSTANCE.getPvDay_PeakTime();

		/**
		 * The meta object literal for the '<em><b>Specific Yield</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__SPECIFIC_YIELD = eINSTANCE.getPvDay_SpecificYield();

		/**
		 * The meta object literal for the '<em><b>Hours Covered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__HOURS_COVERED = eINSTANCE.getPvDay_HoursCovered();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PV_DAY__SOURCE = eINSTANCE.getPvDay_Source();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.model.pv.Mounting <em>Mounting</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.model.pv.Mounting
		 * @see org.gecko.weather.pv.model.pv.impl.PvPackageImpl#getMounting()
		 * @generated
		 */
		EEnum MOUNTING = eINSTANCE.getMounting();

	}

} //PvPackage
