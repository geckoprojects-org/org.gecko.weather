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

import org.gecko.weather.pv.fronius.model.solarapi.Site;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getBatteryStandby <em>Battery Standby</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getBackupMode <em>Backup Mode</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getPowerGrid <em>Power Grid</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getPowerLoad <em>Power Load</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getPowerBattery <em>Power Battery</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getPowerPv <em>Power Pv</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getRelativeSelfConsumption <em>Relative Self Consumption</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getRelativeAutonomy <em>Relative Autonomy</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getMeterLocation <em>Meter Location</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getEnergyDay <em>Energy Day</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getEnergyYear <em>Energy Year</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl#getEnergyTotal <em>Energy Total</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SiteImpl extends MinimalEObjectImpl.Container implements Site {
	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final String MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected String mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatteryStandby() <em>Battery Standby</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryStandby()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BATTERY_STANDBY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatteryStandby() <em>Battery Standby</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryStandby()
	 * @generated
	 * @ordered
	 */
	protected Boolean batteryStandby = BATTERY_STANDBY_EDEFAULT;

	/**
	 * The default value of the '{@link #getBackupMode() <em>Backup Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackupMode()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BACKUP_MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBackupMode() <em>Backup Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackupMode()
	 * @generated
	 * @ordered
	 */
	protected Boolean backupMode = BACKUP_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerGrid() <em>Power Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerGrid()
	 * @generated
	 * @ordered
	 */
	protected static final Double POWER_GRID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPowerGrid() <em>Power Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerGrid()
	 * @generated
	 * @ordered
	 */
	protected Double powerGrid = POWER_GRID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerLoad() <em>Power Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerLoad()
	 * @generated
	 * @ordered
	 */
	protected static final Double POWER_LOAD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPowerLoad() <em>Power Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerLoad()
	 * @generated
	 * @ordered
	 */
	protected Double powerLoad = POWER_LOAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerBattery() <em>Power Battery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerBattery()
	 * @generated
	 * @ordered
	 */
	protected static final Double POWER_BATTERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPowerBattery() <em>Power Battery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerBattery()
	 * @generated
	 * @ordered
	 */
	protected Double powerBattery = POWER_BATTERY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerPv() <em>Power Pv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerPv()
	 * @generated
	 * @ordered
	 */
	protected static final Double POWER_PV_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPowerPv() <em>Power Pv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerPv()
	 * @generated
	 * @ordered
	 */
	protected Double powerPv = POWER_PV_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelativeSelfConsumption() <em>Relative Self Consumption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeSelfConsumption()
	 * @generated
	 * @ordered
	 */
	protected static final Double RELATIVE_SELF_CONSUMPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRelativeSelfConsumption() <em>Relative Self Consumption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeSelfConsumption()
	 * @generated
	 * @ordered
	 */
	protected Double relativeSelfConsumption = RELATIVE_SELF_CONSUMPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelativeAutonomy() <em>Relative Autonomy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeAutonomy()
	 * @generated
	 * @ordered
	 */
	protected static final Double RELATIVE_AUTONOMY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRelativeAutonomy() <em>Relative Autonomy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeAutonomy()
	 * @generated
	 * @ordered
	 */
	protected Double relativeAutonomy = RELATIVE_AUTONOMY_EDEFAULT;

	/**
	 * The default value of the '{@link #getMeterLocation() <em>Meter Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeterLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String METER_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMeterLocation() <em>Meter Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeterLocation()
	 * @generated
	 * @ordered
	 */
	protected String meterLocation = METER_LOCATION_EDEFAULT;

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
	protected SiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SolarApiPackage.Literals.SITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMode(String newMode) {
		String oldMode = mode;
		mode = newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getBatteryStandby() {
		return batteryStandby;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryStandby(Boolean newBatteryStandby) {
		Boolean oldBatteryStandby = batteryStandby;
		batteryStandby = newBatteryStandby;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__BATTERY_STANDBY, oldBatteryStandby, batteryStandby));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getBackupMode() {
		return backupMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBackupMode(Boolean newBackupMode) {
		Boolean oldBackupMode = backupMode;
		backupMode = newBackupMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__BACKUP_MODE, oldBackupMode, backupMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getPowerGrid() {
		return powerGrid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPowerGrid(Double newPowerGrid) {
		Double oldPowerGrid = powerGrid;
		powerGrid = newPowerGrid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__POWER_GRID, oldPowerGrid, powerGrid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getPowerLoad() {
		return powerLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPowerLoad(Double newPowerLoad) {
		Double oldPowerLoad = powerLoad;
		powerLoad = newPowerLoad;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__POWER_LOAD, oldPowerLoad, powerLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getPowerBattery() {
		return powerBattery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPowerBattery(Double newPowerBattery) {
		Double oldPowerBattery = powerBattery;
		powerBattery = newPowerBattery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__POWER_BATTERY, oldPowerBattery, powerBattery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getPowerPv() {
		return powerPv;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPowerPv(Double newPowerPv) {
		Double oldPowerPv = powerPv;
		powerPv = newPowerPv;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__POWER_PV, oldPowerPv, powerPv));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getRelativeSelfConsumption() {
		return relativeSelfConsumption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelativeSelfConsumption(Double newRelativeSelfConsumption) {
		Double oldRelativeSelfConsumption = relativeSelfConsumption;
		relativeSelfConsumption = newRelativeSelfConsumption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__RELATIVE_SELF_CONSUMPTION, oldRelativeSelfConsumption, relativeSelfConsumption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getRelativeAutonomy() {
		return relativeAutonomy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelativeAutonomy(Double newRelativeAutonomy) {
		Double oldRelativeAutonomy = relativeAutonomy;
		relativeAutonomy = newRelativeAutonomy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__RELATIVE_AUTONOMY, oldRelativeAutonomy, relativeAutonomy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMeterLocation() {
		return meterLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMeterLocation(String newMeterLocation) {
		String oldMeterLocation = meterLocation;
		meterLocation = newMeterLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__METER_LOCATION, oldMeterLocation, meterLocation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__ENERGY_DAY, oldEnergyDay, energyDay));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__ENERGY_YEAR, oldEnergyYear, energyYear));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.SITE__ENERGY_TOTAL, oldEnergyTotal, energyTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SolarApiPackage.SITE__MODE:
				return getMode();
			case SolarApiPackage.SITE__BATTERY_STANDBY:
				return getBatteryStandby();
			case SolarApiPackage.SITE__BACKUP_MODE:
				return getBackupMode();
			case SolarApiPackage.SITE__POWER_GRID:
				return getPowerGrid();
			case SolarApiPackage.SITE__POWER_LOAD:
				return getPowerLoad();
			case SolarApiPackage.SITE__POWER_BATTERY:
				return getPowerBattery();
			case SolarApiPackage.SITE__POWER_PV:
				return getPowerPv();
			case SolarApiPackage.SITE__RELATIVE_SELF_CONSUMPTION:
				return getRelativeSelfConsumption();
			case SolarApiPackage.SITE__RELATIVE_AUTONOMY:
				return getRelativeAutonomy();
			case SolarApiPackage.SITE__METER_LOCATION:
				return getMeterLocation();
			case SolarApiPackage.SITE__ENERGY_DAY:
				return getEnergyDay();
			case SolarApiPackage.SITE__ENERGY_YEAR:
				return getEnergyYear();
			case SolarApiPackage.SITE__ENERGY_TOTAL:
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
			case SolarApiPackage.SITE__MODE:
				setMode((String)newValue);
				return;
			case SolarApiPackage.SITE__BATTERY_STANDBY:
				setBatteryStandby((Boolean)newValue);
				return;
			case SolarApiPackage.SITE__BACKUP_MODE:
				setBackupMode((Boolean)newValue);
				return;
			case SolarApiPackage.SITE__POWER_GRID:
				setPowerGrid((Double)newValue);
				return;
			case SolarApiPackage.SITE__POWER_LOAD:
				setPowerLoad((Double)newValue);
				return;
			case SolarApiPackage.SITE__POWER_BATTERY:
				setPowerBattery((Double)newValue);
				return;
			case SolarApiPackage.SITE__POWER_PV:
				setPowerPv((Double)newValue);
				return;
			case SolarApiPackage.SITE__RELATIVE_SELF_CONSUMPTION:
				setRelativeSelfConsumption((Double)newValue);
				return;
			case SolarApiPackage.SITE__RELATIVE_AUTONOMY:
				setRelativeAutonomy((Double)newValue);
				return;
			case SolarApiPackage.SITE__METER_LOCATION:
				setMeterLocation((String)newValue);
				return;
			case SolarApiPackage.SITE__ENERGY_DAY:
				setEnergyDay((Double)newValue);
				return;
			case SolarApiPackage.SITE__ENERGY_YEAR:
				setEnergyYear((Double)newValue);
				return;
			case SolarApiPackage.SITE__ENERGY_TOTAL:
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
			case SolarApiPackage.SITE__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SolarApiPackage.SITE__BATTERY_STANDBY:
				setBatteryStandby(BATTERY_STANDBY_EDEFAULT);
				return;
			case SolarApiPackage.SITE__BACKUP_MODE:
				setBackupMode(BACKUP_MODE_EDEFAULT);
				return;
			case SolarApiPackage.SITE__POWER_GRID:
				setPowerGrid(POWER_GRID_EDEFAULT);
				return;
			case SolarApiPackage.SITE__POWER_LOAD:
				setPowerLoad(POWER_LOAD_EDEFAULT);
				return;
			case SolarApiPackage.SITE__POWER_BATTERY:
				setPowerBattery(POWER_BATTERY_EDEFAULT);
				return;
			case SolarApiPackage.SITE__POWER_PV:
				setPowerPv(POWER_PV_EDEFAULT);
				return;
			case SolarApiPackage.SITE__RELATIVE_SELF_CONSUMPTION:
				setRelativeSelfConsumption(RELATIVE_SELF_CONSUMPTION_EDEFAULT);
				return;
			case SolarApiPackage.SITE__RELATIVE_AUTONOMY:
				setRelativeAutonomy(RELATIVE_AUTONOMY_EDEFAULT);
				return;
			case SolarApiPackage.SITE__METER_LOCATION:
				setMeterLocation(METER_LOCATION_EDEFAULT);
				return;
			case SolarApiPackage.SITE__ENERGY_DAY:
				setEnergyDay(ENERGY_DAY_EDEFAULT);
				return;
			case SolarApiPackage.SITE__ENERGY_YEAR:
				setEnergyYear(ENERGY_YEAR_EDEFAULT);
				return;
			case SolarApiPackage.SITE__ENERGY_TOTAL:
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
			case SolarApiPackage.SITE__MODE:
				return MODE_EDEFAULT == null ? mode != null : !MODE_EDEFAULT.equals(mode);
			case SolarApiPackage.SITE__BATTERY_STANDBY:
				return BATTERY_STANDBY_EDEFAULT == null ? batteryStandby != null : !BATTERY_STANDBY_EDEFAULT.equals(batteryStandby);
			case SolarApiPackage.SITE__BACKUP_MODE:
				return BACKUP_MODE_EDEFAULT == null ? backupMode != null : !BACKUP_MODE_EDEFAULT.equals(backupMode);
			case SolarApiPackage.SITE__POWER_GRID:
				return POWER_GRID_EDEFAULT == null ? powerGrid != null : !POWER_GRID_EDEFAULT.equals(powerGrid);
			case SolarApiPackage.SITE__POWER_LOAD:
				return POWER_LOAD_EDEFAULT == null ? powerLoad != null : !POWER_LOAD_EDEFAULT.equals(powerLoad);
			case SolarApiPackage.SITE__POWER_BATTERY:
				return POWER_BATTERY_EDEFAULT == null ? powerBattery != null : !POWER_BATTERY_EDEFAULT.equals(powerBattery);
			case SolarApiPackage.SITE__POWER_PV:
				return POWER_PV_EDEFAULT == null ? powerPv != null : !POWER_PV_EDEFAULT.equals(powerPv);
			case SolarApiPackage.SITE__RELATIVE_SELF_CONSUMPTION:
				return RELATIVE_SELF_CONSUMPTION_EDEFAULT == null ? relativeSelfConsumption != null : !RELATIVE_SELF_CONSUMPTION_EDEFAULT.equals(relativeSelfConsumption);
			case SolarApiPackage.SITE__RELATIVE_AUTONOMY:
				return RELATIVE_AUTONOMY_EDEFAULT == null ? relativeAutonomy != null : !RELATIVE_AUTONOMY_EDEFAULT.equals(relativeAutonomy);
			case SolarApiPackage.SITE__METER_LOCATION:
				return METER_LOCATION_EDEFAULT == null ? meterLocation != null : !METER_LOCATION_EDEFAULT.equals(meterLocation);
			case SolarApiPackage.SITE__ENERGY_DAY:
				return ENERGY_DAY_EDEFAULT == null ? energyDay != null : !ENERGY_DAY_EDEFAULT.equals(energyDay);
			case SolarApiPackage.SITE__ENERGY_YEAR:
				return ENERGY_YEAR_EDEFAULT == null ? energyYear != null : !ENERGY_YEAR_EDEFAULT.equals(energyYear);
			case SolarApiPackage.SITE__ENERGY_TOTAL:
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
		result.append(" (mode: ");
		result.append(mode);
		result.append(", batteryStandby: ");
		result.append(batteryStandby);
		result.append(", backupMode: ");
		result.append(backupMode);
		result.append(", powerGrid: ");
		result.append(powerGrid);
		result.append(", powerLoad: ");
		result.append(powerLoad);
		result.append(", powerBattery: ");
		result.append(powerBattery);
		result.append(", powerPv: ");
		result.append(powerPv);
		result.append(", relativeSelfConsumption: ");
		result.append(relativeSelfConsumption);
		result.append(", relativeAutonomy: ");
		result.append(relativeAutonomy);
		result.append(", meterLocation: ");
		result.append(meterLocation);
		result.append(", energyDay: ");
		result.append(energyDay);
		result.append(", energyYear: ");
		result.append(energyYear);
		result.append(", energyTotal: ");
		result.append(energyTotal);
		result.append(')');
		return result.toString();
	}

} //SiteImpl
