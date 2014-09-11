/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupones
 * Autor: Manuel Murcia - 22-nov-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupones.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Clase que representa un usuario del SistemaCupones<br>
 * <b>Inv: </b> <br>
 * cuponesRedimidos != null <br>
 * No puede haber dos cupones con el mismo id
 */
public class Usuario implements Serializable
{
    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase
     */
    private static final long serialVersionUID = 3869586199426398667L;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * El documento de identificaci�n del usuario
     */
    private String id;

    /**
     * El nombre del usuario
     */
    private String nombre;

    /**
     * El apellido del usuario
     */
    private String apellido;

    /**
     * El email del usuario
     */
    private String email;

    /**
     * El tel�fono del usuario
     */
    private String telefono;

    /**
     * La ciudad de residencia del usuario
     */
    private String ciudadResidencia;

    /**
     * La lista de cupones que ha redimido el usuario
     */
    private ArrayList cuponesRedimidos;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor de la clase. Crea un nuevo usuario con los datos ingresados por par�metro. <br>
     * <b>pos: </b> La lista de cupones redimidos fue inicializada.
     * @param nId - Identificaci�n del usuario - nId != null y nId != ""
     * @param nNombre - Nombre del usuario - nNombre != null y nNombre != ""
     * @param nApellido - Apellido del usuario - nApellido != null y nApellido != ""
     * @param nEmail - Correo electr�nico del usuario - nEmail != null y nEmail != ""
     * @param nTelefono - N�mero telef�nico del usuario - nTelefono != null y nTelefono != ""
     * @param nCiudadResidencia - Ciudad de residencia del usuario - nCiudadResidencia != null y nCiudadResidencia != ""
     * @throws DatosNoValidosException - Lanza la excepci�n si el correo electr�nico o el tel�fono no tienen formatos v�lidos
     */
    public Usuario( String nId, String nNombre, String nApellido, String nEmail, String nTelefono, String nCiudadResidencia ) throws DatosNoValidosException
    {
        if( !esCorreoValido( nEmail ) )
        {
            throw new DatosNoValidosException( "El correo ingresado no es v�lido." );
        }
        if( !esTelefonoValido( nTelefono ) )
        {
            throw new DatosNoValidosException( "El tel�fono ingresado no es v�lido." );
        }
        id = nId;
        nombre = nNombre;
        apellido = nApellido;
        email = nEmail;
        telefono = nTelefono;
        ciudadResidencia = nCiudadResidencia;
        cuponesRedimidos = new ArrayList( );
        verificarInvariante( );
    }
    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna el id del usuario
     * @return Documento de identificaci�n del usuario
     */
    public String darId( )
    {
        return id;
    }

    /**
     * Retorna el nombre del usuario
     * @return Nombre del usuario
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el apellido del usuario
     * @return Apellido del usuario
     */
    public String darApellido( )
    {
        return apellido;
    }

    /**
     * Retorna el correo electr�nico del usuario
     * @return Correo electr�nico
     */
    public String darEmail( )
    {
        return email;
    }

    /**
     * Retorna el tel�fono del usuario
     * @return Tel�fono residencia
     */
    public String darTelefono( )
    {

        return telefono;
    }

    /**
     * Retorna la ciudad de residencia del usuario
     * @return - Ciudad de residencia del usuario
     */
    public String darCiudadResidencia( )
    {
        return ciudadResidencia;
    }

    /**
     * Retorna la lista de cupones que ha redimido el usuario
     * @return lista de cupones redimidos
     */
    public ArrayList darCuponesRedimidos( )
    {
        return cuponesRedimidos;
    }

