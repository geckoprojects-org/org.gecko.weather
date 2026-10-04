/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The contract between the weather view and whatever answers it — in xdp-ui this goes into
 * @xdp/contracts as a service id (XDP_WEATHER_OUTLOOK) beside XDP_DATASOURCE_INVENTORY. Plain
 * values: the view knows no EMF and no registry.
 */

/** A site an outlook can be asked for */
export interface SiteInfo {
  id: string
  name: string
  latitude?: number
  longitude?: number
  timeZone: string
}

/**
 * One hour from `time`. Instant quantities are the values at `time`, period quantities those of
 * the hour that starts there. Missing means no source had the value.
 */
export interface HourValue {
  time: Date
  /** °C at 2 m */
  temperature?: number
  dewPoint?: number
  /** total cloud cover, % */
  cloudCover?: number
  /** mm in the hour */
  precipitation?: number
  /** % probability of more than 0.1 mm in the hour */
  precipitationProbability?: number
  /** m/s at 10 m */
  windSpeed?: number
  windGust?: number
  /** degrees the wind comes from, 0 = north */
  windDirection?: number
  /** mean W/m² over the hour */
  globalRadiation?: number
  sunElevation?: number
  /** WMO present-weather code ww */
  weatherCode?: number
  daylight: boolean
}

/** One calendar day in the site's time zone */
export interface DayValue {
  /** ISO local date */
  date: string
  temperatureMin?: number
  temperatureMax?: number
  precipitation?: number
  precipitationProbability?: number
  sunshineHours?: number
  cloudCoverMean?: number
  windGustMax?: number
  uvIndexMax?: number
  weatherCode?: number
  sunrise?: Date
  sunset?: Date
  daylightHours?: number
}

/** Where a group of quantities came from */
export interface SourceInfo {
  quantities: string
  providerId: string
  productId: string
  location: string
  distanceMeters?: number
  issuedAt?: Date
}

export interface OutlookSnapshot {
  siteId: string
  siteName: string
  latitude?: number
  longitude?: number
  timeZone: string
  generatedAt?: Date
  hours: HourValue[]
  days: DayValue[]
  sources: SourceInfo[]
}

/** What the weather view asks; one implementation reads the registry, one makes up examples. */
export interface WeatherOutlook {
  /** Where the answers come from, for the line under the view */
  origin(): string
  /** True for made-up data — the view says so */
  readonly examples: boolean
  sites(): Promise<SiteInfo[]>
  outlook(siteId: string): Promise<OutlookSnapshot>
}
