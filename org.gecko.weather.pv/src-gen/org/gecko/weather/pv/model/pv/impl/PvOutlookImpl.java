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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gecko.weather.pv.model.pv.PvArrayInfo;
import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvHour;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outlook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getPlantId <em>Plant Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getPlantName <em>Plant Name</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getHours <em>Hours</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getDays <em>Days</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvOutlookImpl#getArrays <em>Arrays</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvOutlookImpl extends MinimalEObjectImpl.Container implements PvOutlook {
	/**
	 * The default value of the '{@link #getPlantId() <em>Plant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlantId()
	 * @generated
	 * @ordered
	 */
	protected static final String PLANT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPlantId() <em>Plant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlantId()
	 * @generated
	 * @ordered
	 */
	protected String plantId = PLANT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPlantName() <em>Plant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlantName()
	 * @generated
	 * @ordered
	 */
	protected static final String PLANT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPlantName() <em>Plant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlantName()
	 * @generated
	 * @ordered
	 */
	protected String plantName = PLANT_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getGeneratedAt() <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Date GENERATED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedAt() <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedAt()
	 * @generated
	 * @ordered
	 */
	protected Date generatedAt = GENERATED_AT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHours() <em>Hours</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHours()
	 * @generated
	 * @ordered
	 */
	protected EList<PvHour> hours;

	/**
	 * The cached value of the '{@link #getDays() <em>Days</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDays()
	 * @generated
	 * @ordered
	 */
	protected EList<PvDay> days;

	/**
	 * The cached value of the '{@link #getArrays() <em>Arrays</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrays()
	 * @generated
	 * @ordered
	 */
	protected EList<PvArrayInfo> arrays;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvOutlookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_OUTLOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPlantId() {
		return plantId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlantId(String newPlantId) {
		String oldPlantId = plantId;
		plantId = newPlantId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__PLANT_ID, oldPlantId, plantId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPlantName() {
		return plantName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlantName(String newPlantName) {
		String oldPlantName = plantName;
		plantName = newPlantName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__PLANT_NAME, oldPlantName, plantName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__SITE_ID, oldSiteId, siteId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__TIME_ZONE, oldTimeZone, timeZone));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__PEAK_POWER, oldPeakPower, peakPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getGeneratedAt() {
		return generatedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeneratedAt(Date newGeneratedAt) {
		Date oldGeneratedAt = generatedAt;
		generatedAt = newGeneratedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_OUTLOOK__GENERATED_AT, oldGeneratedAt, generatedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PvHour> getHours() {
		if (hours == null) {
			hours = new EObjectContainmentEList<PvHour>(PvHour.class, this, PvPackage.PV_OUTLOOK__HOURS);
		}
		return hours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PvDay> getDays() {
		if (days == null) {
			days = new EObjectContainmentEList<PvDay>(PvDay.class, this, PvPackage.PV_OUTLOOK__DAYS);
		}
		return days;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PvArrayInfo> getArrays() {
		if (arrays == null) {
			arrays = new EObjectContainmentEList<PvArrayInfo>(PvArrayInfo.class, this, PvPackage.PV_OUTLOOK__ARRAYS);
		}
		return arrays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PvPackage.PV_OUTLOOK__HOURS:
				return ((InternalEList<?>)getHours()).basicRemove(otherEnd, msgs);
			case PvPackage.PV_OUTLOOK__DAYS:
				return ((InternalEList<?>)getDays()).basicRemove(otherEnd, msgs);
			case PvPackage.PV_OUTLOOK__ARRAYS:
				return ((InternalEList<?>)getArrays()).basicRemove(otherEnd, msgs);
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
			case PvPackage.PV_OUTLOOK__PLANT_ID:
				return getPlantId();
			case PvPackage.PV_OUTLOOK__PLANT_NAME:
				return getPlantName();
			case PvPackage.PV_OUTLOOK__SITE_ID:
				return getSiteId();
			case PvPackage.PV_OUTLOOK__TIME_ZONE:
				return getTimeZone();
			case PvPackage.PV_OUTLOOK__PEAK_POWER:
				return getPeakPower();
			case PvPackage.PV_OUTLOOK__GENERATED_AT:
				return getGeneratedAt();
			case PvPackage.PV_OUTLOOK__HOURS:
				return getHours();
			case PvPackage.PV_OUTLOOK__DAYS:
				return getDays();
			case PvPackage.PV_OUTLOOK__ARRAYS:
				return getArrays();
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
			case PvPackage.PV_OUTLOOK__PLANT_ID:
				setPlantId((String)newValue);
				return;
			case PvPackage.PV_OUTLOOK__PLANT_NAME:
				setPlantName((String)newValue);
				return;
			case PvPackage.PV_OUTLOOK__SITE_ID:
				setSiteId((String)newValue);
				return;
			case PvPackage.PV_OUTLOOK__TIME_ZONE:
				setTimeZone((String)newValue);
				return;
			case PvPackage.PV_OUTLOOK__PEAK_POWER:
				setPeakPower((Double)newValue);
				return;
			case PvPackage.PV_OUTLOOK__GENERATED_AT:
				setGeneratedAt((Date)newValue);
				return;
			case PvPackage.PV_OUTLOOK__HOURS:
				getHours().clear();
				getHours().addAll((Collection<? extends PvHour>)newValue);
				return;
			case PvPackage.PV_OUTLOOK__DAYS:
				getDays().clear();
				getDays().addAll((Collection<? extends PvDay>)newValue);
				return;
			case PvPackage.PV_OUTLOOK__ARRAYS:
				getArrays().clear();
				getArrays().addAll((Collection<? extends PvArrayInfo>)newValue);
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
			case PvPackage.PV_OUTLOOK__PLANT_ID:
				setPlantId(PLANT_ID_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__PLANT_NAME:
				setPlantName(PLANT_NAME_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__SITE_ID:
				setSiteId(SITE_ID_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__TIME_ZONE:
				setTimeZone(TIME_ZONE_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__PEAK_POWER:
				setPeakPower(PEAK_POWER_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__GENERATED_AT:
				setGeneratedAt(GENERATED_AT_EDEFAULT);
				return;
			case PvPackage.PV_OUTLOOK__HOURS:
				getHours().clear();
				return;
			case PvPackage.PV_OUTLOOK__DAYS:
				getDays().clear();
				return;
			case PvPackage.PV_OUTLOOK__ARRAYS:
				getArrays().clear();
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
			case PvPackage.PV_OUTLOOK__PLANT_ID:
				return PLANT_ID_EDEFAULT == null ? plantId != null : !PLANT_ID_EDEFAULT.equals(plantId);
			case PvPackage.PV_OUTLOOK__PLANT_NAME:
				return PLANT_NAME_EDEFAULT == null ? plantName != null : !PLANT_NAME_EDEFAULT.equals(plantName);
			case PvPackage.PV_OUTLOOK__SITE_ID:
				return SITE_ID_EDEFAULT == null ? siteId != null : !SITE_ID_EDEFAULT.equals(siteId);
			case PvPackage.PV_OUTLOOK__TIME_ZONE:
				return TIME_ZONE_EDEFAULT == null ? timeZone != null : !TIME_ZONE_EDEFAULT.equals(timeZone);
			case PvPackage.PV_OUTLOOK__PEAK_POWER:
				return peakPower != PEAK_POWER_EDEFAULT;
			case PvPackage.PV_OUTLOOK__GENERATED_AT:
				return GENERATED_AT_EDEFAULT == null ? generatedAt != null : !GENERATED_AT_EDEFAULT.equals(generatedAt);
			case PvPackage.PV_OUTLOOK__HOURS:
				return hours != null && !hours.isEmpty();
			case PvPackage.PV_OUTLOOK__DAYS:
				return days != null && !days.isEmpty();
			case PvPackage.PV_OUTLOOK__ARRAYS:
				return arrays != null && !arrays.isEmpty();
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
		result.append(" (plantId: ");
		result.append(plantId);
		result.append(", plantName: ");
		result.append(plantName);
		result.append(", siteId: ");
		result.append(siteId);
		result.append(", timeZone: ");
		result.append(timeZone);
		result.append(", peakPower: ");
		result.append(peakPower);
		result.append(", generatedAt: ");
		result.append(generatedAt);
		result.append(')');
		return result.toString();
	}

} //PvOutlookImpl
