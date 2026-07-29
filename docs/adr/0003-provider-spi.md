# ADR-0003: Provider SPI with transport separated from decoding, via a streaming sink

- **Status:** Proposed
- **Date:** 2026-07-28, revised 2026-07-29 (Fennec stack)
- **Deciders:** DIM development team — sign-off pending
- **Supersedes:** —

## Context

Today a "fetcher" is one component doing everything: `DWDMOSMIXStationForecastFetcher` extends
`DWDEMFFetcher` (transport), implements `CronJob` (scheduling), and its `doDecode` method performs KML
navigation, station construction, timestep expansion, measurement mapping and astro enrichment before
handing off to the index (`F-4`). Nothing in it can be tested without a scheduler, a network and an
index.

The redesign has to accommodate formats with fundamentally different characteristics, **within DWD
alone**: MOSMIX is KMZ-wrapped KML per station, SIS is NetCDF on a 0.05° grid, ICON fields are GRIB2,
station catalogues are plain text. European expansion later adds CSV, JSON and BUFR.

The critical constraint comes from gridded data. `DWDEMFFetcher` loads an entire document into an EMF
`ResourceSet` before decoding. That works for one station's KML and does not generalise: the SIS spike
demonstrates the failure mode by materialising the full German grid into roughly half a million objects
per run to serve a site needing one cell (`F-19`).

**What the stack offers, and what it does not oblige.** Fennec Codec
([ADR-0002](0002-emf-as-core-model.md)) provides two levels of access:

- `FormatReaderDelegate<S>` — a **pull-based token stream**, format-agnostic:
  `nextToken()`, `currentName()`, `skipChildren()`, and typed `readString()`/`readInt()`/`readDouble()`
  accessors over `START_OBJECT`/`FIELD_NAME`/`VALUE_*` tokens. Format implementations are contributed as
  `CodecFormatProvider`s keyed by a format id.
- `CodecResourceFactory` / `CodecResource` — the EMF `Resource.Factory` route for whole documents.

`skipChildren()` is a genuinely useful primitive where a decoder must skip most of a structured document
without building objects for it, which is what [subset-on-ingest](0010-subset-on-ingest.md) needs.

**But using the codec is not a requirement.** Fennec has no NetCDF and no GRIB2 support, and there are
formats where routing through a framework buys nothing. A decoder may therefore be written directly
against whatever library or parser suits its source, with no codec involvement at all. The SPI below is
what providers must satisfy; how they satisfy it is theirs to choose. Where the codec fits, use it —
because a token loop is less code than a parser — and where it does not, do not bend the source to it.

**KML is loaded and saved as EMF, normally.** `net.opengis.kml.model` and `de.dwd.cdc.forecast.model` are
Ecore models, so `ecore.xmi` reads them directly; no codec is involved and none is wanted. This
materialises the document, which is correct here: one station's forecast is small. The volume constraint
this ADR exists for is about **gridded fields**, not about whole-document loading as such.

## Decision

Split the provider concern into **four independent extension points** — `SourceTransport`,
`SourceDecoder`, `MeasurementMapper`, `SiteBindingResolver` — and make decoding **push-based via a
sink** rather than returning a materialised document.

```java
public interface SourceTransport {
    SourceResponse fetch(SourceRequest request) throws IOException;
}

public sealed interface SourceResponse {
    record Unchanged() implements SourceResponse {}
    record Content(InputStream data, Optional<String> etag, Optional<Instant> lastModified)
            implements SourceResponse {}
}

public interface SourceDecoder {
    boolean supports(SourceFormat format);
    void decode(InputStream in, DecodeContext context, DecodeSink sink) throws IOException;
}

public interface DecodeSink {
    void accept(String sourceElementId, Instant validAt, Object rawValue, SourceLocation location);
}
```

`DecodeContext` carries what the caller wants — notably the requested grid window — so a decoder can
skip everything else.

### One interface, three kinds of decoder

`SourceDecoder` is the same interface throughout. What sits behind it is chosen per source, and the
effort differs by an order of magnitude — worth stating, because "write a decoder" is not one task:

| | Ecore source models | Structured text | Gridded binary |
| --- | --- | --- | --- |
| Examples | MOSMIX KML | station catalogue CSV/text | SIS NetCDF, ICON GRIB2 |
| Reader | `ecore.xmi` via `ResourceSet` — whole document | Fennec Codec token pull, or a plain parser | external library (UCAR or equivalent), windowed access |
| Decoder is | navigate the loaded model, push values | a loop, either way | real code with its own dependency and OSGi packaging problems |
| Effort | small | small | an `L` item each (`R-8`) |

**The only hard constraint is volume, and it applies to gridded fields.** A decoder must not materialise
a field to extract the cells a site needs — that is `F-19`, and `DecodeContext` carries the requested
window precisely so it need not. Everywhere else, load the document if the document is small.

## Consequences

### Positive

