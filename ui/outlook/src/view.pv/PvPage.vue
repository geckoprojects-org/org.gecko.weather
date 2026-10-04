<script setup lang="ts">
/**
 * A PV plant: what it is expected to produce from the start of today over the next 48 hours, what
 * its meter measured so far, and the days ahead. In xdp-ui a view of its own beside view.weather;
 * without the registry it shows examples and says so.
 */
import { computed, onMounted, onUnmounted, ref, watch } from 'vue'
import type { PlantInfo, PlantProfile, PvForecast, PvReadings, PvSnapshot } from '../contracts.js'
import { hourLabel, whole } from '../view.weather/weather.js'
import PvChart from './PvChart.vue'
import PvDays from './PvDays.vue'
import PvScene from './PvScene.vue'
import { facing, kw, kwh, latest, measuredRatio, percent } from './pv.js'

const props = defineProps<{ pv: PvForecast; fallback?: PvForecast }>()

const source = ref<PvForecast>(props.pv)
const plants = ref<PlantInfo[]>([])
const plantId = ref('')
const snapshot = ref<PvSnapshot | null>(null)
const readings = ref<PvReadings | null>(null)
const profile = ref<PlantProfile | null>(null)
const state = ref<'loading' | 'ready' | 'error'>('loading')
const error = ref('')
const now = ref(new Date())

const timeZone = computed(() => snapshot.value?.timeZone ?? 'Europe/Berlin')
const today = computed(() => new Intl.DateTimeFormat('en-CA', { timeZone: timeZone.value }).format(now.value))
const todayDay = computed(() => snapshot.value?.days.find((d) => d.date === today.value))
const current = computed(() => (readings.value ? latest(readings.value.readings, now.value) : undefined))
const ratio = computed(() => (snapshot.value ? measuredRatio(snapshot.value.hours, now.value) : undefined))
const metered = computed(() => (readings.value?.readings.length ?? 0) > 0)

async function loadPlants(): Promise<void> {
  try {
    plants.value = await source.value.plants()
  } catch (e) {
    if (!props.fallback || source.value === props.fallback) throw e
    console.warn('pv: Registry nicht erreichbar, zeige Beispieldaten', e)
    source.value = props.fallback
    plants.value = await source.value.plants()
  }
  if (!plants.value.some((p) => p.id === plantId.value)) plantId.value = plants.value[0]?.id ?? ''
}

async function load(): Promise<void> {
  state.value = 'loading'
  try {
    if (plants.value.length === 0) await loadPlants()
    if (!plantId.value) throw new Error('Keine Anlage mit Profil')
    now.value = new Date()
    const [s, r] = await Promise.all([source.value.forecast(plantId.value), source.value.measurements(plantId.value)])
    snapshot.value = s
    readings.value = r
    // the geometry changes only with the profile: read once per plant
    if (profile.value?.id !== plantId.value) profile.value = await source.value.plant(plantId.value).catch(() => null)
    state.value = 'ready'
  } catch (e) {
    error.value = e instanceof Error ? e.message : String(e)
    state.value = 'error'
  }
}

/** Readings arrive every minute; the page follows every five */
let timer: ReturnType<typeof setInterval> | undefined
onMounted(() => {
  void load()
  timer = setInterval(() => void load(), 5 * 60_000)
})
onUnmounted(() => clearInterval(timer))
watch(plantId, (n, b) => {
  if (b && n !== b) void load()
})

function deviation(r: number): string {
  const p = Math.round((r - 1) * 100)
  return p === 0 ? 'wie erwartet' : `${p > 0 ? '+' : '−'}${Math.abs(p)} %`
}
</script>

