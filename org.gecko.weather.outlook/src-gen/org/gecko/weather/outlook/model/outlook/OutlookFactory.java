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

import org.eclipse.emf.ecore.EFactory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage
 * @generated
 */
@ProviderType
public interface OutlookFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OutlookFactory eINSTANCE = org.gecko.weather.outlook.model.outlook.impl.OutlookFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Site Directory</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site Directory</em>'.
	 * @generated
	 */
	SiteDirectory createSiteDirectory();

	/**
	 * Returns a new object of class '<em>Site Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site Entry</em>'.
	 * @generated
	 */
	SiteEntry createSiteEntry();

	/**
	 * Returns a new object of class '<em>Outlook</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outlook</em>'.
	 * @generated
	 */
	Outlook createOutlook();

	/**
	 * Returns a new object of class '<em>Hour Outlook</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hour Outlook</em>'.
	 * @generated
	 */
	HourOutlook createHourOutlook();

	/**
	 * Returns a new object of class '<em>Day Outlook</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Day Outlook</em>'.
	 * @generated
	 */
	DayOutlook createDayOutlook();

	/**
	 * Returns a new object of class '<em>Source Note</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source Note</em>'.
	 * @generated
	 */
	SourceNote createSourceNote();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OutlookPackage getOutlookPackage();

} //OutlookFactory
