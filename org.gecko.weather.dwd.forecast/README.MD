# DWD Forecast

## Introduction

The forecast fetcher is currently activated for MOSMIX (https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/met_verfahren_mosmix.html)[https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/met_verfahren_mosmix.html].
The 'WeatherReportSearch' service starts the configurable cron based `org.gecko.weathcer.dwd.fc.impl.DWDMOSMIXStationForecastFetcher`. This needs a station identifier which can be retrieved through the (station service)[../org.gecko.weather.dwd.stations/README.MD] / station search.

In this implementation the configuration for the Station-Forecast-Fetcher is created when triggering a command: `org.gecko.weathcer.dwd.fc.cmd.MOSMIXForecastCommand`. To automatically schedule the cron job, just create a corresponding configuration.

The latest downloaded data are retrieved from DWD OpenData portal: [https://opendata.dwd.de/weather/local_forecasts/mos/MOSMIX_L/single_stations/<station-id>/kml/MOSMIX_L_LATEST_<station-id>.kmz](https://opendata.dwd.de/weather/local_forecasts/mos/MOSMIX_L/single_stations/10567/kml/MOSMIX_L_LATEST_10567.kmz)

*KMZ* is zipped KML file. We extract the KML and load it using EMF into our KML model from [Gecko EMF Models](https://github.com/geckoprojects-org/org.gecko.emf.models/tree/main/net.opengis.kml.model). Finally the content will be mapped into the weather model. 
The `org.gecko.weathcer.dwd.fc.impl.DWDMOSMIXStationForecastFetcher#onDecoded` callback indexes the reports in-memory and can be found using the `org.gecko.weather.dwd.fc.WeatherReportSearch` service.

## Commands

### Start loading weatherdata

Gogo Command: *forecastbystation*

**Parameter:** 

* *StationId* - the station id

**Example:**

Start loading weather data for station with id *“10567”*

`g! forecastbystation 10567` 

### Stop cron jop by Station

Gogo Command: *forecaststop*

**Parameter: **

* *StationId* - the station id

**Example:**

Stop updating weatcher reports for station with id *“10567”*: 

`g! forecaststop 10567` 

