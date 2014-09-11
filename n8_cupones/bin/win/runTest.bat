@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n8_cupones
REM Autor: Manuel Murcia - 22-nov-2011
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
	
java -ea -classpath lib/cupones.jar;test/lib/junit.jar;test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.SistemaCuponesTest
	
java -ea -classpath lib/cupones.jar;test/lib/junit.jar;test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.CiudadTest
	
java -ea -classpath lib/cupones.jar;test/lib/junit.jar;test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.CuponTest
	
java -ea -classpath lib/cupones.jar;test/lib/junit.jar;test/lib/cuponesTest.jar junit.swingui.TestRunner uniandes.cupi2.cupones.test.UsuarioTest
cd bin/win