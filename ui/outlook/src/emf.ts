/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * Reading answers of the remote services: an ecore registered once in the global registry, and the
 * small accessors that turn an EObject into the plain values a contract speaks. Shared by the
 * weather and the PV client.
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

const registered = new Map<string, EPackage>()

/**
 * Registers an ecore — imported raw from the Java bundle it is generated from — in the global
 * EPackage registry, once. The DDSR client parses an answer in a resource set of its own, which
 * falls back to that registry for nsURIs it does not know.
 */
export function registerPackage(nsUri: string, ecoreXml: string, name: string): EPackage {
  const done = registered.get(nsUri)
  if (done) return done
  const known = EPackageRegistry.INSTANCE.getEPackage(nsUri)
  if (known) {
    registered.set(nsUri, known)
    return known
  }
  registerEcorePackage()
  const set = new BasicResourceSet()
  set.getResourceFactoryRegistry().getExtensionToFactoryMap().set('ecore', new XMIResourceFactory())
  const resource = set.createResource(URI.createURI(name)) as XMIResource
  resource.loadFromString(ecoreXml)
  if (resource.getErrors().length > 0) {
    console.warn(`${name}: Ecore-Modell mit Warnungen geladen`, resource.getErrors())
  }
  const loaded = Array.from(resource.getContents())[0] as EPackage
  EPackageRegistry.INSTANCE.set(nsUri, loaded)
  registered.set(nsUri, loaded)
  return loaded
}

/** A feature's value, or `undefined` when the object has none or it is unset */
export function value(object: EObject, name: string): unknown {
  const feature = object.eClass().getEStructuralFeature(name)
  if (!feature || !object.eIsSet(feature)) return undefined
  return object.eGet(feature)
}

export function text(object: EObject, name: string): string | undefined {
  const v = value(object, name)
  return v == null ? undefined : String(v)
}

export function num(object: EObject, name: string): number | undefined {
  const v = value(object, name)
  if (v == null) return undefined
  const n = typeof v === 'number' ? v : Number(v)
  return Number.isFinite(n) ? n : undefined
}

/** The numbers of a many-valued attribute */
export function nums(object: EObject, name: string): number[] {
  const feature = object.eClass().getEStructuralFeature(name)
  const v = feature ? object.eGet(feature) : undefined
  return v ? Array.from(v as Iterable<unknown>).map(Number).filter(Number.isFinite) : []
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

export function date(object: EObject, name: string): Date | undefined {
  return parseDate(value(object, name))
}

export function flag(object: EObject, name: string): boolean {
  const feature = object.eClass().getEStructuralFeature(name)
  const v = feature ? object.eGet(feature) : undefined
  return v === true || v === 'true'
}

export function one(object: EObject, name: string): EObject | undefined {
  const v = value(object, name)
  return v && typeof v === 'object' && 'eClass' in v ? (v as EObject) : undefined
}

export function many(object: EObject, name: string): EObject[] {
  const feature = object.eClass().getEStructuralFeature(name)
  const v = feature ? object.eGet(feature) : undefined
  return v ? (Array.from(v as Iterable<EObject>) as EObject[]) : []
}

export function expect(object: EObject, eClass: string): void {
  if (object.eClass().getName() !== eClass) {
    throw new Error(`Antwort ist kein ${eClass}, sondern ${object.eClass().getName()}`)
  }
}

/** The first root of an answer — the client hands over one EObject or the resource's contents */
export function rootOf(answer: unknown, what: string): EObject {
  const root = (Array.isArray(answer) ? answer[0] : answer) as EObject | undefined
  if (!root || typeof root !== 'object' || !('eClass' in root)) {
    throw new Error(`${what} hat kein Modell geantwortet`)
  }
  return root
}
