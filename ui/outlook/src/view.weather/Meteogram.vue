<script setup lang="ts">
/**
 * Temperature, precipitation and the sun in one picture — a meteogram, not a chart with two y-axes.
 * Two bands share the hour columns and the frame: temperature above as a line, precipitation below
 * as bars, each with its own scale and labelled directly (the band, the extremes, every wet hour),
 * so no number is read off the wrong axis. Night hours are shaded, sunrise and sunset marked.
 *
 * The UV index has no hours: DWD publishes one maximum per day. It sits as a mark at the day's
 * solar noon, where that maximum is reached, when solar noon falls into the window.
 */
import { computed } from 'vue'
import type { DayValue, HourValue } from '../contracts.js'
import { clock, degrees, fixed1, niceRange } from './weather.js'

const props = defineProps<{
  hours: HourValue[]
  /** today and the following days — their sun events and UV maxima are placed where they fall */
  days: DayValue[]
  timeZone: string
  col: number
  active: number | null
}>()

const HOUR = 3_600_000
/** Room above the bands for the sun and UV marks */
const TOP = 26
const TEMP_H = 118
const GAP = 22
const RAIN_H = 58
const H = TOP + TEMP_H + GAP + RAIN_H + 6

const width = computed(() => props.hours.length * props.col)
const start = computed(() => props.hours[0]?.time.getTime() ?? 0)

function xOf(t: Date): number {
  return ((t.getTime() - start.value) / HOUR) * props.col
}

function xCenter(i: number): number {
  return i * props.col + props.col / 2
}

function inWindow(t: Date | undefined): t is Date {
  if (!t) return false
  const x = xOf(t)
  return x >= 0 && x <= width.value
}

// --- temperature band -----------------------------------------------------------------------

const tempRange = computed(() =>
  niceRange(props.hours.map((h) => h.temperature).filter((t): t is number => t !== undefined), 2, 6),
)

function yTemp(t: number): number {
  const [lo, hi] = tempRange.value
  return TOP + 10 + (TEMP_H - 20) * (1 - (t - lo) / (hi - lo))
}

const tempPath = computed(() => {
  let d = ''
  let pen = false
  props.hours.forEach((h, i) => {
    if (h.temperature === undefined) {
      pen = false
      return
    }
    d += `${pen ? 'L' : 'M'}${xCenter(i)},${yTemp(h.temperature).toFixed(1)}`
    pen = true
  })
  return d
})

const tempGrid = computed(() => {
  const [lo, hi] = tempRange.value
  return [lo, (lo + hi) / 2, hi]
})

/** Selective labels: the warmest and the coldest hour */
const extremes = computed(() => {
  const known = props.hours.map((h, i) => ({ i, t: h.temperature })).filter((e): e is { i: number; t: number } => e.t !== undefined)
  if (known.length === 0) return []
  const max = known.reduce((a, b) => (b.t > a.t ? b : a))
  const min = known.reduce((a, b) => (b.t < a.t ? b : a))
  return max.i === min.i ? [{ ...max, above: true }] : [{ ...max, above: true }, { ...min, above: false }]
})

// --- precipitation band ---------------------------------------------------------------------

const RAIN_TOP = TOP + TEMP_H + GAP
const RAIN_BASE = RAIN_TOP + RAIN_H

/** at least 2 mm full scale, so a drizzle does not look like a cloudburst */
const rainMax = computed(() => Math.max(2, ...props.hours.map((h) => h.precipitation ?? 0)))
const dry = computed(() => props.hours.every((h) => !h.precipitation))

function rainHeight(mm: number): number {
  return Math.max(3, (RAIN_H - 14) * (mm / rainMax.value))
}

/** 4px rounded top, anchored at the baseline; the gap keeps neighbours apart */
function rainBar(i: number, mm: number | undefined): string {
  if (!mm || mm <= 0) return ''
  const w = props.col - 16
  const x = i * props.col + 8
  const h = rainHeight(mm)
  const r = Math.min(4, h, w / 2)
  const top = RAIN_BASE - h
  return `M${x},${RAIN_BASE}V${top + r}Q${x},${top} ${x + r},${top}H${x + w - r}Q${x + w},${top} ${x + w},${top + r}V${RAIN_BASE}Z`
}

// --- night, sun and UV ----------------------------------------------------------------------

/** Runs of night hours as rectangles */
const nights = computed(() => {
  const runs: { x: number; w: number }[] = []
  props.hours.forEach((h, i) => {
    if (h.daylight) return
    const last = runs[runs.length - 1]
    if (last && Math.abs(last.x + last.w - i * props.col) < 0.5) last.w += props.col
    else runs.push({ x: i * props.col, w: props.col })
  })
  return runs
})

const sunEvents = computed(() =>
  props.days.flatMap((d) => [
    ...(inWindow(d.sunrise) ? [{ x: xOf(d.sunrise), label: `Aufgang ${clock(d.sunrise, props.timeZone)}`, rise: true }] : []),
    ...(inWindow(d.sunset) ? [{ x: xOf(d.sunset), label: `Untergang ${clock(d.sunset, props.timeZone)}`, rise: false }] : []),
  ]),
)

