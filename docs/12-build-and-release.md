# Build and release

Two builds, one of them publishes.

| Where | On | Does | Publishes |
| --- | --- | --- | --- |
| **GitHub Actions** (`.github/workflows/build.yml`) | every push and pull request | licence header check, `./gradlew build` (compile, bundles, plain JUnit tests) on Temurin 25 | nothing — the pre-gate only |
| **Jenkins** (`Jenkinsfile`) | the DIM Jenkins, every branch | the same build; on `snapshot` and `main` also `release` and the launch export | `snapshot` → DIM Nexus `dim-snapshot` **and** the Maven Central snapshot repository of the Portal; `main` → DIM Nexus `dim-release` **and** Maven Central |

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
- **Maven Central** uses the mechanism bnd 7.4 added for the Sonatype Central Portal — the one
  that replaced OSSRH staging (the old DIM library still went through
  `ossrh-staging-api.central.sonatype.com`): a `MavenBndRepository` with `stagingUrl` on
  `production.portal.central.sonatype.com/api/v1/publisher/upload` and `sonatypeMode = autopublish`
  (upload, validation, publish without a manual step), the artifacts signed through
  `-maven-release: pom, sign`. The library's `releng/central.bnd` configures it as repository
  `Sonatype` and sets `-releaserepo: Sonatype` — but only when `GPG_PASSPHRASE` is set and
  `maven-central` is true (the library's default, which this workspace keeps). Jenkins sets it on
  `snapshot` and `main`; `-releaserepo` is then `Sonatype, Release` and bnd releases to both. A
  `-SNAPSHOT` lands in the Portal's snapshot repository (`sonatypeMode none`), a bare version is
  uploaded to the publisher API and published after validation (`autopublish`) — as emf.osgi does it
  from GitHub.
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
| `gnupg-passphrase` | Jenkins credential, secret text | the passphrase of the signing key, bound as `GPG_PASSPHRASE`; without it the node's `GNUPG_PASSPHRASE` (the old setup's name) is used, and without that the run stays unsigned and Central is left out |
| `gnupg-private-key` | Jenkins credential, secret file | ASCII export of the signing key pair, imported into a keyring of the run only and removed afterwards (as emf.osgi does on GitHub); without it the keyring under `GNUPGHOME`, default `/var/jenkins_home/.gnupg`, must hold the key |
| `central-portal-token` | Jenkins credential, username + password | the Portal user token, handed over as `CS_USERNAME`/`CS_PASSWORD` for the inline `<server>` of `production.portal.central.sonatype.com` in `release.bnd`. The Jenkinsfile checks whether the credential exists and, without it, still publishes to the Nexus with a warning. (The old library's server id was `ossrh-staging-api.central.sonatype.com`; the Portal's publisher API has its own host) |

The Maven group id (`maven_group_id` in `gradle.properties`) must lie in a namespace the Portal
account has verified, or the upload is rejected at validation.

No credential lives in GitHub: the Actions workflow has `contents: read` and nothing to publish
with. Everything that can publish is on the Jenkins agent.

## Versions

`base-version` in `cnf/ext/fennec.bnd` is the workspace version; every bundle is
`${base-version}.SNAPSHOT` until `DO_RELEASE` strips the qualifier. A release is therefore: bump
`base-version`, merge to `main`, let Jenkins run. Baselining against the last release
(`fennec-baselining`, `DEV-8`) stays off until there is one on the Nexus to compare with.
