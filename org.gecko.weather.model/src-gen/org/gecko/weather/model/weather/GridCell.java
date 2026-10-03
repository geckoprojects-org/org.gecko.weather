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

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Cell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One cell of a gridded product, addressed by column and row index on the product's grid.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.GridCell#getGridId <em>Grid Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GridCell#getI <em>I</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GridCell#getJ <em>J</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GridCell#getCenter <em>Center</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.GridCell#getResolutionDegrees <em>Resolution Degrees</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell()
 * @model
 * @generated
 */
@ProviderType
public interface GridCell extends EObject {
	/**
	 * Returns the value of the '<em><b>Grid Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the grid definition, e.g. icon-d2-regular-lat-lon or sis-de-v3. Indices are only meaningful together with it.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Grid Id</em>' attribute.
	 * @see #setGridId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell_GridId()
	 * @model required="true"
	 * @generated
	 */
	String getGridId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridCell#getGridId <em>Grid Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Id</em>' attribute.
	 * @see #getGridId()
	 * @generated
	 */
	void setGridId(String value);

	/**
	 * Returns the value of the '<em><b>I</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Column index (longitude direction).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>I</em>' attribute.
	 * @see #setI(int)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell_I()
	 * @model
	 * @generated
	 */
	int getI();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridCell#getI <em>I</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>I</em>' attribute.
	 * @see #getI()
	 * @generated
	 */
	void setI(int value);

	/**
	 * Returns the value of the '<em><b>J</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Row index (latitude direction).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>J</em>' attribute.
	 * @see #setJ(int)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell_J()
	 * @model
	 * @generated
	 */
	int getJ();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridCell#getJ <em>J</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>J</em>' attribute.
	 * @see #getJ()
	 * @generated
	 */
	void setJ(int value);

	/**
	 * Returns the value of the '<em><b>Center</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Center</em>' containment reference.
	 * @see #setCenter(GeoPosition)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell_Center()
	 * @model containment="true"
	 * @generated
	 */
	GeoPosition getCenter();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridCell#getCenter <em>Center</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Center</em>' containment reference.
	 * @see #getCenter()
	 * @generated
	 */
	void setCenter(GeoPosition value);

	/**
	 * Returns the value of the '<em><b>Resolution Degrees</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution Degrees</em>' attribute.
	 * @see #isSetResolutionDegrees()
	 * @see #unsetResolutionDegrees()
	 * @see #setResolutionDegrees(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridCell_ResolutionDegrees()
	 * @model unsettable="true"
	 * @generated
	 */
	double getResolutionDegrees();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridCell#getResolutionDegrees <em>Resolution Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution Degrees</em>' attribute.
	 * @see #isSetResolutionDegrees()
	 * @see #unsetResolutionDegrees()
	 * @see #getResolutionDegrees()
	 * @generated
	 */
	void setResolutionDegrees(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.GridCell#getResolutionDegrees <em>Resolution Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResolutionDegrees()
	 * @see #getResolutionDegrees()
	 * @see #setResolutionDegrees(double)
	 * @generated
	 */
	void unsetResolutionDegrees();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.GridCell#getResolutionDegrees <em>Resolution Degrees</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Resolution Degrees</em>' attribute is set.
	 * @see #unsetResolutionDegrees()
	 * @see #getResolutionDegrees()
	 * @see #setResolutionDegrees(double)
	 * @generated
	 */
	boolean isSetResolutionDegrees();

} // GridCell
