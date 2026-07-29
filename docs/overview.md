# Gecko Weather — Redesign Dossier

Entry point for the complete redesign of Gecko Weather. Read the documents in the order below; each
builds on the previous.

## Reading order

| # | Document | Answers |
| --- | --- | --- |
| 1 | [01-vision.md](01-vision.md) | What are we building, for what purpose, and what are we deliberately not building? |
| 2 | [02-stakeholders.md](02-stakeholders.md) | Who cares, what do they need, what hurts them today? |
| 3 | [03-requirements.md](03-requirements.md) | Requirements per stakeholder, prioritised and traceable |
| 4 | [04-architecture-current.md](04-architecture-current.md) | Honest as-is analysis; findings `F-1` … `F-20` |
| 5 | [05-architecture-target.md](05-architecture-target.md) | Target architecture: bundles, SPI contracts, data flow |
| 6 | [06-redesign-plan.md](06-redesign-plan.md) | Sequence around the MVP, exit criteria, effort classes, risks |
| 7 | [07-migration.md](07-migration.md) | What to salvage from the old branches, and the cutover |
| **8** | **[08-mvp.md](08-mvp.md)** | **What is actually being built first — read this before the plan** |
| **9** | **[09-source-inventory.md](09-source-inventory.md)** | Which DWD products deliver what, measured — the answer to `Q-I` |
| — | [adr/index.md](adr/index.md) | Architecture Decision Records, one decision per file |

> **Start at `08-mvp.md`.** It is the authority on what is being built; `06-redesign-plan.md` has been
> re-cut around it and describes the surrounding sequence. The workspace lives on the **`sunorcloud`**
> branch of `geckoprojects-org/org.gecko.weather`, on the **Eclipse Fennec** stack — not Gecko
> ([ADR-0001](adr/0001-greenfield-new-repository.md), [ADR-0002](adr/0002-emf-as-core-model.md)).

## The short version

**The purpose is energy, not weather reporting.** Gecko Weather exists so that a building with a PV
array and a heat pump can be optimised against the weather it will actually receive. That single
purpose is what makes the current implementation insufficient — not that it is badly built, but that
it answers the wrong question.

**The core defect: a forecast location is a weather station.** Today a forecast position is bound to
a DWD MOSMIX station and served that station's forecast. MOSMIX carries the right quantities — the
model already has `cloudCoverTotal/Effective/High/Mid/Low` and `irRadianceGlobal` — but they describe
the *station*, and stations are sparse. For wind or pressure that is tolerable; for cloud cover, the
quantity that decides PV yield, two locations 20 km apart routinely differ at the same hour. → `F-17`

**The fix is fusion at the site.** Combine station point forecasts with gridded radiation and cloud
fields and exact solar geometry, per registered site. The pieces for this already exist in the
workspace but are inert:

- `UVRadiationMeasurement` → `GeoArea(topLeft, xDim, yDim)` — a raster cell was modelled and never
  used in production. → `F-19`
- `org.gecko.weather.netcdf` contains a working spike that decodes **DWD SIS** (satellite global
  radiation, 0.05° grid, NetCDF, with an 18-hour forecast product) — but it iterates the entire
  German grid, producing roughly half a million measurement objects per run to serve a house that
  needs one cell. → `F-19`, and the reason [ADR-0010](adr/0010-subset-on-ingest.md) exists.
- `AstrotimeService` gives sunrise, sunset and twilight but no sun elevation or azimuth — exactly
  what PV needs. → `F-18`

**Three structural blockers**, independent of the site question:

- **DWD is baked into the foundation.** The bundle named `org.gecko.weather.api` contains
  `DWDFetcher`, `DWDEMFFetcher` and a 599-line `DWDUtils`; the central model class is
  `MOSMIXSWeatherReport`, a DWD product name. → `F-1`, `F-2`, `F-3`
- **Nothing survives a restart.** The only storage implementation is a `ConcurrentHashMap`, and both
  Lucene indexes run with `directory.type=ByteBuffer`. Sources are perishable, so what is lost is
  lost for good. → `F-6`, `F-7`
- **Ingest has no operational qualities.** Full download every hour with no conditional GET, no
  retry, no backoff, no health signal, no metrics. → `F-9`, `F-10`

The redesign is a **greenfield rebuild on the `sunorcloud` branch** that keeps what is good — EMF/Ecore as
single source of truth, Lucene search, the DWD decoding knowledge and the SIS spike — and rebuilds the
structure around the site, with durable storage, sparse grid access and traceable values.

The platform moved with it: what was the Gecko OSGi stack is now **Eclipse Fennec**, since GeckoEMF was
donated to the Eclipse Foundation. Same architecture, new coordinates, plus a codec, a persistence layer
and OCL that Gecko did not have.

