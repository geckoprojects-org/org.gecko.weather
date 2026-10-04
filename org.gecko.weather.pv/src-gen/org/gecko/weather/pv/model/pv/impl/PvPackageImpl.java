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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.pv.model.pv.HorizonPoint;
import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.Meter;
import org.gecko.weather.pv.model.pv.Mounting;
import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PlantDirectory;
import org.gecko.weather.pv.model.pv.PlantEntry;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvArrayInfo;
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvMeasurement;
import org.gecko.weather.pv.model.pv.PvMeasurementLog;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PvPackageImpl extends EPackageImpl implements PvPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvArrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inverterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass obstacleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass horizonPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plantDirectoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plantEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvOutlookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvArrayInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvHourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvDayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pvMeasurementLogEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum mountingEEnum = null;

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
	 * @see org.gecko.weather.pv.model.pv.PvPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PvPackageImpl() {
		super(eNS_URI, PvFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PvPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PvPackage init() {
		if (isInited) return (PvPackage)EPackage.Registry.INSTANCE.getEPackage(PvPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPvPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PvPackageImpl thePvPackage = registeredPvPackage instanceof PvPackageImpl ? (PvPackageImpl)registeredPvPackage : new PvPackageImpl();

		isInited = true;

		// Create package meta-data objects
		thePvPackage.createPackageContents();

		// Initialize created meta-data
		thePvPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePvPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PvPackage.eNS_URI, thePvPackage);
		return thePvPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlant() {
		return plantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_Id() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_Name() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_SiteId() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_Latitude() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_Longitude() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_MountingHeight() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_Albedo() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlant_SystemLosses() {
		return (EAttribute)plantEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlant_Arrays() {
		return (EReference)plantEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlant_Inverters() {
		return (EReference)plantEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlant_Obstacles() {
		return (EReference)plantEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlant_Meter() {
		return (EReference)plantEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlant_Horizon() {
		return (EReference)plantEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvArray() {
		return pvArrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_Name() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_Azimuth() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_Tilt() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_PeakPower() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_ModuleCount() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_TemperatureCoefficient() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArray_Mounting() {
		return (EAttribute)pvArrayEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPvArray_Inverter() {
		return (EReference)pvArrayEClass.getEStructuralFeatures().get(7);
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
	public EAttribute getInverter_Name() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_AcPower() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInverter_Efficiency() {
		return (EAttribute)inverterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getObstacle() {
		return obstacleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_Name() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_AzimuthFrom() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_AzimuthTo() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_Distance() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_Height() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getObstacle_LeafOffTransmittance() {
		return (EAttribute)obstacleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHorizonPoint() {
		return horizonPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHorizonPoint_Azimuth() {
		return (EAttribute)horizonPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHorizonPoint_Elevation() {
		return (EAttribute)horizonPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlantDirectory() {
		return plantDirectoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlantDirectory_Plants() {
		return (EReference)plantDirectoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlantEntry() {
		return plantEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlantEntry_Id() {
		return (EAttribute)plantEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlantEntry_Name() {
		return (EAttribute)plantEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlantEntry_SiteId() {
		return (EAttribute)plantEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlantEntry_PeakPower() {
		return (EAttribute)plantEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlantEntry_TimeZone() {
		return (EAttribute)plantEntryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvOutlook() {
		return pvOutlookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_PlantId() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_PlantName() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_SiteId() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_TimeZone() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_PeakPower() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvOutlook_GeneratedAt() {
		return (EAttribute)pvOutlookEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPvOutlook_Hours() {
		return (EReference)pvOutlookEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPvOutlook_Days() {
		return (EReference)pvOutlookEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPvOutlook_Arrays() {
		return (EReference)pvOutlookEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvArrayInfo() {
		return pvArrayInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArrayInfo_Name() {
		return (EAttribute)pvArrayInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArrayInfo_Azimuth() {
		return (EAttribute)pvArrayInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArrayInfo_Tilt() {
		return (EAttribute)pvArrayInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvArrayInfo_PeakPower() {
		return (EAttribute)pvArrayInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvHour() {
		return pvHourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_Time() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_Power() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_DcPower() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_ArrayPower() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_PlaneIrradiance() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_GlobalRadiation() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_CellTemperature() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_SunElevation() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_SunAzimuth() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_Shaded() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_Clipped() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_MeasuredPower() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvHour_Source() {
		return (EAttribute)pvHourEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvDay() {
		return pvDayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_Date() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_Energy() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_PeakPower() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_PeakTime() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_SpecificYield() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_HoursCovered() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_MeasuredEnergy() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvDay_Source() {
		return (EAttribute)pvDayEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMeter() {
		return meterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeter_Type() {
		return (EAttribute)meterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeter_Url() {
		return (EAttribute)meterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeter_Interval() {
		return (EAttribute)meterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeter_Enabled() {
		return (EAttribute)meterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvMeasurement() {
		return pvMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_Time() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_PvPower() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_AcPower() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_LoadPower() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_GridPower() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_BatteryPower() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_StateOfCharge() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurement_EnergyTotal() {
		return (EAttribute)pvMeasurementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPvMeasurementLog() {
		return pvMeasurementLogEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurementLog_PlantId() {
		return (EAttribute)pvMeasurementLogEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurementLog_Date() {
		return (EAttribute)pvMeasurementLogEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPvMeasurementLog_MeterType() {
		return (EAttribute)pvMeasurementLogEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPvMeasurementLog_Measurements() {
		return (EReference)pvMeasurementLogEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getMounting() {
		return mountingEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PvFactory getPvFactory() {
		return (PvFactory)getEFactoryInstance();
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
		plantEClass = createEClass(PLANT);
		createEAttribute(plantEClass, PLANT__ID);
		createEAttribute(plantEClass, PLANT__NAME);
		createEAttribute(plantEClass, PLANT__SITE_ID);
		createEAttribute(plantEClass, PLANT__LATITUDE);
		createEAttribute(plantEClass, PLANT__LONGITUDE);
		createEAttribute(plantEClass, PLANT__MOUNTING_HEIGHT);
		createEAttribute(plantEClass, PLANT__ALBEDO);
		createEAttribute(plantEClass, PLANT__SYSTEM_LOSSES);
		createEReference(plantEClass, PLANT__ARRAYS);
		createEReference(plantEClass, PLANT__INVERTERS);
		createEReference(plantEClass, PLANT__OBSTACLES);
		createEReference(plantEClass, PLANT__METER);
		createEReference(plantEClass, PLANT__HORIZON);

		pvArrayEClass = createEClass(PV_ARRAY);
		createEAttribute(pvArrayEClass, PV_ARRAY__NAME);
		createEAttribute(pvArrayEClass, PV_ARRAY__AZIMUTH);
		createEAttribute(pvArrayEClass, PV_ARRAY__TILT);
		createEAttribute(pvArrayEClass, PV_ARRAY__PEAK_POWER);
		createEAttribute(pvArrayEClass, PV_ARRAY__MODULE_COUNT);
		createEAttribute(pvArrayEClass, PV_ARRAY__TEMPERATURE_COEFFICIENT);
		createEAttribute(pvArrayEClass, PV_ARRAY__MOUNTING);
		createEReference(pvArrayEClass, PV_ARRAY__INVERTER);

		inverterEClass = createEClass(INVERTER);
		createEAttribute(inverterEClass, INVERTER__NAME);
		createEAttribute(inverterEClass, INVERTER__AC_POWER);
		createEAttribute(inverterEClass, INVERTER__EFFICIENCY);

		obstacleEClass = createEClass(OBSTACLE);
		createEAttribute(obstacleEClass, OBSTACLE__NAME);
		createEAttribute(obstacleEClass, OBSTACLE__AZIMUTH_FROM);
		createEAttribute(obstacleEClass, OBSTACLE__AZIMUTH_TO);
		createEAttribute(obstacleEClass, OBSTACLE__DISTANCE);
		createEAttribute(obstacleEClass, OBSTACLE__HEIGHT);
		createEAttribute(obstacleEClass, OBSTACLE__LEAF_OFF_TRANSMITTANCE);

		horizonPointEClass = createEClass(HORIZON_POINT);
		createEAttribute(horizonPointEClass, HORIZON_POINT__AZIMUTH);
		createEAttribute(horizonPointEClass, HORIZON_POINT__ELEVATION);

		plantDirectoryEClass = createEClass(PLANT_DIRECTORY);
		createEReference(plantDirectoryEClass, PLANT_DIRECTORY__PLANTS);

		plantEntryEClass = createEClass(PLANT_ENTRY);
		createEAttribute(plantEntryEClass, PLANT_ENTRY__ID);
		createEAttribute(plantEntryEClass, PLANT_ENTRY__NAME);
		createEAttribute(plantEntryEClass, PLANT_ENTRY__SITE_ID);
		createEAttribute(plantEntryEClass, PLANT_ENTRY__PEAK_POWER);
		createEAttribute(plantEntryEClass, PLANT_ENTRY__TIME_ZONE);

		pvOutlookEClass = createEClass(PV_OUTLOOK);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__PLANT_ID);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__PLANT_NAME);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__SITE_ID);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__TIME_ZONE);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__PEAK_POWER);
		createEAttribute(pvOutlookEClass, PV_OUTLOOK__GENERATED_AT);
		createEReference(pvOutlookEClass, PV_OUTLOOK__HOURS);
		createEReference(pvOutlookEClass, PV_OUTLOOK__DAYS);
		createEReference(pvOutlookEClass, PV_OUTLOOK__ARRAYS);

		pvArrayInfoEClass = createEClass(PV_ARRAY_INFO);
		createEAttribute(pvArrayInfoEClass, PV_ARRAY_INFO__NAME);
		createEAttribute(pvArrayInfoEClass, PV_ARRAY_INFO__AZIMUTH);
		createEAttribute(pvArrayInfoEClass, PV_ARRAY_INFO__TILT);
		createEAttribute(pvArrayInfoEClass, PV_ARRAY_INFO__PEAK_POWER);

		pvHourEClass = createEClass(PV_HOUR);
		createEAttribute(pvHourEClass, PV_HOUR__TIME);
		createEAttribute(pvHourEClass, PV_HOUR__POWER);
		createEAttribute(pvHourEClass, PV_HOUR__DC_POWER);
		createEAttribute(pvHourEClass, PV_HOUR__ARRAY_POWER);
		createEAttribute(pvHourEClass, PV_HOUR__PLANE_IRRADIANCE);
		createEAttribute(pvHourEClass, PV_HOUR__GLOBAL_RADIATION);
		createEAttribute(pvHourEClass, PV_HOUR__CELL_TEMPERATURE);
		createEAttribute(pvHourEClass, PV_HOUR__SUN_ELEVATION);
		createEAttribute(pvHourEClass, PV_HOUR__SUN_AZIMUTH);
		createEAttribute(pvHourEClass, PV_HOUR__SHADED);
		createEAttribute(pvHourEClass, PV_HOUR__CLIPPED);
		createEAttribute(pvHourEClass, PV_HOUR__MEASURED_POWER);
		createEAttribute(pvHourEClass, PV_HOUR__SOURCE);

		pvDayEClass = createEClass(PV_DAY);
		createEAttribute(pvDayEClass, PV_DAY__DATE);
		createEAttribute(pvDayEClass, PV_DAY__ENERGY);
		createEAttribute(pvDayEClass, PV_DAY__PEAK_POWER);
		createEAttribute(pvDayEClass, PV_DAY__PEAK_TIME);
		createEAttribute(pvDayEClass, PV_DAY__SPECIFIC_YIELD);
		createEAttribute(pvDayEClass, PV_DAY__HOURS_COVERED);
		createEAttribute(pvDayEClass, PV_DAY__MEASURED_ENERGY);
		createEAttribute(pvDayEClass, PV_DAY__SOURCE);

		meterEClass = createEClass(METER);
		createEAttribute(meterEClass, METER__TYPE);
		createEAttribute(meterEClass, METER__URL);
		createEAttribute(meterEClass, METER__INTERVAL);
		createEAttribute(meterEClass, METER__ENABLED);

		pvMeasurementEClass = createEClass(PV_MEASUREMENT);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__TIME);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__PV_POWER);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__AC_POWER);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__LOAD_POWER);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__GRID_POWER);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__BATTERY_POWER);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__STATE_OF_CHARGE);
		createEAttribute(pvMeasurementEClass, PV_MEASUREMENT__ENERGY_TOTAL);

		pvMeasurementLogEClass = createEClass(PV_MEASUREMENT_LOG);
		createEAttribute(pvMeasurementLogEClass, PV_MEASUREMENT_LOG__PLANT_ID);
		createEAttribute(pvMeasurementLogEClass, PV_MEASUREMENT_LOG__DATE);
		createEAttribute(pvMeasurementLogEClass, PV_MEASUREMENT_LOG__METER_TYPE);
		createEReference(pvMeasurementLogEClass, PV_MEASUREMENT_LOG__MEASUREMENTS);

		// Create enums
		mountingEEnum = createEEnum(MOUNTING);
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
		initEClass(plantEClass, Plant.class, "Plant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlant_Id(), ecorePackage.getEString(), "id", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_Name(), ecorePackage.getEString(), "name", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_SiteId(), ecorePackage.getEString(), "siteId", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_Latitude(), ecorePackage.getEDouble(), "latitude", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_Longitude(), ecorePackage.getEDouble(), "longitude", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_MountingHeight(), ecorePackage.getEDouble(), "mountingHeight", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_Albedo(), ecorePackage.getEDouble(), "albedo", "0.2", 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlant_SystemLosses(), ecorePackage.getEDouble(), "systemLosses", "10.0", 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlant_Arrays(), this.getPvArray(), null, "arrays", null, 0, -1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlant_Inverters(), this.getInverter(), null, "inverters", null, 0, -1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlant_Obstacles(), this.getObstacle(), null, "obstacles", null, 0, -1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlant_Meter(), this.getMeter(), null, "meter", null, 0, 1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlant_Horizon(), this.getHorizonPoint(), null, "horizon", null, 0, -1, Plant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvArrayEClass, PvArray.class, "PvArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvArray_Name(), ecorePackage.getEString(), "name", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_Azimuth(), ecorePackage.getEDouble(), "azimuth", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_Tilt(), ecorePackage.getEDouble(), "tilt", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_PeakPower(), ecorePackage.getEDouble(), "peakPower", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_ModuleCount(), ecorePackage.getEInt(), "moduleCount", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_TemperatureCoefficient(), ecorePackage.getEDouble(), "temperatureCoefficient", "-0.37", 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArray_Mounting(), this.getMounting(), "mounting", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPvArray_Inverter(), this.getInverter(), null, "inverter", null, 0, 1, PvArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inverterEClass, Inverter.class, "Inverter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInverter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_AcPower(), ecorePackage.getEDouble(), "acPower", null, 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInverter_Efficiency(), ecorePackage.getEDouble(), "efficiency", "0.96", 0, 1, Inverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(obstacleEClass, Obstacle.class, "Obstacle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getObstacle_Name(), ecorePackage.getEString(), "name", null, 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObstacle_AzimuthFrom(), ecorePackage.getEDouble(), "azimuthFrom", null, 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObstacle_AzimuthTo(), ecorePackage.getEDouble(), "azimuthTo", null, 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObstacle_Distance(), ecorePackage.getEDouble(), "distance", null, 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObstacle_Height(), ecorePackage.getEDouble(), "height", null, 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObstacle_LeafOffTransmittance(), ecorePackage.getEDouble(), "leafOffTransmittance", "0.0", 0, 1, Obstacle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(horizonPointEClass, HorizonPoint.class, "HorizonPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHorizonPoint_Azimuth(), ecorePackage.getEDouble(), "azimuth", null, 0, 1, HorizonPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHorizonPoint_Elevation(), ecorePackage.getEDouble(), "elevation", null, 0, 1, HorizonPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(plantDirectoryEClass, PlantDirectory.class, "PlantDirectory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlantDirectory_Plants(), this.getPlantEntry(), null, "plants", null, 0, -1, PlantDirectory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(plantEntryEClass, PlantEntry.class, "PlantEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlantEntry_Id(), ecorePackage.getEString(), "id", null, 0, 1, PlantEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlantEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, PlantEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlantEntry_SiteId(), ecorePackage.getEString(), "siteId", null, 0, 1, PlantEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlantEntry_PeakPower(), ecorePackage.getEDouble(), "peakPower", null, 0, 1, PlantEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlantEntry_TimeZone(), ecorePackage.getEString(), "timeZone", null, 0, 1, PlantEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvOutlookEClass, PvOutlook.class, "PvOutlook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvOutlook_PlantId(), ecorePackage.getEString(), "plantId", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvOutlook_PlantName(), ecorePackage.getEString(), "plantName", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvOutlook_SiteId(), ecorePackage.getEString(), "siteId", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvOutlook_TimeZone(), ecorePackage.getEString(), "timeZone", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvOutlook_PeakPower(), ecorePackage.getEDouble(), "peakPower", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvOutlook_GeneratedAt(), ecorePackage.getEDate(), "generatedAt", null, 0, 1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPvOutlook_Hours(), this.getPvHour(), null, "hours", null, 0, -1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPvOutlook_Days(), this.getPvDay(), null, "days", null, 0, -1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPvOutlook_Arrays(), this.getPvArrayInfo(), null, "arrays", null, 0, -1, PvOutlook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvArrayInfoEClass, PvArrayInfo.class, "PvArrayInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvArrayInfo_Name(), ecorePackage.getEString(), "name", null, 0, 1, PvArrayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArrayInfo_Azimuth(), ecorePackage.getEDouble(), "azimuth", null, 0, 1, PvArrayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArrayInfo_Tilt(), ecorePackage.getEDouble(), "tilt", null, 0, 1, PvArrayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvArrayInfo_PeakPower(), ecorePackage.getEDouble(), "peakPower", null, 0, 1, PvArrayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvHourEClass, PvHour.class, "PvHour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvHour_Time(), ecorePackage.getEDate(), "time", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_Power(), ecorePackage.getEDouble(), "power", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_DcPower(), ecorePackage.getEDouble(), "dcPower", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_ArrayPower(), ecorePackage.getEDouble(), "arrayPower", null, 0, -1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_PlaneIrradiance(), ecorePackage.getEDouble(), "planeIrradiance", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_GlobalRadiation(), ecorePackage.getEDouble(), "globalRadiation", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_CellTemperature(), ecorePackage.getEDouble(), "cellTemperature", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_SunElevation(), ecorePackage.getEDouble(), "sunElevation", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_SunAzimuth(), ecorePackage.getEDouble(), "sunAzimuth", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_Shaded(), ecorePackage.getEBoolean(), "shaded", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_Clipped(), ecorePackage.getEBoolean(), "clipped", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_MeasuredPower(), ecorePackage.getEDouble(), "measuredPower", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvHour_Source(), ecorePackage.getEString(), "source", null, 0, 1, PvHour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvDayEClass, PvDay.class, "PvDay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvDay_Date(), ecorePackage.getEString(), "date", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_Energy(), ecorePackage.getEDouble(), "energy", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_PeakPower(), ecorePackage.getEDouble(), "peakPower", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_PeakTime(), ecorePackage.getEDate(), "peakTime", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_SpecificYield(), ecorePackage.getEDouble(), "specificYield", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_HoursCovered(), ecorePackage.getEInt(), "hoursCovered", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_MeasuredEnergy(), ecorePackage.getEDouble(), "measuredEnergy", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvDay_Source(), ecorePackage.getEString(), "source", null, 0, 1, PvDay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(meterEClass, Meter.class, "Meter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeter_Type(), ecorePackage.getEString(), "type", null, 0, 1, Meter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeter_Url(), ecorePackage.getEString(), "url", null, 0, 1, Meter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeter_Interval(), ecorePackage.getEInt(), "interval", "60", 0, 1, Meter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeter_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, Meter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvMeasurementEClass, PvMeasurement.class, "PvMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvMeasurement_Time(), ecorePackage.getEDate(), "time", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_PvPower(), ecorePackage.getEDouble(), "pvPower", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_AcPower(), ecorePackage.getEDouble(), "acPower", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_LoadPower(), ecorePackage.getEDouble(), "loadPower", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_GridPower(), ecorePackage.getEDouble(), "gridPower", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_BatteryPower(), ecorePackage.getEDouble(), "batteryPower", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_StateOfCharge(), ecorePackage.getEDouble(), "stateOfCharge", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurement_EnergyTotal(), ecorePackage.getEDouble(), "energyTotal", null, 0, 1, PvMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pvMeasurementLogEClass, PvMeasurementLog.class, "PvMeasurementLog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPvMeasurementLog_PlantId(), ecorePackage.getEString(), "plantId", null, 0, 1, PvMeasurementLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurementLog_Date(), ecorePackage.getEString(), "date", null, 0, 1, PvMeasurementLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPvMeasurementLog_MeterType(), ecorePackage.getEString(), "meterType", null, 0, 1, PvMeasurementLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPvMeasurementLog_Measurements(), this.getPvMeasurement(), null, "measurements", null, 0, -1, PvMeasurementLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(mountingEEnum, Mounting.class, "Mounting");
		addEEnumLiteral(mountingEEnum, Mounting.ROOF_MOUNTED);
		addEEnumLiteral(mountingEEnum, Mounting.ROOF_INTEGRATED);
		addEEnumLiteral(mountingEEnum, Mounting.OPEN_RACK);

		// Create resource
		createResource(eNS_URI);
	}

} //PvPackageImpl
