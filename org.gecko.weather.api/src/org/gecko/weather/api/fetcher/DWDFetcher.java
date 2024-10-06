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
			LOGGER.log(Level.INFO, "{0} Downloading from URL: {1}", getName(), urlString);
			URI uri = URI.create(urlString);
			URL url;
			LOGGER.log(Level.INFO, "Base dir Project dir {0}", System.getProperty("baseDir"));
			LOGGER.log(Level.INFO, "Base path Project dir {0}", System.getProperty("basePath"));
			if (uri.isAbsolute()) {
				LOGGER.log(Level.INFO, "Absolute URI {0}", uri);
				url = uri.toURL();
			} else {
				String path = System.getProperty("baseDir", "");
				URI current = Paths.get(path).toAbsolutePath().toUri();
				LOGGER.log(Level.INFO, "Current relative URI {0}, given URI is: {1}", current, uri);
				url = current.resolve(uri).toURL();
			}
			LOGGER.log(Level.INFO, "URL is {0}", url);
			return new BufferedInputStream(url.openStream());
		} finally {
			LOGGER.log(Level.INFO, "{0} Downloading file {1}", getName(), (System.currentTimeMillis() - start));
		}
	}

	public InputStream doUnzip(InputStream zippedSource) {
		requireNonNull(zippedSource);
		LOGGER.log(Level.INFO, "{0} Unzip thefile: {1}", getName());
		long start = System.currentTimeMillis();
		try {
			return DWDUtils.unzip(zippedSource);
		} finally {
			LOGGER.log(Level.INFO, "{0} Unzipped from the file {1}", getName(), (System.currentTimeMillis() - start));
		}
	}

}
