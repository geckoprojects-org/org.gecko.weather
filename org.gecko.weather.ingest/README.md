# org.gecko.weather.ingest

The ingest runtime: what turns providers, sink and repository into a service that keeps itself
current.

| Class | Does |
| --- | --- |
| `ProviderJob` | one run of one product: active sites' bindings → `WeatherProvider.fetch` with the persisted `SourceState` → every dataset through `WeatherDataSink.replace`, or `append` for a provider whose `delivery()` is `STREAM` (SIS analyses) → state saved. Bookkeeping for `IngestStatus`. No threads. |
| `IngestScheduler` | one daemon thread; each job reschedules itself by its own `nextDelay()`. Implements `IngestControl` (`status`, `runNow`). One thread on purpose: providers share network and repository, a slow source must not fan out. |
| `IngestRuntimeComponent` | the DS service; a job per `WeatherProvider` that comes and goes with the whiteboard |

## Pacing

Polls are conditional requests, so a poll that finds nothing costs one round trip. The runtime
therefore polls every `pollInterval` (default 20 min) regardless of a product's cadence; the product's
`expectedRefresh` is what staleness is judged against, not when to ask.

| Outcome | Next run |
| --- | --- |
| success, changed or unchanged | `pollInterval` |
| `IOException` (transport) | `backoffInitial × 2^(n−1)`, capped at `backoffMax` — 1 min, 2, 4, … 1 h |
| `FetchException` (content not as expected) | `pollInterval` — retrying sooner would not make the file better |
| anything else | `pollInterval`, logged as an error |

`runNow` resets a pending backoff and runs on the scheduler thread.

## State

`SourceState` (ETag / Last-Modified per URL) is persisted after every change as a
`SourceStateRecord` through `WeatherRepository.saveSourceState`, so a restart continues with
conditional requests instead of downloading everything again.

## Configuration

PID `org.gecko.weather.ingest`, optional — the runtime itself causes no traffic, configured providers
do. `pollInterval` `PT20M`, `initialDelay` `PT10S`, `backoffInitial` `PT1M`, `backoffMax` `PT1H`.
