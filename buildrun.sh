#!/usr/bin/env bash

./gradlew build

docker compose down clone-factory galactic-empire
docker compose up -d clone-factory galactic-empire