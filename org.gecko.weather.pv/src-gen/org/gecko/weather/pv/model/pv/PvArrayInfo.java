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
 * A representation of the model object '<em><b>Array Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Name and orientation of an array, for labels.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getTilt <em>Tilt</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getPeakPower <em>Peak Power</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArrayInfo()
 * @model
 * @generated
 */
@ProviderType
public interface PvArrayInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArrayInfo_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getName <em>Name</em>}' attribute.
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
	 * @return the value of the '<em>Azimuth</em>' attribute.
	 * @see #setAzimuth(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArrayInfo_Azimuth()
	 * @model
	 * @generated
	 */
	double getAzimuth();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getAzimuth <em>Azimuth</em>}' attribute.
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
	 * @return the value of the '<em>Tilt</em>' attribute.
	 * @see #setTilt(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArrayInfo_Tilt()
	 * @model
	 * @generated
	 */
	double getTilt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getTilt <em>Tilt</em>}' attribute.
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
	 * @return the value of the '<em>Peak Power</em>' attribute.
	 * @see #setPeakPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvArrayInfo_PeakPower()
	 * @model
	 * @generated
	 */
	double getPeakPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvArrayInfo#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Power</em>' attribute.
	 * @see #getPeakPower()
	 * @generated
	 */
	void setPeakPower(double value);

} // PvArrayInfo
