/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * Made-up weather for when no registry answers — the xdp-ui convention: a view without its data
 * bundle shows examples and says so. Deterministic: the same hour always gets the same values.
 */
import type { DayValue, HourValue, MeasuredPoint, ObservationValue, OutlookSnapshot, SiteInfo, WeatherOutlook } from '../contracts.js'

const SITE: SiteInfo = { id: 'beispiel', name: 'Beispielstandort', latitude: 51.05, longitude: 13.74, timeZone: 'Europe/Berlin' }
const HOUR = 3_600_000
/** The look-back the examples show, as the service's default */
const PAST_HOURS = 6

export class SampleWeatherOutlook implements WeatherOutlook {
  readonly examples = true

  constructor(private readonly now: () => Date = () => new Date()) {}

  origin(): string {
    return 'Beispieldaten'
  }

  async sites(): Promise<SiteInfo[]> {
    return [SITE]
  }

  async outlook(): Promise<OutlookSnapshot> {
    const start = Math.floor(this.now().getTime() / HOUR) * HOUR
    const hours: HourValue[] = []
    for (let h = 0; h < 8 * 24; h++) hours.push(hour(new Date(start + h * HOUR)))
    const days = [1, 2, 3, 4, 5, 6, 7].map((d) => day(new Date(start + d * 24 * HOUR), hours.slice(d * 24 - 6, d * 24 + 18)))
    const today = day(new Date(start), hours.slice(0, 18))
    const past: HourValue[] = []
    for (let h = PAST_HOURS; h > 0; h--) past.push(hour(new Date(start - h * HOUR)))
    return {
      ...SITE,
      siteId: SITE.id,
      siteName: SITE.name,
      generatedAt: this.now(),
      hours: [...past, ...hours.slice(0, 24)],
      measured: measured(this.now(), start - PAST_HOURS * HOUR),
      today,
      days,
      sources: [
        { quantities: 'Vorhersage, alle Werte', providerId: 'beispiel', productId: 'erfunden', location: '—' },
        { quantities: 'gemessen jetzt', providerId: 'ecowitt', productId: 'WS90', location: 'Station 75459', distanceMeters: 0 },
      ],
      observation: observation(this.now(), hours[0]),
    }
  }
}

/** The station over the look-back in ten-minute slots: the forecast's curve, a little warmer and a touch of noise */
function measured(now: Date, from: number): MeasuredPoint[] {
  const out: MeasuredPoint[] = []
  for (let t = from; t < now.getTime(); t += 600_000) {
    const h = hour(new Date(Math.floor(t / HOUR) * HOUR))
    const next = hour(new Date(Math.floor(t / HOUR) * HOUR + HOUR))
    const f = (t % HOUR) / HOUR
    const lerp = (a?: number, b?: number) => (a === undefined || b === undefined ? a : a + (b - a) * f)
    const wobble = Math.sin(t / 900_000) * 0.4
    out.push({
      time: new Date(t + 300_000),
      temperature: Math.round(((lerp(h.temperature, next.temperature) ?? 10) + 0.6 + wobble) * 10) / 10,
      humidity: 64,
      windSpeed: Math.round((lerp(h.windSpeed, next.windSpeed) ?? 3) * 0.7 * 10) / 10,
      windGust: Math.round((lerp(h.windGust, next.windGust) ?? 7) * 0.8 * 10) / 10,
      windDirection: 235,
      globalRadiation: Math.max(0, Math.round((lerp(h.globalRadiation, next.globalRadiation) ?? 0) * 0.9 + wobble * 40)),
    })
  }
  return out
}

/** The own station, a little off the forecast's hour — as a real one would be */
function observation(now: Date, hour: HourValue): ObservationValue {
  return {
    time: new Date(now.getTime() - 40_000),
    providerId: 'ecowitt',
    productId: 'WS90',
    stationId: '75459',
    temperature: Math.round(((hour.temperature ?? 10) + 0.6) * 10) / 10,
    humidity: 64,
    windSpeed: Math.round(((hour.windSpeed ?? 3) * 0.7) * 10) / 10,
    windGust: Math.round(((hour.windGust ?? 7) * 0.8) * 10) / 10,
    windDirection: 235,
    globalRadiation: hour.globalRadiation !== undefined ? Math.round(hour.globalRadiation * 0.9) : undefined,
    uvIndex: hour.daylight ? 1.0 : 0,
    rainTotal: 12.5,
    batteryLevel: 1,
    capacitorVoltage: 0.7,
    signalStrength: -9,
  }
}

function hour(time: Date): HourValue {
  const local = (time.getUTCHours() + 2) % 24
  const daylight = local >= 7 && local < 19
  const wave = Math.sin(((local - 9) / 24) * 2 * Math.PI)
  const rainy = local >= 15 && local <= 18
  return {
    time,
    temperature: Math.round((11 + 5 * wave) * 10) / 10,
    dewPoint: 7,
    cloudCover: rainy ? 95 : daylight ? 40 + 20 * Math.cos(local) : 70,
    precipitation: rainy ? Math.round((0.3 + 0.4 * Math.sin(local)) * 10) / 10 + 0.3 : 0,
    precipitationProbability: rainy ? 70 : 10,
    windSpeed: 3 + Math.abs(wave) * 3,
    windGust: 7 + Math.abs(wave) * 5,
    windDirection: 250,
    globalRadiation: daylight ? Math.max(0, Math.round(400 * Math.sin(((local - 7) / 12) * Math.PI))) : 0,
    directRadiation: daylight ? Math.max(0, Math.round((rainy ? 40 : 260) * Math.sin(((local - 7) / 12) * Math.PI))) : 0,
    diffuseRadiation: daylight ? Math.max(0, Math.round((rainy ? 110 : 140) * Math.sin(((local - 7) / 12) * Math.PI))) : 0,
    sunElevation: daylight ? 30 * Math.sin(((local - 7) / 12) * Math.PI) : -10,
    weatherCode: rainy ? 61 : daylight ? 2 : 3,
    daylight,
  }
}

function day(at: Date, hours: HourValue[]): DayValue {
  const temps = hours.map((h) => h.temperature ?? 0)
  return {
    date: at.toISOString().slice(0, 10),
    temperatureMin: Math.min(...temps),
    temperatureMax: Math.max(...temps),
    precipitation: hours.reduce((s, h) => s + (h.precipitation ?? 0), 0),
    precipitationProbability: 70,
    sunshineHours: 4.5,
    insolation: 2.4,
    cloudCoverMean: 62,
    windGustMax: 12,
    uvIndexMax: 2.4,
    weatherCode: 61,
    sunrise: new Date(Date.UTC(at.getUTCFullYear(), at.getUTCMonth(), at.getUTCDate(), 5, 17)),
    sunset: new Date(Date.UTC(at.getUTCFullYear(), at.getUTCMonth(), at.getUTCDate(), 16, 43)),
    solarNoon: new Date(Date.UTC(at.getUTCFullYear(), at.getUTCMonth(), at.getUTCDate(), 11, 0)),
    daylightHours: 11.4,
  }
}
