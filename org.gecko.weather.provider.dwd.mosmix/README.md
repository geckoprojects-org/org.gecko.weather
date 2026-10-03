# org.gecko.weather.provider.dwd.mosmix

DWD MOSMIX_L point forecasts as a `WeatherProvider`. The first provider, and the one that proves the
SPI without library risk: KMZ over HTTP, the Fennec KML and DWD point-forecast EMF models to read
it, a mapping table.

| Piece | Does |
| --- | --- |
| `StationCatalogParser` | `mosmix_stationskatalog.cfg`, fixed width, ISO-8859-1. **Coordinates are `DD.MM` degrees and minutes** (`50.59` = 50°59′ = 50.983°), not decimals — the previous implementation read them as decimals, up to 0.4° off. |
| `MosmixBindingResolver` | nearest stations by haversine, within 150 km, ranked; `bind` by station id |
| `MosmixKmlDecoder` | loads the KML as an EMF resource through `net.opengis.kml.model` and `de.dwd.cdc.forecast.model` (both Fennec common models, pulled in by the `fennecEMFModels` library) and navigates it: product definition (issue time, referenced model run, time steps), then each wanted `Placemark` with its `dwd:Forecast` lists. KML coordinates are `lon,lat,alt` (the old code swapped them). Whole-document loading is fine for a 350 KB station file — and the reason this provider serves MOSMIX_L only; the 40 MB all-stations file (MOSMIX_S) would need a streaming decoder. |
| `MosmixElements` | the vocabulary → canonical kinds, qualifiers, units, conversions: Kelvin → °C, kJ/m²·h → W/m², probabilities with their source thresholds (`FXh25` → `WIND_GUST`, `PROBABILITY`, `PT12H`, `25 [kn_i]`), `ww` as a code. ~60 of the ~115 MOSMIX_L elements; error estimates (`E_*`), `W1W2` and the precipitation-type probabilities (`wwP*`, `wwZ*`, …) are deliberately unmapped for now. |
| `MosmixDatasets` | one `SourceDataset` per site and station, every value with provenance (`STATION`, station id, distance, source element, issue time, model run, licence) and a `FORECAST` uncertainty (lead time, distance) |
| `MosmixProvider` | `fetch`: distinct bound stations → one KMZ each, conditional via `SourceState`; unchanged files are neither decoded nor reported |
| `MosmixProviderComponent` | the DS service; the prototype `ResourceSet` targeted at the KML model (`emf.name=kml`) for decoding; catalogue from the repository when younger than `catalogMaxAge`, else fetched and stored |

## Configuration

PID `org.gecko.weather.provider.dwd.mosmix`, **required** — a provider fetches from a third-party
server on a schedule; which products a deployment ingests is the operator's decision, made in
configuration. An empty configuration gives MOSMIX_L from DWD Open Data.

| Key | Default | Meaning |
| --- | --- | --- |
| `baseUrl` | `https://opendata.dwd.de/weather/local_forecasts/mos/` | folder holding `MOSMIX_L/` |
| `catalogUrl` | DWD's `mosmix_stationskatalog.cfg` | station catalogue |
| `catalogMaxAge` | `P30D` | re-fetch the catalogue after this |
| `licence`, `attribution` | `GeoNutzV`, `Datenbasis: Deutscher Wetterdienst` | carried with every value |

## Tests

Offline (DEV-6), against two recorded files in `test/fixtures`: the MOSMIX_L KMZ of station 10554
(Erfurt, issued 2024-09-26 09:00 UTC, 247 time steps, 114 elements) and a station catalogue. Decoder,
mapping (one expected value per conversion, QR-6), catalogue and resolver, and the provider end to
end with a fixture `ByteSource` — two sites bound to one station share one download; a matching
validator yields `Unchanged`.
