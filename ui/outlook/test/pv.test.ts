/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
import { BasicResourceSet, URI, XMIResourceFactory, type EObject, type XMIResource } from '@emfts/core'
import { describe, expect, it } from 'vitest'
import { rootOf } from '../src/emf.js'
import { registerPvPackage, toPlants, toPvSnapshot, toReadings } from '../src/pv.ddsr/model.js'
import { SamplePvForecast } from '../src/pv.ddsr/SamplePvForecast.js'
import { facing, kw, latest, measuredRatio } from '../src/view.pv/pv.js'

/** As Java EMF writes it: a power of 0 (the default) and false flags are absent */
const OUTLOOK = `<?xml version="1.0" encoding="UTF-8"?>
<pv:PvOutlook xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pv="https://geckoprojects.org/weather/pv/1.0"
    plantId="demo" plantName="Garage" siteId="home" timeZone="Europe/Berlin" peakPower="6.88" generatedAt="2026-10-04T09:30:00.000+0000">
  <hours time="2026-10-04T05:00:00.000+0000" sunElevation="-2.0" sunAzimuth="95.0"/>
  <hours time="2026-10-04T08:00:00.000+0000" power="1.27" dcPower="1.3" planeIrradiance="209.0" globalRadiation="223.0" cellTemperature="18.0" sunElevation="26.0" sunAzimuth="137.0" measuredPower="0.85" source="ICON-D2">
    <arrayPower>0.49</arrayPower>
    <arrayPower>0.81</arrayPower>
  </hours>
  <hours time="2026-10-04T12:00:00.000+0000" power="1.14" dcPower="1.18" sunElevation="31.0" sunAzimuth="207.0" shaded="true" source="ICON-D2"/>
  <days date="2026-10-04" energy="11.0" peakPower="2.28" peakTime="2026-10-04T11:00:00.000+0000" specificYield="1.6" hoursCovered="19" measuredEnergy="2.3" source="MOSMIX_L + ICON-D2"/>
  <arrays name="Reihe" azimuth="225.0" tilt="10.0" peakPower="2.58"/>
</pv:PvOutlook>`

const PLANTS = `<?xml version="1.0" encoding="UTF-8"?>
<pv:PlantDirectory xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pv="https://geckoprojects.org/weather/pv/1.0">
  <plants id="demo" name="Garage" siteId="home" peakPower="6.88" timeZone="Europe/Berlin"/>
</pv:PlantDirectory>`

const LOG = `<?xml version="1.0" encoding="UTF-8"?>
<pv:PvMeasurementLog xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pv="https://geckoprojects.org/weather/pv/1.0" plantId="demo" date="2026-10-04" meterType="fronius-solar-api">
  <measurements time="2026-10-04T10:42:50.222+0200" pvPower="1.5448" acPower="1.2105" loadPower="0.6554" gridPower="-0.4021" batteryPower="-0.31025" stateOfCharge="42.5"/>
  <measurements time="2026-10-04T10:43:00.354+0200" pvPower="0.0"/>
</pv:PvMeasurementLog>`

function load(xml: string): EObject {
  registerPvPackage()
  const set = new BasicResourceSet()
  set.getResourceFactoryRegistry().getExtensionToFactoryMap().set('xmi', new XMIResourceFactory())
  const resource = set.createResource(URI.createURI('answer.xmi')) as XMIResource
  resource.loadFromString(xml)
  return rootOf(Array.from(resource.getContents())[0], 'test')
}

