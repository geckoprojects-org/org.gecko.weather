# Redesign Plan

Execution plan for the target architecture in [05-architecture-target.md](05-architecture-target.md).

**No dates.** Capacity is roughly one person alongside project work with no deadline (`C-1`), so dated
milestones would be fiction. The plan is ordered by dependency and risk retirement, with effort
classes instead of estimates.

## Shape: walking skeleton

The purpose spans the whole stack — a site forecast needs model, SPI, durable storage, grid handling,
solar geometry and API at once. Building horizontally (all persistence, then all ingest, then all
the API) would produce nothing usable for a long time and is a poor fit for interval work.

So each slice is a **thin end-to-end path that answers a real question**, and each is independently
deployable.

| Slice | Delivers | Vision claim status after it |
| --- | --- | --- |
| **0 — Foundation** | Branch, workspace, CI, baselining | — |
| **MVP** | Registered sites with ids; MOSMIX (station) and ICON-D2 (cell) as separate datasets per site, SIS after; solar; XMI folder; in-process access by site id | **Site accuracy true from the first deployment.** No HTTP, no index, no operational qualities beyond conditional GET and retry |
| **3 — Depth** | Reading helpers across datasets, as-of queries over the archive, temporal interpolation, retention | History and alignment helpers available |
| **4 — Breadth** | HTTP API, observations, ad-hoc coordinates, derived quantities, operational polish | Complete for the stated purpose |

**The MVP replaces what were Slices 1 and 2.** See [08-mvp.md](08-mvp.md) — that document is the
authority on the next step; this one is the surrounding sequence.

**Two things changed the shape of the plan** after `Q-I` was answered
([09-source-inventory.md](09-source-inventory.md)):

- The old Slice 1 was **deliberately less accurate** than the current implementation — station-only
  values, correctly labelled. That step is gone. ICON-D2 delivers gridded cloud *and* radiation at 2.2 km
  for 0–48 h, so the first deployable state is already site-accurate. There is no "deploy something
  worse first".
- **GRIB2 moved from Slice 3.5 onto the critical path**, which cost `R-8` its mitigation. See the risks.

## Effort classes

| Class | Meaning |
| --- | --- |
| **S** | One working session |
| **M** | A few sessions; safely interruptible between them |
| **L** | Many sessions; the dominant item of its slice |

---

## Slice 0 — Foundation

Goal: a real, green, empty workspace, so that no later increment is blocked on tooling.

| # | Increment | Effort | Status |
| --- | --- | --- | --- |
| 0.1 | bnd workspace on the Fennec `cnf` template rather than the old one; Gradle wrapper, licence config | S | **done** — template debris removed, old `weather.model` reference dropped |
| 0.2 | Baseline: Java 21, bnd 7.4, OSGi R8 | S | **done** — [ADR-0006](adr/0006-java-baseline-toolchain.md) |
| 0.3 | CI: Jenkins for build/test/release, GitHub Actions as licence/build/test pre-gate | S | **done** — `DEV-8`, `C-6` |
| 0.4 | `docs/` as the canonical documentation home | S | **done** — `DEV-12` |
| 0.6 | Branch and coordinates | S | **done** — `sunorcloud`, group unchanged ([ADR-0001](adr/0001-greenfield-new-repository.md)) |
| 0.5 | Curated dependency set with a duplicate-version check | M | `DEV-9`, prevents `F-13` recurring — matters more now, because the Fennec libraries are not version-aligned |
| 0.7 | `git init` on `sunorcloud`, commit the workspace | S | **done** — branch pushed, CI green |
| 0.8 | **Unidata repository into `cnf`, UCAR cdm-core + grib wrap bundle *in this workspace*** | M | open, **no longer blocked upstream** — decided 2026-10-03 (`M-14`): libraries that need OSGi-fication are wrapped here and moved out later. The upstream request [org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3) stays as a note |
| 0.9 | bnd 7.4.0 release instead of the snapshot | S | **done** 2026-10-03 |

**Exit criteria.** A trivial bundle builds, CI is green including baselining and the licence check, and a
GRIB2 message from a real ICON-D2 file can be read in a plain JUnit test.

