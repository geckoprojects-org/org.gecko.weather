# DWD Stations

## Introduction

Fetches two different station list from the DWD. This is done by the `org.gecko.weathcer.dwd.impl.DWDStationListFetcher` that is auotmatically scheduled on application *reboot*.

It uses stations from:

* https://opendata.dwd.de/climate_environment/CDC/help/stations_list_CLIMAT_data.txt
* https://www.dwd.de/DE/leistungen/met_verfahren_mosmix/mosmix_stationskatalog.cfg?view=nasPublication&nn=16102

The retrieved stations are indexed in-memory and can be found using the `org.gecko.weather.dwd.StationSearch` service.

## Commands

### Search Station By Name

Gogo Command: *stationbyname*

**Parameter:** 

* *Name* - the station name to serach for

**Example:**

Search for all station with similar name *“Erf”*

`g! stationByName Erf` 

### Search Station Nearby

Gogo Command: *stationbylocation*

**Parameter: **

* *Position - position to search nearby:* **-p (alt. --position) *<lat-value>,<lon-value>***
* *Radius in km (optional, defaults to 30, if not given):* **-r (alt. --radius) *<radius-value>***

**Example:**

Searches for a station near the given position in a radius of 20km: 

`g! stationbylocation -p 51.0,11.03 -r 20` 

Search for a station near the given position using the default radius of 30km:

`g! stationbylocation -p 51.0,11.03` 