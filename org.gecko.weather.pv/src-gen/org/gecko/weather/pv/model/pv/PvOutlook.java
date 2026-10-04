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

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outlook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * What a plant is expected to produce: hours from the current full hour on, days from today on.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantId <em>Plant Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantName <em>Plant Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getHours <em>Hours</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getDays <em>Days</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvOutlook#getArrays <em>Arrays</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook()
 * @model
 * @generated
 */
@ProviderType
public interface PvOutlook extends EObject {
	/**
	 * Returns the value of the '<em><b>Plant Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plant Id</em>' attribute.
	 * @see #setPlantId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_PlantId()
	 * @model
	 * @generated
	 */
	String getPlantId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantId <em>Plant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plant Id</em>' attribute.
	 * @see #getPlantId()
	 * @generated
	 */
	void setPlantId(String value);

	/**
	 * Returns the value of the '<em><b>Plant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plant Name</em>' attribute.
	 * @see #setPlantName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_PlantName()
	 * @model
	 * @generated
	 */
	String getPlantName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPlantName <em>Plant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plant Name</em>' attribute.
	 * @see #getPlantName()
	 * @generated
	 */
	void setPlantName(String value);

	/**
	 * Returns the value of the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site Id</em>' attribute.
	 * @see #setSiteId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_SiteId()
	 * @model
	 * @generated
	 */
	String getSiteId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getSiteId <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Id</em>' attribute.
	 * @see #getSiteId()
	 * @generated
	 */
	void setSiteId(String value);

	/**
	 * Returns the value of the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Zone</em>' attribute.
	 * @see #setTimeZone(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_TimeZone()
	 * @model
	 * @generated
	 */
	String getTimeZone();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getTimeZone <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Zone</em>' attribute.
	 * @see #getTimeZone()
	 * @generated
	 */
	void setTimeZone(String value);

	/**
	 * Returns the value of the '<em><b>Peak Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peak Power</em>' attribute.
	 * @see #setPeakPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_PeakPower()
	 * @model
	 * @generated
	 */
	double getPeakPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getPeakPower <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Power</em>' attribute.
	 * @see #getPeakPower()
	 * @generated
	 */
	void setPeakPower(double value);

	/**
	 * Returns the value of the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated At</em>' attribute.
	 * @see #setGeneratedAt(Date)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_GeneratedAt()
	 * @model
	 * @generated
	 */
	Date getGeneratedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvOutlook#getGeneratedAt <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated At</em>' attribute.
	 * @see #getGeneratedAt()
	 * @generated
	 */
	void setGeneratedAt(Date value);

	/**
	 * Returns the value of the '<em><b>Hours</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PvHour}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hours</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_Hours()
	 * @model containment="true"
	 * @generated
	 */
	EList<PvHour> getHours();

	/**
	 * Returns the value of the '<em><b>Days</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PvDay}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Days</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_Days()
	 * @model containment="true"
	 * @generated
	 */
	EList<PvDay> getDays();

	/**
	 * Returns the value of the '<em><b>Arrays</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PvArrayInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The arrays in the order their hourly values are given.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arrays</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvOutlook_Arrays()
	 * @model containment="true"
	 * @generated
	 */
	EList<PvArrayInfo> getArrays();

} // PvOutlook
