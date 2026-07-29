# ADR-0009: The site is the central entity, not the station

- **Status:** Accepted
- **Date:** 2026-07-28
- **Deciders:** DIM development team (redesign kickoff)
- **Supersedes:** —

## Context

This is the decision the redesign exists for.

Today a forecast location **is** a weather station. `MOSMIXStationConfig` defines exactly `stationId`,
`name`, `latitude` and `longitude`; the fetcher builds a `Station` from that configuration, or takes it
from the KML placemark when the configured name is `"NONE"`. Either way the report's location is the
station's location, and a configured `latitude`/`longitude` that differs from the station's is silently
ignored rather than used to correct anything (`F-17`).

For the purpose — optimising a building with PV and a heat pump ([01-vision.md](../01-vision.md)) — this
is structurally insufficient. MOSMIX carries the right quantities; the model already has
`cloudCoverTotal/Effective/High/Mid/Low` and `irRadianceGlobal`. The problem is purely spatial: those
values describe a station, MOSMIX stations are sparse, and cloud cover — the quantity that decides PV
yield — routinely differs between locations 20 km apart at the same hour.

Fixing it requires combining sources with different spatial characteristics — station point forecasts,
gridded fields, and solar geometry computed exactly. That combination needs something to combine *for*,
and that something is not a station.

## Decision

Introduce the **site** as the central domain entity: a location of interest with its own identity,
coordinates and elevation. Registering a site **resolves and persists its source binding** — which
station(s) apply and how far away, which grid cell to read in each gridded product — and that binding is
what drives ingest.

```
Site
  id, name
  location          : GeoPosition     // lat, lon, elevation
  attributes        : Map<String,String>   // extensible; tilt/azimuth for stage 2
  bindings          : SourceBinding[]
  dataCompleteFrom  : Instant

SourceBinding
  providerId, stationId?, gridRef?, distance, resolvedAt
```

Each provider contributes a `SiteBindingResolver`: a station provider does nearest-station search, a grid
provider does index arithmetic on its lat/lon axes.

**Two access modes, different guarantees:**

- **Registered sites** are actively supplied and historised, get the full fusion, and have their own
  history.
- **Ad-hoc coordinates** are answered best-effort from data that already exists, and are explicitly
  marked as degraded (`INT-13`, `INT-7`).

## Consequences

### Positive

- The vision's central claim becomes expressible. "What weather will this location get?" has a subject.
- **"Which quadrant do I read?" is answered once per site and persisted**, rather than recomputed or
  guessed. This is what makes [ADR-0010](0010-subset-on-ingest.md) possible: ingest knows exactly which
  cells matter.
- Distance from the site to each source is known and can be reported, so `INT-3` is verifiable rather
  than merely claimed — a value from a station 20 km away and one from the site's own cell are
  distinguishable.
- Solar geometry is computed for the site's exact coordinates, so it has no spatial error at all and
  needs no storage.
- A site accumulates history, which is the precondition for later accuracy checking and bias correction.
- Site `attributes` give stage-2 plane-of-array irradiance somewhere to land without a model break
  (`INT-16`, `Q-J`).

### Costs accepted

- **Sites are state.** They must be stored, their bindings resolved and re-resolved when a provider's
  grid or station set changes, and they must survive restart. The current implementation has no
  comparable state.
- **Binding resolution is a new failure mode.** A site in a region a provider does not cover, or outside
  a grid's extent, must resolve to "no binding" cleanly rather than to a nonsensical nearest cell.
- **Registration becomes consequential.** With subset-on-ingest, a site not registered is a site with no
  data — so registration is an operational act with a permanent effect on what history exists
  ([ADR-0010](0010-subset-on-ingest.md), `OPS-16`).
- The two access modes mean **two quality tiers** and a support burden: explaining why an ad-hoc
  coordinate is worse than a registered site is a documentation obligation, not just a code path.
- Sites exist both as configuration (`OPS-5`) and as an API resource (`INT-1`), and those two have to
  converge on one source of truth (open point in [ADR-0008](0008-rest-api-design.md)).
- Re-resolution policy: if a provider changes its grid, existing bindings become stale. Detecting that
  is not free.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Keep the station as the subject, interpolate at query time** | No stored state, no registration. But with subset-on-ingest there would be nothing to interpolate *from* for grid data, because nothing would tell ingest which cells to fetch. Requires full-grid ingest, which was rejected in [ADR-0010](0010-subset-on-ingest.md). |
| **Coordinates as a pure query parameter, no registration at all** | Maximum flexibility and no state. Requires storing complete grids so any coordinate is answerable — the volume problem `F-19` demonstrates — and forfeits per-site history entirely, so accuracy can never be checked. |
| **Site as a client-side concept only** | The service serves stations and grid cells; the consumer combines them. Honest, but pushes the hard part — cell resolution, temporal alignment, fusion, quality assessment — onto every consumer, to be solved differently and mostly wrongly each time. That is the value the service is supposed to add. |
| **Site = station with an offset** | Minimal change from today. Does not help: the offset cannot improve a value that was never measured near the site. |

## Open points

- Whether a site binds to **one** station or several. Several would allow spatial interpolation between
  stations for quantities with no gridded product; one is simpler. `Q-I` yielded a gridded cloud product, so
  multi-station binding lost most of its value: only air temperature and wind stay station-only.
- Re-resolution triggers: on provider version change, on schedule, or manually.
- Whether sites carry a time zone for day-boundary semantics, or whether that is derived from
  coordinates.
- Site lifecycle: what deleting a site does to its accumulated history. Leaning towards retaining the
  data and marking the site inactive, since the history is unrecoverable if discarded.
