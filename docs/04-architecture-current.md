# Current Architecture — As-Is Analysis

An honest assessment of the existing workspace, written to be referenced rather than re-argued.
Findings carry stable IDs (`F-1` … `F-20`) used throughout
[03-requirements.md](03-requirements.md), the ADRs and [06-redesign-plan.md](06-redesign-plan.md).

This document is deliberately not a criticism of the people who wrote the code. Most findings are
the normal consequence of a working prototype that grew: the original scope was "get DWD MOSMIX
forecasts served over REST", and for that scope the design is reasonable. The findings describe the
gap between that scope and the [vision](01-vision.md), not a gap between the code and good practice.

## Workspace overview

Bnd workspace, Gradle build, 11 projects. Hand-written code is small — roughly 3,400 lines outside
generated EMF sources.

| Bundle | Role | Hand-written LOC | Assessment |
| --- | --- | --- | --- |
| `org.gecko.weather.api` | Nominally the generic API; in practice the DWD base layer | ~740 | Misnamed — see `F-1` |
| `org.gecko.weather.model` | EMF model generated from `model/dwd-weather.ecore` | generated | Sound approach, wrong content — `F-2`, `F-5` |
| `org.gecko.weather.dwd.stations` | Station list fetch, Lucene index, search, Gogo commands | ~400 | Cleanest bundle in the workspace |
| `org.gecko.weather.dwd.forecast` | MOSMIX/DMO fetchers, report index, search, storage | ~700 | Carries most of the findings |
| `org.gecko.weather.dwd.config` | Resource-only bundle with Configurator JSON | — | Correct pattern |
| `org.gecko.weather.rest` | JAX-RS resources for station and forecast, OpenAPI | ~320 | Thin, unversioned — `F-16` |
| `org.gecko.time4j` | `AstrotimeService` implementation via Time4J | ~100 | Good separation, incomplete API — `F-18` |
| `org.gecko.weather.netcdf` | SIS/NetCDF spike | ~180 | Valuable knowledge, not shippable — `F-19` |
| `org.gecko.weather.runtime` | `launch.bndrun` and HTTP/whiteboard configuration | config only | Two conflicting versions — `F-13` |
| `*.test` (2 bundles) | OSGi integration tests for stations and forecast | ~230 | Present but thin |

### Runtime topology

From `org.gecko.weather.runtime/launch.bndrun` and `configs/config.json`:

- Felix 7.0.5, `JavaSE-17`, Felix SCR, Configuration Admin and Configurator.
- Felix Jetty on `localhost:8081`, servlet context path `/weather`, Jersey context path `rest` →
  effective REST base `/weather/rest`.
- EMF (`org.eclipse.emf.*`), Gecko EMF OSGi, Gecko EMF JSON, Gecko EMF REST.
- Lucene 9.8 via `org.gecko.search` / `org.gecko.emf.search`.
- `biz.aQute.scheduler` for cron, `org.apache.felix.eventadmin` for events.
- Swagger/OpenAPI, Gogo shell, Time4J, UCAR NetCDF (via the `Unidata` repository plugin in
  `cnf/build.bnd`).

### Data flow today

**Forecast ingest.** `@CronExpression(cron = {HOURLY, REBOOT})` on
`DWDMOSMIXStationForecastFetcher:77` triggers `run()`, which does
`doDownload()` → `doUnzip()` → `doLoad()` → `doDecode()`. `doDecode` navigates the KML, builds a
`WeatherStation`, expands forecast timesteps into `MOSMIXSWeatherReport` instances, maps each DWD
element via `DWDUtils.setMOSMIXMeasurement`, enriches with astro times, then calls
`reportIndex.indexReports(...)`.

**Storage and index.** `WeatherReportIndexService` writes to both an injected
`WeatherReportStorageHandler` (`cache`) and a Lucene index targeted `(id=dwd.forecast)`, then posts
an EventAdmin event on `ReportIndexHelper.REPORT_TOPIC`.

**Query.** `WeatherReportSearchService` reads single reports from the storage handler and queries
Lucene for everything else. REST resources at `@Path("station")` and `@Path("forecast")` expose it.

## Assets worth keeping

Naming these matters as much as naming the defects, because a greenfield rebuild that discards them
would be strictly worse than the current state.

