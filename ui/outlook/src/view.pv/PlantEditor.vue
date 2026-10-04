<script setup lang="ts">
/**
 * The plant's profile as a form: the plant itself, its arrays, inverters and obstacles. Edits a
 * copy and hands the result up on save; the page stores it through the service. Numbers are kept
 * as numbers — an empty field is "unset", so that the service's defaults apply.
 */
import { computed, reactive, toRaw } from 'vue'
import type { Mounting, PlantProfile, SiteInfo } from '../contracts.js'

const props = defineProps<{ profile: PlantProfile; sites: SiteInfo[]; isNew?: boolean; busy?: boolean; error?: string }>()
const emit = defineEmits<{ save: [profile: PlantProfile]; cancel: [] }>()

// toRaw: a reactive proxy cannot be structured-cloned
const draft = reactive<PlantProfile>(structuredClone(toRaw(props.profile)))

const MOUNTINGS: { value: Mounting; label: string }[] = [
  { value: 'ROOF_MOUNTED', label: 'Aufdach, hinterlüftet' },
  { value: 'ROOF_INTEGRATED', label: 'Indach' },
  { value: 'OPEN_RACK', label: 'Freiaufstellung' },
]

const idOk = computed(() => /^[A-Za-z0-9][A-Za-z0-9._-]*$/.test(draft.id))
const peak = computed(() => draft.arrays.reduce((a, r) => a + (r.peakPower ?? 0), 0))

function addArray(): void {
  draft.arrays.push({ name: `Fläche ${draft.arrays.length + 1}`, azimuth: 180, tilt: 30, peakPower: 1, moduleCount: undefined, temperatureCoefficient: -0.37, mounting: 'ROOF_MOUNTED', inverter: draft.inverters.length ? 0 : undefined })
}
function addInverter(): void {
  draft.inverters.push({ name: `Wechselrichter ${draft.inverters.length + 1}`, acPower: undefined, efficiency: 0.96 })
}
function addObstacle(): void {
  draft.obstacles.push({ name: `Hindernis ${draft.obstacles.length + 1}`, azimuthFrom: 200, azimuthTo: 240, distance: 30, height: 15, leafOffTransmittance: 0 })
}
function removeInverter(i: number): void {
  draft.inverters.splice(i, 1)
  for (const a of draft.arrays) {
    if (a.inverter === i) a.inverter = undefined
    else if (a.inverter !== undefined && a.inverter > i) a.inverter -= 1
  }
}

/** v-model.number leaves '' for an emptied field; the contract wants undefined */
function clean(): PlantProfile {
  const p = structuredClone(toRaw(draft)) as PlantProfile
  const n = (v: unknown) => (v === '' || v === null || (typeof v === 'number' && Number.isNaN(v)) ? undefined : (v as number))
  p.latitude = n(p.latitude)
  p.longitude = n(p.longitude)
  p.albedo = n(p.albedo)
  p.systemLosses = n(p.systemLosses)
  for (const a of p.arrays) {
    a.moduleCount = n(a.moduleCount)
    a.peakPower = n(a.peakPower)
    a.azimuth = n(a.azimuth)
    a.tilt = n(a.tilt)
    a.temperatureCoefficient = n(a.temperatureCoefficient)
    a.inverter = n(a.inverter)
  }
  for (const i of p.inverters) {
    i.acPower = n(i.acPower)
    i.efficiency = n(i.efficiency)
  }
  return p
}
</script>

