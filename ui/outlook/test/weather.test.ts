/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
import { describe, expect, it } from 'vitest'
import { SampleWeatherOutlook } from '../src/weather.ddsr/SampleWeatherOutlook.js'
import { compass, dayLabel, degrees, hourLabel, kmh, niceRange, skyOf } from '../src/view.weather/weather.js'

describe('weather view helpers', () => {
  it('chooses the icon by ww first, cloud cover for the sky codes', () => {
    expect(skyOf(95, 10)).toBe('thunder')
    expect(skyOf(71, 100)).toBe('snow')
    expect(skyOf(86, 100)).toBe('snow')
    expect(skyOf(61, 100)).toBe('rain')
    expect(skyOf(81, 100)).toBe('rain')
    expect(skyOf(68, 100)).toBe('sleet')
    expect(skyOf(53, 100)).toBe('drizzle')
    expect(skyOf(45, 100)).toBe('fog')
    expect(skyOf(2, 5)).toBe('clear')
    expect(skyOf(2, 50)).toBe('partly')
    expect(skyOf(3, 90)).toBe('cloudy')
    expect(skyOf(undefined, undefined)).toBe('partly')
  })

  it('formats', () => {
    expect(degrees(12.4)).toBe('12°')
    expect(degrees(undefined)).toBe('–')
    expect(kmh(10)).toBe('36')
    expect(compass(270)).toBe('W')
    expect(compass(-10)).toBe('N')
    expect(compass(135)).toBe('SO')
    expect(hourLabel(new Date('2026-10-04T06:00:00Z'), 'Europe/Berlin')).toBe('08:00')
    expect(dayLabel('2026-10-05')).toEqual({ weekday: 'Montag', date: '5. Oktober' })
  })

  it('rounds chart ranges with a minimum span', () => {
    expect(niceRange([7.2, 14.9], 2, 6)).toEqual([6, 16])
    expect(niceRange([10, 10.5], 2, 6)).toEqual([8, 14])
    expect(niceRange([], 2, 6)).toEqual([0, 6])
  })

  it('makes up a full outlook as examples', async () => {
    const sample = new SampleWeatherOutlook(() => new Date('2026-10-04T06:40:00Z'))
    const o = await sample.outlook()
    expect(sample.examples).toBe(true)
    expect(o.hours).toHaveLength(24)
    expect(o.days).toHaveLength(2)
    expect(o.hours[0].time.toISOString()).toBe('2026-10-04T06:00:00.000Z')
  })
})
