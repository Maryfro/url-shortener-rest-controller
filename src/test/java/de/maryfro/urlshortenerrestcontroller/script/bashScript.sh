#!/bin/bash

SHORTURLS=(03bI6 LY6RB gwBye bXLDwW bKpHI4 bYd8fs oxjq0)
host="http://localhost:8080/"
#COUNTER=0


while true
do
  INDEX=$(($RANDOM % ${#SHORTURLS[@]}))
  curl ${host}"${SHORTURLS[INDEX]}"
  ((COUNTER++))
  sleep 1
done
echo All done

#hit [CTRL+C] to stop!
#while [ $COUNTER -lt 30 ]; do

