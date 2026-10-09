/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
import { describe, expect, it } from 'vitest'
import type { PlantProfile } from '../src/contracts.js'
import { bearing, compass, layoutProblems, planeToGround, sideView, topView } from '../src/view.pv/preview.js'

/** The parents' carport as the drone photo shows it */
const carport: PlantProfile = {
  id: 'p',
  name: 'P',
  siteId: 'home',
  mountingHeight: 2.1,
  inverters: [],
  arrays: [
    { name: 'Reihe', azimuth: 225, tilt: 6.5, peakPower: 2.58, moduleCount: 6, modulesAcross: 1, modulesDown: 6, landscape: true, offsetAcross: -2.95, offsetDown: -0.53 },
    { name: 'Block', azimuth: 225, tilt: 6.5, peakPower: 4.3, moduleCount: 10, modulesAcross: 2, modulesDown: 5, landscape: true, offsetAcross: 0, offsetDown: 0 },
  ],
  obstacles: [{ name: 'Laubwald 220-230', azimuthFrom: 220, azimuthTo: 230, distance: 32, height: 16.5, leafOffTransmittance: 0.2, crownHeight: 0 }],
  horizon: [],
}

describe('editor preview', () => {
  it('maps the plane onto the ground: down the slope towards the azimuth, foreshortened by the tilt', () => {
    const down = planeToGround(0, 1, 180, 60)
    expect(down.x).toBeCloseTo(0)
    expect(down.y).toBeCloseTo(-0.5)
    // right of someone in front of a south face, looking up north, is east
    const right = planeToGround(1, 0, 180, 30)
    expect(right.x).toBeCloseTo(1)
    expect(right.y).toBeCloseTo(0)
  })

  it('shows the row north-west of the block and the slope towards the south-west', () => {
    const v = topView(carport)!
    expect(v.mixed).toBe(false)
    const [row, block] = v.arrays
    // the row lies to the left seen from in front (looking up north-east): north-west of the block, a little up the slope
    expect(bearing(block.centre, row.centre)).toBeGreaterThan(300)
    expect(bearing(block.centre, row.centre)).toBeLessThan(330)
    expect(bearing(v.slope.from, v.slope.to)).toBeCloseTo(225)
    expect(v.modules).toHaveLength(16)
    // a landscape module: its long side across the slope, i.e. along 135°/315°
    const m = v.modules[0].corners
    expect(Math.hypot(m[1].x - m[0].x, m[1].y - m[0].y)).toBeCloseTo(1.69)
    expect(bearing(m[0], m[1])).toBeCloseTo(135)
    expect(v.bounds.maxX - v.bounds.minX).toBeGreaterThan(5)
  })

  it('says when the arrays face differently', () => {
    const turned = { ...carport, arrays: [carport.arrays[0], { ...carport.arrays[1], azimuth: 135 }] }
    expect(topView(turned)!.mixed).toBe(true)
    expect(topView({ ...carport, arrays: [] })).toBeUndefined()
  })

  it('cuts along the slope: the edges around the mounting height, the forest ahead', () => {
    const s = sideView(carport)!
    expect(s.depth).toBeCloseTo(6 * 1.046 + 5 * 0.02)
    expect(s.high - s.low).toBeCloseTo(s.depth * Math.sin((6.5 * Math.PI) / 180))
    expect((s.high + s.low) / 2).toBeCloseTo(2.1)
    expect(s.horizon.by).toBe('Laubwald 220-230')
    expect(s.horizon.elevation).toBeCloseTo((Math.atan2(16.5 - 2.1, 32) * 180) / Math.PI)
  })

  it('says where the layout does not fit the count', () => {
    expect(layoutProblems(carport.arrays)).toEqual([])
    expect(layoutProblems([{ name: 'Block', peakPower: 4.3, moduleCount: 10, modulesAcross: 2, modulesDown: 6 }])).toEqual([
      'Block: Raster 2 × 6 = 12 Module, eingetragen sind 10.',
    ])
    expect(layoutProblems([{ name: 'Dach', peakPower: 4.3, modulesAcross: 3 }])).toEqual(['Dach: Module längs fehlt – schematisch gezeichnet.'])
    expect(layoutProblems([{ name: 'Dach', peakPower: 4.3, moduleCount: 8 }])).toEqual([])
  })

  it('names the compass points', () => {
    expect(compass(225)).toBe('SW')
    expect(compass(-10)).toBe('N')
    expect(compass(100)).toBe('O')
  })
})
