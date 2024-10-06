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
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getStation <em>Station</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getAstrotime <em>Astrotime</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getWeatherStation <em>Weather Station</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport()
 * @model
 * @generated
 */
@ProviderType
public interface WeatherReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(Date)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Timestamp()
	 * @model required="true"
	 * @generated
	 */
	Date getTimestamp();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(Date value);

	/**
	 * Returns the value of the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Station the measurments are for. This is usually the exact location the report belongs to
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Station</em>' containment reference.
	 * @see #setStation(Station)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Station()
	 * @model containment="true" keys="name" required="true"
	 * @generated
	 */
	Station getStation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getStation <em>Station</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Station</em>' containment reference.
	 * @see #getStation()
	 * @generated
	 */
	void setStation(Station value);

	/**
	 * Returns the value of the '<em><b>Astrotime</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Additional astro time information for this report, like sunset und sunrise times for the report day
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Astrotime</em>' containment reference.
	 * @see #setAstrotime(Astrotime)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Astrotime()
	 * @model containment="true"
	 * @generated
	 */
	Astrotime getAstrotime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getAstrotime <em>Astrotime</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Astrotime</em>' containment reference.
	 * @see #getAstrotime()
	 * @generated
	 */
	void setAstrotime(Astrotime value);

	/**
	 * Returns the value of the '<em><b>Weather Station</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The next official (DWD) weather station closest to the 'station'. This is the official weather station most measurement com from
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Weather Station</em>' reference.
	 * @see #setWeatherStation(WeatherStation)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_WeatherStation()
	 * @model required="true"
	 * @generated
	 */
	WeatherStation getWeatherStation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getWeatherStation <em>Weather Station</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Station</em>' reference.
	 * @see #getWeatherStation()
	 * @generated
	 */
	void setWeatherStation(WeatherStation value);

} // WeatherReport
