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
package org.gecko.weather.model.weather;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MOSMIXS Weather Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindDirection <em>Wind Direction</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindSpeed <em>Wind Speed</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastHour <em>Wind Gust Last Hour</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastThreeHours <em>Wind Gust Last Three Hours</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustMaxLast12Hours <em>Wind Gust Max Last12 Hours</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb25 <em>Wind Gust Prob25</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb40 <em>Wind Gust Prob40</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb55 <em>Wind Gust Prob55</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverTotal <em>Cloud Cover Total</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverBelow500 <em>Cloud Cover Below500</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverEffective <em>Cloud Cover Effective</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverHigh <em>Cloud Cover High</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverMid <em>Cloud Cover Mid</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverLow <em>Cloud Cover Low</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getIrRadianceGlobal <em>Ir Radiance Global</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSurfacePressure <em>Surface Pressure</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last6 <em>Precipitation Larger02 Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last6 <em>Precipitation Larger50 Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02LastDay <em>Precipitation Larger02 Last Day</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50LastDay <em>Precipitation Larger50 Last Day</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger00Last12 <em>Precipitation Larger00 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last12 <em>Precipitation Larger02 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger10Last12 <em>Precipitation Larger10 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last12 <em>Precipitation Larger50 Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherTotal <em>Precipitation Significant Weather Total</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherLast3 <em>Precipitation Significant Weather Last3</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast1 <em>Snow Rain Eq Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast3 <em>Snow Rain Eq Last3</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSunshineDurationLast1 <em>Sunshine Duration Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface5 <em>Temp Above Surface5</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface200 <em>Temp Above Surface200</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempDewpointAboveSurface200 <em>Temp Dewpoint Above Surface200</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMinLast12 <em>Temp Min Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMaxLast12 <em>Temp Max Last12</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPastWeather <em>Past Weather</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSignificantWeather <em>Significant Weather</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast1 <em>Fog Prop Last1</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast6 <em>Fog Prop Last6</em>}</li>
 *   <li>{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast12 <em>Fog Prop Last12</em>}</li>
 * </ul>
 *
 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport()
 * @model annotation="http://www.eclipse.org/emf/2002/GenModel"
 * @generated
 */
