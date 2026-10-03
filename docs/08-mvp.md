# MVP

The first thing worth deploying. Defined 2026-07-29; **re-cut 2026-10-03** after the purpose was
re-stated — Gecko Weather is a weather backend, energy optimisation is one consumer of it — and after
[ADR-0013](adr/0013-values-per-source.md) took source fusion out of the service.

This document is deliberately narrower than the vision. Where it contradicts another document, it
wins for now and the other document is the thing to correct.

## What the MVP delivers

1. **Sites are registered and get a stable id.** Coordinates, elevation, optional time zone, optional
   manual station assignment. Registration resolves the bindings — nearest MOSMIX station(s) with
   distance, grid cell per gridded product — and persists them.
2. **A report per site, requested by site id, through an in-process OSGi service.** The report holds
   **one dataset per source product**: MOSMIX from the bound station, ICON-D2 from the bound cell, SIS
   once it lands, and a computed solar dataset. **Values are not merged.** Cloud cover for 14:00 appears
   once from the station and once from the grid cell, each with its distance and issue time; the
   consumer decides.
3. **Every value carries its provenance and uncertainty**: provider, product, model run, issue time,
   station or cell with distance, lead time, staleness.
4. **Sources refresh on their own cadence, and the report shows it.** A product's refresh replaces that
   product's dataset only. The superseded dataset is archived, append-only.
5. **Persistence is a configurable local folder of XMI files** — sites, reports, archive, station
   catalogues — behind a narrow `WeatherRepository` interface, so that the Fennec persistence layer
   (Mongo, JPA) or a Lucene index can be added without touching the rest.
6. **Solar position and day events** per site via the NREL SPA algorithm (`solarpositioning`, MIT): sunrise, sunset, twilight, solar noon per
   day; elevation and azimuth per timestep.
7. **Fetchers** retrieve each source with **conditional GET and bounded retry**, map it into the model
   and hand it to the repository.
8. **Horizon is whatever the sources give.** 24–48 h is the window that matters most; MOSMIX reaches
   +240 h and is kept in full, ICON-D2 covers 0–48 h, SIS +18 h.

The model is [`org.gecko.weather.model`](10-model.md), **done 2026-10-03**.

## Decisions

| # | Decision | Status |
| --- | --- | --- |
| M-1 | **Datasets per source — provider, product and station or cell — archived on refresh.** The report is the present; the archive is the history. Nothing is merged. | revised 2026-10-03, [ADR-0013](adr/0013-values-per-source.md) |
| M-2 | **Full provenance per value** — provider, product, model run, issue time, station or grid cell, distance. | unchanged |
| M-3 | The central entity is **`Site`**, not `Geolocation`. A site *has* a position. | unchanged |
| M-4 | A station is **resolved automatically** (nearest, distance recorded); an explicit assignment **overrides**. Several stations per product may be bound, ranked. | unchanged |
| M-5 | **One XMI file per site** for the report, one for the site itself; archive per site and issue; catalogues per product. In a **configurable folder**. | revised 2026-10-03 |
| M-6 | Access is an **in-process OSGi service**, keyed by site id. No HTTP. | unchanged |
| M-7 | Initial `MeasurementKind` set per [09-source-inventory.md](09-source-inventory.md) and [10-model.md](10-model.md): twenty kinds with level / statistic / period / threshold qualifiers. | closed by the model |
| M-8 | **Solar position is in scope** (elevation and azimuth per timestep, day events per day). | unchanged |
| M-9 | Fetchers do **conditional GET plus bounded retry with backoff**. No health endpoint, no metrics, no jitter. | unchanged |
| M-14 | **Libraries that need OSGi-fication are wrapped in this workspace** (UCAR cdm-core + grib, anything else that turns up) and moved to a shared place later if they are not already there. Nothing waits upstream. | new 2026-10-03 |
| M-15 | **Energy-specific computation is an add-on module**, not part of the service. The service stops at meteorological and solar quantities with provenance. | new 2026-10-03 |
| M-17 | **Own weather stations (Bresser, Ecowitt, …) are a source like any other**: a provider with a manual binding, `Origin.LOCAL_STATION`, observations pushed through `WeatherDataSink.append`. The sink is part of the API now; the first local-station provider is Slice 4. | new 2026-10-03 |

### Why M-1 and M-2 matter more than they look

Both are *cheap now and impossible later*. Sources do not serve history: MOSMIX publishes only
`LATEST`, SIS analysis files appear per hour and are gone. A value not recorded when it arrives, and
an origin not captured when it is known, are not recoverable afterwards.

