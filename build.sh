#!/bin/bash

sudo kill -9 $(sudo lsof -t -i:8080) 
sudo kill -9 $(sudo lsof -t -i:9000) 

. ./setenv_test.sh

./mvnw clean package -DskipTests
docker build -f  src/main/docker/Dockerfile.jvm -t ${PROJECT_NAME}:${PROJECT_VERSION} .

echo "Stopping and removing previous ${PROJECT_NAME} containers"
OLD=$(sudo docker ps -a | grep "${PROJECT_NAME}" | awk '{print $1}' | paste -sd ' ' -)
if [ ! -z $OLD ]; then
  docker stop $OLD
  docker rm $OLD
fi

echo "Start ${PROJECT_NAME}"
docker run  -t \
	--name ${PROJECT_NAME} \
	-p 8080:8080 \
	-p 9000:9000 \
	${PROJECT_NAME}:${PROJECT_VERSION}