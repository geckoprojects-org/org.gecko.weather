# ADR-0004: Durable repository, separate rebuildable index

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

The current implementation has no durable state at all. Reports live in a `ConcurrentHashMap` with no
eviction (`F-6`); both Lucene indexes are configured `"directory.type": "ByteBuffer"` (`F-7`). Because
DWD serves only `LATEST`, a restart destroys data permanently rather than causing a cache miss — the
single most consequential finding in the analysis.

The dependency direction is also inverted. `WeatherReportIndexService` holds both the storage handler
and the Lucene index, and performs `cache.saveReport` / `updateReport` / `deleteReport` *inside* index
operations (`F-8`). Indexing is the entry point and storage is a side effect, so the index can never be
treated as disposable.

The abstraction itself was correctly placed — `WeatherReportStorageHandler` exists and is wired — only
a durable implementation is missing and the direction is backwards.

## Decision

Two separate concerns with a **one-way dependency**: the repository is the truth, the index is derived.

```java
public interface WeatherRepository {
    void store(Collection<MeasuredValue> values);
    List<MeasuredValue> query(SiteRef site, Set<MeasurementKind> kinds,
                              Instant from, Instant to, QueryOptions options);
    Stream<MeasuredValue> replay(Instant from);      // the index's only input
    void evictOlderThan(Instant cutoff);
}

public interface WeatherIndex {
    void index(Collection<MeasuredValue> values);
    void rebuildFrom(Stream<MeasuredValue> values);
}
```

| | Repository | Index |
| --- | --- | --- |
| Role | Durable truth | Derived acceleration |
| Loss on restart | Unacceptable | Acceptable — rebuilt via `replay` |
| Implementation | Open — see below | **Lucene, on a filesystem directory, integrated by us** |

### The index: Lucene, our own integration

Lucene stays the index technology. What does *not* carry over is `org.gecko.emf.search`, which
indexed `EObject`s for us — we write that integration ourselves against
[Fennec EMF](0002-emf-as-core-model.md) rather than adopt the Gecko bundle.

This is affordable precisely because of the split in this ADR: the index is disposable, so a
hand-written integration that turns out to be wrong costs a rebuild, never data. The blast radius of
our own code is bounded by `rebuildFrom`.

Encouragingly, the Fennec persistence concept states the same principle as its own first premise:
*"Losing a projection means re-indexing, never data loss (the Lucene model: documents are truth, the
index is disposable)."* The split is therefore aligned with where the stack is heading, not a local
invention.

### The repository backend: EMF/XMI now, Fennec Persistence prepared

Briefly reopened when the stack gained a persistence layer, and settled by the MVP
([08-mvp.md](../08-mvp.md), decisions M-1 and M-5):

- **Now: EMF resources as XMI on a mounted volume.** One file per site holds the merged current state;
  the append-only raw source record sits beside it, per site and time bucket. One file per site means one
  writer per site, so "persist on change" needs no locking.
- **Prepared: Fennec Persistence** — `org.eclipse.fennec.persistence` with the `eclipselink` (JPA) and
  `mongo` backends behind one API, plus a query metamodel (`query.model`, `persistence.query`), streaming
  (`persistence.pushstreams`) and a TCK. "Prepared" means `WeatherRepository` is the seam and nothing
  above it knows the storage form; it does not mean a second implementation is written.

Two things follow from the MVP's shape that this ADR did not originally account for:

- **Storage is two-part**, not one. The merged instance is overwritten on change by design; the raw
  record is append-only and never overwritten. Retention therefore applies almost entirely to the raw
  record.
- **The index is out of MVP scope.** Without queries and without an HTTP API there is nothing to
  accelerate, so the hand-written Lucene integration is designed for but not built. The split below
  stands; only its second half is deferred.

## Consequences

### Positive

- `OPS-1` is satisfiable at all: data survives restart, upgrade and container replacement.
- The index becomes genuinely disposable (`OPS-2`). Corruption, a Lucene upgrade or a schema change is
  recovered by rebuilding from the repository — never by re-fetching from DWD, which for perishable
  data would be impossible.
- Retention has one home (`evictOlderThan`), so growth is bounded in one place (`OPS-8`).
- Swapping the repository implementation touches exactly one bundle, because `WeatherRepository` is the
  boundary — which is what lets the backend question stay open without blocking anything else.
- A Fennec-backed repository would come with a TCK, so "does this implementation behave?" is a test run
  rather than a judgement.

### Costs accepted

- **Data is written twice**, to the repository and to the index. Ingest cost roughly doubles at the
  write path. Acceptable because subset-on-ingest keeps volumes at the order of 1,500 values per site
  per run.
- **The rebuild path must be maintained and tested**, not merely present. An untested `rebuildFrom` is
  a false sense of safety, so it is an explicit Slice 1 exit criterion rather than a later addition.
- **XMI has real limits** — no concurrent access within one file, no transactions, and query performance
  that degrades as the raw record grows. Chosen against unknown requirements: `Q-B` (retention) and `Q-C`
  (site count) are still unanswered. Risk `R-5`, mitigated only by the interface boundary.
- **Writing the Lucene integration ourselves is new work** that the old stack provided. Bounded by the
  index being disposable, but it is code to write, test and maintain in a one-person project.
- `replay` implies the repository can stream its whole content in a stable order, which constrains the
  storage layout more than a pure key-value store would.
- Two implementations to keep consistent when the model changes, and a documented migration story for
  both (`OPS-13`, risk `R-10`).

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Lucene as the only store** (persistent directory, no separate repository) | Tempting — one store, and we are writing the integration anyway. But Lucene documents are a projection: storing full provenance and reconstructing exact objects makes it a poor primary store, and a Lucene version upgrade or schema change would then risk the *authoritative* data. |
| **Adopt `org.gecko.emf.search` for the index** | Already written and proven against this very model. Rejected: it is on the frozen side of the Gecko/Fennec fork, so adopting it means a dependency that will not follow the rest of the stack. |
| **Keep the current shape but persist the map** | Retains the inverted dependency (`F-8`), so the index still cannot be rebuilt from the store. Fixes the symptom, not the structure. |
| **Event log as the truth, both store and index derived** | Conceptually clean and would make supersession natural. Rejected as over-engineering for one person: log compaction, replay tooling and retention over a log are a project of their own. |

## Open points

- **When to move off XMI, and to which backend.** `persistence.mongo` is document-shaped, which fits
  `MeasuredValue` with nested provenance, and BSON is already a Fennec Codec format; `persistence.eclipselink`
  gives real transactions and turns `evict` into a delete statement. Both cost a service to operate.
  Depends on `Q-B` and `Q-C`.
- Time-bucket granularity for the raw record — hourly, daily or per model run. Determines file count and
  how expensive an as-of query is.
- Whether the merged instance and the raw record share one `WeatherRepository` or are two services. One
  interface keeps callers simple; two make the different overwrite semantics explicit in the type system.
- Whether index rebuild blocks readiness or runs in the background while serving degraded results.
  Leaning towards the latter, per `QR-4`.
- Whether `replay` needs a resumable cursor for large repositories, or whether a full stream is always
  acceptable.
