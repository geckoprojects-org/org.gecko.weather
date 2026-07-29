# Source Inventory — the answer to `Q-I`

Investigated 2026-07-29 against the live DWD Open Data server. This closes `Q-I` and makes `M-7` (the
initial `MeasurementKind` set) decidable.

## Verdict first

Two assumptions in the dossier were wrong, both in our favour, and one consequence is uncomfortable.

- **`R-2` is retired.** A gridded cloud-cover product by layer exists and is better than hoped:
  ICON-D2 publishes `clct`, `clcl`, `clcm`, `clch` at **2.2 km** for **0–48 h**, eight times a day.
- **`R-3` dissolves.** The worry was that SIS's +18 h horizon leaves the 24–48 h window without gridded
  radiation, to be patched with station values from MOSMIX. Unnecessary: ICON-D2 also publishes
  **`aswdir_s` and `aswdifd_s`** — direct and diffuse shortwave radiation at the surface — for the full
  48 h, at finer resolution than SIS. The mixed-horizon handling planned as increment 3.6 is not needed
  for radiation.
- **The uncomfortable part: GRIB2 moves onto the MVP critical path.** ICON-D2 is GRIB2, and risk `R-8`
  (GRIB2 decoding is harder than NetCDF, may need a further library with its own OSGi packaging problems)
  was deliberately deferred to Slice 3.5 so that NetCDF could prove the streaming SPI first. ICON-D2 is
  the primary source, so **ICON goes before NetCDF** — decided 2026-07-29 — and that sequencing is gone.
  Replacement mitigation in [08-mvp.md](08-mvp.md): prove the SPI on a non-gridded source first, so the
  GRIB2 decoder only has to prove GRIB2.

  The immediate open question this creates: **can one library read both GRIB2 and NetCDF?** UCAR's
  netcdf-java has a GRIB IOSP, which would make it one dependency rather than two — worth establishing
  before the decoder is written, because it decides whether ICON-first is cheap or expensive.

There is also a volume problem that `OPS-9` as written does not survive. See below.

## Products

| Product | Quantities | Grid | Cadence | Horizon | Format | Size per file |
| --- | --- | --- | --- | --- | --- | --- |
| **ICON-D2** | `clct`, `clcl`, `clcm`, `clch` (cloud by layer); `aswdir_s`, `aswdifd_s` (direct/diffuse SW at surface); plus `clc`, `clct_mod`, `ceiling`, `cldepth`, `asob_s`, `apab_s` | 2.2 km ≈ 0.02°, rotated lat-lon *and* icosahedral | 8×/day (00,03,06,09,12,15,18,21 UTC) | **0–48 h**, hourly | GRIB2 `.bz2` | ~0.6 MB per parameter per hour |
| **SIS analysis** | global radiation (`SIS`), W/m² | 0.05° ≈ 5 km | every 15 min | now | NetCDF | **DE: ~100 KB** · EA: ~53 MB |
| **SIS forecast** | global radiation, hourly means | 0.05° | hourly | **+18 h** | NetCDF | ~3.5 MB (DE) |
| **UV index** | `uvi` (UV index), `uvh` (hour of daily maximum) | ICON-EU grid, plus a global variant | **1×/day**, ~04:25 UTC | 0–48 h | GRIB2 | 5–138 MB |
| **MOSMIX_L** | ~115 parameters per station incl. global radiation | station points (~5400 worldwide) | every 6 h (03,09,15,21) | **+240 h**, hourly | KMZ/KML | small per station |
| **MOSMIX_S** | fewer parameters, all stations in one file | station points | hourly | +240 h | KMZ/KML | large (all stations) |

Paths: `opendata.dwd.de/weather/nwp/icon-d2/grib/<run>/<param>/`,
`/weather/satellite/radiation/sis/`, `/climate_environment/health/forecasts/`,
`/weather/local_forecasts/mos/MOSMIX_{L,S}/`.

### Notes that change decisions

- **SIS regions.** The spike used `SISin…DEv3.nc`. The live directory also carries `EAv4` (Europe) —
  and it is **500× larger**: 53 MB versus 100 KB per timestep. For a German site `DEv3` is the correct
  choice; `EAv4` would be 5 GB/day for nothing. The product page still documents only DE and FD, so
  `EAv4` is newer than the documentation.
- **UV is the weakest link.** One update per day, on the ICON-EU grid rather than a 2 km grid. For a UV
  index that is defensible — it varies smoothly — but it is not "geolocation-accurate" in the same sense
  as cloud cover at 2.2 km. It is also the only quantity in the MVP whose product is refreshed daily
  rather than several times a day.
- **ICON-D2 gives the direct/diffuse split for free.** That was planned as a *derived* quantity in
  increment 4.1. It is a source quantity instead — better, because a model split beats one computed from
  a global value with a correlation model.
- **MOSMIX keeps two roles**: the station-only baseline the MVP must be honest about, and the only
  source beyond +48 h. If the horizon ever needs to exceed two days, MOSMIX is what extends it, and
  *then* the mixed-horizon handling matters.

## What this settles for the MVP

`M-7` — the initial `MeasurementKind` set — becomes:

| Kind | Source | Grid-accurate? |
| --- | --- | --- |
| `CLOUD_COVER_TOTAL` / `_LOW` / `_MID` / `_HIGH` | ICON-D2 `clct`/`clcl`/`clcm`/`clch` | yes, 2.2 km |
| `RADIATION_DIRECT` | ICON-D2 `aswdir_s` | yes, 2.2 km |
| `RADIATION_DIFFUSE` | ICON-D2 `aswdifd_s` | yes, 2.2 km |
| `GLOBAL_RADIATION` | SIS (now and +18 h); ICON-D2 sum as the longer-horizon path | yes, 5 km / 2.2 km |
| `UV_INDEX` | health forecasts `uvi` | coarser, ICON-EU grid |
| `AIR_TEMPERATURE`, `WIND_SPEED` | MOSMIX_L | **no — station only**, labelled as such |

