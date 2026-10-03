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

import org.eclipse.emf.ecore.EFactory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage
 * @generated
 */
@ProviderType
public interface WeatherFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WeatherFactory eINSTANCE = org.gecko.weather.model.weather.impl.WeatherFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Geo Position</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geo Position</em>'.
	 * @generated
	 */
	GeoPosition createGeoPosition();

	/**
	 * Returns a new object of class '<em>Site</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site</em>'.
	 * @generated
	 */
	Site createSite();

	/**
	 * Returns a new object of class '<em>Station Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Station Binding</em>'.
	 * @generated
	 */
	StationBinding createStationBinding();

	/**
	 * Returns a new object of class '<em>Grid Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Binding</em>'.
	 * @generated
	 */
	GridBinding createGridBinding();

	/**
	 * Returns a new object of class '<em>Station</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Station</em>'.
	 * @generated
	 */
	Station createStation();

	/**
	 * Returns a new object of class '<em>Station Catalog</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Station Catalog</em>'.
	 * @generated
	 */
	StationCatalog createStationCatalog();

	/**
	 * Returns a new object of class '<em>Grid Cell</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Cell</em>'.
	 * @generated
	 */
	GridCell createGridCell();

	/**
	 * Returns a new object of class '<em>Measured Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measured Value</em>'.
	 * @generated
	 */
	MeasuredValue createMeasuredValue();

	/**
	 * Returns a new object of class '<em>Provenance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provenance</em>'.
	 * @generated
	 */
	Provenance createProvenance();

	/**
	 * Returns a new object of class '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derivation</em>'.
	 * @generated
	 */
	Derivation createDerivation();

	/**
	 * Returns a new object of class '<em>Uncertainty</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uncertainty</em>'.
	 * @generated
	 */
	Uncertainty createUncertainty();

	/**
	 * Returns a new object of class '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	WeatherReport createWeatherReport();

	/**
	 * Returns a new object of class '<em>Source Dataset</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source Dataset</em>'.
	 * @generated
	 */
	SourceDataset createSourceDataset();

	/**
	 * Returns a new object of class '<em>Day Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Day Info</em>'.
	 * @generated
	 */
	DayInfo createDayInfo();

	/**
	 * Returns a new object of class '<em>Source State Record</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source State Record</em>'.
	 * @generated
	 */
	SourceStateRecord createSourceStateRecord();

	/**
	 * Returns a new object of class '<em>Source Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source Entity</em>'.
	 * @generated
	 */
	SourceEntity createSourceEntity();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WeatherPackage getWeatherPackage();

} //WeatherFactory
