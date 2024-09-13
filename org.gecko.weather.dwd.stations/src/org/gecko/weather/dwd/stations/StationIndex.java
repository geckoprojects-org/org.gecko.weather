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
package org.gecko.weather.dwd.stations;

import org.gecko.weather.model.weather.Station;

/**
 * Station index service interface
 * @author Mark Hoffmann
 * @since 10.09.2024
 */
public interface StationIndex {

	/**
	 * Indexes a station as add, if parameter add is set to <code>true</code>
	 * @param station the station to be added
	 * @param add set to <code>true</code>, for adding, <code>false</code> for updating
	 */
	void indexStation(Station station, boolean add);

	/**
	 * Deletes a station from the index
	 * @param station the station to delete
	 */
	void deleteStation(Station station);

	/**
	 * Resets the whole index
	 */
	void resetIndex();

}