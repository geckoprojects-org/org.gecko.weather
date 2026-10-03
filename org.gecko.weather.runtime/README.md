# org.gecko.weather.runtime

Deployment glue, no domain logic.

- **`configs/weather.json`** — Configurator defaults for a local run: repository `root`
  `data/weather`, core and ingest defaults, the MOSMIX_L provider with an empty (= default)
  configuration. Production replaces these.
- The Gogo commands live in `org.gecko.weather.shell`; `launch.bndrun` includes them, the smoke run
  does not (a bundle with `@GogoCommand` requires Gogo at resolve time).
- **`SmokeRun`** — the end-to-end proof; armed by `-Dweather.smoke=true`, otherwise inert.

## Launches

| bndrun | What |
| --- | --- |
| `launch.bndrun` | Felix, Gogo shell + `weather` commands, Configurator, Fennec EMF runtime, all weather bundles, MOSMIX provider |
| `smoke.bndrun` | the same without an interactive shell, `SmokeRun` armed; exits 0 when a report with temperatures, sun elevations and day events exists for a freshly registered site |

```
./gradlew :org.gecko.weather.runtime:resolve :org.gecko.weather.runtime:resolve.smoke
./gradlew :org.gecko.weather.runtime:export.smoke
java -jar org.gecko.weather.runtime/generated/distributions/executable/smoke.jar
```

The smoke run needs the network and writes under `./data/weather` of the working directory (ignored
by git). Measured 2026-10-03: the run takes ~15 s, three MOSMIX_L stations give a 14 MB report XMI;
`state/dwd/MOSMIX_L.xmi` holds the ETags for the next conditional poll.

Felix prints `Error ungetting service` for the prototype `ResourceSet` of two bundles on shutdown —
stop order, harmless, to be raised with Fennec EMF.
