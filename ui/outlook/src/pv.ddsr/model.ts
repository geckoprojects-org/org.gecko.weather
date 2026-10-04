/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The PV metamodel — pv.ecore from org.gecko.weather.pv, imported raw — and reading its answers
 * into the plain values of the PV contract.
 */
import type { EClass, EEnum, EList, EObject, EPackage } from '@emfts/core'
import type { ArrayInfo, Mounting, PlantInfo, PlantProfile, ProfileArray, PvDayValue, PvHourValue, PvReading, PvReadings, PvSnapshot } from '../contracts.js'
import { date, expect, flag, many, num, nums, one, registerPackage, text, value } from '../emf.js'

import ecoreXml from '../../../../org.gecko.weather.pv/model/pv.ecore?raw'

export const NS_URI = 'https://geckoprojects.org/weather/pv/1.0'

export function registerPvPackage(): EPackage {
  return registerPackage(NS_URI, ecoreXml, 'pv.ecore')
}

function toHour(o: EObject): PvHourValue {
  return {
    time: date(o, 'time') ?? new Date(NaN),
    power: num(o, 'power'),
    dcPower: num(o, 'dcPower'),
    arrayPower: nums(o, 'arrayPower'),
    planeIrradiance: num(o, 'planeIrradiance'),
    globalRadiation: num(o, 'globalRadiation'),
    cellTemperature: num(o, 'cellTemperature'),
    sunElevation: num(o, 'sunElevation'),
    sunAzimuth: num(o, 'sunAzimuth'),
    shaded: flag(o, 'shaded'),
    clipped: flag(o, 'clipped'),
    measuredPower: num(o, 'measuredPower'),
    source: text(o, 'source'),
  }
}

function toDay(o: EObject): PvDayValue {
  return {
    date: text(o, 'date') ?? '',
    energy: num(o, 'energy'),
    peakPower: num(o, 'peakPower'),
    peakTime: date(o, 'peakTime'),
    specificYield: num(o, 'specificYield'),
    hoursCovered: num(o, 'hoursCovered') ?? 0,
    measuredEnergy: num(o, 'measuredEnergy'),
    source: text(o, 'source'),
  }
}

function toArray(o: EObject): ArrayInfo {
  return { name: text(o, 'name') ?? '', azimuth: num(o, 'azimuth'), tilt: num(o, 'tilt'), peakPower: num(o, 'peakPower') }
}

/**
 * The `PvOutlook` EObject as plain values. A power of 0 is not written by Java EMF (it is the
 * default), so a missing power in an hour that has a source or lies in the night is 0, not unknown.
 */
export function toPvSnapshot(o: EObject): PvSnapshot {
  expect(o, 'PvOutlook')
  return {
    plantId: text(o, 'plantId') ?? '',
    plantName: text(o, 'plantName') ?? text(o, 'plantId') ?? '',
    siteId: text(o, 'siteId') ?? '',
    timeZone: text(o, 'timeZone') ?? 'UTC',
    peakPower: num(o, 'peakPower'),
    generatedAt: date(o, 'generatedAt'),
    arrays: many(o, 'arrays').map(toArray),
    hours: many(o, 'hours').map(toHour).map((h) => ({ ...h, power: h.power ?? 0 })),
    days: many(o, 'days').map(toDay),
  }
}

export function toPlants(directory: EObject): PlantInfo[] {
  expect(directory, 'PlantDirectory')
  return many(directory, 'plants').map((p) => ({
    id: text(p, 'id') ?? '',
    name: text(p, 'name') ?? text(p, 'id') ?? '',
    siteId: text(p, 'siteId') ?? '',
    peakPower: num(p, 'peakPower'),
    timeZone: text(p, 'timeZone') ?? 'UTC',
  }))
}

function toReading(o: EObject): PvReading {
  return {
    time: date(o, 'time') ?? new Date(NaN),
    pvPower: num(o, 'pvPower'),
    acPower: num(o, 'acPower'),
    loadPower: num(o, 'loadPower'),
    gridPower: num(o, 'gridPower'),
    batteryPower: num(o, 'batteryPower'),
    stateOfCharge: num(o, 'stateOfCharge'),
  }
}

export function toReadings(log: EObject): PvReadings {
  expect(log, 'PvMeasurementLog')
  return {
    date: text(log, 'date') ?? '',
    meterType: text(log, 'meterType'),
    readings: many(log, 'measurements').map(toReading).filter((r) => !Number.isNaN(r.time.getTime())),
  }
}

const MOUNTINGS: Mounting[] = ['ROOF_MOUNTED', 'ROOF_INTEGRATED', 'OPEN_RACK']

/** An enum attribute: the loader may hold the literal object or its name */
function mounting(o: EObject): Mounting | undefined {
  const v = value(o, 'mounting') as { getName?: () => string; getLiteral?: () => string } | string | undefined
  if (v == null) return undefined
  const name = typeof v === 'string' ? v : (v.getName?.() ?? v.getLiteral?.() ?? String(v))
  return MOUNTINGS.includes(name as Mounting) ? (name as Mounting) : undefined
}

