/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/** What the PV view derives from the values — formatting and the comparison. No Vue in here. */
import type { PvHourValue, PvReading } from '../contracts.js'

const nf = (digits: number) => new Intl.NumberFormat('de-DE', { minimumFractionDigits: digits, maximumFractionDigits: digits })
const NF0 = nf(0)
const NF1 = nf(1)
const NF2 = nf(2)

/** kW with two decimals below 10, one above — `0,87`, `12,4` */
export function kw(v: number | undefined): string {
  if (v === undefined) return '–'
  return Math.abs(v) < 10 ? NF2.format(v) : NF1.format(v)
}

/** kWh with one decimal */
export function kwh(v: number | undefined): string {
  return v === undefined ? '–' : NF1.format(v)
}

export function percent(v: number | undefined): string {
  return v === undefined ? '–' : `${NF0.format(v)} %`
}

/** Compass point for an orientation, south = 180 */
export function facing(azimuth: number | undefined): string {
  if (azimuth === undefined) return '–'
  const points = ['N', 'NNO', 'NO', 'ONO', 'O', 'OSO', 'SO', 'SSO', 'S', 'SSW', 'SW', 'WSW', 'W', 'WNW', 'NW', 'NNW']
  return points[Math.round((((azimuth % 360) + 360) % 360) / 22.5) % 16]
}

/**
 * Measured against forecast over the hours that are complete and have both: the ratio of the sums
 * (1 = as forecast), and how many hours it rests on. Hours without production on either side do not
 * count — night says nothing about the model.
 */
export function measuredRatio(hours: PvHourValue[], now: Date): { ratio: number; hours: number } | undefined {
  const done = hours.filter(
    (h) => h.measuredPower !== undefined && h.time.getTime() + 3_600_000 <= now.getTime() && ((h.dcPower ?? h.power ?? 0) > 0.05 || h.measuredPower > 0.05),
  )
  const forecast = done.reduce((a, h) => a + (h.dcPower ?? h.power ?? 0), 0)
  const measured = done.reduce((a, h) => a + (h.measuredPower ?? 0), 0)
  if (done.length === 0 || forecast <= 0) return undefined
  return { ratio: measured / forecast, hours: done.length }
}

/** The latest reading, if it is recent enough to call it "now" (15 minutes) */
export function latest(readings: PvReading[], now: Date): PvReading | undefined {
  const last = readings.reduce<PvReading | undefined>((a, r) => (!a || r.time > a.time ? r : a), undefined)
  return last && now.getTime() - last.time.getTime() <= 15 * 60_000 ? last : undefined
}
