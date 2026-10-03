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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Station</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A weather station as listed in a provider's catalogue.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Station#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Station#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Station#getIcaoCode <em>Icao Code</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Station#getCountry <em>Country</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Station#getPosition <em>Position</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getStation()
 * @model
 * @generated
 */
@ProviderType
public interface Station extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The provider's station id, e.g. the MOSMIX id 10385.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStation_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Station#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Station#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Icao Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icao Code</em>' attribute.
	 * @see #setIcaoCode(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStation_IcaoCode()
	 * @model
	 * @generated
	 */
	String getIcaoCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Station#getIcaoCode <em>Icao Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icao Code</em>' attribute.
	 * @see #getIcaoCode()
	 * @generated
	 */
	void setIcaoCode(String value);

	/**
	 * Returns the value of the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Country</em>' attribute.
	 * @see #setCountry(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStation_Country()
	 * @model
	 * @generated
	 */
	String getCountry();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Station#getCountry <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Country</em>' attribute.
	 * @see #getCountry()
	 * @generated
	 */
	void setCountry(String value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' containment reference.
	 * @see #setPosition(GeoPosition)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStation_Position()
	 * @model containment="true" required="true"
	 * @generated
	 */
	GeoPosition getPosition();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Station#getPosition <em>Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' containment reference.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(GeoPosition value);

} // Station
