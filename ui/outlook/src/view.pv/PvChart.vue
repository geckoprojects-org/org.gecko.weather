<script setup lang="ts">
/**
 * Expected and measured PV power on one axis (kW) — the same quantity twice, so one scale is honest.
 * Bars: expected DC power of the generator per hour (what a hybrid inverter reports as PV power);
 * hours in which the sun stands behind the horizon or the forest are hatched. Line: the meter
 * readings of today, as they came; dots: their mean per hour, the value to compare with the bar.
 * Night is shaded, "now" is a vertical line. Hover or focus shows the hour's values.
 */
import { computed, ref } from 'vue'
import type { PvHourValue, PvReading } from '../contracts.js'
import { hourLabel, isMidnight, shortDay, whole } from '../view.weather/weather.js'
import { kw } from './pv.js'

const props = defineProps<{ hours: PvHourValue[]; readings: PvReading[]; timeZone: string; now: Date }>()

const COL = 30
const HOUR = 3_600_000
/** Room above the plot for the day names and the "now" label */
const TOP = 30
const PLOT = 190
const H = TOP + PLOT + 4

const width = computed(() => props.hours.length * COL)
const start = computed(() => props.hours[0]?.time.getTime() ?? 0)
const active = ref<number | null>(null)

const expected = (h: PvHourValue) => h.dcPower ?? h.power ?? 0

/** Full scale in whole kW, at least 2 */
const max = computed(() =>
  Math.max(2, Math.ceil(Math.max(...props.hours.map(expected), ...props.readings.map((r) => r.pvPower ?? 0), 0))),
)
const grid = computed(() => {
  const step = max.value > 6 ? 2 : 1
  const out: number[] = []
  for (let v = step; v <= max.value; v += step) out.push(v)
  return out
})

const BASE = TOP + PLOT
function y(kw: number): number {
  return BASE - PLOT * (kw / max.value)
}
function xOf(t: Date): number {
  return ((t.getTime() - start.value) / HOUR) * COL
}
function xCenter(i: number): number {
  return i * COL + COL / 2
}

/** 4px rounded top on the baseline, 2px gap to the neighbours */
function bar(i: number, v: number): string {
  if (v <= 0.005) return ''
  const w = COL - 4
  const x = i * COL + 2
  const h = Math.max(2, BASE - y(v))
  const r = Math.min(4, h, w / 2)
  const top = BASE - h
  return `M${x},${BASE}V${top + r}Q${x},${top} ${x + r},${top}H${x + w - r}Q${x + w},${top} ${x + w},${top + r}V${BASE}Z`
}

const nights = computed(() => {
  const out: { x: number; w: number }[] = []
  props.hours.forEach((h, i) => {
    if ((h.sunElevation ?? -1) > 0) return
    const last = out[out.length - 1]
    if (last && Math.abs(last.x + last.w - i * COL) < 0.5) last.w += COL
    else out.push({ x: i * COL, w: COL })
  })
  return out
})

/** The readings as a line; a gap of more than 15 minutes lifts the pen */
const readingPath = computed(() => {
  let d = ''
  let last: number | undefined
  for (const r of [...props.readings].sort((a, b) => a.time.getTime() - b.time.getTime())) {
    if (r.pvPower === undefined) continue
    const x = xOf(r.time)
    if (x < 0 || x > width.value) continue
    const pen = last !== undefined && r.time.getTime() - last <= 15 * 60_000
    d += `${pen ? 'L' : 'M'}${x.toFixed(1)},${y(r.pvPower).toFixed(1)}`
    last = r.time.getTime()
  }
  return d
})

const nowX = computed(() => xOf(props.now))
const midnights = computed(() => props.hours.map((h, i) => ({ i, h })).filter(({ h, i }) => i > 0 && isMidnight(h.time, props.timeZone)))

/** The day's highest expected hour gets its value written on it */
const peaks = computed(() => {
  const byDay = new Map<string, number>()
  props.hours.forEach((h, i) => {
    const day = shortDay(h.time, props.timeZone)
    const best = byDay.get(day)
    if (expected(h) > 0.05 && (best === undefined || expected(h) > expected(props.hours[best]))) byDay.set(day, i)
  })
  return [...byDay.values()]
})

function pick(event: PointerEvent): void {
  const el = event.currentTarget as HTMLElement
  const i = Math.floor((event.clientX - el.getBoundingClientRect().left) / COL)
  active.value = i >= 0 && i < props.hours.length ? i : null
}

