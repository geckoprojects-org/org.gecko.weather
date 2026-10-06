/**
 * The geometry of the editor's preview, plain and tested: the arrays seen from above with north
 * up — the same layout the 3D view builds — and a section along the slope with tilt, mounting
 * height and the horizon the modules face. Metres; x east, y north.
 */
import type { PlantProfile, ProfileArray } from '../contracts.js'
import { horizonAt, layout } from './pv3d.js'

const RAD = Math.PI / 180

export interface Pt {
  x: number
  y: number
}

/** Unit vector on the ground towards an azimuth (clockwise from north) */
function toward(azimuth: number): Pt {
  return { x: Math.sin(azimuth * RAD), y: Math.cos(azimuth * RAD) }
}

/**
 * A point of the module plane on the ground map: v runs down the slope, towards the azimuth the
 * modules face, and is foreshortened by the tilt; u runs across, to the right of someone in front
 * looking up — that is towards azimuth − 90°.
 */
export function planeToGround(u: number, v: number, azimuth: number, tilt: number): Pt {
  const down = toward(azimuth)
  const right = toward(azimuth - 90)
  const dv = v * Math.cos(tilt * RAD)
  return { x: dv * down.x + u * right.x, y: dv * down.y + u * right.y }
}

export interface TopView {
  modules: { array: number; corners: Pt[] }[]
  arrays: { array: number; name: string; centre: Pt }[]
  /** min/max of everything drawn */
  bounds: { minX: number; minY: number; maxX: number; maxY: number }
  /** centre of the field and the end of a slope arrow, 1 m long */
  slope: { from: Pt; to: Pt }
  azimuth: number
  tilt: number
  /** the arrays do not share azimuth and tilt — drawn by the first, as the 3D view does */
  mixed: boolean
}

/** Bearing from a to b, degrees clockwise from north */
export function bearing(a: Pt, b: Pt): number {
  return ((Math.atan2(b.x - a.x, b.y - a.y) / RAD) % 360 + 360) % 360
}

export function topView(profile: PlantProfile): TopView | undefined {
  const arrays = profile.arrays
  if (arrays.length === 0) return undefined
  const azimuth = arrays[0].azimuth ?? 180
  const tilt = arrays[0].tilt ?? 0
  const mixed = arrays.some((a) => (a.azimuth ?? 180) !== azimuth || (a.tilt ?? 0) !== tilt)
  const { modules, rects } = layout(arrays)
  const map = (u: number, v: number) => planeToGround(u, v, azimuth, tilt)
  const drawn = modules.map((m) => ({
    array: m.array,
    corners: [
      map(m.u - m.across / 2, m.v - m.down / 2),
      map(m.u + m.across / 2, m.v - m.down / 2),
      map(m.u + m.across / 2, m.v + m.down / 2),
      map(m.u - m.across / 2, m.v + m.down / 2),
    ],
  }))
  const points = drawn.flatMap((m) => m.corners)
  const bounds = {
    minX: Math.min(...points.map((p) => p.x)),
    minY: Math.min(...points.map((p) => p.y)),
    maxX: Math.max(...points.map((p) => p.x)),
    maxY: Math.max(...points.map((p) => p.y)),
  }
  const from = { x: 0, y: 0 }
  const to = { x: toward(azimuth).x, y: toward(azimuth).y }
  return {
    modules: drawn,
    arrays: rects.map((r) => ({ array: r.array, name: arrays[r.array].name, centre: map(r.u, r.v) })),
    bounds,
    slope: { from, to },
    azimuth,
    tilt,
    mixed,
  }
}

export interface SideView {
  /** length of the first array along the slope, m */
  depth: number
  tilt: number
  /** mounting height of the modules' centre, m */
  height: number
  /** heights of the lower and the upper edge, m */
  low: number
  high: number
  /** the horizon in the direction the modules face */
  horizon: { azimuth: number; elevation: number; by?: string }
}

/** A section along the slope of the first array, with the horizon straight ahead of it */
export function sideView(profile: PlantProfile): SideView | undefined {
  const first = profile.arrays[0]
  if (!first) return undefined
  const tilt = first.tilt ?? 0
  const azimuth = first.azimuth ?? 180
  const rect = layout([first]).rects[0]
  const depth = rect?.depth ?? 0
  const height = profile.mountingHeight || 0
  const rise = (depth / 2) * Math.sin(tilt * RAD)
  const h = horizonAt(profile, azimuth)
  return { depth, tilt, height, low: height - rise, high: height + rise, horizon: { azimuth, elevation: h.elevation, by: h.by } }
}

/**
 * What does not fit in the layout, as sentences for the preview: a grid with another number of
 * modules than the array has, a grid given only half.
 */
export function layoutProblems(arrays: ProfileArray[]): string[] {
  const out: string[] = []
  for (const a of arrays) {
    const across = a.modulesAcross
    const down = a.modulesDown
    if (across && down) {
      if (a.moduleCount !== undefined && across * down !== a.moduleCount) {
        out.push(`${a.name}: Raster ${across} × ${down} = ${across * down} Module, eingetragen sind ${a.moduleCount}.`)
      }
    } else if (across || down) {
      out.push(`${a.name}: Module ${across ? 'längs' : 'quer'} fehlt – schematisch gezeichnet.`)
    }
  }
  return out
}

const POINTS = ['N', 'NO', 'O', 'SO', 'S', 'SW', 'W', 'NW']

/** Compass name of an azimuth, eight points */
export function compass(azimuth: number): string {
  return POINTS[Math.round((((azimuth % 360) + 360) % 360) / 45) % 8]
}
