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
package org.gecko.weather.outlook;

import org.gecko.weather.outlook.model.outlook.Outlook;
import org.gecko.weather.outlook.model.outlook.SiteDirectory;
import org.osgi.annotation.versioning.ProviderType;

/**
 * A weather page's view of a site, as a remote service. Exported through the Remote Service Admin
 * of Fennec Services: the contract is derived from this interface, served over REST and announced
 * to the DDSR broker, where a Java or TypeScript consumer finds it by the name {@code WeatherOutlook}.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@ProviderType
public interface WeatherOutlook {

	/** The registered, active sites an outlook can be asked for. */
	SiteDirectory sites();

	/**
	 * The next 24 hours from the current full hour and the next two days, in the site's time zone.
	 *
	 * @throws org.gecko.weather.api.UnknownSiteException if no such site is registered
	 */
	Outlook outlook(String siteId);
}
