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
package org.gecko.weather.model.weather;

import java.time.Duration;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uncertainty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#getQuality <em>Quality</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#getSpatialMeters <em>Spatial Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#getTemporalOffset <em>Temporal Offset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#getLeadTime <em>Lead Time</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#isStale <em>Stale</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Uncertainty#getNote <em>Note</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty()
 * @model
 * @generated
 */
@ProviderType
public interface Uncertainty extends EObject {
	/**
	 * Returns the value of the '<em><b>Quality</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.Quality}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality</em>' attribute.
	 * @see org.gecko.weather.model.weather.Quality
	 * @see #setQuality(Quality)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_Quality()
	 * @model required="true"
	 * @generated
	 */
	Quality getQuality();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getQuality <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality</em>' attribute.
	 * @see org.gecko.weather.model.weather.Quality
	 * @see #getQuality()
	 * @generated
	 */
	void setQuality(Quality value);

	/**
	 * Returns the value of the '<em><b>Spatial Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Spatial representativeness: the distance the value was carried, or half the cell size for a grid value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spatial Meters</em>' attribute.
	 * @see #isSetSpatialMeters()
	 * @see #unsetSpatialMeters()
	 * @see #setSpatialMeters(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_SpatialMeters()
	 * @model unsettable="true"
	 * @generated
	 */
	double getSpatialMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getSpatialMeters <em>Spatial Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spatial Meters</em>' attribute.
	 * @see #isSetSpatialMeters()
	 * @see #unsetSpatialMeters()
	 * @see #getSpatialMeters()
	 * @generated
	 */
	void setSpatialMeters(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getSpatialMeters <em>Spatial Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSpatialMeters()
	 * @see #getSpatialMeters()
	 * @see #setSpatialMeters(double)
	 * @generated
	 */
	void unsetSpatialMeters();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getSpatialMeters <em>Spatial Meters</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Spatial Meters</em>' attribute is set.
	 * @see #unsetSpatialMeters()
	 * @see #getSpatialMeters()
	 * @see #setSpatialMeters(double)
	 * @generated
	 */
	boolean isSetSpatialMeters();

	/**
	 * Returns the value of the '<em><b>Temporal Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Distance to the nearest source timestep where the value was interpolated in time.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temporal Offset</em>' attribute.
	 * @see #isSetTemporalOffset()
	 * @see #unsetTemporalOffset()
	 * @see #setTemporalOffset(Duration)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_TemporalOffset()
	 * @model unsettable="true" dataType="org.gecko.weather.model.weather.Duration"
	 * @generated
	 */
	Duration getTemporalOffset();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getTemporalOffset <em>Temporal Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporal Offset</em>' attribute.
	 * @see #isSetTemporalOffset()
	 * @see #unsetTemporalOffset()
	 * @see #getTemporalOffset()
	 * @generated
	 */
	void setTemporalOffset(Duration value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getTemporalOffset <em>Temporal Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTemporalOffset()
	 * @see #getTemporalOffset()
	 * @see #setTemporalOffset(Duration)
	 * @generated
	 */
	void unsetTemporalOffset();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getTemporalOffset <em>Temporal Offset</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Temporal Offset</em>' attribute is set.
	 * @see #unsetTemporalOffset()
	 * @see #getTemporalOffset()
	 * @see #setTemporalOffset(Duration)
	 * @generated
	 */
	boolean isSetTemporalOffset();

	/**
	 * Returns the value of the '<em><b>Lead Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * validAt minus issuedAt — how far ahead this value was forecast. Negative for values describing the past.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lead Time</em>' attribute.
	 * @see #isSetLeadTime()
	 * @see #unsetLeadTime()
	 * @see #setLeadTime(Duration)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_LeadTime()
	 * @model unsettable="true" dataType="org.gecko.weather.model.weather.Duration"
	 * @generated
	 */
	Duration getLeadTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getLeadTime <em>Lead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lead Time</em>' attribute.
	 * @see #isSetLeadTime()
	 * @see #unsetLeadTime()
	 * @see #getLeadTime()
	 * @generated
	 */
	void setLeadTime(Duration value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getLeadTime <em>Lead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLeadTime()
	 * @see #getLeadTime()
	 * @see #setLeadTime(Duration)
	 * @generated
	 */
	void unsetLeadTime();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getLeadTime <em>Lead Time</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Lead Time</em>' attribute is set.
	 * @see #unsetLeadTime()
	 * @see #getLeadTime()
	 * @see #setLeadTime(Duration)
	 * @generated
	 */
	boolean isSetLeadTime();

	/**
	 * Returns the value of the '<em><b>Stale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True when the dataset is older than the product's expected refresh interval, i.e. a newer issue should have existed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stale</em>' attribute.
	 * @see #setStale(boolean)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_Stale()
	 * @model
	 * @generated
	 */
	boolean isStale();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#isStale <em>Stale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stale</em>' attribute.
	 * @see #isStale()
	 * @generated
	 */
	void setStale(boolean value);

	/**
	 * Returns the value of the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Note</em>' attribute.
	 * @see #setNote(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getUncertainty_Note()
	 * @model
	 * @generated
	 */
	String getNote();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Uncertainty#getNote <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Note</em>' attribute.
	 * @see #getNote()
	 * @generated
	 */
	void setNote(String value);

} // Uncertainty
