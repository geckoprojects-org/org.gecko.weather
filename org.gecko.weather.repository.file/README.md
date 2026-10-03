# org.gecko.weather.repository.file

`WeatherRepository` as a folder of XMI files. The first and, for the MVP, only storage.

```
<root>/                                     default data/weather, configurable
  sites/<siteId>.xmi                        Site
  reports/<siteId>.xmi                      WeatherReport — current dataset per product
  archive/<siteId>/<providerId>/<productId>/<yyyyMMddTHHmmssZ>.xmi
                                            superseded SourceDataset, append-only; "-n" suffix if
                                            two share an issue second
  catalogs/<providerId>/<productId>.xmi     StationCatalog
  state/<providerId>/<productId>.xmi        SourceStateRecord — the ingest runtime's validators per URL
```

Identifiers are percent-encoded into file names (`Berlin/Mitte` → `Berlin%2FMitte.xmi`), so any id
works on any file system. Every write goes to a `.tmp` sibling and is moved into place atomically.
Writes to one site are serialised; sites do not block each other. Every operation uses a fresh
`ResourceSet`, so loaded objects are detached copies and saving copies the caller's object.

## Configuration

PID `org.gecko.weather.repository.file`, **required** — without a configuration there is no
repository service. Deliberate: an unconfigured durable store that quietly writes next to the JVM's
working directory is worse than none (OPS-1, QR-12). One property:

| Key | Default | Meaning |
| --- | --- | --- |
| `root` | `data/weather` (metatype suggestion only) | Root folder, absolute or relative to the working directory. Mount this as the durable volume. |

Development default: the `runtime` bundle's Configurator JSON sets `root` for the local launch.

## Plain Java

`XmiFolderRepository` is the whole implementation and needs no framework:

```java
WeatherRepository repo = new XmiFolderRepository(Path.of("/var/lib/weather"));
```

It registers the model's `java.time` conversion delegate itself, so XMI with `Instant`/`Duration`
works wherever it is constructed. In OSGi the DS component wraps it with the prototype-scoped
`ResourceSet` service Fennec EMF publishes for the model (`target=(emf.name=weather)`); the core
synchronises on it and removes its resource after every operation, so one instance serves all.

## Tests

Plain JUnit 5 against a temp folder: round trips, detachment, archive window and order, eviction,
delete, catalogues, corrupt files, file-name encoding.
