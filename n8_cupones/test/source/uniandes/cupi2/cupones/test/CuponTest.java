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
package uniandes.cupi2.cupones.test;

import junit.framework.*; 
import uniandes.cupi2.cupones.mundo.*; 

/**
 * Clase usada para verificar la correcta implementación de Cupón
 */
public class CuponTest  extends TestCase  
{
	
	//-------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------
		
	/**
	 * El cupón donde se harán las pruebas
	 */
	private Cupon cupon; 
		
	//-------------------------------------------------------------
	// Métodos
	//-------------------------------------------------------------
	
		
	/**
	 * Construye un nuevo cupón
	 */
	public void setupEscenario1()
	{
	   cupon = new Cupon( "12345", "Pizza 4x3", "Por la compra de 3 pizzas familiares, lleve la cuarta pizza gratis.", 3, 5000, "Válido hasta el 31/01/2012." );
	}
	
	/**
	 * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * Cupon, darId, darNombre, darCantidadDisponible, darCantidadRedimido, darDescripcion, darPrecio, darCondiciones
	 */
	public void testCupon( )
	{
	    setupEscenario1( );
	    assertEquals( "No inicializó el id correctamente", "12345", cupon.darId( ) );
	    assertEquals( "No inicializo el nombre correctamente", "Pizza 4x3", cupon.darNombre( ) );
	    assertEquals( "No inicializó la cantidad disponible correctamente", 3, cupon.darCantidadDisponible( ) );
	    assertEquals( "No inicializó la cantidad redimida correctamente", 0, cupon.darCantidadRedimido( ) );
	    assertEquals( "No inicializó la descripción correctamente", "Por la compra de 3 pizzas familiares, lleve la cuarta pizza gratis.", cupon.darDescripcion( ) );
	    assertEquals( "No inicializó el precio correctamente", (double) 5000, cupon.darPrecio( ) );
	    assertEquals( "No inicializó las conticiones correctamente", "Válido hasta el 31/01/2012.", cupon.darCondiciones( ) );
	}
	
	/**
	 * Prueba 2 - Prueba el método redimirCupon <br>
	 * Métodos a probar: <br>
	 * redimirCupon, darCantidadDisponible, darCantidadRedimido
	 */
	public void testRedimirCupon( )
	{
	   setupEscenario1( );
	   assertTrue( "Debería poder redimir el cupón", cupon.redimirCupon( ) );
	   assertEquals( "No modificó la cantidad disponible correctamente", 2, cupon.darCantidadDisponible( ) );
	   assertEquals( "No modificó la cantidad redimida correctamente", 1, cupon.darCantidadRedimido( ) );
	   assertTrue( "Debería poder redimir el cupón", cupon.redimirCupon( ) );
	   assertEquals( "No modificó la cantidad disponible correctamente", 1, cupon.darCantidadDisponible( ) );
       assertEquals( "No modificó la cantidad redimida correctamente", 2, cupon.darCantidadRedimido( ) );
       assertTrue( "Debería poder redimir el cupón", cupon.redimirCupon( ) );
       assertEquals( "No modificó la cantidad disponible correctamente", 0, cupon.darCantidadDisponible( ) );
       assertEquals( "No modificó la cantidad redimida correctamente", 3, cupon.darCantidadRedimido( ) );
       assertFalse( "No debería poder redimir el cupón", cupon.redimirCupon( ) );	   
	}
	
	/**
	 * Prueba 3 - Prueba el método toString <br>
	 * Métodos a probar: <br>
	 * toString
	 */
	public void testToString( )
	{
	    setupEscenario1( );
	    assertEquals( "No retorna el string correcto", "Pizza 4x3 (12345)", cupon.toString( ) );
	}
	
}