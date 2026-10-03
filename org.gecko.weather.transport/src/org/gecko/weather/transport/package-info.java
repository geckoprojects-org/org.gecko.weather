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
 * Byte transport for providers: {@link org.gecko.weather.transport.ByteSource} with conditional
 * fetches, {@link org.gecko.weather.transport.HttpByteSource} over the JDK client,
 * {@link org.gecko.weather.transport.Unwrap} for archives. Deliberately not part of the SPI — a
 * provider may use it or not.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.gecko.weather.transport;
