# Gecko Weather — Redesign Dossier

Entry point for the complete redesign of Gecko Weather. Read the documents in the order below; each
builds on the previous.

## Reading order

| # | Document | Answers |
| --- | --- | --- |
| 1 | [01-vision.md](01-vision.md) | What are we building, for whom, and what are we deliberately not building? |
| 2 | [02-stakeholders.md](02-stakeholders.md) | Who cares, what do they need, what hurts them today? |
| 3 | [03-requirements.md](03-requirements.md) | Requirements per stakeholder, prioritised and traceable |
| 4 | [04-architecture-current.md](04-architecture-current.md) | Honest as-is analysis; findings `F-1` … `F-20` |
| 5 | [05-architecture-target.md](05-architecture-target.md) | Target architecture: bundles, SPI contracts, data flow |
| 6 | [06-redesign-plan.md](06-redesign-plan.md) | Sequence around the MVP, exit criteria, effort classes, risks |
| 7 | [07-migration.md](07-migration.md) | What to salvage from the old branches, and the cutover |
| **8** | **[08-mvp.md](08-mvp.md)** | **What is actually being built first — read this before the plan** |
| **9** | **[09-source-inventory.md](09-source-inventory.md)** | Which DWD products deliver what, measured — the answer to `Q-I` |
| **10** | **[10-model.md](10-model.md)** | The model: classes, qualifiers, canonical units, mapping conventions |
| — | [adr/index.md](adr/index.md) | Architecture Decision Records, one decision per file |

> **Start at `08-mvp.md`.** It is the authority on what is being built; `06-redesign-plan.md` has been
> re-cut around it and describes the surrounding sequence. The workspace lives on the **`sunorcloud`**
> branch of `geckoprojects-org/org.gecko.weather`, on the **Eclipse Fennec** stack — not Gecko
> ([ADR-0001](adr/0001-greenfield-new-repository.md), [ADR-0002](adr/0002-emf-as-core-model.md)).

## The short version

**Gecko Weather is a weather backend.** It answers "what weather does *this exact location* get, from
every source that covers it?" — and it answers it for registered sites, with every value stating where
it came from and how far away that was. Energy optimisation of a building with PV and a heat pump is
the first consumer of that data and the reason the project exists; it is not the service itself.
PV-specific computation is an add-on module on top.

**The core defect of the current implementation: a forecast location is a weather station.** Today a
forecast position is bound to a DWD MOSMIX station and served that station's forecast. MOSMIX carries
the right quantities — but they describe the *station*, and stations are sparse. For wind or pressure
that is tolerable; for cloud cover, two locations 20 km apart routinely differ at the same hour. → `F-17`

**The fix is to bring geolocation-accurate sources alongside the station.** ICON-D2 publishes cloud
cover by layer and direct/diffuse radiation at 2.2 km for 48 hours; SIS gives satellite global radiation
at 5 km every 15 minutes ([09-source-inventory.md](09-source-inventory.md)). A registered site is bound
to its MOSMIX station *and* to its grid cell in each gridded product, and its report carries the values
of each source side by side — **not merged**. The consumer sees that cloud cover at 14:00 is 62 % from
the cell 640 m away and 75 % from the station 8.9 km away, and decides
([ADR-0013](adr/0013-values-per-source.md)).

**Three structural blockers**, independent of the site question:

- **DWD is baked into the foundation.** The bundle named `org.gecko.weather.api` contains
  `DWDFetcher`, `DWDEMFFetcher` and a 599-line `DWDUtils`; the central model class is
  `MOSMIXSWeatherReport`, a DWD product name. → `F-1`, `F-2`, `F-3`
- **Nothing survives a restart.** The only storage implementation is a `ConcurrentHashMap`, and both
  Lucene indexes run with `directory.type=ByteBuffer`. Sources are perishable, so what is lost is
  lost for good. → `F-6`, `F-7`
- **Ingest has no operational qualities.** Full download every hour with no conditional GET, no
  retry, no backoff, no health signal, no metrics. → `F-9`, `F-10`

The redesign is a **greenfield rebuild on the `sunorcloud` branch** that keeps what is good — EMF/Ecore
as single source of truth, the DWD decoding knowledge, the SIS spike, Time4J for solar geometry — and
rebuilds the structure around the site, with durable storage, sparse grid access and traceable values.

The platform moved with it: what was the Gecko OSGi stack is now **Eclipse Fennec**, since GeckoEMF was
donated to the Eclipse Foundation. Same architecture, new coordinates, plus a codec, a persistence
layer, OCL/QVT and a Lucene integration that Gecko did not have.

## Decisions taken

