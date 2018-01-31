#!/usr/bin/env bash

cd core/product-service;                mvn clean install; cd -
cd core/recommendation-service;         mvn clean install; cd -
cd core/review-service;                 mvn clean install; cd -

cd composite/product-composite-service; mvn clean compile; cd -

cd support/discovery-server;            mvn clean compile; cd -
cd support/edge-server;                 mvn clean compile; cd -
