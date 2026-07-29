# Migration

Greenfield ([ADR-0001](adr/0001-greenfield-new-repository.md)) means a new **branch** — `sunorcloud` of
`geckoprojects-org/org.gecko.weather` — **not** a blank slate. Most of the value in the current workspace
is knowledge, not structure, and knowledge is what gets carried over.

## The one piece of good luck

**There is no data to migrate.** Everything the current implementation holds is volatile — reports in a
`ConcurrentHashMap` (`F-6`), both Lucene indexes on `ByteBuffer` directories (`F-7`). A restart already
discards all of it.

This is a serious defect that happens to remove the hardest part of a migration entirely. There is no
export, no schema conversion, no dual-write period, no backfill of historical records. The new service
starts collecting from the moment it is deployed, and the old one loses nothing that it would not have
lost anyway at its next restart.

The corollary is worth stating: **every day the new service is not collecting is a day of history that
can never be recovered.** That is an argument for reaching a deployable state early — see the
[MVP](08-mvp.md) — even before it is more accurate than the old one.

## Salvage inventory

| Item | From | Treatment |
| --- | --- | --- |
| **`cnf` workspace setup** | ~~old `cnf`~~ | **Superseded.** The `sunorcloud` workspace was built fresh on the Fennec `cnf` template instead: Java 21, bnd 7.4.0-SNAPSHOT, the Fennec library set ([ADR-0002](adr/0002-emf-as-core-model.md), [ADR-0006](adr/0006-java-baseline-toolchain.md)). Two things still to take from the old `cnf`: the **Unidata repository** declaration (`-plugin.3.Unidata` → `artifacts.unidata.ucar.edu`) and the `org.gecko.ucar.*` coordinates. Note the old `cnf/unidata.mvn` the plugin references **does not exist** — the plugin was dead and the UCAR bundles came as Gecko wraps from Maven Central. |
| **CI and quality config** | `.github/`, `Jenkinsfile`, `build.gradle` Sonar/Jacoco setup, `.licenserc.yaml` | **Done.** Jenkinsfile carried over with JDK raised to `OpenJDK21`; GitHub Actions trimmed to the pre-gate (licence, build, test) since Jenkins does the releases. |
| **UCAR wrap bundles** | [org.gecko.libraries](https://github.com/geckoprojects-org/org.gecko.libraries): `org.gecko.ucar.netcdf`, `org.gecko.ucar.units` (5.6.0) | **Reuse and extend.** These are the OSGi wraps for the UCAR library; the SIS spike used them. `org.gecko.ucar.netcdf` contains **no GRIB support**, so ICON-D2 needs `edu.ucar:grib` added to the same wrap — not a second bundle, or `ucar.nc2.*` lands in two bundles at once (`F-13`, `DEV-9`). See [09-source-inventory.md](09-source-inventory.md). |
| **MOSMIX KML decoding knowledge** | `DWDMOSMIXStationForecastFetcher.doDecode`, `DWDUtils` | **Port with rework.** The navigation logic (document → placemark → extended data → forecast elements → timesteps) is correct and hard to re-derive. Restructure into a streaming decoder; do not copy the method. |
| **DWD element mapping table** | `DWDUtils.createMOSMIXMeasurement` (line 225), `setMOSMIXMeasurement` (line 433) | **Convert to data.** Every element ID, unit and target quantity is valuable; the branch structure is not. Extract into declarative annotations on canonical kinds (`DEV-5`). |
| **`sensinact.mapping` annotations** | `dwd-weather.ecore` | **Carry over the content, relocate the mechanism.** Canonical unit, `sensorthings.unit.name`, `dwd.id` — the right idea on the wrong class. Where it lands is [ADR-0005](adr/0005-provider-neutral-model.md)'s open question: Ecore annotations on canonical kinds, or the emf.osgi 1.1 metadata service (`INT-15`). |
| **SIS / NetCDF decoding** | `org.gecko.weather.netcdf/NetCDFTest.java` | **Port with rework, discard the shell.** The variable names (`time`, `lat`, `lon`, `SIS`), the `short` vs `float` distinction between analysis and forecast files, the 0.05° cell size, the time-axis-to-instant arithmetic — all keep. The `@Component`-in-`@Activate` structure, hardcoded filename and hardcoded reference date do not. **Note the file naming has moved on**: the spike used `SISin…DEv3.nc`, the server now also serves `EAv4` (Europe) at 53 MB against 100 KB for DE. Use DE. |
| **DWD URL construction** | `DWDUtils` constants and `buildMOSMIXSingleForecastUrl`, `buildDMOForecastFile` | **Port with rework.** Correct knowledge of DWD Open Data paths and filename patterns; move into the relevant provider bundle as configuration plus a small builder. |
| **Station list sources and parsing** | `DWDStationListFetcher`, `StationIndexHelper` | **Port.** Two catalogue URLs (CLIMAT text list and the MOSMIX station catalogue) and their parsing. |
| **Lucene index and spatial search** | `StationIndexService`, `WeatherReportIndexService`, `ReportIndexHelper` | **Port with inversion — but not yet.** Field layout and spatial query approach keep; the dependency direction inverts so the index is derived from the repository (`F-8`). Out of MVP scope, and the integration is now ours to write since `org.gecko.emf.search` is on the Gecko side of the fork ([ADR-0004](adr/0004-persistence-index-split.md)). The Lucene wraps in `org.gecko.libraries` are what to build against. |
| **`AstrotimeService` + Time4J implementation** | `org.gecko.time4j` | **Port and extend.** Day events keep; add elevation and azimuth (`F-18`). |
| **Report identity scheme** | `ReportHelper.createReportId` | **Review, then port.** Identity now has to accommodate provenance and supersession, so re-derive rather than copy. |
| **Gogo commands** | `StationSearchCommand`, `GeoPositionConverter`, `MOSMIXForecastCommand` | **Port selectively.** Useful for operations; rewrite against the new SPI. `GeoPositionConverter` is directly reusable. |
| **OSGi test setup** | `*.test` bundles, `test.bndrun` | **Carry over the pattern**, write new tests. |
| **Docker setup** | `docker/Dockerfile`, `docker-compose`, `build.gradle` | **Carry over, then extend** with the durable volume the persisted XMI needs. |
| **Configurator pattern** | `org.gecko.weather.dwd.config` | **Carry over the pattern**, new content. |
| `TestComponent` | `dwd.forecast/impl` | **Discard.** 175 dead lines. |
| `StationSearchComponent` | `dwd.stations/impl/example` | **Discard.** |
| `MOSMIXSWeatherReport` and the report class hierarchy | `dwd-weather.ecore` | **Discard as structure**, keep as a checklist of quantities to canonicalise. |
| `WeatherReportSearch` / `WeatherReportStorageHandler` interfaces | `dwd.forecast` | **Discard.** Correct instinct, wrong shape — `EClass` parameters and no provenance. |
| Module `README.MD` files | five bundles | **Discard and rewrite.** Content partly wrong, typos throughout (`F-15`). |

**Rule of thumb:** copy *knowledge about DWD*, rewrite *structure*. Anything that encodes what DWD
publishes, where, in what format, with which element identifiers is expensive to re-derive and cheap to
port. Anything that encodes how the old service was organised is what the redesign exists to change.

## Consumer migration

> **Unknown that must be resolved before cutover:** who consumes the current API today. The REST paths
> (`/weather/rest/station`, `/weather/rest/forecast`) are unversioned (`F-16`), so there is no way to
> tell from the code whether anything depends on them. Establish this before cutover; the MVP has no API, so nothing is urgent until one exists.

Once known:

| Consumer type | Migration |
| --- | --- |
| None (likely) | Nothing to do. Retire the old service when the new one is deployed. |
| Internal / DIM projects | Port directly to `/api/v1`. The response shape changes fundamentally — values carry provenance and are grouped per timestep — so this is a rewrite of the consuming code, not a path change. |
| External | Run both services in parallel; the old one is read-only and frozen. Announce a retirement date only after the new one has been stable in production. |

There is **no compatibility layer.** The old contract exposes DWD product semantics
(`MOSMIXSWeatherReport` with DWD element field names) that the new model deliberately does not have.
Emulating it would reintroduce exactly the coupling the redesign removes, and would have to be
maintained forever.

## Branches, not repositories

The naming problem this section used to describe no longer exists. The rebuild is the **`sunorcloud`**
branch of `geckoprojects-org/org.gecko.weather` ([ADR-0001](adr/0001-greenfield-new-repository.md)), so
nothing has to be renamed, freed, redirected or archived. `main` and `snapshot` keep the running service;
the Maven group stays `org.geckoprojects.weather`.

What that leaves to handle instead:

1. **Tag the current state** of the old implementation before it stops being the focus, e.g. `v1-final`
   on `main`. Cheap, and it names the boundary.
2. **Keep the release paths apart.** The Jenkinsfile keys on branch names, and `snapshot` and
   `sunorcloud` would otherwise publish snapshots to the same coordinates. `sunorcloud` currently builds
   without publishing, which is fine until it should publish.
3. **Retire by branch, not by archive.** When the new service is primary, `snapshot` and `main` carry the
   old code as history rather than as a separate repository to archive.

## Cutover

Deliberately unhurried. The old service keeps running, untouched, and is not decommissioned on a
schedule.

| Phase | Old service | New service | Gate to proceed |
| --- | --- | --- | --- |
| Workspace | Running, untouched | `sunorcloud` builds green | CI green on the branch |
| MVP in development | Running, untouched | Fetch → merge → persist for one site | [MVP](08-mvp.md) delivers |
| MVP deployed | Running | **Collecting in parallel.** Site-accurate from the start, because ICON-D2 covers cloud and radiation gridded ([09](09-source-inventory.md)) | Runs stable for a meaningful period |
| Comparison | Running | Both collecting | New service demonstrably better for a real site, and consumers are known |
| Switch | Read-only, frozen | Primary | Consumers migrated or confirmed absent — needs the API question reopened ([ADR-0008](adr/0008-rest-api-design.md) is deferred) |
| Retire | Stopped, kept as branch history | Sole service | — |

One thing changed for the better here. The old plan had the new service deliberately *less* accurate than
the old one for its first deployed phase — Slice 1 was station-only by design. That is no longer true: the
MVP is site-accurate for cloud and radiation from its first deployment, because `Q-I` turned out
favourably. The "deploy something worse first" step is gone.

Both services may run against DWD simultaneously during the parallel phase. Two consumers of the same
open data with conditional requests and jittered schedules is not a fair-use concern, but it is a
reason not to extend the parallel phase indefinitely (`OPS-11` politeness, `S-4`).

## Checklist

Foundation:

- [x] Coordinates settled — branch `sunorcloud`, group `org.geckoprojects.weather`, no rename needed
- [x] Workspace on the Fennec `cnf`: Java 21, bnd 7.4.0-SNAPSHOT, Fennec library set
- [x] CI split — Jenkins releases, GitHub Actions as licence/build/test pre-gate
- [x] `docs/` is the canonical documentation home
- [ ] Git initialised on `sunorcloud`, workspace committed
- [ ] Unidata repository declaration and `org.gecko.ucar.*` coordinates carried into the new `cnf`
- [ ] `v1-final` tag on `main`

Knowledge salvage — before the corresponding increment:

- [ ] `edu.ucar:grib` added to the `org.gecko.ucar.netcdf` wrap — requested as
      [org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3)
- [ ] DWD element mapping extracted from `DWDUtils` into declarative form
- [ ] `sensinact.mapping` content relocated onto canonical kinds, mechanism per [ADR-0005](adr/0005-provider-neutral-model.md)
- [ ] MOSMIX KML navigation logic understood and restructured, not copied
- [ ] Station catalogue URLs and parsing ported
- [ ] SIS variable layout, dtype distinction and time arithmetic ported
- [ ] ICON-D2 de-averaging for `aswdir_s`/`aswdifd_s` implemented and tested against a known value —
      product template 8 means these are averages, and reading them raw is silently wrong
- [ ] Fixtures committed: a real ICON-D2 `.grib2`, a real SIS `.nc`, a real MOSMIX `.kmz`

Before cutover:

- [ ] Current API consumers identified — the open unknown above
- [ ] New service deployed and collecting in parallel
- [ ] `V-1` demonstrated for a real site
- [ ] An API exists for consumers to migrate to — [ADR-0008](adr/0008-rest-api-design.md) is deferred, so
      this has to be reopened before a switch is possible
- [ ] Consumers migrated or confirmed absent
- [ ] Retirement communicated

After cutover:

- [ ] Old service stopped
- [ ] `main`/`snapshot` kept as history; nothing to archive
- [ ] `docs/` updated to describe the running system rather than a plan
