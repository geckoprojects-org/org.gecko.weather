/*
 * Copyright (c) 2012 - 2024 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.W1W2;
import org.gecko.weather.model.weather.WMOWeatherCodeType;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>W1W2</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.W1W2Impl#getW1 <em>W1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.W1W2Impl#getW2 <em>W2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class W1W2Impl extends MinimalEObjectImpl.Container implements W1W2 {
	/**
	 * The default value of the '{@link #getW1() <em>W1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getW1()
	 * @generated
	 * @ordered
	 */
	protected static final WMOWeatherCodeType W1_EDEFAULT = WMOWeatherCodeType.WUNKNOWN;

	/**
	 * The cached value of the '{@link #getW1() <em>W1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getW1()
	 * @generated
	 * @ordered
	 */
	protected WMOWeatherCodeType w1 = W1_EDEFAULT;

	/**
	 * The default value of the '{@link #getW2() <em>W2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getW2()
	 * @generated
	 * @ordered
	 */
	protected static final WMOWeatherCodeType W2_EDEFAULT = WMOWeatherCodeType.WUNKNOWN;

	/**
	 * The cached value of the '{@link #getW2() <em>W2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getW2()
	 * @generated
	 * @ordered
	 */
	protected WMOWeatherCodeType w2 = W2_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected W1W2Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.W1W2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WMOWeatherCodeType getW1() {
		return w1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setW1(WMOWeatherCodeType newW1) {
		WMOWeatherCodeType oldW1 = w1;
		w1 = newW1 == null ? W1_EDEFAULT : newW1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.W1W2__W1, oldW1, w1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WMOWeatherCodeType getW2() {
		return w2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setW2(WMOWeatherCodeType newW2) {
		WMOWeatherCodeType oldW2 = w2;
		w2 = newW2 == null ? W2_EDEFAULT : newW2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.W1W2__W2, oldW2, w2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.W1W2__W1:
				return getW1();
			case WeatherPackage.W1W2__W2:
				return getW2();
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
			case WeatherPackage.W1W2__W1:
				setW1((WMOWeatherCodeType)newValue);
				return;
			case WeatherPackage.W1W2__W2:
				setW2((WMOWeatherCodeType)newValue);
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
			case WeatherPackage.W1W2__W1:
				setW1(W1_EDEFAULT);
				return;
			case WeatherPackage.W1W2__W2:
				setW2(W2_EDEFAULT);
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
			case WeatherPackage.W1W2__W1:
				return w1 != W1_EDEFAULT;
			case WeatherPackage.W1W2__W2:
				return w2 != W2_EDEFAULT;
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
		result.append(" (w1: ");
		result.append(w1);
		result.append(", w2: ");
		result.append(w2);
		result.append(')');
		return result.toString();
	}

} //W1W2Impl
