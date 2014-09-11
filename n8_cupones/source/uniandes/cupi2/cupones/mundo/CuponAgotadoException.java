package uniandes.cupi2.cupones.mundo;

/**
 * 
 * @author zd
 *
 */
public class CuponAgotadoException extends Exception
{
    /**
     * Constructor de la excepci�n para el manejo cupones
     * @param msj - Mensaje de la excepci�n - msg!= null y msj != "" 
     */
    public CuponAgotadoException( String msj )
    {
        super( msj );
    }
    
}
