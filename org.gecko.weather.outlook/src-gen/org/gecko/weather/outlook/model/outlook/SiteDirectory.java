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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Site Directory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The sites an outlook can be asked for.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.SiteDirectory#getSites <em>Sites</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSiteDirectory()
 * @model
 * @generated
 */
@ProviderType
public interface SiteDirectory extends EObject {
	/**
	 * Returns the value of the '<em><b>Sites</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.outlook.model.outlook.SiteEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sites</em>' containment reference list.
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getSiteDirectory_Sites()
	 * @model containment="true"
	 * @generated
	 */
	EList<SiteEntry> getSites();

} // SiteDirectory
