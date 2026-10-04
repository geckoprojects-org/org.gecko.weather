/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * A made-up plant for when no registry answers: 6.9 kWp facing south-west, a forest in the
 * south-west that shades the afternoon, readings every five minutes for today so far that run about
 * a fifth below the forecast. Deterministic for a given clock.
 */
import type { PlantInfo, PlantProfile, PvDayValue, PvForecast, PvHourValue, PvReading, PvReadings, PvSnapshot } from '../contracts.js'

const HOUR = 3_600_000
const ZONE = 'Europe/Berlin'
const PLANT: PlantInfo = { id: 'beispiel', name: 'Beispielanlage', siteId: 'beispiel', peakPower: 6.88, timeZone: ZONE }

/** Local midnight in Berlin for the day of `t` — good enough for examples (offset from the clock) */
function startOfDay(t: Date): number {
  const parts = new Intl.DateTimeFormat('en-CA', { timeZone: ZONE, year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', hourCycle: 'h23' }).formatToParts(t)
  const hour = Number(parts.find((p) => p.type === 'hour')?.value ?? 0)
  return Math.floor(t.getTime() / HOUR) * HOUR - hour * HOUR
}

/** Sun from 07 to 19 local, highest at 13 */
function sun(hourOfDay: number): { elevation: number; azimuth: number } {
  const e = hourOfDay > 7 && hourOfDay < 19 ? 36 * Math.sin((Math.PI * (hourOfDay - 7)) / 12) : -10
  return { elevation: e, azimuth: 90 + 15 * (hourOfDay - 7) }
}

/** Clouds that change from day to day */
function clearness(day: number, hourOfDay: number): number {
  return 0.55 + 0.35 * Math.sin(day * 1.7 + hourOfDay / 3)
}

function hourAt(t: number, start: number): PvHourValue {
  const day = Math.floor((t - start) / (24 * HOUR))
  const h = ((t - start) / HOUR) % 24
  const s = sun(h + 0.5)
  const shaded = s.elevation > 0 && s.azimuth > 200 && s.elevation < 32
  const global = s.elevation > 0 ? 800 * Math.sin((s.elevation * Math.PI) / 180) * clearness(day, h) : 0
  const plane = shaded ? global * 0.45 : global * 1.05
  const dc = Math.max(0, (6.88 * plane) / 1000) * 0.9
  return {
    time: new Date(t),
    power: Math.round(dc * 0.97 * 1000) / 1000,
    dcPower: Math.round(dc * 1000) / 1000,
    arrayPower: [dc * 0.375, dc * 0.625],
    planeIrradiance: Math.round(plane),
    globalRadiation: Math.round(global),
    cellTemperature: s.elevation > 0 ? 12 + plane / 30 : undefined,
    sunElevation: s.elevation,
    sunAzimuth: s.azimuth,
    shaded,
    clipped: false,
    source: day < 2 ? 'ICON-D2' : 'MOSMIX_L',
  }
}

export class SamplePvForecast implements PvForecast {
  readonly examples = true

  constructor(private readonly now: () => Date = () => new Date()) {}

  origin(): string {
    return 'Beispieldaten'
  }

  async plants(): Promise<PlantInfo[]> {
    return [PLANT]
  }

  async plant(): Promise<PlantProfile> {
    // a deciduous forest from south-east to west, 30 to 120 m away, 24 m high
    const edge = [110, 60, 42, 34, 32, 31, 31, 33, 36, 42, 55, 75, 100, 130]
    return {
      id: PLANT.id,
      name: PLANT.name,
      mountingHeight: 2.5,
      arrays: [
        { name: 'Reihe', azimuth: 225, tilt: 10, peakPower: 2.58, moduleCount: 6 },
        { name: 'Block', azimuth: 225, tilt: 10, peakPower: 4.3, moduleCount: 10 },
      ],
      obstacles: edge.map((d, k) => ({
        name: `Wald ${150 + 10 * k}`,
        azimuthFrom: 150 + 10 * k,
        azimuthTo: 160 + 10 * k,
        distance: d,
        height: 24,
        leafOffTransmittance: 0.3,
      })),
      horizon: [],
    }
  }

  async forecast(): Promise<PvSnapshot> {
    const now = this.now()
    const start = startOfDay(now)
    const end = Math.floor(now.getTime() / HOUR) * HOUR + 48 * HOUR
    const hours: PvHourValue[] = []
    for (let t = start; t < end; t += HOUR) hours.push(hourAt(t, start))
    const readings = this.readings(now, start)
    for (const h of hours) {
      const inHour = readings.filter((r) => r.time.getTime() >= h.time.getTime() && r.time.getTime() < h.time.getTime() + HOUR)
      if (inHour.length) h.measuredPower = inHour.reduce((a, r) => a + (r.pvPower ?? 0), 0) / inHour.length
    }
    const days: PvDayValue[] = []
    for (let d = 0; d < 3; d++) {
      const all: PvHourValue[] = []
      for (let t = start + d * 24 * HOUR; t < start + (d + 1) * 24 * HOUR; t += HOUR) all.push(hourAt(t, start))
      const energy = all.reduce((a, h) => a + (h.power ?? 0), 0)
      const best = all.reduce((a, h) => ((h.power ?? 0) > (a.power ?? 0) ? h : a))
      days.push({
        date: new Date(start + d * 24 * HOUR + 12 * HOUR).toISOString().slice(0, 10),
        energy,
        peakPower: best.power,
        peakTime: best.time,
        specificYield: energy / 6.88,
        hoursCovered: 24,
        measuredEnergy: d === 0 ? readings.reduce((a, r) => a + (r.pvPower ?? 0) / 12, 0) : undefined,
        source: d < 2 ? 'ICON-D2' : 'MOSMIX_L',
      })
    }
    return {
      plantId: PLANT.id,
      plantName: PLANT.name,
      siteId: PLANT.siteId,
      timeZone: ZONE,
      peakPower: PLANT.peakPower,
      generatedAt: now,
      arrays: [
        { name: 'Reihe', azimuth: 225, tilt: 10, peakPower: 2.58 },
        { name: 'Block', azimuth: 225, tilt: 10, peakPower: 4.3 },
      ],
      hours,
      days,
    }
  }

  async measurements(): Promise<PvReadings> {
    const now = this.now()
    const start = startOfDay(now)
    return { date: new Date(start + 12 * HOUR).toISOString().slice(0, 10), meterType: 'fronius-solar-api', readings: this.readings(now, start) }
  }

  private readings(now: Date, start: number): PvReading[] {
    const out: PvReading[] = []
    for (let t = start; t <= now.getTime(); t += 5 * 60_000) {
      const h = hourAt(Math.floor(t / HOUR) * HOUR, start)
      const wobble = 0.8 + 0.12 * Math.sin(t / 700_000)
      const pv = (h.dcPower ?? 0) * wobble
      const load = 0.35 + 0.2 * Math.sin(t / 2_000_000) ** 2
      const battery = pv > load ? -Math.min(pv - load, 2.5) : Math.min(load - pv, 1.5)
      out.push({
        time: new Date(t),
        pvPower: pv,
        acPower: pv * 0.97 + Math.max(0, battery),
        loadPower: load,
        gridPower: load - pv - battery,
        batteryPower: battery,
        stateOfCharge: Math.min(100, 30 + ((t - start) / HOUR) * 4),
      })
    }
    return out
  }
}
