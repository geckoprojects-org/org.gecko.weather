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
package org.gecko.weather.pv.model.pv.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.weather.pv.model.pv.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PvFactoryImpl extends EFactoryImpl implements PvFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PvFactory init() {
		try {
			PvFactory thePvFactory = (PvFactory)EPackage.Registry.INSTANCE.getEFactory(PvPackage.eNS_URI);
			if (thePvFactory != null) {
				return thePvFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PvFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PvFactoryImpl() {
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
			case PvPackage.PLANT: return createPlant();
			case PvPackage.PV_ARRAY: return createPvArray();
			case PvPackage.INVERTER: return createInverter();
			case PvPackage.OBSTACLE: return createObstacle();
			case PvPackage.HORIZON_POINT: return createHorizonPoint();
			case PvPackage.PLANT_DIRECTORY: return createPlantDirectory();
			case PvPackage.PLANT_ENTRY: return createPlantEntry();
			case PvPackage.PV_OUTLOOK: return createPvOutlook();
			case PvPackage.PV_ARRAY_INFO: return createPvArrayInfo();
			case PvPackage.PV_HOUR: return createPvHour();
			case PvPackage.PV_DAY: return createPvDay();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PvPackage.MOUNTING:
				return createMountingFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PvPackage.MOUNTING:
				return convertMountingToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Plant createPlant() {
		PlantImpl plant = new PlantImpl();
		return plant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvArray createPvArray() {
		PvArrayImpl pvArray = new PvArrayImpl();
		return pvArray;
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
	public Obstacle createObstacle() {
		ObstacleImpl obstacle = new ObstacleImpl();
		return obstacle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HorizonPoint createHorizonPoint() {
		HorizonPointImpl horizonPoint = new HorizonPointImpl();
		return horizonPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PlantDirectory createPlantDirectory() {
		PlantDirectoryImpl plantDirectory = new PlantDirectoryImpl();
		return plantDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PlantEntry createPlantEntry() {
		PlantEntryImpl plantEntry = new PlantEntryImpl();
		return plantEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvOutlook createPvOutlook() {
		PvOutlookImpl pvOutlook = new PvOutlookImpl();
		return pvOutlook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvArrayInfo createPvArrayInfo() {
		PvArrayInfoImpl pvArrayInfo = new PvArrayInfoImpl();
		return pvArrayInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvHour createPvHour() {
		PvHourImpl pvHour = new PvHourImpl();
		return pvHour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvDay createPvDay() {
		PvDayImpl pvDay = new PvDayImpl();
		return pvDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mounting createMountingFromString(EDataType eDataType, String initialValue) {
		Mounting result = Mounting.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMountingToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvPackage getPvPackage() {
		return (PvPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PvPackage getPackage() {
		return PvPackage.eINSTANCE;
	}

} //PvFactoryImpl
