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


/** 
 * Clase que representa un cupón en el SistemaCupones
 */
public class Cupon implements Serializable
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------
    
    /**
     * Constante de serialización de la clase
     */
    private static final long serialVersionUID = -1519285154068127714L;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------
    
    /**
     * El identificador del cupón
     */
    private String id;

    /**
     * El nombre del descuento ofrecido
     */
    private String nombre;

    /**
     * La descripción del descuento ofrecido
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
     * El precio del cupón
     */
    private double precio;

    /**
     * Las condiciones de uso del cupón
     */
    private String condiciones;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor de la clase. Crea un nuevo cupón con los parámetros ingresados por parámetro.
     * <b>pos: </b>  Se inicializó la cantidad redimida en 0
     * @param nId - Identificador del cupón - nId != " y nId != null
     * @param nNombre - Nombre del descuento ofrecido - nNombre!= "" y nNombre != null
     * @param nDescripcion - Descripción del descuento ofrecido - nDescripcion!= "" y nDescripcion != null
     * @param nCantidadDisponible - Cantidad disponible - nCantidadDisponible > 0
     * @param nPrecio - Precio del cupón - nPrecio >= 0
     * @param nCondiciones - Condiciones de uso del cupón - nCondiciones != "" y nCondiciones != null
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
    // Métodos
    // -------------------------------------------------------------
   
    /**
     * Retorna la identificación del cupón
     * @return Id del cupón
     */
    public String darId( )
    {
        return id;
    }

    /**
     * Retorna el nombre del cupón
     * @return Nombre del cupón
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la descripción del descuento ofrecido
     * @return Descripción del descuento
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
     * Retorna el precio del cupón
     * @return Precio del cupón
     */
    public double darPrecio( )
    {
        return precio;
    }
  
    /**
     * Retorna las condiciones de uso del cupón
     * @return Condiciones de uso
     */
    public String darCondiciones( )
    {
        return condiciones;
    }

    /**
     * Redime un cupón si todavía hay disponibles
     * @return true si se pudo redimir el cupón, false de lo contrario
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
     * Retorna la representación en String del cupón. Tiene el formato <nombre> (<id>)
     */
    public String toString( )
    {
        return nombre +" ("+id+")";
    }
}