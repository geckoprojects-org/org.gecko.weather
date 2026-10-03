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

import java.time.Duration;
import java.time.Instant;

import org.eclipse.emf.ecore.EObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measured Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One value of one quantity at one instant from one source. It stands on its own: provenance says where it came from, uncertainty says how much to trust it. Nothing in the model merges values from different sources.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getKind <em>Kind</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getLevel <em>Level</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getStatistic <em>Statistic</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getThreshold <em>Threshold</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getThresholdUnit <em>Threshold Unit</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getValidAt <em>Valid At</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getValue <em>Value</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getCode <em>Code</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getProvenance <em>Provenance</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MeasuredValue#getUncertainty <em>Uncertainty</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue()
 * @model
 * @generated
 */
@ProviderType
public interface MeasuredValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.MeasurementKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.gecko.weather.model.weather.MeasurementKind
	 * @see #setKind(MeasurementKind)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Kind()
	 * @model required="true"
	 * @generated
	 */
	MeasurementKind getKind();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.gecko.weather.model.weather.MeasurementKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(MeasurementKind value);

	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * The default value is <code>"UNSPECIFIED"</code>.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.Level}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see org.gecko.weather.model.weather.Level
	 * @see #setLevel(Level)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Level()
	 * @model default="UNSPECIFIED"
	 * @generated
	 */
	Level getLevel();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see org.gecko.weather.model.weather.Level
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(Level value);

	/**
	 * Returns the value of the '<em><b>Statistic</b></em>' attribute.
	 * The default value is <code>"INSTANT"</code>.
	 * The literals are from the enumeration {@link org.gecko.weather.model.weather.Statistic}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statistic</em>' attribute.
	 * @see org.gecko.weather.model.weather.Statistic
	 * @see #setStatistic(Statistic)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Statistic()
	 * @model default="INSTANT"
	 * @generated
	 */
	Statistic getStatistic();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getStatistic <em>Statistic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statistic</em>' attribute.
	 * @see org.gecko.weather.model.weather.Statistic
	 * @see #getStatistic()
	 * @generated
	 */
	void setStatistic(Statistic value);

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Aggregation window ending at validAt; unset for INSTANT values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #isSetPeriod()
	 * @see #unsetPeriod()
	 * @see #setPeriod(Duration)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Period()
	 * @model unsettable="true" dataType="org.gecko.weather.model.weather.Duration"
	 * @generated
	 */
	Duration getPeriod();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #isSetPeriod()
	 * @see #unsetPeriod()
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(Duration value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(Duration)
	 * @generated
	 */
	void unsetPeriod();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getPeriod <em>Period</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Period</em>' attribute is set.
	 * @see #unsetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(Duration)
	 * @generated
	 */
	boolean isSetPeriod();

	/**
	 * Returns the value of the '<em><b>Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For PROBABILITY: the threshold the quantity has to reach, in thresholdUnit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Threshold</em>' attribute.
	 * @see #isSetThreshold()
	 * @see #unsetThreshold()
	 * @see #setThreshold(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Threshold()
	 * @model unsettable="true"
	 * @generated
	 */
	double getThreshold();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getThreshold <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threshold</em>' attribute.
	 * @see #isSetThreshold()
	 * @see #unsetThreshold()
	 * @see #getThreshold()
	 * @generated
	 */
	void setThreshold(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getThreshold <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetThreshold()
	 * @see #getThreshold()
	 * @see #setThreshold(double)
	 * @generated
	 */
	void unsetThreshold();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getThreshold <em>Threshold</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Threshold</em>' attribute is set.
	 * @see #unsetThreshold()
	 * @see #getThreshold()
	 * @see #setThreshold(double)
	 * @generated
	 */
	boolean isSetThreshold();

	/**
	 * Returns the value of the '<em><b>Threshold Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Threshold Unit</em>' attribute.
	 * @see #setThresholdUnit(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_ThresholdUnit()
	 * @model
	 * @generated
	 */
	String getThresholdUnit();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getThresholdUnit <em>Threshold Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threshold Unit</em>' attribute.
	 * @see #getThresholdUnit()
	 * @generated
	 */
	void setThresholdUnit(String value);

	/**
	 * Returns the value of the '<em><b>Valid At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The instant the value is valid for (end of the period for aggregated values).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Valid At</em>' attribute.
	 * @see #setValidAt(Instant)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_ValidAt()
	 * @model dataType="org.gecko.weather.model.weather.Instant" required="true"
	 * @generated
	 */
	Instant getValidAt();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getValidAt <em>Valid At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid At</em>' attribute.
	 * @see #getValidAt()
	 * @generated
	 */
	void setValidAt(Instant value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Numeric value in the canonical unit of the kind. Unset for coded kinds and for timesteps the source left empty.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #setValue(double)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Value()
	 * @model unsettable="true"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValue()
	 * @see #getValue()
	 * @see #setValue(double)
	 * @generated
	 */
	void unsetValue();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getValue <em>Value</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Value</em>' attribute is set.
	 * @see #unsetValue()
	 * @see #getValue()
	 * @see #setValue(double)
	 * @generated
	 */
	boolean isSetValue();

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Coded value for coded kinds — SIGNIFICANT_WEATHER carries the WMO 4677 ww code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #isSetCode()
	 * @see #unsetCode()
	 * @see #setCode(int)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Code()
	 * @model unsettable="true"
	 * @generated
	 */
	int getCode();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #isSetCode()
	 * @see #unsetCode()
	 * @see #getCode()
	 * @generated
	 */
	void setCode(int value);

	/**
	 * Unsets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCode()
	 * @see #getCode()
	 * @see #setCode(int)
	 * @generated
	 */
	void unsetCode();

	/**
	 * Returns whether the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getCode <em>Code</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Code</em>' attribute is set.
	 * @see #unsetCode()
	 * @see #getCode()
	 * @see #setCode(int)
	 * @generated
	 */
	boolean isSetCode();

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * UCUM unit string of value, always the canonical unit of the kind. Carried on the value so that a value is interpretable on its own.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Unit()
	 * @model required="true"
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

	/**
	 * Returns the value of the '<em><b>Provenance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provenance</em>' containment reference.
	 * @see #setProvenance(Provenance)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Provenance()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Provenance getProvenance();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getProvenance <em>Provenance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provenance</em>' containment reference.
	 * @see #getProvenance()
	 * @generated
	 */
	void setProvenance(Provenance value);

	/**
	 * Returns the value of the '<em><b>Uncertainty</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uncertainty</em>' containment reference.
	 * @see #setUncertainty(Uncertainty)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMeasuredValue_Uncertainty()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Uncertainty getUncertainty();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MeasuredValue#getUncertainty <em>Uncertainty</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uncertainty</em>' containment reference.
	 * @see #getUncertainty()
	 * @generated
	 */
	void setUncertainty(Uncertainty value);

} // MeasuredValue
