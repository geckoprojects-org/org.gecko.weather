# Weather outlook — demo UI

A weather page for a registered site: the next **24 hours by the hour** and the **next two days in
summary**, read from the Java remote service `WeatherOutlook` through the
[Fennec Services](https://github.com/eclipse-fennec/emf.services) registry (DDSR). Built in the
style of [xdp-ui](../../../xdp-ui) — Vue 3, `@emfts/core`, the `@ddsr/*` TypeScript client, the xdp
tokens and tiles — so that it moves over as two packages.

```
browser ──/ddsr──▶ DDSR broker :8887        lookup "WeatherOutlook" → endpoint + contract
        ──/weather──▶ weather runtime :9093  POST …/weatheroutlook/outlook?siteId=home → Outlook (XMI)
```

## Run it

```bash
# 1. the broker (emf.services)
java -Dgosh.args=--nointeractive -jar ../emf.services/org.eclipse.fennec.services.broker.rest/generated/distributions/executable/broker.jar

# 2. the weather runtime with the remote role (dev launch + RSA + WeatherOutlook)
./gradlew :org.gecko.weather.runtime:export.demo
WEATHER_PUBLIC_URL=http://localhost:5181/weather \
  java -jar org.gecko.weather.runtime/generated/distributions/executable/demo.jar

# 3. the page
cd ui/outlook && npm install && npm run dev      # http://localhost:5181/
```

Neither broker nor runtime speaks CORS, so the page reaches both under its own origin (the Vite
proxy), and the runtime announces that origin as its public URL — the same arrangement as xdp-ui
with the otel-demo inventory. `?theme=light|dark` picks the scheme, `?examples` shows made-up data;
without a reachable registry the page falls back to examples and says so.

`npm test` (vitest: model mapping against Java-written XMI, icons, formatting), `npm run
type-check`, `npm run build`.

## Layout — what becomes which xdp-ui package

| Here | In xdp-ui |
| --- | --- |
| `src/contracts.ts` — `WeatherOutlook` and its plain values | `@xdp/contracts`: `XDP_WEATHER_OUTLOOK = serviceId<WeatherOutlook>('xdp.weather.outlook')` |
| `src/weather.ddsr/` — `DdsrWeatherOutlook`, `model.ts` | `packages/weather.ddsr`, a tsm `@component({ service: [XDP_WEATHER_OUTLOOK] })` like `datasource.ddsr`; the broker URL from `registryConnection()` |
| `src/weather.ddsr/SampleWeatherOutlook.ts` | the examples the view shows without the registry bundle |
| `src/view.weather/` — `WeatherPage`, `HourlyForecast`, `DayOverview`, `WeatherIcon`, `weather.ts` | `packages/view.weather`, registered under `XDP_VIEW` |
| `src/contracts.ts` — `PvForecast` and its plain values | `@xdp/contracts`: a service id of its own, e.g. `XDP_PV_FORECAST` |
| `src/pv.ddsr/` — `DdsrPvForecast`, `model.ts`, `SamplePvForecast` | `packages/pv.ddsr`, a tsm component like `weather.ddsr` |
| `src/view.pv/` — `PvPage`, `PvChart`, `PvDays`, `PvScene`, `pv.ts`, `pv3d.ts` | `packages/view.pv`, registered under `XDP_VIEW` |
| `src/emf.ts` — registering an ecore, reading values off an EObject | shared by both `.ddsr` packages, or one copy each |
| `src/App.vue` — the two tabs | nothing — the xdp shell's navigation |
| `src/styles/` | nothing — copies of `@xdp/ui.tokens`, loaded by the xdp host |
| `shims/node-crypto.ts` | `scripts/shims/node-crypto.ts` (copied from there) |
| `vendor/ddsr/` | `vendor/ddsr/` (the same four tarballs, emf.services `82e7f3b`) |

The models are not copied: the `model.ts` files import `org.gecko.weather.outlook/model/outlook.ecore`
and `org.gecko.weather.pv/model/pv.ecore`, the files the Java side is generated from. In xdp-ui it would be copied into the package, as
`datasource.ddsr` does with `datasource.ecore`.

## What the page shows

- **Today** in one line above the hours: sunrise, sunset, day length, UV maximum and when it is reached.
- **24 hours** as columns: time, sky icon (WMO `ww` first, cloud cover for the sky codes, moon at
  night), temperature; below, on the same columns, a **meteogram** — temperature, radiation,
  precipitation, night, sun and UV in one picture without a second y-axis: three bands in one frame,
  temperature as a line (warmest and coldest hour labelled), **radiation as a stacked area, diffuse
  below and direct on top** (hourly means in W/m², ICON-D2 for 48 h; the peak labelled; MOSMIX
  global radiation as a dashed line where nothing splits it), precipitation as bars (every wet hour
  labelled), night hours shaded, sunrise and sunset as marks, the day's **UV maximum as a mark at
  solar noon** — DWD publishes the UV index once per day, so there is no hourly curve to draw. Then
  the hourly rain probability and the wind (arrows point where the air goes). Hover or keyboard
  focus on an hour shows all its values.
- **Two days** as xdp tiles: high/low, precipitation, sunshine, **insolation in kWh/m²**, UV maximum, mean cloud cover,
  strongest gust, sunrise, sunset, day length.
- **Sources**: which dataset each group of quantities came from, how far away, when it was issued.
- Colours: the xdp series colours `--s2` (radiation, amber, in two steps for direct/diffuse) and
  `--s1` (precipitation, teal), plus a violet for temperature (`src/styles/weather.css`); the triple
  is validated for both schemes against colour-vision deficiency, all pairs.

## The PV view (`?view=pv`)

The second tab reads the remote service `PvForecast` (`org.gecko.weather.pv`) the same way.

- **Plant line**: total kWp and every array with orientation and tilt.
- **Tiles**: the latest meter reading (PV generator), today's expected energy and what was measured
  so far, measured against expected over the complete hours with production, and house load, grid
  and battery from the latest reading. Without a meter the page says so instead of showing empty
  comparisons.
- **Chart**, from the start of today to 48 hours ahead, one axis in kW: the expected DC power of the
  PV generator per hour as bars (that is what a hybrid inverter reports as PV power); **hatched**
  where the sun stands behind the horizon or an obstacle such as a forest, so only diffuse light
  arrives. Over it the meter readings of today as a line (teal, `--s1`) and their hourly means as
  dots — the value to compare with the bar. Night shaded, "now" marked, each day's best hour labelled.
  Hover or keyboard focus on an hour shows expected DC and AC power, the measured mean, irradiance on
  the module and horizontal, cell temperature, sun position, shading and the weather source.
- **Shading in 3D** (`PvScene.vue`, three.js): the carport with its modules as the profile says
  (count, orientation, tilt, mounting height; the layout itself is schematic), the obstacles as
  trees standing from the profile's forest edge on, the sun of a chosen minute with real shadows
  (shadow map), the sun's path of the day; a slider runs through the day, the forest turns bare in
  the leafless season. The line under the picture says it in words — where the sun stands, what hides
  it, what the forecast expects for the hour — so the picture adds shape, not information one could
  only get by looking. The geometry (`pv3d.ts`: sun between the forecast hours, horizon angle,
  beam share, tree positions, module layout) is plain TypeScript and tested. The profile comes from
  `PvForecast.plant(plantId)`, without the plant's coordinates.
- **Days**: expected energy, specific yield in kWh/kWp, the best hour, and for today the measured
  energy.

The page reloads every five minutes; the runtime reads a meter every minute.
