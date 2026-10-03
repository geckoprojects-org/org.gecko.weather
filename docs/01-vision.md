# Vision

## Vision statement

> **Gecko Weather is a weather backend. It answers "what weather does *this exact location* get, from
> every source that covers it?" — not "what did the nearest weather station get?".**
>
> **For each registered site it brings station forecasts, gridded cloud and radiation fields and solar
> geometry together in one report — side by side, each value traceable to its source, its distance and
> its issue time — and stores it durably, because the sources publish it only once.**
>
> **It does not decide for its consumers.** Energy optimisation is the first consumer and the reason
> the project exists; PV computation is an add-on module. The service itself stops at weather.

*(Revised 2026-10-03. The earlier statement made energy the purpose of the service; that was the
purpose of its first consumer.)*

Every clause is falsifiable:

- *"this exact location, not the nearest station"* — if the answer for a site is simply the forecast
  of an assigned DWD station, nothing has been achieved. That is what the current implementation
  does, and it is the defect this redesign exists to fix.
- *"from every source that covers it … side by side"* — if a consumer has to query three endpoints to
  see station and grid values for the same hour, or if the service silently picked one, the claim is
  false.
- *"traceable"* — if a value cannot say which source it came from, how far away that was and when it
  was issued, it cannot be weighed against the value next to it.
- *"stores it durably"* — if a restart loses data, the claim is false. Sources are perishable:
  MOSMIX single-station URLs serve only `LATEST`.
- *"does not decide for its consumers"* — if the report contains a merged or "best" value, the
  service has made a judgement that belongs to the consumer ([ADR-0013](adr/0013-values-per-source.md)).

## Purpose and consumers

**The first consumer: optimising a building's self-consumption.** A house with a PV array and a heat
pump makes decisions in the next 24–48 hours: when to run the heat pump, when to charge storage, when
to heat water. Those decisions are only as good as the expected irradiance and cloud cover *at that
roof* — not 20 km away. That consumer is why the project exists and why geolocation accuracy, gridded
radiation and solar geometry are core rather than optional.

**But the service is a weather service.** It serves every quantity its sources carry — significant
weather, visibility, precipitation probabilities, fog — for whoever needs weather for a registered
place: a display, a building-automation rule, a later accuracy analysis. Energy-specific computation
(plane-of-array irradiance, anything with a panel in it) is an **add-on module** that consumes the
service; it is never built into it.

The first consumer still decides what matters most:

| Because energy optimisation is the first consumer… | …this follows |
| --- | --- |
| Irradiance and cloud cover dominate PV yield variance | Cloud fields by height layer and global radiation are the **primary** quantities, not one of many |
| Cloud fields vary on kilometre scales | Station point forecasts are structurally insufficient → gridded products are core, not optional |
| PV output depends on where the sun is | Solar elevation and azimuth per timestep are core data, not a convenience |
| Heat pump decisions need air temperature and its trajectory | Temperature continuity over the horizon matters more than instantaneous precision |
| A wrong forecast costs money, not inconvenience | Uncertainty must be visible so a consumer can decide how much to bet on it |
| Decisions are made continuously | What is currently known must be answerable at any moment, even when only some sources have published |
| Different consumers weigh sources differently | Values stay **per source**; the service never merges them ([ADR-0013](adr/0013-values-per-source.md)) |

## Why the nearest station is not enough

This is the concrete defect being fixed, and it is worth stating precisely.

The current implementation assigns a forecast location to a DWD weather station and serves that
station's MOSMIX forecast. MOSMIX itself is not the problem — it already carries the right
quantities: the model has `cloudCoverTotal`, `cloudCoverEffective`, `cloudCoverHigh`,
`cloudCoverMid`, `cloudCoverLow` and `irRadianceGlobal`. The problem is purely **spatial**: those
values describe the station, and MOSMIX stations are sparse. For wind or pressure that is often
tolerable. For cloud cover — the quantity that decides PV yield — it is not: two locations 20 km
apart routinely have different cloud cover at the same hour.

The fix is to combine sources with different spatial characteristics:

- **Station point forecasts** (MOSMIX) — rich parameter set, high temporal resolution, sparse in
  space.
