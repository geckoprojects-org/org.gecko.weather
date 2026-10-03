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
package org.gecko.weather.model.weather;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derivation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Derivation#getFunctionId <em>Function Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Derivation#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getDerivation()
 * @model
 * @generated
 */
@ProviderType
public interface Derivation extends EObject {
	/**
	 * Returns the value of the '<em><b>Function Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifier and version of the computation, e.g. solar.position/time4j-5.9.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Id</em>' attribute.
	 * @see #setFunctionId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDerivation_FunctionId()
	 * @model required="true"
	 * @generated
	 */
	String getFunctionId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Derivation#getFunctionId <em>Function Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Id</em>' attribute.
	 * @see #getFunctionId()
	 * @generated
	 */
	void setFunctionId(String value);

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Descriptors of the inputs, enough to recompute the value: site position, instant, or references to other values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inputs</em>' attribute list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDerivation_Inputs()
	 * @model
	 * @generated
	 */
	EList<String> getInputs();

} // Derivation
