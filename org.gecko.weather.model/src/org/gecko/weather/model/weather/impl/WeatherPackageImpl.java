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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;

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
	public EAttribute getStation_Id() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_IcaoCode() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Name() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStation_Location() {
		return (EReference)stationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Country() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getWeatherReport_Timestamp() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_Station() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getMOSMIXSWeatherReport_PastWeather() {
		return (EAttribute)mosmixsWeatherReportEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMOSMIXSWeatherReport_SignificantWeather() {
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
		createEAttribute(stationEClass, STATION__ID);
		createEAttribute(stationEClass, STATION__ICAO_CODE);
		createEAttribute(stationEClass, STATION__NAME);
		createEReference(stationEClass, STATION__LOCATION);
		createEAttribute(stationEClass, STATION__COUNTRY);

		weatherReportEClass = createEClass(WEATHER_REPORT);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__ID);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__TIMESTAMP);
		createEReference(weatherReportEClass, WEATHER_REPORT__STATION);

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
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__PAST_WEATHER);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6);
		createEAttribute(mosmixsWeatherReportEClass, MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12);

		measurementWeatherReportEClass = createEClass(MEASUREMENT_WEATHER_REPORT);
		createEReference(measurementWeatherReportEClass, MEASUREMENT_WEATHER_REPORT__MEASUREMENTS);
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

		// Initialize classes, features, and operations; add parameters
		initEClass(stationEClass, Station.class, "Station", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStation_Id(), ecorePackage.getEString(), "id", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_IcaoCode(), ecorePackage.getEString(), "icaoCode", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_Name(), ecorePackage.getEString(), "name", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStation_Location(), this.getGeoPosition(), null, "location", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_Country(), ecorePackage.getEString(), "country", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weatherReportEClass, WeatherReport.class, "WeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeatherReport_Id(), ecorePackage.getEString(), "id", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeatherReport_Timestamp(), ecorePackage.getEDate(), "timestamp", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReport_Station(), this.getStation(), null, "station", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getWeatherReport_Station().getEKeys().add(this.getStation_Id());

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
		initEAttribute(getGeoPosition_Elevation(), ecorePackage.getEShort(), "elevation", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEAttribute(getMOSMIXSWeatherReport_PastWeather(), ecorePackage.getEFloatObject(), "pastWeather", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_SignificantWeather(), ecorePackage.getEFloatObject(), "significantWeather", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast1(), ecorePackage.getEFloatObject(), "fogPropLast1", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast6(), ecorePackage.getEFloatObject(), "fogPropLast6", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMOSMIXSWeatherReport_FogPropLast12(), ecorePackage.getEFloatObject(), "fogPropLast12", null, 0, 1, MOSMIXSWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementWeatherReportEClass, MeasurementWeatherReport.class, "MeasurementWeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurementWeatherReport_Measurements(), this.getMeasurement(), null, "measurements", null, 0, -1, MeasurementWeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //WeatherPackageImpl
