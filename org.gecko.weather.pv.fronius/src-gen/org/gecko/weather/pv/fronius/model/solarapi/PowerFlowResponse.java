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
 * A representation of the model object '<em><b>Power Flow Response</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Answer of /solar_api/v1/GetPowerFlowRealtimeData.fcgi.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getHead <em>Head</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowResponse()
 * @model
 * @generated
 */
@ProviderType
public interface PowerFlowResponse extends EObject {
	/**
	 * Returns the value of the '<em><b>Head</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Head</em>' containment reference.
	 * @see #setHead(Head)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowResponse_Head()
	 * @model containment="true"
	 *        annotation="http://eclipse.org/fennec/codec key='Head'"
	 * @generated
	 */
	Head getHead();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getHead <em>Head</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Head</em>' containment reference.
	 * @see #getHead()
	 * @generated
	 */
	void setHead(Head value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(PowerFlowBody)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowResponse_Body()
	 * @model containment="true"
	 *        annotation="http://eclipse.org/fennec/codec key='Body'"
	 * @generated
	 */
	PowerFlowBody getBody();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(PowerFlowBody value);

} // PowerFlowResponse
