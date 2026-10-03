# org.gecko.weather.model

The provider-neutral weather model. The specification — classes, qualifiers, canonical units and the
reasoning behind them — is [docs/10-model.md](../docs/10-model.md); this file only covers what is
specific to the bundle.

## Generation

`model/weather.ecore` is the single source. The genmodel next to it is **derived** by the Fennec
generator from the GenModel annotations in the ecore (`ecore=` attribute of `-generate`) and is
written, not maintained. `src-gen` is generated code and committed; never edit it.

```
./gradlew :org.gecko.weather.model:generate   # regenerate after an ecore change
```

## `java.time` data types

`Instant`, `Duration` and `LocalDate` are custom `EDataType`s on `java.time`. EMF cannot convert them
to and from strings by itself, so the ecore declares `conversionDelegates="java.time"` and this
bundle ships the delegate factory in `org.gecko.weather.model.conversion`:

- **In OSGi** `JavaTimeConversionDelegateComponent` publishes it with
  `emf.configuratorType=CONVERSION_DELEGATE_FACTORY`; the Fennec `DefaultConversionDelegateRegistry`
  puts it into EMF's registry. `org.eclipse.fennec.emf.osgi` must be running for XMI with these
  types to work.
- **Without OSGi** (plain JUnit, tools) call `JavaTimeConversionDelegateFactory.register()` before
  the first save or load. EMF caches the *absence* of a delegate per data type, so a late
  registration has no effect.

## Tests

Plain JUnit 5 in `test/`: XMI round trips of a `Site` and a `WeatherReport`, with no OSGi framework.

```
./gradlew :org.gecko.weather.model:test
```
