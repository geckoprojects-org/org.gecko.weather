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
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A registered location of interest — the central entity. Reports are requested by its id. Registering a site resolves and persists its source bindings; those bindings tell ingest what to fetch for it.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Site#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getPosition <em>Position</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getRegisteredAt <em>Registered At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#isActive <em>Active</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getDataCompleteFrom <em>Data Complete From</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Site#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getSite()
 * @model
 * @generated
 */
@ProviderType
public interface Site extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stable identifier assigned at registration. Used as the file name of the persisted site and report.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' containment reference.
	 * @see #setPosition(GeoPosition)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Position()
	 * @model containment="true" required="true"
	 * @generated
	 */
	GeoPosition getPosition();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getPosition <em>Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' containment reference.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(GeoPosition value);

	/**
	 * Returns the value of the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * IANA zone id, e.g. Europe/Berlin. Defines day boundaries for DayInfo. Resolved from the coordinates when not given explicitly.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Zone</em>' attribute.
	 * @see #setTimeZone(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_TimeZone()
	 * @model
	 * @generated
	 */
	String getTimeZone();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getTimeZone <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Zone</em>' attribute.
	 * @see #getTimeZone()
	 * @generated
	 */
	void setTimeZone(String value);

	/**
	 * Returns the value of the '<em><b>Registered At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Registered At</em>' attribute.
	 * @see #setRegisteredAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_RegisteredAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getRegisteredAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getRegisteredAt <em>Registered At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Registered At</em>' attribute.
	 * @see #getRegisteredAt()
	 * @generated
	 */
	void setRegisteredAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Inactive sites are no longer supplied by ingest but keep their stored data.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Active</em>' attribute.
	 * @see #setActive(boolean)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Active()
	 * @model default="true"
	 * @generated
	 */
	boolean isActive();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#isActive <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
	void setActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Data Complete From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Instant from which the site's data is actually complete. A site added later has no history before this (subset-on-ingest).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Complete From</em>' attribute.
	 * @see #setDataCompleteFrom(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_DataCompleteFrom()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getDataCompleteFrom();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Site#getDataCompleteFrom <em>Data Complete From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Complete From</em>' attribute.
	 * @see #getDataCompleteFrom()
	 * @generated
	 */
	void setDataCompleteFrom(Instant value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Extensible project-specific data without a model change — e.g. panel tilt and azimuth for a later plane-of-array module.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Attributes()
	 * @model mapType="org.gecko.weather.model.weather.SiteAttribute&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getAttributes();

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.model.weather.SourceBinding}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The stations and grid cells this site reads from, one binding per source product (several where a product allows more than one station).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSite_Bindings()
	 * @model containment="true"
	 * @generated
	 */
	EList<SourceBinding> getBindings();

} // Site
