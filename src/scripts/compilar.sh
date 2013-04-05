#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
javac */*.java 
echo "Compilado!"
