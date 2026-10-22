<script setup lang="ts">
/**
 * The weather of a site: the next 24 hours by the hour, the days after in summary, and where
 * the values come from. In xdp-ui this becomes view.weather, registered under XDP_VIEW; the
 * outlook arrives as a service (XDP_WEATHER_OUTLOOK) and, without the registry bundle, the page
 * shows examples and says so — as view.persistence does.
 */
import { computed, onMounted, onUnmounted, ref, watch } from 'vue'
import type { OutlookSnapshot, SiteInfo, WeatherOutlook } from '../contracts.js'
import DayOverview from './DayOverview.vue'
import HourlyForecast from './HourlyForecast.vue'
import SourceChip from './SourceChip.vue'
import { ago, clock, clockSeconds, compass, degrees, fixed1, hourLabel, kmh, whole } from './weather.js'

/** The page reads itself anew this often while it is visible — the station reports every minute */
const REFRESH_MS = 60_000

const props = defineProps<{ outlook: WeatherOutlook; fallback?: WeatherOutlook }>()

const source = ref<WeatherOutlook>(props.outlook)
const sites = ref<SiteInfo[]>([])
/** The site from the URL (`?site=<id>`) first, else the first the registry lists; the choice is kept in the URL */
const siteId = ref<string>(new URLSearchParams(window.location.search).get('site') ?? '')
const snapshot = ref<OutlookSnapshot | null>(null)
const state = ref<'loading' | 'ready' | 'error'>('loading')
const error = ref<string>('')

const timeZone = computed(() => snapshot.value?.timeZone ?? 'Europe/Berlin')
const generated = computed(() => clockSeconds(snapshot.value?.generatedAt, timeZone.value))

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

let timer: number | undefined
onMounted(() => {
  void load()
  timer = window.setInterval(() => {
    if (document.visibilityState === 'visible' && state.value !== 'loading') void load()
  }, REFRESH_MS)
})
onUnmounted(() => window.clearInterval(timer))
watch(siteId, (now, before) => {
  if (now) {
    const url = new URL(window.location.href)
    url.searchParams.set('site', now)
    window.history.replaceState(null, '', url)
  }
  if (before && now !== before) void load()
})

const NUMBER_WORDS = ['', 'ein', 'zwei', 'drei', 'vier', 'fünf', 'sechs', 'sieben', 'acht', 'neun', 'zehn']

/** "Nächste sieben Tage" — MOSMIX reaches ten days, the configuration says how many are shown */
function daysTitle(n: number): string {
  if (n === 1) return 'Morgen'
  return `Nächste ${NUMBER_WORDS[n] ?? n} Tage`
}

function dayLength(h: number | undefined): string {
  if (h === undefined) return '–'
  const m = Math.round(h * 60)
  return `${Math.floor(m / 60)} h ${String(m % 60).padStart(2, '0')} min`
}

function distance(m: number | undefined): string {
  if (m === undefined) return ''
  return m < 1000 ? `${Math.round(m)} m` : `${(m / 1000).toLocaleString('de-DE', { maximumFractionDigits: 1 })} km`
}

/** The forecast's current hour — what the station's measurement stands beside */
const currentHour = computed(() => {
  const hours = snapshot.value?.hours ?? []
  const t = (snapshot.value?.generatedAt ?? new Date()).getTime()
  return hours.find((h) => h.time.getTime() <= t && t < h.time.getTime() + 3_600_000) ?? hours[0]
})

/** The station is silent when its newest value is older than ten minutes */
const observationStale = computed(() => {
  const o = snapshot.value?.observation
  const at = snapshot.value?.generatedAt ?? new Date()
  return !!o && at.getTime() - o.time.getTime() > 10 * 60_000
})

/** The forecast's providers for the current hour, one chip each */
const forecastProviders = computed(() => {
  const seen = new Map<string, { providerId: string; productId: string }>()
  for (const s of snapshot.value?.sources ?? []) {
    if (s.quantities.startsWith('gemessen')) continue
    if (!seen.has(s.providerId)) seen.set(s.providerId, { providerId: s.providerId, productId: s.productId })
  }
  return [...seen.values()]
})

function issued(d: Date | undefined): string {
  if (!d) return ''
  return new Intl.DateTimeFormat('de-DE', { day: 'numeric', month: 'numeric', hour: '2-digit', minute: '2-digit', timeZone: timeZone.value }).format(d)
}
</script>

