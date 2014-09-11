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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.*;
import uniandes.cupi2.cupones.mundo.*;

/**
 * Clase usada para verificar la correcta implementación de SistemaCupones
 */
public class SistemaCuponesTest extends TestCase
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Ruta donde se encuentran los archivos de prueba
     */
    private final static String RUTA_PERSISTENCIA = "./test/data/";

    /**
     * Ruta con el archivo de prueba para la importación de información
     */
    private final static String NOMBRE_IMPORTACION = "importacion_prueba.txt";

    /**
     * Nombre del archivo de prueba para la exportación de información
     */
    private final static String NOMBRE_EXPORTACION = "exportacion_prueba.txt";

    /**
     * Ruta con el archivo de prueba para la persistencia (cargar estado) de la aplicación
     */
    private final static String RUTA_ESTADO1 = "./test/data/estado1_test.data";

    /**
     * Ruta con el archivo de prueba para la persistencia (guardar estado) de la aplicación
     */
    private final static String RUTA_ESTADO2 = "./test/data/estado2_test.data";
    
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * SistemaCupones usado para las pruebas
     */
    private SistemaCupones sistemaCupones;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario que crea un sistema de cupones cargando un estado del mundo
     */
    public void setupEscenario1( )
    {
        try
        {
            sistemaCupones = new SistemaCupones( RUTA_ESTADO1 );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería generar excepción :" + e.getMessage( ) );
        }
    }

    /**
     * Escenario que crea un sistema de cupones vacío
     */
    public void setupEscenario2( )
    {
        try
        {
            sistemaCupones = new SistemaCupones( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería generar excepción :" + e.getMessage( ) );
        }
    }

    /**
     * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * darCiudades, darUsuarios <br>
     * Caso de Prueba: <br>
     * Crea un nuevo SistemaCupones a partir de la información en el archivo
     */
    public void testSistemaCupones1( )
    {
        setupEscenario1( );
        ArrayList ciudades = sistemaCupones.darCiudades( );
        ArrayList usuarios = sistemaCupones.darUsuarios( );
        assertFalse( "No se cargaron las ciudades", ciudades.isEmpty( ) );
        assertFalse( "No se cargaron los usuarios", usuarios.isEmpty( ) );
        try
        {
            for( int i = 0; i < 5; i++ )
            {
                Ciudad ciudad = ( Ciudad )ciudades.get( i );
                ArrayList cupones = ciudad.darCupones( );
                assertEquals( "No cargó la ciudad correctamente", "Ciudad" + i, ciudad.darNombre( ) );
                assertEquals( "No cargó la ciudad correctamente", "Departamento" + i, ciudad.darDepartamento( ) );
                assertEquals( "No cargó la ciudad correctamente", i, cupones.size( ) );
                for( int j = 0; j < i; j++ )
                {
                    Cupon cupon = ( Cupon )cupones.get( j );
                    assertEquals( "No cargó  el cupón correctamente", "Cupon" + j, cupon.darNombre( ) );
                }
            }

            for( int i = 0; i < 5; i++ )
            {
                Usuario usuario = ( Usuario )usuarios.get( i );
                assertEquals( "No cargó el usuario correctamente", "Usuario" + i, usuario.darId( ) );
                assertEquals( "No cargó el usuario correctamente", "Nombre" + i, usuario.darNombre( ) );
                assertEquals( "No cargó el usuario correctamente", "Apellido" + i, usuario.darApellido( ) );
                assertEquals( "No cargó el usuario correctamente", "Email" + i + "@uniandes.edu.co", usuario.darEmail( ) );
                assertEquals( "No cargó el usuario correctamente", "12345678" + i, usuario.darTelefono( ) );
                assertEquals( "No cargo el usuario correctamente", "Ciudad" + i, usuario.darCiudadResidencia( ) );                
            }
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 2 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * darCiudades, darUsuarios, darUsuarioActual, darCiudadActual<br>
     * Caso de Prueba: <br>
     * Crea un nuevo SistemaCupones vacío
     */
    public void testSistemaCupones2( )
    {
        setupEscenario2( );
        assertNotNull( "No se inicializó la lista de ciudades", sistemaCupones.darCiudades( ) );
        assertNotNull( "No se inicializó la lista de usuarios", sistemaCupones.darUsuarios( ) );
        assertEquals( "No inicializó el usuario actual correctamente", null, sistemaCupones.darUsuarioActual( ) );
        assertEquals( "No inicializó elal ciudad actual correctamente", null, sistemaCupones.darCiudadActual( ) );
    }

    /**
     * Prueba 3 - Prueba el método guardarEstado <br>
     * Métodos a probar: <br>
     * guardarEstado, agregarCiudad, agregarCupon, registrarUsuario, cargarEstado, darCiudad, darUsuarios
     */
    public void testGuardarEstado( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Descuento ropa", "15% de descuento en toda nuestra ropa", 50, 1000, "Válido hasta el 31/12/2011" );
            sistemaCupones.agregarCupon( "Medias gratis", "Compre 2 camisetas y lleve ", 100, 1000, "Sólo aplica para camisetas de adulto" );
            sistemaCupones.agregarCiudad( "Bucaramanga", "Santander" );
            sistemaCupones.registrarUsuario( "12345", "Rafael", "Martinez", "rafael@uniandes.edu.co", "612354685", "Bogotá" );
            sistemaCupones.registrarUsuario( "54321", "Maria", "Gonzalez", "maria_gonzalez@uniandes.edu.co", "76513215", "Girardot" );
            sistemaCupones.guardarEstado( RUTA_ESTADO2 );

            sistemaCupones.cargarEstado( new File( RUTA_ESTADO2 ) );
            assertFalse( "No guardó las ciudades correctamente", sistemaCupones.darCiudades( ).isEmpty( ) );
            assertEquals( "No guardó las ciudades correctamente", 2, sistemaCupones.darCiudades( ).size( ) );

            // Verifica que guardó correctamente la primera ciudad
            Ciudad ciudad = sistemaCupones.darCiudad( 0 );
            assertEquals( "No guardó la ciudad correctamente", "Bogotá", ciudad.darNombre( ) );
            assertEquals( "No guardó la ciudad correctamente", "Cundinamarca", ciudad.darDepartamento( ) );

            // Verifica que guardó correctamente los cupones de la primera ciudad
            ArrayList cupones = ciudad.darCupones( );
            assertNotNull( "No guardó la ciudad correctamente", cupones );
            assertEquals( "No guardó los cupones correctamente", 2, cupones.size( ) );
            assertEquals( "No guardó los cupones correctamente", "Descuento ropa", ( ( Cupon )cupones.get( 0 ) ).darNombre( ) );
            assertEquals( "No guardó los cupones correctamente", "Medias gratis", ( ( Cupon )cupones.get( 1 ) ).darNombre( ) );

            // Verifica que guardó correctamente la segunda ciudad
            ciudad = sistemaCupones.darCiudad( 1 );
            assertEquals( "No guardó la ciudad correctamente", "Bucaramanga", ciudad.darNombre( ) );
            assertEquals( "No guardó la ciudad correctamente", "Santander", ciudad.darDepartamento( ) );

            // Verifica que guardó correctamente los cupones de la segunda ciudad
            cupones = ciudad.darCupones( );
            assertNotNull( "No guardó la ciudad correctamente", cupones );
            assertEquals( "No guardó los cupones correctamente", 0, cupones.size( ) );

            // Verifica que guardó los usuarios correctamente
            assertNotNull( "No guardó los usuarios correctamente", sistemaCupones.darUsuarios( ) );
            assertEquals( "No guardó los usuarios correctamente", 2, sistemaCupones.darUsuarios( ).size( ) );
            assertEquals( "No guardó el usuario correctamente", "12345", ( ( Usuario )sistemaCupones.darUsuarios( ).get( 0 ) ).darId( ) );
            assertEquals( "No guardó el usuario correctamente", "54321", ( ( Usuario )sistemaCupones.darUsuarios( ).get( 1 ) ).darId( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
        catch( DatosNoValidosException e )
        {
            fail( "No debería generar error" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 4 - Prueba el método guardarCuponesArchivo <br>
     * Métodos a probar: <br>
     * guardarCuponesArchivo, agregarCiudad, agregarCupon
     */
    public void testGuardarCuponesArchivo( )
    {
        setupEscenario2( );
        try
        {
            for( int i = 0; i < 5; i++ )
            {
                sistemaCupones.agregarCiudad( "Ciudad" + i, "Departamento" + i );
                for( int j = 0; j < i; j++ )
                {
                    sistemaCupones.agregarCupon( "Nombre" + j, "Descripción" + j, j, j, "Condiciones" + j );
                }
            }
            sistemaCupones.guardarCuponesArchivo( NOMBRE_EXPORTACION, RUTA_PERSISTENCIA );
            File nuevoArchivo = new File( RUTA_PERSISTENCIA + NOMBRE_EXPORTACION );
            BufferedReader br = new BufferedReader( new FileReader( nuevoArchivo ) );
            String linea = br.readLine( );

            for( int i = 0; i < 5; i++ )
            {
                assertEquals( "No guardó la información de la ciudad correctamente", "Ciudad: Ciudad" + i + ";Departamento" + i, linea );
                linea = br.readLine( );
                for( int j = 0; j < i; j++ )
                {
                    assertTrue( "No guardó la información del cupón correctamente", linea.startsWith( "Cupon;" ) );
                    assertTrue( "No guardó la información del cupón correctamente", linea.endsWith( "Nombre" + j + ";" + j + ".0;" + j + ";Condiciones" + j + ";Descripción" + j ) );
                    linea = br.readLine( );
                }
            }
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            fail( "No debería generar error" );
        }
        catch( IOException e )
        {
            fail( "No debería generar error" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 5 - Prueba el método importarCuponesDeArchivo <br>
     * Métodos a probar: <br>
     * importarCuponesDeArchivo, darCiudades
     */
    public void testImportarCuponesDeArchivo( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.importarCuponesDeArchivo( RUTA_PERSISTENCIA + NOMBRE_IMPORTACION );
            ArrayList ciudades = sistemaCupones.darCiudades( );
            assertEquals( "No importó las ciudades correctamente", 3, ciudades.size( ) );

            Ciudad ciudad = ( Ciudad )ciudades.get( 0 );
            assertEquals( "No importó la ciudad correctamente", "Bogota", ciudad.darNombre( ) );
            assertEquals( "No importó la ciuad correctamente", "Cundinamarca", ciudad.darDepartamento( ) );

            ArrayList cupones = ciudad.darCupones( );
            assertEquals( "No importó los cupones correctamente", 2, cupones.size( ) );

            Cupon cupon = ( Cupon )cupones.get( 0 );
            assertEquals( "No importó el cupón correctamente", "Pizza Gratis", cupon.darNombre( ) );
            assertEquals( "No importó el cupón correctamente", 2000.0, cupon.darPrecio( ) );
            assertEquals( "No importó el cupón correctamnete", 50, cupon.darCantidadDisponible( ) );
            assertEquals( "No importó el cupón correctamente", "Aplica antes de 31/08/2012", cupon.darCondiciones( ) );
            assertEquals( "No importó el cupón correctamente", "Pague 3 y lleve 4", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 1 );
            assertEquals( "No importó el cupón correctamente", "Gran Comilona", cupon.darNombre( ) );
            assertEquals( "No importó el cupón correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No importó el cupón correctamnete", 30, cupon.darCantidadDisponible( ) );
            assertEquals( "No importó el cupón correctamente", "No aplica para postres ni entradas. Válido hasta el 31/11/2012", cupon.darCondiciones( ) );
            assertEquals( "No importó el cupón correctamente", "Barra libre de todos nuestros platos", cupon.darDescripcion( ) );

            ciudad = ( Ciudad )ciudades.get( 1 );
            assertEquals( "No importó la ciudad correctamente", "Medellin", ciudad.darNombre( ) );
            assertEquals( "No importó la ciuad correctamente", "Antioquia", ciudad.darDepartamento( ) );
            cupones = ciudad.darCupones( );
            assertEquals( "No importó los cupones correctamente", 3, cupones.size( ) );

            cupon = ( Cupon )cupones.get( 0 );
            assertEquals( "No importó el cupón correctamente", "Museo 2 x 1", cupon.darNombre( ) );
            assertEquals( "No importó el cupón correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No importó el cupón correctamnete", 100, cupon.darCantidadDisponible( ) );
            assertEquals( "No importó el cupón correctamente", "Cupón válido antes de las 11:00 am", cupon.darCondiciones( ) );
            assertEquals( "No importó el cupón correctamente", "Pague su entrada y lleve a un acompañante gratis", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 1 );
            assertEquals( "No importó el cupón correctamente", "Descuento ropa", cupon.darNombre( ) );
            assertEquals( "No importó el cupón correctamente", 1500.0, cupon.darPrecio( ) );
            assertEquals( "No importó el cupón correctamnete", 40, cupon.darCantidadDisponible( ) );
            assertEquals( "No importó el cupón correctamente", "Válido hasta el 31/12/2011", cupon.darCondiciones( ) );
            assertEquals( "No importó el cupón correctamente", "15% de descuento en toda nuestra ropa", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 2 );

            assertEquals( "No importó el cupón correctamente", "Medias gratis", cupon.darNombre( ) );
            assertEquals( "No importó el cupón correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No importó el cupón correctamnete", 100, cupon.darCantidadDisponible( ) );
            assertEquals( "No importó el cupón correctamente", "Sólo aplica para camisetas de adulto", cupon.darCondiciones( ) );
            assertEquals( "No importó el cupón correctamente", "Compre 2 camisetas y lleve", cupon.darDescripcion( ) );

            ciudad = ( Ciudad )ciudades.get( 2 );
            assertEquals( "No importó la ciudad correctamente", "Bucaramanga", ciudad.darNombre( ) );
            assertEquals( "No importó la ciuad correctamente", "Santander", ciudad.darDepartamento( ) );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 6 - Prueba el método cambiarCiudadActual <br>
     * Métodos a probar: <br>
     * cambiarCiudadActual, darCiudadActual
     */
    public void testCambiarCiudadActual( )
    {
        setupEscenario1( );
        sistemaCupones.cambiarCiudadActual( 3 );
        assertEquals( "No cambió la ciudad actual correctamente", "Ciudad3", sistemaCupones.darCiudadActual( ).darNombre( ) );

        sistemaCupones.cambiarCiudadActual( 5 );
        assertNull( "No cambió la ciudad actual correctamente", sistemaCupones.darCiudadActual( ) );

        sistemaCupones.cambiarCiudadActual( -1 );
        assertNull( "No cambió la ciudad actual correctamente", sistemaCupones.darCiudadActual( ) );

    }

    /**
     * Prueba 7 - Prueba el método buscarCiudadPorNombreYDepartamento <br>
     * Métodos a probar: <br>
     * buscarCiudadPorNombreYDepartamento, agregarCiudad
     */
    public void testBuscarCiudadPorNombreYDepartamento( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.agregarCiudad( "Barbosa", "Santander" );
            sistemaCupones.agregarCiudad( "Barbosa", "Antioquia" );
            assertEquals( "No busca la ciudad correctamente", 6, sistemaCupones.buscarCiudadPorNombreYDepartamento( "Barbosa", "Antioquia" ) );
            assertEquals( "No busca la ciudad correctamente", 5, sistemaCupones.buscarCiudadPorNombreYDepartamento( "Barbosa", "Santander" ) );
            assertEquals( "No busca la ciudad correctamente", -1, sistemaCupones.buscarCiudadPorNombreYDepartamento( "Barbosa", "Cundinamarca" ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 8 - Prueba el método darCiudad <br>
     * Métodos a probar: <br>
     * darCiudad
     */
    public void testDarCiudad( )
    {
        setupEscenario1( );
        assertEquals( "No retornó la ciudad correcta", "Ciudad3", sistemaCupones.darCiudad( 3 ).darNombre( ) );
        assertNull( "No debería retornar una ciudad", sistemaCupones.darCiudad( 8 ) );
        assertNull( "No debería retornar una ciudad", sistemaCupones.darCiudad( -1 ) );
    }

    /**
     * Prueba 9 - Prueba el método agregarCiudad <br>
     * Métodos a probar: <br>
     * agregarCiudad, darCiudadActual<br>
     * Caso de prueba: <br>
     * Se agrega la ciudad exitosamente
     */
    public void testAgregarCiudad1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            assertEquals( "La ciudad actual no es la esperada", "Bogotá", sistemaCupones.darCiudadActual( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 10 - Prueba el método agregarCiudad <br>
     * Métodos a probar: <br>
     * agregarCiudad<br>
     * Caso de prueba: <br>
     * Ya existe la ciudad en el sistema
     */
    public void testAgregarCiudad2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.agregarCiudad( "Ciudad0", "Departamento0" );
            fail( "Debería generar error" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 11 - Prueba el método agregarCupon <br>
     * Métodos a probar: <br>
     * agregarCupon, darCiudadActual
     */
    public void testAgregarCupon( )
    {
        setupEscenario1( );
        sistemaCupones.agregarCupon( "nCupon", "nDescripcion", 5, 5.0, "nCondiciones" );
        assertNotNull( "No se agregó el cupón correctamente", sistemaCupones.darCiudadActual( ) );
        assertEquals( "No agregó el cupón correctamente", "nCupon", sistemaCupones.darCiudadActual( ).darCuponActual( ).darNombre( ) );
    }

    /**
     * Prueba 12 - Prueba el método redimirCupon<br>
     * Métodos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion, darUsuarioActual<br>
     * Caso de prueba <br>
     * Se redime el cupón exitosamente
     */
    public void testRedimirCupon1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del día de 7:00 pm a 11:00pm", 5, 10000, "Válido sólo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            sistemaCupones.agregarCupon( "Tarde de Spa 2x1", "Vaya a una relajante tarde de spa con otra persona por el precio de una.", 20, 10000, "Sólo para mayores de 15 años" );
            sistemaCupones.redimirCupon( );
            ArrayList cupones = sistemaCupones.darUsuarioActual( ).darCuponesRedimidos( );
            assertEquals( "No agregó el cupón correctamente", "Barra libre de cerveza", ( ( Cupon )cupones.get( 0 ) ).darNombre( ) );
            assertEquals( "No agregó el cupón correctamente", "Tarde de Spa 2x1", ( ( Cupon )cupones.get( 1 ) ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
        catch( CuponAgotadoException e )
        {
            fail( "No debería generar error" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 13 - Prueba el método redimirCupon<br>
     * Métodos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion<br>
     * Caso de prueba <br>
     * El cupón ya está agotado
     */
    public void testRedimirCupon2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del día de 7:00 pm a 11:00pm", 0, 10000, "Válido sólo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            fail( "Debería generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
        catch( CuponAgotadoException e )
        {
            // Debe pasar por aquí
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar error" );
        }
    }

    /**
     * Prueba 14 - Prueba el método redimirCupon<br>
     * Métodos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion<br>
     * Caso de prueba <br>
     * El usuario ya redimió el cupón
     */
    public void testRedimirCupon3( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del día de 7:00 pm a 11:00pm", 5, 10000, "Válido sólo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            sistemaCupones.redimirCupon( );
            fail( "Debería generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar error" );
        }
        catch( CuponAgotadoException e )
        {
            fail( "No debería generar error" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 15 - Prueba el método darCuponActual<br>
     * Métodos a probar:<br>
     * darCuponActual, agregarCiudad, agregarCupon
     */
    public void testDarCuponActual( )
    {
        setupEscenario2( );
        try
        {
            assertEquals( "No debería existir un cupón actual", null, sistemaCupones.darCuponActual( ) );
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            assertEquals( "No retornó el cupón correctamente", "Cupon1", sistemaCupones.darCuponActual( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            assertEquals( "No retornó el cupón correctamente", "Cupon2", sistemaCupones.darCuponActual( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            assertEquals( "No retornó el cupón correctamente", "Cupon3", sistemaCupones.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 16 - Prueba el método darCuponAnterior<br>
     * Métodos a probar: <br>
     * darCuponAnterior, agregarCiudad, agregarCupon<br>
     * Caso de prueba: <br>
     * Retorna el cupón anterior exitosamente
     */
    public void testDarCuponAnterior1( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            assertEquals( "No retornó el cupón correctamente", "Cupon2", sistemaCupones.darCuponAnterior( ).darNombre( ) );
            assertEquals( "No retornó el cupón correctamente", "Cupon1", sistemaCupones.darCuponAnterior( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 17 - Prueba el método darCuponAnterior<br>
     * Métodos a probar: <br>
     * darCuponAnterior, agregarCiudad, agregarCupon<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el primer cupón
     */
    public void testDarCuponAnterior2( )
    {

        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.darCuponAnterior( ).darNombre( );
            fail( "Debería generar excepción" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 18 - Prueba el método darCuponSiguiente<br>
     * Métodos a probar: <br>
     * darCuponSiguiente, agregarCiudad, agregarCupon, darCuponAnterior<br>
     * Caso de prueba: <br>
     * Retorna el cupón siguiente exitosamente
     */
    public void testDarCuponSiguiente1( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            sistemaCupones.darCuponAnterior( );
            assertEquals( "No retornó el cupón correctamente", "Cupon2", sistemaCupones.darCuponSiguiente( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            sistemaCupones.darCuponAnterior( );
            assertEquals( "No retornó el cupón correctamente", "Cupon3", sistemaCupones.darCuponSiguiente( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 19 - Prueba el método darCuponSiguiente<br>
     * Métodos a probar: <br>
     * darCuponSiguiente, agregarCiudad, agregarCupon <br>
     * Caso de prueba: <br>
     * Ya se encuentra en el último cupón
     */
    public void testDarCuponSiguiente2( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogotá", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );

            sistemaCupones.darCuponSiguiente( ).darNombre( );
            fail( "Debería generar excepción" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 20 - Prueba el método buscarUsuario<br>
     * Métodos a probar: <br>
     * buscarUsuario
     */
    public void testBuscarUsuario( )
    {
        setupEscenario1( );
        Usuario usuario = sistemaCupones.buscarUsuario( "Usuario3" );
        assertNotNull( "No encontró un usuario existente", usuario );
        assertEquals( "No encontró el usuario correcto", "Nombre3", usuario.darNombre( ) );

        usuario = sistemaCupones.buscarUsuario( "Usuario4" );
        assertNotNull( "No encontró un usuario existente", usuario );
        assertEquals( "No encontró el usuario correcto", "Nombre4", usuario.darNombre( ) );

        usuario = sistemaCupones.buscarUsuario( "Usuario6" );
        assertNull( "Encontró un usuario no existente", usuario );
    }

    /**
     * Prueba 21- Prueba el método registrarUsuario<br>
     * Métodos a probar: <br>
     * registrarUsuario, darUsuarioActual, buscarUsuario<br>
     * Caso de prueba: <br>
     * Se registra el usuario exitosamente
     */
    public void testRegistrarUsuario1( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.registrarUsuario( "1956213516", "Rafael", "Manrique", "rafaelmanrique@cupi2.uniandes.edu.co", "12345678", "Calle 1 no 5-21" );
            assertEquals( "No registró al usuario correctamente", "1956213516", sistemaCupones.darUsuarioActual( ).darId( ) );
            assertNotNull( "No registró al usuario correctamente", sistemaCupones.buscarUsuario( "1956213516" ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( DatosNoValidosException e )
        {
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 22- Prueba el método registrarUsuario<br>
     * Métodos a probar: <br>
     * registrarUsuario<br>
     * Caso de prueba: <br>
     * El usuario ya se encuentra registrado
     */
    public void testRegistrarUsuario2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.registrarUsuario( "Usuario1", "Rafael", "Manrique", "rafaelmanrique@cupi2.uniandes.edu.co", "12345678", "Calle 1 no 5-21" );
            fail( "Debería generar excepción" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aquí
        }
        catch( DatosNoValidosException e )
        {
            fail( "No debería generar excepción" );
        }
    }

    /**
     * Prueba 23- Prueba el método registrarUsuario<br>
     * Métodos a probar: <br>
     * registrarUsuario<br>
     * Caso de prueba: <br>
     * El correo ingresado no es válido
     */
    public void testRegistrarUsuario3( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.registrarUsuario( "1956213516", "Rafael", "Manrique", "rafaelmanriquecupi2.uniandes.edu.co", "12345678", "Calle 1 no 5-21" );
            fail( "Debería generar excepción" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( DatosNoValidosException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 24- Prueba el método registrarUsuario<br>
     * Métodos a probar: <br>
     * registrarUsuario<br>
     * Caso de prueba: <br>
     * El teléfono ingresado no es válido
     */
    public void testRegistrarUsuario4( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.registrarUsuario( "1956213516", "Rafael", "Manrique", "rafaelmanrique@cupi2.uniandes.edu.co", "123a", "Calle 1 no 5-21" );
            fail( "Debería generar excepción" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería generar excepción" );
        }
        catch( DatosNoValidosException e )
        {
            // Debe pasar por aquí
        }
    }

    /**
     * Prueba 25 - Prueba el método iniciarSesion<br>
     * Métodos a probar: <br>
     * iniciarSesion, darUsuarioActual<br>
     * Caso de prueba: <br>
     * Se inició la sesión exitosamente
     */
    public void testIniciarSesion1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.iniciarSesion( "Usuario2" );
            assertEquals( "No inició la sesión correctamente", "Usuario2", sistemaCupones.darUsuarioActual( ).darId( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debería generar excepción" );
        }

    }

    /**
     * Prueba 26 - Prueba el método iniciarSesion<br>
     * Métodos a probar: <br>
     * iniciarSesion<br>
     * Caso de prueba: <br>
     * El usuario no está registrado
     */
    public void testIniciarSesion2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.iniciarSesion( "Usuario27" );
            fail( "No debería generar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debería pasar por aquí
        }
    }
}