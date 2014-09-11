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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.*;
import uniandes.cupi2.cupones.mundo.*;

/**
 * Clase usada para verificar la correcta implementación de Ciudad
 */
public class CiudadTest extends TestCase
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Prefijo de la ruta donde están los archivos de prueba
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
     * La ciudad donde se harán las pruebas
     */
    private Ciudad ciudad;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Crea una nueva ciudad sin cupones
     */
    public void setupEscenario1( )
    {
        ciudad = new Ciudad( "Bogotá", "Cundinamarca" );
    }

    /**
     * Crea una nueva ciudad con cupones
     */
    public void setupEscenario2( )
    {
        ciudad = new Ciudad( "Medellin", "Antioquia" );
        for( int i = 0; i < 5; i++ )
        {
            ciudad.agregarCupon( "Promocion" + i, "Compre un combo hamburguesa y reciba un combo infantil", i + 1, 3500, "Aplica sólo los martes de 2:00pm a 4:00pm" );
        }
    }

    /**
     * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * Ciudad, darNombre, darDepartamento, darCupones
     */
    public void testCiudad( )
    {
        setupEscenario1( );
        assertEquals( "No inicializó el nombre correctamente", "Bogotá", ciudad.darNombre( ) );
        assertEquals( "No inicializó el nombre del departamento correctamente", "Cundinamarca", ciudad.darDepartamento( ) );
        assertNotNull( "No inicializó la lista de cupones", ciudad.darCupones( ) );
    }

    /**
     * Prueba 2 - Prueba el método agregarCupon <br>
     * Métodos a probar: <br>
     * agregarCupon, darCupones, darCuponActual
     */
    public void testAgregarCupon( )
    {
        setupEscenario1( );
        ciudad.agregarCupon( "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", 2, 3500, "Aplica sólo los martes de 2:00pm a 4:00pm" );
        assertEquals( "No agregó el cupón correctamente", 1, ciudad.darCupones( ).size( ) );
        assertEquals( "No agregó el cupón correctamente", "Promocion hamburguesas", ciudad.darCuponActual( ).darNombre( ) );
        assertTrue( "No agregó el cupón correctamente", ciudad.darCuponActual( ).darId( ).startsWith( ciudad.darNombre( ).substring( 0, 2 ) + ciudad.darDepartamento( ).substring( 0, 2 ) ) );
    }

    /**
     * Prueba 3 - Prueba el método cambiarCuponActual <br>
     * Métodos a probar: <br>
     * cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Cambia el cupón actual exitosamente
     */
    public void testCambiarCuponActual1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 3 );
            assertEquals( "No cambió el cupón actual correctamente", "Promocion3", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.cambiarCuponActual( 1 );
            assertEquals( "No cambió el cupón actual correctamente", "Promocion1", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 4 - Prueba el método cambiarCuponActual <br>
     * Métodos a probar: <br>
     * cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * El cupón ingresado no existe
     */
    public void testCambiarCuponActual2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( -1 );
            fail( "Debería generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
        try
        {
            ciudad.cambiarCuponActual( 8 );
            fail( "Debería generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 5 - Prueba el método darCuponAnterior <br>
     * Métodos a probar: <br>
     * darCuponAnterior, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Existe un cupón anterior
     */
    public void testDarCuponAnterior1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 3 );
            ciudad.darCuponAnterior( );
            assertEquals( "No retornó el cupón esperado", "Promocion2", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponAnterior( );
            assertEquals( "No retornó el cupón esperado", "Promocion1", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponAnterior( );
            assertEquals( "No retornó el cupón esperado", "Promocion0", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 6 - Prueba el método darCuponAnterior <br>
     * Métodos a probar: <br>
     * darCuponAnterior, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el primer cupón
     */
    public void testDarCuponAnterior2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 0 );
            ciudad.darCuponAnterior( );
            fail( "Debería generar error" );

        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
    }
    
    /**
     * Prueba 7 - Prueba el método darCuponSiguiente <br>
     * Métodos a probar: <br>
     * darCuponSiguiente, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Existe un cupón anterior
     */
    public void testDarCuponSiguiente1( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 1 );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retornó el cupón esperado", "Promocion2", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retornó el cupón esperado", "Promocion3", ciudad.darCuponActual( ).darNombre( ) );
            ciudad.darCuponSiguiente( );
            assertEquals( "No retornó el cupón esperado", "Promocion4", ciudad.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 8 - Prueba el método darCuponSiguiente <br>
     * Métodos a probar: <br>
     * darCuponSiguiente, cambiarCuponActual, darCuponActual<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el último cupón
     */
    public void testDarCuponSiguiente2( )
    {
        setupEscenario2( );
        try
        {
            ciudad.cambiarCuponActual( 4 );
            ciudad.darCuponSiguiente( );
            fail( "Debería generar error" );

        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 9 - Prueba el método buscarCupon <br>
     * Métodos a probar: <br>
     * buscarCupon, darCupones<br>
     */
    public void testBuscarCupon( )
    {
        setupEscenario2( );
        String id = ( ( Cupon )ciudad.darCupones( ).get( 3 ) ).darId( );
        assertEquals( "No retornó el cupón esperado", "Promocion3", ciudad.buscarCupon( id ).darNombre( ) );
        id = ( ( Cupon )ciudad.darCupones( ).get( 0 ) ).darId( );
        assertEquals( "No retornó el cupón esperado", "Promocion0", ciudad.buscarCupon( id ).darNombre( ) );
        assertNull( "No retornó el cupón esperado", ciudad.buscarCupon( "idNoExistente" ) );
    }

    /**
     * Prueba 10 - Prueba el método esCiudadBuscada <br>
     * Métodos a probar: <br>
     * esCiudadBuscada
     */
    public void testEsCiudadBuscada( )
    {
        setupEscenario1( );
        assertFalse( "No hace la comparación correctamente", ciudad.esCiudadBuscada( "Bogotá", "Antioquia" ) );
        assertFalse( "No hace la comparación correctamente", ciudad.esCiudadBuscada( "Medellín", "Cundinamarca" ) );
        assertTrue( "No hace la comparación correctamente", ciudad.esCiudadBuscada( "Bogotá", "Cundinamarca" ) );
    }

    /**
     * Prueba 11 - Prueba el método guardarCuponesArchivo <br>
     * Métodos a probar: <br>
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
                assertEquals( "No guardó la línea correctamente", "Cupon;Promocion" + i + ";3500.0;" + ( i + 1 ) + ";Aplica sólo los martes de 2:00pm a 4:00pm;Compre un combo hamburguesa y reciba un combo infantil", linea );
                linea = br.readLine( );
            }
            br.close( );

        }
        catch( FileNotFoundException e )
        {
            fail( "No debería generar error" );
        }
        catch( IOException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 12 - Prueba el método toString <br>
     * Métodos a probar: <br>
     * toString
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertEquals( "No retorna el string esperado", "Bogotá, Cundinamarca", ciudad.toString( ) );
    }
}