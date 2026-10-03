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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.weather.model.weather.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WeatherFactoryImpl extends EFactoryImpl implements WeatherFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WeatherFactory init() {
		try {
			WeatherFactory theWeatherFactory = (WeatherFactory)EPackage.Registry.INSTANCE.getEFactory(WeatherPackage.eNS_URI);
			if (theWeatherFactory != null) {
				return theWeatherFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WeatherFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherFactoryImpl() {
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
			case WeatherPackage.GEO_POSITION: return createGeoPosition();
			case WeatherPackage.SITE: return createSite();
			case WeatherPackage.SITE_ATTRIBUTE: return (EObject)createSiteAttribute();
			case WeatherPackage.STATION_BINDING: return createStationBinding();
			case WeatherPackage.GRID_BINDING: return createGridBinding();
			case WeatherPackage.STATION: return createStation();
			case WeatherPackage.STATION_CATALOG: return createStationCatalog();
			case WeatherPackage.GRID_CELL: return createGridCell();
			case WeatherPackage.MEASURED_VALUE: return createMeasuredValue();
			case WeatherPackage.PROVENANCE: return createProvenance();
			case WeatherPackage.DERIVATION: return createDerivation();
			case WeatherPackage.UNCERTAINTY: return createUncertainty();
			case WeatherPackage.WEATHER_REPORT: return createWeatherReport();
			case WeatherPackage.SOURCE_DATASET: return createSourceDataset();
			case WeatherPackage.DAY_INFO: return createDayInfo();
			case WeatherPackage.SOURCE_STATE_RECORD: return createSourceStateRecord();
			case WeatherPackage.SOURCE_ENTITY: return createSourceEntity();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case WeatherPackage.BINDING_ORIGIN:
				return createBindingOriginFromString(eDataType, initialValue);
			case WeatherPackage.MEASUREMENT_KIND:
				return createMeasurementKindFromString(eDataType, initialValue);
			case WeatherPackage.LEVEL:
				return createLevelFromString(eDataType, initialValue);
			case WeatherPackage.STATISTIC:
				return createStatisticFromString(eDataType, initialValue);
			case WeatherPackage.ORIGIN:
				return createOriginFromString(eDataType, initialValue);
			case WeatherPackage.QUALITY:
				return createQualityFromString(eDataType, initialValue);
			case WeatherPackage.INSTANT:
				return createInstantFromString(eDataType, initialValue);
			case WeatherPackage.DURATION:
				return createDurationFromString(eDataType, initialValue);
			case WeatherPackage.LOCAL_DATE:
				return createLocalDateFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case WeatherPackage.BINDING_ORIGIN:
				return convertBindingOriginToString(eDataType, instanceValue);
			case WeatherPackage.MEASUREMENT_KIND:
				return convertMeasurementKindToString(eDataType, instanceValue);
			case WeatherPackage.LEVEL:
				return convertLevelToString(eDataType, instanceValue);
			case WeatherPackage.STATISTIC:
				return convertStatisticToString(eDataType, instanceValue);
			case WeatherPackage.ORIGIN:
				return convertOriginToString(eDataType, instanceValue);
			case WeatherPackage.QUALITY:
				return convertQualityToString(eDataType, instanceValue);
			case WeatherPackage.INSTANT:
				return convertInstantToString(eDataType, instanceValue);
			case WeatherPackage.DURATION:
				return convertDurationToString(eDataType, instanceValue);
			case WeatherPackage.LOCAL_DATE:
				return convertLocalDateToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GeoPosition createGeoPosition() {
		GeoPositionImpl geoPosition = new GeoPositionImpl();
		return geoPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Site createSite() {
		SiteImpl site = new SiteImpl();
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createSiteAttribute() {
		SiteAttributeImpl siteAttribute = new SiteAttributeImpl();
		return siteAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StationBinding createStationBinding() {
		StationBindingImpl stationBinding = new StationBindingImpl();
		return stationBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GridBinding createGridBinding() {
		GridBindingImpl gridBinding = new GridBindingImpl();
		return gridBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Station createStation() {
		StationImpl station = new StationImpl();
		return station;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StationCatalog createStationCatalog() {
		StationCatalogImpl stationCatalog = new StationCatalogImpl();
		return stationCatalog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GridCell createGridCell() {
		GridCellImpl gridCell = new GridCellImpl();
		return gridCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MeasuredValue createMeasuredValue() {
		MeasuredValueImpl measuredValue = new MeasuredValueImpl();
		return measuredValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Provenance createProvenance() {
		ProvenanceImpl provenance = new ProvenanceImpl();
		return provenance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Derivation createDerivation() {
		DerivationImpl derivation = new DerivationImpl();
		return derivation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Uncertainty createUncertainty() {
		UncertaintyImpl uncertainty = new UncertaintyImpl();
		return uncertainty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherReport createWeatherReport() {
		WeatherReportImpl weatherReport = new WeatherReportImpl();
		return weatherReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SourceDataset createSourceDataset() {
		SourceDatasetImpl sourceDataset = new SourceDatasetImpl();
		return sourceDataset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DayInfo createDayInfo() {
		DayInfoImpl dayInfo = new DayInfoImpl();
		return dayInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SourceStateRecord createSourceStateRecord() {
		SourceStateRecordImpl sourceStateRecord = new SourceStateRecordImpl();
		return sourceStateRecord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SourceEntity createSourceEntity() {
		SourceEntityImpl sourceEntity = new SourceEntityImpl();
		return sourceEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingOrigin createBindingOriginFromString(EDataType eDataType, String initialValue) {
		BindingOrigin result = BindingOrigin.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBindingOriginToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementKind createMeasurementKindFromString(EDataType eDataType, String initialValue) {
		MeasurementKind result = MeasurementKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMeasurementKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Level createLevelFromString(EDataType eDataType, String initialValue) {
		Level result = Level.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLevelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statistic createStatisticFromString(EDataType eDataType, String initialValue) {
		Statistic result = Statistic.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStatisticToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Origin createOriginFromString(EDataType eDataType, String initialValue) {
		Origin result = Origin.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOriginToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quality createQualityFromString(EDataType eDataType, String initialValue) {
		Quality result = Quality.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQualityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instant createInstantFromString(EDataType eDataType, String initialValue) {
		return (Instant)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInstantToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Duration createDurationFromString(EDataType eDataType, String initialValue) {
		return (Duration)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalDate createLocalDateFromString(EDataType eDataType, String initialValue) {
		return (LocalDate)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocalDateToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WeatherPackage getWeatherPackage() {
		return (WeatherPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WeatherPackage getPackage() {
		return WeatherPackage.eINSTANCE;
	}

} //WeatherFactoryImpl
