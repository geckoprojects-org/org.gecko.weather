/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
import { describe, expect, it } from 'vitest'
import type { PlantProfile, PvHourValue } from '../src/contracts.js'
import { beamShare, direction, ground, horizonAt, layout, leafOff, solarPosition, sunAt, trees } from '../src/view.pv/pv3d.js'

const profile: PlantProfile = {
  id: 'p',
  name: 'P',
  siteId: 'home',
  mountingHeight: 2.1,
  inverters: [],
  arrays: [
    { name: 'A', azimuth: 225, tilt: 10, peakPower: 2.58, moduleCount: 6 },
    { name: 'B', azimuth: 225, tilt: 10, peakPower: 4.3, moduleCount: 10 },
  ],
  obstacles: [
    { name: 'Wald', azimuthFrom: 180, azimuthTo: 190, distance: 33, height: 25, leafOffTransmittance: 0.3 },
    { name: 'Haus', azimuthFrom: 350, azimuthTo: 10, distance: 10, height: 8, leafOffTransmittance: 0 },
  ],
  horizon: [],
}

const hour = (iso: string, az: number, el: number): PvHourValue => ({
  time: new Date(iso),
  sunAzimuth: az,
  sunElevation: el,
  arrayPower: [],
  shaded: false,
  clipped: false,
})

describe('pv3d geometry', () => {
  it('points the axes: north is −z, east is +x, up is +y', () => {
    expect(direction(0, 0).z).toBeCloseTo(-1)
    expect(direction(90, 0).x).toBeCloseTo(1)
    expect(direction(180, 0).z).toBeCloseTo(1)
    expect(direction(0, 90).y).toBeCloseTo(1)
    const g = ground(270, 10)
    expect(g.x).toBeCloseTo(-10)
    expect(g.z).toBeCloseTo(0)
  })

  it('interpolates the sun between mid-hours, the short way round the compass', () => {
    const hours = [hour('2026-10-04T10:00:00Z', 170, 30), hour('2026-10-04T11:00:00Z', 190, 32), hour('2026-10-04T12:00:00Z', 350, 10), hour('2026-10-04T13:00:00Z', 10, 5)]
    const s = sunAt(hours, Date.parse('2026-10-04T11:00:00Z'))
    expect(s?.azimuth).toBeCloseTo(180)
    expect(s?.elevation).toBeCloseTo(31)
    const n = sunAt(hours, Date.parse('2026-10-04T13:00:00Z'))
    expect(n?.azimuth).toBeCloseTo(0)
    expect(sunAt(hours, Date.parse('2026-10-04T09:00:00Z'))).toBeUndefined()
  })

  it('finds the horizon and lets the leafless forest pass part of the sun', () => {
    const h = horizonAt(profile, 185)
    expect(h.by).toBe('Wald')
    expect(h.elevation).toBeCloseTo((Math.atan2(25 - 2.1, 33) * 180) / Math.PI)
    expect(horizonAt(profile, 0).by).toBe('Haus')
    expect(horizonAt(profile, 90).elevation).toBe(0)
    expect(beamShare(profile, 185, 20, '2026-10-04')).toBe(0)
    expect(beamShare(profile, 185, 20, '2026-12-21')).toBeCloseTo(0.3)
    expect(beamShare(profile, 185, 40, '2026-10-04')).toBe(1)
    expect(beamShare(profile, 5, 20, '2026-12-21')).toBe(0)
    expect(beamShare(profile, 90, -3, '2026-10-04')).toBe(0)
    expect(leafOff('2026-11-15')).toBe(true)
    expect(leafOff('2026-11-14')).toBe(false)
    expect(leafOff('2026-04-30')).toBe(true)
    expect(leafOff('2026-05-01')).toBe(false)
  })

  it('puts the first row of trees at the obstacle distance and the rest behind', () => {
    const list = trees(profile, 20, 6, 1)
    const forest = list.filter((t) => t.deciduous)
    expect(forest.length).toBeGreaterThan(3)
    const dist = (t: { x: number; z: number }) => Math.hypot(t.x, t.z)
    expect(Math.min(...forest.map(dist))).toBeGreaterThanOrEqual(33)
    expect(Math.max(...forest.map(dist))).toBeLessThan(33 + 20 + 3)
    for (const t of forest) {
      const az = (((Math.atan2(t.x, -t.z) * 180) / Math.PI) % 360 + 360) % 360
      expect(az).toBeGreaterThanOrEqual(180)
      expect(az).toBeLessThanOrEqual(190)
      expect(t.height).toBeGreaterThan(22)
      expect(t.height).toBeLessThan(28)
    }
    expect(list.filter((t) => !t.deciduous).length).toBeGreaterThan(0)
    expect(trees(profile, 20, 6, 1)).toEqual(list)
  })

  it('lays the modules out in two rows per array, centred', () => {
    const { modules, width, depth } = layout(profile.arrays)
    expect(modules).toHaveLength(16)
    expect(modules.filter((m) => m.array === 0)).toHaveLength(6)
    expect(depth).toBeCloseTo(2 * 1.71)
    expect(width).toBeCloseTo(8 * 1.066 + 0.5)
    const us = modules.map((m) => m.u)
    expect(Math.min(...us)).toBeCloseTo(-Math.max(...us), 5)
    expect(layout([{ name: 'x', peakPower: 0.86 }]).modules).toHaveLength(2)
  })
})

describe('solar position', () => {
  it('puts the midsummer sun at 62° over Dresden at solar noon, due south', () => {
    // solar noon at 13.74° E on 21 June: about 13:07 CEST = 11:07 UTC
    const s = solarPosition(51.05, 13.74, Date.parse('2026-06-21T11:07:00Z'))
    expect(s.elevation).toBeCloseTo(90 - 51.05 + 23.44, 0)
    expect(Math.abs(s.azimuth - 180)).toBeLessThan(1.5)
  })
  it('has the sun below the horizon at midnight and rising in the east', () => {
    expect(solarPosition(51.05, 13.74, Date.parse('2026-10-04T23:00:00Z')).elevation).toBeLessThan(-30)
    const morning = solarPosition(51.05, 13.74, Date.parse('2026-10-04T06:00:00Z'))
    expect(morning.elevation).toBeGreaterThan(0)
    expect(morning.elevation).toBeLessThan(15)
    expect(morning.azimuth).toBeGreaterThan(95)
    expect(morning.azimuth).toBeLessThan(125)
    const evening = solarPosition(51.05, 13.74, Date.parse('2026-10-04T15:30:00Z'))
    expect(evening.azimuth).toBeGreaterThan(235)
    expect(evening.azimuth).toBeLessThan(265)
  })
  it('crosses the horizon within a few minutes of the published sunrise and sunset', () => {
    // DWD/our solar service for a site at about 51 °N, 12.3 °E on 4 Oct 2026: 07:15 / 18:42 CEST
    const rise = solarPosition(50.9, 12.27, Date.parse('2026-10-04T05:15:00Z')).elevation
    const set = solarPosition(50.9, 12.27, Date.parse('2026-10-04T16:42:00Z')).elevation
    // refraction lifts the visible sun by ~0.8° at the horizon; geometric elevation is then about −0.8°
    expect(rise).toBeGreaterThan(-1.5)
    expect(rise).toBeLessThan(0.2)
    expect(set).toBeGreaterThan(-1.5)
    expect(set).toBeLessThan(0.2)
  })
})
