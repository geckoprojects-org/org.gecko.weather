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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Astrotime</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.AstrotimeImpl#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.AstrotimeImpl#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.AstrotimeImpl#getSunsetTwilight <em>Sunset Twilight</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.AstrotimeImpl#getSunriseTwilight <em>Sunrise Twilight</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AstrotimeImpl extends MinimalEObjectImpl.Container implements Astrotime {
	/**
	 * The default value of the '{@link #getSunset() <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunset()
	 * @generated
	 * @ordered
	 */
	protected static final Date SUNSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunset() <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunset()
	 * @generated
	 * @ordered
	 */
	protected Date sunset = SUNSET_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunrise() <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunrise()
	 * @generated
	 * @ordered
	 */
	protected static final Date SUNRISE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunrise() <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunrise()
	 * @generated
	 * @ordered
	 */
	protected Date sunrise = SUNRISE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunsetTwilight() <em>Sunset Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunsetTwilight()
	 * @generated
	 * @ordered
	 */
	protected static final Date SUNSET_TWILIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunsetTwilight() <em>Sunset Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunsetTwilight()
	 * @generated
	 * @ordered
	 */
	protected Date sunsetTwilight = SUNSET_TWILIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunriseTwilight() <em>Sunrise Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunriseTwilight()
	 * @generated
	 * @ordered
	 */
	protected static final Date SUNRISE_TWILIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunriseTwilight() <em>Sunrise Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunriseTwilight()
	 * @generated
	 * @ordered
	 */
	protected Date sunriseTwilight = SUNRISE_TWILIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AstrotimeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.ASTROTIME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSunset() {
		return sunset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunset(Date newSunset) {
		Date oldSunset = sunset;
		sunset = newSunset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.ASTROTIME__SUNSET, oldSunset, sunset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSunrise() {
		return sunrise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunrise(Date newSunrise) {
		Date oldSunrise = sunrise;
		sunrise = newSunrise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.ASTROTIME__SUNRISE, oldSunrise, sunrise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSunsetTwilight() {
		return sunsetTwilight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunsetTwilight(Date newSunsetTwilight) {
		Date oldSunsetTwilight = sunsetTwilight;
		sunsetTwilight = newSunsetTwilight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.ASTROTIME__SUNSET_TWILIGHT, oldSunsetTwilight, sunsetTwilight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSunriseTwilight() {
		return sunriseTwilight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunriseTwilight(Date newSunriseTwilight) {
		Date oldSunriseTwilight = sunriseTwilight;
		sunriseTwilight = newSunriseTwilight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.ASTROTIME__SUNRISE_TWILIGHT, oldSunriseTwilight, sunriseTwilight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.ASTROTIME__SUNSET:
				return getSunset();
			case WeatherPackage.ASTROTIME__SUNRISE:
				return getSunrise();
			case WeatherPackage.ASTROTIME__SUNSET_TWILIGHT:
				return getSunsetTwilight();
			case WeatherPackage.ASTROTIME__SUNRISE_TWILIGHT:
				return getSunriseTwilight();
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
			case WeatherPackage.ASTROTIME__SUNSET:
				setSunset((Date)newValue);
				return;
			case WeatherPackage.ASTROTIME__SUNRISE:
				setSunrise((Date)newValue);
				return;
			case WeatherPackage.ASTROTIME__SUNSET_TWILIGHT:
				setSunsetTwilight((Date)newValue);
				return;
			case WeatherPackage.ASTROTIME__SUNRISE_TWILIGHT:
				setSunriseTwilight((Date)newValue);
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
			case WeatherPackage.ASTROTIME__SUNSET:
				setSunset(SUNSET_EDEFAULT);
				return;
			case WeatherPackage.ASTROTIME__SUNRISE:
				setSunrise(SUNRISE_EDEFAULT);
				return;
			case WeatherPackage.ASTROTIME__SUNSET_TWILIGHT:
				setSunsetTwilight(SUNSET_TWILIGHT_EDEFAULT);
				return;
			case WeatherPackage.ASTROTIME__SUNRISE_TWILIGHT:
				setSunriseTwilight(SUNRISE_TWILIGHT_EDEFAULT);
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
			case WeatherPackage.ASTROTIME__SUNSET:
				return SUNSET_EDEFAULT == null ? sunset != null : !SUNSET_EDEFAULT.equals(sunset);
			case WeatherPackage.ASTROTIME__SUNRISE:
				return SUNRISE_EDEFAULT == null ? sunrise != null : !SUNRISE_EDEFAULT.equals(sunrise);
			case WeatherPackage.ASTROTIME__SUNSET_TWILIGHT:
				return SUNSET_TWILIGHT_EDEFAULT == null ? sunsetTwilight != null : !SUNSET_TWILIGHT_EDEFAULT.equals(sunsetTwilight);
			case WeatherPackage.ASTROTIME__SUNRISE_TWILIGHT:
				return SUNRISE_TWILIGHT_EDEFAULT == null ? sunriseTwilight != null : !SUNRISE_TWILIGHT_EDEFAULT.equals(sunriseTwilight);
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
		result.append(" (sunset: ");
		result.append(sunset);
		result.append(", sunrise: ");
		result.append(sunrise);
		result.append(", sunsetTwilight: ");
		result.append(sunsetTwilight);
		result.append(", sunriseTwilight: ");
		result.append(sunriseTwilight);
		result.append(')');
		return result.toString();
	}

} //AstrotimeImpl
