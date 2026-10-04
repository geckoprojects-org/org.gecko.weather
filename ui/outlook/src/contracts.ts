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

// --- PV ------------------------------------------------------------------------------------------

/** A PV plant with a profile */
export interface PlantInfo {
  id: string
  name: string
  siteId: string
  /** kWp, all arrays */
  peakPower?: number
  timeZone: string
}

/** One orientation of modules */
export interface ArrayInfo {
  name: string
  azimuth?: number
  tilt?: number
  peakPower?: number
}

/** One hour from `time`; powers are means over the hour in kW, so they are kWh as well. */
export interface PvHourValue {
  time: Date
  /** expected AC output */
  power?: number
  /** expected DC output of the generator — what a hybrid inverter reports as PV power */
  dcPower?: number
  /** DC per array, in the order of the plant's arrays */
  arrayPower: number[]
  /** W/m² on the plane of the largest array */
  planeIrradiance?: number
  /** W/m² on the horizontal, from the weather source */
  globalRadiation?: number
  cellTemperature?: number
  sunElevation?: number
  sunAzimuth?: number
  /** the sun stands behind the horizon or an obstacle */
  shaded: boolean
  /** the inverter caps the output */
  clipped: boolean
  /** measured mean PV power of the hour so far, when the plant has a meter */
  measuredPower?: number
  source?: string
}

export interface PvDayValue {
  /** ISO local date */
  date: string
  /** kWh expected */
  energy?: number
  peakPower?: number
  peakTime?: Date
  /** kWh per kWp */
  specificYield?: number
  hoursCovered: number
  /** kWh measured so far — today only */
  measuredEnergy?: number
  source?: string
}

export interface PvSnapshot {
  plantId: string
  plantName: string
  siteId: string
  timeZone: string
  peakPower?: number
  generatedAt?: Date
  arrays: ArrayInfo[]
  /** from the start of today up to 48 hours ahead */
  hours: PvHourValue[]
  days: PvDayValue[]
}

/** One meter reading, powers in kW */
export interface PvReading {
  time: Date
  pvPower?: number
  acPower?: number
  /** consumption, positive */
  loadPower?: number
  /** positive when drawing from the grid */
  gridPower?: number
  /** positive when the battery discharges */
  batteryPower?: number
  /** % */
  stateOfCharge?: number
}

export interface PvReadings {
  date: string
  meterType?: string
  readings: PvReading[]
}

export type Mounting = 'ROOF_MOUNTED' | 'ROOF_INTEGRATED' | 'OPEN_RACK'

/** One orientation of modules, as the profile has it */
export interface ProfileArray extends ArrayInfo {
  moduleCount?: number
  /** %/K of Pmax, e.g. −0.37 */
  temperatureCoefficient?: number
  mounting?: Mounting
  /** index into the plant's inverters, undefined for the default inverter */
  inverter?: number
}

export interface ProfileInverter {
  name: string
  /** kW AC ceiling; undefined or 0 for none */
  acPower?: number
  /** 0..1 */
  efficiency?: number
}

/** Something that hides the sun in a range of directions — a forest, a house */
export interface ProfileObstacle {
  name: string
  /** left edge seen from the plant, clockwise from north */
  azimuthFrom: number
  azimuthTo: number
  /** m from the modules */
  distance: number
  /** m above ground */
  height: number
  /** share of direct sun passing while leafless, 0 = opaque all year */
  leafOffTransmittance: number
}

/** A plant's profile as stored: what is installed where, what limits it, what shades it */
export interface PlantProfile {
  id: string
  name: string
  /** the weather site feeding the forecast */
  siteId: string
  /** own position for the sun; the site's when absent */
  latitude?: number
  longitude?: number
  /** m above ground */
  mountingHeight: number
  /** ground reflectance, 0.2 grass */
  albedo?: number
  /** % DC losses not modelled otherwise */
  systemLosses?: number
  arrays: ProfileArray[]
  inverters: ProfileInverter[]
  obstacles: ProfileObstacle[]
  /** a measured horizon line, if any: azimuth → elevation */
  horizon: { azimuth: number; elevation: number }[]
}

/** What the PV view asks */
export interface PvForecast {
  origin(): string
  readonly examples: boolean
  plants(): Promise<PlantInfo[]>
  /** the plant's geometry, for the 3D view */
  plant(plantId: string): Promise<PlantProfile>
  forecast(plantId: string): Promise<PvSnapshot>
  /** the readings of a local day, today when `date` is empty */
  measurements(plantId: string, date?: string): Promise<PvReadings>
  /** stores a profile under its id — a new id creates a plant; returns it as stored */
  savePlant(profile: PlantProfile): Promise<PlantProfile>
}
