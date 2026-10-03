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
 * The site id is not registered. Kept distinct from other failures so that a later HTTP layer can map
 * it to its own status (INT-11).
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class UnknownSiteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String siteId;

	public UnknownSiteException(String siteId) {
		super("Unknown site: " + siteId);
		this.siteId = siteId;
	}

	public String getSiteId() {
		return siteId;
	}

}
