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
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.BasicMeasurement;
import org.gecko.weather.model.weather.BoolMeasurement;
import org.gecko.weather.model.weather.DoubleMeasurement;
import org.gecko.weather.model.weather.FloatMeasurement;
import org.gecko.weather.model.weather.GeoArea;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.IntMeasurement;
import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.Measurement;
import org.gecko.weather.model.weather.MeasurementWeatherReport;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.UVRadiationMeasurement;
import org.gecko.weather.model.weather.WMOWeatherCodeType;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.model.weather.WeatherReports;
import org.gecko.weather.model.weather.WeatherStation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WeatherPackageImpl extends EPackageImpl implements WeatherPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weatherReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boolMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoAreaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uvRadiationMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mosmixsWeatherReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementWeatherReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass astrotimeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weatherStationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass w1W2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weatherReportsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum wmoWeatherCodeTypeEEnum = null;

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
	 * @see org.gecko.weather.model.weather.WeatherPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WeatherPackageImpl() {
		super(eNS_URI, WeatherFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link WeatherPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WeatherPackage init() {
		if (isInited) return (WeatherPackage)EPackage.Registry.INSTANCE.getEPackage(WeatherPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredWeatherPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		WeatherPackageImpl theWeatherPackage = registeredWeatherPackage instanceof WeatherPackageImpl ? (WeatherPackageImpl)registeredWeatherPackage : new WeatherPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theWeatherPackage.createPackageContents();

		// Initialize created meta-data
		theWeatherPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWeatherPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WeatherPackage.eNS_URI, theWeatherPackage);
		return theWeatherPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStation() {
		return stationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Name() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStation_Location() {
		return (EReference)stationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Country() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWeatherReport() {
		return weatherReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReport_Id() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReport_IssueTime() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReport_Timestamp() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_Station() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_Astrotime() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_WeatherStation() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMeasurement() {
		return measurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasurement_Unit() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasurement_Name() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasurement_Timestamp() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasurement_RawValue() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntMeasurement() {
		return intMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntMeasurement_Value() {
		return (EAttribute)intMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatMeasurement() {
		return floatMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatMeasurement_Value() {
		return (EAttribute)floatMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoubleMeasurement() {
		return doubleMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoubleMeasurement_Value() {
		return (EAttribute)doubleMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBasicMeasurement() {
		return basicMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBasicMeasurement_Value() {
		return (EAttribute)basicMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBoolMeasurement() {
		return boolMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBoolMeasurement_Value() {
		return (EAttribute)boolMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGeoArea() {
		return geoAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGeoArea_TopLeft() {
		return (EReference)geoAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoArea_XDim() {
		return (EAttribute)geoAreaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoArea_YDim() {
		return (EAttribute)geoAreaEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGeoPosition() {
		return geoPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Latitude() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Longitude() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Elevation() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUVRadiationMeasurement() {
		return uvRadiationMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUVRadiationMeasurement_Area() {
		return (EReference)uvRadiationMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMOSMIXSWeatherReport() {
		return mosmixsWeatherReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindDirection() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindSpeed() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustLastHour() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustLastThreeHours() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustMaxLast12Hours() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustProb25() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustProb40() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_WindGustProb55() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverTotal() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverBelow500() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverEffective() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverHigh() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverMid() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_CloudCoverLow() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_IrRadianceGlobal() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SurfacePressure() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02Last6() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50Last6() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02LastDay() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50LastDay() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger00Last12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger02Last12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger10Last12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationLarger50Last12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SnowRainEqLast1() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SnowRainEqLast3() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SunshineDurationLast1() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_TempAboveSurface5() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_TempAboveSurface200() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_TempDewpointAboveSurface200() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_TempMinLast12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_TempMaxLast12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_Visibility() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMOSMIXSWeatherReport_SignificantWeather6Hours() {
		return (EReference)mosmixsWeatherReportEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SignificantWeather3Hours() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_FogPropLast1() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_FogPropLast6() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_FogPropLast12() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMeasurementWeatherReport() {
		return measurementWeatherReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMeasurementWeatherReport_Measurements() {
		return (EReference)measurementWeatherReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAstrotime() {
		return astrotimeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAstrotime_Sunset() {
		return (EAttribute)astrotimeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAstrotime_Sunrise() {
		return (EAttribute)astrotimeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAstrotime_SunsetTwilight() {
		return (EAttribute)astrotimeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAstrotime_SunriseTwilight() {
		return (EAttribute)astrotimeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWeatherStation() {
		return weatherStationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherStation_Id() {
		return (EAttribute)weatherStationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherStation_IcaoCode() {
		return (EAttribute)weatherStationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getW1W2() {
		return w1W2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getW1W2_W1() {
		return (EAttribute)w1W2EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getW1W2_W2() {
		return (EAttribute)w1W2EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWeatherReports() {
		return weatherReportsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReports_Id() {
		return (EAttribute)weatherReportsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReports_Reports() {
		return (EReference)weatherReportsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getWMOWeatherCodeType() {
		return wmoWeatherCodeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherFactory getWeatherFactory() {
		return (WeatherFactory)getEFactoryInstance();
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
		stationEClass = createEClass(STATION);
		createEAttribute(stationEClass, STATION__NAME);
		createEReference(stationEClass, STATION__LOCATION);
		createEAttribute(stationEClass, STATION__COUNTRY);

		weatherReportEClass = createEClass(WEATHER_REPORT);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__ID);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__ISSUE_TIME);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__TIMESTAMP);
		createEReference(weatherReportEClass, WEATHER_REPORT__STATION);
		createEReference(weatherReportEClass, WEATHER_REPORT__ASTROTIME);
		createEReference(weatherReportEClass, WEATHER_REPORT__WEATHER_STATION);

		measurementEClass = createEClass(MEASUREMENT);
		createEAttribute(measurementEClass, MEASUREMENT__UNIT);
		createEAttribute(measurementEClass, MEASUREMENT__NAME);
		createEAttribute(measurementEClass, MEASUREMENT__TIMESTAMP);
		createEAttribute(measurementEClass, MEASUREMENT__RAW_VALUE);

		intMeasurementEClass = createEClass(INT_MEASUREMENT);
		createEAttribute(intMeasurementEClass, INT_MEASUREMENT__VALUE);

		floatMeasurementEClass = createEClass(FLOAT_MEASUREMENT);
		createEAttribute(floatMeasurementEClass, FLOAT_MEASUREMENT__VALUE);

		doubleMeasurementEClass = createEClass(DOUBLE_MEASUREMENT);
		createEAttribute(doubleMeasurementEClass, DOUBLE_MEASUREMENT__VALUE);

		basicMeasurementEClass = createEClass(BASIC_MEASUREMENT);
		createEAttribute(basicMeasurementEClass, BASIC_MEASUREMENT__VALUE);

		boolMeasurementEClass = createEClass(BOOL_MEASUREMENT);
		createEAttribute(boolMeasurementEClass, BOOL_MEASUREMENT__VALUE);

		geoAreaEClass = createEClass(GEO_AREA);
		createEReference(geoAreaEClass, GEO_AREA__TOP_LEFT);
		createEAttribute(geoAreaEClass, GEO_AREA__XDIM);
		createEAttribute(geoAreaEClass, GEO_AREA__YDIM);

		geoPositionEClass = createEClass(GEO_POSITION);
		createEAttribute(geoPositionEClass, GEO_POSITION__LATITUDE);
		createEAttribute(geoPositionEClass, GEO_POSITION__LONGITUDE);
		createEAttribute(geoPositionEClass, GEO_POSITION__ELEVATION);

		uvRadiationMeasurementEClass = createEClass(UV_RADIATION_MEASUREMENT);
		createEReference(uvRadiationMeasurementEClass, UV_RADIATION_MEASUREMENT__AREA);

		mosmixsWeatherReportEClass = createEClass(MOSMIXS_WEATHER_REPORT);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_DIRECTION);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_SPEED);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__VISIBILITY);
		createEReference(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER6_HOURS);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER3_HOURS);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12);

		measurementWeatherReportEClass = createEClass(MEASUREMENT_WEATHER_REPORT);
		createEReference(measurementWeatherReportEClass, MEASUREMENT_WEATHER_REPORT__MEASUREMENTS);

		astrotimeEClass = createEClass(ASTROTIME);
		createEAttribute(astrotimeEClass, ASTROTIME__SUNSET);
		createEAttribute(astrotimeEClass, ASTROTIME__SUNRISE);
		createEAttribute(astrotimeEClass, ASTROTIME__SUNSET_TWILIGHT);
		createEAttribute(astrotimeEClass, ASTROTIME__SUNRISE_TWILIGHT);

		weatherStationEClass = createEClass(WEATHER_STATION);
		createEAttribute(weatherStationEClass, WEATHER_STATION__ID);
		createEAttribute(weatherStationEClass, WEATHER_STATION__ICAO_CODE);

		w1W2EClass = createEClass(W1W2);
		createEAttribute(w1W2EClass, W1W2__W1);
		createEAttribute(w1W2EClass, W1W2__W2);

		weatherReportsEClass = createEClass(WEATHER_REPORTS);
		createEAttribute(weatherReportsEClass, WEATHER_REPORTS__ID);
		createEReference(weatherReportsEClass, WEATHER_REPORTS__REPORTS);

		// Create enums
		wmoWeatherCodeTypeEEnum = createEEnum(WMO_WEATHER_CODE_TYPE);
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
		intMeasurementEClass.getESuperTypes().add(this.getMeasurement());
		floatMeasurementEClass.getESuperTypes().add(this.getMeasurement());
		doubleMeasurementEClass.getESuperTypes().add(this.getMeasurement());
		basicMeasurementEClass.getESuperTypes().add(this.getMeasurement());
		boolMeasurementEClass.getESuperTypes().add(this.getMeasurement());
		uvRadiationMeasurementEClass.getESuperTypes().add(this.getFloatMeasurement());
		mosmixsWeatherReportEClass.getESuperTypes().add(this.getWeatherReport());
		measurementWeatherReportEClass.getESuperTypes().add(this.getWeatherReport());
		weatherStationEClass.getESuperTypes().add(this.getStation());

		// Initialize classes, features, and operations; add parameters
		initEClass(stationEClass, Station.class, "Station", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStation_Name(), ecorePackage.getEString(), "name", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStation_Location(), this.getGeoPosition(), null, "location", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_Country(), ecorePackage.getEString(), "country", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weatherReportEClass, WeatherReport.class, "WeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeatherReport_Id(), ecorePackage.getEString(), "id", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeatherReport_IssueTime(), ecorePackage.getEDate(), "issueTime", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeatherReport_Timestamp(), ecorePackage.getEDate(), "timestamp", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReport_Station(), this.getStation(), null, "station", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getWeatherReport_Station().getEKeys().add(this.getStation_Name());
		initEReference(getWeatherReport_Astrotime(), this.getAstrotime(), null, "astrotime", null, 0, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReport_WeatherStation(), this.getWeatherStation(), null, "weatherStation", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementEClass, Measurement.class, "Measurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasurement_Unit(), ecorePackage.getEString(), "unit", null, 1, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_Name(), ecorePackage.getEString(), "name", null, 1, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_Timestamp(), ecorePackage.getEDate(), "timestamp", null, 1, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_RawValue(), ecorePackage.getEJavaObject(), "rawValue", null, 0, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intMeasurementEClass, IntMeasurement.class, "IntMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntMeasurement_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatMeasurementEClass, FloatMeasurement.class, "FloatMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatMeasurement_Value(), ecorePackage.getEFloat(), "value", null, 0, 1, FloatMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleMeasurementEClass, DoubleMeasurement.class, "DoubleMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleMeasurement_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, DoubleMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(basicMeasurementEClass, BasicMeasurement.class, "BasicMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBasicMeasurement_Value(), ecorePackage.getEString(), "value", null, 0, 1, BasicMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(boolMeasurementEClass, BoolMeasurement.class, "BoolMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBoolMeasurement_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, BoolMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(geoAreaEClass, GeoArea.class, "GeoArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeoArea_TopLeft(), this.getGeoPosition(), null, "topLeft", null, 0, 1, GeoArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoArea_XDim(), ecorePackage.getEDouble(), "xDim", null, 0, 1, GeoArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoArea_YDim(), ecorePackage.getEDouble(), "yDim", null, 0, 1, GeoArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(geoPositionEClass, GeoPosition.class, "GeoPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeoPosition_Latitude(), ecorePackage.getEDouble(), "latitude", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoPosition_Longitude(), ecorePackage.getEDouble(), "longitude", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoPosition_Elevation(), ecorePackage.getEInt(), "elevation", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uvRadiationMeasurementEClass, UVRadiationMeasurement.class, "UVRadiationMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUVRadiationMeasurement_Area(), this.getGeoArea(), null, "area", null, 0, 1, UVRadiationMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mosmixsWeatherReportEClass, MOSMIXSWeatherReport.class, "MOSMIXSWeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMOSMIXSWeatherReport_WindDirection(), ecorePackage.getEFloatObject(), "windDirection", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindSpeed(), ecorePackage.getEFloatObject(), "windSpeed", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustLastHour(), ecorePackage.getEFloatObject(), "windGustLastHour", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustLastThreeHours(), ecorePackage.getEFloatObject(), "windGustLastThreeHours", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustMaxLast12Hours(), ecorePackage.getEFloatObject(), "windGustMaxLast12Hours", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustProb25(), ecorePackage.getEFloatObject(), "windGustProb25", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustProb40(), ecorePackage.getEFloatObject(), "windGustProb40", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_WindGustProb55(), ecorePackage.getEFloatObject(), "windGustProb55", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverTotal(), ecorePackage.getEFloatObject(), "cloudCoverTotal", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverBelow500(), ecorePackage.getEFloatObject(), "cloudCoverBelow500", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverEffective(), ecorePackage.getEFloatObject(), "cloudCoverEffective", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverHigh(), ecorePackage.getEFloatObject(), "cloudCoverHigh", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverMid(), ecorePackage.getEFloatObject(), "cloudCoverMid", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_CloudCoverLow(), ecorePackage.getEFloatObject(), "cloudCoverLow", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_IrRadianceGlobal(), ecorePackage.getEFloatObject(), "irRadianceGlobal", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SurfacePressure(), ecorePackage.getEFloatObject(), "surfacePressure", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger02Last6(), ecorePackage.getEFloatObject(), "precipitationLarger02Last6", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger50Last6(), ecorePackage.getEFloatObject(), "precipitationLarger50Last6", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger02LastDay(), ecorePackage.getEFloatObject(), "precipitationLarger02LastDay", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger50LastDay(), ecorePackage.getEFloatObject(), "precipitationLarger50LastDay", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger00Last12(), ecorePackage.getEFloatObject(), "precipitationLarger00Last12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger02Last12(), ecorePackage.getEFloatObject(), "precipitationLarger02Last12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger10Last12(), ecorePackage.getEFloatObject(), "precipitationLarger10Last12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationLarger50Last12(), ecorePackage.getEFloatObject(), "precipitationLarger50Last12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal(), ecorePackage.getEFloatObject(), "precipitationSignificantWeatherTotal", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3(), ecorePackage.getEFloatObject(), "precipitationSignificantWeatherLast3", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SnowRainEqLast1(), ecorePackage.getEFloatObject(), "snowRainEqLast1", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SnowRainEqLast3(), ecorePackage.getEFloatObject(), "snowRainEqLast3", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SunshineDurationLast1(), ecorePackage.getEFloatObject(), "sunshineDurationLast1", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_TempAboveSurface5(), ecorePackage.getEFloatObject(), "tempAboveSurface5", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_TempAboveSurface200(), ecorePackage.getEFloatObject(), "tempAboveSurface200", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_TempDewpointAboveSurface200(), ecorePackage.getEFloatObject(), "tempDewpointAboveSurface200", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_TempMinLast12(), ecorePackage.getEFloatObject(), "tempMinLast12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_TempMaxLast12(), ecorePackage.getEFloatObject(), "tempMaxLast12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_Visibility(), ecorePackage.getEFloatObject(), "visibility", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMOSMIXSWeatherReport_SignificantWeather6Hours(), this.getW1W2(), null, "significantWeather6Hours", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SignificantWeather3Hours(), this.getWMOWeatherCodeType(), "significantWeather3Hours", "UNKNOWN", 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast1(), ecorePackage.getEFloatObject(), "fogPropLast1", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast6(), ecorePackage.getEFloatObject(), "fogPropLast6", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast12(), ecorePackage.getEFloatObject(), "fogPropLast12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementWeatherReportEClass, MeasurementWeatherReport.class, "MeasurementWeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurementWeatherReport_Measurements(), this.getMeasurement(), null, "measurements", null, 0, -1, MeasurementWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(astrotimeEClass, Astrotime.class, "Astrotime", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAstrotime_Sunset(), ecorePackage.getEDate(), "sunset", null, 0, 1, Astrotime.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAstrotime_Sunrise(), ecorePackage.getEDate(), "sunrise", null, 0, 1, Astrotime.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAstrotime_SunsetTwilight(), ecorePackage.getEDate(), "sunsetTwilight", null, 0, 1, Astrotime.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAstrotime_SunriseTwilight(), ecorePackage.getEDate(), "sunriseTwilight", null, 0, 1, Astrotime.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weatherStationEClass, WeatherStation.class, "WeatherStation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeatherStation_Id(), ecorePackage.getEString(), "id", null, 1, 1, WeatherStation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeatherStation_IcaoCode(), ecorePackage.getEString(), "icaoCode", null, 0, 1, WeatherStation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(w1W2EClass, org.gecko.weather.model.weather.W1W2.class, "W1W2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getW1W2_W1(), this.getWMOWeatherCodeType(), "w1", "UNKNOWN", 0, 1, org.gecko.weather.model.weather.W1W2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getW1W2_W2(), this.getWMOWeatherCodeType(), "w2", "UNKNOWN", 0, 1, org.gecko.weather.model.weather.W1W2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weatherReportsEClass, WeatherReports.class, "WeatherReports", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeatherReports_Id(), ecorePackage.getEString(), "id", null, 1, 1, WeatherReports.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReports_Reports(), this.getWeatherReport(), null, "reports", null, 0, -1, WeatherReports.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.class, "WMOWeatherCodeType");
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W00);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W01);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W02);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W03);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W04);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W05);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W10);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W20);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W21);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W22);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W23);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W24);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W25);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W26);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W27);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W28);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W29);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W30);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W31);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W32);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W33);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W34);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W35);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W40);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W41);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W42);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W43);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W44);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W45);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W50);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W51);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W52);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W53);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W54);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W55);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W56);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W57);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W58);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W60);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W61);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W62);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W63);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W64);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W65);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W66);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W67);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W68);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W70);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W71);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W72);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W73);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W74);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W75);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W76);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W77);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W80);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W81);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W82);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W83);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W85);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W86);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W87);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W90);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W91);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W92);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W93);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W94);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W95);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W96);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W97);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W98);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.W99);
		addEEnumLiteral(wmoWeatherCodeTypeEEnum, WMOWeatherCodeType.WUNKNOWN);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// sensinact.mapping
		createSensinactAnnotations();
		// sensinact.mapping.metadata
		createSensinact_1Annotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "copyrightText", "Copyright (c) 2012 - 2024 Data In Motion and others.\nAll rights reserved. \n\nThis program and the accompanying materials are made\navailable under the terms of the Eclipse Public License 2.0\nwhich is available at https://www.eclipse.org/legal/epl-2.0/\n\nSPDX-License-Identifier: EPL-2.0\n\nContributors:\n     Mark Hoffmann - initial API and implementation",
			   "complianceLevel", "17.0",
			   "oSGiCompatible", "true",
			   "modelName", "DWDWeather",
			   "basePackage", "org.gecko.weather.model",
			   "resource", "XMI"
		   });
		addAnnotation
		  (getWeatherReport_IssueTime(),
		   source,
		   new String[] {
			   "documentation", "This is the time the report was issued."
		   });
		addAnnotation
		  (getWeatherReport_Timestamp(),
		   source,
		   new String[] {
			   "documentation", "This is the time to which the report refers. So, if it is a forecast for a certain time, the forecasted time will be here."
		   });
		addAnnotation
		  (getWeatherReport_Station(),
		   source,
		   new String[] {
			   "documentation", "Station the measurments are for. This is usually the exact location the report belongs to"
		   });
		addAnnotation
		  (getWeatherReport_Astrotime(),
		   source,
		   new String[] {
			   "documentation", "Additional astro time information for this report, like sunset und sunrise times for the report day"
		   });
		addAnnotation
		  (getWeatherReport_WeatherStation(),
		   source,
		   new String[] {
			   "documentation", "The next official (DWD) weather station closest to the \'station\'. This is the official weather station most measurement com from"
		   });
		addAnnotation
		  (mosmixsWeatherReportEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindDirection(),
		   source,
		   new String[] {
			   "documentation", "Wind direction: 0..360 Degrees (DD)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindSpeed(),
		   source,
		   new String[] {
			   "documentation", "Wind speed: m/s (FF)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastHour(),
		   source,
		   new String[] {
			   "documentation", "Maximum wind gust within the last hour: m/s (FX1)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastThreeHours(),
		   source,
		   new String[] {
			   "documentation", "Maximum wind gust within the last 3 hours: m/s (FX3)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustMaxLast12Hours(),
		   source,
		   new String[] {
			   "documentation", "Maximum wind gust within the last 12 hours: m/s (FXh)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb25(),
		   source,
		   new String[] {
			   "documentation", "Probability of wind gusts >= 25kn within the last 12 hours: 0..100% (FXh25)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb40(),
		   source,
		   new String[] {
			   "documentation", "Probability of wind gusts >= 40kn within the last 12 hours: 0..100% (FXh40)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb55(),
		   source,
		   new String[] {
			   "documentation", "Probability of wind gusts >= 55kn within the last 12 hours: 0..100% (FXh55)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverTotal(),
		   source,
		   new String[] {
			   "documentation", "Total cloud cover: 0..100% (N)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverBelow500(),
		   source,
		   new String[] {
			   "documentation", "Cloud cover below 500 ft.: 0..100% (N05)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverEffective(),
		   source,
		   new String[] {
			   "documentation", "Effective cloud cover: 0..100% (Neff)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverHigh(),
		   source,
		   new String[] {
			   "documentation", "High cloud cover (>7 km): 0..100% (Nh)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverMid(),
		   source,
		   new String[] {
			   "documentation", "Midlevel cloud cover (2-7 km): 0..100% (Nm)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverLow(),
		   source,
		   new String[] {
			   "documentation", "Low cloud cover (lower than 2 km): 0..100% (Nl)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_IrRadianceGlobal(),
		   source,
		   new String[] {
			   "documentation", "Global Irradiance: kJ/m2 (Rad1h)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SurfacePressure(),
		   source,
		   new String[] {
			   "documentation", "Surface pressure, reduced: Pa (PPPP)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last6(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 0.2mm during the last 6 hours: 0..100% (R602)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last6(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 5mm during the last 6 hours: 0..100% (R650)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02LastDay(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 0.2mm during the last 24 hours: 0..100% (Rd02)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50LastDay(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 5mm during the last 24 hours: 0..100% (Rd50)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger00Last12(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 0.0mm during the last 12 hours: 0..100% (Rh00)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last12(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 0.2mm during the last 12 hours: 0..100% (Rh02)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger10Last12(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 1 mm during the last 12 hours: 0..100% (Rh10)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last12(),
		   source,
		   new String[] {
			   "documentation", "Probability of precipitation > 5mm during the last 12 hours: 0..100% (Rh50)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal(),
		   source,
		   new String[] {
			   "documentation", "Total precipitation during the last hour consistent with significant weather: kg/m2 (RR1c)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3(),
		   source,
		   new String[] {
			   "documentation", "Total precipitation during the last 3 hours  consistent with significant weather: kg/m2 (RR3c)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast1(),
		   source,
		   new String[] {
			   "documentation", "Snow-Rain-Equivalent during the last hour: kg/m2 (RRS1c)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast3(),
		   source,
		   new String[] {
			   "documentation", "Snow-Rain-Equivalent during the 3 hours: kg/m2 (RRS3c)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SunshineDurationLast1(),
		   source,
		   new String[] {
			   "documentation", "Sunshine duration during the last Hour: s (SunD1)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface5(),
		   source,
		   new String[] {
			   "documentation", "Temperature 5cm above surface: Kelvin (T5cm)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface200(),
		   source,
		   new String[] {
			   "documentation", "Temperature 2m above surface: Kelvin (TTT)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempDewpointAboveSurface200(),
		   source,
		   new String[] {
			   "documentation", "Dewpoint 2m above surface: Kelvin (Td)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMinLast12(),
		   source,
		   new String[] {
			   "documentation", "Minimum temperature - within the last 12 hours: Kelvin (TN)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMaxLast12(),
		   source,
		   new String[] {
			   "documentation", "Maximum temperature - within the last 12 hours: Kelvin (TX)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_Visibility(),
		   source,
		   new String[] {
			   "documentation", "Visibility: m (VV)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SignificantWeather6Hours(),
		   source,
		   new String[] {
			   "documentation", "Significant weather during the last 6 hours. It encodes significant weather for two consecutive 3-hour intervals in the past 6 hours: - (W1W2)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SignificantWeather3Hours(),
		   source,
		   new String[] {
			   "documentation", "Significant weather during the past 3 hours: - (ww)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast1(),
		   source,
		   new String[] {
			   "documentation", "Probability for fog within the last hour: 0..100% (wwM)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast6(),
		   source,
		   new String[] {
			   "documentation", "Probability for fog within the last 6 hours: 0..100% (wwM6)"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast12(),
		   source,
		   new String[] {
			   "documentation", "Probability for fog within the last 12 hours: 0..100% (wwMh)"
		   });
		addAnnotation
		  (astrotimeEClass,
		   source,
		   new String[] {
			   "documentation", "Additional astro time information, like sunset und sunrise times  for a certain day"
		   });
		addAnnotation
		  (getAstrotime_SunsetTwilight(),
		   source,
		   new String[] {
			   "documentation", "Times for the civil twilight, means 6 degrees below the horizon"
		   });
		addAnnotation
		  (getAstrotime_SunriseTwilight(),
		   source,
		   new String[] {
			   "documentation", "Times for the civil twilight, means 6 degrees below the horizon"
		   });
		addAnnotation
		  (getWeatherStation_Id(),
		   source,
		   new String[] {
			   "documentation", "The DWD id"
		   });
		addAnnotation
		  (getWeatherStation_IcaoCode(),
		   source,
		   new String[] {
			   "documentation", "Internation Civil Aviation Organization code"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "No change in cloud cover (MOSMIX-specific)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "Cloudiness is decreasing (MOSMIX-specific)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "Cloudiness remains unchanged (MOSMIX-specific)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(3),
		   source,
		   new String[] {
			   "documentation", "Cloudiness is increasing (MOSMIX-specific)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(4),
		   source,
		   new String[] {
			   "documentation", "Haze, smoke, dust, visibility \u2265 1 km"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(5),
		   source,
		   new String[] {
			   "documentation", "Haze, smoke, dust, visibility < 1 km"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(6),
		   source,
		   new String[] {
			   "documentation", "Mist"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(7),
		   source,
		   new String[] {
			   "documentation", "Fog"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(8),
		   source,
		   new String[] {
			   "documentation", "Precipitation (recent, unspecified)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(9),
		   source,
		   new String[] {
			   "documentation", "Drizzle or snow grains (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(10),
		   source,
		   new String[] {
			   "documentation", "Rain (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(11),
		   source,
		   new String[] {
			   "documentation", "Snow (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(12),
		   source,
		   new String[] {
			   "documentation", "Freezing rain/drizzle (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(13),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(14),
		   source,
		   new String[] {
			   "documentation", "Blowing snow or sand (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(15),
		   source,
		   new String[] {
			   "documentation", "Blowing snow/sand, V \u2265 1 km (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(16),
		   source,
		   new String[] {
			   "documentation", "Blowing snow/sand, V < 1 km (recent)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(17),
		   source,
		   new String[] {
			   "documentation", "Fog"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(18),
		   source,
		   new String[] {
			   "documentation", "Patches of fog"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(19),
		   source,
		   new String[] {
			   "documentation", "Fog \u2014 thinning"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(20),
		   source,
		   new String[] {
			   "documentation", "Fog \u2014 no change"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(21),
		   source,
		   new String[] {
			   "documentation", "Fog \u2014 thickening"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(22),
		   source,
		   new String[] {
			   "documentation", "Rime fog"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(23),
		   source,
		   new String[] {
			   "documentation", "Precipitation (present, unspecified)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(24),
		   source,
		   new String[] {
			   "documentation", "Precipitation slight/moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(25),
		   source,
		   new String[] {
			   "documentation", "Precipitation heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(26),
		   source,
		   new String[] {
			   "documentation", "Liquid precipitation slight/mod"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(27),
		   source,
		   new String[] {
			   "documentation", "Liquid precipitation heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(28),
		   source,
		   new String[] {
			   "documentation", "Solid precipitation slight/mod"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(29),
		   source,
		   new String[] {
			   "documentation", "Drizzle"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(30),
		   source,
		   new String[] {
			   "documentation", "Drizzle slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(31),
		   source,
		   new String[] {
			   "documentation", "Drizzle moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(32),
		   source,
		   new String[] {
			   "documentation", "Drizzle heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(33),
		   source,
		   new String[] {
			   "documentation", "Freezing drizzle slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(34),
		   source,
		   new String[] {
			   "documentation", "Freezing drizzle moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(35),
		   source,
		   new String[] {
			   "documentation", "Freezing drizzle heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(36),
		   source,
		   new String[] {
			   "documentation", "Drizzle & rain slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(37),
		   source,
		   new String[] {
			   "documentation", "Drizzle & rain mod/heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(38),
		   source,
		   new String[] {
			   "documentation", "Rain"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(39),
		   source,
		   new String[] {
			   "documentation", "Rain slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(40),
		   source,
		   new String[] {
			   "documentation", "Rain moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(41),
		   source,
		   new String[] {
			   "documentation", "Rain heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(42),
		   source,
		   new String[] {
			   "documentation", "Freezing rain slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(43),
		   source,
		   new String[] {
			   "documentation", "Freezing rain moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(44),
		   source,
		   new String[] {
			   "documentation", "Freezing rain heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(45),
		   source,
		   new String[] {
			   "documentation", "Rain + snow (or drizzle) slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(46),
		   source,
		   new String[] {
			   "documentation", "Rain + snow mod/heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(47),
		   source,
		   new String[] {
			   "documentation", "Snow"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(48),
		   source,
		   new String[] {
			   "documentation", "Snow slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(49),
		   source,
		   new String[] {
			   "documentation", "Snow moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(50),
		   source,
		   new String[] {
			   "documentation", "Snow heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(51),
		   source,
		   new String[] {
			   "documentation", "Ice pellets slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(52),
		   source,
		   new String[] {
			   "documentation", "Ice pellets moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(53),
		   source,
		   new String[] {
			   "documentation", "Ice pellets heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(54),
		   source,
		   new String[] {
			   "documentation", "Snow grains"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(55),
		   source,
		   new String[] {
			   "documentation", "Showers"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(56),
		   source,
		   new String[] {
			   "documentation", "Rain showers slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(57),
		   source,
		   new String[] {
			   "documentation", "Rain showers moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(58),
		   source,
		   new String[] {
			   "documentation", "Rain showers heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(59),
		   source,
		   new String[] {
			   "documentation", "Snow showers slight"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(60),
		   source,
		   new String[] {
			   "documentation", "Snow showers moderate"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(61),
		   source,
		   new String[] {
			   "documentation", "Snow showers heavy"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(62),
		   source,
		   new String[] {
			   "documentation", "Hail showers (no thunder)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(63),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm recent + slight rain"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(64),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm recent + mod/heavy rain"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(65),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm recent + slight snow/hail"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(66),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm recent + mod/heavy snow/hail"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(67),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm, no hail (slight or moderate)"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(68),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm with slight hail"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(69),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm, heavy, no hail"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(70),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm with dust or sandstorm"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(71),
		   source,
		   new String[] {
			   "documentation", "Thunderstorm with heavy hail"
		   });
		addAnnotation
		  (wmoWeatherCodeTypeEEnum.getELiterals().get(72),
		   source,
		   new String[] {
			   "documentation", "No value was set"
		   });
		addAnnotation
		  (getW1W2_W1(),
		   source,
		   new String[] {
			   "documentation", "Significant weather in the past 6 to 3 hours: - (W1 part of W1W2)"
		   });
		addAnnotation
		  (getW1W2_W2(),
		   source,
		   new String[] {
			   "documentation", "Significant weather in the past 3 hours: - (W2 part of W1W2)"
		   });
	}

	/**
	 * Initializes the annotations for <b>sensinact.mapping</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createSensinactAnnotations() {
		String source = "sensinact.mapping";
		addAnnotation
		  (getMOSMIXSWeatherReport_WindDirection(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "\u00b0"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindSpeed(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "m/s"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastHour(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "m/s"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastThreeHours(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "m/s"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustMaxLast12Hours(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "m/s"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb25(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb40(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb55(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverTotal(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverBelow500(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverEffective(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverHigh(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverMid(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverLow(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_IrRadianceGlobal(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "kJ/m2"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SurfacePressure(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "Pa"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last6(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last6(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02LastDay(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50LastDay(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger00Last12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger10Last12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "kg/m2"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "kg/m2"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast1(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "kg/m2"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast3(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "kg/m2"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SunshineDurationLast1(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "s"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface5(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "K"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface200(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "K"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempDewpointAboveSurface200(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "K"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMinLast12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "K"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMaxLast12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "K"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_Visibility(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "m"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast1(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast6(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast12(),
		   source,
		   new String[] {
			   "sensinact.mapping.unit", "%"
		   });
	}

	/**
	 * Initializes the annotations for <b>sensinact.mapping.metadata</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createSensinact_1Annotations() {
		String source = "sensinact.mapping.metadata";
		addAnnotation
		  (getMOSMIXSWeatherReport_WindDirection(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "degrees",
			   "sensorthings.unit.definition", "degrees",
			   "dwd.id", "DD"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindSpeed(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "meters per seconds",
			   "sensorthings.unit.definition", "meters per seconds",
			   "dwd.id", "FF"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastHour(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "meters per seconds",
			   "sensorthings.unit.definition", "meters per seconds",
			   "dwd.id", "FX1"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustLastThreeHours(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "meters per seconds",
			   "sensorthings.unit.definition", "meters per seconds",
			   "dwd.id", "FX3"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustMaxLast12Hours(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "meters per seconds",
			   "sensorthings.unit.definition", "meters per seconds",
			   "dwd.id", "FXh"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb25(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "FXh25"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb40(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "FXh40"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_WindGustProb55(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "FXh55"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverTotal(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "N"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverBelow500(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "N05"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverEffective(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "Neff"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverHigh(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "Nh"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverMid(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "Nm"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_CloudCoverLow(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage",
			   "sensorthings.unit.definition", "percentage",
			   "dwd.id", "Nl"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_IrRadianceGlobal(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kilo Joul per square meter",
			   "sensorthings.unit.definition", "Kilo Joul per square meter",
			   "dwd.id", "Rad1h"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SurfacePressure(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Pascal",
			   "sensorthings.unit.definition", "Pascal",
			   "dwd.id", "PPPP"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last6(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "R602"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last6(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "R650"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02LastDay(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rd02"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50LastDay(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rd50"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger00Last12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rh00"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger02Last12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rh02"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger10Last12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rh10"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationLarger50Last12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "Rh50"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kilograms per square meter",
			   "sensorthings.unit.definition", "Kilograms per square meter",
			   "dwd.id", "RR1c"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kilograms per square meter",
			   "sensorthings.unit.definition", "Kilograms per square meter",
			   "dwd.id", "RR3c"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast1(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kilograms per square meter",
			   "sensorthings.unit.definition", "Kilograms per square meter",
			   "dwd.id", "RRS1c"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SnowRainEqLast3(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kilograms per square meter",
			   "sensorthings.unit.definition", "Kilograms per square meter",
			   "dwd.id", "RRS3c"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SunshineDurationLast1(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "seconds",
			   "sensorthings.unit.definition", "seconds",
			   "dwd.id", "SunD1"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface5(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kelvin",
			   "sensorthings.unit.definition", "Kelvin",
			   "dwd.id", "T5cm"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempAboveSurface200(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kelvin",
			   "sensorthings.unit.definition", "Kelvin",
			   "dwd.id", "TTT"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempDewpointAboveSurface200(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kelvin",
			   "sensorthings.unit.definition", "Kelvin",
			   "dwd.id", "Td"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMinLast12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kelvin",
			   "sensorthings.unit.definition", "Kelvin",
			   "dwd.id", "TN"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_TempMaxLast12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "Kelvin",
			   "sensorthings.unit.definition", "Kelvin",
			   "dwd.id", "TX"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_Visibility(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "meters",
			   "sensorthings.unit.definition", "meters",
			   "dwd.id", "VV"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_SignificantWeather3Hours(),
		   source,
		   new String[] {
			   "dwd.id", "ww"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast1(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "wwM"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast6(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "wwM6"
		   });
		addAnnotation
		  (getMOSMIXSWeatherReport_FogPropLast12(),
		   source,
		   new String[] {
			   "sensorthings.unit.name", "percentage probability",
			   "sensorthings.unit.definition", "percentage probability",
			   "dwd.id", "wwMh"
		   });
		addAnnotation
		  (getW1W2_W1(),
		   source,
		   new String[] {
			   "dwd.id", "W1 part of W1W2"
		   });
		addAnnotation
		  (getW1W2_W2(),
		   source,
		   new String[] {
			   "dwd.id", "W2 part of W1W2"
		   });
	}

} //WeatherPackageImpl
