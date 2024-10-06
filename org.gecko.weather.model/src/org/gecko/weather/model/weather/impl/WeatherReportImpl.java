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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.Astrotime;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;
import org.gecko.weather.model.weather.WeatherStation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getStation <em>Station</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getAstrotime <em>Astrotime</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getWeatherStation <em>Weather Station</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WeatherReportImpl extends MinimalEObjectImpl.Container implements WeatherReport {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected Date timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStation() <em>Station</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStation()
	 * @generated
	 * @ordered
	 */
	protected Station station;

	/**
	 * The cached value of the '{@link #getAstrotime() <em>Astrotime</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAstrotime()
	 * @generated
	 * @ordered
	 */
	protected Astrotime astrotime;

	/**
	 * The cached value of the '{@link #getWeatherStation() <em>Weather Station</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeatherStation()
	 * @generated
	 * @ordered
	 */
	protected WeatherStation weatherStation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeatherReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.WEATHER_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimestamp(Date newTimestamp) {
		Date oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Station getStation() {
		return station;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStation(Station newStation, NotificationChain msgs) {
		Station oldStation = station;
		station = newStation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__STATION, oldStation, newStation);
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
	public void setStation(Station newStation) {
		if (newStation != station) {
			NotificationChain msgs = null;
			if (station != null)
				msgs = ((InternalEObject)station).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.WEATHER_REPORT__STATION, null, msgs);
			if (newStation != null)
				msgs = ((InternalEObject)newStation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.WEATHER_REPORT__STATION, null, msgs);
			msgs = basicSetStation(newStation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__STATION, newStation, newStation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Astrotime getAstrotime() {
		return astrotime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAstrotime(Astrotime newAstrotime, NotificationChain msgs) {
		Astrotime oldAstrotime = astrotime;
		astrotime = newAstrotime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__ASTROTIME, oldAstrotime, newAstrotime);
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
	public void setAstrotime(Astrotime newAstrotime) {
		if (newAstrotime != astrotime) {
			NotificationChain msgs = null;
			if (astrotime != null)
				msgs = ((InternalEObject)astrotime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.WEATHER_REPORT__ASTROTIME, null, msgs);
			if (newAstrotime != null)
				msgs = ((InternalEObject)newAstrotime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.WEATHER_REPORT__ASTROTIME, null, msgs);
			msgs = basicSetAstrotime(newAstrotime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__ASTROTIME, newAstrotime, newAstrotime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherStation getWeatherStation() {
		if (weatherStation != null && weatherStation.eIsProxy()) {
			InternalEObject oldWeatherStation = (InternalEObject)weatherStation;
			weatherStation = (WeatherStation)eResolveProxy(oldWeatherStation);
			if (weatherStation != oldWeatherStation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WeatherPackage.WEATHER_REPORT__WEATHER_STATION, oldWeatherStation, weatherStation));
			}
		}
		return weatherStation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherStation basicGetWeatherStation() {
		return weatherStation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWeatherStation(WeatherStation newWeatherStation) {
		WeatherStation oldWeatherStation = weatherStation;
		weatherStation = newWeatherStation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__WEATHER_STATION, oldWeatherStation, weatherStation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.WEATHER_REPORT__STATION:
				return basicSetStation(null, msgs);
			case WeatherPackage.WEATHER_REPORT__ASTROTIME:
				return basicSetAstrotime(null, msgs);
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
			case WeatherPackage.WEATHER_REPORT__ID:
				return getId();
			case WeatherPackage.WEATHER_REPORT__TIMESTAMP:
				return getTimestamp();
			case WeatherPackage.WEATHER_REPORT__STATION:
				return getStation();
			case WeatherPackage.WEATHER_REPORT__ASTROTIME:
				return getAstrotime();
			case WeatherPackage.WEATHER_REPORT__WEATHER_STATION:
				if (resolve) return getWeatherStation();
				return basicGetWeatherStation();
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
			case WeatherPackage.WEATHER_REPORT__ID:
				setId((String)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__TIMESTAMP:
				setTimestamp((Date)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__STATION:
				setStation((Station)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__ASTROTIME:
				setAstrotime((Astrotime)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__WEATHER_STATION:
				setWeatherStation((WeatherStation)newValue);
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
			case WeatherPackage.WEATHER_REPORT__ID:
				setId(ID_EDEFAULT);
				return;
			case WeatherPackage.WEATHER_REPORT__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
			case WeatherPackage.WEATHER_REPORT__STATION:
				setStation((Station)null);
				return;
			case WeatherPackage.WEATHER_REPORT__ASTROTIME:
				setAstrotime((Astrotime)null);
				return;
			case WeatherPackage.WEATHER_REPORT__WEATHER_STATION:
				setWeatherStation((WeatherStation)null);
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
			case WeatherPackage.WEATHER_REPORT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case WeatherPackage.WEATHER_REPORT__TIMESTAMP:
				return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
			case WeatherPackage.WEATHER_REPORT__STATION:
				return station != null;
			case WeatherPackage.WEATHER_REPORT__ASTROTIME:
				return astrotime != null;
			case WeatherPackage.WEATHER_REPORT__WEATHER_STATION:
				return weatherStation != null;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(')');
		return result.toString();
	}

} //WeatherReportImpl
