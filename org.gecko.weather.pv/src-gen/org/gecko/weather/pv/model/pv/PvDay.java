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

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Day</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One calendar day in the plant's time zone.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getEnergy <em>Energy</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getPeakTime <em>Peak Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getSpecificYield <em>Specific Yield</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getHoursCovered <em>Hours Covered</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvDay#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay()
 * @model
 * @generated
 */
@ProviderType
public interface PvDay extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ISO local date.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_Date()
	 * @model
	 * @generated
	 */
	String getDate();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(String value);

	/**
	 * Returns the value of the '<em><b>Energy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kWh over the day.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Energy</em>' attribute.
	 * @see #isSetEnergy()
	 * @see #unsetEnergy()
	 * @see #setEnergy(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_Energy()
	 * @model unsettable="true"
	 * @generated
	 */
	double getEnergy();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getEnergy <em>Energy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy</em>' attribute.
	 * @see #isSetEnergy()
	 * @see #unsetEnergy()
	 * @see #getEnergy()
	 * @generated
	 */
	void setEnergy(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getEnergy <em>Energy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnergy()
	 * @see #getEnergy()
	 * @see #setEnergy(double)
	 * @generated
	 */
	void unsetEnergy();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getEnergy <em>Energy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Energy</em>' attribute is set.
	 * @see #unsetEnergy()
	 * @see #getEnergy()
	 * @see #setEnergy(double)
	 * @generated
	 */
	boolean isSetEnergy();

	/**
	 * Returns the value of the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Highest hourly mean power, kW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Peak Power</em>' attribute.
	 * @see #isSetPeakPower()
	 * @see #unsetPeakPower()
	 * @see #setPeakPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_PeakPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPeakPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Power</em>' attribute.
	 * @see #isSetPeakPower()
	 * @see #unsetPeakPower()
	 * @see #getPeakPower()
	 * @generated
	 */
	void setPeakPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeakPower()
	 * @see #getPeakPower()
	 * @see #setPeakPower(double)
	 * @generated
	 */
	void unsetPeakPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakPower <em>Peak Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Peak Power</em>' attribute is set.
	 * @see #unsetPeakPower()
	 * @see #getPeakPower()
	 * @see #setPeakPower(double)
	 * @generated
	 */
	boolean isSetPeakPower();

	/**
	 * Returns the value of the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peak Time</em>' attribute.
	 * @see #setPeakTime(Date)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_PeakTime()
	 * @model
	 * @generated
	 */
	Date getPeakTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getPeakTime <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Time</em>' attribute.
	 * @see #getPeakTime()
	 * @generated
	 */
	void setPeakTime(Date value);

	/**
	 * Returns the value of the '<em><b>Specific Yield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * energy / peakPower of the plant, kWh/kWp — comparable between plants.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specific Yield</em>' attribute.
	 * @see #isSetSpecificYield()
	 * @see #unsetSpecificYield()
	 * @see #setSpecificYield(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_SpecificYield()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSpecificYield();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getSpecificYield <em>Specific Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specific Yield</em>' attribute.
	 * @see #isSetSpecificYield()
	 * @see #unsetSpecificYield()
	 * @see #getSpecificYield()
	 * @generated
	 */
	void setSpecificYield(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getSpecificYield <em>Specific Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSpecificYield()
	 * @see #getSpecificYield()
	 * @see #setSpecificYield(double)
	 * @generated
	 */
	void unsetSpecificYield();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getSpecificYield <em>Specific Yield</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Specific Yield</em>' attribute is set.
	 * @see #unsetSpecificYield()
	 * @see #getSpecificYield()
	 * @see #setSpecificYield(double)
	 * @generated
	 */
	boolean isSetSpecificYield();

	/**
	 * Returns the value of the '<em><b>Hours Covered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hours with weather data among the day's daylight hours; fewer than all means the day is not fully known yet.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hours Covered</em>' attribute.
	 * @see #setHoursCovered(int)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_HoursCovered()
	 * @model
	 * @generated
	 */
	int getHoursCovered();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getHoursCovered <em>Hours Covered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hours Covered</em>' attribute.
	 * @see #getHoursCovered()
	 * @generated
	 */
	void setHoursCovered(int value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvDay_Source()
	 * @model
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvDay#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

} // PvDay
