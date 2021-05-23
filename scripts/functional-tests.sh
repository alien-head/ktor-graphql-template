#!/bin/bash

set -e

current_directory="$PWD"

./gradlew check -PfunctionalTests

result=$?

cd "$current_directory"

exit $result