| Asset | Why it is worth carrying forward |
| --- | --- |
| **EMF/Ecore as single source of truth** | `-generate: geckoEMF` in `org.gecko.weather.model/bnd.bnd` produces the model from `dwd-weather.ecore`. The mechanism is right; only the content needs rework. → [ADR-0002](adr/0002-emf-as-core-model.md) |
| **DWD MOSMIX decoding knowledge** | Navigating KML with the DWD extension schema, mapping element IDs, handling the `W1W2` significant-weather structure. This is hard-won and hard to re-derive. |
| **The SIS/NetCDF spike** | `NetCDFTest` proves DWD SIS global radiation is decodable: `lat`/`lon`/`time` axes, `SIS` in W/m², 0.05° grid, `short` for analysis and `float` for the +18 h forecast file. This is the foundation of site accuracy. |
| **`sensinact.mapping` annotation approach** | Canonical unit plus `sensorthings.unit.name` plus the source's own `dwd.id`, carried declaratively on model features. Exactly the right mechanism — it just sits on the wrong class. → `DEV-5` |
| **`GeoArea(topLeft, xDim, yDim)`** | A raster cell was already modelled. The concept is correct and becomes load-bearing. |
| **Lucene search via Gecko EMF Search** | Spatial and temporal querying of EMF objects, with a working station and report index. |
| **`AstrotimeService` as a separate SPI** | Astro computation behind an interface with a Time4J implementation is good separation; the interface merely needs extending. |
| **Resource-only Configurator bundle** | `org.gecko.weather.dwd.config` is the correct OSGi pattern for shipping default configuration. |
| **EventAdmin notification on index** | `WeatherReportIndexService:188` already posts an event per indexed report — a foundation for `INT-14`. |
| **CI and quality infrastructure** | GitHub Actions, SonarCloud, Jacoco aggregation, licence header check, bnd baselining configured (`dimcBaselining: true`). |

---

## A. Provider coupling

### F-1 — The "generic" API bundle is the DWD bundle

**Statement.** `org.gecko.weather.api` is named and documented as the general API but contains
exclusively DWD-specific code, so there is no home for genuinely shared abstractions.

**Evidence.** `org.gecko.weather.api/src/org/gecko/weather/api/fetcher/DWDFetcher.java`,
`DWDEMFFetcher.java`, `util/DWDUtils.java`. The bundle's own `README.MD` describes it as
"API and base implementation for data fetcher and some Utils".

**Why it matters.** A second source — even a second *DWD product* in a different format — has
nowhere to attach. The choice becomes duplicating the stack or extending the DWD-shaped one, and
both make the next source harder than the last.

**Addressed by** `DEV-1`, `DEV-3`, [ADR-0003](adr/0003-provider-spi.md).

### F-2 — Provider product names and EMF types leak into consumer contracts

**Statement.** The central model class is `MOSMIXSWeatherReport` — a DWD product name — and the
service contracts expose EMF types to consumers.

**Evidence.** `org.gecko.weather.model/model/dwd-weather.ecore`; every signature in
`WeatherReportSearch` takes an `org.eclipse.emf.ecore.EClass` to select the report type, e.g.
`<R extends T> List<R> getReportsByStation(String stationId, EClass type)`. The `sensinact.mapping`
annotations are attached to `MOSMIXSWeatherReport` features, so the semantic mapping is bound to one
provider's product.

**Why it matters.** Consumers encode DWD product semantics in their own code, so adding or switching
a source is a breaking change for them. The `EClass` parameter additionally forces consumers to
depend on EMF internals just to ask a question. And the sensiNact mapping metadata — the most
forward-looking part of the model — cannot be reused across providers where it sits.

**Addressed by** `DEV-1`, `INT-5`, `INT-15`, [ADR-0005](adr/0005-provider-neutral-model.md).

### F-3 — Source element mapping is a 599-line hand-written utility

**Statement.** DWD element IDs are mapped to model features by hand-written conditional code in a
single large class that also owns URL construction, zip handling and EMF navigation.

**Evidence.** `org.gecko.weather.api/src/org/gecko/weather/api/util/DWDUtils.java` — 599 lines.
`createMOSMIXMeasurement` at line 225 and `setMOSMIXMeasurement` at line 433 carry the element
mapping. The class also mixes a static-method API with an instance singleton
(`getInstance()` at line 53, mutable `dwdBaseUrl`), which is awkward inside an OSGi bundle where
configuration should arrive by injection.

**Why it matters.** Every new source product grows this file, and it is the file most likely to be
touched by someone unfamiliar with it. Mapping declared as data can be tested, generated and
reviewed per entry; mapping expressed as control flow cannot.

