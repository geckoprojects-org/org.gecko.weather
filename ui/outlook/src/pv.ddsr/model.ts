/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The PV metamodel — pv.ecore from org.gecko.weather.pv, imported raw — and reading its answers
 * into the plain values of the PV contract.
 */
import type { EObject, EPackage } from '@emfts/core'
import type { ArrayInfo, PlantInfo, PlantProfile, PvDayValue, PvHourValue, PvReading, PvReadings, PvSnapshot } from '../contracts.js'
import { date, expect, flag, many, num, nums, registerPackage, text } from '../emf.js'

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

/**
 * The `Plant` EObject as its geometry. Java EMF leaves default values out: an obstacle that is
 * opaque all year has no leafOffTransmittance, a plant without mounting height none either.
 */
export function toPlantProfile(o: EObject): PlantProfile {
  expect(o, 'Plant')
  return {
    id: text(o, 'id') ?? '',
    name: text(o, 'name') ?? text(o, 'id') ?? '',
    mountingHeight: num(o, 'mountingHeight') ?? 0,
    arrays: many(o, 'arrays').map((a) => ({ ...toArray(a), moduleCount: num(a, 'moduleCount') })),
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
