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
package org.gecko.weather.outlook.model.outlook.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.outlook.model.outlook.DayOutlook;
import org.gecko.weather.outlook.model.outlook.HourOutlook;
import org.gecko.weather.outlook.model.outlook.Outlook;
import org.gecko.weather.outlook.model.outlook.OutlookFactory;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;
import org.gecko.weather.outlook.model.outlook.SiteDirectory;
import org.gecko.weather.outlook.model.outlook.SiteEntry;
import org.gecko.weather.outlook.model.outlook.SourceNote;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OutlookPackageImpl extends EPackageImpl implements OutlookPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siteDirectoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siteEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hourOutlookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dayOutlookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceNoteEClass = null;

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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OutlookPackageImpl() {
		super(eNS_URI, OutlookFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OutlookPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OutlookPackage init() {
		if (isInited) return (OutlookPackage)EPackage.Registry.INSTANCE.getEPackage(OutlookPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredOutlookPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		OutlookPackageImpl theOutlookPackage = registeredOutlookPackage instanceof OutlookPackageImpl ? (OutlookPackageImpl)registeredOutlookPackage : new OutlookPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theOutlookPackage.createPackageContents();

		// Initialize created meta-data
		theOutlookPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOutlookPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OutlookPackage.eNS_URI, theOutlookPackage);
		return theOutlookPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSiteDirectory() {
		return siteDirectoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSiteDirectory_Sites() {
		return (EReference)siteDirectoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSiteEntry() {
		return siteEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteEntry_Id() {
		return (EAttribute)siteEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteEntry_Name() {
		return (EAttribute)siteEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteEntry_Latitude() {
		return (EAttribute)siteEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteEntry_Longitude() {
		return (EAttribute)siteEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteEntry_TimeZone() {
		return (EAttribute)siteEntryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOutlook() {
		return outlookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_SiteId() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_SiteName() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_Latitude() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_Longitude() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_TimeZone() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOutlook_GeneratedAt() {
		return (EAttribute)outlookEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOutlook_Hours() {
		return (EReference)outlookEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOutlook_Today() {
		return (EReference)outlookEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOutlook_Days() {
		return (EReference)outlookEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOutlook_Sources() {
		return (EReference)outlookEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHourOutlook() {
		return hourOutlookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_Time() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_Temperature() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_DewPoint() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_CloudCover() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_Precipitation() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_PrecipitationProbability() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_WindSpeed() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_WindGust() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_WindDirection() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_GlobalRadiation() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_DirectRadiation() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_DiffuseRadiation() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_SunElevation() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_SunAzimuth() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_WeatherCode() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHourOutlook_Daylight() {
		return (EAttribute)hourOutlookEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDayOutlook() {
		return dayOutlookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_Date() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_TemperatureMin() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_TemperatureMax() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_Precipitation() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_PrecipitationProbability() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_SunshineHours() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_Insolation() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_CloudCoverMean() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_WindGustMax() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_UvIndexMax() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_WeatherCode() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_Sunrise() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_Sunset() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_SolarNoon() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayOutlook_DaylightHours() {
		return (EAttribute)dayOutlookEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSourceNote() {
		return sourceNoteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_Quantities() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_ProviderId() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_ProductId() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_Location() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_DistanceMeters() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceNote_IssuedAt() {
		return (EAttribute)sourceNoteEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutlookFactory getOutlookFactory() {
		return (OutlookFactory)getEFactoryInstance();
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
		siteDirectoryEClass = createEClass(SITE_DIRECTORY);
		createEReference(siteDirectoryEClass, SITE_DIRECTORY__SITES);

		siteEntryEClass = createEClass(SITE_ENTRY);
		createEAttribute(siteEntryEClass, SITE_ENTRY__ID);
		createEAttribute(siteEntryEClass, SITE_ENTRY__NAME);
		createEAttribute(siteEntryEClass, SITE_ENTRY__LATITUDE);
		createEAttribute(siteEntryEClass, SITE_ENTRY__LONGITUDE);
		createEAttribute(siteEntryEClass, SITE_ENTRY__TIME_ZONE);

		outlookEClass = createEClass(OUTLOOK);
		createEAttribute(outlookEClass, OUTLOOK__SITE_ID);
		createEAttribute(outlookEClass, OUTLOOK__SITE_NAME);
		createEAttribute(outlookEClass, OUTLOOK__LATITUDE);
		createEAttribute(outlookEClass, OUTLOOK__LONGITUDE);
		createEAttribute(outlookEClass, OUTLOOK__TIME_ZONE);
		createEAttribute(outlookEClass, OUTLOOK__GENERATED_AT);
		createEReference(outlookEClass, OUTLOOK__HOURS);
		createEReference(outlookEClass, OUTLOOK__TODAY);
		createEReference(outlookEClass, OUTLOOK__DAYS);
		createEReference(outlookEClass, OUTLOOK__SOURCES);

		hourOutlookEClass = createEClass(HOUR_OUTLOOK);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__TIME);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__TEMPERATURE);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__DEW_POINT);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__CLOUD_COVER);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__PRECIPITATION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__PRECIPITATION_PROBABILITY);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__WIND_SPEED);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__WIND_GUST);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__WIND_DIRECTION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__GLOBAL_RADIATION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__DIRECT_RADIATION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__DIFFUSE_RADIATION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__SUN_ELEVATION);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__SUN_AZIMUTH);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__WEATHER_CODE);
		createEAttribute(hourOutlookEClass, HOUR_OUTLOOK__DAYLIGHT);

		dayOutlookEClass = createEClass(DAY_OUTLOOK);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__DATE);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__TEMPERATURE_MIN);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__TEMPERATURE_MAX);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__PRECIPITATION);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__PRECIPITATION_PROBABILITY);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__SUNSHINE_HOURS);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__INSOLATION);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__CLOUD_COVER_MEAN);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__WIND_GUST_MAX);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__UV_INDEX_MAX);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__WEATHER_CODE);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__SUNRISE);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__SUNSET);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__SOLAR_NOON);
		createEAttribute(dayOutlookEClass, DAY_OUTLOOK__DAYLIGHT_HOURS);

		sourceNoteEClass = createEClass(SOURCE_NOTE);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__QUANTITIES);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__PROVIDER_ID);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__PRODUCT_ID);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__LOCATION);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__DISTANCE_METERS);
		createEAttribute(sourceNoteEClass, SOURCE_NOTE__ISSUED_AT);
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
		initEClass(siteDirectoryEClass, SiteDirectory.class, "SiteDirectory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSiteDirectory_Sites(), this.getSiteEntry(), null, "sites", null, 0, -1, SiteDirectory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(siteEntryEClass, SiteEntry.class, "SiteEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSiteEntry_Id(), ecorePackage.getEString(), "id", null, 0, 1, SiteEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSiteEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, SiteEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSiteEntry_Latitude(), ecorePackage.getEDouble(), "latitude", null, 0, 1, SiteEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSiteEntry_Longitude(), ecorePackage.getEDouble(), "longitude", null, 0, 1, SiteEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSiteEntry_TimeZone(), ecorePackage.getEString(), "timeZone", null, 0, 1, SiteEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outlookEClass, Outlook.class, "Outlook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutlook_SiteId(), ecorePackage.getEString(), "siteId", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutlook_SiteName(), ecorePackage.getEString(), "siteName", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutlook_Latitude(), ecorePackage.getEDouble(), "latitude", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutlook_Longitude(), ecorePackage.getEDouble(), "longitude", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutlook_TimeZone(), ecorePackage.getEString(), "timeZone", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutlook_GeneratedAt(), ecorePackage.getEDate(), "generatedAt", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutlook_Hours(), this.getHourOutlook(), null, "hours", null, 0, -1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutlook_Today(), this.getDayOutlook(), null, "today", null, 0, 1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutlook_Days(), this.getDayOutlook(), null, "days", null, 0, -1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutlook_Sources(), this.getSourceNote(), null, "sources", null, 0, -1, Outlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hourOutlookEClass, HourOutlook.class, "HourOutlook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHourOutlook_Time(), ecorePackage.getEDate(), "time", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_Temperature(), ecorePackage.getEDouble(), "temperature", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_DewPoint(), ecorePackage.getEDouble(), "dewPoint", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_CloudCover(), ecorePackage.getEDouble(), "cloudCover", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_Precipitation(), ecorePackage.getEDouble(), "precipitation", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_PrecipitationProbability(), ecorePackage.getEDouble(), "precipitationProbability", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_WindSpeed(), ecorePackage.getEDouble(), "windSpeed", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_WindGust(), ecorePackage.getEDouble(), "windGust", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_WindDirection(), ecorePackage.getEDouble(), "windDirection", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_GlobalRadiation(), ecorePackage.getEDouble(), "globalRadiation", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_DirectRadiation(), ecorePackage.getEDouble(), "directRadiation", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_DiffuseRadiation(), ecorePackage.getEDouble(), "diffuseRadiation", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_SunElevation(), ecorePackage.getEDouble(), "sunElevation", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_SunAzimuth(), ecorePackage.getEDouble(), "sunAzimuth", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_WeatherCode(), ecorePackage.getEInt(), "weatherCode", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHourOutlook_Daylight(), ecorePackage.getEBoolean(), "daylight", null, 0, 1, HourOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dayOutlookEClass, DayOutlook.class, "DayOutlook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDayOutlook_Date(), ecorePackage.getEString(), "date", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_TemperatureMin(), ecorePackage.getEDouble(), "temperatureMin", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_TemperatureMax(), ecorePackage.getEDouble(), "temperatureMax", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_Precipitation(), ecorePackage.getEDouble(), "precipitation", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_PrecipitationProbability(), ecorePackage.getEDouble(), "precipitationProbability", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_SunshineHours(), ecorePackage.getEDouble(), "sunshineHours", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_Insolation(), ecorePackage.getEDouble(), "insolation", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_CloudCoverMean(), ecorePackage.getEDouble(), "cloudCoverMean", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_WindGustMax(), ecorePackage.getEDouble(), "windGustMax", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_UvIndexMax(), ecorePackage.getEDouble(), "uvIndexMax", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_WeatherCode(), ecorePackage.getEInt(), "weatherCode", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_Sunrise(), ecorePackage.getEDate(), "sunrise", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_Sunset(), ecorePackage.getEDate(), "sunset", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_SolarNoon(), ecorePackage.getEDate(), "solarNoon", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayOutlook_DaylightHours(), ecorePackage.getEDouble(), "daylightHours", null, 0, 1, DayOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourceNoteEClass, SourceNote.class, "SourceNote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSourceNote_Quantities(), ecorePackage.getEString(), "quantities", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceNote_ProviderId(), ecorePackage.getEString(), "providerId", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceNote_ProductId(), ecorePackage.getEString(), "productId", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceNote_Location(), ecorePackage.getEString(), "location", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceNote_DistanceMeters(), ecorePackage.getEDouble(), "distanceMeters", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceNote_IssuedAt(), ecorePackage.getEDate(), "issuedAt", null, 0, 1, SourceNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OutlookPackageImpl
