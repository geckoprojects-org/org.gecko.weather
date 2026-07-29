# ADR-0008: Versioned, OpenAPI-first REST API with RFC 9457 errors

- **Status:** Deferred
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack), deferred 2026-07-29
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

> **Deferred.** No REST interface is needed yet, and how it is cut will be decided when one is. Nothing
> below is settled; it is kept because the analysis of `F-16` and the presentation problem remains valid
> and will be the starting point. The MVP ([08-mvp.md](../08-mvp.md)) contains no HTTP API.

## Context

The current API is mounted at `/weather/rest` (Felix HTTP context path `/weather`, Jersey context path
`rest`) with resources at `@Path("station")` and `@Path("forecast")`. It has no version segment, returns
bare `Response` status codes with no machine-readable body, and exposes a `hello` debug endpoint on both
resources (`F-16`).

Two consequences matter. Without a version segment, every change breaks every consumer at once and old
and new contracts cannot run side by side. Without structured errors, an integrator cannot distinguish
"unknown station" from "provider temporarily unavailable" — and they do not have access to the logs that
would tell them.

The new model also creates a presentation problem. Values are keyed by canonical
`MeasurementKind` with per-value provenance and uncertainty
([ADR-0005](0005-provider-neutral-model.md), [ADR-0011](0011-lineage-and-uncertainty.md)). Serialised
naively, a 48-hour forecast becomes a flat list of hundreds of value objects, each repeating its
provenance — technically correct and hostile to use.

**The stack changed** ([ADR-0002](0002-emf-as-core-model.md)). `org.gecko.emf.rest.jakartars` is
replaced by two pieces: **`org.eclipse.osgitech.rest`** (1.2.3) as the JakartaRS whiteboard, and
**`org.eclipse.fennec.codec.rest`** for turning `EObject`s into representations. The latter is more than
a message body writer: it carries JakartaRS annotations that control serialisation per endpoint
(`ResourceOption`, `EMFResourceOptions`, `ResourceEClass`, `ResourceOverwriteContentType`) plus request
validation (`ValidateContent`, `ContentNotEmpty`). That directly affects two decisions below — content
negotiation and the lineage verbosity switch.

## Decision

- **Versioned base path `/api/v1`.** Breaking changes go to `/api/v2`; both may run concurrently.
- **OpenAPI-first**, with the published description matching the implementation and covering measurement
  kinds, units and lineage structures (`INT-10`).
- **RFC 9457 problem details** for all errors, distinguishing at least unknown site, no data for the
  requested period, provider temporarily unavailable, and invalid request (`INT-11`).
- **Timestep-grouped representation.** A site forecast is a list of timesteps, each carrying its
  quantities as a map keyed by measurement kind — restoring per-timestep readability without
  reintroducing typed product fields.
- **Lineage travels with the value, not in a separate call.** A verbose default with an opt-in
  `?lineage=summary` for consumers who do not need it.
- **No `hello` endpoints.** Health is a real endpoint with real content.
- **JakartaRS whiteboard (`org.eclipse.osgitech.rest`) with `codec.rest` for representations.** JSON is
  the default content type; the other codec formats are available through normal content negotiation
  rather than as separate endpoints.

Resource shape:

```
POST   /api/v1/sites                      register a site
GET    /api/v1/sites/{id}                 site with resolved bindings and dataCompleteFrom
GET    /api/v1/sites/{id}/forecast        fused timeline: ?from&to&kinds&lineage
GET    /api/v1/forecast?lat&lon           ad-hoc coordinate, marked degraded (INT-13)
GET    /api/v1/kinds                      canonical vocabulary with units and metadata
GET    /api/v1/health                     per-provider health, data age, failure counts
```

## Consequences

### Positive

- Consumers get a contract that can evolve without breaking them, and a migration path when it must
  (`INT-9`).
- Errors are actionable without log access (`INT-11`).
- Timestep grouping keeps the response usable for the actual use case: an energy optimiser iterating
  hour by hour reads one entry per hour.
- `GET /api/v1/kinds` makes the canonical vocabulary self-describing, so the units and semantic metadata
  that make [ADR-0005](0005-provider-neutral-model.md) work are discoverable rather than documented
  elsewhere.
- Lineage-by-default makes `INT-7` unavoidable: a consumer cannot accidentally ignore that a value is
  station-only, because it is in the response they already parse.
