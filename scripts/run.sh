#!/bin/bash

set -e

current_directory="$PWD"

./gradlew build

cd $(dirname $0)/..

docker-compose down

docker-compose up -d --build

result=$?

cd "$current_directory"

exit $result
