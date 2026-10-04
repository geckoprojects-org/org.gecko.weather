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
package org.gecko.weather.pv.model.pv;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Horizon Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One point of a measured horizon line; between points the elevation is interpolated.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.HorizonPoint#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.HorizonPoint#getElevation <em>Elevation</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getHorizonPoint()
 * @model
 * @generated
 */
@ProviderType
public interface HorizonPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Azimuth</em>' attribute.
	 * @see #setAzimuth(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getHorizonPoint_Azimuth()
	 * @model
	 * @generated
	 */
	double getAzimuth();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.HorizonPoint#getAzimuth <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azimuth</em>' attribute.
	 * @see #getAzimuth()
	 * @generated
	 */
	void setAzimuth(double value);

	/**
	 * Returns the value of the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation</em>' attribute.
	 * @see #setElevation(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getHorizonPoint_Elevation()
	 * @model
	 * @generated
	 */
	double getElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.HorizonPoint#getElevation <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation</em>' attribute.
	 * @see #getElevation()
	 * @generated
	 */
	void setElevation(double value);

} // HorizonPoint
