<script setup lang="ts">
/**
 * The plant in 3D: carport and modules as the profile says (orientation, tilt, mounting height,
 * module count — the layout itself is schematic), the obstacles as trees standing where the profile
 * puts the forest edge, and the sun at a chosen time with real shadows (three.js shadow map). A
 * slider runs through the day, "Abspielen" lets it run. The line under the picture says in words
 * what the picture shows: where the sun stands, whether and by what it is hidden, what the forecast
 * expects for that hour.
 */
import * as THREE from 'three'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js'
import { computed, onBeforeUnmount, onMounted, ref, watch } from 'vue'
import type { PlantProfile, PvHourValue } from '../contracts.js'
import { dayLabel, hourLabel, whole } from '../view.weather/weather.js'
import { kw } from './pv.js'
import { MODULE, beamShare, direction, ground, horizonAt, layout, leafOff, sunAt, trees } from './pv3d.js'

const props = defineProps<{ profile: PlantProfile; hours: PvHourValue[]; timeZone: string; now: Date }>()

const RAD = Math.PI / 180
const MINUTE = 60_000
const host = ref<HTMLDivElement | null>(null)

// --- time ------------------------------------------------------------------------------------

/** The local days the hours cover, with their first instant */
const days = computed(() => {
  const out: { date: string; start: number }[] = []
  for (const h of props.hours) {
    const date = new Intl.DateTimeFormat('en-CA', { timeZone: props.timeZone }).format(h.time)
    if (!out.some((d) => d.date === date)) out.push({ date, start: h.time.getTime() })
  }
  return out
})
const dayIndex = ref(0)
/** minutes since the start of the chosen day */
const minute = ref(12 * 60)
const playing = ref(false)

const day = computed(() => days.value[dayIndex.value])
const instant = computed(() => (day.value ? day.value.start + minute.value * MINUTE : props.now.getTime()))
const sun = computed(() => sunAt(props.hours, instant.value))
const share = computed(() => (sun.value && day.value ? beamShare(props.profile, sun.value.azimuth, sun.value.elevation, day.value.date) : 0))
const blocker = computed(() => (sun.value ? horizonAt(props.profile, sun.value.azimuth) : undefined))
const hour = computed(() => props.hours.find((h) => h.time.getTime() <= instant.value && instant.value < h.time.getTime() + 3_600_000))
const bare = computed(() => (day.value ? leafOff(day.value.date) : false))

function toNow(): void {
  const i = days.value.findIndex((d) => d.start <= props.now.getTime() && props.now.getTime() < d.start + 24 * 3_600_000)
  dayIndex.value = Math.max(0, i)
  if (day.value) minute.value = Math.round((props.now.getTime() - day.value.start) / MINUTE / 10) * 10
}

const status = computed(() => {
  const s = sun.value
  if (!s || s.elevation <= 0) return 'Die Sonne ist untergegangen.'
  const where = `Sonne ${whole(s.elevation)}° hoch, Richtung ${whole(s.azimuth)}°`
  const b = blocker.value
  if (share.value >= 1) return `${where} — frei${b && b.elevation > 0 ? `, Hindernis dort bis ${whole(b.elevation)}°` : ''}.`
  if (share.value > 0) return `${where} — hinter ${b?.by ?? 'einem Hindernis'} (${whole(b?.elevation)}°), unbelaubt kommen etwa ${whole(share.value * 100)} % durch.`
  return `${where} — verdeckt durch ${b?.by ?? 'den Horizont'} (bis ${whole(b?.elevation)}°), nur diffuses Licht.`
})

// --- scene -----------------------------------------------------------------------------------

let renderer: THREE.WebGLRenderer | undefined
let scene: THREE.Scene
let camera: THREE.PerspectiveCamera
let controls: OrbitControls
let light: THREE.DirectionalLight
let ambient: THREE.HemisphereLight
let sunBall: THREE.Mesh
let crowns: THREE.InstancedMesh | undefined
let resize: ResizeObserver | undefined
let frame = 0
let timer: ReturnType<typeof setInterval> | undefined

