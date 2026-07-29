# MVP

The first thing worth deploying. Defined 2026-07-29, after the technology stack moved to Eclipse Fennec
and the plan in [06-redesign-plan.md](06-redesign-plan.md) had begun to drift from what is actually
being built.

This document is deliberately narrower than the vision. Where it contradicts
another document, it wins for now and the other document is the thing to correct.

## What the MVP delivers

1. **Cloud cover, UV and the other quantities the sources carry, accurate to a site** — not to a weather
   station. With a forecast as far ahead as the data allows; the horizon is whatever the sources give,
   not a target we set.
2. **Values for one site are merged across sources.** One site, one coherent set of values, assembled
   from whichever sources cover it.
3. **The merged model instance is persisted whenever it changes**, as an XMI file — and the incoming
   source values are additionally **appended to a raw record**.
4. **Every value carries its provenance**: provider, product, model run, and the station or grid cell it
   came from with its distance from the site.
5. **Solar position** — elevation and azimuth — per site and timestep, alongside the day events that
   already exist.
6. **Fetchers** retrieve data from each source with **conditional GET and bounded retry**, and transfer
   it into the model instance.
7. **The model is reachable in-process** as an OSGi service.
8. **A persistence layer for Mongo or JPA is prepared** — not built, but the seam exists so that
   swapping the file store for a backend is a bounded change.

The **weather data model may be reworked or replaced outright** to serve this. Nothing in the current
model is protected.

## Decisions

Taken 2026-07-29. These were the open questions; they are now answers.

| # | Decision |
| --- | --- |
| M-1 | **Merged instance plus an append-only record of raw source values.** The merged instance is what consumers read; the raw record is written alongside and queried by nobody yet. |
| M-2 | **Full provenance per value** — provider, product, model run, station or grid cell, distance. |
| M-3 | The central entity is **`Site`**, not `Geolocation`. A site *has* a geo position; naming the entity after its coordinate would be confusing. |
| M-4 | A station is **resolved automatically** (nearest, with the distance recorded) and an explicit assignment **overrides** the result. |
| M-5 | **One XMI file per site** for the merged instance; the raw record separately per site and time bucket. |
| M-6 | Access is an **in-process OSGi service**. No HTTP. |
| M-7 | **Closed** by the source inventory ([09-source-inventory.md](09-source-inventory.md)): cloud cover by four layers, direct and diffuse radiation and global radiation grid-accurate; UV index coarser; air temperature and wind station-only. |
| M-8 | **Solar position is in scope** (elevation and azimuth). |
| M-9 | Fetchers do **conditional GET plus bounded retry with backoff**. No health endpoint, no metrics, no jitter. |

### Why M-1 and M-2 matter more than they look

Both are *cheap now and impossible later*. Sources do not serve history: MOSMIX publishes only `LATEST`,
SIS analysis files appear per hour and are gone. So a value not recorded when it arrives, and an origin
not captured when it is known, are not recoverable afterwards by any amount of later work.

M-1 keeps `INT-17` and any later accuracy analysis or bias correction reachable. M-2 makes a merged
number interpretable at all — for a site whose cloud cover comes from a grid cell and whose wind comes
from a station 20 km away, "which of these is trustworthy?" has no answer without it.

### How updates and horizons actually behave

Three properties of the sources shape the merge, and none of them is a problem to be solved — they are
the normal case:

- **Different sources cover different forecast ranges**, and some carry no forecast at all. SIS reaches
  +18 h; MOSMIX goes further; analysis products describe only the present. So a site's timeline is
  legitimately assembled from different sources at different distances into the future, and it simply
  ends where the data ends.
- **Sources refresh several times a day.** A prediction for tomorrow 08:00 is replaced by a newer one
  with each fresh dataset, presumably more accurate because it was made closer to the event.
- **In the merged instance, the newest value wins and the old one is gone.** That is correct and
  intended. The merged instance answers "what do we currently expect?", and a superseded prediction is
  not part of that answer.

The history lives in the raw record (M-1), which is what turns "presumably more accurate" into something
checkable: with both the old and the new prediction stored alongside their issue times, forecast error
against the eventual outcome is measurable later. Without M-1 that claim could only ever be assumed.

Two things follow for the merge itself:

- It is keyed by `(site, kind, validAt)`, and per key the **newest issue time wins** unless policy says
  a different source outranks it — which is the normal case for radiation, where a grid cell beats a
  station forecast regardless of which arrived later.
- Where the timeline crosses from one source to another mid-horizon — grid up to +18 h, station beyond —
  the crossing must be visible rather than hidden in a smooth-looking curve. Per-value provenance (M-2)
  is what makes it visible; there is no separate mechanism needed.

### What M-5 solves for free

One file per site means **exactly one writer per site**, so the concurrency problem that "persist on
change" would otherwise create never arises — no locking over a shared XMI resource, and write size stays
proportional to one site rather than to the whole dataset.

