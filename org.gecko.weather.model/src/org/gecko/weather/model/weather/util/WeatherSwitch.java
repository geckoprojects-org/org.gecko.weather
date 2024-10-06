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
package org.gecko.weather.model.weather.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.gecko.weather.model.weather.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage
 * @generated
 */
public class WeatherSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WeatherPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherSwitch() {
		if (modelPackage == null) {
			modelPackage = WeatherPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case WeatherPackage.STATION: {
				Station station = (Station)theEObject;
				T result = caseStation(station);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.WEATHER_REPORT: {
				WeatherReport weatherReport = (WeatherReport)theEObject;
				T result = caseWeatherReport(weatherReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.MEASUREMENT: {
				Measurement measurement = (Measurement)theEObject;
				T result = caseMeasurement(measurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.INT_MEASUREMENT: {
				IntMeasurement intMeasurement = (IntMeasurement)theEObject;
				T result = caseIntMeasurement(intMeasurement);
				if (result == null) result = caseMeasurement(intMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.FLOAT_MEASUREMENT: {
				FloatMeasurement floatMeasurement = (FloatMeasurement)theEObject;
				T result = caseFloatMeasurement(floatMeasurement);
				if (result == null) result = caseMeasurement(floatMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.DOUBLE_MEASUREMENT: {
				DoubleMeasurement doubleMeasurement = (DoubleMeasurement)theEObject;
				T result = caseDoubleMeasurement(doubleMeasurement);
				if (result == null) result = caseMeasurement(doubleMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.BASIC_MEASUREMENT: {
				BasicMeasurement basicMeasurement = (BasicMeasurement)theEObject;
				T result = caseBasicMeasurement(basicMeasurement);
				if (result == null) result = caseMeasurement(basicMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.BOOL_MEASUREMENT: {
				BoolMeasurement boolMeasurement = (BoolMeasurement)theEObject;
				T result = caseBoolMeasurement(boolMeasurement);
				if (result == null) result = caseMeasurement(boolMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.GEO_AREA: {
				GeoArea geoArea = (GeoArea)theEObject;
				T result = caseGeoArea(geoArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.GEO_POSITION: {
				GeoPosition geoPosition = (GeoPosition)theEObject;
				T result = caseGeoPosition(geoPosition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.UV_RADIATION_MEASUREMENT: {
				UVRadiationMeasurement uvRadiationMeasurement = (UVRadiationMeasurement)theEObject;
				T result = caseUVRadiationMeasurement(uvRadiationMeasurement);
				if (result == null) result = caseFloatMeasurement(uvRadiationMeasurement);
				if (result == null) result = caseMeasurement(uvRadiationMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.MOSMIXS_WEATHER_REPORT: {
				MOSMIXSWeatherReport mosmixsWeatherReport = (MOSMIXSWeatherReport)theEObject;
				T result = caseMOSMIXSWeatherReport(mosmixsWeatherReport);
				if (result == null) result = caseWeatherReport(mosmixsWeatherReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.MEASUREMENT_WEATHER_REPORT: {
				MeasurementWeatherReport measurementWeatherReport = (MeasurementWeatherReport)theEObject;
				T result = caseMeasurementWeatherReport(measurementWeatherReport);
				if (result == null) result = caseWeatherReport(measurementWeatherReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.ASTROTIME: {
				Astrotime astrotime = (Astrotime)theEObject;
				T result = caseAstrotime(astrotime);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WeatherPackage.WEATHER_STATION: {
				WeatherStation weatherStation = (WeatherStation)theEObject;
				T result = caseWeatherStation(weatherStation);
				if (result == null) result = caseStation(weatherStation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Station</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Station</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStation(Station object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeatherReport(WeatherReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurement(Measurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntMeasurement(IntMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatMeasurement(FloatMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleMeasurement(DoubleMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBasicMeasurement(BasicMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bool Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bool Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoolMeasurement(BoolMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Geo Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Geo Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoArea(GeoArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Geo Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Geo Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoPosition(GeoPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>UV Radiation Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UV Radiation Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUVRadiationMeasurement(UVRadiationMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MOSMIXS Weather Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MOSMIXS Weather Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMOSMIXSWeatherReport(MOSMIXSWeatherReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Weather Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Weather Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementWeatherReport(MeasurementWeatherReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Astrotime</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Astrotime</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAstrotime(Astrotime object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Station</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Station</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeatherStation(WeatherStation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //WeatherSwitch
