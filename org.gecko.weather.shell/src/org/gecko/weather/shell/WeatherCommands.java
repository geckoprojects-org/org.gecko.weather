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
package org.gecko.weather.shell;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.annotations.GogoCommand;
import org.gecko.weather.api.IngestControl;
import org.gecko.weather.api.IngestControl.IngestStatus;
import org.gecko.weather.api.Reports;
import org.gecko.weather.api.SiteRegistration;
import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.model.weather.MeasuredValue;
import org.gecko.weather.model.weather.MeasurementKind;
import org.gecko.weather.model.weather.GridBinding;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceBinding;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.StationBinding;
import org.gecko.weather.model.weather.WeatherReport;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Gogo shell commands in the {@code weather} scope — the operator's and the developer's hands on
 * the service until there is an HTTP API. Output is plain text for a terminal; nothing here is API.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(service = WeatherCommands.class)
@GogoCommand(scope = "weather", function = { "register", "sites", "assign", "remove", "runNow", "status", "report", "values" })
public class WeatherCommands {

	@Reference
	private SiteRegistry sites;

	@Reference
	private WeatherService weather;

	@Reference
	private IngestControl ingest;

	@Descriptor("Register a site: weather:register <id> <name> <lat> <lon> [elevation]")
	public String register(@Descriptor("site id") String id, @Descriptor("name") String name,
			@Descriptor("latitude") double lat, @Descriptor("longitude") double lon) {
		return describe(sites.register(SiteRegistration.of(name, lat, lon).withId(id)));
	}

	@Descriptor("Register a site with elevation: weather:register <id> <name> <lat> <lon> <elevation>")
	public String register(String id, String name, double lat, double lon, double elevation) {
		return describe(sites.register(SiteRegistration.of(name, lat, lon).withId(id).withElevation(elevation)));
	}

	@Descriptor("List registered sites with their bindings")
	public String sites() {
		StringBuilder sb = new StringBuilder();
		for (Site site : sites.list()) {
			sb.append(describe(site)).append('\n');
		}
		return sb.length() == 0 ? "no sites registered" : sb.toString();
	}

	@Descriptor("Assign a station by hand: weather:assign <siteId> <providerId> <productId> <stationId>")
	public String assign(String siteId, String providerId, String productId, String stationId) {
		SourceBinding b = sites.assign(siteId, providerId, productId, stationId);
		return "bound " + describe(b);
	}

	@Descriptor("Remove a site and everything stored for it")
	public String remove(String siteId) {
		sites.remove(siteId);
		return "removed " + siteId;
	}

	@Descriptor("Run one product's ingest now: weather:runNow <providerId> <productId>")
	public String runNow(String providerId, String productId) {
		return describe(ingest.runNow(providerId, productId));
	}

	@Descriptor("Ingest status per product")
	public String status() {
		StringBuilder sb = new StringBuilder();
		for (IngestStatus s : ingest.status()) {
			sb.append(describe(s)).append('\n');
		}
		return sb.length() == 0 ? "no ingest jobs" : sb.toString();
	}

	@Descriptor("The datasets of a site's report")
	public String report(String siteId) {
		Optional<WeatherReport> report = weather.report(siteId);
		if (report.isEmpty()) {
			return "no report yet for " + siteId;
		}
		StringBuilder sb = new StringBuilder("report of ").append(siteId).append(" generated ")
				.append(report.get().getGeneratedAt()).append('\n');
		for (SourceDataset d : report.get().getDatasets()) {
			sb.append(String.format(Locale.ROOT, "  %s/%s issued %s  %d values  %s .. %s%s%n", d.getProviderId(), d.getProductId(),
					d.getIssuedAt(), d.getValues().size(), d.getHorizonStart(), d.getHorizonEnd(),
					d.getStationId() != null ? "  station " + d.getStationId() + " @ " + Math.round(d.getDistanceMeters()) + " m"
							: d.getCell() != null ? "  cell " + d.getCell().getGridId() + " " + d.getCell().getI() + "," + d.getCell().getJ() + " @ "
									+ Math.round(d.getDistanceMeters()) + " m"
									: ""));
		}
		sb.append("  days: ").append(report.get().getDays().size()).append(", horizon end ")
				.append(Reports.horizonEnd(report.get()).map(Instant::toString).orElse("-"));
		return sb.toString();
	}

	@Descriptor("Values of one kind for a site, every source: weather:values <siteId> <kind> [max]")
	public String values(String siteId, String kind) {
		return values(siteId, kind, 24);
	}

	public String values(String siteId, String kind, int max) {
		MeasurementKind k = MeasurementKind.get(kind.toUpperCase());
		if (k == null) {
			return "unknown kind " + kind + "; one of " + MeasurementKind.VALUES;
		}
		List<MeasuredValue> values = weather.values(siteId, k);
		if (values.isEmpty()) {
			return "no " + k + " values for " + siteId;
		}
		StringBuilder sb = new StringBuilder();
		values.stream().limit(max).forEach(v -> sb.append(String.format(Locale.ROOT, "  %s  %-14s %-10s %8s %-5s  %s/%s%s issued %s%n",
				v.getValidAt(), v.getLevel(), v.getStatistic(), v.isSetValue() ? String.format(Locale.ROOT, "%.1f", v.getValue()) : "#" + v.getCode(),
				v.getUnit(), v.getProvenance().getProviderId(), v.getProvenance().getProductId(),
				v.getProvenance().getStationId() == null ? "" : " (" + v.getProvenance().getStationId() + ")",
				v.getProvenance().getIssuedAt())));
		if (values.size() > max) {
			sb.append("  ... ").append(values.size() - max).append(" more");
		}
		return sb.toString();
	}

	// --- formatting ------------------------------------------------------------------------

	static String describe(Site site) {
		StringBuilder sb = new StringBuilder(String.format(Locale.ROOT, "%s \"%s\" %.4f,%.4f %s%s", site.getId(), site.getName(),
				site.getPosition().getLatitude(), site.getPosition().getLongitude(), site.getTimeZone(),
				site.isActive() ? "" : " (inactive)"));
		for (SourceBinding b : site.getBindings()) {
			sb.append("\n    ").append(describe(b));
		}
		return sb.toString();
	}

	static String describe(SourceBinding b) {
		String where = b instanceof StationBinding s ? s.getStation().getId() + " " + s.getStation().getName()
				: b instanceof GridBinding g && g.getCell() != null ? "cell " + g.getCell().getI() + "," + g.getCell().getJ()
				: "cell";
		return String.format(Locale.ROOT, "%s/%s #%d %s %s @ %.1f km", b.getProviderId(), b.getProductId(), b.getRank(), b.getOrigin(), where,
				b.getDistanceMeters() / 1000);
	}

	static String describe(IngestStatus s) {
		return String.format(Locale.ROOT, "%s/%s last run %s, last success %s, last change %s, next %s, failures %d%s%s",
				s.providerId(), s.productId(), s.lastRun().map(Instant::toString).orElse("-"),
				s.lastSuccess().map(Instant::toString).orElse("-"), s.lastChange().map(Instant::toString).orElse("-"),
				s.nextRun().map(n -> "in " + Duration.between(Instant.now(), n).truncatedTo(java.time.temporal.ChronoUnit.SECONDS)).orElse("-"),
				s.consecutiveFailures(), s.lastError().map(e -> ", error: " + e).orElse(""), s.running() ? ", running" : "");
	}

}