Solar elevation and azimuth are computed, not sourced, so they carry no spatial error at all.

## The volume problem

`OPS-9` says ingest volume for gridded products must scale with the number of registered sites, not with
the size of the source field, and that server-side subsetting be used where available. **DWD Open Data is
a plain HTTP file server. There is no server-side subsetting.** To read one 2.2 km cell, the whole field
must be downloaded.

With measured sizes, per ICON-D2 run, regular-lat-lon only:

- 6 parameters × 49 forecast hours × ~0.6 MB ≈ **175 MB per run**
- at 8 runs per day ≈ **1.4 GB per day**, to store a few kilobytes per site

SIS by contrast is genuinely cheap: 100 KB per analysis timestep, 3.5 MB per forecast file.

So `OPS-9` has to be split into two statements that were conflated:

- **Storage** scales with sites. True, and subset-on-ingest delivers it.
- **Transfer** scales with the field, and cannot be made to do otherwise on this server.

The levers are all about fetching *less*, not fetching *smaller*: fewer runs per day (4 instead of 8
halves it), hourly resolution only where it matters, and only the parameters actually mapped. This is a
number to decide against deliberately rather than discover in a bandwidth bill.

## Measured from actual ICON-D2 files

Two files were downloaded and their GRIB2 sections parsed directly
(`…_2026072900_003_2d_clct.grib2` and `…_2d_aswdir_s.grib2`, regular-lat-lon, run 00, +3 h). This
replaces three of the open questions with facts.

**Grid — identical in both, and simpler than expected:**

| | |
| --- | --- |
| Grid definition template | **0 — plain latitude/longitude, no rotation** |
| Ni × Nj | 1215 × 746 = 906,390 grid points |
| Extent | lat 43.18 … 58.08, lon 356.06 … 20.34 (i.e. −3.94 … 20.34) |
| Increment | Di = Dj = **0.02°** |
| Scanning mode | `0x40` — west→east, **south→north** |
| Points with data | 754,862 of 906,390 → a **bitmap is present** (Section 6) |
| Packing | Data representation template **0 — `grid_simple`** |

So the cell resolver is index arithmetic on plain axes, the same shape as SIS:
`i = round((lon − Lo1)/0.02)`, `j = round((lat − La1)/0.02)`, flat index `j·Ni + i` — then mapped through
the bitmap, because a quarter of the rectangle carries no data.

**`grid_simple` is the important one.** DWD does use CCSDS/AEC packing operationally for some products,
which would have required libaec through JNA plus a native library — painful anywhere, worse under OSGi.
These files do not use it. **No JNA, no native dependency.**

**The averaging suspicion was correct**, and it matters:

| Parameter | Product template | Meaning |
| --- | --- | --- |
| `clct` | 0 | **instantaneous** — use as-is |
| `aswdir_s` | 8, statistical process 0 | **time-averaged**, per ICON convention since model start |

An hourly value therefore has to be recovered from two consecutive files:
`hourly = (t₂·M₂ − t₁·M₁) / (t₂ − t₁)`. Reading `aswdir_s` at face value gives numbers that look
entirely plausible and are wrong — exactly the failure mode `QR-6` (a test per mapping with a known
expected value) exists to catch. Same expected for `aswdifd_s`.

Also settled: **use the `regular-lat-lon` files, not the icosahedral ones.** Template 0 needs no
coordinate transform and no neighbour lookup.

## GRIB2 library: a UCAR wrap bundle

**Decided 2026-07-29: wrap `edu.ucar:grib`** rather than hand-write a reader for the four templates DWD
uses. The library handles every template, so a DWD switch to CCSDS packing or another grid definition is
a non-event instead of a project.

Tracked upstream as
[org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3).

Three consequences that have to be handled when the bundle is created:

- **The UCAR artifacts are not on Maven Central**, only on
  `https://artifacts.unidata.ucar.edu/repository/unidata-all/`. The old workspace had that repository in
  `cnf/build.bnd`; the new one does not yet, so it has to be added.
- **One wrap, not two.** The existing `org.gecko.ucar.netcdf` 5.6.0 contains cdm-core but **no GRIB** —
  its few `grib` hits are a NetCDF-4 chunking strategy and GEMPAK tables. Since `edu.ucar:grib` depends
  on cdm-core, wrapping it separately would put `ucar.nc2.*` in two bundles at once: duplicate packages,
  which is `F-13` and which `DEV-9` requires to be a build failure. So the wrap covers **cdm-core plus
  grib in one bundle**, extending the existing one rather than sitting beside it.
- **Review the exports while touching it.** The wrap's `Export-Package` is already curated by intent —
  `com.google.re2j.*, thredds.*, ucar.*, uk.*` — which expands to 420 packages, none of them `java.*`.
  That is simply what wrapping cdm-core costs. Two smaller smells are worth fixing in passing:
  `org.jdom2` appears in both `Export-Package` and `Import-Package`, and 22 `java.*` packages are
  imported, which the system bundle has to satisfy.

libaec is not needed for the products in scope (`grid_simple`, measured above), so the JNA and native
library path stays out — but the library supports it if that ever changes, which is the point of taking
the library.

## Still to confirm

- **The exact grid of the UV product**, from its GRIB header rather than from documentation.
- How far back each product is retained on the server, which bounds the best-effort backfill (`OPS-16`).
- Whether `aswdifd_s` matches `aswdir_s` in template and averaging — expected, not verified.