- **Gridded fields** (cloud cover by layer, global and diffuse radiation, UV) — continuous in
  space at the grid resolution, which is what makes a specific roof addressable.
- **Solar geometry** — computed exactly for the site's coordinates, with no spatial error at all.

Notably, the existing Ecore already anticipated this: `UVRadiationMeasurement` references a
`GeoArea` with `topLeft`, `xDim` and `yDim` — a raster cell. The concept was modelled and then
never used. The redesign makes it load-bearing.

## The site as the central concept

The domain entity is the **site** (forecast location), not the station. A site is registered with
its coordinates and elevation; registering it **resolves its source binding**:

- which station forecast(s) apply, and how far away they are;
- which **grid cell** to read in each gridded product;
- solar geometry, computed from the coordinates directly.

That binding is persisted, and it is what tells ingest what to fetch. This is the direct answer to
the question "when a new forecast position is added, which quadrant do I read?" — the site knows its
cell index per product, and ingest extracts only those cells.

Both access modes are supported, with different guarantees:

- **Registered sites** are actively supplied and historised. They get every bound source and their own
  history, which later enables checking forecast accuracy against observations.
- **Ad-hoc coordinates** are answered best-effort from data that already exists, and are explicitly
  marked as such.

### The honest consequence of subset-on-ingest

Extracting only the cells that registered sites need keeps data volume small and ingest fast — a
handful of cells instead of full model fields per run. It has two costs that must not be hidden:

1. **A new site has no history.** Until it has been ingesting, nothing exists for it, and the
   sources rarely allow retrospective retrieval. Registering a site therefore triggers a
   best-effort backfill from whatever the source still publishes, and the site records the
   timestamp from which its data is actually complete.
2. **Ad-hoc coordinates far from any registered site have no gridded data.** They can only be
   answered from station forecasts, i.e. with exactly the spatial error this project exists to
   remove.

Both are acceptable *only because* lineage and uncertainty are mandatory: a degraded answer is
delivered as a degraded answer, never as an equal one. A consumer optimising an expensive decision
can see that it is holding a station-only value with a 20 km spatial error, and act accordingly.

## Sources arrive at different times

Products publish on different cadences and with different lag — MOSMIX_L every six hours, MOSMIX_S
hourly, ICON-D2 every three hours, SIS every fifteen minutes. A forecast for two hours ahead can be
replaced within the hour by a newer, presumably better one from the same product while another product
still shows its previous issue. A site's report is therefore **assembled from pieces that arrive
asynchronously**, and is never "finished".

This makes four things requirements rather than refinements:

- Every value carries its own **issue time and model run**, because different parts of one report
  legitimately come from different runs.
- **Each source keeps its own dataset in the report**, with its issue time, refresh cadence and
  horizon, so a consumer can tell fresh from stale per product without knowing the product.
- A query must be answerable at any moment with **what is currently known**, degrading per product
  rather than failing.
- When a product publishes anew, **its dataset is replaced and the previous one archived, not
  discarded** — the earlier values and their lineage remain, which is what makes later accuracy
  analysis possible.

## Computation: what the service derives

**Stage 1 — now:**

- **Solar geometry per site and timestep:** sun elevation and azimuth, sunrise, sunset and twilight.
  Today's `AstrotimeService` provides only `getSunTimes`, `getSunrise` and `getSunset` — position
  over the day is missing and is exactly what PV needs.
- **Spatial alignment:** resolving the station and the grid cell per product for the site, with
  distances recorded, so that every value can say how far it was carried.
- **No fusion.** Values stay per source. Reading helpers — a timeline per kind across datasets, the
  newest issue per product — may exist in the API; a "best" value does not
  ([ADR-0013](adr/0013-values-per-source.md)).
- **Derived meteorological quantities** that follow deterministically from stored values, later: dew
  point, apparent temperature, wind chill — each a `COMPUTED` value with its derivation recorded.

**Add-on modules — designed for, not built:** anything PV-specific, starting with plane-of-array
irradiance from panel tilt and azimuth. Such a module consumes the service's report and the site's
open `attributes` (where tilt and azimuth would live) and produces its own values; the model's
`Derivation` gives it a place to record how. The service does not know it exists.

