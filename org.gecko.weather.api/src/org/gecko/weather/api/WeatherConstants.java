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
package org.gecko.weather.api;

/**
 * Service property names shared by the whiteboards of this API, so that consumers and ingest can
 * target one provider or product with an LDAP filter, e.g.
 * {@code (&(weather.provider.id=dwd)(weather.product.id=MOSMIX_L))}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class WeatherConstants {

	/** Service property: the provider identifier, e.g. {@code dwd}. */
	public static final String PROVIDER_ID = "weather.provider.id";

	/** Service property: the product identifier within the provider, e.g. {@code MOSMIX_L}. */
	public static final String PRODUCT_ID = "weather.product.id";

	/** Provider id used for values this service computes itself (solar geometry). */
	public static final String COMPUTED_PROVIDER_ID = "gecko";

	/** Product id of the computed solar dataset. */
	public static final String SOLAR_PRODUCT_ID = "solar";

	private WeatherConstants() {
	}

}