/**
 * The `Plant` EObject as its profile. Java EMF leaves default values out: an obstacle that is
 * opaque all year has no leafOffTransmittance, a plant with 10 % losses no systemLosses — the
 * defaults are filled in here so that an editor shows them.
 */
export function toPlantProfile(o: EObject): PlantProfile {
  expect(o, 'Plant')
  const inverters = many(o, 'inverters')
  return {
    id: text(o, 'id') ?? '',
    name: text(o, 'name') ?? text(o, 'id') ?? '',
    siteId: text(o, 'siteId') ?? '',
    latitude: num(o, 'latitude'),
    longitude: num(o, 'longitude'),
    mountingHeight: num(o, 'mountingHeight') ?? 0,
    albedo: num(o, 'albedo') ?? 0.2,
    systemLosses: num(o, 'systemLosses') ?? 10,
    arrays: many(o, 'arrays').map((a): ProfileArray => {
      const inv = one(a, 'inverter')
      const index = inv ? inverters.indexOf(inv) : -1
      return {
        ...toArray(a),
        moduleCount: num(a, 'moduleCount'),
        temperatureCoefficient: num(a, 'temperatureCoefficient') ?? -0.37,
        mounting: mounting(a) ?? 'ROOF_MOUNTED',
        inverter: index >= 0 ? index : undefined,
      }
    }),
    inverters: inverters.map((i) => ({ name: text(i, 'name') ?? '', acPower: num(i, 'acPower'), efficiency: num(i, 'efficiency') ?? 0.96 })),
    obstacles: many(o, 'obstacles').map((b) => ({
      name: text(b, 'name') ?? '',
      azimuthFrom: num(b, 'azimuthFrom') ?? 0,
      azimuthTo: num(b, 'azimuthTo') ?? 0,
      distance: num(b, 'distance') ?? 0,
      height: num(b, 'height') ?? 0,
      leafOffTransmittance: num(b, 'leafOffTransmittance') ?? 0,
    })),
    horizon: many(o, 'horizon').map((h) => ({ azimuth: num(h, 'azimuth') ?? 0, elevation: num(h, 'elevation') ?? 0 })),
  }
}

function set(o: EObject, name: string, v: unknown): void {
  const f = o.eClass().getEStructuralFeature(name)
  if (!f) throw new Error(`${o.eClass().getName()} hat kein Merkmal ${name}`)
  if (v === undefined || v === null || v === '' || (typeof v === 'number' && Number.isNaN(v))) o.eUnset(f)
  else o.eSet(f, v)
}

function list(o: EObject, name: string): EList<EObject> {
  return o.eGet(o.eClass().getEStructuralFeature(name)!) as EList<EObject>
}

/** A profile as the `Plant` EObject the service takes — the inverse of {@link toPlantProfile} */
export function fromPlantProfile(p: PlantProfile): EObject {
  const pkg = registerPvPackage()
  const factory = pkg.getEFactoryInstance()
  const create = (name: string) => factory.create(pkg.getEClassifier(name) as EClass)
  const plant = create('Plant')
  set(plant, 'id', p.id.trim())
  set(plant, 'name', p.name.trim())
  set(plant, 'siteId', p.siteId.trim())
  set(plant, 'latitude', p.latitude)
  set(plant, 'longitude', p.longitude)
  set(plant, 'mountingHeight', p.mountingHeight)
  set(plant, 'albedo', p.albedo)
  set(plant, 'systemLosses', p.systemLosses)
  const inverters = p.inverters.map((i) => {
    const o = create('Inverter')
    set(o, 'name', i.name)
    set(o, 'acPower', i.acPower)
    set(o, 'efficiency', i.efficiency)
    list(plant, 'inverters').add(o)
    return o
  })
  const mountingEnum = (pkg.getEClassifier('Mounting') as EEnum)
  for (const a of p.arrays) {
    const o = create('PvArray')
    set(o, 'name', a.name)
    set(o, 'azimuth', a.azimuth)
    set(o, 'tilt', a.tilt)
    set(o, 'peakPower', a.peakPower)
    set(o, 'moduleCount', a.moduleCount)
    set(o, 'temperatureCoefficient', a.temperatureCoefficient)
    if (a.mounting) set(o, 'mounting', mountingEnum.getEEnumLiteral(a.mounting) ?? a.mounting)
    if (a.inverter !== undefined && inverters[a.inverter]) set(o, 'inverter', inverters[a.inverter])
    list(plant, 'arrays').add(o)
  }
  for (const b of p.obstacles) {
    const o = create('Obstacle')
    set(o, 'name', b.name)
    set(o, 'azimuthFrom', b.azimuthFrom)
    set(o, 'azimuthTo', b.azimuthTo)
    set(o, 'distance', b.distance)
    set(o, 'height', b.height)
    set(o, 'leafOffTransmittance', b.leafOffTransmittance)
    list(plant, 'obstacles').add(o)
  }
  for (const h of p.horizon) {
    const o = create('HorizonPoint')
    set(o, 'azimuth', h.azimuth)
    set(o, 'elevation', h.elevation)
    list(plant, 'horizon').add(o)
  }
  return plant
}
