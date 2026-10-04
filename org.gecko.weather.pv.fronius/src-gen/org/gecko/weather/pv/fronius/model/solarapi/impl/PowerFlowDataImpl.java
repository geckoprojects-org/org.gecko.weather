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
package org.gecko.weather.pv.fronius.model.solarapi.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gecko.weather.pv.fronius.model.solarapi.Inverter;
import org.gecko.weather.pv.fronius.model.solarapi.PowerFlowData;
import org.gecko.weather.pv.fronius.model.solarapi.Site;
import org.gecko.weather.pv.fronius.model.solarapi.SolarApiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Power Flow Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl#getSite <em>Site</em>}</li>
 *   <li>{@link org.gecko.weather.pv.fronius.model.solarapi.impl.PowerFlowDataImpl#getInverters <em>Inverters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PowerFlowDataImpl extends MinimalEObjectImpl.Container implements PowerFlowData {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSite() <em>Site</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSite()
	 * @generated
	 * @ordered
	 */
	protected Site site;

	/**
	 * The cached value of the '{@link #getInverters() <em>Inverters</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverters()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Inverter> inverters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PowerFlowDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SolarApiPackage.Literals.POWER_FLOW_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.POWER_FLOW_DATA__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Site getSite() {
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSite(Site newSite, NotificationChain msgs) {
		Site oldSite = site;
		site = newSite;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SolarApiPackage.POWER_FLOW_DATA__SITE, oldSite, newSite);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSite(Site newSite) {
		if (newSite != site) {
			NotificationChain msgs = null;
			if (site != null)
				msgs = ((InternalEObject)site).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SolarApiPackage.POWER_FLOW_DATA__SITE, null, msgs);
			if (newSite != null)
				msgs = ((InternalEObject)newSite).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SolarApiPackage.POWER_FLOW_DATA__SITE, null, msgs);
			msgs = basicSetSite(newSite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SolarApiPackage.POWER_FLOW_DATA__SITE, newSite, newSite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, Inverter> getInverters() {
		if (inverters == null) {
			inverters = new EcoreEMap<String,Inverter>(SolarApiPackage.Literals.INVERTER_ENTRY, InverterEntryImpl.class, this, SolarApiPackage.POWER_FLOW_DATA__INVERTERS);
		}
		return inverters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SolarApiPackage.POWER_FLOW_DATA__SITE:
				return basicSetSite(null, msgs);
			case SolarApiPackage.POWER_FLOW_DATA__INVERTERS:
				return ((InternalEList<?>)getInverters()).basicRemove(otherEnd, msgs);
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
			case SolarApiPackage.POWER_FLOW_DATA__VERSION:
				return getVersion();
			case SolarApiPackage.POWER_FLOW_DATA__SITE:
				return getSite();
			case SolarApiPackage.POWER_FLOW_DATA__INVERTERS:
				if (coreType) return getInverters();
				else return getInverters().map();
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
			case SolarApiPackage.POWER_FLOW_DATA__VERSION:
				setVersion((String)newValue);
				return;
			case SolarApiPackage.POWER_FLOW_DATA__SITE:
				setSite((Site)newValue);
				return;
			case SolarApiPackage.POWER_FLOW_DATA__INVERTERS:
				((EStructuralFeature.Setting)getInverters()).set(newValue);
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
			case SolarApiPackage.POWER_FLOW_DATA__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case SolarApiPackage.POWER_FLOW_DATA__SITE:
				setSite((Site)null);
				return;
			case SolarApiPackage.POWER_FLOW_DATA__INVERTERS:
				getInverters().clear();
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
			case SolarApiPackage.POWER_FLOW_DATA__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case SolarApiPackage.POWER_FLOW_DATA__SITE:
				return site != null;
			case SolarApiPackage.POWER_FLOW_DATA__INVERTERS:
				return inverters != null && !inverters.isEmpty();
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
		result.append(" (version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //PowerFlowDataImpl
