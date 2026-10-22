# org.gecko.weather.provider.ecowitt

An own Ecowitt weather station (WS90 "Wittboy", WS80) as a local source — plan item 4.9, `M-17`.
The station's 868 MHz telegrams are received by **rtl_433** on a DVB-T / RTL-SDR stick and served
as a JSON line stream over HTTP; this bundle reads that stream, decodes every line into the
`rtl433` EMF model (`model/rtl433.ecore`) with the Fennec JSON codec, maps the readings to the
canonical kinds and appends them to the report of every site bound to the device through
`WeatherDataSink.append` — `Origin.LOCAL_STATION`, `Quality.OBSERVED`, one partial dataset per
reporting interval (default a minute; the station sends every ~9 s). No gateway, no cloud; the data never leaves the host.

## The radio side

```
sdr/sdr.sh install            # apt rtl-433 + rtl-sdr, blacklist and unload the DVB-T kernel driver (sudo)
sdr/sdr.sh install --service  # … and a systemd user unit gecko-weather-sdr that starts rtl_433 at login
sdr/sdr.sh start              # rtl_433 -f 868.3M -g 19.2 -s 1024k -M level -M time:utc -F http:127.0.0.1:8433
sdr/sdr.sh status             # running? stick on USB? stream answering? shows the next telegram
sdr/sdr.sh stop
sdr/sdr.sh uninstall [--purge]  # unit and blacklist away; --purge also removes the packages
```

`sdr/sdr.sh` runs rtl_433 in the background with a pid file and log under
`~/.local/state/gecko-weather-sdr/`; `start` is a no-op when it already runs and refuses when
another rtl_433 holds the stick, `stop` only ends the one it started, `check` decodes for 30 s in
the foreground without starting anything, `log` follows the log. Frequency, gain, rate, port and
extra rtl_433 arguments come from the environment or from `sdr/sdr.env` next to the script
(`SDR_FREQ`, `SDR_GAIN`, `SDR_RATE`, `SDR_PORT`, `SDR_HOST`, `SDR_EXTRA`). The runtime side
reconnects on its own when rtl_433 is restarted. The user unit calls the script's `start`/`stop`
(`systemctl --user status gecko-weather-sdr`); it runs while the user is logged in, or always
after `loginctl enable-linger`. `install` and `uninstall` are idempotent and only call `sudo`
for what is missing or present.

`-F http` serves on port 8433: `/stream` is the line stream this bundle reads, `/` a small web UI.
Fixed gain matters — with auto gain a cheap FC0012 tuner, which only reaches 868 MHz at the edge of
its range, decodes nothing. `-M level` adds RSSI and SNR to every telegram, which become
`SIGNAL_STRENGTH` / `SIGNAL_NOISE_RATIO`. Every device on the band appears in the stream
(neighbours' sensors included); the component filters by model and device id.

## Configuration

Factory PID `org.gecko.weather.provider.ecowitt`, one instance per station, `REQUIRE`:

```json
"org.gecko.weather.provider.ecowitt~ws90": {
	"station": "WS90",
	"deviceId": "75459",
	"transport": "SDR",
	"url": "http://127.0.0.1:8433/stream",
	"interval": "PT1M",
	"siteId": "gera"
}
```

| Key | Meaning |
| --- | --- |
| `station` | `WS90` or `WS80` — the product id, and which rtl_433 model name is accepted |
| `deviceId` | the sensor's `id` as rtl_433 prints it; empty accepts any device of the model and logs its id |
| `transport` | `SDR` — the rtl_433 line stream; a gateway upload is the obvious second value, not built |
| `url` | the stream |
| `interval` | reporting interval, default `PT1M`: the first telegram after it is appended, the gust is the maximum over it (`period` = interval); `PT0S` appends every telegram, about 125,000 values a day |
| `siteId` | the site the station stands at: bound automatically on `weather:register` / rebind. Empty: `weather:assign <site> ecowitt WS90 <deviceId>` |
| `licence`, `attribution` | carried with every value; defaults `private`, `Eigene Wetterstation` |

The component is a `SiteBindingResolver` (provider `ecowitt`, product = station model), not a
`WeatherProvider`: nothing is fetched, the data is pushed. `weather:status` therefore does not list
it; the log does (`connected to rtl_433 stream`, `device … is sending but no site is bound`).

## What is mapped

| rtl_433 | kind | level | statistic | unit | note |
| --- | --- | --- | --- | --- | --- |
| `temperature_C` | `AIR_TEMPERATURE` | `GROUND_2M` | `INSTANT` | `Cel` | |
| `humidity` | `RELATIVE_HUMIDITY` | `GROUND_2M` | `INSTANT` | `%` | |
| `wind_avg_m_s` | `WIND_SPEED` | `UNSPECIFIED` | `INSTANT` | `m/s` | mast height as installed, not 10 m |
| `wind_max_m_s` | `WIND_GUST` | `UNSPECIFIED` | `MAX` | `m/s` | maximum over the reporting interval, `period` = interval |
| `wind_dir_deg` | `WIND_DIRECTION` | `UNSPECIFIED` | `INSTANT` | `deg` | |
| `uvi` | `UV_INDEX` | `SURFACE` | `INSTANT` | `1` | |
| `light_lux` | `GLOBAL_RADIATION` | `SURFACE` | `INSTANT` | `W/m2` | lux / 126.7, Ecowitt's own convention |
| `rain_mm` | `PRECIPITATION` | `SURFACE` | `ACCUMULATED` | `mm` | the device's running counter, not a period sum |
| `battery_ok` | `BATTERY_LEVEL` | `UNSPECIFIED` | `INSTANT` | `1` | 1 fine, 0 empty |
| `battery_mV` | `BATTERY_VOLTAGE` | `UNSPECIFIED` | `INSTANT` | `V` | |
| `supercap_V` | `CAPACITOR_VOLTAGE` | `UNSPECIFIED` | `INSTANT` | `V` | the solar-charged supercap that runs the WS90 by day |
| `rssi`, `snr` | `SIGNAL_STRENGTH`, `SIGNAL_NOISE_RATIO` | `UNSPECIFIED` | `INSTANT` | `dB` | only with `-M level` |

Station health is ordinary values on purpose: a screen for the own station reads the same
timelines as everything else (`weather:values gera CAPACITOR_VOLTAGE`). `validAt` is the receive
time by the runtime's clock, not rtl_433's local-time string.

## Stream handling

`Rtl433Stream` keeps one GET open on `/stream` and hands over every line as it arrives. When the
stream ends (rtl_433 stopped) or cannot be opened (not running, wrong port, HTTP error) it
reconnects with a backoff from 5 s to 60 s, logging the failure once and the recovery once. The
site list is cached for 30 s, so a new `weather:assign` takes effect within that time.
