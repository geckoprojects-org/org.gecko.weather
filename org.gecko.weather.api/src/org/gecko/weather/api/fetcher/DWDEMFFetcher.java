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

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Abstract class for fetching and loading DWD. It also supports unzipping
 * single files out of a zip archive. It supports loading EMF files
 * 
 * @author Mark Hoffmann
 * @since 05.09.2024
 */
public abstract class DWDEMFFetcher<T extends EObject> extends DWDFetcher {

	private static final Logger LOGGER = System.getLogger(DWDEMFFetcher.class.getName());

	/**
	 * Returns the file extension of the model to be loaded
	 * 
	 * @return the file extension of the model to be loaded
	 */
	protected abstract String getModelFileExtension();

	/**
	 * Return the resource set to be used for loading the data
	 * 
	 * @return the resource set, must nor be <code>null</code>
	 */
	protected abstract ResourceSet getResourceSet();

	/**
	 * Extracts the content type out of resource content
	 * 
	 * @param <T>     the typed value
	 * @param content the resource content
	 * @return the typed value
	 */
	protected abstract T get(EObject content);

	public T doLoad(InputStream inputStream) {
		long start = System.currentTimeMillis();
		requireNonNull(inputStream);
		String name = getName();
		String extension = getModelFileExtension();
		LOGGER.log(Level.INFO, "[{0}] Loading the {1} data out of the bytes", name, extension.toUpperCase());
		String uri = UUID.randomUUID().toString() + "." + extension;
		ResourceSet resourceSet = getResourceSet();
		requireNonNull(resourceSet);
		Resource resource = resourceSet.createResource(URI.createURI(uri));
		try {
			resource.load(inputStream, null);
			EObject root = resource.getContents().get(0);
			return get(root);
		} catch (IOException e) {
			throw new IllegalStateException(String.format("[%s] Error loading %s data", name, extension.toUpperCase()),
					e);
		} finally {
			LOGGER.log(Level.INFO, "{0} Loading the {1}  data ({2} ms)", name, extension.toUpperCase(),
					(System.currentTimeMillis() - start));
		}
	}

}
