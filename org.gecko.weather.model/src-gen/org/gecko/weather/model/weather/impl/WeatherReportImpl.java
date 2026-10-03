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
package org.gecko.weather.model.weather.impl;

import java.time.Instant;

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

import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getDatasets <em>Datasets</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.WeatherReportImpl#getDays <em>Days</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WeatherReportImpl extends MinimalEObjectImpl.Container implements WeatherReport {
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
	 * The default value of the '{@link #getGeneratedAt() <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant GENERATED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedAt() <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratedAt()
	 * @generated
	 * @ordered
	 */
	protected Instant generatedAt = GENERATED_AT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDatasets() <em>Datasets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatasets()
	 * @generated
	 * @ordered
	 */
	protected EList<SourceDataset> datasets;

	/**
	 * The cached value of the '{@link #getDays() <em>Days</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDays()
	 * @generated
	 * @ordered
	 */
	protected EList<DayInfo> days;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeatherReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.WEATHER_REPORT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__SITE_ID, oldSiteId, siteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getGeneratedAt() {
		return generatedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeneratedAt(Instant newGeneratedAt) {
		Instant oldGeneratedAt = generatedAt;
		generatedAt = newGeneratedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WEATHER_REPORT__GENERATED_AT, oldGeneratedAt, generatedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SourceDataset> getDatasets() {
		if (datasets == null) {
			datasets = new EObjectContainmentEList<SourceDataset>(SourceDataset.class, this, WeatherPackage.WEATHER_REPORT__DATASETS);
		}
		return datasets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DayInfo> getDays() {
		if (days == null) {
			days = new EObjectContainmentEList<DayInfo>(DayInfo.class, this, WeatherPackage.WEATHER_REPORT__DAYS);
		}
		return days;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.WEATHER_REPORT__DATASETS:
				return ((InternalEList<?>)getDatasets()).basicRemove(otherEnd, msgs);
			case WeatherPackage.WEATHER_REPORT__DAYS:
				return ((InternalEList<?>)getDays()).basicRemove(otherEnd, msgs);
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
			case WeatherPackage.WEATHER_REPORT__SITE_ID:
				return getSiteId();
			case WeatherPackage.WEATHER_REPORT__GENERATED_AT:
				return getGeneratedAt();
			case WeatherPackage.WEATHER_REPORT__DATASETS:
				return getDatasets();
			case WeatherPackage.WEATHER_REPORT__DAYS:
				return getDays();
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
			case WeatherPackage.WEATHER_REPORT__SITE_ID:
				setSiteId((String)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__GENERATED_AT:
				setGeneratedAt((Instant)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__DATASETS:
				getDatasets().clear();
				getDatasets().addAll((Collection<? extends SourceDataset>)newValue);
				return;
			case WeatherPackage.WEATHER_REPORT__DAYS:
				getDays().clear();
				getDays().addAll((Collection<? extends DayInfo>)newValue);
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
			case WeatherPackage.WEATHER_REPORT__SITE_ID:
				setSiteId(SITE_ID_EDEFAULT);
				return;
			case WeatherPackage.WEATHER_REPORT__GENERATED_AT:
				setGeneratedAt(GENERATED_AT_EDEFAULT);
				return;
			case WeatherPackage.WEATHER_REPORT__DATASETS:
				getDatasets().clear();
				return;
			case WeatherPackage.WEATHER_REPORT__DAYS:
				getDays().clear();
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
			case WeatherPackage.WEATHER_REPORT__SITE_ID:
				return SITE_ID_EDEFAULT == null ? siteId != null : !SITE_ID_EDEFAULT.equals(siteId);
			case WeatherPackage.WEATHER_REPORT__GENERATED_AT:
				return GENERATED_AT_EDEFAULT == null ? generatedAt != null : !GENERATED_AT_EDEFAULT.equals(generatedAt);
			case WeatherPackage.WEATHER_REPORT__DATASETS:
				return datasets != null && !datasets.isEmpty();
			case WeatherPackage.WEATHER_REPORT__DAYS:
				return days != null && !days.isEmpty();
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
		result.append(", generatedAt: ");
		result.append(generatedAt);
		result.append(')');
		return result.toString();
	}

} //WeatherReportImpl
