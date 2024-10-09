[![CI Build](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/build.yml/badge.svg)](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/build.yml)[![License](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/license.yml/badge.svg)](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/license.yml )[![Sonar](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/sonar.yml/badge.svg)](https://github.com/geckoprojects-org/org.gecko.weather/actions/workflows/sonar.yml )[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=bugs)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=code_smells)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=coverage)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=alert_status)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=security_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=sqale_index)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.gecko.weather&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.gecko.weather)

# org.gecko.weather



## Rest API

With the default configuration two rest resources deployed, ```station``` and ```forecast```.  

### station 

load a list of stations from the DWD and makes it searchable.

http://localhost:8081/weather/rest/station/name?name=jena

http://localhost:8081/weather/rest/station/location?latitude=51.01&longitude=11.56&radius=50000&max=10

### forecast

provides methods to start the fetching of weather reports for specific stations

http://localhost:8081/weather/rest/forecast/start/10554/

and methods to search and get weather forecast reports

http://localhost:8081/weather/rest/forecast/10554?startDate=Thu,%2010%20Oct%202024%2011:05:30%20CET



## Links

* [Documentation](https://github.com/geckoprojects-org/org.gecko.weather)
* [Source Code](https://github.com/geckoprojects-org/org.gecko.weather) (clone with `scm:git:git@github.com:geckoprojects-org/org.gecko.weather.git`)


## Developers

* **Juergen Albert** (jalbert) / [j.albert@data-in-motion.biz](mailto:j.albert@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *architect*, *developer*
* **Mark Hoffmann** (mhoffmann) / [m.hoffmann@data-in-motion.biz](mailto:m.hoffmann@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *developer*, *architect*

## Licenses

**Eclipse Public License 2.0**

## Copyright

Data In Motion Consuling GmbH - All rights reserved

-+
Data In Motion Consuling GmbH - [info@data-in-motion.biz](mailto:info@data-in-motion.biz)
