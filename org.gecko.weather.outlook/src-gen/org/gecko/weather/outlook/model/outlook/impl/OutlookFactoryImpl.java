/**
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
package org.gecko.weather.outlook.model.outlook.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.weather.outlook.model.outlook.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OutlookFactoryImpl extends EFactoryImpl implements OutlookFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OutlookFactory init() {
		try {
			OutlookFactory theOutlookFactory = (OutlookFactory)EPackage.Registry.INSTANCE.getEFactory(OutlookPackage.eNS_URI);
			if (theOutlookFactory != null) {
				return theOutlookFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OutlookFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlookFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OutlookPackage.SITE_DIRECTORY: return createSiteDirectory();
			case OutlookPackage.SITE_ENTRY: return createSiteEntry();
			case OutlookPackage.OUTLOOK: return createOutlook();
			case OutlookPackage.HOUR_OUTLOOK: return createHourOutlook();
			case OutlookPackage.DAY_OUTLOOK: return createDayOutlook();
			case OutlookPackage.SOURCE_NOTE: return createSourceNote();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SiteDirectory createSiteDirectory() {
		SiteDirectoryImpl siteDirectory = new SiteDirectoryImpl();
		return siteDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SiteEntry createSiteEntry() {
		SiteEntryImpl siteEntry = new SiteEntryImpl();
		return siteEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Outlook createOutlook() {
		OutlookImpl outlook = new OutlookImpl();
		return outlook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HourOutlook createHourOutlook() {
		HourOutlookImpl hourOutlook = new HourOutlookImpl();
		return hourOutlook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DayOutlook createDayOutlook() {
		DayOutlookImpl dayOutlook = new DayOutlookImpl();
		return dayOutlook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SourceNote createSourceNote() {
		SourceNoteImpl sourceNote = new SourceNoteImpl();
		return sourceNote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutlookPackage getOutlookPackage() {
		return (OutlookPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OutlookPackage getPackage() {
		return OutlookPackage.eINSTANCE;
	}

} //OutlookFactoryImpl
