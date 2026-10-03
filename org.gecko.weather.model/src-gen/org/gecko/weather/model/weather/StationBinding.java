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
 * A representation of the model object '<em><b>Station Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.StationBinding#getStation <em>Station</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getStationBinding()
 * @model
 * @generated
 */
@ProviderType
public interface StationBinding extends SourceBinding {
	/**
	 * Returns the value of the '<em><b>Station</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snapshot of the catalogue entry at resolution time, so the site file stands on its own.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Station</em>' containment reference.
	 * @see #setStation(Station)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getStationBinding_Station()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Station getStation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.StationBinding#getStation <em>Station</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Station</em>' containment reference.
	 * @see #getStation()
	 * @generated
	 */
	void setStation(Station value);

} // StationBinding
