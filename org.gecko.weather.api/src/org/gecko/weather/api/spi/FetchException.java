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
package org.gecko.weather.api.spi;

/**
 * The source answered, but not with what the provider expects: wrong format, missing header, unknown
 * grid, a systematically failing mapping. Distinct from {@link java.io.IOException} so that the
 * runtime can tell "retry soon" from "this will not get better by retrying".
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class FetchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FetchException(String message) {
		super(message);
	}

	public FetchException(String message, Throwable cause) {
		super(message, cause);
	}

}