## What the MVP does not contain

- **No HTTP API.** [ADR-0008](adr/0008-rest-api-design.md) is deferred; how the interface is cut is
  decided when one is needed.
- **No Lucene index.** Without queries and without an API there is nothing to accelerate, so the
  hand-written integration [ADR-0004](adr/0004-persistence-index-split.md) calls for is out of scope.
  The repository/index split stays valid design; it is simply not needed yet.
- No health endpoint, no metrics, no jitter, no container image.
- No ad-hoc coordinate queries, no derived quantities beyond solar position.
- No second provider. DWD only.

## What this changes in the dossier

### Fusion happens at ingest, not on read — and that is now safe

[ADR-0012](adr/0012-fusion-and-supersession.md) decided fusion is **computed on read**. The MVP
materialises it at ingest instead: the merged instance is persisted and updated on change.

M-1 is what makes that acceptable rather than lossy. Because the raw source values are kept
append-only, a merged instance is **recomputable** — so a later change of merge priority is a
reprocessing job rather than a permanent inconsistency, and the question "what was predicted, and when"
remains answerable from the raw record even though the merged instance no longer shows it.

The division of labour is therefore clean, and worth stating in one line: **the merged instance is
current state, the raw record is history.** Overwriting in the former is intended; nothing is ever
overwritten in the latter.

Two consequences follow and should be honoured in the MVP, not deferred:

- The merged value must record **which merge policy version produced it** ([`QR-7`](03-requirements.md)),
  otherwise it cannot be recomputed identically.
- Retention now has two dimensions, because the raw record grows with every revision, not just with every
  timestep.

### ADR-0009 holds, with one addition

The site as central entity, with a station binding resolved and persisted along with its distance, is
exactly [ADR-0009](adr/0009-site-as-central-entity.md). M-4 adds something that ADR did not have: an
**explicit assignment that overrides** automatic resolution. Worth having — the nearest station is not
always the better one, for instance across a ridge — and it costs one optional field.

### ADR-0004's reopened backend question is answered

**XMI file per site now, Fennec `persistence.mongo` or `persistence.eclipselink` prepared.** That is the
first of the three candidates listed there, with the Fennec persistence layer as the prepared path rather
than a rejected one. The `WeatherRepository` boundary is what makes "prepared" mean something.

### `Q-I` is answered, and it moves GRIB2 to the front

See [09-source-inventory.md](09-source-inventory.md). The short version: **ICON-D2** delivers cloud cover
by layer *and* direct/diffuse surface radiation at **2.2 km for the full 0–48 h**, eight times a day. SIS
adds high-cadence global radiation for now and +18 h. UV comes from the health forecasts, once daily and
on a coarser grid.

Two consequences for the MVP:

- **The MVP's central claim is achievable for every quantity that matters to PV.** Cloud and radiation are
  grid-accurate; only air temperature and wind stay station-only, and they are labelled as such.
- **The GRIB2 decoder is built first — ICON before NetCDF.** Decided 2026-07-29. ICON-D2 carries cloud
  *and* radiation for the whole horizon, so it is the source that makes the MVP's claim true; SIS follows
  and adds cadence rather than coverage.

  The cost of that ordering is that risk `R-8`'s mitigation is gone. It read: *"NetCDF (Slice 2) proves
  the streaming SPI first, so the SPI is not also being validated at the same time."* Now the first
  gridded decoder validates three things at once — the SPI shape, a GRIB2 library and its OSGi packaging,
  and grid-cell index arithmetic. If it goes wrong, which of the three is at fault is not obvious.

  **A cheaper replacement mitigation exists**: prove the SPI with a decoder that is not gridded at all.
  MOSMIX KML via `ecore.xmi`, or the station catalogue, exercises transport → decoder → sink → mapper →
  persist end to end with no library risk and no index arithmetic. Then the GRIB2 decoder only has to
  prove GRIB2. That keeps ICON first in the sense that matters — it is the first *gridded* source and the
  first real payload — while removing the three-unknowns-at-once problem.

There is also a transfer-volume problem that `OPS-9` as written does not survive — the inventory has the
measured numbers.

### Where this sits relative to the slices

Roughly the old **Slices 1 and 2 combined**, minus REST, minus the operational qualities, plus
solar position pulled forward. The existing slice structure is no longer an accurate description of the
next step, so [06-redesign-plan.md](06-redesign-plan.md) needs re-cutting against this document rather
than being read alongside it.

## Still open

| # | Question | Depends on |
| --- | --- | --- |
| M-10 | Retention for the raw record — by age, by revision depth, or both | `Q-B` |
| M-11 | Whether the merge policy is global or per site | `Q-F` |
| M-12 | Orphan branch or cut from `snapshot` | — |
| M-13 | How far back each DWD product is retained on the server, which bounds the backfill | measurement |
