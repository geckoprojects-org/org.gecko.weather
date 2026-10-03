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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.Derivation;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provenance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getModelRun <em>Model Run</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getIssuedAt <em>Issued At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getRetrievedAt <em>Retrieved At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getStationId <em>Station Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getCell <em>Cell</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getLicence <em>Licence</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getAttribution <em>Attribution</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.ProvenanceImpl#getDerivation <em>Derivation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProvenanceImpl extends MinimalEObjectImpl.Container implements Provenance {
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
	 * The default value of the '{@link #getSourceElement() <em>Source Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceElement()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceElement() <em>Source Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceElement()
	 * @generated
	 * @ordered
	 */
	protected String sourceElement = SOURCE_ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelRun() <em>Model Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelRun()
	 * @generated
	 * @ordered
	 */
	protected static final Instant MODEL_RUN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelRun() <em>Model Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelRun()
	 * @generated
	 * @ordered
	 */
	protected Instant modelRun = MODEL_RUN_EDEFAULT;

	/**
	 * The default value of the '{@link #getIssuedAt() <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssuedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant ISSUED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIssuedAt() <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssuedAt()
	 * @generated
	 * @ordered
	 */
	protected Instant issuedAt = ISSUED_AT_EDEFAULT;

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
	 * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected static final Origin ORIGIN_EDEFAULT = Origin.STATION;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected Origin origin = ORIGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getStationId() <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStationId()
	 * @generated
	 * @ordered
	 */
	protected static final String STATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStationId() <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStationId()
	 * @generated
	 * @ordered
	 */
	protected String stationId = STATION_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCell() <em>Cell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCell()
	 * @generated
	 * @ordered
	 */
	protected GridCell cell;

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
	 * The default value of the '{@link #getLicence() <em>Licence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicence()
	 * @generated
	 * @ordered
	 */
	protected static final String LICENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLicence() <em>Licence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicence()
	 * @generated
	 * @ordered
	 */
	protected String licence = LICENCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttribution() <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribution()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribution() <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribution()
	 * @generated
	 * @ordered
	 */
	protected String attribution = ATTRIBUTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDerivation() <em>Derivation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivation()
	 * @generated
	 * @ordered
	 */
	protected Derivation derivation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvenanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.PROVENANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__PROVIDER_ID, oldProviderId, providerId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__PRODUCT_ID, oldProductId, productId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSourceElement() {
		return sourceElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourceElement(String newSourceElement) {
		String oldSourceElement = sourceElement;
		sourceElement = newSourceElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__SOURCE_ELEMENT, oldSourceElement, sourceElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getModelRun() {
		return modelRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModelRun(Instant newModelRun) {
		Instant oldModelRun = modelRun;
		modelRun = newModelRun;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__MODEL_RUN, oldModelRun, modelRun));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getIssuedAt() {
		return issuedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIssuedAt(Instant newIssuedAt) {
		Instant oldIssuedAt = issuedAt;
		issuedAt = newIssuedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__ISSUED_AT, oldIssuedAt, issuedAt));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__RETRIEVED_AT, oldRetrievedAt, retrievedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Origin getOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrigin(Origin newOrigin) {
		Origin oldOrigin = origin;
		origin = newOrigin == null ? ORIGIN_EDEFAULT : newOrigin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__ORIGIN, oldOrigin, origin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStationId() {
		return stationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStationId(String newStationId) {
		String oldStationId = stationId;
		stationId = newStationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__STATION_ID, oldStationId, stationId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GridCell getCell() {
		return cell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCell(GridCell newCell, NotificationChain msgs) {
		GridCell oldCell = cell;
		cell = newCell;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__CELL, oldCell, newCell);
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
	public void setCell(GridCell newCell) {
		if (newCell != cell) {
			NotificationChain msgs = null;
			if (cell != null)
				msgs = ((InternalEObject)cell).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.PROVENANCE__CELL, null, msgs);
			if (newCell != null)
				msgs = ((InternalEObject)newCell).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.PROVENANCE__CELL, null, msgs);
			msgs = basicSetCell(newCell, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__CELL, newCell, newCell));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__DISTANCE_METERS, oldDistanceMeters, distanceMeters, !oldDistanceMetersESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.PROVENANCE__DISTANCE_METERS, oldDistanceMeters, DISTANCE_METERS_EDEFAULT, oldDistanceMetersESet));
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
	public String getLicence() {
		return licence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicence(String newLicence) {
		String oldLicence = licence;
		licence = newLicence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__LICENCE, oldLicence, licence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAttribution() {
		return attribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttribution(String newAttribution) {
		String oldAttribution = attribution;
		attribution = newAttribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__ATTRIBUTION, oldAttribution, attribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Derivation getDerivation() {
		return derivation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDerivation(Derivation newDerivation, NotificationChain msgs) {
		Derivation oldDerivation = derivation;
		derivation = newDerivation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__DERIVATION, oldDerivation, newDerivation);
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
	public void setDerivation(Derivation newDerivation) {
		if (newDerivation != derivation) {
			NotificationChain msgs = null;
			if (derivation != null)
				msgs = ((InternalEObject)derivation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.PROVENANCE__DERIVATION, null, msgs);
			if (newDerivation != null)
				msgs = ((InternalEObject)newDerivation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.PROVENANCE__DERIVATION, null, msgs);
			msgs = basicSetDerivation(newDerivation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.PROVENANCE__DERIVATION, newDerivation, newDerivation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.PROVENANCE__CELL:
				return basicSetCell(null, msgs);
			case WeatherPackage.PROVENANCE__DERIVATION:
				return basicSetDerivation(null, msgs);
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
			case WeatherPackage.PROVENANCE__PROVIDER_ID:
				return getProviderId();
			case WeatherPackage.PROVENANCE__PRODUCT_ID:
				return getProductId();
			case WeatherPackage.PROVENANCE__SOURCE_ELEMENT:
				return getSourceElement();
			case WeatherPackage.PROVENANCE__MODEL_RUN:
				return getModelRun();
			case WeatherPackage.PROVENANCE__ISSUED_AT:
				return getIssuedAt();
			case WeatherPackage.PROVENANCE__RETRIEVED_AT:
				return getRetrievedAt();
			case WeatherPackage.PROVENANCE__ORIGIN:
				return getOrigin();
			case WeatherPackage.PROVENANCE__STATION_ID:
				return getStationId();
			case WeatherPackage.PROVENANCE__CELL:
				return getCell();
			case WeatherPackage.PROVENANCE__DISTANCE_METERS:
				return getDistanceMeters();
			case WeatherPackage.PROVENANCE__LICENCE:
				return getLicence();
			case WeatherPackage.PROVENANCE__ATTRIBUTION:
				return getAttribution();
			case WeatherPackage.PROVENANCE__DERIVATION:
				return getDerivation();
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
			case WeatherPackage.PROVENANCE__PROVIDER_ID:
				setProviderId((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__PRODUCT_ID:
				setProductId((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__SOURCE_ELEMENT:
				setSourceElement((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__MODEL_RUN:
				setModelRun((Instant)newValue);
				return;
			case WeatherPackage.PROVENANCE__ISSUED_AT:
				setIssuedAt((Instant)newValue);
				return;
			case WeatherPackage.PROVENANCE__RETRIEVED_AT:
				setRetrievedAt((Instant)newValue);
				return;
			case WeatherPackage.PROVENANCE__ORIGIN:
				setOrigin((Origin)newValue);
				return;
			case WeatherPackage.PROVENANCE__STATION_ID:
				setStationId((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__CELL:
				setCell((GridCell)newValue);
				return;
			case WeatherPackage.PROVENANCE__DISTANCE_METERS:
				setDistanceMeters((Double)newValue);
				return;
			case WeatherPackage.PROVENANCE__LICENCE:
				setLicence((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__ATTRIBUTION:
				setAttribution((String)newValue);
				return;
			case WeatherPackage.PROVENANCE__DERIVATION:
				setDerivation((Derivation)newValue);
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
			case WeatherPackage.PROVENANCE__PROVIDER_ID:
				setProviderId(PROVIDER_ID_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__PRODUCT_ID:
				setProductId(PRODUCT_ID_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__SOURCE_ELEMENT:
				setSourceElement(SOURCE_ELEMENT_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__MODEL_RUN:
				setModelRun(MODEL_RUN_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__ISSUED_AT:
				setIssuedAt(ISSUED_AT_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__RETRIEVED_AT:
				setRetrievedAt(RETRIEVED_AT_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__ORIGIN:
				setOrigin(ORIGIN_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__STATION_ID:
				setStationId(STATION_ID_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__CELL:
				setCell((GridCell)null);
				return;
			case WeatherPackage.PROVENANCE__DISTANCE_METERS:
				unsetDistanceMeters();
				return;
			case WeatherPackage.PROVENANCE__LICENCE:
				setLicence(LICENCE_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__ATTRIBUTION:
				setAttribution(ATTRIBUTION_EDEFAULT);
				return;
			case WeatherPackage.PROVENANCE__DERIVATION:
				setDerivation((Derivation)null);
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
			case WeatherPackage.PROVENANCE__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerId != null : !PROVIDER_ID_EDEFAULT.equals(providerId);
			case WeatherPackage.PROVENANCE__PRODUCT_ID:
				return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
			case WeatherPackage.PROVENANCE__SOURCE_ELEMENT:
				return SOURCE_ELEMENT_EDEFAULT == null ? sourceElement != null : !SOURCE_ELEMENT_EDEFAULT.equals(sourceElement);
			case WeatherPackage.PROVENANCE__MODEL_RUN:
				return MODEL_RUN_EDEFAULT == null ? modelRun != null : !MODEL_RUN_EDEFAULT.equals(modelRun);
			case WeatherPackage.PROVENANCE__ISSUED_AT:
				return ISSUED_AT_EDEFAULT == null ? issuedAt != null : !ISSUED_AT_EDEFAULT.equals(issuedAt);
			case WeatherPackage.PROVENANCE__RETRIEVED_AT:
				return RETRIEVED_AT_EDEFAULT == null ? retrievedAt != null : !RETRIEVED_AT_EDEFAULT.equals(retrievedAt);
			case WeatherPackage.PROVENANCE__ORIGIN:
				return origin != ORIGIN_EDEFAULT;
			case WeatherPackage.PROVENANCE__STATION_ID:
				return STATION_ID_EDEFAULT == null ? stationId != null : !STATION_ID_EDEFAULT.equals(stationId);
			case WeatherPackage.PROVENANCE__CELL:
				return cell != null;
			case WeatherPackage.PROVENANCE__DISTANCE_METERS:
				return isSetDistanceMeters();
			case WeatherPackage.PROVENANCE__LICENCE:
				return LICENCE_EDEFAULT == null ? licence != null : !LICENCE_EDEFAULT.equals(licence);
			case WeatherPackage.PROVENANCE__ATTRIBUTION:
				return ATTRIBUTION_EDEFAULT == null ? attribution != null : !ATTRIBUTION_EDEFAULT.equals(attribution);
			case WeatherPackage.PROVENANCE__DERIVATION:
				return derivation != null;
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
		result.append(", sourceElement: ");
		result.append(sourceElement);
		result.append(", modelRun: ");
		result.append(modelRun);
		result.append(", issuedAt: ");
		result.append(issuedAt);
		result.append(", retrievedAt: ");
		result.append(retrievedAt);
		result.append(", origin: ");
		result.append(origin);
		result.append(", stationId: ");
		result.append(stationId);
		result.append(", distanceMeters: ");
		if (distanceMetersESet) result.append(distanceMeters); else result.append("<unset>");
		result.append(", licence: ");
		result.append(licence);
		result.append(", attribution: ");
		result.append(attribution);
		result.append(')');
		return result.toString();
	}

} //ProvenanceImpl
