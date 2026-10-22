<script setup lang="ts">
/**
 * Where a value comes from, as a small chip: "Station" for the own weather station, "DWD" for
 * the weather service's products, "berechnet" for what the backend computes. The product is the
 * tooltip, so the chip stays one word.
 */
import { sourceKind, sourceLabel } from './weather.js'

const props = defineProps<{ providerId: string; productId?: string; location?: string }>()
const title = () => [props.productId, props.location].filter(Boolean).join(' · ')
</script>

<template>
  <span class="src" :class="sourceKind(providerId)" :title="title()">{{ sourceLabel(providerId) }}</span>
</template>

<style scoped>
.src {
  display: inline-block;
  font-size: 11px;
  font-weight: 600;
  letter-spacing: .02em;
  line-height: 1;
  padding: 4px 8px;
  border-radius: 10px;
  border: 1px solid var(--line-2);
  color: var(--ink-2);
  background: var(--surface);
  white-space: nowrap;
  vertical-align: middle;
}
.src.station { color: var(--accent); border-color: transparent; background: var(--accent-2); }
.src.dwd { color: var(--ink-2); }
.src.computed { color: var(--muted); border-style: dashed; }
.src.example { color: var(--warn); border-color: var(--warn); }
</style>
