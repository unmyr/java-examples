#!/bin/bash
SCRIPT_PATH_IN=${BASH_SOURCE:-$0}
SCRIPT_NAME=$(basename ${SCRIPT_PATH_IN} .sh)
SCRIPT_DIR=$(dirname ${SCRIPT_PATH_IN})

. ${SCRIPT_DIR}/.env.postgres
mkdir -p src/main/resources
cat > ${SCRIPT_DIR}/src/main/resources/application.properties <<EOF
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/fruits
spring.datasource.username=${ADDITIONAL_USER}
spring.datasource.password=${ADDITIONAL_PASSWORD}
EOF
