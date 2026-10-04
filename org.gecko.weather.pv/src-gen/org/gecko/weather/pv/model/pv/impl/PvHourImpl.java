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
package org.gecko.weather.pv.model.pv.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getPower <em>Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getDcPower <em>Dc Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getArrayPower <em>Array Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getPlaneIrradiance <em>Plane Irradiance</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getCellTemperature <em>Cell Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getSunElevation <em>Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getSunAzimuth <em>Sun Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#isShaded <em>Shaded</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#isClipped <em>Clipped</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getMeasuredPower <em>Measured Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvHourImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvHourImpl extends MinimalEObjectImpl.Container implements PvHour {
	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Date time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected static final double POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected double power = POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getDcPower() <em>Dc Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDcPower()
	 * @generated
	 * @ordered
	 */
	protected static final double DC_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDcPower() <em>Dc Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDcPower()
	 * @generated
	 * @ordered
	 */
	protected double dcPower = DC_POWER_EDEFAULT;

	/**
	 * This is true if the Dc Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dcPowerESet;

	/**
	 * The cached value of the '{@link #getArrayPower() <em>Array Power</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayPower()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> arrayPower;

	/**
	 * The default value of the '{@link #getPlaneIrradiance() <em>Plane Irradiance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaneIrradiance()
	 * @generated
	 * @ordered
	 */
	protected static final double PLANE_IRRADIANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPlaneIrradiance() <em>Plane Irradiance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaneIrradiance()
	 * @generated
	 * @ordered
	 */
	protected double planeIrradiance = PLANE_IRRADIANCE_EDEFAULT;

	/**
	 * This is true if the Plane Irradiance attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean planeIrradianceESet;

	/**
	 * The default value of the '{@link #getGlobalRadiation() <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalRadiation()
	 * @generated
	 * @ordered
	 */
	protected static final double GLOBAL_RADIATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGlobalRadiation() <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalRadiation()
	 * @generated
	 * @ordered
	 */
	protected double globalRadiation = GLOBAL_RADIATION_EDEFAULT;

	/**
	 * This is true if the Global Radiation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean globalRadiationESet;

	/**
	 * The default value of the '{@link #getCellTemperature() <em>Cell Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final double CELL_TEMPERATURE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCellTemperature() <em>Cell Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellTemperature()
	 * @generated
	 * @ordered
	 */
	protected double cellTemperature = CELL_TEMPERATURE_EDEFAULT;

	/**
	 * This is true if the Cell Temperature attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cellTemperatureESet;

	/**
	 * The default value of the '{@link #getSunElevation() <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunElevation()
	 * @generated
	 * @ordered
	 */
	protected static final double SUN_ELEVATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSunElevation() <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunElevation()
	 * @generated
	 * @ordered
	 */
	protected double sunElevation = SUN_ELEVATION_EDEFAULT;

	/**
	 * This is true if the Sun Elevation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sunElevationESet;

	/**
	 * The default value of the '{@link #getSunAzimuth() <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunAzimuth()
	 * @generated
	 * @ordered
	 */
	protected static final double SUN_AZIMUTH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSunAzimuth() <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunAzimuth()
	 * @generated
	 * @ordered
	 */
	protected double sunAzimuth = SUN_AZIMUTH_EDEFAULT;

	/**
	 * This is true if the Sun Azimuth attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sunAzimuthESet;

	/**
	 * The default value of the '{@link #isShaded() <em>Shaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShaded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHADED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isShaded() <em>Shaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShaded()
	 * @generated
	 * @ordered
	 */
	protected boolean shaded = SHADED_EDEFAULT;

	/**
	 * The default value of the '{@link #isClipped() <em>Clipped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClipped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLIPPED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClipped() <em>Clipped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClipped()
	 * @generated
	 * @ordered
	 */
	protected boolean clipped = CLIPPED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMeasuredPower() <em>Measured Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasuredPower()
	 * @generated
	 * @ordered
	 */
	protected static final double MEASURED_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMeasuredPower() <em>Measured Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasuredPower()
	 * @generated
	 * @ordered
	 */
	protected double measuredPower = MEASURED_POWER_EDEFAULT;

	/**
	 * This is true if the Measured Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean measuredPowerESet;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvHourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_HOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTime(Date newTime) {
		Date oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPower() {
		return power;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPower(double newPower) {
		double oldPower = power;
		power = newPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__POWER, oldPower, power));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getDcPower() {
		return dcPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDcPower(double newDcPower) {
		double oldDcPower = dcPower;
		dcPower = newDcPower;
		boolean oldDcPowerESet = dcPowerESet;
		dcPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__DC_POWER, oldDcPower, dcPower, !oldDcPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDcPower() {
		double oldDcPower = dcPower;
		boolean oldDcPowerESet = dcPowerESet;
		dcPower = DC_POWER_EDEFAULT;
		dcPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__DC_POWER, oldDcPower, DC_POWER_EDEFAULT, oldDcPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDcPower() {
		return dcPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Double> getArrayPower() {
		if (arrayPower == null) {
			arrayPower = new EDataTypeUniqueEList<Double>(Double.class, this, PvPackage.PV_HOUR__ARRAY_POWER);
		}
		return arrayPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPlaneIrradiance() {
		return planeIrradiance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlaneIrradiance(double newPlaneIrradiance) {
		double oldPlaneIrradiance = planeIrradiance;
		planeIrradiance = newPlaneIrradiance;
		boolean oldPlaneIrradianceESet = planeIrradianceESet;
		planeIrradianceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__PLANE_IRRADIANCE, oldPlaneIrradiance, planeIrradiance, !oldPlaneIrradianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPlaneIrradiance() {
		double oldPlaneIrradiance = planeIrradiance;
		boolean oldPlaneIrradianceESet = planeIrradianceESet;
		planeIrradiance = PLANE_IRRADIANCE_EDEFAULT;
		planeIrradianceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__PLANE_IRRADIANCE, oldPlaneIrradiance, PLANE_IRRADIANCE_EDEFAULT, oldPlaneIrradianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPlaneIrradiance() {
		return planeIrradianceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGlobalRadiation() {
		return globalRadiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGlobalRadiation(double newGlobalRadiation) {
		double oldGlobalRadiation = globalRadiation;
		globalRadiation = newGlobalRadiation;
		boolean oldGlobalRadiationESet = globalRadiationESet;
		globalRadiationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__GLOBAL_RADIATION, oldGlobalRadiation, globalRadiation, !oldGlobalRadiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGlobalRadiation() {
		double oldGlobalRadiation = globalRadiation;
		boolean oldGlobalRadiationESet = globalRadiationESet;
		globalRadiation = GLOBAL_RADIATION_EDEFAULT;
		globalRadiationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__GLOBAL_RADIATION, oldGlobalRadiation, GLOBAL_RADIATION_EDEFAULT, oldGlobalRadiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGlobalRadiation() {
		return globalRadiationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCellTemperature() {
		return cellTemperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCellTemperature(double newCellTemperature) {
		double oldCellTemperature = cellTemperature;
		cellTemperature = newCellTemperature;
		boolean oldCellTemperatureESet = cellTemperatureESet;
		cellTemperatureESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__CELL_TEMPERATURE, oldCellTemperature, cellTemperature, !oldCellTemperatureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCellTemperature() {
		double oldCellTemperature = cellTemperature;
		boolean oldCellTemperatureESet = cellTemperatureESet;
		cellTemperature = CELL_TEMPERATURE_EDEFAULT;
		cellTemperatureESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__CELL_TEMPERATURE, oldCellTemperature, CELL_TEMPERATURE_EDEFAULT, oldCellTemperatureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCellTemperature() {
		return cellTemperatureESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSunElevation() {
		return sunElevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunElevation(double newSunElevation) {
		double oldSunElevation = sunElevation;
		sunElevation = newSunElevation;
		boolean oldSunElevationESet = sunElevationESet;
		sunElevationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__SUN_ELEVATION, oldSunElevation, sunElevation, !oldSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSunElevation() {
		double oldSunElevation = sunElevation;
		boolean oldSunElevationESet = sunElevationESet;
		sunElevation = SUN_ELEVATION_EDEFAULT;
		sunElevationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__SUN_ELEVATION, oldSunElevation, SUN_ELEVATION_EDEFAULT, oldSunElevationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSunElevation() {
		return sunElevationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSunAzimuth() {
		return sunAzimuth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunAzimuth(double newSunAzimuth) {
		double oldSunAzimuth = sunAzimuth;
		sunAzimuth = newSunAzimuth;
		boolean oldSunAzimuthESet = sunAzimuthESet;
		sunAzimuthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__SUN_AZIMUTH, oldSunAzimuth, sunAzimuth, !oldSunAzimuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSunAzimuth() {
		double oldSunAzimuth = sunAzimuth;
		boolean oldSunAzimuthESet = sunAzimuthESet;
		sunAzimuth = SUN_AZIMUTH_EDEFAULT;
		sunAzimuthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__SUN_AZIMUTH, oldSunAzimuth, SUN_AZIMUTH_EDEFAULT, oldSunAzimuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSunAzimuth() {
		return sunAzimuthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isShaded() {
		return shaded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShaded(boolean newShaded) {
		boolean oldShaded = shaded;
		shaded = newShaded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__SHADED, oldShaded, shaded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isClipped() {
		return clipped;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClipped(boolean newClipped) {
		boolean oldClipped = clipped;
		clipped = newClipped;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__CLIPPED, oldClipped, clipped));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMeasuredPower() {
		return measuredPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMeasuredPower(double newMeasuredPower) {
		double oldMeasuredPower = measuredPower;
		measuredPower = newMeasuredPower;
		boolean oldMeasuredPowerESet = measuredPowerESet;
		measuredPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__MEASURED_POWER, oldMeasuredPower, measuredPower, !oldMeasuredPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMeasuredPower() {
		double oldMeasuredPower = measuredPower;
		boolean oldMeasuredPowerESet = measuredPowerESet;
		measuredPower = MEASURED_POWER_EDEFAULT;
		measuredPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_HOUR__MEASURED_POWER, oldMeasuredPower, MEASURED_POWER_EDEFAULT, oldMeasuredPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMeasuredPower() {
		return measuredPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_HOUR__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PV_HOUR__TIME:
				return getTime();
			case PvPackage.PV_HOUR__POWER:
				return getPower();
			case PvPackage.PV_HOUR__DC_POWER:
				return getDcPower();
			case PvPackage.PV_HOUR__ARRAY_POWER:
				return getArrayPower();
			case PvPackage.PV_HOUR__PLANE_IRRADIANCE:
				return getPlaneIrradiance();
			case PvPackage.PV_HOUR__GLOBAL_RADIATION:
				return getGlobalRadiation();
			case PvPackage.PV_HOUR__CELL_TEMPERATURE:
				return getCellTemperature();
			case PvPackage.PV_HOUR__SUN_ELEVATION:
				return getSunElevation();
			case PvPackage.PV_HOUR__SUN_AZIMUTH:
				return getSunAzimuth();
			case PvPackage.PV_HOUR__SHADED:
				return isShaded();
			case PvPackage.PV_HOUR__CLIPPED:
				return isClipped();
			case PvPackage.PV_HOUR__MEASURED_POWER:
				return getMeasuredPower();
			case PvPackage.PV_HOUR__SOURCE:
				return getSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PvPackage.PV_HOUR__TIME:
				setTime((Date)newValue);
				return;
			case PvPackage.PV_HOUR__POWER:
				setPower((Double)newValue);
				return;
			case PvPackage.PV_HOUR__DC_POWER:
				setDcPower((Double)newValue);
				return;
			case PvPackage.PV_HOUR__ARRAY_POWER:
				getArrayPower().clear();
				getArrayPower().addAll((Collection<? extends Double>)newValue);
				return;
			case PvPackage.PV_HOUR__PLANE_IRRADIANCE:
				setPlaneIrradiance((Double)newValue);
				return;
			case PvPackage.PV_HOUR__GLOBAL_RADIATION:
				setGlobalRadiation((Double)newValue);
				return;
			case PvPackage.PV_HOUR__CELL_TEMPERATURE:
				setCellTemperature((Double)newValue);
				return;
			case PvPackage.PV_HOUR__SUN_ELEVATION:
				setSunElevation((Double)newValue);
				return;
			case PvPackage.PV_HOUR__SUN_AZIMUTH:
				setSunAzimuth((Double)newValue);
				return;
			case PvPackage.PV_HOUR__SHADED:
				setShaded((Boolean)newValue);
				return;
			case PvPackage.PV_HOUR__CLIPPED:
				setClipped((Boolean)newValue);
				return;
			case PvPackage.PV_HOUR__MEASURED_POWER:
				setMeasuredPower((Double)newValue);
				return;
			case PvPackage.PV_HOUR__SOURCE:
				setSource((String)newValue);
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
			case PvPackage.PV_HOUR__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case PvPackage.PV_HOUR__POWER:
				setPower(POWER_EDEFAULT);
				return;
			case PvPackage.PV_HOUR__DC_POWER:
				unsetDcPower();
				return;
			case PvPackage.PV_HOUR__ARRAY_POWER:
				getArrayPower().clear();
				return;
			case PvPackage.PV_HOUR__PLANE_IRRADIANCE:
				unsetPlaneIrradiance();
				return;
			case PvPackage.PV_HOUR__GLOBAL_RADIATION:
				unsetGlobalRadiation();
				return;
			case PvPackage.PV_HOUR__CELL_TEMPERATURE:
				unsetCellTemperature();
				return;
			case PvPackage.PV_HOUR__SUN_ELEVATION:
				unsetSunElevation();
				return;
			case PvPackage.PV_HOUR__SUN_AZIMUTH:
				unsetSunAzimuth();
				return;
			case PvPackage.PV_HOUR__SHADED:
				setShaded(SHADED_EDEFAULT);
				return;
			case PvPackage.PV_HOUR__CLIPPED:
				setClipped(CLIPPED_EDEFAULT);
				return;
			case PvPackage.PV_HOUR__MEASURED_POWER:
				unsetMeasuredPower();
				return;
			case PvPackage.PV_HOUR__SOURCE:
				setSource(SOURCE_EDEFAULT);
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
			case PvPackage.PV_HOUR__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case PvPackage.PV_HOUR__POWER:
				return power != POWER_EDEFAULT;
			case PvPackage.PV_HOUR__DC_POWER:
				return isSetDcPower();
			case PvPackage.PV_HOUR__ARRAY_POWER:
				return arrayPower != null && !arrayPower.isEmpty();
			case PvPackage.PV_HOUR__PLANE_IRRADIANCE:
				return isSetPlaneIrradiance();
			case PvPackage.PV_HOUR__GLOBAL_RADIATION:
				return isSetGlobalRadiation();
			case PvPackage.PV_HOUR__CELL_TEMPERATURE:
				return isSetCellTemperature();
			case PvPackage.PV_HOUR__SUN_ELEVATION:
				return isSetSunElevation();
			case PvPackage.PV_HOUR__SUN_AZIMUTH:
				return isSetSunAzimuth();
			case PvPackage.PV_HOUR__SHADED:
				return shaded != SHADED_EDEFAULT;
			case PvPackage.PV_HOUR__CLIPPED:
				return clipped != CLIPPED_EDEFAULT;
			case PvPackage.PV_HOUR__MEASURED_POWER:
				return isSetMeasuredPower();
			case PvPackage.PV_HOUR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
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
		result.append(", power: ");
		result.append(power);
		result.append(", dcPower: ");
		if (dcPowerESet) result.append(dcPower); else result.append("<unset>");
		result.append(", arrayPower: ");
		result.append(arrayPower);
		result.append(", planeIrradiance: ");
		if (planeIrradianceESet) result.append(planeIrradiance); else result.append("<unset>");
		result.append(", globalRadiation: ");
		if (globalRadiationESet) result.append(globalRadiation); else result.append("<unset>");
		result.append(", cellTemperature: ");
		if (cellTemperatureESet) result.append(cellTemperature); else result.append("<unset>");
		result.append(", sunElevation: ");
		if (sunElevationESet) result.append(sunElevation); else result.append("<unset>");
		result.append(", sunAzimuth: ");
		if (sunAzimuthESet) result.append(sunAzimuth); else result.append("<unset>");
		result.append(", shaded: ");
		result.append(shaded);
		result.append(", clipped: ");
		result.append(clipped);
		result.append(", measuredPower: ");
		if (measuredPowerESet) result.append(measuredPower); else result.append("<unset>");
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}

} //PvHourImpl