- **Gridded data becomes feasible.** A decoder reads only the cells in `DecodeContext` and emits a
  handful of values, which is what makes [ADR-0010](0010-subset-on-ingest.md) implementable rather than
  aspirational.
- **Memory is bounded by the sink, not by the source.** A GRIB2 field of any size decodes in constant
  memory.
- **`Unchanged` as an explicit response type makes `OPS-6` structural.** A transport that cannot report
  "nothing changed" is visibly incomplete rather than quietly inefficient.
- **Decoders become unit-testable** against a recorded fixture with a collecting sink — no network, no
  framework, no index (`DEV-6`, `QR-6`).
- **The codec is available where it helps without being mandatory.** For formats it covers, a decoder is
  a token loop rather than a parser; where it does not fit, a provider writes its own. Only NetCDF and
  GRIB2 are genuinely large work either way.
- `SourceFormat` can line up with the codec's format ids (`getFormatId()`, `getFileExtensions()`) where
  the codec is used, without becoming dependent on them.
- Transport is reusable: HTTP-with-conditional-GET plus zip/gzip unwrapping is written once and used by
  every DWD product.
- Mapping is written once, generically, over declared metadata — eliminating the hand-written branch
  tree of `F-3`. Which metadata mechanism is [ADR-0005](0005-provider-neutral-model.md)'s open question;
  `MeasurementMapper` is unaffected either way, since it consumes the mapping rather than declaring it.

### Costs accepted

- **More interfaces than a single fetcher class.** Following one value from URL to storage means reading
  four collaborators instead of one method. Real cost for a newcomer, offset by each piece being
  individually comprehensible.
- **Push-based decoding is less obvious to read than returning a list.** Inversion of control always is.
  Mitigated by keeping `DecodeSink` deliberately tiny.
- `Object rawValue` in the sink is untyped, deferring type resolution to the mapper. The alternative —
  a generic or sealed value type — was judged more ceremony than benefit given that source values
  arrive as whatever the format produces (`short` for SIS analysis files, `float` for forecast files).
- Four extension points mean four ways to get a provider wrong. Mitigated by `provider.dwd.mosmix`
  serving as the reference implementation.
- A decoder that genuinely needs whole-document context (cross-referencing, back-references) fits the
  streaming model awkwardly and may need to buffer internally.
- **One interface, very different effort classes.** A newcomer reading `SourceDecoder` cannot tell that a
  CSV decoder is an afternoon and a GRIB2 decoder is weeks. Documented in the table above rather than
  solved, because splitting the interface would buy nothing at runtime.
- **Freedom to skip the codec means less uniformity.** Two providers may read structurally similar
  sources in unrelated ways, and there is no single place to fix a class of parsing bug. Accepted
  deliberately: forcing every source through one framework was how the old code ended up with a 599-line
  utility serving one product (`F-3`).

## Alternatives considered

| Alternative | Why not |
| --- | --- |
| **Keep the fetcher-returns-document shape** (`DWDEMFFetcher`) | Cannot handle gridded data at all without materialising it — the exact defect in `F-19`. |
| **Return `Stream<MeasuredValue>` instead of a sink** | Cleaner to read, but pull-based streaming over binary grid formats forces the decoder to hold reader state across `next()` calls, which is harder to get right with NetCDF/GRIB2 readers than pushing. Note the codec's own reader *is* pull-based and that is fine — the state sits in the codec's delegate, one level below us. The objection is to *our* interface being pull, not to pulling at all. |
| **Use the codec as the decoder SPI directly**, no `SourceDecoder` of our own | Fewer interfaces, and for text formats it would nearly work. Rejected because it has no answer for NetCDF and GRIB2, which are not codec formats and are the reason this SPI exists at all — and because `DecodeContext` (the requested grid window) has no place in the codec's model. |
| **Mandate the codec for every format it covers** | Would give uniformity across providers. Rejected: a framework that must be used becomes a constraint to work around the moment a source does not fit it, and the SPI's contract is the emitted values, not the route taken to them. |
| **One `Provider` interface with all methods** | Fewer types, but forces every provider to implement transport concerns it does not care about, and prevents reusing one HTTP transport across all DWD products. |
| **Decode straight into canonical values, no mapper** | Puts the source vocabulary mapping inside each decoder, so it cannot be declarative (`DEV-5`) and cannot be shared across products of the same provider. |

## Open points

- Whether `SourceTransport` also owns decompression (KMZ → KML, gzip) or whether that is a decorating
  transport. Leaning towards a decorator so a decoder never sees compression.
- Whether GRIB2 decoding can use the same UCAR library as NetCDF, or needs a separate dependency with
  its own OSGi packaging problems. Risk `R-8`; investigate in Slice 3.5, not before. Fennec contributes
  nothing here either way.
- **MOSMIX_S, if it is ever used.** KML via `ecore.xmi` is settled and fine for per-station files. A
  single file containing every station is a different volume question and would need measuring before it
  is loaded whole. Not an MVP concern — MOSMIX_L is per station.
