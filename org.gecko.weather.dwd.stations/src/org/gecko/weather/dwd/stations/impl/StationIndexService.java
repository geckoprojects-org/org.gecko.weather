/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
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
package org.gecko.weather.dwd.stations.impl;

import java.io.IOException;

import org.gecko.emf.search.document.EObjectDocumentIndexObjectContext;
import org.gecko.search.IndexActionType;
import org.gecko.search.document.LuceneIndexService;
import org.gecko.weather.dwd.stations.StationIndex;
import org.gecko.weather.dwd.stations.helper.StationIndexHelper;
import org.gecko.weather.model.weather.WeatherStation;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * This is a sample Index Service to index objects.
 */
@Component
public class StationIndexService implements StationIndex {

	@Reference(target = "(id=dwd.station)")
	private LuceneIndexService<EObjectDocumentIndexObjectContext> stationIndex;
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.stations.impl.StationIndex#indexStation(de.dwd.cdc.weather.Station, boolean)
	 */
	@Override
	public void indexStation(WeatherStation station, boolean add) {
		if(add) {
			doIndexStation(station, IndexActionType.ADD);
		}
		else {
			doIndexStation(station, IndexActionType.MODIFY);
		}	
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.stations.impl.StationIndex#deleteStation(de.dwd.cdc.weather.Station)
	 */
	@Override
	public void deleteStation(WeatherStation station) {
		doIndexStation(station, IndexActionType.REMOVE);		
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.weather.dwd.stations.impl.StationIndex#resetIndex()
	 */
	@Override
	public void resetIndex() {
		try {
			stationIndex.getIndexWriter().deleteAll();
			stationIndex.commit();
		} catch (IOException e) {
			System.err.println("Could not delete Station index " + e);
		}		
		
	}
	
	private void doIndexStation(WeatherStation station, IndexActionType actionType) {
		EObjectDocumentIndexObjectContext context = StationIndexHelper.mapStation(station, actionType);			
		stationIndex.handleContextSync(context);
	}
	
}