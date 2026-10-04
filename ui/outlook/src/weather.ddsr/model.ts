/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The outlook's metamodel, and reading an answer with it.
 *
 * outlook.ecore is the very file the Java bundle is generated from (org.gecko.weather.outlook),
 * imported raw. It is registered dynamically in the global EPackage registry: the DDSR client
 * parses an answer in a resource set of its own, which falls back to that registry for nsURIs it
 * does not know. Registered once — as in xdp-ui's datasource.ddsr.
 */
import {
  BasicResourceSet,
  EPackageRegistry,
  URI,
  XMIResourceFactory,
  registerEcorePackage,
  type EObject,
  type EPackage,
  type XMIResource,
} from '@emfts/core'
import type { DayValue, HourValue, OutlookSnapshot, SiteInfo, SourceInfo } from '../contracts.js'

import ecoreXml from '../../../../org.gecko.weather.outlook/model/outlook.ecore?raw'

export const NS_URI = 'https://geckoprojects.org/weather/outlook/1.0'

let registered: EPackage | undefined

export function registerOutlookPackage(): EPackage {
  if (registered) return registered
  const known = EPackageRegistry.INSTANCE.getEPackage(NS_URI)
  if (known) return (registered = known)
  registerEcorePackage()
  const set = new BasicResourceSet()
  set.getResourceFactoryRegistry().getExtensionToFactoryMap().set('ecore', new XMIResourceFactory())
  const resource = set.createResource(URI.createURI('outlook.ecore')) as XMIResource
  resource.loadFromString(ecoreXml)
  if (resource.getErrors().length > 0) {
    console.warn('weather.ddsr: Ecore-Modell mit Warnungen geladen', resource.getErrors())
  }
  const loaded = Array.from(resource.getContents())[0] as EPackage
  EPackageRegistry.INSTANCE.set(NS_URI, loaded)
  return (registered = loaded)
}

/** A feature's value, or `undefined` when the object has none or it is unset */
function value(object: EObject, name: string): unknown {
  const feature = object.eClass().getEStructuralFeature(name)
  if (!feature || !object.eIsSet(feature)) return undefined
  return object.eGet(feature)
}

function text(object: EObject, name: string): string | undefined {
  const v = value(object, name)
  return v == null ? undefined : String(v)
}

function num(object: EObject, name: string): number | undefined {
  const v = value(object, name)
  if (v == null) return undefined
  const n = typeof v === 'number' ? v : Number(v)
  return Number.isFinite(n) ? n : undefined
}

/**
 * An EDate as Java's XMI writes it — `2026-10-04T06:00:00.000+0000`. The offset without a colon is
 * not ISO, and not every browser parses it, hence the colon.
 */
export function parseDate(raw: unknown): Date | undefined {
  if (raw == null) return undefined
  if (raw instanceof Date) return Number.isNaN(raw.getTime()) ? undefined : raw
  const s = String(raw).replace(/([+-]\d{2})(\d{2})$/, '$1:$2')
  const d = new Date(s)
  return Number.isNaN(d.getTime()) ? undefined : d
}

function date(object: EObject, name: string): Date | undefined {
  return parseDate(value(object, name))
}

function flag(object: EObject, name: string): boolean {
  const feature = object.eClass().getEStructuralFeature(name)
  const v = feature ? object.eGet(feature) : undefined
  return v === true || v === 'true'
}

function one(object: EObject, name: string): EObject | undefined {
  const v = value(object, name)
  return v && typeof v === 'object' && 'eClass' in v ? (v as EObject) : undefined
}

function many(object: EObject, name: string): EObject[] {
  const feature = object.eClass().getEStructuralFeature(name)
  const v = feature ? object.eGet(feature) : undefined
  return v ? (Array.from(v as Iterable<EObject>) as EObject[]) : []
}

function expect(object: EObject, eClass: string): void {
  if (object.eClass().getName() !== eClass) {
    throw new Error(`Antwort ist kein ${eClass}, sondern ${object.eClass().getName()}`)
  }
}

function toHour(o: EObject): HourValue {
  return {
    time: date(o, 'time') ?? new Date(NaN),
    temperature: num(o, 'temperature'),
    dewPoint: num(o, 'dewPoint'),
    cloudCover: num(o, 'cloudCover'),
    precipitation: num(o, 'precipitation'),
    precipitationProbability: num(o, 'precipitationProbability'),
    windSpeed: num(o, 'windSpeed'),
    windGust: num(o, 'windGust'),
    windDirection: num(o, 'windDirection'),
    globalRadiation: num(o, 'globalRadiation'),
    sunElevation: num(o, 'sunElevation'),
    weatherCode: num(o, 'weatherCode'),
    daylight: flag(o, 'daylight'),
  }
}

function toDay(o: EObject): DayValue {
  return {
    date: text(o, 'date') ?? '',
    temperatureMin: num(o, 'temperatureMin'),
    temperatureMax: num(o, 'temperatureMax'),
    precipitation: num(o, 'precipitation'),
    precipitationProbability: num(o, 'precipitationProbability'),
    sunshineHours: num(o, 'sunshineHours'),
    cloudCoverMean: num(o, 'cloudCoverMean'),
    windGustMax: num(o, 'windGustMax'),
    uvIndexMax: num(o, 'uvIndexMax'),
    weatherCode: num(o, 'weatherCode'),
    sunrise: date(o, 'sunrise'),
    sunset: date(o, 'sunset'),
    solarNoon: date(o, 'solarNoon'),
    daylightHours: num(o, 'daylightHours'),
  }
}

function toSource(o: EObject): SourceInfo {
  return {
    quantities: text(o, 'quantities') ?? '',
    providerId: text(o, 'providerId') ?? '',
    productId: text(o, 'productId') ?? '',
    location: text(o, 'location') ?? '',
    distanceMeters: num(o, 'distanceMeters'),
    issuedAt: date(o, 'issuedAt'),
  }
}

/** The `Outlook` EObject as the plain values the contract speaks */
export function toSnapshot(outlook: EObject): OutlookSnapshot {
  expect(outlook, 'Outlook')
  const today = one(outlook, 'today')
  return {
    siteId: text(outlook, 'siteId') ?? '',
    siteName: text(outlook, 'siteName') ?? text(outlook, 'siteId') ?? '',
    latitude: num(outlook, 'latitude'),
    longitude: num(outlook, 'longitude'),
    timeZone: text(outlook, 'timeZone') ?? 'UTC',
    generatedAt: date(outlook, 'generatedAt'),
    hours: many(outlook, 'hours').map(toHour),
    today: today ? toDay(today) : undefined,
    days: many(outlook, 'days').map(toDay),
    sources: many(outlook, 'sources').map(toSource),
  }
}

/** The `SiteDirectory` EObject as a list */
export function toSites(directory: EObject): SiteInfo[] {
  expect(directory, 'SiteDirectory')
  return many(directory, 'sites').map((o) => ({
    id: text(o, 'id') ?? '',
    name: text(o, 'name') ?? text(o, 'id') ?? '',
    latitude: num(o, 'latitude'),
    longitude: num(o, 'longitude'),
    timeZone: text(o, 'timeZone') ?? 'UTC',
  }))
}

/** The first root of an answer — the client hands over one EObject or the resource's contents */
export function rootOf(answer: unknown, what: string): EObject {
  const root = (Array.isArray(answer) ? answer[0] : answer) as EObject | undefined
  if (!root || typeof root !== 'object' || !('eClass' in root)) {
    throw new Error(`${what} hat kein Modell geantwortet`)
  }
  return root
}
