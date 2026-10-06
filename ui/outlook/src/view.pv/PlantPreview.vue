<script setup lang="ts">
/**
 * The editor's preview of what the profile says: the arrays from above with north up, laid out as
 * the 3D view lays them out, and a section along the slope with tilt, mounting height and the
 * horizon the modules face. Redrawn on every keystroke — set beside an aerial photo, a wrong
 * azimuth, a swapped landscape or a too steep tilt shows at once.
 */
import { computed, useId } from 'vue'
import type { PlantProfile } from '../contracts.js'
import { compass, layoutProblems, sideView, topView } from './preview.js'

const props = defineProps<{ profile: PlantProfile }>()
// one marker id per instance: two previews on a page must not share it
const arrowId = `pv-arrow-${useId()}`

const COLORS = ['var(--s1)', 'var(--s2)', 'var(--accent)']
const RAD = Math.PI / 180
const problems = computed(() => layoutProblems(props.profile.arrays))
const de = (v: number, digits = 1) => v.toLocaleString('de-DE', { maximumFractionDigits: digits, minimumFractionDigits: digits })

const top = computed(() => {
  const v = topView(props.profile)
  if (!v) return undefined
  const pad = 1.4
  const minX = v.bounds.minX - pad
  const maxX = v.bounds.maxX + pad
  const minY = v.bounds.minY - pad
  const maxY = v.bounds.maxY + pad
  const w = maxX - minX
  const h = maxY - minY
  // svg y grows downwards: y_svg = −north
  const pt = (p: { x: number; y: number }) => `${p.x.toFixed(3)},${(-p.y).toFixed(3)}`
  const reach = 0.45 * Math.min(w, h)
  const tip = { x: v.slope.to.x * reach, y: v.slope.to.y * reach }
  const scale = w > 14 ? 5 : w > 7 ? 2 : 1
  return {
    view: `${minX.toFixed(3)} ${(-maxY).toFixed(3)} ${w.toFixed(3)} ${h.toFixed(3)}`,
    fs: w / 26,
    modules: v.modules.map((m) => ({ points: m.corners.map(pt).join(' '), color: COLORS[m.array % COLORS.length] })),
    labels: v.arrays.map((a) => ({ x: a.centre.x, y: -a.centre.y, name: a.name })),
    arrow: { x1: 0, y1: 0, x2: tip.x, y2: -tip.y },
    arrowLabel: { x: tip.x * 1.08, y: -tip.y * 1.08 },
    north: { x: minX + w * 0.07, y: -maxY + h * 0.1, len: h * 0.12 },
    scale: { x: minX + w * 0.06, y: -minY - h * 0.07, len: scale },
    azimuth: v.azimuth,
    mixed: v.mixed,
  }
})

const side = computed(() => {
  const s = sideView(props.profile)
  if (!s || s.depth <= 0) return undefined
  const run = s.depth * Math.cos(s.tilt * RAD)
  // upper edge on the left, the slope falling to the right — towards the horizon the modules face
  const x0 = 0
  const x1 = run
  const far = run + Math.max(4, run * 0.8)
  const top = Math.max(s.high + 1.6, 3)
  const minX = -1.2
  const w = far + 0.6 - minX
  const cx = run / 2
  const fs = w / 26
  // the ray ends low enough for its label to fit above it
  const ceiling = top - s.height - 2.2 * fs
  const ray = Math.min(far - cx, ceiling / Math.max(Math.tan(s.horizon.elevation * RAD), 0.01))
  return {
    view: `${minX} ${-top} ${w} ${top + 1.6 * (w / 26)}`,
    fs,
    panel: { x1: x0, y1: -s.high, x2: x1, y2: -s.low },
    posts: [
      { x: x0, y: -s.high },
      { x: x1, y: -s.low },
    ],
    ground: { x1: minX, x2: far + 0.6 },
    centre: { x: cx, y: -s.height },
    arc: arcPath(x1, -s.low, 1.2, 180, 180 - s.tilt),
    tiltLabel: { x: x1 - 0.7, y: -s.low + 1.15 * fs },
    ends: { up: minX + 0.15, down: far + 0.45, y: 1.1 * fs },
    ray: s.horizon.elevation > 0 ? { x2: cx + ray, y2: -(s.height + ray * Math.tan(s.horizon.elevation * RAD)) } : undefined,
    s,
  }
})

/** An arc around (cx, cy) from one angle to another, degrees counter-clockwise from +x (svg y down) */
function arcPath(cx: number, cy: number, r: number, from: number, to: number): string {
  const p = (a: number) => `${(cx + r * Math.cos(a * RAD)).toFixed(3)},${(cy - r * Math.sin(a * RAD)).toFixed(3)}`
  return `M${p(from)} A${r},${r} 0 0 ${to < from ? 1 : 0} ${p(to)}`
}
</script>