    /**
     * Redime el cup�n dado por par�metro <b>pre: </b> La lista de cupones redimidos fue inicializada
     * @param cupon - Cup�n a redimir - cupon!= null
     * @throws ElementoExisteException - Lanza excepci�n si el cup�n ya fue redimido por el usuario
     * @throws CuponAgotadoException - Lanza excepci�n si no hay m�s cupones disponibles
     */
    public void redimirCupon( Cupon cupon ) throws ElementoExisteException, CuponAgotadoException
    {
        if( cuponRedimido( cupon.darId( ) ) )
        {
            throw new ElementoExisteException( "El cup�n ya fue redimido" );
        }
        if( !cupon.redimirCupon( ) )
        {
            throw new CuponAgotadoException( "No hay cupones disponibles" );
        }
        cuponesRedimidos.add( cupon );
        verificarInvariante( );
    }

    /**
     * Verifica si el cup�n con el id dado ya fue redimido
     * @param idCupon - Id del cup�n buscado - idCupon != null y idCupon != ""
     * @return true si el cup�n ya fue redimido por el usuario, false de lo contrario
     */
    public boolean cuponRedimido( String idCupon )
    {
        Cupon cupon = null;
        boolean encontro = false;
        for( int i = 0; i < cuponesRedimidos.size( ) && !encontro; i++ )
        {
            cupon = ( Cupon )cuponesRedimidos.get( i );
            if( idCupon.equals( cupon.darId( ) ) )
            {
                encontro = true;
            }
        }
        return encontro;
    }

    /**
     * Indica si el correo ingresado por par�metro es v�lido <br>
     * El formato del correo debe ser < direccionElectronica>@<servidor>.<dominio>
     * @param correo - Correo a validar - correo != "" y correo != null
     * @return true si el correo es v�lido, false de lo contrario
     */
    public boolean esCorreoValido( String correo )
    {
        String[] split1 = correo.split( "@" );
        if( split1.length != 2 )
        {
            return false;
        }

        String[] split2 = split1[ 1 ].split( "\\." );
        if( split2.length < 2 )
        {

            return false;
        }
        return true;
    }

    /**
     * Indica si el tel�fono ingresado por par�metro es v�lido <br>
     * Un tel�fono es v�lido si tiene por lo menos 7 d�gitos y todos sus caracteres son num�ricos
     * @param nTelefono - Tel�fono a validar - nTelefono != !! y nTelefono != null
     * @return true si el n�mero de tel�fono es v�lido, false de lo contrario
     */
    public boolean esTelefonoValido( String nTelefono )
    {
        int tama�o = nTelefono.length( );
        boolean esValido = true;
        if( tama�o < 7 )
        {
            esValido = false;
        }
        else
        {
            for( int i = 0; i < tama�o; i++ )
            {
                if( nTelefono.charAt( i ) < 48 || nTelefono.charAt( i ) > 57 )
                {
                    esValido = false;
                }
            }
        }
        return esValido;
    }

    /**
     * Indica si la identificaci�n dada es igual a la ingresada por par�metro
     * @param nId - Identificaci�n a comparar - nId != "" y nId != null
     * @return true si los ids son iguales, false de lo contrario
     */
    public boolean compararPorId( String nId )
    {
        return id.equalsIgnoreCase( nId );
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------
    /**
     * Verifica el invariante de la clase Ciudad <br>
     * cuponesRedimidos != null <br>
     * No puede haber dos cupones con el mismo id
     */
    private void verificarInvariante( )
    {
        assert cuponesRedimidos != null : "La lista de cupones es nula";
        assert !hayCuponesConElMismoId( ) : "Hay dos cupones con el mismo id";
    }
    /**
     * Indica si hay cupones con el mismo nombre
     * @return true si hay ciudades con el mismo nombre, false de lo contrario
     */
    private boolean hayCuponesConElMismoId( )
    {
        int cantCupones = cuponesRedimidos.size( );
        for( int i = 0; i < cantCupones; i++ )
        {
            Cupon d1 = ( Cupon )cuponesRedimidos.get( i );
            for( int j = i + 1; j < cantCupones; j++ )
            {
                Cupon d2 = ( Cupon )cuponesRedimidos.get( j );
                if( d1.equals( d2.darId( ) ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}