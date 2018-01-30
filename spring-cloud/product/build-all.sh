#!/usr/bin/env bash

cd product/core/product-service;                ./mvn clean install; cd -
cd product/core/recommendation-service;         ./mvn clean install; cd -
cd product/core/review-service;                 ./mvn clean install; cd -
cd product/composite/product-composite-service; ./mvn clean install; cd -

cd product/support/discovery-server;            ./mvn clean install; cd -
cd product/support/edge-server;                 ./mvn clean install; cd -