<script setup lang="ts">
/**
 * The weather of a site: the next 24 hours by the hour, the next two days in summary, and where
 * the values come from. In xdp-ui this becomes view.weather, registered under XDP_VIEW; the
 * outlook arrives as a service (XDP_WEATHER_OUTLOOK) and, without the registry bundle, the page
 * shows examples and says so — as view.persistence does.
 */
import { computed, onMounted, ref, watch } from 'vue'
import type { OutlookSnapshot, SiteInfo, WeatherOutlook } from '../contracts.js'
import DayOverview from './DayOverview.vue'
import HourlyForecast from './HourlyForecast.vue'
import { clock, fixed1, hourLabel } from './weather.js'

const props = defineProps<{ outlook: WeatherOutlook; fallback?: WeatherOutlook }>()

const source = ref<WeatherOutlook>(props.outlook)
const sites = ref<SiteInfo[]>([])
const siteId = ref<string>('')
const snapshot = ref<OutlookSnapshot | null>(null)
const state = ref<'loading' | 'ready' | 'error'>('loading')
const error = ref<string>('')

const timeZone = computed(() => snapshot.value?.timeZone ?? 'Europe/Berlin')
const generated = computed(() =>
  snapshot.value?.generatedAt ? hourLabel(snapshot.value.generatedAt, timeZone.value) : '–',
)

async function loadSites(): Promise<void> {
  try {
    sites.value = await source.value.sites()
  } catch (e) {
    if (!props.fallback || source.value === props.fallback) throw e
    console.warn('weather: Registry nicht erreichbar, zeige Beispieldaten', e)
    error.value = e instanceof Error ? e.message : String(e)
    source.value = props.fallback
    sites.value = await source.value.sites()
  }
  if (!sites.value.some((s) => s.id === siteId.value)) siteId.value = sites.value[0]?.id ?? ''
}

async function load(): Promise<void> {
  state.value = 'loading'
  try {
    if (sites.value.length === 0) await loadSites()
    if (!siteId.value) throw new Error('Kein Standort registriert')
    snapshot.value = await source.value.outlook(siteId.value)
    state.value = 'ready'
  } catch (e) {
    error.value = e instanceof Error ? e.message : String(e)
    state.value = 'error'
  }
}

onMounted(load)
watch(siteId, (now, before) => {
  if (before && now !== before) void load()
})

function dayLength(h: number | undefined): string {
  if (h === undefined) return '–'
  const m = Math.round(h * 60)
  return `${Math.floor(m / 60)} h ${String(m % 60).padStart(2, '0')} min`
}

function distance(m: number | undefined): string {
  if (m === undefined) return ''
  return m < 1000 ? `${Math.round(m)} m` : `${(m / 1000).toLocaleString('de-DE', { maximumFractionDigits: 1 })} km`
}

function issued(d: Date | undefined): string {
  if (!d) return ''
  return new Intl.DateTimeFormat('de-DE', { day: 'numeric', month: 'numeric', hour: '2-digit', minute: '2-digit', timeZone: timeZone.value }).format(d)
}
</script>

<template>
  <section class="view wide">
    <h1>Wetter{{ snapshot ? ` · ${snapshot.siteName}` : '' }}</h1>
    <p class="intro">
      Die nächsten 24 Stunden Stunde für Stunde, die nächsten zwei Tage im Überblick — je Größe aus der
      Quelle, die für den Standort am nächsten liegt.
    </p>

    <div class="section-head toolbar">
      <label v-if="sites.length > 1" class="site">Standort
        <select v-model="siteId">
          <option v-for="s in sites" :key="s.id" :value="s.id">{{ s.name }}</option>
        </select>
      </label>
      <span class="stamp">Stand {{ generated }}</span>
      <button class="btn small" type="button" :disabled="state === 'loading'" @click="load()">Neu lesen</button>
    </div>

    <p v-if="state === 'error'" class="problem" role="alert">{{ error }}</p>

    <div :class="{ refreshing: state === 'loading' && snapshot }">
      <template v-if="snapshot">
        <h2 class="section-title">Nächste 24 Stunden</h2>
        <p v-if="snapshot.today" class="today" aria-label="Heute">
          <span class="label">Heute</span>
          <span>Sonnenaufgang <b>{{ clock(snapshot.today.sunrise, timeZone) }}</b></span>
          <span>Sonnenuntergang <b>{{ clock(snapshot.today.sunset, timeZone) }}</b></span>
          <span>Tageslänge <b>{{ dayLength(snapshot.today.daylightHours) }}</b></span>
          <span>UV-Index max. <b>{{ fixed1(snapshot.today.uvIndexMax) }}</b>{{ snapshot.today.solarNoon ? ` um ${clock(snapshot.today.solarNoon, timeZone)}` : '' }}</span>
        </p>
        <HourlyForecast
          v-if="snapshot.hours.length"
          :hours="snapshot.hours"
          :today="snapshot.today"
          :days="snapshot.days"
          :time-zone="timeZone"
        />
        <p v-else class="dim">Für diesen Standort liegen noch keine Werte vor.</p>

        <h2 class="section-title">Nächste zwei Tage</h2>
        <DayOverview v-if="snapshot.days.length" :days="snapshot.days" :time-zone="timeZone" />

        <h2 class="section-title">Quellen</h2>
        <ul class="list sources">
          <li v-for="s in snapshot.sources" :key="s.productId + s.location">
            <span class="product">{{ s.providerId }}/{{ s.productId }}</span>
            <span>{{ s.quantities }}</span>
            <span class="dim">{{ s.location }}{{ s.distanceMeters !== undefined ? ` · ${distance(s.distanceMeters)}` : '' }}{{ s.issuedAt ? ` · ausgegeben ${issued(s.issuedAt)}` : '' }}</span>
          </li>
        </ul>
      </template>
      <p v-else-if="state === 'loading'" class="dim">Lese die Vorhersage …</p>
    </div>

    <p class="note">
      {{ source.origin() }}<template v-if="source.examples"> — Beispieldaten, die Registry hat nicht geantwortet.</template>
    </p>
  </section>
</template>

<style scoped>
.toolbar { margin-top: 0; }
.site { display: flex; align-items: center; gap: 8px; color: var(--muted); font-size: 13px; }
.site select {
  font: inherit;
  color: var(--ink);
  background: var(--surface);
  border: 1px solid var(--line-2);
  border-radius: 8px;
  padding: 4px 8px;
}
.stamp { color: var(--muted); font-size: 13px; }
.problem { color: var(--crit); }
.today { display: flex; flex-wrap: wrap; gap: 6px 22px; align-items: baseline; margin: -4px 0 14px; color: var(--muted); font-size: 13px; }
.today b { color: var(--ink); font-weight: 600; font-variant-numeric: tabular-nums; }
.today .label { color: var(--ink); font-weight: 600; }
.refreshing { opacity: .55; transition: opacity .2s; }
.sources li { display: grid; grid-template-columns: 170px 1fr auto; gap: 16px; align-items: baseline; }
.product { font-family: var(--mono); font-size: 12.5px; }
.dim { color: var(--muted); }
@media (max-width: 760px) {
  .sources li { grid-template-columns: 1fr; gap: 2px; }
}
</style>
