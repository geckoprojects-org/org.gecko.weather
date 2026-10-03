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
package org.gecko.weather.model.weather.impl;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gecko.weather.model.weather.BindingOrigin;
import org.gecko.weather.model.weather.DayInfo;
import org.gecko.weather.model.weather.Derivation;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.GridCell;
import org.gecko.weather.model.weather.Level;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.Origin;
import org.gecko.weather.model.weather.Provenance;
import org.gecko.weather.model.weather.Quality;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.Statistic;
import org.gecko.weather.model.weather.Uncertainty;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WeatherPackageImpl extends EPackageImpl implements WeatherPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siteAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stationBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gridBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stationCatalogEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gridCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measuredValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass provenanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derivationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uncertaintyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weatherReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceDatasetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dayInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingOriginEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum measurementKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum levelEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum statisticEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum originEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum qualityEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType instantEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType localDateEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.gecko.weather.model.weather.WeatherPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WeatherPackageImpl() {
		super(eNS_URI, WeatherFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link WeatherPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WeatherPackage init() {
		if (isInited) return (WeatherPackage)EPackage.Registry.INSTANCE.getEPackage(WeatherPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredWeatherPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		WeatherPackageImpl theWeatherPackage = registeredWeatherPackage instanceof WeatherPackageImpl ? (WeatherPackageImpl)registeredWeatherPackage : new WeatherPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theWeatherPackage.createPackageContents();

		// Initialize created meta-data
		theWeatherPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWeatherPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WeatherPackage.eNS_URI, theWeatherPackage);
		return theWeatherPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGeoPosition() {
		return geoPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Latitude() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Longitude() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGeoPosition_Elevation() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSite() {
		return siteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_Id() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_Name() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSite_Position() {
		return (EReference)siteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_TimeZone() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_RegisteredAt() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_Active() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSite_DataCompleteFrom() {
		return (EAttribute)siteEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSite_Attributes() {
		return (EReference)siteEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSite_Bindings() {
		return (EReference)siteEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSiteAttribute() {
		return siteAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteAttribute_Key() {
		return (EAttribute)siteAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSiteAttribute_Value() {
		return (EAttribute)siteAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSourceBinding() {
		return sourceBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_ProviderId() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_ProductId() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_Origin() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_Rank() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_DistanceMeters() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_ElevationDeltaMeters() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceBinding_ResolvedAt() {
		return (EAttribute)sourceBindingEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStationBinding() {
		return stationBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStationBinding_Station() {
		return (EReference)stationBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGridBinding() {
		return gridBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGridBinding_Cell() {
		return (EReference)gridBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStation() {
		return stationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Id() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Name() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_IcaoCode() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStation_Country() {
		return (EAttribute)stationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStation_Position() {
		return (EReference)stationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStationCatalog() {
		return stationCatalogEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStationCatalog_ProviderId() {
		return (EAttribute)stationCatalogEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStationCatalog_ProductId() {
		return (EAttribute)stationCatalogEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStationCatalog_RetrievedAt() {
		return (EAttribute)stationCatalogEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStationCatalog_Stations() {
		return (EReference)stationCatalogEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGridCell() {
		return gridCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGridCell_GridId() {
		return (EAttribute)gridCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGridCell_I() {
		return (EAttribute)gridCellEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGridCell_J() {
		return (EAttribute)gridCellEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGridCell_Center() {
		return (EReference)gridCellEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGridCell_ResolutionDegrees() {
		return (EAttribute)gridCellEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMeasuredValue() {
		return measuredValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Kind() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Level() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Statistic() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Period() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Threshold() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_ThresholdUnit() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_ValidAt() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Value() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Code() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMeasuredValue_Unit() {
		return (EAttribute)measuredValueEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMeasuredValue_Provenance() {
		return (EReference)measuredValueEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMeasuredValue_Uncertainty() {
		return (EReference)measuredValueEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProvenance() {
		return provenanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_ProviderId() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_ProductId() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_SourceElement() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_ModelRun() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_IssuedAt() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_RetrievedAt() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_Origin() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_StationId() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProvenance_Cell() {
		return (EReference)provenanceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_DistanceMeters() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_Licence() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProvenance_Attribution() {
		return (EAttribute)provenanceEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProvenance_Derivation() {
		return (EReference)provenanceEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDerivation() {
		return derivationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDerivation_FunctionId() {
		return (EAttribute)derivationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDerivation_Inputs() {
		return (EAttribute)derivationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUncertainty() {
		return uncertaintyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_Quality() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_SpatialMeters() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_TemporalOffset() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_LeadTime() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_Stale() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUncertainty_Note() {
		return (EAttribute)uncertaintyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWeatherReport() {
		return weatherReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReport_SiteId() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWeatherReport_GeneratedAt() {
		return (EAttribute)weatherReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_Datasets() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWeatherReport_Days() {
		return (EReference)weatherReportEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSourceDataset() {
		return sourceDatasetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_ProviderId() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_ProductId() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_IssuedAt() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_ModelRun() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_RetrievedAt() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_ExpectedRefresh() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_HorizonStart() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_HorizonEnd() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_Origin() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_StationId() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSourceDataset_Cell() {
		return (EReference)sourceDatasetEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_DistanceMeters() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_Licence() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSourceDataset_Attribution() {
		return (EAttribute)sourceDatasetEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSourceDataset_Values() {
		return (EReference)sourceDatasetEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDayInfo() {
		return dayInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_Date() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_Sunrise() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_Sunset() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_CivilDawn() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_CivilDusk() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_NauticalDawn() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_NauticalDusk() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_SolarNoon() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_DayLength() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDayInfo_MaxSunElevation() {
		return (EAttribute)dayInfoEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDayInfo_Provenance() {
		return (EReference)dayInfoEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBindingOrigin() {
		return bindingOriginEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getMeasurementKind() {
		return measurementKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getLevel() {
		return levelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getStatistic() {
		return statisticEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getOrigin() {
		return originEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getQuality() {
		return qualityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getInstant() {
		return instantEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDuration() {
		return durationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getLocalDate() {
		return localDateEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherFactory getWeatherFactory() {
		return (WeatherFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		geoPositionEClass = createEClass(GEO_POSITION);
		createEAttribute(geoPositionEClass, GEO_POSITION__LATITUDE);
		createEAttribute(geoPositionEClass, GEO_POSITION__LONGITUDE);
		createEAttribute(geoPositionEClass, GEO_POSITION__ELEVATION);

		siteEClass = createEClass(SITE);
		createEAttribute(siteEClass, SITE__ID);
		createEAttribute(siteEClass, SITE__NAME);
		createEReference(siteEClass, SITE__POSITION);
		createEAttribute(siteEClass, SITE__TIME_ZONE);
		createEAttribute(siteEClass, SITE__REGISTERED_AT);
		createEAttribute(siteEClass, SITE__ACTIVE);
		createEAttribute(siteEClass, SITE__DATA_COMPLETE_FROM);
		createEReference(siteEClass, SITE__ATTRIBUTES);
		createEReference(siteEClass, SITE__BINDINGS);

		siteAttributeEClass = createEClass(SITE_ATTRIBUTE);
		createEAttribute(siteAttributeEClass, SITE_ATTRIBUTE__KEY);
		createEAttribute(siteAttributeEClass, SITE_ATTRIBUTE__VALUE);

		sourceBindingEClass = createEClass(SOURCE_BINDING);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__PROVIDER_ID);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__PRODUCT_ID);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__ORIGIN);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__RANK);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__DISTANCE_METERS);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__ELEVATION_DELTA_METERS);
		createEAttribute(sourceBindingEClass, SOURCE_BINDING__RESOLVED_AT);

		stationBindingEClass = createEClass(STATION_BINDING);
		createEReference(stationBindingEClass, STATION_BINDING__STATION);

		gridBindingEClass = createEClass(GRID_BINDING);
		createEReference(gridBindingEClass, GRID_BINDING__CELL);

		stationEClass = createEClass(STATION);
		createEAttribute(stationEClass, STATION__ID);
		createEAttribute(stationEClass, STATION__NAME);
		createEAttribute(stationEClass, STATION__ICAO_CODE);
		createEAttribute(stationEClass, STATION__COUNTRY);
		createEReference(stationEClass, STATION__POSITION);

		stationCatalogEClass = createEClass(STATION_CATALOG);
		createEAttribute(stationCatalogEClass, STATION_CATALOG__PROVIDER_ID);
		createEAttribute(stationCatalogEClass, STATION_CATALOG__PRODUCT_ID);
		createEAttribute(stationCatalogEClass, STATION_CATALOG__RETRIEVED_AT);
		createEReference(stationCatalogEClass, STATION_CATALOG__STATIONS);

		gridCellEClass = createEClass(GRID_CELL);
		createEAttribute(gridCellEClass, GRID_CELL__GRID_ID);
		createEAttribute(gridCellEClass, GRID_CELL__I);
		createEAttribute(gridCellEClass, GRID_CELL__J);
		createEReference(gridCellEClass, GRID_CELL__CENTER);
		createEAttribute(gridCellEClass, GRID_CELL__RESOLUTION_DEGREES);

		measuredValueEClass = createEClass(MEASURED_VALUE);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__KIND);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__LEVEL);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__STATISTIC);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__PERIOD);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__THRESHOLD);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__THRESHOLD_UNIT);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__VALID_AT);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__VALUE);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__CODE);
		createEAttribute(measuredValueEClass, MEASURED_VALUE__UNIT);
		createEReference(measuredValueEClass, MEASURED_VALUE__PROVENANCE);
		createEReference(measuredValueEClass, MEASURED_VALUE__UNCERTAINTY);

		provenanceEClass = createEClass(PROVENANCE);
		createEAttribute(provenanceEClass, PROVENANCE__PROVIDER_ID);
		createEAttribute(provenanceEClass, PROVENANCE__PRODUCT_ID);
		createEAttribute(provenanceEClass, PROVENANCE__SOURCE_ELEMENT);
		createEAttribute(provenanceEClass, PROVENANCE__MODEL_RUN);
		createEAttribute(provenanceEClass, PROVENANCE__ISSUED_AT);
		createEAttribute(provenanceEClass, PROVENANCE__RETRIEVED_AT);
		createEAttribute(provenanceEClass, PROVENANCE__ORIGIN);
		createEAttribute(provenanceEClass, PROVENANCE__STATION_ID);
		createEReference(provenanceEClass, PROVENANCE__CELL);
		createEAttribute(provenanceEClass, PROVENANCE__DISTANCE_METERS);
		createEAttribute(provenanceEClass, PROVENANCE__LICENCE);
		createEAttribute(provenanceEClass, PROVENANCE__ATTRIBUTION);
		createEReference(provenanceEClass, PROVENANCE__DERIVATION);

		derivationEClass = createEClass(DERIVATION);
		createEAttribute(derivationEClass, DERIVATION__FUNCTION_ID);
		createEAttribute(derivationEClass, DERIVATION__INPUTS);

		uncertaintyEClass = createEClass(UNCERTAINTY);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__QUALITY);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__SPATIAL_METERS);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__TEMPORAL_OFFSET);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__LEAD_TIME);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__STALE);
		createEAttribute(uncertaintyEClass, UNCERTAINTY__NOTE);

		weatherReportEClass = createEClass(WEATHER_REPORT);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__SITE_ID);
		createEAttribute(weatherReportEClass, WEATHER_REPORT__GENERATED_AT);
		createEReference(weatherReportEClass, WEATHER_REPORT__DATASETS);
		createEReference(weatherReportEClass, WEATHER_REPORT__DAYS);

		sourceDatasetEClass = createEClass(SOURCE_DATASET);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__PROVIDER_ID);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__PRODUCT_ID);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__ISSUED_AT);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__MODEL_RUN);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__RETRIEVED_AT);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__EXPECTED_REFRESH);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__HORIZON_START);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__HORIZON_END);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__ORIGIN);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__STATION_ID);
		createEReference(sourceDatasetEClass, SOURCE_DATASET__CELL);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__DISTANCE_METERS);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__LICENCE);
		createEAttribute(sourceDatasetEClass, SOURCE_DATASET__ATTRIBUTION);
		createEReference(sourceDatasetEClass, SOURCE_DATASET__VALUES);

		dayInfoEClass = createEClass(DAY_INFO);
		createEAttribute(dayInfoEClass, DAY_INFO__DATE);
		createEAttribute(dayInfoEClass, DAY_INFO__SUNRISE);
		createEAttribute(dayInfoEClass, DAY_INFO__SUNSET);
		createEAttribute(dayInfoEClass, DAY_INFO__CIVIL_DAWN);
		createEAttribute(dayInfoEClass, DAY_INFO__CIVIL_DUSK);
		createEAttribute(dayInfoEClass, DAY_INFO__NAUTICAL_DAWN);
		createEAttribute(dayInfoEClass, DAY_INFO__NAUTICAL_DUSK);
		createEAttribute(dayInfoEClass, DAY_INFO__SOLAR_NOON);
		createEAttribute(dayInfoEClass, DAY_INFO__DAY_LENGTH);
		createEAttribute(dayInfoEClass, DAY_INFO__MAX_SUN_ELEVATION);
		createEReference(dayInfoEClass, DAY_INFO__PROVENANCE);

		// Create enums
		bindingOriginEEnum = createEEnum(BINDING_ORIGIN);
		measurementKindEEnum = createEEnum(MEASUREMENT_KIND);
		levelEEnum = createEEnum(LEVEL);
		statisticEEnum = createEEnum(STATISTIC);
		originEEnum = createEEnum(ORIGIN);
		qualityEEnum = createEEnum(QUALITY);

		// Create data types
		instantEDataType = createEDataType(INSTANT);
		durationEDataType = createEDataType(DURATION);
		localDateEDataType = createEDataType(LOCAL_DATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stationBindingEClass.getESuperTypes().add(this.getSourceBinding());
		gridBindingEClass.getESuperTypes().add(this.getSourceBinding());

		// Initialize classes, features, and operations; add parameters
		initEClass(geoPositionEClass, GeoPosition.class, "GeoPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeoPosition_Latitude(), ecorePackage.getEDouble(), "latitude", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoPosition_Longitude(), ecorePackage.getEDouble(), "longitude", null, 1, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoPosition_Elevation(), ecorePackage.getEDouble(), "elevation", null, 0, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(siteEClass, Site.class, "Site", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSite_Id(), ecorePackage.getEString(), "id", null, 1, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_Name(), ecorePackage.getEString(), "name", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSite_Position(), this.getGeoPosition(), null, "position", null, 1, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_TimeZone(), ecorePackage.getEString(), "timeZone", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_RegisteredAt(), this.getInstant(), "registeredAt", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_Active(), ecorePackage.getEBoolean(), "active", "true", 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_DataCompleteFrom(), this.getInstant(), "dataCompleteFrom", null, 0, 1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSite_Attributes(), this.getSiteAttribute(), null, "attributes", null, 0, -1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSite_Bindings(), this.getSourceBinding(), null, "bindings", null, 0, -1, Site.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(siteAttributeEClass, Map.Entry.class, "SiteAttribute", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSiteAttribute_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSiteAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourceBindingEClass, SourceBinding.class, "SourceBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSourceBinding_ProviderId(), ecorePackage.getEString(), "providerId", null, 1, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_ProductId(), ecorePackage.getEString(), "productId", null, 1, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_Origin(), this.getBindingOrigin(), "origin", null, 1, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_Rank(), ecorePackage.getEInt(), "rank", null, 0, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_DistanceMeters(), ecorePackage.getEDouble(), "distanceMeters", null, 0, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_ElevationDeltaMeters(), ecorePackage.getEDouble(), "elevationDeltaMeters", null, 0, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceBinding_ResolvedAt(), this.getInstant(), "resolvedAt", null, 0, 1, SourceBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stationBindingEClass, StationBinding.class, "StationBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStationBinding_Station(), this.getStation(), null, "station", null, 1, 1, StationBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gridBindingEClass, GridBinding.class, "GridBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGridBinding_Cell(), this.getGridCell(), null, "cell", null, 1, 1, GridBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stationEClass, Station.class, "Station", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStation_Id(), ecorePackage.getEString(), "id", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_Name(), ecorePackage.getEString(), "name", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_IcaoCode(), ecorePackage.getEString(), "icaoCode", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStation_Country(), ecorePackage.getEString(), "country", null, 0, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStation_Position(), this.getGeoPosition(), null, "position", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stationCatalogEClass, StationCatalog.class, "StationCatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStationCatalog_ProviderId(), ecorePackage.getEString(), "providerId", null, 1, 1, StationCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStationCatalog_ProductId(), ecorePackage.getEString(), "productId", null, 1, 1, StationCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStationCatalog_RetrievedAt(), this.getInstant(), "retrievedAt", null, 0, 1, StationCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStationCatalog_Stations(), this.getStation(), null, "stations", null, 0, -1, StationCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gridCellEClass, GridCell.class, "GridCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGridCell_GridId(), ecorePackage.getEString(), "gridId", null, 1, 1, GridCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGridCell_I(), ecorePackage.getEInt(), "i", null, 0, 1, GridCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGridCell_J(), ecorePackage.getEInt(), "j", null, 0, 1, GridCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGridCell_Center(), this.getGeoPosition(), null, "center", null, 0, 1, GridCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGridCell_ResolutionDegrees(), ecorePackage.getEDouble(), "resolutionDegrees", null, 0, 1, GridCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measuredValueEClass, MeasuredValue.class, "MeasuredValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasuredValue_Kind(), this.getMeasurementKind(), "kind", null, 1, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Level(), this.getLevel(), "level", "UNSPECIFIED", 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Statistic(), this.getStatistic(), "statistic", "INSTANT", 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Period(), this.getDuration(), "period", null, 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Threshold(), ecorePackage.getEDouble(), "threshold", null, 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_ThresholdUnit(), ecorePackage.getEString(), "thresholdUnit", null, 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_ValidAt(), this.getInstant(), "validAt", null, 1, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Code(), ecorePackage.getEInt(), "code", null, 0, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasuredValue_Unit(), ecorePackage.getEString(), "unit", null, 1, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasuredValue_Provenance(), this.getProvenance(), null, "provenance", null, 1, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasuredValue_Uncertainty(), this.getUncertainty(), null, "uncertainty", null, 1, 1, MeasuredValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(provenanceEClass, Provenance.class, "Provenance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvenance_ProviderId(), ecorePackage.getEString(), "providerId", null, 1, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_ProductId(), ecorePackage.getEString(), "productId", null, 1, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_SourceElement(), ecorePackage.getEString(), "sourceElement", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_ModelRun(), this.getInstant(), "modelRun", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_IssuedAt(), this.getInstant(), "issuedAt", null, 1, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_RetrievedAt(), this.getInstant(), "retrievedAt", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_Origin(), this.getOrigin(), "origin", null, 1, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_StationId(), ecorePackage.getEString(), "stationId", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvenance_Cell(), this.getGridCell(), null, "cell", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_DistanceMeters(), ecorePackage.getEDouble(), "distanceMeters", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_Licence(), ecorePackage.getEString(), "licence", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvenance_Attribution(), ecorePackage.getEString(), "attribution", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvenance_Derivation(), this.getDerivation(), null, "derivation", null, 0, 1, Provenance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(derivationEClass, Derivation.class, "Derivation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDerivation_FunctionId(), ecorePackage.getEString(), "functionId", null, 1, 1, Derivation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDerivation_Inputs(), ecorePackage.getEString(), "inputs", null, 0, -1, Derivation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uncertaintyEClass, Uncertainty.class, "Uncertainty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUncertainty_Quality(), this.getQuality(), "quality", null, 1, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUncertainty_SpatialMeters(), ecorePackage.getEDouble(), "spatialMeters", null, 0, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUncertainty_TemporalOffset(), this.getDuration(), "temporalOffset", null, 0, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUncertainty_LeadTime(), this.getDuration(), "leadTime", null, 0, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUncertainty_Stale(), ecorePackage.getEBoolean(), "stale", null, 0, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUncertainty_Note(), ecorePackage.getEString(), "note", null, 0, 1, Uncertainty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weatherReportEClass, WeatherReport.class, "WeatherReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeatherReport_SiteId(), ecorePackage.getEString(), "siteId", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeatherReport_GeneratedAt(), this.getInstant(), "generatedAt", null, 1, 1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReport_Datasets(), this.getSourceDataset(), null, "datasets", null, 0, -1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeatherReport_Days(), this.getDayInfo(), null, "days", null, 0, -1, WeatherReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourceDatasetEClass, SourceDataset.class, "SourceDataset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSourceDataset_ProviderId(), ecorePackage.getEString(), "providerId", null, 1, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_ProductId(), ecorePackage.getEString(), "productId", null, 1, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_IssuedAt(), this.getInstant(), "issuedAt", null, 1, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_ModelRun(), this.getInstant(), "modelRun", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_RetrievedAt(), this.getInstant(), "retrievedAt", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_ExpectedRefresh(), this.getDuration(), "expectedRefresh", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_HorizonStart(), this.getInstant(), "horizonStart", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_HorizonEnd(), this.getInstant(), "horizonEnd", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_Origin(), this.getOrigin(), "origin", null, 1, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_StationId(), ecorePackage.getEString(), "stationId", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSourceDataset_Cell(), this.getGridCell(), null, "cell", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_DistanceMeters(), ecorePackage.getEDouble(), "distanceMeters", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_Licence(), ecorePackage.getEString(), "licence", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSourceDataset_Attribution(), ecorePackage.getEString(), "attribution", null, 0, 1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSourceDataset_Values(), this.getMeasuredValue(), null, "values", null, 0, -1, SourceDataset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dayInfoEClass, DayInfo.class, "DayInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDayInfo_Date(), this.getLocalDate(), "date", null, 1, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_Sunrise(), this.getInstant(), "sunrise", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_Sunset(), this.getInstant(), "sunset", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_CivilDawn(), this.getInstant(), "civilDawn", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_CivilDusk(), this.getInstant(), "civilDusk", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_NauticalDawn(), this.getInstant(), "nauticalDawn", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_NauticalDusk(), this.getInstant(), "nauticalDusk", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_SolarNoon(), this.getInstant(), "solarNoon", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_DayLength(), this.getDuration(), "dayLength", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDayInfo_MaxSunElevation(), ecorePackage.getEDouble(), "maxSunElevation", null, 0, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDayInfo_Provenance(), this.getProvenance(), null, "provenance", null, 1, 1, DayInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(bindingOriginEEnum, BindingOrigin.class, "BindingOrigin");
		addEEnumLiteral(bindingOriginEEnum, BindingOrigin.AUTOMATIC);
		addEEnumLiteral(bindingOriginEEnum, BindingOrigin.MANUAL);

		initEEnum(measurementKindEEnum, MeasurementKind.class, "MeasurementKind");
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.AIR_TEMPERATURE);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.DEW_POINT);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.RELATIVE_HUMIDITY);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SURFACE_PRESSURE);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.WIND_SPEED);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.WIND_DIRECTION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.WIND_GUST);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.CLOUD_COVER);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.GLOBAL_RADIATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.DIRECT_RADIATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.DIFFUSE_RADIATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SUNSHINE_DURATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.PRECIPITATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SNOW_WATER_EQUIVALENT);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.FOG);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.VISIBILITY);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.UV_INDEX);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SIGNIFICANT_WEATHER);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SUN_ELEVATION);
		addEEnumLiteral(measurementKindEEnum, MeasurementKind.SUN_AZIMUTH);

		initEEnum(levelEEnum, Level.class, "Level");
		addEEnumLiteral(levelEEnum, Level.UNSPECIFIED);
		addEEnumLiteral(levelEEnum, Level.SURFACE);
		addEEnumLiteral(levelEEnum, Level.GROUND_5CM);
		addEEnumLiteral(levelEEnum, Level.GROUND_2M);
		addEEnumLiteral(levelEEnum, Level.GROUND_10M);
		addEEnumLiteral(levelEEnum, Level.MEAN_SEA_LEVEL);
		addEEnumLiteral(levelEEnum, Level.CLOUD_TOTAL);
		addEEnumLiteral(levelEEnum, Level.CLOUD_EFFECTIVE);
		addEEnumLiteral(levelEEnum, Level.CLOUD_LOW);
		addEEnumLiteral(levelEEnum, Level.CLOUD_MID);
		addEEnumLiteral(levelEEnum, Level.CLOUD_HIGH);
		addEEnumLiteral(levelEEnum, Level.CLOUD_BELOW_500FT);

		initEEnum(statisticEEnum, Statistic.class, "Statistic");
		addEEnumLiteral(statisticEEnum, Statistic.INSTANT);
		addEEnumLiteral(statisticEEnum, Statistic.MEAN);
		addEEnumLiteral(statisticEEnum, Statistic.MIN);
		addEEnumLiteral(statisticEEnum, Statistic.MAX);
		addEEnumLiteral(statisticEEnum, Statistic.ACCUMULATED);
		addEEnumLiteral(statisticEEnum, Statistic.PROBABILITY);

		initEEnum(originEEnum, Origin.class, "Origin");
		addEEnumLiteral(originEEnum, Origin.STATION);
		addEEnumLiteral(originEEnum, Origin.GRID_CELL);
		addEEnumLiteral(originEEnum, Origin.COMPUTED);
		addEEnumLiteral(originEEnum, Origin.ADHOC);

		initEEnum(qualityEEnum, Quality.class, "Quality");
		addEEnumLiteral(qualityEEnum, Quality.OBSERVED);
		addEEnumLiteral(qualityEEnum, Quality.ANALYSIS);
		addEEnumLiteral(qualityEEnum, Quality.FORECAST);
		addEEnumLiteral(qualityEEnum, Quality.INTERPOLATED);
		addEEnumLiteral(qualityEEnum, Quality.DERIVED);
		addEEnumLiteral(qualityEEnum, Quality.DEGRADED);

		// Initialize data types
		initEDataType(instantEDataType, Instant.class, "Instant", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(durationEDataType, Duration.class, "Duration", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(localDateEDataType, LocalDate.class, "LocalDate", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// java.time
		createJavaAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "conversionDelegates", "java.time"
		   });
	}

	/**
	 * Initializes the annotations for <b>java.time</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createJavaAnnotations() {
		String source = "java.time";
		addAnnotation
		  (instantEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (durationEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (localDateEDataType,
		   source,
		   new String[] {
		   });
	}

} //WeatherPackageImpl
