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

import org.gecko.weather.outlook.model.outlook.DayOutlook;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Day Outlook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getTemperatureMin <em>Temperature Min</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getTemperatureMax <em>Temperature Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getPrecipitation <em>Precipitation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getPrecipitationProbability <em>Precipitation Probability</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getSunshineHours <em>Sunshine Hours</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getInsolation <em>Insolation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getCloudCoverMean <em>Cloud Cover Mean</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getWindGustMax <em>Wind Gust Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getUvIndexMax <em>Uv Index Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getWeatherCode <em>Weather Code</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getSolarNoon <em>Solar Noon</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.DayOutlookImpl#getDaylightHours <em>Daylight Hours</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DayOutlookImpl extends MinimalEObjectImpl.Container implements DayOutlook {
	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final String DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected String date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemperatureMin() <em>Temperature Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureMin()
	 * @generated
	 * @ordered
	 */
	protected static final double TEMPERATURE_MIN_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTemperatureMin() <em>Temperature Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureMin()
	 * @generated
	 * @ordered
	 */
	protected double temperatureMin = TEMPERATURE_MIN_EDEFAULT;

	/**
	 * This is true if the Temperature Min attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean temperatureMinESet;

	/**
	 * The default value of the '{@link #getTemperatureMax() <em>Temperature Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureMax()
	 * @generated
	 * @ordered
	 */
	protected static final double TEMPERATURE_MAX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTemperatureMax() <em>Temperature Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperatureMax()
	 * @generated
	 * @ordered
	 */
	protected double temperatureMax = TEMPERATURE_MAX_EDEFAULT;

	/**
	 * This is true if the Temperature Max attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean temperatureMaxESet;

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
	 * The default value of the '{@link #getSunshineHours() <em>Sunshine Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunshineHours()
	 * @generated
	 * @ordered
	 */
	protected static final double SUNSHINE_HOURS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSunshineHours() <em>Sunshine Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunshineHours()
	 * @generated
	 * @ordered
	 */
	protected double sunshineHours = SUNSHINE_HOURS_EDEFAULT;

	/**
	 * This is true if the Sunshine Hours attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sunshineHoursESet;

	/**
	 * The default value of the '{@link #getInsolation() <em>Insolation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInsolation()
	 * @generated
	 * @ordered
	 */
	protected static final double INSOLATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getInsolation() <em>Insolation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInsolation()
	 * @generated
	 * @ordered
	 */
	protected double insolation = INSOLATION_EDEFAULT;

	/**
	 * This is true if the Insolation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean insolationESet;

	/**
	 * The default value of the '{@link #getCloudCoverMean() <em>Cloud Cover Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverMean()
	 * @generated
	 * @ordered
	 */
	protected static final double CLOUD_COVER_MEAN_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCloudCoverMean() <em>Cloud Cover Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverMean()
	 * @generated
	 * @ordered
	 */
	protected double cloudCoverMean = CLOUD_COVER_MEAN_EDEFAULT;

	/**
	 * This is true if the Cloud Cover Mean attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cloudCoverMeanESet;

	/**
	 * The default value of the '{@link #getWindGustMax() <em>Wind Gust Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustMax()
	 * @generated
	 * @ordered
	 */
	protected static final double WIND_GUST_MAX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWindGustMax() <em>Wind Gust Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustMax()
	 * @generated
	 * @ordered
	 */
	protected double windGustMax = WIND_GUST_MAX_EDEFAULT;

	/**
	 * This is true if the Wind Gust Max attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean windGustMaxESet;

	/**
	 * The default value of the '{@link #getUvIndexMax() <em>Uv Index Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndexMax()
	 * @generated
	 * @ordered
	 */
	protected static final double UV_INDEX_MAX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUvIndexMax() <em>Uv Index Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndexMax()
	 * @generated
	 * @ordered
	 */
	protected double uvIndexMax = UV_INDEX_MAX_EDEFAULT;

	/**
	 * This is true if the Uv Index Max attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean uvIndexMaxESet;

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
	 * The default value of the '{@link #getSolarNoon() <em>Solar Noon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSolarNoon()
	 * @generated
	 * @ordered
	 */
	protected static final Date SOLAR_NOON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSolarNoon() <em>Solar Noon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSolarNoon()
	 * @generated
	 * @ordered
	 */
	protected Date solarNoon = SOLAR_NOON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDaylightHours() <em>Daylight Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaylightHours()
	 * @generated
	 * @ordered
	 */
	protected static final double DAYLIGHT_HOURS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDaylightHours() <em>Daylight Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaylightHours()
	 * @generated
	 * @ordered
	 */
	protected double daylightHours = DAYLIGHT_HOURS_EDEFAULT;

	/**
	 * This is true if the Daylight Hours attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean daylightHoursESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DayOutlookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.DAY_OUTLOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(String newDate) {
		String oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTemperatureMin() {
		return temperatureMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemperatureMin(double newTemperatureMin) {
		double oldTemperatureMin = temperatureMin;
		temperatureMin = newTemperatureMin;
		boolean oldTemperatureMinESet = temperatureMinESet;
		temperatureMinESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN, oldTemperatureMin, temperatureMin, !oldTemperatureMinESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTemperatureMin() {
		double oldTemperatureMin = temperatureMin;
		boolean oldTemperatureMinESet = temperatureMinESet;
		temperatureMin = TEMPERATURE_MIN_EDEFAULT;
		temperatureMinESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN, oldTemperatureMin, TEMPERATURE_MIN_EDEFAULT, oldTemperatureMinESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTemperatureMin() {
		return temperatureMinESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTemperatureMax() {
		return temperatureMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemperatureMax(double newTemperatureMax) {
		double oldTemperatureMax = temperatureMax;
		temperatureMax = newTemperatureMax;
		boolean oldTemperatureMaxESet = temperatureMaxESet;
		temperatureMaxESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX, oldTemperatureMax, temperatureMax, !oldTemperatureMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTemperatureMax() {
		double oldTemperatureMax = temperatureMax;
		boolean oldTemperatureMaxESet = temperatureMaxESet;
		temperatureMax = TEMPERATURE_MAX_EDEFAULT;
		temperatureMaxESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX, oldTemperatureMax, TEMPERATURE_MAX_EDEFAULT, oldTemperatureMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTemperatureMax() {
		return temperatureMaxESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__PRECIPITATION, oldPrecipitation, precipitation, !oldPrecipitationESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__PRECIPITATION, oldPrecipitation, PRECIPITATION_EDEFAULT, oldPrecipitationESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY, oldPrecipitationProbability, precipitationProbability, !oldPrecipitationProbabilityESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY, oldPrecipitationProbability, PRECIPITATION_PROBABILITY_EDEFAULT, oldPrecipitationProbabilityESet));
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
	public double getSunshineHours() {
		return sunshineHours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunshineHours(double newSunshineHours) {
		double oldSunshineHours = sunshineHours;
		sunshineHours = newSunshineHours;
		boolean oldSunshineHoursESet = sunshineHoursESet;
		sunshineHoursESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS, oldSunshineHours, sunshineHours, !oldSunshineHoursESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSunshineHours() {
		double oldSunshineHours = sunshineHours;
		boolean oldSunshineHoursESet = sunshineHoursESet;
		sunshineHours = SUNSHINE_HOURS_EDEFAULT;
		sunshineHoursESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS, oldSunshineHours, SUNSHINE_HOURS_EDEFAULT, oldSunshineHoursESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSunshineHours() {
		return sunshineHoursESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getInsolation() {
		return insolation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInsolation(double newInsolation) {
		double oldInsolation = insolation;
		insolation = newInsolation;
		boolean oldInsolationESet = insolationESet;
		insolationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__INSOLATION, oldInsolation, insolation, !oldInsolationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetInsolation() {
		double oldInsolation = insolation;
		boolean oldInsolationESet = insolationESet;
		insolation = INSOLATION_EDEFAULT;
		insolationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__INSOLATION, oldInsolation, INSOLATION_EDEFAULT, oldInsolationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetInsolation() {
		return insolationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCloudCoverMean() {
		return cloudCoverMean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverMean(double newCloudCoverMean) {
		double oldCloudCoverMean = cloudCoverMean;
		cloudCoverMean = newCloudCoverMean;
		boolean oldCloudCoverMeanESet = cloudCoverMeanESet;
		cloudCoverMeanESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN, oldCloudCoverMean, cloudCoverMean, !oldCloudCoverMeanESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCloudCoverMean() {
		double oldCloudCoverMean = cloudCoverMean;
		boolean oldCloudCoverMeanESet = cloudCoverMeanESet;
		cloudCoverMean = CLOUD_COVER_MEAN_EDEFAULT;
		cloudCoverMeanESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN, oldCloudCoverMean, CLOUD_COVER_MEAN_EDEFAULT, oldCloudCoverMeanESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCloudCoverMean() {
		return cloudCoverMeanESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getWindGustMax() {
		return windGustMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustMax(double newWindGustMax) {
		double oldWindGustMax = windGustMax;
		windGustMax = newWindGustMax;
		boolean oldWindGustMaxESet = windGustMaxESet;
		windGustMaxESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX, oldWindGustMax, windGustMax, !oldWindGustMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWindGustMax() {
		double oldWindGustMax = windGustMax;
		boolean oldWindGustMaxESet = windGustMaxESet;
		windGustMax = WIND_GUST_MAX_EDEFAULT;
		windGustMaxESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX, oldWindGustMax, WIND_GUST_MAX_EDEFAULT, oldWindGustMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWindGustMax() {
		return windGustMaxESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getUvIndexMax() {
		return uvIndexMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUvIndexMax(double newUvIndexMax) {
		double oldUvIndexMax = uvIndexMax;
		uvIndexMax = newUvIndexMax;
		boolean oldUvIndexMaxESet = uvIndexMaxESet;
		uvIndexMaxESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX, oldUvIndexMax, uvIndexMax, !oldUvIndexMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetUvIndexMax() {
		double oldUvIndexMax = uvIndexMax;
		boolean oldUvIndexMaxESet = uvIndexMaxESet;
		uvIndexMax = UV_INDEX_MAX_EDEFAULT;
		uvIndexMaxESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX, oldUvIndexMax, UV_INDEX_MAX_EDEFAULT, oldUvIndexMaxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetUvIndexMax() {
		return uvIndexMaxESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__WEATHER_CODE, oldWeatherCode, weatherCode, !oldWeatherCodeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__WEATHER_CODE, oldWeatherCode, WEATHER_CODE_EDEFAULT, oldWeatherCodeESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__SUNRISE, oldSunrise, sunrise));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__SUNSET, oldSunset, sunset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSolarNoon() {
		return solarNoon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSolarNoon(Date newSolarNoon) {
		Date oldSolarNoon = solarNoon;
		solarNoon = newSolarNoon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__SOLAR_NOON, oldSolarNoon, solarNoon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getDaylightHours() {
		return daylightHours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDaylightHours(double newDaylightHours) {
		double oldDaylightHours = daylightHours;
		daylightHours = newDaylightHours;
		boolean oldDaylightHoursESet = daylightHoursESet;
		daylightHoursESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS, oldDaylightHours, daylightHours, !oldDaylightHoursESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDaylightHours() {
		double oldDaylightHours = daylightHours;
		boolean oldDaylightHoursESet = daylightHoursESet;
		daylightHours = DAYLIGHT_HOURS_EDEFAULT;
		daylightHoursESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS, oldDaylightHours, DAYLIGHT_HOURS_EDEFAULT, oldDaylightHoursESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDaylightHours() {
		return daylightHoursESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutlookPackage.DAY_OUTLOOK__DATE:
				return getDate();
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN:
				return getTemperatureMin();
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX:
				return getTemperatureMax();
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION:
				return getPrecipitation();
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY:
				return getPrecipitationProbability();
			case OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS:
				return getSunshineHours();
			case OutlookPackage.DAY_OUTLOOK__INSOLATION:
				return getInsolation();
			case OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN:
				return getCloudCoverMean();
			case OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX:
				return getWindGustMax();
			case OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX:
				return getUvIndexMax();
			case OutlookPackage.DAY_OUTLOOK__WEATHER_CODE:
				return getWeatherCode();
			case OutlookPackage.DAY_OUTLOOK__SUNRISE:
				return getSunrise();
			case OutlookPackage.DAY_OUTLOOK__SUNSET:
				return getSunset();
			case OutlookPackage.DAY_OUTLOOK__SOLAR_NOON:
				return getSolarNoon();
			case OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS:
				return getDaylightHours();
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
			case OutlookPackage.DAY_OUTLOOK__DATE:
				setDate((String)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN:
				setTemperatureMin((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX:
				setTemperatureMax((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION:
				setPrecipitation((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY:
				setPrecipitationProbability((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS:
				setSunshineHours((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__INSOLATION:
				setInsolation((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN:
				setCloudCoverMean((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX:
				setWindGustMax((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX:
				setUvIndexMax((Double)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__WEATHER_CODE:
				setWeatherCode((Integer)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNRISE:
				setSunrise((Date)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNSET:
				setSunset((Date)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__SOLAR_NOON:
				setSolarNoon((Date)newValue);
				return;
			case OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS:
				setDaylightHours((Double)newValue);
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
			case OutlookPackage.DAY_OUTLOOK__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN:
				unsetTemperatureMin();
				return;
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX:
				unsetTemperatureMax();
				return;
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION:
				unsetPrecipitation();
				return;
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY:
				unsetPrecipitationProbability();
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS:
				unsetSunshineHours();
				return;
			case OutlookPackage.DAY_OUTLOOK__INSOLATION:
				unsetInsolation();
				return;
			case OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN:
				unsetCloudCoverMean();
				return;
			case OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX:
				unsetWindGustMax();
				return;
			case OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX:
				unsetUvIndexMax();
				return;
			case OutlookPackage.DAY_OUTLOOK__WEATHER_CODE:
				unsetWeatherCode();
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNRISE:
				setSunrise(SUNRISE_EDEFAULT);
				return;
			case OutlookPackage.DAY_OUTLOOK__SUNSET:
				setSunset(SUNSET_EDEFAULT);
				return;
			case OutlookPackage.DAY_OUTLOOK__SOLAR_NOON:
				setSolarNoon(SOLAR_NOON_EDEFAULT);
				return;
			case OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS:
				unsetDaylightHours();
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
			case OutlookPackage.DAY_OUTLOOK__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MIN:
				return isSetTemperatureMin();
			case OutlookPackage.DAY_OUTLOOK__TEMPERATURE_MAX:
				return isSetTemperatureMax();
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION:
				return isSetPrecipitation();
			case OutlookPackage.DAY_OUTLOOK__PRECIPITATION_PROBABILITY:
				return isSetPrecipitationProbability();
			case OutlookPackage.DAY_OUTLOOK__SUNSHINE_HOURS:
				return isSetSunshineHours();
			case OutlookPackage.DAY_OUTLOOK__INSOLATION:
				return isSetInsolation();
			case OutlookPackage.DAY_OUTLOOK__CLOUD_COVER_MEAN:
				return isSetCloudCoverMean();
			case OutlookPackage.DAY_OUTLOOK__WIND_GUST_MAX:
				return isSetWindGustMax();
			case OutlookPackage.DAY_OUTLOOK__UV_INDEX_MAX:
				return isSetUvIndexMax();
			case OutlookPackage.DAY_OUTLOOK__WEATHER_CODE:
				return isSetWeatherCode();
			case OutlookPackage.DAY_OUTLOOK__SUNRISE:
				return SUNRISE_EDEFAULT == null ? sunrise != null : !SUNRISE_EDEFAULT.equals(sunrise);
			case OutlookPackage.DAY_OUTLOOK__SUNSET:
				return SUNSET_EDEFAULT == null ? sunset != null : !SUNSET_EDEFAULT.equals(sunset);
			case OutlookPackage.DAY_OUTLOOK__SOLAR_NOON:
				return SOLAR_NOON_EDEFAULT == null ? solarNoon != null : !SOLAR_NOON_EDEFAULT.equals(solarNoon);
			case OutlookPackage.DAY_OUTLOOK__DAYLIGHT_HOURS:
				return isSetDaylightHours();
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
		result.append(", temperatureMin: ");
		if (temperatureMinESet) result.append(temperatureMin); else result.append("<unset>");
		result.append(", temperatureMax: ");
		if (temperatureMaxESet) result.append(temperatureMax); else result.append("<unset>");
		result.append(", precipitation: ");
		if (precipitationESet) result.append(precipitation); else result.append("<unset>");
		result.append(", precipitationProbability: ");
		if (precipitationProbabilityESet) result.append(precipitationProbability); else result.append("<unset>");
		result.append(", sunshineHours: ");
		if (sunshineHoursESet) result.append(sunshineHours); else result.append("<unset>");
		result.append(", insolation: ");
		if (insolationESet) result.append(insolation); else result.append("<unset>");
		result.append(", cloudCoverMean: ");
		if (cloudCoverMeanESet) result.append(cloudCoverMean); else result.append("<unset>");
		result.append(", windGustMax: ");
		if (windGustMaxESet) result.append(windGustMax); else result.append("<unset>");
		result.append(", uvIndexMax: ");
		if (uvIndexMaxESet) result.append(uvIndexMax); else result.append("<unset>");
		result.append(", weatherCode: ");
		if (weatherCodeESet) result.append(weatherCode); else result.append("<unset>");
		result.append(", sunrise: ");
		result.append(sunrise);
		result.append(", sunset: ");
		result.append(sunset);
		result.append(", solarNoon: ");
		result.append(solarNoon);
		result.append(", daylightHours: ");
		if (daylightHoursESet) result.append(daylightHours); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //DayOutlookImpl