**Addressed by** `DEV-5`, `DEV-3`.

### F-4 — Decoding is a god-method mixing five concerns

**Statement.** `doDecode` performs KML navigation, station construction, timestep expansion,
measurement mapping and astro enrichment in one method, then triggers indexing — so none of it is
unit-testable in isolation.

**Evidence.** `org.gecko.weather.dwd.forecast/src/org/gecko/weather/dwd/fc/impl/DWDMOSMIXStationForecastFetcher.java:163-251`.
The class simultaneously extends `DWDEMFFetcher` (transport), implements `CronJob` (scheduling) and
holds seven `@Reference` dependencies including `AstrotimeService` and `WeatherReportIndex`.

**Why it matters.** Transport, decoding, mapping, enrichment and persistence have different reasons
to change and different test strategies. Fused into one component, a decoder change cannot be
verified without a scheduler, a network and an index.

**Addressed by** `DEV-3`, `DEV-4`, `DEV-6`.

---

## B. Domain model gaps

### F-5 — Forecasts only, no observations

**Statement.** The model has no concept of a measured value. `WeatherReport` and its subtypes are
forecasts.

**Evidence.** `dwd-weather.ecore` classifiers: `WeatherReport`, `MOSMIXSWeatherReport`,
`MeasurementWeatherReport`, `WeatherReports`. Nothing distinguishes forecast from observation, and
no fetcher retrieves observations.

**Why it matters.** "What is it doing right now" cannot be answered, and — more importantly for the
vision — forecast accuracy can never be checked after the fact, because there is nothing to compare
against.

