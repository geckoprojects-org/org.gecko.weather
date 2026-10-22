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


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.fennec.emf.osgi.annotation.provide.EPackage;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * One decoded telegram as rtl_433 emits it in its JSON output (-F json, -F http /stream, MQTT): the generic envelope (time, model, id, signal) and the fields the Fine Offset / Ecowitt station decoders produce (WS90, WS80). Member names are rtl_433's, mapped through the codec key annotation; units are rtl_433's canonical SI ones (°C, m/s, mm, lux). Fields a telegram does not carry are null; fields the model does not know are skipped.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Factory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = Rtl433Package.eNS_URI, fingerprint = "fp1:527aadfdb30fd376552f16a4cf782944cf86ffb197bee9014c4e06a7d3a7dc95", genModel = "/model/rtl433.genmodel", genModelSourceLocations = {"model/rtl433.genmodel","org.gecko.weather.provider.ecowitt/model/rtl433.genmodel"}, ecore = "/model/rtl433.ecore", ecoreSourceLocations = "/model/rtl433.ecore")
public interface Rtl433Package extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rtl433";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/rtl433/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rtl433";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Rtl433Package eINSTANCE = org.gecko.weather.provider.ecowitt.model.rtl433.impl.Rtl433PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl <em>Reading</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.impl.Rtl433PackageImpl#getReading()
	 * @generated
	 */
	int READING = 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__TIME = 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__MODEL = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__ID = 2;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__CHANNEL = 3;

	/**
	 * The feature id for the '<em><b>Battery Ok</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__BATTERY_OK = 4;

	/**
	 * The feature id for the '<em><b>Battery Milli Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__BATTERY_MILLI_VOLTS = 5;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__TEMPERATURE = 6;

	/**
	 * The feature id for the '<em><b>Humidity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__HUMIDITY = 7;

	/**
	 * The feature id for the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__WIND_DIRECTION = 8;

	/**
	 * The feature id for the '<em><b>Wind Average</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__WIND_AVERAGE = 9;

	/**
	 * The feature id for the '<em><b>Wind Gust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__WIND_GUST = 10;

	/**
	 * The feature id for the '<em><b>Uv Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__UV_INDEX = 11;

	/**
	 * The feature id for the '<em><b>Illuminance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__ILLUMINANCE = 12;

	/**
	 * The feature id for the '<em><b>Rain Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__RAIN_TOTAL = 13;

	/**
	 * The feature id for the '<em><b>Rain Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__RAIN_START = 14;

	/**
	 * The feature id for the '<em><b>Supercap Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__SUPERCAP_VOLTS = 15;

	/**
	 * The feature id for the '<em><b>Firmware</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__FIRMWARE = 16;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__FLAGS = 17;

	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__DATA = 18;

	/**
	 * The feature id for the '<em><b>Mic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__MIC = 19;

	/**
	 * The feature id for the '<em><b>Modulation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__MODULATION = 20;

	/**
	 * The feature id for the '<em><b>Rssi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__RSSI = 21;

	/**
	 * The feature id for the '<em><b>Snr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__SNR = 22;

	/**
	 * The feature id for the '<em><b>Noise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING__NOISE = 23;

	/**
	 * The number of structural features of the '<em>Reading</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_FEATURE_COUNT = 24;

	/**
	 * The number of operations of the '<em>Reading</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading <em>Reading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reading</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading
	 * @generated
	 */
	EClass getReading();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTime()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Time();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModel()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Model();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getId()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getChannel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Channel</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getChannel()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Channel();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryOk <em>Battery Ok</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Battery Ok</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryOk()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_BatteryOk();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryMilliVolts <em>Battery Milli Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Battery Milli Volts</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getBatteryMilliVolts()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_BatteryMilliVolts();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTemperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getTemperature()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Temperature();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getHumidity <em>Humidity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Humidity</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getHumidity()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Humidity();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindDirection <em>Wind Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Direction</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindDirection()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_WindDirection();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindAverage <em>Wind Average</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Average</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindAverage()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_WindAverage();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindGust <em>Wind Gust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wind Gust</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getWindGust()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_WindGust();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getUvIndex <em>Uv Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uv Index</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getUvIndex()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_UvIndex();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getIlluminance <em>Illuminance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Illuminance</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getIlluminance()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Illuminance();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainTotal <em>Rain Total</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rain Total</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainTotal()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_RainTotal();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainStart <em>Rain Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rain Start</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRainStart()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_RainStart();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSupercapVolts <em>Supercap Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supercap Volts</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSupercapVolts()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_SupercapVolts();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFirmware <em>Firmware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Firmware</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFirmware()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Firmware();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getFlags()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Flags();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getData()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getMic <em>Mic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mic</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getMic()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Mic();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModulation <em>Modulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modulation</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getModulation()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Modulation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRssi <em>Rssi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rssi</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getRssi()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Rssi();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSnr <em>Snr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snr</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getSnr()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Snr();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getNoise <em>Noise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Noise</em>'.
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Reading#getNoise()
	 * @see #getReading()
	 * @generated
	 */
	EAttribute getReading_Noise();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Rtl433Factory getRtl433Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl <em>Reading</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.provider.ecowitt.model.rtl433.impl.ReadingImpl
		 * @see org.gecko.weather.provider.ecowitt.model.rtl433.impl.Rtl433PackageImpl#getReading()
		 * @generated
		 */
		EClass READING = eINSTANCE.getReading();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__TIME = eINSTANCE.getReading_Time();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__MODEL = eINSTANCE.getReading_Model();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__ID = eINSTANCE.getReading_Id();

		/**
		 * The meta object literal for the '<em><b>Channel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__CHANNEL = eINSTANCE.getReading_Channel();

		/**
		 * The meta object literal for the '<em><b>Battery Ok</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__BATTERY_OK = eINSTANCE.getReading_BatteryOk();

		/**
		 * The meta object literal for the '<em><b>Battery Milli Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__BATTERY_MILLI_VOLTS = eINSTANCE.getReading_BatteryMilliVolts();

		/**
		 * The meta object literal for the '<em><b>Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__TEMPERATURE = eINSTANCE.getReading_Temperature();

		/**
		 * The meta object literal for the '<em><b>Humidity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__HUMIDITY = eINSTANCE.getReading_Humidity();

		/**
		 * The meta object literal for the '<em><b>Wind Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__WIND_DIRECTION = eINSTANCE.getReading_WindDirection();

		/**
		 * The meta object literal for the '<em><b>Wind Average</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__WIND_AVERAGE = eINSTANCE.getReading_WindAverage();

		/**
		 * The meta object literal for the '<em><b>Wind Gust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__WIND_GUST = eINSTANCE.getReading_WindGust();

		/**
		 * The meta object literal for the '<em><b>Uv Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__UV_INDEX = eINSTANCE.getReading_UvIndex();

		/**
		 * The meta object literal for the '<em><b>Illuminance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__ILLUMINANCE = eINSTANCE.getReading_Illuminance();

		/**
		 * The meta object literal for the '<em><b>Rain Total</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__RAIN_TOTAL = eINSTANCE.getReading_RainTotal();

		/**
		 * The meta object literal for the '<em><b>Rain Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__RAIN_START = eINSTANCE.getReading_RainStart();

		/**
		 * The meta object literal for the '<em><b>Supercap Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__SUPERCAP_VOLTS = eINSTANCE.getReading_SupercapVolts();

		/**
		 * The meta object literal for the '<em><b>Firmware</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__FIRMWARE = eINSTANCE.getReading_Firmware();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__FLAGS = eINSTANCE.getReading_Flags();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__DATA = eINSTANCE.getReading_Data();

		/**
		 * The meta object literal for the '<em><b>Mic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__MIC = eINSTANCE.getReading_Mic();

		/**
		 * The meta object literal for the '<em><b>Modulation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__MODULATION = eINSTANCE.getReading_Modulation();

		/**
		 * The meta object literal for the '<em><b>Rssi</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__RSSI = eINSTANCE.getReading_Rssi();

		/**
		 * The meta object literal for the '<em><b>Snr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__SNR = eINSTANCE.getReading_Snr();

		/**
		 * The meta object literal for the '<em><b>Noise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING__NOISE = eINSTANCE.getReading_Noise();

	}

} //Rtl433Package