- **Extra formats cost nothing.** Because `codec.rest` sits on the same codec as everything else, YAML,
  CBOR and CSV come with the content-type negotiation rather than as work. Two are more than novelties
  here: **CSV** for a spreadsheet-shaped forecast export, and **GeoJSON** for site locations and grid-cell
  geometry, which is otherwise a hand-rolled representation of `GeoPosition` and `GridRef`.
- **The lineage verbosity switch may be a serialisation option rather than a second representation.**
  `ResourceOption` / `EMFResourceOptions` express per-endpoint codec options declaratively, so
  `?lineage=summary` has a chance of being configuration instead of a parallel DTO tree — which is where
  the "two representations to design" cost below would otherwise land.

### Costs accepted

- **Responses are considerably larger** than the current ones, because every value carries provenance
  and uncertainty. Mitigated by `?lineage=summary`, but the verbose default is a deliberate choice:
  opt-out is safe, opt-in would mean most consumers never see quality information.
- **The version segment must actually be honoured.** A `v1` that accumulates breaking changes is worse
  than no version at all, because it lies. This depends on discipline plus baselining on the
  representation types.
- **OpenAPI-first requires the description to be maintained.** Generated from annotations it drifts less
  but is shaped by implementation types; hand-written it drifts more. Either way it needs a test that
  the description matches reality.
- No compatibility layer for the old paths — the response shape changes fundamentally, so emulation
  would reintroduce the DWD coupling being removed ([07-migration.md](../07-migration.md)).
- Two representations to design: the wire format and the model. They deliberately differ, which means a
  mapping layer in `rest` — unless codec options turn out to cover the difference, which is unproven.
- **Timestep grouping is the part codec options almost certainly cannot express.** Dropping or shortening
  lineage is a serialisation concern; restructuring a flat list of `(kind, validAt, value)` into one entry
  per timestep with a kind-keyed map is a different shape, not a filtered one. Expect that to be real
  mapping code even if the lineage switch is free.
- **`codec.rest` and `osgitech.rest` are a second and third dependency where there was one.** Both are
  pre-1.0 or young (`codec` 0.1.0-SNAPSHOT, `osgitech.rest` 1.2.3), and the REST layer is the part
  consumers see, so churn there is churn in the contract.

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Version by media type or header** (`Accept: application/vnd.gecko.weather.v1+json`) | Purer REST, but harder to use from a browser or `curl`, harder to route, and unfamiliar to the integrators who are the actual consumers. Path versioning is the pragmatic choice. |
| **No versioning, evolve compatibly forever** | What exists today. Works until it does not, and then breaks every consumer simultaneously with no escape. |
| **Flat list of values** (model shape on the wire) | Simplest to implement and directly reflects the model, but forces every consumer to group by timestep themselves — repeated work, done differently each time. |
| **Lineage only on request** (`?lineage=full`) | Smaller default responses, but makes quality information invisible to anyone who does not know to ask. For consumers making decisions with money attached, that inverts the safe default. |
| **GraphQL** | Would solve the verbosity trade-off elegantly, but adds a technology to operate and to learn for one maintainer, and OpenAPI is what the Gecko/OSGi stack already supports. |

## Open points

- Authentication and authorisation are unresolved (`QR-10`, `Q-A`). Until decided, the deployment
  documentation must state that the service is not safe to expose publicly.
- **How the OpenAPI description is produced.** `INT-10` requires it to match the implementation, and the
  stack offers candidates rather than an answer: `org.eclipse.fennec.codec.openapi` and
  `org.eclipse.fennec.openapi.model` exist, and `codec.jsonschema` can describe model shapes. Whether the
  description is generated from the Ecore model, from JakartaRS annotations, or written by hand and
  verified by a test is undecided — and it determines how `INT-10` is actually enforced.
- Whether `?lineage=summary` is a codec option or a distinct representation. See the note above; test it
  before committing the API shape.
- Pagination for long time ranges: probably unnecessary for a 48-hour horizon, likely necessary once
  historical queries (`INT-17`) exist.
- Whether the in-process SPI (`INT-12`) mirrors these resources exactly or exposes the model directly.
  Leaning towards mirroring, so there is one mental model.
- Whether `POST /api/v1/sites` is the right registration mechanism given that sites are also
  configuration (`OPS-5`). Both paths need to converge on one source of truth.
