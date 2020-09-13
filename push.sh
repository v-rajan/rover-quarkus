#!/bin/bash

. ./setenv_test.sh

./mvnw clean package -DskipTests
docker build -f  src/main/docker/Dockerfile.jvm -t ${PROJECT_NAME}:${PROJECT_VERSION} .

docker tag ${PROJECT_NAME}:${PROJECT_VERSION} ${PROJECT_ID}/${PROJECT_NAME}:${PROJECT_VERSION}
docker push ${PROJECT_ID}/${PROJECT_NAME}:${PROJECT_VERSION}