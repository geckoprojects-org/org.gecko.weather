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

import java.time.Duration;
import java.time.Instant;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Dataset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The values one source product currently provides for the site, from one issue. Products refresh on their own cadence; a refresh replaces the dataset of that product and nothing else. The superseded dataset is archived by the repository, not kept here.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getIssuedAt <em>Issued At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getModelRun <em>Model Run</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getRetrievedAt <em>Retrieved At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh <em>Expected Refresh</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getHorizonStart <em>Horizon Start</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getHorizonEnd <em>Horizon End</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getStationId <em>Station Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getCell <em>Cell</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getLicence <em>Licence</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getAttribution <em>Attribution</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.SourceDataset#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset()
 * @model
 * @generated
 */
@ProviderType
public interface SourceDataset extends EObject {
	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_ProviderId()
	 * @model required="true"
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getProviderId <em>Provider Id</em>}' attribute.
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
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_ProductId()
	 * @model required="true"
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issued At</em>' attribute.
	 * @see #setIssuedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_IssuedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant" required="true"
	 * @generated
	 */
	Instant getIssuedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getIssuedAt <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issued At</em>' attribute.
	 * @see #getIssuedAt()
	 * @generated
	 */
	void setIssuedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Model Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Run</em>' attribute.
	 * @see #setModelRun(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_ModelRun()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getModelRun();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getModelRun <em>Model Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Run</em>' attribute.
	 * @see #getModelRun()
	 * @generated
	 */
	void setModelRun(Instant value);

	/**
	 * Returns the value of the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retrieved At</em>' attribute.
	 * @see #setRetrievedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_RetrievedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getRetrievedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getRetrievedAt <em>Retrieved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retrieved At</em>' attribute.
	 * @see #getRetrievedAt()
	 * @generated
	 */
	void setRetrievedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Expected Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The product's publication interval (MOSMIX_S PT1H, MOSMIX_L PT6H, ICON-D2 PT3H, SIS PT15M). Lets a consumer judge staleness without knowing the product.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expected Refresh</em>' attribute.
	 * @see #isSetExpectedRefresh()
	 * @see #unsetExpectedRefresh()
	 * @see #setExpectedRefresh(Duration)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_ExpectedRefresh()
	 * @model unsettable="true" dataType="org.gecko.weather.model.weather.Duration"
	 * @generated
	 */
	Duration getExpectedRefresh();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh <em>Expected Refresh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expected Refresh</em>' attribute.
	 * @see #isSetExpectedRefresh()
	 * @see #unsetExpectedRefresh()
	 * @see #getExpectedRefresh()
	 * @generated
	 */
	void setExpectedRefresh(Duration value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh <em>Expected Refresh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExpectedRefresh()
	 * @see #getExpectedRefresh()
	 * @see #setExpectedRefresh(Duration)
	 * @generated
	 */
	void unsetExpectedRefresh();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getExpectedRefresh <em>Expected Refresh</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Expected Refresh</em>' attribute is set.
	 * @see #unsetExpectedRefresh()
	 * @see #getExpectedRefresh()
	 * @see #setExpectedRefresh(Duration)
	 * @generated
	 */
	boolean isSetExpectedRefresh();

	/**
	 * Returns the value of the '<em><b>Horizon Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Horizon Start</em>' attribute.
	 * @see #setHorizonStart(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_HorizonStart()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getHorizonStart();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getHorizonStart <em>Horizon Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Horizon Start</em>' attribute.
	 * @see #getHorizonStart()
	 * @generated
	 */
	void setHorizonStart(Instant value);

	/**
	 * Returns the value of the '<em><b>Horizon End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Horizon End</em>' attribute.
	 * @see #setHorizonEnd(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_HorizonEnd()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getHorizonEnd();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getHorizonEnd <em>Horizon End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Horizon End</em>' attribute.
	 * @see #getHorizonEnd()
	 * @generated
	 */
	void setHorizonEnd(Instant value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.Origin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' attribute.
	 * @see org.gecko.weather.model.weather.Origin
	 * @see #setOrigin(Origin)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_Origin()
	 * @model required="true"
	 * @generated
	 */
	Origin getOrigin();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getOrigin <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' attribute.
	 * @see org.gecko.weather.model.weather.Origin
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(Origin value);

	/**
	 * Returns the value of the '<em><b>Station Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Station Id</em>' attribute.
	 * @see #setStationId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_StationId()
	 * @model
	 * @generated
	 */
	String getStationId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getStationId <em>Station Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Station Id</em>' attribute.
	 * @see #getStationId()
	 * @generated
	 */
	void setStationId(String value);

	/**
	 * Returns the value of the '<em><b>Cell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell</em>' containment reference.
	 * @see #setCell(GridCell)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_Cell()
	 * @model containment="true"
	 * @generated
	 */
	GridCell getCell();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getCell <em>Cell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell</em>' containment reference.
	 * @see #getCell()
	 * @generated
	 */
	void setCell(GridCell value);

	/**
	 * Returns the value of the '<em><b>Distance Meters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distance Meters</em>' attribute.
	 * @see #isSetDistanceMeters()
	 * @see #unsetDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_DistanceMeters()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDistanceMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getDistanceMeters <em>Distance Meters</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getDistanceMeters <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDistanceMeters()
	 * @see #getDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @generated
	 */
	void unsetDistanceMeters();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getDistanceMeters <em>Distance Meters</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Licence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Licence</em>' attribute.
	 * @see #setLicence(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_Licence()
	 * @model
	 * @generated
	 */
	String getLicence();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getLicence <em>Licence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Licence</em>' attribute.
	 * @see #getLicence()
	 * @generated
	 */
	void setLicence(String value);

	/**
	 * Returns the value of the '<em><b>Attribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribution</em>' attribute.
	 * @see #setAttribution(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_Attribution()
	 * @model
	 * @generated
	 */
	String getAttribution();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.SourceDataset#getAttribution <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribution</em>' attribute.
	 * @see #getAttribution()
	 * @generated
	 */
	void setAttribution(String value);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.weather.model.weather.MeasuredValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.gecko.weather.model.weather.WeatherPackage#getSourceDataset_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<MeasuredValue> getValues();

} // SourceDataset
