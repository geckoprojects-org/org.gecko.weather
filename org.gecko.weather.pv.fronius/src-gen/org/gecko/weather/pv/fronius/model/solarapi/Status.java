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
 * A representation of the model object '<em><b>Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Whether the request succeeded: code 0 is OK, anything else an error (Solar API error code table).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getCode <em>Code</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getReason <em>Reason</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getUserMessage <em>User Message</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getStatus()
 * @model
 * @generated
 */
@ProviderType
public interface Status extends EObject {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(int)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getStatus_Code()
	 * @model annotation="http://eclipse.org/fennec/codec key='Code'"
	 * @generated
	 */
	int getCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(int value);

	/**
	 * Returns the value of the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reason</em>' attribute.
	 * @see #setReason(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getStatus_Reason()
	 * @model annotation="http://eclipse.org/fennec/codec key='Reason'"
	 * @generated
	 */
	String getReason();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getReason <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reason</em>' attribute.
	 * @see #getReason()
	 * @generated
	 */
	void setReason(String value);

	/**
	 * Returns the value of the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Message</em>' attribute.
	 * @see #setUserMessage(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getStatus_UserMessage()
	 * @model annotation="http://eclipse.org/fennec/codec key='UserMessage'"
	 * @generated
	 */
	String getUserMessage();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getUserMessage <em>User Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Message</em>' attribute.
	 * @see #getUserMessage()
	 * @generated
	 */
	void setUserMessage(String value);

} // Status
