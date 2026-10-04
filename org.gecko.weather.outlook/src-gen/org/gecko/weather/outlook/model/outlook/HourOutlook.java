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
 * A representation of the model object '<em><b>Hour Outlook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One hour, from 'time' to one hour later. Instant quantities (temperature, cloud cover, wind) are the values at 'time'; quantities over a period (precipitation, gusts, radiation, weather code) are the ones of the hour that starts at 'time'. Unset means no source has the value.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint <em>Dew Point</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover <em>Cloud Cover</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation <em>Precipitation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDirectRadiation <em>Direct Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDiffuseRadiation <em>Diffuse Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation <em>Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunAzimuth <em>Sun Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode <em>Weather Code</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.HourOutlook#isDaylight <em>Daylight</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook()
 * @model
 * @generated
 */
@ProviderType
public interface HourOutlook extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * °C at 2 m.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see #isSetTemperature()
	 * @see #unsetTemperature()
	 * @see #setTemperature(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_Temperature()
	 * @model unsettable="true"
	 * @generated
	 */
	double getTemperature();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature</em>' attribute.
	 * @see #isSetTemperature()
	 * @see #unsetTemperature()
	 * @see #getTemperature()
	 * @generated
	 */
	void setTemperature(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemperature()
	 * @see #getTemperature()
	 * @see #setTemperature(double)
	 * @generated
	 */
	void unsetTemperature();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getTemperature <em>Temperature</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Temperature</em>' attribute is set.
	 * @see #unsetTemperature()
	 * @see #getTemperature()
	 * @see #setTemperature(double)
	 * @generated
	 */
	boolean isSetTemperature();

	/**
	 * Returns the value of the '<em><b>Dew Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dew Point</em>' attribute.
	 * @see #isSetDewPoint()
	 * @see #unsetDewPoint()
	 * @see #setDewPoint(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_DewPoint()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDewPoint();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint <em>Dew Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dew Point</em>' attribute.
	 * @see #isSetDewPoint()
	 * @see #unsetDewPoint()
	 * @see #getDewPoint()
	 * @generated
	 */
	void setDewPoint(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint <em>Dew Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDewPoint()
	 * @see #getDewPoint()
	 * @see #setDewPoint(double)
	 * @generated
	 */
	void unsetDewPoint();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDewPoint <em>Dew Point</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Dew Point</em>' attribute is set.
	 * @see #unsetDewPoint()
	 * @see #getDewPoint()
	 * @see #setDewPoint(double)
	 * @generated
	 */
	boolean isSetDewPoint();

	/**
	 * Returns the value of the '<em><b>Cloud Cover</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total cloud cover in %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover</em>' attribute.
	 * @see #isSetCloudCover()
	 * @see #unsetCloudCover()
	 * @see #setCloudCover(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_CloudCover()
	 * @model unsettable="true"
	 * @generated
	 */
	double getCloudCover();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover <em>Cloud Cover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover</em>' attribute.
	 * @see #isSetCloudCover()
	 * @see #unsetCloudCover()
	 * @see #getCloudCover()
	 * @generated
	 */
	void setCloudCover(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover <em>Cloud Cover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCloudCover()
	 * @see #getCloudCover()
	 * @see #setCloudCover(double)
	 * @generated
	 */
	void unsetCloudCover();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getCloudCover <em>Cloud Cover</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cloud Cover</em>' attribute is set.
	 * @see #unsetCloudCover()
	 * @see #getCloudCover()
	 * @see #setCloudCover(double)
	 * @generated
	 */
	boolean isSetCloudCover();

	/**
	 * Returns the value of the '<em><b>Precipitation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * mm in the hour.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation</em>' attribute.
	 * @see #isSetPrecipitation()
	 * @see #unsetPrecipitation()
	 * @see #setPrecipitation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_Precipitation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPrecipitation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation <em>Precipitation</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation <em>Precipitation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPrecipitation()
	 * @see #getPrecipitation()
	 * @see #setPrecipitation(double)
	 * @generated
	 */
	void unsetPrecipitation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitation <em>Precipitation</em>}' attribute is set.
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
	 * % probability of more than 0.1 mm in the hour.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Probability</em>' attribute.
	 * @see #isSetPrecipitationProbability()
	 * @see #unsetPrecipitationProbability()
	 * @see #setPrecipitationProbability(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_PrecipitationProbability()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPrecipitationProbability();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPrecipitationProbability()
	 * @see #getPrecipitationProbability()
	 * @see #setPrecipitationProbability(double)
	 * @generated
	 */
	void unsetPrecipitationProbability();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getPrecipitationProbability <em>Precipitation Probability</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Wind Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * m/s at 10 m.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Speed</em>' attribute.
	 * @see #isSetWindSpeed()
	 * @see #unsetWindSpeed()
	 * @see #setWindSpeed(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_WindSpeed()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindSpeed();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Speed</em>' attribute.
	 * @see #isSetWindSpeed()
	 * @see #unsetWindSpeed()
	 * @see #getWindSpeed()
	 * @generated
	 */
	void setWindSpeed(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindSpeed()
	 * @see #getWindSpeed()
	 * @see #setWindSpeed(double)
	 * @generated
	 */
	void unsetWindSpeed();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindSpeed <em>Wind Speed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Wind Speed</em>' attribute is set.
	 * @see #unsetWindSpeed()
	 * @see #getWindSpeed()
	 * @see #setWindSpeed(double)
	 * @generated
	 */
	boolean isSetWindSpeed();

	/**
	 * Returns the value of the '<em><b>Wind Gust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wind Gust</em>' attribute.
	 * @see #isSetWindGust()
	 * @see #unsetWindGust()
	 * @see #setWindGust(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_WindGust()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindGust();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust</em>' attribute.
	 * @see #isSetWindGust()
	 * @see #unsetWindGust()
	 * @see #getWindGust()
	 * @generated
	 */
	void setWindGust(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindGust()
	 * @see #getWindGust()
	 * @see #setWindGust(double)
	 * @generated
	 */
	void unsetWindGust();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindGust <em>Wind Gust</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Wind Gust</em>' attribute is set.
	 * @see #unsetWindGust()
	 * @see #getWindGust()
	 * @see #setWindGust(double)
	 * @generated
	 */
	boolean isSetWindGust();

	/**
	 * Returns the value of the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Degrees the wind comes from, 0 = north.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Direction</em>' attribute.
	 * @see #isSetWindDirection()
	 * @see #unsetWindDirection()
	 * @see #setWindDirection(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_WindDirection()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindDirection();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Direction</em>' attribute.
	 * @see #isSetWindDirection()
	 * @see #unsetWindDirection()
	 * @see #getWindDirection()
	 * @generated
	 */
	void setWindDirection(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindDirection()
	 * @see #getWindDirection()
	 * @see #setWindDirection(double)
	 * @generated
	 */
	void unsetWindDirection();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWindDirection <em>Wind Direction</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Wind Direction</em>' attribute is set.
	 * @see #unsetWindDirection()
	 * @see #getWindDirection()
	 * @see #setWindDirection(double)
	 * @generated
	 */
	boolean isSetWindDirection();

	/**
	 * Returns the value of the '<em><b>Global Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean W/m² over the hour.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global Radiation</em>' attribute.
	 * @see #isSetGlobalRadiation()
	 * @see #unsetGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_GlobalRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getGlobalRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Radiation</em>' attribute.
	 * @see #isSetGlobalRadiation()
	 * @see #unsetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @generated
	 */
	void setGlobalRadiation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	void unsetGlobalRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getGlobalRadiation <em>Global Radiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Global Radiation</em>' attribute is set.
	 * @see #unsetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	boolean isSetGlobalRadiation();

	/**
	 * Returns the value of the '<em><b>Direct Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean direct shortwave radiation on a horizontal surface over the hour, W/m² — only where a source splits it (ICON-D2). With diffuseRadiation and the sun's position it is what a PV computation needs for a tilted module.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Direct Radiation</em>' attribute.
	 * @see #isSetDirectRadiation()
	 * @see #unsetDirectRadiation()
	 * @see #setDirectRadiation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_DirectRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDirectRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDirectRadiation <em>Direct Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direct Radiation</em>' attribute.
	 * @see #isSetDirectRadiation()
	 * @see #unsetDirectRadiation()
	 * @see #getDirectRadiation()
	 * @generated
	 */
	void setDirectRadiation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDirectRadiation <em>Direct Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDirectRadiation()
	 * @see #getDirectRadiation()
	 * @see #setDirectRadiation(double)
	 * @generated
	 */
	void unsetDirectRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDirectRadiation <em>Direct Radiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Direct Radiation</em>' attribute is set.
	 * @see #unsetDirectRadiation()
	 * @see #getDirectRadiation()
	 * @see #setDirectRadiation(double)
	 * @generated
	 */
	boolean isSetDirectRadiation();

	/**
	 * Returns the value of the '<em><b>Diffuse Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean diffuse shortwave radiation on a horizontal surface over the hour, W/m².
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Diffuse Radiation</em>' attribute.
	 * @see #isSetDiffuseRadiation()
	 * @see #unsetDiffuseRadiation()
	 * @see #setDiffuseRadiation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_DiffuseRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDiffuseRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDiffuseRadiation <em>Diffuse Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diffuse Radiation</em>' attribute.
	 * @see #isSetDiffuseRadiation()
	 * @see #unsetDiffuseRadiation()
	 * @see #getDiffuseRadiation()
	 * @generated
	 */
	void setDiffuseRadiation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDiffuseRadiation <em>Diffuse Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDiffuseRadiation()
	 * @see #getDiffuseRadiation()
	 * @see #setDiffuseRadiation(double)
	 * @generated
	 */
	void unsetDiffuseRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getDiffuseRadiation <em>Diffuse Radiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Diffuse Radiation</em>' attribute is set.
	 * @see #unsetDiffuseRadiation()
	 * @see #getDiffuseRadiation()
	 * @see #setDiffuseRadiation(double)
	 * @generated
	 */
	boolean isSetDiffuseRadiation();

	/**
	 * Returns the value of the '<em><b>Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sun Elevation</em>' attribute.
	 * @see #isSetSunElevation()
	 * @see #unsetSunElevation()
	 * @see #setSunElevation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_SunElevation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSunElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sun Elevation</em>' attribute.
	 * @see #isSetSunElevation()
	 * @see #unsetSunElevation()
	 * @see #getSunElevation()
	 * @generated
	 */
	void setSunElevation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSunElevation()
	 * @see #getSunElevation()
	 * @see #setSunElevation(double)
	 * @generated
	 */
	void unsetSunElevation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunElevation <em>Sun Elevation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sun Elevation</em>' attribute is set.
	 * @see #unsetSunElevation()
	 * @see #getSunElevation()
	 * @see #setSunElevation(double)
	 * @generated
	 */
	boolean isSetSunElevation();

	/**
	 * Returns the value of the '<em><b>Sun Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Degrees clockwise from north.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sun Azimuth</em>' attribute.
	 * @see #isSetSunAzimuth()
	 * @see #unsetSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_SunAzimuth()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSunAzimuth();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunAzimuth <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sun Azimuth</em>' attribute.
	 * @see #isSetSunAzimuth()
	 * @see #unsetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @generated
	 */
	void setSunAzimuth(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunAzimuth <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @generated
	 */
	void unsetSunAzimuth();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getSunAzimuth <em>Sun Azimuth</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sun Azimuth</em>' attribute is set.
	 * @see #unsetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @generated
	 */
	boolean isSetSunAzimuth();

	/**
	 * Returns the value of the '<em><b>Weather Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * WMO present-weather code (ww, 0..99) of the hour.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Weather Code</em>' attribute.
	 * @see #isSetWeatherCode()
	 * @see #unsetWeatherCode()
	 * @see #setWeatherCode(int)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_WeatherCode()
	 * @model unsettable="true"
	 * @generated
	 */
	int getWeatherCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode <em>Weather Code</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode <em>Weather Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWeatherCode()
	 * @see #getWeatherCode()
	 * @see #setWeatherCode(int)
	 * @generated
	 */
	void unsetWeatherCode();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#getWeatherCode <em>Weather Code</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Daylight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daylight</em>' attribute.
	 * @see #setDaylight(boolean)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getHourOutlook_Daylight()
	 * @model
	 * @generated
	 */
	boolean isDaylight();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.HourOutlook#isDaylight <em>Daylight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daylight</em>' attribute.
	 * @see #isDaylight()
	 * @generated
	 */
	void setDaylight(boolean value);

} // HourOutlook