**Explicitly not, anywhere:** an electrical yield model. No module areas, no temperature derating, no
shading horizon, no expected kWh.

## Lineage and uncertainty

With two sources for the same hour, grid cells at a distance, interpolation and derivation in play,
"12.4 °C" or "340 W/m²" stops being self-explanatory. Two questions must always be answerable:
**where did this come from, and how much should I trust it?** Without the answers, two cloud-cover
values side by side are noise; with them, they are information.

Therefore, as first-class model concepts from the first increment:

- **Lineage.** Which provider and product, which model run and issue time, which grid cell or station
  and how far away it was, which derivation step and from which inputs for computed values.
- **Uncertainty.** A quality statement matched to how the value was produced: lead time, spatial
  distance, temporal offset where interpolated, staleness against the product's refresh cadence, and a
  clear marker where the answer is degraded (station-only, stale run, ad-hoc coordinate).

This is the largest departure from the current model, where `MOSMIXSWeatherReport` is an
undifferentiated set of floats with no origin and no quality. It cannot be retrofitted: provenance
not captured at ingest time no longer exists. That is why it is in the first increment even though
the computation that consumes it comes later.

## Scope: DWD first, Europe designed for

**The initial focus is DWD, deliberately and exclusively.** Depth on one national service that
actually serves the PV use case beats breadth that serves nobody.

**European coverage is a design constraint, not a deliverable.** The architecture must not preclude
connecting MeteoSwiss, GeoSphere Austria, KNMI, Met Norway or Météo-France later, which means
provider-neutral model, canonical measurement kinds, and transport separated from decoding. But no
second national provider is built in this redesign, and no effort is spent on their specifics.

The useful consequence: **DWD alone supplies the format variety needed to validate the abstraction.**
MOSMIX is KMZ-wrapped KML with a DWD extension schema; gridded fields are binary (GRIB2/NetCDF);
station catalogues are text; observations are different again. If all of those arrive through one
transport/decoder SPI, the abstraction is proven — without building a provider we do not need.

### The gridded product is already identified

