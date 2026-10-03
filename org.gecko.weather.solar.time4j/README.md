# org.gecko.weather.solar.time4j

`SolarService` over Time4J (`time4j-base` 5.9.4, an OSGi bundle; its astronomical module is part of
the base artifact). One DS component, `Time4jSolarService`, no configuration.

- `positionAt(position, instant)` — topocentric elevation and azimuth from `SunPosition`, with the
  site's altitude.
- `dayInfo(position, date, zone)` — sunrise, sunset, civil and nautical twilight, solar noon,
  maximum elevation and day length from `SolarTime`, evaluated **for the civil date in the given
  zone**. The old `AstrotimeService` used the platform zone and returned sunset for sunrise; both are
  covered by tests here.
- Day events that do not occur stay unset; day length is `PT0S` in polar night and `PT24H` under the
  midnight sun.
- Every `DayInfo` carries a `COMPUTED` provenance with `functionId` and the inputs needed to
  recompute it. `provenance(functionId, position, …)` is public so that report assembly can stamp
  per-timestep `SUN_ELEVATION`/`SUN_AZIMUTH` values the same way.

## Tests

Plain JUnit 5 against geometry, not against another library: noon elevation `90° − |φ − δ|`, noon
azimuth south (north in the southern hemisphere), solstice day lengths, event order and civil date in
Berlin and Tokyo, polar night and midnight sun on Svalbard.
