# org.gecko.weather.pv.fronius

Meter reader for Fronius inverters through the local **Solar API v1** (Fronius document
42,0410,2012). A `PvMeter` service of type `fronius-solar-api` for the PV add-on: it reads
`/solar_api/v1/GetPowerFlowRealtimeData.fcgi`, which every platform answers — Datamanager, Hybrid,
GEN24 and Tauro. Plain HTTP in the local network, no credentials, nothing to Solar.web.

The answer is read into the `solarapi` EMF model (`model/solarapi.ecore`) with the Fennec JSON
codec: the API's member names are codec `key` annotations, the `Inverters` object is an
`EMap<String, Inverter>` keyed by device number, values the device reports as null are null, and
members the model does not know (Smartloads, SecondaryMeters, …) are skipped. In OSGi the
component takes the model's prototype `ResourceSet` (`emf.name=solarapi`); `org.eclipse.fennec.codec`
must run beside it.

```xml
<meter type="fronius-solar-api" url="http://192.168.178.40" interval="60"/>
```

The URL is the inverter's address as seen from the runtime — directly in the plant network or
through a VPN. Only TCP 80 from the runtime host to the inverter is needed.

## What is read

| Solar API | `PvMeasurement` | Note |
| --- | --- | --- |
| `Site.P_PV` | `pvPower` kW | DC side on GEN24 and Hybrid, AC on SnapInverter; null (inverter asleep) becomes 0 |
| `Inverters.*.P` | `acPower` kW | summed over all inverters; includes battery discharge on a hybrid |
| `Site.P_Load` | `loadPower` kW | sign turned: consumption positive |
| `Site.P_Grid` | `gridPower` kW | positive when drawing from the grid |
| `Site.P_Akku` | `batteryPower` kW | positive when discharging |
| `Inverters.*.SOC` | `stateOfCharge` % | lowest device number that reports one |
| `Site.E_Total` | `energyTotal` kWh | on GEN24 updated every 5 minutes only |

`E_Day` and `E_Year` are always null on GEN24, so the day's energy is integrated from the readings
by the PV add-on.

## Errors

- HTTP 404: not a Fronius device, or its Solar API is off. On GEN24 (firmware 1.14.1 and newer it
  is off by default) switch it on in the web UI under *Communication → Solar API*.
- `Head.Status.Code` not 0: a `FroniusException` with the code and its name from the error table
  (12 = DeviceNotAvailable, …).
- No answer within 5 s: an `IOException`. The poller logs it once and keeps trying.

Fronius allows a realtime request every 4 s; the PV add-on never reads a meter faster than every 5 s.

## Tests

`FroniusSolarApiTest`, plain JUnit with the codec set up outside OSGi (a `CodecResourceFactory`
over a metadata whiteboard), an in-process HTTP server and made-up answers in the devices' shape: a GEN24 with battery, a sleeping inverter without meter, two inverters behind a
Datamanager, an error status, garbage, 404 and an unreachable device.
