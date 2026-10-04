# org.gecko.weather.runtime

Deployment glue, no domain logic.

- **`configs/weather.json`** — Configurator defaults for a local run: repository `root`
  `data/weather`, core and ingest defaults, the MOSMIX_L, ICON-D2, both SIS and the UV index providers
  with an empty (= default) configuration — ICON-D2 at its defaults fetches ~500 MB per run, four runs a
  day; SIS is 100 KB per quarter hour plus 3.5 MB per hourly run. Production replaces these.
- The Gogo commands live in `org.gecko.weather.shell`; `launch.bndrun` includes them, the smoke run
  does not (a bundle with `@GogoCommand` requires Gogo at resolve time).
- **`SmokeRun`** — the end-to-end proof; armed by `-Dweather.smoke=true`, otherwise inert.

## Launches

| bndrun | What |
| --- | --- |
| `launch.bndrun` | Felix, Gogo shell + `weather` commands, Configurator, Fennec EMF runtime, all weather bundles, the MOSMIX_L, ICON-D2, SIS and UV providers (the gridded ones bring netCDF-Java, Guava, protobuf, joda-time, commons-compress + commons-io) |
| `smoke.bndrun` | the same without an interactive shell, `SmokeRun` armed; exits 0 when a report with temperatures, sun elevations and day events exists for a freshly registered site |
| `dev.bndrun` | the launch plus the Fennec Gogo MCP server on `127.0.0.1:8088/mcp/gogo` (configured by `org.gecko.weather.runtime.dev`, never in production), non-interactive shell so it can run in the background; commands go through MCP — `.mcp.json` at the workspace root points a Claude Code session at it |

```
./gradlew :org.gecko.weather.runtime:resolve :org.gecko.weather.runtime:resolve.smoke :org.gecko.weather.runtime:resolve.dev
./gradlew :org.gecko.weather.runtime:export.smoke        # separate invocation from resolve, Gradle insists
java -jar org.gecko.weather.runtime/generated/distributions/executable/smoke.jar

./gradlew :org.gecko.weather.runtime:export.dev
setsid nohup java -jar org.gecko.weather.runtime/generated/distributions/executable/dev.jar > data/dev.log 2>&1 < /dev/null &
```

The smoke run needs the network and writes under `./data/weather` of the working directory (ignored
by git). Measured 2026-10-03: the run takes ~15 s, three MOSMIX_L stations give a 14 MB report XMI;
`state/dwd/MOSMIX_L.xmi` holds the ETags for the next conditional poll.

## Long run, night of 2026-10-03/04

`dev.bndrun` against the real DWD for eleven hours (18:50–05:57 UTC), one site (Dresden) bound to
MOSMIX_L (three stations), ICON-D2, SIS, SISfc and — from 22:00 — the UV index; polls every 20 min,
an hourly snapshot of status, report, JVM and log.

| | |
| --- | --- |
| Failures, warnings, exceptions | **0** over the whole night |
| Updates seen | MOSMIX_L 21 and 03 UTC issues, ICON-D2 18 and 00 UTC runs, SISfc every hour, SIS every quarter hour, UV file of 4 October |
| Latency after publication | first poll after it: MOSMIX at :20 past, UV 15 min after the file appeared |
| ICON-D2 while a run is published | one 404 per poll on the probe file, fallback to the known run, no download — then one 465–515 MB fetch |
| Report on disk | 266–269 KB (gzip XMI), stable |
| Archive | 11 → 27 files, 14.5 MB, one per superseded issue |
| Restart at 22:00 (UV deployment) | all six datasets identical afterwards — exit criterion 3 |

**Memory.** RSS sat at 1.0–1.25 GB, which looked like a leak and is not one: `jcmd GC.heap_info`
showed a 832 MB committed G1 heap with 290 MB in use, `GC.class_histogram` no retained
`MeasuredValue` (only the 5 649 stations of the MOSMIX catalogue), and the full GC the histogram
triggers brought RSS down to 505 MB. Without a cap the JVM takes a quarter of the machine's memory
and grows into it lazily. `launch.bndrun` therefore sets **`-Xmx768m`** (`-runvm`, inherited by
`dev` and `smoke`): an ICON field is 3.6 MB of floats, a report load ~26 000 values.

**Threads** went from 62 to 76: Reactor's `parallel-N` pool of the Fennec Gogo MCP server growing up
to the core count while answering the hourly checks, plus Jetty's recycled `qtp` threads — nothing of
the weather bundles.

Felix prints `Error ungetting service` for the prototype `ResourceSet` of two bundles on shutdown —
stop order, harmless, to be raised with Fennec EMF.
