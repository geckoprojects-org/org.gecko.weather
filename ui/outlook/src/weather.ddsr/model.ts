/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The outlook's metamodel, and reading an answer with it.
 *
 * outlook.ecore is the very file the Java bundle is generated from (org.gecko.weather.outlook),
 * imported raw and registered once in the global EPackage registry (see ../emf.ts).
 */
import type { EObject, EPackage } from '@emfts/core'
import type { DayValue, HourValue, OutlookSnapshot, SiteInfo, SourceInfo } from '../contracts.js'
import { date, expect, flag, many, num, one, registerPackage, text } from '../emf.js'

import ecoreXml from '../../../../org.gecko.weather.outlook/model/outlook.ecore?raw'

export { parseDate, rootOf } from '../emf.js'

export const NS_URI = 'https://geckoprojects.org/weather/outlook/1.0'

export function registerOutlookPackage(): EPackage {
  return registerPackage(NS_URI, ecoreXml, 'outlook.ecore')
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
    directRadiation: num(o, 'directRadiation'),
    diffuseRadiation: num(o, 'diffuseRadiation'),
    sunElevation: num(o, 'sunElevation'),
    sunAzimuth: num(o, 'sunAzimuth'),
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
    insolation: num(o, 'insolation'),
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
