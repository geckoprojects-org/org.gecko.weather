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
package org.gecko.weather.provider.ecowitt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.fennec.codec.constants.CodecOptions;
import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package;

/**
 * Reads one line of rtl_433's JSON output into a {@link Reading} with the Fennec JSON codec. The
 * resource set must carry the codec's resource factory for {@code json} — in OSGi the model's
 * prototype resource set ({@code emf.name=rtl433}) does.
 *
 * @author Mark Hoffmann
 * @since 22.10.2026
 */
public final class Rtl433Reader {

	private Rtl433Reader() {
	}

	/**
	 * @param line        one JSON object, as rtl_433 writes it
	 * @param resourceSet a resource set with the codec
	 * @return the decoded telegram; members the telegram lacks are null
	 * @throws IOException if the line is not a JSON object the codec can read
	 */
	public static Reading read(String line, ResourceSet resourceSet) throws IOException {
		synchronized (resourceSet) {
			Resource resource = resourceSet.createResource(URI.createURI("rtl433-reading.json"));
			if (resource == null) {
				throw new IOException("No resource factory for JSON in the resource set — is the Fennec codec running?");
			}
			try {
				resource.load(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)),
						Map.of(CodecOptions.CODEC_ROOT_TYPE, Rtl433Package.Literals.READING));
				if (!resource.getErrors().isEmpty()) {
					throw new IOException("Not an rtl_433 line: " + resource.getErrors().get(0).getMessage());
				}
				if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Reading r)) {
					throw new IOException("Not an rtl_433 line: no content");
				}
				resource.getContents().clear();
				return r;
			} finally {
				resourceSet.getResources().remove(resource);
			}
		}
	}
}
