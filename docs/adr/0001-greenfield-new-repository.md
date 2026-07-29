# ADR-0001: Rebuild greenfield on a new branch

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

The current implementation cannot reach the [vision](../01-vision.md) by incremental change. Two
clusters of findings block it, and neither is a local fix:

- **Wrong subject.** A forecast location *is* a station (`F-17`); values carry no origin or quality
  (`F-20`); gridded data exists only as a spike (`F-19`). Correcting this means a different domain
  model, which invalidates the storage format, the index layout, the service contracts and the REST
  representation simultaneously.
- **Inverted dependencies.** The index owns storage (`F-8`), the "generic" API bundle is the DWD
  bundle (`F-1`), and transport, decoding, mapping and persistence are fused into single components
  (`F-4`). Untangling these in place means rewriting each bundle's internals anyway.

An in-place refactor would therefore rewrite nearly everything while carrying the constraint of
keeping a working system at every commit — paying the full cost of a rebuild plus the cost of
compatibility, for a codebase of only ~3,400 hand-written lines.

Crucially, **there is no data to preserve.** Storage is a `ConcurrentHashMap` (`F-6`) and both Lucene
indexes are `ByteBuffer` directories (`F-7`), so a restart already discards everything. The usual
strongest argument for in-place migration — protecting accumulated data — does not apply.

## Decision

Rebuild **greenfield on a new branch of the existing repository**. No file is carried forward by
inheritance: the branch starts from a fresh bnd workspace, and knowledge is ported deliberately per
[07-migration.md](../07-migration.md) while structure is not. The existing branches keep running
untouched.

**Coordinates stay as they are** — GitHub `geckoprojects-org/org.gecko.weather`, Maven group
`org.geckoprojects.weather`. The naming question this ADR previously left open is therefore closed
without a rename: there is nothing to free and nothing to redirect.

**The branch is named `sunorcloud`.** `main` and `snapshot` carry the current service and are untouched.

The workspace has been prepared in `/opt/git/org.gecko.weather2`, which is a working directory only —
its content becomes the `sunorcloud` branch, and the directory name is not the project name.

### Why a branch and not a new repository

An earlier draft of this ADR chose a new repository. The reasoning against it is practical rather
than architectural: the value a second repository was supposed to add — a clean history and a clean
dependency set — a fresh branch delivers just as well, while a second repository adds a second set of
CI credentials, Sonar project, Maven Central staging configuration and release job to maintain. For
one person that overhead is the whole cost with none of the benefit.

What is genuinely given up is the *physical* separation: the old code is one `git checkout` away, so
the temptation to fix things there instead of rebuilding is closer to hand than it would have been
across repositories.

## Consequences

### Positive

- No compatibility constraint on any intermediate state, so the model, SPI and storage format can be
  designed correctly rather than reachable-by-refactor.
- The running service is never destabilised. Risk of the redesign harming production is zero.
- Clean dependency set from the start; `F-13` (duplicate bundle versions) and `F-12` (dead code) cannot
  be inherited.
- Salvage becomes a conscious act with a checklist, rather than "whatever happened to still compile".
- **One repository means one CI setup, one Sonar project, one release pipeline, one set of secrets.**
  Nothing has to be provisioned twice, and the existing Jenkins job and its release paths
  (`repo.gecko/{release,snapshot}/org.gecko.weather`) keep working unchanged.
- Old and new are directly comparable — `git diff` across branches answers "how did this work before?"
  without cloning anything.

### Costs accepted

- **Two live branches during the transition**, with the temptation to fix things on the old one. Both
  attention and any bug fix have to be consciously directed — and the branch is easier to slip into
  than a separate clone would have been.
- **No incremental safety net.** An in-place refactor is green at every commit; a greenfield build is
  not useful until the MVP's exit criteria are met. This is the main way the redesign could waste
  effort — tracked as risk `R-6`.
- Knowledge in the old code that nobody remembers to port is silently lost. Mitigated by the salvage
  inventory, which is written before the porting starts.
- **Branch-level release configuration has to distinguish old from new.** The Jenkinsfile keys its
  behaviour off branch names, and both the old development branch and the new one publish snapshots to
  the same coordinates — so two branches could otherwise overwrite each other's artefacts.
- **One shared history that does not connect.** Whether the branch shares ancestry with the old code
  or starts empty, the result is misleading in one direction or the other: a shared base makes the
  first commit look like a mass deletion, and an unrelated history makes `git log --follow` useless
  across the boundary.
- Consumers of the current API get no compatibility layer; the response shape changes fundamentally
  ([ADR-0008](0008-rest-api-design.md)).

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **In-place incremental refactor** (strangler) | Would rewrite nearly every file anyway, while additionally maintaining compatibility with a model that is the thing being replaced. The usual benefit — protecting live data — is absent because no data survives a restart. |
| **A separate new repository** | The original choice here. Rejected on operational overhead: a second CI setup, Sonar project, credential set and release job, for a clean-history benefit a branch already provides. Reconsider only if the two ever need to release independently. |
| **New model alongside the old in the same workspace** | Two model namespaces, two storage layers and two REST trees in one runtime. All the coupling problems of in-place refactoring plus double the surface. |

## Open points

- ~~Branch name.~~ **Settled: `sunorcloud`.**
- **Whether the branch is an orphan** (no shared ancestry) or cut from `snapshot`. Orphan matches the
  content, which is disjoint — cutting from `snapshot` makes the first commit read as a mass deletion of
  the old tree. Against orphan: `git log --follow` stops at the boundary, and `docs/` has to be carried
  over deliberately rather than inherited.
- **How the Jenkinsfile distinguishes the branches** so `snapshot` and `sunorcloud` do not publish
  snapshots to the same coordinates. The Jenkinsfile keys on branch names and currently has a stage for
  `snapshot` only, so `sunorcloud` would build but not publish — which is acceptable at first and has to
  be decided before it should.
- Who consumes the current REST API is unknown, because the paths are unversioned (`F-16`). Must be
  established before cutover; see [07-migration.md](../07-migration.md#consumer-migration).
