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
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getRegisteredAt <em>Registered At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getDataCompleteFrom <em>Data Complete From</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.SiteImpl#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SiteImpl extends MinimalEObjectImpl.Container implements Site {
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
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected GeoPosition position;

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
	 * The default value of the '{@link #getRegisteredAt() <em>Registered At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegisteredAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant REGISTERED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRegisteredAt() <em>Registered At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegisteredAt()
	 * @generated
	 * @ordered
	 */
	protected Instant registeredAt = REGISTERED_AT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataCompleteFrom() <em>Data Complete From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataCompleteFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Instant DATA_COMPLETE_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataCompleteFrom() <em>Data Complete From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataCompleteFrom()
	 * @generated
	 * @ordered
	 */
	protected Instant dataCompleteFrom = DATA_COMPLETE_FROM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> attributes;

	/**
	 * The cached value of the '{@link #getBindings() <em>Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<SourceBinding> bindings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.SITE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoPosition getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPosition(GeoPosition newPosition, NotificationChain msgs) {
		GeoPosition oldPosition = position;
		position = newPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__POSITION, oldPosition, newPosition);
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
	public void setPosition(GeoPosition newPosition) {
		if (newPosition != position) {
			NotificationChain msgs = null;
			if (position != null)
				msgs = ((InternalEObject)position).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.SITE__POSITION, null, msgs);
			if (newPosition != null)
				msgs = ((InternalEObject)newPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.SITE__POSITION, null, msgs);
			msgs = basicSetPosition(newPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__POSITION, newPosition, newPosition));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__TIME_ZONE, oldTimeZone, timeZone));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getRegisteredAt() {
		return registeredAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRegisteredAt(Instant newRegisteredAt) {
		Instant oldRegisteredAt = registeredAt;
		registeredAt = newRegisteredAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__REGISTERED_AT, oldRegisteredAt, registeredAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__ACTIVE, oldActive, active));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getDataCompleteFrom() {
		return dataCompleteFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDataCompleteFrom(Instant newDataCompleteFrom) {
		Instant oldDataCompleteFrom = dataCompleteFrom;
		dataCompleteFrom = newDataCompleteFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.SITE__DATA_COMPLETE_FROM, oldDataCompleteFrom, dataCompleteFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(WeatherPackage.Literals.SITE_ATTRIBUTE, SiteAttributeImpl.class, this, WeatherPackage.SITE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SourceBinding> getBindings() {
		if (bindings == null) {
			bindings = new EObjectContainmentEList<SourceBinding>(SourceBinding.class, this, WeatherPackage.SITE__BINDINGS);
		}
		return bindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.SITE__POSITION:
				return basicSetPosition(null, msgs);
			case WeatherPackage.SITE__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case WeatherPackage.SITE__BINDINGS:
				return ((InternalEList<?>)getBindings()).basicRemove(otherEnd, msgs);
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
			case WeatherPackage.SITE__ID:
				return getId();
			case WeatherPackage.SITE__NAME:
				return getName();
			case WeatherPackage.SITE__POSITION:
				return getPosition();
			case WeatherPackage.SITE__TIME_ZONE:
				return getTimeZone();
			case WeatherPackage.SITE__REGISTERED_AT:
				return getRegisteredAt();
			case WeatherPackage.SITE__ACTIVE:
				return isActive();
			case WeatherPackage.SITE__DATA_COMPLETE_FROM:
				return getDataCompleteFrom();
			case WeatherPackage.SITE__ATTRIBUTES:
				if (coreType) return getAttributes();
				else return getAttributes().map();
			case WeatherPackage.SITE__BINDINGS:
				return getBindings();
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
			case WeatherPackage.SITE__ID:
				setId((String)newValue);
				return;
			case WeatherPackage.SITE__NAME:
				setName((String)newValue);
				return;
			case WeatherPackage.SITE__POSITION:
				setPosition((GeoPosition)newValue);
				return;
			case WeatherPackage.SITE__TIME_ZONE:
				setTimeZone((String)newValue);
				return;
			case WeatherPackage.SITE__REGISTERED_AT:
				setRegisteredAt((Instant)newValue);
				return;
			case WeatherPackage.SITE__ACTIVE:
				setActive((Boolean)newValue);
				return;
			case WeatherPackage.SITE__DATA_COMPLETE_FROM:
				setDataCompleteFrom((Instant)newValue);
				return;
			case WeatherPackage.SITE__ATTRIBUTES:
				((EStructuralFeature.Setting)getAttributes()).set(newValue);
				return;
			case WeatherPackage.SITE__BINDINGS:
				getBindings().clear();
				getBindings().addAll((Collection<? extends SourceBinding>)newValue);
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
			case WeatherPackage.SITE__ID:
				setId(ID_EDEFAULT);
				return;
			case WeatherPackage.SITE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WeatherPackage.SITE__POSITION:
				setPosition((GeoPosition)null);
				return;
			case WeatherPackage.SITE__TIME_ZONE:
				setTimeZone(TIME_ZONE_EDEFAULT);
				return;
			case WeatherPackage.SITE__REGISTERED_AT:
				setRegisteredAt(REGISTERED_AT_EDEFAULT);
				return;
			case WeatherPackage.SITE__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
				return;
			case WeatherPackage.SITE__DATA_COMPLETE_FROM:
				setDataCompleteFrom(DATA_COMPLETE_FROM_EDEFAULT);
				return;
			case WeatherPackage.SITE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case WeatherPackage.SITE__BINDINGS:
				getBindings().clear();
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
			case WeatherPackage.SITE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case WeatherPackage.SITE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WeatherPackage.SITE__POSITION:
				return position != null;
			case WeatherPackage.SITE__TIME_ZONE:
				return TIME_ZONE_EDEFAULT == null ? timeZone != null : !TIME_ZONE_EDEFAULT.equals(timeZone);
			case WeatherPackage.SITE__REGISTERED_AT:
				return REGISTERED_AT_EDEFAULT == null ? registeredAt != null : !REGISTERED_AT_EDEFAULT.equals(registeredAt);
			case WeatherPackage.SITE__ACTIVE:
				return active != ACTIVE_EDEFAULT;
			case WeatherPackage.SITE__DATA_COMPLETE_FROM:
				return DATA_COMPLETE_FROM_EDEFAULT == null ? dataCompleteFrom != null : !DATA_COMPLETE_FROM_EDEFAULT.equals(dataCompleteFrom);
			case WeatherPackage.SITE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case WeatherPackage.SITE__BINDINGS:
				return bindings != null && !bindings.isEmpty();
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
		result.append(", timeZone: ");
		result.append(timeZone);
		result.append(", registeredAt: ");
		result.append(registeredAt);
		result.append(", active: ");
		result.append(active);
		result.append(", dataCompleteFrom: ");
		result.append(dataCompleteFrom);
		result.append(')');
		return result.toString();
	}

} //SiteImpl
