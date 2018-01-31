#!/usr/bin/env bash

cd support/discovery-server;            mvn spring-boot:run; cd -
cd support/edge-server;                 mvn spring-boot:run; cd -

cd core/product-service;                mvn spring-boot:run; cd -
cd core/recommendation-service;         mvn spring-boot:run; cd -
cd core/review-service;                 mvn spring-boot:run; cd -

cd composite/product-composite-service; mvn spring-boot:run; cd -
