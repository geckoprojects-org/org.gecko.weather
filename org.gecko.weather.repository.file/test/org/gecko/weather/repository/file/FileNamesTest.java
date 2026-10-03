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
package org.gecko.weather.repository.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
class FileNamesTest {

	@ParameterizedTest
	@ValueSource(strings = { "home", "MOSMIX_L", "ICON-D2", "Berlin/Mitte", "a b", "ü-Straße", "..", ".hidden", "x%y", "10488" })
	void idsRoundTrip(String id) {
		String file = FileNames.of(id) + FileNames.XMI;
		assertThat(file).matches("[A-Za-z0-9._%-]+\\.xmi").doesNotStartWith(".");
		assertThat(FileNames.idOf(file)).isEqualTo(id);
	}

	@Test
	void unsafeCharactersArePercentEncoded() {
		assertThat(FileNames.of("Berlin/Mitte")).isEqualTo("Berlin%2FMitte");
		assertThat(FileNames.of("..")).isEqualTo("%2E.");
		assertThatThrownBy(() -> FileNames.of("")).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void stampsRoundTripAndSortAsText() {
		Instant a = Instant.parse("2026-10-03T06:00:00Z");
		Instant b = Instant.parse("2026-10-03T12:00:00.750Z");
		assertThat(FileNames.stamp(a)).isEqualTo("20261003T060000Z");
		assertThat(FileNames.stampOf(FileNames.stamp(a) + FileNames.XMI)).contains(a);
		assertThat(FileNames.stampOf(FileNames.stamp(b) + "-1" + FileNames.XMI)).contains(b.truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		assertThat(FileNames.stampOf(FileNames.stamp(a) + FileNames.XMI_GZ)).contains(a);
		assertThat(FileNames.stampOf(FileNames.stamp(a) + FileNames.XMI_GZ + ".tmp")).isEmpty();
		assertThat(FileNames.idOf("Berlin%2FMitte" + FileNames.XMI_GZ)).isEqualTo("Berlin/Mitte");
		assertThat(FileNames.isData("x.xmi")).isTrue();
		assertThat(FileNames.isData("x.xmi.gz")).isTrue();
		assertThat(FileNames.isData("x.xmi.gz.tmp")).isFalse();
		assertThat(FileNames.stripExtension("x.xmi.gz")).isEqualTo("x");
		assertThat(FileNames.extension(true)).isEqualTo(".xmi.gz");
		assertThat(FileNames.stamp(a).compareTo(FileNames.stamp(b))).isNegative();
		assertThat(FileNames.stampOf("notes.txt")).isEmpty();
		assertThat(FileNames.stampOf("home.xmi")).isEmpty();
	}

}
