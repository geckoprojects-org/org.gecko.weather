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
package org.gecko.weather.dwd.fc.fetcher;

import static java.util.Objects.requireNonNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.gecko.weather.dwd.fc.util.DWDUtils;

/**
 * Abstract class for fetching and loading DWD. It also supports unzipping single files out of a zip archive.
 * @author Mark Hoffmann
 * @since 05.09.2024
 */
public abstract class DWDFetcher {
	
	/**
	 * Return the download url
	 * @return the download url, must not be <code>null</code>
	 */
	protected abstract String getFetchUrl(); 
	
	/**
	 * Returns an identifier for the downloader / fetcher
	 * @return an identifier
	 */
	protected abstract String getName();
	
	public InputStream doDownload() throws IOException {
		String urlString = getFetchUrl();
		long start = System.currentTimeMillis();
		try {
			System.out.println(String.format("[%s] Downloading from URL: '%s'", getName(), urlString));
			URL url = new URL(urlString);
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			return bis;
		} finally {
			System.out.println(String.format("[%s] Downloaded file (%s ms)", getName(), (System.currentTimeMillis() - start)));
		}
	}

	public InputStream doUnzip(InputStream zippedSource) {
		requireNonNull(zippedSource);
		System.out.println(String.format("[%s] Unzipping the file", getName()));
		long start = System.currentTimeMillis();
		try {
			return DWDUtils.unzip(zippedSource);
		} finally {
			System.out.println(String.format("[%s] Unzipped from the file (%s ms)", getName(), (System.currentTimeMillis() - start)));
		}
	}

}