| Question | Decision | ADR |
| --- | --- | --- |
| How do we execute the rebuild? | Greenfield on a new branch, `sunorcloud` | [ADR-0001](adr/0001-greenfield-new-repository.md) |
| Do we keep EMF? | Yes — Ecore stays the single source of truth, on the **Eclipse Fennec** stack | [ADR-0002](adr/0002-emf-as-core-model.md) |
| How do we support different sources and formats? | Provider SPI with transport separated from decoder | [ADR-0003](adr/0003-provider-spi.md) |
| How do we get durability? | Durable repository (XMI folder now, Fennec persistence / Lucene prepared) | [ADR-0004](adr/0004-persistence-index-split.md) |
| How do we name model concepts? | Provider-neutral, canonical measurement kinds with qualifiers | [ADR-0005](adr/0005-provider-neutral-model.md) |
| What toolchain? | Java 21, bnd 7.4, OSGi R8 | [ADR-0006](adr/0006-java-baseline-toolchain.md) |
| How is ingest scheduled and hardened? | Ingest runtime with conditional GET, retry, per-provider isolation | [ADR-0007](adr/0007-ingest-and-scheduling.md) |
| How is the HTTP API shaped? | **Deferred** — no API in the MVP; analysis kept | [ADR-0008](adr/0008-rest-api-design.md) |
| What is the central domain entity? | The **site**, with persisted source bindings — not the station | [ADR-0009](adr/0009-site-as-central-entity.md) |
| How is gridded data ingested? | Subset-on-ingest: only cells registered sites need | [ADR-0010](adr/0010-subset-on-ingest.md) |
| How do we keep values interpretable? | Lineage and uncertainty as first-class model concepts | [ADR-0011](adr/0011-lineage-and-uncertainty.md) |
| ~~How are asynchronous sources combined?~~ | ~~Configurable priority, supersession instead of overwrite~~ | ~~[ADR-0012](adr/0012-fusion-and-supersession.md)~~ superseded |
| How are several sources presented? | **Per source, not merged.** Datasets per product, replaced on refresh and archived; the consumer combines | [ADR-0013](adr/0013-values-per-source.md) |

## Framing decisions

Premises for everything that follows. Revised 2026-10-03; the earlier version framed the service as
an energy tool, which it is not.

- **It is a weather service.** Geolocation-accurate weather data for registered sites, from every
  source that covers them, with provenance. Consumers: energy optimisation first, anything else that
  needs weather for a place second.
- **Energy and PV computation are add-on modules**, built on the service's data and never inside it.
  The service stops at meteorological and solar quantities.
- **Horizon: whatever the sources give.** 24–48 h is the window that matters most; MOSMIX's +240 h is
  kept rather than cut.
- **The site is the unit of interest**, not the station. A site is registered, gets an id, its
  bindings (station(s), grid cell per product) are resolved and persisted, and reports are requested by
  that id. Ad-hoc coordinates are a later, degraded mode.
- **Both station and grid, side by side.** MOSMIX via the bound station *and* gridded products via the
  bound cell, in one report, each value labelled with source, distance and issue time. No merging.
- **Product character: an operable service.** Deployed, monitored, depended upon.
- **Initial focus is DWD, exclusively.** DWD alone supplies the format variety (KML, GRIB2, NetCDF,
  text) that validates the provider abstraction.
- **European coverage is a design constraint, not a deliverable.** Provider-neutral model, canonical
  kinds, transport separated from decoding; no second national provider is built.
- **Gridded data is core.** Subset-on-ingest: only the cells registered sites need.
- **Solar geometry is core**, via Time4J: day events per day, elevation and azimuth per timestep.
- **Lineage and uncertainty from the first slice.** Provenance cannot be retrofitted.
- **Libraries that need OSGi-fication are wrapped in this workspace** and moved out later. Nothing waits
  upstream.
- **Capacity: roughly one person, alongside project work, no fixed date.** Hence a
  **walking-skeleton** sequence of thin, independently useful slices. See
  [risk R-1](06-redesign-plan.md#risks).

## Conventions

- Documentation language is **English**, matching the code, Javadoc and commit history.
- As-is findings have stable IDs (`F-n`) so requirements, ADRs and plan items can reference the
  analysis without repeating it.
- Requirements have stable IDs by stakeholder (`DEV-n`, `INT-n`, `OPS-n`) plus cross-cutting quality
  requirements (`QR-n`).
- ADRs are immutable once `Accepted`. To change a decision, write a new ADR that supersedes it.

## Known drift

Documents that still read as written at the kickoff and have not been revised for the Fennec stack or
the 2026-10-03 reframing: `05-architecture-target.md` (names Gecko bundles throughout and a
`compute.fusion` layer that no longer exists) and `07-migration.md`. `01-vision.md`, `03-requirements.md`
(`INT-2`), `06-redesign-plan.md` and `08-mvp.md` are current.
