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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grid Cell</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.GridCellImpl#getGridId <em>Grid Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GridCellImpl#getI <em>I</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GridCellImpl#getJ <em>J</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GridCellImpl#getCenter <em>Center</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GridCellImpl#getResolutionDegrees <em>Resolution Degrees</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GridCellImpl extends MinimalEObjectImpl.Container implements GridCell {
	/**
	 * The default value of the '{@link #getGridId() <em>Grid Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridId()
	 * @generated
	 * @ordered
	 */
	protected static final String GRID_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGridId() <em>Grid Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridId()
	 * @generated
	 * @ordered
	 */
	protected String gridId = GRID_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getI() <em>I</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getI()
	 * @generated
	 * @ordered
	 */
	protected static final int I_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getI() <em>I</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getI()
	 * @generated
	 * @ordered
	 */
	protected int i = I_EDEFAULT;

	/**
	 * The default value of the '{@link #getJ() <em>J</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJ()
	 * @generated
	 * @ordered
	 */
	protected static final int J_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getJ() <em>J</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJ()
	 * @generated
	 * @ordered
	 */
	protected int j = J_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCenter() <em>Center</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCenter()
	 * @generated
	 * @ordered
	 */
	protected GeoPosition center;

	/**
	 * The default value of the '{@link #getResolutionDegrees() <em>Resolution Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionDegrees()
	 * @generated
	 * @ordered
	 */
	protected static final double RESOLUTION_DEGREES_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getResolutionDegrees() <em>Resolution Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionDegrees()
	 * @generated
	 * @ordered
	 */
	protected double resolutionDegrees = RESOLUTION_DEGREES_EDEFAULT;

	/**
	 * This is true if the Resolution Degrees attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resolutionDegreesESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GridCellImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.GRID_CELL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGridId() {
		return gridId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridId(String newGridId) {
		String oldGridId = gridId;
		gridId = newGridId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__GRID_ID, oldGridId, gridId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getI() {
		return i;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setI(int newI) {
		int oldI = i;
		i = newI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__I, oldI, i));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getJ() {
		return j;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJ(int newJ) {
		int oldJ = j;
		j = newJ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__J, oldJ, j));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoPosition getCenter() {
		return center;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCenter(GeoPosition newCenter, NotificationChain msgs) {
		GeoPosition oldCenter = center;
		center = newCenter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__CENTER, oldCenter, newCenter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCenter(GeoPosition newCenter) {
		if (newCenter != center) {
			NotificationChain msgs = null;
			if (center != null)
				msgs = ((InternalEObject)center).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.GRID_CELL__CENTER, null, msgs);
			if (newCenter != null)
				msgs = ((InternalEObject)newCenter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.GRID_CELL__CENTER, null, msgs);
			msgs = basicSetCenter(newCenter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__CENTER, newCenter, newCenter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getResolutionDegrees() {
		return resolutionDegrees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResolutionDegrees(double newResolutionDegrees) {
		double oldResolutionDegrees = resolutionDegrees;
		resolutionDegrees = newResolutionDegrees;
		boolean oldResolutionDegreesESet = resolutionDegreesESet;
		resolutionDegreesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GRID_CELL__RESOLUTION_DEGREES, oldResolutionDegrees, resolutionDegrees, !oldResolutionDegreesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetResolutionDegrees() {
		double oldResolutionDegrees = resolutionDegrees;
		boolean oldResolutionDegreesESet = resolutionDegreesESet;
		resolutionDegrees = RESOLUTION_DEGREES_EDEFAULT;
		resolutionDegreesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.GRID_CELL__RESOLUTION_DEGREES, oldResolutionDegrees, RESOLUTION_DEGREES_EDEFAULT, oldResolutionDegreesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetResolutionDegrees() {
		return resolutionDegreesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.GRID_CELL__CENTER:
				return basicSetCenter(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.GRID_CELL__GRID_ID:
				return getGridId();
			case WeatherPackage.GRID_CELL__I:
				return getI();
			case WeatherPackage.GRID_CELL__J:
				return getJ();
			case WeatherPackage.GRID_CELL__CENTER:
				return getCenter();
			case WeatherPackage.GRID_CELL__RESOLUTION_DEGREES:
				return getResolutionDegrees();
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
			case WeatherPackage.GRID_CELL__GRID_ID:
				setGridId((String)newValue);
				return;
			case WeatherPackage.GRID_CELL__I:
				setI((Integer)newValue);
				return;
			case WeatherPackage.GRID_CELL__J:
				setJ((Integer)newValue);
				return;
			case WeatherPackage.GRID_CELL__CENTER:
				setCenter((GeoPosition)newValue);
				return;
			case WeatherPackage.GRID_CELL__RESOLUTION_DEGREES:
				setResolutionDegrees((Double)newValue);
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
			case WeatherPackage.GRID_CELL__GRID_ID:
				setGridId(GRID_ID_EDEFAULT);
				return;
			case WeatherPackage.GRID_CELL__I:
				setI(I_EDEFAULT);
				return;
			case WeatherPackage.GRID_CELL__J:
				setJ(J_EDEFAULT);
				return;
			case WeatherPackage.GRID_CELL__CENTER:
				setCenter((GeoPosition)null);
				return;
			case WeatherPackage.GRID_CELL__RESOLUTION_DEGREES:
				unsetResolutionDegrees();
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
			case WeatherPackage.GRID_CELL__GRID_ID:
				return GRID_ID_EDEFAULT == null ? gridId != null : !GRID_ID_EDEFAULT.equals(gridId);
			case WeatherPackage.GRID_CELL__I:
				return i != I_EDEFAULT;
			case WeatherPackage.GRID_CELL__J:
				return j != J_EDEFAULT;
			case WeatherPackage.GRID_CELL__CENTER:
				return center != null;
			case WeatherPackage.GRID_CELL__RESOLUTION_DEGREES:
				return isSetResolutionDegrees();
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
		result.append(" (gridId: ");
		result.append(gridId);
		result.append(", i: ");
		result.append(i);
		result.append(", j: ");
		result.append(j);
		result.append(", resolutionDegrees: ");
		if (resolutionDegreesESet) result.append(resolutionDegrees); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //GridCellImpl
