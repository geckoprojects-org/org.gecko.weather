/**
 * Copyright (c) 2012 - 2024 Data In Motion and others.
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
package org.gecko.weather.api.fetcher;

import static java.util.Objects.requireNonNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

import org.gecko.weather.api.util.DWDUtils;

/**
 * Abstract class for fetching and loading DWD. It also supports unzipping
 * single files out of a zip archive.
 * 
 * @author Mark Hoffmann
 * @since 05.09.2024
 */
public abstract class DWDFetcher {

	private static final Logger LOGGER = System.getLogger(DWDFetcher.class.getName());

	/**
	 * Return the download url
	 * 
	 * @return the download url, must not be <code>null</code>
	 */
	protected abstract String getFetchUrl();

	/**
	 * Returns an identifier for the downloader / fetcher
	 * 
	 * @return an identifier
	 */
	protected abstract String getName();
	
	public InputStream doDownload() throws IOException	 {
		String urlString = getFetchUrl();
		long start = System.currentTimeMillis();
		try {
			LOGGER.log(Level.INFO, "[{0}] Downloading from URL: {1}", getName(), urlString);
			URI uri = URI.create(urlString);
			URL url;
			if (uri.isAbsolute()) {
				LOGGER.log(Level.INFO, "[{0}] Absolute URI {1}", getName(), uri);
				url = uri.toURL();
			} else {
				String path = System.getProperty("baseDir", "");
				URI current = Paths.get(path).toAbsolutePath().toUri();
				LOGGER.log(Level.INFO, "[{0}] Current relative URI {0}, given URI is: {1}", getName(), current, uri);
				url = current.resolve(uri).toURL();
			}
			return new BufferedInputStream(url.openStream());
		} finally {
			LOGGER.log(Level.INFO, "[{0}] Downloaded file ({1} ms)", getName(), (System.currentTimeMillis() - start));
		}
	}

	public InputStream doUnzip(InputStream zippedSource) {
		requireNonNull(zippedSource);
		LOGGER.log(Level.INFO, "[{0}] Unzip the file", getName());
		long start = System.currentTimeMillis();
		try {
			return DWDUtils.unzip(zippedSource);
		} finally {
			LOGGER.log(Level.INFO, "[{0}] Unzipped file ({1} ms)", getName(), (System.currentTimeMillis() - start));
		}
	}

}
