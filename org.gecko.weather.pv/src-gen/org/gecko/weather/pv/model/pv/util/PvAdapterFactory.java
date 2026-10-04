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
package org.gecko.weather.pv.model.pv.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.gecko.weather.pv.model.pv.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.pv.model.pv.PvPackage
 * @generated
 */
public class PvAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PvPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PvAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PvPackage.eINSTANCE;
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
	protected PvSwitch<Adapter> modelSwitch =
		new PvSwitch<Adapter>() {
			@Override
			public Adapter casePlant(Plant object) {
				return createPlantAdapter();
			}
			@Override
			public Adapter casePvArray(PvArray object) {
				return createPvArrayAdapter();
			}
			@Override
			public Adapter caseInverter(Inverter object) {
				return createInverterAdapter();
			}
			@Override
			public Adapter caseObstacle(Obstacle object) {
				return createObstacleAdapter();
			}
			@Override
			public Adapter caseHorizonPoint(HorizonPoint object) {
				return createHorizonPointAdapter();
			}
			@Override
			public Adapter casePlantDirectory(PlantDirectory object) {
				return createPlantDirectoryAdapter();
			}
			@Override
			public Adapter casePlantEntry(PlantEntry object) {
				return createPlantEntryAdapter();
			}
			@Override
			public Adapter casePvOutlook(PvOutlook object) {
				return createPvOutlookAdapter();
			}
			@Override
			public Adapter casePvArrayInfo(PvArrayInfo object) {
				return createPvArrayInfoAdapter();
			}
			@Override
			public Adapter casePvHour(PvHour object) {
				return createPvHourAdapter();
			}
			@Override
			public Adapter casePvDay(PvDay object) {
				return createPvDayAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.Plant <em>Plant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.Plant
	 * @generated
	 */
	public Adapter createPlantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PvArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PvArray
	 * @generated
	 */
	public Adapter createPvArrayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.Inverter <em>Inverter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.Inverter
	 * @generated
	 */
	public Adapter createInverterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.Obstacle <em>Obstacle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.Obstacle
	 * @generated
	 */
	public Adapter createObstacleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.HorizonPoint <em>Horizon Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.HorizonPoint
	 * @generated
	 */
	public Adapter createHorizonPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PlantDirectory <em>Plant Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PlantDirectory
	 * @generated
	 */
	public Adapter createPlantDirectoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PlantEntry <em>Plant Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PlantEntry
	 * @generated
	 */
	public Adapter createPlantEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PvOutlook <em>Outlook</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PvOutlook
	 * @generated
	 */
	public Adapter createPvOutlookAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PvArrayInfo <em>Array Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PvArrayInfo
	 * @generated
	 */
	public Adapter createPvArrayInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PvHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PvHour
	 * @generated
	 */
	public Adapter createPvHourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.gecko.weather.pv.model.pv.PvDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.gecko.weather.pv.model.pv.PvDay
	 * @generated
	 */
	public Adapter createPvDayAdapter() {
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

} //PvAdapterFactory
