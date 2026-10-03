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
package org.gecko.weather.model.weather.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.gecko.weather.model.weather.Derivation;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derivation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.DerivationImpl#getFunctionId <em>Function Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DerivationImpl#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DerivationImpl extends MinimalEObjectImpl.Container implements Derivation {
	/**
	 * The default value of the '{@link #getFunctionId() <em>Function Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionId()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunctionId() <em>Function Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionId()
	 * @generated
	 * @ordered
	 */
	protected String functionId = FUNCTION_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> inputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.DERIVATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFunctionId(String newFunctionId) {
		String oldFunctionId = functionId;
		functionId = newFunctionId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DERIVATION__FUNCTION_ID, oldFunctionId, functionId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getInputs() {
		if (inputs == null) {
			inputs = new EDataTypeUniqueEList<String>(String.class, this, WeatherPackage.DERIVATION__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.DERIVATION__FUNCTION_ID:
				return getFunctionId();
			case WeatherPackage.DERIVATION__INPUTS:
				return getInputs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WeatherPackage.DERIVATION__FUNCTION_ID:
				setFunctionId((String)newValue);
				return;
			case WeatherPackage.DERIVATION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends String>)newValue);
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
			case WeatherPackage.DERIVATION__FUNCTION_ID:
				setFunctionId(FUNCTION_ID_EDEFAULT);
				return;
			case WeatherPackage.DERIVATION__INPUTS:
				getInputs().clear();
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
			case WeatherPackage.DERIVATION__FUNCTION_ID:
				return FUNCTION_ID_EDEFAULT == null ? functionId != null : !FUNCTION_ID_EDEFAULT.equals(functionId);
			case WeatherPackage.DERIVATION__INPUTS:
				return inputs != null && !inputs.isEmpty();
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
		result.append(" (functionId: ");
		result.append(functionId);
		result.append(", inputs: ");
		result.append(inputs);
		result.append(')');
		return result.toString();
	}

} //DerivationImpl
