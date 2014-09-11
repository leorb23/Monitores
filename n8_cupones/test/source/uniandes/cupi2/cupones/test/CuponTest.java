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
package uniandes.cupi2.cupones.test;

import junit.framework.*; 
import uniandes.cupi2.cupones.mundo.*; 

/**
 * Clase usada para verificar la correcta implementaci�n de Cup�n
 */
public class CuponTest  extends TestCase  
{
	
	//-------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------
		
	/**
	 * El cup�n donde se har�n las pruebas
	 */
	private Cupon cupon; 
		
	//-------------------------------------------------------------
	// M�todos
	//-------------------------------------------------------------
	
		
	/**
	 * Construye un nuevo cup�n
	 */
	public void setupEscenario1()
	{
	   cupon = new Cupon( "12345", "Pizza 4x3", "Por la compra de 3 pizzas familiares, lleve la cuarta pizza gratis.", 3, 5000, "V�lido hasta el 31/01/2012." );
	}
	
	/**
	 * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * Cupon, darId, darNombre, darCantidadDisponible, darCantidadRedimido, darDescripcion, darPrecio, darCondiciones
	 */
	public void testCupon( )
	{
	    setupEscenario1( );
	    assertEquals( "No inicializ� el id correctamente", "12345", cupon.darId( ) );
	    assertEquals( "No inicializo el nombre correctamente", "Pizza 4x3", cupon.darNombre( ) );
	    assertEquals( "No inicializ� la cantidad disponible correctamente", 3, cupon.darCantidadDisponible( ) );
	    assertEquals( "No inicializ� la cantidad redimida correctamente", 0, cupon.darCantidadRedimido( ) );
	    assertEquals( "No inicializ� la descripci�n correctamente", "Por la compra de 3 pizzas familiares, lleve la cuarta pizza gratis.", cupon.darDescripcion( ) );
	    assertEquals( "No inicializ� el precio correctamente", (double) 5000, cupon.darPrecio( ) );
	    assertEquals( "No inicializ� las conticiones correctamente", "V�lido hasta el 31/01/2012.", cupon.darCondiciones( ) );
	}
	
	/**
	 * Prueba 2 - Prueba el m�todo redimirCupon <br>
	 * M�todos a probar: <br>
	 * redimirCupon, darCantidadDisponible, darCantidadRedimido
	 */
	public void testRedimirCupon( )
	{
	   setupEscenario1( );
	   assertTrue( "Deber�a poder redimir el cup�n", cupon.redimirCupon( ) );
	   assertEquals( "No modific� la cantidad disponible correctamente", 2, cupon.darCantidadDisponible( ) );
	   assertEquals( "No modific� la cantidad redimida correctamente", 1, cupon.darCantidadRedimido( ) );
	   assertTrue( "Deber�a poder redimir el cup�n", cupon.redimirCupon( ) );
	   assertEquals( "No modific� la cantidad disponible correctamente", 1, cupon.darCantidadDisponible( ) );
       assertEquals( "No modific� la cantidad redimida correctamente", 2, cupon.darCantidadRedimido( ) );
       assertTrue( "Deber�a poder redimir el cup�n", cupon.redimirCupon( ) );
       assertEquals( "No modific� la cantidad disponible correctamente", 0, cupon.darCantidadDisponible( ) );
       assertEquals( "No modific� la cantidad redimida correctamente", 3, cupon.darCantidadRedimido( ) );
       assertFalse( "No deber�a poder redimir el cup�n", cupon.redimirCupon( ) );	   
	}
	
	/**
	 * Prueba 3 - Prueba el m�todo toString <br>
	 * M�todos a probar: <br>
	 * toString
	 */
	public void testToString( )
	{
	    setupEscenario1( );
	    assertEquals( "No retorna el string correcto", "Pizza 4x3 (12345)", cupon.toString( ) );
	}
	
}