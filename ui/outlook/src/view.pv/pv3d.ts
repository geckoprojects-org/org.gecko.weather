/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The geometry behind the 3D view, without three.js so that it is testable: where the sun stands
 * between two forecast hours, under which angle an obstacle appears, whether a forest is leafless,
 * where its trees stand and how the modules lie. Coordinates: metres, origin under the modules,
 * x east, y up, z south — the three.js convention with north at −z.
 */
import type { PlantProfile, ProfileArray, PvHourValue } from '../contracts.js'

const RAD = Math.PI / 180

export interface Vec {
  x: number
  y: number
  z: number
}

/** Unit vector towards azimuth/elevation (azimuth clockwise from north) */
export function direction(azimuth: number, elevation: number): Vec {
  const a = azimuth * RAD
  const e = elevation * RAD
  return { x: Math.cos(e) * Math.sin(a), y: Math.sin(e), z: -Math.cos(e) * Math.cos(a) }
}

/** A point on the ground at azimuth and distance */
export function ground(azimuth: number, distance: number): { x: number; z: number } {
  const d = direction(azimuth, 0)
  return { x: d.x * distance, z: d.z * distance }
}

/**
 * The sun at instant `t`, interpolated between the hours' mid-hour positions (the forecast gives
 * the position at the middle of each hour). Undefined outside the hours.
 */
export function sunAt(hours: PvHourValue[], t: number): { azimuth: number; elevation: number } | undefined {
  const pts = hours
    .filter((h) => h.sunAzimuth !== undefined && h.sunElevation !== undefined)
    .map((h) => ({ t: h.time.getTime() + 1_800_000, az: h.sunAzimuth!, el: h.sunElevation! }))
  for (let i = 0; i + 1 < pts.length; i++) {
    const a = pts[i]
    const b = pts[i + 1]
    if (t < a.t || t > b.t || b.t - a.t > 3_600_000 * 1.5) continue
    const f = (t - a.t) / (b.t - a.t)
    // the shorter way round, so that an evening crossing north does not swing through south
    let dAz = b.az - a.az
    if (dAz > 180) dAz -= 360
    if (dAz < -180) dAz += 360
    return { azimuth: (((a.az + f * dAz) % 360) + 360) % 360, elevation: a.el + f * (b.el - a.el) }
  }
  return undefined
}

function within(azimuth: number, from: number, to: number): boolean {
  const n = (v: number) => ((v % 360) + 360) % 360
  const a = n(azimuth)
  const f = n(from)
  const t = n(to)
  return f <= t ? a >= f && a <= t : a >= f || a <= t
}

/** Under which angle the plant's horizon — obstacles and a measured line — lies at an azimuth, and what forms it */
export function horizonAt(profile: PlantProfile, azimuth: number): { elevation: number; by?: string; transmittance: number } {
  let best = { elevation: 0, by: undefined as string | undefined, transmittance: 0 }
  for (const o of profile.obstacles) {
    if (o.distance <= 0 || !within(azimuth, o.azimuthFrom, o.azimuthTo)) continue
    const e = Math.atan2(o.height - profile.mountingHeight, o.distance) / RAD
    if (e > best.elevation) best = { elevation: e, by: o.name, transmittance: o.leafOffTransmittance }
  }
  if (profile.horizon.length) {
    const near = profile.horizon.reduce((a, p) => (Math.abs(p.azimuth - azimuth) < Math.abs(a.azimuth - azimuth) ? p : a))
    if (near.elevation > best.elevation) best = { elevation: near.elevation, by: 'Horizont', transmittance: 0 }
  }
  return best
}

/** Leafless from 15 November to 30 April — the season the Java side uses */
export function leafOff(isoDate: string): boolean {
  const [, m, d] = isoDate.split('-').map(Number)
  const md = m * 100 + d
  return md >= 1115 || md <= 430
}

/** Share of the direct sun that reaches the modules, as the forecast computes it */
export function beamShare(profile: PlantProfile, azimuth: number, elevation: number, isoDate: string): number {
  if (elevation <= 0) return 0
  const h = horizonAt(profile, azimuth)
  if (elevation > h.elevation) return 1
  return leafOff(isoDate) ? h.transmittance : 0
}

/** Small deterministic random numbers — the same forest on every render */
export function random(seed: number): () => number {
  let a = seed >>> 0
  return () => {
    a = (a + 0x6d2b79f5) >>> 0
    let t = a
    t = Math.imul(t ^ (t >>> 15), t | 1)
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61)
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296
  }
}

export interface Tree {
  x: number
  z: number
  /** total height, m */
  height: number
  /** crown radius, m */
  crown: number
  /** the obstacle it belongs to */
  deciduous: boolean
}

/**
 * Trees for every obstacle: its sector from the given distance on, `depth` metres deep, about one
 * tree per `spacing`² — the front row exactly at the obstacle's distance, so that the forest edge
 * stands where the profile says. Heights vary by ±10 %.
 */
export function trees(profile: PlantProfile, depth = 30, spacing = 6, seed = 7): Tree[] {
  const rnd = random(seed)
  const out: Tree[] = []
  for (const o of profile.obstacles) {
    if (o.distance <= 0 || o.height <= 0) continue
    let span = o.azimuthTo - o.azimuthFrom
    if (span <= 0) span += 360
    for (let r = o.distance; r < o.distance + depth; r += spacing) {
      const arc = span * RAD * r
      const n = Math.max(1, Math.round(arc / spacing))
      for (let k = 0; k < n; k++) {
        const az = o.azimuthFrom + (span * (k + 0.2 + 0.6 * rnd())) / n
        const dist = r === o.distance ? r + 1 : r + (rnd() - 0.5) * spacing * 0.6
        const p = ground(az, dist)
        const height = o.height * (0.9 + 0.2 * rnd())
        out.push({ x: p.x, z: p.z, height, crown: Math.min(5, height * 0.18) * (0.85 + 0.3 * rnd()), deciduous: o.leafOffTransmittance > 0 })
      }
    }
  }
  return out
}

/** Maxeon 3 module, m — the common 104-cell size; the profile does not know the format */
export const MODULE = { width: 1.046, length: 1.69 }

export interface ModuleRect {
  /** index of the array */
  array: number
  /** centre in the array's own plane coordinates: u along the row, v down the slope */
  u: number
  v: number
}

/**
 * The modules in rows of two, portrait, arrays side by side along the row with half a metre
 * between them — a schematic: the profile has the count, not the layout.
 */
export function layout(arrays: ProfileArray[]): { modules: ModuleRect[]; width: number; depth: number } {
  const rows = 2
  const gap = 0.5
  const modules: ModuleRect[] = []
  let u0 = 0
  arrays.forEach((a, i) => {
    const count = a.moduleCount ?? Math.max(1, Math.round((a.peakPower ?? 0.43) / 0.43))
    const cols = Math.ceil(count / rows)
    for (let k = 0; k < count; k++) {
      const c = Math.floor(k / rows)
      const r = k % rows
      modules.push({ array: i, u: u0 + (c + 0.5) * (MODULE.width + 0.02), v: (r + 0.5) * (MODULE.length + 0.02) })
    }
    u0 += cols * (MODULE.width + 0.02) + gap
  })
  const width = Math.max(0, u0 - gap)
  const depth = rows * (MODULE.length + 0.02)
  // centre the field on the origin
  for (const m of modules) {
    m.u -= width / 2
    m.v -= depth / 2
  }
  return { modules, width, depth }
}
