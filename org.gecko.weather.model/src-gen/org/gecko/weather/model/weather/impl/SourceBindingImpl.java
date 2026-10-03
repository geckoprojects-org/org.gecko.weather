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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getRank <em>Rank</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getElevationDeltaMeters <em>Elevation Delta Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SourceBindingImpl#getResolvedAt <em>Resolved At</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class SourceBindingImpl extends MinimalEObjectImpl.Container implements SourceBinding {
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
	 * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected static final BindingOrigin ORIGIN_EDEFAULT = BindingOrigin.AUTOMATIC;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected BindingOrigin origin = ORIGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected static final int RANK_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected int rank = RANK_EDEFAULT;

	/**
	 * The default value of the '{@link #getDistanceMeters() <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistanceMeters()
	 * @generated
	 * @ordered
	 */
	protected static final double DISTANCE_METERS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDistanceMeters() <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistanceMeters()
	 * @generated
	 * @ordered
	 */
	protected double distanceMeters = DISTANCE_METERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getElevationDeltaMeters() <em>Elevation Delta Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevationDeltaMeters()
	 * @generated
	 * @ordered
	 */
	protected static final double ELEVATION_DELTA_METERS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getElevationDeltaMeters() <em>Elevation Delta Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevationDeltaMeters()
	 * @generated
	 * @ordered
	 */
	protected double elevationDeltaMeters = ELEVATION_DELTA_METERS_EDEFAULT;

	/**
	 * This is true if the Elevation Delta Meters attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elevationDeltaMetersESet;

	/**
	 * The default value of the '{@link #getResolvedAt() <em>Resolved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolvedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant RESOLVED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolvedAt() <em>Resolved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolvedAt()
	 * @generated
	 * @ordered
	 */
	protected Instant resolvedAt = RESOLVED_AT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.SOURCE_BINDING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__PROVIDER_ID, oldProviderId, providerId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__PRODUCT_ID, oldProductId, productId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BindingOrigin getOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrigin(BindingOrigin newOrigin) {
		BindingOrigin oldOrigin = origin;
		origin = newOrigin == null ? ORIGIN_EDEFAULT : newOrigin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__ORIGIN, oldOrigin, origin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getRank() {
		return rank;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRank(int newRank) {
		int oldRank = rank;
		rank = newRank;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__RANK, oldRank, rank));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getDistanceMeters() {
		return distanceMeters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDistanceMeters(double newDistanceMeters) {
		double oldDistanceMeters = distanceMeters;
		distanceMeters = newDistanceMeters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__DISTANCE_METERS, oldDistanceMeters, distanceMeters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getElevationDeltaMeters() {
		return elevationDeltaMeters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElevationDeltaMeters(double newElevationDeltaMeters) {
		double oldElevationDeltaMeters = elevationDeltaMeters;
		elevationDeltaMeters = newElevationDeltaMeters;
		boolean oldElevationDeltaMetersESet = elevationDeltaMetersESet;
		elevationDeltaMetersESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS, oldElevationDeltaMeters, elevationDeltaMeters, !oldElevationDeltaMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetElevationDeltaMeters() {
		double oldElevationDeltaMeters = elevationDeltaMeters;
		boolean oldElevationDeltaMetersESet = elevationDeltaMetersESet;
		elevationDeltaMeters = ELEVATION_DELTA_METERS_EDEFAULT;
		elevationDeltaMetersESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS, oldElevationDeltaMeters, ELEVATION_DELTA_METERS_EDEFAULT, oldElevationDeltaMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetElevationDeltaMeters() {
		return elevationDeltaMetersESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getResolvedAt() {
		return resolvedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResolvedAt(Instant newResolvedAt) {
		Instant oldResolvedAt = resolvedAt;
		resolvedAt = newResolvedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SOURCE_BINDING__RESOLVED_AT, oldResolvedAt, resolvedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.SOURCE_BINDING__PROVIDER_ID:
				return getProviderId();
			case WeatherPackage.SOURCE_BINDING__PRODUCT_ID:
				return getProductId();
			case WeatherPackage.SOURCE_BINDING__ORIGIN:
				return getOrigin();
			case WeatherPackage.SOURCE_BINDING__RANK:
				return getRank();
			case WeatherPackage.SOURCE_BINDING__DISTANCE_METERS:
				return getDistanceMeters();
			case WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS:
				return getElevationDeltaMeters();
			case WeatherPackage.SOURCE_BINDING__RESOLVED_AT:
				return getResolvedAt();
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
			case WeatherPackage.SOURCE_BINDING__PROVIDER_ID:
				setProviderId((String)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__PRODUCT_ID:
				setProductId((String)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__ORIGIN:
				setOrigin((BindingOrigin)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__RANK:
				setRank((Integer)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__DISTANCE_METERS:
				setDistanceMeters((Double)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS:
				setElevationDeltaMeters((Double)newValue);
				return;
			case WeatherPackage.SOURCE_BINDING__RESOLVED_AT:
				setResolvedAt((Instant)newValue);
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
			case WeatherPackage.SOURCE_BINDING__PROVIDER_ID:
				setProviderId(PROVIDER_ID_EDEFAULT);
				return;
			case WeatherPackage.SOURCE_BINDING__PRODUCT_ID:
				setProductId(PRODUCT_ID_EDEFAULT);
				return;
			case WeatherPackage.SOURCE_BINDING__ORIGIN:
				setOrigin(ORIGIN_EDEFAULT);
				return;
			case WeatherPackage.SOURCE_BINDING__RANK:
				setRank(RANK_EDEFAULT);
				return;
			case WeatherPackage.SOURCE_BINDING__DISTANCE_METERS:
				setDistanceMeters(DISTANCE_METERS_EDEFAULT);
				return;
			case WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS:
				unsetElevationDeltaMeters();
				return;
			case WeatherPackage.SOURCE_BINDING__RESOLVED_AT:
				setResolvedAt(RESOLVED_AT_EDEFAULT);
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
			case WeatherPackage.SOURCE_BINDING__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerId != null : !PROVIDER_ID_EDEFAULT.equals(providerId);
			case WeatherPackage.SOURCE_BINDING__PRODUCT_ID:
				return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
			case WeatherPackage.SOURCE_BINDING__ORIGIN:
				return origin != ORIGIN_EDEFAULT;
			case WeatherPackage.SOURCE_BINDING__RANK:
				return rank != RANK_EDEFAULT;
			case WeatherPackage.SOURCE_BINDING__DISTANCE_METERS:
				return distanceMeters != DISTANCE_METERS_EDEFAULT;
			case WeatherPackage.SOURCE_BINDING__ELEVATION_DELTA_METERS:
				return isSetElevationDeltaMeters();
			case WeatherPackage.SOURCE_BINDING__RESOLVED_AT:
				return RESOLVED_AT_EDEFAULT == null ? resolvedAt != null : !RESOLVED_AT_EDEFAULT.equals(resolvedAt);
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
		result.append(", origin: ");
		result.append(origin);
		result.append(", rank: ");
		result.append(rank);
		result.append(", distanceMeters: ");
		result.append(distanceMeters);
		result.append(", elevationDeltaMeters: ");
		if (elevationDeltaMetersESet) result.append(elevationDeltaMeters); else result.append("<unset>");
		result.append(", resolvedAt: ");
		result.append(resolvedAt);
		result.append(')');
		return result.toString();
	}

} //SourceBindingImpl
