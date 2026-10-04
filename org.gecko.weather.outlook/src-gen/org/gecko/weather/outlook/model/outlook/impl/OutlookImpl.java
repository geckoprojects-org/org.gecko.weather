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
package org.gecko.weather.outlook.model.outlook.impl;

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

import org.gecko.weather.outlook.model.outlook.DayOutlook;
import org.gecko.weather.outlook.model.outlook.HourOutlook;
import org.gecko.weather.outlook.model.outlook.Outlook;
import org.gecko.weather.outlook.model.outlook.OutlookPackage;
import org.gecko.weather.outlook.model.outlook.SourceNote;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outlook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getSiteName <em>Site Name</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getHours <em>Hours</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getDays <em>Days</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.OutlookImpl#getSources <em>Sources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OutlookImpl extends MinimalEObjectImpl.Container implements Outlook {
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
	 * The default value of the '{@link #getSiteName() <em>Site Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSiteName()
	 * @generated
	 * @ordered
	 */
	protected static final String SITE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSiteName() <em>Site Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSiteName()
	 * @generated
	 * @ordered
	 */
	protected String siteName = SITE_NAME_EDEFAULT;

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
	protected EList<HourOutlook> hours;

	/**
	 * The cached value of the '{@link #getDays() <em>Days</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDays()
	 * @generated
	 * @ordered
	 */
	protected EList<DayOutlook> days;

	/**
	 * The cached value of the '{@link #getSources() <em>Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSources()
	 * @generated
	 * @ordered
	 */
	protected EList<SourceNote> sources;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutlookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.OUTLOOK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__SITE_ID, oldSiteId, siteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSiteName() {
		return siteName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSiteName(String newSiteName) {
		String oldSiteName = siteName;
		siteName = newSiteName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__SITE_NAME, oldSiteName, siteName));
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__LATITUDE, oldLatitude, latitude));
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__LONGITUDE, oldLongitude, longitude));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__TIME_ZONE, oldTimeZone, timeZone));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.OUTLOOK__GENERATED_AT, oldGeneratedAt, generatedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<HourOutlook> getHours() {
		if (hours == null) {
			hours = new EObjectContainmentEList<HourOutlook>(HourOutlook.class, this, OutlookPackage.OUTLOOK__HOURS);
		}
		return hours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DayOutlook> getDays() {
		if (days == null) {
			days = new EObjectContainmentEList<DayOutlook>(DayOutlook.class, this, OutlookPackage.OUTLOOK__DAYS);
		}
		return days;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SourceNote> getSources() {
		if (sources == null) {
			sources = new EObjectContainmentEList<SourceNote>(SourceNote.class, this, OutlookPackage.OUTLOOK__SOURCES);
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutlookPackage.OUTLOOK__HOURS:
				return ((InternalEList<?>)getHours()).basicRemove(otherEnd, msgs);
			case OutlookPackage.OUTLOOK__DAYS:
				return ((InternalEList<?>)getDays()).basicRemove(otherEnd, msgs);
			case OutlookPackage.OUTLOOK__SOURCES:
				return ((InternalEList<?>)getSources()).basicRemove(otherEnd, msgs);
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
			case OutlookPackage.OUTLOOK__SITE_ID:
				return getSiteId();
			case OutlookPackage.OUTLOOK__SITE_NAME:
				return getSiteName();
			case OutlookPackage.OUTLOOK__LATITUDE:
				return getLatitude();
			case OutlookPackage.OUTLOOK__LONGITUDE:
				return getLongitude();
			case OutlookPackage.OUTLOOK__TIME_ZONE:
				return getTimeZone();
			case OutlookPackage.OUTLOOK__GENERATED_AT:
				return getGeneratedAt();
			case OutlookPackage.OUTLOOK__HOURS:
				return getHours();
			case OutlookPackage.OUTLOOK__DAYS:
				return getDays();
			case OutlookPackage.OUTLOOK__SOURCES:
				return getSources();
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
			case OutlookPackage.OUTLOOK__SITE_ID:
				setSiteId((String)newValue);
				return;
			case OutlookPackage.OUTLOOK__SITE_NAME:
				setSiteName((String)newValue);
				return;
			case OutlookPackage.OUTLOOK__LATITUDE:
				setLatitude((Double)newValue);
				return;
			case OutlookPackage.OUTLOOK__LONGITUDE:
				setLongitude((Double)newValue);
				return;
			case OutlookPackage.OUTLOOK__TIME_ZONE:
				setTimeZone((String)newValue);
				return;
			case OutlookPackage.OUTLOOK__GENERATED_AT:
				setGeneratedAt((Date)newValue);
				return;
			case OutlookPackage.OUTLOOK__HOURS:
				getHours().clear();
				getHours().addAll((Collection<? extends HourOutlook>)newValue);
				return;
			case OutlookPackage.OUTLOOK__DAYS:
				getDays().clear();
				getDays().addAll((Collection<? extends DayOutlook>)newValue);
				return;
			case OutlookPackage.OUTLOOK__SOURCES:
				getSources().clear();
				getSources().addAll((Collection<? extends SourceNote>)newValue);
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
			case OutlookPackage.OUTLOOK__SITE_ID:
				setSiteId(SITE_ID_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__SITE_NAME:
				setSiteName(SITE_NAME_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__LATITUDE:
				setLatitude(LATITUDE_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__LONGITUDE:
				setLongitude(LONGITUDE_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__TIME_ZONE:
				setTimeZone(TIME_ZONE_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__GENERATED_AT:
				setGeneratedAt(GENERATED_AT_EDEFAULT);
				return;
			case OutlookPackage.OUTLOOK__HOURS:
				getHours().clear();
				return;
			case OutlookPackage.OUTLOOK__DAYS:
				getDays().clear();
				return;
			case OutlookPackage.OUTLOOK__SOURCES:
				getSources().clear();
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
			case OutlookPackage.OUTLOOK__SITE_ID:
				return SITE_ID_EDEFAULT == null ? siteId != null : !SITE_ID_EDEFAULT.equals(siteId);
			case OutlookPackage.OUTLOOK__SITE_NAME:
				return SITE_NAME_EDEFAULT == null ? siteName != null : !SITE_NAME_EDEFAULT.equals(siteName);
			case OutlookPackage.OUTLOOK__LATITUDE:
				return latitude != LATITUDE_EDEFAULT;
			case OutlookPackage.OUTLOOK__LONGITUDE:
				return longitude != LONGITUDE_EDEFAULT;
			case OutlookPackage.OUTLOOK__TIME_ZONE:
				return TIME_ZONE_EDEFAULT == null ? timeZone != null : !TIME_ZONE_EDEFAULT.equals(timeZone);
			case OutlookPackage.OUTLOOK__GENERATED_AT:
				return GENERATED_AT_EDEFAULT == null ? generatedAt != null : !GENERATED_AT_EDEFAULT.equals(generatedAt);
			case OutlookPackage.OUTLOOK__HOURS:
				return hours != null && !hours.isEmpty();
			case OutlookPackage.OUTLOOK__DAYS:
				return days != null && !days.isEmpty();
			case OutlookPackage.OUTLOOK__SOURCES:
				return sources != null && !sources.isEmpty();
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
		result.append(" (siteId: ");
		result.append(siteId);
		result.append(", siteName: ");
		result.append(siteName);
		result.append(", latitude: ");
		result.append(latitude);
		result.append(", longitude: ");
		result.append(longitude);
		result.append(", timeZone: ");
		result.append(timeZone);
		result.append(", generatedAt: ");
		result.append(generatedAt);
		result.append(')');
		return result.toString();
	}

} //OutlookImpl
