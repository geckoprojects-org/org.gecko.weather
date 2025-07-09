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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>W1W2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.W1W2#getW1 <em>W1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.W1W2#getW2 <em>W2</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getW1W2()
 * @model
 * @generated
 */
@ProviderType
public interface W1W2 extends EObject {
	/**
	 * Returns the value of the '<em><b>W1</b></em>' attribute.
	 * The default value is <code>"UNKNOWN"</code>.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.WMOWeatherCodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Significant weather in the past 6 to 3 hours: - (W1 part of W1W2)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>W1</em>' attribute.
	 * @see org.gecko.weather.model.weather.WMOWeatherCodeType
	 * @see #setW1(WMOWeatherCodeType)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getW1W2_W1()
	 * @model default="UNKNOWN"
	 * @generated
	 */
	WMOWeatherCodeType getW1();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.W1W2#getW1 <em>W1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>W1</em>' attribute.
	 * @see org.gecko.weather.model.weather.WMOWeatherCodeType
	 * @see #getW1()
	 * @generated
	 */
	void setW1(WMOWeatherCodeType value);

	/**
	 * Returns the value of the '<em><b>W2</b></em>' attribute.
	 * The default value is <code>"UNKNOWN"</code>.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.WMOWeatherCodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Significant weather in the past 3 hours: - (W2 part of W1W2)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>W2</em>' attribute.
	 * @see org.gecko.weather.model.weather.WMOWeatherCodeType
	 * @see #setW2(WMOWeatherCodeType)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getW1W2_W2()
	 * @model default="UNKNOWN"
	 * @generated
	 */
	WMOWeatherCodeType getW2();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.W1W2#getW2 <em>W2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>W2</em>' attribute.
	 * @see org.gecko.weather.model.weather.WMOWeatherCodeType
	 * @see #getW2()
	 * @generated
	 */
	void setW2(WMOWeatherCodeType value);

} // W1W2