**Addressed by** `INT-17` partially. See the known gap in
[03-requirements.md](03-requirements.md#coverage-check).

### F-17 — A forecast location is a weather station

**Statement.** There is no concept of a site distinct from a station, and no spatial correction from
the station's position to the location of interest.

**Evidence.** `org.gecko.weather.dwd.forecast/src/org/gecko/weather/dwd/fc/MOSMIXStationConfig.java`
defines exactly `stationId`, `name`, `latitude`, `longitude`.
`DWDMOSMIXStationForecastFetcher:102-115` builds a `Station` from that configuration; if the
configured name is `"NONE"` the station is instead taken from the KML placemark
(`doDecode`, lines 174-179). Either way the report's location *is* the station's location.

**Why it matters.** This is the central defect relative to the vision. Cloud cover — the quantity
that decides PV yield — varies on kilometre scales, and MOSMIX stations are sparse. A configured
`latitude`/`longitude` that differs from the station's is silently ignored rather than used to
correct anything.

**Addressed by** `INT-1`, `INT-3`, [ADR-0009](adr/0009-site-as-central-entity.md).

### F-18 — No sun position, only sunrise and sunset

**Statement.** `AstrotimeService` exposes daily event times but not the sun's position over the day.

**Evidence.** `org.gecko.weather.api/src/org/gecko/weather/api/AstrotimeService.java` — the complete
interface is `getSunTimes(GeoPosition, LocalDate)`, `getSunset(...)`, `getSunrise(...)`. The
`Astrotime` model class carries `sunrise`, `sunset`, `sunriseTwilight`, `sunsetTwilight`.

**Why it matters.** PV output depends on solar elevation and azimuth per timestep. Sunrise and sunset
alone cannot express it. Time4J can compute position, so this is a missing API rather than a missing
capability.

**Addressed by** `INT-4`.

### F-19 — Gridded data exists only as a spike, and materialises the whole grid

**Statement.** The raster concepts in the model are unused in production; the only grid code is a
spike that reads a local file and expands the entire German grid into individual objects.

**Evidence.** `GeoArea` and `UVRadiationMeasurement` appear nowhere outside the generated model
except in `org.gecko.weather.netcdf/src/org/gecko/weather/netcdf/NetCDFTest.java`. That class is an
active `@Component` (line 45) that does its work in `@Activate`; it has a hardcoded filename
(`FILE_NAME = "SISin202409040800DEv3.nc"`, line 48), a hardcoded reference date
(`sdf.parse("2024090608")`, line 169), reads from a relative path `data/`, and iterates the full
`time × lat × lon` array creating one `UVRadiationMeasurement` per cell per timestep
(lines 86-118).

**Why it matters.** The knowledge is exactly right and the product is exactly the one the vision
needs — but the access pattern is the opposite of what is required. At 0.05° over Germany this is on
the order of tens of thousands of cells per timestep, roughly half a million objects per run, to
serve a site that needs one cell. This is the concrete argument for
[ADR-0010](adr/0010-subset-on-ingest.md).

**Addressed by** `INT-3`, `OPS-9`, `DEV-7`.

### F-20 — Values carry no provenance or quality, and one quantity is misnamed

**Statement.** Measurements are bare numbers with no origin and no uncertainty, and the spike stores
global radiation in a class named for UV radiation.

**Evidence.** `MOSMIXSWeatherReport` features are plain `EFloatObject` attributes
(`windDirection`, `cloudCoverTotal`, `irRadianceGlobal`, …) with documentation and unit annotations
but no source, run, issue-time or quality information. `Measurement` has `unit`, `name`,
`timestamp`, `rawValue` — no provenance. `NetCDFTest:104` stores the `SIS` value, shortwave *global*
radiation, into `weatherFactory.createUVRadiationMeasurement()`.

**Why it matters.** Provenance cannot be added retroactively: once a value is stored without knowing
which run and which cell produced it, that information is gone. And conflating global radiation with
UV radiation in a type name is precisely how a wrong quantity reaches a consumer who trusted the
model.

**Addressed by** `INT-5`, `INT-6`, `INT-7`, [ADR-0011](adr/0011-lineage-and-uncertainty.md).

---

## C. Persistence and state

### F-6 — Storage is a volatile in-memory map

**Statement.** The only `WeatherReportStorageHandler` implementation keeps reports in a
`ConcurrentHashMap` with no eviction and no persistence.

**Evidence.** `org.gecko.weather.dwd.forecast/src/org/gecko/weather/dwd/fc/cache/SimpleWeatherReportStorage.java:38`
— `private final Map<String, WeatherReport> cache = new ConcurrentHashMap<>();`. Configured by
`org.gecko.weather.dwd.config/configs/config.json` as `"SimpleStorage~dwd-report": {}`.

**Why it matters.** Two separate defects. First, restart loses everything — and because DWD serves
only `LATEST`, the loss is permanent, not a cache miss. Second, there is no eviction, so the map
grows until the heap is exhausted. The abstraction (`WeatherReportStorageHandler`) is correctly
placed and correctly wired; only a durable implementation is missing.

**Addressed by** `OPS-1`, `OPS-8`, [ADR-0004](adr/0004-persistence-index-split.md).

### F-7 — Both Lucene indexes are configured volatile

**Statement.** The station and forecast indexes use an in-memory directory, so they are lost on
restart and cannot be rebuilt from a durable source.

**Evidence.** `org.gecko.weather.dwd.config/configs/config.json` — both `EMFLuceneIndex~dwd-station`
and `EMFLuceneIndex~dwd-forecast` specify `"directory.type": "ByteBuffer"`.

**Why it matters.** Combined with `F-6` there is no durable copy of anything. Station data can be
re-fetched from DWD; forecast data cannot.

**Addressed by** `OPS-1`, `OPS-2`, [ADR-0004](adr/0004-persistence-index-split.md).

### F-8 — Index and repository responsibilities are entangled

**Statement.** The index service also owns writing to storage, so the two cannot evolve or fail
independently and the index is not derivable from the store.

**Evidence.** `WeatherReportIndexService.java:67-68` holds both a `WeatherReportStorageHandler` and
the Lucene `LuceneIndexService`; lines 158, 161 and 165 perform `cache.saveReport`,
`cache.updateReport` and `cache.deleteReport` inside index operations.

**Why it matters.** A rebuildable index requires a one-way dependency: repository is the truth, index
is derived. Here indexing is the entry point and storage is a side effect, which is the inverse.

**Addressed by** `OPS-2`, [ADR-0004](adr/0004-persistence-index-split.md).

---

## D. Ingest operations

### F-9 — Full download every hour, with no change detection

**Statement.** Each scheduled run downloads the complete KMZ for every configured station
regardless of whether the upstream data changed.

**Evidence.** `DWDMOSMIXStationForecastFetcher:77-78` —
`@CronExpression(cron = {CRON_EXPRESSION_HOURLY, CRON_EXPRESSION_REBOOT})`. `DWDFetcher.doDownload()`
opens `url.openStream()` directly with no `If-Modified-Since` or `ETag` handling. MOSMIX_L per
station is published roughly every four hours, so most hourly fetches retrieve unchanged data.

**Why it matters.** Cost scales linearly with configured stations for no benefit, and unconditional
polling of shared open-data infrastructure risks being throttled or blocked.

**Addressed by** `OPS-6`, `OPS-7`.

### F-10 — No retry, isolation, health or metrics

**Statement.** A failed ingest produces a log line and nothing else. There is no retry, no backoff,
no health signal and no metric.

**Evidence.** `DWDMOSMIXStationForecastFetcher.run()` declares `throws Exception` and lets failures
propagate to the scheduler; nothing records the failure, the last success, or the age of the data.
No health or readiness endpoint exists in `org.gecko.weather.rest`.

**Why it matters.** Operations cannot answer "is the data current?" without reading logs, and a
transient network failure silently costs a forecast window that cannot be re-fetched.

**Addressed by** `OPS-3`, `OPS-4`, `OPS-7`, `OPS-11`.

### F-11 — Failures are swallowed, and log statements are parameter-incorrect

**Statement.** Per-element decode failures are caught and logged individually, and several log calls
have mismatched or misordered parameters — so the diagnostic output is misleading even when someone
reads it.

**Evidence.** `DWDMOSMIXStationForecastFetcher:230-236` catches `Exception` per element and continues,
so a systematically broken mapping yields a partially populated report and no overall failure. The
log call in that handler passes six arguments to a five-placeholder message with the counter `cnt`
where the element name belongs and the element name where the index belongs. Separately,
`DWDFetcher.java:67` uses `{0}` twice in one message and passes three arguments:
`"[{0}] Current relative URI {0}, given URI is: {1}"`.

**Why it matters.** Silent partial success is the worst failure mode for a data service — the data
looks present and is wrong. Misleading log messages then cost time precisely when someone is
debugging under pressure.

**Addressed by** `DEV-15`, `OPS-12`, `OPS-3`.

---

## E. Code and build hygiene

### F-12 — Dead and experimental code in production source folders

**Statement.** Three non-production artefacts sit in `src`.

**Evidence.**
`org.gecko.weather.dwd.forecast/src/org/gecko/weather/dwd/fc/impl/TestComponent.java` — 175 lines
with `//@Component` commented out at line 56.
`org.gecko.weather.netcdf/src/org/gecko/weather/netcdf/NetCDFTest.java` — an active `@Component`
named `…Test` in a `src` folder.
`org.gecko.weather.dwd.stations/src/org/gecko/weather/dwd/stations/impl/example/StationSearchComponent.java`
— an `example` package inside `impl`.

**Why it matters.** Each costs a moment of "is this load-bearing?" on every visit, and that cost is
paid disproportionately by someone returning after months — which is this project's normal working
mode.

**Addressed by** `DEV-7`.

### F-13 — Two versions of the same bundle in the runtime

**Statement.** `launch.bndrun` resolves two `jakarta.xml.bind-api` versions simultaneously.

**Evidence.** `org.gecko.weather.runtime/launch.bndrun` contains both
`jakarta.xml.bind-api;version='[4.0.0,4.0.1)'` and `jakarta.xml.bind-api;version='[3.0.1,3.0.2)'`.

**Why it matters.** Latent classloading hazard of the kind that surfaces as an inexplicable
production failure long after the change that caused it.

**Addressed by** `DEV-9`.

### F-14 — Legacy date/time API in new code

**Statement.** `java.util.Date`, `Calendar`, `GregorianCalendar` and `SimpleDateFormat` are used
throughout code written in 2024, including in service contracts.

**Evidence.** `WeatherReportSearch` signatures take `Date` parameters;
`DWDMOSMIXStationForecastFetcher:255` constructs a `SimpleDateFormat` per invocation (not
thread-safe); `DWDUtils` uses `Calendar` for URL date arithmetic; `NetCDFTest:168-177` mixes
`SimpleDateFormat` and `Calendar` for reference-time computation. `cnf/build.bnd` sets
`javac.source: 17` and `gradle.properties` pins bnd 7.0.0.

**Why it matters.** Time zone handling is where weather data goes wrong, and these APIs make implicit
local-time assumptions. Forecast timesteps arrive as `XMLGregorianCalendar` in UTC and are converted
via `LocalDate.ofInstant(..., ZoneId.systemDefault())` at
`DWDMOSMIXStationForecastFetcher:212` — a system-default dependency inside astro computation.

**Addressed by** `DEV-10`, `DEV-11`.

### F-15 — Documentation is scattered, contradictory and contains repeated typos

**Statement.** Five module READMEs plus the root readme, with errors that indicate they are not read
or maintained.

**Evidence.** `org.gecko.weathcer` appears in both `org.gecko.weather.dwd.stations/README.MD` and
`org.gecko.weather.dwd.forecast/README.MD` (four occurrences); the root `readme.md:13` says
`prg.gecko.weather.model`; `WeatherStationResource.java:46` declares
`COMPONENT_NAME = "WeatherStationResouce"`; `MOSMIXStationConfig.java` javadoc reads
"COnfiguration fir the MOSMIX Station Fetcher". The root readme documents REST paths under
`/weather/rest/...` which are correct, but describes `forecast/start/{id}` behaviour that is only
meaningful with a manually created configuration.

**Why it matters.** Individually trivial. Collectively they signal that documentation is not trusted,
and untrusted documentation stops being read — which matters most for the newcomer scenario in
`OPS`/`V-14`.

**Addressed by** `DEV-12`.

---

## F. API contract

### F-16 — Unversioned API with no machine-readable errors

**Statement.** REST paths carry no version, and errors are bare status codes without a structured
body.

**Evidence.** `WeatherStationResource.java:45` `@Path("station")` and
`WeatherForecastResource.java:59` `@Path("forecast")`, mounted at `/weather/rest` via
`org.gecko.weather.runtime/configs/config.json`. Resource methods return `Response` with status
codes and no problem-detail body. A `hello` debug endpoint is exposed on both resources
(`WeatherStationResource:51`, `WeatherForecastResource:96`).

**Why it matters.** Without a version segment, every change breaks every consumer simultaneously and
there is no way to run old and new contracts side by side. Without structured errors, an integrator
cannot distinguish "unknown station" from "provider temporarily unavailable" — and they do not have
access to the logs that would tell them.

**Addressed by** `INT-9`, `INT-10`, `INT-11`, [ADR-0008](adr/0008-rest-api-design.md).

---

## Summary

| # | Finding | Severity relative to the vision |
| --- | --- | --- |
| `F-17` | A forecast location is a weather station | **Blocking** — the vision's core claim is false without this |
| `F-6` | Storage is a volatile in-memory map | **Blocking** — perishable data is permanently lost on restart |
| `F-7` | Both Lucene indexes are volatile | **Blocking** — no durable copy of anything exists |
| `F-20` | No provenance or quality; SIS stored as UV | **Blocking** — cannot be retrofitted once data is stored |
| `F-19` | Gridded data only in a spike, whole grid materialised | **Blocking** — site accuracy depends on it |
| `F-1` | The "generic" API bundle is the DWD bundle | High — no home for shared abstractions |
| `F-2` | Provider and EMF types leak into consumer contracts | High — every source change breaks consumers |
| `F-18` | No sun position, only sunrise and sunset | High — PV needs elevation and azimuth |
| `F-4` | Decoding god-method mixing five concerns | High — nothing is testable in isolation |
| `F-10` | No retry, isolation, health or metrics | High — not operable |
| `F-11` | Failures swallowed; log parameters incorrect | High — silent partial success is the worst failure mode |
| `F-3` | 599-line hand-written element mapping | Medium — grows with every product |
| `F-8` | Index and repository entangled | Medium — prevents a rebuildable index |
| `F-9` | Full download every hour, no change detection | Medium — wasteful and impolite to the source |
| `F-16` | Unversioned API, no machine-readable errors | Medium — painful but fixable at any time |
| `F-5` | Forecasts only, no observations | Medium — blocks accuracy verification, not the PV case |
| `F-13` | Two versions of the same bundle | Medium — latent, unpredictable |
| `F-14` | Legacy date/time API | Low–Medium — time zones are where weather data goes wrong |
| `F-12` | Dead and experimental code in `src` | Low — pure friction |
| `F-15` | Scattered documentation with typos | Low — but erodes trust in all documentation |

**The pattern.** Findings cluster into "wrong question" (`F-17`, `F-19`, `F-18`, `F-5`, `F-20` — the
service answers about stations, not sites, and does not know where its numbers came from) and "no
operational qualities" (`F-6`, `F-7`, `F-10`, `F-11`, `F-9` — it cannot keep data or report on
itself). The remainder is ordinary prototype debt.

Neither cluster is fixable incrementally in place: the first requires a different domain model and a
different ingest shape, the second requires the persistence and index layers to be inverted. That
combination is what justifies [ADR-0001](adr/0001-greenfield-new-repository.md).