---

## The MVP

Goal, scope and decisions live in [08-mvp.md](08-mvp.md). The increments, re-cut from the old Slices 1
and 2:

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| M.1 | **Model** — `org.gecko.weather.model`: `Site` with bindings, `WeatherReport` with one `SourceDataset` per product, `MeasuredValue` with qualifiers, `Provenance`, `Uncertainty`, `DayInfo`; `java.time` data types via conversion delegate; plain-JUnit XMI round trip. Spec in [10-model.md](10-model.md) | M | **done 2026-10-03** — `INT-5`, `INT-6`, `INT-7`, `QR-11` |
| M.2 | **`api`** — `SiteRegistry` (register with id, assign, rebind, deactivate, remove), `WeatherService` (report, **values of one kind or a `ValueQuery`** — e.g. only temperature, only UV — timeline, archive; all by site id), `Reports`, `SolarService`, `WeatherRepository`; SPI: `WeatherProvider.fetch` once per product for all sites, `SiteBindingResolver` ([ADR-0003](adr/0003-provider-spi.md) revision) | M | **done 2026-10-03** — `DEV-1`, `INT-1`, `INT-12` |
| M.3 | **`repository.file`** — configurable folder: `sites/`, `reports/`, `archive/<site>/<provider>/<product>/`, `catalogs/` as XMI; atomic writes, append-only archive, `evictArchive` | M | **done 2026-10-03** — `OPS-1`, and the precondition for `INT-17` |
| M.4 | **`site`** — registry over the repository, automatic binding resolution with distance plus manual override, `dataCompleteFrom` | M | `INT-1` |
| M.5 | **`provider.dwd.icon`** — conditional-GET transport, **GRIB2 decoder** over the wrap, cell resolver by index arithmetic on plain lat/lon, de-averaging for `aswdir_s`/`aswdifd_s` | **L** | `INT-3`, `OPS-9`; the largest item |
| M.6 | **`provider.dwd.mosmix`** — station catalogue (degrees-and-minutes trap fixed), nearest-station resolver, one KMZ per bound station with conditional requests, decode through the Fennec `net.opengis.kml.model` + `de.dwd.cdc.forecast.model` EMF models (MOSMIX_L only — the all-stations file would need a streaming decoder), ~60 elements mapped to canonical kinds; plus **`transport`** (JDK HttpClient, 304, zip/gzip) | M | **done 2026-10-03** — `F-1`…`F-4` resolved, SPI proven |
| M.7 | **`solar`** — `SolarService` over NREL SPA (`net.e175.klaus:solarpositioning`, MIT, OSGi bundle; replaced Time4J, LGPL, on 2026-10-03): position per instant, `DayInfo` per civil date in the site's zone (the old service used the platform zone and returned sunset for sunrise); the per-timestep `COMPUTED` dataset is assembled in M.8 | S | **done 2026-10-03** — `INT-4`, `V-8`, resolves `F-18` |
| M.8 | ~~`compute.merge`~~ **report assembly = `WeatherDataSink`** — `replace` (new issue: swap the product's dataset, archive the previous) and `append` (streams: rolling window per product, archive in buckets); refresh the solar dataset and `DayInfo` for the horizon; the fetch path and push sources both go through it | M | `INT-2` as reworded, `M-17`, [ADR-0013](adr/0013-values-per-source.md) |
| M.9 | **`ingest`** — per-provider scheduling, conditional GET, bounded backoff | M | `OPS-6`, `OPS-7` |
| M.10 | **In-process service** — report by site id, plus the first reading helpers (timeline per kind across datasets, newest issue per product) | S | `INT-12` in its cheapest form |
| M.11 | **`provider.dwd.sis`** — NetCDF decoder, 0.05° cell resolver | M | high-cadence global radiation |
| M.12 | **`provider.dwd.uv`** — GRIB2, health forecasts | S | completes the quantity set |
| M.13 | **`config` + `runtime`** — Configurator defaults, bndrun, documented volume | M | `OPS-5`, `QR-12` |
| M.14 | Offline fixtures: ICON-D2 `.grib2`, SIS `.nc`, ~~MOSMIX `.kmz`~~ (done, with a station catalogue) | M | `DEV-6`, `QR-6` |
| M.15 | **`wrap.ucar`** — cdm-core + grib as one bundle in this workspace, Unidata repository in `cnf` (was 0.8) | M | `M-14`, unblocks M.5 |

**Sequencing note.** M.6 before M.5 is deliberate even though ICON is the primary source: it proves
transport → decoder → sink → mapper → persist with no library risk and no cell arithmetic, so M.5 only has
to prove GRIB2. This is the replacement mitigation for `R-8`.

**Exit criteria.**

1. A registered site's cloud cover and radiation come from its own 2.2 km ICON-D2 cell; the value states
   the cell and its distance. (`V-1`, `INT-3`)
2. The report holds one dataset per bound product, each with issue time, cadence and horizon; every
   value carries provenance; solar elevation and azimuth are present per timestep. No merged value
   exists. (`V-2`)
3. Restart with data present → identical results from the persisted XMI. (`V-3`)
4. The archive contains the superseded datasets, with issue times. (precondition for `INT-17`, `V-7`)
5. An unchanged upstream file produces no re-download, verified against a fixture. (`V-11`)
6. Decoder and mapping tests run with no network and no OSGi framework start. (`DEV-6`)
7. `aswdir_s` de-averaging verified against a hand-computed expected value. (`QR-6`)
8. Air temperature and wind are marked station-only with the station distance. (`INT-7`)

**Explicitly not delivered:** HTTP API, Lucene index, health endpoint, metrics, jitter, container image,
ad-hoc coordinates.

---

## Slice 2 — folded into the MVP

`Q-I` is answered ([09-source-inventory.md](09-source-inventory.md)) and grid accuracy moved forward into
the MVP, so this slice no longer exists as a separate step. What it contained went as follows:

| Was | Now |
| --- | --- |
| 2.1 Investigation: resolve `Q-I` | **done** — see [09](09-source-inventory.md) |
| 2.2 Grid descriptor in the model | M.1 |
| 2.3 `provider.dwd.sis` — NetCDF | M.11, after ICON |
| 2.4 Backfill on registration, `dataCompleteFrom` | deferred — depends on how far back each product is retained, which is still unknown (`OPS-16`) |
| 2.5 Verify ingest volume scales with sites | folded into M.5, and the answer is uncomfortable: **transfer** cannot scale with sites on a plain file server, only **storage** can. See the volume section in [09](09-source-inventory.md) |

The risk note this slice carried — "if there is no usable gridded cloud product, we still succeed on
radiation alone" — is obsolete. There is one, at 2.2 km.

---

## Slice 3 — Depth

Goal: the history and the reading helpers that make per-source data comfortable to consume, without
ever merging it.

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| 3.1 | ~~Per-kind source priority~~ **Consumer-side selection module**, only if a consumer asks for one by name — never in the report ([ADR-0013](adr/0013-values-per-source.md)) | S | — |
| 3.2 | **As-of querying over the archive** — "what was predicted for tomorrow 08:00, and when" | M | `INT-17` becomes real |
| 3.3 | Staleness surfaced per dataset (`stale`, against `expectedRefresh`) and per-product degradation instead of request failure | S | `INT-8`, `V-5` |
| 3.4 | Temporal interpolation as a reading helper where product timesteps do not align (15-minute SIS against hourly ICON) | M | `INT-3` completeness |
| 3.5 | Retention for the archive — by age and by issue depth per product (`M-10`, `Q-B`) | M | `OPS-8` |

**What changed here.** Fusion is gone (ADR-0013), so 3.1 is no longer a core increment. The old 3.6
(mixed horizon beyond +48 h) needs nothing: datasets end where their products end, and the consumer sees
MOSMIX continuing past ICON-D2 by itself.

**Exit criteria.** An as-of query returns what was known at a chosen time; a stale dataset is marked
as such; retention keeps the archive bounded.

---

## Slice 4 — Breadth

Everything valuable that no longer changes the architecture. Order by whichever project asks first.

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| 4.1 | Derived quantities — dew point, apparent temperature, direct/diffuse split where a source lacks it (scope from `Q-G`) | M | Derivation complete |
| 4.2 | Ad-hoc coordinate queries, marked degraded | M | `INT-13` |
| 4.3 | Observation provider — enables checking forecasts against reality | **L** | Closes the `F-5` gap |
| 4.4 | **HTTP API** — reopen [ADR-0008](adr/0008-rest-api-design.md), which is deferred; plus push notification per site | **L** | `INT-2`, `INT-9`…`INT-11`, `INT-14`. Blocks any external consumer, so it gates cutover |
| 4.5 | **`index.lucene`** — our own integration against the Lucene wraps, `rebuildFrom` the repository | M | `OPS-2`, needed once queries exist |
| 4.6 | Health endpoint, metrics, jitter, failure isolation, backup/restore, upgrade path, footprint | M | `OPS-3`, `OPS-4`, `OPS-11`, `OPS-13`…`OPS-15` |
| 4.7 | Resolve `QR-10` / `Q-A`: authentication, or a documented decision to delegate it | S–M | `QR-10` |
| 4.8 | Site attributes for tilt and azimuth, add-on module preparation only | S | `INT-16`, `Q-J` |
| 4.9 | **First local-station provider** — Ecowitt gateway upload (HTTP POST in the device's format) or Bresser, mapped to canonical kinds, `Origin.LOCAL_STATION`, pushed through `WeatherDataSink.append`; manual binding by device id | M | `M-17`; the own station beside the forecasts |

## Deferred indefinitely

Not scheduled, not designed for beyond not being precluded. Each would be a new planning
conversation.

- Radar (RADOLAN) ingest and nowcasting
- A second national provider (`Q-E`)
- Stage-2 plane-of-array irradiance
- Statistical post-processing and bias correction against stored observations
- PV add-on modules (plane-of-array irradiance) — on the service, never in it
- Server-backed repository (Fennec persistence) or Lucene index (`emf.search`) — only if `Q-B` /
  `Q-C` answers or a real query demand it
- Multi-instance operation

---

## Where the 38 Musts land

The requirement count is only manageable because the Musts distribute across steps. Nothing is required
of the MVP that the MVP does not deliver.

| Step | Musts satisfied |
| --- | --- |
| 0 | `DEV-8`, `DEV-9`, `DEV-11` |
| MVP | `DEV-1`…`DEV-7`, **`INT-1`…`INT-7`**, `OPS-1`, `OPS-5`, `OPS-6`, `OPS-7`, `OPS-9` (storage half), `QR-6`, `QR-7`, `QR-8`, `QR-11` |
| 3 | `OPS-8`, completes `INT-8` |
| 4 | `INT-9`, `INT-10`, `OPS-2`, `OPS-3`, `OPS-4`, `OPS-10`, `QR-1`, `QR-3`, `QR-4`, `QR-10` |
| Ongoing | `QR-5` (retention once `Q-B` is answered) |

**`INT-3` — the requirement the whole redesign exists for — is in the MVP.** Under the old plan it landed
in Slice 2, one step after the first deployment. Because ICON-D2 turned out to carry cloud and radiation
gridded for the full horizon, it now lands with the first thing that runs.

Three Musts moved *later* than the old plan had them, and that is a deliberate consequence of deferring
the HTTP API: `INT-9`/`INT-10` (versioned API, OpenAPI) and `OPS-3`/`OPS-4` (health, failure isolation)
are Slice 4 now. `QR-1` (availability) and `QR-4` (recovery) go with them, since both are stated in terms
of an API answering. **The MVP is therefore not an operable service by `QR-*` standards** — it collects
and persists correctly, and nothing depends on it yet.

## Definition of done, per slice

A slice is done when all of the following hold. Partial slices are fine to *pause* in, never to
*declare*.

1. Every exit criterion is demonstrated, not argued.
2. Tests for new decoders, mappings and derivations run offline. (`DEV-6`)
3. CI green including baselining and the licence check. (`DEV-8`)
4. The runtime starts, serves and survives a restart. (`OPS-1`)
5. Documentation updated in the same change — `docs/` and the affected bundle README.
6. No dead code, no commented-out components. (`DEV-7`)

## Next step

*Updated 2026-10-03.*

**Done this session:** the purpose was re-stated (weather backend; energy is a consumer; PV is an add-on
module), [ADR-0013](adr/0013-values-per-source.md) replaced fusion with per-source datasets, **M.1 the
model is built** (`org.gecko.weather.model`, [10-model.md](10-model.md), plain-JUnit XMI round trip
green), and the workspace runs bnd 7.4.0 release. The `DEV-5` question from the previous note is
unchanged and still not blocking: the first mapper can be hand-written against the model and the
mechanism chosen when the second provider needs the same metadata.

**M.2, the `api` bundle, is done** (same day): `SiteRegistry`, `WeatherService` with `ValueQuery`
("only temperature", "only UV", any window, any subset of products), `Reports`, `WeatherRepository`,
`SolarService`, and the SPI re-cut to one `WeatherProvider.fetch` per product and run for all bound
sites ([ADR-0003](adr/0003-provider-spi.md) revision). 9 plain-JUnit tests.

**M.3, `repository.file`, is done** (same day): `XmiFolderRepository` as plain core, DS component with
metatype `root`, 12 plain-JUnit tests against a temp folder. Also added the same day: `M-17` — own
weather stations (Bresser, Ecowitt) are a source like any other; `Origin.LOCAL_STATION` in the model,
`WeatherDataSink` (`replace` / `append`) in the SPI as the one way data enters a report.

**M.7, `solar`, is done** (same day): `SpaSolarService`, 8 tests against geometry
(`90° − |φ − δ|`, solstice day lengths, civil date in Berlin and Tokyo, polar night, midnight sun).

**M.4 + M.8 (`core`) and M.6 (`provider.dwd.mosmix` + `transport`) are done** (same day). Two
findings from the old code, both fixed and tested here: the MOSMIX station catalogue gives
coordinates as degrees and minutes (`50.59` = 50°59′), which the old implementation read as decimals;
and KML coordinates are `lon,lat`, which it read the other way round. 83 plain-JUnit tests across
seven bundles.

**Next is M.9, the ingest runtime** — the piece that turns the bundles into a running service: per
`WeatherProvider` a scheduled job on `expectedRefresh`, collecting the active sites' bindings for that
product, `fetch` with the persisted `SourceState`, results through `WeatherDataSink.replace`, bounded
exponential backoff on `IOException`, no retry on `FetchException` until the next schedule, per-value
`skipped` counts to the log. `SourceState` persistence belongs in the repository (one more small
method pair) so a restart does not re-download everything. Then M.13 (`runtime`: Configurator JSON
with the repository root, a `launch.bndrun`, first end-to-end run against DWD), then M.15 (UCAR wrap)
and M.5 (ICON-D2).

Still open, none of it blocking: `M-10` (archive retention, needs `Q-B`), `M-11` (which reading helpers),
`M-16` (several stations per site by default?), the UV product's exact grid, and how long each DWD
product stays on the server (`OPS-16`). `05-architecture-target.md` still describes a `compute.fusion`
layer and Gecko bundle names; it is the next document to re-cut.

## Resuming after a gap

The dominant failure mode for this project is not a wrong decision, it is **a resumption that costs a
week of re-reading**. Two cheap habits, treated as part of the work rather than overhead:

- **Leave the next step written down.** Each session ends by recording the next increment and any
  half-finished decision in the plan or an ADR — not in a commit message, and not in memory.
- **Keep `docs/` true.** A documentation set that lags the code becomes the thing nobody trusts
  (`F-15` is what that looks like after two years). Updating it is part of the slice, per the
  definition of done.

---

## Risks

| # | Risk | Impact | Mitigation |
| --- | --- | --- | --- |
| **R-1** | **Scope exceeds capacity.** The vision is a substantial system; capacity is one person alongside project work, no deadline. | The rebuild stalls half-finished while the old service still runs — the worst outcome, because effort is spent and nothing is gained. | MVP-first sequencing: the MVP must reach a deployable, useful state before anything else starts. Every step independently shippable. Focus already narrowed hard: DWD only, no second provider, no yield model, no radar. Revisit whenever capacity changes. |
| ~~R-2~~ | ~~No usable gridded cloud-cover product.~~ | — | **Retired 2026-07-29.** ICON-D2 publishes `clct`/`clcl`/`clcm`/`clch` at 2.2 km, 0–48 h, 8×/day. |
| ~~R-3~~ | ~~SIS's +18 h horizon is shorter than the 24–48 h window.~~ | — | **Retired 2026-07-29.** ICON-D2 also carries direct and diffuse surface radiation for the full 48 h, at finer resolution than SIS. No MOSMIX patching needed inside the window. |
| **R-11** | **Transfer volume.** No server-side subsetting exists on DWD Open Data, so the whole field must be downloaded to read one cell: ~175 MB per ICON-D2 run for 6 parameters, ~1.4 GB/day at 8 runs. | Bandwidth cost, and impoliteness towards a shared open-data server (`S-4`). | `OPS-9` splits: storage scales with sites, transfer cannot. Levers are all "fetch less" — fewer runs per day, only mapped parameters, hourly only where it matters. Decide the number deliberately; log what was fetched. |
| **R-12** | **Averaged radiation fields read as instantaneous.** `aswdir_s` is product template 8, statistical process 0 — averaged since model start. Reading it raw produces plausible, wrong numbers. | Silently wrong PV input, the worst failure mode for this service. | De-averaging implemented once in the ICON mapper, with a test against a hand-computed expected value (`QR-6`, exit criterion 7 of the MVP). |
| R-4 | Subset-on-ingest is irreversible: a site added later has no history, and sources rarely allow retrospective retrieval. | New sites are permanently poorer than old ones for accuracy analysis. | Best-effort backfill at registration and a recorded `dataCompleteFrom` (`OPS-16`). Documented as a known cost in [ADR-0010](adr/0010-subset-on-ingest.md), not hidden. |
| R-5 | File-based repository insufficient once `Q-B` retention and `Q-C` site count are answered. | Repository replacement mid-project. | The `WeatherRepository` interface is the boundary; swapping the implementation touches one bundle. Answer `Q-B`/`Q-C` before sizing the raw record. |
| R-6 | Greenfield abandonment: the `sunorcloud` branch stalls while the old one remains in production. | Two half-systems to maintain. | The old branches are untouched and keep running until the MVP demonstrably beats them. No migration commitment before then ([07-migration.md](07-migration.md)). |
| R-7 | Kind-keyed values create many small EMF objects; creation cost at volume. | Ingest or query latency. | Subset-on-ingest keeps volumes tiny — order of 1,500 values per site per run. Measure once ICON-D2 ingest runs rather than optimising speculatively. |
| R-8 | GRIB2 streaming decode is harder than NetCDF and may need a further library with its own OSGi packaging problems. | **Now on the critical path, not in Slice 3.5.** | **The original mitigation is void.** It relied on NetCDF proving the streaming SPI first, but `Q-I` showed ICON-D2 (GRIB2) is the primary source and it goes first ([09-source-inventory.md](09-source-inventory.md)). Replacement: prove the SPI on a non-gridded source — MOSMIX KML or the station catalogue — so the GRIB2 decoder validates only GRIB2. Establish first whether one library covers GRIB2 *and* NetCDF. See [08-mvp.md](08-mvp.md). |
| R-9 | Single maintainer; bus factor of one. | Project stops entirely. | Documentation-as-deliverable, ADRs capturing *why*, offline-runnable tests. This dossier is part of the mitigation. |
| R-10 | Model evolution: adding a `MeasurementKind` or changing `Provenance` affects stored data. | Migration burden on every model release. | Repository stores a model version per record; upgrade path documented (`OPS-13`) before the first breaking model change, not after. |

**R-1 is the risk that matters.** The others are technical and have technical answers. R-1 is
structural, and the only real mitigation is the discipline that each slice must be genuinely
deployable — so that stopping is always an acceptable outcome rather than a loss.
