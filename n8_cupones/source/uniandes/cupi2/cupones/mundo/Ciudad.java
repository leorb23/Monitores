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

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Clase que representa una ciudad existente en el SistemaCupones
 * <b>Inv: </b> <br>
 * cupones != null.
 * No puede haber dos cupones con el mismo id
 */
public class Ciudad implements Serializable
{
    

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase
     */
    private static final long serialVersionUID = -1406442399581663128L;
    
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

  
    /**
     * El nombre de la ciudad
     */
    private String nombre;

    /**
     * El departamento donde se encuentra la ciudad
     */
    private String departamento;

    /**
     * El cup�n actualmente seleccionado
     */
    private int cuponActual;

    /**
     * La lista de cupones disponibles
     */
    private ArrayList cupones;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor de la clase. Crea una nueva ciudad con los par�metros ingresados por par�metro. <br>
     * <b>pos: </b> Se inicializ� el cup�n actual en -1 y se inicializ� la lista de ciudades
     * @param nNombre - Nombre de la ciudad - nNombre != "" y nNombre != null
     * @param nDepartamento - Nombre del departamento donde se encuentra la ciudad - nDepartamento != null y nDepartamento != ""
     */
    public Ciudad( String nNombre, String nDepartamento )
    {
        nombre = nNombre;
        departamento = nDepartamento;
        cupones = new ArrayList( );
        int cuponActual = -1;
        
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------
    /**
     * Retorna el nombre de la ciudad
     * @return Nombre de la ciudad
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el nombre del departamento donde se encuentra la ciudad
     * @return Nombre del departamento
     */
    public String darDepartamento( )
    {
        return departamento;
    }

    /**
     * Retorna la lista de cupones disponibles en la ciudad
     * @return Lista de cupones
     */
    public ArrayList darCupones( )
    {
        return cupones;
    }

    /**
     * Agrega un nuevo cup�n que estar� disponible en la ciudad<br>
     * <b>pos: </b> La lista de cupones se encuentra inicializada
     * @param nNombre - Nombre del descuento ofrecido - nNombre!= "" y nNombre != null
     * @param nDescripcion - Descripci�n del descuento ofrecido - nDescripcion!= "" y nDescripcion != null
     * @param nCantidadDisponible - Cantidad disponible - nCantidadDisponible > 0
     * @param nPrecio - Precio del cup�n - nPrecio >= 0
     * @param nCondiciones - Condiciones de uso del cup�n - nCondiciones != "" y nCondiciones != null
     */
    public void agregarCupon( String nNombre, String nDescripcion, int nCantidadDisponible, double nPrecio, String nCondiciones )
    {
        String id = nombre.substring( 0, 2 ) + departamento.substring( 0, 2 ) + System.currentTimeMillis( )+cupones.size( );
        cupones.add( new Cupon( id, nNombre, nDescripcion, nCantidadDisponible, nPrecio, nCondiciones ) );
        cuponActual = cupones.size( ) - 1;
        
        verificarInvariante( );
    }

    /**
     * Cambia el cup�n actual por el n�mero dado por par�metro
     * @param nCupon - Nueva posici�n del cup�n actual
     * @throws ElementoNoExisteException - Lanza excepci�n cuando el cup�n con el n�mero ingresado no existe.
     */
    public void cambiarCuponActual( int nCupon ) throws ElementoNoExisteException
    {
        if(nCupon < 0 || nCupon > cupones.size( ))
        {
            throw new ElementoNoExisteException( "El cup�n buscado no existe" );
        }
        cuponActual = nCupon;
        
        verificarInvariante( );
    }

    /**
     * Retorna el cup�n actual <b>pre: </b> La lista de cupones se encuentra actualizada<br> <b>pre: </b> La lista de ciudades se encuentra inicializada
     * @return El cup�n actual, null si no hay ning�n cup�n en la lista
     */
    public Cupon darCuponActual( )
    {
        if( cupones.size( ) != 0 )
        {
            return ( Cupon )cupones.get( cuponActual );
        }
        else
        {
            return null;
        }
    }

    /**
     * Retorna el cup�n anterior al actual <br>
     * <b>pre: </b> La lista de cupones se encuentra actualizada <br>
     * <b>pos: </b> Se actualiz� la posici�n del cup�n actual
     * @return El cup�n anterior al actual o null si no hay cupones en la ciudad
     * @throws ElementoNoExisteException - Lanza excepci�n si ya se encuentra en el primer cup�n
     */
    public Cupon darCuponAnterior( ) throws ElementoNoExisteException
    {
        if( cupones.size( ) != 0 )
        {
            if( cuponActual == 0 )
            {
                throw new ElementoNoExisteException( "Ya se encuentra en el primer cup�n" );
            }
            else
            {
                cuponActual--;
                return darCuponActual( );
            }
        }
        else
        {
            return null;
        }
    }
    /**
     * Retorna el cup�n siguiente al actual<br>
     * <b>pre: </b> La lista de cupones se encuentra actualizada <br>
     * <b>pos: </b> Se actualiz� la posici�n del cup�n actual
     * @return El cup�n siguiente al actual o null si no hay cupones en la ciudad
     * @throws ElementoNoExisteException - Lanza excepci�n si ya se encuentra en el �ltimo cup�n
     */
    public Cupon darCuponSiguiente( ) throws ElementoNoExisteException
    {
        int tamCupones = cupones.size( );
        if( tamCupones != 0 )
        {
            if( cuponActual == tamCupones - 1 )
            {
                throw new ElementoNoExisteException( "Ya se encuentra en el �ltimo cup�n" );
            }
            else
            {
                cuponActual++;
                return darCuponActual( );
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Retorna el cup�n con el id dado por par�metro <br>
     * <b>pre: </b> La lista de ciudades se encuentra inicializada
     * @param id - Identificador del cup�n buscado - id != null y id != ""
     * @return cup�n con el id dado por par�metro, null si no lo encontr�
     */
    public Cupon buscarCupon( String id )
    {
        Cupon cuponBuscado = null;
        Cupon cupon = null;
        boolean encontro = false;
        for( int i = 0; i < cupones.size( ) && !encontro; i++ )
        {
            cupon = ( Cupon )cupones.get( i );
            if( cupon.darId( ).equalsIgnoreCase( id ) )
            {
                cuponActual = i;
                cuponBuscado=cupon;
                encontro = true;
            }
        }
        return cuponBuscado;
    }

    /**
     * Indica si la ciudad es la buscada, dados uno nombre y un departamento
     * @param nNombre - Nombre de la ciudad buscada - nNombre != null y nNombre != ""
     * @param nDepartamento  - Nombre del departamento donde se encuentra la ciudad buscada - nDepartamento != null y nDepartamento != ""
     * @return true si es la ciudad buscada, false de lo contrario
     */
    public boolean esCiudadBuscada( String nNombre, String nDepartamento )
    {
        boolean esCiudad = false;
        if( nNombre.equalsIgnoreCase( nombre ) && nDepartamento.equalsIgnoreCase( departamento ) )
        {
            esCiudad = true;
        }
        return esCiudad;

    }

    /**
     * Guarda la informaci�n de los cupones de la ciudad en el archivo de texto
     * @param pw - Objeto que escribe en un archivo de reporte - pw != null
     */
    public void guardarCuponesArchivo( PrintWriter pw )
    {
        pw.println("Ciduad : " +nombre+";" + departamento);
        for( int i = 0; i < cupones.size( ); i++ )
        {
            Cupon miCupon = (Cupon)cupones.get( i );
            pw.println( "Cupon ;" + miCupon.darNombre( ) + ";"+ miCupon.darPrecio( )+";"+miCupon.darCantidadDisponible( )+";"+miCupon.darCondiciones( )+";"+ miCupon.darDescripcion( ));
        }
    }

    /**
     * Indica si hay cupones con el mismo ID
     * @return true si hay cupones con el mismo ID, false de lo contrario
     */
    public boolean hayCuponesConElMismoId()
    {
        boolean encontrado = false;

        for( int i = 0; i < cupones.size( ) ; i++ )
        {
            Cupon micupon = ( Cupon )cupones.get( i );
            for( int j = 0; j < cupones.size( ) ; j++ )
            {
                Cupon micupon2 = ( Cupon )cupones.get( j );
                if(i!=j)
                {
                    if(micupon.darId( ).equalsIgnoreCase( micupon2.darId( ) ))
                    {
                        encontrado = true;
                    }
                }
            }
        }
        return encontrado;

    }

    /**
     * Retorna la representaci�n en String de la ciudad
     * @return Representaci�n en String de la ciudad
     */
    public String toString( )
    {
        return nombre + ", " + departamento;
    }

    
    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------
    /**
     * Verifica el invariante de la clase Ciudad <br>
     * cupones != null <br>
     * No puede haber dos cupones con el mismo id
     */
    private void verificarInvariante()
    {
        assert (cupones != null): "la lista de cupones de la ciudad no puede ser null";
        assert (!hayCuponesConElMismoId( )): "Hay cupones con el mismo id";        
        assert nombre!=null:("El nombre debe ser diferente de null");
        assert departamento!=null:("El departamento debe ser diferente de null");
    }
    

}