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
 * {@link org.gecko.weather.api.repository.WeatherRepository} over a folder of XMI files. The plain
 * core is {@link org.gecko.weather.repository.file.XmiFolderRepository}; the OSGi layer is one DS
 * component with a metatype configuration for the root folder.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.gecko.weather.repository.file;