<template>
  <ul v-if="problems.length" class="problems">
    <li v-for="(p, i) in problems" :key="i">{{ p }}</li>
  </ul>
  <div class="preview">
    <figure v-if="top">
      <svg :viewBox="top.view" role="img" :aria-label="`Draufsicht, Norden oben: Module fallen nach ${Math.round(top.azimuth)}° ${compass(top.azimuth)}`">
        <defs>
          <marker :id="arrowId" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="5" markerHeight="5" orient="auto-start-reverse">
            <path d="M0,0 L10,5 L0,10 z" fill="var(--ink)" />
          </marker>
        </defs>
        <polygon v-for="(m, i) in top.modules" :key="i" :points="m.points" :fill="m.color" fill-opacity="0.85" stroke="var(--surface)" :stroke-width="top.fs * 0.12" />
        <text v-for="(l, i) in top.labels" :key="'l' + i" :x="l.x" :y="l.y" :font-size="top.fs" class="label" text-anchor="middle" dominant-baseline="middle">{{ l.name }}</text>
        <line v-bind="top.arrow" stroke="var(--ink)" :stroke-width="top.fs * 0.14" :marker-end="`url(#${arrowId})`" />
        <text :x="top.arrowLabel.x" :y="top.arrowLabel.y" :font-size="top.fs * 0.9" class="label" text-anchor="middle" dominant-baseline="middle">hangab {{ Math.round(top.azimuth) }}° {{ compass(top.azimuth) }}</text>
        <g class="dim">
          <line :x1="top.north.x" :y1="top.north.y + top.north.len" :x2="top.north.x" :y2="top.north.y" stroke="currentColor" :stroke-width="top.fs * 0.12" :marker-end="`url(#${arrowId})`" />
          <text :x="top.north.x" :y="top.north.y - top.fs * 0.5" :font-size="top.fs" text-anchor="middle">N</text>
          <line :x1="top.scale.x" :y1="top.scale.y" :x2="top.scale.x + top.scale.len" :y2="top.scale.y" stroke="currentColor" :stroke-width="top.fs * 0.14" />
          <text :x="top.scale.x + top.scale.len / 2" :y="top.scale.y - top.fs * 0.5" :font-size="top.fs * 0.85" text-anchor="middle">{{ top.scale.len }} m</text>
        </g>
      </svg>
      <figcaption>Von oben, Norden oben – wie ein Luftbild.<span v-if="top.mixed"> Die Flächen sind unterschiedlich ausgerichtet; gezeichnet nach der ersten, wie in der 3D-Ansicht.</span></figcaption>
    </figure>

    <figure v-if="side">
      <svg :viewBox="side.view" role="img" :aria-label="`Schnitt in Gefällerichtung: ${de(side.s.tilt)}° Neigung, Mitte ${de(side.s.height)} m über Grund`">
        <line :x1="side.ground.x1" y1="0" :x2="side.ground.x2" y2="0" stroke="var(--line-2)" :stroke-width="side.fs * 0.12" />
        <line v-for="(p, i) in side.posts" :key="i" :x1="p.x" :y1="p.y" :x2="p.x" y2="0" stroke="var(--muted)" :stroke-width="side.fs * 0.14" />
        <line v-bind="side.panel" stroke="var(--s1)" :stroke-width="side.fs * 0.45" stroke-linecap="round" />
        <line :x1="side.panel.x2 - 1.4" :y1="side.panel.y2" :x2="side.panel.x2 + 0.2" :y2="side.panel.y2" stroke="var(--muted)" :stroke-width="side.fs * 0.06" stroke-dasharray="0.1 0.08" />
        <path :d="side.arc" fill="none" stroke="var(--ink)" :stroke-width="side.fs * 0.08" />
        <text :x="side.tiltLabel.x" :y="side.tiltLabel.y" :font-size="side.fs" class="label" text-anchor="middle">{{ de(side.s.tilt) }}°</text>
        <text :x="side.ends.up" :y="side.ends.y" :font-size="side.fs * 0.85" class="dimtext">{{ compass(side.s.horizon.azimuth + 180) }}</text>
        <text :x="side.ends.down" :y="side.ends.y" :font-size="side.fs * 0.85" class="dimtext" text-anchor="end">hangab {{ compass(side.s.horizon.azimuth) }} →</text>
        <line :x1="side.centre.x" :y1="side.centre.y" :x2="side.centre.x" y2="0" stroke="var(--muted)" :stroke-width="side.fs * 0.06" stroke-dasharray="0.12 0.1" />
        <text :x="side.centre.x + 0.15" :y="side.centre.y / 2" :font-size="side.fs * 0.9" class="dimtext" dominant-baseline="middle">{{ de(side.s.height) }} m</text>
        <template v-if="side.ray">
          <line :x1="side.centre.x" :y1="side.centre.y" :x2="side.ray.x2" :y2="side.ray.y2" stroke="var(--warn)" :stroke-width="side.fs * 0.1" stroke-dasharray="0.25 0.15" />
          <text :x="side.ray.x2" :y="side.ray.y2 - side.fs * 0.6" :font-size="side.fs * 0.85" class="label" text-anchor="end">{{ side.s.horizon.by }}: {{ de(side.s.horizon.elevation) }}°</text>
        </template>
      </svg>
      <figcaption>
        Schnitt in Gefällerichtung ({{ Math.round(side.s.horizon.azimuth) }}° {{ compass(side.s.horizon.azimuth) }}): Unterkante {{ de(side.s.low) }} m, Oberkante {{ de(side.s.high) }} m über Grund<template v-if="side.s.horizon.by">; der Horizont davor nicht maßstäblich, nur sein Winkel</template>.
      </figcaption>
    </figure>
  </div>
</template>

<style scoped>
.problems { margin: 4px 0 8px; padding-left: 18px; font-size: 12.5px; color: var(--warn); }
.preview { display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 12px 20px; margin: 4px 0 8px; }
figure { margin: 0; background: var(--sunken); border: 1px solid var(--line); border-radius: 8px; padding: 8px; }
svg { display: block; width: 100%; height: auto; max-height: 280px; color: var(--muted); }
.label { fill: var(--ink); paint-order: stroke; stroke: var(--sunken); stroke-width: 0.08; stroke-linejoin: round; }
.dim text, .dimtext { fill: var(--muted); }
figcaption { font-size: 12px; color: var(--muted); margin-top: 6px; }
</style>
