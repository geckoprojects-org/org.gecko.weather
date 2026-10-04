<script setup lang="ts">
/**
 * The demo host's frame: the weather view and one tab per PV plant, remembered in the URL
 * (`?plant=<id>`), plus a tab that creates a plant. The host decides once whether the registry
 * answers and falls back to examples for all tabs together. In xdp-ui the shell's navigation does
 * this; each view is an XDP_VIEW of its own.
 */
import { onMounted, ref } from 'vue'
import type { PlantInfo, PlantProfile, PvForecast, SiteInfo, WeatherOutlook } from './contracts.js'
import PlantEditor from './view.pv/PlantEditor.vue'
import PvPage from './view.pv/PvPage.vue'
import WeatherPage from './view.weather/WeatherPage.vue'

const props = defineProps<{ outlook: WeatherOutlook; outlookFallback: WeatherOutlook; pv: PvForecast; pvFallback: PvForecast }>()

const params = new URLSearchParams(window.location.search)
/** 'weather', a plant id, or 'new' */
const view = ref<string>(params.get('plant') ?? (params.get('view') === 'pv' ? 'pv' : 'weather'))
const pv = ref<PvForecast>(props.pv)
const plants = ref<PlantInfo[]>([])
const sites = ref<SiteInfo[]>([])
const saving = ref(false)
const saveError = ref('')

const EMPTY: PlantProfile = { id: '', name: '', siteId: '', mountingHeight: 2, albedo: 0.2, systemLosses: 10, arrays: [], inverters: [], obstacles: [], horizon: [] }

async function loadPlants(): Promise<void> {
  try {
    plants.value = await pv.value.plants()
  } catch (e) {
    if (pv.value === props.pvFallback) throw e
    console.warn('pv: Registry nicht erreichbar, zeige Beispieldaten', e)
    pv.value = props.pvFallback
    plants.value = await pv.value.plants()
  }
  // 'pv' from an old link means the first plant
  if (view.value === 'pv' || (view.value !== 'weather' && view.value !== 'new' && !plants.value.some((p) => p.id === view.value))) {
    view.value = plants.value[0]?.id ?? 'weather'
  }
  sites.value = await props.outlook.sites().catch(() => props.outlookFallback.sites())
}

onMounted(() => void loadPlants())

function show(v: string): void {
  view.value = v
  saveError.value = ''
  const url = new URL(window.location.href)
  url.searchParams.delete('view')
  if (v === 'weather') url.searchParams.delete('plant')
  else url.searchParams.set('plant', v)
  window.history.replaceState(null, '', url)
}

async function create(p: PlantProfile): Promise<void> {
  saving.value = true
  saveError.value = ''
  try {
    const stored = await pv.value.savePlant(p)
    await loadPlants()
    show(stored.id)
  } catch (e) {
    saveError.value = e instanceof Error ? e.message : String(e)
  } finally {
    saving.value = false
  }
}

/** A renamed plant changes its tab */
function saved(): void {
  void loadPlants()
}
</script>

<template>
  <nav class="tabs" aria-label="Ansichten">
    <button type="button" :aria-current="view === 'weather' ? 'page' : undefined" @click="show('weather')">Wetter</button>
    <button v-for="p in plants" :key="p.id" type="button" :aria-current="view === p.id ? 'page' : undefined" @click="show(p.id)">{{ p.name }}</button>
    <button type="button" class="add" :aria-current="view === 'new' ? 'page' : undefined" @click="show('new')" aria-label="Neue Anlage">+ Anlage</button>
  </nav>
  <WeatherPage v-if="view === 'weather'" :outlook="props.outlook" :fallback="props.outlookFallback" />
  <section v-else-if="view === 'new'" class="view wide">
    <h1>Neue PV-Anlage</h1>
    <p class="intro">Ein Profil sagt, was wo installiert ist und was es verschattet. Es wird als Datei im Anlagenordner der Laufzeit abgelegt.</p>
    <PlantEditor :profile="EMPTY" :sites="sites" is-new :busy="saving" :error="saveError" @save="create" @cancel="show(plants[0]?.id ?? 'weather')" />
    <p class="note">{{ pv.origin() }}<template v-if="pv.examples"> — Beispieldaten, Änderungen gelten nur für diese Sitzung.</template></p>
  </section>
  <PvPage v-else :key="view" :source="pv" :plant-id="view" :sites="sites" @saved="saved" />
</template>

<style scoped>
.tabs { display: flex; gap: 4px; padding: 18px 40px 0; }
.tabs button {
  font: inherit;
  font-size: 13.5px;
  color: var(--muted);
  background: none;
  border: 1px solid transparent;
  border-radius: 8px;
  padding: 6px 12px;
  cursor: pointer;
}
.tabs button:hover { color: var(--ink); }
.tabs button[aria-current='page'] { color: var(--ink); background: var(--surface); border-color: var(--line-2); }
.tabs .add { color: var(--muted); margin-left: 4px; }
@media (max-width: 760px) { .tabs { padding: 14px 16px 0; } }
</style>
