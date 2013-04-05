#!/bin/sh
cd ..
CLASSPATH=.:../lib/gson-2.2.2.jar
export CLASSPATH
xterm -T "Servidor de Imagenes" -e java SImg/ServidorImg &
