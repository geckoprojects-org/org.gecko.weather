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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A PV plant: where it is, which arrays it has, what limits it and what shades it. Stored one XMI file per plant in the plants folder; a profile with a private address belongs there, not into a repository.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getMountingHeight <em>Mounting Height</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getAlbedo <em>Albedo</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getSystemLosses <em>System Losses</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getArrays <em>Arrays</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getInverters <em>Inverters</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getObstacles <em>Obstacles</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.Plant#getHorizon <em>Horizon</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant()
 * @model
 * @generated
 */
@ProviderType
public interface Plant extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The weather site whose report feeds the forecast — register a site at the plant first.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Site Id</em>' attribute.
	 * @see #setSiteId(String)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_SiteId()
	 * @model
	 * @generated
	 */
	String getSiteId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getSiteId <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Id</em>' attribute.
	 * @see #getSiteId()
	 * @generated
	 */
	void setSiteId(String value);

	/**
	 * Returns the value of the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Position for the sun; when unset the site's position is used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Latitude</em>' attribute.
	 * @see #isSetLatitude()
	 * @see #unsetLatitude()
	 * @see #setLatitude(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Latitude()
	 * @model unsettable="true"
	 * @generated
	 */
	double getLatitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLatitude <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latitude</em>' attribute.
	 * @see #isSetLatitude()
	 * @see #unsetLatitude()
	 * @see #getLatitude()
	 * @generated
	 */
	void setLatitude(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLatitude <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLatitude()
	 * @see #getLatitude()
	 * @see #setLatitude(double)
	 * @generated
	 */
	void unsetLatitude();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLatitude <em>Latitude</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Latitude</em>' attribute is set.
	 * @see #unsetLatitude()
	 * @see #getLatitude()
	 * @see #setLatitude(double)
	 * @generated
	 */
	boolean isSetLatitude();

	/**
	 * Returns the value of the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longitude</em>' attribute.
	 * @see #isSetLongitude()
	 * @see #unsetLongitude()
	 * @see #setLongitude(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Longitude()
	 * @model unsettable="true"
	 * @generated
	 */
	double getLongitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLongitude <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Longitude</em>' attribute.
	 * @see #isSetLongitude()
	 * @see #unsetLongitude()
	 * @see #getLongitude()
	 * @generated
	 */
	void setLongitude(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLongitude <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLongitude()
	 * @see #getLongitude()
	 * @see #setLongitude(double)
	 * @generated
	 */
	void unsetLongitude();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getLongitude <em>Longitude</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Longitude</em>' attribute is set.
	 * @see #unsetLongitude()
	 * @see #getLongitude()
	 * @see #setLongitude(double)
	 * @generated
	 */
	boolean isSetLongitude();

	/**
	 * Returns the value of the '<em><b>Mounting Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Height of the modules above ground in m, for the angle under which an obstacle appears.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mounting Height</em>' attribute.
	 * @see #isSetMountingHeight()
	 * @see #unsetMountingHeight()
	 * @see #setMountingHeight(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_MountingHeight()
	 * @model unsettable="true"
	 * @generated
	 */
	double getMountingHeight();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getMountingHeight <em>Mounting Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mounting Height</em>' attribute.
	 * @see #isSetMountingHeight()
	 * @see #unsetMountingHeight()
	 * @see #getMountingHeight()
	 * @generated
	 */
	void setMountingHeight(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getMountingHeight <em>Mounting Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMountingHeight()
	 * @see #getMountingHeight()
	 * @see #setMountingHeight(double)
	 * @generated
	 */
	void unsetMountingHeight();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getMountingHeight <em>Mounting Height</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Mounting Height</em>' attribute is set.
	 * @see #unsetMountingHeight()
	 * @see #getMountingHeight()
	 * @see #setMountingHeight(double)
	 * @generated
	 */
	boolean isSetMountingHeight();

	/**
	 * Returns the value of the '<em><b>Albedo</b></em>' attribute.
	 * The default value is <code>"0.2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ground reflectance, 0.2 for grass, up to 0.8 for fresh snow.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Albedo</em>' attribute.
	 * @see #setAlbedo(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Albedo()
	 * @model default="0.2"
	 * @generated
	 */
	double getAlbedo();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getAlbedo <em>Albedo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Albedo</em>' attribute.
	 * @see #getAlbedo()
	 * @generated
	 */
	void setAlbedo(double value);

	/**
	 * Returns the value of the '<em><b>System Losses</b></em>' attribute.
	 * The default value is <code>"10.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DC losses in % not modelled otherwise — wiring, soiling, mismatch, ageing. 10 is a usual start; calibrate against measured yields.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>System Losses</em>' attribute.
	 * @see #setSystemLosses(double)
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_SystemLosses()
	 * @model default="10.0"
	 * @generated
	 */
	double getSystemLosses();

	/**
	 * Sets the value of the '{@link org.gecko.weather.pv.model.pv.Plant#getSystemLosses <em>System Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Losses</em>' attribute.
	 * @see #getSystemLosses()
	 * @generated
	 */
	void setSystemLosses(double value);

	/**
	 * Returns the value of the '<em><b>Arrays</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PvArray}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arrays</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Arrays()
	 * @model containment="true"
	 * @generated
	 */
	EList<PvArray> getArrays();

	/**
	 * Returns the value of the '<em><b>Inverters</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.Inverter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inverters</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Inverters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Inverter> getInverters();

	/**
	 * Returns the value of the '<em><b>Obstacles</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.Obstacle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Obstacles</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Obstacles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Obstacle> getObstacles();

	/**
	 * Returns the value of the '<em><b>Horizon</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.HorizonPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A measured horizon, if one exists; combined with the obstacles, the higher angle wins.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Horizon</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlant_Horizon()
	 * @model containment="true"
	 * @generated
	 */
	EList<HorizonPoint> getHorizon();

} // Plant
