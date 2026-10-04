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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Log</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The readings of one plant on one local day — stored one XMI file per plant and day.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getPlantId <em>Plant Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getMeterType <em>Meter Type</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getMeasurements <em>Measurements</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurementLog()
 * @model
 * @generated
 */
@ProviderType
public interface PvMeasurementLog extends EObject {
	/**
	 * Returns the value of the '<em><b>Plant Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plant Id</em>' attribute.
	 * @see #setPlantId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurementLog_PlantId()
	 * @model
	 * @generated
	 */
	String getPlantId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getPlantId <em>Plant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plant Id</em>' attribute.
	 * @see #getPlantId()
	 * @generated
	 */
	void setPlantId(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Local date, ISO yyyy-MM-dd.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurementLog_Date()
	 * @model
	 * @generated
	 */
	String getDate();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(String value);

	/**
	 * Returns the value of the '<em><b>Meter Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meter Type</em>' attribute.
	 * @see #setMeterType(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurementLog_MeterType()
	 * @model
	 * @generated
	 */
	String getMeterType();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurementLog#getMeterType <em>Meter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meter Type</em>' attribute.
	 * @see #getMeterType()
	 * @generated
	 */
	void setMeterType(String value);

	/**
	 * Returns the value of the '<em><b>Measurements</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PvMeasurement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurements</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurementLog_Measurements()
	 * @model containment="true"
	 * @generated
	 */
	EList<PvMeasurement> getMeasurements();

} // PvMeasurementLog
