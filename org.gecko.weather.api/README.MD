# Weather API

## Introduction

It contains API and base implemenmtation for data fetcher and some Utils

## Data Fetcher

This is an abstract implementation of a fetcher to get data from an OpenData location. There is also an implementation that supports loading data directly into EMF. 

## AstrotimeService

This service delivers sunset and sunrise information depending on geo-location and date. The correct sunrise and sunset times are then calculated.

An implementation is provided by [Time4J](https://github.com/MenoData/Time4J) and there is a service implemlentation in [org.gecko.time4j](../org.gecko.time4j/README.MD)