const hovered = computed(() => (active.value === null ? null : props.hours[active.value]))
const rows = computed(() => {
  const h = hovered.value
  if (!h) return []
  return [
    { label: 'Erwartet, PV-Generator', value: `${kw(h.dcPower)} kW`, key: h.shaded ? 'box hatch' : 'box expected' },
    { label: 'nach Wechselrichter', value: `${kw(h.power)} kW`, sub: true },
    ...(h.measuredPower !== undefined ? [{ label: 'Gemessen, Mittel', value: `${kw(h.measuredPower)} kW`, key: 'dot measured' }] : []),
    { label: 'Einstrahlung Modul', value: `${whole(h.planeIrradiance)} W/m²` },
    { label: 'horizontal', value: `${whole(h.globalRadiation)} W/m²`, sub: true },
    { label: 'Zelltemperatur', value: h.cellTemperature === undefined ? '–' : `${whole(h.cellTemperature)} °C` },
    { label: 'Sonne', value: `${whole(h.sunElevation)}° hoch, ${whole(h.sunAzimuth)}°` },
    ...(h.shaded ? [{ label: 'Sonne verdeckt', value: 'Horizont/Wald' }] : []),
    ...(h.clipped ? [{ label: 'Wechselrichter', value: 'begrenzt' }] : []),
    { label: 'Quelle', value: h.source ?? '–' },
  ] as { label: string; value: string; key?: string; sub?: boolean }[]
})
const tooltipLeft = computed(() => {
  if (active.value === null) return 0
  return Math.min(Math.max(xCenter(active.value) - 145, 0), width.value - 290)
})
</script>

<template>
  <div class="frame">
    <figure class="chart" :style="{ width: `${width}px` }" @pointermove="pick" @pointerleave="active = null">
      <figcaption class="legend">
        <span>PV-Leistung · kW</span>
        <span><i class="key box expected" />erwartet, Stundenmittel</span>
        <span><i class="key box hatch" />erwartet, Sonne hinter Horizont/Wald</span>
        <span><i class="key line measured" />gemessen</span>
        <span><i class="key dot measured" />gemessen, Stundenmittel</span>
        <span><i class="key night" />Nacht</span>
      </figcaption>
      <svg :width="width" :height="H + 22" :viewBox="`0 0 ${width} ${H + 22}`" role="img" aria-label="Erwartete und gemessene PV-Leistung je Stunde">
        <defs>
          <pattern id="pv-hatch" width="6" height="6" patternUnits="userSpaceOnUse" patternTransform="rotate(45)">
            <rect width="6" height="6" class="hatch-bg" />
            <line x1="0" y1="0" x2="0" y2="6" class="hatch-line" />
          </pattern>
        </defs>
        <rect v-for="(n, k) in nights" :key="'n' + k" class="night" :x="n.x" :y="TOP - 6" :width="n.w" :height="PLOT + 6" />
        <rect v-if="active !== null" class="band" :x="active * COL" :y="TOP - 6" :width="COL" :height="PLOT + 6" />
        <g class="grid">
          <template v-for="g in grid" :key="g">
            <line :x1="0" :x2="width" :y1="y(g)" :y2="y(g)" />
            <text class="axis" x="4" :y="y(g) - 4">{{ g }} kW</text>
          </template>
          <line class="baseline" :x1="0" :x2="width" :y1="BASE + 0.5" :y2="BASE + 0.5" />
          <line v-for="m in midnights" :key="'m' + m.i" class="day" :x1="m.i * COL" :x2="m.i * COL" :y1="TOP - 6" :y2="BASE + 20" />
        </g>
        <template v-for="(h, i) in hours" :key="'b' + i">
          <path class="bar" :class="{ shaded: h.shaded, lit: active === i }" :d="bar(i, expected(h))" />
        </template>
        <text v-for="i in peaks" :key="'p' + i" class="peak" :x="xCenter(i)" :y="y(expected(hours[i])) - 6" text-anchor="middle">{{ kw(expected(hours[i])) }}</text>
        <path class="reading" :d="readingPath" />
        <template v-for="(h, i) in hours" :key="'m' + i">
          <circle v-if="h.measuredPower !== undefined && (h.measuredPower > 0.02 || expected(h) > 0.02)" class="measured" :cx="xCenter(i)" :cy="y(h.measuredPower)" r="5" />
        </template>
        <g v-if="nowX >= 0 && nowX <= width" class="now">
          <line :x1="nowX" :x2="nowX" :y1="TOP - 26" :y2="BASE" />
          <text :x="nowX + 4" :y="TOP - 16">jetzt</text>
        </g>
        <template v-for="(h, i) in hours" :key="'t' + i">
          <text v-if="i % 3 === 0" class="hour" :x="xCenter(i)" :y="BASE + 15" text-anchor="middle">{{ hourLabel(h.time, timeZone).slice(0, 2) }}</text>
        </template>
        <template v-for="m in [{ i: 0 }, ...midnights]" :key="'d' + m.i">
          <text class="dayname" :x="m.i * COL + 4" :y="TOP - 16">{{ shortDay(hours[m.i].time, timeZone) }}</text>
        </template>
      </svg>
      <button
        v-for="(h, i) in hours"
        :key="'f' + i"
        type="button"
        class="focus"
        :style="{ left: `${i * COL}px`, width: `${COL}px` }"
        :aria-label="`${shortDay(h.time, timeZone)} ${hourLabel(h.time, timeZone)}: erwartet ${kw(h.dcPower)} kW${h.measuredPower !== undefined ? `, gemessen ${kw(h.measuredPower)} kW` : ''}${h.shaded ? ', verschattet' : ''}`"
        @focus="active = i"
        @blur="active = null"
      />
      <div v-if="hovered && active !== null" class="tooltip" :style="{ left: `${tooltipLeft}px` }" role="status">
        <div class="tt-head">{{ shortDay(hovered.time, timeZone) }}, {{ hourLabel(hovered.time, timeZone) }}–{{ hourLabel(new Date(hovered.time.getTime() + HOUR), timeZone) }}</div>
        <dl>
          <template v-for="row in rows" :key="row.label">
            <dt :class="{ sub: row.sub }"><span class="slot"><i v-if="row.key" class="key" :class="row.key" /></span>{{ row.label }}</dt>
            <dd :class="{ sub: row.sub }">{{ row.value }}</dd>
          </template>
        </dl>
      </div>
    </figure>
  </div>