One gridded product is already known to work, because a spike for it exists in the current
workspace (`org.gecko.weather.netcdf`): **DWD SIS** — satellite-derived global radiation
("Globalstrahlung", [product page](https://www.dwd.de/DE/leistungen/fernerkund_globalstrahlung_sis/fernerkund_globalstrahlung_sis.html)).

| Property | Value |
| --- | --- |
| Quantity | `SIS` — surface incoming shortwave radiation, W/m² |
| Grid | 0.05° × 0.05°, Germany, with explicit `lat` / `lon` / `time` axes |
| Products | analysis (`SISin<YYYYMMDDHH>DEv3.nc`, `short` values) and **forecast** (`SISfc<…>_fc+18h-DE.nc`, `float`, 18 timesteps) |
| Format | NetCDF, decoded with the UCAR netcdf-java library — the `Unidata` repository in `cnf/build.bnd` exists for exactly this |

This is the single most valuable source for the PV use case: it is the actual irradiance signal, at a
resolution that addresses an individual roof, with an 18-hour forecast horizon.

The spike also demonstrates the problem this vision solves, quantitatively. It iterates the entire
German grid and creates one measurement object per cell per timestep — on the order of tens of
thousands of cells times 18 timesteps, roughly half a million objects per run, to serve a house that
needs **one** cell. Subset-on-ingest is not an optimisation here; it is the difference between
feasible and infeasible.

> **Answered.** `Q-I` was the first investigation task and it came out well:
> [**ICON-D2**](09-source-inventory.md) publishes cloud cover by layer *and* direct and diffuse surface
> radiation at **2.2 km for 0–48 h**, eight times a day. SIS adds global radiation at 0.05° with
> 15-minute cadence and a +18 h forecast; UV comes from the health forecasts, once daily on a coarser
> grid. So SIS's horizon needs no patching with MOSMIX station values, and the whole decision window is
> covered gridded. The cost: ICON-D2 is GRIB2, and there is no server-side subsetting — transfer scales
> with the field even though storage scales with sites.

## What "good" looks like

In priority order. Each maps to requirements in [03-requirements.md](03-requirements.md).

1. **Site-accurate.** A registered site's forecast reflects its own coordinates, using gridded cloud
   and radiation data plus exact solar geometry — not a station kilometres away.
2. **Durable.** Ingested data survives restart, upgrade and container replacement. Sources are
   perishable; durability is the difference between a service and a cache.
3. **Traceable.** Every value states its origin and its uncertainty. Degraded answers are visibly
   degraded. Derived values are reproducible from stored inputs.
4. **Continuously answerable.** What is currently known is served at any moment, per source, as
   sources arrive asynchronously — never a failed request because one product is late.
5. **Provider-neutral.** Model, repository and API contain no vendor product names, field names or
   units. Source specifics live only in provider bundles and declarative mapping metadata.
6. **Observable.** From outside the process: is each provider healthy, when did each last succeed,
   how fresh is each site's data, what failed and why.
7. **Extensible where it matters.** New capability is new bundles plus configuration. Extension
   points: transport, decoder, measurement mapping, persistence, index, derivation, add-on modules
   on the report.
8. **Stable at the edges.** REST API and exported packages versioned, evolving compatibly within a
   major version, enforced by bnd baselining rather than discipline.
9. **Maintainable by one person.** Small bundles with single responsibilities, no 599-line utility
   classes, no dead code, real coverage on decoders, mappings and derivations, documentation that
   explains *why*.

## Explicit non-goals

- **Not a weather portal.** No general-purpose browsing, no city search, no "weather for everyone".
  It serves registered sites — with every quantity its sources carry, not only the energy-relevant
  ones.
- **Not a fusion engine.** It does not pick, blend or rank sources. It shows them
  ([ADR-0013](adr/0013-values-per-source.md)).
- **Not numerical weather prediction.** No ICON-LAM, no WRF, no model physics. Gecko Weather derives
  from data others produce.
- **Not a PV model of any kind.** Plane-of-array irradiance is the first candidate for an add-on
  module *on* the service; module electrical behaviour, derating, shading horizons and expected kWh
  belong to the consumer. None of it is in the service.
- **Not statistical post-processing or nowcasting (yet).** Bias correction against our own
  observation history, radar extrapolation and ML downscaling are plausible later — durability is
  what keeps that door open — but nothing is designed for them now.
- **Not a general geospatial platform.** Gridded data is handled as *weather* with a bounded feature
  set: resolve cell, extract, store, interpolate. No tiling, reprojection, raster algebra or map
  rendering.
- **Not a commercial-API aggregator.** OpenWeatherMap, Meteomatics and similar stay out of scope;
  their keys, quotas and licence terms would shape the SPI around concerns no in-scope provider has.
- **Not a horizontally scaled cluster.** One well-behaved instance that restarts cleanly and
  recovers. Multi-instance must not be *designed out* — hence a durable repository and a rebuildable
  index — but clustering, leader election and distributed locking are not built.
- **Not a UI.** REST plus OpenAPI is the contract.
- **Not backwards compatible with the current implementation.** Greenfield rebuild
  ([ADR-0001](adr/0001-greenfield-new-repository.md)); REST paths, service interfaces and model
  namespace are all free to change. See [07-migration.md](07-migration.md).

## Success indicators

| # | Indicator | Target | How it is checked |
| --- | --- | --- | --- |
| V-1 | A site is answered from its own location | For a registered site, cloud cover and radiation come from its resolved grid cell, not from a station | Integration test asserts lineage names a grid cell, and its distance is below the grid resolution |
| V-2 | Sources sit side by side | One request returns every bound source's dataset for a site — station, grid and solar — each with issue time, cadence and horizon; no merged value anywhere | Test on the report service |
| V-3 | Data survives restart | Zero data loss across restart | Ingest → restart → report returns identical results |
| V-4 | Every value is traceable | No report contains a value without lineage; every derived value is recomputable from stored inputs | Round-trip test: recompute from lineage, compare |
| V-5 | Degradation is visible, never silent | Station-only, interpolated, stale-run and ad-hoc answers are all explicitly marked | Asserted in API contract tests |
| V-6 | Only needed cells are ingested | Ingest volume scales with registered sites, not with model field size | Measured per ingest run and logged |
| V-7 | Asynchronous sources assemble correctly | A product's refresh replaces only that product's dataset; the previous one is archived with its lineage; other products are untouched | Test with two products at different issue times |
| V-8 | Solar geometry is exact | Elevation and azimuth per site and timestep, validated against a reference implementation | Unit test against known values |
| V-9 | The abstraction holds across formats | KML, a binary grid format and a text catalogue all arrive through the same transport/decoder SPI | Satisfied when the grid provider lands |
| V-10 | Adding a provider does not touch the core | A new provider is a new bundle plus configuration; zero changes in model/api/repository/rest | Reviewed as a diff whenever a provider is added |
| V-11 | Ingest is polite to the source | Unchanged upstream data causes no full re-download | Conditional-GET behaviour asserted against a fixture |
| V-12 | API stability is enforced mechanically | Baselining fails the build on an unintended breaking change | bnd baselining active in CI from Increment 1 |
| V-13 | Decoding and derivation are trustworthy | Real coverage on decoders, mappings, derivations and query logic | Coverage gate in CI; every mapping and derivation has a test |
| V-14 | Operable by someone who did not write it | A newcomer deploys, registers a site, configures a provider and diagnoses a failure from documentation alone | Walkthrough per major increment |

Absent by design: no bundle count, no lines-of-code target, no "modern architecture" claim. Those
are means.

## Sequencing: a vertical slice, not layers

The purpose spans the whole stack — a site forecast needs model, provider SPI, durable storage, grid
handling, solar geometry and API simultaneously. Building horizontally (all of persistence, then all
of ingest, then all of the API) would produce nothing usable for a long time and would be a poor fit
for one person working in intervals.

So the plan is shaped as a **walking skeleton**: the thinnest end-to-end path that answers a real
question for a real site, then broadened.

- **The [MVP](08-mvp.md) — registered sites with ids, MOSMIX from the bound station and ICON-D2 from
  the bound cell as separate datasets in one report per site, solar day events and position, every
  value with provenance, persisted as XMI in a configurable folder, reachable in-process by site id.**
  No HTTP API, no index, no operational qualities beyond conditional GET and retry. **Site-accurate
  from the first deployment** — the core claim is true immediately, because `Q-I` came out favourably.
- **Depth — reading helpers** across datasets, as-of queries over the archive, temporal interpolation,
  retention.
- **Breadth — the HTTP API**, observations for accuracy checking, ad-hoc coordinates, further derived
  quantities, and the operational qualities an externally consumed service needs.
- **Deferrable indefinitely:** radar/nowcasting, additional national providers, PV add-on modules,
  bias correction.

An earlier version of this plan had a first step that was *deliberately less accurate* than the existing
service — station-only values, honestly labelled. That step is gone.

Detailed increments, exit criteria and effort classes are in
[06-redesign-plan.md](06-redesign-plan.md).

## Acknowledged tension

Stated plainly rather than buried: **the vision remains larger than the available capacity** —
roughly one person alongside project work, no fixed date. Site-accurate multi-source data with
gridded products, durable storage, lineage and uncertainty is a substantial system.

Two things make it tractable, and neither is "shrink the vision":

- **Focus was narrowed where it costs the most and buys the least.** DWD only. No second national
  provider. No yield model. No radar. Each of those exclusions is what makes the remainder
  achievable, and each is recoverable later because the architecture is built not to preclude it.
- **The walking-skeleton sequence means every slice is independently useful.** Stopping after any
  slice leaves a coherent, deployable service rather than a construction site — which is the only
  realistic property for work done in intervals.

The one non-negotiable structural commitment is that **lineage and uncertainty exist in the model
from the first slice**, before anything computes derived values. Everything else can arrive late;
provenance cannot arrive at all if it is not captured when the data does.

Tracked as [risk R-1](06-redesign-plan.md#risks); revisit whenever capacity changes.
