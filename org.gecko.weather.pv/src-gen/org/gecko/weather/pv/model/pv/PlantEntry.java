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
 * A representation of the model object '<em><b>Plant Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A plant in the directory.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantEntry#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantEntry#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantEntry#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantEntry#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantEntry#getTimeZone <em>Time Zone</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry()
 * @model
 * @generated
 */
@ProviderType
public interface PlantEntry extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PlantEntry#getId <em>Id</em>}' attribute.
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
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PlantEntry#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site Id</em>' attribute.
	 * @see #setSiteId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry_SiteId()
	 * @model
	 * @generated
	 */
	String getSiteId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PlantEntry#getSiteId <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Id</em>' attribute.
	 * @see #getSiteId()
	 * @generated
	 */
	void setSiteId(String value);

	/**
	 * Returns the value of the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sum over the arrays, kWp.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Peak Power</em>' attribute.
	 * @see #setPeakPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry_PeakPower()
	 * @model
	 * @generated
	 */
	double getPeakPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PlantEntry#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Power</em>' attribute.
	 * @see #getPeakPower()
	 * @generated
	 */
	void setPeakPower(double value);

	/**
	 * Returns the value of the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Zone</em>' attribute.
	 * @see #setTimeZone(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantEntry_TimeZone()
	 * @model
	 * @generated
	 */
	String getTimeZone();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PlantEntry#getTimeZone <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Zone</em>' attribute.
	 * @see #getTimeZone()
	 * @generated
	 */
	void setTimeZone(String value);

} // PlantEntry
