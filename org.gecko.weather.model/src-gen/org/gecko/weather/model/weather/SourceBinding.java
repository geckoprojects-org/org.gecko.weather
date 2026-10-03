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
 * A representation of the model object '<em><b>Source Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Links a site to the concrete source location a product is read at: a station or a grid cell. Resolved automatically (nearest) or assigned manually; the manual assignment overrides.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getRank <em>Rank</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters <em>Elevation Delta Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceBinding#getResolvedAt <em>Resolved At</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding()
 * @model abstract="true"
 * @generated
 */
@ProviderType
public interface SourceBinding extends EObject {
	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provider identifier, e.g. dwd.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_ProviderId()
	 * @model required="true"
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getProviderId <em>Provider Id</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Product identifier within the provider, e.g. MOSMIX_L, ICON-D2, SIS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Product Id</em>' attribute.
	 * @see #setProductId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_ProductId()
	 * @model required="true"
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.BindingOrigin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' attribute.
	 * @see org.gecko.weather.model.weather.BindingOrigin
	 * @see #setOrigin(BindingOrigin)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_Origin()
	 * @model required="true"
	 * @generated
	 */
	BindingOrigin getOrigin();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getOrigin <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' attribute.
	 * @see org.gecko.weather.model.weather.BindingOrigin
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(BindingOrigin value);

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 0 is the primary binding for the product; higher ranks are alternates (e.g. second-nearest station).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_Rank()
	 * @model
	 * @generated
	 */
	int getRank();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 * @generated
	 */
	void setRank(int value);

	/**
	 * Returns the value of the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Great-circle distance from the site to the station or cell centre.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Distance Meters</em>' attribute.
	 * @see #setDistanceMeters(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_DistanceMeters()
	 * @model
	 * @generated
	 */
	double getDistanceMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getDistanceMeters <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distance Meters</em>' attribute.
	 * @see #getDistanceMeters()
	 * @generated
	 */
	void setDistanceMeters(double value);

	/**
	 * Returns the value of the '<em><b>Elevation Delta Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Source elevation minus site elevation, where both are known.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elevation Delta Meters</em>' attribute.
	 * @see #isSetElevationDeltaMeters()
	 * @see #unsetElevationDeltaMeters()
	 * @see #setElevationDeltaMeters(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_ElevationDeltaMeters()
	 * @model unsettable="true"
	 * @generated
	 */
	double getElevationDeltaMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters <em>Elevation Delta Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation Delta Meters</em>' attribute.
	 * @see #isSetElevationDeltaMeters()
	 * @see #unsetElevationDeltaMeters()
	 * @see #getElevationDeltaMeters()
	 * @generated
	 */
	void setElevationDeltaMeters(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters <em>Elevation Delta Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetElevationDeltaMeters()
	 * @see #getElevationDeltaMeters()
	 * @see #setElevationDeltaMeters(double)
	 * @generated
	 */
	void unsetElevationDeltaMeters();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getElevationDeltaMeters <em>Elevation Delta Meters</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Elevation Delta Meters</em>' attribute is set.
	 * @see #unsetElevationDeltaMeters()
	 * @see #getElevationDeltaMeters()
	 * @see #setElevationDeltaMeters(double)
	 * @generated
	 */
	boolean isSetElevationDeltaMeters();

	/**
	 * Returns the value of the '<em><b>Resolved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolved At</em>' attribute.
	 * @see #setResolvedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceBinding_ResolvedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getResolvedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceBinding#getResolvedAt <em>Resolved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolved At</em>' attribute.
	 * @see #getResolvedAt()
	 * @generated
	 */
	void setResolvedAt(Instant value);

} // SourceBinding
