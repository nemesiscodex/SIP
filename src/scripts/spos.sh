#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
xterm -T "Servidor Telefonia A" -e java SPos/ServerTelefonia BDA 2026 &
xterm -T "Servidor Telefonia B"  -e java SPos/ServerTelefonia BDB 2027 &
xterm -T "Servidor de Posicion" -hold -e java SPos/ServidorPos &
