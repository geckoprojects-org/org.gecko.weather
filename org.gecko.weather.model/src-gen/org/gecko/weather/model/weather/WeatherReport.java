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

import java.time.Instant;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Everything currently known for one site: one SourceDataset per source product, each replaced whole when the source publishes anew, plus solar day events. Consumers read values across datasets per kind and validAt and decide themselves how to combine them.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getDatasets <em>Datasets</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.WeatherReport#getDays <em>Days</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport()
 * @model
 * @generated
 */
@ProviderType
public interface WeatherReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site Id</em>' attribute.
	 * @see #setSiteId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_SiteId()
	 * @model required="true"
	 * @generated
	 */
	String getSiteId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getSiteId <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Id</em>' attribute.
	 * @see #getSiteId()
	 * @generated
	 */
	void setSiteId(String value);

	/**
	 * Returns the value of the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When this report instance was last written.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Generated At</em>' attribute.
	 * @see #setGeneratedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_GeneratedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant" required="true"
	 * @generated
	 */
	Instant getGeneratedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.WeatherReport#getGeneratedAt <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated At</em>' attribute.
	 * @see #getGeneratedAt()
	 * @generated
	 */
	void setGeneratedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Datasets</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.model.weather.SourceDataset}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Datasets</em>' containment reference list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Datasets()
	 * @model containment="true"
	 * @generated
	 */
	EList<SourceDataset> getDatasets();

	/**
	 * Returns the value of the '<em><b>Days</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.model.weather.DayInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Days</em>' containment reference list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getWeatherReport_Days()
	 * @model containment="true"
	 * @generated
	 */
	EList<DayInfo> getDays();

} // WeatherReport
