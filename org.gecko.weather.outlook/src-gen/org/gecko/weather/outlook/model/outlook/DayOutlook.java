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
package org.gecko.weather.outlook.model.outlook;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Day Outlook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One calendar day in the site's time zone, summarised from the hourly values of that day.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin <em>Temperature Min</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax <em>Temperature Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation <em>Precipitation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours <em>Sunshine Hours</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean <em>Cloud Cover Mean</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax <em>Wind Gust Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax <em>Uv Index Max</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode <em>Weather Code</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours <em>Daylight Hours</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook()
 * @model
 * @generated
 */
@ProviderType
public interface DayOutlook extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ISO local date, e.g. 2026-10-05.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_Date()
	 * @model
	 * @generated
	 */
	String getDate();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(String value);

	/**
	 * Returns the value of the '<em><b>Temperature Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temperature Min</em>' attribute.
	 * @see #isSetTemperatureMin()
	 * @see #unsetTemperatureMin()
	 * @see #setTemperatureMin(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_TemperatureMin()
	 * @model unsettable="true"
	 * @generated
	 */
	double getTemperatureMin();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin <em>Temperature Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature Min</em>' attribute.
	 * @see #isSetTemperatureMin()
	 * @see #unsetTemperatureMin()
	 * @see #getTemperatureMin()
	 * @generated
	 */
	void setTemperatureMin(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin <em>Temperature Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemperatureMin()
	 * @see #getTemperatureMin()
	 * @see #setTemperatureMin(double)
	 * @generated
	 */
	void unsetTemperatureMin();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMin <em>Temperature Min</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Temperature Min</em>' attribute is set.
	 * @see #unsetTemperatureMin()
	 * @see #getTemperatureMin()
	 * @see #setTemperatureMin(double)
	 * @generated
	 */
	boolean isSetTemperatureMin();

	/**
	 * Returns the value of the '<em><b>Temperature Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temperature Max</em>' attribute.
	 * @see #isSetTemperatureMax()
	 * @see #unsetTemperatureMax()
	 * @see #setTemperatureMax(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_TemperatureMax()
	 * @model unsettable="true"
	 * @generated
	 */
	double getTemperatureMax();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax <em>Temperature Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature Max</em>' attribute.
	 * @see #isSetTemperatureMax()
	 * @see #unsetTemperatureMax()
	 * @see #getTemperatureMax()
	 * @generated
	 */
	void setTemperatureMax(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax <em>Temperature Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemperatureMax()
	 * @see #getTemperatureMax()
	 * @see #setTemperatureMax(double)
	 * @generated
	 */
	void unsetTemperatureMax();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getTemperatureMax <em>Temperature Max</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Temperature Max</em>' attribute is set.
	 * @see #unsetTemperatureMax()
	 * @see #getTemperatureMax()
	 * @see #setTemperatureMax(double)
	 * @generated
	 */
	boolean isSetTemperatureMax();

	/**
	 * Returns the value of the '<em><b>Precipitation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * mm over the day.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation</em>' attribute.
	 * @see #isSetPrecipitation()
	 * @see #unsetPrecipitation()
	 * @see #setPrecipitation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_Precipitation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPrecipitation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation <em>Precipitation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation</em>' attribute.
	 * @see #isSetPrecipitation()
	 * @see #unsetPrecipitation()
	 * @see #getPrecipitation()
	 * @generated
	 */
	void setPrecipitation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation <em>Precipitation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPrecipitation()
	 * @see #getPrecipitation()
	 * @see #setPrecipitation(double)
	 * @generated
	 */
	void unsetPrecipitation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitation <em>Precipitation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Precipitation</em>' attribute is set.
	 * @see #unsetPrecipitation()
	 * @see #getPrecipitation()
	 * @see #setPrecipitation(double)
	 * @generated
	 */
	boolean isSetPrecipitation();

	/**
	 * Returns the value of the '<em><b>Precipitation Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Highest hourly probability of more than 0.1 mm, in %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Probability</em>' attribute.
	 * @see #isSetPrecipitationProbability()
	 * @see #unsetPrecipitationProbability()
	 * @see #setPrecipitationProbability(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_PrecipitationProbability()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPrecipitationProbability();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Probability</em>' attribute.
	 * @see #isSetPrecipitationProbability()
	 * @see #unsetPrecipitationProbability()
	 * @see #getPrecipitationProbability()
	 * @generated
	 */
	void setPrecipitationProbability(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPrecipitationProbability()
	 * @see #getPrecipitationProbability()
	 * @see #setPrecipitationProbability(double)
	 * @generated
	 */
	void unsetPrecipitationProbability();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Precipitation Probability</em>' attribute is set.
	 * @see #unsetPrecipitationProbability()
	 * @see #getPrecipitationProbability()
	 * @see #setPrecipitationProbability(double)
	 * @generated
	 */
	boolean isSetPrecipitationProbability();

	/**
	 * Returns the value of the '<em><b>Sunshine Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunshine Hours</em>' attribute.
	 * @see #isSetSunshineHours()
	 * @see #unsetSunshineHours()
	 * @see #setSunshineHours(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_SunshineHours()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSunshineHours();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours <em>Sunshine Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunshine Hours</em>' attribute.
	 * @see #isSetSunshineHours()
	 * @see #unsetSunshineHours()
	 * @see #getSunshineHours()
	 * @generated
	 */
	void setSunshineHours(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours <em>Sunshine Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSunshineHours()
	 * @see #getSunshineHours()
	 * @see #setSunshineHours(double)
	 * @generated
	 */
	void unsetSunshineHours();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunshineHours <em>Sunshine Hours</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sunshine Hours</em>' attribute is set.
	 * @see #unsetSunshineHours()
	 * @see #getSunshineHours()
	 * @see #setSunshineHours(double)
	 * @generated
	 */
	boolean isSetSunshineHours();

	/**
	 * Returns the value of the '<em><b>Cloud Cover Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cloud Cover Mean</em>' attribute.
	 * @see #isSetCloudCoverMean()
	 * @see #unsetCloudCoverMean()
	 * @see #setCloudCoverMean(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_CloudCoverMean()
	 * @model unsettable="true"
	 * @generated
	 */
	double getCloudCoverMean();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean <em>Cloud Cover Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Mean</em>' attribute.
	 * @see #isSetCloudCoverMean()
	 * @see #unsetCloudCoverMean()
	 * @see #getCloudCoverMean()
	 * @generated
	 */
	void setCloudCoverMean(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean <em>Cloud Cover Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCloudCoverMean()
	 * @see #getCloudCoverMean()
	 * @see #setCloudCoverMean(double)
	 * @generated
	 */
	void unsetCloudCoverMean();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getCloudCoverMean <em>Cloud Cover Mean</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cloud Cover Mean</em>' attribute is set.
	 * @see #unsetCloudCoverMean()
	 * @see #getCloudCoverMean()
	 * @see #setCloudCoverMean(double)
	 * @generated
	 */
	boolean isSetCloudCoverMean();

	/**
	 * Returns the value of the '<em><b>Wind Gust Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wind Gust Max</em>' attribute.
	 * @see #isSetWindGustMax()
	 * @see #unsetWindGustMax()
	 * @see #setWindGustMax(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_WindGustMax()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindGustMax();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax <em>Wind Gust Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Max</em>' attribute.
	 * @see #isSetWindGustMax()
	 * @see #unsetWindGustMax()
	 * @see #getWindGustMax()
	 * @generated
	 */
	void setWindGustMax(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax <em>Wind Gust Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindGustMax()
	 * @see #getWindGustMax()
	 * @see #setWindGustMax(double)
	 * @generated
	 */
	void unsetWindGustMax();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWindGustMax <em>Wind Gust Max</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Wind Gust Max</em>' attribute is set.
	 * @see #unsetWindGustMax()
	 * @see #getWindGustMax()
	 * @see #setWindGustMax(double)
	 * @generated
	 */
	boolean isSetWindGustMax();

	/**
	 * Returns the value of the '<em><b>Uv Index Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uv Index Max</em>' attribute.
	 * @see #isSetUvIndexMax()
	 * @see #unsetUvIndexMax()
	 * @see #setUvIndexMax(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_UvIndexMax()
	 * @model unsettable="true"
	 * @generated
	 */
	double getUvIndexMax();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax <em>Uv Index Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uv Index Max</em>' attribute.
	 * @see #isSetUvIndexMax()
	 * @see #unsetUvIndexMax()
	 * @see #getUvIndexMax()
	 * @generated
	 */
	void setUvIndexMax(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax <em>Uv Index Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUvIndexMax()
	 * @see #getUvIndexMax()
	 * @see #setUvIndexMax(double)
	 * @generated
	 */
	void unsetUvIndexMax();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getUvIndexMax <em>Uv Index Max</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Uv Index Max</em>' attribute is set.
	 * @see #unsetUvIndexMax()
	 * @see #getUvIndexMax()
	 * @see #setUvIndexMax(double)
	 * @generated
	 */
	boolean isSetUvIndexMax();

	/**
	 * Returns the value of the '<em><b>Weather Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The most significant ww code of the day's hours.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Weather Code</em>' attribute.
	 * @see #isSetWeatherCode()
	 * @see #unsetWeatherCode()
	 * @see #setWeatherCode(int)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_WeatherCode()
	 * @model unsettable="true"
	 * @generated
	 */
	int getWeatherCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode <em>Weather Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Code</em>' attribute.
	 * @see #isSetWeatherCode()
	 * @see #unsetWeatherCode()
	 * @see #getWeatherCode()
	 * @generated
	 */
	void setWeatherCode(int value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode <em>Weather Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWeatherCode()
	 * @see #getWeatherCode()
	 * @see #setWeatherCode(int)
	 * @generated
	 */
	void unsetWeatherCode();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getWeatherCode <em>Weather Code</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Weather Code</em>' attribute is set.
	 * @see #unsetWeatherCode()
	 * @see #getWeatherCode()
	 * @see #setWeatherCode(int)
	 * @generated
	 */
	boolean isSetWeatherCode();

	/**
	 * Returns the value of the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunrise</em>' attribute.
	 * @see #setSunrise(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_Sunrise()
	 * @model
	 * @generated
	 */
	Date getSunrise();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunrise <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunrise</em>' attribute.
	 * @see #getSunrise()
	 * @generated
	 */
	void setSunrise(Date value);

	/**
	 * Returns the value of the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunset</em>' attribute.
	 * @see #setSunset(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_Sunset()
	 * @model
	 * @generated
	 */
	Date getSunset();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getSunset <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunset</em>' attribute.
	 * @see #getSunset()
	 * @generated
	 */
	void setSunset(Date value);

	/**
	 * Returns the value of the '<em><b>Daylight Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daylight Hours</em>' attribute.
	 * @see #isSetDaylightHours()
	 * @see #unsetDaylightHours()
	 * @see #setDaylightHours(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getDayOutlook_DaylightHours()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDaylightHours();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours <em>Daylight Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daylight Hours</em>' attribute.
	 * @see #isSetDaylightHours()
	 * @see #unsetDaylightHours()
	 * @see #getDaylightHours()
	 * @generated
	 */
	void setDaylightHours(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours <em>Daylight Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDaylightHours()
	 * @see #getDaylightHours()
	 * @see #setDaylightHours(double)
	 * @generated
	 */
	void unsetDaylightHours();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.DayOutlook#getDaylightHours <em>Daylight Hours</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Daylight Hours</em>' attribute is set.
	 * @see #unsetDaylightHours()
	 * @see #getDaylightHours()
	 * @see #setDaylightHours(double)
	 * @generated
	 */
	boolean isSetDaylightHours();

} // DayOutlook
