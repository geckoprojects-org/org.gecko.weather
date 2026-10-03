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
 * A representation of the literals of the enumeration '<em><b>Measurement Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Canonical, provider-neutral quantity. Variants of one quantity (height, layer, aggregation window, probability threshold) are expressed by the qualifiers on MeasuredValue, not by further kinds. Canonical units per kind are documented in docs/10-model.md.
 * <!-- end-model-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasurementKind()
 * @model
 * @generated
 */
@ProviderType
public enum MeasurementKind implements Enumerator {
	/**
	 * The '<em><b>AIR TEMPERATURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AIR_TEMPERATURE_VALUE
	 * @generated
	 * @ordered
	 */
	AIR_TEMPERATURE(0, "AIR_TEMPERATURE", "AIR_TEMPERATURE"),

	/**
	 * The '<em><b>DEW POINT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEW_POINT_VALUE
	 * @generated
	 * @ordered
	 */
	DEW_POINT(1, "DEW_POINT", "DEW_POINT"),

	/**
	 * The '<em><b>RELATIVE HUMIDITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIVE_HUMIDITY_VALUE
	 * @generated
	 * @ordered
	 */
	RELATIVE_HUMIDITY(2, "RELATIVE_HUMIDITY", "RELATIVE_HUMIDITY"),

	/**
	 * The '<em><b>SURFACE PRESSURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SURFACE_PRESSURE_VALUE
	 * @generated
	 * @ordered
	 */
	SURFACE_PRESSURE(3, "SURFACE_PRESSURE", "SURFACE_PRESSURE"),

	/**
	 * The '<em><b>WIND SPEED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_SPEED_VALUE
	 * @generated
	 * @ordered
	 */
	WIND_SPEED(4, "WIND_SPEED", "WIND_SPEED"),

	/**
	 * The '<em><b>WIND DIRECTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_DIRECTION_VALUE
	 * @generated
	 * @ordered
	 */
	WIND_DIRECTION(5, "WIND_DIRECTION", "WIND_DIRECTION"),

	/**
	 * The '<em><b>WIND GUST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_GUST_VALUE
	 * @generated
	 * @ordered
	 */
	WIND_GUST(6, "WIND_GUST", "WIND_GUST"),

	/**
	 * The '<em><b>CLOUD COVER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_COVER_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD_COVER(7, "CLOUD_COVER", "CLOUD_COVER"),

	/**
	 * The '<em><b>GLOBAL RADIATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GLOBAL_RADIATION_VALUE
	 * @generated
	 * @ordered
	 */
	GLOBAL_RADIATION(8, "GLOBAL_RADIATION", "GLOBAL_RADIATION"),

	/**
	 * The '<em><b>DIRECT RADIATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_RADIATION_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECT_RADIATION(9, "DIRECT_RADIATION", "DIRECT_RADIATION"),

	/**
	 * The '<em><b>DIFFUSE RADIATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIFFUSE_RADIATION_VALUE
	 * @generated
	 * @ordered
	 */
	DIFFUSE_RADIATION(10, "DIFFUSE_RADIATION", "DIFFUSE_RADIATION"),

	/**
	 * The '<em><b>SUNSHINE DURATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUNSHINE_DURATION_VALUE
	 * @generated
	 * @ordered
	 */
	SUNSHINE_DURATION(11, "SUNSHINE_DURATION", "SUNSHINE_DURATION"),

	/**
	 * The '<em><b>PRECIPITATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRECIPITATION_VALUE
	 * @generated
	 * @ordered
	 */
	PRECIPITATION(12, "PRECIPITATION", "PRECIPITATION"),

	/**
	 * The '<em><b>SNOW WATER EQUIVALENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SNOW_WATER_EQUIVALENT_VALUE
	 * @generated
	 * @ordered
	 */
	SNOW_WATER_EQUIVALENT(13, "SNOW_WATER_EQUIVALENT", "SNOW_WATER_EQUIVALENT"),

	/**
	 * The '<em><b>FOG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOG_VALUE
	 * @generated
	 * @ordered
	 */
	FOG(14, "FOG", "FOG"),

	/**
	 * The '<em><b>VISIBILITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VISIBILITY_VALUE
	 * @generated
	 * @ordered
	 */
	VISIBILITY(15, "VISIBILITY", "VISIBILITY"),

	/**
	 * The '<em><b>UV INDEX</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UV_INDEX_VALUE
	 * @generated
	 * @ordered
	 */
	UV_INDEX(16, "UV_INDEX", "UV_INDEX"),

	/**
	 * The '<em><b>SIGNIFICANT WEATHER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIGNIFICANT_WEATHER_VALUE
	 * @generated
	 * @ordered
	 */
	SIGNIFICANT_WEATHER(17, "SIGNIFICANT_WEATHER", "SIGNIFICANT_WEATHER"),

	/**
	 * The '<em><b>SUN ELEVATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUN_ELEVATION_VALUE
	 * @generated
	 * @ordered
	 */
	SUN_ELEVATION(18, "SUN_ELEVATION", "SUN_ELEVATION"),

	/**
	 * The '<em><b>SUN AZIMUTH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUN_AZIMUTH_VALUE
	 * @generated
	 * @ordered
	 */
	SUN_AZIMUTH(19, "SUN_AZIMUTH", "SUN_AZIMUTH");