M-1's archive keeps `INT-17` and any later accuracy analysis reachable. M-2 is what makes a report with
two cloud-cover values for the same hour interpretable at all.

### How updates and horizons actually behave

Three properties of the sources shape the report, and none of them is a problem to be solved — they
are the normal case, and the per-source structure exists to show them rather than hide them:

- **Different products cover different ranges.** SIS reaches +18 h, ICON-D2 +48 h, MOSMIX +240 h,
  analyses describe only the present. A report's datasets legitimately end at different instants.
- **Products refresh several times a day, each on its own clock.** A MOSMIX_S value for two hours
  ahead can be replaced within the hour; the ICON-D2 dataset beside it may still be from the previous
  run. Each dataset says when it was issued and how often its product refreshes (`expectedRefresh`),
  so a consumer can tell "fresh" from "stale" without product knowledge.
- **In the report, a product's newest issue wins and its previous one moves to the archive.** The
  report answers "what does each source currently expect?"; the archive answers "what did it expect
  before?". With both, forecast error against the eventual outcome becomes measurable later.

### What M-5 solves for free

One file per site means **exactly one writer per site**, so "persist on change" never needs locking
across a shared resource, and a write is proportional to one report rather than to the whole dataset.

## What the MVP does not contain

- **No HTTP API.** [ADR-0008](adr/0008-rest-api-design.md) stays deferred.
- **No Lucene index.** Without queries beyond "report by site id" there is nothing to index. The
  Fennec `emf.search` integration is an option once there is a query to serve.
- **No merging, no "best value", no selection policy** — not even as a default view. If a consumer
  wants one, it is a separate consumer-side module ([ADR-0013](adr/0013-values-per-source.md)).
- No health endpoint, no metrics, no jitter, no container image.
- No ad-hoc coordinate queries, no derived meteorological quantities beyond solar position.
- No second provider. DWD only.
- No PV or energy computation of any kind.

## What this changes in the dossier

### Fusion is gone, not moved

The 2026-07-29 version of this document moved fusion from read time (ADR-0012) to ingest time and
persisted a merged instance. [ADR-0013](adr/0013-values-per-source.md) removes it altogether. The
consequences that version listed — a merge policy version per value, reprocessing on policy change,
two-dimensional retention — reduce to one: the archive grows per refresh, so retention (`M-10`) has to
bound it by age and by issue depth.

`INT-2` is reworded accordingly in [03-requirements.md](03-requirements.md).

### ADR-0009 holds, with one addition

The site as central entity, with bindings resolved and persisted with their distances, is exactly
[ADR-0009](adr/0009-site-as-central-entity.md). M-4 adds an explicit assignment that overrides automatic
resolution — the nearest station is not always the better one, for instance across a ridge — and it
costs one enumeration value (`BindingOrigin.MANUAL`).

### ADR-0004's reopened backend question is answered

**XMI files in a configurable folder now; Fennec `persistence.mongo` / `persistence.eclipselink` and
`emf.search` prepared.** The `WeatherRepository` boundary is what makes "prepared" mean something.

### `Q-I` is answered, and it moves GRIB2 to the front

See [09-source-inventory.md](09-source-inventory.md). **ICON-D2** delivers cloud cover by layer *and*
direct/diffuse surface radiation at **2.2 km for 0–48 h**, eight times a day — the source that makes
geolocation-accurate values real. So:

- **The GRIB2 decoder is built before NetCDF.** Decided 2026-07-29.
- **The SPI is proven first on MOSMIX KML**, which has no library risk and no cell arithmetic, so the
  GRIB2 decoder only has to prove GRIB2. This replaces the mitigation `R-8` lost.
- **The UCAR wrap (cdm-core + grib) is built here** (M-14), not requested upstream. The old request
  [org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3) is no
  longer on the critical path.

There is also a transfer-volume problem that `OPS-9` as written does not survive — the inventory has
the measured numbers.

## Still open

| # | Question | Depends on |
| --- | --- | --- |
| M-10 | Retention for the archive — by age, by issue depth per product, or both | `Q-B` |
| M-11 | ~~Merge policy scope~~ → which reading helpers the service API offers (timeline per kind, newest issue per product, values at an instant), and whether any of them interpolates | first consumer |
| M-12 | Orphan branch or cut from `snapshot` — moot in practice, `sunorcloud` carries its own history | — |
| M-13 | How far back each DWD product is retained on the server, which bounds the backfill | measurement |
| ~~M-16~~ | ~~Several MOSMIX stations per site by default?~~ **Decided 2026-10-03: yes — several, ranked by distance (`rank 0` nearest); a manual assignment becomes rank 0.** How many is provider configuration. | — |
