package uniandes.cupi2.cupones.mundo;


/**
 * @author zd
 *
 */
public class ElementoExisteException extends Exception
{
    /**
     * Construye la excepción con el mensaje que describe el problema
     * @param mensaje Mensaje que describe la causa de la excepción - mensaje != null
     */
    public ElementoExisteException(String mensaje)
    {
        super( mensaje );
    }
}
