# ADR-0006: Java 21, bnd 7.4, OSGi R8 baseline

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

The current workspace sets `javac.source: 17` / `javac.target: 17` in `cnf/build.bnd`, pins
`bnd_version=7.0.0` in `gradle.properties`, and runs on Felix 7.0.5 with `-runee: JavaSE-17`.

New code written in 2024 uses `java.util.Date`, `Calendar`, `GregorianCalendar` and `SimpleDateFormat`
throughout, including in service contracts (`WeatherReportSearch` takes `Date` parameters) and in one
place a `SimpleDateFormat` constructed per invocation (`F-14`). More consequentially, forecast
timesteps arrive as `XMLGregorianCalendar` in UTC and are converted via
`LocalDate.ofInstant(..., ZoneId.systemDefault())` before astro computation — a system-default time zone
dependency inside a calculation whose whole purpose is location-specific correctness.

Time zone handling is where weather and solar data goes wrong, and solar position calculations are
about to become central (`INT-4`).

The design in [ADR-0003](0003-provider-spi.md) also uses records and sealed interfaces in the SPI.

## Decision

Java **21**, bnd **7.4**, OSGi **R8**. `java.time` throughout, with no `Date`, `Calendar` or
`SimpleDateFormat` in new code and no reliance on the system default time zone in any computation.

**As set up in the workspace:** `javac.source`/`javac.target: 21` in `cnf/ext/fennec.bnd`, and
`bnd_version=7.4.0-SNAPSHOT` resolved from `https://bndtools.jfrog.io/bndtools/libs-snapshot-local`.
The snapshot is deliberate rather than provisional: bnd 7.3.0 shipped a regression in
`PomResource.doSnapshot`, which parsed the Maven version `x.y.z-SNAPSHOT` as an OSGi version and
threw, breaking bundle jar builds against the Fennec libraries. The fix is in the 7.4.0 line, and
7.4.0 is not released yet — so the choice is the 7.4.0 snapshot or a broken build. Pin to the release
when it appears.

`-require-bnd: "(version>=7.1.0)"` stays as the declared floor, since that is the genuine minimum;
7.4 is what we run.

## Consequences

### Positive

- Records and sealed interfaces are available for the SPI value types, so `SourceRequest`,
  `SourceResponse` and `GridRef` are concise and exhaustively matchable.
- `Instant`, `Duration`, `ZoneId` and `ZonedDateTime` make the time-handling requirements expressible.
  Forecast validity instants, model runs and issue times are all genuinely instants, and storing them
  as such removes a class of bug rather than mitigating it.
- Pattern matching for `switch` makes the sealed `SourceResponse` handling readable.
- Java 21 is an LTS release with long support, appropriate for a project maintained in intervals over
  years.
- bnd 7.1+ brings baselining and resolver fixes over 7.0.0, and the current setup already depends on
  bnd snapshots for the workspace plugin.
- Java 21 is what the Fennec libraries are built against, so the baseline is not an independent choice
  that could drift from the stack.
- Fennec Persistence ships converters for `Instant`, `ZonedDateTime`, `Duration`, `LocalDate` and
  `LocalDateTime`, so the `java.time` requirement is supported by the persistence layer rather than
  fought against it — the `XMLGregorianCalendar` conversion in the current code has a converter too,
  which is what the DWD KML input actually delivers.

### Costs accepted

- **Divergence from the current workspace**, so the `cnf` carried over in increment 0.1 must be raised
  in 0.2 before anything else is built. Small, but it is the first thing that can go wrong.
- **Running a bnd snapshot means the build depends on a non-release artifact** from a JFrog snapshot
  repository, which can be republished or removed under us. Accepted because the alternative is a
  build that does not work at all; the mitigation is to move to 7.4.0 final as soon as it ships.
- Java 21 must be available in CI and in the container base image — a change to the Dockerfile carried
  over from `docker/` — **and as a configured JDK tool named in the Jenkins tool configuration**. The
  Jenkinsfile requests `jdk 'OpenJDK21'`, replacing the old `'OpenJDK17'`; that name has to exist on
  the Jenkins instance or every build fails at once.
- Records in exported OSGi packages interact with baselining in ways worth verifying early: adding a
  record component is a breaking change, which is correct but stricter than the equivalent bean.
- Contributors need a Java 21 toolchain. Low risk for a single-maintainer project.
- Some dependencies in the current runtime are compiled for older targets; they continue to work, but
  the `-runee` change to `JavaSE-21` requires a full resolve, which may surface latent constraints.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Stay on Java 17** | Also an LTS and already proven here. Rejected because the SPI design leans on records and sealed types, and because starting a multi-year greenfield on the older LTS spends the greenfield opportunity for nothing. |
| **Java 25 / the newest LTS** | 25 has been LTS since September 2025, so this looked like the obvious choice. Rejected because the Fennec libraries we build on target 21: going higher would make this project the only consumer on a different baseline, for language features we do not need. Revisit when Fennec moves. |
| **Keep bnd 7.0.0** | Nothing forces the upgrade, but baselining is a `Must` (`DEV-8`) and is precisely where bnd fixes accumulate. |
| **Wait for bnd 7.4.0 final before starting** | Would avoid depending on a snapshot, but ties the project start to someone else's release date for no gain — the snapshot works and the pin is one line to change. |

## Open points

- ~~Confirm the exact LTS at increment 0.2.~~ **Settled: Java 21**, because that is what the Fennec
  libraries target. Moving follows Fennec rather than leading it.
- `-jpms-module-info` is present but empty in `cnf/ext/fennec.bnd`. Not needed for OSGi; only relevant
  if bundles are ever consumed outside it. Note that `cnf/build.bnd` carries a `-fixupmessages` entry
  ignoring the `jpms.jarname` warning, so the empty setting is deliberate upstream.
- Felix framework version to pair with `JavaSE-21`. `central.mvn` currently offers
  `org.apache.felix.framework:7.0.5`, the same version the old runtime pinned — needs checking against
  a `JavaSE-21` resolve.
- When bnd 7.4.0 is released, drop the JFrog snapshot repository from `settings.gradle` and pin the
  release.
