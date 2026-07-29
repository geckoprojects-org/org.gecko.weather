# ADR-0010: Subset-on-ingest for gridded products

- **Status:** Accepted
- **Date:** 2026-07-28
- **Deciders:** DIM development team (redesign kickoff)
- **Supersedes:** —

## Context

Site accuracy depends on gridded products — radiation and cloud cover — because station point forecasts
are spatially insufficient ([ADR-0009](0009-site-as-central-entity.md)). Gridded fields are large;
a site needs almost none of one.

The existing SIS spike quantifies this. `NetCDFTest` decodes DWD SIS satellite global radiation — `SIS`
in W/m² on a 0.05° grid over Germany with `lat`, `lon` and `time` axes — and iterates the entire
`time × lat × lon` array, creating one `UVRadiationMeasurement` per cell per timestep (`F-19`). At 0.05°
over Germany that is on the order of tens of thousands of cells per timestep, roughly half a million
objects per run, to serve a house that needs **one** cell.

So the question is what to store: everything, or only what is needed.

## Decision

**Extract and store only the grid cells that registered sites are bound to.** The decoder receives the
requested cell window in `DecodeContext` and skips everything else
([ADR-0003](0003-provider-spi.md)). Where a source supports server-side subsetting, that is used in
preference to downloading and discarding (`OPS-9`).

## Consequences

### Positive

- **Gridded data becomes feasible at all** for a one-person, file-backed deployment. Ingest for one site
  costs roughly one value per kind per timestep per provider — order of 1,500 values per site per run,
  kilobytes rather than hundreds of megabytes.
- Storage grows with **sites**, not with country size or grid resolution, so a finer-resolution product
  costs nothing extra (`QR-8`).
- The file-based repository default stays defensible ([ADR-0004](0004-persistence-index-split.md)); with
  full grids it would not be.
- Ingest duration and memory are bounded and predictable, which is what makes `QR-8` and `OPS-15`
  answerable.
- Politeness towards the supplier: less transferred, especially with server-side subsetting (`S-4`).

### Costs accepted

These are the most consequential accepted costs in the entire dossier, because they are **irreversible**.

- **A newly registered site has no history.** Until it has been ingesting, nothing exists for it, and
  sources rarely permit retrospective retrieval — DWD SIS analysis files and MOSMIX `LATEST` are
  published and then gone. Mitigated, not solved, by best-effort backfill at registration and a recorded
  `dataCompleteFrom` telling consumers where the site's history genuinely begins (`OPS-16`).
- **Ad-hoc coordinates far from any registered site have no gridded data.** They can only be answered
  from station forecasts — with exactly the spatial error this project exists to remove. Acceptable
  *only* because lineage and uncertainty mark such an answer as degraded rather than presenting it as
  equal ([ADR-0011](0011-lineage-and-uncertainty.md), `INT-7`).
- **No retrospective analysis over areas.** Questions like "what was the irradiance across this region
  last July?" are permanently unanswerable, because the data was never stored. If area analysis ever
  becomes a requirement, it requires a different ingest strategy from that day forward and cannot
  recover the past.
- **Registration becomes a consequential operational act.** Forgetting to register a site is not a
  configuration oversight that can be corrected later; it is permanent data loss for that location.
  This must be prominent in operator documentation, not a footnote.
- Bindings must be resolved *before* ingest, creating an ordering dependency between the site registry
  and ingest that a full-grid strategy would not have.
- Requires decoders capable of random or windowed access. Fine for NetCDF and GRIB2; a format that only
  supports sequential whole-field reads would force reading everything and discarding — still cheaper in
  storage, not in transfer.

**This trade is accepted knowingly.** The alternative — full grids — buys retrospective flexibility for
a cost that makes the whole design infeasible at the available capacity. But the costs above are real,
permanent, and must be revisited if `Q-C` reveals many sites across wide areas, at which point storing
full grids for a rolling window becomes worth reconsidering.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Store full grids** | Any coordinate answerable immediately and retrospectively, and no registration ordering problem. Rejected on volume: per-run field sizes are orders of magnitude above what a file-based repository and a one-person deployment can carry, and almost all of it would never be read. |
| **Hybrid: full grid for a rolling window, site cells retained permanently** | Genuinely attractive — a new site gets backfilled from the window, and old history stays compact. Rejected **for now** as added complexity (two storage regimes, two retention policies) before the simple case is proven. This is the first thing to reconsider if `Q-C` shows many or moving sites. |
| **Fetch on demand at query time** | No ingest storage at all. Unworkable: sources publish perishable snapshots, so by query time the data may be gone — contradicting the vision's durability premise. |
| **Store a coarse aggregate of the full grid plus exact site cells** | Enables rough area queries cheaply. Rejected as speculative: no requirement asks for area queries, and an aggregate that no one uses is pure cost. |

## Open points

- Whether to extract a **small neighbourhood** (for example 3×3 cells) around each site rather than a
  single cell. Cheap, and it would enable spatial interpolation and a plausibility check against
  neighbours — likely worth it. Decide during increment 2.3 with real data.
- How far backfill can actually reach per product — how long each product stays on the server. Must be measured rather than
  assumed, and the answer belongs in operator documentation.
- Whether server-side subsetting is available for any DWD product. If OPeNDAP or equivalent is offered
  for SIS, transfer volume drops further.
- Re-resolution when a provider changes its grid definition: existing cell indices become wrong, and
  silently wrong is the dangerous case.
