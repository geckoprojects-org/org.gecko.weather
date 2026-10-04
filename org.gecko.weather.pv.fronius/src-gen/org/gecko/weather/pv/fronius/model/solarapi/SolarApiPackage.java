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
package org.gecko.weather.pv.fronius.model.solarapi;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

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
 * The answers of the local Fronius Solar API v1 (Fronius document 42,0410,2012) as they come over the wire, read with the Fennec JSON codec. Member names are the API's, mapped through the codec key annotation. Only GetPowerFlowRealtimeData so far. Units and signs are Fronius': W, Wh; P_Load negative for consumption, P_Grid positive when drawing, P_Akku positive when discharging. A value the device reports as null is null.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.pv.fronius.model.solarapi.SolarApiFactory
 * @model kind="package"
 * @generated
 */
@ProviderType
@EPackage(uri = SolarApiPackage.eNS_URI, fingerprint = "fp1:fa4b156e221b858a19674feddf04cfa7da67a7d1928b4ef08de048bf16337b1d", genModel = "/model/solarapi.genmodel", genModelSourceLocations = {"model/solarapi.genmodel","org.gecko.weather.pv.fronius/model/solarapi.genmodel"}, ecore = "/model/solarapi.ecore", ecoreSourceLocations = "/model/solarapi.ecore")
public interface SolarApiPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "solarapi";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://geckoprojects.org/fronius/solarapi/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "solarapi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SolarApiPackage eINSTANCE = org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowResponseImpl <em>Power Flow Response</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowResponseImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowResponse()
	 * @generated
	 */
	int POWER_FLOW_RESPONSE = 0;

	/**
	 * The feature id for the '<em><b>Head</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_RESPONSE__HEAD = 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_RESPONSE__BODY = 1;

	/**
	 * The number of structural features of the '<em>Power Flow Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_RESPONSE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Power Flow Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_RESPONSE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.HeadImpl <em>Head</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.HeadImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getHead()
	 * @generated
	 */
	int HEAD = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEAD__STATUS = 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEAD__TIMESTAMP = 1;

	/**
	 * The number of structural features of the '<em>Head</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEAD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Head</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEAD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.StatusImpl <em>Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.StatusImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getStatus()
	 * @generated
	 */
	int STATUS = 2;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS__CODE = 0;

	/**
	 * The feature id for the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS__REASON = 1;

	/**
	 * The feature id for the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS__USER_MESSAGE = 2;

	/**
	 * The number of structural features of the '<em>Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowBodyImpl <em>Power Flow Body</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowBodyImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowBody()
	 * @generated
	 */
	int POWER_FLOW_BODY = 3;

	/**
	 * The feature id for the '<em><b>Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_BODY__DATA = 0;

	/**
	 * The number of structural features of the '<em>Power Flow Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_BODY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Power Flow Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_BODY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl <em>Power Flow Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowData()
	 * @generated
	 */
	int POWER_FLOW_DATA = 4;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_DATA__VERSION = 0;

	/**
	 * The feature id for the '<em><b>Site</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_DATA__SITE = 1;

	/**
	 * The feature id for the '<em><b>Inverters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_DATA__INVERTERS = 2;

	/**
	 * The number of structural features of the '<em>Power Flow Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_DATA_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Power Flow Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FLOW_DATA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl <em>Site</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getSite()
	 * @generated
	 */
	int SITE = 5;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__MODE = 0;

	/**
	 * The feature id for the '<em><b>Battery Standby</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__BATTERY_STANDBY = 1;

	/**
	 * The feature id for the '<em><b>Backup Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__BACKUP_MODE = 2;

	/**
	 * The feature id for the '<em><b>Power Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POWER_GRID = 3;

	/**
	 * The feature id for the '<em><b>Power Load</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POWER_LOAD = 4;

	/**
	 * The feature id for the '<em><b>Power Battery</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POWER_BATTERY = 5;

	/**
	 * The feature id for the '<em><b>Power Pv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POWER_PV = 6;

	/**
	 * The feature id for the '<em><b>Relative Self Consumption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__RELATIVE_SELF_CONSUMPTION = 7;

	/**
	 * The feature id for the '<em><b>Relative Autonomy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__RELATIVE_AUTONOMY = 8;

	/**
	 * The feature id for the '<em><b>Meter Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__METER_LOCATION = 9;

	/**
	 * The feature id for the '<em><b>Energy Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ENERGY_DAY = 10;

	/**
	 * The feature id for the '<em><b>Energy Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ENERGY_YEAR = 11;

	/**
	 * The feature id for the '<em><b>Energy Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ENERGY_TOTAL = 12;

	/**
	 * The number of structural features of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_FEATURE_COUNT = 13;

	/**
	 * The number of operations of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterEntryImpl <em>Inverter Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.InverterEntryImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getInverterEntry()
	 * @generated
	 */
	int INVERTER_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Inverter Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Inverter Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl <em>Inverter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl
	 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getInverter()
	 * @generated
	 */
	int INVERTER = 7;

	/**
	 * The feature id for the '<em><b>Device Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__DEVICE_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__POWER = 1;

	/**
	 * The feature id for the '<em><b>State Of Charge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__STATE_OF_CHARGE = 2;

	/**
	 * The feature id for the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__COMPONENT_ID = 3;

	/**
	 * The feature id for the '<em><b>Battery Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__BATTERY_MODE = 4;

	/**
	 * The feature id for the '<em><b>Energy Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__ENERGY_DAY = 5;

	/**
	 * The feature id for the '<em><b>Energy Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__ENERGY_YEAR = 6;

	/**
	 * The feature id for the '<em><b>Energy Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER__ENERGY_TOTAL = 7;

	/**
	 * The number of structural features of the '<em>Inverter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Inverter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVERTER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse <em>Power Flow Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Flow Response</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse
	 * @generated
	 */
	EClass getPowerFlowResponse();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Head</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getHead()
	 * @see #getPowerFlowResponse()
	 * @generated
	 */
	EReference getPowerFlowResponse_Head();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowResponse#getBody()
	 * @see #getPowerFlowResponse()
	 * @generated
	 */
	EReference getPowerFlowResponse_Body();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.Head <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Head</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Head
	 * @generated
	 */
	EClass getHead();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Status</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Head#getStatus()
	 * @see #getHead()
	 * @generated
	 */
	EReference getHead_Status();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Head#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Head#getTimestamp()
	 * @see #getHead()
	 * @generated
	 */
	EAttribute getHead_Timestamp();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.Status <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Status</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Status
	 * @generated
	 */
	EClass getStatus();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Status#getCode()
	 * @see #getStatus()
	 * @generated
	 */
	EAttribute getStatus_Code();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getReason <em>Reason</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reason</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Status#getReason()
	 * @see #getStatus()
	 * @generated
	 */
	EAttribute getStatus_Reason();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Status#getUserMessage <em>User Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Message</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Status#getUserMessage()
	 * @see #getStatus()
	 * @generated
	 */
	EAttribute getStatus_UserMessage();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowBody <em>Power Flow Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Flow Body</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowBody
	 * @generated
	 */
	EClass getPowerFlowBody();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowBody#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowBody#getData()
	 * @see #getPowerFlowBody()
	 * @generated
	 */
	EReference getPowerFlowBody_Data();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData <em>Power Flow Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Flow Data</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData
	 * @generated
	 */
	EClass getPowerFlowData();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getVersion()
	 * @see #getPowerFlowData()
	 * @generated
	 */
	EAttribute getPowerFlowData_Version();

	/**
	 * Returns the meta object for the containment reference '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getSite <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Site</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getSite()
	 * @see #getPowerFlowData()
	 * @generated
	 */
	EReference getPowerFlowData_Site();

	/**
	 * Returns the meta object for the map '{@link org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getInverters <em>Inverters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Inverters</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData#getInverters()
	 * @see #getPowerFlowData()
	 * @generated
	 */
	EReference getPowerFlowData_Inverters();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.Site <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site
	 * @generated
	 */
	EClass getSite();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getMode()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_Mode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBatteryStandby <em>Battery Standby</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Battery Standby</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getBatteryStandby()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_BatteryStandby();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getBackupMode <em>Backup Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Backup Mode</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getBackupMode()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_BackupMode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerGrid <em>Power Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Grid</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerGrid()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_PowerGrid();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerLoad <em>Power Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Load</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerLoad()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_PowerLoad();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerBattery <em>Power Battery</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Battery</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerBattery()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_PowerBattery();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerPv <em>Power Pv</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Pv</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getPowerPv()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_PowerPv();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeSelfConsumption <em>Relative Self Consumption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative Self Consumption</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeSelfConsumption()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_RelativeSelfConsumption();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeAutonomy <em>Relative Autonomy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative Autonomy</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getRelativeAutonomy()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_RelativeAutonomy();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getMeterLocation <em>Meter Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Meter Location</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getMeterLocation()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_MeterLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyDay <em>Energy Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Day</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyDay()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_EnergyDay();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyYear <em>Energy Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Year</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyYear()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_EnergyYear();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyTotal <em>Energy Total</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Total</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Site#getEnergyTotal()
	 * @see #getSite()
	 * @generated
	 */
	EAttribute getSite_EnergyTotal();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Inverter Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inverter Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.gecko.weather.pv.fronius.model.solarapi.Inverter" valueContainment="true"
	 * @generated
	 */
	EClass getInverterEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInverterEntry()
	 * @generated
	 */
	EAttribute getInverterEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInverterEntry()
	 * @generated
	 */
	EReference getInverterEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter <em>Inverter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inverter</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter
	 * @generated
	 */
	EClass getInverter();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getDeviceType <em>Device Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Type</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getDeviceType()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_DeviceType();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getPower <em>Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getPower()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_Power();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getStateOfCharge <em>State Of Charge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Of Charge</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getStateOfCharge()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_StateOfCharge();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getComponentId <em>Component Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Id</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getComponentId()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_ComponentId();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getBatteryMode <em>Battery Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Battery Mode</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getBatteryMode()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_BatteryMode();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyDay <em>Energy Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Day</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyDay()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_EnergyDay();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyYear <em>Energy Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Year</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyYear()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_EnergyYear();

	/**
	 * Returns the meta object for the attribute '{@link org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyTotal <em>Energy Total</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Energy Total</em>'.
	 * @see org.gecko.weather.pv.fronius.model.solarapi.Inverter#getEnergyTotal()
	 * @see #getInverter()
	 * @generated
	 */
	EAttribute getInverter_EnergyTotal();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SolarApiFactory getSolarApiFactory();

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
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowResponseImpl <em>Power Flow Response</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowResponseImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowResponse()
		 * @generated
		 */
		EClass POWER_FLOW_RESPONSE = eINSTANCE.getPowerFlowResponse();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_FLOW_RESPONSE__HEAD = eINSTANCE.getPowerFlowResponse_Head();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_FLOW_RESPONSE__BODY = eINSTANCE.getPowerFlowResponse_Body();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.HeadImpl <em>Head</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.HeadImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getHead()
		 * @generated
		 */
		EClass HEAD = eINSTANCE.getHead();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEAD__STATUS = eINSTANCE.getHead_Status();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEAD__TIMESTAMP = eINSTANCE.getHead_Timestamp();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.StatusImpl <em>Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.StatusImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getStatus()
		 * @generated
		 */
		EClass STATUS = eINSTANCE.getStatus();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS__CODE = eINSTANCE.getStatus_Code();

		/**
		 * The meta object literal for the '<em><b>Reason</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS__REASON = eINSTANCE.getStatus_Reason();

		/**
		 * The meta object literal for the '<em><b>User Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS__USER_MESSAGE = eINSTANCE.getStatus_UserMessage();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowBodyImpl <em>Power Flow Body</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowBodyImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowBody()
		 * @generated
		 */
		EClass POWER_FLOW_BODY = eINSTANCE.getPowerFlowBody();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_FLOW_BODY__DATA = eINSTANCE.getPowerFlowBody_Data();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl <em>Power Flow Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getPowerFlowData()
		 * @generated
		 */
		EClass POWER_FLOW_DATA = eINSTANCE.getPowerFlowData();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_FLOW_DATA__VERSION = eINSTANCE.getPowerFlowData_Version();

		/**
		 * The meta object literal for the '<em><b>Site</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_FLOW_DATA__SITE = eINSTANCE.getPowerFlowData_Site();

		/**
		 * The meta object literal for the '<em><b>Inverters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_FLOW_DATA__INVERTERS = eINSTANCE.getPowerFlowData_Inverters();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl <em>Site</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SiteImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getSite()
		 * @generated
		 */
		EClass SITE = eINSTANCE.getSite();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__MODE = eINSTANCE.getSite_Mode();

		/**
		 * The meta object literal for the '<em><b>Battery Standby</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__BATTERY_STANDBY = eINSTANCE.getSite_BatteryStandby();

		/**
		 * The meta object literal for the '<em><b>Backup Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__BACKUP_MODE = eINSTANCE.getSite_BackupMode();

		/**
		 * The meta object literal for the '<em><b>Power Grid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__POWER_GRID = eINSTANCE.getSite_PowerGrid();

		/**
		 * The meta object literal for the '<em><b>Power Load</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__POWER_LOAD = eINSTANCE.getSite_PowerLoad();

		/**
		 * The meta object literal for the '<em><b>Power Battery</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__POWER_BATTERY = eINSTANCE.getSite_PowerBattery();

		/**
		 * The meta object literal for the '<em><b>Power Pv</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__POWER_PV = eINSTANCE.getSite_PowerPv();

		/**
		 * The meta object literal for the '<em><b>Relative Self Consumption</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__RELATIVE_SELF_CONSUMPTION = eINSTANCE.getSite_RelativeSelfConsumption();

		/**
		 * The meta object literal for the '<em><b>Relative Autonomy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__RELATIVE_AUTONOMY = eINSTANCE.getSite_RelativeAutonomy();

		/**
		 * The meta object literal for the '<em><b>Meter Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__METER_LOCATION = eINSTANCE.getSite_MeterLocation();

		/**
		 * The meta object literal for the '<em><b>Energy Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__ENERGY_DAY = eINSTANCE.getSite_EnergyDay();

		/**
		 * The meta object literal for the '<em><b>Energy Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__ENERGY_YEAR = eINSTANCE.getSite_EnergyYear();

		/**
		 * The meta object literal for the '<em><b>Energy Total</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE__ENERGY_TOTAL = eINSTANCE.getSite_EnergyTotal();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterEntryImpl <em>Inverter Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.InverterEntryImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getInverterEntry()
		 * @generated
		 */
		EClass INVERTER_ENTRY = eINSTANCE.getInverterEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER_ENTRY__KEY = eINSTANCE.getInverterEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INVERTER_ENTRY__VALUE = eINSTANCE.getInverterEntry_Value();

		/**
		 * The meta object literal for the '{@link org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl <em>Inverter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.InverterImpl
		 * @see org.gecko.weather.pv.fronius.model.solarapi.impl.SolarApiPackageImpl#getInverter()
		 * @generated
		 */
		EClass INVERTER = eINSTANCE.getInverter();

		/**
		 * The meta object literal for the '<em><b>Device Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__DEVICE_TYPE = eINSTANCE.getInverter_DeviceType();

		/**
		 * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__POWER = eINSTANCE.getInverter_Power();

		/**
		 * The meta object literal for the '<em><b>State Of Charge</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__STATE_OF_CHARGE = eINSTANCE.getInverter_StateOfCharge();

		/**
		 * The meta object literal for the '<em><b>Component Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__COMPONENT_ID = eINSTANCE.getInverter_ComponentId();

		/**
		 * The meta object literal for the '<em><b>Battery Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__BATTERY_MODE = eINSTANCE.getInverter_BatteryMode();

		/**
		 * The meta object literal for the '<em><b>Energy Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__ENERGY_DAY = eINSTANCE.getInverter_EnergyDay();

		/**
		 * The meta object literal for the '<em><b>Energy Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__ENERGY_YEAR = eINSTANCE.getInverter_EnergyYear();

		/**
		 * The meta object literal for the '<em><b>Energy Total</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVERTER__ENERGY_TOTAL = eINSTANCE.getInverter_EnergyTotal();

	}

} //SolarApiPackage
