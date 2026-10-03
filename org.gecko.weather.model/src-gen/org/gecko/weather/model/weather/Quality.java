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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Quality</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Coarse classification of how the value was produced. The facts behind it (distance, offsets, staleness) sit beside it in Uncertainty.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getQuality()
 * @model
 * @generated
 */
@ProviderType
public enum Quality implements Enumerator {
	/**
	 * The '<em><b>OBSERVED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBSERVED_VALUE
	 * @generated
	 * @ordered
	 */
	OBSERVED(0, "OBSERVED", "OBSERVED"),

	/**
	 * The '<em><b>ANALYSIS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANALYSIS_VALUE
	 * @generated
	 * @ordered
	 */
	ANALYSIS(1, "ANALYSIS", "ANALYSIS"),

	/**
	 * The '<em><b>FORECAST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORECAST_VALUE
	 * @generated
	 * @ordered
	 */
	FORECAST(2, "FORECAST", "FORECAST"),

	/**
	 * The '<em><b>INTERPOLATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERPOLATED_VALUE
	 * @generated
	 * @ordered
	 */
	INTERPOLATED(3, "INTERPOLATED", "INTERPOLATED"),

	/**
	 * The '<em><b>DERIVED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DERIVED_VALUE
	 * @generated
	 * @ordered
	 */
	DERIVED(4, "DERIVED", "DERIVED"),

	/**
	 * The '<em><b>DEGRADED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEGRADED_VALUE
	 * @generated
	 * @ordered
	 */
	DEGRADED(5, "DEGRADED", "DEGRADED");

	/**
	 * The '<em><b>OBSERVED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBSERVED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OBSERVED_VALUE = 0;

	/**
	 * The '<em><b>ANALYSIS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANALYSIS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ANALYSIS_VALUE = 1;

	/**
	 * The '<em><b>FORECAST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORECAST
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FORECAST_VALUE = 2;

	/**
	 * The '<em><b>INTERPOLATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERPOLATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INTERPOLATED_VALUE = 3;

	/**
	 * The '<em><b>DERIVED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DERIVED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DERIVED_VALUE = 4;

	/**
	 * The '<em><b>DEGRADED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEGRADED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEGRADED_VALUE = 5;

	/**
	 * An array of all the '<em><b>Quality</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Quality[] VALUES_ARRAY =
		new Quality[] {
			OBSERVED,
			ANALYSIS,
			FORECAST,
			INTERPOLATED,
			DERIVED,
			DEGRADED,
		};

	/**
	 * A public read-only list of all the '<em><b>Quality</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Quality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Quality</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Quality get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Quality result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Quality</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Quality getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Quality result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Quality</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Quality get(int value) {
		switch (value) {
			case OBSERVED_VALUE: return OBSERVED;
			case ANALYSIS_VALUE: return ANALYSIS;
			case FORECAST_VALUE: return FORECAST;
			case INTERPOLATED_VALUE: return INTERPOLATED;
			case DERIVED_VALUE: return DERIVED;
			case DEGRADED_VALUE: return DEGRADED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Quality(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //Quality
