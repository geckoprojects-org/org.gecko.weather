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
package org.gecko.weather.pv.model.pv;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The hour from time to one hour later: mean power over it, which in kW equals its energy in kWh.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getTime <em>Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getPower <em>Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getDcPower <em>Dc Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getArrayPower <em>Array Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance <em>Plane Irradiance</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation <em>Global Radiation</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getCellTemperature <em>Cell Temperature</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getSunElevation <em>Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth <em>Sun Azimuth</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#isShaded <em>Shaded</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#isClipped <em>Clipped</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getMeasuredPower <em>Measured Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.PvHour#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour()
 * @model
 * @generated
 */
@ProviderType
public interface PvHour extends EObject {
	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean AC power over the hour in kW, after inverter losses and clipping = the hour's energy in kWh.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Power</em>' attribute.
	 * @see #setPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_Power()
	 * @model
	 * @generated
	 */
	double getPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getPower <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power</em>' attribute.
	 * @see #getPower()
	 * @generated
	 */
	void setPower(double value);

	/**
	 * Returns the value of the '<em><b>Dc Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean DC power before the inverter, kW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dc Power</em>' attribute.
	 * @see #isSetDcPower()
	 * @see #unsetDcPower()
	 * @see #setDcPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_DcPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDcPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getDcPower <em>Dc Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dc Power</em>' attribute.
	 * @see #isSetDcPower()
	 * @see #unsetDcPower()
	 * @see #getDcPower()
	 * @generated
	 */
	void setDcPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getDcPower <em>Dc Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDcPower()
	 * @see #getDcPower()
	 * @see #setDcPower(double)
	 * @generated
	 */
	void unsetDcPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getDcPower <em>Dc Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Dc Power</em>' attribute is set.
	 * @see #unsetDcPower()
	 * @see #getDcPower()
	 * @see #setDcPower(double)
	 * @generated
	 */
	boolean isSetDcPower();

	/**
	 * Returns the value of the '<em><b>Array Power</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean DC power per array, kW, in the order of PvOutlook.arrays.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Array Power</em>' attribute list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_ArrayPower()
	 * @model
	 * @generated
	 */
	EList<Double> getArrayPower();