</template>

<style scoped>
.frame { overflow-x: auto; background: var(--surface); border: 1px solid var(--line); border-radius: 12px; padding: 12px 0 8px; }
.chart { position: relative; margin: 0; }
svg { display: block; overflow: visible; }
.legend { position: sticky; left: 0; display: flex; flex-wrap: wrap; gap: 4px 18px; font-size: 12px; color: var(--muted); padding: 0 10px 10px; width: max-content; max-width: 100vw; }
.legend span { display: inline-flex; align-items: center; gap: 6px; }
.night { fill: color-mix(in srgb, var(--ink) 4%, transparent); }
.band { fill: color-mix(in srgb, var(--accent) 9%, transparent); }
.grid line { stroke: var(--line); }
.grid .baseline { stroke: var(--line-2); }
.grid .day { stroke: var(--line-2); stroke-dasharray: 2 3; }
.axis, .hour { font-size: 10.5px; fill: var(--muted); font-variant-numeric: tabular-nums; }
.dayname { font-size: 11.5px; fill: var(--ink-2); }
.bar { fill: var(--s2); }
.bar.shaded { fill: url(#pv-hatch); stroke: var(--s2); stroke-width: 1; }
.bar.lit { filter: brightness(1.12); }
.hatch-bg { fill: color-mix(in srgb, var(--s2) 30%, var(--surface)); }
.hatch-line { stroke: var(--s2); stroke-width: 2.2; }
.peak { font-size: 11px; font-weight: 600; fill: var(--ink); font-variant-numeric: tabular-nums; }
.reading { fill: none; stroke: var(--s1); stroke-width: 2; stroke-linejoin: round; stroke-linecap: round; }
.measured { fill: var(--s1); stroke: var(--surface); stroke-width: 1.5; }
.now line { stroke: var(--ink-2); stroke-width: 1; stroke-dasharray: 3 3; }
.now text { font-size: 11px; fill: var(--ink-2); }
.focus { position: absolute; top: 44px; height: 200px; background: none; border: 0; padding: 0; cursor: default; }
.focus:focus-visible { outline: 2px solid var(--accent); outline-offset: -2px; border-radius: 4px; }
.key { display: inline-block; }
.key.box { width: 10px; height: 10px; border-radius: 2px; }
.key.expected { background: var(--s2); }
.key.hatch { background: repeating-linear-gradient(45deg, var(--s2) 0 2px, color-mix(in srgb, var(--s2) 30%, var(--surface)) 2px 5px); outline: 1px solid var(--s2); }
.key.line { width: 14px; height: 2px; border-radius: 1px; }
.key.line.measured { background: var(--s1); }
.key.dot { width: 9px; height: 9px; border-radius: 50%; }
.key.dot.measured { background: var(--s1); }
.key.night { width: 12px; height: 10px; border-radius: 2px; background: color-mix(in srgb, var(--ink) 9%, transparent); }
.tooltip { position: absolute; top: 40px; width: 290px; background: var(--sunken); border: 1px solid var(--line-2); border-radius: 8px; padding: 10px 12px; font-size: 12.5px; pointer-events: none; box-shadow: 0 6px 20px rgb(0 0 0 / .25); z-index: 2; }
.tt-head { color: var(--muted); margin-bottom: 6px; }
dl { display: grid; grid-template-columns: 1fr auto; gap: 2px 12px; margin: 0; }
dt { color: var(--muted); display: flex; align-items: center; gap: 6px; white-space: nowrap; }
dt .slot { flex: none; width: 14px; display: inline-flex; align-items: center; justify-content: center; }
dt .key { flex: none; }
dd { margin: 0; text-align: right; font-weight: 600; color: var(--ink); font-variant-numeric: tabular-nums; white-space: nowrap; }
dt.sub { padding-left: 18px; font-size: 11.5px; }
dd.sub { font-weight: 400; color: var(--ink-2); font-size: 11.5px; }
</style>
