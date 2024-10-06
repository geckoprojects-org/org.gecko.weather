/*
 * Copyright (c) 2012 - 2024 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Astrotime</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Additional astro time information, like sunset und sunrise times  for a certain day
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Astrotime#getSunset <em>Sunset</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Astrotime#getSunrise <em>Sunrise</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Astrotime#getSunsetTwilight <em>Sunset Twilight</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Astrotime#getSunriseTwilight <em>Sunrise Twilight</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getAstrotime()
 * @model
 * @generated
 */
@ProviderType
public interface Astrotime extends EObject {
	/**
	 * Returns the value of the '<em><b>Sunset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunset</em>' attribute.
	 * @see #setSunset(Date)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getAstrotime_Sunset()
	 * @model
	 * @generated
	 */
	Date getSunset();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Astrotime#getSunset <em>Sunset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunset</em>' attribute.
	 * @see #getSunset()
	 * @generated
	 */
	void setSunset(Date value);

	/**
	 * Returns the value of the '<em><b>Sunrise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sunrise</em>' attribute.
	 * @see #setSunrise(Date)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getAstrotime_Sunrise()
	 * @model
	 * @generated
	 */
	Date getSunrise();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Astrotime#getSunrise <em>Sunrise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunrise</em>' attribute.
	 * @see #getSunrise()
	 * @generated
	 */
	void setSunrise(Date value);

	/**
	 * Returns the value of the '<em><b>Sunset Twilight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Times for the civil twilight, means 6 degrees below the horizon
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sunset Twilight</em>' attribute.
	 * @see #setSunsetTwilight(Date)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getAstrotime_SunsetTwilight()
	 * @model
	 * @generated
	 */
	Date getSunsetTwilight();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Astrotime#getSunsetTwilight <em>Sunset Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunset Twilight</em>' attribute.
	 * @see #getSunsetTwilight()
	 * @generated
	 */
	void setSunsetTwilight(Date value);

	/**
	 * Returns the value of the '<em><b>Sunrise Twilight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Times for the civil twilight, means 6 degrees below the horizon
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sunrise Twilight</em>' attribute.
	 * @see #setSunriseTwilight(Date)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getAstrotime_SunriseTwilight()
	 * @model
	 * @generated
	 */
	Date getSunriseTwilight();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Astrotime#getSunriseTwilight <em>Sunrise Twilight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunrise Twilight</em>' attribute.
	 * @see #getSunriseTwilight()
	 * @generated
	 */
	void setSunriseTwilight(Date value);

} // Astrotime
