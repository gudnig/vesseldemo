#!/usr/bin/env bash

./mvnw package -DskipTests
eb deploy -v