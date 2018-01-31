#!/usr/bin/env bash

cd support/discovery-server;            mvn clean; cd -
cd support/edge-server;                 mvn clean; cd -

cd core/product-service;                mvn clean; cd -
cd core/recommendation-service;         mvn clean; cd -
cd core/review-service;                 mvn clean; cd -

cd composite/product-composite-service; mvn clean; cd -
