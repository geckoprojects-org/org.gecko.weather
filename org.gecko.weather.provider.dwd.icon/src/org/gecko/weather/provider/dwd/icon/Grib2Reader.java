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
package org.gecko.weather.provider.dwd.icon;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.gecko.weather.api.spi.FetchException;
import org.gecko.weather.provider.dwd.icon.Grib2Field.GridDefinition;
import org.gecko.weather.provider.dwd.icon.Grib2Field.Interval;

import ucar.nc2.grib.grib2.Grib2Gds;
import ucar.nc2.grib.grib2.Grib2Pds;
import ucar.nc2.grib.grib2.Grib2Record;
import ucar.nc2.grib.grib2.Grib2RecordScanner;
import ucar.unidata.io.InMemoryRandomAccessFile;
import ucar.unidata.io.RandomAccessFile;

/**
 * Reads the records of a DWD single-level GRIB2 file through netCDF-Java's GRIB module, from
 * memory: a decompressed ICON-D2 file is 1.6–6.5 MB, so no temporary file is needed. The library
 * handles the packing (grid_simple today, anything else DWD may switch to); this class only turns
 * its sections into a {@link Grib2Field}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public final class Grib2Reader {

	private Grib2Reader() {
	}

	/** Every record of the message, with data; the caller closes the stream. */
	public static List<Grib2Field> readAll(InputStream grib2) throws IOException {
		return readAll(requireNonNull(grib2, "grib2").readAllBytes());
	}

	/**
	 * Every record of the message, with data. DWD cloud files hold one record per step; the
	 * radiation files hold four — the averages ending at :00, :15, :30 and :45 of the step.
	 *
	 * @throws FetchException if the bytes are not GRIB2 or a record is not on a plain latitude/longitude grid
	 * @throws IOException    if the library cannot decode a record
	 */
	public static List<Grib2Field> readAll(byte[] message) throws IOException {
		return scan(message, r -> true);
	}

	/**
	 * The one record of a single-record file.
	 *
	 * @throws FetchException if there is none or more than one
	 */
	public static Grib2Field read(byte[] message) throws IOException {
		List<Grib2Field> fields = readAll(message);
		if (fields.size() != 1) {
			throw new FetchException("expected one GRIB2 record per file, found " + fields.size());
		}
		return fields.get(0);
	}

	/**
	 * The record valid at the instant — for a processed field the end of its interval. Only that
	 * record's data is decoded.
	 *
	 * @throws FetchException if no record is valid at the instant
	 */
	public static Grib2Field read(byte[] message, Instant validAt) throws IOException {
		requireNonNull(validAt, "validAt");
		List<Grib2Field> records = scan(message, r -> validAt.equals(r.validAt()));
		return records.stream().filter(Grib2Field::hasData).findFirst().orElseThrow(() -> new FetchException(
				"no GRIB2 record valid at " + validAt + ", the file holds " + records.stream().map(Grib2Field::validAt).toList()));
	}

	/** Every record of the message; data is decoded for the records the predicate accepts, the others stay header-only. */
	private static List<Grib2Field> scan(byte[] message, Predicate<Grib2Field> withData) throws IOException {
		requireNonNull(message, "message");
		List<Grib2Field> result = new ArrayList<>();
		try (RandomAccessFile raf = new InMemoryRandomAccessFile("grib2", message)) {
			Grib2RecordScanner scanner = new Grib2RecordScanner(raf);
			if (!scanner.hasNext()) {
				throw new FetchException("not a GRIB2 message (" + message.length + " bytes)");
			}
			while (scanner.hasNext()) {
				Grib2Record record = scanner.next();
				Grib2Field header = header(record);
				if (!withData.test(header)) {
					result.add(header);
					continue;
				}
				float[] data = record.readData(raf);
				if (data.length != header.grid().ni() * header.grid().nj()) {
					throw new FetchException("decoded " + data.length + " points for a " + header.grid().ni() + "x"
							+ header.grid().nj() + " grid");
				}
				result.add(header.withData(data));
			}
			return result;
		} catch (IllegalStateException | UnsupportedOperationException e) {
			throw new FetchException("GRIB2 record not decodable: " + e.getMessage(), e);
		}
	}

	/** Sections 1, 3 and 4 of a record, without data. */
	private static Grib2Field header(Grib2Record record) {
		Grib2Gds gds = record.getGDS();
		if (!(gds instanceof Grib2Gds.LatLon latLon) || gds.template != 0) {
			throw new FetchException("grid definition template " + gds.template + " is not a plain latitude/longitude grid");
		}
		GridDefinition grid = new GridDefinition(gds.template, latLon.la1, normalizeLongitude(latLon.lo1), latLon.deltaLon,
				latLon.deltaLat, gds.getNx(), gds.getNy(), gds.getScanMode());
		Grib2Pds pds = record.getPDS();
		Instant reference = Instant.ofEpochMilli(record.getId().getReferenceDate().getMillis());
		Instant validAt = reference.plus(duration(pds.getTimeUnit(), pds.getForecastTime()));
		Optional<Interval> interval = Optional.empty();
		if (pds instanceof Grib2Pds.PdsInterval processed) {
			Instant end = Instant.ofEpochMilli(processed.getIntervalTimeEnd().getMillis());
			interval = Optional.of(new Interval(validAt, end, processed.getStatisticalProcessType()));
			validAt = end;
		}
		return new Grib2Field(record.getDiscipline(), pds.getParameterCategory(), pds.getParameterNumber(),
				pds.getTemplateNumber(), pds.getLevelType1(), pds.getLevelValue1(), pds.getLevelType2(), pds.getLevelValue2(),
				reference, validAt, interval, grid, Grib2Field.NO_DATA);
	}

	/** Code table 4.4: the units DWD uses plus the common ones. */
	static Duration duration(int unit, int value) {
		return switch (unit) {
			case 0 -> Duration.ofMinutes(value);
			case 1 -> Duration.ofHours(value);
			case 2 -> Duration.ofDays(value);
			case 10 -> Duration.ofHours(3L * value);
			case 11 -> Duration.ofHours(6L * value);
			case 12 -> Duration.ofHours(12L * value);
			case 13 -> Duration.ofSeconds(value);
			default -> throw new FetchException("unsupported time unit code " + unit);
		};
	}

	static double normalizeLongitude(double lon) {
		double l = lon % 360;
		if (l >= 180) {
			l -= 360;
		}
		if (l < -180) {
			l += 360;
		}
		return l;
	}
}
