<script setup lang="ts">
/**
 * The demo host's frame: two views, weather and PV, switched by tabs and remembered in the URL
 * (`?view=pv`). In xdp-ui the shell's navigation does this; each view is an XDP_VIEW of its own.
 */
import { ref } from 'vue'
import type { PvForecast, WeatherOutlook } from './contracts.js'
import PvPage from './view.pv/PvPage.vue'
import WeatherPage from './view.weather/WeatherPage.vue'

const props = defineProps<{ outlook: WeatherOutlook; outlookFallback: WeatherOutlook; pv: PvForecast; pvFallback: PvForecast }>()

type View = 'weather' | 'pv'
const initial = new URLSearchParams(window.location.search).get('view')
const view = ref<View>(initial === 'pv' ? 'pv' : 'weather')

function show(v: View): void {
  view.value = v
  const url = new URL(window.location.href)
  if (v === 'pv') url.searchParams.set('view', 'pv')
  else url.searchParams.delete('view')
  window.history.replaceState(null, '', url)
}
</script>

<template>
  <nav class="tabs" aria-label="Ansichten">
    <button type="button" :aria-current="view === 'weather' ? 'page' : undefined" @click="show('weather')">Wetter</button>
    <button type="button" :aria-current="view === 'pv' ? 'page' : undefined" @click="show('pv')">PV-Anlage</button>
  </nav>
  <WeatherPage v-if="view === 'weather'" :outlook="props.outlook" :fallback="props.outlookFallback" />
  <PvPage v-else :pv="props.pv" :fallback="props.pvFallback" />
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
@media (max-width: 760px) { .tabs { padding: 14px 16px 0; } }
</style>
