# ADR-0005: Provider-neutral model with canonical measurement kinds

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

The current model is named after a supplier's product. `MOSMIXSWeatherReport` is the central class, its
fields document DWD element IDs (`windDirection` — "Wind direction: 0..360 Degrees (DD)"), and query
signatures take an EMF `EClass` to select the report type (`F-2`). Consumers therefore encode DWD
product semantics in their own code, and adding or switching a source is a breaking change for them.

The recent `sensinact.mapping` / `sensinact.mapping.metadata` annotations are the most forward-looking
part of the existing model — canonical unit, `sensorthings.unit.name`, and the source's own `dwd.id`,
all declared as data. But they sit on `MOSMIXSWeatherReport`, so the semantic mapping is bound to one
provider's product and cannot be reused.

Meanwhile the element mapping itself is control flow: `DWDUtils.setMOSMIXMeasurement` at line 433 of a
599-line class (`F-3`), growing with every product.

There is also a naming defect that shows why this matters concretely: the SIS spike stores shortwave
**global** radiation in a class called `UVRadiationMeasurement` (`F-20`). Global radiation and UV are
different quantities.

## Decision

Values are keyed by a **canonical `MeasurementKind`** — a curated, closed enumeration in the core model,
named after the physical quantity and carrying its canonical unit. Source vocabularies map *into* it
declaratively, not by hand-written conditional code.

**By which declarative mechanism is now an open decision**, because the stack gained one after this ADR
was drafted — see below. The canonical-kinds decision itself does not depend on it.

```
MeasuredValue
  kind   : MeasurementKind   // AIR_TEMPERATURE, GLOBAL_RADIATION, CLOUD_COVER_LOW, …
  value  : double
  unit   : Unit              // canonical for the kind
  …
```

No vendor product name appears in any core bundle name, type, field or constant. No `EClass` appears in
any query signature.

### The mechanism question: `EAnnotations` or the metadata service

This ADR originally assumed `EAnnotations`, continuing the existing `sensinact.mapping` approach. Since
then **Eclipse Fennec EMF OSGi 1.1 ships a metadata mechanism** in
`org.eclipse.fennec.emf.osgi.metadata`, and it is aimed at exactly this problem. `MetadataService`
resolves metadata for an `EPackage`, `EClass`, `EStructuralFeature` or `EOperation`, returning typed
`PackageMetadata` / `ClassMetadata` / `FeatureMetadata` objects, plus **aspects addressed by
`aspectTypeId`** (`getFeatureAspect(feature, aspectTypeId)`). Packages resolve by nsURI, by fingerprint
and by version. The metadata is itself an Ecore model (`model/metadata.ecore`), contributed through a
whiteboard and held in an index that can be read and written.

The decisive difference: **metadata lives beside the model, not inside it.**

| | `EAnnotations` | Metadata service (1.1) |
| --- | --- | --- |
| Where it lives | in the `.ecore`, versioned with the model | separate artefact, resolved at runtime |
| Adding a provider's mapping | edit the core model | contribute metadata, core untouched |
| Typing | string key/value | typed EMF objects, aspects by id |
| Versioning | whatever the model does | per-package fingerprint and version |
| Tooling needed | Ecore editor | index reader/writer |
| Availability | now | emf.osgi **1.1.0-SNAPSHOT**, which the workspace now runs |

The second column is plainly the better fit for `INT-15` — the whole point of that requirement is that
sensiNact and SensorThings metadata be *reusable across providers* rather than tied to one, and metadata
held apart from the model is reusable by construction. It also removes this ADR's sharpest accepted
cost: if a provider's source-element mapping is contributed metadata rather than a core model edit, then
adding a provider stops touching the core even in the mapping dimension.

The mechanism is **available**: the workspace runs emf.osgi `1.1.0-SNAPSHOT`, and the separate
`fennecEMFMetadata` library — which carried the older, standalone `org.eclipse.fennec.model.metadata`
bundles — is replaced by it. The Codec snapshot is being adapted to consume the 1.1 mechanism, so the
whole stack converges on one metadata mechanism rather than two.

