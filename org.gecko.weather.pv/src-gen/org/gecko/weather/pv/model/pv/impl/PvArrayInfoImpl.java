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

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.model.pv.PvArrayInfo;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl#getTilt <em>Tilt</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvArrayInfoImpl#getPeakPower <em>Peak Power</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvArrayInfoImpl extends MinimalEObjectImpl.Container implements PvArrayInfo {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvArrayInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_ARRAY_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY_INFO__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY_INFO__AZIMUTH, oldAzimuth, azimuth));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY_INFO__TILT, oldTilt, tilt));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_ARRAY_INFO__PEAK_POWER, oldPeakPower, peakPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PV_ARRAY_INFO__NAME:
				return getName();
			case PvPackage.PV_ARRAY_INFO__AZIMUTH:
				return getAzimuth();
			case PvPackage.PV_ARRAY_INFO__TILT:
				return getTilt();
			case PvPackage.PV_ARRAY_INFO__PEAK_POWER:
				return getPeakPower();
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
			case PvPackage.PV_ARRAY_INFO__NAME:
				setName((String)newValue);
				return;
			case PvPackage.PV_ARRAY_INFO__AZIMUTH:
				setAzimuth((Double)newValue);
				return;
			case PvPackage.PV_ARRAY_INFO__TILT:
				setTilt((Double)newValue);
				return;
			case PvPackage.PV_ARRAY_INFO__PEAK_POWER:
				setPeakPower((Double)newValue);
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
			case PvPackage.PV_ARRAY_INFO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY_INFO__AZIMUTH:
				setAzimuth(AZIMUTH_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY_INFO__TILT:
				setTilt(TILT_EDEFAULT);
				return;
			case PvPackage.PV_ARRAY_INFO__PEAK_POWER:
				setPeakPower(PEAK_POWER_EDEFAULT);
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
			case PvPackage.PV_ARRAY_INFO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.PV_ARRAY_INFO__AZIMUTH:
				return azimuth != AZIMUTH_EDEFAULT;
			case PvPackage.PV_ARRAY_INFO__TILT:
				return tilt != TILT_EDEFAULT;
			case PvPackage.PV_ARRAY_INFO__PEAK_POWER:
				return peakPower != PEAK_POWER_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

} //PvArrayInfoImpl
