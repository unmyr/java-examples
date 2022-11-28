#!/bin/bash
case $1 in
drop)
    set -x
    for DB_USER in "user_1" "user_2"; do
        sudo -u postgres psql -U postgres <<EOF
DROP DATABASE IF EXISTS ${DB_USER};
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM ${DB_USER};
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public FROM ${DB_USER};
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public FROM ${DB_USER};
DROP ROLE ${DB_USER};
EOF
    done
    ;;
esac