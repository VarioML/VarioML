#!/bin/bash
# install and setup alias: alias jing='java -jar /opt/apps/xml/jing/bin/jing.jar'
# use source command to execute
FILES=cafe_variome/examples/*.xml
for f in $FILES
do
  echo "Validating $f file..."
  jing -c cafe_variome/cafe_variome.rnc $f
done