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
/**
 * The provider SPI: a {@link org.gecko.weather.api.spi.WeatherProvider} per source product fetches
 * for all bound sites in one go and returns ready datasets; a
 * {@link org.gecko.weather.api.spi.SiteBindingResolver} per product answers where the product is
 * read for a site. Nothing here knows a vendor.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.gecko.weather.api.spi;
