#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname $0)
DATABASE=final_capstone_V2
PORT=5432
psql -U postgres -p $PORT -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres -p $PORT $DATABASE &&
psql -U postgres -p $PORT -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -p $PORT -d $DATABASE -f "$BASEDIR/data.sql" &&
psql -U postgres -p $PORT -d $DATABASE -f "$BASEDIR/user.sql"
