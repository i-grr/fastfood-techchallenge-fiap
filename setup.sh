#!/bin/bash

# Stop and remove containers, networks, images, and volumes
docker-compose down -v

# Clean and build the Maven project
./mvnw clean install

# Start the containers in detached mode and build the images
docker-compose up -d --build