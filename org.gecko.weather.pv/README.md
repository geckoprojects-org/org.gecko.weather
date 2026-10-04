# org.gecko.weather.pv

The PV add-on (M-15): what a photovoltaic plant is expected to produce, hour by hour for 48 hours
and per day for a week, as the remote service **`PvForecast`** (`plants()`, `forecast(plantId)`),
exported through the Remote Service Admin of Fennec Services like `WeatherOutlook`. A consumer of the
weather service: radiation, temperature and wind come from the report of the plant's weather site,
the sun from the `SolarService`. The weather service knows nothing of PV.

## Plant profile

One `pv:Plant` XMI file per plant in the plants folder (`plantsFolder`, default
`data/weather/plants` — local data, git-ignored: a profile names an address).

| Element | What |
| --- | --- |
| `Plant` | `id`, `name`, `siteId` (the weather site feeding it — register one at the plant first), optional own `latitude`/`longitude`, `mountingHeight` (m, for obstacle angles), `albedo` (0.2), `systemLosses` (% for wiring, soiling, mismatch, ageing; 10) |
| `PvArray` | one orientation: `azimuth` (180 = south), `tilt`, `peakPower` kWp, `moduleCount`, `temperatureCoefficient` (%/K of Pmax, data sheet), `mounting` (`ROOF_MOUNTED`, `ROOF_INTEGRATED`, `OPEN_RACK`), `inverter` |
| `Inverter` | `acPower` kW (output above it is clipped), `efficiency` (0.96) |
| `Obstacle` | a forest, a house: `azimuthFrom`/`azimuthTo` (may cross north), `distance`, `height` — its top appears under atan((height − mountingHeight) / distance); `leafOffTransmittance` (0) is the share of direct sun a deciduous obstacle lets through while leafless, 15 November to 30 April — about 0.3 for a bare oak or beech edge |
| `HorizonPoint` | a measured horizon line, interpolated; the higher of horizon and obstacles wins |
| `Meter` | where the actual output can be read: `type` (selects the `PvMeter` reader, e.g. `fronius-solar-api`), `url` of the device, `interval` in s (60, at least 5), `enabled` |

```xml
<pv:Plant xmlns:xmi="http://www.omg.org/XMI" xmlns:pv="https://geckoprojects.org/weather/pv/1.0" xmi:version="2.0"
    id="garage" name="Garage" siteId="home" mountingHeight="3.0" systemLosses="10.0">
  <arrays name="Süd" azimuth="180.0" tilt="15.0" peakPower="4.3" temperatureCoefficient="-0.27" mounting="OPEN_RACK" inverter="//@inverters.0"/>
  <inverters name="WR" acPower="5.0" efficiency="0.97"/>
  <obstacles name="Wald" azimuthFrom="220.0" azimuthTo="300.0" distance="25.0" height="20.0"/>
</pv:Plant>
```

## The computation, per hour

Radiation values are hourly means, so the mean power of an hour in kW is its energy in kWh.

1. **Weather of the hour** (`WeatherHours`): direct and diffuse radiation of the ICON-D2 cell where
   it covers (48 h); beyond, MOSMIX global radiation of the rank-0 station, split by **Erbs**.
   Air temperature and wind in the middle of the hour from the same station.
2. **Sun** in the middle of the hour (NREL SPA through `SolarService`).
3. **Horizon** (`Horizon`): is the sun behind an obstacle or the horizon line? The hidden share of
   the sky dome reduces the isotropic diffuse part.
4. **Plane irradiance** per array (`PvPhysics.plane`): beam by the angle of incidence, sky diffuse
   by **Hay–Davies** (circumsolar part follows the beam's shading), ground reflection isotropic.
5. **Cell temperature** by **Faiman** with coefficients per mounting, **DC power** linear in
   irradiance with the temperature coefficient and the system losses.
6. **Inverters**: efficiency and AC ceiling (clipping); arrays without one get 96 % and no ceiling.

Days sum the hours of the calendar day in the site's time zone; `specificYield` is kWh/kWp,
`hoursCovered` says how many hours had weather, `source` which products fed the day.

## Measurements

A plant with a `Meter` is read at its interval through the `PvMeter` service of the meter's type
(SPI `org.gecko.weather.pv.spi`; the Fronius reader is `org.gecko.weather.pv.fronius`). Readings —
PV, AC, load, grid and battery power in kW, state of charge, energy counter — go to
`measurementsFolder` (default `data/weather/pv-measurements`, local data), one
`pv:PvMeasurementLog` XMI file per plant and local day. A device that cannot be reached is logged
when its error changes and does not hold up the other plants.

`PvForecast.plant(plantId)` returns the profile as stored; `savePlant(plant)` writes one as
`<id>.xmi` (letters, digits, dot, dash, underscore), replacing a profile of the same id — a file
edited by hand loses its XML comments that way. `PvForecast.measurements(plantId, date)` returns a day's log. `forecast` adds `measuredPower` (mean
PV power) to the hours that have begun and `measuredEnergy` to today; gaps over 15 minutes count as
nothing. On hybrid inverters the PV power is the DC side — compare it with `dcPower`.

**Frozen forecasts.** The outlook changes with every model run; a comparison with what was
measured needs it as it stood before. At the local hours in `snapshotHours` (default `6,18`: the
day itself in the morning, the day ahead in the evening) every plant's outlook is written to
`snapshotsFolder` (default `data/weather/pv-forecasts`) as `<plantId>/<yyyy-MM-dd>T<HH>.xmi`, once —
a restart in the same hour writes nothing twice.

The snapshots also keep today whole: the weather service holds each source's latest run, and the
runs of the afternoon begin after the morning, so the report no longer covers the hours already
passed. `forecast` fills such hours of today from the day's snapshots (and the evening before's),
newer over older, marked `(eingefroren)` in the hour's source — the day's energy no longer shrinks
as the day goes on. A snapshot itself is always computed from the current report only.

Configuration: `measurementsFolder` (env `WEATHER_PV_MEASUREMENTS`), `metering` (env
`WEATHER_PV_METERING`, true), `snapshotsFolder` (env `WEATHER_PV_SNAPSHOTS`), `snapshotHours` (env
`WEATHER_PV_SNAPSHOT_HOURS`).

## Tests

Plain JUnit, no network. `PvPhysicsTest` against hand-computed values and invariants — a
horizontal plane receives exactly the global radiation; a plane facing the sun head-on; Erbs at
three clearness indices; Faiman; derating; clipping; a horizon from obstacles across north.
`PlantForecasterTest` on a made-up east/west plant with a synthetic sun and report: east beats west
in the morning, ICON → MOSMIX hand-over, inverter clipping, day sums, a clear midsummer day within
4–8 kWh/kWp, a forest in the west costing the evening, a leafless forest letting part of the sun through in winter only, profiles round-tripping through the folder.
`MeteringTest`: the store per plant and day, mean power and energy with gaps, the poller with its
intervals, a failing and an unknown meter, the measured values in the outlook.

## Calibration

The models are standard and uncalibrated. `systemLosses` and the obstacle heights are the knobs;
compare a few weeks of forecast with the inverter's measured yields and adjust them, rather than
the physics.
