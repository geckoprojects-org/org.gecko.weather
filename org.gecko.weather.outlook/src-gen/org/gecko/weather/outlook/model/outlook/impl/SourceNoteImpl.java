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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.outlook.model.outlook.OutlookPackage;
import org.gecko.weather.outlook.model.outlook.SourceNote;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Note</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getQuantities <em>Quantities</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.impl.SourceNoteImpl#getIssuedAt <em>Issued At</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SourceNoteImpl extends MinimalEObjectImpl.Container implements SourceNote {
	/**
	 * The default value of the '{@link #getQuantities() <em>Quantities</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantities()
	 * @generated
	 * @ordered
	 */
	protected static final String QUANTITIES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQuantities() <em>Quantities</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantities()
	 * @generated
	 * @ordered
	 */
	protected String quantities = QUANTITIES_EDEFAULT;

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
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

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
	 * This is true if the Distance Meters attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean distanceMetersESet;

	/**
	 * The default value of the '{@link #getIssuedAt() <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssuedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Date ISSUED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIssuedAt() <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssuedAt()
	 * @generated
	 * @ordered
	 */
	protected Date issuedAt = ISSUED_AT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceNoteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutlookPackage.Literals.SOURCE_NOTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getQuantities() {
		return quantities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setQuantities(String newQuantities) {
		String oldQuantities = quantities;
		quantities = newQuantities;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__QUANTITIES, oldQuantities, quantities));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__PROVIDER_ID, oldProviderId, providerId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__PRODUCT_ID, oldProductId, productId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__LOCATION, oldLocation, location));
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
		boolean oldDistanceMetersESet = distanceMetersESet;
		distanceMetersESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__DISTANCE_METERS, oldDistanceMeters, distanceMeters, !oldDistanceMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDistanceMeters() {
		double oldDistanceMeters = distanceMeters;
		boolean oldDistanceMetersESet = distanceMetersESet;
		distanceMeters = DISTANCE_METERS_EDEFAULT;
		distanceMetersESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, OutlookPackage.SOURCE_NOTE__DISTANCE_METERS, oldDistanceMeters, DISTANCE_METERS_EDEFAULT, oldDistanceMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDistanceMeters() {
		return distanceMetersESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getIssuedAt() {
		return issuedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIssuedAt(Date newIssuedAt) {
		Date oldIssuedAt = issuedAt;
		issuedAt = newIssuedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutlookPackage.SOURCE_NOTE__ISSUED_AT, oldIssuedAt, issuedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutlookPackage.SOURCE_NOTE__QUANTITIES:
				return getQuantities();
			case OutlookPackage.SOURCE_NOTE__PROVIDER_ID:
				return getProviderId();
			case OutlookPackage.SOURCE_NOTE__PRODUCT_ID:
				return getProductId();
			case OutlookPackage.SOURCE_NOTE__LOCATION:
				return getLocation();
			case OutlookPackage.SOURCE_NOTE__DISTANCE_METERS:
				return getDistanceMeters();
			case OutlookPackage.SOURCE_NOTE__ISSUED_AT:
				return getIssuedAt();
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
			case OutlookPackage.SOURCE_NOTE__QUANTITIES:
				setQuantities((String)newValue);
				return;
			case OutlookPackage.SOURCE_NOTE__PROVIDER_ID:
				setProviderId((String)newValue);
				return;
			case OutlookPackage.SOURCE_NOTE__PRODUCT_ID:
				setProductId((String)newValue);
				return;
			case OutlookPackage.SOURCE_NOTE__LOCATION:
				setLocation((String)newValue);
				return;
			case OutlookPackage.SOURCE_NOTE__DISTANCE_METERS:
				setDistanceMeters((Double)newValue);
				return;
			case OutlookPackage.SOURCE_NOTE__ISSUED_AT:
				setIssuedAt((Date)newValue);
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
			case OutlookPackage.SOURCE_NOTE__QUANTITIES:
				setQuantities(QUANTITIES_EDEFAULT);
				return;
			case OutlookPackage.SOURCE_NOTE__PROVIDER_ID:
				setProviderId(PROVIDER_ID_EDEFAULT);
				return;
			case OutlookPackage.SOURCE_NOTE__PRODUCT_ID:
				setProductId(PRODUCT_ID_EDEFAULT);
				return;
			case OutlookPackage.SOURCE_NOTE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case OutlookPackage.SOURCE_NOTE__DISTANCE_METERS:
				unsetDistanceMeters();
				return;
			case OutlookPackage.SOURCE_NOTE__ISSUED_AT:
				setIssuedAt(ISSUED_AT_EDEFAULT);
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
			case OutlookPackage.SOURCE_NOTE__QUANTITIES:
				return QUANTITIES_EDEFAULT == null ? quantities != null : !QUANTITIES_EDEFAULT.equals(quantities);
			case OutlookPackage.SOURCE_NOTE__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerId != null : !PROVIDER_ID_EDEFAULT.equals(providerId);
			case OutlookPackage.SOURCE_NOTE__PRODUCT_ID:
				return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
			case OutlookPackage.SOURCE_NOTE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case OutlookPackage.SOURCE_NOTE__DISTANCE_METERS:
				return isSetDistanceMeters();
			case OutlookPackage.SOURCE_NOTE__ISSUED_AT:
				return ISSUED_AT_EDEFAULT == null ? issuedAt != null : !ISSUED_AT_EDEFAULT.equals(issuedAt);
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
		result.append(" (quantities: ");
		result.append(quantities);
		result.append(", providerId: ");
		result.append(providerId);
		result.append(", productId: ");
		result.append(productId);
		result.append(", location: ");
		result.append(location);
		result.append(", distanceMeters: ");
		if (distanceMetersESet) result.append(distanceMeters); else result.append("<unset>");
		result.append(", issuedAt: ");
		result.append(issuedAt);
		result.append(')');
		return result.toString();
	}

} //SourceNoteImpl
