/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
     * Constante de serialización de la clase
     */
    private static final long serialVersionUID = 3869586199426398667L;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * El documento de identificación del usuario
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
     * El teléfono del usuario
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
     * Constructor de la clase. Crea un nuevo usuario con los datos ingresados por parámetro. <br>
     * <b>pos: </b> La lista de cupones redimidos fue inicializada.
     * @param nId - Identificación del usuario - nId != null y nId != ""
     * @param nNombre - Nombre del usuario - nNombre != null y nNombre != ""
     * @param nApellido - Apellido del usuario - nApellido != null y nApellido != ""
     * @param nEmail - Correo electrónico del usuario - nEmail != null y nEmail != ""
     * @param nTelefono - Número telefónico del usuario - nTelefono != null y nTelefono != ""
     * @param nCiudadResidencia - Ciudad de residencia del usuario - nCiudadResidencia != null y nCiudadResidencia != ""
     * @throws DatosNoValidosException - Lanza la excepción si el correo electrónico o el teléfono no tienen formatos válidos
     */
    public Usuario( String nId, String nNombre, String nApellido, String nEmail, String nTelefono, String nCiudadResidencia ) throws DatosNoValidosException
    {
        if( !esCorreoValido( nEmail ) )
        {
            throw new DatosNoValidosException( "El correo ingresado no es válido." );
        }
        if( !esTelefonoValido( nTelefono ) )
        {
            throw new DatosNoValidosException( "El teléfono ingresado no es válido." );
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
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna el id del usuario
     * @return Documento de identificación del usuario
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
     * Retorna el correo electrónico del usuario
     * @return Correo electrónico
     */
    public String darEmail( )
    {
        return email;
    }

    /**
     * Retorna el teléfono del usuario
     * @return Teléfono residencia
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
     * Redime el cupón dado por parámetro <b>pre: </b> La lista de cupones redimidos fue inicializada
     * @param cupon - Cupón a redimir - cupon!= null
     * @throws ElementoExisteException - Lanza excepción si el cupón ya fue redimido por el usuario
     * @throws CuponAgotadoException - Lanza excepción si no hay más cupones disponibles
     */
    public void redimirCupon( Cupon cupon ) throws ElementoExisteException, CuponAgotadoException
    {
        if( cuponRedimido( cupon.darId( ) ) )
        {
            throw new ElementoExisteException( "El cupón ya fue redimido" );
        }
        if( !cupon.redimirCupon( ) )
        {
            throw new CuponAgotadoException( "No hay cupones disponibles" );
        }
        cuponesRedimidos.add( cupon );
        verificarInvariante( );
    }

    /**
     * Verifica si el cupón con el id dado ya fue redimido
     * @param idCupon - Id del cupón buscado - idCupon != null y idCupon != ""
     * @return true si el cupón ya fue redimido por el usuario, false de lo contrario
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
     * Indica si el correo ingresado por parámetro es válido <br>
     * El formato del correo debe ser < direccionElectronica>@<servidor>.<dominio>
     * @param correo - Correo a validar - correo != "" y correo != null
     * @return true si el correo es válido, false de lo contrario
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
     * Indica si el teléfono ingresado por parámetro es válido <br>
     * Un teléfono es válido si tiene por lo menos 7 dígitos y todos sus caracteres son numéricos
     * @param nTelefono - Teléfono a validar - nTelefono != !! y nTelefono != null
     * @return true si el número de teléfono es válido, false de lo contrario
     */
    public boolean esTelefonoValido( String nTelefono )
    {
        int tamaño = nTelefono.length( );
        boolean esValido = true;
        if( tamaño < 7 )
        {
            esValido = false;
        }
        else
        {
            for( int i = 0; i < tamaño; i++ )
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
     * Indica si la identificación dada es igual a la ingresada por parámetro
     * @param nId - Identificación a comparar - nId != "" y nId != null
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