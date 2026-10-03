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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Day Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getCivilDawn <em>Civil Dawn</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getCivilDusk <em>Civil Dusk</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getNauticalDawn <em>Nautical Dawn</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getNauticalDusk <em>Nautical Dusk</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getSolarNoon <em>Solar Noon</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getDayLength <em>Day Length</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getMaxSunElevation <em>Max Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.DayInfoImpl#getProvenance <em>Provenance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DayInfoImpl extends MinimalEObjectImpl.Container implements DayInfo {
	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final LocalDate DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected LocalDate date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunrise() <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunrise()
	 * @generated
	 * @ordered
	 */
	protected static final Instant SUNRISE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunrise() <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunrise()
	 * @generated
	 * @ordered
	 */
	protected Instant sunrise = SUNRISE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunset() <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunset()
	 * @generated
	 * @ordered
	 */
	protected static final Instant SUNSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunset() <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunset()
	 * @generated
	 * @ordered
	 */
	protected Instant sunset = SUNSET_EDEFAULT;

	/**
	 * The default value of the '{@link #getCivilDawn() <em>Civil Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCivilDawn()
	 * @generated
	 * @ordered
	 */
	protected static final Instant CIVIL_DAWN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCivilDawn() <em>Civil Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCivilDawn()
	 * @generated
	 * @ordered
	 */
	protected Instant civilDawn = CIVIL_DAWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getCivilDusk() <em>Civil Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCivilDusk()
	 * @generated
	 * @ordered
	 */
	protected static final Instant CIVIL_DUSK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCivilDusk() <em>Civil Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCivilDusk()
	 * @generated
	 * @ordered
	 */
	protected Instant civilDusk = CIVIL_DUSK_EDEFAULT;

	/**
	 * The default value of the '{@link #getNauticalDawn() <em>Nautical Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNauticalDawn()
	 * @generated
	 * @ordered
	 */
	protected static final Instant NAUTICAL_DAWN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNauticalDawn() <em>Nautical Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNauticalDawn()
	 * @generated
	 * @ordered
	 */
	protected Instant nauticalDawn = NAUTICAL_DAWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getNauticalDusk() <em>Nautical Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNauticalDusk()
	 * @generated
	 * @ordered
	 */
	protected static final Instant NAUTICAL_DUSK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNauticalDusk() <em>Nautical Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNauticalDusk()
	 * @generated
	 * @ordered
	 */
	protected Instant nauticalDusk = NAUTICAL_DUSK_EDEFAULT;

	/**
	 * The default value of the '{@link #getSolarNoon() <em>Solar Noon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSolarNoon()
	 * @generated
	 * @ordered
	 */
	protected static final Instant SOLAR_NOON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSolarNoon() <em>Solar Noon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSolarNoon()
	 * @generated
	 * @ordered
	 */
	protected Instant solarNoon = SOLAR_NOON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDayLength() <em>Day Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayLength()
	 * @generated
	 * @ordered
	 */
	protected static final Duration DAY_LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDayLength() <em>Day Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayLength()
	 * @generated
	 * @ordered
	 */
	protected Duration dayLength = DAY_LENGTH_EDEFAULT;

	/**
	 * This is true if the Day Length attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dayLengthESet;

	/**
	 * The default value of the '{@link #getMaxSunElevation() <em>Max Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSunElevation()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_SUN_ELEVATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxSunElevation() <em>Max Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSunElevation()
	 * @generated
	 * @ordered
	 */
	protected double maxSunElevation = MAX_SUN_ELEVATION_EDEFAULT;

	/**
	 * This is true if the Max Sun Elevation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean maxSunElevationESet;

	/**
	 * The cached value of the '{@link #getProvenance() <em>Provenance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvenance()
	 * @generated
	 * @ordered
	 */
	protected Provenance provenance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DayInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.DAY_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalDate getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(LocalDate newDate) {
		LocalDate oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getSunrise() {
		return sunrise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunrise(Instant newSunrise) {
		Instant oldSunrise = sunrise;
		sunrise = newSunrise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__SUNRISE, oldSunrise, sunrise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getSunset() {
		return sunset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunset(Instant newSunset) {
		Instant oldSunset = sunset;
		sunset = newSunset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__SUNSET, oldSunset, sunset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getCivilDawn() {
		return civilDawn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCivilDawn(Instant newCivilDawn) {
		Instant oldCivilDawn = civilDawn;
		civilDawn = newCivilDawn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__CIVIL_DAWN, oldCivilDawn, civilDawn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getCivilDusk() {
		return civilDusk;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCivilDusk(Instant newCivilDusk) {
		Instant oldCivilDusk = civilDusk;
		civilDusk = newCivilDusk;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__CIVIL_DUSK, oldCivilDusk, civilDusk));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getNauticalDawn() {
		return nauticalDawn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNauticalDawn(Instant newNauticalDawn) {
		Instant oldNauticalDawn = nauticalDawn;
		nauticalDawn = newNauticalDawn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__NAUTICAL_DAWN, oldNauticalDawn, nauticalDawn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getNauticalDusk() {
		return nauticalDusk;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNauticalDusk(Instant newNauticalDusk) {
		Instant oldNauticalDusk = nauticalDusk;
		nauticalDusk = newNauticalDusk;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__NAUTICAL_DUSK, oldNauticalDusk, nauticalDusk));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getSolarNoon() {
		return solarNoon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSolarNoon(Instant newSolarNoon) {
		Instant oldSolarNoon = solarNoon;
		solarNoon = newSolarNoon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__SOLAR_NOON, oldSolarNoon, solarNoon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Duration getDayLength() {
		return dayLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDayLength(Duration newDayLength) {
		Duration oldDayLength = dayLength;
		dayLength = newDayLength;
		boolean oldDayLengthESet = dayLengthESet;
		dayLengthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__DAY_LENGTH, oldDayLength, dayLength, !oldDayLengthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDayLength() {
		Duration oldDayLength = dayLength;
		boolean oldDayLengthESet = dayLengthESet;
		dayLength = DAY_LENGTH_EDEFAULT;
		dayLengthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.DAY_INFO__DAY_LENGTH, oldDayLength, DAY_LENGTH_EDEFAULT, oldDayLengthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDayLength() {
		return dayLengthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxSunElevation() {
		return maxSunElevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxSunElevation(double newMaxSunElevation) {
		double oldMaxSunElevation = maxSunElevation;
		maxSunElevation = newMaxSunElevation;
		boolean oldMaxSunElevationESet = maxSunElevationESet;
		maxSunElevationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION, oldMaxSunElevation, maxSunElevation, !oldMaxSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMaxSunElevation() {
		double oldMaxSunElevation = maxSunElevation;
		boolean oldMaxSunElevationESet = maxSunElevationESet;
		maxSunElevation = MAX_SUN_ELEVATION_EDEFAULT;
		maxSunElevationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION, oldMaxSunElevation, MAX_SUN_ELEVATION_EDEFAULT, oldMaxSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMaxSunElevation() {
		return maxSunElevationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Provenance getProvenance() {
		return provenance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProvenance(Provenance newProvenance, NotificationChain msgs) {
		Provenance oldProvenance = provenance;
		provenance = newProvenance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__PROVENANCE, oldProvenance, newProvenance);
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
	public void setProvenance(Provenance newProvenance) {
		if (newProvenance != provenance) {
			NotificationChain msgs = null;
			if (provenance != null)
				msgs = ((InternalEObject)provenance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.DAY_INFO__PROVENANCE, null, msgs);
			if (newProvenance != null)
				msgs = ((InternalEObject)newProvenance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.DAY_INFO__PROVENANCE, null, msgs);
			msgs = basicSetProvenance(newProvenance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.DAY_INFO__PROVENANCE, newProvenance, newProvenance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.DAY_INFO__PROVENANCE:
				return basicSetProvenance(null, msgs);
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
			case WeatherPackage.DAY_INFO__DATE:
				return getDate();
			case WeatherPackage.DAY_INFO__SUNRISE:
				return getSunrise();
			case WeatherPackage.DAY_INFO__SUNSET:
				return getSunset();
			case WeatherPackage.DAY_INFO__CIVIL_DAWN:
				return getCivilDawn();
			case WeatherPackage.DAY_INFO__CIVIL_DUSK:
				return getCivilDusk();
			case WeatherPackage.DAY_INFO__NAUTICAL_DAWN:
				return getNauticalDawn();
			case WeatherPackage.DAY_INFO__NAUTICAL_DUSK:
				return getNauticalDusk();
			case WeatherPackage.DAY_INFO__SOLAR_NOON:
				return getSolarNoon();
			case WeatherPackage.DAY_INFO__DAY_LENGTH:
				return getDayLength();
			case WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION:
				return getMaxSunElevation();
			case WeatherPackage.DAY_INFO__PROVENANCE:
				return getProvenance();
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
			case WeatherPackage.DAY_INFO__DATE:
				setDate((LocalDate)newValue);
				return;
			case WeatherPackage.DAY_INFO__SUNRISE:
				setSunrise((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__SUNSET:
				setSunset((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__CIVIL_DAWN:
				setCivilDawn((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__CIVIL_DUSK:
				setCivilDusk((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__NAUTICAL_DAWN:
				setNauticalDawn((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__NAUTICAL_DUSK:
				setNauticalDusk((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__SOLAR_NOON:
				setSolarNoon((Instant)newValue);
				return;
			case WeatherPackage.DAY_INFO__DAY_LENGTH:
				setDayLength((Duration)newValue);
				return;
			case WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION:
				setMaxSunElevation((Double)newValue);
				return;
			case WeatherPackage.DAY_INFO__PROVENANCE:
				setProvenance((Provenance)newValue);
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
			case WeatherPackage.DAY_INFO__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__SUNRISE:
				setSunrise(SUNRISE_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__SUNSET:
				setSunset(SUNSET_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__CIVIL_DAWN:
				setCivilDawn(CIVIL_DAWN_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__CIVIL_DUSK:
				setCivilDusk(CIVIL_DUSK_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__NAUTICAL_DAWN:
				setNauticalDawn(NAUTICAL_DAWN_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__NAUTICAL_DUSK:
				setNauticalDusk(NAUTICAL_DUSK_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__SOLAR_NOON:
				setSolarNoon(SOLAR_NOON_EDEFAULT);
				return;
			case WeatherPackage.DAY_INFO__DAY_LENGTH:
				unsetDayLength();
				return;
			case WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION:
				unsetMaxSunElevation();
				return;
			case WeatherPackage.DAY_INFO__PROVENANCE:
				setProvenance((Provenance)null);
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
			case WeatherPackage.DAY_INFO__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case WeatherPackage.DAY_INFO__SUNRISE:
				return SUNRISE_EDEFAULT == null ? sunrise != null : !SUNRISE_EDEFAULT.equals(sunrise);
			case WeatherPackage.DAY_INFO__SUNSET:
				return SUNSET_EDEFAULT == null ? sunset != null : !SUNSET_EDEFAULT.equals(sunset);
			case WeatherPackage.DAY_INFO__CIVIL_DAWN:
				return CIVIL_DAWN_EDEFAULT == null ? civilDawn != null : !CIVIL_DAWN_EDEFAULT.equals(civilDawn);
			case WeatherPackage.DAY_INFO__CIVIL_DUSK:
				return CIVIL_DUSK_EDEFAULT == null ? civilDusk != null : !CIVIL_DUSK_EDEFAULT.equals(civilDusk);
			case WeatherPackage.DAY_INFO__NAUTICAL_DAWN:
				return NAUTICAL_DAWN_EDEFAULT == null ? nauticalDawn != null : !NAUTICAL_DAWN_EDEFAULT.equals(nauticalDawn);
			case WeatherPackage.DAY_INFO__NAUTICAL_DUSK:
				return NAUTICAL_DUSK_EDEFAULT == null ? nauticalDusk != null : !NAUTICAL_DUSK_EDEFAULT.equals(nauticalDusk);
			case WeatherPackage.DAY_INFO__SOLAR_NOON:
				return SOLAR_NOON_EDEFAULT == null ? solarNoon != null : !SOLAR_NOON_EDEFAULT.equals(solarNoon);
			case WeatherPackage.DAY_INFO__DAY_LENGTH:
				return isSetDayLength();
			case WeatherPackage.DAY_INFO__MAX_SUN_ELEVATION:
				return isSetMaxSunElevation();
			case WeatherPackage.DAY_INFO__PROVENANCE:
				return provenance != null;
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
		result.append(" (date: ");
		result.append(date);
		result.append(", sunrise: ");
		result.append(sunrise);
		result.append(", sunset: ");
		result.append(sunset);
		result.append(", civilDawn: ");
		result.append(civilDawn);
		result.append(", civilDusk: ");
		result.append(civilDusk);
		result.append(", nauticalDawn: ");
		result.append(nauticalDawn);
		result.append(", nauticalDusk: ");
		result.append(nauticalDusk);
		result.append(", solarNoon: ");
		result.append(solarNoon);
		result.append(", dayLength: ");
		if (dayLengthESet) result.append(dayLength); else result.append("<unset>");
		result.append(", maxSunElevation: ");
		if (maxSunElevationESet) result.append(maxSunElevation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //DayInfoImpl
