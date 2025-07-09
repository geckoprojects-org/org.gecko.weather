# Note on DWD Significant Weather

The **DWD (Deutscher Wetterdienst)** — Germany's national meteorological service — uses the term **"significant weather"** ("**signifikantes Wetter**" in German) to refer to **weather phenomena that are relevant for public safety, aviation, or meteorological analysis**, due to their intensity, impact, or deviation from normal conditions.

### In DWD's context, "significant weather" typically includes:

1. **Precipitation**:
   - Rain (light to heavy)
   - Snow
   - Freezing rain
   - Sleet or hail
2. **Storm-related phenomena**:
   - Thunderstorms
   - Lightning
   - Wind gusts (especially when severe or damaging)
3. **Reduced visibility**:
   - Fog
   - Heavy rain or snow reducing visibility
4. **Other impactful events**:
   - Sand or dust storms
   - Volcanic ash (rare in Germany but relevant for aviation)

###  **"Significant weather" in MOSMIX is a categorical value**, **not a probability**.

It’s a **forecasted weather condition**, represented by a **numerical code**, describing the **most dominant expected weather phenomenon** at a given time and location.

The **`ww` parameter** (WMO code for present weather) in MOSMIX is used to represent **significant weather conditions**, 

Here’s a complete table of **WMO Table 4678** significant weather codes—the exact values that the `ww` parameter in MOSMIX can take. These cover all phenomena from 0 to 99 used in present and forecast weather:

| Code | Description                               |
| ---- | ----------------------------------------- |
| 00   | No significant weather                    |
| 04   | Haze/smoke/dust, V ≥ 1 km                 |
| 05   | Haze/smoke/dust, V < 1 km                 |
| 10   | Mist                                      |
| 20   | Fog                                       |
| 21   | Precipitation (recent, unspecified)       |
| 22   | Drizzle or snow grains (recent)           |
| 23   | Rain (recent)                             |
| 24   | Snow (recent)                             |
| 25   | Freezing rain/drizzle (recent)            |
| 26   | Thunderstorm (recent)                     |
| 27   | Blowing snow or sand (recent)             |
| 28   | Blowing snow/sand, V ≥ 1 km (recent)      |
| 29   | Blowing snow/sand, V < 1 km (recent)      |
| 30   | Fog                                       |
| 31   | Patches of fog                            |
| 32   | Fog — thinning                            |
| 33   | Fog — no change                           |
| 34   | Fog — thickening                          |
| 35   | Rime fog                                  |
| 40   | Precipitation (present, unspecified)      |
| 41   | Precipitation slight/moderate             |
| 42   | Precipitation heavy                       |
| 43   | Liquid precipitation slight/mod           |
| 44   | Liquid precipitation heavy                |
| 45   | Solid precipitation slight/mod            |
| 50   | Drizzle                                   |
| 51   | Drizzle slight                            |
| 52   | Drizzle moderate                          |
| 53   | Drizzle heavy                             |
| 54   | Freezing drizzle slight                   |
| 55   | Freezing drizzle moderate                 |
| 56   | Freezing drizzle heavy                    |
| 57   | Drizzle & rain slight                     |
| 58   | Drizzle & rain mod/heavy                  |
| 60   | Rain                                      |
| 61   | Rain slight                               |
| 62   | Rain moderate                             |
| 63   | Rain heavy                                |
| 64   | Freezing rain slight                      |
| 65   | Freezing rain moderate                    |
| 66   | Freezing rain heavy                       |
| 67   | Rain + snow (or drizzle) slight           |
| 68   | Rain + snow mod/heavy                     |
| 70   | Snow                                      |
| 71   | Snow slight                               |
| 72   | Snow moderate                             |
| 73   | Snow heavy                                |
| 74   | Ice pellets slight                        |
| 75   | Ice pellets moderate                      |
| 76   | Ice pellets heavy                         |
| 77   | Snow grains                               |
| 80   | Showers                                   |
| 81   | Rain showers slight                       |
| 82   | Rain showers moderate                     |
| 83   | Rain showers heavy                        |
| 85   | Snow showers slight                       |
| 86   | Snow showers moderate                     |
| 87   | Snow showers heavy                        |
| 90   | Hail showers (no thunder)                 |
| 91   | Thunderstorm recent + slight rain         |
| 92   | Thunderstorm recent + mod/heavy rain      |
| 93   | Thunderstorm recent + slight snow/hail    |
| 94   | Thunderstorm recent + mod/heavy snow/hail |
| 95   | Thunderstorm slight/moderate, no hail     |
| 96   | Thunderstorm with slight hail             |
| 97   | Thunderstorm heavy, no hail               |
| 98   | Thunderstorm with dust/sand storm         |
| 99   | Thunderstorm heavy with hail              |

