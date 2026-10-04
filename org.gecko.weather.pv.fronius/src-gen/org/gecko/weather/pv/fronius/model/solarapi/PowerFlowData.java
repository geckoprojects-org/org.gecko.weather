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
package org.gecko.weather.pv.fronius.model.solarapi;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Power Flow Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The power flow of the site and its inverters.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getVersion <em>Version</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getSite <em>Site</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getInverters <em>Inverters</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowData()
 * @model
 * @generated
 */
@ProviderType
public interface PowerFlowData extends EObject {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * PowerFlowVersion — which fields to expect (12, 13, …).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowData_Version()
	 * @model annotation="http://eclipse.org/fennec/codec key='Version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Site</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site</em>' containment reference.
	 * @see #setSite(Site)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowData_Site()
	 * @model containment="true"
	 *        annotation="http://eclipse.org/fennec/codec key='Site'"
	 * @generated
	 */
	Site getSite();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getSite <em>Site</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site</em>' containment reference.
	 * @see #getSite()
	 * @generated
	 */
	void setSite(Site value);

	/**
	 * Returns the value of the '<em><b>Inverters</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.gecko.weather.pv.fronius.model.solarapi.Inverter},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The inverters, keyed by their device number as the API writes them: an object with one member per inverter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inverters</em>' map.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getPowerFlowData_Inverters()
	 * @model mapType="org.gecko.weather.pv.fronius.model.solarapi.InverterEntry&lt;org.eclipse.emf.ecore.EString, org.gecko.weather.pv.fronius.model.solarapi.Inverter&gt;"
	 *        annotation="http://eclipse.org/fennec/codec key='Inverters'"
	 * @generated
	 */
	EMap<String, Inverter> getInverters();

} // PowerFlowData
