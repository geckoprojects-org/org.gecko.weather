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
 * A representation of the model object '<em><b>Station Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A provider's station list as retrieved, kept so that binding resolution works offline.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.StationCatalog#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.StationCatalog#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.StationCatalog#getRetrievedAt <em>Retrieved At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.StationCatalog#getStations <em>Stations</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getStationCatalog()
 * @model
 * @generated
 */
@ProviderType
public interface StationCatalog extends EObject {
	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStationCatalog_ProviderId()
	 * @model required="true"
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.StationCatalog#getProviderId <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider Id</em>' attribute.
	 * @see #getProviderId()
	 * @generated
	 */
	void setProviderId(String value);

	/**
	 * Returns the value of the '<em><b>Product Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Id</em>' attribute.
	 * @see #setProductId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStationCatalog_ProductId()
	 * @model required="true"
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.StationCatalog#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retrieved At</em>' attribute.
	 * @see #setRetrievedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStationCatalog_RetrievedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getRetrievedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.StationCatalog#getRetrievedAt <em>Retrieved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retrieved At</em>' attribute.
	 * @see #getRetrievedAt()
	 * @generated
	 */
	void setRetrievedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Stations</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.model.weather.Station}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stations</em>' containment reference list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStationCatalog_Stations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Station> getStations();

} // StationCatalog
