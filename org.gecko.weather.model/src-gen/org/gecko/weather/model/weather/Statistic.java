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
 * A representation of the literals of the enumeration '<em><b>Statistic</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * How the value relates to its period. INSTANT values have no period; the others describe the window ending at validAt. PROBABILITY is the probability (0..100 %) that the quantity reaches the threshold within the period.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getStatistic()
 * @model
 * @generated
 */
@ProviderType
public enum Statistic implements Enumerator {
	/**
	 * The '<em><b>INSTANT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTANT_VALUE
	 * @generated
	 * @ordered
	 */
	INSTANT(0, "INSTANT", "INSTANT"),

	/**
	 * The '<em><b>MEAN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEAN_VALUE
	 * @generated
	 * @ordered
	 */
	MEAN(1, "MEAN", "MEAN"),

	/**
	 * The '<em><b>MIN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIN_VALUE
	 * @generated
	 * @ordered
	 */
	MIN(2, "MIN", "MIN"),

	/**
	 * The '<em><b>MAX</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAX_VALUE
	 * @generated
	 * @ordered
	 */
	MAX(3, "MAX", "MAX"),

	/**
	 * The '<em><b>ACCUMULATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACCUMULATED_VALUE
	 * @generated
	 * @ordered
	 */
	ACCUMULATED(4, "ACCUMULATED", "ACCUMULATED"),

	/**
	 * The '<em><b>PROBABILITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROBABILITY_VALUE
	 * @generated
	 * @ordered
	 */
	PROBABILITY(5, "PROBABILITY", "PROBABILITY");

	/**
	 * The '<em><b>INSTANT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTANT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INSTANT_VALUE = 0;

	/**
	 * The '<em><b>MEAN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEAN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MEAN_VALUE = 1;

	/**
	 * The '<em><b>MIN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MIN_VALUE = 2;

	/**
	 * The '<em><b>MAX</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAX
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MAX_VALUE = 3;

	/**
	 * The '<em><b>ACCUMULATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACCUMULATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ACCUMULATED_VALUE = 4;

	/**
	 * The '<em><b>PROBABILITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROBABILITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROBABILITY_VALUE = 5;

	/**
	 * An array of all the '<em><b>Statistic</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Statistic[] VALUES_ARRAY =
		new Statistic[] {
			INSTANT,
			MEAN,
			MIN,
			MAX,
			ACCUMULATED,
			PROBABILITY,
		};

	/**
	 * A public read-only list of all the '<em><b>Statistic</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Statistic> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Statistic</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Statistic get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Statistic result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Statistic</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Statistic getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Statistic result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Statistic</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Statistic get(int value) {
		switch (value) {
			case INSTANT_VALUE: return INSTANT;
			case MEAN_VALUE: return MEAN;
			case MIN_VALUE: return MIN;
			case MAX_VALUE: return MAX;
			case ACCUMULATED_VALUE: return ACCUMULATED;
			case PROBABILITY_VALUE: return PROBABILITY;
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
	private Statistic(int value, String name, String literal) {
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
	
} //Statistic
