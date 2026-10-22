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
package org.gecko.weather.provider.ecowitt.model.rtl433;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reading</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One line of the rtl_433 JSON stream.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModel <em>Model</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getChannel <em>Channel</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryOk <em>Battery Ok</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryMilliVolts <em>Battery Milli Volts</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindAverage <em>Wind Average</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getUvIndex <em>Uv Index</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getIlluminance <em>Illuminance</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainTotal <em>Rain Total</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainStart <em>Rain Start</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSupercapVolts <em>Supercap Volts</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFirmware <em>Firmware</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getData <em>Data</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getMic <em>Mic</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModulation <em>Modulation</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRssi <em>Rssi</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSnr <em>Snr</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getNoise <em>Noise</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading()
 * @model
 * @generated
 */
@ProviderType
public interface Reading extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Receive time as rtl_433 formats it (local time without zone unless -M time:utc); the receiver stamps its own clock instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(String)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Time()
	 * @model annotation="http://eclipse.org/fennec/codec key='time'"
	 * @generated
	 */
	String getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The decoder's device model name, e.g. Fineoffset-WS90.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see #setModel(String)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Model()
	 * @model annotation="http://eclipse.org/fennec/codec key='model'"
	 * @generated
	 */
	String getModel();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The device's own id, fixed per sensor (changes on a battery swap for some models).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(Long)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Id()
	 * @model annotation="http://eclipse.org/fennec/codec key='id'"
	 * @generated
	 */
	Long getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(Long value);

	/**
	 * Returns the value of the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Channel</em>' attribute.
	 * @see #setChannel(Integer)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Channel()
	 * @model annotation="http://eclipse.org/fennec/codec key='channel'"
	 * @generated
	 */
	Integer getChannel();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getChannel <em>Channel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Channel</em>' attribute.
	 * @see #getChannel()
	 * @generated
	 */
	void setChannel(Integer value);

	/**
	 * Returns the value of the '<em><b>Battery Ok</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 1.0 is fine, lower values mean a weak battery.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Battery Ok</em>' attribute.
	 * @see #setBatteryOk(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_BatteryOk()
	 * @model annotation="http://eclipse.org/fennec/codec key='battery_ok'"
	 * @generated
	 */
	Double getBatteryOk();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryOk <em>Battery Ok</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Ok</em>' attribute.
	 * @see #getBatteryOk()
	 * @generated
	 */
	void setBatteryOk(Double value);

	/**
	 * Returns the value of the '<em><b>Battery Milli Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Battery Milli Volts</em>' attribute.
	 * @see #setBatteryMilliVolts(Integer)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_BatteryMilliVolts()
	 * @model annotation="http://eclipse.org/fennec/codec key='battery_mV'"
	 * @generated
	 */
	Integer getBatteryMilliVolts();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryMilliVolts <em>Battery Milli Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Battery Milli Volts</em>' attribute.
	 * @see #getBatteryMilliVolts()
	 * @generated
	 */
	void setBatteryMilliVolts(Integer value);

	/**
	 * Returns the value of the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Air temperature, °C.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see #setTemperature(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Temperature()
	 * @model annotation="http://eclipse.org/fennec/codec key='temperature_C'"
	 * @generated
	 */
	Double getTemperature();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature</em>' attribute.
	 * @see #getTemperature()
	 * @generated
	 */
	void setTemperature(Double value);

	/**
	 * Returns the value of the '<em><b>Humidity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Relative humidity, %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Humidity</em>' attribute.
	 * @see #setHumidity(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Humidity()
	 * @model annotation="http://eclipse.org/fennec/codec key='humidity'"
	 * @generated
	 */
	Double getHumidity();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getHumidity <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Humidity</em>' attribute.
	 * @see #getHumidity()
	 * @generated
	 */
	void setHumidity(Double value);

	/**
	 * Returns the value of the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Direction the wind comes from, degrees clockwise from north.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Direction</em>' attribute.
	 * @see #setWindDirection(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_WindDirection()
	 * @model annotation="http://eclipse.org/fennec/codec key='wind_dir_deg'"
	 * @generated
	 */
	Double getWindDirection();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Direction</em>' attribute.
	 * @see #getWindDirection()
	 * @generated
	 */
	void setWindDirection(Double value);

	/**
	 * Returns the value of the '<em><b>Wind Average</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Wind speed averaged over the reporting interval, m/s.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Average</em>' attribute.
	 * @see #setWindAverage(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_WindAverage()
	 * @model annotation="http://eclipse.org/fennec/codec key='wind_avg_m_s'"
	 * @generated
	 */
	Double getWindAverage();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindAverage <em>Wind Average</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Average</em>' attribute.
	 * @see #getWindAverage()
	 * @generated
	 */
	void setWindAverage(Double value);

	/**
	 * Returns the value of the '<em><b>Wind Gust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Strongest gust in the reporting interval, m/s.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust</em>' attribute.
	 * @see #setWindGust(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_WindGust()
	 * @model annotation="http://eclipse.org/fennec/codec key='wind_max_m_s'"
	 * @generated
	 */
	Double getWindGust();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindGust <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust</em>' attribute.
	 * @see #getWindGust()
	 * @generated
	 */
	void setWindGust(Double value);

	/**
	 * Returns the value of the '<em><b>Uv Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uv Index</em>' attribute.
	 * @see #setUvIndex(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_UvIndex()
	 * @model annotation="http://eclipse.org/fennec/codec key='uvi'"
	 * @generated
	 */
	Double getUvIndex();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getUvIndex <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uv Index</em>' attribute.
	 * @see #getUvIndex()
	 * @generated
	 */
	void setUvIndex(Double value);

	/**
	 * Returns the value of the '<em><b>Illuminance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Illuminance, lux. Ecowitt derives solar radiation from it as lux / 126.7 W/m².
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Illuminance</em>' attribute.
	 * @see #setIlluminance(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Illuminance()
	 * @model annotation="http://eclipse.org/fennec/codec key='light_lux'"
	 * @generated
	 */
	Double getIlluminance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getIlluminance <em>Illuminance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Illuminance</em>' attribute.
	 * @see #getIlluminance()
	 * @generated
	 */
	void setIlluminance(Double value);

	/**
	 * Returns the value of the '<em><b>Rain Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain counter, mm since the device was last reset — a running total, not a period sum.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rain Total</em>' attribute.
	 * @see #setRainTotal(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_RainTotal()
	 * @model annotation="http://eclipse.org/fennec/codec key='rain_mm'"
	 * @generated
	 */
	Double getRainTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainTotal <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rain Total</em>' attribute.
	 * @see #getRainTotal()
	 * @generated
	 */
	void setRainTotal(Double value);

	/**
	 * Returns the value of the '<em><b>Rain Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rain Start</em>' attribute.
	 * @see #setRainStart(Integer)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_RainStart()
	 * @model annotation="http://eclipse.org/fennec/codec key='rain_start'"
	 * @generated
	 */
	Integer getRainStart();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainStart <em>Rain Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rain Start</em>' attribute.
	 * @see #getRainStart()
	 * @generated
	 */
	void setRainStart(Integer value);

	/**
	 * Returns the value of the '<em><b>Supercap Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supercap Volts</em>' attribute.
	 * @see #setSupercapVolts(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_SupercapVolts()
	 * @model annotation="http://eclipse.org/fennec/codec key='supercap_V'"
	 * @generated
	 */
	Double getSupercapVolts();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSupercapVolts <em>Supercap Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supercap Volts</em>' attribute.
	 * @see #getSupercapVolts()
	 * @generated
	 */
	void setSupercapVolts(Double value);

	/**
	 * Returns the value of the '<em><b>Firmware</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Firmware</em>' attribute.
	 * @see #setFirmware(Integer)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Firmware()
	 * @model annotation="http://eclipse.org/fennec/codec key='firmware'"
	 * @generated
	 */
	Integer getFirmware();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFirmware <em>Firmware</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Firmware</em>' attribute.
	 * @see #getFirmware()
	 * @generated
	 */
	void setFirmware(Integer value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(Integer)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Flags()
	 * @model annotation="http://eclipse.org/fennec/codec key='flags'"
	 * @generated
	 */
	Integer getFlags();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(Integer value);

	/**
	 * Returns the value of the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' attribute.
	 * @see #setData(String)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Data()
	 * @model annotation="http://eclipse.org/fennec/codec key='data'"
	 * @generated
	 */
	String getData();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getData <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' attribute.
	 * @see #getData()
	 * @generated
	 */
	void setData(String value);

	/**
	 * Returns the value of the '<em><b>Mic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Message integrity check that passed, e.g. CRC.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mic</em>' attribute.
	 * @see #setMic(String)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Mic()
	 * @model annotation="http://eclipse.org/fennec/codec key='mic'"
	 * @generated
	 */
	String getMic();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getMic <em>Mic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mic</em>' attribute.
	 * @see #getMic()
	 * @generated
	 */
	void setMic(String value);

	/**
	 * Returns the value of the '<em><b>Modulation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modulation</em>' attribute.
	 * @see #setModulation(String)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Modulation()
	 * @model annotation="http://eclipse.org/fennec/codec key='mod'"
	 * @generated
	 */
	String getModulation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModulation <em>Modulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modulation</em>' attribute.
	 * @see #getModulation()
	 * @generated
	 */
	void setModulation(String value);

	/**
	 * Returns the value of the '<em><b>Rssi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rssi</em>' attribute.
	 * @see #setRssi(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Rssi()
	 * @model annotation="http://eclipse.org/fennec/codec key='rssi'"
	 * @generated
	 */
	Double getRssi();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRssi <em>Rssi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rssi</em>' attribute.
	 * @see #getRssi()
	 * @generated
	 */
	void setRssi(Double value);

	/**
	 * Returns the value of the '<em><b>Snr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snr</em>' attribute.
	 * @see #setSnr(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Snr()
	 * @model annotation="http://eclipse.org/fennec/codec key='snr'"
	 * @generated
	 */
	Double getSnr();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSnr <em>Snr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snr</em>' attribute.
	 * @see #getSnr()
	 * @generated
	 */
	void setSnr(Double value);

	/**
	 * Returns the value of the '<em><b>Noise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Noise</em>' attribute.
	 * @see #setNoise(Double)
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#getReading_Noise()
	 * @model annotation="http://eclipse.org/fennec/codec key='noise'"
	 * @generated
	 */
	Double getNoise();

	/**
	 * Sets the value of the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getNoise <em>Noise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Noise</em>' attribute.
	 * @see #getNoise()
	 * @generated
	 */
	void setNoise(Double value);

} // Reading
