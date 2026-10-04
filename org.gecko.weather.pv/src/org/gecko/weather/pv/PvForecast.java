/**
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
 *     Data In Motion - initial API and implementation
 */
package org.gecko.weather.pv;

import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PlantDirectory;
import org.gecko.weather.pv.model.pv.PvMeasurementLog;
import org.gecko.weather.pv.model.pv.PvOutlook;
import org.osgi.annotation.versioning.ProviderType;

/**
 * What a PV plant is expected to produce, as a remote service — exported through the Remote
 * Service Admin of Fennec Services like WeatherOutlook. A consumer of the weather service: the
 * radiation, temperature and wind come from the report of the plant's weather site. A plant with a
 * meter has its readings beside the forecast, for comparison.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@ProviderType
public interface PvForecast {

	/** The plants with a profile. */
	PlantDirectory plants();

	/**
	 * A plant's profile as stored — arrays, inverters, obstacles, horizon, position — for a view that
	 * draws or edits it.
	 *
	 * @throws IllegalArgumentException if there is no such plant
	 */
	Plant plant(String plantId);

	/**
	 * Stores a profile under its id, replacing one of the same id; a new id creates a plant. The id
	 * is the file name: letters, digits, dot, dash and underscore.
	 *
	 * @return the profile as stored
	 * @throws IllegalArgumentException if the id is missing or not usable as a file name
	 */
	Plant savePlant(Plant plant);

	/**
	 * The hours from the start of today (in the site's time zone) up to 48 hours past the current
	 * full hour, and the days from today on. The hours already passed today carry the measured power
	 * beside the forecast when the plant has a meter.
	 *
	 * @throws IllegalArgumentException if there is no such plant
	 * @throws org.gecko.weather.api.UnknownSiteException if the plant's weather site is not registered
	 */
	PvOutlook forecast(String plantId);

	/**
	 * What the plant's meter recorded on a local day — empty when nothing was recorded.
	 *
	 * @param date ISO {@code yyyy-MM-dd}; empty or null for today
	 * @throws IllegalArgumentException if there is no such plant or the date is malformed
	 */
	PvMeasurementLog measurements(String plantId, String date);
}
