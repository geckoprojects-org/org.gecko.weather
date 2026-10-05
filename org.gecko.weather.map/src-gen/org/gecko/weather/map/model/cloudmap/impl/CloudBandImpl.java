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
package org.gecko.weather.map.model.cloudmap.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gecko.weather.map.model.cloudmap.CloudBand;
import org.gecko.weather.map.model.cloudmap.CloudmapPackage;

import org.geojson.Geometry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cloud Band</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getValidTime <em>Valid Time</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getRun <em>Run</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getLeadHours <em>Lead Hours</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getAttribution <em>Attribution</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getMinX <em>Min X</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getMinY <em>Min Y</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getMaxX <em>Max X</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.impl.CloudBandImpl#getMaxY <em>Max Y</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CloudBandImpl extends MinimalEObjectImpl.Container implements CloudBand {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeometry() <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeometry()
	 * @generated
	 * @ordered
	 */
	protected static final Geometry GEOMETRY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeometry() <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeometry()
	 * @generated
	 * @ordered
	 */
	protected Geometry geometry = GEOMETRY_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidTime() <em>Valid Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidTime() <em>Valid Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTime()
	 * @generated
	 * @ordered
	 */
	protected Date validTime = VALID_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRun() <em>Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRun()
	 * @generated
	 * @ordered
	 */
	protected static final Date RUN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRun() <em>Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRun()
	 * @generated
	 * @ordered
	 */
	protected Date run = RUN_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeadHours() <em>Lead Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadHours()
	 * @generated
	 * @ordered
	 */
	protected static final int LEAD_HOURS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLeadHours() <em>Lead Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadHours()
	 * @generated
	 * @ordered
	 */
	protected int leadHours = LEAD_HOURS_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected String parameter = PARAMETER_EDEFAULT;

	/**
	 * The default value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected static final double LOWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected double lower = LOWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected static final double UPPER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected double upper = UPPER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected String unit = UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected String color = COLOR_EDEFAULT;

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
	 * The default value of the '{@link #getAttribution() <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribution()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribution() <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribution()
	 * @generated
	 * @ordered
	 */
	protected String attribution = ATTRIBUTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinX() <em>Min X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinX()
	 * @generated
	 * @ordered
	 */
	protected static final double MIN_X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinX() <em>Min X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinX()
	 * @generated
	 * @ordered
	 */
	protected double minX = MIN_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinY() <em>Min Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinY()
	 * @generated
	 * @ordered
	 */
	protected static final double MIN_Y_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinY() <em>Min Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinY()
	 * @generated
	 * @ordered
	 */
	protected double minY = MIN_Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxX() <em>Max X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxX()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxX() <em>Max X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxX()
	 * @generated
	 * @ordered
	 */
	protected double maxX = MAX_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxY() <em>Max Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxY()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_Y_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxY() <em>Max Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxY()
	 * @generated
	 * @ordered
	 */
	protected double maxY = MAX_Y_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudBandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CloudmapPackage.Literals.CLOUD_BAND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__ID, oldId, id));
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeometry(Geometry newGeometry) {
		Geometry oldGeometry = geometry;
		geometry = newGeometry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__GEOMETRY, oldGeometry, geometry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getValidTime() {
		return validTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidTime(Date newValidTime) {
		Date oldValidTime = validTime;
		validTime = newValidTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__VALID_TIME, oldValidTime, validTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getRun() {
		return run;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRun(Date newRun) {
		Date oldRun = run;
		run = newRun;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__RUN, oldRun, run));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLeadHours() {
		return leadHours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeadHours(int newLeadHours) {
		int oldLeadHours = leadHours;
		leadHours = newLeadHours;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__LEAD_HOURS, oldLeadHours, leadHours));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getParameter() {
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParameter(String newParameter) {
		String oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLower() {
		return lower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLower(double newLower) {
		double oldLower = lower;
		lower = newLower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__LOWER, oldLower, lower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getUpper() {
		return upper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpper(double newUpper) {
		double oldUpper = upper;
		upper = newUpper;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__UPPER, oldUpper, upper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnit(String newUnit) {
		String oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		String oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__COLOR, oldColor, color));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAttribution() {
		return attribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttribution(String newAttribution) {
		String oldAttribution = attribution;
		attribution = newAttribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__ATTRIBUTION, oldAttribution, attribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMinX() {
		return minX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinX(double newMinX) {
		double oldMinX = minX;
		minX = newMinX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__MIN_X, oldMinX, minX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMinY() {
		return minY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinY(double newMinY) {
		double oldMinY = minY;
		minY = newMinY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__MIN_Y, oldMinY, minY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxX() {
		return maxX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxX(double newMaxX) {
		double oldMaxX = maxX;
		maxX = newMaxX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__MAX_X, oldMaxX, maxX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxY() {
		return maxY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxY(double newMaxY) {
		double oldMaxY = maxY;
		maxY = newMaxY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CloudmapPackage.CLOUD_BAND__MAX_Y, oldMaxY, maxY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CloudmapPackage.CLOUD_BAND__ID:
				return getId();
			case CloudmapPackage.CLOUD_BAND__NAME:
				return getName();
			case CloudmapPackage.CLOUD_BAND__GEOMETRY:
				return getGeometry();
			case CloudmapPackage.CLOUD_BAND__VALID_TIME:
				return getValidTime();
			case CloudmapPackage.CLOUD_BAND__RUN:
				return getRun();
			case CloudmapPackage.CLOUD_BAND__LEAD_HOURS:
				return getLeadHours();
			case CloudmapPackage.CLOUD_BAND__PARAMETER:
				return getParameter();
			case CloudmapPackage.CLOUD_BAND__LOWER:
				return getLower();
			case CloudmapPackage.CLOUD_BAND__UPPER:
				return getUpper();
			case CloudmapPackage.CLOUD_BAND__UNIT:
				return getUnit();
			case CloudmapPackage.CLOUD_BAND__COLOR:
				return getColor();
			case CloudmapPackage.CLOUD_BAND__SOURCE:
				return getSource();
			case CloudmapPackage.CLOUD_BAND__ATTRIBUTION:
				return getAttribution();
			case CloudmapPackage.CLOUD_BAND__MIN_X:
				return getMinX();
			case CloudmapPackage.CLOUD_BAND__MIN_Y:
				return getMinY();
			case CloudmapPackage.CLOUD_BAND__MAX_X:
				return getMaxX();
			case CloudmapPackage.CLOUD_BAND__MAX_Y:
				return getMaxY();
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
			case CloudmapPackage.CLOUD_BAND__ID:
				setId((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__NAME:
				setName((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__GEOMETRY:
				setGeometry((Geometry)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__VALID_TIME:
				setValidTime((Date)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__RUN:
				setRun((Date)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__LEAD_HOURS:
				setLeadHours((Integer)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__PARAMETER:
				setParameter((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__LOWER:
				setLower((Double)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__UPPER:
				setUpper((Double)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__UNIT:
				setUnit((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__COLOR:
				setColor((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__SOURCE:
				setSource((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__ATTRIBUTION:
				setAttribution((String)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__MIN_X:
				setMinX((Double)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__MIN_Y:
				setMinY((Double)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__MAX_X:
				setMaxX((Double)newValue);
				return;
			case CloudmapPackage.CLOUD_BAND__MAX_Y:
				setMaxY((Double)newValue);
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
			case CloudmapPackage.CLOUD_BAND__ID:
				setId(ID_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__GEOMETRY:
				setGeometry(GEOMETRY_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__VALID_TIME:
				setValidTime(VALID_TIME_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__RUN:
				setRun(RUN_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__LEAD_HOURS:
				setLeadHours(LEAD_HOURS_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__PARAMETER:
				setParameter(PARAMETER_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__UPPER:
				setUpper(UPPER_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__UNIT:
				setUnit(UNIT_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__ATTRIBUTION:
				setAttribution(ATTRIBUTION_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__MIN_X:
				setMinX(MIN_X_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__MIN_Y:
				setMinY(MIN_Y_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__MAX_X:
				setMaxX(MAX_X_EDEFAULT);
				return;
			case CloudmapPackage.CLOUD_BAND__MAX_Y:
				setMaxY(MAX_Y_EDEFAULT);
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
			case CloudmapPackage.CLOUD_BAND__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CloudmapPackage.CLOUD_BAND__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CloudmapPackage.CLOUD_BAND__GEOMETRY:
				return GEOMETRY_EDEFAULT == null ? geometry != null : !GEOMETRY_EDEFAULT.equals(geometry);
			case CloudmapPackage.CLOUD_BAND__VALID_TIME:
				return VALID_TIME_EDEFAULT == null ? validTime != null : !VALID_TIME_EDEFAULT.equals(validTime);
			case CloudmapPackage.CLOUD_BAND__RUN:
				return RUN_EDEFAULT == null ? run != null : !RUN_EDEFAULT.equals(run);
			case CloudmapPackage.CLOUD_BAND__LEAD_HOURS:
				return leadHours != LEAD_HOURS_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__PARAMETER:
				return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
			case CloudmapPackage.CLOUD_BAND__LOWER:
				return lower != LOWER_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__UPPER:
				return upper != UPPER_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__UNIT:
				return UNIT_EDEFAULT == null ? unit != null : !UNIT_EDEFAULT.equals(unit);
			case CloudmapPackage.CLOUD_BAND__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case CloudmapPackage.CLOUD_BAND__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case CloudmapPackage.CLOUD_BAND__ATTRIBUTION:
				return ATTRIBUTION_EDEFAULT == null ? attribution != null : !ATTRIBUTION_EDEFAULT.equals(attribution);
			case CloudmapPackage.CLOUD_BAND__MIN_X:
				return minX != MIN_X_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__MIN_Y:
				return minY != MIN_Y_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__MAX_X:
				return maxX != MAX_X_EDEFAULT;
			case CloudmapPackage.CLOUD_BAND__MAX_Y:
				return maxY != MAX_Y_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", geometry: ");
		result.append(geometry);
		result.append(", validTime: ");
		result.append(validTime);
		result.append(", run: ");
		result.append(run);
		result.append(", leadHours: ");
		result.append(leadHours);
		result.append(", parameter: ");
		result.append(parameter);
		result.append(", lower: ");
		result.append(lower);
		result.append(", upper: ");
		result.append(upper);
		result.append(", unit: ");
		result.append(unit);
		result.append(", color: ");
		result.append(color);
		result.append(", source: ");
		result.append(source);
		result.append(", attribution: ");
		result.append(attribution);
		result.append(", minX: ");
		result.append(minX);
		result.append(", minY: ");
		result.append(minY);
		result.append(", maxX: ");
		result.append(maxX);
		result.append(", maxY: ");
		result.append(maxY);
		result.append(')');
		return result.toString();
	}

} //CloudBandImpl
