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
package org.gecko.weather.outlook.internal;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Comparator;

import org.gecko.weather.api.SiteRegistry;
import org.gecko.weather.api.UnknownSiteException;
import org.gecko.weather.api.WeatherService;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.outlook.WeatherOutlook;
import org.gecko.weather.outlook.model.outlook.Outlook;
import org.gecko.weather.outlook.model.outlook.OutlookFactory;
import org.gecko.weather.outlook.model.outlook.SiteDirectory;
import org.gecko.weather.outlook.model.outlook.SiteEntry;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * {@link WeatherOutlook} over the weather service, exported as a remote service: the two
 * {@code service.exported.*} properties make the Remote Service Admin of Fennec Services derive the
 * contract from the interface, serve it over REST and announce it to the DDSR broker. Configuration
 * is required, so that a launch without the remote role does not export anything.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
@Designate(ocd = WeatherOutlookComponent.Config.class)
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE, property = {
		"service.exported.interfaces=*",
		"service.exported.configs=fennec.rest",
		"ddsr.provider.name=gecko-weather" })
public class WeatherOutlookComponent implements WeatherOutlook {

	@ObjectClassDefinition(name = "Gecko Weather outlook", description = "The weather page's view of a site as the remote service WeatherOutlook")
	public @interface Config {
		@AttributeDefinition(description = "Hours from the current full hour on.")
		int hours() default 24;

		@AttributeDefinition(description = "Days from tomorrow on.")
		int days() default 2;

		@AttributeDefinition(description = "Time zone for a site that names none.")
		String defaultTimeZone() default "Europe/Berlin";
	}

	@Reference
	private WeatherService weather;

	@Reference
	private SiteRegistry registry;

	/** Holds the export until the outlook model is registered with Fennec EMF. */
	@Reference
	private OutlookFactory factory;

	private OutlookBuilder builder;
	private ZoneId defaultZone;
	private final Clock clock = Clock.systemUTC();

	@Activate
	void activate(Config config) {
		builder = new OutlookBuilder(config.hours(), config.days());
		defaultZone = ZoneId.of(config.defaultTimeZone());
	}

	@Override
	public SiteDirectory sites() {
		SiteDirectory directory = factory.createSiteDirectory();
		registry.list().stream().filter(Site::isActive).sorted(Comparator.comparing(Site::getId)).forEach(site -> {
			SiteEntry e = factory.createSiteEntry();
			e.setId(site.getId());
			e.setName(site.getName());
			if (site.getPosition() != null) {
				e.setLatitude(site.getPosition().getLatitude());
				e.setLongitude(site.getPosition().getLongitude());
			}
			e.setTimeZone(zone(site).getId());
			directory.getSites().add(e);
		});
		return directory;
	}

	@Override
	public Outlook outlook(String siteId) {
		Site site = registry.get(siteId).orElseThrow(() -> new UnknownSiteException(siteId));
		return builder.build(site, weather.report(siteId), zone(site), clock.instant());
	}

	private ZoneId zone(Site site) {
		try {
			return site.getTimeZone() == null || site.getTimeZone().isBlank() ? defaultZone : ZoneId.of(site.getTimeZone());
		} catch (DateTimeException e) {
			return defaultZone;
		}
	}
}
