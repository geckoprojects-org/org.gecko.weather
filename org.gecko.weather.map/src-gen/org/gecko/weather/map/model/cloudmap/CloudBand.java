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
package org.gecko.weather.map.model.cloudmap;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import org.geojson.Geometry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cloud Band</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Where the total cloud cover of one hour lies in [lower, upper) — the last band includes 100 %. The field is coarsened before the outlines are traced, so the shapes are a picture and a query by band, not values per cell.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getValidTime <em>Valid Time</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getRun <em>Run</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLeadHours <em>Lead Hours</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLower <em>Lower</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getColor <em>Color</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getSource <em>Source</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getAttribution <em>Attribution</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinX <em>Min X</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinY <em>Min Y</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxX <em>Max X</em>}</li>
 *   <li>{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxY <em>Max Y</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand()
 * @model annotation="https://eclipse.org/fennec/ogc/features collection='true' id='cloudcover' title='Bew\366lkung jetzt (ICON-D2)' description='Gesamtbedeckungsgrad clct der aktuellen Stunde aus dem neuesten ICON-D2-Lauf, in Vierteln, rund um die Standorte. Datenbasis: Deutscher Wetterdienst.' geometry='geometry' bbox='minX,minY,maxX,maxY' temporal='validTime' layerGroup='Wetter' style='{\"color\":\"#78909c\",\"fillOpacity\":0.45,\"labels\":false}'"
 * @generated
 */
@ProviderType
public interface CloudBand extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parameter, run, valid time and band, e.g. clct-2026100300-20261003T0300Z-75.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A line for a popup, e.g. "Bewölkung 75–100 %".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A MultiPolygon in CRS84, outer rings counter-clockwise.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Geometry</em>' attribute.
	 * @see #setGeometry(Geometry)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Geometry()
	 * @model dataType="org.gecko.weather.map.model.cloudmap.GeoJsonGeometry"
	 * @generated
	 */
	Geometry getGeometry();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getGeometry <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geometry</em>' attribute.
	 * @see #getGeometry()
	 * @generated
	 */
	void setGeometry(Geometry value);

	/**
	 * Returns the value of the '<em><b>Valid Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The instant the field is valid for, on the full hour.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Valid Time</em>' attribute.
	 * @see #setValidTime(Date)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_ValidTime()
	 * @model required="true"
	 * @generated
	 */
	Date getValidTime();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getValidTime <em>Valid Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Time</em>' attribute.
	 * @see #getValidTime()
	 * @generated
	 */
	void setValidTime(Date value);

	/**
	 * Returns the value of the '<em><b>Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The model run (issue time) the field comes from.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Run</em>' attribute.
	 * @see #setRun(Date)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Run()
	 * @model required="true"
	 * @generated
	 */
	Date getRun();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getRun <em>Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run</em>' attribute.
	 * @see #getRun()
	 * @generated
	 */
	void setRun(Date value);

	/**
	 * Returns the value of the '<em><b>Lead Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lead Hours</em>' attribute.
	 * @see #setLeadHours(int)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_LeadHours()
	 * @model
	 * @generated
	 */
	int getLeadHours();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLeadHours <em>Lead Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lead Hours</em>' attribute.
	 * @see #getLeadHours()
	 * @generated
	 */
	void setLeadHours(int value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The source's parameter name, clct.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter</em>' attribute.
	 * @see #setParameter(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Parameter()
	 * @model
	 * @generated
	 */
	String getParameter();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(String value);

	/**
	 * Returns the value of the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower</em>' attribute.
	 * @see #setLower(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Lower()
	 * @model
	 * @generated
	 */
	double getLower();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(double value);

	/**
	 * Returns the value of the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper</em>' attribute.
	 * @see #setUpper(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Upper()
	 * @model
	 * @generated
	 */
	double getUpper();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(double value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Unit()
	 * @model
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CSS colour of the band — the viewer of emf.ogc.features fills a feature with its color property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provider and product, dwd/ICON-D2.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Source()
	 * @model
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

	/**
	 * Returns the value of the '<em><b>Attribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribution</em>' attribute.
	 * @see #setAttribution(String)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_Attribution()
	 * @model
	 * @generated
	 */
	String getAttribution();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getAttribution <em>Attribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribution</em>' attribute.
	 * @see #getAttribution()
	 * @generated
	 */
	void setAttribution(String value);

	/**
	 * Returns the value of the '<em><b>Min X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min X</em>' attribute.
	 * @see #setMinX(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_MinX()
	 * @model
	 * @generated
	 */
	double getMinX();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinX <em>Min X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min X</em>' attribute.
	 * @see #getMinX()
	 * @generated
	 */
	void setMinX(double value);

	/**
	 * Returns the value of the '<em><b>Min Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Y</em>' attribute.
	 * @see #setMinY(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_MinY()
	 * @model
	 * @generated
	 */
	double getMinY();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMinY <em>Min Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Y</em>' attribute.
	 * @see #getMinY()
	 * @generated
	 */
	void setMinY(double value);

	/**
	 * Returns the value of the '<em><b>Max X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max X</em>' attribute.
	 * @see #setMaxX(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_MaxX()
	 * @model
	 * @generated
	 */
	double getMaxX();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxX <em>Max X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max X</em>' attribute.
	 * @see #getMaxX()
	 * @generated
	 */
	void setMaxX(double value);

	/**
	 * Returns the value of the '<em><b>Max Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Y</em>' attribute.
	 * @see #setMaxY(double)
	 * @see org.gecko.weather.map.model.cloudmap.CloudmapPackage#getCloudBand_MaxY()
	 * @model
	 * @generated
	 */
	double getMaxY();

	/**
	 * Sets the value of the '{@link org.gecko.weather.map.model.cloudmap.CloudBand#getMaxY <em>Max Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Y</em>' attribute.
	 * @see #getMaxY()
	 * @generated
	 */
	void setMaxY(double value);

} // CloudBand
