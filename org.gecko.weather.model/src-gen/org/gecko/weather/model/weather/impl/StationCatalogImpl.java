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

import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Station Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.StationCatalogImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.StationCatalogImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.StationCatalogImpl#getRetrievedAt <em>Retrieved At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.StationCatalogImpl#getStations <em>Stations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StationCatalogImpl extends MinimalEObjectImpl.Container implements StationCatalog {
	/**
	 * The default value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderId()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderId()
	 * @generated
	 * @ordered
	 */
	protected String providerId = PROVIDER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductId()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductId()
	 * @generated
	 * @ordered
	 */
	protected String productId = PRODUCT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getRetrievedAt() <em>Retrieved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetrievedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant RETRIEVED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRetrievedAt() <em>Retrieved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetrievedAt()
	 * @generated
	 * @ordered
	 */
	protected Instant retrievedAt = RETRIEVED_AT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStations() <em>Stations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStations()
	 * @generated
	 * @ordered
	 */
	protected EList<Station> stations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StationCatalogImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.STATION_CATALOG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProviderId() {
		return providerId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProviderId(String newProviderId) {
		String oldProviderId = providerId;
		providerId = newProviderId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.STATION_CATALOG__PROVIDER_ID, oldProviderId, providerId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductId() {
		return productId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductId(String newProductId) {
		String oldProductId = productId;
		productId = newProductId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.STATION_CATALOG__PRODUCT_ID, oldProductId, productId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getRetrievedAt() {
		return retrievedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRetrievedAt(Instant newRetrievedAt) {
		Instant oldRetrievedAt = retrievedAt;
		retrievedAt = newRetrievedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.STATION_CATALOG__RETRIEVED_AT, oldRetrievedAt, retrievedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Station> getStations() {
		if (stations == null) {
			stations = new EObjectContainmentEList<Station>(Station.class, this, WeatherPackage.STATION_CATALOG__STATIONS);
		}
		return stations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.STATION_CATALOG__STATIONS:
				return ((InternalEList<?>)getStations()).basicRemove(otherEnd, msgs);
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
			case WeatherPackage.STATION_CATALOG__PROVIDER_ID:
				return getProviderId();
			case WeatherPackage.STATION_CATALOG__PRODUCT_ID:
				return getProductId();
			case WeatherPackage.STATION_CATALOG__RETRIEVED_AT:
				return getRetrievedAt();
			case WeatherPackage.STATION_CATALOG__STATIONS:
				return getStations();
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
			case WeatherPackage.STATION_CATALOG__PROVIDER_ID:
				setProviderId((String)newValue);
				return;
			case WeatherPackage.STATION_CATALOG__PRODUCT_ID:
				setProductId((String)newValue);
				return;
			case WeatherPackage.STATION_CATALOG__RETRIEVED_AT:
				setRetrievedAt((Instant)newValue);
				return;
			case WeatherPackage.STATION_CATALOG__STATIONS:
				getStations().clear();
				getStations().addAll((Collection<? extends Station>)newValue);
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
			case WeatherPackage.STATION_CATALOG__PROVIDER_ID:
				setProviderId(PROVIDER_ID_EDEFAULT);
				return;
			case WeatherPackage.STATION_CATALOG__PRODUCT_ID:
				setProductId(PRODUCT_ID_EDEFAULT);
				return;
			case WeatherPackage.STATION_CATALOG__RETRIEVED_AT:
				setRetrievedAt(RETRIEVED_AT_EDEFAULT);
				return;
			case WeatherPackage.STATION_CATALOG__STATIONS:
				getStations().clear();
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
			case WeatherPackage.STATION_CATALOG__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerId != null : !PROVIDER_ID_EDEFAULT.equals(providerId);
			case WeatherPackage.STATION_CATALOG__PRODUCT_ID:
				return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
			case WeatherPackage.STATION_CATALOG__RETRIEVED_AT:
				return RETRIEVED_AT_EDEFAULT == null ? retrievedAt != null : !RETRIEVED_AT_EDEFAULT.equals(retrievedAt);
			case WeatherPackage.STATION_CATALOG__STATIONS:
				return stations != null && !stations.isEmpty();
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
		result.append(" (providerId: ");
		result.append(providerId);
		result.append(", productId: ");
		result.append(productId);
		result.append(", retrievedAt: ");
		result.append(retrievedAt);
		result.append(')');
		return result.toString();
	}

} //StationCatalogImpl
