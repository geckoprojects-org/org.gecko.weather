/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The weather outlook, found and called through the service registry.
 *
 * The provider is the Java component in org.gecko.weather.outlook, exported through the Remote
 * Service Admin of Fennec Services. Nothing here knows where it runs: the broker's lookup answer
 * carries its endpoint, and the REST flavor plugin calls it there. In xdp-ui this class becomes a
 * tsm component (`@component({ service: [XDP_WEATHER_OUTLOOK] })`) in its own bundle, exactly like
 * datasource.ddsr; the broker URL then comes from the configuration model.
 */
import { DdsrClientImpl, type ServiceLocator } from '@ddsr/client'
import { RestFlavorPlugin } from '@ddsr/flavor-rest'
import type { OutlookSnapshot, SiteInfo, WeatherOutlook } from '../contracts.js'
import { registerOutlookPackage, rootOf, toSites, toSnapshot } from './model.js'

/** The contract's name in the broker's catalog — the Java interface's simple name */
export const CONTRACT = 'WeatherOutlook'

export class DdsrWeatherOutlook implements WeatherOutlook {
  readonly examples = false
  private client: DdsrClientImpl | undefined
  private locator: ServiceLocator | undefined

  constructor(private readonly brokerUrl: string) {}

  origin(): string {
    return `${CONTRACT} über ${this.brokerUrl}`
  }

  async sites(): Promise<SiteInfo[]> {
    registerOutlookPackage()
    return toSites(rootOf(await (await this.find()).invoke('sites'), CONTRACT))
  }

  async outlook(siteId: string): Promise<OutlookSnapshot> {
    registerOutlookPackage()
    return toSnapshot(rootOf(await (await this.find()).invoke('outlook', { siteId }), CONTRACT))
  }

  /** The locator, looked up once; it follows the provider on its own */
  private async find(): Promise<ServiceLocator> {
    if (this.locator) return this.locator
    this.client ??= DdsrClientImpl.create({
      brokerUrl: this.brokerUrl,
      flavorPlugins: [new RestFlavorPlugin()],
      originLabel: 'weather-outlook-ui',
      // a pure consumer: no provider heartbeats to send
      providerHeartbeatSeconds: 0,
    })
    const locator = await this.client.consumer.findOne(CONTRACT)
    if (!locator) throw new Error(`Kein Anbieter für ${CONTRACT} beim Broker angemeldet`)
    return (this.locator = locator)
  }

  async close(): Promise<void> {
    const client = this.client
    this.client = undefined
    this.locator = undefined
    await client?.close()
  }
}
