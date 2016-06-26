#!/bin/bash

# Name *--*

echo "Messing with DB_HOST :p "
echo $DB_HOST

if [ -z "$DB_HOSTO" ]; then
    echo "DB_HOST is empty"
else
    echo "DB_HOST is $DB_HOST"
fi

cat /home/traincommander/api/server/datasources.json
sed -i 's/DB_HOST/'"$DB_HOST"'/g' /home/traincommander/api/server/datasources.json

echo "cat server/datasources.json"
cat /home/traincommander/api/server/datasources.json

slc run