<template>
  <section class="view wide">
    <h1>PV-Anlage{{ snapshot ? ` · ${snapshot.plantName}` : '' }}</h1>
    <p class="intro">
      Was die Anlage heute und in den nächsten 48 Stunden erzeugen sollte — aus Strahlung, Temperatur und Wind
      des Wetterstandorts, mit Ausrichtung und Verschattung der Anlage — und was ihr Zähler bisher gemessen hat.
    </p>

    <div class="section-head toolbar">
      <label v-if="plants.length > 1" class="pick">Anlage
        <select v-model="plantId">
          <option v-for="p in plants" :key="p.id" :value="p.id">{{ p.name }}</option>
        </select>
      </label>
      <span class="stamp">Stand {{ snapshot?.generatedAt ? hourLabel(snapshot.generatedAt, timeZone) : '–' }}</span>
      <button class="btn small" type="button" :disabled="state === 'loading'" @click="load()">Neu lesen</button>
    </div>

    <p v-if="state === 'error'" class="problem" role="alert">{{ error }}</p>

    <div :class="{ refreshing: state === 'loading' && snapshot }">
      <template v-if="snapshot">
        <p class="plant">
          <span><b>{{ kwh(snapshot.peakPower) }} kWp</b></span>
          <span v-for="a in snapshot.arrays" :key="a.name">{{ a.name }}: {{ kwh(a.peakPower) }} kWp, {{ facing(a.azimuth) }} {{ whole(a.azimuth) }}°, {{ whole(a.tilt) }}° geneigt</span>
        </p>

        <div class="tiles now-tiles">
          <article class="tile stat">
            <div class="caption">Jetzt, PV-Generator</div>
            <div class="value">{{ current ? kw(current.pvPower) : '–' }} <em>kW</em></div>
            <div class="sub">{{ current ? `gemessen ${hourLabel(current.time, timeZone)}` : metered ? 'kein aktueller Messwert' : 'Anlage ohne Zähler' }}</div>
          </article>
          <article class="tile stat">
            <div class="caption">Heute erwartet</div>
            <div class="value">{{ kwh(todayDay?.energy) }} <em>kWh</em></div>
            <div class="sub">{{ todayDay?.measuredEnergy !== undefined ? `bisher gemessen ${kwh(todayDay.measuredEnergy)} kWh` : metered ? 'noch nichts gemessen' : 'ohne Zähler kein Vergleich' }}</div>
          </article>
          <article v-if="metered" class="tile stat">
            <div class="caption">Messung gegen Erwartung</div>
            <div class="value">{{ ratio ? deviation(ratio.ratio) : '–' }}</div>
            <div class="sub">{{ ratio ? `über ${ratio.hours} abgeschlossene Stunden mit Ertrag` : 'noch keine abgeschlossene Stunde gemessen' }}</div>
          </article>
          <article v-if="current" class="tile stat">
            <div class="caption">Haus, Netz, Akku</div>
            <div class="value small">{{ kw(current.loadPower) }} <em>kW Verbrauch</em></div>
            <div class="sub">
              Netz {{ current.gridPower === undefined ? '–' : current.gridPower >= 0 ? `Bezug ${kw(current.gridPower)}` : `Einspeisung ${kw(-current.gridPower)}` }} kW ·
              Akku {{ current.batteryPower === undefined ? '–' : current.batteryPower >= 0 ? `entlädt ${kw(current.batteryPower)}` : `lädt ${kw(-current.batteryPower)}` }} kW,
              {{ percent(current.stateOfCharge) }}
            </div>
          </article>
        </div>

        <h2 class="section-title">Heute und die nächsten 48 Stunden</h2>
        <PvChart v-if="snapshot.hours.length" :hours="snapshot.hours" :readings="readings?.readings ?? []" :time-zone="timeZone" :now="now" />
        <p v-else class="dim">Für den Standort der Anlage liegen noch keine Wetterwerte vor.</p>
        <p class="hint">
          Verglichen wird die Leistung des PV-Generators — bei Hybrid-Wechselrichtern die Gleichstromseite, so wie der
          Zähler sie meldet. Schraffiert: Stunden, in denen die Sonne hinter Horizont oder Wald steht; dann kommt nur
          diffuses Licht an.
        </p>

        <template v-if="profile">
          <h2 class="section-title">Verschattung in 3D</h2>
          <PvScene :key="profile.id" :profile="profile" :hours="snapshot.hours" :time-zone="timeZone" :now="now" />
        </template>

        <h2 class="section-title">Tage</h2>
        <PvDays :days="snapshot.days" :time-zone="timeZone" :today="today" />
      </template>
      <p v-else-if="state === 'loading'" class="dim">Lese die Prognose …</p>
    </div>

    <p class="note">
      {{ source.origin() }}<template v-if="source.examples"> — Beispieldaten, die Registry hat nicht geantwortet.</template>
    </p>
  </section>
</template>

<style scoped>
.toolbar { margin-top: 0; }
.pick { display: flex; align-items: center; gap: 8px; color: var(--muted); font-size: 13px; }
.pick select { font: inherit; color: var(--ink); background: var(--surface); border: 1px solid var(--line-2); border-radius: 8px; padding: 4px 8px; }
.stamp { color: var(--muted); font-size: 13px; }
.problem { color: var(--crit); }
.plant { display: flex; flex-wrap: wrap; gap: 6px 22px; color: var(--muted); font-size: 13px; margin: -4px 0 16px; }
.plant b { color: var(--ink); font-weight: 600; }
.now-tiles { grid-template-columns: repeat(auto-fill, minmax(230px, 1fr)); margin-bottom: 8px; }
.stat .caption { color: var(--muted); font-size: 12.5px; }
.stat .value { font-size: 26px; font-weight: 600; letter-spacing: -.03em; margin: 4px 0 2px; font-variant-numeric: tabular-nums; }
.stat .value.small { font-size: 20px; }
.stat .value em { font-style: normal; font-size: 13px; font-weight: 400; color: var(--muted); letter-spacing: 0; }
.stat .sub { color: var(--muted); font-size: 12.5px; }
.hint { color: var(--muted); font-size: 12.5px; margin: 10px 0 0; max-width: 820px; }
.refreshing { opacity: .55; transition: opacity .2s; }
.dim { color: var(--muted); }
</style>
