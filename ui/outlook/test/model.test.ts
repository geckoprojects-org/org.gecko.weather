/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
import { BasicResourceSet, URI, XMIResourceFactory, type EObject, type XMIResource } from '@emfts/core'
import { describe, expect, it } from 'vitest'
import { parseDate, registerOutlookPackage, rootOf, toSites, toSnapshot } from '../src/weather.ddsr/model.js'

/** An answer as Java EMF writes it: EDate with a colon-less offset, unset values absent */
const OUTLOOK = `<?xml version="1.0" encoding="UTF-8"?>
<outlook:Outlook xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:outlook="https://geckoprojects.org/weather/outlook/1.0"
    siteId="home" siteName="Home roof" latitude="51.05" longitude="13.74" timeZone="Europe/Berlin" generatedAt="2026-10-04T06:40:00.000+0000">
  <hours time="2026-10-04T06:00:00.000+0000" temperature="6.0" cloudCover="1006.0" precipitation="0.7" precipitationProbability="70.0" windSpeed="3.0" windDirection="270.0" weatherCode="61" daylight="true"/>
  <hours time="2026-10-04T07:00:00.000+0000"/>
  <today date="2026-10-04" uvIndexMax="2.0" sunrise="2026-10-04T05:15:00.000+0000" sunset="2026-10-04T16:45:00.000+0000" solarNoon="2026-10-04T11:00:00.000+0000"/>
  <days date="2026-10-05" temperatureMin="0.0" temperatureMax="23.0" uvIndexMax="2.7" sunrise="2026-10-05T05:17:00.000+0000" sunset="2026-10-05T16:43:00.000+0000" daylightHours="11.4"/>
  <sources quantities="Bewölkung" providerId="dwd" productId="ICON-D2" location="Zelle 884,393" distanceMeters="1112.0" issuedAt="2026-10-04T00:00:00.000+0000"/>
</outlook:Outlook>`

const SITES = `<?xml version="1.0" encoding="UTF-8"?>
<outlook:SiteDirectory xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:outlook="https://geckoprojects.org/weather/outlook/1.0">
  <sites id="home" name="Home roof" latitude="51.05" longitude="13.74" timeZone="Europe/Berlin"/>
  <sites id="erfurt" timeZone="Europe/Berlin"/>
</outlook:SiteDirectory>`

function load(xml: string): EObject {
  registerOutlookPackage()
  const set = new BasicResourceSet()
  set.getResourceFactoryRegistry().getExtensionToFactoryMap().set('xmi', new XMIResourceFactory())
  const resource = set.createResource(URI.createURI('answer.xmi')) as XMIResource
  resource.loadFromString(xml)
  return Array.from(resource.getContents())[0] as EObject
}

describe('outlook model', () => {
  it('reads an outlook answer into plain values', () => {
    const o = toSnapshot(rootOf(load(OUTLOOK), 'test'))
    expect(o.siteId).toBe('home')
    expect(o.siteName).toBe('Home roof')
    expect(o.timeZone).toBe('Europe/Berlin')
    expect(o.generatedAt?.toISOString()).toBe('2026-10-04T06:40:00.000Z')
    expect(o.hours).toHaveLength(2)
    const first = o.hours[0]
    expect(first.time.toISOString()).toBe('2026-10-04T06:00:00.000Z')
    expect(first.temperature).toBe(6)
    expect(first.precipitation).toBeCloseTo(0.7)
    expect(first.weatherCode).toBe(61)
    expect(first.daylight).toBe(true)
    const empty = o.hours[1]
    expect(empty.temperature).toBeUndefined()
    expect(empty.weatherCode).toBeUndefined()
    expect(empty.daylight).toBe(false)
    expect(o.today?.date).toBe('2026-10-04')
    expect(o.today?.uvIndexMax).toBe(2)
    expect(o.today?.solarNoon?.toISOString()).toBe('2026-10-04T11:00:00.000Z')
    expect(o.days[0].date).toBe('2026-10-05')
    expect(o.days[0].temperatureMax).toBe(23)
    expect(o.days[0].precipitation).toBeUndefined()
    expect(o.days[0].sunrise?.toISOString()).toBe('2026-10-05T05:17:00.000Z')
    expect(o.sources[0].productId).toBe('ICON-D2')
    expect(o.sources[0].distanceMeters).toBe(1112)
  })

  it('reads the site directory and refuses the wrong root', () => {
    const sites = toSites(load(SITES))
    expect(sites.map((s) => s.id)).toEqual(['home', 'erfurt'])
    expect(sites[1].name).toBe('erfurt')
    expect(toSnapshot(load(OUTLOOK.replace(/<today[^>]*>/, ''))).today).toBeUndefined()
    expect(() => toSnapshot(load(SITES))).toThrow(/kein Outlook/)
    expect(() => rootOf(undefined, 'WeatherOutlook')).toThrow(/kein Modell/)
  })

  it('parses Java EDates with and without colon', () => {
    expect(parseDate('2026-10-04T06:00:00.000+0000')?.toISOString()).toBe('2026-10-04T06:00:00.000Z')
    expect(parseDate('2026-10-04T08:00:00.000+0200')?.toISOString()).toBe('2026-10-04T06:00:00.000Z')
    expect(parseDate('2026-10-04T06:00:00Z')?.toISOString()).toBe('2026-10-04T06:00:00.000Z')
    expect(parseDate('nonsense')).toBeUndefined()
    expect(parseDate(undefined)).toBeUndefined()
  })
})
