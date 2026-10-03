# org.gecko.weather.transport

Byte transport shared by providers. Not SPI — a provider may use it or bring its own.

- `ByteSource` — `fetch(uri, validators) → Unchanged | Content(stream, validators)`. The validators
  are the API's `SourceState.Entity` (`ETag`, `Last-Modified`), so a provider threads the ingest
  runtime's state straight through.
- `HttpByteSource` — over the JDK `HttpClient`: conditional headers out, 304 in, redirects followed,
  `Accept-Encoding: identity` so that sizes and validators refer to the real file. Non-2xx/304 is an
  `IOException` with the status (the runtime retries with backoff).
- `Unwrap` — `zip` (first file entry; a KMZ holds one KML), `gzip`, `byName`.

Tests use an in-process `com.sun.net.httpserver` for the HTTP paths and build archives in memory.
