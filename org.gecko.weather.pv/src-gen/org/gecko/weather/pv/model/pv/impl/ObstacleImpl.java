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

import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Obstacle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getAzimuthFrom <em>Azimuth From</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getAzimuthTo <em>Azimuth To</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getDistance <em>Distance</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.ObstacleImpl#getLeafOffTransmittance <em>Leaf Off Transmittance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObstacleImpl extends MinimalEObjectImpl.Container implements Obstacle {
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
	 * The default value of the '{@link #getAzimuthFrom() <em>Azimuth From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuthFrom()
	 * @generated
	 * @ordered
	 */
	protected static final double AZIMUTH_FROM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAzimuthFrom() <em>Azimuth From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuthFrom()
	 * @generated
	 * @ordered
	 */
	protected double azimuthFrom = AZIMUTH_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getAzimuthTo() <em>Azimuth To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuthTo()
	 * @generated
	 * @ordered
	 */
	protected static final double AZIMUTH_TO_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAzimuthTo() <em>Azimuth To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuthTo()
	 * @generated
	 * @ordered
	 */
	protected double azimuthTo = AZIMUTH_TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getDistance() <em>Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistance()
	 * @generated
	 * @ordered
	 */
	protected static final double DISTANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDistance() <em>Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistance()
	 * @generated
	 * @ordered
	 */
	protected double distance = DISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final double HEIGHT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected double height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeafOffTransmittance() <em>Leaf Off Transmittance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeafOffTransmittance()
	 * @generated
	 * @ordered
	 */
	protected static final double LEAF_OFF_TRANSMITTANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLeafOffTransmittance() <em>Leaf Off Transmittance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeafOffTransmittance()
	 * @generated
	 * @ordered
	 */
	protected double leafOffTransmittance = LEAF_OFF_TRANSMITTANCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObstacleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.OBSTACLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAzimuthFrom() {
		return azimuthFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAzimuthFrom(double newAzimuthFrom) {
		double oldAzimuthFrom = azimuthFrom;
		azimuthFrom = newAzimuthFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__AZIMUTH_FROM, oldAzimuthFrom, azimuthFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAzimuthTo() {
		return azimuthTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAzimuthTo(double newAzimuthTo) {
		double oldAzimuthTo = azimuthTo;
		azimuthTo = newAzimuthTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__AZIMUTH_TO, oldAzimuthTo, azimuthTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getDistance() {
		return distance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDistance(double newDistance) {
		double oldDistance = distance;
		distance = newDistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__DISTANCE, oldDistance, distance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeight(double newHeight) {
		double oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLeafOffTransmittance() {
		return leafOffTransmittance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeafOffTransmittance(double newLeafOffTransmittance) {
		double oldLeafOffTransmittance = leafOffTransmittance;
		leafOffTransmittance = newLeafOffTransmittance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.OBSTACLE__LEAF_OFF_TRANSMITTANCE, oldLeafOffTransmittance, leafOffTransmittance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.OBSTACLE__NAME:
				return getName();
			case PvPackage.OBSTACLE__AZIMUTH_FROM:
				return getAzimuthFrom();
			case PvPackage.OBSTACLE__AZIMUTH_TO:
				return getAzimuthTo();
			case PvPackage.OBSTACLE__DISTANCE:
				return getDistance();
			case PvPackage.OBSTACLE__HEIGHT:
				return getHeight();
			case PvPackage.OBSTACLE__LEAF_OFF_TRANSMITTANCE:
				return getLeafOffTransmittance();
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
			case PvPackage.OBSTACLE__NAME:
				setName((String)newValue);
				return;
			case PvPackage.OBSTACLE__AZIMUTH_FROM:
				setAzimuthFrom((Double)newValue);
				return;
			case PvPackage.OBSTACLE__AZIMUTH_TO:
				setAzimuthTo((Double)newValue);
				return;
			case PvPackage.OBSTACLE__DISTANCE:
				setDistance((Double)newValue);
				return;
			case PvPackage.OBSTACLE__HEIGHT:
				setHeight((Double)newValue);
				return;
			case PvPackage.OBSTACLE__LEAF_OFF_TRANSMITTANCE:
				setLeafOffTransmittance((Double)newValue);
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
			case PvPackage.OBSTACLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.OBSTACLE__AZIMUTH_FROM:
				setAzimuthFrom(AZIMUTH_FROM_EDEFAULT);
				return;
			case PvPackage.OBSTACLE__AZIMUTH_TO:
				setAzimuthTo(AZIMUTH_TO_EDEFAULT);
				return;
			case PvPackage.OBSTACLE__DISTANCE:
				setDistance(DISTANCE_EDEFAULT);
				return;
			case PvPackage.OBSTACLE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case PvPackage.OBSTACLE__LEAF_OFF_TRANSMITTANCE:
				setLeafOffTransmittance(LEAF_OFF_TRANSMITTANCE_EDEFAULT);
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
			case PvPackage.OBSTACLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.OBSTACLE__AZIMUTH_FROM:
				return azimuthFrom != AZIMUTH_FROM_EDEFAULT;
			case PvPackage.OBSTACLE__AZIMUTH_TO:
				return azimuthTo != AZIMUTH_TO_EDEFAULT;
			case PvPackage.OBSTACLE__DISTANCE:
				return distance != DISTANCE_EDEFAULT;
			case PvPackage.OBSTACLE__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case PvPackage.OBSTACLE__LEAF_OFF_TRANSMITTANCE:
				return leafOffTransmittance != LEAF_OFF_TRANSMITTANCE_EDEFAULT;
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
		result.append(", azimuthFrom: ");
		result.append(azimuthFrom);
		result.append(", azimuthTo: ");
		result.append(azimuthTo);
		result.append(", distance: ");
		result.append(distance);
		result.append(", height: ");
		result.append(height);
		result.append(", leafOffTransmittance: ");
		result.append(leafOffTransmittance);
		result.append(')');
		return result.toString();
	}

} //ObstacleImpl
