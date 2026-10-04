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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.Mounting;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getTilt <em>Tilt</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getModuleCount <em>Module Count</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getTemperatureCoefficient <em>Temperature Coefficient</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getMounting <em>Mounting</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayImpl#getInverter <em>Inverter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvArrayImpl extends MinimalEObjectImpl.Container implements PvArray {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAzimuth() <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuth()
	 * @generated
	 * @ordered
	 */
	protected static final double AZIMUTH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAzimuth() <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuth()
	 * @generated
	 * @ordered
	 */
	protected double azimuth = AZIMUTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getTilt() <em>Tilt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTilt()
	 * @generated
	 * @ordered
	 */
	protected static final double TILT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTilt() <em>Tilt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTilt()
	 * @generated
	 * @ordered
	 */
	protected double tilt = TILT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected static final double PEAK_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected double peakPower = PEAK_POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getModuleCount() <em>Module Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleCount()
	 * @generated
	 * @ordered
	 */
	protected static final int MODULE_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getModuleCount() <em>Module Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleCount()
	 * @generated
	 * @ordered
	 */
	protected int moduleCount = MODULE_COUNT_EDEFAULT;

	/**
	 * This is true if the Module Count attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moduleCountESet;

	/**
	 * The default value of the '{@link #getTemperatureCoefficient() <em>Temperature Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureCoefficient()
	 * @generated
	 * @ordered
	 */
	protected static final double TEMPERATURE_COEFFICIENT_EDEFAULT = -0.37;

	/**
	 * The cached value of the '{@link #getTemperatureCoefficient() <em>Temperature Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureCoefficient()
	 * @generated
	 * @ordered
	 */
	protected double temperatureCoefficient = TEMPERATURE_COEFFICIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMounting() <em>Mounting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMounting()
	 * @generated
	 * @ordered
	 */
	protected static final Mounting MOUNTING_EDEFAULT = Mounting.ROOF_MOUNTED;

	/**
	 * The cached value of the '{@link #getMounting() <em>Mounting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMounting()
	 * @generated
	 * @ordered
	 */
	protected Mounting mounting = MOUNTING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInverter() <em>Inverter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverter()
	 * @generated
	 * @ordered
	 */
	protected Inverter inverter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAzimuth() {
		return azimuth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAzimuth(double newAzimuth) {
		double oldAzimuth = azimuth;
		azimuth = newAzimuth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__AZIMUTH, oldAzimuth, azimuth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTilt() {
		return tilt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTilt(double newTilt) {
		double oldTilt = tilt;
		tilt = newTilt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__TILT, oldTilt, tilt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPeakPower() {
		return peakPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeakPower(double newPeakPower) {
		double oldPeakPower = peakPower;
		peakPower = newPeakPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__PEAK_POWER, oldPeakPower, peakPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getModuleCount() {
		return moduleCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModuleCount(int newModuleCount) {
		int oldModuleCount = moduleCount;
		moduleCount = newModuleCount;
		boolean oldModuleCountESet = moduleCountESet;
		moduleCountESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__MODULE_COUNT, oldModuleCount, moduleCount, !oldModuleCountESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetModuleCount() {
		int oldModuleCount = moduleCount;
		boolean oldModuleCountESet = moduleCountESet;
		moduleCount = MODULE_COUNT_EDEFAULT;
		moduleCountESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_ARRAY__MODULE_COUNT, oldModuleCount, MODULE_COUNT_EDEFAULT, oldModuleCountESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetModuleCount() {
		return moduleCountESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTemperatureCoefficient() {
		return temperatureCoefficient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemperatureCoefficient(double newTemperatureCoefficient) {
		double oldTemperatureCoefficient = temperatureCoefficient;
		temperatureCoefficient = newTemperatureCoefficient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__TEMPERATURE_COEFFICIENT, oldTemperatureCoefficient, temperatureCoefficient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Mounting getMounting() {
		return mounting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMounting(Mounting newMounting) {
		Mounting oldMounting = mounting;
		mounting = newMounting == null ? MOUNTING_EDEFAULT : newMounting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__MOUNTING, oldMounting, mounting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Inverter getInverter() {
		if (inverter != null && inverter.eIsProxy()) {
			InternalEObject oldInverter = (InternalEObject)inverter;
			inverter = (Inverter)eResolveProxy(oldInverter);
			if (inverter != oldInverter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PvPackage.PV_ARRAY__INVERTER, oldInverter, inverter));
			}
		}
		return inverter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Inverter basicGetInverter() {
		return inverter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInverter(Inverter newInverter) {
		Inverter oldInverter = inverter;
		inverter = newInverter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY__INVERTER, oldInverter, inverter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PV_ARRAY__NAME:
				return getName();
			case PvPackage.PV_ARRAY__AZIMUTH:
				return getAzimuth();
			case PvPackage.PV_ARRAY__TILT:
				return getTilt();
			case PvPackage.PV_ARRAY__PEAK_POWER:
				return getPeakPower();
			case PvPackage.PV_ARRAY__MODULE_COUNT:
				return getModuleCount();
			case PvPackage.PV_ARRAY__TEMPERATURE_COEFFICIENT:
				return getTemperatureCoefficient();
			case PvPackage.PV_ARRAY__MOUNTING:
				return getMounting();
			case PvPackage.PV_ARRAY__INVERTER:
				if (resolve) return getInverter();
				return basicGetInverter();
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
			case PvPackage.PV_ARRAY__NAME:
				setName((String)newValue);
				return;
			case PvPackage.PV_ARRAY__AZIMUTH:
				setAzimuth((Double)newValue);
				return;
			case PvPackage.PV_ARRAY__TILT:
				setTilt((Double)newValue);
				return;
			case PvPackage.PV_ARRAY__PEAK_POWER:
				setPeakPower((Double)newValue);
				return;
			case PvPackage.PV_ARRAY__MODULE_COUNT:
				setModuleCount((Integer)newValue);
				return;
			case PvPackage.PV_ARRAY__TEMPERATURE_COEFFICIENT:
				setTemperatureCoefficient((Double)newValue);
				return;
			case PvPackage.PV_ARRAY__MOUNTING:
				setMounting((Mounting)newValue);
				return;
			case PvPackage.PV_ARRAY__INVERTER:
				setInverter((Inverter)newValue);
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
			case PvPackage.PV_ARRAY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__AZIMUTH:
				setAzimuth(AZIMUTH_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__TILT:
				setTilt(TILT_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__PEAK_POWER:
				setPeakPower(PEAK_POWER_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__MODULE_COUNT:
				unsetModuleCount();
				return;
			case PvPackage.PV_ARRAY__TEMPERATURE_COEFFICIENT:
				setTemperatureCoefficient(TEMPERATURE_COEFFICIENT_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__MOUNTING:
				setMounting(MOUNTING_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY__INVERTER:
				setInverter((Inverter)null);
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
			case PvPackage.PV_ARRAY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.PV_ARRAY__AZIMUTH:
				return azimuth != AZIMUTH_EDEFAULT;
			case PvPackage.PV_ARRAY__TILT:
				return tilt != TILT_EDEFAULT;
			case PvPackage.PV_ARRAY__PEAK_POWER:
				return peakPower != PEAK_POWER_EDEFAULT;
			case PvPackage.PV_ARRAY__MODULE_COUNT:
				return isSetModuleCount();
			case PvPackage.PV_ARRAY__TEMPERATURE_COEFFICIENT:
				return temperatureCoefficient != TEMPERATURE_COEFFICIENT_EDEFAULT;
			case PvPackage.PV_ARRAY__MOUNTING:
				return mounting != MOUNTING_EDEFAULT;
			case PvPackage.PV_ARRAY__INVERTER:
				return inverter != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", azimuth: ");
		result.append(azimuth);
		result.append(", tilt: ");
		result.append(tilt);
		result.append(", peakPower: ");
		result.append(peakPower);
		result.append(", moduleCount: ");
		if (moduleCountESet) result.append(moduleCount); else result.append("<unset>");
		result.append(", temperatureCoefficient: ");
		result.append(temperatureCoefficient);
		result.append(", mounting: ");
		result.append(mounting);
		result.append(')');
		return result.toString();
	}

} //PvArrayImpl