One thing in the analysis below is now out of date in a good way. It says cloud cover has no gridded
source and that SIS's 18-hour horizon is the best available. Both were wrong: **ICON-D2 carries cloud
cover by layer and direct/diffuse radiation at 2.2 km for a full 48 hours**
([09-source-inventory.md](09-source-inventory.md)). Site accuracy is therefore achievable in the first
deployable version rather than one step after it.

## Decisions taken

| Question | Decision | ADR |
| --- | --- | --- |
| How do we execute the rebuild? | Greenfield on a new branch, `sunorcloud` | [ADR-0001](adr/0001-greenfield-new-repository.md) |
| Do we keep EMF? | Yes — Ecore stays the single source of truth, on the **Eclipse Fennec** stack | [ADR-0002](adr/0002-emf-as-core-model.md) |
| How do we support different sources and formats? | Provider SPI with transport separated from decoder | [ADR-0003](adr/0003-provider-spi.md) |
| How do we get durability? | Durable repository (XMI now, Mongo/JPA prepared), separate rebuildable index | [ADR-0004](adr/0004-persistence-index-split.md) |
| How do we name model concepts? | Provider-neutral, canonical measurement kinds | [ADR-0005](adr/0005-provider-neutral-model.md) |
| What toolchain? | Java 21, bnd 7.4, OSGi R8 | [ADR-0006](adr/0006-java-baseline-toolchain.md) |
| How is ingest scheduled and hardened? | Ingest runtime with conditional GET, retry, per-provider isolation | [ADR-0007](adr/0007-ingest-and-scheduling.md) |
| How is the HTTP API shaped? | **Deferred** — no API in the MVP; analysis kept | [ADR-0008](adr/0008-rest-api-design.md) |
| What is the central domain entity? | The **site**, with a persisted source binding — not the station | [ADR-0009](adr/0009-site-as-central-entity.md) |
| How is gridded data ingested? | Subset-on-ingest: only cells registered sites need | [ADR-0010](adr/0010-subset-on-ingest.md) |
| How do we keep values interpretable? | Lineage and uncertainty as first-class model concepts | [ADR-0011](adr/0011-lineage-and-uncertainty.md) |
| How are asynchronous sources combined? | Configurable priority, supersession instead of overwrite | [ADR-0012](adr/0012-fusion-and-supersession.md) |

## Framing decisions

Premises for everything that follows. These came from the redesign kickoff and are not re-litigated
per document.

- **Purpose: energy optimisation for a specific building.** PV and heat pump self-consumption over a
  24–48 hour horizon. Explicitly *not* a weather portal.
- **The site is the unit of interest**, not the station. A site is registered, its source binding
  (station(s), grid cell per product) is resolved and persisted, and that binding drives ingest.
  Ad-hoc coordinates are answered best-effort from existing data and marked as degraded.
- **Product character: an operable service.** Deployed, monitored, depended upon. This is what makes
  persistence, health and API stability requirements rather than nice-to-haves.
- **Initial focus is DWD, exclusively.** Depth on one service that actually serves the PV case beats
  breadth that serves nobody.
- **European coverage is a design constraint, not a deliverable.** The architecture must not preclude
  MeteoSwiss, GeoSphere Austria, KNMI, Met Norway or Météo-France later — hence provider-neutral
  model, canonical measurement kinds, transport separated from decoding. No second national provider
  is built. Usefully, **DWD alone supplies the format variety** (KML, NetCDF, GRIB2, text) that
  validates the abstraction.
- **Gridded data is core, not a later track.** Cloud and radiation fields are the whole point of
  site accuracy. Access is **subset-on-ingest**: only the cells registered sites need.
- **Computation stage 1 now, stage 2 designed for.** Now: solar position, spatial and temporal
  alignment, fusion, and derived meteorological quantities. Designed for but not built:
  plane-of-array irradiance from panel tilt and azimuth. Never: an electrical yield model, or
  numerical weather prediction.
- **Lineage and uncertainty from the first slice.** Once values can be fused, interpolated or
  derived, a bare number is not interpretable — and provenance cannot be retrofitted, because the
  information no longer exists later.
- **Capacity: roughly one person, alongside project work, no fixed date.** The hardest constraint in
  the dossier. It dictates a **walking-skeleton** sequence — thin end-to-end slices, each
  independently deployable and useful — rather than building layer by layer. See
  [risk R-1](06-redesign-plan.md#risks).

## Conventions

- Documentation language is **English**, matching the code, Javadoc and commit history.
- As-is findings have stable IDs (`F-n`) so requirements, ADRs and plan items can reference the
  analysis without repeating it.
- Requirements have stable IDs by stakeholder (`DEV-n`, `INT-n`, `OPS-n`) plus cross-cutting quality
  requirements (`QR-n`).
- ADRs are immutable once `Accepted`. To change a decision, write a new ADR that supersedes it.
