# ADR-0002: EMF/Ecore remains the core model

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

A greenfield rebuild reopens the question of model technology. The current model is generated from
`model/dwd-weather.ecore` via `-generate: geckoEMF`, and the whole surrounding stack assumes EMF:
`org.gecko.emf.osgi`, `org.gecko.emf.json`, `org.gecko.emf.rest.jakartars`, `org.gecko.emf.search`
over Lucene, and `ResourceSet`-based loading of the DWD KML and OpenGIS models
(`net.opengis.kml.model`, `de.dwd.cdc.forecast.model`).

**The stack has moved to Eclipse Fennec.** GeckoEMF was donated to the Eclipse Foundation and
continues as *Eclipse Fennec EMF OSGi* — same architecture (`ResourceSet` as a prototype-scoped OSGi
service, `EPackage`/`EFactory` as services instead of the static registries, no PDE or Equinox
dependency), new coordinates. A compatibility layer ships alongside it
(`org.eclipse.fennec.emf.gecko.compatibility.api`, providing `emf.core=osgi;version=6.3.0` marked
`deprecated=true`), so this is a rename plus a migration path, not a different technology. That
removes what would otherwise have been the strongest argument against EMF here — that the surrounding
stack was a single vendor's and unmaintained.

Two pieces of the old stack do *not* carry over one-to-one, and both are decided in this ADR:
`org.gecko.emf.json` and `org.gecko.emf.search`.

The redesign also introduces a requirement that interacts with model technology directly: declarative
mapping metadata on model features (`DEV-5`, `INT-15`), continuing the existing `sensinact.mapping`
annotation approach.

Against EMF: the model becomes a bag of small value objects with lineage
([ADR-0011](0011-lineage-and-uncertainty.md)), which plain Java records would express more concisely,
and EMF's generated code dominates the source tree.

## Decision

**Ecore plus genmodel remains the single source of truth**, with generated code in the repository and
never hand-edited. The mapping metadata mechanism continues as Ecore `EAnnotations`, relocated from
provider-specific classes onto canonical measurement kinds.

The runtime stack is **Eclipse Fennec**, not Gecko:

| Concern | Implementation |
| --- | --- |
| EMF in OSGi (`ResourceSet`, `EPackage`/`EFactory` as services) | `org.eclipse.fennec.emf.osgi` **1.1.0-SNAPSHOT** — 1.1 because that is where the metadata mechanism lives ([ADR-0005](0005-provider-neutral-model.md)); newest release line is 1.0.2 |
| JSON and other textual formats | **Fennec Codec** — Jackson-based core plus per-format bundles (`yaml`, `csv`, `cbor`, `bson`, `geojson`, `tabular`, `xlsx`, `ods`, `jsonschema`, `openapi`, `rest`) |
| Persistence | **Fennec Persistence** — `persistence` API with `eclipselink` (JPA) and `mongo` backends, `query.model` / `persistence.query` for querying, `persistence.pushstreams` for streaming |
| OCL and QVT | **Fennec M2X** (`expression.ocl`) |
| Source models (DWD, KML) | **`fennec.common.models`** — `de.dwd.cdc.model`, `de.dwd.cdc.common.model`, `de.dwd.cdc.forecast.model`, `net.opengis.kml.model` are all already published there |
| Search index | **Our own Lucene integration**, not `org.gecko.emf.search` |

The DWD and KML Ecore models being already available under Fennec is the single most useful
consequence: the MOSMIX decoding path needs no model porting at all.

## Consequences

### Positive

- The platform keeps working across the rename: `ResourceSet` as an injectable service, model
  registration by service properties, and the JakartaRS integration all exist under Fennec, and the
  compatibility bundle covers anything still speaking the Gecko contract.
- **Serialisation gets better, not merely equivalent.** Fennec Codec covers more than the old
  `emf.json` did — the same machinery reads and writes YAML, CSV, CBOR, BSON and GeoJSON. CSV matters
  for the DWD station catalogue, GeoJSON for site and grid-cell geometry, and BSON aligns with the
  Mongo persistence backend.
- Source models stay usable as models. DWD KML arrives via `net.opengis.kml.model` and
  `de.dwd.cdc.forecast.model` — **already published in `fennec.common.models`** — so abandoning EMF
  would mean hand-writing deserialisation for the DWD extension schema that we currently get for free.
- Persistence becomes a stack concern rather than something to build: `persistence.eclipselink` and
  `persistence.mongo` are alternative backends behind one API, with a TCK, and the converter set
  already includes `Instant`, `ZonedDateTime`, `Duration` and `LocalDate` — exactly the `java.time`
  types [ADR-0006](0006-java-baseline-toolchain.md) requires.
- **`EAnnotations` give declarative mapping for free.** Canonical unit, `sensorthings.unit.name` and
  per-provider source identifiers live on the model where they are visible and reviewable — the
  mechanism `DEV-5` needs to eliminate the 599-line mapping utility (`F-3`).