function css(name: string, fallback: string): THREE.Color {
  const v = getComputedStyle(document.documentElement).getPropertyValue(name).trim()
  return new THREE.Color(v || fallback)
}

const lightTheme = () => document.documentElement.dataset.theme === 'light'
/** Grass, a little darker in the dark scheme so that the shadows still read */
const GRASS = () => new THREE.Color(lightTheme() ? '#86a86e' : '#4a6a3e')

function requestRender(): void {
  if (frame || !renderer) return
  frame = requestAnimationFrame(() => {
    frame = 0
    controls.update()
    renderer!.render(scene, camera)
  })
}

function label(text: string): THREE.Sprite {
  const c = document.createElement('canvas')
  c.width = 64
  c.height = 64
  const g = c.getContext('2d')!
  g.fillStyle = lightTheme() ? '#1a2418' : '#e6ece8'
  g.font = '600 40px sans-serif'
  g.textAlign = 'center'
  g.textBaseline = 'middle'
  g.fillText(text, 32, 34)
  const sprite = new THREE.Sprite(new THREE.SpriteMaterial({ map: new THREE.CanvasTexture(c), depthWrite: false }))
  sprite.scale.set(3.2, 3.2, 1)
  return sprite
}

function build(el: HTMLDivElement): void {
  renderer = new THREE.WebGLRenderer({ antialias: true, preserveDrawingBuffer: true })
  renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2))
  renderer.shadowMap.enabled = true
  renderer.shadowMap.type = THREE.PCFSoftShadowMap
  el.appendChild(renderer.domElement)

  scene = new THREE.Scene()
  scene.background = css('--sunken', '#101413')
  scene.fog = new THREE.Fog(scene.background, 260, 520)

  camera = new THREE.PerspectiveCamera(42, 1, 0.5, 2000)
  // from the north-east, high enough to see the carport, its shadow and the forest edge at once
  const eye = direction(40, 36)
  camera.position.set(eye.x * 62, eye.y * 62, eye.z * 62)
  controls = new OrbitControls(camera, renderer.domElement)
  controls.target.set(-4, 1, 4)
  controls.maxPolarAngle = Math.PI / 2 - 0.04
  controls.minDistance = 6
  controls.maxDistance = 320
  controls.addEventListener('change', requestRender)

  ambient = new THREE.HemisphereLight(0xdfe8ff, 0x3a3428, 0.9)
  scene.add(ambient)
  light = new THREE.DirectionalLight(0xfff3dc, 2.4)
  light.castShadow = true
  light.shadow.mapSize.set(4096, 4096)
  const sc = light.shadow.camera
  sc.left = -170
  sc.right = 170
  sc.top = 170
  sc.bottom = -170
  sc.near = 1
  sc.far = 800
  light.shadow.bias = -0.0004
  light.shadow.normalBias = 0.04
  scene.add(light, light.target)

  // ground and compass
  const floor = new THREE.Mesh(
    new THREE.CircleGeometry(300, 96),
    new THREE.MeshLambertMaterial({ color: GRASS() }),
  )
  floor.rotation.x = -Math.PI / 2
  floor.receiveShadow = true
  scene.add(floor)
  for (const [t, az] of [['N', 0], ['O', 90], ['S', 180], ['W', 270]] as const) {
    const p = ground(az, 20)
    const s = label(t)
    s.position.set(p.x, 0.8, p.z)
    scene.add(s)
  }

  buildPlant()
  buildForest()

  // the sun as a disc in the sky, and today's path
  sunBall = new THREE.Mesh(new THREE.SphereGeometry(6, 24, 16), new THREE.MeshBasicMaterial({ color: css('--s2', '#be8629') }))
  scene.add(sunBall)
  buildSunPath()

  resize = new ResizeObserver(() => {
    const w = el.clientWidth
    const h = el.clientHeight
    renderer!.setSize(w, h, false)
    renderer!.domElement.style.width = '100%'
    renderer!.domElement.style.height = '100%'
    camera.aspect = w / Math.max(1, h)
    camera.updateProjectionMatrix()
    requestRender()
  })
  resize.observe(el)
  placeSun()
}

