# ADR-0007: Ingest runtime with conditional GET, retry and per-provider isolation

- **Status:** Accepted
- **Date:** 2026-07-28
- **Deciders:** DIM development team (redesign kickoff)
- **Supersedes:** —

## Context

Ingest today is a method on the fetcher component. `DWDMOSMIXStationForecastFetcher` is annotated
`@CronExpression(cron = {CRON_EXPRESSION_HOURLY, CRON_EXPRESSION_REBOOT})` and its `run()` performs
download, unzip, load and decode in sequence, declaring `throws Exception` and letting failures
propagate to the scheduler (`F-10`).

The consequences are all operational:

- **No change detection.** `DWDFetcher.doDownload()` calls `url.openStream()` with no `ETag` or
  `If-Modified-Since`. MOSMIX_L per station publishes roughly every four hours, so most hourly fetches
  re-download unchanged data (`F-9`). Cost scales with configured stations for no benefit, and
  unconditional polling of shared open-data infrastructure risks being throttled.
- **No retry.** A transient network failure silently costs a forecast window that cannot be re-fetched,
  because the source serves only `LATEST`.
- **No health signal.** The only evidence of failure is a log line — and per-element decode failures are
  caught individually and continued past, so a systematically broken mapping produces a partially
  populated report and no overall failure at all (`F-11`). Silent partial success is the worst failure
  mode for a data service: the data looks present and is wrong.
- **No isolation.** With one component per station and no failure accounting, there is no notion of
  "this provider is unhealthy" as distinct from "this run failed".

## Decision

Extract ingest into its own bundle that **orchestrates** the SPI collaborators from
[ADR-0003](0003-provider-spi.md) and owns the operational qualities:

- One ingest job **per provider**, with its own schedule, its own failure state and its own health
  record. A provider's failure never affects another provider or the API (`OPS-4`).
- **Conditional requests by default.** Ingest holds the last `ETag` / `Last-Modified` per source and
  passes them in `SourceRequest`; an `Unchanged` response ends the run successfully without decoding
  (`OPS-6`).
- **Bounded exponential backoff** on failure, and **jittered schedules** so many sites or providers do
  not hit a source simultaneously (`OPS-7`).
- A `ProviderHealth` service per provider recording last success, last change, data age and consecutive
  failures, exposed read-only over HTTP (`OPS-3`).
- **Decode errors are counted and surfaced.** A systematically failing mapping fails the run rather than
  producing a partial result; isolated per-value failures are counted and reported, never silently
  dropped.
- Readiness is "repository reachable and index ready or rebuilding" — explicitly **not** "all providers
  healthy", so an unreachable source delays data rather than startup (`QR-9`).

## Consequences

### Positive

- The service becomes operable: "is the data current?" is answerable without reading logs (`OPS-3`).
- Politeness towards suppliers becomes the default rather than a later optimisation, and freshness gets
  cheaper — polling more often costs almost nothing when nothing changed (`S-4`, `OPS-6`).
- Perishable data is protected against transient failures by retry, which matters precisely because a
  missed window is unrecoverable.
- Scheduling is configuration per provider, matching the reality that MOSMIX_L, MOSMIX_S, DMO and SIS
  publish on different cadences (`OPS-5`).
- Providers become simpler: a provider bundle contributes transport, decoder, mapping and binding
  resolution, and knows nothing about scheduling, retry or health.

### Costs accepted

- **Ingest becomes a component with state** — last `ETag`, failure counts, health — which must itself be
  persisted or accepted as lost on restart. Losing it means one unnecessary re-download after restart,
  which is acceptable; losing it silently and re-downloading forever would not be.
- More moving parts than a `@CronExpression` on a fetcher. The scheduling annotation approach is genuinely
  elegant for the simple case, and this trades that elegance for observability.
- Conditional requests only help where the source supports them. For sources that do not, change
  detection needs a fallback (content hash), which costs the download it was meant to avoid.
- Jitter makes ingest timing non-deterministic, which slightly complicates tests. Mitigated by making
  jitter injectable.
- Failing a run on systematic decode errors is stricter than today and will surface problems that are
  currently invisible — expected, but it means the first deployment may fail loudly where the old one
  quietly produced partial data.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Keep `@CronExpression` on providers** | Elegant and already working, but distributes health, retry and change detection across every provider, so each one reimplements them — or, as today, none of them do. |
| **A generic scheduler library with retry** (Quartz-style) | More capability than needed, and the existing `biz.aQute.scheduler` / `org.eclipse.osgi.technology.scheduler` already provides cron. The missing part is not scheduling, it is the surrounding operational state. |
| **Push/subscribe from the source** | Not available: DWD Open Data is a file server. Not applicable to any in-scope provider. |
| **Ingest triggered by query** (lazy) | Would avoid scheduling entirely, but perishable sources make it unworkable — by the time someone asks, the data may be gone. Directly contradicts the vision's durability premise. |

## Open points

- Whether ingest state (`ETag`, health counters) is stored in the repository, in Configuration Admin, or
  held in memory and accepted as lost. Leaning towards the repository, so restart does not cause a
  re-download storm.
- Whether one ingest job per provider is fine-grained enough, or whether per-provider-per-site jobs are
  needed. Per provider is the starting point: a grid provider fetches one file serving many sites, so
  per-site jobs would multiply requests — the opposite of the goal.
- Alerting: health is exposed, but nothing notifies anyone. Out of scope here; a consumer of the health
  endpoint's concern.
