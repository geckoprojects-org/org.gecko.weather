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
package org.gecko.weather.netcdf;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.gecko.weather.model.weather.GeoArea;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Measurement;
import org.gecko.weather.model.weather.UVRadiationMeasurement;
import org.gecko.weather.model.weather.WeatherFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import ucar.ma2.Array;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFiles;
import ucar.nc2.Variable;

/**
 * https://www.dwd.de/DE/leistungen/fernerkund_globalstrahlung_sis/fernerkund_globalstrahlung_sis.html
 * https://www.dwd.de/DE/leistungen/fernerkund_globalstrahlung_sis/sis_datenformat.pdf;jsessionid=53181F813B1489B1B1EEA62ACD734250.live11044?__blob=publicationFile&v=3
 * @author mark
 * @since 06.09.2024
 */
@Component
public class NetCDFTest {

	private static final String FILE_NAME = "SISin202409040800DEv3.nc";
//	private static final String FILE_NAME_FC = "SISfc2024090608_fc+18h-DE.nc";

	@Reference
	private WeatherFactory weatherFactory;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

	@Activate
	public void activate() {
		try {
			List<Measurement> measurements = new LinkedList<>();
			long start = System.currentTimeMillis();
			if (NetcdfFiles.canOpen("data/" + FILE_NAME)) {
				System.out.println("canopen");
				try (NetcdfFile netcdfFile = NetcdfFiles.open("data/" + FILE_NAME)) {
					System.out.println(netcdfFile.getFileTypeId());
					Variable time = netcdfFile.findVariable("time");
					double[] times = null;
					float[] lats = null;
					float[] lons = null;
					if (Objects.nonNull(time)) {
						times = (double[])time.read().copyTo1DJavaArray();// 18 forecasts
						System.out.println("Time Array: " + times.length);
					}
					Variable lat = netcdfFile.findVariable("lat");
					if (Objects.nonNull(lat)) {
						lats = (float[])lat.read().copyTo1DJavaArray();
						System.out.println("Lat Array: " + lats.length);
					}
					Variable lon = netcdfFile.findVariable("lon");
					if (Objects.nonNull(lon)) {
						lons = (float[])lon.read().copyTo1DJavaArray();
						System.out.println("Lon Array: " + lons.length);
					}

					Variable sis = netcdfFile.findVariable("SIS");
					if (Objects.nonNull(sis)) {
						try {
							Object[][] o = (Object[][]) sis.read().copyToNDJavaArray();
							for (int i = 0; i < o.length; i++) {
								double h = times[i];
								Object[] la = o[i];
								for (int j = 0; j < la.length; j++) {
									float lav = lats[j];
									/**
									 * use float for forecast files
									 */
//									float[] siss = (float[]) la[j];
									/**
									 * use short for single time files
									 */
									short[] siss = (short[]) la[j];
									for (int k = 0; k < siss.length; k++) {
										float lov = lons[k];
										float sisValue = siss[k];
										System.out.println("Value: " + sisValue + "W/m2 \t (hour: " + h + ", lat: " + lav + ", lon: " + lov + ")");
										UVRadiationMeasurement measurement = weatherFactory.createUVRadiationMeasurement();
										GeoPosition pos = weatherFactory.createGeoPosition();
										pos.setLatitude(lav);
										pos.setLongitude(lov);
										GeoArea area = weatherFactory.createGeoArea();
										area.setTopLeft(pos);
										area.setXDim(0.05d);
										area.setYDim(0.05d);
										measurement.setArea(area);
										measurement.setValue(sisValue);
										measurement.setTimestamp(getRefDate((int)h));
										measurements.add(measurement);
									}
								}
							}
							// sectionSpec is string specifying a range of data, eg ":,1:2,0:3"
							Array data = sis.read();
							System.out.println("SIS Array: " + data.getSize());
							//							Index3D idx = (Index3D) data.getIndex();
							//							while (Objects.nonNull(idx)) {
							//								int[] counter = idx.getCurrentCounter();
							//								if (counter.length == idx.getRank()) {
							//									double hour = times[counter[0]];
							//									float latValue = lats[counter[1]];
							//									float lonValue = lons[counter[2]];
							//									float sisData = data.getFloat(idx);
							////									System.out.println("Value: " + sisData + " (hour: " + hour + ", lat: " + latValue + ", lon: " + lonValue + ")");
							////									UVRadiationMeasurement measurement = weatherFactory.createUVRadiationMeasurement();
							////									GeoPosition pos = weatherFactory.createGeoPosition();
							////									pos.setLatitude(latValue);
							////									pos.setLongitude(lonValue);
							////									GeoArea area = weatherFactory.createGeoArea();
							////									area.setTopLeft(pos);
							////									area.setXDim(0.05d);
							////									area.setYDim(0.05d);
							////									measurement.setArea(area);
							////									measurement.setValue(sisData);
							////									measurement.setTimestamp(getRefDate((int)hour));
							////									measurements.add(measurement);
							//								}
							//								idx.incr();
							//							}
							System.out.println("SIS Measures: " + measurements.size() + " within " + (System.currentTimeMillis() - start) + " ms");
							//							String arrayStr = Ncdump.printArray(data, "SIS", null);
							//							System.out.println(arrayStr);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				};
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @throws ParseException
	 */
	private Date getRefDate(int hour) throws ParseException {
		Date refDate = sdf.parse("2024090608");
		Calendar refCal = Calendar.getInstance();
		refCal.setTime(refDate);
		refCal.set(Calendar.HOUR_OF_DAY, 0);
		refCal.set(Calendar.MINUTE, 0);
		refCal.set(Calendar.SECOND, 0);
		refCal.add(Calendar.HOUR_OF_DAY, hour);
		return refCal.getTime();
	}

}
