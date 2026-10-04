<script setup lang="ts">
/** The next days as tiles — the xdp tile with its metrics and foot. */
import type { DayValue } from '../contracts.js'
import WeatherIcon from './WeatherIcon.vue'
import { clock, dayLabel, degrees, fixed1, kmh, skyOf, skyText, whole } from './weather.js'

defineProps<{ days: DayValue[]; timeZone: string }>()

function hours(h: number | undefined): string {
  if (h === undefined) return '–'
  const m = Math.round(h * 60)
  return `${Math.floor(m / 60)} h ${String(m % 60).padStart(2, '0')} min`
}
</script>

<template>
  <div class="tiles">
    <article v-for="d in days" :key="d.date" class="tile day">
      <div class="head">
        <div class="icon"><WeatherIcon :sky="skyOf(d.weatherCode, d.cloudCoverMean)" :size="26" /></div>
        <div>
          <h3>{{ dayLabel(d.date).weekday }}</h3>
          <div class="role">{{ dayLabel(d.date).date }} · {{ skyText(skyOf(d.weatherCode, d.cloudCoverMean)) }}</div>
        </div>
        <div class="range" :aria-label="`Höchstwert ${degrees(d.temperatureMax)}, Tiefstwert ${degrees(d.temperatureMin)}`">
          <span class="max">{{ degrees(d.temperatureMax) }}</span>
          <span class="min">{{ degrees(d.temperatureMin) }}</span>
        </div>
      </div>
      <div class="metrics">
        <div class="metric">
          <div class="value">{{ fixed1(d.precipitation) }} <em>mm</em></div>
          <div class="caption">Niederschlag · bis {{ whole(d.precipitationProbability) }} % je Stunde</div>
        </div>
        <div class="metric">
          <div class="value">{{ fixed1(d.sunshineHours) }} <em>h</em></div>
          <div class="caption">Sonnenschein</div>
        </div>
        <div class="metric">
          <div class="value">{{ fixed1(d.uvIndexMax) }}</div>
          <div class="caption">UV-Index, Maximum</div>
        </div>
        <div class="metric">
          <div class="value">{{ whole(d.cloudCoverMean) }} <em>%</em></div>
          <div class="caption">Bewölkung im Mittel</div>
        </div>
        <div class="metric">
          <div class="value">{{ kmh(d.windGustMax) }} <em>km/h</em></div>
          <div class="caption">Böen, stärkste</div>
        </div>
      </div>
      <dl class="foot">
        <dt>Sonnenaufgang</dt><dd>{{ clock(d.sunrise, timeZone) }}</dd>
        <dt>Sonnenuntergang</dt><dd>{{ clock(d.sunset, timeZone) }}</dd>
        <dt>Tageslänge</dt><dd>{{ hours(d.daylightHours) }}</dd>
      </dl>
    </article>
  </div>
</template>

<style scoped>
.day { --status: var(--accent); }
.range { margin-left: auto; text-align: right; line-height: 1.15; font-variant-numeric: tabular-nums; }
.range .max { display: block; font-size: 26px; font-weight: 600; letter-spacing: -.03em; }
.range .min { color: var(--muted); font-size: 15px; }
.foot dd { margin: 0; font-variant-numeric: tabular-nums; }
.foot dt { color: var(--muted); }
</style>
