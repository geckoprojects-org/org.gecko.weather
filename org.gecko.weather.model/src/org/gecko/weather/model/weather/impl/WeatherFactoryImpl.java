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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.weather.model.weather.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WeatherFactoryImpl extends EFactoryImpl implements WeatherFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WeatherFactory init() {
		try {
			WeatherFactory theWeatherFactory = (WeatherFactory)EPackage.Registry.INSTANCE.getEFactory(WeatherPackage.eNS_URI);
			if (theWeatherFactory != null) {
				return theWeatherFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WeatherFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WeatherPackage.STATION: return createStation();
			case WeatherPackage.WEATHER_REPORT: return createWeatherReport();
			case WeatherPackage.MEASUREMENT: return createMeasurement();
			case WeatherPackage.INT_MEASUREMENT: return createIntMeasurement();
			case WeatherPackage.FLOAT_MEASUREMENT: return createFloatMeasurement();
			case WeatherPackage.DOUBLE_MEASUREMENT: return createDoubleMeasurement();
			case WeatherPackage.BASIC_MEASUREMENT: return createBasicMeasurement();
			case WeatherPackage.BOOL_MEASUREMENT: return createBoolMeasurement();
			case WeatherPackage.GEO_AREA: return createGeoArea();
			case WeatherPackage.GEO_POSITION: return createGeoPosition();
			case WeatherPackage.UV_RADIATION_MEASUREMENT: return createUVRadiationMeasurement();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT: return createMOSMIXSWeatherReport();
			case WeatherPackage.MEASUREMENT_WEATHER_REPORT: return createMeasurementWeatherReport();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Station createStation() {
		StationImpl station = new StationImpl();
		return station;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherReport createWeatherReport() {
		WeatherReportImpl weatherReport = new WeatherReportImpl();
		return weatherReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Measurement createMeasurement() {
		MeasurementImpl measurement = new MeasurementImpl();
		return measurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntMeasurement createIntMeasurement() {
		IntMeasurementImpl intMeasurement = new IntMeasurementImpl();
		return intMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatMeasurement createFloatMeasurement() {
		FloatMeasurementImpl floatMeasurement = new FloatMeasurementImpl();
		return floatMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleMeasurement createDoubleMeasurement() {
		DoubleMeasurementImpl doubleMeasurement = new DoubleMeasurementImpl();
		return doubleMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BasicMeasurement createBasicMeasurement() {
		BasicMeasurementImpl basicMeasurement = new BasicMeasurementImpl();
		return basicMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoolMeasurement createBoolMeasurement() {
		BoolMeasurementImpl boolMeasurement = new BoolMeasurementImpl();
		return boolMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoArea createGeoArea() {
		GeoAreaImpl geoArea = new GeoAreaImpl();
		return geoArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoPosition createGeoPosition() {
		GeoPositionImpl geoPosition = new GeoPositionImpl();
		return geoPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UVRadiationMeasurement createUVRadiationMeasurement() {
		UVRadiationMeasurementImpl uvRadiationMeasurement = new UVRadiationMeasurementImpl();
		return uvRadiationMeasurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MOSMIXSWeatherReport createMOSMIXSWeatherReport() {
		MOSMIXSWeatherReportImpl mosmixsWeatherReport = new MOSMIXSWeatherReportImpl();
		return mosmixsWeatherReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MeasurementWeatherReport createMeasurementWeatherReport() {
		MeasurementWeatherReportImpl measurementWeatherReport = new MeasurementWeatherReportImpl();
		return measurementWeatherReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherPackage getWeatherPackage() {
		return (WeatherPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WeatherPackage getPackage() {
		return WeatherPackage.eINSTANCE;
	}

} //WeatherFactoryImpl
