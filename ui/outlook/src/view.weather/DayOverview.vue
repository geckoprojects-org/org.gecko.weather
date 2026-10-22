<script setup lang="ts">
/**
 * The next days as tiles — the xdp tile with its metrics and foot. Three to a row from seven days
 * on, so that a week does not become a column.
 */
import { computed } from 'vue'
import type { DayValue, SourceInfo } from '../contracts.js'
import SourceChip from './SourceChip.vue'
import WeatherIcon from './WeatherIcon.vue'
import { clock, dayLabel, degrees, fixed1, kmh, skyOf, skyText, whole } from './weather.js'

const props = defineProps<{ days: DayValue[]; sources?: SourceInfo[]; timeZone: string }>()

/** The days are forecast: one chip per provider that contributed, the measured-now note left out */
const providers = computed(() => {
  const seen = new Map<string, SourceInfo>()
  for (const s of props.sources ?? []) {
    if (s.quantities.startsWith('gemessen')) continue
    if (!seen.has(s.providerId)) seen.set(s.providerId, s)
  }
  return [...seen.values()]
})

function hours(h: number | undefined): string {
  if (h === undefined) return '–'
  const m = Math.round(h * 60)
  return `${Math.floor(m / 60)} h ${String(m % 60).padStart(2, '0')} min`
}
</script>

<template>
  <div class="tiles" :class="{ three: days.length > 4 }">
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
          <div class="value">{{ fixed1(d.insolation) }} <em>kWh/m²</em></div>
          <div class="caption">Einstrahlung, horizontal</div>
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
        <dt v-if="providers.length">Quelle</dt><dd v-if="providers.length" class="chips"><SourceChip v-for="p in providers" :key="p.providerId" :provider-id="p.providerId" :product-id="p.productId" /></dd>
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
.foot .chips { display: flex; gap: 4px; }
</style>