- The sensiNact direction (`Q-D`) is an EMF-annotation-based integration; keeping EMF keeps it cheap.
- Model versioning, change notification and reflection come without extra work.
- OCL is available via Fennec M2X, so model constraints can be declared rather than hand-coded —
  which is what makes the value invariant in [ADR-0011](0011-lineage-and-uncertainty.md) enforceable
  by something other than review discipline.

### Costs accepted

- **Ecore editing needs tooling.** Practically this means Eclipse, and it makes model changes heavier
  than editing a Java file — a real cost for a one-person project working in intervals.
- **Generated code dominates the tree.** The current `model` bundle is almost entirely generated, which
  makes diffs noisy and can obscure hand-written changes. Regeneration must be a deliberate,
  reviewed step.
- Verbosity: a `MeasuredValue` with provenance and uncertainty is heavier as an `EObject` than as a
  record, and ingest creates many of them. Acceptable only because subset-on-ingest keeps volumes
  small (`R-7`) — to be measured once ICON-D2 ingest runs rather than assumed.
- Generation slows the build. Mitigated by making it skippable (`DEV-13`), as the current
  `bnd.bnd` comment already suggests.
- Consumers of the in-process SPI see EMF types. Mitigated by keeping `EClass` out of query
  signatures — the specific leak in `F-2` — not by hiding EMF entirely.
- **The Lucene integration is now ours to write and maintain.** `org.gecko.emf.search` indexed
  `EObject`s for us; Fennec has no equivalent and we are deliberately not adopting the Gecko one. That
  is a real cost in a one-person project, paid to avoid depending on an unmaintained bundle — and it
  is bounded, because [ADR-0004](0004-persistence-index-split.md) makes the index disposable and
  rebuildable rather than authoritative.
- **We build on snapshots, and the stack is not version-aligned yet.** `emf.osgi` has releases up to
  1.0.2, but the workspace deliberately runs **1.1.0-SNAPSHOT** because 1.1 is where the metadata
  mechanism lives and that has no release. Codec (0.1.0), Persistence (0.1.0), M2X (0.1.0) and
  `common.models` (0.0.1) are SNAPSHOT-only in any case.
- **Version alignment across the Fennec libraries is a standing obligation.** Each library ships its own
  Maven index, and Codec, M2X, Persistence and `common.models` currently pin `emf.osgi` `0.1.2` in
  theirs, so the repository *pool* offers several `emf.osgi` versions at once. That is not by itself
  `F-13`: which version reaches a runtime is settled by the resolve against package version ranges, and
  with no bundle and no bndrun in the workspace yet, nothing has been resolved — so this is a **risk to
  verify at the first real resolve**, not a defect already present. Upstream is aligning it in the next
  snapshot round.
- **One library disappears in the process.** `fennecEMFMetadata`, which carried the standalone
  `org.eclipse.fennec.model.metadata` bundles, is replaced by emf.osgi 1.1 — so the `-library` list in
  `cnf/ext/fennec.bnd` shrinks by one and the metadata mechanism stops being a separate dependency. Good
  news for the dependency graph, and the reason not to build anything against the old metadata bundles
  in the meantime.
- Accepted because these are our own project's libraries and influence over them is direct — but it
  means dependency bumps are coordinated work across repositories, not background noise, and `DEV-9`
  (one version of each bundle, enforced as a build failure) has to be wired up early enough to catch a
  misalignment rather than discover it at runtime.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Java records + Jackson** | Concise and pleasant for the new value-object-heavy model, but discards Fennec Codec, Fennec Persistence, the REST integration, and the ability to consume the DWD/OpenGIS Ecore models that `fennec.common.models` already publishes. The replacement work vastly exceeds the benefit. |
| **EMF internally, records/DTOs at the boundary** | Attractive for API cleanliness, but doubles the type set and requires mapper code for every quantity — ongoing cost paid by the one person maintaining it. The actual problem in `F-2` is `EClass` in signatures and provider names in types, both fixable within EMF. |
| **Ecore as schema, generate records instead of EMF impls** | No mature path in this toolchain, and it would forfeit `ResourceSet` and the codec/persistence integration while still requiring Ecore tooling. |
| **Stay on the Gecko bundles** | They still work. Rejected because the code has moved to Eclipse Fennec and the Gecko copies are the frozen side of the fork — the compatibility layer exists to leave them, not to stay. |

## Open points

- Which persistence backend the repository uses — Fennec `persistence.eclipselink` (JPA), `mongo`, or
  EMF resources on a plain volume. Left to [ADR-0004](0004-persistence-index-split.md); the model
  decision does not depend on it.
- Generated-code review policy: whether generated sources are reviewed in pull requests or excluded
  via `.gitattributes`, as the current repository already does for some paths.
- Whether the Gecko compatibility bundle is used at all. Preference is no: a greenfield build has
  nothing to be compatible with, and pulling in a bundle whose capability is declared `deprecated=true`
  on day one would be odd. Confirm when the first bundle is written.
