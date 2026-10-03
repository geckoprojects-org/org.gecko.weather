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
 * A representation of the literals of the enumeration '<em><b>Level</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Vertical reference of a value: height above ground, cloud layer, or sea level for reduced pressure.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getLevel()
 * @model
 * @generated
 */
@ProviderType
public enum Level implements Enumerator {
	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	UNSPECIFIED(0, "UNSPECIFIED", "UNSPECIFIED"),

	/**
	 * The '<em><b>SURFACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SURFACE_VALUE
	 * @generated
	 * @ordered
	 */
	SURFACE(1, "SURFACE", "SURFACE"),

	/**
	 * The '<em><b>GROUND 5CM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_5CM_VALUE
	 * @generated
	 * @ordered
	 */
	GROUND_5CM(2, "GROUND_5CM", "GROUND_5CM"),

	/**
	 * The '<em><b>GROUND 2M</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_2M_VALUE
	 * @generated
	 * @ordered
	 */
	GROUND_2M(3, "GROUND_2M", "GROUND_2M"),

	/**
	 * The '<em><b>GROUND 10M</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_10M_VALUE
	 * @generated
	 * @ordered
	 */
	GROUND_10M(4, "GROUND_10M", "GROUND_10M"),

	/**
	 * The '<em><b>MEAN SEA LEVEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEAN_SEA_LEVEL_VALUE
	 * @generated
	 * @ordered
	 */
	MEAN_SEA_LEVEL(5, "MEAN_SEA_LEVEL", "MEAN_SEA_LEVEL"),

	/**
	 * The '<em><b>CLOUD TOTAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_TOTAL_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_TOTAL(6, "CLOUD_TOTAL", "CLOUD_TOTAL"),

	/**
	 * The '<em><b>CLOUD EFFECTIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_EFFECTIVE_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_EFFECTIVE(7, "CLOUD_EFFECTIVE", "CLOUD_EFFECTIVE"),

	/**
	 * The '<em><b>CLOUD LOW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_LOW_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_LOW(8, "CLOUD_LOW", "CLOUD_LOW"),

	/**
	 * The '<em><b>CLOUD MID</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_MID_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_MID(9, "CLOUD_MID", "CLOUD_MID"),

	/**
	 * The '<em><b>CLOUD HIGH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_HIGH_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_HIGH(10, "CLOUD_HIGH", "CLOUD_HIGH"),

	/**
	 * The '<em><b>CLOUD BELOW 500FT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_BELOW_500FT_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_BELOW_500FT(11, "CLOUD_BELOW_500FT", "CLOUD_BELOW_500FT");

	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNSPECIFIED_VALUE = 0;

	/**
	 * The '<em><b>SURFACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SURFACE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SURFACE_VALUE = 1;

	/**
	 * The '<em><b>GROUND 5CM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_5CM
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GROUND_5CM_VALUE = 2;

	/**
	 * The '<em><b>GROUND 2M</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_2M
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GROUND_2M_VALUE = 3;

	/**
	 * The '<em><b>GROUND 10M</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROUND_10M
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GROUND_10M_VALUE = 4;

	/**
	 * The '<em><b>MEAN SEA LEVEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEAN_SEA_LEVEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MEAN_SEA_LEVEL_VALUE = 5;

	/**
	 * The '<em><b>CLOUD TOTAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_TOTAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_TOTAL_VALUE = 6;

	/**
	 * The '<em><b>CLOUD EFFECTIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_EFFECTIVE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_EFFECTIVE_VALUE = 7;

	/**
	 * The '<em><b>CLOUD LOW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_LOW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_LOW_VALUE = 8;

	/**
	 * The '<em><b>CLOUD MID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_MID
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_MID_VALUE = 9;

	/**
	 * The '<em><b>CLOUD HIGH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_HIGH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_HIGH_VALUE = 10;

	/**
	 * The '<em><b>CLOUD BELOW 500FT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_BELOW_500FT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_BELOW_500FT_VALUE = 11;

	/**
	 * An array of all the '<em><b>Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Level[] VALUES_ARRAY =
		new Level[] {
			UNSPECIFIED,
			SURFACE,
			GROUND_5CM,
			GROUND_2M,
			GROUND_10M,
			MEAN_SEA_LEVEL,
			CLOUD_TOTAL,
			CLOUD_EFFECTIVE,
			CLOUD_LOW,
			CLOUD_MID,
			CLOUD_HIGH,
			CLOUD_BELOW_500FT,
		};

	/**
	 * A public read-only list of all the '<em><b>Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Level> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Level</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Level get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Level result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Level</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Level getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Level result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Level</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Level get(int value) {
		switch (value) {
			case UNSPECIFIED_VALUE: return UNSPECIFIED;
			case SURFACE_VALUE: return SURFACE;
			case GROUND_5CM_VALUE: return GROUND_5CM;
			case GROUND_2M_VALUE: return GROUND_2M;
			case GROUND_10M_VALUE: return GROUND_10M;
			case MEAN_SEA_LEVEL_VALUE: return MEAN_SEA_LEVEL;
			case CLOUD_TOTAL_VALUE: return CLOUD_TOTAL;
			case CLOUD_EFFECTIVE_VALUE: return CLOUD_EFFECTIVE;
			case CLOUD_LOW_VALUE: return CLOUD_LOW;
			case CLOUD_MID_VALUE: return CLOUD_MID;
			case CLOUD_HIGH_VALUE: return CLOUD_HIGH;
			case CLOUD_BELOW_500FT_VALUE: return CLOUD_BELOW_500FT;
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
	private Level(int value, String name, String literal) {
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
	
} //Level
