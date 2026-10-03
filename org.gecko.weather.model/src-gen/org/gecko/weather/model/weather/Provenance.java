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

import java.time.Instant;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provenance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Where a value came from: provider, product, model run and issue time, the station or grid cell it was read at and how far that is from the site. Deliberately repeated per value — a value must be interpretable without its dataset.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getModelRun <em>Model Run</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getIssuedAt <em>Issued At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getRetrievedAt <em>Retrieved At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getStationId <em>Station Id</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getCell <em>Cell</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getDistanceMeters <em>Distance Meters</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getLicence <em>Licence</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getAttribution <em>Attribution</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.Provenance#getDerivation <em>Derivation</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance()
 * @model
 * @generated
 */
@ProviderType
public interface Provenance extends EObject {
	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_ProviderId()
	 * @model required="true"
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getProviderId <em>Provider Id</em>}' attribute.
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
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_ProductId()
	 * @model required="true"
	 * @generated
	 */
	String getProductId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getProductId <em>Product Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Id</em>' attribute.
	 * @see #getProductId()
	 * @generated
	 */
	void setProductId(String value);

	/**
	 * Returns the value of the '<em><b>Source Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The source's own element name the value was mapped from, e.g. TTT or clct. Kept for traceability only; consumers use kind.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Element</em>' attribute.
	 * @see #setSourceElement(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_SourceElement()
	 * @model
	 * @generated
	 */
	String getSourceElement();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getSourceElement <em>Source Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Element</em>' attribute.
	 * @see #getSourceElement()
	 * @generated
	 */
	void setSourceElement(String value);

	/**
	 * Returns the value of the '<em><b>Model Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference time of the model run that produced the value, where the product is model-based.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Run</em>' attribute.
	 * @see #setModelRun(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_ModelRun()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getModelRun();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getModelRun <em>Model Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Run</em>' attribute.
	 * @see #getModelRun()
	 * @generated
	 */
	void setModelRun(Instant value);

	/**
	 * Returns the value of the '<em><b>Issued At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the source published this value. Two values for the same validAt from the same product differ in issuedAt; the newer one is presumably the better forecast.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Issued At</em>' attribute.
	 * @see #setIssuedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_IssuedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant" required="true"
	 * @generated
	 */
	Instant getIssuedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getIssuedAt <em>Issued At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issued At</em>' attribute.
	 * @see #getIssuedAt()
	 * @generated
	 */
	void setIssuedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Retrieved At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retrieved At</em>' attribute.
	 * @see #setRetrievedAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_RetrievedAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant"
	 * @generated
	 */
	Instant getRetrievedAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getRetrievedAt <em>Retrieved At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retrieved At</em>' attribute.
	 * @see #getRetrievedAt()
	 * @generated
	 */
	void setRetrievedAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.Origin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' attribute.
	 * @see org.gecko.weather.model.weather.Origin
	 * @see #setOrigin(Origin)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_Origin()
	 * @model required="true"
	 * @generated
	 */
	Origin getOrigin();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getOrigin <em>Origin</em>}' attribute.
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
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_StationId()
	 * @model
	 * @generated
	 */
	String getStationId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getStationId <em>Station Id</em>}' attribute.
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
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_Cell()
	 * @model containment="true"
	 * @generated
	 */
	GridCell getCell();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getCell <em>Cell</em>}' containment reference.
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
	 * <!-- begin-model-doc -->
	 * Distance from the site to the station or cell centre. Zero for COMPUTED values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Distance Meters</em>' attribute.
	 * @see #isSetDistanceMeters()
	 * @see #unsetDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_DistanceMeters()
	 * @model unsettable="true"
	 * @generated
	 */
	double getDistanceMeters();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getDistanceMeters <em>Distance Meters</em>}' attribute.
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
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.Provenance#getDistanceMeters <em>Distance Meters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDistanceMeters()
	 * @see #getDistanceMeters()
	 * @see #setDistanceMeters(double)
	 * @generated
	 */
	void unsetDistanceMeters();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.Provenance#getDistanceMeters <em>Distance Meters</em>}' attribute is set.
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
	 * <!-- begin-model-doc -->
	 * Licence identifier of the source data, e.g. DL-DE-BY-2.0, so downstream consumers can meet their obligations.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Licence</em>' attribute.
	 * @see #setLicence(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_Licence()
	 * @model
	 * @generated
	 */
	String getLicence();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getLicence <em>Licence</em>}' attribute.
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
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_Attribution()
	 * @model
	 * @generated
	 */
	String getAttribution();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getAttribution <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribution</em>' attribute.
	 * @see #getAttribution()
	 * @generated
	 */
	void setAttribution(String value);

	/**
	 * Returns the value of the '<em><b>Derivation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Present for COMPUTED values: which function produced it from which inputs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Derivation</em>' containment reference.
	 * @see #setDerivation(Derivation)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getProvenance_Derivation()
	 * @model containment="true"
	 * @generated
	 */
	Derivation getDerivation();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.Provenance#getDerivation <em>Derivation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derivation</em>' containment reference.
	 * @see #getDerivation()
	 * @generated
	 */
	void setDerivation(Derivation value);

} // Provenance
