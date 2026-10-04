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
package org.gecko.weather.pv.fronius.model.solarapi.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.fronius.model.solarapi.Inverter;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inverter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getDeviceType <em>Device Type</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getPower <em>Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getStateOfCharge <em>State Of Charge</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getComponentId <em>Component Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getBatteryMode <em>Battery Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getEnergyDay <em>Energy Day</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getEnergyYear <em>Energy Year</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InverterImpl extends MinimalEObjectImpl.Container implements Inverter {
	/**
	 * The default value of the '{@link #getDeviceType() <em>Device Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceType()
	 * @generated
	 * @ordered
	 */
	protected static final Integer DEVICE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeviceType() <em>Device Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceType()
	 * @generated
	 * @ordered
	 */
	protected Integer deviceType = DEVICE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected static final Double POWER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected Double power = POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getStateOfCharge() <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateOfCharge()
	 * @generated
	 * @ordered
	 */
	protected static final Double STATE_OF_CHARGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStateOfCharge() <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateOfCharge()
	 * @generated
	 * @ordered
	 */
	protected Double stateOfCharge = STATE_OF_CHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentId() <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentId()
	 * @generated
	 * @ordered
	 */
	protected static final Long COMPONENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentId() <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentId()
	 * @generated
	 * @ordered
	 */
	protected Long componentId = COMPONENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatteryMode() <em>Battery Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryMode()
	 * @generated
	 * @ordered
	 */
	protected static final String BATTERY_MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatteryMode() <em>Battery Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryMode()
	 * @generated
	 * @ordered
	 */
	protected String batteryMode = BATTERY_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnergyDay() <em>Energy Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyDay()
	 * @generated
	 * @ordered
	 */
	protected static final Double ENERGY_DAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnergyDay() <em>Energy Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyDay()
	 * @generated
	 * @ordered
	 */
	protected Double energyDay = ENERGY_DAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnergyYear() <em>Energy Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyYear()
	 * @generated
	 * @ordered
	 */
	protected static final Double ENERGY_YEAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnergyYear() <em>Energy Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyYear()
	 * @generated
	 * @ordered
	 */
	protected Double energyYear = ENERGY_YEAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnergyTotal() <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyTotal()
	 * @generated
	 * @ordered
	 */
	protected static final Double ENERGY_TOTAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnergyTotal() <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyTotal()
	 * @generated
	 * @ordered
	 */
	protected Double energyTotal = ENERGY_TOTAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InverterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SolarApiPackage.Literals.INVERTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getDeviceType() {
		return deviceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDeviceType(Integer newDeviceType) {
		Integer oldDeviceType = deviceType;
		deviceType = newDeviceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__DEVICE_TYPE, oldDeviceType, deviceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getPower() {
		return power;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPower(Double newPower) {
		Double oldPower = power;
		power = newPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__POWER, oldPower, power));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getStateOfCharge() {
		return stateOfCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStateOfCharge(Double newStateOfCharge) {
		Double oldStateOfCharge = stateOfCharge;
		stateOfCharge = newStateOfCharge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__STATE_OF_CHARGE, oldStateOfCharge, stateOfCharge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Long getComponentId() {
		return componentId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComponentId(Long newComponentId) {
		Long oldComponentId = componentId;
		componentId = newComponentId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__COMPONENT_ID, oldComponentId, componentId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBatteryMode() {
		return batteryMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryMode(String newBatteryMode) {
		String oldBatteryMode = batteryMode;
		batteryMode = newBatteryMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__BATTERY_MODE, oldBatteryMode, batteryMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getEnergyDay() {
		return energyDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnergyDay(Double newEnergyDay) {
		Double oldEnergyDay = energyDay;
		energyDay = newEnergyDay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__ENERGY_DAY, oldEnergyDay, energyDay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getEnergyYear() {
		return energyYear;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnergyYear(Double newEnergyYear) {
		Double oldEnergyYear = energyYear;
		energyYear = newEnergyYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__ENERGY_YEAR, oldEnergyYear, energyYear));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getEnergyTotal() {
		return energyTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnergyTotal(Double newEnergyTotal) {
		Double oldEnergyTotal = energyTotal;
		energyTotal = newEnergyTotal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.INVERTER__ENERGY_TOTAL, oldEnergyTotal, energyTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SolarApiPackage.INVERTER__DEVICE_TYPE:
				return getDeviceType();
			case SolarApiPackage.INVERTER__POWER:
				return getPower();
			case SolarApiPackage.INVERTER__STATE_OF_CHARGE:
				return getStateOfCharge();
			case SolarApiPackage.INVERTER__COMPONENT_ID:
				return getComponentId();
			case SolarApiPackage.INVERTER__BATTERY_MODE:
				return getBatteryMode();
			case SolarApiPackage.INVERTER__ENERGY_DAY:
				return getEnergyDay();
			case SolarApiPackage.INVERTER__ENERGY_YEAR:
				return getEnergyYear();
			case SolarApiPackage.INVERTER__ENERGY_TOTAL:
				return getEnergyTotal();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SolarApiPackage.INVERTER__DEVICE_TYPE:
				setDeviceType((Integer)newValue);
				return;
			case SolarApiPackage.INVERTER__POWER:
				setPower((Double)newValue);
				return;
			case SolarApiPackage.INVERTER__STATE_OF_CHARGE:
				setStateOfCharge((Double)newValue);
				return;
			case SolarApiPackage.INVERTER__COMPONENT_ID:
				setComponentId((Long)newValue);
				return;
			case SolarApiPackage.INVERTER__BATTERY_MODE:
				setBatteryMode((String)newValue);
				return;
			case SolarApiPackage.INVERTER__ENERGY_DAY:
				setEnergyDay((Double)newValue);
				return;
			case SolarApiPackage.INVERTER__ENERGY_YEAR:
				setEnergyYear((Double)newValue);
				return;
			case SolarApiPackage.INVERTER__ENERGY_TOTAL:
				setEnergyTotal((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SolarApiPackage.INVERTER__DEVICE_TYPE:
				setDeviceType(DEVICE_TYPE_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__POWER:
				setPower(POWER_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__STATE_OF_CHARGE:
				setStateOfCharge(STATE_OF_CHARGE_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__COMPONENT_ID:
				setComponentId(COMPONENT_ID_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__BATTERY_MODE:
				setBatteryMode(BATTERY_MODE_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__ENERGY_DAY:
				setEnergyDay(ENERGY_DAY_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__ENERGY_YEAR:
				setEnergyYear(ENERGY_YEAR_EDEFAULT);
				return;
			case SolarApiPackage.INVERTER__ENERGY_TOTAL:
				setEnergyTotal(ENERGY_TOTAL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SolarApiPackage.INVERTER__DEVICE_TYPE:
				return DEVICE_TYPE_EDEFAULT == null ? deviceType != null : !DEVICE_TYPE_EDEFAULT.equals(deviceType);
			case SolarApiPackage.INVERTER__POWER:
				return POWER_EDEFAULT == null ? power != null : !POWER_EDEFAULT.equals(power);
			case SolarApiPackage.INVERTER__STATE_OF_CHARGE:
				return STATE_OF_CHARGE_EDEFAULT == null ? stateOfCharge != null : !STATE_OF_CHARGE_EDEFAULT.equals(stateOfCharge);
			case SolarApiPackage.INVERTER__COMPONENT_ID:
				return COMPONENT_ID_EDEFAULT == null ? componentId != null : !COMPONENT_ID_EDEFAULT.equals(componentId);
			case SolarApiPackage.INVERTER__BATTERY_MODE:
				return BATTERY_MODE_EDEFAULT == null ? batteryMode != null : !BATTERY_MODE_EDEFAULT.equals(batteryMode);
			case SolarApiPackage.INVERTER__ENERGY_DAY:
				return ENERGY_DAY_EDEFAULT == null ? energyDay != null : !ENERGY_DAY_EDEFAULT.equals(energyDay);
			case SolarApiPackage.INVERTER__ENERGY_YEAR:
				return ENERGY_YEAR_EDEFAULT == null ? energyYear != null : !ENERGY_YEAR_EDEFAULT.equals(energyYear);
			case SolarApiPackage.INVERTER__ENERGY_TOTAL:
				return ENERGY_TOTAL_EDEFAULT == null ? energyTotal != null : !ENERGY_TOTAL_EDEFAULT.equals(energyTotal);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (deviceType: ");
		result.append(deviceType);
		result.append(", power: ");
		result.append(power);
		result.append(", stateOfCharge: ");
		result.append(stateOfCharge);
		result.append(", componentId: ");
		result.append(componentId);
		result.append(", batteryMode: ");
		result.append(batteryMode);
		result.append(", energyDay: ");
		result.append(energyDay);
		result.append(", energyYear: ");
		result.append(energyYear);
		result.append(", energyTotal: ");
		result.append(energyTotal);
		result.append(')');
		return result.toString();
	}

} //InverterImpl