One thing stops this from being decided here: **`DEV-5` names Ecore annotations explicitly** ("Ecore
annotations carrying source ID, canonical kind and unit"). Changing the mechanism changes that
requirement, and a requirement is not an ADR's to rewrite. So the decision is: confirm the change to
`DEV-5`, then the metadata service is the mechanism, with `EAnnotations` as the fallback rather than the
plan.

Nothing is lost by deciding it at increment 1.1 rather than now — but it must be decided *by* then,
because the mapping table's home changes with it.

## Consequences

### Positive

- Consumers program against physical quantities, so adding or switching a provider does not break them
  (`INT-5`).
- One generic `MeasurementMapper` driven by declared metadata replaces the hand-written branch tree, so a
  new product is a table of entries rather than new code (`DEV-5`, resolves `F-3`).
- The sensiNact / SensorThings metadata becomes reusable across providers instead of tied to
  MOSMIX (`INT-15`, `Q-D`).
- Unit errors become structurally unlikely: the canonical unit belongs to the kind, and conversion
  happens once in the mapper rather than being assumed at each use site.
- Naming a kind after the physical quantity makes the `F-20` confusion impossible to express —
  `GLOBAL_RADIATION` and `UV_INDEX` are different kinds.
- Fusion, derivation and storage all operate uniformly over `(kind, validAt, value)`.

### Costs accepted

- **Consumers lose typed field access.** `report.getWindSpeed()` becomes a lookup by kind. Mitigated at
  the API boundary, where the REST representation groups quantities per timestep
  ([ADR-0008](0008-rest-api-design.md)) — not in the model.
- **The canonical vocabulary must be curated.** Someone has to decide that a source's parameter is
  "the same quantity as" an existing kind, or a new one. That judgement cannot be automated and is
  where a subtle wrong mapping could hide.
- **Adding a quantity is a core model release.** A closed enumeration means a provider bundle cannot
  introduce a kind by itself. See the note below. Note that this cost applies to the *vocabulary* only;
  under the metadata service a provider's *mapping into* the vocabulary would no longer touch the core.
- Kind-keyed values mean many small objects rather than one report with fields — heavier in EMF
  (`R-7`).
- Migration of stored data when a kind is added or renamed (`OPS-13`, `R-10`).

### Note: this does not contradict the vision

The vision says new sources are added "by dropping in a provider bundle, never by changing the core".
A closed `MeasurementKind` enumeration appears to violate that. The distinction:

- Adding a **provider** for quantities that already exist: no core change. This is the claim, and it
  holds.
- Adding a genuinely **new quantity**: a change to the canonical vocabulary, which is a core concern
  *by definition*. Letting providers define kinds would mean two providers independently inventing
  "cloud cover" with different units — precisely the fragmentation canonical kinds exist to prevent.

So vocabulary extension is a core release, and provider addition is not. Stated here because the
apparent contradiction would otherwise be discovered later and read as a design failure.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Open kind registry** — kinds are objects contributed by bundles, referenced by string id | Fully extensible without core releases, but forfeits the curation that makes the vocabulary canonical, loses compile-time safety, and invites duplicate or contradictory definitions of the same quantity. |
| **Typed attributes per quantity** (today's approach, renamed) | Familiar and pleasant for consumers, but every new quantity is a model change *and* there is nowhere to attach per-value provenance without duplicating it per attribute — incompatible with [ADR-0011](0011-lineage-and-uncertainty.md). |
| **Hybrid: closed enum plus a free-text escape hatch** for un-canonicalised source values | Genuinely useful and was close to being chosen. Deferred because it doubles the query and fusion paths (canonical and non-canonical) for a need that has not yet arisen. Revisit if a DWD product carries a quantity worth storing before it is canonicalised. |
| **Adopt an external vocabulary wholesale** (CF standard names, SensorThings) | Attractive for interoperability, but CF standard names are vast and mostly irrelevant here. Better: a small curated set that *carries* CF/SensorThings identifiers as metadata — which is what the annotation approach already does. |

## Open points

- **The declarative mechanism** — `EAnnotations` or the emf.osgi 1.1 metadata service. The mechanism is
  available; what is open is the change to `DEV-5`, which names annotations. Decide before increment 1.1
  builds the model, because the mapping table's home changes with it.
- Which `aspectTypeId`s we define, if the metadata service wins — one aspect carrying source id,
  canonical kind and unit together, or separate aspects per concern. Aspects are addressed by id, so this
  is a naming contract that other consumers of our metadata would depend on.
- ~~The initial curated kind set.~~ **Known** — see [09-source-inventory.md](../09-source-inventory.md):
  cloud cover in four layers, direct, diffuse and global radiation, UV index, air temperature, wind speed.
  The remaining question is which *derived* quantities are wanted (`Q-G`).
- Whether `Unit` is an enumeration, a string with a UCUM code, or a reference to a unit definition.
  Leaning towards a UCUM code, matching the existing `sensorthings.unit.definition` approach — under the
  metadata service this would be a unit aspect rather than an annotation.
- Whether cloud cover by layer is four kinds (`CLOUD_COVER_LOW/MID/HIGH/TOTAL`) or one kind with a
  layer qualifier. Four kinds is simpler and matches how sources publish it.
