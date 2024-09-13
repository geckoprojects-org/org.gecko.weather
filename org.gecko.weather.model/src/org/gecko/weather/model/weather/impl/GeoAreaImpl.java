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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.GeoArea;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geo Area</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoAreaImpl#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoAreaImpl#getXDim <em>XDim</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoAreaImpl#getYDim <em>YDim</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeoAreaImpl extends MinimalEObjectImpl.Container implements GeoArea {
	/**
	 * The cached value of the '{@link #getTopLeft() <em>Top Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopLeft()
	 * @generated
	 * @ordered
	 */
	protected GeoPosition topLeft;

	/**
	 * The default value of the '{@link #getXDim() <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXDim()
	 * @generated
	 * @ordered
	 */
	protected static final double XDIM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXDim() <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXDim()
	 * @generated
	 * @ordered
	 */
	protected double xDim = XDIM_EDEFAULT;

	/**
	 * The default value of the '{@link #getYDim() <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYDim()
	 * @generated
	 * @ordered
	 */
	protected static final double YDIM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYDim() <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYDim()
	 * @generated
	 * @ordered
	 */
	protected double yDim = YDIM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeoAreaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.GEO_AREA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoPosition getTopLeft() {
		if (topLeft != null && topLeft.eIsProxy()) {
			InternalEObject oldTopLeft = (InternalEObject)topLeft;
			topLeft = (GeoPosition)eResolveProxy(oldTopLeft);
			if (topLeft != oldTopLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WeatherPackage.GEO_AREA__TOP_LEFT, oldTopLeft, topLeft));
			}
		}
		return topLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoPosition basicGetTopLeft() {
		return topLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTopLeft(GeoPosition newTopLeft) {
		GeoPosition oldTopLeft = topLeft;
		topLeft = newTopLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_AREA__TOP_LEFT, oldTopLeft, topLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getXDim() {
		return xDim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setXDim(double newXDim) {
		double oldXDim = xDim;
		xDim = newXDim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_AREA__XDIM, oldXDim, xDim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getYDim() {
		return yDim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setYDim(double newYDim) {
		double oldYDim = yDim;
		yDim = newYDim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_AREA__YDIM, oldYDim, yDim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.GEO_AREA__TOP_LEFT:
				if (resolve) return getTopLeft();
				return basicGetTopLeft();
			case WeatherPackage.GEO_AREA__XDIM:
				return getXDim();
			case WeatherPackage.GEO_AREA__YDIM:
				return getYDim();
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
			case WeatherPackage.GEO_AREA__TOP_LEFT:
				setTopLeft((GeoPosition)newValue);
				return;
			case WeatherPackage.GEO_AREA__XDIM:
				setXDim((Double)newValue);
				return;
			case WeatherPackage.GEO_AREA__YDIM:
				setYDim((Double)newValue);
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
			case WeatherPackage.GEO_AREA__TOP_LEFT:
				setTopLeft((GeoPosition)null);
				return;
			case WeatherPackage.GEO_AREA__XDIM:
				setXDim(XDIM_EDEFAULT);
				return;
			case WeatherPackage.GEO_AREA__YDIM:
				setYDim(YDIM_EDEFAULT);
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
			case WeatherPackage.GEO_AREA__TOP_LEFT:
				return topLeft != null;
			case WeatherPackage.GEO_AREA__XDIM:
				return xDim != XDIM_EDEFAULT;
			case WeatherPackage.GEO_AREA__YDIM:
				return yDim != YDIM_EDEFAULT;
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
		result.append(" (xDim: ");
		result.append(xDim);
		result.append(", yDim: ");
		result.append(yDim);
		result.append(')');
		return result.toString();
	}

} //GeoAreaImpl