const uvMarks = computed(() =>
  props.days
    .filter((d) => d.uvIndexMax !== undefined && inWindow(d.solarNoon))
    .map((d) => ({ x: xOf(d.solarNoon!), label: `UV ${fixed1(d.uvIndexMax)}` })),
)

/** A label near the right edge grows to the left */
function anchor(x: number): 'start' | 'end' {
  return x > width.value - 110 ? 'end' : 'start'
}
</script>

<template>
  <svg
    :width="width"
    :height="H"
    :viewBox="`0 0 ${width} ${H}`"
    role="img"
    aria-label="Temperatur, Niederschlag, Nacht und Sonnenzeiten der nächsten 24 Stunden"
  >
    <rect v-for="(n, k) in nights" :key="'n' + k" class="night" :x="n.x" :y="TOP - 4" :width="n.w" :height="H - TOP + 4" />

    <g class="grid">
      <template v-for="g in tempGrid" :key="'g' + g">
        <line :x1="0" :x2="width" :y1="yTemp(g)" :y2="yTemp(g)" />
        <text class="axis" x="4" :y="yTemp(g) - 4">{{ g }}°</text>
      </template>
      <line class="separator" :x1="0" :x2="width" :y1="RAIN_TOP - GAP / 2" :y2="RAIN_TOP - GAP / 2" />
      <text class="axis" x="4" :y="RAIN_TOP + 2">mm</text>
      <line class="baseline" :x1="0" :x2="width" :y1="RAIN_BASE + 0.5" :y2="RAIN_BASE + 0.5" />
      <text v-if="dry" class="axis" x="30" :y="RAIN_BASE - 6">kein Niederschlag erwartet</text>
    </g>

    <g v-for="(e, k) in sunEvents" :key="'s' + k" class="sun">
      <line :x1="e.x" :x2="e.x" :y1="TOP - 6" :y2="RAIN_BASE" />
      <text :x="e.x + (anchor(e.x) === 'end' ? -5 : 5)" :y="TOP - 10" :text-anchor="anchor(e.x)">{{ e.rise ? '↑' : '↓' }} {{ e.label }}</text>
    </g>

    <g v-for="(u, k) in uvMarks" :key="'u' + k" class="uv">
      <circle :cx="u.x" :cy="TOP - 14" r="3.5" />
      <text :x="u.x + 8" :y="TOP - 10">{{ u.label }}</text>
    </g>

    <path class="line temp" :d="tempPath" />
    <g v-for="e in extremes" :key="'x' + e.i" class="extreme">
      <circle :cx="xCenter(e.i)" :cy="yTemp(e.t)" r="4" />
      <text :x="xCenter(e.i)" :y="yTemp(e.t) + (e.above ? -10 : 18)" text-anchor="middle">{{ degrees(e.t) }}</text>
    </g>
    <circle
      v-if="active !== null && hours[active]?.temperature !== undefined"
      class="marker temp"
      :cx="xCenter(active)"
      :cy="yTemp(hours[active].temperature!)"
      r="4.5"
    />

    <template v-for="(h, i) in hours" :key="'r' + i">
      <path class="bar rain" :class="{ lit: active === i }" :d="rainBar(i, h.precipitation)" />
      <text
        v-if="h.precipitation && h.precipitation > 0"
        class="bar-label"
        :x="xCenter(i)"
        :y="RAIN_BASE - rainHeight(h.precipitation) - 4"
        text-anchor="middle"
      >{{ fixed1(h.precipitation) }}</text>
    </template>
  </svg>
</template>

<style scoped>
svg { display: block; overflow: visible; }
.night { fill: color-mix(in srgb, var(--ink) 4%, transparent); }
.grid line { stroke: var(--line); stroke-width: 1; }
.grid .separator { stroke: var(--line-2); stroke-dasharray: 2 3; }
.grid .baseline { stroke: var(--line-2); }
.axis { font-size: 10.5px; fill: var(--muted); }
.line { fill: none; stroke-width: 2; stroke-linejoin: round; stroke-linecap: round; }
.line.temp { stroke: var(--s2); }
.extreme circle { fill: var(--s2); stroke: var(--surface); stroke-width: 2; }
.extreme text { font-size: 11.5px; font-weight: 600; fill: var(--ink); }
.marker.temp { fill: var(--s2); stroke: var(--surface); stroke-width: 2; }
.bar.rain { fill: var(--s1); }
.bar.rain.lit { fill: color-mix(in srgb, var(--s1) 75%, var(--ink)); }
.bar-label { font-size: 10.5px; fill: var(--ink-2); font-variant-numeric: tabular-nums; }
.sun line { stroke: var(--warn); stroke-width: 1; stroke-dasharray: 3 3; opacity: .7; }
.sun text { font-size: 11px; fill: var(--ink-2); }
.uv circle { fill: none; stroke: var(--ink-2); stroke-width: 1.4; }
.uv text { font-size: 11px; font-weight: 600; fill: var(--ink); }
</style>
