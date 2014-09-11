/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * Excepción para manejar los problemas de persistencia de la aplicación
 * 
 */
public class PersistenciaException extends Exception
{

    /**
     * Constructor de la excepción para el manejo de los problemas de persistencia 
     * @param msg - Mensaje de la excepción - msg!= null y msg != "" 
     */
    public PersistenciaException( String msg )
    {
        super( msg );
    }

}