<template>
  <form class="editor" @submit.prevent="emit('save', clean())">
    <h3>{{ isNew ? 'Neue Anlage' : `Anlage bearbeiten · ${profile.name}` }}</h3>

    <fieldset>
      <legend>Anlage</legend>
      <div class="grid">
        <label>Kennung <input v-model.trim="draft.id" :disabled="!isNew" required pattern="[A-Za-z0-9][A-Za-z0-9._\-]*" /><small>Dateiname: Buchstaben, Ziffern, Punkt, Strich</small></label>
        <label>Name <input v-model.trim="draft.name" required /></label>
        <label>Wetterstandort
          <select v-model="draft.siteId" required>
            <option v-for="s in sites" :key="s.id" :value="s.id">{{ s.name }}</option>
            <option v-if="draft.siteId && !sites.some((s) => s.id === draft.siteId)" :value="draft.siteId">{{ draft.siteId }}</option>
          </select>
          <small>liefert Strahlung, Temperatur und Wind</small>
        </label>
        <label>Montagehöhe <span class="unit"><input v-model.number="draft.mountingHeight" type="number" step="0.1" min="0" /> m</span><small>Module über Grund, für den Winkel zu Hindernissen</small></label>
        <label>Breite <span class="unit"><input v-model.number="draft.latitude" type="number" step="0.000001" min="-90" max="90" placeholder="vom Standort" /> °</span></label>
        <label>Länge <span class="unit"><input v-model.number="draft.longitude" type="number" step="0.000001" min="-180" max="180" placeholder="vom Standort" /> °</span></label>
        <label>Albedo <input v-model.number="draft.albedo" type="number" step="0.05" min="0" max="1" /><small>0,2 Gras, bis 0,8 Neuschnee</small></label>
        <label>Systemverluste <span class="unit"><input v-model.number="draft.systemLosses" type="number" step="0.5" min="0" max="50" /> %</span><small>Leitungen, Verschmutzung, Mismatch</small></label>
      </div>
    </fieldset>

    <fieldset>
      <legend>Modulflächen · {{ peak.toLocaleString('de-DE', { maximumFractionDigits: 2 }) }} kWp</legend>
      <table class="rows">
        <thead><tr><th>Name</th><th>Module</th><th>kWp</th><th>Ausrichtung °</th><th>Neigung °</th><th>Temp.-Koeff. %/K</th><th>Montage</th><th>Wechselrichter</th><th></th></tr></thead>
        <tbody>
          <tr v-for="(a, i) in draft.arrays" :key="i">
            <td><input v-model.trim="a.name" required /></td>
            <td><input v-model.number="a.moduleCount" type="number" min="1" step="1" class="n" /></td>
            <td><input v-model.number="a.peakPower" type="number" min="0" step="0.01" class="n" required /></td>
            <td><input v-model.number="a.azimuth" type="number" min="0" max="360" step="1" class="n" required /></td>
            <td><input v-model.number="a.tilt" type="number" min="0" max="90" step="1" class="n" required /></td>
            <td><input v-model.number="a.temperatureCoefficient" type="number" step="0.01" class="n" /></td>
            <td><select v-model="a.mounting"><option v-for="m in MOUNTINGS" :key="m.value" :value="m.value">{{ m.label }}</option></select></td>
            <td>
              <select v-model="a.inverter">
                <option :value="undefined">Standard, 96 %</option>
                <option v-for="(inv, k) in draft.inverters" :key="k" :value="k">{{ inv.name }}</option>
              </select>
            </td>
            <td><button type="button" class="btn small" aria-label="Fläche entfernen" @click="draft.arrays.splice(i, 1)">–</button></td>
          </tr>
        </tbody>
      </table>
      <button type="button" class="btn small" @click="addArray">Fläche hinzufügen</button>
    </fieldset>

    <fieldset>
      <legend>Wechselrichter</legend>
      <table class="rows">
        <thead><tr><th>Name</th><th>AC-Leistung kW</th><th>Wirkungsgrad</th><th></th></tr></thead>
        <tbody>
          <tr v-for="(inv, i) in draft.inverters" :key="i">
            <td><input v-model.trim="inv.name" required /></td>
            <td><input v-model.number="inv.acPower" type="number" min="0" step="0.1" class="n" placeholder="ohne Grenze" /></td>
            <td><input v-model.number="inv.efficiency" type="number" min="0.5" max="1" step="0.01" class="n" /></td>
            <td><button type="button" class="btn small" aria-label="Wechselrichter entfernen" @click="removeInverter(i)">–</button></td>
          </tr>
        </tbody>
      </table>
      <button type="button" class="btn small" @click="addInverter">Wechselrichter hinzufügen</button>
    </fieldset>

    <fieldset>
      <legend>Hindernisse</legend>
      <p class="help">Wald, Haus, Hügel: von wo bis wo am Horizont (im Uhrzeigersinn ab Nord, Süd = 180), wie weit weg, wie hoch. Laubwald lässt unbelaubt einen Teil der Sonne durch, 0 für ganzjährig dicht.</p>
      <table class="rows">
        <thead><tr><th>Name</th><th>von °</th><th>bis °</th><th>Abstand m</th><th>Höhe m</th><th>Durchlass unbelaubt</th><th></th></tr></thead>
        <tbody>
          <tr v-for="(b, i) in draft.obstacles" :key="i">
            <td><input v-model.trim="b.name" required /></td>
            <td><input v-model.number="b.azimuthFrom" type="number" min="0" max="360" step="1" class="n" required /></td>
            <td><input v-model.number="b.azimuthTo" type="number" min="0" max="360" step="1" class="n" required /></td>
            <td><input v-model.number="b.distance" type="number" min="1" step="1" class="n" required /></td>
            <td><input v-model.number="b.height" type="number" min="0" step="0.5" class="n" required /></td>
            <td><input v-model.number="b.leafOffTransmittance" type="number" min="0" max="1" step="0.05" class="n" /></td>
            <td><button type="button" class="btn small" aria-label="Hindernis entfernen" @click="draft.obstacles.splice(i, 1)">–</button></td>
          </tr>
        </tbody>
      </table>
      <button type="button" class="btn small" @click="addObstacle">Hindernis hinzufügen</button>
      <p v-if="draft.horizon.length" class="help">Dazu eine gemessene Horizontlinie mit {{ draft.horizon.length }} Punkten, die hier nicht bearbeitet wird und erhalten bleibt.</p>
    </fieldset>

    <p v-if="error" class="problem" role="alert">{{ error }}</p>
    <div class="actions">
      <button type="submit" class="btn" :disabled="busy || !idOk || draft.arrays.length === 0">{{ busy ? 'Speichere …' : 'Speichern' }}</button>
      <button type="button" class="btn small" :disabled="busy" @click="emit('cancel')">Abbrechen</button>
      <span v-if="draft.arrays.length === 0" class="help">Mindestens eine Modulfläche.</span>
    </div>
  </form>
