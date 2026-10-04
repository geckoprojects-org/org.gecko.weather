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
package org.gecko.weather.pv.model.pv;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plant Directory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The plants a forecast can be asked for.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.PlantDirectory#getPlants <em>Plants</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantDirectory()
 * @model
 * @generated
 */
@ProviderType
public interface PlantDirectory extends EObject {
	/**
	 * Returns the value of the '<em><b>Plants</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.pv.model.pv.PlantEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plants</em>' containment reference list.
	 * @see org.gecko.weather.pv.model.pv.PvPackage#getPlantDirectory_Plants()
	 * @model containment="true"
	 * @generated
	 */
	EList<PlantEntry> getPlants();

} // PlantDirectory
