# Redesign Plan

Execution plan for the target architecture in [05-architecture-target.md](05-architecture-target.md).

**No dates.** Capacity is roughly one person alongside project work with no deadline (`C-1`), so dated
milestones would be fiction. The plan is ordered by dependency and risk retirement, with effort
classes instead of estimates.

## Shape: walking skeleton

The purpose spans the whole stack — a site forecast needs model, SPI, durable storage, grid handling,
solar geometry and API at once. Building horizontally (all persistence, then all ingest, then all
fusion) would produce nothing usable for a long time and is a poor fit for interval work.

So each slice is a **thin end-to-end path that answers a real question**, and each is independently
deployable.

| Slice | Delivers | Vision claim status after it |
| --- | --- | --- |
| **0 — Foundation** | Branch, workspace, CI, baselining | — |
| **MVP** | One site, ICON-D2 gridded cloud and radiation, SIS, solar position, merged + raw persisted, in-process access | **Site accuracy true from the first deployment.** No HTTP, no index, no operational qualities beyond conditional GET and retry |
| **3 — Fusion depth** | As-of querying over the raw record, temporal interpolation, mixed-horizon beyond +48 h | Continuous fused timeline true |
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
| 0.7 | **`git init` on `sunorcloud`, commit the workspace** | S | **next**; orphan-vs-branched still open |
| 0.8 | **Unidata repository into `cnf`, `edu.ucar:grib` added to the `org.gecko.ucar.netcdf` wrap** | M | requested upstream: [org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3) |

**Exit criteria.** A trivial bundle builds, CI is green including baselining and the licence check, and a
GRIB2 message from a real ICON-D2 file can be read in a plain JUnit test.

---

## The MVP

Goal, scope and decisions live in [08-mvp.md](08-mvp.md). The increments, re-cut from the old Slices 1
and 2:

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| M.1 | **Model v0** — `Site`, `SourceBinding`, `MeasuredValue`, `Provenance` (incl. licence/attribution), `Uncertainty`, `GeoPosition`, `GridRef`; `MeasurementKind` per [09](09-source-inventory.md); builder + validator so no value exists without provenance | M | `INT-5`, `INT-6`, `INT-7`, `QR-11` |
| M.2 | **`api` SPI packages** — provider, transport, decoder, mapper, binding, repository, solar | M | `DEV-1`, `DEV-3` |
| M.3 | **`repository.file`** — XMI per site for the merged state, append-only raw record per site and time bucket | M | `OPS-1`, and the precondition for `INT-17` |
| M.4 | **`site`** — registry, automatic binding resolution with distance plus manual override, `dataCompleteFrom` | M | `INT-1` |
| M.5 | **`provider.dwd.icon`** — conditional-GET transport, **GRIB2 decoder** over the wrap, cell resolver by index arithmetic on plain lat/lon, de-averaging for `aswdir_s`/`aswdifd_s` | **L** | `INT-3`, `OPS-9`; the largest item |
| M.6 | **`provider.dwd.mosmix`** — KML via `ecore.xmi`, nearest-station resolver. Also the cheap way to prove the SPI before M.5 | M | `F-1`…`F-4` resolved |
| M.7 | **`solar`** — `SolarPositionService`, elevation and azimuth plus day events, via Time4J | S | `INT-4`, resolves `F-18` |
| M.8 | **`compute.merge`** — merge per `(site, kind, validAt)`, newest issue time wins unless policy outranks, policy version recorded | M | `INT-2`, `QR-7` |
| M.9 | **`ingest`** — per-provider scheduling, conditional GET, bounded backoff | M | `OPS-6`, `OPS-7` |
| M.10 | **In-process service** exposing the merged model | S | `INT-12` in its cheapest form |
| M.11 | **`provider.dwd.sis`** — NetCDF decoder, 0.05° cell resolver | M | high-cadence global radiation |
| M.12 | **`provider.dwd.uv`** — GRIB2, health forecasts | S | completes the quantity set |
| M.13 | **`config` + `runtime`** — Configurator defaults, bndrun, documented volume | M | `OPS-5`, `QR-12` |
| M.14 | Offline fixtures: ICON-D2 `.grib2`, SIS `.nc`, MOSMIX `.kmz` | M | `DEV-6`, `QR-6` |

**Sequencing note.** M.6 before M.5 is deliberate even though ICON is the primary source: it proves
transport → decoder → sink → mapper → persist with no library risk and no cell arithmetic, so M.5 only has
to prove GRIB2. This is the replacement mitigation for `R-8`.

**Exit criteria.**

1. A registered site's cloud cover and radiation come from its own 2.2 km ICON-D2 cell; the value states
   the cell and its distance. (`V-1`, `INT-3`)
2. The merged instance covers 0–48 h; every value carries provenance; solar elevation and azimuth are
   present per timestep.
