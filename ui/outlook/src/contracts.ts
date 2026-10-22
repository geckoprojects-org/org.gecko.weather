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
 * the hour that starts there. Missing means no source had the value. Hours before `generatedAt`
 * are the look-back: what was forecast for them, with the station's measurements in `measured`.
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
  /** mean W/m² over the hour, on a horizontal surface */
  globalRadiation?: number
  /** the direct part of it — only where a source splits (ICON-D2, 48 h) */
  directRadiation?: number
  /** the diffuse part */
  diffuseRadiation?: number
  sunElevation?: number
  /** degrees clockwise from north */
  sunAzimuth?: number
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
  /** global radiation summed over the day, kWh/m² on a horizontal surface */
  insolation?: number
  cloudCoverMean?: number
  windGustMax?: number
  uvIndexMax?: number
  weatherCode?: number
  sunrise?: Date
  sunset?: Date
  /** when the sun is highest — where a daily maximum such as the UV index sits on a time axis */
  solarNoon?: Date
  daylightHours?: number
}

/**
 * What the site's own station measured last — beside the forecast, never merged into it. Absent
 * without a station. Units as in HourValue; rainTotal is the device's running counter in mm.
 */
export interface ObservationValue {
  /** when the newest of these values was measured */
  time: Date
  providerId: string
  productId: string
  stationId?: string
  temperature?: number
  /** relative humidity, % */
  humidity?: number
  windSpeed?: number
  windGust?: number
  windDirection?: number
  /** W/m² on a horizontal surface, now */
  globalRadiation?: number
  uvIndex?: number
  rainTotal?: number
  /** station health: 1 fine, 0 empty */
  batteryLevel?: number
  capacitorVoltage?: number
  signalStrength?: number
}

/** The own station in one ten-minute slot of the look-back: means, the gust its maximum */
export interface MeasuredPoint {
  /** the slot's middle */
  time: Date
  temperature?: number
  humidity?: number
  windSpeed?: number
  windGust?: number
  windDirection?: number
  globalRadiation?: number
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
  /** today, mainly for its sun events and UV maximum; temperatures only cover the hours still known */
  today?: DayValue
  days: DayValue[]
  sources: SourceInfo[]
  /** the own station's newest values, when one is bound to the site */
  observation?: ObservationValue
  /** the own station over the look-back — the hours before `generatedAt` that `hours` starts with */
  measured: MeasuredPoint[]
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
