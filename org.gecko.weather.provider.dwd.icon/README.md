# org.gecko.weather.provider.dwd.icon

DWD ICON-D2 gridded forecasts as a `WeatherProvider`: cloud cover by layer and direct/diffuse
surface radiation at 2.2 km for 0–48 h — the gridded half of the MVP, exit criterion 1 of
[docs/08-mvp.md](../docs/08-mvp.md). GRIB2 over HTTP (bzip2), netCDF-Java to read it
([org.gecko.ucar.netcdf](../org.gecko.ucar.netcdf/README.md)), index arithmetic for the cell.

| Piece | Does |
| --- | --- |
| `IconD2Grid` | the regular lat/lon grid as measured from the files: template 0, 1215 × 746 at 0.02°, first point 43.18° N / 3.94° W, west→east, south→north. `cellFor(lat, lon)` is `round((lon − Lo1)/0.02)`, `round((lat − La1)/0.02)`; `gridId` is `icon-d2-regular-lat-lon` |
| `Grib2Reader`, `Grib2Field` | one file → its records through `Grib2RecordScanner` / `Grib2Record.readData` from memory (`InMemoryRandomAccessFile`); identification, grid, product definition (template 0 or 8 with interval and statistical process), levels, data with NaN where the bitmap masks |
| `IconParameters` | `clct clcl clcm clch` → `CLOUD_COVER` at `CLOUD_TOTAL/LOW/MID/HIGH`, `%`; `aswdir_s aswdifd_s` → `DIRECT_RADIATION` / `DIFFUSE_RADIATION`, `SURFACE`, `MEAN` over `PT1H`, `W/m2`. Each carries the **signature** its file must have (parameter, template, levels) and `check` fails loudly on a mismatch. `hourly` is the de-averaging |
| `IconBindingResolver` | one `GridBinding` per site: the containing cell, distance to its centre; `bind(site, "i,j")` for a manual assignment |
| `IconDatasets` | one `SourceDataset` per site and cell, every value with provenance (`GRID_CELL`, the cell, distance, DWD parameter name, model run as issue time) and a `FORECAST` uncertainty (lead time, distance) |
| `IconProvider` | picks the newest **complete** run among the configured run hours, fetches every file of it once, takes the values at all bound cells from each field, de-averages the radiation, remembers the run's URIs in the `SourceState` |
| `IconProviderComponent` | the DS service, configuration required |

## What the files turned out to be

Measured 2026-10-03 against run `2026100300`, recorded in `test/fixtures` where small enough:

- **Radiation files hold four records per step**: the average since model start ending at :00,
  :15, :30 and :45 of the step hour. The provider reads the full-hour record (`validAt == run + step`).
  The quarter-hour records would allow 15-minute means later.
- **The three cloud layers share parameter 6/22** and differ only by their pressure bounds
  (`clcl` 800 hPa–surface, `clcm` 400–800 hPa, `clch` 0–400 hPa); `clct` is 6/1 at the surface.
  Hence the level check in `IconParameters.Signature`.
- Time unit in the product definition is **minutes** (code 0), forecast time 180 for +3 h.
- Sizes: cloud files 0.65–0.8 MB bzip2 (1.6 MB raw) at any hour; radiation files 3 KB at night
  (all zero) and **4.4–4.6 MB bzip2 (6.5 MB raw) by day**. A full run of the six default parameters
  over 48 h measured **515 MB over the wire, 898 MB decoded, 294 files, ~50 s** — not the 175 MB
  estimated from a night-time file.
- `aswdifd_s` matches `aswdir_s` in template (8), statistical process (0, average) and interval.

De-averaging, as [docs/09-source-inventory.md](../docs/09-source-inventory.md) derived it:
`hourly(t) = (t·M(t) − (t−1)·M(t−1)) / 1 h`. At the Dresden cell (884, 394) the means since 00 UTC were
33.02 W/m² at +10 h and 52.27 W/m² at +11 h, so the direct radiation for 10–11 UTC is 244.8 W/m².

## Fetch strategy

A run is a set of immutable files, published step by step over about forty minutes. The provider
therefore does not do conditional requests per file; it asks "do I have this run?":

1. Candidate runs are the configured hours at or before `now`, newest first, three of them.
2. A run whose URIs are all in the `SourceState` is known — `Unchanged`, no request (unless a
   site has no data yet, then the run is fetched again for it).
3. Otherwise the run is fetched, **last step of every parameter first**: a 404 there means the run
   is still being published, the provider logs it, counts `run-incomplete` and tries the previous
   run. Any other transport error propagates and the ingest backs off.
4. Every file is verified against its name — grid, parameter signature, run, step — and the
   values at the bound cells are taken before the next file is read; one field of 906 390 floats is
   in memory at a time.
5. The new state holds exactly the URIs of the run fetched; older runs are forgotten.

Between runs a poll costs at most the probe of the run being published — one cloud file and one
404. A cell outside the model domain (the bitmap masks ~17 % of the rectangle) yields no dataset
and is counted as `cell-without-data`.

## Configuration

PID `org.gecko.weather.provider.dwd.icon`, **required**. An empty configuration gives the six
parameters, 48 h, four runs a day.

| Key | Default | Meaning |
| --- | --- | --- |
| `baseUrl` | `https://opendata.dwd.de/weather/nwp/icon-d2/grib/` | folder holding `00/` … `21/` |
| `runs` | `00,06,12,18` | UTC run hours to ingest; DWD publishes eight (`00,03,…,21`). The longest gap between runs is the dataset's `expectedRefresh` |
| `horizonHours` | `48` | forecast steps 0..n |
| `parameters` | `clct,clcl,clcm,clch,aswdir_s,aswdifd_s` | which fields |
| `licence`, `attribution` | `GeoNutzV`, `Datenbasis: Deutscher Wetterdienst` | carried with every value |

Volume (`R-11`): `runs × parameters × (horizon + 1)` files; the defaults are ~515 MB per run and
~2 GB a day for a few kilobytes of values per site (292 values per cell and run). Fewer runs, fewer parameters or a shorter
horizon are the only levers — DWD Open Data has no server-side subsetting.

## Tests

Offline (DEV-6). Two recorded files in `test/fixtures`: `clct` +3 h (one record, 807 KB) and
`aswdir_s` +3 h (four records, night, 3 KB). Expected values come from an independent hand-written
GRIB2 section parser (QR-6): 99.10 % total cloud cover at the Dresden cell, 100 % at Hamburg,
2.96 % over the North Sea, masked west of 3° W. `Grib2TestFiles` writes synthetic GRIB2 messages
on the ICON-D2 grid (templates 0/8, 16-bit `grid_simple`, optional bitmap), read back through
netCDF-Java; the provider test runs on such files: fallback to the last complete run, de-averaging
(`5·step` means → `5, 15, 25` W/m²; rounding noise below zero becomes 0), known run → `Unchanged`
without a request, a new site forces the run again, a newer run replaces the state, masked cell,
file not matching its name, no run at all.

**Proven end to end 2026-10-03** in the dev launch against the real DWD: run `2026-10-03T12:00Z`,
294 files, 515 MB transferred and 898 MB decoded in about 50 s, one dataset with 292 values for the
Dresden site's cell (884, 393) 1.1 km from the site, direct radiation 198 W/m² at 13 UTC falling to 0
after sunset, and the report showing three MOSMIX_L station datasets and the ICON-D2 cell dataset side
by side. The smoke run stays MOSMIX-only.
