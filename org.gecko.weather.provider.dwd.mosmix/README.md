# org.gecko.weather.provider.dwd.mosmix

DWD MOSMIX point forecasts as a `WeatherProvider`. The first provider, and the one that proves the
SPI without library risk: KMZ over HTTP, a StAX parser, a mapping table.

| Piece | Does |
| --- | --- |
| `StationCatalogParser` | `mosmix_stationskatalog.cfg`, fixed width, ISO-8859-1. **Coordinates are `DD.MM` degrees and minutes** (`50.59` = 50°59′ = 50.983°), not decimals — the previous implementation read them as decimals, up to 0.4° off. |
| `MosmixBindingResolver` | nearest stations by haversine, within 150 km, ranked; `bind` by station id |
| `MosmixKmlParser` | streaming: header (issue time, referenced model run, time steps), then each wanted `Placemark`. Constant memory per station, so MOSMIX_S's 40 MB all-stations file works the same way. KML coordinates are `lon,lat,alt` (the old code swapped them). |
| `MosmixElements` | the vocabulary → canonical kinds, qualifiers, units, conversions: Kelvin → °C, kJ/m²·h → W/m², probabilities with their source thresholds (`FXh25` → `WIND_GUST`, `PROBABILITY`, `PT12H`, `25 [kn_i]`), `ww` as a code. ~60 of the ~115 MOSMIX_L elements; error estimates (`E_*`), `W1W2` and the precipitation-type probabilities (`wwP*`, `wwZ*`, …) are deliberately unmapped for now. |
| `MosmixDatasets` | one `SourceDataset` per site and station, every value with provenance (`STATION`, station id, distance, source element, issue time, model run, licence) and a `FORECAST` uncertainty (lead time, distance) |
| `MosmixProvider` | `fetch`: distinct bound stations → one KMZ each (MOSMIX_L) or one for all (MOSMIX_S), conditional via `SourceState`; unchanged files are neither parsed nor reported |
| `MosmixProviderComponent` | the DS service; catalogue from the repository when younger than `catalogMaxAge`, else fetched and stored |

## Configuration

PID `org.gecko.weather.provider.dwd.mosmix`, optional — defaults give MOSMIX_L from DWD Open Data.

| Key | Default | Meaning |
| --- | --- | --- |
| `product` | `MOSMIX_L` | `MOSMIX_L` (per station, 6-hourly, ~115 elements) or `MOSMIX_S` (all stations, hourly, ~40 elements) |
| `baseUrl` | `https://opendata.dwd.de/weather/local_forecasts/mos/` | folder holding `MOSMIX_L/` and `MOSMIX_S/` |
| `catalogUrl` | DWD's `mosmix_stationskatalog.cfg` | station catalogue |
| `catalogMaxAge` | `P30D` | re-fetch the catalogue after this |
| `licence`, `attribution` | `GeoNutzV`, `Datenbasis: Deutscher Wetterdienst` | carried with every value |

## Tests

Offline (DEV-6), against two recorded files in `test/fixtures`: the MOSMIX_L KMZ of station 10554
(Erfurt, issued 2024-09-26 09:00 UTC, 247 time steps, 114 elements) and a station catalogue. Parser,
mapping (one expected value per conversion, QR-6), catalogue and resolver, and the provider end to
end with a fixture `ByteSource` — two sites bound to one station share one download; a matching
validator yields `Unchanged`.
