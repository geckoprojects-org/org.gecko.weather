<script setup lang="ts">
/**
 * The next 24 hours as columns: time, sky, temperature, then two small charts sharing the columns —
 * temperature as a line, precipitation as bars — and the wind. Two charts rather than one with two
 * y-axes: temperature and millimetres have nothing in common but the hour.
 *
 * Wind arrows point where the air goes (a wind from the west points east).
 *
 * The rows above the charts carry every value as text, so the charts add shape, not information
 * one could only get by hovering. Hover or focus on a column shows all of its values at once.
 */
import { computed, ref } from 'vue'
import type { HourValue } from '../contracts.js'
import WeatherIcon from './WeatherIcon.vue'
import { compass, degrees, fixed1, hourLabel, isMidnight, kmh, niceRange, shortDay, skyOf, skyText, whole } from './weather.js'

const props = defineProps<{ hours: HourValue[]; timeZone: string }>()

/** Width of one hour */
const COL = 56
const TEMP_H = 110
const RAIN_H = 64
const PAD = 14

const width = computed(() => props.hours.length * COL)
const active = ref<number | null>(null)

const temps = computed(() => props.hours.map((h) => h.temperature).filter((t): t is number => t !== undefined))
const tempRange = computed(() => niceRange(temps.value, 2, 6))

function yTemp(t: number): number {
  const [lo, hi] = tempRange.value
  return PAD + (TEMP_H - 2 * PAD) * (1 - (t - lo) / (hi - lo))
}

function xCenter(i: number): number {
  return i * COL + COL / 2
}

/** The line, broken where a temperature is missing */
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
  const step = (hi - lo) / 2
  return [lo, lo + step, hi]
})

/** mm scale: at least 2 mm so a drizzle does not look like a cloudburst */
const rainMax = computed(() => Math.max(2, ...props.hours.map((h) => h.precipitation ?? 0)))
const dry = computed(() => props.hours.every((h) => !h.precipitation))

/** A bar with 4px rounded top, anchored at the baseline; the 16px gap keeps neighbours apart */
function rainBar(i: number, mm: number | undefined): string {
  if (!mm || mm <= 0) return ''
  const w = COL - 16
  const x = i * COL + 8
  const base = RAIN_H - 1
  const h = Math.max(3, (RAIN_H - 8) * (mm / rainMax.value))
  const r = Math.min(4, h, w / 2)
  const top = base - h
  return `M${x},${base}V${top + r}Q${x},${top} ${x + r},${top}H${x + w - r}Q${x + w},${top} ${x + w},${top + r}V${base}Z`
}

function pick(event: PointerEvent): void {
  const el = event.currentTarget as HTMLElement
  const x = event.clientX - el.getBoundingClientRect().left
  const i = Math.floor(x / COL)
  active.value = i >= 0 && i < props.hours.length ? i : null
}

const hovered = computed(() => (active.value === null ? null : props.hours[active.value]))
const tooltipLeft = computed(() => {
  if (active.value === null) return 0
  const x = xCenter(active.value)
  return Math.min(Math.max(x - 100, 0), width.value - 200)
})
</script>

