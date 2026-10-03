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
package org.gecko.weather.provider.dwd.mosmix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.provider.dwd.mosmix.MosmixKmlDecoder.Header;
import org.gecko.weather.provider.dwd.mosmix.MosmixKmlDecoder.StationForecast;
import org.gecko.weather.transport.Unwrap;
import org.junit.jupiter.api.Test;

/**
 * The EMF-based decoder against the recorded MOSMIX_L file.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class MosmixKmlDecoderTest {

	private final ResourceSet rs = MosmixKmlDecoder.plainResourceSet();

	@Test
	void decodesHeaderStationAndValuesOfTheRecordedFile() throws IOException {
		List<StationForecast> forecasts = new ArrayList<>();
		Header header;
		try (InputStream kml = Unwrap.zip(Fixtures.open(Fixtures.KMZ_10554))) {
			header = MosmixKmlDecoder.decode(kml, Set.of(), forecasts::add, rs);
		}

		assertThat(header.issuedAt()).isEqualTo(Instant.parse("2024-09-26T09:00:00Z"));
		assertThat(header.modelRun()).contains(Instant.parse("2024-09-26T00:00:00Z"));
		assertThat(header.timeSteps()).hasSize(247);
		assertThat(header.timeSteps().get(0)).isEqualTo(Instant.parse("2024-09-26T10:00:00Z"));

		assertThat(forecasts).hasSize(1);
		StationForecast erfurt = forecasts.get(0);
		assertThat(erfurt.stationId()).isEqualTo("10554");
		assertThat(erfurt.description()).isEqualTo("ERFURT");
		// KML coordinates are longitude,latitude,altitude — the old code read them the other way round
		assertThat(erfurt.latitude()).isCloseTo(50.98, within(0.001));
		assertThat(erfurt.longitude()).isCloseTo(10.97, within(0.001));
		assertThat(erfurt.elevation()).isEqualTo(315.0);

		assertThat(erfurt.values()).hasSize(114).containsKeys("TTT", "Rad1h", "ww", "FXh25", "PPPP", "N");
		assertThat(erfurt.values().get("TTT")).hasSize(247);
		assertThat(erfurt.values().get("TTT")[0]).isCloseTo(289.25, within(1e-4));
		assertThat(erfurt.values().get("Rad1h")[0]).isCloseTo(630.0, within(1e-4));
		assertThat(erfurt.values().get("ww")[0]).isCloseTo(61.0, within(1e-4));
		// FXh25 is published for 12-hour windows only; the rest is "-"
		double[] gust = erfurt.values().get("FXh25");
		assertThat(gust[0]).isNaN();
		assertThat(gust[8]).isCloseTo(77.0, within(1e-4));
		assertThat(erfurt.rejectedElements()).isEmpty();

		assertThat(rs.getResources()).as("the decoder cleans up after itself").isEmpty();
	}

	@Test
	void unwantedStationsAreSkipped() throws IOException {
		List<StationForecast> forecasts = new ArrayList<>();
		try (InputStream kml = Unwrap.zip(Fixtures.open(Fixtures.KMZ_10554))) {
			Header header = MosmixKmlDecoder.decode(kml, Set.of("10488"), forecasts::add, rs);
			assertThat(header.timeSteps()).hasSize(247);
		}
		assertThat(forecasts).isEmpty();
	}

	@Test
	void valueListConversion() {
		assertThat(MosmixKmlDecoder.toDoubles(Arrays.asList(1.0f, "-", 3.5f), 3)).containsExactly(1.0, Double.NaN, 3.5);
		assertThat(MosmixKmlDecoder.toDoubles(Arrays.asList(1.0f, 2.0f), 3)).isNull();
		assertThat(MosmixKmlDecoder.toDoubles(Arrays.asList(1.0f, "x", 3.0f), 3)).isNull();
		assertThat(MosmixKmlDecoder.toDoubles(null, 1)).isNull();
	}

	@Test
	void garbageIsAFetchExceptionNotAnIOException() {
		byte[] notKml = "<html><body>maintenance</body></html>".getBytes(StandardCharsets.ISO_8859_1);
		assertThatThrownBy(() -> MosmixKmlDecoder.decode(new ByteArrayInputStream(notKml), Set.of(), f -> {
		}, rs)).isInstanceOf(FetchException.class);
		byte[] truncated = "<kml:kml xmlns:kml=\"http://www.opengis.net/kml/2.2\"><kml:Document>".getBytes(StandardCharsets.ISO_8859_1);
		assertThatThrownBy(() -> MosmixKmlDecoder.decode(new ByteArrayInputStream(truncated), Set.of(), f -> {
		}, rs)).isInstanceOf(FetchException.class);
		assertThat(rs.getResources()).isEmpty();
	}

}
