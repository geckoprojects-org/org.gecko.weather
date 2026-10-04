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

import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inverter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.InverterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.InverterImpl#getAcPower <em>Ac Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.InverterImpl#getEfficiency <em>Efficiency</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InverterImpl extends MinimalEObjectImpl.Container implements Inverter {
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
	 * The default value of the '{@link #getEfficiency() <em>Efficiency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEfficiency()
	 * @generated
	 * @ordered
	 */
	protected static final double EFFICIENCY_EDEFAULT = 0.96;

	/**
	 * The cached value of the '{@link #getEfficiency() <em>Efficiency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEfficiency()
	 * @generated
	 * @ordered
	 */
	protected double efficiency = EFFICIENCY_EDEFAULT;

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
		return PvPackage.Literals.INVERTER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.INVERTER__NAME, oldName, name));
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.INVERTER__AC_POWER, oldAcPower, acPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getEfficiency() {
		return efficiency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEfficiency(double newEfficiency) {
		double oldEfficiency = efficiency;
		efficiency = newEfficiency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.INVERTER__EFFICIENCY, oldEfficiency, efficiency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.INVERTER__NAME:
				return getName();
			case PvPackage.INVERTER__AC_POWER:
				return getAcPower();
			case PvPackage.INVERTER__EFFICIENCY:
				return getEfficiency();
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
			case PvPackage.INVERTER__NAME:
				setName((String)newValue);
				return;
			case PvPackage.INVERTER__AC_POWER:
				setAcPower((Double)newValue);
				return;
			case PvPackage.INVERTER__EFFICIENCY:
				setEfficiency((Double)newValue);
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
			case PvPackage.INVERTER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.INVERTER__AC_POWER:
				setAcPower(AC_POWER_EDEFAULT);
				return;
			case PvPackage.INVERTER__EFFICIENCY:
				setEfficiency(EFFICIENCY_EDEFAULT);
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
			case PvPackage.INVERTER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.INVERTER__AC_POWER:
				return acPower != AC_POWER_EDEFAULT;
			case PvPackage.INVERTER__EFFICIENCY:
				return efficiency != EFFICIENCY_EDEFAULT;
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
		result.append(", acPower: ");
		result.append(acPower);
		result.append(", efficiency: ");
		result.append(efficiency);
		result.append(')');
		return result.toString();
	}

} //InverterImpl
