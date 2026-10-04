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
 * A representation of the model object '<em><b>Inverter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Turns the arrays' DC into AC — with a conversion efficiency and a ceiling.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.Inverter#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Inverter#getAcPower <em>Ac Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Inverter#getEfficiency <em>Efficiency</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getInverter()
 * @model
 * @generated
 */
@ProviderType
public interface Inverter extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getInverter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Inverter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Ac Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rated AC power in kW; output above it is clipped.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ac Power</em>' attribute.
	 * @see #setAcPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getInverter_AcPower()
	 * @model
	 * @generated
	 */
	double getAcPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Inverter#getAcPower <em>Ac Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ac Power</em>' attribute.
	 * @see #getAcPower()
	 * @generated
	 */
	void setAcPower(double value);

	/**
	 * Returns the value of the '<em><b>Efficiency</b></em>' attribute.
	 * The default value is <code>"0.96"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean conversion efficiency, 0..1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Efficiency</em>' attribute.
	 * @see #setEfficiency(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getInverter_Efficiency()
	 * @model default="0.96"
	 * @generated
	 */
	double getEfficiency();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Inverter#getEfficiency <em>Efficiency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Efficiency</em>' attribute.
	 * @see #getEfficiency()
	 * @generated
	 */
	void setEfficiency(double value);

} // Inverter
