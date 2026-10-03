# org.gecko.weather.provider.dwd.uv

DWD's daily **UV index** forecast as a `WeatherProvider`: `UVI_MAX_CL`, the day's maximum under the
forecast cloud cover, on the ICON-EU grid (0.0625° ≈ 7 km). One GRIB2 file a day, three daily maxima
(today, tomorrow, the day after), read through [org.gecko.weather.grib2](../org.gecko.weather.grib2)
— the reader the ICON-D2 provider uses — at the bound cells only.

| Piece | Does |
| --- | --- |
| `IconEuGrid` | 1377 × 657 at 0.0625° from 29.5° N / 23.5° W, as a `RegularLatLonGrid` (`gridId` `icon-eu-regular-lat-lon`); binding through the api's `RegularGridBindingResolver` |
| `UvProvider` | reads the folder listing (`climate_environment/health/forecasts/`), picks the newest `…icreu_uvi_icreu…` file by run — its name carries the processing time, which becomes `issuedAt`, the run `modelRun` — verifies every record (grid, parameter 4/51, template 8 with process 2 = maximum, level type 103, run) and replaces the dataset; known in the state → `Unchanged` after one listing request |
| `UvProviderComponent` | the DS service, configuration required (PID `org.gecko.weather.provider.dwd.uv`: `baseUrl`, `licence`, `attribution`) |

## What the file turned out to be

Measured 2026-10-03 (`Z__C_EDZW_20261003042842_grb02,icreu_uvi_icreu__000048_999999_2610030000_HPC.bin`,
5.4 MB, three 1.8 MB records, no bitmap, `grid_simple` 16 bit with a non-zero reference value):

- Grid definition template 0, **1377 × 657 at 0.0625°**, 29.5–70.5° N, 23.5° W–62.5° E, scan 0x40.
- Product definition template 8, parameter **4/51**, statistical process **2 (maximum)**, level type
  103 at 0 m, time unit **days**, forecast time 0, 1, 2. **DWD's quirk:** the interval *end* in the
  record equals its start (range length 0) — midnight at the *beginning* of the day, where GRIB
  would put the end. The README beside the files says `stepRange 0-24` for forecast time 0, so the
  provider takes the record as the maximum of the day starting there and writes `validAt` as that
  day's end, period `P1D`, statistic `MAX` — the convention every aggregate here follows.
- Published once a day around 04:30 UTC, the folder keeps two days. `uvh` (hour of the maximum) and
  the global file (`gmi_uvi_global`, 35 MB) are not read; the perceived-temperature files beside
  them are 130 MB each.
- Dresden cell (596, 345), centre 51.0625° N 13.75° E, 1.5 km from the site: **UV index 2.77 for
  2026-10-03, 1.68 for the 4th, 2.45 for the 5th** — an October sky with clouds.

## Tests

Offline (DEV-6). No recorded file — a single record is 1.2 MB even compressed — so the tests write
three-record files through `Grib2Writer` exactly as DWD does (template 8, maximum, days, level 103)
and read them back: grid and listing parsing (several files for one run → the latest processed),
newest run with full provenance, `Unchanged` on a known run, a newer run replacing it, a file of
another run under the name, an empty listing. The numbers above were cross-checked once by hand with
an independent GRIB2 section parser and through the reader against the real file.
