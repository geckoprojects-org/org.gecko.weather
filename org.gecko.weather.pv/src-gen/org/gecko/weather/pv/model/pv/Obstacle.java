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
 * A representation of the model object '<em><b>Obstacle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something that hides the sun in a range of directions — a forest, a house, a hill. Its top appears under the angle atan((height − mountingHeight) / distance).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthFrom <em>Azimuth From</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthTo <em>Azimuth To</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getDistance <em>Distance</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getHeight <em>Height</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Obstacle#getLeafOffTransmittance <em>Leaf Off Transmittance</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle()
 * @model
 * @generated
 */
@ProviderType
public interface Obstacle extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Azimuth From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Left edge of the obstacle seen from the plant, clockwise from north.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Azimuth From</em>' attribute.
	 * @see #setAzimuthFrom(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_AzimuthFrom()
	 * @model
	 * @generated
	 */
	double getAzimuthFrom();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthFrom <em>Azimuth From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azimuth From</em>' attribute.
	 * @see #getAzimuthFrom()
	 * @generated
	 */
	void setAzimuthFrom(double value);

	/**
	 * Returns the value of the '<em><b>Azimuth To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Right edge; a range across north (from 330 to 30) is allowed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Azimuth To</em>' attribute.
	 * @see #setAzimuthTo(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_AzimuthTo()
	 * @model
	 * @generated
	 */
	double getAzimuthTo();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getAzimuthTo <em>Azimuth To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azimuth To</em>' attribute.
	 * @see #getAzimuthTo()
	 * @generated
	 */
	void setAzimuthTo(double value);

	/**
	 * Returns the value of the '<em><b>Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Horizontal distance from the modules to the obstacle in m.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Distance</em>' attribute.
	 * @see #setDistance(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_Distance()
	 * @model
	 * @generated
	 */
	double getDistance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getDistance <em>Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distance</em>' attribute.
	 * @see #getDistance()
	 * @generated
	 */
	void setDistance(double value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Height of the obstacle above ground in m.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_Height()
	 * @model
	 * @generated
	 */
	double getHeight();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(double value);

	/**
	 * Returns the value of the '<em><b>Leaf Off Transmittance</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Share of the direct sun that passes the obstacle while it is leafless — a deciduous forest from mid-November to the end of April. 0 for an obstacle that is opaque all year (a house, conifers); about 0.3 for the edge of a bare oak or beech forest.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leaf Off Transmittance</em>' attribute.
	 * @see #setLeafOffTransmittance(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getObstacle_LeafOffTransmittance()
	 * @model default="0.0"
	 * @generated
	 */
	double getLeafOffTransmittance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Obstacle#getLeafOffTransmittance <em>Leaf Off Transmittance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leaf Off Transmittance</em>' attribute.
	 * @see #getLeafOffTransmittance()
	 * @generated
	 */
	void setLeafOffTransmittance(double value);

} // Obstacle
