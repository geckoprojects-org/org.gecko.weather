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
package org.gecko.weather.outlook.model.outlook.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.outlook.model.outlook.MeasuredPoint;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measured Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.MeasuredPointImpl#getGlobalRadiation <em>Global Radiation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MeasuredPointImpl extends MinimalEObjectImpl.Container implements MeasuredPoint {
	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Date time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final double TEMPERATURE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected double temperature = TEMPERATURE_EDEFAULT;

	/**
	 * This is true if the Temperature attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean temperatureESet;

	/**
	 * The default value of the '{@link #getHumidity() <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHumidity()
	 * @generated
	 * @ordered
	 */
	protected static final double HUMIDITY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getHumidity() <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHumidity()
	 * @generated
	 * @ordered
	 */
	protected double humidity = HUMIDITY_EDEFAULT;

	/**
	 * This is true if the Humidity attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean humidityESet;

	/**
	 * The default value of the '{@link #getWindSpeed() <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final double WIND_SPEED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWindSpeed() <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindSpeed()
	 * @generated
	 * @ordered
	 */
	protected double windSpeed = WIND_SPEED_EDEFAULT;

	/**
	 * This is true if the Wind Speed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean windSpeedESet;

	/**
	 * The default value of the '{@link #getWindGust() <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGust()
	 * @generated
	 * @ordered
	 */
	protected static final double WIND_GUST_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWindGust() <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGust()
	 * @generated
	 * @ordered
	 */
	protected double windGust = WIND_GUST_EDEFAULT;

	/**
	 * This is true if the Wind Gust attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean windGustESet;

	/**
	 * The default value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected static final double WIND_DIRECTION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected double windDirection = WIND_DIRECTION_EDEFAULT;

	/**
	 * This is true if the Wind Direction attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean windDirectionESet;

	/**
	 * The default value of the '{@link #getGlobalRadiation() <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalRadiation()
	 * @generated
	 * @ordered
	 */
	protected static final double GLOBAL_RADIATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGlobalRadiation() <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalRadiation()
	 * @generated
	 * @ordered
	 */
	protected double globalRadiation = GLOBAL_RADIATION_EDEFAULT;

	/**
	 * This is true if the Global Radiation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean globalRadiationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasuredPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.MEASURED_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTime(Date newTime) {
		Date oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTemperature() {
		return temperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemperature(double newTemperature) {
		double oldTemperature = temperature;
		temperature = newTemperature;
		boolean oldTemperatureESet = temperatureESet;
		temperatureESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__TEMPERATURE, oldTemperature, temperature, !oldTemperatureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTemperature() {
		double oldTemperature = temperature;
		boolean oldTemperatureESet = temperatureESet;
		temperature = TEMPERATURE_EDEFAULT;
		temperatureESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__TEMPERATURE, oldTemperature, TEMPERATURE_EDEFAULT, oldTemperatureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTemperature() {
		return temperatureESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getHumidity() {
		return humidity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHumidity(double newHumidity) {
		double oldHumidity = humidity;
		humidity = newHumidity;
		boolean oldHumidityESet = humidityESet;
		humidityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__HUMIDITY, oldHumidity, humidity, !oldHumidityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetHumidity() {
		double oldHumidity = humidity;
		boolean oldHumidityESet = humidityESet;
		humidity = HUMIDITY_EDEFAULT;
		humidityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__HUMIDITY, oldHumidity, HUMIDITY_EDEFAULT, oldHumidityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetHumidity() {
		return humidityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindSpeed(double newWindSpeed) {
		double oldWindSpeed = windSpeed;
		windSpeed = newWindSpeed;
		boolean oldWindSpeedESet = windSpeedESet;
		windSpeedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__WIND_SPEED, oldWindSpeed, windSpeed, !oldWindSpeedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWindSpeed() {
		double oldWindSpeed = windSpeed;
		boolean oldWindSpeedESet = windSpeedESet;
		windSpeed = WIND_SPEED_EDEFAULT;
		windSpeedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__WIND_SPEED, oldWindSpeed, WIND_SPEED_EDEFAULT, oldWindSpeedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWindSpeed() {
		return windSpeedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getWindGust() {
		return windGust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGust(double newWindGust) {
		double oldWindGust = windGust;
		windGust = newWindGust;
		boolean oldWindGustESet = windGustESet;
		windGustESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__WIND_GUST, oldWindGust, windGust, !oldWindGustESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWindGust() {
		double oldWindGust = windGust;
		boolean oldWindGustESet = windGustESet;
		windGust = WIND_GUST_EDEFAULT;
		windGustESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__WIND_GUST, oldWindGust, WIND_GUST_EDEFAULT, oldWindGustESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWindGust() {
		return windGustESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getWindDirection() {
		return windDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindDirection(double newWindDirection) {
		double oldWindDirection = windDirection;
		windDirection = newWindDirection;
		boolean oldWindDirectionESet = windDirectionESet;
		windDirectionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__WIND_DIRECTION, oldWindDirection, windDirection, !oldWindDirectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWindDirection() {
		double oldWindDirection = windDirection;
		boolean oldWindDirectionESet = windDirectionESet;
		windDirection = WIND_DIRECTION_EDEFAULT;
		windDirectionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__WIND_DIRECTION, oldWindDirection, WIND_DIRECTION_EDEFAULT, oldWindDirectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWindDirection() {
		return windDirectionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGlobalRadiation() {
		return globalRadiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGlobalRadiation(double newGlobalRadiation) {
		double oldGlobalRadiation = globalRadiation;
		globalRadiation = newGlobalRadiation;
		boolean oldGlobalRadiationESet = globalRadiationESet;
		globalRadiationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION, oldGlobalRadiation, globalRadiation, !oldGlobalRadiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGlobalRadiation() {
		double oldGlobalRadiation = globalRadiation;
		boolean oldGlobalRadiationESet = globalRadiationESet;
		globalRadiation = GLOBAL_RADIATION_EDEFAULT;
		globalRadiationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION, oldGlobalRadiation, GLOBAL_RADIATION_EDEFAULT, oldGlobalRadiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGlobalRadiation() {
		return globalRadiationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutlookPackage.MEASURED_POINT__TIME:
				return getTime();
			case OutlookPackage.MEASURED_POINT__TEMPERATURE:
				return getTemperature();
			case OutlookPackage.MEASURED_POINT__HUMIDITY:
				return getHumidity();
			case OutlookPackage.MEASURED_POINT__WIND_SPEED:
				return getWindSpeed();
			case OutlookPackage.MEASURED_POINT__WIND_GUST:
				return getWindGust();
			case OutlookPackage.MEASURED_POINT__WIND_DIRECTION:
				return getWindDirection();
			case OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION:
				return getGlobalRadiation();
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
			case OutlookPackage.MEASURED_POINT__TIME:
				setTime((Date)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__TEMPERATURE:
				setTemperature((Double)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__HUMIDITY:
				setHumidity((Double)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__WIND_SPEED:
				setWindSpeed((Double)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__WIND_GUST:
				setWindGust((Double)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__WIND_DIRECTION:
				setWindDirection((Double)newValue);
				return;
			case OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION:
				setGlobalRadiation((Double)newValue);
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
			case OutlookPackage.MEASURED_POINT__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case OutlookPackage.MEASURED_POINT__TEMPERATURE:
				unsetTemperature();
				return;
			case OutlookPackage.MEASURED_POINT__HUMIDITY:
				unsetHumidity();
				return;
			case OutlookPackage.MEASURED_POINT__WIND_SPEED:
				unsetWindSpeed();
				return;
			case OutlookPackage.MEASURED_POINT__WIND_GUST:
				unsetWindGust();
				return;
			case OutlookPackage.MEASURED_POINT__WIND_DIRECTION:
				unsetWindDirection();
				return;
			case OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION:
				unsetGlobalRadiation();
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
			case OutlookPackage.MEASURED_POINT__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case OutlookPackage.MEASURED_POINT__TEMPERATURE:
				return isSetTemperature();
			case OutlookPackage.MEASURED_POINT__HUMIDITY:
				return isSetHumidity();
			case OutlookPackage.MEASURED_POINT__WIND_SPEED:
				return isSetWindSpeed();
			case OutlookPackage.MEASURED_POINT__WIND_GUST:
				return isSetWindGust();
			case OutlookPackage.MEASURED_POINT__WIND_DIRECTION:
				return isSetWindDirection();
			case OutlookPackage.MEASURED_POINT__GLOBAL_RADIATION:
				return isSetGlobalRadiation();
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
		result.append(" (time: ");
		result.append(time);
		result.append(", temperature: ");
		if (temperatureESet) result.append(temperature); else result.append("<unset>");
		result.append(", humidity: ");
		if (humidityESet) result.append(humidity); else result.append("<unset>");
		result.append(", windSpeed: ");
		if (windSpeedESet) result.append(windSpeed); else result.append("<unset>");
		result.append(", windGust: ");
		if (windGustESet) result.append(windGust); else result.append("<unset>");
		result.append(", windDirection: ");
		if (windDirectionESet) result.append(windDirection); else result.append("<unset>");
		result.append(", globalRadiation: ");
		if (globalRadiationESet) result.append(globalRadiation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //MeasuredPointImpl
