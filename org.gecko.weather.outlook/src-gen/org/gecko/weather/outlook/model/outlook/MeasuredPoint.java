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
 * A representation of the model object '<em><b>Measured Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * What the own station measured in one ten-minute slot of the look-back: means over the slot, the gust its maximum; 'time' is the slot's middle. Units as in HourOutlook.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getGlobalRadiation <em>Global Radiation</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint()
 * @model
 * @generated
 */
@ProviderType
public interface MeasuredPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTime <em>Time</em>}' attribute.
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
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see #isSetTemperature()
	 * @see #unsetTemperature()
	 * @see #setTemperature(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_Temperature()
	 * @model unsettable="true"
	 * @generated
	 */
	double getTemperature();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTemperature <em>Temperature</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemperature()
	 * @see #getTemperature()
	 * @see #setTemperature(double)
	 * @generated
	 */
	void unsetTemperature();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getTemperature <em>Temperature</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Humidity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Humidity</em>' attribute.
	 * @see #isSetHumidity()
	 * @see #unsetHumidity()
	 * @see #setHumidity(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_Humidity()
	 * @model unsettable="true"
	 * @generated
	 */
	double getHumidity();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getHumidity <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Humidity</em>' attribute.
	 * @see #isSetHumidity()
	 * @see #unsetHumidity()
	 * @see #getHumidity()
	 * @generated
	 */
	void setHumidity(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getHumidity <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHumidity()
	 * @see #getHumidity()
	 * @see #setHumidity(double)
	 * @generated
	 */
	void unsetHumidity();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getHumidity <em>Humidity</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Humidity</em>' attribute is set.
	 * @see #unsetHumidity()
	 * @see #getHumidity()
	 * @see #setHumidity(double)
	 * @generated
	 */
	boolean isSetHumidity();

	/**
	 * Returns the value of the '<em><b>Wind Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wind Speed</em>' attribute.
	 * @see #isSetWindSpeed()
	 * @see #unsetWindSpeed()
	 * @see #setWindSpeed(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_WindSpeed()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindSpeed();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindSpeed <em>Wind Speed</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindSpeed <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindSpeed()
	 * @see #getWindSpeed()
	 * @see #setWindSpeed(double)
	 * @generated
	 */
	void unsetWindSpeed();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindSpeed <em>Wind Speed</em>}' attribute is set.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_WindGust()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindGust();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindGust <em>Wind Gust</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindGust <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindGust()
	 * @see #getWindGust()
	 * @see #setWindGust(double)
	 * @generated
	 */
	void unsetWindGust();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindGust <em>Wind Gust</em>}' attribute is set.
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
	 * @return the value of the '<em>Wind Direction</em>' attribute.
	 * @see #isSetWindDirection()
	 * @see #unsetWindDirection()
	 * @see #setWindDirection(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_WindDirection()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindDirection();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindDirection <em>Wind Direction</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindDirection()
	 * @see #getWindDirection()
	 * @see #setWindDirection(double)
	 * @generated
	 */
	void unsetWindDirection();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getWindDirection <em>Wind Direction</em>}' attribute is set.
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
	 * @return the value of the '<em>Global Radiation</em>' attribute.
	 * @see #isSetGlobalRadiation()
	 * @see #unsetGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getMeasuredPoint_GlobalRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getGlobalRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getGlobalRadiation <em>Global Radiation</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	void unsetGlobalRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.MeasuredPoint#getGlobalRadiation <em>Global Radiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Global Radiation</em>' attribute is set.
	 * @see #unsetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	boolean isSetGlobalRadiation();

} // MeasuredPoint