function buildPlant(): void {
  const { modules, width, depth } = layout(props.profile.arrays)
  const first = props.profile.arrays[0]
  const azimuth = first?.azimuth ?? 180
  const tilt = first?.tilt ?? 0
  const height = props.profile.mountingHeight || 2
  const panel = new THREE.MeshStandardMaterial({ color: 0x1b2a44, metalness: 0.35, roughness: 0.35 })
  const steel = new THREE.MeshStandardMaterial({ color: 0x9aa3a8, metalness: 0.6, roughness: 0.5 })
  const field = new THREE.Group()
  field.position.set(0, height, 0)
  field.rotation.y = Math.PI - azimuth * RAD
  scene.add(field)
  const plane = new THREE.Group()
  plane.rotation.x = tilt * RAD
  field.add(plane)
  const box = new THREE.BoxGeometry(MODULE.width, 0.04, MODULE.length)
  for (const m of modules) {
    const mesh = new THREE.Mesh(box, panel)
    mesh.position.set(m.u, 0, m.v)
    mesh.castShadow = true
    mesh.receiveShadow = true
    plane.add(mesh)
  }
  // a frame under the modules and four posts down to the ground
  const frame = new THREE.Mesh(new THREE.BoxGeometry(width + 0.3, 0.12, depth + 0.3), steel)
  frame.position.y = -0.1
  frame.castShadow = true
  plane.add(frame)
  for (const [u, v] of [[-1, -1], [1, -1], [-1, 1], [1, 1]]) {
    const local = new THREE.Vector3((u * (width + 0.1)) / 2, -0.16, (v * (depth + 0.1)) / 2)
    plane.updateMatrixWorld(true)
    const world = plane.localToWorld(local.clone())
    const post = new THREE.Mesh(new THREE.CylinderGeometry(0.07, 0.07, world.y, 10), steel)
    post.position.set(world.x, world.y / 2, world.z)
    post.castShadow = true
    scene.add(post)
  }
}

function buildForest(): void {
  const list = trees(props.profile)
  if (list.length === 0) return
  const trunk = new THREE.InstancedMesh(
    new THREE.CylinderGeometry(0.25, 0.35, 1, 8),
    new THREE.MeshLambertMaterial({ color: 0x5a4632 }),
    list.length,
  )
  crowns = new THREE.InstancedMesh(new THREE.IcosahedronGeometry(1, 1), new THREE.MeshLambertMaterial({ color: 0x3f6b35, transparent: true }), list.length)
  const m = new THREE.Matrix4()
  list.forEach((t, i) => {
    const trunkHeight = t.height - t.crown * 1.4
    m.compose(new THREE.Vector3(t.x, trunkHeight / 2, t.z), new THREE.Quaternion(), new THREE.Vector3(1, trunkHeight, 1))
    trunk.setMatrixAt(i, m)
    m.compose(new THREE.Vector3(t.x, t.height - t.crown * 1.2, t.z), new THREE.Quaternion(), new THREE.Vector3(t.crown, t.crown * 1.25, t.crown))
    crowns!.setMatrixAt(i, m)
  })
  trunk.castShadow = true
  crowns.castShadow = true
  crowns.receiveShadow = true
  scene.add(trunk, crowns)
}

let path: THREE.Line | undefined
function buildSunPath(): void {
  if (path) scene.remove(path)
  if (!day.value) return
  const pts: THREE.Vector3[] = []
  for (let t = day.value.start; t < day.value.start + 24 * 3_600_000; t += 10 * MINUTE) {
    const s = sunAt(props.hours, t)
    if (!s || s.elevation <= 0) continue
    const d = direction(s.azimuth, s.elevation)
    pts.push(new THREE.Vector3(d.x * 240, d.y * 240, d.z * 240))
  }
  if (pts.length < 2) return
  path = new THREE.Line(
    new THREE.BufferGeometry().setFromPoints(pts),
    new THREE.LineDashedMaterial({ color: css('--s2', '#be8629'), dashSize: 6, gapSize: 5, transparent: true, opacity: 0.7 }),
  )
  path.computeLineDistances()
  scene.add(path)
}

