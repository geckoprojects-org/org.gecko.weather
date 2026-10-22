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

import org.gecko.weather.outlook.model.outlook.Observation;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Observation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getStationId <em>Station Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getUvIndex <em>Uv Index</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getRainTotal <em>Rain Total</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getBatteryLevel <em>Battery Level</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getCapacitorVoltage <em>Capacitor Voltage</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.ObservationImpl#getSignalStrength <em>Signal Strength</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObservationImpl extends MinimalEObjectImpl.Container implements Observation {
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
	 * The default value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderId()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderId()
	 * @generated
	 * @ordered
	 */
	protected String providerId = PROVIDER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductId()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductId()
	 * @generated
	 * @ordered
	 */
	protected String productId = PRODUCT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStationId() <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStationId()
	 * @generated
	 * @ordered
	 */
	protected static final String STATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStationId() <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStationId()
	 * @generated
	 * @ordered
	 */
	protected String stationId = STATION_ID_EDEFAULT;

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
	 * The default value of the '{@link #getUvIndex() <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndex()
	 * @generated
	 * @ordered
	 */
	protected static final double UV_INDEX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUvIndex() <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndex()
	 * @generated
	 * @ordered
	 */
	protected double uvIndex = UV_INDEX_EDEFAULT;

	/**
	 * This is true if the Uv Index attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean uvIndexESet;

	/**
	 * The default value of the '{@link #getRainTotal() <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainTotal()
	 * @generated
	 * @ordered
	 */
	protected static final double RAIN_TOTAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRainTotal() <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainTotal()
	 * @generated
	 * @ordered
	 */
	protected double rainTotal = RAIN_TOTAL_EDEFAULT;

	/**
	 * This is true if the Rain Total attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rainTotalESet;

	/**
	 * The default value of the '{@link #getBatteryLevel() <em>Battery Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryLevel()
	 * @generated
	 * @ordered
	 */
	protected static final double BATTERY_LEVEL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBatteryLevel() <em>Battery Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryLevel()
	 * @generated
	 * @ordered
	 */
	protected double batteryLevel = BATTERY_LEVEL_EDEFAULT;

	/**
	 * This is true if the Battery Level attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean batteryLevelESet;

	/**
	 * The default value of the '{@link #getCapacitorVoltage() <em>Capacitor Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacitorVoltage()
	 * @generated
	 * @ordered
	 */
	protected static final double CAPACITOR_VOLTAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCapacitorVoltage() <em>Capacitor Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacitorVoltage()
	 * @generated
	 * @ordered
	 */
	protected double capacitorVoltage = CAPACITOR_VOLTAGE_EDEFAULT;

	/**
	 * This is true if the Capacitor Voltage attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean capacitorVoltageESet;

	/**
	 * The default value of the '{@link #getSignalStrength() <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalStrength()
	 * @generated
	 * @ordered
	 */
	protected static final double SIGNAL_STRENGTH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSignalStrength() <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalStrength()
	 * @generated
	 * @ordered
	 */
	protected double signalStrength = SIGNAL_STRENGTH_EDEFAULT;

	/**
	 * This is true if the Signal Strength attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean signalStrengthESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObservationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.OBSERVATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProviderId() {
		return providerId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProviderId(String newProviderId) {
		String oldProviderId = providerId;
		providerId = newProviderId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__PROVIDER_ID, oldProviderId, providerId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductId() {
		return productId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductId(String newProductId) {
		String oldProductId = productId;
		productId = newProductId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__PRODUCT_ID, oldProductId, productId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStationId() {
		return stationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStationId(String newStationId) {
		String oldStationId = stationId;
		stationId = newStationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__STATION_ID, oldStationId, stationId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__TEMPERATURE, oldTemperature, temperature, !oldTemperatureESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__TEMPERATURE, oldTemperature, TEMPERATURE_EDEFAULT, oldTemperatureESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__HUMIDITY, oldHumidity, humidity, !oldHumidityESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__HUMIDITY, oldHumidity, HUMIDITY_EDEFAULT, oldHumidityESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__WIND_SPEED, oldWindSpeed, windSpeed, !oldWindSpeedESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__WIND_SPEED, oldWindSpeed, WIND_SPEED_EDEFAULT, oldWindSpeedESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__WIND_GUST, oldWindGust, windGust, !oldWindGustESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__WIND_GUST, oldWindGust, WIND_GUST_EDEFAULT, oldWindGustESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__WIND_DIRECTION, oldWindDirection, windDirection, !oldWindDirectionESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__WIND_DIRECTION, oldWindDirection, WIND_DIRECTION_EDEFAULT, oldWindDirectionESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__GLOBAL_RADIATION, oldGlobalRadiation, globalRadiation, !oldGlobalRadiationESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__GLOBAL_RADIATION, oldGlobalRadiation, GLOBAL_RADIATION_EDEFAULT, oldGlobalRadiationESet));
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
	public double getUvIndex() {
		return uvIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUvIndex(double newUvIndex) {
		double oldUvIndex = uvIndex;
		uvIndex = newUvIndex;
		boolean oldUvIndexESet = uvIndexESet;
		uvIndexESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__UV_INDEX, oldUvIndex, uvIndex, !oldUvIndexESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetUvIndex() {
		double oldUvIndex = uvIndex;
		boolean oldUvIndexESet = uvIndexESet;
		uvIndex = UV_INDEX_EDEFAULT;
		uvIndexESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__UV_INDEX, oldUvIndex, UV_INDEX_EDEFAULT, oldUvIndexESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetUvIndex() {
		return uvIndexESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getRainTotal() {
		return rainTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRainTotal(double newRainTotal) {
		double oldRainTotal = rainTotal;
		rainTotal = newRainTotal;
		boolean oldRainTotalESet = rainTotalESet;
		rainTotalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__RAIN_TOTAL, oldRainTotal, rainTotal, !oldRainTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetRainTotal() {
		double oldRainTotal = rainTotal;
		boolean oldRainTotalESet = rainTotalESet;
		rainTotal = RAIN_TOTAL_EDEFAULT;
		rainTotalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__RAIN_TOTAL, oldRainTotal, RAIN_TOTAL_EDEFAULT, oldRainTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetRainTotal() {
		return rainTotalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryLevel(double newBatteryLevel) {
		double oldBatteryLevel = batteryLevel;
		batteryLevel = newBatteryLevel;
		boolean oldBatteryLevelESet = batteryLevelESet;
		batteryLevelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__BATTERY_LEVEL, oldBatteryLevel, batteryLevel, !oldBatteryLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetBatteryLevel() {
		double oldBatteryLevel = batteryLevel;
		boolean oldBatteryLevelESet = batteryLevelESet;
		batteryLevel = BATTERY_LEVEL_EDEFAULT;
		batteryLevelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__BATTERY_LEVEL, oldBatteryLevel, BATTERY_LEVEL_EDEFAULT, oldBatteryLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetBatteryLevel() {
		return batteryLevelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCapacitorVoltage() {
		return capacitorVoltage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCapacitorVoltage(double newCapacitorVoltage) {
		double oldCapacitorVoltage = capacitorVoltage;
		capacitorVoltage = newCapacitorVoltage;
		boolean oldCapacitorVoltageESet = capacitorVoltageESet;
		capacitorVoltageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE, oldCapacitorVoltage, capacitorVoltage, !oldCapacitorVoltageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCapacitorVoltage() {
		double oldCapacitorVoltage = capacitorVoltage;
		boolean oldCapacitorVoltageESet = capacitorVoltageESet;
		capacitorVoltage = CAPACITOR_VOLTAGE_EDEFAULT;
		capacitorVoltageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE, oldCapacitorVoltage, CAPACITOR_VOLTAGE_EDEFAULT, oldCapacitorVoltageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCapacitorVoltage() {
		return capacitorVoltageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSignalStrength() {
		return signalStrength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSignalStrength(double newSignalStrength) {
		double oldSignalStrength = signalStrength;
		signalStrength = newSignalStrength;
		boolean oldSignalStrengthESet = signalStrengthESet;
		signalStrengthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OBSERVATION__SIGNAL_STRENGTH, oldSignalStrength, signalStrength, !oldSignalStrengthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSignalStrength() {
		double oldSignalStrength = signalStrength;
		boolean oldSignalStrengthESet = signalStrengthESet;
		signalStrength = SIGNAL_STRENGTH_EDEFAULT;
		signalStrengthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.OBSERVATION__SIGNAL_STRENGTH, oldSignalStrength, SIGNAL_STRENGTH_EDEFAULT, oldSignalStrengthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSignalStrength() {
		return signalStrengthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutlookPackage.OBSERVATION__TIME:
				return getTime();
			case OutlookPackage.OBSERVATION__PROVIDER_ID:
				return getProviderId();
			case OutlookPackage.OBSERVATION__PRODUCT_ID:
				return getProductId();
			case OutlookPackage.OBSERVATION__STATION_ID:
				return getStationId();
			case OutlookPackage.OBSERVATION__TEMPERATURE:
				return getTemperature();
			case OutlookPackage.OBSERVATION__HUMIDITY:
				return getHumidity();
			case OutlookPackage.OBSERVATION__WIND_SPEED:
				return getWindSpeed();
			case OutlookPackage.OBSERVATION__WIND_GUST:
				return getWindGust();
			case OutlookPackage.OBSERVATION__WIND_DIRECTION:
				return getWindDirection();
			case OutlookPackage.OBSERVATION__GLOBAL_RADIATION:
				return getGlobalRadiation();
			case OutlookPackage.OBSERVATION__UV_INDEX:
				return getUvIndex();
			case OutlookPackage.OBSERVATION__RAIN_TOTAL:
				return getRainTotal();
			case OutlookPackage.OBSERVATION__BATTERY_LEVEL:
				return getBatteryLevel();
			case OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE:
				return getCapacitorVoltage();
			case OutlookPackage.OBSERVATION__SIGNAL_STRENGTH:
				return getSignalStrength();
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
			case OutlookPackage.OBSERVATION__TIME:
				setTime((Date)newValue);
				return;
			case OutlookPackage.OBSERVATION__PROVIDER_ID:
				setProviderId((String)newValue);
				return;
			case OutlookPackage.OBSERVATION__PRODUCT_ID:
				setProductId((String)newValue);
				return;
			case OutlookPackage.OBSERVATION__STATION_ID:
				setStationId((String)newValue);
				return;
			case OutlookPackage.OBSERVATION__TEMPERATURE:
				setTemperature((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__HUMIDITY:
				setHumidity((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__WIND_SPEED:
				setWindSpeed((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__WIND_GUST:
				setWindGust((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__WIND_DIRECTION:
				setWindDirection((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__GLOBAL_RADIATION:
				setGlobalRadiation((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__UV_INDEX:
				setUvIndex((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__RAIN_TOTAL:
				setRainTotal((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__BATTERY_LEVEL:
				setBatteryLevel((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE:
				setCapacitorVoltage((Double)newValue);
				return;
			case OutlookPackage.OBSERVATION__SIGNAL_STRENGTH:
				setSignalStrength((Double)newValue);
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
			case OutlookPackage.OBSERVATION__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case OutlookPackage.OBSERVATION__PROVIDER_ID:
				setProviderId(PROVIDER_ID_EDEFAULT);
				return;
			case OutlookPackage.OBSERVATION__PRODUCT_ID:
				setProductId(PRODUCT_ID_EDEFAULT);
				return;
			case OutlookPackage.OBSERVATION__STATION_ID:
				setStationId(STATION_ID_EDEFAULT);
				return;
			case OutlookPackage.OBSERVATION__TEMPERATURE:
				unsetTemperature();
				return;
			case OutlookPackage.OBSERVATION__HUMIDITY:
				unsetHumidity();
				return;
			case OutlookPackage.OBSERVATION__WIND_SPEED:
				unsetWindSpeed();
				return;
			case OutlookPackage.OBSERVATION__WIND_GUST:
				unsetWindGust();
				return;
			case OutlookPackage.OBSERVATION__WIND_DIRECTION:
				unsetWindDirection();
				return;
			case OutlookPackage.OBSERVATION__GLOBAL_RADIATION:
				unsetGlobalRadiation();
				return;
			case OutlookPackage.OBSERVATION__UV_INDEX:
				unsetUvIndex();
				return;
			case OutlookPackage.OBSERVATION__RAIN_TOTAL:
				unsetRainTotal();
				return;
			case OutlookPackage.OBSERVATION__BATTERY_LEVEL:
				unsetBatteryLevel();
				return;
			case OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE:
				unsetCapacitorVoltage();
				return;
			case OutlookPackage.OBSERVATION__SIGNAL_STRENGTH:
				unsetSignalStrength();
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
			case OutlookPackage.OBSERVATION__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case OutlookPackage.OBSERVATION__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerId != null : !PROVIDER_ID_EDEFAULT.equals(providerId);
			case OutlookPackage.OBSERVATION__PRODUCT_ID:
				return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
			case OutlookPackage.OBSERVATION__STATION_ID:
				return STATION_ID_EDEFAULT == null ? stationId != null : !STATION_ID_EDEFAULT.equals(stationId);
			case OutlookPackage.OBSERVATION__TEMPERATURE:
				return isSetTemperature();
			case OutlookPackage.OBSERVATION__HUMIDITY:
				return isSetHumidity();
			case OutlookPackage.OBSERVATION__WIND_SPEED:
				return isSetWindSpeed();
			case OutlookPackage.OBSERVATION__WIND_GUST:
				return isSetWindGust();
			case OutlookPackage.OBSERVATION__WIND_DIRECTION:
				return isSetWindDirection();
			case OutlookPackage.OBSERVATION__GLOBAL_RADIATION:
				return isSetGlobalRadiation();
			case OutlookPackage.OBSERVATION__UV_INDEX:
				return isSetUvIndex();
			case OutlookPackage.OBSERVATION__RAIN_TOTAL:
				return isSetRainTotal();
			case OutlookPackage.OBSERVATION__BATTERY_LEVEL:
				return isSetBatteryLevel();
			case OutlookPackage.OBSERVATION__CAPACITOR_VOLTAGE:
				return isSetCapacitorVoltage();
			case OutlookPackage.OBSERVATION__SIGNAL_STRENGTH:
				return isSetSignalStrength();
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
		result.append(", providerId: ");
		result.append(providerId);
		result.append(", productId: ");
		result.append(productId);
		result.append(", stationId: ");
		result.append(stationId);
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
		result.append(", uvIndex: ");
		if (uvIndexESet) result.append(uvIndex); else result.append("<unset>");
		result.append(", rainTotal: ");
		if (rainTotalESet) result.append(rainTotal); else result.append("<unset>");
		result.append(", batteryLevel: ");
		if (batteryLevelESet) result.append(batteryLevel); else result.append("<unset>");
		result.append(", capacitorVoltage: ");
		if (capacitorVoltageESet) result.append(capacitorVoltage); else result.append("<unset>");
		result.append(", signalStrength: ");
		if (signalStrengthESet) result.append(signalStrength); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ObservationImpl
