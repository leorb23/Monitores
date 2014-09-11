/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupones
 * Autor: Manuel Alejandro Murcia - 18-ago-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupones.mundo;

/**
 * JHON MERA
 * Excepci�n para manejar los problemas de persistencia de la aplicaci�n
 * 
 */
public class PersistenciaException extends Exception
{

    /**
     * Constructor de la excepci�n para el manejo de los problemas de persistencia 
     * @param msg - Mensaje de la excepci�n - msg!= null y msg != "" 
     */
    public PersistenciaException( String msg )
    {
        super( msg );
    }

}
