# org.gecko.weather.outlook

A weather page's view of a site — the next 24 hours by the hour, the next seven days in summary — as
the remote service **`WeatherOutlook`**, exported through the Remote Service Admin of
[Fennec Services](https://github.com/eclipse-fennec/emf.services). A **consumer** of the weather
service, not part of it: the report keeps every source apart (ADR-0013); this bundle picks one value
per quantity and hour by a fixed rule and says which datasets it picked from.

| Piece | Does |
| --- | --- |
| `model/outlook.ecore` | `SiteDirectory`/`SiteEntry`, `Outlook` with `HourOutlook`, `today` and `days` as `DayOutlook` (with `solarNoon`, where a daily maximum such as the UV index belongs on a time axis), `SourceNote`. Plain types only (`EDate`, `EDouble`, `EInt`, `EString`, unsettable numbers for "no value") so that the TypeScript client reads it without the `java.time` conversion delegate |
| `WeatherOutlook` | `sites()` and `outlook(siteId)` — the interface the contract is derived from (Gradle compiles with `-parameters`, so the parameter is called `siteId` on the wire) |
| `internal.OutlookBuilder` | the picking rule, plain Java |
| `internal.WeatherOutlookComponent` | the DS service with `service.exported.interfaces=*`, `service.exported.configs=fennec.rest`, `ddsr.provider.name=gecko-weather`; configuration required |

## The picking rule

| Quantity | Source |
| --- | --- |
| temperature, dew point, wind, gusts, precipitation and its probability (> 0.1 mm), weather code, sunshine | MOSMIX_L of the rank-0 station (nearest, unless assigned by hand) |
| cloud cover, global radiation | the ICON-D2 cell where it covers the hour, MOSMIX otherwise; ICON radiation = direct + diffuse |
| direct and diffuse radiation | ICON-D2 only (48 h) — the split a PV estimate for a tilted module needs, with the sun's elevation and azimuth from the solar dataset, which every hour carries too |
| insolation per day | the hourly global radiation summed, kWh/m² on a horizontal surface |
| UV index | the daily maximum of the UV product |
| sun elevation, sunrise, sunset, day length | the computed solar dataset and day events |

Instant quantities are read at the hour's start, period quantities from the value ending one hour
later: the hour from 14:00 shows the rain that falls until 15:00. Days are calendar days in the
site's time zone, from tomorrow on, plus `today` for its sun events and UV maximum (its temperatures
cover only the hours the sources still have); their weather code is the most significant of the day
(thunder > snow > rain > drizzle > fog > cloud codes).

## Configuration and launch

PID `org.gecko.weather.outlook.internal.WeatherOutlookComponent` (`hours` 24, `days` 2,
`defaultTimeZone`), and the RSA provider role `org.eclipse.fennec.services.rsa.provider` — both in
`org.gecko.weather.runtime.remote`, environment-overridable. `demo.bndrun` in the runtime is the dev
launch plus these; the UI is in [ui/outlook](../ui/outlook/README.md).

Measured 2026-10-04 against the live runtime: `outlook(home)` answers 10.5 KB of XMI in 0.3 s.

## Tests

`OutlookBuilderTest`, plain JUnit on a hand-made report: rank-0 station wins over report order,
ICON-D2 where it covers and MOSMIX after, period values of the hour ahead, local-day summaries,
UV by day, source notes, ww significance.