### 🔄 What codes 00–03 actually mean in MOSMIX:

These are used **exclusively** for cloudiness behavior over the forecast period:

| Code | Description                               |
| ---- | ----------------------------------------- |
| 00   | No change in cloud cover (no development) |
| 01   | Cloudiness is **decreasing**              |
| 02   | Cloudiness remains **unchanged**          |
| 03   | Cloudiness is **increasing**              |

### 🧩 So here's the key difference:

- **00–03**: MOSMIX-only codes for **cloud trend**, *not* part of WMO's present-weather system.
- **04–99**: Standard **WMO Table 4678** significant weather codes used by `ww` in MOSMIX.

------

### ✅ Final Summary for `ww`

- Valid values: **00–03** for cloud cover trend (MOSMIX-specific).
- Plus **04–99**, matching WMO weather codes (rain, snow, hail, fog, etc.).
- Always treat `ww` as the **union** of these two sets when parsing MOSMIX data.

## W1W2

**`w1w2` refers to the past 6 hours**, which is consistent with the MOSMIX reports where:

- `w1w2` encodes **significant weather for two consecutive 3-hour intervals in the past 6 hours**.
- Each **two-digit code** corresponds to one 3-hour block.

### Summary for `w1w2`:

| Part | Time Interval          | Code type                    |
| ---- | ---------------------- | ---------------------------- |
| `w1` | Past 6 to past 3 hours | WMO significant weather code |
| `w2` | Past 3 to current hour | WMO significant weather code |

### Example:

`w1w2 = "0103"` means:

- `01` = significant weather code for 6-3 hours ago
- `03` = significant weather code for 3-0 hours ago

So, looking at the definition, **ww** should be basically be the same as the **w2** part of **w1w2**. However, **in DWD MOSMIX forecasts**, you might **receive different values for `ww` and `w2` (the second part of `w1w2`) for the same forecast time**.

------

### 🔍 So why do `ww` and `w2` sometimes differ?

Even though both describe **significant weather in the 3 hours before the forecast time**, they are **not always derived using the exact same algorithm**. Here's why:

------

### 🔄 1. **`ww` is a direct forecast parameter**

- `ww` is **forecasted directly** by the MOSMIX statistical model for the specific 3-hour interval before the time step.
- It’s derived using a **probabilistic model ensemble** that fuses NWP (numerical weather prediction) outputs.
- The model may apply **thresholds, weights, or filters** to highlight **the most relevant or expected** weather condition.

------

### 🔢 2. **`w1w2` is post-processed from hourly weather values**

- `w1w2` may be **computed** from **hourly or sub-hourly weather symbols** (like from `ww1`, `ww2`, etc.).
- DWD documentation suggests that `w1w2` can be assembled using **dominant or most frequent** codes in the 3-hour blocks.

Thus, `w2` might:

- Reflect a **mode** or **aggregated condition**,
- Or be based on a slightly different **threshold logic** (e.g., occurrence vs dominance).

------

### 📌 Example of divergence

Let’s say:

- 3 hours before the forecast time, conditions vary:
  - 08:00–09:00: rain (`ww=61`)
  - 09:00–10:00: no weather (`ww=00`)
  - 10:00–11:00: drizzle (`ww=51`)
- The **`w2` code** (computed from the block) might be `51` (drizzle),
- But the **`ww` forecast** might select `61` (rain) as **more impactful**, depending on model output.

------

### ✅ In short:

| Parameter | Purpose                                | How it's determined                       |
| --------- | -------------------------------------- | ----------------------------------------- |
| `ww`      | Forecasted most significant weather    | Direct model output (3-hourly)            |
| `w2`      | Aggregated code for last 3-hour period | Derived from hourly/post-processed values |