/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTrenes
 * Autor: Vanessa Pérez Romanello - 07-oct-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupones.mundo;

/**
 * zd 
 */
public class DatosNoValidosException extends Exception
{
    /**
     * Construye la excepción con el mensaje que describe el problema
     * @param msj Mensaje que describe la causa de la excepción - msj != null
     */
    public DatosNoValidosException( String msj )
    {
        super( msj );
    }
}
