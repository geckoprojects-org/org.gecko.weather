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
| `Obstacle` | a forest, a house: `azimuthFrom`/`azimuthTo` (may cross north), `distance`, `height` — its top appears under atan((height − mountingHeight) / distance) |
| `HorizonPoint` | a measured horizon line, interpolated; the higher of horizon and obstacles wins |

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

## Tests

Plain JUnit, no network. `PvPhysicsTest` against hand-computed values and invariants — a
horizontal plane receives exactly the global radiation; a plane facing the sun head-on; Erbs at
three clearness indices; Faiman; derating; clipping; a horizon from obstacles across north.
`PlantForecasterTest` on a made-up east/west plant with a synthetic sun and report: east beats west
in the morning, ICON → MOSMIX hand-over, inverter clipping, day sums, a clear midsummer day within
4–8 kWh/kWp, a forest in the west costing the evening, profiles round-tripping through the folder.

## Calibration

The models are standard and uncalibrated. `systemLosses` and the obstacle heights are the knobs;
compare a few weeks of forecast with the inverter's measured yields and adjust them, rather than
the physics.