<template>
  <div class="hourly-frame">
    <div
      class="hourly"
      :style="{ width: `${width}px`, '--col': `${COL}px`, '--n': hours.length }"
      @pointermove="pick"
      @pointerleave="active = null"
    >
      <div v-if="active !== null" class="band" :style="{ left: `${active * COL}px`, width: `${COL}px` }" aria-hidden="true" />

      <div class="row days" aria-hidden="true">
        <span
          v-for="(h, i) in hours"
          :key="'d' + i"
          class="cell"
          :class="{ start: i === 0 || isMidnight(h.time, timeZone) }"
        >{{ i === 0 || isMidnight(h.time, timeZone) ? shortDay(h.time, timeZone) : '' }}</span>
      </div>

      <div class="row times" role="list" aria-label="Stunden">
        <button
          v-for="(h, i) in hours"
          :key="'t' + i"
          type="button"
          role="listitem"
          class="cell time"
          :class="{ night: !h.daylight }"
          :aria-label="`${hourLabel(h.time, timeZone)}: ${skyText(skyOf(h.weatherCode, h.cloudCover))}, ${degrees(h.temperature)}, ${fixed1(h.precipitation)} mm, Wind ${kmh(h.windSpeed)} km/h`"
          @focus="active = i"
          @blur="active = null"
        >{{ hourLabel(h.time, timeZone).slice(0, 2) }}</button>
      </div>

      <div class="row sky" aria-hidden="true">
        <span v-for="(h, i) in hours" :key="'s' + i" class="cell" :class="{ night: !h.daylight }" :title="skyText(skyOf(h.weatherCode, h.cloudCover))">
          <WeatherIcon :sky="skyOf(h.weatherCode, h.cloudCover)" :night="!h.daylight" :size="26" />
        </span>
      </div>

      <div class="row temp" aria-hidden="true">
        <span v-for="(h, i) in hours" :key="'v' + i" class="cell value">{{ degrees(h.temperature) }}</span>
      </div>

      <figure class="chart">
        <figcaption>Temperatur · °C</figcaption>
        <svg :width="width" :height="TEMP_H" :viewBox="`0 0 ${width} ${TEMP_H}`" role="img" aria-label="Temperaturverlauf der nächsten 24 Stunden">
          <g class="grid">
            <template v-for="g in tempGrid" :key="'g' + g">
              <line :x1="0" :x2="width" :y1="yTemp(g)" :y2="yTemp(g)" />
              <text class="axis" x="4" :y="yTemp(g) - 4">{{ g }}°</text>
            </template>
          </g>
          <path class="line temp" :d="tempPath" />
          <circle
            v-if="hovered?.temperature !== undefined && active !== null"
            class="marker temp"
            :cx="xCenter(active)"
            :cy="yTemp(hovered!.temperature!)"
            r="4.5"
          />
        </svg>
      </figure>

      <figure class="chart">
        <figcaption>Niederschlag · mm pro Stunde</figcaption>
        <svg :width="width" :height="RAIN_H" :viewBox="`0 0 ${width} ${RAIN_H}`" role="img" aria-label="Niederschlag der nächsten 24 Stunden">
          <line class="baseline" :x1="0" :x2="width" :y1="RAIN_H - 0.5" :y2="RAIN_H - 0.5" />
          <text v-if="dry" class="axis" x="8" :y="RAIN_H - 10">kein Niederschlag erwartet</text>
          <path
            v-for="(h, i) in hours"
            :key="'r' + i"
            class="bar rain"
            :class="{ lit: active === i }"
            :d="rainBar(i, h.precipitation)"
          />
        </svg>
      </figure>

      <div class="row prob" aria-hidden="true">
        <span v-for="(h, i) in hours" :key="'p' + i" class="cell value small" :class="{ dim: (h.precipitationProbability ?? 0) < 30 }">
          {{ h.precipitationProbability === undefined ? '–' : `${whole(h.precipitationProbability)} %` }}
        </span>
      </div>

      <div class="row wind" aria-hidden="true">
        <span v-for="(h, i) in hours" :key="'w' + i" class="cell value small">
          <svg
            v-if="h.windDirection !== undefined"
            class="arrow"
            width="12"
            height="12"
            viewBox="0 0 12 12"
            :style="{ transform: `rotate(${h.windDirection}deg)` }"
          ><path d="M6 1v10M2.5 7.5 6 11l3.5-3.5" /></svg>
          {{ kmh(h.windSpeed) }}
        </span>
      </div>

      <div v-if="hovered && active !== null" class="tooltip" :style="{ left: `${tooltipLeft}px` }" role="status">
        <div class="tt-head">{{ shortDay(hovered.time, timeZone) }}, {{ hourLabel(hovered.time, timeZone) }} · {{ skyText(skyOf(hovered.weatherCode, hovered.cloudCover)) }}</div>
        <dl>
          <dt><i class="key temp" />Temperatur</dt><dd>{{ degrees(hovered.temperature) }}<em>Taupunkt {{ degrees(hovered.dewPoint) }}</em></dd>
          <dt><i class="key rain" />Niederschlag</dt><dd>{{ fixed1(hovered.precipitation) }} mm<em>{{ whole(hovered.precipitationProbability) }} %</em></dd>
          <dt>Bewölkung</dt><dd>{{ whole(hovered.cloudCover) }} %</dd>
          <dt>Wind</dt><dd>{{ kmh(hovered.windSpeed) }} km/h {{ compass(hovered.windDirection) }}<em>Böen {{ kmh(hovered.windGust) }}</em></dd>
          <dt>Strahlung</dt><dd>{{ whole(hovered.globalRadiation) }} W/m²</dd>
        </dl>
      </div>
    </div>
  </div>
