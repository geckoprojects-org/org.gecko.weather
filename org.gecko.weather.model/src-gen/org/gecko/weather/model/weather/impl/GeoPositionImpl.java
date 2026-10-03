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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geo Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoPositionImpl#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoPositionImpl#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.GeoPositionImpl#getElevation <em>Elevation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeoPositionImpl extends MinimalEObjectImpl.Container implements GeoPosition {
	/**
	 * The default value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected static final double LATITUDE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected double latitude = LATITUDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected static final double LONGITUDE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected double longitude = LONGITUDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getElevation() <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevation()
	 * @generated
	 * @ordered
	 */
	protected static final double ELEVATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getElevation() <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevation()
	 * @generated
	 * @ordered
	 */
	protected double elevation = ELEVATION_EDEFAULT;

	/**
	 * This is true if the Elevation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elevationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeoPositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.GEO_POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLatitude(double newLatitude) {
		double oldLatitude = latitude;
		latitude = newLatitude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_POSITION__LATITUDE, oldLatitude, latitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLongitude(double newLongitude) {
		double oldLongitude = longitude;
		longitude = newLongitude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_POSITION__LONGITUDE, oldLongitude, longitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getElevation() {
		return elevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElevation(double newElevation) {
		double oldElevation = elevation;
		elevation = newElevation;
		boolean oldElevationESet = elevationESet;
		elevationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.GEO_POSITION__ELEVATION, oldElevation, elevation, !oldElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetElevation() {
		double oldElevation = elevation;
		boolean oldElevationESet = elevationESet;
		elevation = ELEVATION_EDEFAULT;
		elevationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.GEO_POSITION__ELEVATION, oldElevation, ELEVATION_EDEFAULT, oldElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetElevation() {
		return elevationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.GEO_POSITION__LATITUDE:
				return getLatitude();
			case WeatherPackage.GEO_POSITION__LONGITUDE:
				return getLongitude();
			case WeatherPackage.GEO_POSITION__ELEVATION:
				return getElevation();
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
			case WeatherPackage.GEO_POSITION__LATITUDE:
				setLatitude((Double)newValue);
				return;
			case WeatherPackage.GEO_POSITION__LONGITUDE:
				setLongitude((Double)newValue);
				return;
			case WeatherPackage.GEO_POSITION__ELEVATION:
				setElevation((Double)newValue);
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
			case WeatherPackage.GEO_POSITION__LATITUDE:
				setLatitude(LATITUDE_EDEFAULT);
				return;
			case WeatherPackage.GEO_POSITION__LONGITUDE:
				setLongitude(LONGITUDE_EDEFAULT);
				return;
			case WeatherPackage.GEO_POSITION__ELEVATION:
				unsetElevation();
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
			case WeatherPackage.GEO_POSITION__LATITUDE:
				return latitude != LATITUDE_EDEFAULT;
			case WeatherPackage.GEO_POSITION__LONGITUDE:
				return longitude != LONGITUDE_EDEFAULT;
			case WeatherPackage.GEO_POSITION__ELEVATION:
				return isSetElevation();
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
		result.append(" (latitude: ");
		result.append(latitude);
		result.append(", longitude: ");
		result.append(longitude);
		result.append(", elevation: ");
		if (elevationESet) result.append(elevation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //GeoPositionImpl