	/**
	 * Returns the value of the '<em><b>Plane Irradiance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Irradiance on the module plane of the largest array, W/m² — what the modules see after orientation and shading.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Plane Irradiance</em>' attribute.
	 * @see #isSetPlaneIrradiance()
	 * @see #unsetPlaneIrradiance()
	 * @see #setPlaneIrradiance(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_PlaneIrradiance()
	 * @model unsettable="true"
	 * @generated
	 */
	double getPlaneIrradiance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance <em>Plane Irradiance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plane Irradiance</em>' attribute.
	 * @see #isSetPlaneIrradiance()
	 * @see #unsetPlaneIrradiance()
	 * @see #getPlaneIrradiance()
	 * @generated
	 */
	void setPlaneIrradiance(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance <em>Plane Irradiance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPlaneIrradiance()
	 * @see #getPlaneIrradiance()
	 * @see #setPlaneIrradiance(double)
	 * @generated
	 */
	void unsetPlaneIrradiance();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getPlaneIrradiance <em>Plane Irradiance</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Plane Irradiance</em>' attribute is set.
	 * @see #unsetPlaneIrradiance()
	 * @see #getPlaneIrradiance()
	 * @see #setPlaneIrradiance(double)
	 * @generated
	 */
	boolean isSetPlaneIrradiance();

	/**
	 * Returns the value of the '<em><b>Global Radiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The horizontal global radiation the hour was computed from, W/m².
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global Radiation</em>' attribute.
	 * @see #isSetGlobalRadiation()
	 * @see #unsetGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_GlobalRadiation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getGlobalRadiation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Radiation</em>' attribute.
	 * @see #isSetGlobalRadiation()
	 * @see #unsetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @generated
	 */
	void setGlobalRadiation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation <em>Global Radiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	void unsetGlobalRadiation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getGlobalRadiation <em>Global Radiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Global Radiation</em>' attribute is set.
	 * @see #unsetGlobalRadiation()
	 * @see #getGlobalRadiation()
	 * @see #setGlobalRadiation(double)
	 * @generated
	 */
	boolean isSetGlobalRadiation();

	/**
	 * Returns the value of the '<em><b>Cell Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cell temperature of the largest array, °C.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cell Temperature</em>' attribute.
	 * @see #isSetCellTemperature()
	 * @see #unsetCellTemperature()
	 * @see #setCellTemperature(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_CellTemperature()
	 * @model unsettable="true"
	 * @generated
	 */
	double getCellTemperature();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getCellTemperature <em>Cell Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell Temperature</em>' attribute.
	 * @see #isSetCellTemperature()
	 * @see #unsetCellTemperature()
	 * @see #getCellTemperature()
	 * @generated
	 */
	void setCellTemperature(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getCellTemperature <em>Cell Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCellTemperature()
	 * @see #getCellTemperature()
	 * @see #setCellTemperature(double)
	 * @generated
	 */
	void unsetCellTemperature();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getCellTemperature <em>Cell Temperature</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cell Temperature</em>' attribute is set.
	 * @see #unsetCellTemperature()
	 * @see #getCellTemperature()
	 * @see #setCellTemperature(double)
	 * @generated
	 */
	boolean isSetCellTemperature();

	/**
	 * Returns the value of the '<em><b>Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sun Elevation</em>' attribute.
	 * @see #isSetSunElevation()
	 * @see #unsetSunElevation()
	 * @see #setSunElevation(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_SunElevation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSunElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunElevation <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sun Elevation</em>' attribute.
	 * @see #isSetSunElevation()
	 * @see #unsetSunElevation()
	 * @see #getSunElevation()
	 * @generated
	 */
	void setSunElevation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunElevation <em>Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSunElevation()
	 * @see #getSunElevation()
	 * @see #setSunElevation(double)
	 * @generated
	 */
	void unsetSunElevation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunElevation <em>Sun Elevation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sun Elevation</em>' attribute is set.
	 * @see #unsetSunElevation()
	 * @see #getSunElevation()
	 * @see #setSunElevation(double)
	 * @generated
	 */
	boolean isSetSunElevation();

	/**
	 * Returns the value of the '<em><b>Sun Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sun Azimuth</em>' attribute.
	 * @see #isSetSunAzimuth()
	 * @see #unsetSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_SunAzimuth()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSunAzimuth();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sun Azimuth</em>' attribute.
	 * @see #isSetSunAzimuth()
	 * @see #unsetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @generated
	 */
	void setSunAzimuth(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth <em>Sun Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @generated
	 */
	void unsetSunAzimuth();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSunAzimuth <em>Sun Azimuth</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sun Azimuth</em>' attribute is set.
	 * @see #unsetSunAzimuth()
	 * @see #getSunAzimuth()
	 * @see #setSunAzimuth(double)
	 * @generated
	 */
	boolean isSetSunAzimuth();

	/**
	 * Returns the value of the '<em><b>Shaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The direct sun is behind the horizon or an obstacle for the largest array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shaded</em>' attribute.
	 * @see #setShaded(boolean)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_Shaded()
	 * @model
	 * @generated
	 */
	boolean isShaded();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#isShaded <em>Shaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shaded</em>' attribute.
	 * @see #isShaded()
	 * @generated
	 */
	void setShaded(boolean value);

	/**
	 * Returns the value of the '<em><b>Clipped</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An inverter limited the output.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Clipped</em>' attribute.
	 * @see #setClipped(boolean)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_Clipped()
	 * @model
	 * @generated
	 */
	boolean isClipped();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#isClipped <em>Clipped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clipped</em>' attribute.
	 * @see #isClipped()
	 * @generated
	 */
	void setClipped(boolean value);

	/**
	 * Returns the value of the '<em><b>Measured Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Measured mean PV generator power in kW over the hour (or the part of it measured so far), when the plant has a meter. On hybrid inverters this is the DC side — compare with dcPower.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Measured Power</em>' attribute.
	 * @see #isSetMeasuredPower()
	 * @see #unsetMeasuredPower()
	 * @see #setMeasuredPower(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_MeasuredPower()
	 * @model unsettable="true"
	 * @generated
	 */
	double getMeasuredPower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getMeasuredPower <em>Measured Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Measured Power</em>' attribute.
	 * @see #isSetMeasuredPower()
	 * @see #unsetMeasuredPower()
	 * @see #getMeasuredPower()
	 * @generated
	 */
	void setMeasuredPower(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getMeasuredPower <em>Measured Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMeasuredPower()
	 * @see #getMeasuredPower()
	 * @see #setMeasuredPower(double)
	 * @generated
	 */
	void unsetMeasuredPower();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getMeasuredPower <em>Measured Power</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Measured Power</em>' attribute is set.
	 * @see #unsetMeasuredPower()
	 * @see #getMeasuredPower()
	 * @see #setMeasuredPower(double)
	 * @generated
	 */
	boolean isSetMeasuredPower();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Where the radiation came from: ICON-D2 (direct and diffuse) or MOSMIX (global, split by the Erbs model).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPvHour_Source()
	 * @model
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.PvHour#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

} // PvHour
