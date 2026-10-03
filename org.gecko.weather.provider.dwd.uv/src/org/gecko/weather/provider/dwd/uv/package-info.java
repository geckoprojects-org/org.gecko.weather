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
 * DWD MOSMIX as a {@link org.gecko.weather.api.spi.WeatherProvider}: catalogue, binding, fetch,
 * decode, map. Exported so that a MOSMIX_S variant or a test can reuse the pieces; the vocabulary
 * stays inside — no MOSMIX element name leaves this package except as {@code sourceElement} data.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.gecko.weather.provider.dwd.uv;
