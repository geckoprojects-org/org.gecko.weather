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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inverter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One inverter in the power flow.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getDeviceType <em>Device Type</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getPower <em>Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getStateOfCharge <em>State Of Charge</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getComponentId <em>Component Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getBatteryMode <em>Battery Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyDay <em>Energy Day</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyYear <em>Energy Year</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter()
 * @model
 * @generated
 */
@ProviderType
public interface Inverter extends EObject {
	/**
	 * Returns the value of the '<em><b>Device Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Device type; GEN24 and Tauro report 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Device Type</em>' attribute.
	 * @see #setDeviceType(Integer)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_DeviceType()
	 * @model annotation="http://eclipse.org/fennec/codec key='DT'"
	 * @generated
	 */
	Integer getDeviceType();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getDeviceType <em>Device Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Type</em>' attribute.
	 * @see #getDeviceType()
	 * @generated
	 */
	void setDeviceType(Integer value);

	/**
	 * Returns the value of the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * AC power in W, null if not running.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power</em>' attribute.
	 * @see #setPower(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_Power()
	 * @model annotation="http://eclipse.org/fennec/codec key='P'"
	 * @generated
	 */
	Double getPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getPower <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power</em>' attribute.
	 * @see #getPower()
	 * @generated
	 */
	void setPower(Double value);

	/**
	 * Returns the value of the '<em><b>State Of Charge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Battery state of charge in %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>State Of Charge</em>' attribute.
	 * @see #setStateOfCharge(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_StateOfCharge()
	 * @model annotation="http://eclipse.org/fennec/codec key='SOC'"
	 * @generated
	 */
	Double getStateOfCharge();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getStateOfCharge <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Of Charge</em>' attribute.
	 * @see #getStateOfCharge()
	 * @generated
	 */
	void setStateOfCharge(Double value);

	/**
	 * Returns the value of the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Id</em>' attribute.
	 * @see #setComponentId(Long)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_ComponentId()
	 * @model annotation="http://eclipse.org/fennec/codec key='CID'"
	 * @generated
	 */
	Long getComponentId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getComponentId <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Id</em>' attribute.
	 * @see #getComponentId()
	 * @generated
	 */
	void setComponentId(Long value);

	/**
	 * Returns the value of the '<em><b>Battery Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Battery Mode</em>' attribute.
	 * @see #setBatteryMode(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_BatteryMode()
	 * @model annotation="http://eclipse.org/fennec/codec key='Battery_Mode'"
	 * @generated
	 */
	String getBatteryMode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getBatteryMode <em>Battery Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Mode</em>' attribute.
	 * @see #getBatteryMode()
	 * @generated
	 */
	void setBatteryMode(String value);

	/**
	 * Returns the value of the '<em><b>Energy Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Energy Day</em>' attribute.
	 * @see #setEnergyDay(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_EnergyDay()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Day'"
	 * @generated
	 */
	Double getEnergyDay();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyDay <em>Energy Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy Day</em>' attribute.
	 * @see #getEnergyDay()
	 * @generated
	 */
	void setEnergyDay(Double value);

	/**
	 * Returns the value of the '<em><b>Energy Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Energy Year</em>' attribute.
	 * @see #setEnergyYear(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_EnergyYear()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Year'"
	 * @generated
	 */
	Double getEnergyYear();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyYear <em>Energy Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy Year</em>' attribute.
	 * @see #getEnergyYear()
	 * @generated
	 */
	void setEnergyYear(Double value);

	/**
	 * Returns the value of the '<em><b>Energy Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Energy Total</em>' attribute.
	 * @see #setEnergyTotal(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getInverter_EnergyTotal()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Total'"
	 * @generated
	 */
	Double getEnergyTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyTotal <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy Total</em>' attribute.
	 * @see #getEnergyTotal()
	 * @generated
	 */
	void setEnergyTotal(Double value);

} // Inverter
