# Weather outlook — demo UI

A weather page for a registered site: the next **24 hours by the hour** and the **next seven days in
summary** (MOSMIX reaches ten; `WEATHER_OUTLOOK_DAYS` says how many), read from the Java remote service `WeatherOutlook` through the
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
type-check`, `npm run build`. The house's views (PV plants, inverter, own station) moved to the
HANS workspace with [ADR-0015](../../docs/adr/0015-repository-split.md).

## Layout — what becomes which xdp-ui package

| Here | In xdp-ui |
| --- | --- |
| `src/contracts.ts` — `WeatherOutlook` and its plain values | `@xdp/contracts`: `XDP_WEATHER_OUTLOOK = serviceId<WeatherOutlook>('xdp.weather.outlook')` |
| `src/weather.ddsr/` — `DdsrWeatherOutlook`, `model.ts` | `packages/weather.ddsr`, a tsm `@component({ service: [XDP_WEATHER_OUTLOOK] })` like `datasource.ddsr`; the broker URL from `registryConnection()` |
| `src/weather.ddsr/SampleWeatherOutlook.ts` | the examples the view shows without the registry bundle |
| `src/view.weather/` — `WeatherPage`, `HourlyForecast`, `DayOverview`, `WeatherIcon`, `weather.ts` | `packages/view.weather`, registered under `XDP_VIEW` |
| `src/emf.ts` — registering an ecore, reading values off an EObject | shared by both `.ddsr` packages, or one copy each |
| `src/App.vue` — the frame around the weather view | nothing — the xdp shell's navigation |
| `src/styles/` | nothing — copies of `@xdp/ui.tokens`, loaded by the xdp host |
| `shims/node-crypto.ts` | `scripts/shims/node-crypto.ts` (copied from there) |
| `vendor/ddsr/` | `vendor/ddsr/` (the same four tarballs, emf.services `82e7f3b`) |

The model is not copied: `model.ts` imports `org.gecko.weather.outlook/model/outlook.ecore`, the file the
Java side is generated from. In xdp-ui it would be copied into the package, as `datasource.ddsr` does
with `datasource.ecore`.

## What the page shows

- **Today** in one line above the hours: sunrise, sunset, day length, UV maximum and when it is reached.
- **Look-back**, six hours by default (`WEATHER_OUTLOOK_PAST_HOURS`): the columns start before
  now, showing what was forecast for those hours, and the meteogram draws the own station's
  measurements over it as a second line in the station colour — temperature and global radiation,
  ten-minute means — with a "jetzt" mark where the forecast begins. The hour tooltip adds the
  measured values under the forecast ones. The page reads itself anew every minute while visible.
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
- **Seven days** as xdp tiles, three to a row: high/low, precipitation, sunshine, **insolation in kWh/m²**, UV maximum, mean cloud cover,
  strongest gust, sunrise, sunset, day length.
- **Now**, when the site has an own weather station (Ecowitt WS90 over rtl_433): what it measured
  last — temperature, humidity, wind and gusts, radiation, UV — in one row, and the forecast's
  current hour in the row below, so measurement and forecast stand side by side and neither
  replaces the other. The row says how old the measurement is and greys out when the station has
  been silent for ten minutes; a weak battery is called out.
- **Source chips** on everything: "Station" (own station), "DWD" (the weather service's products),
  "berechnet" (sun position, computed here). On the now-row, in the hour tooltip's foot (which
  product gave which quantities), on every day tile and in the sources list.
- **Sources**: which dataset each group of quantities came from, how far away, when it was issued.
- Colours: the xdp series colours `--s2` (radiation, amber, in two steps for direct/diffuse) and
  `--s1` (precipitation, teal), plus a violet for temperature (`src/styles/weather.css`); the triple
  is validated for both schemes against colour-vision deficiency, all pairs.
