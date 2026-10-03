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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.SourceEntity#getUri <em>Uri</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceEntity#getEtag <em>Etag</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceEntity#getLastModified <em>Last Modified</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceEntity()
 * @model
 * @generated
 */
@ProviderType
public interface SourceEntity extends EObject {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceEntity_Uri()
	 * @model required="true"
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceEntity#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Etag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Etag</em>' attribute.
	 * @see #setEtag(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceEntity_Etag()
	 * @model
	 * @generated
	 */
	String getEtag();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceEntity#getEtag <em>Etag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Etag</em>' attribute.
	 * @see #getEtag()
	 * @generated
	 */
	void setEtag(String value);

	/**
	 * Returns the value of the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Modified</em>' attribute.
	 * @see #setLastModified(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceEntity_LastModified()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getLastModified();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceEntity#getLastModified <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Modified</em>' attribute.
	 * @see #getLastModified()
	 * @generated
	 */
	void setLastModified(Instant value);

} // SourceEntity
