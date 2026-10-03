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

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.GridBinding#getCell <em>Cell</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getGridBinding()
 * @model
 * @generated
 */
@ProviderType
public interface GridBinding extends SourceBinding {
	/**
	 * Returns the value of the '<em><b>Cell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell</em>' containment reference.
	 * @see #setCell(GridCell)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getGridBinding_Cell()
	 * @model containment="true" required="true"
	 * @generated
	 */
	GridCell getCell();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.GridBinding#getCell <em>Cell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell</em>' containment reference.
	 * @see #getCell()
	 * @generated
	 */
	void setCell(GridCell value);

} // GridBinding
