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
package org.gecko.weather.pv.fronius.model.solarapi;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Head</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Common response header of every Solar API answer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getStatus <em>Status</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getTimestamp <em>Timestamp</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getHead()
 * @model
 * @generated
 */
@ProviderType
public interface Head extends EObject {
	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' containment reference.
	 * @see #setStatus(Status)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getHead_Status()
	 * @model containment="true"
	 *        annotation="http://eclipse.org/fennec/codec key='Status'"
	 * @generated
	 */
	Status getStatus();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getStatus <em>Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' containment reference.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(Status value);

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RFC 3339 time of the answer in the device&apos;s local time — not when it measured.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getHead_Timestamp()
	 * @model annotation="http://eclipse.org/fennec/codec key='Timestamp'"
	 * @generated
	 */
	String getTimestamp();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(String value);

} // Head
