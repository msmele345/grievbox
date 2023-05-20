#!/bin/bash

./gradlew clean assemble && \ 
cp build/libs/grievbox-0.0.1-SNAPSHOT.jar src/main/docker && \
cd src/main/docker && docker-compose up
