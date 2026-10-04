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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.model.pv.PlantEntry;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plant Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PlantEntryImpl#getTimeZone <em>Time Zone</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlantEntryImpl extends MinimalEObjectImpl.Container implements PlantEntry {
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
	 * The default value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected static final double PEAK_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected double peakPower = PEAK_POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeZone() <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeZone()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_ZONE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeZone() <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeZone()
	 * @generated
	 * @ordered
	 */
	protected String timeZone = TIME_ZONE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlantEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PLANT_ENTRY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT_ENTRY__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT_ENTRY__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT_ENTRY__SITE_ID, oldSiteId, siteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPeakPower() {
		return peakPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeakPower(double newPeakPower) {
		double oldPeakPower = peakPower;
		peakPower = newPeakPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT_ENTRY__PEAK_POWER, oldPeakPower, peakPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeZone(String newTimeZone) {
		String oldTimeZone = timeZone;
		timeZone = newTimeZone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PLANT_ENTRY__TIME_ZONE, oldTimeZone, timeZone));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PLANT_ENTRY__ID:
				return getId();
			case PvPackage.PLANT_ENTRY__NAME:
				return getName();
			case PvPackage.PLANT_ENTRY__SITE_ID:
				return getSiteId();
			case PvPackage.PLANT_ENTRY__PEAK_POWER:
				return getPeakPower();
			case PvPackage.PLANT_ENTRY__TIME_ZONE:
				return getTimeZone();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PvPackage.PLANT_ENTRY__ID:
				setId((String)newValue);
				return;
			case PvPackage.PLANT_ENTRY__NAME:
				setName((String)newValue);
				return;
			case PvPackage.PLANT_ENTRY__SITE_ID:
				setSiteId((String)newValue);
				return;
			case PvPackage.PLANT_ENTRY__PEAK_POWER:
				setPeakPower((Double)newValue);
				return;
			case PvPackage.PLANT_ENTRY__TIME_ZONE:
				setTimeZone((String)newValue);
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
			case PvPackage.PLANT_ENTRY__ID:
				setId(ID_EDEFAULT);
				return;
			case PvPackage.PLANT_ENTRY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PvPackage.PLANT_ENTRY__SITE_ID:
				setSiteId(SITE_ID_EDEFAULT);
				return;
			case PvPackage.PLANT_ENTRY__PEAK_POWER:
				setPeakPower(PEAK_POWER_EDEFAULT);
				return;
			case PvPackage.PLANT_ENTRY__TIME_ZONE:
				setTimeZone(TIME_ZONE_EDEFAULT);
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
			case PvPackage.PLANT_ENTRY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PvPackage.PLANT_ENTRY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PvPackage.PLANT_ENTRY__SITE_ID:
				return SITE_ID_EDEFAULT == null ? siteId != null : !SITE_ID_EDEFAULT.equals(siteId);
			case PvPackage.PLANT_ENTRY__PEAK_POWER:
				return peakPower != PEAK_POWER_EDEFAULT;
			case PvPackage.PLANT_ENTRY__TIME_ZONE:
				return TIME_ZONE_EDEFAULT == null ? timeZone != null : !TIME_ZONE_EDEFAULT.equals(timeZone);
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
		result.append(", peakPower: ");
		result.append(peakPower);
		result.append(", timeZone: ");
		result.append(timeZone);
		result.append(')');
		return result.toString();
	}

} //PlantEntryImpl
