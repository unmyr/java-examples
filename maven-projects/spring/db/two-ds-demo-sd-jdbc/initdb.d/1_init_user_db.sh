#!/bin/bash
DB_NAME="fruits"

USER_1="user_1"
USER_2="user_2"
sudo -u postgres psql -U ${POSTGRES_USER} <<EOF
CREATE ROLE ${USER_1} LOGIN PASSWORD '${ADDITIONAL_PASSWORD}';
DROP SCHEMA IF EXISTS ${USER_1};
DROP SCHEMA IF EXISTS ${USER_2};

ALTER ROLE ${USER_2} LOGIN PASSWORD '${ADDITIONAL_PASSWORD}';
CREATE SCHEMA IF NOT EXISTS ${USER_2};

DROP DATABASE IF EXISTS ${USER_1};
CREATE DATABASE ${USER_1};
\connect ${USER_1};
CREATE SCHEMA IF NOT EXISTS ${USER_1};
GRANT USAGE ON SCHEMA ${USER_1} TO ${USER_1};
SET SCHEMA '${USER_1}';
DROP TABLE IF EXISTS simple;
CREATE TABLE simple (id INTEGER PRIMARY KEY);
GRANT SELECT,INSERT,UPDATE,DELETE ON simple TO ${USER_1};
INSERT INTO simple (id) VALUES (1);

DROP DATABASE IF EXISTS ${USER_2};
CREATE DATABASE ${USER_2};
\connect ${USER_2};
CREATE SCHEMA IF NOT EXISTS ${USER_2};
GRANT USAGE ON SCHEMA ${USER_2} TO ${USER_2};
SET SCHEMA '${USER_2}';
DROP TABLE IF EXISTS simple;
CREATE TABLE simple (id INTEGER PRIMARY KEY);
GRANT SELECT,INSERT,UPDATE,DELETE ON simple TO ${USER_2};
INSERT INTO simple (id) VALUES (1), (2);

\connect ${USER_1};
\l
\dt ${USER_1}.*
\d simple
\dn+

\connect ${USER_2};
\l
\dt ${USER_2}.*
\d simple
\dn+
EOF