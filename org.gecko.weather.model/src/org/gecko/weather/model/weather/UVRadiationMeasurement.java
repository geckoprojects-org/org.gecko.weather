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
 * A representation of the model object '<em><b>UV Radiation Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.UVRadiationMeasurement#getArea <em>Area</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getUVRadiationMeasurement()
 * @model
 * @generated
 */
@ProviderType
public interface UVRadiationMeasurement extends FloatMeasurement {
	/**
	 * Returns the value of the '<em><b>Area</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Area</em>' containment reference.
	 * @see #setArea(GeoArea)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUVRadiationMeasurement_Area()
	 * @model containment="true"
	 * @generated
	 */
	GeoArea getArea();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.UVRadiationMeasurement#getArea <em>Area</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area</em>' containment reference.
	 * @see #getArea()
	 * @generated
	 */
	void setArea(GeoArea value);

} // UVRadiationMeasurement
