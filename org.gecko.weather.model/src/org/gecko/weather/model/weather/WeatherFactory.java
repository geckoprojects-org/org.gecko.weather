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

import org.eclipse.emf.ecore.EFactory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage
 * @generated
 */
@ProviderType
public interface WeatherFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WeatherFactory eINSTANCE = org.gecko.weather.model.weather.impl.WeatherFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Station</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Station</em>'.
	 * @generated
	 */
	Station createStation();

	/**
	 * Returns a new object of class '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	WeatherReport createWeatherReport();

	/**
	 * Returns a new object of class '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement</em>'.
	 * @generated
	 */
	Measurement createMeasurement();

	/**
	 * Returns a new object of class '<em>Int Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Int Measurement</em>'.
	 * @generated
	 */
	IntMeasurement createIntMeasurement();

	/**
	 * Returns a new object of class '<em>Float Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Measurement</em>'.
	 * @generated
	 */
	FloatMeasurement createFloatMeasurement();

	/**
	 * Returns a new object of class '<em>Double Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Measurement</em>'.
	 * @generated
	 */
	DoubleMeasurement createDoubleMeasurement();

	/**
	 * Returns a new object of class '<em>Basic Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Basic Measurement</em>'.
	 * @generated
	 */
	BasicMeasurement createBasicMeasurement();

	/**
	 * Returns a new object of class '<em>Bool Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bool Measurement</em>'.
	 * @generated
	 */
	BoolMeasurement createBoolMeasurement();

	/**
	 * Returns a new object of class '<em>Geo Area</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geo Area</em>'.
	 * @generated
	 */
	GeoArea createGeoArea();

	/**
	 * Returns a new object of class '<em>Geo Position</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geo Position</em>'.
	 * @generated
	 */
	GeoPosition createGeoPosition();

	/**
	 * Returns a new object of class '<em>UV Radiation Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UV Radiation Measurement</em>'.
	 * @generated
	 */
	UVRadiationMeasurement createUVRadiationMeasurement();

	/**
	 * Returns a new object of class '<em>MOSMIXS Weather Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MOSMIXS Weather Report</em>'.
	 * @generated
	 */
	MOSMIXSWeatherReport createMOSMIXSWeatherReport();

	/**
	 * Returns a new object of class '<em>Measurement Weather Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement Weather Report</em>'.
	 * @generated
	 */
	MeasurementWeatherReport createMeasurementWeatherReport();

	/**
	 * Returns a new object of class '<em>Astrotime</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Astrotime</em>'.
	 * @generated
	 */
	Astrotime createAstrotime();

	/**
	 * Returns a new object of class '<em>Station</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Station</em>'.
	 * @generated
	 */
	WeatherStation createWeatherStation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WeatherPackage getWeatherPackage();

} //WeatherFactory