<template>
  <section class="view wide">
    <h1>Wetter{{ snapshot ? ` · ${snapshot.siteName}` : '' }}</h1>
    <p class="intro">
      Die nächsten 24 Stunden Stunde für Stunde, die nächsten Tage im Überblick — je Größe aus der
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
        <h2 class="section-title">{{ snapshot.measured.length ? 'Rückblick und nächste 24 Stunden' : 'Nächste 24 Stunden' }}</h2>
        <p v-if="snapshot.today" class="today" aria-label="Heute">
          <span class="label">Heute</span>
          <span>Sonnenaufgang <b>{{ clock(snapshot.today.sunrise, timeZone) }}</b></span>
          <span>Sonnenuntergang <b>{{ clock(snapshot.today.sunset, timeZone) }}</b></span>
          <span>Tageslänge <b>{{ dayLength(snapshot.today.daylightHours) }}</b></span>
          <span>UV-Index max. <b>{{ fixed1(snapshot.today.uvIndexMax) }}</b>{{ snapshot.today.solarNoon ? ` um ${clock(snapshot.today.solarNoon, timeZone)}` : '' }}</span>
        </p>
        <div v-if="snapshot.observation" class="now" :class="{ stale: observationStale }" aria-label="Jetzt">
          <div class="now-head">
            <span class="label">Jetzt</span>
            <span class="dim">gemessen an der eigenen Station, Vorhersage daneben</span>
          </div>
          <table class="now-table">
            <thead>
              <tr>
                <th scope="col"></th>
                <th scope="col">Temperatur</th>
                <th scope="col">Luftfeuchte</th>
                <th scope="col">Wind</th>
                <th scope="col">Böen</th>
                <th scope="col">Strahlung</th>
                <th scope="col">UV</th>
              </tr>
            </thead>
            <tbody>
              <tr class="measured">
                <th scope="row">
                  <SourceChip :provider-id="snapshot.observation.providerId" :product-id="snapshot.observation.productId" :location="snapshot.observation.stationId ? `Station ${snapshot.observation.stationId}` : undefined" />
                  <span class="dim">{{ observationStale ? 'keine Telegramme seit' : '' }} {{ ago(snapshot.observation.time, snapshot.generatedAt) }}</span>
                </th>
                <td>{{ degrees(snapshot.observation.temperature) }}C</td>
                <td>{{ whole(snapshot.observation.humidity) }} %</td>
                <td>{{ kmh(snapshot.observation.windSpeed) }} km/h {{ compass(snapshot.observation.windDirection) }}</td>
                <td>{{ kmh(snapshot.observation.windGust) }} km/h</td>
                <td>{{ whole(snapshot.observation.globalRadiation) }} W/m²</td>
                <td>{{ fixed1(snapshot.observation.uvIndex) }}</td>
              </tr>
              <tr v-if="currentHour" class="forecast">
                <th scope="row">
                  <SourceChip v-for="p in forecastProviders" :key="p.providerId" :provider-id="p.providerId" :product-id="p.productId" />
                  <span class="dim">{{ hourLabel(currentHour.time, timeZone) }}</span>
                </th>
                <td>{{ degrees(currentHour.temperature) }}C</td>
                <td class="dim">–</td>
                <td>{{ kmh(currentHour.windSpeed) }} km/h {{ compass(currentHour.windDirection) }}</td>
                <td>{{ kmh(currentHour.windGust) }} km/h</td>
                <td>{{ whole(currentHour.globalRadiation) }} W/m²</td>
                <td class="dim">{{ snapshot.today ? `max. ${fixed1(snapshot.today.uvIndexMax)}` : '–' }}</td>
              </tr>
            </tbody>
          </table>
          <p v-if="snapshot.observation.batteryLevel !== undefined && snapshot.observation.batteryLevel < 1" class="problem">
            Die Station meldet eine schwache Batterie.
          </p>
        </div>
        <HourlyForecast
          v-if="snapshot.hours.length"
          :hours="snapshot.hours"
          :today="snapshot.today"
          :days="snapshot.days"
          :sources="snapshot.sources"
          :measured="snapshot.measured"
          :now="snapshot.generatedAt"
          :time-zone="timeZone"
        />
        <p v-else class="dim">Für diesen Standort liegen noch keine Werte vor.</p>

        <h2 class="section-title">{{ daysTitle(snapshot.days.length) }}</h2>
        <DayOverview v-if="snapshot.days.length" :days="snapshot.days" :sources="snapshot.sources" :time-zone="timeZone" />

        <h2 class="section-title">Quellen</h2>
        <ul class="list sources">
          <li v-for="s in snapshot.sources" :key="s.productId + s.location">
            <span class="product"><SourceChip :provider-id="s.providerId" :product-id="s.productId" /> {{ s.providerId }}/{{ s.productId }}</span>
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
.sources li { display: grid; grid-template-columns: 250px 1fr auto; gap: 16px; align-items: baseline; }
.now {
  background: var(--surface);
  border: 1px solid var(--line);
  border-radius: 12px;
  padding: 12px 16px 10px;
  margin: 0 0 14px;
}
.now-head { display: flex; gap: 12px; align-items: baseline; margin-bottom: 6px; }
.now-head .label { color: var(--ink); font-weight: 600; }
.now-table { border-collapse: collapse; width: 100%; font-variant-numeric: tabular-nums; }
.now-table th, .now-table td { padding: 5px 10px 5px 0; text-align: left; white-space: nowrap; }
.now-table thead th { color: var(--muted); font-weight: 500; font-size: 12.5px; border-bottom: 1px solid var(--line); }
.now-table tbody th { font-weight: 500; display: flex; gap: 8px; align-items: center; }
.now-table .measured td { color: var(--ink); font-weight: 600; }
.now-table .forecast td { color: var(--ink-2); }
.now.stale .measured td { color: var(--muted); font-weight: 500; }
.product { font-family: var(--mono); font-size: 12.5px; }
.dim { color: var(--muted); }
@media (max-width: 760px) {
  .sources li { grid-template-columns: 1fr; gap: 2px; }
  .now-table { display: block; overflow-x: auto; }
}
</style>
