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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.fennec.codec.resource.CodecResourceFactory;
import org.eclipse.fennec.codec.util.MetadataServiceFactory;
import org.eclipse.fennec.emf.osgi.metadata.MetadataWhiteboard;
import org.gecko.weather.provider.ecowitt.model.rtl433.Reading;
import org.gecko.weather.provider.ecowitt.model.rtl433.Rtl433Package;

/**
 * Shared test plumbing: the codec outside OSGi, the recorded stream, a configuration.
 */
final class TestSupport {

	private TestSupport() {
	}

	static ResourceSet resourceSet() {
		MetadataWhiteboard metadata = MetadataServiceFactory.create();
		metadata.registerPackage(Rtl433Package.eINSTANCE);
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("json", new CodecResourceFactory(metadata));
		return rs;
	}

	/** The lines of a recorded rtl_433 stream under test/fixtures. */
	static List<String> lines(String fixture) throws IOException {
		try (InputStream in = TestSupport.class.getClassLoader().getResourceAsStream("fixtures/" + fixture)) {
			assertThat(in).as(fixture).isNotNull();
			return new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().filter(l -> !l.isBlank()).toList();
		}
	}

	static Reading reading(String line) throws IOException {
		return Rtl433Reader.read(line, resourceSet());
	}

	static EcowittConfig config(StationModel station, String deviceId, String siteId) {
		return config(station, deviceId, siteId, "PT1M");
	}

	static EcowittConfig config(StationModel station, String deviceId, String siteId, String interval) {
		return new EcowittConfig() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return EcowittConfig.class;
			}

			@Override
			public StationModel station() {
				return station;
			}

			@Override
			public String deviceId() {
				return deviceId;
			}

			@Override
			public Transport transport() {
				return Transport.SDR;
			}

			@Override
			public String url() {
				return "http://127.0.0.1:1/stream";
			}

			@Override
			public String siteId() {
				return siteId;
			}

			@Override
			public String interval() {
				return interval;
			}

			@Override
			public String attribution() {
				return "Test station";
			}

			@Override
			public String licence() {
				return "private";
			}
		};
	}
}
