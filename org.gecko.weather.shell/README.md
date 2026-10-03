# org.gecko.weather.shell

Gogo commands in the `weather` scope — the operator's and the developer's hands on the service until
there is an HTTP API. A separate bundle because `@GogoCommand` makes a bundle require Gogo at
resolve time, and a headless launch (the smoke run) must not carry a shell.

```
weather:register home "Home roof" 51.05 13.74 118
weather:sites
weather:assign home dwd MOSMIX_L 10488
weather:runNow dwd MOSMIX_L
weather:status
weather:report home
weather:values home AIR_TEMPERATURE 12
weather:remove home
```
