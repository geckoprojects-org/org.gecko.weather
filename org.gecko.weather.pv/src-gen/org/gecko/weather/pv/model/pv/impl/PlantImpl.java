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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gecko.weather.pv.model.pv.HorizonPoint;
import org.gecko.weather.pv.model.pv.Inverter;
import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvArray;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getMountingHeight <em>Mounting Height</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getAlbedo <em>Albedo</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getSystemLosses <em>System Losses</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getArrays <em>Arrays</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getInverters <em>Inverters</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getObstacles <em>Obstacles</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantImpl#getHorizon <em>Horizon</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlantImpl extends MinimalEObjectImpl.Container implements Plant {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSiteId() <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSiteId()
	 * @generated
	 * @ordered
	 */
	protected static final String SITE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSiteId() <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSiteId()
	 * @generated
	 * @ordered
	 */
	protected String siteId = SITE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected static final double LATITUDE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected double latitude = LATITUDE_EDEFAULT;

	/**
	 * This is true if the Latitude attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean latitudeESet;

	/**
	 * The default value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected static final double LONGITUDE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected double longitude = LONGITUDE_EDEFAULT;

	/**
	 * This is true if the Longitude attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean longitudeESet;

	/**
	 * The default value of the '{@link #getMountingHeight() <em>Mounting Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMountingHeight()
	 * @generated
	 * @ordered
	 */
	protected static final double MOUNTING_HEIGHT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMountingHeight() <em>Mounting Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMountingHeight()
	 * @generated
	 * @ordered
	 */
	protected double mountingHeight = MOUNTING_HEIGHT_EDEFAULT;

	/**
	 * This is true if the Mounting Height attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean mountingHeightESet;

	/**
	 * The default value of the '{@link #getAlbedo() <em>Albedo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlbedo()
	 * @generated
	 * @ordered
	 */
	protected static final double ALBEDO_EDEFAULT = 0.2;

	/**
	 * The cached value of the '{@link #getAlbedo() <em>Albedo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlbedo()
	 * @generated
	 * @ordered
	 */
	protected double albedo = ALBEDO_EDEFAULT;

	/**
	 * The default value of the '{@link #getSystemLosses() <em>System Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemLosses()
	 * @generated
	 * @ordered
	 */
	protected static final double SYSTEM_LOSSES_EDEFAULT = 10.0;

	/**
	 * The cached value of the '{@link #getSystemLosses() <em>System Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemLosses()
	 * @generated
	 * @ordered
	 */
	protected double systemLosses = SYSTEM_LOSSES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArrays() <em>Arrays</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrays()
	 * @generated
	 * @ordered
	 */
	protected EList<PvArray> arrays;

	/**
	 * The cached value of the '{@link #getInverters() <em>Inverters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverters()
	 * @generated
	 * @ordered
	 */
	protected EList<Inverter> inverters;

	/**
	 * The cached value of the '{@link #getObstacles() <em>Obstacles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObstacles()
	 * @generated
	 * @ordered
	 */
	protected EList<Obstacle> obstacles;

	/**
	 * The cached value of the '{@link #getHorizon() <em>Horizon</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizon()
	 * @generated
	 * @ordered
	 */
	protected EList<HorizonPoint> horizon;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PLANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSiteId() {
		return siteId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSiteId(String newSiteId) {
		String oldSiteId = siteId;
		siteId = newSiteId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__SITE_ID, oldSiteId, siteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLatitude(double newLatitude) {
		double oldLatitude = latitude;
		latitude = newLatitude;
		boolean oldLatitudeESet = latitudeESet;
		latitudeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__LATITUDE, oldLatitude, latitude, !oldLatitudeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLatitude() {
		double oldLatitude = latitude;
		boolean oldLatitudeESet = latitudeESet;
		latitude = LATITUDE_EDEFAULT;
		latitudeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PLANT__LATITUDE, oldLatitude, LATITUDE_EDEFAULT, oldLatitudeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLatitude() {
		return latitudeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLongitude(double newLongitude) {
		double oldLongitude = longitude;
		longitude = newLongitude;
		boolean oldLongitudeESet = longitudeESet;
		longitudeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__LONGITUDE, oldLongitude, longitude, !oldLongitudeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLongitude() {
		double oldLongitude = longitude;
		boolean oldLongitudeESet = longitudeESet;
		longitude = LONGITUDE_EDEFAULT;
		longitudeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PLANT__LONGITUDE, oldLongitude, LONGITUDE_EDEFAULT, oldLongitudeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLongitude() {
		return longitudeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMountingHeight() {
		return mountingHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMountingHeight(double newMountingHeight) {
		double oldMountingHeight = mountingHeight;
		mountingHeight = newMountingHeight;
		boolean oldMountingHeightESet = mountingHeightESet;
		mountingHeightESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__MOUNTING_HEIGHT, oldMountingHeight, mountingHeight, !oldMountingHeightESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMountingHeight() {
		double oldMountingHeight = mountingHeight;
		boolean oldMountingHeightESet = mountingHeightESet;
		mountingHeight = MOUNTING_HEIGHT_EDEFAULT;
		mountingHeightESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PLANT__MOUNTING_HEIGHT, oldMountingHeight, MOUNTING_HEIGHT_EDEFAULT, oldMountingHeightESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMountingHeight() {
		return mountingHeightESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAlbedo() {
		return albedo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlbedo(double newAlbedo) {
		double oldAlbedo = albedo;
		albedo = newAlbedo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__ALBEDO, oldAlbedo, albedo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSystemLosses() {
		return systemLosses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSystemLosses(double newSystemLosses) {
		double oldSystemLosses = systemLosses;
		systemLosses = newSystemLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT__SYSTEM_LOSSES, oldSystemLosses, systemLosses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PvArray> getArrays() {
		if (arrays == null) {
			arrays = new EObjectContainmentEList<PvArray>(PvArray.class, this, PvPackage.PLANT__ARRAYS);
		}
		return arrays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Inverter> getInverters() {
		if (inverters == null) {
			inverters = new EObjectContainmentEList<Inverter>(Inverter.class, this, PvPackage.PLANT__INVERTERS);
		}
		return inverters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Obstacle> getObstacles() {
		if (obstacles == null) {
			obstacles = new EObjectContainmentEList<Obstacle>(Obstacle.class, this, PvPackage.PLANT__OBSTACLES);
		}
		return obstacles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<HorizonPoint> getHorizon() {
		if (horizon == null) {
			horizon = new EObjectContainmentEList<HorizonPoint>(HorizonPoint.class, this, PvPackage.PLANT__HORIZON);
		}
		return horizon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PvPackage.PLANT__ARRAYS:
				return ((InternalEList<?>)getArrays()).basicRemove(otherEnd, msgs);
			case PvPackage.PLANT__INVERTERS:
				return ((InternalEList<?>)getInverters()).basicRemove(otherEnd, msgs);
			case PvPackage.PLANT__OBSTACLES:
				return ((InternalEList<?>)getObstacles()).basicRemove(otherEnd, msgs);
			case PvPackage.PLANT__HORIZON:
				return ((InternalEList<?>)getHorizon()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PLANT__ID:
				return getId();
			case PvPackage.PLANT__NAME:
				return getName();
			case PvPackage.PLANT__SITE_ID:
				return getSiteId();
			case PvPackage.PLANT__LATITUDE:
				return getLatitude();
			case PvPackage.PLANT__LONGITUDE:
				return getLongitude();
			case PvPackage.PLANT__MOUNTING_HEIGHT:
				return getMountingHeight();
			case PvPackage.PLANT__ALBEDO:
				return getAlbedo();
			case PvPackage.PLANT__SYSTEM_LOSSES:
				return getSystemLosses();
			case PvPackage.PLANT__ARRAYS:
				return getArrays();
			case PvPackage.PLANT__INVERTERS:
				return getInverters();
			case PvPackage.PLANT__OBSTACLES:
				return getObstacles();
			case PvPackage.PLANT__HORIZON:
				return getHorizon();
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
			case PvPackage.PLANT__ID:
				setId((String)newValue);
				return;
			case PvPackage.PLANT__NAME:
				setName((String)newValue);
				return;
			case PvPackage.PLANT__SITE_ID:
				setSiteId((String)newValue);
				return;
			case PvPackage.PLANT__LATITUDE:
				setLatitude((Double)newValue);
				return;
			case PvPackage.PLANT__LONGITUDE:
				setLongitude((Double)newValue);
				return;
			case PvPackage.PLANT__MOUNTING_HEIGHT:
				setMountingHeight((Double)newValue);
				return;
			case PvPackage.PLANT__ALBEDO:
				setAlbedo((Double)newValue);
				return;
			case PvPackage.PLANT__SYSTEM_LOSSES:
				setSystemLosses((Double)newValue);
				return;
			case PvPackage.PLANT__ARRAYS:
				getArrays().clear();
				getArrays().addAll((Collection<? extends PvArray>)newValue);
				return;
			case PvPackage.PLANT__INVERTERS:
				getInverters().clear();
				getInverters().addAll((Collection<? extends Inverter>)newValue);
				return;
			case PvPackage.PLANT__OBSTACLES:
				getObstacles().clear();
				getObstacles().addAll((Collection<? extends Obstacle>)newValue);
				return;
			case PvPackage.PLANT__HORIZON:
				getHorizon().clear();
				getHorizon().addAll((Collection<? extends HorizonPoint>)newValue);
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
			case PvPackage.PLANT__ID:
				setId(ID_EDEFAULT);
				return;
			case PvPackage.PLANT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.PLANT__SITE_ID:
				setSiteId(SITE_ID_EDEFAULT);
				return;
			case PvPackage.PLANT__LATITUDE:
				unsetLatitude();
				return;
			case PvPackage.PLANT__LONGITUDE:
				unsetLongitude();
				return;
			case PvPackage.PLANT__MOUNTING_HEIGHT:
				unsetMountingHeight();
				return;
			case PvPackage.PLANT__ALBEDO:
				setAlbedo(ALBEDO_EDEFAULT);
				return;
			case PvPackage.PLANT__SYSTEM_LOSSES:
				setSystemLosses(SYSTEM_LOSSES_EDEFAULT);
				return;
			case PvPackage.PLANT__ARRAYS:
				getArrays().clear();
				return;
			case PvPackage.PLANT__INVERTERS:
				getInverters().clear();
				return;
			case PvPackage.PLANT__OBSTACLES:
				getObstacles().clear();
				return;
			case PvPackage.PLANT__HORIZON:
				getHorizon().clear();
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
			case PvPackage.PLANT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PvPackage.PLANT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.PLANT__SITE_ID:
				return SITE_ID_EDEFAULT == null ? siteId != null : !SITE_ID_EDEFAULT.equals(siteId);
			case PvPackage.PLANT__LATITUDE:
				return isSetLatitude();
			case PvPackage.PLANT__LONGITUDE:
				return isSetLongitude();
			case PvPackage.PLANT__MOUNTING_HEIGHT:
				return isSetMountingHeight();
			case PvPackage.PLANT__ALBEDO:
				return albedo != ALBEDO_EDEFAULT;
			case PvPackage.PLANT__SYSTEM_LOSSES:
				return systemLosses != SYSTEM_LOSSES_EDEFAULT;
			case PvPackage.PLANT__ARRAYS:
				return arrays != null && !arrays.isEmpty();
			case PvPackage.PLANT__INVERTERS:
				return inverters != null && !inverters.isEmpty();
			case PvPackage.PLANT__OBSTACLES:
				return obstacles != null && !obstacles.isEmpty();
			case PvPackage.PLANT__HORIZON:
				return horizon != null && !horizon.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", siteId: ");
		result.append(siteId);
		result.append(", latitude: ");
		if (latitudeESet) result.append(latitude); else result.append("<unset>");
		result.append(", longitude: ");
		if (longitudeESet) result.append(longitude); else result.append("<unset>");
		result.append(", mountingHeight: ");
		if (mountingHeightESet) result.append(mountingHeight); else result.append("<unset>");
		result.append(", albedo: ");
		result.append(albedo);
		result.append(", systemLosses: ");
		result.append(systemLosses);
		result.append(')');
		return result.toString();
	}

} //PlantImpl