	/**
	 * The '<em><b>AIR TEMPERATURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AIR_TEMPERATURE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AIR_TEMPERATURE_VALUE = 0;

	/**
	 * The '<em><b>DEW POINT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEW_POINT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEW_POINT_VALUE = 1;

	/**
	 * The '<em><b>RELATIVE HUMIDITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIVE_HUMIDITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RELATIVE_HUMIDITY_VALUE = 2;

	/**
	 * The '<em><b>SURFACE PRESSURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SURFACE_PRESSURE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SURFACE_PRESSURE_VALUE = 3;

	/**
	 * The '<em><b>WIND SPEED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_SPEED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WIND_SPEED_VALUE = 4;

	/**
	 * The '<em><b>WIND DIRECTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_DIRECTION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WIND_DIRECTION_VALUE = 5;

	/**
	 * The '<em><b>WIND GUST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIND_GUST
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WIND_GUST_VALUE = 6;

	/**
	 * The '<em><b>CLOUD COVER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_COVER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_COVER_VALUE = 7;

	/**
	 * The '<em><b>GLOBAL RADIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GLOBAL_RADIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GLOBAL_RADIATION_VALUE = 8;

	/**
	 * The '<em><b>DIRECT RADIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_RADIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIRECT_RADIATION_VALUE = 9;

	/**
	 * The '<em><b>DIFFUSE RADIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIFFUSE_RADIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIFFUSE_RADIATION_VALUE = 10;

	/**
	 * The '<em><b>SUNSHINE DURATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUNSHINE_DURATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUNSHINE_DURATION_VALUE = 11;

	/**
	 * The '<em><b>PRECIPITATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRECIPITATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PRECIPITATION_VALUE = 12;

	/**
	 * The '<em><b>SNOW WATER EQUIVALENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SNOW_WATER_EQUIVALENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SNOW_WATER_EQUIVALENT_VALUE = 13;

	/**
	 * The '<em><b>FOG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FOG_VALUE = 14;

	/**
	 * The '<em><b>VISIBILITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VISIBILITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int VISIBILITY_VALUE = 15;

	/**
	 * The '<em><b>UV INDEX</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UV_INDEX
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UV_INDEX_VALUE = 16;

	/**
	 * The '<em><b>SIGNIFICANT WEATHER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIGNIFICANT_WEATHER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SIGNIFICANT_WEATHER_VALUE = 17;

	/**
	 * The '<em><b>SUN ELEVATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUN_ELEVATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUN_ELEVATION_VALUE = 18;

	/**
	 * The '<em><b>SUN AZIMUTH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUN_AZIMUTH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUN_AZIMUTH_VALUE = 19;

	/**
	 * An array of all the '<em><b>Measurement Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MeasurementKind[] VALUES_ARRAY =
		new MeasurementKind[] {
			AIR_TEMPERATURE,
			DEW_POINT,
			RELATIVE_HUMIDITY,
			SURFACE_PRESSURE,
			WIND_SPEED,
			WIND_DIRECTION,
			WIND_GUST,
			CLOUD_COVER,
			GLOBAL_RADIATION,
			DIRECT_RADIATION,
			DIFFUSE_RADIATION,
			SUNSHINE_DURATION,
			PRECIPITATION,
			SNOW_WATER_EQUIVALENT,
			FOG,
			VISIBILITY,
			UV_INDEX,
			SIGNIFICANT_WEATHER,
			SUN_ELEVATION,
			SUN_AZIMUTH,
		};

	/**
	 * A public read-only list of all the '<em><b>Measurement Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MeasurementKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Measurement Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MeasurementKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MeasurementKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Measurement Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MeasurementKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MeasurementKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Measurement Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MeasurementKind get(int value) {
		switch (value) {
			case AIR_TEMPERATURE_VALUE: return AIR_TEMPERATURE;
			case DEW_POINT_VALUE: return DEW_POINT;
			case RELATIVE_HUMIDITY_VALUE: return RELATIVE_HUMIDITY;
			case SURFACE_PRESSURE_VALUE: return SURFACE_PRESSURE;
			case WIND_SPEED_VALUE: return WIND_SPEED;
			case WIND_DIRECTION_VALUE: return WIND_DIRECTION;
			case WIND_GUST_VALUE: return WIND_GUST;
			case CLOUD_COVER_VALUE: return CLOUD_COVER;
			case GLOBAL_RADIATION_VALUE: return GLOBAL_RADIATION;
			case DIRECT_RADIATION_VALUE: return DIRECT_RADIATION;
			case DIFFUSE_RADIATION_VALUE: return DIFFUSE_RADIATION;
			case SUNSHINE_DURATION_VALUE: return SUNSHINE_DURATION;
			case PRECIPITATION_VALUE: return PRECIPITATION;
			case SNOW_WATER_EQUIVALENT_VALUE: return SNOW_WATER_EQUIVALENT;
			case FOG_VALUE: return FOG;
			case VISIBILITY_VALUE: return VISIBILITY;
			case UV_INDEX_VALUE: return UV_INDEX;
			case SIGNIFICANT_WEATHER_VALUE: return SIGNIFICANT_WEATHER;
			case SUN_ELEVATION_VALUE: return SUN_ELEVATION;
			case SUN_AZIMUTH_VALUE: return SUN_AZIMUTH;
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
	private MeasurementKind(int value, String name, String literal) {
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
	
} //MeasurementKind
