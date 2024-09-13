/*
 * Copyright (c) 2012 - 2024 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.WeatherPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MOSMIXS Weather Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustLastHour <em>Wind Gust Last Hour</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustLastThreeHours <em>Wind Gust Last Three Hours</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustMaxLast12Hours <em>Wind Gust Max Last12 Hours</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustProb25 <em>Wind Gust Prob25</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustProb40 <em>Wind Gust Prob40</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getWindGustProb55 <em>Wind Gust Prob55</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverTotal <em>Cloud Cover Total</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverBelow500 <em>Cloud Cover Below500</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverEffective <em>Cloud Cover Effective</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverHigh <em>Cloud Cover High</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverMid <em>Cloud Cover Mid</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getCloudCoverLow <em>Cloud Cover Low</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getIrRadianceGlobal <em>Ir Radiance Global</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getSurfacePressure <em>Surface Pressure</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger02Last6 <em>Precipitation Larger02 Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger50Last6 <em>Precipitation Larger50 Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger02LastDay <em>Precipitation Larger02 Last Day</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger50LastDay <em>Precipitation Larger50 Last Day</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger00Last12 <em>Precipitation Larger00 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger02Last12 <em>Precipitation Larger02 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger10Last12 <em>Precipitation Larger10 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationLarger50Last12 <em>Precipitation Larger50 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationSignificantWeatherTotal <em>Precipitation Significant Weather Total</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPrecipitationSignificantWeatherLast3 <em>Precipitation Significant Weather Last3</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getSnowRainEqLast1 <em>Snow Rain Eq Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getSnowRainEqLast3 <em>Snow Rain Eq Last3</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getSunshineDurationLast1 <em>Sunshine Duration Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getTempAboveSurface5 <em>Temp Above Surface5</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getTempAboveSurface200 <em>Temp Above Surface200</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getTempDewpointAboveSurface200 <em>Temp Dewpoint Above Surface200</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getTempMinLast12 <em>Temp Min Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getTempMaxLast12 <em>Temp Max Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getPastWeather <em>Past Weather</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getSignificantWeather <em>Significant Weather</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getFogPropLast1 <em>Fog Prop Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getFogPropLast6 <em>Fog Prop Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.impl.MOSMIXSWeatherReportImpl#getFogPropLast12 <em>Fog Prop Last12</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MOSMIXSWeatherReportImpl extends WeatherReportImpl implements MOSMIXSWeatherReport {
	/**
	 * The default value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_DIRECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindDirection() <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindDirection()
	 * @generated
	 * @ordered
	 */
	protected Float windDirection = WIND_DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindSpeed() <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_SPEED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindSpeed() <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindSpeed()
	 * @generated
	 * @ordered
	 */
	protected Float windSpeed = WIND_SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustLastHour() <em>Wind Gust Last Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustLastHour()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_LAST_HOUR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustLastHour() <em>Wind Gust Last Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustLastHour()
	 * @generated
	 * @ordered
	 */
	protected Float windGustLastHour = WIND_GUST_LAST_HOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustLastThreeHours() <em>Wind Gust Last Three Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustLastThreeHours()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_LAST_THREE_HOURS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustLastThreeHours() <em>Wind Gust Last Three Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustLastThreeHours()
	 * @generated
	 * @ordered
	 */
	protected Float windGustLastThreeHours = WIND_GUST_LAST_THREE_HOURS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustMaxLast12Hours() <em>Wind Gust Max Last12 Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustMaxLast12Hours()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_MAX_LAST12_HOURS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustMaxLast12Hours() <em>Wind Gust Max Last12 Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustMaxLast12Hours()
	 * @generated
	 * @ordered
	 */
	protected Float windGustMaxLast12Hours = WIND_GUST_MAX_LAST12_HOURS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustProb25() <em>Wind Gust Prob25</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb25()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_PROB25_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustProb25() <em>Wind Gust Prob25</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb25()
	 * @generated
	 * @ordered
	 */
	protected Float windGustProb25 = WIND_GUST_PROB25_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustProb40() <em>Wind Gust Prob40</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb40()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_PROB40_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustProb40() <em>Wind Gust Prob40</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb40()
	 * @generated
	 * @ordered
	 */
	protected Float windGustProb40 = WIND_GUST_PROB40_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindGustProb55() <em>Wind Gust Prob55</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb55()
	 * @generated
	 * @ordered
	 */
	protected static final Float WIND_GUST_PROB55_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWindGustProb55() <em>Wind Gust Prob55</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindGustProb55()
	 * @generated
	 * @ordered
	 */
	protected Float windGustProb55 = WIND_GUST_PROB55_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverTotal() <em>Cloud Cover Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverTotal()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_TOTAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverTotal() <em>Cloud Cover Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverTotal()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverTotal = CLOUD_COVER_TOTAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverBelow500() <em>Cloud Cover Below500</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverBelow500()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_BELOW500_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverBelow500() <em>Cloud Cover Below500</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverBelow500()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverBelow500 = CLOUD_COVER_BELOW500_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverEffective() <em>Cloud Cover Effective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverEffective()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_EFFECTIVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverEffective() <em>Cloud Cover Effective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverEffective()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverEffective = CLOUD_COVER_EFFECTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverHigh() <em>Cloud Cover High</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverHigh()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_HIGH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverHigh() <em>Cloud Cover High</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverHigh()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverHigh = CLOUD_COVER_HIGH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverMid() <em>Cloud Cover Mid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverMid()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_MID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverMid() <em>Cloud Cover Mid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverMid()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverMid = CLOUD_COVER_MID_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloudCoverLow() <em>Cloud Cover Low</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverLow()
	 * @generated
	 * @ordered
	 */
	protected static final Float CLOUD_COVER_LOW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloudCoverLow() <em>Cloud Cover Low</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverLow()
	 * @generated
	 * @ordered
	 */
	protected Float cloudCoverLow = CLOUD_COVER_LOW_EDEFAULT;

	/**
	 * The default value of the '{@link #getIrRadianceGlobal() <em>Ir Radiance Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIrRadianceGlobal()
	 * @generated
	 * @ordered
	 */
	protected static final Float IR_RADIANCE_GLOBAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIrRadianceGlobal() <em>Ir Radiance Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIrRadianceGlobal()
	 * @generated
	 * @ordered
	 */
	protected Float irRadianceGlobal = IR_RADIANCE_GLOBAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getSurfacePressure() <em>Surface Pressure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSurfacePressure()
	 * @generated
	 * @ordered
	 */
	protected static final Float SURFACE_PRESSURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSurfacePressure() <em>Surface Pressure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSurfacePressure()
	 * @generated
	 * @ordered
	 */
	protected Float surfacePressure = SURFACE_PRESSURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger02Last6() <em>Precipitation Larger02 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02Last6()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER02_LAST6_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger02Last6() <em>Precipitation Larger02 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02Last6()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger02Last6 = PRECIPITATION_LARGER02_LAST6_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger50Last6() <em>Precipitation Larger50 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50Last6()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER50_LAST6_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger50Last6() <em>Precipitation Larger50 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50Last6()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger50Last6 = PRECIPITATION_LARGER50_LAST6_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger02LastDay() <em>Precipitation Larger02 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02LastDay()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER02_LAST_DAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger02LastDay() <em>Precipitation Larger02 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02LastDay()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger02LastDay = PRECIPITATION_LARGER02_LAST_DAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger50LastDay() <em>Precipitation Larger50 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50LastDay()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER50_LAST_DAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger50LastDay() <em>Precipitation Larger50 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50LastDay()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger50LastDay = PRECIPITATION_LARGER50_LAST_DAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger00Last12() <em>Precipitation Larger00 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger00Last12()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER00_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger00Last12() <em>Precipitation Larger00 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger00Last12()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger00Last12 = PRECIPITATION_LARGER00_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger02Last12() <em>Precipitation Larger02 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02Last12()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER02_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger02Last12() <em>Precipitation Larger02 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger02Last12()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger02Last12 = PRECIPITATION_LARGER02_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger10Last12() <em>Precipitation Larger10 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger10Last12()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER10_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger10Last12() <em>Precipitation Larger10 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger10Last12()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger10Last12 = PRECIPITATION_LARGER10_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationLarger50Last12() <em>Precipitation Larger50 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50Last12()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_LARGER50_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationLarger50Last12() <em>Precipitation Larger50 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationLarger50Last12()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationLarger50Last12 = PRECIPITATION_LARGER50_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationSignificantWeatherTotal() <em>Precipitation Significant Weather Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationSignificantWeatherTotal()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationSignificantWeatherTotal() <em>Precipitation Significant Weather Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationSignificantWeatherTotal()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationSignificantWeatherTotal = PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecipitationSignificantWeatherLast3() <em>Precipitation Significant Weather Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationSignificantWeatherLast3()
	 * @generated
	 * @ordered
	 */
	protected static final Float PRECIPITATION_SIGNIFICANT_WEATHER_LAST3_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecipitationSignificantWeatherLast3() <em>Precipitation Significant Weather Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecipitationSignificantWeatherLast3()
	 * @generated
	 * @ordered
	 */
	protected Float precipitationSignificantWeatherLast3 = PRECIPITATION_SIGNIFICANT_WEATHER_LAST3_EDEFAULT;

	/**
	 * The default value of the '{@link #getSnowRainEqLast1() <em>Snow Rain Eq Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnowRainEqLast1()
	 * @generated
	 * @ordered
	 */
	protected static final Float SNOW_RAIN_EQ_LAST1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSnowRainEqLast1() <em>Snow Rain Eq Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnowRainEqLast1()
	 * @generated
	 * @ordered
	 */
	protected Float snowRainEqLast1 = SNOW_RAIN_EQ_LAST1_EDEFAULT;

	/**
	 * The default value of the '{@link #getSnowRainEqLast3() <em>Snow Rain Eq Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnowRainEqLast3()
	 * @generated
	 * @ordered
	 */
	protected static final Float SNOW_RAIN_EQ_LAST3_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSnowRainEqLast3() <em>Snow Rain Eq Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnowRainEqLast3()
	 * @generated
	 * @ordered
	 */
	protected Float snowRainEqLast3 = SNOW_RAIN_EQ_LAST3_EDEFAULT;

	/**
	 * The default value of the '{@link #getSunshineDurationLast1() <em>Sunshine Duration Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunshineDurationLast1()
	 * @generated
	 * @ordered
	 */
	protected static final Float SUNSHINE_DURATION_LAST1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSunshineDurationLast1() <em>Sunshine Duration Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSunshineDurationLast1()
	 * @generated
	 * @ordered
	 */
	protected Float sunshineDurationLast1 = SUNSHINE_DURATION_LAST1_EDEFAULT;

	/**
	 * The default value of the '{@link #getTempAboveSurface5() <em>Temp Above Surface5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempAboveSurface5()
	 * @generated
	 * @ordered
	 */
	protected static final Float TEMP_ABOVE_SURFACE5_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempAboveSurface5() <em>Temp Above Surface5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempAboveSurface5()
	 * @generated
	 * @ordered
	 */
	protected Float tempAboveSurface5 = TEMP_ABOVE_SURFACE5_EDEFAULT;

	/**
	 * The default value of the '{@link #getTempAboveSurface200() <em>Temp Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempAboveSurface200()
	 * @generated
	 * @ordered
	 */
	protected static final Float TEMP_ABOVE_SURFACE200_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempAboveSurface200() <em>Temp Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempAboveSurface200()
	 * @generated
	 * @ordered
	 */
	protected Float tempAboveSurface200 = TEMP_ABOVE_SURFACE200_EDEFAULT;

	/**
	 * The default value of the '{@link #getTempDewpointAboveSurface200() <em>Temp Dewpoint Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempDewpointAboveSurface200()
	 * @generated
	 * @ordered
	 */
	protected static final Float TEMP_DEWPOINT_ABOVE_SURFACE200_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempDewpointAboveSurface200() <em>Temp Dewpoint Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempDewpointAboveSurface200()
	 * @generated
	 * @ordered
	 */
	protected Float tempDewpointAboveSurface200 = TEMP_DEWPOINT_ABOVE_SURFACE200_EDEFAULT;

	/**
	 * The default value of the '{@link #getTempMinLast12() <em>Temp Min Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempMinLast12()
	 * @generated
	 * @ordered
	 */
	protected static final Float TEMP_MIN_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempMinLast12() <em>Temp Min Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempMinLast12()
	 * @generated
	 * @ordered
	 */
	protected Float tempMinLast12 = TEMP_MIN_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getTempMaxLast12() <em>Temp Max Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempMaxLast12()
	 * @generated
	 * @ordered
	 */
	protected static final Float TEMP_MAX_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempMaxLast12() <em>Temp Max Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempMaxLast12()
	 * @generated
	 * @ordered
	 */
	protected Float tempMaxLast12 = TEMP_MAX_LAST12_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final Float VISIBILITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected Float visibility = VISIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPastWeather() <em>Past Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPastWeather()
	 * @generated
	 * @ordered
	 */
	protected static final Float PAST_WEATHER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPastWeather() <em>Past Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPastWeather()
	 * @generated
	 * @ordered
	 */
	protected Float pastWeather = PAST_WEATHER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignificantWeather() <em>Significant Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignificantWeather()
	 * @generated
	 * @ordered
	 */
	protected static final Float SIGNIFICANT_WEATHER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignificantWeather() <em>Significant Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignificantWeather()
	 * @generated
	 * @ordered
	 */
	protected Float significantWeather = SIGNIFICANT_WEATHER_EDEFAULT;

	/**
	 * The default value of the '{@link #getFogPropLast1() <em>Fog Prop Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast1()
	 * @generated
	 * @ordered
	 */
	protected static final Float FOG_PROP_LAST1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFogPropLast1() <em>Fog Prop Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast1()
	 * @generated
	 * @ordered
	 */
	protected Float fogPropLast1 = FOG_PROP_LAST1_EDEFAULT;

	/**
	 * The default value of the '{@link #getFogPropLast6() <em>Fog Prop Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast6()
	 * @generated
	 * @ordered
	 */
	protected static final Float FOG_PROP_LAST6_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFogPropLast6() <em>Fog Prop Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast6()
	 * @generated
	 * @ordered
	 */
	protected Float fogPropLast6 = FOG_PROP_LAST6_EDEFAULT;

	/**
	 * The default value of the '{@link #getFogPropLast12() <em>Fog Prop Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast12()
	 * @generated
	 * @ordered
	 */
	protected static final Float FOG_PROP_LAST12_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFogPropLast12() <em>Fog Prop Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFogPropLast12()
	 * @generated
	 * @ordered
	 */
	protected Float fogPropLast12 = FOG_PROP_LAST12_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MOSMIXSWeatherReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindDirection() {
		return windDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindDirection(Float newWindDirection) {
		Float oldWindDirection = windDirection;
		windDirection = newWindDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION, oldWindDirection, windDirection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindSpeed() {
		return windSpeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindSpeed(Float newWindSpeed) {
		Float oldWindSpeed = windSpeed;
		windSpeed = newWindSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_SPEED, oldWindSpeed, windSpeed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustLastHour() {
		return windGustLastHour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustLastHour(Float newWindGustLastHour) {
		Float oldWindGustLastHour = windGustLastHour;
		windGustLastHour = newWindGustLastHour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR, oldWindGustLastHour, windGustLastHour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustLastThreeHours() {
		return windGustLastThreeHours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustLastThreeHours(Float newWindGustLastThreeHours) {
		Float oldWindGustLastThreeHours = windGustLastThreeHours;
		windGustLastThreeHours = newWindGustLastThreeHours;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS, oldWindGustLastThreeHours, windGustLastThreeHours));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustMaxLast12Hours() {
		return windGustMaxLast12Hours;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustMaxLast12Hours(Float newWindGustMaxLast12Hours) {
		Float oldWindGustMaxLast12Hours = windGustMaxLast12Hours;
		windGustMaxLast12Hours = newWindGustMaxLast12Hours;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS, oldWindGustMaxLast12Hours, windGustMaxLast12Hours));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustProb25() {
		return windGustProb25;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustProb25(Float newWindGustProb25) {
		Float oldWindGustProb25 = windGustProb25;
		windGustProb25 = newWindGustProb25;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25, oldWindGustProb25, windGustProb25));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustProb40() {
		return windGustProb40;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustProb40(Float newWindGustProb40) {
		Float oldWindGustProb40 = windGustProb40;
		windGustProb40 = newWindGustProb40;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40, oldWindGustProb40, windGustProb40));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getWindGustProb55() {
		return windGustProb55;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWindGustProb55(Float newWindGustProb55) {
		Float oldWindGustProb55 = windGustProb55;
		windGustProb55 = newWindGustProb55;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55, oldWindGustProb55, windGustProb55));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverTotal() {
		return cloudCoverTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverTotal(Float newCloudCoverTotal) {
		Float oldCloudCoverTotal = cloudCoverTotal;
		cloudCoverTotal = newCloudCoverTotal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL, oldCloudCoverTotal, cloudCoverTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverBelow500() {
		return cloudCoverBelow500;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverBelow500(Float newCloudCoverBelow500) {
		Float oldCloudCoverBelow500 = cloudCoverBelow500;
		cloudCoverBelow500 = newCloudCoverBelow500;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500, oldCloudCoverBelow500, cloudCoverBelow500));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverEffective() {
		return cloudCoverEffective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverEffective(Float newCloudCoverEffective) {
		Float oldCloudCoverEffective = cloudCoverEffective;
		cloudCoverEffective = newCloudCoverEffective;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE, oldCloudCoverEffective, cloudCoverEffective));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverHigh() {
		return cloudCoverHigh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverHigh(Float newCloudCoverHigh) {
		Float oldCloudCoverHigh = cloudCoverHigh;
		cloudCoverHigh = newCloudCoverHigh;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH, oldCloudCoverHigh, cloudCoverHigh));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverMid() {
		return cloudCoverMid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverMid(Float newCloudCoverMid) {
		Float oldCloudCoverMid = cloudCoverMid;
		cloudCoverMid = newCloudCoverMid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID, oldCloudCoverMid, cloudCoverMid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCloudCoverLow() {
		return cloudCoverLow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloudCoverLow(Float newCloudCoverLow) {
		Float oldCloudCoverLow = cloudCoverLow;
		cloudCoverLow = newCloudCoverLow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW, oldCloudCoverLow, cloudCoverLow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getIrRadianceGlobal() {
		return irRadianceGlobal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIrRadianceGlobal(Float newIrRadianceGlobal) {
		Float oldIrRadianceGlobal = irRadianceGlobal;
		irRadianceGlobal = newIrRadianceGlobal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL, oldIrRadianceGlobal, irRadianceGlobal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getSurfacePressure() {
		return surfacePressure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSurfacePressure(Float newSurfacePressure) {
		Float oldSurfacePressure = surfacePressure;
		surfacePressure = newSurfacePressure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE, oldSurfacePressure, surfacePressure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger02Last6() {
		return precipitationLarger02Last6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger02Last6(Float newPrecipitationLarger02Last6) {
		Float oldPrecipitationLarger02Last6 = precipitationLarger02Last6;
		precipitationLarger02Last6 = newPrecipitationLarger02Last6;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6, oldPrecipitationLarger02Last6, precipitationLarger02Last6));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger50Last6() {
		return precipitationLarger50Last6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger50Last6(Float newPrecipitationLarger50Last6) {
		Float oldPrecipitationLarger50Last6 = precipitationLarger50Last6;
		precipitationLarger50Last6 = newPrecipitationLarger50Last6;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6, oldPrecipitationLarger50Last6, precipitationLarger50Last6));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger02LastDay() {
		return precipitationLarger02LastDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger02LastDay(Float newPrecipitationLarger02LastDay) {
		Float oldPrecipitationLarger02LastDay = precipitationLarger02LastDay;
		precipitationLarger02LastDay = newPrecipitationLarger02LastDay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY, oldPrecipitationLarger02LastDay, precipitationLarger02LastDay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger50LastDay() {
		return precipitationLarger50LastDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger50LastDay(Float newPrecipitationLarger50LastDay) {
		Float oldPrecipitationLarger50LastDay = precipitationLarger50LastDay;
		precipitationLarger50LastDay = newPrecipitationLarger50LastDay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY, oldPrecipitationLarger50LastDay, precipitationLarger50LastDay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger00Last12() {
		return precipitationLarger00Last12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger00Last12(Float newPrecipitationLarger00Last12) {
		Float oldPrecipitationLarger00Last12 = precipitationLarger00Last12;
		precipitationLarger00Last12 = newPrecipitationLarger00Last12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12, oldPrecipitationLarger00Last12, precipitationLarger00Last12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger02Last12() {
		return precipitationLarger02Last12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger02Last12(Float newPrecipitationLarger02Last12) {
		Float oldPrecipitationLarger02Last12 = precipitationLarger02Last12;
		precipitationLarger02Last12 = newPrecipitationLarger02Last12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12, oldPrecipitationLarger02Last12, precipitationLarger02Last12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger10Last12() {
		return precipitationLarger10Last12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger10Last12(Float newPrecipitationLarger10Last12) {
		Float oldPrecipitationLarger10Last12 = precipitationLarger10Last12;
		precipitationLarger10Last12 = newPrecipitationLarger10Last12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12, oldPrecipitationLarger10Last12, precipitationLarger10Last12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationLarger50Last12() {
		return precipitationLarger50Last12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationLarger50Last12(Float newPrecipitationLarger50Last12) {
		Float oldPrecipitationLarger50Last12 = precipitationLarger50Last12;
		precipitationLarger50Last12 = newPrecipitationLarger50Last12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12, oldPrecipitationLarger50Last12, precipitationLarger50Last12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationSignificantWeatherTotal() {
		return precipitationSignificantWeatherTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationSignificantWeatherTotal(Float newPrecipitationSignificantWeatherTotal) {
		Float oldPrecipitationSignificantWeatherTotal = precipitationSignificantWeatherTotal;
		precipitationSignificantWeatherTotal = newPrecipitationSignificantWeatherTotal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL, oldPrecipitationSignificantWeatherTotal, precipitationSignificantWeatherTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPrecipitationSignificantWeatherLast3() {
		return precipitationSignificantWeatherLast3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecipitationSignificantWeatherLast3(Float newPrecipitationSignificantWeatherLast3) {
		Float oldPrecipitationSignificantWeatherLast3 = precipitationSignificantWeatherLast3;
		precipitationSignificantWeatherLast3 = newPrecipitationSignificantWeatherLast3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3, oldPrecipitationSignificantWeatherLast3, precipitationSignificantWeatherLast3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getSnowRainEqLast1() {
		return snowRainEqLast1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSnowRainEqLast1(Float newSnowRainEqLast1) {
		Float oldSnowRainEqLast1 = snowRainEqLast1;
		snowRainEqLast1 = newSnowRainEqLast1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1, oldSnowRainEqLast1, snowRainEqLast1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getSnowRainEqLast3() {
		return snowRainEqLast3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSnowRainEqLast3(Float newSnowRainEqLast3) {
		Float oldSnowRainEqLast3 = snowRainEqLast3;
		snowRainEqLast3 = newSnowRainEqLast3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3, oldSnowRainEqLast3, snowRainEqLast3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getSunshineDurationLast1() {
		return sunshineDurationLast1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSunshineDurationLast1(Float newSunshineDurationLast1) {
		Float oldSunshineDurationLast1 = sunshineDurationLast1;
		sunshineDurationLast1 = newSunshineDurationLast1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1, oldSunshineDurationLast1, sunshineDurationLast1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getTempAboveSurface5() {
		return tempAboveSurface5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTempAboveSurface5(Float newTempAboveSurface5) {
		Float oldTempAboveSurface5 = tempAboveSurface5;
		tempAboveSurface5 = newTempAboveSurface5;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5, oldTempAboveSurface5, tempAboveSurface5));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getTempAboveSurface200() {
		return tempAboveSurface200;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTempAboveSurface200(Float newTempAboveSurface200) {
		Float oldTempAboveSurface200 = tempAboveSurface200;
		tempAboveSurface200 = newTempAboveSurface200;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200, oldTempAboveSurface200, tempAboveSurface200));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getTempDewpointAboveSurface200() {
		return tempDewpointAboveSurface200;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTempDewpointAboveSurface200(Float newTempDewpointAboveSurface200) {
		Float oldTempDewpointAboveSurface200 = tempDewpointAboveSurface200;
		tempDewpointAboveSurface200 = newTempDewpointAboveSurface200;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200, oldTempDewpointAboveSurface200, tempDewpointAboveSurface200));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getTempMinLast12() {
		return tempMinLast12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTempMinLast12(Float newTempMinLast12) {
		Float oldTempMinLast12 = tempMinLast12;
		tempMinLast12 = newTempMinLast12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12, oldTempMinLast12, tempMinLast12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getTempMaxLast12() {
		return tempMaxLast12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTempMaxLast12(Float newTempMaxLast12) {
		Float oldTempMaxLast12 = tempMaxLast12;
		tempMaxLast12 = newTempMaxLast12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12, oldTempMaxLast12, tempMaxLast12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisibility(Float newVisibility) {
		Float oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getPastWeather() {
		return pastWeather;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPastWeather(Float newPastWeather) {
		Float oldPastWeather = pastWeather;
		pastWeather = newPastWeather;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__PAST_WEATHER, oldPastWeather, pastWeather));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getSignificantWeather() {
		return significantWeather;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSignificantWeather(Float newSignificantWeather) {
		Float oldSignificantWeather = significantWeather;
		significantWeather = newSignificantWeather;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER, oldSignificantWeather, significantWeather));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getFogPropLast1() {
		return fogPropLast1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFogPropLast1(Float newFogPropLast1) {
		Float oldFogPropLast1 = fogPropLast1;
		fogPropLast1 = newFogPropLast1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1, oldFogPropLast1, fogPropLast1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getFogPropLast6() {
		return fogPropLast6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFogPropLast6(Float newFogPropLast6) {
		Float oldFogPropLast6 = fogPropLast6;
		fogPropLast6 = newFogPropLast6;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6, oldFogPropLast6, fogPropLast6));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getFogPropLast12() {
		return fogPropLast12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFogPropLast12(Float newFogPropLast12) {
		Float oldFogPropLast12 = fogPropLast12;
		fogPropLast12 = newFogPropLast12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12, oldFogPropLast12, fogPropLast12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION:
				return getWindDirection();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_SPEED:
				return getWindSpeed();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR:
				return getWindGustLastHour();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS:
				return getWindGustLastThreeHours();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS:
				return getWindGustMaxLast12Hours();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25:
				return getWindGustProb25();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40:
				return getWindGustProb40();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55:
				return getWindGustProb55();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL:
				return getCloudCoverTotal();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500:
				return getCloudCoverBelow500();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE:
				return getCloudCoverEffective();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH:
				return getCloudCoverHigh();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID:
				return getCloudCoverMid();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW:
				return getCloudCoverLow();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL:
				return getIrRadianceGlobal();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE:
				return getSurfacePressure();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6:
				return getPrecipitationLarger02Last6();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6:
				return getPrecipitationLarger50Last6();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY:
				return getPrecipitationLarger02LastDay();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY:
				return getPrecipitationLarger50LastDay();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12:
				return getPrecipitationLarger00Last12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12:
				return getPrecipitationLarger02Last12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12:
				return getPrecipitationLarger10Last12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12:
				return getPrecipitationLarger50Last12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL:
				return getPrecipitationSignificantWeatherTotal();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3:
				return getPrecipitationSignificantWeatherLast3();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1:
				return getSnowRainEqLast1();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3:
				return getSnowRainEqLast3();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1:
				return getSunshineDurationLast1();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5:
				return getTempAboveSurface5();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200:
				return getTempAboveSurface200();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200:
				return getTempDewpointAboveSurface200();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12:
				return getTempMinLast12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12:
				return getTempMaxLast12();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__VISIBILITY:
				return getVisibility();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PAST_WEATHER:
				return getPastWeather();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER:
				return getSignificantWeather();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1:
				return getFogPropLast1();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6:
				return getFogPropLast6();
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12:
				return getFogPropLast12();
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
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION:
				setWindDirection((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_SPEED:
				setWindSpeed((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR:
				setWindGustLastHour((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS:
				setWindGustLastThreeHours((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS:
				setWindGustMaxLast12Hours((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25:
				setWindGustProb25((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40:
				setWindGustProb40((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55:
				setWindGustProb55((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL:
				setCloudCoverTotal((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500:
				setCloudCoverBelow500((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE:
				setCloudCoverEffective((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH:
				setCloudCoverHigh((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID:
				setCloudCoverMid((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW:
				setCloudCoverLow((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL:
				setIrRadianceGlobal((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE:
				setSurfacePressure((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6:
				setPrecipitationLarger02Last6((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6:
				setPrecipitationLarger50Last6((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY:
				setPrecipitationLarger02LastDay((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY:
				setPrecipitationLarger50LastDay((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12:
				setPrecipitationLarger00Last12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12:
				setPrecipitationLarger02Last12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12:
				setPrecipitationLarger10Last12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12:
				setPrecipitationLarger50Last12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL:
				setPrecipitationSignificantWeatherTotal((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3:
				setPrecipitationSignificantWeatherLast3((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1:
				setSnowRainEqLast1((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3:
				setSnowRainEqLast3((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1:
				setSunshineDurationLast1((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5:
				setTempAboveSurface5((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200:
				setTempAboveSurface200((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200:
				setTempDewpointAboveSurface200((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12:
				setTempMinLast12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12:
				setTempMaxLast12((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__VISIBILITY:
				setVisibility((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PAST_WEATHER:
				setPastWeather((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER:
				setSignificantWeather((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1:
				setFogPropLast1((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6:
				setFogPropLast6((Float)newValue);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12:
				setFogPropLast12((Float)newValue);
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
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION:
				setWindDirection(WIND_DIRECTION_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_SPEED:
				setWindSpeed(WIND_SPEED_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR:
				setWindGustLastHour(WIND_GUST_LAST_HOUR_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS:
				setWindGustLastThreeHours(WIND_GUST_LAST_THREE_HOURS_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS:
				setWindGustMaxLast12Hours(WIND_GUST_MAX_LAST12_HOURS_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25:
				setWindGustProb25(WIND_GUST_PROB25_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40:
				setWindGustProb40(WIND_GUST_PROB40_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55:
				setWindGustProb55(WIND_GUST_PROB55_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL:
				setCloudCoverTotal(CLOUD_COVER_TOTAL_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500:
				setCloudCoverBelow500(CLOUD_COVER_BELOW500_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE:
				setCloudCoverEffective(CLOUD_COVER_EFFECTIVE_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH:
				setCloudCoverHigh(CLOUD_COVER_HIGH_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID:
				setCloudCoverMid(CLOUD_COVER_MID_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW:
				setCloudCoverLow(CLOUD_COVER_LOW_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL:
				setIrRadianceGlobal(IR_RADIANCE_GLOBAL_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE:
				setSurfacePressure(SURFACE_PRESSURE_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6:
				setPrecipitationLarger02Last6(PRECIPITATION_LARGER02_LAST6_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6:
				setPrecipitationLarger50Last6(PRECIPITATION_LARGER50_LAST6_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY:
				setPrecipitationLarger02LastDay(PRECIPITATION_LARGER02_LAST_DAY_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY:
				setPrecipitationLarger50LastDay(PRECIPITATION_LARGER50_LAST_DAY_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12:
				setPrecipitationLarger00Last12(PRECIPITATION_LARGER00_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12:
				setPrecipitationLarger02Last12(PRECIPITATION_LARGER02_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12:
				setPrecipitationLarger10Last12(PRECIPITATION_LARGER10_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12:
				setPrecipitationLarger50Last12(PRECIPITATION_LARGER50_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL:
				setPrecipitationSignificantWeatherTotal(PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3:
				setPrecipitationSignificantWeatherLast3(PRECIPITATION_SIGNIFICANT_WEATHER_LAST3_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1:
				setSnowRainEqLast1(SNOW_RAIN_EQ_LAST1_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3:
				setSnowRainEqLast3(SNOW_RAIN_EQ_LAST3_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1:
				setSunshineDurationLast1(SUNSHINE_DURATION_LAST1_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5:
				setTempAboveSurface5(TEMP_ABOVE_SURFACE5_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200:
				setTempAboveSurface200(TEMP_ABOVE_SURFACE200_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200:
				setTempDewpointAboveSurface200(TEMP_DEWPOINT_ABOVE_SURFACE200_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12:
				setTempMinLast12(TEMP_MIN_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12:
				setTempMaxLast12(TEMP_MAX_LAST12_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PAST_WEATHER:
				setPastWeather(PAST_WEATHER_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER:
				setSignificantWeather(SIGNIFICANT_WEATHER_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1:
				setFogPropLast1(FOG_PROP_LAST1_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6:
				setFogPropLast6(FOG_PROP_LAST6_EDEFAULT);
				return;
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12:
				setFogPropLast12(FOG_PROP_LAST12_EDEFAULT);
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
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION:
				return WIND_DIRECTION_EDEFAULT == null ? windDirection != null : !WIND_DIRECTION_EDEFAULT.equals(windDirection);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_SPEED:
				return WIND_SPEED_EDEFAULT == null ? windSpeed != null : !WIND_SPEED_EDEFAULT.equals(windSpeed);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR:
				return WIND_GUST_LAST_HOUR_EDEFAULT == null ? windGustLastHour != null : !WIND_GUST_LAST_HOUR_EDEFAULT.equals(windGustLastHour);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS:
				return WIND_GUST_LAST_THREE_HOURS_EDEFAULT == null ? windGustLastThreeHours != null : !WIND_GUST_LAST_THREE_HOURS_EDEFAULT.equals(windGustLastThreeHours);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS:
				return WIND_GUST_MAX_LAST12_HOURS_EDEFAULT == null ? windGustMaxLast12Hours != null : !WIND_GUST_MAX_LAST12_HOURS_EDEFAULT.equals(windGustMaxLast12Hours);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25:
				return WIND_GUST_PROB25_EDEFAULT == null ? windGustProb25 != null : !WIND_GUST_PROB25_EDEFAULT.equals(windGustProb25);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40:
				return WIND_GUST_PROB40_EDEFAULT == null ? windGustProb40 != null : !WIND_GUST_PROB40_EDEFAULT.equals(windGustProb40);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55:
				return WIND_GUST_PROB55_EDEFAULT == null ? windGustProb55 != null : !WIND_GUST_PROB55_EDEFAULT.equals(windGustProb55);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL:
				return CLOUD_COVER_TOTAL_EDEFAULT == null ? cloudCoverTotal != null : !CLOUD_COVER_TOTAL_EDEFAULT.equals(cloudCoverTotal);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500:
				return CLOUD_COVER_BELOW500_EDEFAULT == null ? cloudCoverBelow500 != null : !CLOUD_COVER_BELOW500_EDEFAULT.equals(cloudCoverBelow500);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE:
				return CLOUD_COVER_EFFECTIVE_EDEFAULT == null ? cloudCoverEffective != null : !CLOUD_COVER_EFFECTIVE_EDEFAULT.equals(cloudCoverEffective);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH:
				return CLOUD_COVER_HIGH_EDEFAULT == null ? cloudCoverHigh != null : !CLOUD_COVER_HIGH_EDEFAULT.equals(cloudCoverHigh);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID:
				return CLOUD_COVER_MID_EDEFAULT == null ? cloudCoverMid != null : !CLOUD_COVER_MID_EDEFAULT.equals(cloudCoverMid);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW:
				return CLOUD_COVER_LOW_EDEFAULT == null ? cloudCoverLow != null : !CLOUD_COVER_LOW_EDEFAULT.equals(cloudCoverLow);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL:
				return IR_RADIANCE_GLOBAL_EDEFAULT == null ? irRadianceGlobal != null : !IR_RADIANCE_GLOBAL_EDEFAULT.equals(irRadianceGlobal);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE:
				return SURFACE_PRESSURE_EDEFAULT == null ? surfacePressure != null : !SURFACE_PRESSURE_EDEFAULT.equals(surfacePressure);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6:
				return PRECIPITATION_LARGER02_LAST6_EDEFAULT == null ? precipitationLarger02Last6 != null : !PRECIPITATION_LARGER02_LAST6_EDEFAULT.equals(precipitationLarger02Last6);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6:
				return PRECIPITATION_LARGER50_LAST6_EDEFAULT == null ? precipitationLarger50Last6 != null : !PRECIPITATION_LARGER50_LAST6_EDEFAULT.equals(precipitationLarger50Last6);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY:
				return PRECIPITATION_LARGER02_LAST_DAY_EDEFAULT == null ? precipitationLarger02LastDay != null : !PRECIPITATION_LARGER02_LAST_DAY_EDEFAULT.equals(precipitationLarger02LastDay);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY:
				return PRECIPITATION_LARGER50_LAST_DAY_EDEFAULT == null ? precipitationLarger50LastDay != null : !PRECIPITATION_LARGER50_LAST_DAY_EDEFAULT.equals(precipitationLarger50LastDay);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12:
				return PRECIPITATION_LARGER00_LAST12_EDEFAULT == null ? precipitationLarger00Last12 != null : !PRECIPITATION_LARGER00_LAST12_EDEFAULT.equals(precipitationLarger00Last12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12:
				return PRECIPITATION_LARGER02_LAST12_EDEFAULT == null ? precipitationLarger02Last12 != null : !PRECIPITATION_LARGER02_LAST12_EDEFAULT.equals(precipitationLarger02Last12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12:
				return PRECIPITATION_LARGER10_LAST12_EDEFAULT == null ? precipitationLarger10Last12 != null : !PRECIPITATION_LARGER10_LAST12_EDEFAULT.equals(precipitationLarger10Last12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12:
				return PRECIPITATION_LARGER50_LAST12_EDEFAULT == null ? precipitationLarger50Last12 != null : !PRECIPITATION_LARGER50_LAST12_EDEFAULT.equals(precipitationLarger50Last12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL:
				return PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL_EDEFAULT == null ? precipitationSignificantWeatherTotal != null : !PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL_EDEFAULT.equals(precipitationSignificantWeatherTotal);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3:
				return PRECIPITATION_SIGNIFICANT_WEATHER_LAST3_EDEFAULT == null ? precipitationSignificantWeatherLast3 != null : !PRECIPITATION_SIGNIFICANT_WEATHER_LAST3_EDEFAULT.equals(precipitationSignificantWeatherLast3);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1:
				return SNOW_RAIN_EQ_LAST1_EDEFAULT == null ? snowRainEqLast1 != null : !SNOW_RAIN_EQ_LAST1_EDEFAULT.equals(snowRainEqLast1);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3:
				return SNOW_RAIN_EQ_LAST3_EDEFAULT == null ? snowRainEqLast3 != null : !SNOW_RAIN_EQ_LAST3_EDEFAULT.equals(snowRainEqLast3);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1:
				return SUNSHINE_DURATION_LAST1_EDEFAULT == null ? sunshineDurationLast1 != null : !SUNSHINE_DURATION_LAST1_EDEFAULT.equals(sunshineDurationLast1);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5:
				return TEMP_ABOVE_SURFACE5_EDEFAULT == null ? tempAboveSurface5 != null : !TEMP_ABOVE_SURFACE5_EDEFAULT.equals(tempAboveSurface5);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200:
				return TEMP_ABOVE_SURFACE200_EDEFAULT == null ? tempAboveSurface200 != null : !TEMP_ABOVE_SURFACE200_EDEFAULT.equals(tempAboveSurface200);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200:
				return TEMP_DEWPOINT_ABOVE_SURFACE200_EDEFAULT == null ? tempDewpointAboveSurface200 != null : !TEMP_DEWPOINT_ABOVE_SURFACE200_EDEFAULT.equals(tempDewpointAboveSurface200);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12:
				return TEMP_MIN_LAST12_EDEFAULT == null ? tempMinLast12 != null : !TEMP_MIN_LAST12_EDEFAULT.equals(tempMinLast12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12:
				return TEMP_MAX_LAST12_EDEFAULT == null ? tempMaxLast12 != null : !TEMP_MAX_LAST12_EDEFAULT.equals(tempMaxLast12);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__VISIBILITY:
				return VISIBILITY_EDEFAULT == null ? visibility != null : !VISIBILITY_EDEFAULT.equals(visibility);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__PAST_WEATHER:
				return PAST_WEATHER_EDEFAULT == null ? pastWeather != null : !PAST_WEATHER_EDEFAULT.equals(pastWeather);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER:
				return SIGNIFICANT_WEATHER_EDEFAULT == null ? significantWeather != null : !SIGNIFICANT_WEATHER_EDEFAULT.equals(significantWeather);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1:
				return FOG_PROP_LAST1_EDEFAULT == null ? fogPropLast1 != null : !FOG_PROP_LAST1_EDEFAULT.equals(fogPropLast1);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6:
				return FOG_PROP_LAST6_EDEFAULT == null ? fogPropLast6 != null : !FOG_PROP_LAST6_EDEFAULT.equals(fogPropLast6);
			case WeatherPackage.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12:
				return FOG_PROP_LAST12_EDEFAULT == null ? fogPropLast12 != null : !FOG_PROP_LAST12_EDEFAULT.equals(fogPropLast12);
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
		result.append(" (windDirection: ");
		result.append(windDirection);
		result.append(", windSpeed: ");
		result.append(windSpeed);
		result.append(", windGustLastHour: ");
		result.append(windGustLastHour);
		result.append(", windGustLastThreeHours: ");
		result.append(windGustLastThreeHours);
		result.append(", windGustMaxLast12Hours: ");
		result.append(windGustMaxLast12Hours);
		result.append(", windGustProb25: ");
		result.append(windGustProb25);
		result.append(", windGustProb40: ");
		result.append(windGustProb40);
		result.append(", windGustProb55: ");
		result.append(windGustProb55);
		result.append(", cloudCoverTotal: ");
		result.append(cloudCoverTotal);
		result.append(", cloudCoverBelow500: ");
		result.append(cloudCoverBelow500);
		result.append(", cloudCoverEffective: ");
		result.append(cloudCoverEffective);
		result.append(", cloudCoverHigh: ");
		result.append(cloudCoverHigh);
		result.append(", cloudCoverMid: ");
		result.append(cloudCoverMid);
		result.append(", cloudCoverLow: ");
		result.append(cloudCoverLow);
		result.append(", irRadianceGlobal: ");
		result.append(irRadianceGlobal);
		result.append(", surfacePressure: ");
		result.append(surfacePressure);
		result.append(", precipitationLarger02Last6: ");
		result.append(precipitationLarger02Last6);
		result.append(", precipitationLarger50Last6: ");
		result.append(precipitationLarger50Last6);
		result.append(", precipitationLarger02LastDay: ");
		result.append(precipitationLarger02LastDay);
		result.append(", precipitationLarger50LastDay: ");
		result.append(precipitationLarger50LastDay);
		result.append(", precipitationLarger00Last12: ");
		result.append(precipitationLarger00Last12);
		result.append(", precipitationLarger02Last12: ");
		result.append(precipitationLarger02Last12);
		result.append(", precipitationLarger10Last12: ");
		result.append(precipitationLarger10Last12);
		result.append(", precipitationLarger50Last12: ");
		result.append(precipitationLarger50Last12);
		result.append(", precipitationSignificantWeatherTotal: ");
		result.append(precipitationSignificantWeatherTotal);
		result.append(", precipitationSignificantWeatherLast3: ");
		result.append(precipitationSignificantWeatherLast3);
		result.append(", snowRainEqLast1: ");
		result.append(snowRainEqLast1);
		result.append(", snowRainEqLast3: ");
		result.append(snowRainEqLast3);
		result.append(", sunshineDurationLast1: ");
		result.append(sunshineDurationLast1);
		result.append(", tempAboveSurface5: ");
		result.append(tempAboveSurface5);
		result.append(", tempAboveSurface200: ");
		result.append(tempAboveSurface200);
		result.append(", tempDewpointAboveSurface200: ");
		result.append(tempDewpointAboveSurface200);
		result.append(", tempMinLast12: ");
		result.append(tempMinLast12);
		result.append(", tempMaxLast12: ");
		result.append(tempMaxLast12);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(", pastWeather: ");
		result.append(pastWeather);
		result.append(", significantWeather: ");
		result.append(significantWeather);
		result.append(", fogPropLast1: ");
		result.append(fogPropLast1);
		result.append(", fogPropLast6: ");
		result.append(fogPropLast6);
		result.append(", fogPropLast12: ");
		result.append(fogPropLast12);
		result.append(')');
		return result.toString();
	}

} //MOSMIXSWeatherReportImpl
