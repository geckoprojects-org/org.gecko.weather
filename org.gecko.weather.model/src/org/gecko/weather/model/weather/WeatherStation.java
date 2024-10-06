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

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Station</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.WeatherStation#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherStation#getIcaoCode <em>Icao Code</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherStation()
 * @model
 * @generated
 */
@ProviderType
public interface WeatherStation extends Station {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DWD id
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherStation_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherStation#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Icao Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Internation Civil Aviation Organization code
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Icao Code</em>' attribute.
	 * @see #setIcaoCode(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherStation_IcaoCode()
	 * @model
	 * @generated
	 */
	String getIcaoCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherStation#getIcaoCode <em>Icao Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icao Code</em>' attribute.
	 * @see #getIcaoCode()
	 * @generated
	 */
	void setIcaoCode(String value);

} // WeatherStation
