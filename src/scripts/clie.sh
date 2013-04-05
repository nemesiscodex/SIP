#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
java Cliente/Cliente "Cliente A" &
java Cliente/Cliente "Cliente B" & 