3. Restart with data present → identical results from the persisted XMI. (`V-3`)
4. The raw record contains the superseded predictions, with issue times. (precondition for `INT-17`)
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

## Slice 3 — Fusion and asynchronous sources

Goal: several sources with different issue times assemble into one coherent timeline.

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| 3.1 | Per-kind source priority as configuration, per site or global (`Q-F`) | M | `V-2`, [ADR-0012](adr/0012-fusion-and-supersession.md) |
| 3.2 | **As-of querying over the raw record** — "what was predicted for tomorrow 08:00, and when" | M | `INT-17` becomes real |
| 3.3 | Best-available-now: per-timestep degradation instead of request failure | M | `INT-8`, `V-5` |
| 3.4 | Temporal interpolation where source timesteps do not align | M | `INT-3` completeness |
| 3.5 | Retention for the raw record — by age and by revision depth (`M-10`, `Q-B`) | M | `OPS-8` |
| 3.6 | Mixed-horizon handling **beyond +48 h**, where ICON-D2 ends and MOSMIX continues to +240 h | M | Only if a horizon past two days is wanted |

**What changed here.** The MVP already merges across sources, so 3.1 is about making the *policy*
configurable rather than building the merge. And 3.6 shrank: it was "SIS to +18 h, MOSMIX beyond", which
ICON-D2 makes unnecessary inside 48 h — the transition only matters if the horizon is extended past that.

**Exit criteria.** A later-arriving source supersedes the merged value without destroying the earlier one
in the raw record (`V-7`); an as-of query returns what was known at a chosen time; the source of each
value is visible; retention keeps the raw record bounded.

---

## Slice 4 — Breadth

Everything valuable that no longer changes the architecture. Order by whichever project asks first.

| # | Increment | Effort | Delivers |
| --- | --- | --- | --- |
| 4.1 | Derived quantities — dew point, apparent temperature, direct/diffuse split (scope from `Q-G`) | M | Stage-1 derivation complete |
| 4.2 | Ad-hoc coordinate queries, marked degraded | M | `INT-13` |
| 4.3 | Observation provider — enables checking forecasts against reality | **L** | Closes the `F-5` gap |
| 4.4 | **HTTP API** — reopen [ADR-0008](adr/0008-rest-api-design.md), which is deferred; plus push notification per site | **L** | `INT-2`, `INT-9`…`INT-11`, `INT-14`. Blocks any external consumer, so it gates cutover |
| 4.5 | **`index.lucene`** — our own integration against the Lucene wraps, `rebuildFrom` the repository | M | `OPS-2`, needed once queries exist |
| 4.6 | Health endpoint, metrics, jitter, failure isolation, backup/restore, upgrade path, footprint | M | `OPS-3`, `OPS-4`, `OPS-11`, `OPS-13`…`OPS-15` |
| 4.7 | Resolve `QR-10` / `Q-A`: authentication, or a documented decision to delegate it | S–M | `QR-10` |
| 4.8 | Site attributes for tilt and azimuth, stage 2 preparation only | S | `INT-16`, `Q-J` |

## Deferred indefinitely

Not scheduled, not designed for beyond not being precluded. Each would be a new planning
conversation.

- Radar (RADOLAN) ingest and nowcasting
- A second national provider (`Q-E`)
- Stage-2 plane-of-array irradiance
- Statistical post-processing and bias correction against stored observations
- Materialised fusion cache — only if `QR-2` measurement demands it
- Server-backed repository — only if `Q-B` / `Q-C` answers demand it
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

*Updated 2026-07-29, end of session.*

Slice 0 is done except 0.8, which waits on
[org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3) — the GRIB
wrap. `sunorcloud` is pushed with three commits and CI green.

**Next is M.1, the model.** It does not depend on the wrap. Two things have to be settled first, and both
are decisions rather than work:

1. **`DEV-5`.** It names Ecore annotations as the mechanism for source-element mapping metadata. The
   emf.osgi 1.1 metadata service is the better fit for `INT-15` because metadata lives beside the model
   and is therefore reusable across providers by construction — but choosing it means changing that
   requirement. See [ADR-0005](adr/0005-provider-neutral-model.md).
2. **Who writes the `.ecore`.** Generating the model by hand from a written specification is slower than
   doing it in the Ecore tooling directly. If the specification route is taken, the types to specify are
   `Site`, `SourceBinding`, `MeasuredValue`, `Provenance`, `Uncertainty`, `GeoPosition`, `GridRef` and
   `MeasurementKind` with the set from [09-source-inventory.md](09-source-inventory.md).

Also still open, none of it blocking M.1: `M-10` (raw-record retention, needs `Q-B`), `M-11` (merge policy
scope, needs `Q-F`), the UV product's exact grid, and how long each DWD product stays on the server —
which is what bounds the backfill (`OPS-16`).

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
