# Build and release

Two builds, one of them publishes.

| Where | On | Does | Publishes |
| --- | --- | --- | --- |
| **GitHub Actions** (`.github/workflows/build.yml`) | every push and pull request | licence header check, `./gradlew build` (compile, bundles, plain JUnit tests) on Temurin 25 | nothing — the pre-gate only |
| **Jenkins** (`Jenkinsfile`) | the DIM Jenkins, every branch | the same build; on `snapshot` and `main` (and, for trying them, `sunorcloud` and `sunorcloud_main`) also `release` and the launch export | `snapshot` → DIM Nexus `dim-snapshot` **and** the Maven Central snapshot repository of the Portal; `main` → DIM Nexus `dim-release` **and** one Central Portal deployment from `dist/bundles` |

## How a release works

The fennec bnd library (`-library: fennec` in `cnf/ext/fennec.bnd`) carries the mechanics, this
workspace only says where things go (`cnf/ext/release.bnd`):

- **`DO_RELEASE`** decides the version bnd stamps on every bundle. Unset or `false`:
  `releng/snapshot.bnd`, `${tstamp}-SNAPSHOT` and a `-pom` version of `x.y.z-SNAPSHOT`. `true`:
  `releng/release.bnd`, the bare `x.y.z` from `base-version` in `cnf/ext/fennec.bnd`. Because the
  version is baked into the bundles, build and release are one Gradle invocation
  (`./gradlew clean build release`).
- **The DIM Nexus** is the `Release` repository of `cnf/ext/release.bnd`, a `MavenBndRepository`
  with `dim-release` as `releaseUrl` and `dim-snapshot` as `snapshotUrl`; which one receives a
  bundle follows from its version. `release.bnd` sets `-releaserepo` outright — `Release`, or
  `Sonatype, Release` when the library has configured the Portal — so the Nexus is on every
  release. (A suffixed `-releaserepo.nexus` would merge with the library's `Release` into
  `Release~`, a name no repository has.)
- **Maven Central snapshots** use the mechanism bnd 7.4 added for the Sonatype Central Portal: the
  library's `releng/central.bnd` configures the `MavenBndRepository` `Sonatype` (snapshot URL
  `production.portal.central.sonatype.com/repository/maven-snapshots/`), but only when
  `GPG_PASSPHRASE` is set and `maven-central` is true (the library's default, which this workspace
  keeps). On `snapshot` `-releaserepo` is then `Sonatype, Release`.
- **Maven Central releases** go the way bnd releases itself (bnd docs, chapter *Sonatype Central
  Portal Publishing*): with `DO_RELEASE=true` `-releaserepo` is `Release, Dist`. `Dist` is a
  `MavenBndRepository` on the local directory `dist/bundles` (ignored by git and the bnd
  workspace), so bnd writes there the jar, `-sources`, `-javadoc` and pom of every bundle, each
  signed (`-maven-release: pom, sign` from `releng/central.bnd`). The Jenkinsfile's
  `centralUpload()` then checks that every jar has its `.asc`, runs
  `.github/scripts/sonatype-upload.sh` (taken from bnd, as is `sonatype-status.sh`, whose status request had to become a POST), which zips the directory and
  posts it to the Portal's publisher API as one deployment, and polls
  `.github/scripts/sonatype-status.sh` until the Portal has validated it. The bearer token is
  `base64(user:password)` of the `central-portal-token`. `SONATYPE_PUBLISHING_TYPE` decides what
  follows: `USER_MANAGED` (the default) leaves the deployment validated and waiting for *Publish*
  at `central.sonatype.com/publishing`; `AUTOMATIC` publishes it. A release on Central cannot be
  withdrawn, so the manual step stays until a release has gone through once.
- **`NEXUS_RELEASE=false`** leaves the Nexus out of a release (`-releaserepo: Dist`). The Jenkinsfile
  sets it on `sunorcloud_main`, the branch that tries the release: `dim-release` takes every version
  only once.
- **`-pom: true`** writes the pom with the SCM, licence and developers from the library's
  `workspace.bnd`; `-sources: true` puts the sources into the bundle.

## What Jenkins needs

The DIM Jenkins already carries most of it from the old DIM bnd library (`org.gecko.bnd.dimc.library`,
which released through the OSSRH staging API with `GNUPG_PASSPHRASE` and a keyring under
`/var/jenkins_home/.gnupg`); the names are mapped where the fennec library's differ.

| | Where | What |
| --- | --- | --- |
| JDK | tool `OpenJDK25` | the bundles target 25 |
| `dim-nexus` | Jenkins credential, username + password (the one the Docker pushes use) | the Nexus login, handed to bnd as `NEXUS_USERNAME`/`NEXUS_PASSWORD` for the inline `<server>` of `devel.data-in-motion.biz` in `release.bnd`. The public agent has no connection settings of its own; a developer keeps a `<server>` with that id in `~/.bnd/connection-settings.xml` or `~/.m2/settings.xml`, which `release.bnd` lists too |
| `gnupg-passphrase` | Jenkins credential, secret text | the passphrase of the signing key, bound as `GPG_PASSPHRASE`; required: without it the run fails before building |
| `gnupg-private-key` | Jenkins credential, secret file | ASCII export of the signing key pair, imported into a keyring of the run only and removed afterwards (as emf.osgi does on GitHub); required: the key is imported into `$WORKSPACE/.gnupg-run` for the run |
| `central-portal-token` | Jenkins credential, username + password | the Portal user token, handed over as `CS_USERNAME`/`CS_PASSWORD` for the inline `<server>` of `production.portal.central.sonatype.com` in `release.bnd`. The Jenkinsfile checks that all four credentials exist and fails the run naming the missing ones; before `release` it logs the `-releaserepo` bnd computed and fails unless it names `Sonatype` and `Release` (snapshot) or `Release` and `Dist` (release). The same token, as bearer, authorises `sonatype-upload.sh`. (The old library's server id was `ossrh-staging-api.central.sonatype.com`; the Portal's publisher API has its own host) |

The Maven group id (`maven_group_id` in `gradle.properties`) must lie in a namespace the Portal
account has verified, or the upload is rejected at validation.

No credential lives in GitHub: the Actions workflow has `contents: read` and nothing to publish
with. Everything that can publish is on the Jenkins agent.

## Versions

`base-version` in `cnf/ext/fennec.bnd` is the workspace version; every bundle is
`${base-version}.SNAPSHOT` until `DO_RELEASE` strips the qualifier. A release is therefore: bump
`base-version`, merge to `main`, let Jenkins run. Baselining against the last release
(`fennec-baselining`, `DEV-8`) stays off until there is one on the Nexus to compare with.
