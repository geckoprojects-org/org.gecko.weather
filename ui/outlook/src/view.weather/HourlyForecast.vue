<script setup lang="ts">
/**
 * The next 24 hours as columns: time, sky, temperature, then the meteogram on the same columns —
 * temperature, precipitation, night, sun and UV in one picture (see Meteogram) — and below it the
 * rain probability and the wind.
 *
 * Wind arrows point where the air goes (a wind from the west points east).
 *
 * The rows above the charts carry every value as text, so the charts add shape, not information
 * one could only get by hovering. Hover or focus on a column shows all of its values at once.
 */
import { computed, ref } from 'vue'
import type { DayValue, HourValue } from '../contracts.js'
import Meteogram from './Meteogram.vue'
import WeatherIcon from './WeatherIcon.vue'
import { compass, degrees, fixed1, hourLabel, isMidnight, kmh, shortDay, skyOf, skyText, whole } from './weather.js'

const props = defineProps<{ hours: HourValue[]; today?: DayValue; days: DayValue[]; timeZone: string }>()

/** Width of one hour */
const COL = 56

const width = computed(() => props.hours.length * COL)
const active = ref<number | null>(null)
/** Today and the next days — the meteogram places their sun events and UV marks */
const sunDays = computed(() => (props.today ? [props.today, ...props.days] : props.days))

function xCenter(i: number): number {
  return i * COL + COL / 2
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
        <figcaption class="legend">
          <span><i class="key line temp" />Temperatur · °C</span>
          <span><i class="key box rain" />Niederschlag · mm pro Stunde</span>
          <span><i class="key night" />Nacht</span>
          <span><i class="key uv" />UV-Index, Tagesmaximum</span>
        </figcaption>
        <Meteogram :hours="hours" :days="sunDays" :time-zone="timeZone" :col="COL" :active="active" />
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
          <dt><i class="key line temp" />Temperatur</dt><dd>{{ degrees(hovered.temperature) }}<em>Taupunkt {{ degrees(hovered.dewPoint) }}</em></dd>
          <dt><i class="key box rain" />Niederschlag</dt><dd>{{ fixed1(hovered.precipitation) }} mm<em>{{ whole(hovered.precipitationProbability) }} %</em></dd>
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

.chart { margin: 8px 0 0; }
.legend {
  position: sticky;
  left: 0;
  display: flex;
  flex-wrap: wrap;
  gap: 4px 18px;
  font-size: 12px;
  color: var(--muted);
  padding: 0 8px 4px;
  width: max-content;
}
.legend span { display: inline-flex; align-items: center; gap: 6px; }
.key { display: inline-block; }
.key.line { width: 14px; height: 2px; border-radius: 1px; }
.key.box { width: 10px; height: 10px; border-radius: 2px; }
.key.temp { background: var(--s2); }
.key.rain { background: var(--s1); }
.key.night { width: 12px; height: 10px; border-radius: 2px; background: color-mix(in srgb, var(--ink) 9%, transparent); }
.key.uv { width: 8px; height: 8px; border-radius: 50%; border: 1.4px solid var(--ink-2); }

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
dt .key { width: 12px; height: 2px; border-radius: 1px; }
</style>
