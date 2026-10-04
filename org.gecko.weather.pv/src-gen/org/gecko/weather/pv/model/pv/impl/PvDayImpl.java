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
package org.gecko.weather.pv.model.pv.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.pv.model.pv.PvDay;
import org.gecko.weather.pv.model.pv.PvPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Day</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getDate <em>Date</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getEnergy <em>Energy</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getPeakPower <em>Peak Power</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getPeakTime <em>Peak Time</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getSpecificYield <em>Specific Yield</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getHoursCovered <em>Hours Covered</em>}</li>
 *   <li>{@link org.gecko.weather.pv.model.pv.impl.PvDayImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PvDayImpl extends MinimalEObjectImpl.Container implements PvDay {
	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final String DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected String date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnergy() <em>Energy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergy()
	 * @generated
	 * @ordered
	 */
	protected static final double ENERGY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEnergy() <em>Energy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnergy()
	 * @generated
	 * @ordered
	 */
	protected double energy = ENERGY_EDEFAULT;

	/**
	 * This is true if the Energy attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean energyESet;

	/**
	 * The default value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected static final double PEAK_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPeakPower() <em>Peak Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakPower()
	 * @generated
	 * @ordered
	 */
	protected double peakPower = PEAK_POWER_EDEFAULT;

	/**
	 * This is true if the Peak Power attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean peakPowerESet;

	/**
	 * The default value of the '{@link #getPeakTime() <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date PEAK_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPeakTime() <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakTime()
	 * @generated
	 * @ordered
	 */
	protected Date peakTime = PEAK_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpecificYield() <em>Specific Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificYield()
	 * @generated
	 * @ordered
	 */
	protected static final double SPECIFIC_YIELD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSpecificYield() <em>Specific Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificYield()
	 * @generated
	 * @ordered
	 */
	protected double specificYield = SPECIFIC_YIELD_EDEFAULT;

	/**
	 * This is true if the Specific Yield attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean specificYieldESet;

	/**
	 * The default value of the '{@link #getHoursCovered() <em>Hours Covered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHoursCovered()
	 * @generated
	 * @ordered
	 */
	protected static final int HOURS_COVERED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHoursCovered() <em>Hours Covered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHoursCovered()
	 * @generated
	 * @ordered
	 */
	protected int hoursCovered = HOURS_COVERED_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PvDayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PvPackage.Literals.PV_DAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(String newDate) {
		String oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getEnergy() {
		return energy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnergy(double newEnergy) {
		double oldEnergy = energy;
		energy = newEnergy;
		boolean oldEnergyESet = energyESet;
		energyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__ENERGY, oldEnergy, energy, !oldEnergyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetEnergy() {
		double oldEnergy = energy;
		boolean oldEnergyESet = energyESet;
		energy = ENERGY_EDEFAULT;
		energyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_DAY__ENERGY, oldEnergy, ENERGY_EDEFAULT, oldEnergyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetEnergy() {
		return energyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPeakPower() {
		return peakPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeakPower(double newPeakPower) {
		double oldPeakPower = peakPower;
		peakPower = newPeakPower;
		boolean oldPeakPowerESet = peakPowerESet;
		peakPowerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__PEAK_POWER, oldPeakPower, peakPower, !oldPeakPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPeakPower() {
		double oldPeakPower = peakPower;
		boolean oldPeakPowerESet = peakPowerESet;
		peakPower = PEAK_POWER_EDEFAULT;
		peakPowerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_DAY__PEAK_POWER, oldPeakPower, PEAK_POWER_EDEFAULT, oldPeakPowerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPeakPower() {
		return peakPowerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getPeakTime() {
		return peakTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeakTime(Date newPeakTime) {
		Date oldPeakTime = peakTime;
		peakTime = newPeakTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__PEAK_TIME, oldPeakTime, peakTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSpecificYield() {
		return specificYield;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpecificYield(double newSpecificYield) {
		double oldSpecificYield = specificYield;
		specificYield = newSpecificYield;
		boolean oldSpecificYieldESet = specificYieldESet;
		specificYieldESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__SPECIFIC_YIELD, oldSpecificYield, specificYield, !oldSpecificYieldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSpecificYield() {
		double oldSpecificYield = specificYield;
		boolean oldSpecificYieldESet = specificYieldESet;
		specificYield = SPECIFIC_YIELD_EDEFAULT;
		specificYieldESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PvPackage.PV_DAY__SPECIFIC_YIELD, oldSpecificYield, SPECIFIC_YIELD_EDEFAULT, oldSpecificYieldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSpecificYield() {
		return specificYieldESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHoursCovered() {
		return hoursCovered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHoursCovered(int newHoursCovered) {
		int oldHoursCovered = hoursCovered;
		hoursCovered = newHoursCovered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__HOURS_COVERED, oldHoursCovered, hoursCovered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PvPackage.PV_DAY__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PvPackage.PV_DAY__DATE:
				return getDate();
			case PvPackage.PV_DAY__ENERGY:
				return getEnergy();
			case PvPackage.PV_DAY__PEAK_POWER:
				return getPeakPower();
			case PvPackage.PV_DAY__PEAK_TIME:
				return getPeakTime();
			case PvPackage.PV_DAY__SPECIFIC_YIELD:
				return getSpecificYield();
			case PvPackage.PV_DAY__HOURS_COVERED:
				return getHoursCovered();
			case PvPackage.PV_DAY__SOURCE:
				return getSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PvPackage.PV_DAY__DATE:
				setDate((String)newValue);
				return;
			case PvPackage.PV_DAY__ENERGY:
				setEnergy((Double)newValue);
				return;
			case PvPackage.PV_DAY__PEAK_POWER:
				setPeakPower((Double)newValue);
				return;
			case PvPackage.PV_DAY__PEAK_TIME:
				setPeakTime((Date)newValue);
				return;
			case PvPackage.PV_DAY__SPECIFIC_YIELD:
				setSpecificYield((Double)newValue);
				return;
			case PvPackage.PV_DAY__HOURS_COVERED:
				setHoursCovered((Integer)newValue);
				return;
			case PvPackage.PV_DAY__SOURCE:
				setSource((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PvPackage.PV_DAY__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case PvPackage.PV_DAY__ENERGY:
				unsetEnergy();
				return;
			case PvPackage.PV_DAY__PEAK_POWER:
				unsetPeakPower();
				return;
			case PvPackage.PV_DAY__PEAK_TIME:
				setPeakTime(PEAK_TIME_EDEFAULT);
				return;
			case PvPackage.PV_DAY__SPECIFIC_YIELD:
				unsetSpecificYield();
				return;
			case PvPackage.PV_DAY__HOURS_COVERED:
				setHoursCovered(HOURS_COVERED_EDEFAULT);
				return;
			case PvPackage.PV_DAY__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PvPackage.PV_DAY__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case PvPackage.PV_DAY__ENERGY:
				return isSetEnergy();
			case PvPackage.PV_DAY__PEAK_POWER:
				return isSetPeakPower();
			case PvPackage.PV_DAY__PEAK_TIME:
				return PEAK_TIME_EDEFAULT == null ? peakTime != null : !PEAK_TIME_EDEFAULT.equals(peakTime);
			case PvPackage.PV_DAY__SPECIFIC_YIELD:
				return isSetSpecificYield();
			case PvPackage.PV_DAY__HOURS_COVERED:
				return hoursCovered != HOURS_COVERED_EDEFAULT;
			case PvPackage.PV_DAY__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (date: ");
		result.append(date);
		result.append(", energy: ");
		if (energyESet) result.append(energy); else result.append("<unset>");
		result.append(", peakPower: ");
		if (peakPowerESet) result.append(peakPower); else result.append("<unset>");
		result.append(", peakTime: ");
		result.append(peakTime);
		result.append(", specificYield: ");
		if (specificYieldESet) result.append(specificYield); else result.append("<unset>");
		result.append(", hoursCovered: ");
		result.append(hoursCovered);
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}

} //PvDayImpl
