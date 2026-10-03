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
package org.gecko.weather.model.weather.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.gecko.weather.model.weather.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage
 * @generated
 */
public class WeatherAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WeatherPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WeatherPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeatherSwitch<Adapter> modelSwitch =
		new WeatherSwitch<Adapter>() {
			@Override
			public Adapter caseGeoPosition(GeoPosition object) {
				return createGeoPositionAdapter();
			}
			@Override
			public Adapter caseSite(Site object) {
				return createSiteAdapter();
			}
			@Override
			public Adapter caseSiteAttribute(Map.Entry<String, String> object) {
				return createSiteAttributeAdapter();
			}
			@Override
			public Adapter caseSourceBinding(SourceBinding object) {
				return createSourceBindingAdapter();
			}
			@Override
			public Adapter caseStationBinding(StationBinding object) {
				return createStationBindingAdapter();
			}
			@Override
			public Adapter caseGridBinding(GridBinding object) {
				return createGridBindingAdapter();
			}
			@Override
			public Adapter caseStation(Station object) {
				return createStationAdapter();
			}
			@Override
			public Adapter caseStationCatalog(StationCatalog object) {
				return createStationCatalogAdapter();
			}
			@Override
			public Adapter caseGridCell(GridCell object) {
				return createGridCellAdapter();
			}
			@Override
			public Adapter caseMeasuredValue(MeasuredValue object) {
				return createMeasuredValueAdapter();
			}
			@Override
			public Adapter caseProvenance(Provenance object) {
				return createProvenanceAdapter();
			}
			@Override
			public Adapter caseDerivation(Derivation object) {
				return createDerivationAdapter();
			}
			@Override
			public Adapter caseUncertainty(Uncertainty object) {
				return createUncertaintyAdapter();
			}
			@Override
			public Adapter caseWeatherReport(WeatherReport object) {
				return createWeatherReportAdapter();
			}
			@Override
			public Adapter caseSourceDataset(SourceDataset object) {
				return createSourceDatasetAdapter();
			}
			@Override
			public Adapter caseDayInfo(DayInfo object) {
				return createDayInfoAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.GeoPosition <em>Geo Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.GeoPosition
	 * @generated
	 */
	public Adapter createGeoPositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.Site <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.Site
	 * @generated
	 */
	public Adapter createSiteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Site Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createSiteAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.SourceBinding <em>Source Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.SourceBinding
	 * @generated
	 */
	public Adapter createSourceBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.StationBinding <em>Station Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.StationBinding
	 * @generated
	 */
	public Adapter createStationBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.GridBinding <em>Grid Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.GridBinding
	 * @generated
	 */
	public Adapter createGridBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.Station <em>Station</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.Station
	 * @generated
	 */
	public Adapter createStationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.StationCatalog <em>Station Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.StationCatalog
	 * @generated
	 */
	public Adapter createStationCatalogAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.GridCell <em>Grid Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.GridCell
	 * @generated
	 */
	public Adapter createGridCellAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.MeasuredValue <em>Measured Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.MeasuredValue
	 * @generated
	 */
	public Adapter createMeasuredValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.Provenance <em>Provenance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.Provenance
	 * @generated
	 */
	public Adapter createProvenanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.Derivation <em>Derivation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.Derivation
	 * @generated
	 */
	public Adapter createDerivationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.Uncertainty <em>Uncertainty</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.Uncertainty
	 * @generated
	 */
	public Adapter createUncertaintyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.WeatherReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.WeatherReport
	 * @generated
	 */
	public Adapter createWeatherReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.SourceDataset <em>Source Dataset</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.SourceDataset
	 * @generated
	 */
	public Adapter createSourceDatasetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.model.weather.DayInfo <em>Day Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.model.weather.DayInfo
	 * @generated
	 */
	public Adapter createDayInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WeatherAdapterFactory
