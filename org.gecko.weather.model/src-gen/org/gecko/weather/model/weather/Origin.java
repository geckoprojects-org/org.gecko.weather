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
 * A representation of the literals of the enumeration '<em><b>Origin</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getOrigin()
 * @model
 * @generated
 */
@ProviderType
public enum Origin implements Enumerator {
	/**
	 * The '<em><b>STATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATION_VALUE
	 * @generated
	 * @ordered
	 */
	STATION(0, "STATION", "STATION"),

	/**
	 * The '<em><b>GRID CELL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRID_CELL_VALUE
	 * @generated
	 * @ordered
	 */
	GRID_CELL(1, "GRID_CELL", "GRID_CELL"),

	/**
	 * The '<em><b>COMPUTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPUTED_VALUE
	 * @generated
	 * @ordered
	 */
	COMPUTED(2, "COMPUTED", "COMPUTED"),

	/**
	 * The '<em><b>ADHOC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADHOC_VALUE
	 * @generated
	 * @ordered
	 */
	ADHOC(3, "ADHOC", "ADHOC");

	/**
	 * The '<em><b>STATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATION_VALUE = 0;

	/**
	 * The '<em><b>GRID CELL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRID_CELL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GRID_CELL_VALUE = 1;

	/**
	 * The '<em><b>COMPUTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPUTED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMPUTED_VALUE = 2;

	/**
	 * The '<em><b>ADHOC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADHOC
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ADHOC_VALUE = 3;

	/**
	 * An array of all the '<em><b>Origin</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Origin[] VALUES_ARRAY =
		new Origin[] {
			STATION,
			GRID_CELL,
			COMPUTED,
			ADHOC,
		};

	/**
	 * A public read-only list of all the '<em><b>Origin</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Origin> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Origin</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Origin get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Origin result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Origin</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Origin getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Origin result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Origin</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Origin get(int value) {
		switch (value) {
			case STATION_VALUE: return STATION;
			case GRID_CELL_VALUE: return GRID_CELL;
			case COMPUTED_VALUE: return COMPUTED;
			case ADHOC_VALUE: return ADHOC;
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
	private Origin(int value, String name, String literal) {
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
	
} //Origin
