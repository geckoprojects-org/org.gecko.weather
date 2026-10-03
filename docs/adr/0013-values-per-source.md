# ADR-0013: Values are kept per source; combining them is the consumer's decision

- **Status:** Accepted
- **Date:** 2026-10-03
- **Deciders:** Mark Hoffmann
- **Supersedes:** [ADR-0012](0012-fusion-and-supersession.md)

## Context

The dossier was written with energy optimisation as *the* purpose of the service. That framing has
been corrected: **Gecko Weather is a weather backend.** Energy optimisation is one consumer of its
data; PV-specific computation (plane-of-array irradiance and the like) is an add-on module on top of
the service, not the service itself.

[ADR-0012](0012-fusion-and-supersession.md) followed from the old framing. It put *fusion* into the
service — one best value per `(site, kind, validAt)`, selected by a configurable per-kind source
priority — on the argument that the selection is "the hardest domain judgement" and should not be left
to every consumer. [08-mvp.md](../08-mvp.md) of 2026-07-29 then moved that merge from read time to
ingest time and persisted the merged instance.

Both variants assume the service knows better than the consumer which source should win. Once there
is more than one kind of consumer, that assumption fails:

- An energy optimiser wants radiation from the grid cell and does not care about significant weather.
- A display wants the station's significant-weather code and the MOSMIX temperature curve.
- An accuracy analysis wants *all* sources side by side, with their issue times.
- A later PV module wants direct and diffuse radiation separately, exactly as ICON-D2 publishes them.

A single merged number hides precisely the information these consumers differ on. And the sources
make it worse: they refresh on different cadences — MOSMIX_S hourly, MOSMIX_L every six hours,
ICON-D2 every three hours, SIS every fifteen minutes — so a value for two hours ahead can be replaced
within the hour by a newer, presumably better one from the same product, while another product still
shows the older issue. A merge has to hide that too.

## Decision

**The service does not merge. It keeps every value per source, with full provenance, and hands the
consumer everything it currently knows for a site.**

- A `WeatherReport` per site holds **one `SourceDataset` per source product** — MOSMIX_L from station
  10488, ICON-D2 from cell (884, 394), the computed solar dataset. Every `MeasuredValue` in a dataset
  carries its own `Provenance` and `Uncertainty` ([ADR-0011](0011-lineage-and-uncertainty.md)); there
  is no merged value anywhere in the model.
- **A refresh replaces the dataset of that product and nothing else.** Different products in one report
  legitimately have different issue times; each dataset says what it is (`issuedAt`, `modelRun`,
  `expectedRefresh`, horizon) so a consumer can judge staleness without knowing the product.
- **The superseded dataset is archived, not deleted.** The repository writes it to an append-only
  archive per site. "What was predicted for tomorrow 14:00, and when?" stays answerable (`INT-17`),
  at the cost of files rather than a storage model.
- **Consumers read across datasets** by `(kind, validAt)` and apply their own rule. The API may offer
  *helpers* for that reading — a timeline per kind, the newest issue per product — and a separate,
  clearly consumer-side module may offer named selection policies ("prefer grid for radiation") for
  those who want one. Neither lives in the report, and neither is applied unless the consumer asks for
  it by name.

## Consequences

### Positive

- **Nothing is hidden.** Station and grid values for the same hour sit next to each other with their
  distances and issue times; the crossing from one product's horizon to another's is visible by
  construction, not by a provenance trick.
- **Different refresh cadences stop being a problem to solve.** A dataset is replaced when its product
  publishes; the report is never "between states" because no cross-dataset consistency is promised.
- **No merge policy, no policy version, no reprocessing.** `QR-7` reproducibility reduces to derived
  values (solar position), which are recomputable from their `Derivation`.
- **The MVP shrinks.** `compute.merge` disappears from the plan; report assembly is dataset replacement
  plus archiving.
- Energy optimisation loses nothing: it reads the ICON-D2 dataset for radiation and cloud, the MOSMIX
  dataset for temperature and wind, and knows from provenance which is which.

### Costs accepted

- **Every consumer does its own alignment.** Reading values per `(kind, validAt)` across datasets is
  repeated work, and a careless consumer can pick badly — a station value for radiation when a grid
  value exists. The helpers mitigate the first; provenance makes the second visible; neither prevents
  it. This is the cost ADR-0012 refused to pay, and it is accepted now because the alternative hides
  information that the consumers demonstrably differ on.
- **The report is larger.** Two sources for cloud cover means two values per timestep. Volumes stay
  small because of subset-on-ingest ([ADR-0010](0010-subset-on-ingest.md)) — tens of values per
  timestep per site, not thousands.
- **`INT-2` changes.** It asked for a timeline "already fused across sources, with no client-side
  alignment required". It now reads: one request returns everything known for a site, per source,
  aligned on `validAt` within each dataset. The requirement text is updated with this ADR.
- **The archive grows with every refresh,** not just with every timestep. Retention has two dimensions
  (`M-10`, `OPS-8`) — unchanged from ADR-0012, just applied to files.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Fusion in the service, computed on read** (ADR-0012) | Assumes one right answer per value. The consumers listed above want different answers; a priority configuration serves one of them and misleads the rest. |
| **Fusion at ingest, merged instance persisted** (08-mvp, 2026-07-29) | Same assumption, plus a merge policy version per value and a reprocessing job whenever the policy changes. |
| **Per-source datasets *and* a merged "best" dataset beside them** | Tempting as a default view. Rejected for now: it reintroduces the policy, and a "best" dataset that is wrong for a consumer is worse than none. Can be added later as a named, consumer-side policy without changing the model. |
| **Per-timestep structure** (one `Timestep` holding all sources' values) | Reads nicely for a timeline. Rejected because update semantics become cross-cutting: a MOSMIX refresh would touch every timestep object instead of replacing one dataset, and products with different timestep grids (15-minute SIS, hourly ICON) do not share a timestep anyway. |

## Open points

- Which reading helpers the API offers in the first version: timeline per kind across datasets, newest
  issue per product, values at an instant — and whether any of them interpolates in time.
- Whether a consumer-side policy module is wanted at all before a second consumer exists.
- Retention of the archive (`M-10`, `Q-B`): by age, by issue depth per product, or both.
