<script setup lang="ts">
/**
 * The demo host's frame: the weather view. The host decides once whether the registry answers and
 * falls back to examples. In xdp-ui the shell's navigation does this; the view is an XDP_VIEW of
 * its own. The house's views (PV, inverter, station) live in the HANS workspace, not here.
 */
import type { WeatherOutlook } from './contracts.js'
import WeatherPage from './view.weather/WeatherPage.vue'

const props = defineProps<{ outlook: WeatherOutlook; outlookFallback: WeatherOutlook }>()
</script>

<template>
  <nav class="tabs" aria-label="Ansichten">
    <button type="button" aria-current="page">Wetter</button>
  </nav>
  <WeatherPage :outlook="props.outlook" :fallback="props.outlookFallback" />
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
.tabs button[aria-current='page'] { color: var(--ink); background: var(--surface); border-color: var(--line-2); }
@media (max-width: 760px) { .tabs { padding: 14px 16px 0; } }
</style>
