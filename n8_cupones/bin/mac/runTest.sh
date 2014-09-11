#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n8_cupones
# Autor: Manuel Murcia - 22-nov-2011
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecución de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath lib/cupones.jar:test/lib/junit.jar:test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.SistemaCuponesTest
	
java -ea -classpath lib/cupones.jar:test/lib/junit.jar:test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.CiudadTest
	
java -ea -classpath lib/cupones.jar:test/lib/junit.jar:test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.CuponTest
	
java -ea -classpath lib/cupones.jar:test/lib/junit.jar:test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.UsuarioTest
cd bin/mac

stty echo
