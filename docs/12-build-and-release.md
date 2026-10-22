# Build and release

Two builds, one of them publishes.

| Where | On | Does | Publishes |
| --- | --- | --- | --- |
| **GitHub Actions** (`.github/workflows/build.yml`) | every push and pull request | licence header check, `./gradlew build` (compile, bundles, plain JUnit tests) on Temurin 25 | nothing — the pre-gate only |
| **Jenkins** (`Jenkinsfile`) | the DIM Jenkins, every branch | the same build; on `snapshot` and `main` also `release` and the launch export | `snapshot` → DIM Nexus `dim-snapshot`; `main` → DIM Nexus `dim-release` **and** Maven Central |

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
  that replaced OSSRH staging: a `MavenBndRepository` with `stagingUrl` on
  `production.portal.central.sonatype.com/api/v1/publisher/upload` and `sonatypeMode = autopublish`
  (upload, validation, publish without a manual step), the artifacts signed through
  `-maven-release: pom, sign`. The library's `releng/central.bnd` configures it as repository
  `Sonatype` and sets `-releaserepo: Sonatype` — but only when `GPG_PASSPHRASE` is set and
  `maven-central` is true (the library's default, which this workspace keeps). On `main` Jenkins
  sets it; the merged `-releaserepo` is then `Sonatype, Release` and bnd releases to both.
- **`-pom: true`** writes the pom with the SCM, licence and developers from the library's
  `workspace.bnd`; `-sources: true` puts the sources into the bundle.

## What Jenkins needs

| | Where | What |
| --- | --- | --- |
| JDK | tool `OpenJDK25` | the bundles target 25 |
| `dim-nexus` | credential, username + password | the Nexus login; arrives as `NEXUS_USERNAME`/`NEXUS_PASSWORD` and becomes the inline `<server>` for `devel.data-in-motion.biz` in `-connection-settings.nexus`. A developer uses a `<server>` with that id in `~/.m2/settings.xml` instead |
| `gpg-passphrase` | credential, secret text | passphrase of the signing key |
| `gpg-key-id` | credential, secret text | the key id `gpg --local-user` signs with; the key pair must be in the agent's keyring |
| `central-portal-token` | credential, username + password | the Portal user token (`CS_USERNAME` / `CS_PASSWORD`), read by the library's `sonatype-settings.xml` |

The Maven group id (`maven_group_id` in `gradle.properties`) must lie in a namespace the Portal
account has verified, or the upload is rejected at validation.

No credential lives in GitHub: the Actions workflow has `contents: read` and nothing to publish
with. Everything that can publish is on the Jenkins agent.

## Versions

`base-version` in `cnf/ext/fennec.bnd` is the workspace version; every bundle is
`${base-version}.SNAPSHOT` until `DO_RELEASE` strips the qualifier. A release is therefore: bump
`base-version`, merge to `main`, let Jenkins run. Baselining against the last release
(`fennec-baselining`, `DEV-8`) stays off until there is one on the Nexus to compare with.
