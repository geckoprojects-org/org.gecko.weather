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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One reading of a plant's power flow. Powers in kW, all unset when the device did not report them. Signs: gridPower positive when drawing from the grid, batteryPower positive when the battery discharges, loadPower positive for consumption.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getPvPower <em>Pv Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getAcPower <em>Ac Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getLoadPower <em>Load Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getGridPower <em>Grid Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getBatteryPower <em>Battery Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getStateOfCharge <em>State Of Charge</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvMeasurement#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement()
 * @model
 * @generated
 */
@ProviderType
public interface PvMeasurement extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the reading was taken, by the runtime's clock.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Pv Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Power of the PV generator; on Fronius hybrid and GEN24 inverters the DC side.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pv Power</em>' attribute.
	 * @see #isSetPvPower()
	 * @see #unsetPvPower()
	 * @see #setPvPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_PvPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPvPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getPvPower <em>Pv Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pv Power</em>' attribute.
	 * @see #isSetPvPower()
	 * @see #unsetPvPower()
	 * @see #getPvPower()
	 * @generated
	 */
	void setPvPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getPvPower <em>Pv Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPvPower()
	 * @see #getPvPower()
	 * @see #setPvPower(double)
	 * @generated
	 */
	void unsetPvPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getPvPower <em>Pv Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Pv Power</em>' attribute is set.
	 * @see #unsetPvPower()
	 * @see #getPvPower()
	 * @see #setPvPower(double)
	 * @generated
	 */
	boolean isSetPvPower();

	/**
	 * Returns the value of the '<em><b>Ac Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * AC output of the inverter(s) — includes battery discharge on a hybrid system.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ac Power</em>' attribute.
	 * @see #isSetAcPower()
	 * @see #unsetAcPower()
	 * @see #setAcPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_AcPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getAcPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getAcPower <em>Ac Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ac Power</em>' attribute.
	 * @see #isSetAcPower()
	 * @see #unsetAcPower()
	 * @see #getAcPower()
	 * @generated
	 */
	void setAcPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getAcPower <em>Ac Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAcPower()
	 * @see #getAcPower()
	 * @see #setAcPower(double)
	 * @generated
	 */
	void unsetAcPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getAcPower <em>Ac Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ac Power</em>' attribute is set.
	 * @see #unsetAcPower()
	 * @see #getAcPower()
	 * @see #setAcPower(double)
	 * @generated
	 */
	boolean isSetAcPower();

	/**
	 * Returns the value of the '<em><b>Load Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Consumption of the household.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Load Power</em>' attribute.
	 * @see #isSetLoadPower()
	 * @see #unsetLoadPower()
	 * @see #setLoadPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_LoadPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getLoadPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getLoadPower <em>Load Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Power</em>' attribute.
	 * @see #isSetLoadPower()
	 * @see #unsetLoadPower()
	 * @see #getLoadPower()
	 * @generated
	 */
	void setLoadPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getLoadPower <em>Load Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLoadPower()
	 * @see #getLoadPower()
	 * @see #setLoadPower(double)
	 * @generated
	 */
	void unsetLoadPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getLoadPower <em>Load Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Load Power</em>' attribute is set.
	 * @see #unsetLoadPower()
	 * @see #getLoadPower()
	 * @see #setLoadPower(double)
	 * @generated
	 */
	boolean isSetLoadPower();

	/**
	 * Returns the value of the '<em><b>Grid Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Power</em>' attribute.
	 * @see #isSetGridPower()
	 * @see #unsetGridPower()
	 * @see #setGridPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_GridPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getGridPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getGridPower <em>Grid Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Power</em>' attribute.
	 * @see #isSetGridPower()
	 * @see #unsetGridPower()
	 * @see #getGridPower()
	 * @generated
	 */
	void setGridPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getGridPower <em>Grid Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGridPower()
	 * @see #getGridPower()
	 * @see #setGridPower(double)
	 * @generated
	 */
	void unsetGridPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getGridPower <em>Grid Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Grid Power</em>' attribute is set.
	 * @see #unsetGridPower()
	 * @see #getGridPower()
	 * @see #setGridPower(double)
	 * @generated
	 */
	boolean isSetGridPower();

	/**
	 * Returns the value of the '<em><b>Battery Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Battery Power</em>' attribute.
	 * @see #isSetBatteryPower()
	 * @see #unsetBatteryPower()
	 * @see #setBatteryPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_BatteryPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getBatteryPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getBatteryPower <em>Battery Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Power</em>' attribute.
	 * @see #isSetBatteryPower()
	 * @see #unsetBatteryPower()
	 * @see #getBatteryPower()
	 * @generated
	 */
	void setBatteryPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getBatteryPower <em>Battery Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBatteryPower()
	 * @see #getBatteryPower()
	 * @see #setBatteryPower(double)
	 * @generated
	 */
	void unsetBatteryPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getBatteryPower <em>Battery Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Battery Power</em>' attribute is set.
	 * @see #unsetBatteryPower()
	 * @see #getBatteryPower()
	 * @see #setBatteryPower(double)
	 * @generated
	 */
	boolean isSetBatteryPower();

	/**
	 * Returns the value of the '<em><b>State Of Charge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Battery state of charge in %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>State Of Charge</em>' attribute.
	 * @see #isSetStateOfCharge()
	 * @see #unsetStateOfCharge()
	 * @see #setStateOfCharge(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_StateOfCharge()
	 * @model unsettable="true"
	 * @generated
	 */
	double getStateOfCharge();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getStateOfCharge <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Of Charge</em>' attribute.
	 * @see #isSetStateOfCharge()
	 * @see #unsetStateOfCharge()
	 * @see #getStateOfCharge()
	 * @generated
	 */
	void setStateOfCharge(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getStateOfCharge <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStateOfCharge()
	 * @see #getStateOfCharge()
	 * @see #setStateOfCharge(double)
	 * @generated
	 */
	void unsetStateOfCharge();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getStateOfCharge <em>State Of Charge</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>State Of Charge</em>' attribute is set.
	 * @see #unsetStateOfCharge()
	 * @see #getStateOfCharge()
	 * @see #setStateOfCharge(double)
	 * @generated
	 */
	boolean isSetStateOfCharge();

	/**
	 * Returns the value of the '<em><b>Energy Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * AC energy counter of the device in kWh, if it reports one.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Energy Total</em>' attribute.
	 * @see #isSetEnergyTotal()
	 * @see #unsetEnergyTotal()
	 * @see #setEnergyTotal(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvMeasurement_EnergyTotal()
	 * @model unsettable="true"
	 * @generated
	 */
	double getEnergyTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getEnergyTotal <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Energy Total</em>' attribute.
	 * @see #isSetEnergyTotal()
	 * @see #unsetEnergyTotal()
	 * @see #getEnergyTotal()
	 * @generated
	 */
	void setEnergyTotal(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getEnergyTotal <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnergyTotal()
	 * @see #getEnergyTotal()
	 * @see #setEnergyTotal(double)
	 * @generated
	 */
	void unsetEnergyTotal();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvMeasurement#getEnergyTotal <em>Energy Total</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Energy Total</em>' attribute is set.
	 * @see #unsetEnergyTotal()
	 * @see #getEnergyTotal()
	 * @see #setEnergyTotal(double)
	 * @generated
	 */
	boolean isSetEnergyTotal();

} // PvMeasurement
