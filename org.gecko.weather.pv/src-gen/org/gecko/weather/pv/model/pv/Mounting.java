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
package org.gecko.weather.pv.model.pv;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Mounting</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * How the modules sit — it decides how warm they get (Faiman heat-loss coefficients).
 * <!-- end-model-doc -->
 * @see org.gecko.weather.pv.model.pv.PvPackage#getMounting()
 * @model
 * @generated
 */
@ProviderType
public enum Mounting implements Enumerator {
	/**
	 * The '<em><b>ROOF MOUNTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * On a pitched roof with a ventilated gap behind (the common case).
	 * <!-- end-model-doc -->
	 * @see #ROOF_MOUNTED_VALUE
	 * @generated
	 * @ordered
	 */
	ROOF_MOUNTED(0, "ROOF_MOUNTED", "ROOF_MOUNTED"),

	/**
	 * The '<em><b>ROOF INTEGRATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In the roof skin, little ventilation — warmest.
	 * <!-- end-model-doc -->
	 * @see #ROOF_INTEGRATED_VALUE
	 * @generated
	 * @ordered
	 */
	ROOF_INTEGRATED(1, "ROOF_INTEGRATED", "ROOF_INTEGRATED"),

	/**
	 * The '<em><b>OPEN RACK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Free-standing, on a flat roof or the ground — coolest.
	 * <!-- end-model-doc -->
	 * @see #OPEN_RACK_VALUE
	 * @generated
	 * @ordered
	 */
	OPEN_RACK(2, "OPEN_RACK", "OPEN_RACK");

	/**
	 * The '<em><b>ROOF MOUNTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * On a pitched roof with a ventilated gap behind (the common case).
	 * <!-- end-model-doc -->
	 * @see #ROOF_MOUNTED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ROOF_MOUNTED_VALUE = 0;

	/**
	 * The '<em><b>ROOF INTEGRATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In the roof skin, little ventilation — warmest.
	 * <!-- end-model-doc -->
	 * @see #ROOF_INTEGRATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ROOF_INTEGRATED_VALUE = 1;

	/**
	 * The '<em><b>OPEN RACK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Free-standing, on a flat roof or the ground — coolest.
	 * <!-- end-model-doc -->
	 * @see #OPEN_RACK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OPEN_RACK_VALUE = 2;

	/**
	 * An array of all the '<em><b>Mounting</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Mounting[] VALUES_ARRAY =
		new Mounting[] {
			ROOF_MOUNTED,
			ROOF_INTEGRATED,
			OPEN_RACK,
		};

	/**
	 * A public read-only list of all the '<em><b>Mounting</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Mounting> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Mounting</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Mounting get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Mounting result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Mounting</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Mounting getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Mounting result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Mounting</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Mounting get(int value) {
		switch (value) {
			case ROOF_MOUNTED_VALUE: return ROOF_MOUNTED;
			case ROOF_INTEGRATED_VALUE: return ROOF_INTEGRATED;
			case OPEN_RACK_VALUE: return OPEN_RACK;
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
	private Mounting(int value, String name, String literal) {
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
	
} //Mounting
