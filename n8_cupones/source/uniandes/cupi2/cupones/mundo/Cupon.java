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


/** 
 * Clase que representa un cup�n en el SistemaCupones
 */
public class Cupon implements Serializable
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------
    
    /**
     * Constante de serializaci�n de la clase
     */
    private static final long serialVersionUID = -1519285154068127714L;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------
    
    /**
     * El identificador del cup�n
     */
    private String id;

    /**
     * El nombre del descuento ofrecido
     */
    private String nombre;

    /**
     * La descripci�n del descuento ofrecido
     */
    private String descripcion;

    /**
     * La cantidad disponible
     */
    private int cantidadDisponible;

    /**
     * La cantidad redimida
     */
    private int cantidadRedimida;

    /**
     * El precio del cup�n
     */
    private double precio;

    /**
     * Las condiciones de uso del cup�n
     */
    private String condiciones;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor de la clase. Crea un nuevo cup�n con los par�metros ingresados por par�metro.
     * <b>pos: </b>  Se inicializ� la cantidad redimida en 0
     * @param nId - Identificador del cup�n - nId != " y nId != null
     * @param nNombre - Nombre del descuento ofrecido - nNombre!= "" y nNombre != null
     * @param nDescripcion - Descripci�n del descuento ofrecido - nDescripcion!= "" y nDescripcion != null
     * @param nCantidadDisponible - Cantidad disponible - nCantidadDisponible > 0
     * @param nPrecio - Precio del cup�n - nPrecio >= 0
     * @param nCondiciones - Condiciones de uso del cup�n - nCondiciones != "" y nCondiciones != null
     */
    public Cupon( String nId, String nNombre, String nDescripcion, int nCantidadDisponible, double nPrecio, String nCondiciones)
    {
        id = nId;
        nombre = nNombre;
        descripcion = nDescripcion;
        cantidadDisponible = nCantidadDisponible;
        precio = nPrecio; 
        condiciones = nCondiciones;
        cantidadRedimida = 0;        
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------
   
    /**
     * Retorna la identificaci�n del cup�n
     * @return Id del cup�n
     */
    public String darId( )
    {
        return id;
    }

    /**
     * Retorna el nombre del cup�n
     * @return Nombre del cup�n
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la descripci�n del descuento ofrecido
     * @return Descripci�n del descuento
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna la cantidad disponible
     * @return Cantidad disponible
     */
    public int darCantidadDisponible( )
    {
        return cantidadDisponible;
    }
    /**
     * Retorna la cantidad que fue redimida
     * @return - cantidadRedimida
     */
    public int darCantidadRedimido( )
    {
        return cantidadRedimida;
    }

    /**
     * Retorna el precio del cup�n
     * @return Precio del cup�n
     */
    public double darPrecio( )
    {
        return precio;
    }
  
    /**
     * Retorna las condiciones de uso del cup�n
     * @return Condiciones de uso
     */
    public String darCondiciones( )
    {
        return condiciones;
    }

    /**
     * Redime un cup�n si todav�a hay disponibles
     * @return true si se pudo redimir el cup�n, false de lo contrario
     */
    public boolean redimirCupon (  )
    {
        if(cantidadDisponible >0 )
        {
            cantidadDisponible--;
            cantidadRedimida++;
                return true;
        }
        else
        {
            return false;
        }
    } 

    /**
     * Retorna la representaci�n en String del cup�n. Tiene el formato <nombre> (<id>)
     */
    public String toString( )
    {
        return nombre +" ("+id+")";
    }
}