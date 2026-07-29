# Requirements

Requirements per stakeholder, derived from [01-vision.md](01-vision.md) and the pain points in
[02-stakeholders.md](02-stakeholders.md).

## Method

**Identifiers.** `DEV-n` development team, `INT-n` energy integrators, `OPS-n` operations, `QR-n`
cross-cutting quality requirements. Stable — referenced from ADRs and plan increments.

**Priorities.**

| Prio | Meaning |
| --- | --- |
| **Must** | The service is not fit for its purpose without it. A slice containing it is not done until it holds. |
| **Should** | Real value, deliberately deferrable. Absence is a known limitation, not a defect. |
| **Could** | Wanted if cheap or if a project asks. Never a reason to delay a slice. |

**Traceability.** The *Traces to* column names the as-is finding (`F-n`, see
[04-architecture-current.md](04-architecture-current.md)), the vision success indicator (`V-n`) or the
ADR that the requirement derives from. A requirement tracing to nothing is a requirement nobody
asked for.

**A note on numbers.** Where a quality requirement states a numeric target, it is marked
**(proposed)** if it is our suggestion rather than a stakeholder statement. Those need confirmation
before they are treated as commitments — see the open questions in
[02-stakeholders.md](02-stakeholders.md#open-questions-for-stakeholders).

---

## DEV — Development team

Requirements that keep the service changeable by one person across long gaps.

| ID | Requirement | Prio | Traces to |
| --- | --- | --- | --- |
| DEV-1 | The core bundles (model, provider API, repository, index, fusion, REST) contain no vendor product name in any bundle name, type name, field name or constant. Source specifics exist only in provider bundles. | Must | `F-1`, `F-2`, `V-10` |
| DEV-2 | Ecore plus genmodel is the single source of truth for the model. Generated code is never hand-edited, and generation is reproducible from a clean checkout. | Must | [ADR-0002](adr/0002-emf-as-core-model.md) |
| DEV-3 | Transport (how bytes are obtained) and decoding (how bytes become model objects) are separate extension points, independently implementable and independently testable. | Must | `F-1`, `F-4`, [ADR-0003](adr/0003-provider-spi.md) |
| DEV-4 | The ingest pipeline is decomposed into named, individually testable stages: acquire → decode → map → enrich → persist → index. No stage combines more than one of these. | Must | `F-4` |
| DEV-5 | Source element vocabularies are mapped to canonical measurement kinds declaratively (Ecore annotations carrying source ID, canonical kind and unit), not by hand-written conditional code. | Must | `F-3` |
| DEV-6 | Every decoder and every mapping is testable offline against recorded source fixtures, with no network access and no OSGi framework start. | Must | `F-4`, `V-13` |
| DEV-7 | Production source folders contain no dead, commented-out or experimental code. Spikes live in a clearly marked location or not at all. | Must | `F-12` |
| DEV-8 | API compatibility is enforced mechanically: bnd baselining runs in CI and fails the build on an unintended breaking change to an exported package. | Must | `V-12` |
| DEV-9 | The runtime resolves exactly one version of each bundle. Duplicate or conflicting versions of the same artefact are a build failure, not a runtime surprise. | Must | `F-13` |
| DEV-10 | `java.time` is used throughout. No `Date`, `Calendar` or `SimpleDateFormat` in new code, and all instants carry an explicit time zone or offset. | Should | `F-14` |
| DEV-11 | Toolchain baseline: Java 21, bnd 7.1+, OSGi R8. | Should | [ADR-0006](adr/0006-java-baseline-toolchain.md) |
| DEV-12 | Documentation lives in one place (`docs/`) with per-bundle READMEs limited to what is specific to that bundle. Naming and structure conventions are written down. | Should | `F-15` |
| DEV-13 | Fast feedback: model generation is skippable for a normal build, and unit tests run without starting an OSGi framework. | Should | — |
| DEV-14 | Releases go to Maven Central under `org.geckoprojects.weather` with semantic versions derived from baselining. | Should | — |
| DEV-15 | Log statements are parameter-correct: placeholder count matches argument count and argument order matches the message. Verified by review or lint, because the current code gets this wrong silently. | Should | `F-11` |

**On DEV-5.** This is the requirement that decides whether the codebase stays maintainable. Today a
new source product means extending a 599-line `DWDUtils` with more branches (`setMOSMIXMeasurement`
begins at line 433). The existing `sensinact.mapping` / `sensinact.mapping.metadata` annotations —
canonical unit plus `sensorthings.unit.name` plus the source's own `dwd.id` — are already the right
mechanism; they just sit on a provider-specific class instead of on canonical kinds.

**On DEV-7.** Not cosmetic. `TestComponent` (175 lines with `//@Component`), `NetCDFTest` and
`impl/example/StationSearchComponent` each cost a moment of "is this load-bearing?" on every visit,
and that cost is paid disproportionately by someone returning after months. The SIS decoding
knowledge inside `NetCDFTest` is valuable and must be salvaged into a real provider bundle — see
[07-migration.md](07-migration.md) — but the spike itself does not ship.

---

## INT — Energy integrators

Requirements that make the service usable for optimising a specific building.

| ID | Requirement | Prio | Traces to |
| --- | --- | --- | --- |
| INT-1 | A site can be registered with coordinates and elevation and receives a stable identifier. Its source binding — which station(s), which grid cell per product — is resolved and persisted at registration. | Must | `F-17`, [ADR-0009](adr/0009-site-as-central-entity.md) |
| INT-2 | One request returns a site's forecast as a continuous timeline over the horizon: one entry per timestep, already fused across sources, with no client-side alignment required. | Must | `V-2` |
| INT-3 | For a registered site, irradiance and cloud cover come from the site's resolved grid cell, not from a station. The response states the cell and its distance from the site. | Must | `F-17`, `F-19`, `V-1` |
| INT-4 | Solar geometry is available per site and timestep: sun elevation and azimuth, plus sunrise, sunset and twilight. | Must | `F-18`, `V-8` |
| INT-5 | All values use canonical measurement kinds with documented units. No source element identifier and no source-specific unit appears in the API. | Must | `F-3`, `F-20` |
| INT-6 | Every value carries lineage: provider, product, model run and issue time, the station or grid cell it came from with its distance, and — for computed values — the derivation step and its inputs. | Must | `V-4`, [ADR-0011](adr/0011-lineage-and-uncertainty.md) |
| INT-7 | Every value carries a quality statement, including explicit markers for degradation: station-only, interpolated, stale model run, ad-hoc coordinate. Degradation is never silent. | Must | `V-5`, [ADR-0011](adr/0011-lineage-and-uncertainty.md) |
| INT-8 | Queries are answered with best available data at the time of asking. Missing sources degrade individual timesteps or quantities; they never fail the whole request. | Must | `V-7` |
| INT-9 | The HTTP API is versioned (`/api/v1`) and evolves compatibly within a major version, with a written deprecation policy. | Must | `F-16`, [ADR-0008](adr/0008-rest-api-design.md) |
| INT-10 | An OpenAPI description is published and matches the implementation, including measurement kinds, units and lineage structures. | Must | [ADR-0008](adr/0008-rest-api-design.md) |
| INT-11 | Errors are machine-readable (RFC 9457 problem details) and distinguish at least: unknown site, no data for the requested period, provider temporarily unavailable, invalid request. | Should | `F-16` |
| INT-12 | An in-process SPI offers the same capabilities as the REST API, so consumers inside the same OSGi framework need not go through HTTP. | Should | — |
| INT-13 | Ad-hoc coordinates (not a registered site) are answered best-effort from data that already exists, and are marked as such via `INT-7`. | Should | [ADR-0009](adr/0009-site-as-central-entity.md) |
| INT-14 | Consumers can be notified when new data for a site arrives, instead of polling. | Should | — |
| INT-15 | Mapping metadata for external semantic models (sensiNact, SensorThings unit definitions) is attached to canonical measurement kinds, so it is reusable across providers rather than tied to one. | Should | `F-2`, `Q-D` |
| INT-16 | Site attributes are extensible with project-specific data without forking the model — including panel tilt and azimuth, so that stage-2 plane-of-array irradiance can be added without a model break. | Could | `Q-J` |
| INT-17 | Superseded forecasts remain retrievable: "what was predicted for tomorrow 14:00, and when was that predicted?" This is the precondition for any later accuracy analysis or bias correction. | Could | [ADR-0012](adr/0012-fusion-and-supersession.md) |

**On INT-3.** This is the requirement the whole redesign exists for. It is also the one that is easy
to appear to satisfy without actually satisfying: returning a grid value for the *nearest ingested*
cell rather than the site's own cell would pass a naive test. Hence the explicit obligation to state
the cell and its distance, which makes the difference observable.

**On INT-7.** Integrators are making decisions with money attached. The requirement is not "provide a
confidence number" but "never let a weak value look like a strong one". A station-only value 20 km
away and a value from the site's own grid cell must be distinguishable without reading documentation.

**On INT-17.** Marked `Could` because nothing today depends on it, but noted here because it is
**cheap now and impossible later**: it only works if supersession preserves prior values from the
start ([ADR-0012](adr/0012-fusion-and-supersession.md)). If forecasts are overwritten in place, this
requirement can never be satisfied retroactively.

---

## OPS — Operations

Requirements that make the service runnable by someone who did not write it.

| ID | Requirement | Prio | Traces to |
| --- | --- | --- | --- |
| OPS-1 | Ingested data survives process restart, application upgrade and container replacement, with zero loss. | Must | `F-6`, `F-7`, `V-3` |
| OPS-2 | The search index is fully rebuildable from the durable repository, without re-fetching anything from upstream. | Must | `F-7`, [ADR-0004](adr/0004-persistence-index-split.md) |
| OPS-3 | Health is observable from outside the process, per provider and per site: last successful ingest, age of newest data, consecutive failure count — plus liveness and readiness signals. | Must | `F-10`, `V-3` |
| OPS-4 | A failing provider degrades only its own data. Other providers keep ingesting and the API keeps serving what exists. | Must | `F-10` |
| OPS-5 | All configuration is external — providers, schedules, storage location, retention, sites — via OSGi Configuration Admin / Configurator with environment overrides. Adding a site or a provider never requires a rebuild. | Must | `F-9` |
| OPS-6 | Unchanged upstream data does not cause a full re-download. Change detection uses conditional requests (`ETag` / `If-Modified-Since`) or equivalent source-specific means. | Must | `F-9`, `V-11` |
| OPS-7 | Failed fetches are retried with bounded backoff, and schedules are jittered so that many sites or providers do not hit a source simultaneously. | Must | `F-9`, `F-10` |
| OPS-8 | Retention is configurable and enforced automatically. Storage growth is bounded and predictable; nothing grows until the heap or disk is exhausted. | Must | `F-6` |
| OPS-9 | Ingest volume for gridded products scales with the number of registered sites, not with the size of the source field. Where the source supports server-side subsetting, it is used. | Must | `V-6`, [ADR-0010](adr/0010-subset-on-ingest.md) |
| OPS-10 | A container image is published, with documented volumes for the durable store and a documented minimal deployment (compose or equivalent). | Must | — |
| OPS-11 | Metrics are exposed: ingest duration, bytes and cells fetched, failures per provider, repository size, query latency. | Should | `F-10` |
| OPS-12 | Logs are structured, parameter-correct, level-configurable and free of credentials. | Should | `F-10`, `F-11` |
| OPS-13 | Upgrades have a documented path, including what happens to stored data and the index when the model version changes. | Should | — |
| OPS-14 | A backup and restore procedure for the repository is documented and tested at least once. | Should | — |
| OPS-15 | The resource footprint is documented: heap, and disk per site per day per product, so capacity can be planned before production. | Should | — |
| OPS-16 | Registering a site triggers a best-effort backfill of whatever the source still publishes, and the site records the timestamp from which its data is actually complete. | Should | [ADR-0010](adr/0010-subset-on-ingest.md) |

**On OPS-1.** Stronger than ordinary durability. Because sources are perishable — MOSMIX serves only
`LATEST`, SIS analysis files are published per hour — data lost on restart is not re-fetchable. Every
restart of the current implementation silently and permanently destroys history. This is the single
most important requirement in the document.

**On OPS-9.** The concrete target: the SIS spike currently materialises the entire German 0.05° grid,
on the order of tens of thousands of cells per timestep, roughly half a million measurement objects
per run — to serve a site that needs one cell. After the redesign, ingest for one site must cost
approximately one cell per product per timestep.

**On OPS-16.** This is the mitigation for subset-on-ingest's honest cost. Best-effort is the accurate
word: sources frequently do not offer retrospective retrieval, so the recorded
"complete-from" timestamp matters as much as the backfill itself — it tells consumers where the
site's history genuinely begins.

---

## QR — Cross-cutting quality requirements

Numeric targets marked **(proposed)** are our suggestion and need confirmation.

| ID | Quality | Requirement | Prio | Traces to |
| --- | --- | --- | --- | --- |
| QR-1 | Availability | The API answers whenever the process is up, independent of upstream availability — serving stored data is never blocked by a provider being down. Uptime target itself: **(proposed)** 99 % monthly, excluding planned maintenance. | Must | `OPS-4` |
| QR-2 | Query latency | A site forecast over a 48-hour horizon is served in **(proposed)** under 200 ms at p95 from warm state. | Should | `Q-C` |
| QR-3 | Data freshness | Newly published source data is queryable within **(proposed)** one scheduling interval plus 5 minutes, and the actual age is always observable via `OPS-3`. | Must | `V-11` |
| QR-4 | Recovery | After a restart the service serves stored data within **(proposed)** 60 s, including index rebuild, or serves degraded-but-correct results while the rebuild completes. | Must | `OPS-2` |
| QR-5 | Retention | Retention is configurable; the default is **(proposed)** 13 months so that a full year of history plus margin is available for accuracy analysis. Depends on `Q-B`. | Must | `OPS-8` |
| QR-6 | Correctness of decoding | Every source element mapping and every derivation function has at least one test with a known expected value. Coverage on decoder, mapping and derivation packages: **(proposed)** ≥ 80 % line coverage. | Must | `V-13` |
| QR-7 | Reproducibility | Any derived or fused value can be recomputed from its stored lineage and inputs, yielding the identical result. | Must | `V-4` |
| QR-8 | Resource footprint | Memory and disk consumption are proportional to registered sites and retention, not to source field sizes, and are documented per `OPS-15`. | Must | `V-6` |
| QR-9 | Startup | The runtime starts and reaches readiness without network access to any provider — a source being unreachable delays data, never startup. | Should | `OPS-4` |
| QR-10 | Security | **Open.** There is no authentication or authorisation today. Whether the service authenticates consumers itself or delegates to a gateway is unresolved — see `Q-A`. Until decided, the service must not be assumed safe to expose publicly, and this must be stated in the deployment documentation. | Must | `Q-A` |
| QR-11 | Licensing and attribution | Source licence and required attribution are carried with the data, not only in documentation, so downstream consumers can meet their obligations. Code remains EPL-2.0 with the existing header check. | Must | `S-4` |
| QR-12 | Portability | Runs in a container on a standard JVM with no OS-specific assumptions; the durable store is a documented mounted volume. | Should | `OPS-10` |

**On QR-10.** Flagged as `Must` although unresolved, because the *decision* is mandatory even if the
outcome is "delegate to a gateway". An operable service with an unresolved authentication story is a
service that will eventually be exposed by accident.

**On QR-2 and QR-5.** Both depend on unanswered stakeholder questions (`Q-C` site count and
clustering, `Q-B` required retention). They are stated so that the architecture is sized against
*something*, and must be revisited when answered — they materially affect whether a file-based
repository suffices ([ADR-0004](adr/0004-persistence-index-split.md)).

---

## Constraints

Not requirements — conditions the solution must live within.

| ID | Constraint | Consequence |
| --- | --- | --- |
| C-1 | Roughly one developer, alongside project work, no fixed date | Walking-skeleton sequencing; every slice independently shippable; no requirement may depend on sustained parallel effort |
| C-2 | OSGi and the Gecko EMF stack are the platform | Solutions are expressed as bundles, services and configuration; EMF remains the model technology ([ADR-0002](adr/0002-emf-as-core-model.md)) |
| C-3 | Sources are third-party open data and may change or break without notice | Provider bundles independently versionable and replaceable; decode failure isolated per provider (`OPS-4`) |
| C-4 | Source data is perishable | Durability is a `Must`, and subset-on-ingest carries an irreversible cost that must be disclosed (`OPS-16`) |
| C-5 | EPL-2.0, with an automated licence header check | New files carry the header; dependency licences must be compatible |
| C-6 | Public GitHub repository with CI, SonarCloud and Maven Central publication | Quality gates run in CI; releases follow semantic versioning via baselining (`DEV-8`, `DEV-14`) |

---

## Explicit non-requirements

Recorded so that scope creep has to argue against a written decision rather than fill a silence.
Each follows from a non-goal in [01-vision.md](01-vision.md#explicit-non-goals).

| Not required | Because |
| --- | --- |
| Numerical weather prediction | Gecko Weather derives from data others produce |
| Electrical PV yield in kWh, derating, shading horizons | Stops at meteorological and solar quantities; the plant model belongs to the consumer |
| Bias correction, nowcasting, ML downscaling | Possible later on the same derivation layer; nothing designed for it now. Durability keeps the door open |
| A second national provider | DWD's own format variety validates the abstraction (`V-9`) |
| Commercial weather APIs | Keys, quotas and licence terms would shape the SPI around absent concerns |
| Tiling, reprojection, raster algebra, map rendering | Gridded data is handled as weather, not as a geospatial platform |
| Horizontal scaling, leader election, distributed locking | One well-behaved instance that recovers cleanly. Must not be *designed out*, but is not built |
| A user interface | REST plus OpenAPI is the contract |
| Backwards compatibility with the current REST paths, service interfaces or model namespace | Greenfield rebuild ([ADR-0001](adr/0001-greenfield-new-repository.md)) |
| General-purpose weather browsing, city search | Not a weather portal; the API serves sites |

---

## Coverage check

Every finding should be answered by at least one requirement, and every `Must` should be reachable in
a plan slice. Findings not yet addressed are listed so the gap is visible rather than accidental.

| Finding | Addressed by |
| --- | --- |
| `F-1` DWD-specific "generic" API bundle | `DEV-1`, `DEV-3` |
| `F-2` Provider product name in the core model | `DEV-1`, `INT-5`, `INT-15` |
| `F-3` 599-line utility with hand-written element mapping | `DEV-5`, `INT-5` |
| `F-4` Decoding god-method mixing five concerns | `DEV-3`, `DEV-4`, `DEV-6` |
| `F-5` No observations, forecast only | `INT-17` (partly) — see gap below |
| `F-6` Volatile in-memory storage | `OPS-1`, `OPS-8` |
| `F-7` Volatile Lucene index | `OPS-1`, `OPS-2` |
| `F-8` Index and repository responsibilities entangled | [ADR-0004](adr/0004-persistence-index-split.md), `OPS-2` |
| `F-9` No conditional GET, full download every hour | `OPS-6`, `OPS-7`, `OPS-5` |
| `F-10` No retry, health, metrics or failure isolation | `OPS-3`, `OPS-4`, `OPS-7`, `OPS-11` |
| `F-11` Misordered log parameters, swallowed failures | `DEV-15`, `OPS-12` |
| `F-12` Dead and experimental code in production folders | `DEV-7` |
| `F-13` Two versions of the same bundle in the runtime | `DEV-9` |
| `F-14` `Date`/`Calendar`/`SimpleDateFormat` in new code | `DEV-10` |
| `F-15` Scattered, contradictory documentation with typos | `DEV-12` |
| `F-16` Unversioned API, no machine-readable errors | `INT-9`, `INT-10`, `INT-11` |
| `F-17` A forecast location is a station | `INT-1`, `INT-3` |
| `F-18` No sun elevation or azimuth | `INT-4` |
| `F-19` Gridded data only in a spike; whole grid materialised | `INT-3`, `OPS-9`, `DEV-7` |
| `F-20` No provenance or quality; SIS stored as UV | `INT-5`, `INT-6`, `INT-7` |

**Known gap — observations.** `F-5` is only partly addressed. Observations are needed to check
forecast accuracy after the fact, which is what makes `INT-17` and any later bias correction
meaningful, but no `Must` requires them. This is deliberate: the PV use case is served by forecasts
alone, and observations are a Slice 4 concern. Recorded here so the gap is a decision rather than an
oversight.
