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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outlook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The outlook for one site at one moment: hours from the current full hour on, days from tomorrow on, in the site's time zone.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteId <em>Site Id</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteName <em>Site Name</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getTimeZone <em>Time Zone</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getGeneratedAt <em>Generated At</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getHours <em>Hours</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getToday <em>Today</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getDays <em>Days</em>}</li>
 *   <li>{@link org.gecko.weather.outlook.model.outlook.Outlook#getSources <em>Sources</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook()
 * @model
 * @generated
 */
@ProviderType
public interface Outlook extends EObject {
	/**
	 * Returns the value of the '<em><b>Site Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site Id</em>' attribute.
	 * @see #setSiteId(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_SiteId()
	 * @model
	 * @generated
	 */
	String getSiteId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteId <em>Site Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Id</em>' attribute.
	 * @see #getSiteId()
	 * @generated
	 */
	void setSiteId(String value);

	/**
	 * Returns the value of the '<em><b>Site Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Site Name</em>' attribute.
	 * @see #setSiteName(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_SiteName()
	 * @model
	 * @generated
	 */
	String getSiteName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getSiteName <em>Site Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site Name</em>' attribute.
	 * @see #getSiteName()
	 * @generated
	 */
	void setSiteName(String value);

	/**
	 * Returns the value of the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latitude</em>' attribute.
	 * @see #setLatitude(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Latitude()
	 * @model
	 * @generated
	 */
	double getLatitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getLatitude <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latitude</em>' attribute.
	 * @see #getLatitude()
	 * @generated
	 */
	void setLatitude(double value);

	/**
	 * Returns the value of the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longitude</em>' attribute.
	 * @see #setLongitude(double)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Longitude()
	 * @model
	 * @generated
	 */
	double getLongitude();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getLongitude <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Longitude</em>' attribute.
	 * @see #getLongitude()
	 * @generated
	 */
	void setLongitude(double value);

	/**
	 * Returns the value of the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Zone</em>' attribute.
	 * @see #setTimeZone(String)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_TimeZone()
	 * @model
	 * @generated
	 */
	String getTimeZone();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getTimeZone <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Zone</em>' attribute.
	 * @see #getTimeZone()
	 * @generated
	 */
	void setTimeZone(String value);

	/**
	 * Returns the value of the '<em><b>Generated At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated At</em>' attribute.
	 * @see #setGeneratedAt(Date)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_GeneratedAt()
	 * @model
	 * @generated
	 */
	Date getGeneratedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getGeneratedAt <em>Generated At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated At</em>' attribute.
	 * @see #getGeneratedAt()
	 * @generated
	 */
	void setGeneratedAt(Date value);

	/**
	 * Returns the value of the '<em><b>Hours</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.outlook.model.outlook.HourOutlook}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hours</em>' containment reference list.
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Hours()
	 * @model containment="true"
	 * @generated
	 */
	EList<HourOutlook> getHours();

	/**
	 * Returns the value of the '<em><b>Today</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Today in the site's time zone, summarised like the days — mainly for its sun events and UV maximum; temperatures cover only the hours the sources still have.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Today</em>' containment reference.
	 * @see #setToday(DayOutlook)
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Today()
	 * @model containment="true"
	 * @generated
	 */
	DayOutlook getToday();

	/**
	 * Sets the value of the '{@link org.gecko.weather.outlook.model.outlook.Outlook#getToday <em>Today</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Today</em>' containment reference.
	 * @see #getToday()
	 * @generated
	 */
	void setToday(DayOutlook value);

	/**
	 * Returns the value of the '<em><b>Days</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.outlook.model.outlook.DayOutlook}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Days</em>' containment reference list.
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Days()
	 * @model containment="true"
	 * @generated
	 */
	EList<DayOutlook> getDays();

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.outlook.model.outlook.SourceNote}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' containment reference list.
	 * @see org.gecko.weather.outlook.model.outlook.OutlookPackage#getOutlook_Sources()
	 * @model containment="true"
	 * @generated
	 */
	EList<SourceNote> getSources();

} // Outlook