</template>

<style scoped>
.editor { background: var(--surface); border: 1px solid var(--line); border-radius: 12px; padding: 16px 18px 14px; margin: 0 0 18px; }
.editor h3 { margin: 0 0 8px; font-size: 15px; }
fieldset { border: 0; border-top: 1px solid var(--line); padding: 12px 0 4px; margin: 8px 0 0; }
legend { padding: 0 8px 0 0; font-size: 13px; font-weight: 600; color: var(--ink); }
.grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(220px, 1fr)); gap: 10px 18px; }
label { display: flex; flex-direction: column; gap: 4px; font-size: 12.5px; color: var(--muted); }
label small { font-size: 11.5px; color: var(--muted); }
input, select { font: inherit; font-size: 13px; color: var(--ink); background: var(--sunken); border: 1px solid var(--line-2); border-radius: 6px; padding: 5px 8px; min-width: 0; }
input:disabled { color: var(--muted); }
.unit { display: flex; align-items: center; gap: 6px; }
.unit input { flex: 1; }
.rows { width: 100%; border-collapse: collapse; margin: 4px 0 8px; font-size: 12.5px; }
.rows th { text-align: left; font-weight: 500; color: var(--muted); padding: 2px 6px 6px 0; white-space: nowrap; }
.rows td { padding: 2px 6px 2px 0; }
.rows input, .rows select { width: 100%; }
.rows input.n { width: 84px; text-align: right; font-variant-numeric: tabular-nums; }
.help { font-size: 12px; color: var(--muted); margin: 4px 0 8px; max-width: 820px; }
.problem { color: var(--crit); font-size: 13px; }
.actions { display: flex; align-items: center; gap: 12px; margin-top: 14px; }
@media (max-width: 760px) { .rows { display: block; overflow-x: auto; } }
</style>
