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
package org.gecko.weather.pv.model.pv.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getPvPower <em>Pv Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getAcPower <em>Ac Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getLoadPower <em>Load Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getGridPower <em>Grid Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getBatteryPower <em>Battery Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getStateOfCharge <em>State Of Charge</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvMeasurementImpl#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvMeasurementImpl extends MinimalEObjectImpl.Container implements PvMeasurement {
	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Date time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPvPower() <em>Pv Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPvPower()
	 * @generated
	 * @ordered
	 */
	protected static final double PV_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPvPower() <em>Pv Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPvPower()
	 * @generated
	 * @ordered
	 */
	protected double pvPower = PV_POWER_EDEFAULT;

	/**
	 * This is true if the Pv Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean pvPowerESet;

	/**
	 * The default value of the '{@link #getAcPower() <em>Ac Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcPower()
	 * @generated
	 * @ordered
	 */
	protected static final double AC_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAcPower() <em>Ac Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcPower()
	 * @generated
	 * @ordered
	 */
	protected double acPower = AC_POWER_EDEFAULT;

	/**
	 * This is true if the Ac Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean acPowerESet;

	/**
	 * The default value of the '{@link #getLoadPower() <em>Load Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadPower()
	 * @generated
	 * @ordered
	 */
	protected static final double LOAD_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLoadPower() <em>Load Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadPower()
	 * @generated
	 * @ordered
	 */
	protected double loadPower = LOAD_POWER_EDEFAULT;

	/**
	 * This is true if the Load Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean loadPowerESet;

	/**
	 * The default value of the '{@link #getGridPower() <em>Grid Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridPower()
	 * @generated
	 * @ordered
	 */
	protected static final double GRID_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGridPower() <em>Grid Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridPower()
	 * @generated
	 * @ordered
	 */
	protected double gridPower = GRID_POWER_EDEFAULT;

	/**
	 * This is true if the Grid Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean gridPowerESet;

	/**
	 * The default value of the '{@link #getBatteryPower() <em>Battery Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryPower()
	 * @generated
	 * @ordered
	 */
	protected static final double BATTERY_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBatteryPower() <em>Battery Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryPower()
	 * @generated
	 * @ordered
	 */
	protected double batteryPower = BATTERY_POWER_EDEFAULT;

	/**
	 * This is true if the Battery Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean batteryPowerESet;

	/**
	 * The default value of the '{@link #getStateOfCharge() <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateOfCharge()
	 * @generated
	 * @ordered
	 */
	protected static final double STATE_OF_CHARGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getStateOfCharge() <em>State Of Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateOfCharge()
	 * @generated
	 * @ordered
	 */
	protected double stateOfCharge = STATE_OF_CHARGE_EDEFAULT;

	/**
	 * This is true if the State Of Charge attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean stateOfChargeESet;

	/**
	 * The default value of the '{@link #getEnergyTotal() <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyTotal()
	 * @generated
	 * @ordered
	 */
	protected static final double ENERGY_TOTAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEnergyTotal() <em>Energy Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergyTotal()
	 * @generated
	 * @ordered
	 */
	protected double energyTotal = ENERGY_TOTAL_EDEFAULT;

	/**
	 * This is true if the Energy Total attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean energyTotalESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvMeasurementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_MEASUREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTime(Date newTime) {
		Date oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPvPower() {
		return pvPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPvPower(double newPvPower) {
		double oldPvPower = pvPower;
		pvPower = newPvPower;
		boolean oldPvPowerESet = pvPowerESet;
		pvPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__PV_POWER, oldPvPower, pvPower, !oldPvPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPvPower() {
		double oldPvPower = pvPower;
		boolean oldPvPowerESet = pvPowerESet;
		pvPower = PV_POWER_EDEFAULT;
		pvPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__PV_POWER, oldPvPower, PV_POWER_EDEFAULT, oldPvPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPvPower() {
		return pvPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAcPower() {
		return acPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAcPower(double newAcPower) {
		double oldAcPower = acPower;
		acPower = newAcPower;
		boolean oldAcPowerESet = acPowerESet;
		acPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__AC_POWER, oldAcPower, acPower, !oldAcPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAcPower() {
		double oldAcPower = acPower;
		boolean oldAcPowerESet = acPowerESet;
		acPower = AC_POWER_EDEFAULT;
		acPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__AC_POWER, oldAcPower, AC_POWER_EDEFAULT, oldAcPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAcPower() {
		return acPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLoadPower() {
		return loadPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLoadPower(double newLoadPower) {
		double oldLoadPower = loadPower;
		loadPower = newLoadPower;
		boolean oldLoadPowerESet = loadPowerESet;
		loadPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__LOAD_POWER, oldLoadPower, loadPower, !oldLoadPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLoadPower() {
		double oldLoadPower = loadPower;
		boolean oldLoadPowerESet = loadPowerESet;
		loadPower = LOAD_POWER_EDEFAULT;
		loadPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__LOAD_POWER, oldLoadPower, LOAD_POWER_EDEFAULT, oldLoadPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLoadPower() {
		return loadPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGridPower() {
		return gridPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridPower(double newGridPower) {
		double oldGridPower = gridPower;
		gridPower = newGridPower;
		boolean oldGridPowerESet = gridPowerESet;
		gridPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__GRID_POWER, oldGridPower, gridPower, !oldGridPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGridPower() {
		double oldGridPower = gridPower;
		boolean oldGridPowerESet = gridPowerESet;
		gridPower = GRID_POWER_EDEFAULT;
		gridPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__GRID_POWER, oldGridPower, GRID_POWER_EDEFAULT, oldGridPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGridPower() {
		return gridPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getBatteryPower() {
		return batteryPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryPower(double newBatteryPower) {
		double oldBatteryPower = batteryPower;
		batteryPower = newBatteryPower;
		boolean oldBatteryPowerESet = batteryPowerESet;
		batteryPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__BATTERY_POWER, oldBatteryPower, batteryPower, !oldBatteryPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetBatteryPower() {
		double oldBatteryPower = batteryPower;
		boolean oldBatteryPowerESet = batteryPowerESet;
		batteryPower = BATTERY_POWER_EDEFAULT;
		batteryPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__BATTERY_POWER, oldBatteryPower, BATTERY_POWER_EDEFAULT, oldBatteryPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetBatteryPower() {
		return batteryPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getStateOfCharge() {
		return stateOfCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStateOfCharge(double newStateOfCharge) {
		double oldStateOfCharge = stateOfCharge;
		stateOfCharge = newStateOfCharge;
		boolean oldStateOfChargeESet = stateOfChargeESet;
		stateOfChargeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE, oldStateOfCharge, stateOfCharge, !oldStateOfChargeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetStateOfCharge() {
		double oldStateOfCharge = stateOfCharge;
		boolean oldStateOfChargeESet = stateOfChargeESet;
		stateOfCharge = STATE_OF_CHARGE_EDEFAULT;
		stateOfChargeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE, oldStateOfCharge, STATE_OF_CHARGE_EDEFAULT, oldStateOfChargeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetStateOfCharge() {
		return stateOfChargeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getEnergyTotal() {
		return energyTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnergyTotal(double newEnergyTotal) {
		double oldEnergyTotal = energyTotal;
		energyTotal = newEnergyTotal;
		boolean oldEnergyTotalESet = energyTotalESet;
		energyTotalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_MEASUREMENT__ENERGY_TOTAL, oldEnergyTotal, energyTotal, !oldEnergyTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetEnergyTotal() {
		double oldEnergyTotal = energyTotal;
		boolean oldEnergyTotalESet = energyTotalESet;
		energyTotal = ENERGY_TOTAL_EDEFAULT;
		energyTotalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_MEASUREMENT__ENERGY_TOTAL, oldEnergyTotal, ENERGY_TOTAL_EDEFAULT, oldEnergyTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetEnergyTotal() {
		return energyTotalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PV_MEASUREMENT__TIME:
				return getTime();
			case PvPackage.PV_MEASUREMENT__PV_POWER:
				return getPvPower();
			case PvPackage.PV_MEASUREMENT__AC_POWER:
				return getAcPower();
			case PvPackage.PV_MEASUREMENT__LOAD_POWER:
				return getLoadPower();
			case PvPackage.PV_MEASUREMENT__GRID_POWER:
				return getGridPower();
			case PvPackage.PV_MEASUREMENT__BATTERY_POWER:
				return getBatteryPower();
			case PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE:
				return getStateOfCharge();
			case PvPackage.PV_MEASUREMENT__ENERGY_TOTAL:
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
			case PvPackage.PV_MEASUREMENT__TIME:
				setTime((Date)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__PV_POWER:
				setPvPower((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__AC_POWER:
				setAcPower((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__LOAD_POWER:
				setLoadPower((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__GRID_POWER:
				setGridPower((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__BATTERY_POWER:
				setBatteryPower((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE:
				setStateOfCharge((Double)newValue);
				return;
			case PvPackage.PV_MEASUREMENT__ENERGY_TOTAL:
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
			case PvPackage.PV_MEASUREMENT__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case PvPackage.PV_MEASUREMENT__PV_POWER:
				unsetPvPower();
				return;
			case PvPackage.PV_MEASUREMENT__AC_POWER:
				unsetAcPower();
				return;
			case PvPackage.PV_MEASUREMENT__LOAD_POWER:
				unsetLoadPower();
				return;
			case PvPackage.PV_MEASUREMENT__GRID_POWER:
				unsetGridPower();
				return;
			case PvPackage.PV_MEASUREMENT__BATTERY_POWER:
				unsetBatteryPower();
				return;
			case PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE:
				unsetStateOfCharge();
				return;
			case PvPackage.PV_MEASUREMENT__ENERGY_TOTAL:
				unsetEnergyTotal();
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
			case PvPackage.PV_MEASUREMENT__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case PvPackage.PV_MEASUREMENT__PV_POWER:
				return isSetPvPower();
			case PvPackage.PV_MEASUREMENT__AC_POWER:
				return isSetAcPower();
			case PvPackage.PV_MEASUREMENT__LOAD_POWER:
				return isSetLoadPower();
			case PvPackage.PV_MEASUREMENT__GRID_POWER:
				return isSetGridPower();
			case PvPackage.PV_MEASUREMENT__BATTERY_POWER:
				return isSetBatteryPower();
			case PvPackage.PV_MEASUREMENT__STATE_OF_CHARGE:
				return isSetStateOfCharge();
			case PvPackage.PV_MEASUREMENT__ENERGY_TOTAL:
				return isSetEnergyTotal();
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
		result.append(" (time: ");
		result.append(time);
		result.append(", pvPower: ");
		if (pvPowerESet) result.append(pvPower); else result.append("<unset>");
		result.append(", acPower: ");
		if (acPowerESet) result.append(acPower); else result.append("<unset>");
		result.append(", loadPower: ");
		if (loadPowerESet) result.append(loadPower); else result.append("<unset>");
		result.append(", gridPower: ");
		if (gridPowerESet) result.append(gridPower); else result.append("<unset>");
		result.append(", batteryPower: ");
		if (batteryPowerESet) result.append(batteryPower); else result.append("<unset>");
		result.append(", stateOfCharge: ");
		if (stateOfChargeESet) result.append(stateOfCharge); else result.append("<unset>");
		result.append(", energyTotal: ");
		if (energyTotalESet) result.append(energyTotal); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //PvMeasurementImpl
