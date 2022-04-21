#!/bin/bash
mvn clean install  sonar:sonar  -Dsonar.host.url=http://localhost:9000   -Dsonar.login=$1
