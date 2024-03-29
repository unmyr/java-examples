#!/bin/bash
DB_NAME="fruits"

sudo -u postgres psql -U ${POSTGRES_USER} <<EOF
CREATE ROLE ${ADDITIONAL_USER} LOGIN PASSWORD '${ADDITIONAL_PASSWORD}';
CREATE DATABASE ${DB_NAME};

\connect ${DB_NAME};
CREATE SCHEMA IF NOT EXISTS ${ADDITIONAL_USER};
GRANT USAGE ON SCHEMA ${ADDITIONAL_USER} TO ${ADDITIONAL_USER};
EOF