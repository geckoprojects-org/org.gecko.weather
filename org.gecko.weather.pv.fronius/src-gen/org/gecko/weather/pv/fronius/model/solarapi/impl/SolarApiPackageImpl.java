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
package org.gecko.weather.pv.fronius.model.solarapi.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.pv.fronius.model.solarapi.Head;
import org.gecko.weather.pv.fronius.model.solarapi.Inverter;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowBody;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse;
import org.gecko.weather.pv.fronius.model.solarapi.Site;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiFactory;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;
import org.gecko.weather.pv.fronius.model.solarapi.Status;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SolarApiPackageImpl extends EPackageImpl implements SolarApiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass powerFlowResponseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass headEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass powerFlowBodyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass powerFlowDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inverterEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inverterEClass = null;

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
	 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SolarApiPackageImpl() {
		super(eNS_URI, SolarApiFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SolarApiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SolarApiPackage init() {
		if (isInited) return (SolarApiPackage)EPackage.Registry.INSTANCE.getEPackage(SolarApiPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredSolarApiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		SolarApiPackageImpl theSolarApiPackage = registeredSolarApiPackage instanceof SolarApiPackageImpl ? (SolarApiPackageImpl)registeredSolarApiPackage : new SolarApiPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theSolarApiPackage.createPackageContents();

		// Initialize created meta-data
		theSolarApiPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSolarApiPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SolarApiPackage.eNS_URI, theSolarApiPackage);
		return theSolarApiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPowerFlowResponse() {
		return powerFlowResponseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPowerFlowResponse_Head() {
		return (EReference)powerFlowResponseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPowerFlowResponse_Body() {
		return (EReference)powerFlowResponseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHead() {
		return headEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHead_Status() {
		return (EReference)headEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHead_Timestamp() {
		return (EAttribute)headEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStatus() {
		return statusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatus_Code() {
		return (EAttribute)statusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatus_Reason() {
		return (EAttribute)statusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatus_UserMessage() {
		return (EAttribute)statusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPowerFlowBody() {
		return powerFlowBodyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPowerFlowBody_Data() {
		return (EReference)powerFlowBodyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPowerFlowData() {
		return powerFlowDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowerFlowData_Version() {
		return (EAttribute)powerFlowDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPowerFlowData_Site() {
		return (EReference)powerFlowDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPowerFlowData_Inverters() {
		return (EReference)powerFlowDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSite() {
		return siteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_Mode() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_BatteryStandby() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_BackupMode() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_PowerGrid() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_PowerLoad() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_PowerBattery() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_PowerPv() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_RelativeSelfConsumption() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_RelativeAutonomy() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_MeterLocation() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_EnergyDay() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_EnergyYear() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_EnergyTotal() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInverterEntry() {
		return inverterEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverterEntry_Key() {
		return (EAttribute)inverterEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInverterEntry_Value() {
		return (EReference)inverterEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInverter() {
		return inverterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_DeviceType() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_Power() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_StateOfCharge() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_ComponentId() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_BatteryMode() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_EnergyDay() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_EnergyYear() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_EnergyTotal() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SolarApiFactory getSolarApiFactory() {
		return (SolarApiFactory)getEFactoryInstance();
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
		powerFlowResponseEClass = createEClass(POWER_FLOW_RESPONSE);
		createEReference(powerFlowResponseEClass, POWER_FLOW_RESPONSE__HEAD);
		createEReference(powerFlowResponseEClass, POWER_FLOW_RESPONSE__BODY);

		headEClass = createEClass(HEAD);
		createEReference(headEClass, HEAD__STATUS);
		createEAttribute(headEClass, HEAD__TIMESTAMP);

		statusEClass = createEClass(STATUS);
		createEAttribute(statusEClass, STATUS__CODE);
		createEAttribute(statusEClass, STATUS__REASON);
		createEAttribute(statusEClass, STATUS__USER_MESSAGE);

		powerFlowBodyEClass = createEClass(POWER_FLOW_BODY);
		createEReference(powerFlowBodyEClass, POWER_FLOW_BODY__DATA);

		powerFlowDataEClass = createEClass(POWER_FLOW_DATA);
		createEAttribute(powerFlowDataEClass, POWER_FLOW_DATA__VERSION);
		createEReference(powerFlowDataEClass, POWER_FLOW_DATA__SITE);
		createEReference(powerFlowDataEClass, POWER_FLOW_DATA__INVERTERS);

		siteEClass = createEClass(SITE);
		createEAttribute(siteEClass, SITE__MODE);
		createEAttribute(siteEClass, SITE__BATTERY_STANDBY);
		createEAttribute(siteEClass, SITE__BACKUP_MODE);
		createEAttribute(siteEClass, SITE__POWER_GRID);
		createEAttribute(siteEClass, SITE__POWER_LOAD);
		createEAttribute(siteEClass, SITE__POWER_BATTERY);
		createEAttribute(siteEClass, SITE__POWER_PV);
		createEAttribute(siteEClass, SITE__RELATIVE_SELF_CONSUMPTION);
		createEAttribute(siteEClass, SITE__RELATIVE_AUTONOMY);
		createEAttribute(siteEClass, SITE__METER_LOCATION);
		createEAttribute(siteEClass, SITE__ENERGY_DAY);
		createEAttribute(siteEClass, SITE__ENERGY_YEAR);
		createEAttribute(siteEClass, SITE__ENERGY_TOTAL);

		inverterEntryEClass = createEClass(INVERTER_ENTRY);
		createEAttribute(inverterEntryEClass, INVERTER_ENTRY__KEY);
		createEReference(inverterEntryEClass, INVERTER_ENTRY__VALUE);

		inverterEClass = createEClass(INVERTER);
		createEAttribute(inverterEClass, INVERTER__DEVICE_TYPE);
		createEAttribute(inverterEClass, INVERTER__POWER);
		createEAttribute(inverterEClass, INVERTER__STATE_OF_CHARGE);
		createEAttribute(inverterEClass, INVERTER__COMPONENT_ID);
		createEAttribute(inverterEClass, INVERTER__BATTERY_MODE);
		createEAttribute(inverterEClass, INVERTER__ENERGY_DAY);
		createEAttribute(inverterEClass, INVERTER__ENERGY_YEAR);
		createEAttribute(inverterEClass, INVERTER__ENERGY_TOTAL);
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
		initEClass(powerFlowResponseEClass, PowerFlowResponse.class, "PowerFlowResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPowerFlowResponse_Head(), this.getHead(), null, "head", null, 0, 1, PowerFlowResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPowerFlowResponse_Body(), this.getPowerFlowBody(), null, "body", null, 0, 1, PowerFlowResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(headEClass, Head.class, "Head", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHead_Status(), this.getStatus(), null, "status", null, 0, 1, Head.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHead_Timestamp(), ecorePackage.getEString(), "timestamp", null, 0, 1, Head.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statusEClass, Status.class, "Status", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStatus_Code(), ecorePackage.getEInt(), "code", null, 0, 1, Status.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatus_Reason(), ecorePackage.getEString(), "reason", null, 0, 1, Status.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatus_UserMessage(), ecorePackage.getEString(), "userMessage", null, 0, 1, Status.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(powerFlowBodyEClass, PowerFlowBody.class, "PowerFlowBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPowerFlowBody_Data(), this.getPowerFlowData(), null, "data", null, 0, 1, PowerFlowBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(powerFlowDataEClass, PowerFlowData.class, "PowerFlowData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPowerFlowData_Version(), ecorePackage.getEString(), "version", null, 0, 1, PowerFlowData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPowerFlowData_Site(), this.getSite(), null, "site", null, 0, 1, PowerFlowData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPowerFlowData_Inverters(), this.getInverterEntry(), null, "inverters", null, 0, -1, PowerFlowData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(siteEClass, Site.class, "Site", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSite_Mode(), ecorePackage.getEString(), "mode", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_BatteryStandby(), ecorePackage.getEBooleanObject(), "batteryStandby", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_BackupMode(), ecorePackage.getEBooleanObject(), "backupMode", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_PowerGrid(), ecorePackage.getEDoubleObject(), "powerGrid", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_PowerLoad(), ecorePackage.getEDoubleObject(), "powerLoad", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_PowerBattery(), ecorePackage.getEDoubleObject(), "powerBattery", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_PowerPv(), ecorePackage.getEDoubleObject(), "powerPv", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_RelativeSelfConsumption(), ecorePackage.getEDoubleObject(), "relativeSelfConsumption", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_RelativeAutonomy(), ecorePackage.getEDoubleObject(), "relativeAutonomy", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_MeterLocation(), ecorePackage.getEString(), "meterLocation", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_EnergyDay(), ecorePackage.getEDoubleObject(), "energyDay", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_EnergyYear(), ecorePackage.getEDoubleObject(), "energyYear", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_EnergyTotal(), ecorePackage.getEDoubleObject(), "energyTotal", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inverterEntryEClass, Map.Entry.class, "InverterEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInverterEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInverterEntry_Value(), this.getInverter(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inverterEClass, Inverter.class, "Inverter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInverter_DeviceType(), ecorePackage.getEIntegerObject(), "deviceType", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_Power(), ecorePackage.getEDoubleObject(), "power", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_StateOfCharge(), ecorePackage.getEDoubleObject(), "stateOfCharge", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_ComponentId(), ecorePackage.getELongObject(), "componentId", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_BatteryMode(), ecorePackage.getEString(), "batteryMode", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_EnergyDay(), ecorePackage.getEDoubleObject(), "energyDay", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_EnergyYear(), ecorePackage.getEDoubleObject(), "energyYear", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_EnergyTotal(), ecorePackage.getEDoubleObject(), "energyTotal", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (getPowerFlowResponse_Head(),
		   source,
		   new String[] {
			   "key", "Head"
		   });
		addAnnotation
		  (getPowerFlowResponse_Body(),
		   source,
		   new String[] {
			   "key", "Body"
		   });
		addAnnotation
		  (getHead_Status(),
		   source,
		   new String[] {
			   "key", "Status"
		   });
		addAnnotation
		  (getHead_Timestamp(),
		   source,
		   new String[] {
			   "key", "Timestamp"
		   });
		addAnnotation
		  (getStatus_Code(),
		   source,
		   new String[] {
			   "key", "Code"
		   });
		addAnnotation
		  (getStatus_Reason(),
		   source,
		   new String[] {
			   "key", "Reason"
		   });
		addAnnotation
		  (getStatus_UserMessage(),
		   source,
		   new String[] {
			   "key", "UserMessage"
		   });
		addAnnotation
		  (getPowerFlowBody_Data(),
		   source,
		   new String[] {
			   "key", "Data"
		   });
		addAnnotation
		  (getPowerFlowData_Version(),
		   source,
		   new String[] {
			   "key", "Version"
		   });
		addAnnotation
		  (getPowerFlowData_Site(),
		   source,
		   new String[] {
			   "key", "Site"
		   });
		addAnnotation
		  (getPowerFlowData_Inverters(),
		   source,
		   new String[] {
			   "key", "Inverters"
		   });
		addAnnotation
		  (getSite_Mode(),
		   source,
		   new String[] {
			   "key", "Mode"
		   });
		addAnnotation
		  (getSite_BatteryStandby(),
		   source,
		   new String[] {
			   "key", "BatteryStandby"
		   });
		addAnnotation
		  (getSite_BackupMode(),
		   source,
		   new String[] {
			   "key", "BackupMode"
		   });
		addAnnotation
		  (getSite_PowerGrid(),
		   source,
		   new String[] {
			   "key", "P_Grid"
		   });
		addAnnotation
		  (getSite_PowerLoad(),
		   source,
		   new String[] {
			   "key", "P_Load"
		   });
		addAnnotation
		  (getSite_PowerBattery(),
		   source,
		   new String[] {
			   "key", "P_Akku"
		   });
		addAnnotation
		  (getSite_PowerPv(),
		   source,
		   new String[] {
			   "key", "P_PV"
		   });
		addAnnotation
		  (getSite_RelativeSelfConsumption(),
		   source,
		   new String[] {
			   "key", "rel_SelfConsumption"
		   });
		addAnnotation
		  (getSite_RelativeAutonomy(),
		   source,
		   new String[] {
			   "key", "rel_Autonomy"
		   });
		addAnnotation
		  (getSite_MeterLocation(),
		   source,
		   new String[] {
			   "key", "Meter_Location"
		   });
		addAnnotation
		  (getSite_EnergyDay(),
		   source,
		   new String[] {
			   "key", "E_Day"
		   });
		addAnnotation
		  (getSite_EnergyYear(),
		   source,
		   new String[] {
			   "key", "E_Year"
		   });
		addAnnotation
		  (getSite_EnergyTotal(),
		   source,
		   new String[] {
			   "key", "E_Total"
		   });
		addAnnotation
		  (getInverter_DeviceType(),
		   source,
		   new String[] {
			   "key", "DT"
		   });
		addAnnotation
		  (getInverter_Power(),
		   source,
		   new String[] {
			   "key", "P"
		   });
		addAnnotation
		  (getInverter_StateOfCharge(),
		   source,
		   new String[] {
			   "key", "SOC"
		   });
		addAnnotation
		  (getInverter_ComponentId(),
		   source,
		   new String[] {
			   "key", "CID"
		   });
		addAnnotation
		  (getInverter_BatteryMode(),
		   source,
		   new String[] {
			   "key", "Battery_Mode"
		   });
		addAnnotation
		  (getInverter_EnergyDay(),
		   source,
		   new String[] {
			   "key", "E_Day"
		   });
		addAnnotation
		  (getInverter_EnergyYear(),
		   source,
		   new String[] {
			   "key", "E_Year"
		   });
		addAnnotation
		  (getInverter_EnergyTotal(),
		   source,
		   new String[] {
			   "key", "E_Total"
		   });
	}

} //SolarApiPackageImpl
