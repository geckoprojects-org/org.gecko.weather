# ADR-0012: Configurable fusion, supersession instead of overwrite

- **Status:** Accepted
- **Date:** 2026-07-28
- **Deciders:** DIM development team (redesign kickoff)
- **Supersedes:** —

## Context

A site's forecast is assembled from sources that publish on **different cadences with different lag**:
MOSMIX_L per station roughly every four hours, MOSMIX_S hourly, DMO twice daily, DWD SIS with an
18-hour forecast horizon. So a site's timeline is never "finished" — pieces arrive asynchronously, and
different parts of one timeline legitimately come from different model runs.

Two questions follow. **Which value wins** when several sources cover the same quantity at the same
instant? And **what happens to the previous value** when a fresher one arrives?

The second question is easy to answer wrongly. Overwriting in place is the obvious implementation and it
permanently destroys the ability to ask "what did you predict for tomorrow 14:00, and when did you
predict it?" — which is the precondition for `INT-17` and for any later accuracy analysis or bias
correction. Like provenance, it cannot be retrofitted.

There is also a scoping question: is the fused timeline **stored** or **computed on request**?

## Decision

**Fusion is configurable and computed on read. Storage supersedes rather than overwrites.**

- `WeatherRepository.store` **never overwrites**. A newer value for the same
  `(site, kind, validAt)` supersedes the older one, which remains retrievable.
- A `FusionStrategy` picks or combines candidates per `(kind, validAt)` according to **configured
  per-kind source priority** — for radiation the grid product outranks the station forecast; for
  quantities with no gridded source the station is all there is.
- The fused value's `Provenance` **references its contributors**, and its `Uncertainty` records their
  disagreement ([ADR-0011](0011-lineage-and-uncertainty.md)).
- Fusion runs **on read**, not at ingest.
- Missing sources degrade individual timesteps; they never fail the request (`INT-8`).

```java
public interface FusionStrategy {
    MeasuredValue fuse(MeasurementKind kind, Instant validAt, List<MeasuredValue> candidates);
}
```

## Consequences

### Positive

- **"Best available now" is the natural behaviour, not a special case** (`INT-8`). Whatever has arrived
  is fused; whatever has not lowers the quality of affected timesteps.
- Computed-on-read is always consistent with current configuration and current data. A stored fused
  timeline would go stale whenever fusion priority changed or a fresher source arrived, and would
  duplicate data already stored.
- Changing fusion policy takes effect immediately, with no reprocessing — which matters a great deal
  when the policy is being tuned.
- **Supersession keeps `INT-17` reachable.** Since it costs nothing extra to preserve at write time and
  is impossible to recover later, this is the clearest cheap-now/impossible-later decision in the
  dossier.
- Mixed-horizon handling falls out naturally: grid where available, station beyond, transition visible in
  lineage (`R-3`).
- Accuracy analysis and bias correction become possible later without a storage redesign.

### Costs accepted

- **Storage grows with every revision**, not just with every timestep. A timestep re-forecast six times
  is six stored values. Retention must therefore prune by supersession depth as well as by age
  (`OPS-8`), and the naive "keep everything forever" default would grow faster than expected.
- **Fusion cost is paid per request.** With ten kinds over 48 hourly timesteps and three sources this is
  small, but it is repeated work. If `QR-2` latency measurement shows it matters, materialising becomes
  a cache in front of an unchanged contract — a reversible optimisation, deliberately not built now.
- **Queries must be supersession-aware.** "Give me the current forecast" and "give me what was known at
  time T" are different queries over the same data, and the default must be the former without making
  the latter awkward.
- **Fusion priority is configuration, so it can be configured badly.** Preferring a station over a grid
  cell for radiation would silently undo the accuracy gain. Sensible defaults ship, and lineage makes
  the mistake visible in responses.
- **Combining rather than selecting is a domain judgement.** Averaging two disagreeing sources can be
  worse than picking the better one. The initial strategy is deliberately **priority-based selection**,
  not blending, with `sourceSpread` recording disagreement for the consumer to see. Genuine blending
  waits for evidence that it helps.
- Value identity must be stable enough for supersession chains and for `Derivation` references
  ([ADR-0011](0011-lineage-and-uncertainty.md) open point).

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Overwrite in place** | Simplest and smallest. Permanently forfeits `INT-17` and any later accuracy analysis, for a saving that subset-on-ingest already makes irrelevant. |
| **Materialise the fused timeline at ingest** | Faster reads, and the timeline is directly servable. Rejected: goes stale on configuration change, duplicates stored data, and makes "best available now" a cache-invalidation problem rather than a natural outcome. |
| **Fusion in the consumer** — serve all candidates, let the client choose | Honest and simple for the service. Rejected because it pushes the hardest domain judgement onto every consumer, to be solved differently and mostly wrongly each time. That judgement is the value the service adds. |
| **Averaging or weighted blending from the start** | Intuitively "more accurate". Rejected as unfounded: with one grid product and one station product, averaging a good spatial source with a poor one degrades the good one. Revisit when there are genuinely comparable sources. |
| **Keep only the latest plus one previous revision** | Bounds growth while retaining some history. Reasonable and worth reconsidering as a retention policy — but as a *retention* setting, not as a storage-model restriction. |

## Open points

- **Who owns fusion policy** — global operator setting, per site, or per query (`Q-F`). Global with
  per-site override is the leading candidate; per-query would make responses uncacheable and
  irreproducible.
- Retention semantics for superseded values: keep N revisions, keep revisions for M days, or keep all
  within the retention window. Depends on `Q-B`.
- Whether fusion may combine kinds — for example deriving cloud cover from radiation when no cloud
  product exists. That is derivation, not fusion, and belongs in `compute.derive`; noted because the
  boundary will be tempting to blur.
- How to represent "no value available at all" for a timestep: absent entry, or present entry with
  `DEGRADED` quality and no value. The latter is more informative and more verbose.
