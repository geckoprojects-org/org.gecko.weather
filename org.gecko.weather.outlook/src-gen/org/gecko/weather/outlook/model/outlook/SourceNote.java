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
package org.gecko.weather.outlook.model.outlook;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Note</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Where the outlook took a group of quantities from — one note per dataset used.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getQuantities <em>Quantities</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getLocation <em>Location</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SourceNote#getIssuedAt <em>Issued At</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote()
 * @model
 * @generated
 */
@ProviderType
public interface SourceNote extends EObject {
	/**
	 * Returns the value of the '<em><b>Quantities</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantities</em>' attribute.
	 * @see #setQuantities(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_Quantities()
	 * @model
	 * @generated
	 */
	String getQuantities();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getQuantities <em>Quantities</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantities</em>' attribute.
	 * @see #getQuantities()
	 * @generated
	 */
	void setQuantities(String value);

	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_ProviderId()
	 * @model
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProviderId <em>Provider Id</em>}' attribute.
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
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_ProductId()
	 * @model
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distance Meters</em>' attribute.
	 * @see #isSetDistanceMeters()
	 * @see #unsetDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_DistanceMeters()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDistanceMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distance Meters</em>' attribute.
	 * @see #isSetDistanceMeters()
	 * @see #unsetDistanceMeters()
	 * @see #getDistanceMeters()
	 * @generated
	 */
	void setDistanceMeters(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDistanceMeters()
	 * @see #getDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @generated
	 */
	void unsetDistanceMeters();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getDistanceMeters <em>Distance Meters</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Distance Meters</em>' attribute is set.
	 * @see #unsetDistanceMeters()
	 * @see #getDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @generated
	 */
	boolean isSetDistanceMeters();

	/**
	 * Returns the value of the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issued At</em>' attribute.
	 * @see #setIssuedAt(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSourceNote_IssuedAt()
	 * @model
	 * @generated
	 */
	Date getIssuedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.SourceNote#getIssuedAt <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issued At</em>' attribute.
	 * @see #getIssuedAt()
	 * @generated
	 */
	void setIssuedAt(Date value);

} // SourceNote
