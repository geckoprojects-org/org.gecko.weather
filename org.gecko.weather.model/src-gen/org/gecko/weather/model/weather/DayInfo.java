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
import java.time.Instant;
import java.time.LocalDate;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Day Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Solar day events for one calendar day at the site, computed from its coordinates (no spatial error). Sun elevation and azimuth per timestep are MeasuredValues of kind SUN_ELEVATION / SUN_AZIMUTH in a COMPUTED dataset.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getCivilDawn <em>Civil Dawn</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getCivilDusk <em>Civil Dusk</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getNauticalDawn <em>Nautical Dawn</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getNauticalDusk <em>Nautical Dusk</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getSolarNoon <em>Solar Noon</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getDayLength <em>Day Length</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getMaxSunElevation <em>Max Sun Elevation</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.DayInfo#getProvenance <em>Provenance</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo()
 * @model
 * @generated
 */
@ProviderType
public interface DayInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(LocalDate)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_Date()
	 * @model dataType="org.gecko.weather.model.weather.LocalDate" required="true"
	 * @generated
	 */
	LocalDate getDate();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(LocalDate value);

	/**
	 * Returns the value of the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunrise</em>' attribute.
	 * @see #setSunrise(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_Sunrise()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getSunrise();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getSunrise <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunrise</em>' attribute.
	 * @see #getSunrise()
	 * @generated
	 */
	void setSunrise(Instant value);

	/**
	 * Returns the value of the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunset</em>' attribute.
	 * @see #setSunset(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_Sunset()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getSunset();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getSunset <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunset</em>' attribute.
	 * @see #getSunset()
	 * @generated
	 */
	void setSunset(Instant value);

	/**
	 * Returns the value of the '<em><b>Civil Dawn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Civil Dawn</em>' attribute.
	 * @see #setCivilDawn(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_CivilDawn()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getCivilDawn();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getCivilDawn <em>Civil Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Civil Dawn</em>' attribute.
	 * @see #getCivilDawn()
	 * @generated
	 */
	void setCivilDawn(Instant value);

	/**
	 * Returns the value of the '<em><b>Civil Dusk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Civil Dusk</em>' attribute.
	 * @see #setCivilDusk(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_CivilDusk()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getCivilDusk();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getCivilDusk <em>Civil Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Civil Dusk</em>' attribute.
	 * @see #getCivilDusk()
	 * @generated
	 */
	void setCivilDusk(Instant value);

	/**
	 * Returns the value of the '<em><b>Nautical Dawn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nautical Dawn</em>' attribute.
	 * @see #setNauticalDawn(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_NauticalDawn()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getNauticalDawn();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getNauticalDawn <em>Nautical Dawn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nautical Dawn</em>' attribute.
	 * @see #getNauticalDawn()
	 * @generated
	 */
	void setNauticalDawn(Instant value);

	/**
	 * Returns the value of the '<em><b>Nautical Dusk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nautical Dusk</em>' attribute.
	 * @see #setNauticalDusk(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_NauticalDusk()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getNauticalDusk();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getNauticalDusk <em>Nautical Dusk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nautical Dusk</em>' attribute.
	 * @see #getNauticalDusk()
	 * @generated
	 */
	void setNauticalDusk(Instant value);

	/**
	 * Returns the value of the '<em><b>Solar Noon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solar Noon</em>' attribute.
	 * @see #setSolarNoon(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_SolarNoon()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getSolarNoon();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getSolarNoon <em>Solar Noon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solar Noon</em>' attribute.
	 * @see #getSolarNoon()
	 * @generated
	 */
	void setSolarNoon(Instant value);

	/**
	 * Returns the value of the '<em><b>Day Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Day Length</em>' attribute.
	 * @see #isSetDayLength()
	 * @see #unsetDayLength()
	 * @see #setDayLength(Duration)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_DayLength()
	 * @model unsettable="true" dataType="org.gecko.weather.model.weather.Duration"
	 * @generated
	 */
	Duration getDayLength();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getDayLength <em>Day Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Length</em>' attribute.
	 * @see #isSetDayLength()
	 * @see #unsetDayLength()
	 * @see #getDayLength()
	 * @generated
	 */
	void setDayLength(Duration value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getDayLength <em>Day Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDayLength()
	 * @see #getDayLength()
	 * @see #setDayLength(Duration)
	 * @generated
	 */
	void unsetDayLength();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.DayInfo#getDayLength <em>Day Length</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Day Length</em>' attribute is set.
	 * @see #unsetDayLength()
	 * @see #getDayLength()
	 * @see #setDayLength(Duration)
	 * @generated
	 */
	boolean isSetDayLength();

	/**
	 * Returns the value of the '<em><b>Max Sun Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sun elevation at solar noon, degrees above the horizon.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Sun Elevation</em>' attribute.
	 * @see #isSetMaxSunElevation()
	 * @see #unsetMaxSunElevation()
	 * @see #setMaxSunElevation(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_MaxSunElevation()
	 * @model unsettable="true"
	 * @generated
	 */
	double getMaxSunElevation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getMaxSunElevation <em>Max Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Sun Elevation</em>' attribute.
	 * @see #isSetMaxSunElevation()
	 * @see #unsetMaxSunElevation()
	 * @see #getMaxSunElevation()
	 * @generated
	 */
	void setMaxSunElevation(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getMaxSunElevation <em>Max Sun Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMaxSunElevation()
	 * @see #getMaxSunElevation()
	 * @see #setMaxSunElevation(double)
	 * @generated
	 */
	void unsetMaxSunElevation();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.DayInfo#getMaxSunElevation <em>Max Sun Elevation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Max Sun Elevation</em>' attribute is set.
	 * @see #unsetMaxSunElevation()
	 * @see #getMaxSunElevation()
	 * @see #setMaxSunElevation(double)
	 * @generated
	 */
	boolean isSetMaxSunElevation();

	/**
	 * Returns the value of the '<em><b>Provenance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provenance</em>' containment reference.
	 * @see #setProvenance(Provenance)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getDayInfo_Provenance()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Provenance getProvenance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.DayInfo#getProvenance <em>Provenance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provenance</em>' containment reference.
	 * @see #getProvenance()
	 * @generated
	 */
	void setProvenance(Provenance value);

} // DayInfo
