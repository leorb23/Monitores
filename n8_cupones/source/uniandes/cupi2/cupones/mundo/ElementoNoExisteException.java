package uniandes.cupi2.cupones.mundo;

/**
 * @author zd
 *
 */
public class ElementoNoExisteException extends Exception
{
    /**
     * Construye la excepci�n con el mensaje que describe el problema
     * @param msj Mensaje que describe la causa de la excepci�n - msj != null
     */
    public ElementoNoExisteException(String msj)
    {
        super( msj );
    }
}
