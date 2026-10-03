# org.gecko.weather.core

The service implementations over the repository and the solar service. One DS component,
`WeatherCoreComponent`, registered as `SiteRegistry`, `WeatherService` and `WeatherDataSink`; the
logic lives in plain classes that the tests drive against the real file repository and the real
solar service in a temp folder.

| Class | Does |
| --- | --- |
| `SiteRegistryImpl` | registration with an optional own id, bindings from every present `SiteBindingResolver` (from `WeatherProvider`s and standalone), up to `maxStationBindings` stations per product ranked by distance, manual assignment as rank 0, rebind keeps manual |
| `WeatherServiceImpl` | report, values of a kind, `ValueQuery`, timeline, archive — all reads through `Reports`, nothing merged |
| `ReportAssembler` | the `WeatherDataSink`: `replace` swaps a product's dataset and archives the previous issue; `append` grows a streaming product's dataset within `streamWindow`; after every change the solar part is recomputed over the union of all horizons |
| `SolarDatasets` | the `gecko/solar` dataset (`SUN_ELEVATION`, `SUN_AZIMUTH` per `solarStep`) and `DayInfo` per civil date in the site's zone |

## Configuration

PID `org.gecko.weather.core`, optional — every value has a workable default.

| Key | Default | Meaning |
| --- | --- | --- |
| `defaultTimeZone` | `Europe/Berlin` | Zone for sites registered without one. A coordinate→zone lookup would need a boundary dataset; not in the MVP. |
| `maxStationBindings` | `3` | Stations per point product, nearest first. |
| `solarStep` | `PT1H` | Spacing of computed sun positions. |
| `streamWindow` | `P7D` | How much of an own station's past the report keeps. |

## Two deliberate limits

- **Streams are not archived.** Observations that fall out of `streamWindow` are dropped. A file per
  minute would be noise, and the forecast history `INT-17` asks for lives in the replaced issues.
  Open: bucketed observation archive, if accuracy analysis against own measurements is wanted.
- **The solar part is recomputed on every change** rather than cached. With hourly steps over a
  ten-day MOSMIX horizon that is ~480 positions per write; cheap enough until measured otherwise.