describe('pv model', () => {
  it('reads a PV outlook', () => {
    const o = toPvSnapshot(load(OUTLOOK))
    expect(o.plantName).toBe('Garage')
    expect(o.peakPower).toBeCloseTo(6.88)
    expect(o.hours).toHaveLength(3)
    expect(o.hours[0].power).toBe(0)
    expect(o.hours[0].dcPower).toBeUndefined()
    expect(o.hours[0].shaded).toBe(false)
    const h = o.hours[1]
    expect(h.time.toISOString()).toBe('2026-10-04T08:00:00.000Z')
    expect(h.dcPower).toBeCloseTo(1.3)
    expect(h.measuredPower).toBeCloseTo(0.85)
    expect(h.arrayPower).toEqual([0.49, 0.81])
    expect(o.hours[2].shaded).toBe(true)
    expect(o.days[0].measuredEnergy).toBeCloseTo(2.3)
    expect(o.days[0].hoursCovered).toBe(19)
    expect(o.days[0].peakTime?.toISOString()).toBe('2026-10-04T11:00:00.000Z')
    expect(o.arrays[0]).toEqual({ name: 'Reihe', azimuth: 225, tilt: 10, peakPower: 2.58 })
  })

  it('reads plants and readings', () => {
    expect(toPlants(load(PLANTS))).toEqual([{ id: 'demo', name: 'Garage', siteId: 'home', peakPower: 6.88, timeZone: 'Europe/Berlin' }])
    const r = toReadings(load(LOG))
    expect(r.date).toBe('2026-10-04')
    expect(r.readings).toHaveLength(2)
    expect(r.readings[0].time.toISOString()).toBe('2026-10-04T08:42:50.222Z')
    expect(r.readings[0].gridPower).toBeCloseTo(-0.4021)
    expect(r.readings[1].pvPower).toBe(0)
    expect(r.readings[1].loadPower).toBeUndefined()
  })

  it('rejects a foreign answer', () => {
    expect(() => toPvSnapshot(load(PLANTS))).toThrow(/PvOutlook/)
  })
})

describe('pv view helpers', () => {
  const at = (iso: string) => new Date(iso)

  it('compares measured with expected over complete hours with production only', () => {
    const hours = [
      { time: at('2026-10-04T03:00:00Z'), power: 0, dcPower: 0, measuredPower: 0, arrayPower: [], shaded: false, clipped: false },
      { time: at('2026-10-04T08:00:00Z'), power: 1.2, dcPower: 1.3, measuredPower: 0.85, arrayPower: [], shaded: false, clipped: false },
      { time: at('2026-10-04T09:00:00Z'), power: 1.6, dcPower: 1.7, measuredPower: 1.2, arrayPower: [], shaded: false, clipped: false },
      // still running: does not count
      { time: at('2026-10-04T10:00:00Z'), power: 2, dcPower: 2.1, measuredPower: 0.5, arrayPower: [], shaded: false, clipped: false },
    ]
    const r = measuredRatio(hours, at('2026-10-04T10:20:00Z'))
    expect(r?.hours).toBe(2)
    expect(r?.ratio).toBeCloseTo(2.05 / 3.0)
    expect(measuredRatio(hours.slice(0, 1), at('2026-10-04T10:20:00Z'))).toBeUndefined()
  })

  it('takes the latest reading only while it is fresh', () => {
    const readings = [{ time: at('2026-10-04T10:00:00Z'), pvPower: 1 }, { time: at('2026-10-04T10:05:00Z'), pvPower: 2 }]
    expect(latest(readings, at('2026-10-04T10:10:00Z'))?.pvPower).toBe(2)
    expect(latest(readings, at('2026-10-04T10:30:00Z'))).toBeUndefined()
  })

  it('formats', () => {
    expect(kw(0.8666)).toBe('0,87')
    expect(kw(12.44)).toBe('12,4')
    expect(kw(undefined)).toBe('–')
    expect(facing(225)).toBe('SW')
    expect(facing(180)).toBe('S')
  })

  it('makes up a plant from today on, readings until now', async () => {
    const now = at('2026-10-04T09:12:00Z')
    const sample = new SamplePvForecast(() => now)
    const s = await sample.forecast()
    expect(s.hours[0].time.toISOString()).toBe('2026-10-03T22:00:00.000Z')
    expect(s.hours.at(-1)!.time.getTime()).toBeGreaterThan(now.getTime() + 46 * 3_600_000)
    expect(s.hours.some((h) => h.shaded)).toBe(true)
    const r = await sample.measurements()
    expect(r.readings.at(-1)!.time.getTime()).toBeLessThanOrEqual(now.getTime())
    expect(s.days[0].measuredEnergy).toBeGreaterThan(0)
  })
})
