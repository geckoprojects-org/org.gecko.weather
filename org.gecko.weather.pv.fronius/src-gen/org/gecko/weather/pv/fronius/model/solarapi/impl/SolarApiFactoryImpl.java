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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.weather.pv.fronius.model.solarapi.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SolarApiFactoryImpl extends EFactoryImpl implements SolarApiFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SolarApiFactory init() {
		try {
			SolarApiFactory theSolarApiFactory = (SolarApiFactory)EPackage.Registry.INSTANCE.getEFactory(SolarApiPackage.eNS_URI);
			if (theSolarApiFactory != null) {
				return theSolarApiFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SolarApiFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SolarApiFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SolarApiPackage.POWER_FLOW_RESPONSE: return createPowerFlowResponse();
			case SolarApiPackage.HEAD: return createHead();
			case SolarApiPackage.STATUS: return createStatus();
			case SolarApiPackage.POWER_FLOW_BODY: return createPowerFlowBody();
			case SolarApiPackage.POWER_FLOW_DATA: return createPowerFlowData();
			case SolarApiPackage.SITE: return createSite();
			case SolarApiPackage.INVERTER_ENTRY: return (EObject)createInverterEntry();
			case SolarApiPackage.INVERTER: return createInverter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PowerFlowResponse createPowerFlowResponse() {
		PowerFlowResponseImpl powerFlowResponse = new PowerFlowResponseImpl();
		return powerFlowResponse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Head createHead() {
		HeadImpl head = new HeadImpl();
		return head;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Status createStatus() {
		StatusImpl status = new StatusImpl();
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PowerFlowBody createPowerFlowBody() {
		PowerFlowBodyImpl powerFlowBody = new PowerFlowBodyImpl();
		return powerFlowBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PowerFlowData createPowerFlowData() {
		PowerFlowDataImpl powerFlowData = new PowerFlowDataImpl();
		return powerFlowData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Site createSite() {
		SiteImpl site = new SiteImpl();
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Inverter> createInverterEntry() {
		InverterEntryImpl inverterEntry = new InverterEntryImpl();
		return inverterEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Inverter createInverter() {
		InverterImpl inverter = new InverterImpl();
		return inverter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SolarApiPackage getSolarApiPackage() {
		return (SolarApiPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SolarApiPackage getPackage() {
		return SolarApiPackage.eINSTANCE;
	}

} //SolarApiFactoryImpl
