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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uncertainty</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#getQuality <em>Quality</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#getSpatialMeters <em>Spatial Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#getTemporalOffset <em>Temporal Offset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#getLeadTime <em>Lead Time</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#isStale <em>Stale</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.UncertaintyImpl#getNote <em>Note</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UncertaintyImpl extends MinimalEObjectImpl.Container implements Uncertainty {
	/**
	 * The default value of the '{@link #getQuality() <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuality()
	 * @generated
	 * @ordered
	 */
	protected static final Quality QUALITY_EDEFAULT = Quality.OBSERVED;

	/**
	 * The cached value of the '{@link #getQuality() <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuality()
	 * @generated
	 * @ordered
	 */
	protected Quality quality = QUALITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpatialMeters() <em>Spatial Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpatialMeters()
	 * @generated
	 * @ordered
	 */
	protected static final double SPATIAL_METERS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSpatialMeters() <em>Spatial Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpatialMeters()
	 * @generated
	 * @ordered
	 */
	protected double spatialMeters = SPATIAL_METERS_EDEFAULT;

	/**
	 * This is true if the Spatial Meters attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean spatialMetersESet;

	/**
	 * The default value of the '{@link #getTemporalOffset() <em>Temporal Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporalOffset()
	 * @generated
	 * @ordered
	 */
	protected static final Duration TEMPORAL_OFFSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemporalOffset() <em>Temporal Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporalOffset()
	 * @generated
	 * @ordered
	 */
	protected Duration temporalOffset = TEMPORAL_OFFSET_EDEFAULT;

	/**
	 * This is true if the Temporal Offset attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean temporalOffsetESet;

	/**
	 * The default value of the '{@link #getLeadTime() <em>Lead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadTime()
	 * @generated
	 * @ordered
	 */
	protected static final Duration LEAD_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeadTime() <em>Lead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadTime()
	 * @generated
	 * @ordered
	 */
	protected Duration leadTime = LEAD_TIME_EDEFAULT;

	/**
	 * This is true if the Lead Time attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean leadTimeESet;

	/**
	 * The default value of the '{@link #isStale() <em>Stale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStale()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STALE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStale() <em>Stale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStale()
	 * @generated
	 * @ordered
	 */
	protected boolean stale = STALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected String note = NOTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UncertaintyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.UNCERTAINTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Quality getQuality() {
		return quality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setQuality(Quality newQuality) {
		Quality oldQuality = quality;
		quality = newQuality == null ? QUALITY_EDEFAULT : newQuality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__QUALITY, oldQuality, quality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSpatialMeters() {
		return spatialMeters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpatialMeters(double newSpatialMeters) {
		double oldSpatialMeters = spatialMeters;
		spatialMeters = newSpatialMeters;
		boolean oldSpatialMetersESet = spatialMetersESet;
		spatialMetersESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__SPATIAL_METERS, oldSpatialMeters, spatialMeters, !oldSpatialMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSpatialMeters() {
		double oldSpatialMeters = spatialMeters;
		boolean oldSpatialMetersESet = spatialMetersESet;
		spatialMeters = SPATIAL_METERS_EDEFAULT;
		spatialMetersESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.UNCERTAINTY__SPATIAL_METERS, oldSpatialMeters, SPATIAL_METERS_EDEFAULT, oldSpatialMetersESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSpatialMeters() {
		return spatialMetersESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Duration getTemporalOffset() {
		return temporalOffset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemporalOffset(Duration newTemporalOffset) {
		Duration oldTemporalOffset = temporalOffset;
		temporalOffset = newTemporalOffset;
		boolean oldTemporalOffsetESet = temporalOffsetESet;
		temporalOffsetESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET, oldTemporalOffset, temporalOffset, !oldTemporalOffsetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTemporalOffset() {
		Duration oldTemporalOffset = temporalOffset;
		boolean oldTemporalOffsetESet = temporalOffsetESet;
		temporalOffset = TEMPORAL_OFFSET_EDEFAULT;
		temporalOffsetESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET, oldTemporalOffset, TEMPORAL_OFFSET_EDEFAULT, oldTemporalOffsetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTemporalOffset() {
		return temporalOffsetESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Duration getLeadTime() {
		return leadTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeadTime(Duration newLeadTime) {
		Duration oldLeadTime = leadTime;
		leadTime = newLeadTime;
		boolean oldLeadTimeESet = leadTimeESet;
		leadTimeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__LEAD_TIME, oldLeadTime, leadTime, !oldLeadTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLeadTime() {
		Duration oldLeadTime = leadTime;
		boolean oldLeadTimeESet = leadTimeESet;
		leadTime = LEAD_TIME_EDEFAULT;
		leadTimeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WeatherPackage.UNCERTAINTY__LEAD_TIME, oldLeadTime, LEAD_TIME_EDEFAULT, oldLeadTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLeadTime() {
		return leadTimeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isStale() {
		return stale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStale(boolean newStale) {
		boolean oldStale = stale;
		stale = newStale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__STALE, oldStale, stale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNote() {
		return note;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNote(String newNote) {
		String oldNote = note;
		note = newNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.UNCERTAINTY__NOTE, oldNote, note));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.UNCERTAINTY__QUALITY:
				return getQuality();
			case WeatherPackage.UNCERTAINTY__SPATIAL_METERS:
				return getSpatialMeters();
			case WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET:
				return getTemporalOffset();
			case WeatherPackage.UNCERTAINTY__LEAD_TIME:
				return getLeadTime();
			case WeatherPackage.UNCERTAINTY__STALE:
				return isStale();
			case WeatherPackage.UNCERTAINTY__NOTE:
				return getNote();
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
			case WeatherPackage.UNCERTAINTY__QUALITY:
				setQuality((Quality)newValue);
				return;
			case WeatherPackage.UNCERTAINTY__SPATIAL_METERS:
				setSpatialMeters((Double)newValue);
				return;
			case WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET:
				setTemporalOffset((Duration)newValue);
				return;
			case WeatherPackage.UNCERTAINTY__LEAD_TIME:
				setLeadTime((Duration)newValue);
				return;
			case WeatherPackage.UNCERTAINTY__STALE:
				setStale((Boolean)newValue);
				return;
			case WeatherPackage.UNCERTAINTY__NOTE:
				setNote((String)newValue);
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
			case WeatherPackage.UNCERTAINTY__QUALITY:
				setQuality(QUALITY_EDEFAULT);
				return;
			case WeatherPackage.UNCERTAINTY__SPATIAL_METERS:
				unsetSpatialMeters();
				return;
			case WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET:
				unsetTemporalOffset();
				return;
			case WeatherPackage.UNCERTAINTY__LEAD_TIME:
				unsetLeadTime();
				return;
			case WeatherPackage.UNCERTAINTY__STALE:
				setStale(STALE_EDEFAULT);
				return;
			case WeatherPackage.UNCERTAINTY__NOTE:
				setNote(NOTE_EDEFAULT);
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
			case WeatherPackage.UNCERTAINTY__QUALITY:
				return quality != QUALITY_EDEFAULT;
			case WeatherPackage.UNCERTAINTY__SPATIAL_METERS:
				return isSetSpatialMeters();
			case WeatherPackage.UNCERTAINTY__TEMPORAL_OFFSET:
				return isSetTemporalOffset();
			case WeatherPackage.UNCERTAINTY__LEAD_TIME:
				return isSetLeadTime();
			case WeatherPackage.UNCERTAINTY__STALE:
				return stale != STALE_EDEFAULT;
			case WeatherPackage.UNCERTAINTY__NOTE:
				return NOTE_EDEFAULT == null ? note != null : !NOTE_EDEFAULT.equals(note);
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
		result.append(" (quality: ");
		result.append(quality);
		result.append(", spatialMeters: ");
		if (spatialMetersESet) result.append(spatialMeters); else result.append("<unset>");
		result.append(", temporalOffset: ");
		if (temporalOffsetESet) result.append(temporalOffset); else result.append("<unset>");
		result.append(", leadTime: ");
		if (leadTimeESet) result.append(leadTime); else result.append("<unset>");
		result.append(", stale: ");
		result.append(stale);
		result.append(", note: ");
		result.append(note);
		result.append(')');
		return result.toString();
	}

} //UncertaintyImpl
