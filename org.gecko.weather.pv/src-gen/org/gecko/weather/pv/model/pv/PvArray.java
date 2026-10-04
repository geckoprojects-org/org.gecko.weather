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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One orientation of modules — a roof face or a string — with its own azimuth and tilt.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getTilt <em>Tilt</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getModuleCount <em>Module Count</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getTemperatureCoefficient <em>Temperature Coefficient</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getMounting <em>Mounting</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArray#getInverter <em>Inverter</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray()
 * @model
 * @generated
 */
@ProviderType
public interface PvArray extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Where the modules face, clockwise from north: 90 east, 180 south, 270 west.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Azimuth</em>' attribute.
	 * @see #setAzimuth(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_Azimuth()
	 * @model
	 * @generated
	 */
	double getAzimuth();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getAzimuth <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azimuth</em>' attribute.
	 * @see #getAzimuth()
	 * @generated
	 */
	void setAzimuth(double value);

	/**
	 * Returns the value of the '<em><b>Tilt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Angle against the horizontal: 0 flat, 90 a facade.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tilt</em>' attribute.
	 * @see #setTilt(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_Tilt()
	 * @model
	 * @generated
	 */
	double getTilt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getTilt <em>Tilt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tilt</em>' attribute.
	 * @see #getTilt()
	 * @generated
	 */
	void setTilt(double value);

	/**
	 * Returns the value of the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kWp under standard test conditions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Peak Power</em>' attribute.
	 * @see #setPeakPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_PeakPower()
	 * @model
	 * @generated
	 */
	double getPeakPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Power</em>' attribute.
	 * @see #getPeakPower()
	 * @generated
	 */
	void setPeakPower(double value);

	/**
	 * Returns the value of the '<em><b>Module Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Count</em>' attribute.
	 * @see #isSetModuleCount()
	 * @see #unsetModuleCount()
	 * @see #setModuleCount(int)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_ModuleCount()
	 * @model unsettable="true"
	 * @generated
	 */
	int getModuleCount();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getModuleCount <em>Module Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Count</em>' attribute.
	 * @see #isSetModuleCount()
	 * @see #unsetModuleCount()
	 * @see #getModuleCount()
	 * @generated
	 */
	void setModuleCount(int value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getModuleCount <em>Module Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetModuleCount()
	 * @see #getModuleCount()
	 * @see #setModuleCount(int)
	 * @generated
	 */
	void unsetModuleCount();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getModuleCount <em>Module Count</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Module Count</em>' attribute is set.
	 * @see #unsetModuleCount()
	 * @see #getModuleCount()
	 * @see #setModuleCount(int)
	 * @generated
	 */
	boolean isSetModuleCount();

	/**
	 * Returns the value of the '<em><b>Temperature Coefficient</b></em>' attribute.
	 * The default value is <code>"-0.37"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Power change per kelvin above 25 °C cell temperature, in %/K (data sheet, Pmax), typically −0.30 … −0.45.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temperature Coefficient</em>' attribute.
	 * @see #setTemperatureCoefficient(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_TemperatureCoefficient()
	 * @model default="-0.37"
	 * @generated
	 */
	double getTemperatureCoefficient();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getTemperatureCoefficient <em>Temperature Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature Coefficient</em>' attribute.
	 * @see #getTemperatureCoefficient()
	 * @generated
	 */
	void setTemperatureCoefficient(double value);

	/**
	 * Returns the value of the '<em><b>Mounting</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.pv.model.pv.Mounting}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mounting</em>' attribute.
	 * @see org.gecko.weather.pv.model.pv.Mounting
	 * @see #setMounting(Mounting)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_Mounting()
	 * @model
	 * @generated
	 */
	Mounting getMounting();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getMounting <em>Mounting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mounting</em>' attribute.
	 * @see org.gecko.weather.pv.model.pv.Mounting
	 * @see #getMounting()
	 * @generated
	 */
	void setMounting(Mounting value);

	/**
	 * Returns the value of the '<em><b>Inverter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The inverter the array feeds; without one, an unlimited inverter of 96 % is assumed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inverter</em>' reference.
	 * @see #setInverter(Inverter)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArray_Inverter()
	 * @model
	 * @generated
	 */
	Inverter getInverter();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArray#getInverter <em>Inverter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inverter</em>' reference.
	 * @see #getInverter()
	 * @generated
	 */
	void setInverter(Inverter value);

} // PvArray
