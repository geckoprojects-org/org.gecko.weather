/**
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
 *     Data In Motion - initial API and implementation
 */
package org.gecko.weather.api.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.gecko.weather.model.weather.MOSMIXSWeatherReport;
import org.gecko.weather.model.weather.Measurement;
import org.gecko.weather.model.weather.W1W2;
import org.gecko.weather.model.weather.WMOWeatherCodeType;
import org.gecko.weather.model.weather.WeatherFactory;
import org.gecko.weather.model.weather.WeatherPackage;

import de.dwd.cdc.metelements.MetDefRoot;
import de.dwd.cdc.metelements.MetElementDefinitionType;
import net.opengis.kml.DocumentRoot;
import net.opengis.kml.KmlType;

/**
 * Helper class to calculate the forecast files
 * 
 * @author Mark Hoffmann
 * @since 30.08.2024
 */
public class DWDUtils {

	private static DWDUtils instance;

	public static DWDUtils getInstance() {
		if (isNull(instance)) {
			instance = new DWDUtils(System.getProperty("dwdBaseUrl", "https://opendata.dwd.de/weather/"));
		}
		return instance;
	}

	private DWDUtils(String baseUrl) {
		this.dwdBaseUrl = baseUrl;
	}

	public static final String URI_CDC_DWD = "https://opendata.dwd.de/climate_environment/CDC/";
	/*
	 * https://www.dwd.de/DE/leistungen/met_verfahren_ptp_dmo/met_verfahren_ptp_dmo.
	 * html Forecasts with more information in zipped kml
	 */
	public static final String URI_FORECAST_DMO_ALL_STATION = "local_forecasts/dmo/icon/all_stations/kmz/";
	/*
	 * https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/met_verfahren_mosmix.
	 * html Forecasts with less information than DMO in zipped kml. It contains
	 * probabilistic predictions
	 */
	public static final String URI_FORECAST_MOSMIX_ALL_STATION = "local_forecasts/mos/MOSMIX_S/all_stations/kml/";
	public static final String URI_FORECAST_MOSMIX_SINGLE_STATION = "local_forecasts/mos/MOSMIX_L/single_stations/%s/kml/";
	// filename identifier for 78 hour or 168 hour predictions
	public static final String DMO_SMALL_ID = "168_3";
	public static final String DMO_LARGE_ID = "078_1";
	// filename identifier for noon or afternoon
	public static final String DMO_NIGHT_ID = "0000";
	public static final String DMO_NOON_ID = "1200";
	// format is 'ptp_gdmog_<DMO_SMALL|LARGE_ID>_<DAY-OF-MONTH><DMO_NIGHT|NOON_ID>
	public static final String ZIPPED_KML_FILE_EXTENSION = "kmz";
	public static final String DMO_FILE_EXTENSION = "kml";
	public static final String DMO_ALL_BASE_FILENAME = "ptp_gdmog_%s_%02d%s." + ZIPPED_KML_FILE_EXTENSION;
	public static final String MOSMIX_ALL_LATEST_FILENAME = "MOSMIX_S_LATEST_240." + ZIPPED_KML_FILE_EXTENSION;
	public static final String MOSMIX_SINGLE_LATEST_FILENAME = "MOSMIX_L_LATEST_%s." + ZIPPED_KML_FILE_EXTENSION;
	// fetch data earliest
	public static final int DMO_NIGHT_HOUR = 4;
	public static final int DMO_NOON_HOUR = 16;
	private String dwdBaseUrl;

