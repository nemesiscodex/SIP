#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
xterm -hold -T "Servidor de Informacion Policial" -e java SIP/ServerIP & 
