package uniandes.cupi2.cupones.mundo;

/**
 * 
 * @author zd
 *
 */
public class CuponAgotadoException extends Exception
{
    /**
     * Constructor de la excepción para el manejo cupones
     * @param msj - Mensaje de la excepción - msg!= null y msj != "" 
     */
    public CuponAgotadoException( String msj )
    {
        super( msj );
    }
    
}
