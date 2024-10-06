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
 * A representation of the model object '<em><b>Geo Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.GeoPosition#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GeoPosition#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoPosition()
 * @model
 * @generated
 */
@ProviderType
public interface GeoPosition extends EObject {
	/**
	 * Returns the value of the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latitude</em>' attribute.
	 * @see #setLatitude(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoPosition_Latitude()
	 * @model required="true"
	 * @generated
	 */
	double getLatitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getLatitude <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latitude</em>' attribute.
	 * @see #getLatitude()
	 * @generated
	 */
	void setLatitude(double value);

	/**
	 * Returns the value of the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longitude</em>' attribute.
	 * @see #setLongitude(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoPosition_Longitude()
	 * @model required="true"
	 * @generated
	 */
	double getLongitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getLongitude <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Longitude</em>' attribute.
	 * @see #getLongitude()
	 * @generated
	 */
	void setLongitude(double value);

	/**
	 * Returns the value of the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation</em>' attribute.
	 * @see #setElevation(int)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoPosition_Elevation()
	 * @model required="true"
	 * @generated
	 */
	int getElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation</em>' attribute.
	 * @see #getElevation()
	 * @generated
	 */
	void setElevation(int value);

} // GeoPosition
