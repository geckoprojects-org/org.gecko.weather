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
package org.gecko.weather.dwd.stations.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.gecko.weather.dwd.fc.fetcher.DWDFetcher;
import org.gecko.weather.dwd.stations.StationSearch;
import org.gecko.weather.model.weather.GeoPosition;
import org.gecko.weather.model.weather.Station;
import org.gecko.weather.model.weather.WeatherFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import biz.aQute.scheduler.api.CronJob;

//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

@RequireConfigurationAdmin
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
@ExtendWith(BundleContextExtension.class)
//@ExtendWith(MockitoExtension.class)
@WithFactoryConfiguration(factoryPid = "DWDStationFetcher", location = "?", name = "fetcher", properties = {
		@Property(key = "stationMosmixUrl", value = "file:data/mosmix_stationskatalog.csv"),
		@Property(key = "stationUrl", value = "file:data/stations_list_CLIMAT_data.csv") })
@WithFactoryConfiguration(factoryPid = "EMFLuceneIndex", location = "?", name = "index", properties = {
		@Property(key = "id", value = "dwd.station"), @Property(key = "directory.type", value = "ByteBuffer") })
public class DWDStationListTest {

	
	@BeforeAll
	public static void beforeAll(@InjectService(cardinality = 0) ServiceAware<CronJob> dwdAware) throws Exception {
		
		CronJob fetcher = (CronJob) dwdAware.waitForService(1000);
		assertThat(fetcher).isNotNull();
		fetcher.run();
	}
	
	
	@Test
	public void testSearchByName(@InjectService(cardinality = 0) ServiceAware<DWDFetcher> dwdAware,
			@InjectService(cardinality = 0) ServiceAware<StationSearch> siAware) throws InterruptedException  {

		StationSearch stationSearch = siAware.waitForService(1000);
		List<Station> result = stationSearch.searchStationByName("erfurt", false);
		assertThat(result) //
				.hasSize(5)//
				.extracting(Station::getId) //
				.contains("10554", "N6357", "G409", "P0560", "N748");
		assertThat(result) //
				.extracting(Station::getName) //
				.contains("Erfurt", "QUERFURT", "ERFURT LVG", "ERFURT-STADT", "QUERFURT-LODERSL.");
	}

//	@Test
	public void testSearchNear(@InjectService(cardinality = 0) ServiceAware<DWDFetcher> dwdAware,
			@InjectService(cardinality = 0) ServiceAware<StationSearch> siAware) throws InterruptedException {

		StationSearch stationSearch = siAware.waitForService(1000);
		GeoPosition geoPosition = WeatherFactory.eINSTANCE.createGeoPosition();
		geoPosition.setLatitude(50.92126987121617);
		geoPosition.setLongitude(11.580468982385781);
		List<Station> result = stationSearch.searchStationNearLocation(geoPosition, 5);
		assertThat(result) //
				.hasSize(5)//
				.extracting(Station::getId) //
				.contains("10554", "N6357", "G409", "P0560", "N748");
		assertThat(result) //
				.extracting(Station::getName) //
				.contains("Erfurt", "QUERFURT", "ERFURT LVG", "ERFURT-STADT", "QUERFURT-LODERSL.");
	}

}
