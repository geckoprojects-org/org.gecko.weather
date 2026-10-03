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

import java.time.Duration;
import java.time.Instant;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measured Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getStatistic <em>Statistic</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getThreshold <em>Threshold</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getThresholdUnit <em>Threshold Unit</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getValidAt <em>Valid At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getProvenance <em>Provenance</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MeasuredValueImpl#getUncertainty <em>Uncertainty</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MeasuredValueImpl extends MinimalEObjectImpl.Container implements MeasuredValue {
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final MeasurementKind KIND_EDEFAULT = MeasurementKind.AIR_TEMPERATURE;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected MeasurementKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final Level LEVEL_EDEFAULT = Level.UNSPECIFIED;

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected Level level = LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatistic() <em>Statistic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatistic()
	 * @generated
	 * @ordered
	 */
	protected static final Statistic STATISTIC_EDEFAULT = Statistic.INSTANT;

	/**
	 * The cached value of the '{@link #getStatistic() <em>Statistic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatistic()
	 * @generated
	 * @ordered
	 */
	protected Statistic statistic = STATISTIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected static final Duration PERIOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected Duration period = PERIOD_EDEFAULT;

	/**
	 * This is true if the Period attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean periodESet;

	/**
	 * The default value of the '{@link #getThreshold() <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getThreshold() <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreshold()
	 * @generated
	 * @ordered
	 */
	protected double threshold = THRESHOLD_EDEFAULT;

	/**
	 * This is true if the Threshold attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean thresholdESet;

	/**
	 * The default value of the '{@link #getThresholdUnit() <em>Threshold Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThresholdUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String THRESHOLD_UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getThresholdUnit() <em>Threshold Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThresholdUnit()
	 * @generated
	 * @ordered
	 */
	protected String thresholdUnit = THRESHOLD_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidAt() <em>Valid At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidAt()
	 * @generated
	 * @ordered
	 */
	protected static final Instant VALID_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidAt() <em>Valid At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidAt()
	 * @generated
	 * @ordered
	 */
	protected Instant validAt = VALID_AT_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected double value = VALUE_EDEFAULT;

	/**
	 * This is true if the Value attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean valueESet;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final int CODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected int code = CODE_EDEFAULT;

	/**
	 * This is true if the Code attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean codeESet;

	/**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected String unit = UNIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProvenance() <em>Provenance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvenance()
	 * @generated
	 * @ordered
	 */
	protected Provenance provenance;

	/**
	 * The cached value of the '{@link #getUncertainty() <em>Uncertainty</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUncertainty()
	 * @generated
	 * @ordered
	 */
	protected Uncertainty uncertainty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasuredValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.MEASURED_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MeasurementKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(MeasurementKind newKind) {
		MeasurementKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Level getLevel() {
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLevel(Level newLevel) {
		Level oldLevel = level;
		level = newLevel == null ? LEVEL_EDEFAULT : newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__LEVEL, oldLevel, level));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Statistic getStatistic() {
		return statistic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatistic(Statistic newStatistic) {
		Statistic oldStatistic = statistic;
		statistic = newStatistic == null ? STATISTIC_EDEFAULT : newStatistic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__STATISTIC, oldStatistic, statistic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Duration getPeriod() {
		return period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeriod(Duration newPeriod) {
		Duration oldPeriod = period;
		period = newPeriod;
		boolean oldPeriodESet = periodESet;
		periodESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__PERIOD, oldPeriod, period, !oldPeriodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPeriod() {
		Duration oldPeriod = period;
		boolean oldPeriodESet = periodESet;
		period = PERIOD_EDEFAULT;
		periodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.MEASURED_VALUE__PERIOD, oldPeriod, PERIOD_EDEFAULT, oldPeriodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPeriod() {
		return periodESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getThreshold() {
		return threshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setThreshold(double newThreshold) {
		double oldThreshold = threshold;
		threshold = newThreshold;
		boolean oldThresholdESet = thresholdESet;
		thresholdESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__THRESHOLD, oldThreshold, threshold, !oldThresholdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetThreshold() {
		double oldThreshold = threshold;
		boolean oldThresholdESet = thresholdESet;
		threshold = THRESHOLD_EDEFAULT;
		thresholdESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.MEASURED_VALUE__THRESHOLD, oldThreshold, THRESHOLD_EDEFAULT, oldThresholdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetThreshold() {
		return thresholdESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getThresholdUnit() {
		return thresholdUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setThresholdUnit(String newThresholdUnit) {
		String oldThresholdUnit = thresholdUnit;
		thresholdUnit = newThresholdUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__THRESHOLD_UNIT, oldThresholdUnit, thresholdUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getValidAt() {
		return validAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidAt(Instant newValidAt) {
		Instant oldValidAt = validAt;
		validAt = newValidAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__VALID_AT, oldValidAt, validAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(double newValue) {
		double oldValue = value;
		value = newValue;
		boolean oldValueESet = valueESet;
		valueESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__VALUE, oldValue, value, !oldValueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetValue() {
		double oldValue = value;
		boolean oldValueESet = valueESet;
		value = VALUE_EDEFAULT;
		valueESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.MEASURED_VALUE__VALUE, oldValue, VALUE_EDEFAULT, oldValueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetValue() {
		return valueESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCode(int newCode) {
		int oldCode = code;
		code = newCode;
		boolean oldCodeESet = codeESet;
		codeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__CODE, oldCode, code, !oldCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCode() {
		int oldCode = code;
		boolean oldCodeESet = codeESet;
		code = CODE_EDEFAULT;
		codeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.MEASURED_VALUE__CODE, oldCode, CODE_EDEFAULT, oldCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCode() {
		return codeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnit(String newUnit) {
		String oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Provenance getProvenance() {
		return provenance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProvenance(Provenance newProvenance, NotificationChain msgs) {
		Provenance oldProvenance = provenance;
		provenance = newProvenance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__PROVENANCE, oldProvenance, newProvenance);
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
	public void setProvenance(Provenance newProvenance) {
		if (newProvenance != provenance) {
			NotificationChain msgs = null;
			if (provenance != null)
				msgs = ((InternalEObject)provenance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.MEASURED_VALUE__PROVENANCE, null, msgs);
			if (newProvenance != null)
				msgs = ((InternalEObject)newProvenance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.MEASURED_VALUE__PROVENANCE, null, msgs);
			msgs = basicSetProvenance(newProvenance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__PROVENANCE, newProvenance, newProvenance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Uncertainty getUncertainty() {
		return uncertainty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUncertainty(Uncertainty newUncertainty, NotificationChain msgs) {
		Uncertainty oldUncertainty = uncertainty;
		uncertainty = newUncertainty;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__UNCERTAINTY, oldUncertainty, newUncertainty);
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
	public void setUncertainty(Uncertainty newUncertainty) {
		if (newUncertainty != uncertainty) {
			NotificationChain msgs = null;
			if (uncertainty != null)
				msgs = ((InternalEObject)uncertainty).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.MEASURED_VALUE__UNCERTAINTY, null, msgs);
			if (newUncertainty != null)
				msgs = ((InternalEObject)newUncertainty).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WeatherPackage.MEASURED_VALUE__UNCERTAINTY, null, msgs);
			msgs = basicSetUncertainty(newUncertainty, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MEASURED_VALUE__UNCERTAINTY, newUncertainty, newUncertainty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WeatherPackage.MEASURED_VALUE__PROVENANCE:
				return basicSetProvenance(null, msgs);
			case WeatherPackage.MEASURED_VALUE__UNCERTAINTY:
				return basicSetUncertainty(null, msgs);
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
			case WeatherPackage.MEASURED_VALUE__KIND:
				return getKind();
			case WeatherPackage.MEASURED_VALUE__LEVEL:
				return getLevel();
			case WeatherPackage.MEASURED_VALUE__STATISTIC:
				return getStatistic();
			case WeatherPackage.MEASURED_VALUE__PERIOD:
				return getPeriod();
			case WeatherPackage.MEASURED_VALUE__THRESHOLD:
				return getThreshold();
			case WeatherPackage.MEASURED_VALUE__THRESHOLD_UNIT:
				return getThresholdUnit();
			case WeatherPackage.MEASURED_VALUE__VALID_AT:
				return getValidAt();
			case WeatherPackage.MEASURED_VALUE__VALUE:
				return getValue();
			case WeatherPackage.MEASURED_VALUE__CODE:
				return getCode();
			case WeatherPackage.MEASURED_VALUE__UNIT:
				return getUnit();
			case WeatherPackage.MEASURED_VALUE__PROVENANCE:
				return getProvenance();
			case WeatherPackage.MEASURED_VALUE__UNCERTAINTY:
				return getUncertainty();
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
			case WeatherPackage.MEASURED_VALUE__KIND:
				setKind((MeasurementKind)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__LEVEL:
				setLevel((Level)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__STATISTIC:
				setStatistic((Statistic)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__PERIOD:
				setPeriod((Duration)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__THRESHOLD:
				setThreshold((Double)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__THRESHOLD_UNIT:
				setThresholdUnit((String)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__VALID_AT:
				setValidAt((Instant)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__VALUE:
				setValue((Double)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__CODE:
				setCode((Integer)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__UNIT:
				setUnit((String)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__PROVENANCE:
				setProvenance((Provenance)newValue);
				return;
			case WeatherPackage.MEASURED_VALUE__UNCERTAINTY:
				setUncertainty((Uncertainty)newValue);
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
			case WeatherPackage.MEASURED_VALUE__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__LEVEL:
				setLevel(LEVEL_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__STATISTIC:
				setStatistic(STATISTIC_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__PERIOD:
				unsetPeriod();
				return;
			case WeatherPackage.MEASURED_VALUE__THRESHOLD:
				unsetThreshold();
				return;
			case WeatherPackage.MEASURED_VALUE__THRESHOLD_UNIT:
				setThresholdUnit(THRESHOLD_UNIT_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__VALID_AT:
				setValidAt(VALID_AT_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__VALUE:
				unsetValue();
				return;
			case WeatherPackage.MEASURED_VALUE__CODE:
				unsetCode();
				return;
			case WeatherPackage.MEASURED_VALUE__UNIT:
				setUnit(UNIT_EDEFAULT);
				return;
			case WeatherPackage.MEASURED_VALUE__PROVENANCE:
				setProvenance((Provenance)null);
				return;
			case WeatherPackage.MEASURED_VALUE__UNCERTAINTY:
				setUncertainty((Uncertainty)null);
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
			case WeatherPackage.MEASURED_VALUE__KIND:
				return kind != KIND_EDEFAULT;
			case WeatherPackage.MEASURED_VALUE__LEVEL:
				return level != LEVEL_EDEFAULT;
			case WeatherPackage.MEASURED_VALUE__STATISTIC:
				return statistic != STATISTIC_EDEFAULT;
			case WeatherPackage.MEASURED_VALUE__PERIOD:
				return isSetPeriod();
			case WeatherPackage.MEASURED_VALUE__THRESHOLD:
				return isSetThreshold();
			case WeatherPackage.MEASURED_VALUE__THRESHOLD_UNIT:
				return THRESHOLD_UNIT_EDEFAULT == null ? thresholdUnit != null : !THRESHOLD_UNIT_EDEFAULT.equals(thresholdUnit);
			case WeatherPackage.MEASURED_VALUE__VALID_AT:
				return VALID_AT_EDEFAULT == null ? validAt != null : !VALID_AT_EDEFAULT.equals(validAt);
			case WeatherPackage.MEASURED_VALUE__VALUE:
				return isSetValue();
			case WeatherPackage.MEASURED_VALUE__CODE:
				return isSetCode();
			case WeatherPackage.MEASURED_VALUE__UNIT:
				return UNIT_EDEFAULT == null ? unit != null : !UNIT_EDEFAULT.equals(unit);
			case WeatherPackage.MEASURED_VALUE__PROVENANCE:
				return provenance != null;
			case WeatherPackage.MEASURED_VALUE__UNCERTAINTY:
				return uncertainty != null;
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", level: ");
		result.append(level);
		result.append(", statistic: ");
		result.append(statistic);
		result.append(", period: ");
		if (periodESet) result.append(period); else result.append("<unset>");
		result.append(", threshold: ");
		if (thresholdESet) result.append(threshold); else result.append("<unset>");
		result.append(", thresholdUnit: ");
		result.append(thresholdUnit);
		result.append(", validAt: ");
		result.append(validAt);
		result.append(", value: ");
		if (valueESet) result.append(value); else result.append("<unset>");
		result.append(", code: ");
		if (codeESet) result.append(code); else result.append("<unset>");
		result.append(", unit: ");
		result.append(unit);
		result.append(')');
		return result.toString();
	}

} //MeasuredValueImpl
