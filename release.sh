#!/bin/sh

echo "package the war"
./gradlew clean war

mkdir -p build/deploy/config
cp build/libs/*.war build/deploy
cp src/main/resources/*.properties build/deploy/config