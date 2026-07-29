# Stakeholder Analysis

Three stakeholder groups drive the redesign, one external actor constrains it, and two groups are
deliberately treated as secondary. Naming the secondary ones matters as much as the primary ones —
it prevents their concerns from silently re-entering the requirements.

## Map

| Stakeholder | Role | Influence | Interest | Primary concern |
| --- | --- | --- | --- | --- |
| [DIM development team](#s-1-dim-development-team) | Builds and maintains the service | High | High | Can I still change this in two years? |
| [Energy integrators](#s-2-energy-integrators) | Embed the service in PV / heat-pump optimisation | High | High | Is the forecast accurate *at my building*, and can I trust it? |
| [Operations](#s-3-operations) | Deploy and run the service | Medium | High | Can I tell whether it is healthy, and fix it when it is not? |
| [Data suppliers (NMHS)](#s-4-data-suppliers--external-actor) | Provide the upstream data | High (constraining) | None in us | Are you a well-behaved client of our open data? |
| [Gecko OSS community](#secondary-stakeholders) | Use published bundles | Low | Medium | Secondary — benefits indirectly |
| [Domain users](#secondary-stakeholders) | Meteorology / energy modelling | Low | Medium | Secondary — reached via integrators |

Influence and interest are assessed relative to *this redesign*, not in general. Data suppliers
have high influence and no interest: they will never advocate for us, but their formats, licences
and rate limits are hard constraints.

---

## S-1: DIM development team

**Who.** Data In Motion as maintainer — Jürgen Albert, Mark Hoffmann, Ilenia Salvadori. Effectively
one person at a time working alongside customer projects, with long gaps between sessions.

**What they need.**

- A structure where a change has an obvious single home, so that returning after three months does
  not require re-reading the whole codebase.
- Source-specific knowledge isolated, so DWD quirks cannot leak into shared code.
- Tests that run without network access, so a decoder change can be verified in seconds.
- Mechanical enforcement of API compatibility, because discipline does not survive long gaps.

**What hurts today.**

- The bundle named `org.gecko.weather.api` is the DWD bundle in practice — `DWDFetcher`,
  `DWDEMFFetcher`, `DWDUtils`. There is no home for genuinely shared abstractions. → `F-1`
- `DWDUtils` is 599 lines mixing URL construction, zip handling, EMF navigation and a large
  hand-written mapping of DWD element IDs to model features (`setMOSMIXMeasurement` at line 433).
  Every new source product grows this file. → `F-3`
- `DWDMOSMIXStationForecastFetcher.doDecode` does five jobs in one method: KML navigation, station
  construction, timestep expansion, measurement mapping and astro enrichment — then hands off to
  the index. Nothing in it is unit-testable in isolation. → `F-4`
- Dead and experimental code sits in production source folders: `TestComponent` (175 lines with
  `//@Component` commented out), `NetCDFTest`, `StationSearchComponent` under `impl/example`. Each
  one costs a moment of "is this load-bearing?" on every visit. → `F-12`
- Documentation is spread across five READMEs that contradict each other and contain the same typo
  in three places (`org.gecko.weathcer`), plus `prg.gecko.weather.model` in the root readme. Low
  stakes individually, but it signals that docs are not trusted — and untrusted docs stop being
  read. → `F-15`

**Success looks like.** A newcomer — or the same person after a long gap — can add a DWD product or
a new provider by writing one bundle, guided by documentation, with tests that prove it works
offline, and CI that refuses to let them break consumers.

**Requirements:** `DEV-1` … `DEV-15`

---

## S-2: Energy integrators

**Who.** Projects that embed Gecko Weather to optimise energy at a specific building — PV plus heat
pump self-consumption, home and building energy management, storage scheduling. Secondarily
smart-city and IoT platforms, including the Eclipse sensiNact adapter direction visible in the recent
`sensinact.mapping` annotations. They consume the REST API or the OSGi bundles directly.

They are the stakeholder whose need defines the product: **accuracy at a point**, not coverage of a
country.

**What they need.**

- A forecast for **their building's coordinates** — irradiance and cloud cover above that roof, not
  at a station 20 km away.
- **Solar geometry** over the day: elevation and azimuth per timestep, not just sunrise and sunset,
  because PV output depends on where the sun actually is.
- **One continuous timeline** per site over the 24–48 hour decision horizon, already fused across
  sources — not three endpoints they have to align themselves.
- **An honest quality statement.** They are making decisions with money attached. A value that is
  station-only, interpolated, from a stale model run or from an ad-hoc coordinate must say so, so
  they can decide how much to bet on it.
- **Best available now**, at any moment. Sources publish asynchronously; a query must degrade per
  timestep rather than fail.
- An API contract that will not break under them mid-project, and the choice between HTTP and
  in-process consumption — some run inside the same OSGi framework and should not pay for HTTP.
- Extension without forking, for project-specific site attributes.

**What hurts today.**

- **A forecast location is a station.** `MOSMIXStationConfig` carries `stationId`, `name`, `latitude`
  and `longitude`, and the fetcher builds the report around that station. There is no concept of a
  site distinct from a station and no spatial correction whatsoever. This is the central defect. →
  `F-17`
- **No gridded data reaches production.** `GeoArea(topLeft, xDim, yDim)` and
  `UVRadiationMeasurement.area` were modelled for exactly this and are used nowhere outside a spike.
  The spike (`org.gecko.weather.netcdf`) does decode DWD SIS global radiation on a 0.05° grid — but
  it is an `@Component` with a hardcoded filename and a hardcoded reference date that reads a local
  file and materialises the entire German grid. → `F-19`
- **Solar geometry is incomplete.** `AstrotimeService` offers `getSunTimes`, `getSunrise` and
  `getSunset`. Sun elevation and azimuth — the part PV actually needs — do not exist. → `F-18`
- **No value carries its origin or its quality.** `MOSMIXSWeatherReport` is a set of floats. An
  integrator cannot tell whether a number is measured, forecast, interpolated or how good it is. →
  `F-20`
- **Model naming does not match physical quantities.** The spike stores SIS — shortwave *global*
  radiation — in a class called `UVRadiationMeasurement`. Global radiation and UV are different
  quantities; conflating them in the model name is how wrong units reach consumers. → `F-20`
- **No observations exist**, only forecasts. "What is it doing right now" cannot be answered — which
  also removes any way to check forecast accuracy after the fact. → `F-5`
- REST paths are unversioned (`/weather/rest/station`), so any change breaks every consumer at once,
  and errors come back as bare status codes with no machine-readable body. → `F-16`
- Responses carry DWD product semantics — consumers receive `MOSMIXSWeatherReport` with fields
  documented as DWD element IDs, so switching or adding a source breaks consumer code. → `F-2`,
  `F-3`
- The `sensinact.mapping` / `sensinact.mapping.metadata` annotations are a good start and exactly the
  right mechanism — canonical unit plus `sensorthings.unit.name` plus the source's own `dwd.id` — but
  they are attached to a provider-specific class, so the mapping is not reusable across providers. →
  `F-2`

**Success looks like.** An integrator registers a site with its coordinates, queries one endpoint for
the next 48 hours, and receives a continuous timeline of irradiance, cloud cover by layer,
temperature and sun position in documented canonical units — each value stating which source and grid
cell it came from and how much to trust it — and is confident a minor version upgrade will not break
their build.

**Requirements:** `INT-1` … `INT-17`

---

## S-3: Operations

**Who.** Whoever runs the container — DIM operations for hosted deployments, or the customer's own
platform team. Critically: **someone who did not write the code.**

**What they need.**

- To know, from outside the process, whether the service is healthy and whether its data is fresh.
- To configure providers, schedules and storage without touching code or rebuilding an image.
- Confidence that a restart is safe and that recovery is bounded and predictable.
- Disk and memory growth that is understood in advance, not discovered in production.

**What hurts today.**

- Restart destroys everything. Storage is a `ConcurrentHashMap`; both Lucene indexes are configured
  `directory.type=ByteBuffer`. → `F-6`, `F-7`
- This is worse than ordinary cache loss: DWD publishes only the current forecast window, so data
  not persisted at ingest time is **permanently unrecoverable**. Every restart silently destroys
  history.
- There is no health signal. The only evidence of a failed ingest is a log line — and element-level
  decode failures are swallowed individually, with a log call whose arguments are in the wrong
  order, so the message is misleading even when someone reads it. → `F-10`, `F-11`
- Every hour, every configured station triggers a full KMZ download with no conditional GET. Cost
  scales linearly with station count regardless of whether anything changed upstream. → `F-9`
- No retention or housekeeping: the in-memory map and index grow until the heap is exhausted.
  → `F-6`
- The runtime resolves two versions of `jakarta.xml.bind-api` (3.0.1 and 4.0.1) simultaneously —
  the kind of latent classloading hazard that surfaces as an inexplicable production failure.
  → `F-13`
- No documented upgrade path, no backup procedure, and no stated resource footprint.

**Success looks like.** Ops deploys the container, points a volume at it, enables two providers via
configuration, sees green health with data-age metrics per provider, and when a provider breaks,
receives a specific failure reason while every other provider and the API keep working.

**Requirements:** `OPS-1` … `OPS-16`

---

## S-4: Data suppliers — external actor

**Who.** DWD Open Data, and later MeteoSwiss, GeoSphere Austria, KNMI, Met Norway. They are not
stakeholders in the usual sense — they have no interest in Gecko Weather — but they impose hard
constraints and can withdraw access.

**Constraints they impose.**

- **Formats are theirs, and they differ — even within DWD.** MOSMIX is KMZ-wrapped KML with a
  DWD-specific extension schema; SIS global radiation is NetCDF on a 0.05° grid; ICON model fields
  are GRIB2; station catalogues are plain text. The decoder abstraction must not assume KML, and the
  transport abstraction must not assume "download a zip". This variety is why DWD alone is a
  sufficient test of the abstraction.
- **Gridded products are large and mostly irrelevant to us.** A full SIS field over Germany at 0.05°
  is on the order of tens of thousands of cells per timestep; a site needs one. Downloading and
  materialising the whole field per run is wasteful for us and impolite to the supplier — hence
  subset-on-ingest ([ADR-0010](adr/0010-subset-on-ingest.md)). Where the source supports server-side
  subsetting, that is strictly preferable to downloading and discarding.
- **Data is perishable.** MOSMIX single-station URLs serve only `LATEST`. Miss a window and it is
  gone. This single fact is the strongest argument for durability being a Must, not a Should.
- **Publication cadence is theirs.** MOSMIX_L is roughly 4-hourly per station, MOSMIX_S hourly,
  DMO twice daily. Schedules must be per-provider configuration, not a global constant.
- **Fair-use expectations.** Open data portals are shared infrastructure. Unconditional hourly full
  downloads across many stations are impolite and risk being blocked. Conditional GET, jittered
  scheduling and backoff are requirements toward the supplier, not just efficiencies for us.
- **Licence and attribution.** DWD open data requires attribution; other services vary. Provenance
  must be carried in the model, not just as documentation.
- **They change without notice.** URLs, schemas and element sets shift. Provider bundles must be
  independently versionable and replaceable, and decode failures must degrade one provider rather
  than the service.

---

## Secondary stakeholders

Explicitly deprioritised. Their needs are welcome side effects, never justifications.

**Gecko OSS community.** Users of the published `org.geckoprojects.weather` bundles. They benefit
from clean SPI, semantic versioning and Maven Central releases — all of which the primary
stakeholders require anyway. What we will *not* do for them: shape the SPI for hypothetical
external use cases, maintain compatibility beyond what integrators need, or add examples and
tutorials at the cost of service work.

**Domain users** (meteorologists, data scientists). Reached through integrators, never directly —
but their influence grew with the decision to include derivation, interpolation and blending in the
vision. They are the reviewers who will ask *where did this number come from and how much should I
trust it?*, which is why lineage and uncertainty are model concerns (`INT-6`, `INT-7`) and why
canonical units and documented measurement semantics (`INT-4`) are non-negotiable — wrong units or
an undisclosed interpolation make the data worthless to them. Beyond correctness and traceability,
analysis features, exports and notebooks remain out of scope.

---

## Conflicts between stakeholders

Real tensions, with the resolution stated up front so it is not re-litigated per decision.

| # | Conflict | Resolution |
| --- | --- | --- |
| C-1 | Dev wants freedom to restructure; integrators want a frozen API | Freedom *inside* the bundles, contract *at* the edges. Only exported packages and REST paths are frozen, and only within a major version. Baselining marks the line mechanically (`DEV-8`, `INT-1`). |
| C-2 | Ops wants durability; dev wants a simple stack with no database to run | Durability is a Must, but the backend is pluggable. A file-based repository ships as the default so the simple path stays simple; a server-backed one is available where scale demands it ([ADR-0004](adr/0004-persistence-index-split.md)). |
| C-3 | Integrators want fresh data; suppliers want low request volume | Conditional GET resolves most of it — freshness costs almost nothing when data is unchanged. Where it genuinely conflicts, the supplier wins: being blocked serves nobody (`OPS-11`). |
| C-4 | Broad provider coverage vs. depth on DWD | Depth first. The vision's extensibility claim is proved with exactly **one** additional provider (`V-2`); further providers are pull-based, added when a project needs them. |
| C-5 | Vision scope vs. one-person capacity | Not resolved by shrinking the vision but by increment sequencing — see [risk R-1](06-redesign-plan.md#risks). Grid/radar and derivation are separable tracks precisely because of this. |
| C-6 | Integrators want one simple best value per point; domain users want every value's origin and uncertainty | Both, layered. The blended best-available value is the convenient default; lineage and uncertainty travel with it rather than in a separate call, so the simple consumer can ignore them and the rigorous one cannot be denied them (`INT-6`, `INT-7`). |
| C-7 | Derivation and blending add compute cost and complexity; ops wants a predictable, simple service | Derivation is a distinct bundle and a distinct extension point, not logic sprinkled into ingest or REST. It can be disabled entirely by configuration, and the service remains fully functional serving only stored source values. |

---

## Open questions for stakeholders

These need answers from outside the code. They are flagged where they affect requirements, and
none of them blocks the early increments.

| # | Question | Blocks | Asked of |
| --- | --- | --- | --- |
| Q-A | Does the service need authentication and authorisation? There is none today. For an operable service with external consumers this is a genuine gap, but it may be delegated to a gateway. | `INT-1`, REST design | Integrators, Ops |
| Q-B | What retention is actually required — days, months, years? Determines whether a file-based repository suffices or a server-backed one is mandatory. Note that keeping forecasts *and* later observations is what enables accuracy checking. | `OPS-8`, `QR-5`, [ADR-0004](adr/0004-persistence-index-split.md) | Integrators, Ops |
| Q-C | How many **sites** realistically, and how clustered? Ten sites in one region and a thousand across Germany are different systems — and with subset-on-ingest, cost scales with sites, not with country size. | `QR-2`, `QR-8`, sizing, [ADR-0010](adr/0010-subset-on-ingest.md) | Integrators |
| Q-D | Is the sensiNact adapter a committed direction or exploratory? If committed, its mapping metadata becomes a first-class model concern rather than an annotation convention. | `INT-15`, [ADR-0005](adr/0005-provider-neutral-model.md) | DIM, integrators |
| Q-E | If a second national provider is ever built, which one? Not needed to validate the abstraction — DWD's own format variety does that — so this is deliberately unanswered for now. | future | DIM |
| Q-F | Who owns the fusion policy — is source priority a global operator setting, per-site, or per-query? Decides whether a fused timeline is stored or computed per request. | [ADR-0012](adr/0012-fusion-and-supersession.md) | Integrators, Ops |
| Q-G | Which derived quantities are actually needed beyond solar position? Dew point and apparent temperature are our guesses; the real list should come from the energy projects. | Slice 4 scope | Integrators |
| Q-H | Is an interpolated value acceptable at all, or do some consumers need "nearest station only"? Determines whether interpolation is opt-in or the default. | `INT-7`, `V-5` | Integrators, domain users |
| ~~Q-I~~ | ~~Which DWD gridded products for cloud cover by layer and UV index?~~ **Answered 2026-07-29** — see [09-source-inventory.md](09-source-inventory.md). ICON-D2 gives cloud by layer *and* direct/diffuse radiation at 2.2 km for 0–48 h; SIS adds global radiation at 0.05°; UV comes from the health forecasts once daily on the ICON-EU grid. SIS's 18 h horizon does **not** need patching with MOSMIX station values, because ICON-D2 covers the whole window gridded. | — | — |
| Q-J | Are panel tilt and azimuth site attributes we should model now, even though plane-of-array irradiance is stage 2? Cheap to include in the site model, expensive to add to stored history later. | [ADR-0009](adr/0009-site-as-central-entity.md) | Integrators |
