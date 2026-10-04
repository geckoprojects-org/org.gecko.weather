/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * What the view derives from the values: an icon per hour or day, and the formatting. No Vue in
 * here, so that it is testable on its own.
 */

/** The icons the view draws */
export type Sky = 'clear' | 'partly' | 'cloudy' | 'fog' | 'drizzle' | 'rain' | 'sleet' | 'snow' | 'thunder'

/**
 * The icon for a WMO present-weather code (ww) and the cloud cover. Codes 0–3 only describe how
 * the sky changed, so there the cloud cover decides; everything from fog on is the weather itself.
 */
export function skyOf(ww: number | undefined, cloudCover: number | undefined): Sky {
  if (ww !== undefined) {
    if (ww >= 95) return 'thunder'
    if ((ww >= 70 && ww <= 79) || ww === 85 || ww === 86) return 'snow'
    if (ww === 68 || ww === 69 || ww === 83 || ww === 84 || ww === 66 || ww === 67) return 'sleet'
    if ((ww >= 60 && ww <= 65) || (ww >= 80 && ww <= 82) || (ww >= 87 && ww <= 94)) return 'rain'
    if (ww >= 50 && ww <= 59) return 'drizzle'
    if (ww === 45 || ww === 49) return 'fog'
  }
  if (cloudCover === undefined) return 'partly'
  if (cloudCover < 20) return 'clear'
  if (cloudCover < 70) return 'partly'
  return 'cloudy'
}

const SKY_TEXT: Record<Sky, string> = {
  clear: 'klar',
  partly: 'teils bewölkt',
  cloudy: 'bedeckt',
  fog: 'Nebel',
  drizzle: 'Sprühregen',
  rain: 'Regen',
  sleet: 'Schneeregen',
  snow: 'Schnee',
  thunder: 'Gewitter',
}

export function skyText(sky: Sky): string {
  return SKY_TEXT[sky]
}

const COMPASS = ['N', 'NO', 'O', 'SO', 'S', 'SW', 'W', 'NW']

/** The direction the wind comes from as a compass point */
export function compass(degrees: number | undefined): string {
  if (degrees === undefined) return ''
  return COMPASS[Math.round((((degrees % 360) + 360) % 360) / 45) % 8]
}

const nf = (digits: number) => new Intl.NumberFormat('de-DE', { minimumFractionDigits: digits, maximumFractionDigits: digits })
const NF0 = nf(0)
const NF1 = nf(1)

/** `12°`, or a dash for a missing value */
export function degrees(v: number | undefined): string {
  return v === undefined ? '–' : `${NF0.format(v)}°`
}

export function fixed1(v: number | undefined): string {
  return v === undefined ? '–' : NF1.format(v)
}

export function whole(v: number | undefined): string {
  return v === undefined ? '–' : NF0.format(v)
}

/** m/s → km/h, whole */
export function kmh(ms: number | undefined): string {
  return ms === undefined ? '–' : NF0.format(ms * 3.6)
}

export function hourLabel(d: Date, timeZone: string): string {
  return new Intl.DateTimeFormat('de-DE', { hour: '2-digit', minute: '2-digit', timeZone }).format(d)
}

export function clock(d: Date | undefined, timeZone: string): string {
  return d ? hourLabel(d, timeZone) : '–'
}

/** "Montag, 5. Oktober" for an ISO local date */
export function dayLabel(isoDate: string): { weekday: string; date: string } {
  const d = new Date(`${isoDate}T12:00:00Z`)
  return {
    weekday: new Intl.DateTimeFormat('de-DE', { weekday: 'long', timeZone: 'UTC' }).format(d),
    date: new Intl.DateTimeFormat('de-DE', { day: 'numeric', month: 'long', timeZone: 'UTC' }).format(d),
  }
}

/** Start of a new local day between two hours — for the date label above the strip */
export function isMidnight(d: Date, timeZone: string): boolean {
  return hourLabel(d, timeZone) === '00:00'
}

export function shortDay(d: Date, timeZone: string): string {
  return new Intl.DateTimeFormat('de-DE', { weekday: 'short', day: 'numeric', month: 'numeric', timeZone }).format(d)
}

/** Nice rounded bounds for a value range, at least `minSpan` wide */
export function niceRange(values: number[], step: number, minSpan: number): [number, number] {
  if (values.length === 0) return [0, minSpan]
  let lo = Math.floor(Math.min(...values) / step) * step
  let hi = Math.ceil(Math.max(...values) / step) * step
  if (hi - lo < minSpan) {
    const mid = (hi + lo) / 2
    lo = Math.floor((mid - minSpan / 2) / step) * step
    hi = lo + Math.ceil(minSpan / step) * step
  }
  return [lo, hi]
}
