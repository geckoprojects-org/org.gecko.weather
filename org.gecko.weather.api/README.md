# org.gecko.weather.api

The service contracts. Four exported packages, each versioned on its own:

| Package | For | Contains |
| --- | --- | --- |
| `org.gecko.weather.api` | consumers | `SiteRegistry` (register, assign, rebind, deactivate, remove), `WeatherService` (report, values of a kind, `ValueQuery`, timeline, archive — all by site id), `Reports` (the same reads as pure functions over a report you already hold) |
| `org.gecko.weather.api.repository` | the storage implementation | `WeatherRepository` — sites, reports, append-only archive, catalogues |
| `org.gecko.weather.api.solar` | consumers and report assembly | `SolarService` — position at an instant, `DayInfo` for a date |
| `org.gecko.weather.api.spi` | providers | `WeatherProvider` (one `fetch` for all bound sites, returns ready datasets), `SiteBindingResolver`, `FetchRequest`/`FetchResult`/`SourceState` |

## Reading values: never merged

Every read returns each source's values with their provenance, ordered by `validAt` and then newest
issue first (`Reports.TIMELINE_ORDER`). A `ValueQuery` narrows — kind, level, statistic, window,
products — it never selects a winner. See [ADR-0013](../docs/adr/0013-values-per-source.md).

```java
weather.values("home", MeasurementKind.AIR_TEMPERATURE);                       // the whole temperature forecast
weather.values("home", ValueQuery.of(UV_INDEX));                                // only UV
weather.timeline("home", ValueQuery.of(CLOUD_COVER).level(CLOUD_LOW)
                                   .products("ICON-D2").between(now, now.plus(24h)));
```

## Providing data: one call per run

A `WeatherProvider` is handed every site bound to its product and the change-detection state of the
previous run, and returns `Unchanged` or `Fetched(datasets by site, new state)`. It downloads what the
source publishes per product once and extracts each site's stations or cells from it. Transport,
decoding and mapping are the provider's internals — shared helpers welcome, but not SPI — so that they
can be shaped per source and tested offline against fixtures. The runtime owns scheduling, retry,
state persistence, report assembly and the archive.

This replaces the four-interface SPI sketched in ADR-0003; the revision is recorded there.

## Tests

Plain JUnit 5 in `test/`: `Reports` and `ValueQuery` over a two-source report, `SourceState`.
