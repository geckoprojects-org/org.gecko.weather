# ADR-0011: Lineage and uncertainty as first-class model concepts

- **Status:** Accepted
- **Date:** 2026-07-28
- **Deciders:** DIM development team (redesign kickoff)
- **Supersedes:** —

## Context

Today a value is a bare number. `MOSMIXSWeatherReport` features are plain `EFloatObject` attributes;
`Measurement` has `unit`, `name`, `timestamp` and `rawValue` — no source, no model run, no issue time, no
quality (`F-20`). That is tolerable while every value comes from one product at one station.

It stops being tolerable the moment values can be:

- read from a **grid cell** at some distance from the site ([ADR-0009](0009-site-as-central-entity.md)),
- **interpolated** in time between source timesteps,
- **fused** from several sources with different issue times ([ADR-0012](0012-fusion-and-supersession.md)),
- **derived** from other values,
- or served for an **ad-hoc coordinate** with no gridded data at all
  ([ADR-0010](0010-subset-on-ingest.md)).

At that point "340 W/m²" is not self-explanatory. Two questions must always be answerable: *where did
this come from, and how much should I trust it?* Integrators are making decisions with money attached
(`INT-7`), and domain reviewers will ask for provenance directly.

There is a hard constraint: **provenance cannot be retrofitted.** A value stored without knowing which
run and which cell produced it has lost that information permanently. So this cannot be deferred to the
slice where fusion arrives; it has to exist before the first value is stored.

## Decision

Every `MeasuredValue` carries **`Provenance`** and **`Uncertainty`**. This is a model invariant, not a
convention — there is no constructor path that produces a value without them.

```
Provenance
  providerId    : String       // "dwd.mosmix", "dwd.sis"
  productId     : String       // "MOSMIX_L", "SISfc"
  modelRun      : Instant?
  issueTime     : Instant?
  origin        : Origin       // STATION | GRID_CELL | COMPUTED | ADHOC
  stationId     : String?
  gridRef       : GridRef?
  distance      : double?      // metres from the site
  derivation    : Derivation?  // function id + references to input values

Uncertainty
  quality       : Quality      // MEASURED | FORECAST | INTERPOLATED | FUSED | DEGRADED
  spatialMetres : double?
  temporal      : Duration?
  sourceSpread  : double?
  note          : String?
```

Lineage travels **with the value** in API responses by default, not in a separate call
([ADR-0008](0008-rest-api-design.md)).

## Consequences

### Positive

- **Degradation cannot be silent** (`V-5`). A station-only value 20 km away and a value from the site's
  own cell are structurally distinguishable, so `INT-3` is verifiable rather than merely asserted.
- **Results are reproducible** (`QR-7`). Given stored inputs and a recorded derivation, a computed value
  can be recomputed and defended — which is what makes the derivation layer trustworthy rather than a
  black box.
- Fusion becomes explainable: a fused value names its contributors and their disagreement.
- Mixed-horizon answers become honest: where SIS ends at +18 h and MOSMIX continues, the transition is
  visible in the data rather than hidden in a smooth-looking curve.
- Later accuracy analysis and bias correction become possible at all, because the conditions under which
  each prediction was made are recorded.
- The subset-on-ingest and ad-hoc-coordinate compromises become **defensible** — they are acceptable
  precisely because their degraded results are labelled.

### Costs accepted

- **Every value is substantially heavier.** A double plus two structures instead of a double. Storage,
  memory and serialisation all grow by a large multiple. Tolerable only because subset-on-ingest keeps
  the value count small — these two decisions depend on each other.
- **Responses are verbose by default.** Mitigated by `?lineage=summary`, but the verbose default is
  deliberate: opt-out is safe, opt-in would mean most consumers never see quality information.
- **Provenance can be wrong**, and wrong provenance is worse than none — it invites misplaced trust.
  Every provider must populate it correctly, which is a per-provider obligation and a per-provider test.
- **Uncertainty is partly judgement.** "How uncertain is a value carried 3 km from a grid cell centre?"
  has no rigorous answer available to us. The design records the *facts* (distance, method, spread) and
  a coarse `quality` classification rather than pretending to a calibrated error bar. That honesty is
  itself a limitation to document, so consumers do not read `sourceSpread` as a confidence interval.
- Repeated provenance across many values of the same run is redundant. Deliberately not normalised in
  the model — sharing a provenance object would couple value lifetimes and complicate supersession — so
  compression is an implementation concern of the repository.
- Model churn: adding a provenance field affects stored data (`R-10`, `OPS-13`).

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Provenance per report or per ingest batch, not per value** | Far cheaper and adequate while one report comes from one source. Breaks completely under fusion: a fused timestep's values come from different providers, runs and origins, which is exactly the case that needs provenance most. |
| **Provenance in a side table, joined on request** | Keeps values light. Rejected because it makes lineage opt-in in practice — a consumer who does not join sees bare numbers — and because it complicates supersession, where value and provenance must stay together. |
| **Only a coarse quality flag, no structured lineage** | Much cheaper and would satisfy `INT-7` minimally. Rejected because it cannot satisfy `QR-7` reproducibility, and because "degraded" without saying *how* is not actionable. |
| **Full provenance graph** (every derivation edge as a first-class entity, queryable) | Rigorous and would support deep audit. Rejected as over-engineering for one maintainer: a reference from a derived value to its inputs is sufficient for the reproducibility actually required. |
| **Defer to the slice where fusion arrives** | Would simplify Slice 1 considerably. Rejected on the hard constraint: provenance not captured at ingest time no longer exists. This is the reason it is in Slice 1. |

## Open points

- Whether `quality` is a single enumeration or orthogonal flags. A value can be both `FORECAST` and
  `INTERPOLATED`, which suggests flags — but flags are harder for consumers to reason about than a
  worst-case classification. Decide during increment 1.1.
- Whether `Derivation` references input values by identity or by value. Identity is better for
  reproducibility and requires stable value identifiers, which interacts with supersession.
- How to express uncertainty where the source publishes its own (some products carry quality flags) so
  that source-provided and derived uncertainty are not conflated.
- Whether `distance` alone is enough for spatial quality, or whether terrain matters — a cell 3 km away
  across a ridge is not equivalent to 3 km across a plain. Out of scope now; noted because it is a real
  limitation of a distance-only measure.
