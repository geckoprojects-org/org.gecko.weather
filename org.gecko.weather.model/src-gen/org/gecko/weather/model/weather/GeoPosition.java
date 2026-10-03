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
 * A representation of the model object '<em><b>Geo Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * WGS84 position. Latitude north-positive, longitude east-positive, both in decimal degrees; elevation in metres above mean sea level.
 * <!-- end-model-doc -->
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
	 * @see #isSetElevation()
	 * @see #unsetElevation()
	 * @see #setElevation(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoPosition_Elevation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation</em>' attribute.
	 * @see #isSetElevation()
	 * @see #unsetElevation()
	 * @see #getElevation()
	 * @generated
	 */
	void setElevation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetElevation()
	 * @see #getElevation()
	 * @see #setElevation(double)
	 * @generated
	 */
	void unsetElevation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.GeoPosition#getElevation <em>Elevation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Elevation</em>' attribute is set.
	 * @see #unsetElevation()
	 * @see #getElevation()
	 * @see #setElevation(double)
	 * @generated
	 */
	boolean isSetElevation();

} // GeoPosition
