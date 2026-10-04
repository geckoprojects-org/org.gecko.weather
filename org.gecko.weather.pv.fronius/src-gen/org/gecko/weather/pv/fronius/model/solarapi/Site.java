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
 * A representation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The site as a whole.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMode <em>Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBatteryStandby <em>Battery Standby</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBackupMode <em>Backup Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerGrid <em>Power Grid</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerLoad <em>Power Load</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerBattery <em>Power Battery</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerPv <em>Power Pv</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeSelfConsumption <em>Relative Self Consumption</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeAutonomy <em>Relative Autonomy</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMeterLocation <em>Meter Location</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyDay <em>Energy Day</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyYear <em>Energy Year</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite()
 * @model
 * @generated
 */
@ProviderType
public interface Site extends EObject {
	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * produce-only, meter, vague-meter, bidirectional or ac-coupled.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see #setMode(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_Mode()
	 * @model annotation="http://eclipse.org/fennec/codec key='Mode'"
	 * @generated
	 */
	String getMode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see #getMode()
	 * @generated
	 */
	void setMode(String value);

	/**
	 * Returns the value of the '<em><b>Battery Standby</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Battery Standby</em>' attribute.
	 * @see #setBatteryStandby(Boolean)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_BatteryStandby()
	 * @model annotation="http://eclipse.org/fennec/codec key='BatteryStandby'"
	 * @generated
	 */
	Boolean getBatteryStandby();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBatteryStandby <em>Battery Standby</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Standby</em>' attribute.
	 * @see #getBatteryStandby()
	 * @generated
	 */
	void setBatteryStandby(Boolean value);

	/**
	 * Returns the value of the '<em><b>Backup Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Backup Mode</em>' attribute.
	 * @see #setBackupMode(Boolean)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_BackupMode()
	 * @model annotation="http://eclipse.org/fennec/codec key='BackupMode'"
	 * @generated
	 */
	Boolean getBackupMode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBackupMode <em>Backup Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Backup Mode</em>' attribute.
	 * @see #getBackupMode()
	 * @generated
	 */
	void setBackupMode(Boolean value);

	/**
	 * Returns the value of the '<em><b>Power Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * W, positive when drawing from the grid; null without meter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power Grid</em>' attribute.
	 * @see #setPowerGrid(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_PowerGrid()
	 * @model annotation="http://eclipse.org/fennec/codec key='P_Grid'"
	 * @generated
	 */
	Double getPowerGrid();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerGrid <em>Power Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Grid</em>' attribute.
	 * @see #getPowerGrid()
	 * @generated
	 */
	void setPowerGrid(Double value);

	/**
	 * Returns the value of the '<em><b>Power Load</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * W, negative for consumption; null without meter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power Load</em>' attribute.
	 * @see #setPowerLoad(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_PowerLoad()
	 * @model annotation="http://eclipse.org/fennec/codec key='P_Load'"
	 * @generated
	 */
	Double getPowerLoad();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerLoad <em>Power Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Load</em>' attribute.
	 * @see #getPowerLoad()
	 * @generated
	 */
	void setPowerLoad(Double value);

	/**
	 * Returns the value of the '<em><b>Power Battery</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * W, positive when discharging; null without active battery.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power Battery</em>' attribute.
	 * @see #setPowerBattery(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_PowerBattery()
	 * @model annotation="http://eclipse.org/fennec/codec key='P_Akku'"
	 * @generated
	 */
	Double getPowerBattery();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerBattery <em>Power Battery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Battery</em>' attribute.
	 * @see #getPowerBattery()
	 * @generated
	 */
	void setPowerBattery(Double value);

	/**
	 * Returns the value of the '<em><b>Power Pv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * W of the PV generator — DC side on GEN24 and Hybrid; null while the inverter is not running.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power Pv</em>' attribute.
	 * @see #setPowerPv(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_PowerPv()
	 * @model annotation="http://eclipse.org/fennec/codec key='P_PV'"
	 * @generated
	 */
	Double getPowerPv();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerPv <em>Power Pv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Pv</em>' attribute.
	 * @see #getPowerPv()
	 * @generated
	 */
	void setPowerPv(Double value);

	/**
	 * Returns the value of the '<em><b>Relative Self Consumption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Self Consumption</em>' attribute.
	 * @see #setRelativeSelfConsumption(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_RelativeSelfConsumption()
	 * @model annotation="http://eclipse.org/fennec/codec key='rel_SelfConsumption'"
	 * @generated
	 */
	Double getRelativeSelfConsumption();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeSelfConsumption <em>Relative Self Consumption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Self Consumption</em>' attribute.
	 * @see #getRelativeSelfConsumption()
	 * @generated
	 */
	void setRelativeSelfConsumption(Double value);

	/**
	 * Returns the value of the '<em><b>Relative Autonomy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Autonomy</em>' attribute.
	 * @see #setRelativeAutonomy(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_RelativeAutonomy()
	 * @model annotation="http://eclipse.org/fennec/codec key='rel_Autonomy'"
	 * @generated
	 */
	Double getRelativeAutonomy();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeAutonomy <em>Relative Autonomy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Autonomy</em>' attribute.
	 * @see #getRelativeAutonomy()
	 * @generated
	 */
	void setRelativeAutonomy(Double value);

	/**
	 * Returns the value of the '<em><b>Meter Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * load, grid or unknown.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meter Location</em>' attribute.
	 * @see #setMeterLocation(String)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_MeterLocation()
	 * @model annotation="http://eclipse.org/fennec/codec key='Meter_Location'"
	 * @generated
	 */
	String getMeterLocation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMeterLocation <em>Meter Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meter Location</em>' attribute.
	 * @see #getMeterLocation()
	 * @generated
	 */
	void setMeterLocation(String value);

	/**
	 * Returns the value of the '<em><b>Energy Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Wh today; always null on GEN24.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Energy Day</em>' attribute.
	 * @see #setEnergyDay(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_EnergyDay()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Day'"
	 * @generated
	 */
	Double getEnergyDay();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyDay <em>Energy Day</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Wh this year; always null on GEN24.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Energy Year</em>' attribute.
	 * @see #setEnergyYear(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_EnergyYear()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Year'"
	 * @generated
	 */
	Double getEnergyYear();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyYear <em>Energy Year</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Wh ever; on GEN24 updated every 5 minutes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Energy Total</em>' attribute.
	 * @see #setEnergyTotal(Double)
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#getSite_EnergyTotal()
	 * @model annotation="http://eclipse.org/fennec/codec key='E_Total'"
	 * @generated
	 */
	Double getEnergyTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyTotal <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy Total</em>' attribute.
	 * @see #getEnergyTotal()
	 * @generated
	 */
	void setEnergyTotal(Double value);

} // Site
