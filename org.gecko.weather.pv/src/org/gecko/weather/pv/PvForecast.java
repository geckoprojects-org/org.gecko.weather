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
	 * The next 48 hours from the current full hour and the days from today on.
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
