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
package org.gecko.weather.provider.ecowitt.model.rtl433.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reading</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getChannel <em>Channel</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getBatteryOk <em>Battery Ok</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getBatteryMilliVolts <em>Battery Milli Volts</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getHumidity <em>Humidity</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getWindAverage <em>Wind Average</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getWindGust <em>Wind Gust</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getUvIndex <em>Uv Index</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getIlluminance <em>Illuminance</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getRainTotal <em>Rain Total</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getRainStart <em>Rain Start</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getSupercapVolts <em>Supercap Volts</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getFirmware <em>Firmware</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getMic <em>Mic</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getModulation <em>Modulation</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getRssi <em>Rssi</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getSnr <em>Snr</em>}</li>
 *   <li>{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl#getNoise <em>Noise</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReadingImpl extends MinimalEObjectImpl.Container implements Reading {
	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected String time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected String model = MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Long ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Long id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getChannel() <em>Channel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChannel()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CHANNEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChannel() <em>Channel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChannel()
	 * @generated
	 * @ordered
	 */
	protected Integer channel = CHANNEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatteryOk() <em>Battery Ok</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryOk()
	 * @generated
	 * @ordered
	 */
	protected static final Double BATTERY_OK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatteryOk() <em>Battery Ok</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryOk()
	 * @generated
	 * @ordered
	 */
	protected Double batteryOk = BATTERY_OK_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatteryMilliVolts() <em>Battery Milli Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryMilliVolts()
	 * @generated
	 * @ordered
	 */
	protected static final Integer BATTERY_MILLI_VOLTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatteryMilliVolts() <em>Battery Milli Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatteryMilliVolts()
	 * @generated
	 * @ordered
	 */
	protected Integer batteryMilliVolts = BATTERY_MILLI_VOLTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final Double TEMPERATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected Double temperature = TEMPERATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHumidity() <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHumidity()
	 * @generated
	 * @ordered
	 */
	protected static final Double HUMIDITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHumidity() <em>Humidity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHumidity()
	 * @generated
	 * @ordered
	 */
	protected Double humidity = HUMIDITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected static final Double WIND_DIRECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected Double windDirection = WIND_DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindAverage() <em>Wind Average</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindAverage()
	 * @generated
	 * @ordered
	 */
	protected static final Double WIND_AVERAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindAverage() <em>Wind Average</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindAverage()
	 * @generated
	 * @ordered
	 */
	protected Double windAverage = WIND_AVERAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGust() <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGust()
	 * @generated
	 * @ordered
	 */
	protected static final Double WIND_GUST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGust() <em>Wind Gust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGust()
	 * @generated
	 * @ordered
	 */
	protected Double windGust = WIND_GUST_EDEFAULT;

	/**
	 * The default value of the '{@link #getUvIndex() <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Double UV_INDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUvIndex() <em>Uv Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUvIndex()
	 * @generated
	 * @ordered
	 */
	protected Double uvIndex = UV_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getIlluminance() <em>Illuminance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIlluminance()
	 * @generated
	 * @ordered
	 */
	protected static final Double ILLUMINANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIlluminance() <em>Illuminance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIlluminance()
	 * @generated
	 * @ordered
	 */
	protected Double illuminance = ILLUMINANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRainTotal() <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainTotal()
	 * @generated
	 * @ordered
	 */
	protected static final Double RAIN_TOTAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRainTotal() <em>Rain Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainTotal()
	 * @generated
	 * @ordered
	 */
	protected Double rainTotal = RAIN_TOTAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getRainStart() <em>Rain Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainStart()
	 * @generated
	 * @ordered
	 */
	protected static final Integer RAIN_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRainStart() <em>Rain Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRainStart()
	 * @generated
	 * @ordered
	 */
	protected Integer rainStart = RAIN_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getSupercapVolts() <em>Supercap Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupercapVolts()
	 * @generated
	 * @ordered
	 */
	protected static final Double SUPERCAP_VOLTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSupercapVolts() <em>Supercap Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupercapVolts()
	 * @generated
	 * @ordered
	 */
	protected Double supercapVolts = SUPERCAP_VOLTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirmware() <em>Firmware</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirmware()
	 * @generated
	 * @ordered
	 */
	protected static final Integer FIRMWARE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFirmware() <em>Firmware</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirmware()
	 * @generated
	 * @ordered
	 */
	protected Integer firmware = FIRMWARE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFlags() <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlags()
	 * @generated
	 * @ordered
	 */
	protected static final Integer FLAGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFlags() <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlags()
	 * @generated
	 * @ordered
	 */
	protected Integer flags = FLAGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getData() <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected String data = DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #getMic() <em>Mic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMic()
	 * @generated
	 * @ordered
	 */
	protected static final String MIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMic() <em>Mic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMic()
	 * @generated
	 * @ordered
	 */
	protected String mic = MIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getModulation() <em>Modulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModulation()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModulation() <em>Modulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModulation()
	 * @generated
	 * @ordered
	 */
	protected String modulation = MODULATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRssi() <em>Rssi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRssi()
	 * @generated
	 * @ordered
	 */
	protected static final Double RSSI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRssi() <em>Rssi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRssi()
	 * @generated
	 * @ordered
	 */
	protected Double rssi = RSSI_EDEFAULT;

	/**
	 * The default value of the '{@link #getSnr() <em>Snr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnr()
	 * @generated
	 * @ordered
	 */
	protected static final Double SNR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSnr() <em>Snr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnr()
	 * @generated
	 * @ordered
	 */
	protected Double snr = SNR_EDEFAULT;

	/**
	 * The default value of the '{@link #getNoise() <em>Noise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoise()
	 * @generated
	 * @ordered
	 */
	protected static final Double NOISE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNoise() <em>Noise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoise()
	 * @generated
	 * @ordered
	 */
	protected Double noise = NOISE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReadingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Rtl433Package.Literals.READING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTime(String newTime) {
		String oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModel(String newModel) {
		String oldModel = model;
		model = newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__MODEL, oldModel, model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(Long newId) {
		Long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getChannel() {
		return channel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChannel(Integer newChannel) {
		Integer oldChannel = channel;
		channel = newChannel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__CHANNEL, oldChannel, channel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getBatteryOk() {
		return batteryOk;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryOk(Double newBatteryOk) {
		Double oldBatteryOk = batteryOk;
		batteryOk = newBatteryOk;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__BATTERY_OK, oldBatteryOk, batteryOk));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getBatteryMilliVolts() {
		return batteryMilliVolts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatteryMilliVolts(Integer newBatteryMilliVolts) {
		Integer oldBatteryMilliVolts = batteryMilliVolts;
		batteryMilliVolts = newBatteryMilliVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__BATTERY_MILLI_VOLTS, oldBatteryMilliVolts, batteryMilliVolts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getTemperature() {
		return temperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemperature(Double newTemperature) {
		Double oldTemperature = temperature;
		temperature = newTemperature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__TEMPERATURE, oldTemperature, temperature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getHumidity() {
		return humidity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHumidity(Double newHumidity) {
		Double oldHumidity = humidity;
		humidity = newHumidity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__HUMIDITY, oldHumidity, humidity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getWindDirection() {
		return windDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindDirection(Double newWindDirection) {
		Double oldWindDirection = windDirection;
		windDirection = newWindDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__WIND_DIRECTION, oldWindDirection, windDirection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getWindAverage() {
		return windAverage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindAverage(Double newWindAverage) {
		Double oldWindAverage = windAverage;
		windAverage = newWindAverage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__WIND_AVERAGE, oldWindAverage, windAverage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getWindGust() {
		return windGust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGust(Double newWindGust) {
		Double oldWindGust = windGust;
		windGust = newWindGust;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__WIND_GUST, oldWindGust, windGust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getUvIndex() {
		return uvIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUvIndex(Double newUvIndex) {
		Double oldUvIndex = uvIndex;
		uvIndex = newUvIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__UV_INDEX, oldUvIndex, uvIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getIlluminance() {
		return illuminance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIlluminance(Double newIlluminance) {
		Double oldIlluminance = illuminance;
		illuminance = newIlluminance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__ILLUMINANCE, oldIlluminance, illuminance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getRainTotal() {
		return rainTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRainTotal(Double newRainTotal) {
		Double oldRainTotal = rainTotal;
		rainTotal = newRainTotal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__RAIN_TOTAL, oldRainTotal, rainTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getRainStart() {
		return rainStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRainStart(Integer newRainStart) {
		Integer oldRainStart = rainStart;
		rainStart = newRainStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__RAIN_START, oldRainStart, rainStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getSupercapVolts() {
		return supercapVolts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupercapVolts(Double newSupercapVolts) {
		Double oldSupercapVolts = supercapVolts;
		supercapVolts = newSupercapVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__SUPERCAP_VOLTS, oldSupercapVolts, supercapVolts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getFirmware() {
		return firmware;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFirmware(Integer newFirmware) {
		Integer oldFirmware = firmware;
		firmware = newFirmware;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__FIRMWARE, oldFirmware, firmware));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getFlags() {
		return flags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(Integer newFlags) {
		Integer oldFlags = flags;
		flags = newFlags;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__FLAGS, oldFlags, flags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setData(String newData) {
		String oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__DATA, oldData, data));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMic() {
		return mic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMic(String newMic) {
		String oldMic = mic;
		mic = newMic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__MIC, oldMic, mic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getModulation() {
		return modulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModulation(String newModulation) {
		String oldModulation = modulation;
		modulation = newModulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__MODULATION, oldModulation, modulation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getRssi() {
		return rssi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRssi(Double newRssi) {
		Double oldRssi = rssi;
		rssi = newRssi;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__RSSI, oldRssi, rssi));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getSnr() {
		return snr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSnr(Double newSnr) {
		Double oldSnr = snr;
		snr = newSnr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__SNR, oldSnr, snr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getNoise() {
		return noise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNoise(Double newNoise) {
		Double oldNoise = noise;
		noise = newNoise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Rtl433Package.READING__NOISE, oldNoise, noise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Rtl433Package.READING__TIME:
				return getTime();
			case Rtl433Package.READING__MODEL:
				return getModel();
			case Rtl433Package.READING__ID:
				return getId();
			case Rtl433Package.READING__CHANNEL:
				return getChannel();
			case Rtl433Package.READING__BATTERY_OK:
				return getBatteryOk();
			case Rtl433Package.READING__BATTERY_MILLI_VOLTS:
				return getBatteryMilliVolts();
			case Rtl433Package.READING__TEMPERATURE:
				return getTemperature();
			case Rtl433Package.READING__HUMIDITY:
				return getHumidity();
			case Rtl433Package.READING__WIND_DIRECTION:
				return getWindDirection();
			case Rtl433Package.READING__WIND_AVERAGE:
				return getWindAverage();
			case Rtl433Package.READING__WIND_GUST:
				return getWindGust();
			case Rtl433Package.READING__UV_INDEX:
				return getUvIndex();
			case Rtl433Package.READING__ILLUMINANCE:
				return getIlluminance();
			case Rtl433Package.READING__RAIN_TOTAL:
				return getRainTotal();
			case Rtl433Package.READING__RAIN_START:
				return getRainStart();
			case Rtl433Package.READING__SUPERCAP_VOLTS:
				return getSupercapVolts();
			case Rtl433Package.READING__FIRMWARE:
				return getFirmware();
			case Rtl433Package.READING__FLAGS:
				return getFlags();
			case Rtl433Package.READING__DATA:
				return getData();
			case Rtl433Package.READING__MIC:
				return getMic();
			case Rtl433Package.READING__MODULATION:
				return getModulation();
			case Rtl433Package.READING__RSSI:
				return getRssi();
			case Rtl433Package.READING__SNR:
				return getSnr();
			case Rtl433Package.READING__NOISE:
				return getNoise();
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
			case Rtl433Package.READING__TIME:
				setTime((String)newValue);
				return;
			case Rtl433Package.READING__MODEL:
				setModel((String)newValue);
				return;
			case Rtl433Package.READING__ID:
				setId((Long)newValue);
				return;
			case Rtl433Package.READING__CHANNEL:
				setChannel((Integer)newValue);
				return;
			case Rtl433Package.READING__BATTERY_OK:
				setBatteryOk((Double)newValue);
				return;
			case Rtl433Package.READING__BATTERY_MILLI_VOLTS:
				setBatteryMilliVolts((Integer)newValue);
				return;
			case Rtl433Package.READING__TEMPERATURE:
				setTemperature((Double)newValue);
				return;
			case Rtl433Package.READING__HUMIDITY:
				setHumidity((Double)newValue);
				return;
			case Rtl433Package.READING__WIND_DIRECTION:
				setWindDirection((Double)newValue);
				return;
			case Rtl433Package.READING__WIND_AVERAGE:
				setWindAverage((Double)newValue);
				return;
			case Rtl433Package.READING__WIND_GUST:
				setWindGust((Double)newValue);
				return;
			case Rtl433Package.READING__UV_INDEX:
				setUvIndex((Double)newValue);
				return;
			case Rtl433Package.READING__ILLUMINANCE:
				setIlluminance((Double)newValue);
				return;
			case Rtl433Package.READING__RAIN_TOTAL:
				setRainTotal((Double)newValue);
				return;
			case Rtl433Package.READING__RAIN_START:
				setRainStart((Integer)newValue);
				return;
			case Rtl433Package.READING__SUPERCAP_VOLTS:
				setSupercapVolts((Double)newValue);
				return;
			case Rtl433Package.READING__FIRMWARE:
				setFirmware((Integer)newValue);
				return;
			case Rtl433Package.READING__FLAGS:
				setFlags((Integer)newValue);
				return;
			case Rtl433Package.READING__DATA:
				setData((String)newValue);
				return;
			case Rtl433Package.READING__MIC:
				setMic((String)newValue);
				return;
			case Rtl433Package.READING__MODULATION:
				setModulation((String)newValue);
				return;
			case Rtl433Package.READING__RSSI:
				setRssi((Double)newValue);
				return;
			case Rtl433Package.READING__SNR:
				setSnr((Double)newValue);
				return;
			case Rtl433Package.READING__NOISE:
				setNoise((Double)newValue);
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
			case Rtl433Package.READING__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case Rtl433Package.READING__MODEL:
				setModel(MODEL_EDEFAULT);
				return;
			case Rtl433Package.READING__ID:
				setId(ID_EDEFAULT);
				return;
			case Rtl433Package.READING__CHANNEL:
				setChannel(CHANNEL_EDEFAULT);
				return;
			case Rtl433Package.READING__BATTERY_OK:
				setBatteryOk(BATTERY_OK_EDEFAULT);
				return;
			case Rtl433Package.READING__BATTERY_MILLI_VOLTS:
				setBatteryMilliVolts(BATTERY_MILLI_VOLTS_EDEFAULT);
				return;
			case Rtl433Package.READING__TEMPERATURE:
				setTemperature(TEMPERATURE_EDEFAULT);
				return;
			case Rtl433Package.READING__HUMIDITY:
				setHumidity(HUMIDITY_EDEFAULT);
				return;
			case Rtl433Package.READING__WIND_DIRECTION:
				setWindDirection(WIND_DIRECTION_EDEFAULT);
				return;
			case Rtl433Package.READING__WIND_AVERAGE:
				setWindAverage(WIND_AVERAGE_EDEFAULT);
				return;
			case Rtl433Package.READING__WIND_GUST:
				setWindGust(WIND_GUST_EDEFAULT);
				return;
			case Rtl433Package.READING__UV_INDEX:
				setUvIndex(UV_INDEX_EDEFAULT);
				return;
			case Rtl433Package.READING__ILLUMINANCE:
				setIlluminance(ILLUMINANCE_EDEFAULT);
				return;
			case Rtl433Package.READING__RAIN_TOTAL:
				setRainTotal(RAIN_TOTAL_EDEFAULT);
				return;
			case Rtl433Package.READING__RAIN_START:
				setRainStart(RAIN_START_EDEFAULT);
				return;
			case Rtl433Package.READING__SUPERCAP_VOLTS:
				setSupercapVolts(SUPERCAP_VOLTS_EDEFAULT);
				return;
			case Rtl433Package.READING__FIRMWARE:
				setFirmware(FIRMWARE_EDEFAULT);
				return;
			case Rtl433Package.READING__FLAGS:
				setFlags(FLAGS_EDEFAULT);
				return;
			case Rtl433Package.READING__DATA:
				setData(DATA_EDEFAULT);
				return;
			case Rtl433Package.READING__MIC:
				setMic(MIC_EDEFAULT);
				return;
			case Rtl433Package.READING__MODULATION:
				setModulation(MODULATION_EDEFAULT);
				return;
			case Rtl433Package.READING__RSSI:
				setRssi(RSSI_EDEFAULT);
				return;
			case Rtl433Package.READING__SNR:
				setSnr(SNR_EDEFAULT);
				return;
			case Rtl433Package.READING__NOISE:
				setNoise(NOISE_EDEFAULT);
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
			case Rtl433Package.READING__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case Rtl433Package.READING__MODEL:
				return MODEL_EDEFAULT == null ? model != null : !MODEL_EDEFAULT.equals(model);
			case Rtl433Package.READING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Rtl433Package.READING__CHANNEL:
				return CHANNEL_EDEFAULT == null ? channel != null : !CHANNEL_EDEFAULT.equals(channel);
			case Rtl433Package.READING__BATTERY_OK:
				return BATTERY_OK_EDEFAULT == null ? batteryOk != null : !BATTERY_OK_EDEFAULT.equals(batteryOk);
			case Rtl433Package.READING__BATTERY_MILLI_VOLTS:
				return BATTERY_MILLI_VOLTS_EDEFAULT == null ? batteryMilliVolts != null : !BATTERY_MILLI_VOLTS_EDEFAULT.equals(batteryMilliVolts);
			case Rtl433Package.READING__TEMPERATURE:
				return TEMPERATURE_EDEFAULT == null ? temperature != null : !TEMPERATURE_EDEFAULT.equals(temperature);
			case Rtl433Package.READING__HUMIDITY:
				return HUMIDITY_EDEFAULT == null ? humidity != null : !HUMIDITY_EDEFAULT.equals(humidity);
			case Rtl433Package.READING__WIND_DIRECTION:
				return WIND_DIRECTION_EDEFAULT == null ? windDirection != null : !WIND_DIRECTION_EDEFAULT.equals(windDirection);
			case Rtl433Package.READING__WIND_AVERAGE:
				return WIND_AVERAGE_EDEFAULT == null ? windAverage != null : !WIND_AVERAGE_EDEFAULT.equals(windAverage);
			case Rtl433Package.READING__WIND_GUST:
				return WIND_GUST_EDEFAULT == null ? windGust != null : !WIND_GUST_EDEFAULT.equals(windGust);
			case Rtl433Package.READING__UV_INDEX:
				return UV_INDEX_EDEFAULT == null ? uvIndex != null : !UV_INDEX_EDEFAULT.equals(uvIndex);
			case Rtl433Package.READING__ILLUMINANCE:
				return ILLUMINANCE_EDEFAULT == null ? illuminance != null : !ILLUMINANCE_EDEFAULT.equals(illuminance);
			case Rtl433Package.READING__RAIN_TOTAL:
				return RAIN_TOTAL_EDEFAULT == null ? rainTotal != null : !RAIN_TOTAL_EDEFAULT.equals(rainTotal);
			case Rtl433Package.READING__RAIN_START:
				return RAIN_START_EDEFAULT == null ? rainStart != null : !RAIN_START_EDEFAULT.equals(rainStart);
			case Rtl433Package.READING__SUPERCAP_VOLTS:
				return SUPERCAP_VOLTS_EDEFAULT == null ? supercapVolts != null : !SUPERCAP_VOLTS_EDEFAULT.equals(supercapVolts);
			case Rtl433Package.READING__FIRMWARE:
				return FIRMWARE_EDEFAULT == null ? firmware != null : !FIRMWARE_EDEFAULT.equals(firmware);
			case Rtl433Package.READING__FLAGS:
				return FLAGS_EDEFAULT == null ? flags != null : !FLAGS_EDEFAULT.equals(flags);
			case Rtl433Package.READING__DATA:
				return DATA_EDEFAULT == null ? data != null : !DATA_EDEFAULT.equals(data);
			case Rtl433Package.READING__MIC:
				return MIC_EDEFAULT == null ? mic != null : !MIC_EDEFAULT.equals(mic);
			case Rtl433Package.READING__MODULATION:
				return MODULATION_EDEFAULT == null ? modulation != null : !MODULATION_EDEFAULT.equals(modulation);
			case Rtl433Package.READING__RSSI:
				return RSSI_EDEFAULT == null ? rssi != null : !RSSI_EDEFAULT.equals(rssi);
			case Rtl433Package.READING__SNR:
				return SNR_EDEFAULT == null ? snr != null : !SNR_EDEFAULT.equals(snr);
			case Rtl433Package.READING__NOISE:
				return NOISE_EDEFAULT == null ? noise != null : !NOISE_EDEFAULT.equals(noise);
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
		result.append(", model: ");
		result.append(model);
		result.append(", id: ");
		result.append(id);
		result.append(", channel: ");
		result.append(channel);
		result.append(", batteryOk: ");
		result.append(batteryOk);
		result.append(", batteryMilliVolts: ");
		result.append(batteryMilliVolts);
		result.append(", temperature: ");
		result.append(temperature);
		result.append(", humidity: ");
		result.append(humidity);
		result.append(", windDirection: ");
		result.append(windDirection);
		result.append(", windAverage: ");
		result.append(windAverage);
		result.append(", windGust: ");
		result.append(windGust);
		result.append(", uvIndex: ");
		result.append(uvIndex);
		result.append(", illuminance: ");
		result.append(illuminance);
		result.append(", rainTotal: ");
		result.append(rainTotal);
		result.append(", rainStart: ");
		result.append(rainStart);
		result.append(", supercapVolts: ");
		result.append(supercapVolts);
		result.append(", firmware: ");
		result.append(firmware);
		result.append(", flags: ");
		result.append(flags);
		result.append(", data: ");
		result.append(data);
		result.append(", mic: ");
		result.append(mic);
		result.append(", modulation: ");
		result.append(modulation);
		result.append(", rssi: ");
		result.append(rssi);
		result.append(", snr: ");
		result.append(snr);
		result.append(", noise: ");
		result.append(noise);
		result.append(')');
		return result.toString();
	}

} //ReadingImpl
