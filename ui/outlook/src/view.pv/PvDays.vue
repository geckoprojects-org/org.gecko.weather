<script setup lang="ts">
/** The days as tiles: expected energy, yield per kWp, the best hour — and, for today, what was measured. */
import type { PvDayValue } from '../contracts.js'
import { clock, dayLabel } from '../view.weather/weather.js'
import { kw, kwh } from './pv.js'

defineProps<{ days: PvDayValue[]; timeZone: string; today: string }>()
</script>

<template>
  <div class="tiles">
    <article v-for="d in days" :key="d.date" class="tile day">
      <div class="head">
        <div>
          <h3>{{ d.date === today ? 'Heute' : dayLabel(d.date).weekday }}</h3>
          <div class="role">{{ dayLabel(d.date).date }}{{ d.source ? ` · ${d.source}` : '' }}</div>
        </div>
        <div class="energy" :aria-label="`erwartet ${kwh(d.energy)} Kilowattstunden`">
          <span class="big">{{ kwh(d.energy) }}</span> <em>kWh</em>
        </div>
      </div>
      <div class="metrics">
        <div class="metric">
          <div class="value">{{ kwh(d.specificYield) }} <em>kWh/kWp</em></div>
          <div class="caption">spezifischer Ertrag</div>
        </div>
        <div class="metric">
          <div class="value">{{ kw(d.peakPower) }} <em>kW</em></div>
          <div class="caption">stärkste Stunde{{ d.peakTime ? ` ab ${clock(d.peakTime, timeZone)}` : '' }}</div>
        </div>
        <div v-if="d.measuredEnergy !== undefined" class="metric">
          <div class="value">{{ kwh(d.measuredEnergy) }} <em>kWh</em></div>
          <div class="caption">bisher gemessen, PV-Generator</div>
        </div>
      </div>
      <p v-if="d.hoursCovered < 24" class="foot-note">Nur {{ d.hoursCovered }} von 24 Stunden mit Wetterwerten.</p>
    </article>
  </div>
</template>

<style scoped>
.day { --status: var(--s2); }
.energy { margin-left: auto; text-align: right; font-variant-numeric: tabular-nums; color: var(--muted); }
.energy .big { font-size: 26px; font-weight: 600; letter-spacing: -.03em; color: var(--ink); }
.energy em { font-style: normal; }
.foot-note { margin: 10px 0 0; font-size: 12px; color: var(--muted); }
</style>
