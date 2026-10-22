/**
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Factory;
import org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Rtl433PackageImpl extends EPackageImpl implements Rtl433Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readingEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Rtl433PackageImpl() {
		super(eNS_URI, Rtl433Factory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link Rtl433Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Rtl433Package init() {
		if (isInited) return (Rtl433Package)EPackage.Registry.INSTANCE.getEPackage(Rtl433Package.eNS_URI);

		// Obtain or create and register package
		Object registeredRtl433Package = EPackage.Registry.INSTANCE.get(eNS_URI);
		Rtl433PackageImpl theRtl433Package = registeredRtl433Package instanceof Rtl433PackageImpl ? (Rtl433PackageImpl)registeredRtl433Package : new Rtl433PackageImpl();

		isInited = true;

		// Create package meta-data objects
		theRtl433Package.createPackageContents();

		// Initialize created meta-data
		theRtl433Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRtl433Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Rtl433Package.eNS_URI, theRtl433Package);
		return theRtl433Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReading() {
		return readingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Time() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Model() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Id() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Channel() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_BatteryOk() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_BatteryMilliVolts() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Temperature() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Humidity() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_WindDirection() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_WindAverage() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_WindGust() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_UvIndex() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Illuminance() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_RainTotal() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_RainStart() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_SupercapVolts() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Firmware() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Flags() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Data() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Mic() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Modulation() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Rssi() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Snr() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReading_Noise() {
		return (EAttribute)readingEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Rtl433Factory getRtl433Factory() {
		return (Rtl433Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		readingEClass = createEClass(READING);
		createEAttribute(readingEClass, READING__TIME);
		createEAttribute(readingEClass, READING__MODEL);
		createEAttribute(readingEClass, READING__ID);
		createEAttribute(readingEClass, READING__CHANNEL);
		createEAttribute(readingEClass, READING__BATTERY_OK);
		createEAttribute(readingEClass, READING__BATTERY_MILLI_VOLTS);
		createEAttribute(readingEClass, READING__TEMPERATURE);
		createEAttribute(readingEClass, READING__HUMIDITY);
		createEAttribute(readingEClass, READING__WIND_DIRECTION);
		createEAttribute(readingEClass, READING__WIND_AVERAGE);
		createEAttribute(readingEClass, READING__WIND_GUST);
		createEAttribute(readingEClass, READING__UV_INDEX);
		createEAttribute(readingEClass, READING__ILLUMINANCE);
		createEAttribute(readingEClass, READING__RAIN_TOTAL);
		createEAttribute(readingEClass, READING__RAIN_START);
		createEAttribute(readingEClass, READING__SUPERCAP_VOLTS);
		createEAttribute(readingEClass, READING__FIRMWARE);
		createEAttribute(readingEClass, READING__FLAGS);
		createEAttribute(readingEClass, READING__DATA);
		createEAttribute(readingEClass, READING__MIC);
		createEAttribute(readingEClass, READING__MODULATION);
		createEAttribute(readingEClass, READING__RSSI);
		createEAttribute(readingEClass, READING__SNR);
		createEAttribute(readingEClass, READING__NOISE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(readingEClass, Reading.class, "Reading", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReading_Time(), ecorePackage.getEString(), "time", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Model(), ecorePackage.getEString(), "model", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Id(), ecorePackage.getELongObject(), "id", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Channel(), ecorePackage.getEIntegerObject(), "channel", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_BatteryOk(), ecorePackage.getEDoubleObject(), "batteryOk", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_BatteryMilliVolts(), ecorePackage.getEIntegerObject(), "batteryMilliVolts", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Temperature(), ecorePackage.getEDoubleObject(), "temperature", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Humidity(), ecorePackage.getEDoubleObject(), "humidity", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_WindDirection(), ecorePackage.getEDoubleObject(), "windDirection", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_WindAverage(), ecorePackage.getEDoubleObject(), "windAverage", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_WindGust(), ecorePackage.getEDoubleObject(), "windGust", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_UvIndex(), ecorePackage.getEDoubleObject(), "uvIndex", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Illuminance(), ecorePackage.getEDoubleObject(), "illuminance", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_RainTotal(), ecorePackage.getEDoubleObject(), "rainTotal", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_RainStart(), ecorePackage.getEIntegerObject(), "rainStart", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_SupercapVolts(), ecorePackage.getEDoubleObject(), "supercapVolts", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Firmware(), ecorePackage.getEIntegerObject(), "firmware", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Flags(), ecorePackage.getEIntegerObject(), "flags", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Data(), ecorePackage.getEString(), "data", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Mic(), ecorePackage.getEString(), "mic", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Modulation(), ecorePackage.getEString(), "modulation", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Rssi(), ecorePackage.getEDoubleObject(), "rssi", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Snr(), ecorePackage.getEDoubleObject(), "snr", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReading_Noise(), ecorePackage.getEDoubleObject(), "noise", null, 0, 1, Reading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://eclipse.org/fennec/codec
		createCodecAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://eclipse.org/fennec/codec</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCodecAnnotations() {
		String source = "http://eclipse.org/fennec/codec";
		addAnnotation
		  (getReading_Time(),
		   source,
		   new String[] {
			   "key", "time"
		   });
		addAnnotation
		  (getReading_Model(),
		   source,
		   new String[] {
			   "key", "model"
		   });
		addAnnotation
		  (getReading_Id(),
		   source,
		   new String[] {
			   "key", "id"
		   });
		addAnnotation
		  (getReading_Channel(),
		   source,
		   new String[] {
			   "key", "channel"
		   });
		addAnnotation
		  (getReading_BatteryOk(),
		   source,
		   new String[] {
			   "key", "battery_ok"
		   });
		addAnnotation
		  (getReading_BatteryMilliVolts(),
		   source,
		   new String[] {
			   "key", "battery_mV"
		   });
		addAnnotation
		  (getReading_Temperature(),
		   source,
		   new String[] {
			   "key", "temperature_C"
		   });
		addAnnotation
		  (getReading_Humidity(),
		   source,
		   new String[] {
			   "key", "humidity"
		   });
		addAnnotation
		  (getReading_WindDirection(),
		   source,
		   new String[] {
			   "key", "wind_dir_deg"
		   });
		addAnnotation
		  (getReading_WindAverage(),
		   source,
		   new String[] {
			   "key", "wind_avg_m_s"
		   });
		addAnnotation
		  (getReading_WindGust(),
		   source,
		   new String[] {
			   "key", "wind_max_m_s"
		   });
		addAnnotation
		  (getReading_UvIndex(),
		   source,
		   new String[] {
			   "key", "uvi"
		   });
		addAnnotation
		  (getReading_Illuminance(),
		   source,
		   new String[] {
			   "key", "light_lux"
		   });
		addAnnotation
		  (getReading_RainTotal(),
		   source,
		   new String[] {
			   "key", "rain_mm"
		   });
		addAnnotation
		  (getReading_RainStart(),
		   source,
		   new String[] {
			   "key", "rain_start"
		   });
		addAnnotation
		  (getReading_SupercapVolts(),
		   source,
		   new String[] {
			   "key", "supercap_V"
		   });
		addAnnotation
		  (getReading_Firmware(),
		   source,
		   new String[] {
			   "key", "firmware"
		   });
		addAnnotation
		  (getReading_Flags(),
		   source,
		   new String[] {
			   "key", "flags"
		   });
		addAnnotation
		  (getReading_Data(),
		   source,
		   new String[] {
			   "key", "data"
		   });
		addAnnotation
		  (getReading_Mic(),
		   source,
		   new String[] {
			   "key", "mic"
		   });
		addAnnotation
		  (getReading_Modulation(),
		   source,
		   new String[] {
			   "key", "mod"
		   });
		addAnnotation
		  (getReading_Rssi(),
		   source,
		   new String[] {
			   "key", "rssi"
		   });
		addAnnotation
		  (getReading_Snr(),
		   source,
		   new String[] {
			   "key", "snr"
		   });
		addAnnotation
		  (getReading_Noise(),
		   source,
		   new String[] {
			   "key", "noise"
		   });
	}

} //Rtl433PackageImpl
