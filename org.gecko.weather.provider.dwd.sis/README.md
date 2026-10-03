# org.gecko.weather.provider.dwd.sis

DWD SIS — satellite-derived global radiation over Germany at 0.05° — as two `WeatherProvider`s in one
bundle: the **analysis** (`SIS`), a 15-minute stream of what the satellite saw, and the **forecast**
(`SISfc`), hourly means to +18 h, one run per hour. NetCDF-3 over HTTP, netCDF-Java to read it
([org.gecko.ucar.netcdf](../org.gecko.ucar.netcdf/README.md)), only the bound cells are read.

| Piece | Does |
| --- | --- |
| `SisGrid` | the DE grid as measured from the files: 221 × 221 at 0.05° from 46° N / 5° E to 57° N / 16° E, as a `RegularLatLonGrid` (`gridId` `sis-de-v3`); `matches(lats, lons)` checks a file's axes |
| `SisNetcdf` | one file from memory → the series at the wanted cells: `NetcdfFiles.openInMemory`, axes verified, `SIS(time, lat, lon)` read as one small slice per cell (never the whole field — the old implementation's half a million objects per run, F-19), fill → NaN; own udunits parser because the files write `hours since 2026-10-3 00:00:00` |
| `SisListing` | the folder's HTML listing → which analyses (`SISin<yyyyMMddHHmm>DEv3.nc`) and runs (`SISfc<yyyyMMddHH>_fc%2B18h-DE.nc`) exist right now |
| `SisDatasets` | one `SourceDataset` per site and cell; `GLOBAL_RADIATION`, `SURFACE`, `W/m2`, provenance with the cell and `SIS` as source element; `ANALYSIS` quality for the analysis, `FORECAST` with lead time for the forecast |
| `SisAnalysisProvider` | `delivery() == STREAM`: reads the listing, fetches the analyses not yet in the `SourceState`, returns their values as partial datasets the runtime **appends**; a site without data gets the last `backfillSteps` again, others are not given those twice; the state follows the folder's rolling window |
| `SisForecastProvider` | `ISSUE`: the newest run in the listing; known in the state → `Unchanged` after one listing request; otherwise fetched and the datasets **replace** the previous run |
| `SisAnalysisComponent`, `SisForecastComponent` | the DS services, configuration required |

## What the files turned out to be

Measured 2026-10-03, recorded in `test/fixtures` (the 12:00 UTC analysis, 100 KB, and the 18 UTC
forecast run, 3.5 MB bzip2 to 1.3 MB):

- Classic **NetCDF-3** (`CDF\x01`), CF-1.6, written by CDO — pure-Java reading, no HDF5, no native code.
- Axes `lat(221)`, `lon(221)` as floats (`5.050003`), ascending; variable `SIS` in `Watt m-2`,
  `_FillValue −1`: **short** in the analysis, **float** in the forecast.
- Analysis: one instant per file, `time` in `seconds since <day> 00:00:00`; a file every 15 minutes,
  published ~18 minutes after its valid time; the folder keeps about 40 (ten hours).
- Forecast: 18 steps, `time` in `hours since <day> 00:00:00` (day unpadded), the run hour first; its
  CDO history says how it is made: the first hour is the mean of the last four analyses, the rest is
  **ICON-D2 radiation** regridded — so beyond +1 h this is a smoothed, coarser cousin of the ICON-D2
  dataset. Per ADR-0013 both are kept as they are; the consumer sees the relationship in the
  provenance. ~10 minutes after the hour.
- The other families in the folder — `EAv4`/`EUv4` (Europe, 17–53 MB), `FDv3` (full disk), `CALhr`
  (cloud albedo) — are not read.
- Dresden cell (175, 101), centre 51.05° N 13.75° E, 0.9 km from the site: **539 W/m² at 12:00 UTC**;
  the 18 UTC run forecasts 12.6 W/m² for 06 UTC and 313 W/m² for 11 UTC the next morning.

## Configuration

Two PIDs, both **required**; an empty configuration gives the DWD defaults.

| PID | Key | Default | Meaning |
| --- | --- | --- | --- |
| `org.gecko.weather.provider.dwd.sis.analysis` | `baseUrl` | `https://opendata.dwd.de/weather/satellite/radiation/sis/` | the folder |
| | `backfillSteps` | `8` | analyses a site without data gets on first sight (two hours) |
| `org.gecko.weather.provider.dwd.sis.forecast` | `baseUrl` | same | |
| both | `licence`, `attribution` | `GeoNutzV`, `Datenbasis: Deutscher Wetterdienst` | carried with every value |

Volume: a poll costs one listing (~20 KB) and whatever is new — 100 KB per quarter hour, 3.5 MB per
hourly run. The analysis stream lives in the report as a rolling window (`streamWindow` of the core,
default 7 days = 672 values per cell); what falls out is archived.

## Tests

Offline (DEV-6). The recorded files through netCDF-Java against values an independent hand-written
NetCDF-3 reader produced for the same cells (QR-6: 539 / 286 / 274 W/m² at three cells of the
analysis, 12.563965 and 313.156494 W/m² at two steps of the forecast); grid, udunits parser and
listing parser; `SisTestFiles` writes SIS-shaped NetCDF-3 files through netCDF-Java and the provider
tests run on them with a fixture listing: new files only, `Unchanged` after one listing request,
backfill for a new site without duplicates for the old one, the state following the folder, vanished
and masked cells, newest run, run replaced, file not matching its name.
