#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
xterm -T "Servidor de Identificacion Personal" -e java SIdP/ServidorIdP &