	/**
	 * Validate, because we only have 4 values from the reference date: if we are
	 * 0000 then we have two day before 1200 values if we are 1200 then we have one
	 * full day
	 * 
	 * @param fetchDate the fetch date
	 * @param large     - set to <code>true</code> to get the large data set
	 * @param refDate   a reference date
	 */
	public static String buildDMOForecastFile(Calendar fetchDate, boolean large, Calendar refDate) {
		requireNonNull(refDate);
		requireNonNull(fetchDate);
		fetchDate = normalizeToFullHours(fetchDate);
		Calendar now = normalizeToFullHours(refDate);
		Calendar last = normalizeToFullHours(refDate);
		if (isNight(now.get(Calendar.HOUR_OF_DAY))) {
			last.add(Calendar.DAY_OF_YEAR, -2);
			last.set(Calendar.HOUR_OF_DAY, 12);
		} else {
			last.add(Calendar.DAY_OF_YEAR, -1);
			last.set(Calendar.HOUR_OF_DAY, DMO_NIGHT_HOUR);
		}
		if (now.after(fetchDate)) {
			int hour = fetchDate.get(Calendar.HOUR_OF_DAY);
			int dom = fetchDate.get(Calendar.DAY_OF_MONTH);
			if (fetchDate.before(last)) {
				hour = last.get(Calendar.HOUR_OF_DAY);
				dom = last.get(Calendar.DAY_OF_MONTH);
			}
			return buildDMOForecastFile(dom, hour, large);
		} else {
			return buildDMOForecastFile(now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY), large);
		}
	}

	/**
	 * Validate, because we only have 4 values from the current timestamp: if we are
	 * 0000 then we have two day before 1200 values if we are 1200 then we have one
	 * full day
	 * 
	 * @param fetchDate the fetch date
	 * @param large     - set to <code>true</code> to get the large data set
	 */
	public static String buildDMOForecastFile(Calendar fetchDate, boolean large) {
		return buildDMOForecastFile(fetchDate, large, Calendar.getInstance());
	}

	public String buildDMOForecastUrl(Calendar fetchDate, boolean large) {
		return dwdBaseUrl + URI_FORECAST_DMO_ALL_STATION + buildDMOForecastFile(fetchDate, large);
	}

	public String buildMOSMIXForecastUrl() {
		return dwdBaseUrl + URI_FORECAST_MOSMIX_ALL_STATION + MOSMIX_ALL_LATEST_FILENAME;
	}

	public String buildMOSMIXSingleForecastUrl(String stationId) {
		return dwdBaseUrl + String.format(URI_FORECAST_MOSMIX_SINGLE_STATION, stationId)
				+ String.format(MOSMIX_SINGLE_LATEST_FILENAME, stationId);
	}

	public static Calendar normalizeToFullHours(Calendar date) {
		requireNonNull(date);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTimeInMillis());
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static boolean isNight(int hourOfDay) {
		if (hourOfDay < 0 || hourOfDay > 23) {
			throw new IllegalArgumentException("Hour of day value must be in range 0..23");
		}
		return hourOfDay < DMO_NOON_HOUR && hourOfDay >= DMO_NIGHT_HOUR;
	}

	public static String buildDMOForecastFile(int dayOfMonth, int hourOfDay, boolean large) {
		if (hourOfDay < 0 || hourOfDay > 23) {
			throw new IllegalArgumentException("Hour of day value must be in range 0..23");
		}
		if (dayOfMonth < 0 || dayOfMonth > 31) {
			throw new IllegalArgumentException("Day of month must be in range 0..31");
		}
		String dmoDayId = DMO_NOON_ID;
		int day = dayOfMonth;
		if (isNight(hourOfDay)) {
			dmoDayId = DMO_NIGHT_ID;
		} else {
			// we are on the next day, so we have to take the afternoon file from the last
			// day
			if (hourOfDay < DMO_NOON_HOUR) {
				day--;
			}
		}
		return String.format(DMO_ALL_BASE_FILENAME, large ? DMO_LARGE_ID : DMO_SMALL_ID, day, dmoDayId);
	}

	public static InputStream unzip(InputStream zippedSource) {
		requireNonNull(zippedSource);
		try (ZipInputStream zis = new ZipInputStream(zippedSource)) {
			ZipEntry entry = zis.getNextEntry();
			if (nonNull(entry)) {
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len;
				while ((len = zis.read(buffer)) > 0) {
					baos.write(buffer, 0, len);
				}
				zis.closeEntry();
				baos.close();
				return new ByteArrayInputStream(baos.toByteArray());
			} else {
				throw new IllegalStateException("Zip file seems to be empty");
			}

		} catch (IOException e) {
			throw new IllegalStateException("Error unzipping file", e);
		}
	}

	public static KmlType getKML(EObject eObject) {
		DocumentRoot root = (DocumentRoot) eObject;
		return root.getKml();
	}

	public static MetElementDefinitionType getMetDefinitions(EObject eObject) {
		MetDefRoot root = (MetDefRoot) eObject;
		return root.getMetElementDefinition();
	}

	public static Measurement createMOSMIXMeasurement(String id) {
		Measurement m;
		switch (id) {
		case "DD":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Wind Direction");
			m.setUnit("Degree");
			return m;
		case "PPPP":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Surface pressure, reduced");
			m.setUnit("Pascal");
			return m;
		case "TX":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Maximum temperature - within the last 12 hours");
			m.setUnit("Kelvin");
			return m;
		case "TTT":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Temperature 2m above surface");
			m.setUnit("Kelvin");
			return m;
		case "Td":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Dewpoint 2m above surface");
			m.setUnit("Kelvin");
			return m;
		case "TN":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Minimum temperature - within the last 12 hours");
			m.setUnit("Kelvin");
			return m;
		case "T5cm":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Temperature 5cm above surface");
			m.setUnit("Kelvin");
			return m;
		case "FF":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Wind speed");
			m.setUnit("m/s");
			return m;
		case "FX1":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Maximum wind gust within the last hour");
			m.setUnit("m/s");
			return m;
		case "FX3":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Maximum wind gust within the last 3 hours");
			m.setUnit("m/s");
			return m;
		case "FXh":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Maximum wind gust within the last 12 hours");
			m.setUnit("m/s");
			return m;
		case "FXh25":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Probability of wind gusts >= 25kn within the last 12 hours");
			m.setUnit("m/s");
			return m;
		case "FXh40":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Probability of wind gusts >= 40kn within the last 12 hours");
			m.setUnit("m/s");
			return m;
		case "FXh55":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Probability of wind gusts >= 55kn within the last 12 hours");
			m.setUnit("m/s");
			return m;
		case "N":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Total cloud cover");
			m.setUnit("0..100%");
			return m;
		case "Neff":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Effective cloud cover");
			m.setUnit("0..100%");
			return m;
		case "Nh":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("High cloud cover (>7 km)");
			m.setUnit("0..100%");
			return m;
		case "Nm":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Midlevel cloud cover (2-7 km)");
			m.setUnit("0..100%");
			return m;
		case "Nl":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Low cloud cover (lower than 2 km)");
			m.setUnit("0..100%");
			return m;
		case "N05":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Cloud cover below 500 ft.");
			m.setUnit("0..100%");
			return m;
		case "VV":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Visibility");
			m.setUnit("m");
			return m;
		case "wwM":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability for fog within the last hour");
			m.setUnit("0..100%");
			return m;
		case "wwM6":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability for fog within the last 6 hours");
			m.setUnit("0..100%");
			return m;
		case "wwMh":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability for fog within the last 12 hours");
			m.setUnit("0..100%");
			return m;
		case "ww":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Significant Weather");
			m.setUnit("0..95");
			return m;
		case "W1W2":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Past weather during the last 6 hours");
			m.setUnit("0..95");
			return m;
		case "RR1c":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Total precipitation during the last hour consistent with significant weather");
			m.setUnit("kg/m2");
			return m;
		case "RRS1c":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Snow-Rain-Equivalent during the last hour");
			m.setUnit("kg/m2");
			return m;
		case "RR3c":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Total precipitation during the last 3 hours  consistent with significant weather ");
			m.setUnit("kg/m2");
			return m;
		case "RRS3c":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Snow-Rain-Equivalent during the last 3 hours");
			m.setUnit("kg/m2");
			return m;
		case "R602":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 0.2mm during the last 6 hours");
			m.setUnit("0..100");
			return m;
		case "R650":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 5.0mm during the last 6 hours");
			m.setUnit("0..100");
			return m;
		case "Rh00":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 0.0mm during the last 12 hours");
			m.setUnit("0..100");
			return m;
		case "Rh02":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 0.2mm during the last 12 hours");
			m.setUnit("0..100");
			return m;
		case "Rh10":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 1.0mm during the last 12 hours");
			m.setUnit("0..100");
			return m;
		case "Rh50":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 5.0mm during the last 12 hours");
			m.setUnit("0..100");
			return m;
		case "Rd02":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 0.2mm during the last 24 hours");
			m.setUnit("0..100");
			return m;
		case "Rd50":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Probability of precipitation > 5.0mm during the last 24 hours");
			m.setUnit("0..100");
			return m;
		case "Rad1h":
			m = WeatherFactory.eINSTANCE.createFloatMeasurement();
			m.setName("Global Irradiance");
			m.setUnit("kJ/m2");
			return m;
		case "SunD1":
			m = WeatherFactory.eINSTANCE.createIntMeasurement();
			m.setName("Sunshine duration during the last Hour");
			m.setUnit("s");
			return m;
		default:
			throw new IllegalArgumentException("Unexpected value: " + id);
		}
	}

	public static void setMOSMIXMeasurement(MOSMIXSWeatherReport report, String measurementId, Object value) {
		requireNonNull(report);
		requireNonNull(measurementId);
		if (isNull(value) || "-".equals(value.toString().trim())) {
			return;
		}
		EStructuralFeature feature;
		switch (measurementId) {
		case "DD":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_DIRECTION;
			break;
		case "PPPP":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SURFACE_PRESSURE;
			break;
		case "TX":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__TEMP_MAX_LAST12;
			break;
		case "TTT":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE200;
			break;
		case "Td":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__TEMP_DEWPOINT_ABOVE_SURFACE200;
			break;
		case "TN":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__TEMP_MIN_LAST12;
			break;
		case "T5cm":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__TEMP_ABOVE_SURFACE5;
			break;
		case "FF":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_SPEED;
			break;
		case "FX1":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_HOUR;
			break;
		case "FX3":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_LAST_THREE_HOURS;
			break;
		case "FXh":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_MAX_LAST12_HOURS;
			break;
		case "FXh25":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB25;
			break;
		case "FXh40":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB40;
			break;
		case "FXh55":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__WIND_GUST_PROB55;
			break;
		case "N":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_TOTAL;
			break;
		case "Neff":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_EFFECTIVE;
			break;
		case "Nh":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_HIGH;
			break;
		case "Nm":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_MID;
			break;
		case "Nl":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_LOW;
			break;
		case "N05":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__CLOUD_COVER_BELOW500;
			break;
		case "VV":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__VISIBILITY;
			break;
		case "wwM":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST1;
			break;
		case "wwM6":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST6;
			break;
		case "wwMh":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__FOG_PROP_LAST12;
			break;
		case "ww":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER3_HOURS;
			int wwCode = Math.round((float) value);  // e.g., 1.0 → 1
			WMOWeatherCodeType wwEnumCode = WMOWeatherCodeType.get(wwCode);
			if(wwEnumCode == null) wwEnumCode = WMOWeatherCodeType.WUNKNOWN;
			value = wwEnumCode;
			break;
		case "W1W2":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SIGNIFICANT_WEATHER6_HOURS;
			int code = Math.round((float) value); // e.g. → 30.0 -> 30
			int[] w1w2Code = parse(code);
			WMOWeatherCodeType w1Code = WMOWeatherCodeType.get(w1w2Code[0]);
			if(w1Code == null) {
				w1Code = WMOWeatherCodeType.WUNKNOWN;
			}
			WMOWeatherCodeType w2Code = WMOWeatherCodeType.get(w1w2Code[1]);
			if(w2Code == null) {
				w2Code = WMOWeatherCodeType.WUNKNOWN;
			}
			W1W2 w1w2 = WeatherFactory.eINSTANCE.createW1W2();
			w1w2.setW1(w1Code);
			w1w2.setW2(w2Code);
			value = w1w2;
			break;
		case "RR1c":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_TOTAL;
			break;
		case "RRS1c":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST1;
			break;
		case "RR3c":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_SIGNIFICANT_WEATHER_LAST3;
			break;
		case "RRS3c":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SNOW_RAIN_EQ_LAST3;
			break;
		case "R602":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST6;
			break;
		case "R650":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST6;
			break;
		case "Rh00":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER00_LAST12;
			break;
		case "Rh02":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST12;
			break;
		case "Rh10":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER10_LAST12;
			break;
		case "Rh50":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST12;
			break;
		case "Rd02":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER02_LAST_DAY;
			break;
		case "Rd50":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__PRECIPITATION_LARGER50_LAST_DAY;
			break;
		case "Rad1h":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__IR_RADIANCE_GLOBAL;
			break;
		case "SunD1":
			feature = WeatherPackage.Literals.MOSMIXS_WEATHER_REPORT__SUNSHINE_DURATION_LAST1;
			break;
		default:
			return;
		}
		if(nonNull(feature)) {
			if(feature instanceof EAttribute attr) {
				if(value.getClass() == attr.getEAttributeType().getInstanceClass()) {
					report.eSet(attr, value);			
				}
			} else {
				report.eSet(feature, value);		
			}
		}
	}
	
	public static int[] parse(int number) {
        // This works for any non-negative integer (e.g., 3, 23, 123, 1234)
        int firstValue = number / 100;
        int secondValue = number % 100;
        return new int[] {firstValue, secondValue};
    }
}
