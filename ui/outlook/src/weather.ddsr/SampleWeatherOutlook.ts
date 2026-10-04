/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * Made-up weather for when no registry answers — the xdp-ui convention: a view without its data
 * bundle shows examples and says so. Deterministic: the same hour always gets the same values.
 */
import type { DayValue, HourValue, OutlookSnapshot, SiteInfo, WeatherOutlook } from '../contracts.js'

const SITE: SiteInfo = { id: 'beispiel', name: 'Beispielstandort', latitude: 51.05, longitude: 13.74, timeZone: 'Europe/Berlin' }
const HOUR = 3_600_000

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
    for (let h = 0; h < 72; h++) hours.push(hour(new Date(start + h * HOUR)))
    const days = [1, 2].map((d) => day(new Date(start + d * 24 * HOUR), hours.slice(d * 24 - 6, d * 24 + 18)))
    const today = day(new Date(start), hours.slice(0, 18))
    return {
      ...SITE,
      siteId: SITE.id,
      siteName: SITE.name,
      generatedAt: this.now(),
      hours: hours.slice(0, 24),
      today,
      days,
      sources: [
        { quantities: 'alle Werte', providerId: 'beispiel', productId: 'erfunden', location: '—' },
      ],
    }
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
