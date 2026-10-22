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
 * A representation of the model object '<em><b>Observation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The newest value per quantity from the site's own station: measured, not forecast. 'time' is the newest of them; a quantity the station does not report is unset. Units as in HourOutlook; rainTotal is the device's running counter in mm, not an hourly sum.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getStationId <em>Station Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getUvIndex <em>Uv Index</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getRainTotal <em>Rain Total</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getBatteryLevel <em>Battery Level</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getCapacitorVoltage <em>Capacitor Voltage</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Observation#getSignalStrength <em>Signal Strength</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation()
 * @model
 * @generated
 */
@ProviderType
public interface Observation extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_ProviderId()
	 * @model
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getProviderId <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider Id</em>' attribute.
	 * @see #getProviderId()
	 * @generated
	 */
	void setProviderId(String value);

	/**
	 * Returns the value of the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Id</em>' attribute.
	 * @see #setProductId(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_ProductId()
	 * @model
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Station Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Station Id</em>' attribute.
	 * @see #setStationId(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_StationId()
	 * @model
	 * @generated
	 */
	String getStationId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getStationId <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Station Id</em>' attribute.
	 * @see #getStationId()
	 * @generated
	 */
	void setStationId(String value);

	/**
	 * Returns the value of the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see #isSetTemperature()
	 * @see #unsetTemperature()
	 * @see #setTemperature(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_Temperature()
	 * @model unsettable="true"
	 * @generated
	 */
	double getTemperature();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getTemperature <em>Temperature</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemperature()
	 * @see #getTemperature()
	 * @see #setTemperature(double)
	 * @generated
	 */
	void unsetTemperature();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getTemperature <em>Temperature</em>}' attribute is set.
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
	 * <!-- begin-model-doc -->
	 * Relative humidity, %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Humidity</em>' attribute.
	 * @see #isSetHumidity()
	 * @see #unsetHumidity()
	 * @see #setHumidity(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_Humidity()
	 * @model unsettable="true"
	 * @generated
	 */
	double getHumidity();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getHumidity <em>Humidity</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getHumidity <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHumidity()
	 * @see #getHumidity()
	 * @see #setHumidity(double)
	 * @generated
	 */
	void unsetHumidity();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getHumidity <em>Humidity</em>}' attribute is set.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_WindSpeed()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindSpeed();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindSpeed <em>Wind Speed</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindSpeed <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindSpeed()
	 * @see #getWindSpeed()
	 * @see #setWindSpeed(double)
	 * @generated
	 */
	void unsetWindSpeed();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindSpeed <em>Wind Speed</em>}' attribute is set.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_WindGust()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindGust();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindGust <em>Wind Gust</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindGust <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindGust()
	 * @see #getWindGust()
	 * @see #setWindGust(double)
	 * @generated
	 */
	void unsetWindGust();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindGust <em>Wind Gust</em>}' attribute is set.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_WindDirection()
	 * @model unsettable="true"
	 * @generated
	 */
	double getWindDirection();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindDirection <em>Wind Direction</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWindDirection()
	 * @see #getWindDirection()
	 * @see #setWindDirection(double)
	 * @generated
	 */
	void unsetWindDirection();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getWindDirection <em>Wind Direction</em>}' attribute is set.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_GlobalRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getGlobalRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getGlobalRadiation <em>Global Radiation</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	void unsetGlobalRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getGlobalRadiation <em>Global Radiation</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Uv Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uv Index</em>' attribute.
	 * @see #isSetUvIndex()
	 * @see #unsetUvIndex()
	 * @see #setUvIndex(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_UvIndex()
	 * @model unsettable="true"
	 * @generated
	 */
	double getUvIndex();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getUvIndex <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uv Index</em>' attribute.
	 * @see #isSetUvIndex()
	 * @see #unsetUvIndex()
	 * @see #getUvIndex()
	 * @generated
	 */
	void setUvIndex(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getUvIndex <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUvIndex()
	 * @see #getUvIndex()
	 * @see #setUvIndex(double)
	 * @generated
	 */
	void unsetUvIndex();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getUvIndex <em>Uv Index</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Uv Index</em>' attribute is set.
	 * @see #unsetUvIndex()
	 * @see #getUvIndex()
	 * @see #setUvIndex(double)
	 * @generated
	 */
	boolean isSetUvIndex();

	/**
	 * Returns the value of the '<em><b>Rain Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rain Total</em>' attribute.
	 * @see #isSetRainTotal()
	 * @see #unsetRainTotal()
	 * @see #setRainTotal(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_RainTotal()
	 * @model unsettable="true"
	 * @generated
	 */
	double getRainTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getRainTotal <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rain Total</em>' attribute.
	 * @see #isSetRainTotal()
	 * @see #unsetRainTotal()
	 * @see #getRainTotal()
	 * @generated
	 */
	void setRainTotal(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getRainTotal <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRainTotal()
	 * @see #getRainTotal()
	 * @see #setRainTotal(double)
	 * @generated
	 */
	void unsetRainTotal();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getRainTotal <em>Rain Total</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rain Total</em>' attribute is set.
	 * @see #unsetRainTotal()
	 * @see #getRainTotal()
	 * @see #setRainTotal(double)
	 * @generated
	 */
	boolean isSetRainTotal();

	/**
	 * Returns the value of the '<em><b>Battery Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Station health: 1 fine, 0 empty.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Battery Level</em>' attribute.
	 * @see #isSetBatteryLevel()
	 * @see #unsetBatteryLevel()
	 * @see #setBatteryLevel(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_BatteryLevel()
	 * @model unsettable="true"
	 * @generated
	 */
	double getBatteryLevel();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getBatteryLevel <em>Battery Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Level</em>' attribute.
	 * @see #isSetBatteryLevel()
	 * @see #unsetBatteryLevel()
	 * @see #getBatteryLevel()
	 * @generated
	 */
	void setBatteryLevel(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getBatteryLevel <em>Battery Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBatteryLevel()
	 * @see #getBatteryLevel()
	 * @see #setBatteryLevel(double)
	 * @generated
	 */
	void unsetBatteryLevel();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getBatteryLevel <em>Battery Level</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Battery Level</em>' attribute is set.
	 * @see #unsetBatteryLevel()
	 * @see #getBatteryLevel()
	 * @see #setBatteryLevel(double)
	 * @generated
	 */
	boolean isSetBatteryLevel();

	/**
	 * Returns the value of the '<em><b>Capacitor Voltage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacitor Voltage</em>' attribute.
	 * @see #isSetCapacitorVoltage()
	 * @see #unsetCapacitorVoltage()
	 * @see #setCapacitorVoltage(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_CapacitorVoltage()
	 * @model unsettable="true"
	 * @generated
	 */
	double getCapacitorVoltage();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getCapacitorVoltage <em>Capacitor Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacitor Voltage</em>' attribute.
	 * @see #isSetCapacitorVoltage()
	 * @see #unsetCapacitorVoltage()
	 * @see #getCapacitorVoltage()
	 * @generated
	 */
	void setCapacitorVoltage(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getCapacitorVoltage <em>Capacitor Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCapacitorVoltage()
	 * @see #getCapacitorVoltage()
	 * @see #setCapacitorVoltage(double)
	 * @generated
	 */
	void unsetCapacitorVoltage();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getCapacitorVoltage <em>Capacitor Voltage</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Capacitor Voltage</em>' attribute is set.
	 * @see #unsetCapacitorVoltage()
	 * @see #getCapacitorVoltage()
	 * @see #setCapacitorVoltage(double)
	 * @generated
	 */
	boolean isSetCapacitorVoltage();

	/**
	 * Returns the value of the '<em><b>Signal Strength</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal Strength</em>' attribute.
	 * @see #isSetSignalStrength()
	 * @see #unsetSignalStrength()
	 * @see #setSignalStrength(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getObservation_SignalStrength()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSignalStrength();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getSignalStrength <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal Strength</em>' attribute.
	 * @see #isSetSignalStrength()
	 * @see #unsetSignalStrength()
	 * @see #getSignalStrength()
	 * @generated
	 */
	void setSignalStrength(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getSignalStrength <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSignalStrength()
	 * @see #getSignalStrength()
	 * @see #setSignalStrength(double)
	 * @generated
	 */
	void unsetSignalStrength();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.Observation#getSignalStrength <em>Signal Strength</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Signal Strength</em>' attribute is set.
	 * @see #unsetSignalStrength()
	 * @see #getSignalStrength()
	 * @see #setSignalStrength(double)
	 * @generated
	 */
	boolean isSetSignalStrength();

} // Observation
