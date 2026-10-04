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

import org.gecko.weather.outlook.model.outlook.HourOutlook;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hour Outlook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getDewPoint <em>Dew Point</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getCloudCover <em>Cloud Cover</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getPrecipitation <em>Precipitation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getPrecipitationProbability <em>Precipitation Probability</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getSunElevation <em>Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#getWeatherCode <em>Weather Code</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.HourOutlookImpl#isDaylight <em>Daylight</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HourOutlookImpl extends MinimalEObjectImpl.Container implements HourOutlook {
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
	 * The default value of the '{@link #getDewPoint() <em>Dew Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDewPoint()
	 * @generated
	 * @ordered
	 */
	protected static final double DEW_POINT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDewPoint() <em>Dew Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDewPoint()
	 * @generated
	 * @ordered
	 */
	protected double dewPoint = DEW_POINT_EDEFAULT;

	/**
	 * This is true if the Dew Point attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dewPointESet;

	/**
	 * The default value of the '{@link #getCloudCover() <em>Cloud Cover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCover()
	 * @generated
	 * @ordered
	 */
	protected static final double CLOUD_COVER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCloudCover() <em>Cloud Cover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCover()
	 * @generated
	 * @ordered
	 */
	protected double cloudCover = CLOUD_COVER_EDEFAULT;

	/**
	 * This is true if the Cloud Cover attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cloudCoverESet;

	/**
	 * The default value of the '{@link #getPrecipitation() <em>Precipitation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitation()
	 * @generated
	 * @ordered
	 */
	protected static final double PRECIPITATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPrecipitation() <em>Precipitation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitation()
	 * @generated
	 * @ordered
	 */
	protected double precipitation = PRECIPITATION_EDEFAULT;

	/**
	 * This is true if the Precipitation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean precipitationESet;

	/**
	 * The default value of the '{@link #getPrecipitationProbability() <em>Precipitation Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationProbability()
	 * @generated
	 * @ordered
	 */
	protected static final double PRECIPITATION_PROBABILITY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPrecipitationProbability() <em>Precipitation Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationProbability()
	 * @generated
	 * @ordered
	 */
	protected double precipitationProbability = PRECIPITATION_PROBABILITY_EDEFAULT;

	/**
	 * This is true if the Precipitation Probability attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean precipitationProbabilityESet;

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
	 * The default value of the '{@link #getSunElevation() <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunElevation()
	 * @generated
	 * @ordered
	 */
	protected static final double SUN_ELEVATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSunElevation() <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunElevation()
	 * @generated
	 * @ordered
	 */
	protected double sunElevation = SUN_ELEVATION_EDEFAULT;

	/**
	 * This is true if the Sun Elevation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sunElevationESet;

	/**
	 * The default value of the '{@link #getWeatherCode() <em>Weather Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeatherCode()
	 * @generated
	 * @ordered
	 */
	protected static final int WEATHER_CODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWeatherCode() <em>Weather Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeatherCode()
	 * @generated
	 * @ordered
	 */
	protected int weatherCode = WEATHER_CODE_EDEFAULT;

	/**
	 * This is true if the Weather Code attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean weatherCodeESet;

	/**
	 * The default value of the '{@link #isDaylight() <em>Daylight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDaylight()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DAYLIGHT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDaylight() <em>Daylight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDaylight()
	 * @generated
	 * @ordered
	 */
	protected boolean daylight = DAYLIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HourOutlookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.HOUR_OUTLOOK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__TIME, oldTime, time));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__TEMPERATURE, oldTemperature, temperature, !oldTemperatureESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__TEMPERATURE, oldTemperature, TEMPERATURE_EDEFAULT, oldTemperatureESet));
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
	public double getDewPoint() {
		return dewPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDewPoint(double newDewPoint) {
		double oldDewPoint = dewPoint;
		dewPoint = newDewPoint;
		boolean oldDewPointESet = dewPointESet;
		dewPointESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__DEW_POINT, oldDewPoint, dewPoint, !oldDewPointESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDewPoint() {
		double oldDewPoint = dewPoint;
		boolean oldDewPointESet = dewPointESet;
		dewPoint = DEW_POINT_EDEFAULT;
		dewPointESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__DEW_POINT, oldDewPoint, DEW_POINT_EDEFAULT, oldDewPointESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDewPoint() {
		return dewPointESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCloudCover() {
		return cloudCover;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCover(double newCloudCover) {
		double oldCloudCover = cloudCover;
		cloudCover = newCloudCover;
		boolean oldCloudCoverESet = cloudCoverESet;
		cloudCoverESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER, oldCloudCover, cloudCover, !oldCloudCoverESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCloudCover() {
		double oldCloudCover = cloudCover;
		boolean oldCloudCoverESet = cloudCoverESet;
		cloudCover = CLOUD_COVER_EDEFAULT;
		cloudCoverESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER, oldCloudCover, CLOUD_COVER_EDEFAULT, oldCloudCoverESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCloudCover() {
		return cloudCoverESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPrecipitation() {
		return precipitation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitation(double newPrecipitation) {
		double oldPrecipitation = precipitation;
		precipitation = newPrecipitation;
		boolean oldPrecipitationESet = precipitationESet;
		precipitationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__PRECIPITATION, oldPrecipitation, precipitation, !oldPrecipitationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPrecipitation() {
		double oldPrecipitation = precipitation;
		boolean oldPrecipitationESet = precipitationESet;
		precipitation = PRECIPITATION_EDEFAULT;
		precipitationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__PRECIPITATION, oldPrecipitation, PRECIPITATION_EDEFAULT, oldPrecipitationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPrecipitation() {
		return precipitationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPrecipitationProbability() {
		return precipitationProbability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationProbability(double newPrecipitationProbability) {
		double oldPrecipitationProbability = precipitationProbability;
		precipitationProbability = newPrecipitationProbability;
		boolean oldPrecipitationProbabilityESet = precipitationProbabilityESet;
		precipitationProbabilityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY, oldPrecipitationProbability, precipitationProbability, !oldPrecipitationProbabilityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPrecipitationProbability() {
		double oldPrecipitationProbability = precipitationProbability;
		boolean oldPrecipitationProbabilityESet = precipitationProbabilityESet;
		precipitationProbability = PRECIPITATION_PROBABILITY_EDEFAULT;
		precipitationProbabilityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY, oldPrecipitationProbability, PRECIPITATION_PROBABILITY_EDEFAULT, oldPrecipitationProbabilityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPrecipitationProbability() {
		return precipitationProbabilityESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__WIND_SPEED, oldWindSpeed, windSpeed, !oldWindSpeedESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__WIND_SPEED, oldWindSpeed, WIND_SPEED_EDEFAULT, oldWindSpeedESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__WIND_GUST, oldWindGust, windGust, !oldWindGustESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__WIND_GUST, oldWindGust, WIND_GUST_EDEFAULT, oldWindGustESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION, oldWindDirection, windDirection, !oldWindDirectionESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION, oldWindDirection, WIND_DIRECTION_EDEFAULT, oldWindDirectionESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION, oldGlobalRadiation, globalRadiation, !oldGlobalRadiationESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION, oldGlobalRadiation, GLOBAL_RADIATION_EDEFAULT, oldGlobalRadiationESet));
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
	public double getSunElevation() {
		return sunElevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunElevation(double newSunElevation) {
		double oldSunElevation = sunElevation;
		sunElevation = newSunElevation;
		boolean oldSunElevationESet = sunElevationESet;
		sunElevationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION, oldSunElevation, sunElevation, !oldSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSunElevation() {
		double oldSunElevation = sunElevation;
		boolean oldSunElevationESet = sunElevationESet;
		sunElevation = SUN_ELEVATION_EDEFAULT;
		sunElevationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION, oldSunElevation, SUN_ELEVATION_EDEFAULT, oldSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSunElevation() {
		return sunElevationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getWeatherCode() {
		return weatherCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWeatherCode(int newWeatherCode) {
		int oldWeatherCode = weatherCode;
		weatherCode = newWeatherCode;
		boolean oldWeatherCodeESet = weatherCodeESet;
		weatherCodeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE, oldWeatherCode, weatherCode, !oldWeatherCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWeatherCode() {
		int oldWeatherCode = weatherCode;
		boolean oldWeatherCodeESet = weatherCodeESet;
		weatherCode = WEATHER_CODE_EDEFAULT;
		weatherCodeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE, oldWeatherCode, WEATHER_CODE_EDEFAULT, oldWeatherCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWeatherCode() {
		return weatherCodeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDaylight() {
		return daylight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDaylight(boolean newDaylight) {
		boolean oldDaylight = daylight;
		daylight = newDaylight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.HOUR_OUTLOOK__DAYLIGHT, oldDaylight, daylight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutlookPackage.HOUR_OUTLOOK__TIME:
				return getTime();
			case OutlookPackage.HOUR_OUTLOOK__TEMPERATURE:
				return getTemperature();
			case OutlookPackage.HOUR_OUTLOOK__DEW_POINT:
				return getDewPoint();
			case OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER:
				return getCloudCover();
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION:
				return getPrecipitation();
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY:
				return getPrecipitationProbability();
			case OutlookPackage.HOUR_OUTLOOK__WIND_SPEED:
				return getWindSpeed();
			case OutlookPackage.HOUR_OUTLOOK__WIND_GUST:
				return getWindGust();
			case OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION:
				return getWindDirection();
			case OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION:
				return getGlobalRadiation();
			case OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION:
				return getSunElevation();
			case OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE:
				return getWeatherCode();
			case OutlookPackage.HOUR_OUTLOOK__DAYLIGHT:
				return isDaylight();
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
			case OutlookPackage.HOUR_OUTLOOK__TIME:
				setTime((Date)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__TEMPERATURE:
				setTemperature((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__DEW_POINT:
				setDewPoint((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER:
				setCloudCover((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION:
				setPrecipitation((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY:
				setPrecipitationProbability((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_SPEED:
				setWindSpeed((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_GUST:
				setWindGust((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION:
				setWindDirection((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION:
				setGlobalRadiation((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION:
				setSunElevation((Double)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE:
				setWeatherCode((Integer)newValue);
				return;
			case OutlookPackage.HOUR_OUTLOOK__DAYLIGHT:
				setDaylight((Boolean)newValue);
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
			case OutlookPackage.HOUR_OUTLOOK__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case OutlookPackage.HOUR_OUTLOOK__TEMPERATURE:
				unsetTemperature();
				return;
			case OutlookPackage.HOUR_OUTLOOK__DEW_POINT:
				unsetDewPoint();
				return;
			case OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER:
				unsetCloudCover();
				return;
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION:
				unsetPrecipitation();
				return;
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY:
				unsetPrecipitationProbability();
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_SPEED:
				unsetWindSpeed();
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_GUST:
				unsetWindGust();
				return;
			case OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION:
				unsetWindDirection();
				return;
			case OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION:
				unsetGlobalRadiation();
				return;
			case OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION:
				unsetSunElevation();
				return;
			case OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE:
				unsetWeatherCode();
				return;
			case OutlookPackage.HOUR_OUTLOOK__DAYLIGHT:
				setDaylight(DAYLIGHT_EDEFAULT);
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
			case OutlookPackage.HOUR_OUTLOOK__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case OutlookPackage.HOUR_OUTLOOK__TEMPERATURE:
				return isSetTemperature();
			case OutlookPackage.HOUR_OUTLOOK__DEW_POINT:
				return isSetDewPoint();
			case OutlookPackage.HOUR_OUTLOOK__CLOUD_COVER:
				return isSetCloudCover();
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION:
				return isSetPrecipitation();
			case OutlookPackage.HOUR_OUTLOOK__PRECIPITATION_PROBABILITY:
				return isSetPrecipitationProbability();
			case OutlookPackage.HOUR_OUTLOOK__WIND_SPEED:
				return isSetWindSpeed();
			case OutlookPackage.HOUR_OUTLOOK__WIND_GUST:
				return isSetWindGust();
			case OutlookPackage.HOUR_OUTLOOK__WIND_DIRECTION:
				return isSetWindDirection();
			case OutlookPackage.HOUR_OUTLOOK__GLOBAL_RADIATION:
				return isSetGlobalRadiation();
			case OutlookPackage.HOUR_OUTLOOK__SUN_ELEVATION:
				return isSetSunElevation();
			case OutlookPackage.HOUR_OUTLOOK__WEATHER_CODE:
				return isSetWeatherCode();
			case OutlookPackage.HOUR_OUTLOOK__DAYLIGHT:
				return daylight != DAYLIGHT_EDEFAULT;
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
		result.append(", dewPoint: ");
		if (dewPointESet) result.append(dewPoint); else result.append("<unset>");
		result.append(", cloudCover: ");
		if (cloudCoverESet) result.append(cloudCover); else result.append("<unset>");
		result.append(", precipitation: ");
		if (precipitationESet) result.append(precipitation); else result.append("<unset>");
		result.append(", precipitationProbability: ");
		if (precipitationProbabilityESet) result.append(precipitationProbability); else result.append("<unset>");
		result.append(", windSpeed: ");
		if (windSpeedESet) result.append(windSpeed); else result.append("<unset>");
		result.append(", windGust: ");
		if (windGustESet) result.append(windGust); else result.append("<unset>");
		result.append(", windDirection: ");
		if (windDirectionESet) result.append(windDirection); else result.append("<unset>");
		result.append(", globalRadiation: ");
		if (globalRadiationESet) result.append(globalRadiation); else result.append("<unset>");
		result.append(", sunElevation: ");
		if (sunElevationESet) result.append(sunElevation); else result.append("<unset>");
		result.append(", weatherCode: ");
		if (weatherCodeESet) result.append(weatherCode); else result.append("<unset>");
		result.append(", daylight: ");
		result.append(daylight);
		result.append(')');
		return result.toString();
	}

} //HourOutlookImpl
