# org.gecko.ucar.netcdf

Unidata netCDF-Java 5.6.0 as one OSGi bundle: `cdm-core` **plus the GRIB module**, with `re2j` and
`jdom2` embedded because they ship without OSGi manifests. A wrap, not code — `bnd.bnd` is the
whole project.

**Why here.** `org.gecko.libraries` has an `org.gecko.ucar.netcdf` 5.6.0 that wraps `cdm-core` only;
GRIB2 cannot be read with it. `edu.ucar:grib` depends on `cdm-core`, so a second bundle beside it
would split `ucar.nc2.*`, the parameter tables and the `META-INF/services` registrations across
two bundles. This project is the extended wrap, built in this workspace so that nothing waits
upstream (M-14 in [docs/08-mvp.md](../docs/08-mvp.md)); it is meant to **replace** the one in
`org.gecko.libraries` — tracked as
[org.gecko.libraries#3](https://github.com/geckoprojects-org/org.gecko.libraries/issues/3). The
`bnd.bnd` follows that workspace's wrapper pattern (`-buildpath` + `-includeresource` from the
`${repo}` macro, `Export-Package` pulls the classes), so migrating is copying the file and the two
index entries (`cnf/ext/unidata.mvn`, the `com.google.re2j`/`org.jdom` lines in `central.mvn`).

| | |
| --- | --- |
| Embedded | `edu.ucar:cdm-core`, `edu.ucar:grib` (classes, `resources/` tables, `.proto`, `META-INF/services`), `com.google.re2j:re2j`, `org.jdom:jdom2`; sources under `OSGI-OPT/src` |
| Imported from their own bundles | Guava (`com.google.guava` + `failureaccess`), `com.google.protobuf`, `joda-time`, `slf4j.api`, `ucar.units` (`org.gecko.ucar.units` 5.6.0 from Maven Central) |
| Optional | `ucar.httpservices.*`, `org.apache.*` (HttpClient, commons-math), `com.beust.*` (jcommander CLIs), `ucar.jpeg.*` (jj2000, JPEG 2000 packing), `javax.annotation.*` (jsr305), `org.jaxen.*` (jdom2 XPath) — none of them is touched when reading a local GRIB2 or NetCDF-3 file |

554 exported packages; that is what wrapping cdm-core costs. The Unidata artifacts come from
`https://artifacts.unidata.ucar.edu/repository/unidata-all/` (`-plugin.6.Unidata` in
`cnf/ext/fennec.bnd`), they are not on Maven Central.

libaec/CCSDS packing is not supported — it would need JNA and a native library; DWD's ICON-D2
files use `grid_simple`. If DWD ever switches, the library has the hook and this wrap gains a
dependency, nothing else changes.
