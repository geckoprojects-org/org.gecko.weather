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
package org.gecko.weather.outlook.model.outlook;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * What a weather page shows for a site: the next hours one by one and the next days in summary. A presentation built by a consumer of the weather service, not part of it — every hour carries one value per quantity, picked from the report's per-source datasets by a fixed, documented rule, and the outlook names the sources it picked from. Plain data types only (EDate, EDouble, EInt, EString) so that a TypeScript client reads it without the java.time conversion delegate.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.outlook.model.outlook.OutlookFactory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = OutlookPackage.eNS_URI, fingerprint = "fp1:d6bf9efea49e17df2e6570568b692d4e83be7e2ca3d31497c3aef68d6a8b2d24", genModel = "/model/outlook.genmodel", genModelSourceLocations = {"model/outlook.genmodel","org.gecko.weather.outlook/model/outlook.genmodel"}, ecore = "/model/outlook.ecore", ecoreSourceLocations = "/model/outlook.ecore")
public interface OutlookPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "outlook";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/weather/outlook/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "outlook";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OutlookPackage eINSTANCE = org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.SiteDirectoryImpl <em>Site Directory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.SiteDirectoryImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSiteDirectory()
	 * @generated
	 */
	int SITE_DIRECTORY = 0;

	/**
	 * The feature id for the '<em><b>Sites</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_DIRECTORY__SITES = 0;

	/**
	 * The number of structural features of the '<em>Site Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_DIRECTORY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Site Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_DIRECTORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.SiteEntryImpl <em>Site Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.SiteEntryImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSiteEntry()
	 * @generated
	 */
	int SITE_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY__LATITUDE = 2;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY__LONGITUDE = 3;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY__TIME_ZONE = 4;

	/**
	 * The number of structural features of the '<em>Site Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Site Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl <em>Outlook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getOutlook()
	 * @generated
	 */
	int OUTLOOK = 2;

	/**
	 * The feature id for the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__SITE_ID = 0;

	/**
	 * The feature id for the '<em><b>Site Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__SITE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__LATITUDE = 2;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__LONGITUDE = 3;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__TIME_ZONE = 4;

	/**
	 * The feature id for the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__GENERATED_AT = 5;

	/**
	 * The feature id for the '<em><b>Hours</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__HOURS = 6;

	/**
	 * The feature id for the '<em><b>Today</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__TODAY = 7;

	/**
	 * The feature id for the '<em><b>Days</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__DAYS = 8;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK__SOURCES = 9;

	/**
	 * The number of structural features of the '<em>Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl <em>Hour Outlook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getHourOutlook()
	 * @generated
	 */
	int HOUR_OUTLOOK = 3;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__TIME = 0;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__TEMPERATURE = 1;

	/**
	 * The feature id for the '<em><b>Dew Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__DEW_POINT = 2;

	/**
	 * The feature id for the '<em><b>Cloud Cover</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__CLOUD_COVER = 3;

	/**
	 * The feature id for the '<em><b>Precipitation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__PRECIPITATION = 4;

	/**
	 * The feature id for the '<em><b>Precipitation Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__PRECIPITATION_PROBABILITY = 5;

	/**
	 * The feature id for the '<em><b>Wind Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__WIND_SPEED = 6;

	/**
	 * The feature id for the '<em><b>Wind Gust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__WIND_GUST = 7;

	/**
	 * The feature id for the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__WIND_DIRECTION = 8;

	/**
	 * The feature id for the '<em><b>Global Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__GLOBAL_RADIATION = 9;

	/**
	 * The feature id for the '<em><b>Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__SUN_ELEVATION = 10;

	/**
	 * The feature id for the '<em><b>Weather Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__WEATHER_CODE = 11;

	/**
	 * The feature id for the '<em><b>Daylight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK__DAYLIGHT = 12;

	/**
	 * The number of structural features of the '<em>Hour Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK_FEATURE_COUNT = 13;

	/**
	 * The number of operations of the '<em>Hour Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOUR_OUTLOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl <em>Day Outlook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getDayOutlook()
	 * @generated
	 */
	int DAY_OUTLOOK = 4;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__DATE = 0;

	/**
	 * The feature id for the '<em><b>Temperature Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__TEMPERATURE_MIN = 1;

	/**
	 * The feature id for the '<em><b>Temperature Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__TEMPERATURE_MAX = 2;

	/**
	 * The feature id for the '<em><b>Precipitation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__PRECIPITATION = 3;

	/**
	 * The feature id for the '<em><b>Precipitation Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__PRECIPITATION_PROBABILITY = 4;

	/**
	 * The feature id for the '<em><b>Sunshine Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__SUNSHINE_HOURS = 5;

	/**
	 * The feature id for the '<em><b>Cloud Cover Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__CLOUD_COVER_MEAN = 6;

	/**
	 * The feature id for the '<em><b>Wind Gust Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__WIND_GUST_MAX = 7;

	/**
	 * The feature id for the '<em><b>Uv Index Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__UV_INDEX_MAX = 8;

	/**
	 * The feature id for the '<em><b>Weather Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__WEATHER_CODE = 9;

	/**
	 * The feature id for the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__SUNRISE = 10;

	/**
	 * The feature id for the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__SUNSET = 11;

	/**
	 * The feature id for the '<em><b>Solar Noon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__SOLAR_NOON = 12;

	/**
	 * The feature id for the '<em><b>Daylight Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK__DAYLIGHT_HOURS = 13;

	/**
	 * The number of structural features of the '<em>Day Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK_FEATURE_COUNT = 14;

	/**
	 * The number of operations of the '<em>Day Outlook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DAY_OUTLOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl <em>Source Note</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl
	 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSourceNote()
	 * @generated
	 */
	int SOURCE_NOTE = 5;

	/**
	 * The feature id for the '<em><b>Quantities</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__QUANTITIES = 0;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__PROVIDER_ID = 1;

	/**
	 * The feature id for the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__PRODUCT_ID = 2;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__DISTANCE_METERS = 4;

	/**
	 * The feature id for the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE__ISSUED_AT = 5;

	/**
	 * The number of structural features of the '<em>Source Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Source Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_NOTE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.SiteDirectory <em>Site Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site Directory</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteDirectory
	 * @generated
	 */
	EClass getSiteDirectory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.outlook.model.outlook.SiteDirectory#getSites <em>Sites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sites</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteDirectory#getSites()
	 * @see #getSiteDirectory()
	 * @generated
	 */
	EReference getSiteDirectory_Sites();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.SiteEntry <em>Site Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site Entry</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry
	 * @generated
	 */
	EClass getSiteEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SiteEntry#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry#getId()
	 * @see #getSiteEntry()
	 * @generated
	 */
	EAttribute getSiteEntry_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SiteEntry#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry#getName()
	 * @see #getSiteEntry()
	 * @generated
	 */
	EAttribute getSiteEntry_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SiteEntry#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry#getLatitude()
	 * @see #getSiteEntry()
	 * @generated
	 */
	EAttribute getSiteEntry_Latitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SiteEntry#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry#getLongitude()
	 * @see #getSiteEntry()
	 * @generated
	 */
	EAttribute getSiteEntry_Longitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SiteEntry#getTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SiteEntry#getTimeZone()
	 * @see #getSiteEntry()
	 * @generated
	 */
	EAttribute getSiteEntry_TimeZone();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.Outlook <em>Outlook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outlook</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook
	 * @generated
	 */
	EClass getOutlook();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteId <em>Site Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Id</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getSiteId()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_SiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteName <em>Site Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Site Name</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getSiteName()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_SiteName();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getLatitude()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_Latitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getLongitude()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_Longitude();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getTimeZone()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_TimeZone();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.Outlook#getGeneratedAt <em>Generated At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generated At</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getGeneratedAt()
	 * @see #getOutlook()
	 * @generated
	 */
	EAttribute getOutlook_GeneratedAt();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.outlook.model.outlook.Outlook#getHours <em>Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hours</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getHours()
	 * @see #getOutlook()
	 * @generated
	 */
	EReference getOutlook_Hours();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.outlook.model.outlook.Outlook#getToday <em>Today</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Today</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getToday()
	 * @see #getOutlook()
	 * @generated
	 */
	EReference getOutlook_Today();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.outlook.model.outlook.Outlook#getDays <em>Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Days</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getDays()
	 * @see #getOutlook()
	 * @generated
	 */
	EReference getOutlook_Days();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gecko.weather.outlook.model.outlook.Outlook#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sources</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.Outlook#getSources()
	 * @see #getOutlook()
	 * @generated
	 */
	EReference getOutlook_Sources();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.HourOutlook <em>Hour Outlook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hour Outlook</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook
	 * @generated
	 */
	EClass getHourOutlook();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getTime()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_Time();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_Temperature();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint <em>Dew Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dew Point</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_DewPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover <em>Cloud Cover</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_CloudCover();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation <em>Precipitation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_Precipitation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Probability</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_PrecipitationProbability();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed <em>Wind Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Speed</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_WindSpeed();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust <em>Wind Gust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_WindGust();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection <em>Wind Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Direction</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_WindDirection();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation <em>Global Radiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global Radiation</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_GlobalRadiation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation <em>Sun Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sun Elevation</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_SunElevation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode <em>Weather Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weather Code</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_WeatherCode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#isDaylight <em>Daylight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Daylight</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.HourOutlook#isDaylight()
	 * @see #getHourOutlook()
	 * @generated
	 */
	EAttribute getHourOutlook_Daylight();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.DayOutlook <em>Day Outlook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Day Outlook</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook
	 * @generated
	 */
	EClass getDayOutlook();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getDate()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_Date();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin <em>Temperature Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature Min</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_TemperatureMin();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax <em>Temperature Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature Max</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_TemperatureMax();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation <em>Precipitation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_Precipitation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precipitation Probability</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_PrecipitationProbability();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours <em>Sunshine Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunshine Hours</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_SunshineHours();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean <em>Cloud Cover Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Cover Mean</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_CloudCoverMean();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax <em>Wind Gust Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust Max</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_WindGustMax();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax <em>Uv Index Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uv Index Max</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_UvIndexMax();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode <em>Weather Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weather Code</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_WeatherCode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunrise <em>Sunrise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunrise</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getSunrise()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_Sunrise();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunset <em>Sunset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sunset</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getSunset()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_Sunset();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSolarNoon <em>Solar Noon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solar Noon</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getSolarNoon()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_SolarNoon();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours <em>Daylight Hours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Daylight Hours</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours()
	 * @see #getDayOutlook()
	 * @generated
	 */
	EAttribute getDayOutlook_DaylightHours();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.outlook.model.outlook.SourceNote <em>Source Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Note</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote
	 * @generated
	 */
	EClass getSourceNote();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getQuantities <em>Quantities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantities</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getQuantities()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_Quantities();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getProviderId()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProductId <em>Product Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Id</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getProductId()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_ProductId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getLocation()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters <em>Distance Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance Meters</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_DistanceMeters();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getIssuedAt <em>Issued At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issued At</em>'.
	 * @see org.gecko.weather.outlook.model.outlook.SourceNote#getIssuedAt()
	 * @see #getSourceNote()
	 * @generated
	 */
	EAttribute getSourceNote_IssuedAt();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OutlookFactory getOutlookFactory();

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
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.SiteDirectoryImpl <em>Site Directory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.SiteDirectoryImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSiteDirectory()
		 * @generated
		 */
		EClass SITE_DIRECTORY = eINSTANCE.getSiteDirectory();

		/**
		 * The meta object literal for the '<em><b>Sites</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE_DIRECTORY__SITES = eINSTANCE.getSiteDirectory_Sites();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.SiteEntryImpl <em>Site Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.SiteEntryImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSiteEntry()
		 * @generated
		 */
		EClass SITE_ENTRY = eINSTANCE.getSiteEntry();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ENTRY__ID = eINSTANCE.getSiteEntry_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ENTRY__NAME = eINSTANCE.getSiteEntry_Name();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ENTRY__LATITUDE = eINSTANCE.getSiteEntry_Latitude();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ENTRY__LONGITUDE = eINSTANCE.getSiteEntry_Longitude();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_ENTRY__TIME_ZONE = eINSTANCE.getSiteEntry_TimeZone();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl <em>Outlook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getOutlook()
		 * @generated
		 */
		EClass OUTLOOK = eINSTANCE.getOutlook();

		/**
		 * The meta object literal for the '<em><b>Site Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__SITE_ID = eINSTANCE.getOutlook_SiteId();

		/**
		 * The meta object literal for the '<em><b>Site Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__SITE_NAME = eINSTANCE.getOutlook_SiteName();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__LATITUDE = eINSTANCE.getOutlook_Latitude();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__LONGITUDE = eINSTANCE.getOutlook_Longitude();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__TIME_ZONE = eINSTANCE.getOutlook_TimeZone();

		/**
		 * The meta object literal for the '<em><b>Generated At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLOOK__GENERATED_AT = eINSTANCE.getOutlook_GeneratedAt();

		/**
		 * The meta object literal for the '<em><b>Hours</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLOOK__HOURS = eINSTANCE.getOutlook_Hours();

		/**
		 * The meta object literal for the '<em><b>Today</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLOOK__TODAY = eINSTANCE.getOutlook_Today();

		/**
		 * The meta object literal for the '<em><b>Days</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLOOK__DAYS = eINSTANCE.getOutlook_Days();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLOOK__SOURCES = eINSTANCE.getOutlook_Sources();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl <em>Hour Outlook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getHourOutlook()
		 * @generated
		 */
		EClass HOUR_OUTLOOK = eINSTANCE.getHourOutlook();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__TIME = eINSTANCE.getHourOutlook_Time();

		/**
		 * The meta object literal for the '<em><b>Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__TEMPERATURE = eINSTANCE.getHourOutlook_Temperature();

		/**
		 * The meta object literal for the '<em><b>Dew Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__DEW_POINT = eINSTANCE.getHourOutlook_DewPoint();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__CLOUD_COVER = eINSTANCE.getHourOutlook_CloudCover();

		/**
		 * The meta object literal for the '<em><b>Precipitation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__PRECIPITATION = eINSTANCE.getHourOutlook_Precipitation();

		/**
		 * The meta object literal for the '<em><b>Precipitation Probability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__PRECIPITATION_PROBABILITY = eINSTANCE.getHourOutlook_PrecipitationProbability();

		/**
		 * The meta object literal for the '<em><b>Wind Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__WIND_SPEED = eINSTANCE.getHourOutlook_WindSpeed();

		/**
		 * The meta object literal for the '<em><b>Wind Gust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__WIND_GUST = eINSTANCE.getHourOutlook_WindGust();

		/**
		 * The meta object literal for the '<em><b>Wind Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__WIND_DIRECTION = eINSTANCE.getHourOutlook_WindDirection();

		/**
		 * The meta object literal for the '<em><b>Global Radiation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__GLOBAL_RADIATION = eINSTANCE.getHourOutlook_GlobalRadiation();

		/**
		 * The meta object literal for the '<em><b>Sun Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__SUN_ELEVATION = eINSTANCE.getHourOutlook_SunElevation();

		/**
		 * The meta object literal for the '<em><b>Weather Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__WEATHER_CODE = eINSTANCE.getHourOutlook_WeatherCode();

		/**
		 * The meta object literal for the '<em><b>Daylight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOUR_OUTLOOK__DAYLIGHT = eINSTANCE.getHourOutlook_Daylight();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl <em>Day Outlook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getDayOutlook()
		 * @generated
		 */
		EClass DAY_OUTLOOK = eINSTANCE.getDayOutlook();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__DATE = eINSTANCE.getDayOutlook_Date();

		/**
		 * The meta object literal for the '<em><b>Temperature Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__TEMPERATURE_MIN = eINSTANCE.getDayOutlook_TemperatureMin();

		/**
		 * The meta object literal for the '<em><b>Temperature Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__TEMPERATURE_MAX = eINSTANCE.getDayOutlook_TemperatureMax();

		/**
		 * The meta object literal for the '<em><b>Precipitation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__PRECIPITATION = eINSTANCE.getDayOutlook_Precipitation();

		/**
		 * The meta object literal for the '<em><b>Precipitation Probability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__PRECIPITATION_PROBABILITY = eINSTANCE.getDayOutlook_PrecipitationProbability();

		/**
		 * The meta object literal for the '<em><b>Sunshine Hours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__SUNSHINE_HOURS = eINSTANCE.getDayOutlook_SunshineHours();

		/**
		 * The meta object literal for the '<em><b>Cloud Cover Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__CLOUD_COVER_MEAN = eINSTANCE.getDayOutlook_CloudCoverMean();

		/**
		 * The meta object literal for the '<em><b>Wind Gust Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__WIND_GUST_MAX = eINSTANCE.getDayOutlook_WindGustMax();

		/**
		 * The meta object literal for the '<em><b>Uv Index Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__UV_INDEX_MAX = eINSTANCE.getDayOutlook_UvIndexMax();

		/**
		 * The meta object literal for the '<em><b>Weather Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__WEATHER_CODE = eINSTANCE.getDayOutlook_WeatherCode();

		/**
		 * The meta object literal for the '<em><b>Sunrise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__SUNRISE = eINSTANCE.getDayOutlook_Sunrise();

		/**
		 * The meta object literal for the '<em><b>Sunset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__SUNSET = eINSTANCE.getDayOutlook_Sunset();

		/**
		 * The meta object literal for the '<em><b>Solar Noon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__SOLAR_NOON = eINSTANCE.getDayOutlook_SolarNoon();

		/**
		 * The meta object literal for the '<em><b>Daylight Hours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DAY_OUTLOOK__DAYLIGHT_HOURS = eINSTANCE.getDayOutlook_DaylightHours();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl <em>Source Note</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl
		 * @see org.gecko.weather.outlook.model.outlook.impl.OutlookPackageImpl#getSourceNote()
		 * @generated
		 */
		EClass SOURCE_NOTE = eINSTANCE.getSourceNote();

		/**
		 * The meta object literal for the '<em><b>Quantities</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__QUANTITIES = eINSTANCE.getSourceNote_Quantities();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__PROVIDER_ID = eINSTANCE.getSourceNote_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Product Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__PRODUCT_ID = eINSTANCE.getSourceNote_ProductId();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__LOCATION = eINSTANCE.getSourceNote_Location();

		/**
		 * The meta object literal for the '<em><b>Distance Meters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__DISTANCE_METERS = eINSTANCE.getSourceNote_DistanceMeters();

		/**
		 * The meta object literal for the '<em><b>Issued At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_NOTE__ISSUED_AT = eINSTANCE.getSourceNote_IssuedAt();

	}

} //OutlookPackage
