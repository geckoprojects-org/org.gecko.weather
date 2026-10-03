# org.gecko.weather.solar

`SolarService` over the NREL Solar Position Algorithm as implemented by
[`net.e175.klaus:solarpositioning`](https://github.com/klausbrunner/solarpositioning) 3.0.1 — MIT,
no dependencies, an OSGi bundle out of the box (`solarpositioning-osgi`). One DS component,
`SpaSolarService`, no configuration.

It replaced a Time4J implementation on 2026-10-03: Time4J is LGPL-2.1, which is workable for a
separately shipped bundle but a review burden on the Eclipse trajectory; SPA is also the more
accurate algorithm (±0.0003°), which a PV module will appreciate.

- `positionAt(position, instant)` — geometric elevation and azimuth for the site's height, no
  atmosphere model.
- `dayInfo(position, date, zone)` — sunrise and sunset on the conventional −50′ horizon, civil (−6°)
  and nautical (−12°) twilight, solar noon, maximum elevation and the time above the horizon within
  the civil day, evaluated **for the civil date in the given zone**. Events that do not occur stay
  unset; day length is `PT0S` in polar night and `PT24H` under the midnight sun.
- Every `DayInfo` carries a `COMPUTED` provenance with `functionId` (`solar.day-events/spa`) and the
  inputs needed to recompute it. `provenance(functionId, position, …)` is public so that report
  assembly can stamp per-timestep values the same way.

## Tests

Plain JUnit 5 against geometry, not against another library: noon elevation `90° − |φ − δ|`, noon
azimuth south (north in the southern hemisphere), solstice day lengths, event order and civil date in
Berlin and Tokyo, polar night and midnight sun on Svalbard. The assertions are unchanged from the
Time4J version — they are what makes swapping the library safe.
