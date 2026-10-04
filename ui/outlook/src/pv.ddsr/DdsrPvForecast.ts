/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The PV forecast, found and called through the service registry — the Java component in
 * org.gecko.weather.pv, exported like the weather outlook. In xdp-ui a tsm component of its own.
 */
import { DdsrClientImpl, type ServiceLocator } from '@ddsr/client'
import { RestFlavorPlugin } from '@ddsr/flavor-rest'
import type { PlantInfo, PvForecast, PvReadings, PvSnapshot } from '../contracts.js'
import { rootOf } from '../emf.js'
import { registerPvPackage, toPlants, toPvSnapshot, toReadings } from './model.js'

/** The contract's name in the broker's catalog — the Java interface's simple name */
export const CONTRACT = 'PvForecast'

export class DdsrPvForecast implements PvForecast {
  readonly examples = false
  private client: DdsrClientImpl | undefined
  private locator: ServiceLocator | undefined

  constructor(private readonly brokerUrl: string) {}

  origin(): string {
    return `${CONTRACT} über ${this.brokerUrl}`
  }

  async plants(): Promise<PlantInfo[]> {
    registerPvPackage()
    return toPlants(rootOf(await (await this.find()).invoke('plants'), CONTRACT))
  }

  async forecast(plantId: string): Promise<PvSnapshot> {
    registerPvPackage()
    return toPvSnapshot(rootOf(await (await this.find()).invoke('forecast', { plantId }), CONTRACT))
  }

  async measurements(plantId: string, date = ''): Promise<PvReadings> {
    registerPvPackage()
    return toReadings(rootOf(await (await this.find()).invoke('measurements', { plantId, date }), CONTRACT))
  }

  private async find(): Promise<ServiceLocator> {
    if (this.locator) return this.locator
    this.client ??= DdsrClientImpl.create({
      brokerUrl: this.brokerUrl,
      flavorPlugins: [new RestFlavorPlugin()],
      originLabel: 'weather-pv-ui',
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
