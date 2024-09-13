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
 * A representation of the model object '<em><b>Geo Area</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.GeoArea#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GeoArea#getXDim <em>XDim</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GeoArea#getYDim <em>YDim</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoArea()
 * @model
 * @generated
 */
@ProviderType
public interface GeoArea extends EObject {
	/**
	 * Returns the value of the '<em><b>Top Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Top Left</em>' reference.
	 * @see #setTopLeft(GeoPosition)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoArea_TopLeft()
	 * @model
	 * @generated
	 */
	GeoPosition getTopLeft();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoArea#getTopLeft <em>Top Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Top Left</em>' reference.
	 * @see #getTopLeft()
	 * @generated
	 */
	void setTopLeft(GeoPosition value);

	/**
	 * Returns the value of the '<em><b>XDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XDim</em>' attribute.
	 * @see #setXDim(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoArea_XDim()
	 * @model
	 * @generated
	 */
	double getXDim();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoArea#getXDim <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XDim</em>' attribute.
	 * @see #getXDim()
	 * @generated
	 */
	void setXDim(double value);

	/**
	 * Returns the value of the '<em><b>YDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YDim</em>' attribute.
	 * @see #setYDim(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGeoArea_YDim()
	 * @model
	 * @generated
	 */
	double getYDim();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GeoArea#getYDim <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YDim</em>' attribute.
	 * @see #getYDim()
	 * @generated
	 */
	void setYDim(double value);

} // GeoArea