function placeSun(): void {
  if (!renderer) return
  const s = sun.value
  const up = s !== undefined && s.elevation > 0
  if (up) {
    const d = direction(s.azimuth, s.elevation)
    light.position.set(d.x * 400, d.y * 400, d.z * 400)
    sunBall.position.set(d.x * 240, d.y * 240, d.z * 240)
  }
  light.visible = up
  sunBall.visible = up
  ambient.intensity = up ? 0.9 : 0.35
  if (crowns) {
    const mat = crowns.material as THREE.MeshLambertMaterial
    mat.color.set(bare.value ? 0x7a6a55 : 0x3f6b35)
    mat.opacity = bare.value ? 0.55 : 1
  }
  requestRender()
}

watch([instant, bare], placeSun)
watch(dayIndex, () => {
  buildSunPath()
  placeSun()
})
watch(playing, (on) => {
  clearInterval(timer)
  if (!on) return
  timer = setInterval(() => {
    minute.value = (minute.value + 10) % (24 * 60)
  }, 120)
})

onMounted(() => {
  toNow()
  if (host.value) build(host.value)
})
onBeforeUnmount(() => {
  clearInterval(timer)
  cancelAnimationFrame(frame)
  resize?.disconnect()
  controls?.dispose()
  renderer?.dispose()
  renderer?.domElement.remove()
  renderer = undefined
})

const clockText = computed(() => hourLabel(new Date(instant.value), props.timeZone))
</script>

<template>
  <div class="scene-frame">
    <div ref="host" class="scene" role="img" :aria-label="`Anlage und Verschattung, ${clockText}: ${status}`" />
    <div class="controls">
      <label class="pick">Tag
        <select v-model.number="dayIndex">
          <option v-for="(d, i) in days" :key="d.date" :value="i">{{ dayLabel(d.date).weekday }}, {{ dayLabel(d.date).date }}</option>
        </select>
      </label>
      <label class="slider">
        <span class="time">{{ clockText }}</span>
        <input v-model.number="minute" type="range" min="0" max="1430" step="10" aria-label="Uhrzeit" />
      </label>
      <button class="btn small" type="button" @click="playing = !playing">{{ playing ? 'Anhalten' : 'Abspielen' }}</button>
      <button class="btn small" type="button" @click="toNow(); playing = false">Jetzt</button>
    </div>
    <p class="status" role="status">
      {{ status }}
      <template v-if="hour && sun && sun.elevation > 0">
        Erwartet {{ kw(hour.dcPower) }} kW<template v-if="hour.measuredPower !== undefined">, gemessen {{ kw(hour.measuredPower) }} kW</template> in dieser Stunde.
      </template>
      <template v-if="bare"> Der Wald ist unbelaubt.</template>
    </p>
    <p class="hint">Ziehen dreht, Mausrad zoomt. Module schematisch: Anzahl, Ausrichtung, Neigung und Höhe aus dem Profil, die Anordnung nicht. Bäume stehen ab der Waldkante des Profils.</p>
  </div>
</template>

<style scoped>
.scene-frame { background: var(--surface); border: 1px solid var(--line); border-radius: 12px; padding: 0 0 12px; overflow: hidden; }
.scene { height: 460px; width: 100%; cursor: grab; }
.scene:active { cursor: grabbing; }
.controls { display: flex; flex-wrap: wrap; align-items: center; gap: 10px 18px; padding: 12px 14px 0; }
.pick { display: flex; align-items: center; gap: 8px; color: var(--muted); font-size: 13px; }
.pick select { font: inherit; color: var(--ink); background: var(--surface); border: 1px solid var(--line-2); border-radius: 8px; padding: 4px 8px; }
.slider { display: flex; align-items: center; gap: 10px; flex: 1 1 280px; }
.slider input { flex: 1; accent-color: var(--s2); }
.time { font-variant-numeric: tabular-nums; font-weight: 600; min-width: 44px; }
.status { margin: 10px 14px 0; font-size: 13.5px; color: var(--ink); }
.hint { margin: 6px 14px 0; font-size: 12px; color: var(--muted); }
</style>