</template>

<style scoped>
.hourly-frame {
  overflow-x: auto;
  background: var(--surface);
  border: 1px solid var(--line);
  border-radius: 12px;
  padding: 14px 0 12px;
}
.hourly { position: relative; }
.row { display: grid; grid-template-columns: repeat(var(--n), var(--col)); }
.cell { text-align: center; font-variant-numeric: tabular-nums; }
.days .cell { font-size: 12px; color: var(--muted); text-align: left; padding-left: 8px; white-space: nowrap; height: 18px; overflow: visible; }
.days .cell.start { border-left: 1px solid var(--line-2); }
.time {
  font: inherit;
  font-size: 12.5px;
  color: var(--ink-2);
  background: none;
  border: 0;
  padding: 4px 0;
  cursor: default;
}
.time.night, .sky .night { color: var(--muted); }
.sky .cell { color: var(--ink-2); display: grid; place-items: center; height: 34px; }
.temp .value { font-size: 15px; font-weight: 600; color: var(--ink); padding: 2px 0 0; }
.value.small { font-size: 12px; color: var(--ink-2); display: flex; justify-content: center; align-items: center; gap: 3px; }
.value.small.dim { color: var(--muted); }
.prob { margin-top: 4px; }
.wind { margin-top: 6px; }
.arrow { stroke: currentColor; stroke-width: 1.4; fill: none; stroke-linecap: round; stroke-linejoin: round; }

.chart { margin: 10px 0 0; }
figcaption { font-size: 12px; color: var(--muted); padding: 0 8px 2px; }
svg { display: block; overflow: visible; }
.grid line { stroke: var(--line); stroke-width: 1; }
.axis { font-size: 10.5px; fill: var(--muted); }
.baseline { stroke: var(--line-2); stroke-width: 1; }
.line { fill: none; stroke-width: 2; stroke-linejoin: round; stroke-linecap: round; }
.line.temp { stroke: var(--s2); }
.marker.temp { fill: var(--s2); stroke: var(--surface); stroke-width: 2; }
.bar.rain { fill: var(--s1); }
.bar.rain.lit { fill: color-mix(in srgb, var(--s1) 75%, var(--ink)); }

.band {
  position: absolute;
  top: 0;
  bottom: 0;
  background: color-mix(in srgb, var(--accent) 9%, transparent);
  border-radius: 6px;
  pointer-events: none;
}

.tooltip {
  position: absolute;
  top: 56px;
  width: 200px;
  background: var(--sunken);
  border: 1px solid var(--line-2);
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 12.5px;
  pointer-events: none;
  box-shadow: 0 6px 20px rgb(0 0 0 / .25);
  z-index: 2;
}
.tt-head { color: var(--muted); margin-bottom: 6px; }
dl { display: grid; grid-template-columns: auto 1fr; gap: 3px 10px; margin: 0; }
dt { color: var(--muted); display: flex; align-items: center; gap: 6px; }
dd { margin: 0; text-align: right; font-weight: 600; color: var(--ink); font-variant-numeric: tabular-nums; }
dd em { display: block; font-style: normal; font-weight: 400; color: var(--muted); font-size: 11.5px; }
.key { display: inline-block; width: 12px; height: 2px; border-radius: 1px; }
.key.temp { background: var(--s2); }
.key.rain { background: var(--s1); }
</style>
