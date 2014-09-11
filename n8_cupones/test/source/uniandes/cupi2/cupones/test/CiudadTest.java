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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.*;
import uniandes.cupi2.cupones.mundo.*;

/**
 * Clase usada para verificar la correcta implementaci�n de Ciudad
 */
public class CiudadTest extends TestCase
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Prefijo de la ruta donde est�n los archivos de prueba
     */
    public static final String RUTA_ARCHIVO = "test/data/";

    /**
     * Nombre del archivo de prueba
     */
    public static final String NOMBRE_ARCHIVO = "pruebaCiudad.txt";

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * La ciudad donde se har�n las pruebas
     */
    private Ciudad ciudad;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Crea una nueva ciudad sin cupones
     */
    public void setupEscenario1( )
    {
        ciudad = new Ciudad( "Bogot�", "Cundinamarca" );
    }

    /**
     * Crea una nueva ciudad con cupones
     */
    public void setupEscenario2( )
    {
        ciudad = new Ciudad( "Medellin", "Antioquia" );
        for( int i = 0; i < 5; i++ )
        {
            ciudad.agregarCupon( "Promocion" + i, "Compre un combo hamburguesa y reciba un combo infantil", i + 1, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
        }
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * Ciudad, darNombre, darDepartamento, darCupones
     */
    public void testCiudad( )
    {
        setupEscenario1( );
        assertEquals( "No inicializ� el nombre correctamente", "Bogot�", ciudad.darNombre( ) );
        assertEquals( "No inicializ� el nombre del departamento correctamente", "Cundinamarca", ciudad.darDepartamento( ) );
        assertNotNull( "No inicializ� la lista de cupones", ciudad.darCupones( ) );
    }

    /**
     * Prueba 2 - Prueba el m�todo agregarCupon <br>
     * M�todos a probar: <br>
     * agregarCupon, darCupones, darCuponActual
     */
    public void testAgregarCupon( )
    {
        setupEscenario1( );
        ciudad.agregarCupon( "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", 2, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
        assertEquals( "No agreg� el cup�n correctamente", 1, ciudad.darCupones( ).size( ) );
        assertEquals( "No agreg� el cup�n correctamente", "Promocion hamburguesas", ciudad.darCuponActual( ).darNombre( ) );
        assertTrue( "No agreg� el cup�n correctamente", ciudad.darCuponActual( ).darId( ).startsWith( ciudad.darNombre( ).substring( 0, 2 ) + ciudad.darDepartamento( ).substring( 0, 2 ) ) );
    }

    /**
     * Prueba 3 - Prueba el m�todo cambiarCuponActual <br>
     * M�todos a probar: <br>
     * cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Cambia el cup�n actual exitosamente
     */
    public void testCambiarCuponActual1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 3 );
            assertEquals( "No cambi� el cup�n actual correctamente", "Promocion3", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.cambiarCuponActual( 1 );
            assertEquals( "No cambi� el cup�n actual correctamente", "Promocion1", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 4 - Prueba el m�todo cambiarCuponActual <br>
     * M�todos a probar: <br>
     * cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * El cup�n ingresado no existe
     */
    public void testCambiarCuponActual2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( -1 );
            fail( "Deber�a generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
        try
        {
            ciudad.cambiarCuponActual( 8 );
            fail( "Deber�a generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 5 - Prueba el m�todo darCuponAnterior <br>
     * M�todos a probar: <br>
     * darCuponAnterior, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Existe un cup�n anterior
     */
    public void testDarCuponAnterior1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 3 );
            ciudad.darCuponAnterior( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion2", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponAnterior( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion1", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponAnterior( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion0", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 6 - Prueba el m�todo darCuponAnterior <br>
     * M�todos a probar: <br>
     * darCuponAnterior, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el primer cup�n
     */
    public void testDarCuponAnterior2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 0 );
            ciudad.darCuponAnterior( );
            fail( "Deber�a generar error" );

        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }
    
    /**
     * Prueba 7 - Prueba el m�todo darCuponSiguiente <br>
     * M�todos a probar: <br>
     * darCuponSiguiente, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Existe un cup�n anterior
     */
    public void testDarCuponSiguiente1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 1 );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion2", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion3", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retorn� el cup�n esperado", "Promocion4", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 8 - Prueba el m�todo darCuponSiguiente <br>
     * M�todos a probar: <br>
     * darCuponSiguiente, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el �ltimo cup�n
     */
    public void testDarCuponSiguiente2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 4 );
            ciudad.darCuponSiguiente( );
            fail( "Deber�a generar error" );

        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 9 - Prueba el m�todo buscarCupon <br>
     * M�todos a probar: <br>
     * buscarCupon, darCupones<br>
     */
    public void testBuscarCupon( )
    {
        setupEscenario2( );
        String id = ( ( Cupon )ciudad.darCupones( ).get( 3 ) ).darId( );
        assertEquals( "No retorn� el cup�n esperado", "Promocion3", ciudad.buscarCupon( id ).darNombre( ) );
        id = ( ( Cupon )ciudad.darCupones( ).get( 0 ) ).darId( );
        assertEquals( "No retorn� el cup�n esperado", "Promocion0", ciudad.buscarCupon( id ).darNombre( ) );
        assertNull( "No retorn� el cup�n esperado", ciudad.buscarCupon( "idNoExistente" ) );
    }

    /**
     * Prueba 10 - Prueba el m�todo esCiudadBuscada <br>
     * M�todos a probar: <br>
     * esCiudadBuscada
     */
    public void testEsCiudadBuscada( )
    {
        setupEscenario1( );
        assertFalse( "No hace la comparaci�n correctamente", ciudad.esCiudadBuscada( "Bogot�", "Antioquia" ) );
        assertFalse( "No hace la comparaci�n correctamente", ciudad.esCiudadBuscada( "Medell�n", "Cundinamarca" ) );
        assertTrue( "No hace la comparaci�n correctamente", ciudad.esCiudadBuscada( "Bogot�", "Cundinamarca" ) );
    }

    /**
     * Prueba 11 - Prueba el m�todo guardarCuponesArchivo <br>
     * M�todos a probar: <br>
     * guardarCuponesArchivo
     */
    public void testGuardarCuponesArchivo( )
    {
        setupEscenario2( );
        try
        {
            File ruta = new File( RUTA_ARCHIVO );
            if( !ruta.exists( ) )
            {
                ruta.mkdir( );
            }
            File archivo = new File( RUTA_ARCHIVO, NOMBRE_ARCHIVO );
            PrintWriter pw;
            pw = new PrintWriter( archivo );

            ciudad.guardarCuponesArchivo( pw );
            pw.close( );

            File nuevoArchivo = new File( RUTA_ARCHIVO + NOMBRE_ARCHIVO );

            BufferedReader br = new BufferedReader( new FileReader( nuevoArchivo ) );
            String linea = br.readLine( );
            for( int i = 0; i < 5; i++ )
            {
                assertEquals( "No guard� la l�nea correctamente", "Cupon;Promocion" + i + ";3500.0;" + ( i + 1 ) + ";Aplica s�lo los martes de 2:00pm a 4:00pm;Compre un combo hamburguesa y reciba un combo infantil", linea );
                linea = br.readLine( );
            }
            br.close( );

        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( IOException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 12 - Prueba el m�todo toString <br>
     * M�todos a probar: <br>
     * toString
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertEquals( "No retorna el string esperado", "Bogot�, Cundinamarca", ciudad.toString( ) );
    }
}