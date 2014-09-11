/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTrenes
 * Autor: Vanessa P�rez Romanello - 07-oct-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupones.mundo;

/**
 * zd 
 */
public class DatosNoValidosException extends Exception
{
    /**
     * Construye la excepci�n con el mensaje que describe el problema
     * @param msj Mensaje que describe la causa de la excepci�n - msj != null
     */
    public DatosNoValidosException( String msj )
    {
        super( msj );
    }
}