@ProviderType
public interface MOSMIXSWeatherReport extends WeatherReport {
	/**
	 * Returns the value of the '<em><b>Wind Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Wind direction: 0..360 Degrees (DD)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Direction</em>' attribute.
	 * @see #setWindDirection(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindDirection()
	 * @model
	 * @generated
	 */
	Float getWindDirection();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindDirection <em>Wind Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Direction</em>' attribute.
	 * @see #getWindDirection()
	 * @generated
	 */
	void setWindDirection(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Wind speed: m/s (FF)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Speed</em>' attribute.
	 * @see #setWindSpeed(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindSpeed()
	 * @model
	 * @generated
	 */
	Float getWindSpeed();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindSpeed <em>Wind Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Speed</em>' attribute.
	 * @see #getWindSpeed()
	 * @generated
	 */
	void setWindSpeed(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Last Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum wind gust within the last hour: m/s (FX1)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Last Hour</em>' attribute.
	 * @see #setWindGustLastHour(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustLastHour()
	 * @model
	 * @generated
	 */
	Float getWindGustLastHour();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastHour <em>Wind Gust Last Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Last Hour</em>' attribute.
	 * @see #getWindGustLastHour()
	 * @generated
	 */
	void setWindGustLastHour(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Last Three Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum wind gust within the last 3 hours: m/s (FX3)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Last Three Hours</em>' attribute.
	 * @see #setWindGustLastThreeHours(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustLastThreeHours()
	 * @model
	 * @generated
	 */
	Float getWindGustLastThreeHours();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustLastThreeHours <em>Wind Gust Last Three Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Last Three Hours</em>' attribute.
	 * @see #getWindGustLastThreeHours()
	 * @generated
	 */
	void setWindGustLastThreeHours(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Max Last12 Hours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum wind gust within the last 12 hours: m/s (FXh)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Max Last12 Hours</em>' attribute.
	 * @see #setWindGustMaxLast12Hours(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustMaxLast12Hours()
	 * @model
	 * @generated
	 */
	Float getWindGustMaxLast12Hours();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustMaxLast12Hours <em>Wind Gust Max Last12 Hours</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Max Last12 Hours</em>' attribute.
	 * @see #getWindGustMaxLast12Hours()
	 * @generated
	 */
	void setWindGustMaxLast12Hours(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Prob25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of wind gusts >= 25kn within the last 12 hours: 0..100% (FXh25)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Prob25</em>' attribute.
	 * @see #setWindGustProb25(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustProb25()
	 * @model
	 * @generated
	 */
	Float getWindGustProb25();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb25 <em>Wind Gust Prob25</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Prob25</em>' attribute.
	 * @see #getWindGustProb25()
	 * @generated
	 */
	void setWindGustProb25(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Prob40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of wind gusts >= 40kn within the last 12 hours: 0..100% (FXh40)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Prob40</em>' attribute.
	 * @see #setWindGustProb40(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustProb40()
	 * @model
	 * @generated
	 */
	Float getWindGustProb40();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb40 <em>Wind Gust Prob40</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Prob40</em>' attribute.
	 * @see #getWindGustProb40()
	 * @generated
	 */
	void setWindGustProb40(Float value);

	/**
	 * Returns the value of the '<em><b>Wind Gust Prob55</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of wind gusts >= 55kn within the last 12 hours: 0..100% (FXh55)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wind Gust Prob55</em>' attribute.
	 * @see #setWindGustProb55(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_WindGustProb55()
	 * @model
	 * @generated
	 */
	Float getWindGustProb55();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getWindGustProb55 <em>Wind Gust Prob55</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Gust Prob55</em>' attribute.
	 * @see #getWindGustProb55()
	 * @generated
	 */
	void setWindGustProb55(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total cloud cover: 0..100% (N)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover Total</em>' attribute.
	 * @see #setCloudCoverTotal(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverTotal()
	 * @model
	 * @generated
	 */
	Float getCloudCoverTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverTotal <em>Cloud Cover Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Total</em>' attribute.
	 * @see #getCloudCoverTotal()
	 * @generated
	 */
	void setCloudCoverTotal(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover Below500</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloud cover below 500 ft.: 0..100% (N05)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover Below500</em>' attribute.
	 * @see #setCloudCoverBelow500(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverBelow500()
	 * @model
	 * @generated
	 */
	Float getCloudCoverBelow500();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverBelow500 <em>Cloud Cover Below500</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Below500</em>' attribute.
	 * @see #getCloudCoverBelow500()
	 * @generated
	 */
	void setCloudCoverBelow500(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover Effective</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Effective cloud cover: 0..100% (Neff)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover Effective</em>' attribute.
	 * @see #setCloudCoverEffective(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverEffective()
	 * @model
	 * @generated
	 */
	Float getCloudCoverEffective();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverEffective <em>Cloud Cover Effective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Effective</em>' attribute.
	 * @see #getCloudCoverEffective()
	 * @generated
	 */
	void setCloudCoverEffective(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover High</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * High cloud cover (>7 km): 0..100% (Nh)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover High</em>' attribute.
	 * @see #setCloudCoverHigh(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverHigh()
	 * @model
	 * @generated
	 */
	Float getCloudCoverHigh();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverHigh <em>Cloud Cover High</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover High</em>' attribute.
	 * @see #getCloudCoverHigh()
	 * @generated
	 */
	void setCloudCoverHigh(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover Mid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Midlevel cloud cover (2-7 km): 0..100% (Nm)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover Mid</em>' attribute.
	 * @see #setCloudCoverMid(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverMid()
	 * @model
	 * @generated
	 */
	Float getCloudCoverMid();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverMid <em>Cloud Cover Mid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Mid</em>' attribute.
	 * @see #getCloudCoverMid()
	 * @generated
	 */
	void setCloudCoverMid(Float value);

	/**
	 * Returns the value of the '<em><b>Cloud Cover Low</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Low cloud cover (lower than 2 km): 0..100% (Nl)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Cover Low</em>' attribute.
	 * @see #setCloudCoverLow(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_CloudCoverLow()
	 * @model
	 * @generated
	 */
	Float getCloudCoverLow();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getCloudCoverLow <em>Cloud Cover Low</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Cover Low</em>' attribute.
	 * @see #getCloudCoverLow()
	 * @generated
	 */
	void setCloudCoverLow(Float value);

	/**
	 * Returns the value of the '<em><b>Ir Radiance Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global Irradiance: kJ/m2 (Rad1h)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ir Radiance Global</em>' attribute.
	 * @see #setIrRadianceGlobal(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_IrRadianceGlobal()
	 * @model
	 * @generated
	 */
	Float getIrRadianceGlobal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getIrRadianceGlobal <em>Ir Radiance Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ir Radiance Global</em>' attribute.
	 * @see #getIrRadianceGlobal()
	 * @generated
	 */
	void setIrRadianceGlobal(Float value);

	/**
	 * Returns the value of the '<em><b>Surface Pressure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Surface pressure, reduced: Pa (PPPP)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Surface Pressure</em>' attribute.
	 * @see #setSurfacePressure(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_SurfacePressure()
	 * @model
	 * @generated
	 */
	Float getSurfacePressure();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSurfacePressure <em>Surface Pressure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Surface Pressure</em>' attribute.
	 * @see #getSurfacePressure()
	 * @generated
	 */
	void setSurfacePressure(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger02 Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 0.2mm during the last 6 hours: 0..100% (R602)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger02 Last6</em>' attribute.
	 * @see #setPrecipitationLarger02Last6(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger02Last6()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger02Last6();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last6 <em>Precipitation Larger02 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger02 Last6</em>' attribute.
	 * @see #getPrecipitationLarger02Last6()
	 * @generated
	 */
	void setPrecipitationLarger02Last6(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger50 Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 5mm during the last 6 hours: 0..100% (R650)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger50 Last6</em>' attribute.
	 * @see #setPrecipitationLarger50Last6(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger50Last6()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger50Last6();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last6 <em>Precipitation Larger50 Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger50 Last6</em>' attribute.
	 * @see #getPrecipitationLarger50Last6()
	 * @generated
	 */
	void setPrecipitationLarger50Last6(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger02 Last Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 0.2mm during the last 24 hours: 0..100% (Rd02)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger02 Last Day</em>' attribute.
	 * @see #setPrecipitationLarger02LastDay(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger02LastDay()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger02LastDay();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02LastDay <em>Precipitation Larger02 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger02 Last Day</em>' attribute.
	 * @see #getPrecipitationLarger02LastDay()
	 * @generated
	 */
	void setPrecipitationLarger02LastDay(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger50 Last Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 5mm during the last 24 hours: 0..100% (Rd50)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger50 Last Day</em>' attribute.
	 * @see #setPrecipitationLarger50LastDay(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger50LastDay()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger50LastDay();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50LastDay <em>Precipitation Larger50 Last Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger50 Last Day</em>' attribute.
	 * @see #getPrecipitationLarger50LastDay()
	 * @generated
	 */
	void setPrecipitationLarger50LastDay(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger00 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 0.0mm during the last 12 hours: 0..100% (Rh00)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger00 Last12</em>' attribute.
	 * @see #setPrecipitationLarger00Last12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger00Last12()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger00Last12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger00Last12 <em>Precipitation Larger00 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger00 Last12</em>' attribute.
	 * @see #getPrecipitationLarger00Last12()
	 * @generated
	 */
	void setPrecipitationLarger00Last12(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger02 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 0.2mm during the last 12 hours: 0..100% (Rh02)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger02 Last12</em>' attribute.
	 * @see #setPrecipitationLarger02Last12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger02Last12()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger02Last12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger02Last12 <em>Precipitation Larger02 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger02 Last12</em>' attribute.
	 * @see #getPrecipitationLarger02Last12()
	 * @generated
	 */
	void setPrecipitationLarger02Last12(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger10 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 1 mm during the last 12 hours: 0..100% (Rh10)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger10 Last12</em>' attribute.
	 * @see #setPrecipitationLarger10Last12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger10Last12()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger10Last12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger10Last12 <em>Precipitation Larger10 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger10 Last12</em>' attribute.
	 * @see #getPrecipitationLarger10Last12()
	 * @generated
	 */
	void setPrecipitationLarger10Last12(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Larger50 Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability of precipitation > 5mm during the last 12 hours: 0..100% (Rh50)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Larger50 Last12</em>' attribute.
	 * @see #setPrecipitationLarger50Last12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationLarger50Last12()
	 * @model
	 * @generated
	 */
	Float getPrecipitationLarger50Last12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationLarger50Last12 <em>Precipitation Larger50 Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Larger50 Last12</em>' attribute.
	 * @see #getPrecipitationLarger50Last12()
	 * @generated
	 */
	void setPrecipitationLarger50Last12(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Significant Weather Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total precipitation during the last hour consistent with significant weather: kg/m2 (RR1c)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Significant Weather Total</em>' attribute.
	 * @see #setPrecipitationSignificantWeatherTotal(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationSignificantWeatherTotal()
	 * @model
	 * @generated
	 */
	Float getPrecipitationSignificantWeatherTotal();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherTotal <em>Precipitation Significant Weather Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Significant Weather Total</em>' attribute.
	 * @see #getPrecipitationSignificantWeatherTotal()
	 * @generated
	 */
	void setPrecipitationSignificantWeatherTotal(Float value);

	/**
	 * Returns the value of the '<em><b>Precipitation Significant Weather Last3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total precipitation during the last 3 hours  consistent with significant weather: kg/m2 (RR3c)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precipitation Significant Weather Last3</em>' attribute.
	 * @see #setPrecipitationSignificantWeatherLast3(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PrecipitationSignificantWeatherLast3()
	 * @model
	 * @generated
	 */
	Float getPrecipitationSignificantWeatherLast3();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPrecipitationSignificantWeatherLast3 <em>Precipitation Significant Weather Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precipitation Significant Weather Last3</em>' attribute.
	 * @see #getPrecipitationSignificantWeatherLast3()
	 * @generated
	 */
	void setPrecipitationSignificantWeatherLast3(Float value);

	/**
	 * Returns the value of the '<em><b>Snow Rain Eq Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow-Rain-Equivalent during the last hour: kg/m2 (RRS1c)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Snow Rain Eq Last1</em>' attribute.
	 * @see #setSnowRainEqLast1(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_SnowRainEqLast1()
	 * @model
	 * @generated
	 */
	Float getSnowRainEqLast1();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast1 <em>Snow Rain Eq Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snow Rain Eq Last1</em>' attribute.
	 * @see #getSnowRainEqLast1()
	 * @generated
	 */
	void setSnowRainEqLast1(Float value);

	/**
	 * Returns the value of the '<em><b>Snow Rain Eq Last3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow-Rain-Equivalent during the 3 hours: kg/m2 (RRS3c)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Snow Rain Eq Last3</em>' attribute.
	 * @see #setSnowRainEqLast3(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_SnowRainEqLast3()
	 * @model
	 * @generated
	 */
	Float getSnowRainEqLast3();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSnowRainEqLast3 <em>Snow Rain Eq Last3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snow Rain Eq Last3</em>' attribute.
	 * @see #getSnowRainEqLast3()
	 * @generated
	 */
	void setSnowRainEqLast3(Float value);

	/**
	 * Returns the value of the '<em><b>Sunshine Duration Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sunshine duration during the last Hour: s (SunD1)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sunshine Duration Last1</em>' attribute.
	 * @see #setSunshineDurationLast1(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_SunshineDurationLast1()
	 * @model
	 * @generated
	 */
	Float getSunshineDurationLast1();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSunshineDurationLast1 <em>Sunshine Duration Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sunshine Duration Last1</em>' attribute.
	 * @see #getSunshineDurationLast1()
	 * @generated
	 */
	void setSunshineDurationLast1(Float value);

	/**
	 * Returns the value of the '<em><b>Temp Above Surface5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Temperature 5cm above surface: Kelvin (T5cm)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temp Above Surface5</em>' attribute.
	 * @see #setTempAboveSurface5(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_TempAboveSurface5()
	 * @model
	 * @generated
	 */
	Float getTempAboveSurface5();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface5 <em>Temp Above Surface5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Above Surface5</em>' attribute.
	 * @see #getTempAboveSurface5()
	 * @generated
	 */
	void setTempAboveSurface5(Float value);

	/**
	 * Returns the value of the '<em><b>Temp Above Surface200</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Temperature 2m above surface: Kelvin (TTT)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temp Above Surface200</em>' attribute.
	 * @see #setTempAboveSurface200(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_TempAboveSurface200()
	 * @model
	 * @generated
	 */
	Float getTempAboveSurface200();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempAboveSurface200 <em>Temp Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Above Surface200</em>' attribute.
	 * @see #getTempAboveSurface200()
	 * @generated
	 */
	void setTempAboveSurface200(Float value);

	/**
	 * Returns the value of the '<em><b>Temp Dewpoint Above Surface200</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dewpoint 2m above surface: Kelvin (Td)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temp Dewpoint Above Surface200</em>' attribute.
	 * @see #setTempDewpointAboveSurface200(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_TempDewpointAboveSurface200()
	 * @model
	 * @generated
	 */
	Float getTempDewpointAboveSurface200();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempDewpointAboveSurface200 <em>Temp Dewpoint Above Surface200</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Dewpoint Above Surface200</em>' attribute.
	 * @see #getTempDewpointAboveSurface200()
	 * @generated
	 */
	void setTempDewpointAboveSurface200(Float value);

	/**
	 * Returns the value of the '<em><b>Temp Min Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum temperature - within the last 12 hours: Kelvin (TN)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temp Min Last12</em>' attribute.
	 * @see #setTempMinLast12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_TempMinLast12()
	 * @model
	 * @generated
	 */
	Float getTempMinLast12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMinLast12 <em>Temp Min Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Min Last12</em>' attribute.
	 * @see #getTempMinLast12()
	 * @generated
	 */
	void setTempMinLast12(Float value);

	/**
	 * Returns the value of the '<em><b>Temp Max Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum temperature - within the last 12 hours: Kelvin (TX)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temp Max Last12</em>' attribute.
	 * @see #setTempMaxLast12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_TempMaxLast12()
	 * @model
	 * @generated
	 */
	Float getTempMaxLast12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getTempMaxLast12 <em>Temp Max Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temp Max Last12</em>' attribute.
	 * @see #getTempMaxLast12()
	 * @generated
	 */
	void setTempMaxLast12(Float value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Visibility: m (VV)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see #setVisibility(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_Visibility()
	 * @model
	 * @generated
	 */
	Float getVisibility();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(Float value);

	/**
	 * Returns the value of the '<em><b>Past Weather</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Past weather during the last 6 hours: - (W1W2)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Past Weather</em>' attribute.
	 * @see #setPastWeather(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_PastWeather()
	 * @model
	 * @generated
	 */
	Float getPastWeather();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getPastWeather <em>Past Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Past Weather</em>' attribute.
	 * @see #getPastWeather()
	 * @generated
	 */
	void setPastWeather(Float value);

	/**
	 * Returns the value of the '<em><b>Significant Weather</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Significant Weather: - (ww)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Significant Weather</em>' attribute.
	 * @see #setSignificantWeather(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_SignificantWeather()
	 * @model
	 * @generated
	 */
	Float getSignificantWeather();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getSignificantWeather <em>Significant Weather</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Significant Weather</em>' attribute.
	 * @see #getSignificantWeather()
	 * @generated
	 */
	void setSignificantWeather(Float value);

	/**
	 * Returns the value of the '<em><b>Fog Prop Last1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability for fog within the last hour: 0..100% (wwM)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fog Prop Last1</em>' attribute.
	 * @see #setFogPropLast1(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_FogPropLast1()
	 * @model
	 * @generated
	 */
	Float getFogPropLast1();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast1 <em>Fog Prop Last1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fog Prop Last1</em>' attribute.
	 * @see #getFogPropLast1()
	 * @generated
	 */
	void setFogPropLast1(Float value);

	/**
	 * Returns the value of the '<em><b>Fog Prop Last6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability for fog within the last 6 hours: 0..100% (wwM6)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fog Prop Last6</em>' attribute.
	 * @see #setFogPropLast6(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_FogPropLast6()
	 * @model
	 * @generated
	 */
	Float getFogPropLast6();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast6 <em>Fog Prop Last6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fog Prop Last6</em>' attribute.
	 * @see #getFogPropLast6()
	 * @generated
	 */
	void setFogPropLast6(Float value);

	/**
	 * Returns the value of the '<em><b>Fog Prop Last12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Probability for fog within the last 12 hours: 0..100% (wwMh)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fog Prop Last12</em>' attribute.
	 * @see #setFogPropLast12(Float)
	 * @see org.gecko.weather.model.weather.WeatherPackage#getMOSMIXSWeatherReport_FogPropLast12()
	 * @model
	 * @generated
	 */
	Float getFogPropLast12();

	/**
	 * Sets the value of the '{@link org.gecko.weather.model.weather.MOSMIXSWeatherReport#getFogPropLast12 <em>Fog Prop Last12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fog Prop Last12</em>' attribute.
	 * @see #getFogPropLast12()
	 * @generated
	 */
	void setFogPropLast12(Float value);

} // MOSMIXSWeatherReport
