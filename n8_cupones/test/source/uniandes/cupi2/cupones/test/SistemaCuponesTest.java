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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.*;
import uniandes.cupi2.cupones.mundo.*;

/**
 * Clase usada para verificar la correcta implementaci�n de SistemaCupones
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
     * Ruta con el archivo de prueba para la importaci�n de informaci�n
     */
    private final static String NOMBRE_IMPORTACION = "importacion_prueba.txt";

    /**
     * Nombre del archivo de prueba para la exportaci�n de informaci�n
     */
    private final static String NOMBRE_EXPORTACION = "exportacion_prueba.txt";

    /**
     * Ruta con el archivo de prueba para la persistencia (cargar estado) de la aplicaci�n
     */
    private final static String RUTA_ESTADO1 = "./test/data/estado1_test.data";

    /**
     * Ruta con el archivo de prueba para la persistencia (guardar estado) de la aplicaci�n
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
    // M�todos
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
            fail( "No deber�a generar excepci�n :" + e.getMessage( ) );
        }
    }

    /**
     * Escenario que crea un sistema de cupones vac�o
     */
    public void setupEscenario2( )
    {
        try
        {
            sistemaCupones = new SistemaCupones( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a generar excepci�n :" + e.getMessage( ) );
        }
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * darCiudades, darUsuarios <br>
     * Caso de Prueba: <br>
     * Crea un nuevo SistemaCupones a partir de la informaci�n en el archivo
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
                assertEquals( "No carg� la ciudad correctamente", "Ciudad" + i, ciudad.darNombre( ) );
                assertEquals( "No carg� la ciudad correctamente", "Departamento" + i, ciudad.darDepartamento( ) );
                assertEquals( "No carg� la ciudad correctamente", i, cupones.size( ) );
                for( int j = 0; j < i; j++ )
                {
                    Cupon cupon = ( Cupon )cupones.get( j );
                    assertEquals( "No carg�  el cup�n correctamente", "Cupon" + j, cupon.darNombre( ) );
                }
            }

            for( int i = 0; i < 5; i++ )
            {
                Usuario usuario = ( Usuario )usuarios.get( i );
                assertEquals( "No carg� el usuario correctamente", "Usuario" + i, usuario.darId( ) );
                assertEquals( "No carg� el usuario correctamente", "Nombre" + i, usuario.darNombre( ) );
                assertEquals( "No carg� el usuario correctamente", "Apellido" + i, usuario.darApellido( ) );
                assertEquals( "No carg� el usuario correctamente", "Email" + i + "@uniandes.edu.co", usuario.darEmail( ) );
                assertEquals( "No carg� el usuario correctamente", "12345678" + i, usuario.darTelefono( ) );
                assertEquals( "No cargo el usuario correctamente", "Ciudad" + i, usuario.darCiudadResidencia( ) );                
            }
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 2 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * darCiudades, darUsuarios, darUsuarioActual, darCiudadActual<br>
     * Caso de Prueba: <br>
     * Crea un nuevo SistemaCupones vac�o
     */
    public void testSistemaCupones2( )
    {
        setupEscenario2( );
        assertNotNull( "No se inicializ� la lista de ciudades", sistemaCupones.darCiudades( ) );
        assertNotNull( "No se inicializ� la lista de usuarios", sistemaCupones.darUsuarios( ) );
        assertEquals( "No inicializ� el usuario actual correctamente", null, sistemaCupones.darUsuarioActual( ) );
        assertEquals( "No inicializ� elal ciudad actual correctamente", null, sistemaCupones.darCiudadActual( ) );
    }

    /**
     * Prueba 3 - Prueba el m�todo guardarEstado <br>
     * M�todos a probar: <br>
     * guardarEstado, agregarCiudad, agregarCupon, registrarUsuario, cargarEstado, darCiudad, darUsuarios
     */
    public void testGuardarEstado( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Descuento ropa", "15% de descuento en toda nuestra ropa", 50, 1000, "V�lido hasta el 31/12/2011" );
            sistemaCupones.agregarCupon( "Medias gratis", "Compre 2 camisetas y lleve ", 100, 1000, "S�lo aplica para camisetas de adulto" );
            sistemaCupones.agregarCiudad( "Bucaramanga", "Santander" );
            sistemaCupones.registrarUsuario( "12345", "Rafael", "Martinez", "rafael@uniandes.edu.co", "612354685", "Bogot�" );
            sistemaCupones.registrarUsuario( "54321", "Maria", "Gonzalez", "maria_gonzalez@uniandes.edu.co", "76513215", "Girardot" );
            sistemaCupones.guardarEstado( RUTA_ESTADO2 );

            sistemaCupones.cargarEstado( new File( RUTA_ESTADO2 ) );
            assertFalse( "No guard� las ciudades correctamente", sistemaCupones.darCiudades( ).isEmpty( ) );
            assertEquals( "No guard� las ciudades correctamente", 2, sistemaCupones.darCiudades( ).size( ) );

            // Verifica que guard� correctamente la primera ciudad
            Ciudad ciudad = sistemaCupones.darCiudad( 0 );
            assertEquals( "No guard� la ciudad correctamente", "Bogot�", ciudad.darNombre( ) );
            assertEquals( "No guard� la ciudad correctamente", "Cundinamarca", ciudad.darDepartamento( ) );

            // Verifica que guard� correctamente los cupones de la primera ciudad
            ArrayList cupones = ciudad.darCupones( );
            assertNotNull( "No guard� la ciudad correctamente", cupones );
            assertEquals( "No guard� los cupones correctamente", 2, cupones.size( ) );
            assertEquals( "No guard� los cupones correctamente", "Descuento ropa", ( ( Cupon )cupones.get( 0 ) ).darNombre( ) );
            assertEquals( "No guard� los cupones correctamente", "Medias gratis", ( ( Cupon )cupones.get( 1 ) ).darNombre( ) );

            // Verifica que guard� correctamente la segunda ciudad
            ciudad = sistemaCupones.darCiudad( 1 );
            assertEquals( "No guard� la ciudad correctamente", "Bucaramanga", ciudad.darNombre( ) );
            assertEquals( "No guard� la ciudad correctamente", "Santander", ciudad.darDepartamento( ) );

            // Verifica que guard� correctamente los cupones de la segunda ciudad
            cupones = ciudad.darCupones( );
            assertNotNull( "No guard� la ciudad correctamente", cupones );
            assertEquals( "No guard� los cupones correctamente", 0, cupones.size( ) );

            // Verifica que guard� los usuarios correctamente
            assertNotNull( "No guard� los usuarios correctamente", sistemaCupones.darUsuarios( ) );
            assertEquals( "No guard� los usuarios correctamente", 2, sistemaCupones.darUsuarios( ).size( ) );
            assertEquals( "No guard� el usuario correctamente", "12345", ( ( Usuario )sistemaCupones.darUsuarios( ).get( 0 ) ).darId( ) );
            assertEquals( "No guard� el usuario correctamente", "54321", ( ( Usuario )sistemaCupones.darUsuarios( ).get( 1 ) ).darId( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( DatosNoValidosException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 4 - Prueba el m�todo guardarCuponesArchivo <br>
     * M�todos a probar: <br>
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
                    sistemaCupones.agregarCupon( "Nombre" + j, "Descripci�n" + j, j, j, "Condiciones" + j );
                }
            }
            sistemaCupones.guardarCuponesArchivo( NOMBRE_EXPORTACION, RUTA_PERSISTENCIA );
            File nuevoArchivo = new File( RUTA_PERSISTENCIA + NOMBRE_EXPORTACION );
            BufferedReader br = new BufferedReader( new FileReader( nuevoArchivo ) );
            String linea = br.readLine( );

            for( int i = 0; i < 5; i++ )
            {
                assertEquals( "No guard� la informaci�n de la ciudad correctamente", "Ciudad: Ciudad" + i + ";Departamento" + i, linea );
                linea = br.readLine( );
                for( int j = 0; j < i; j++ )
                {
                    assertTrue( "No guard� la informaci�n del cup�n correctamente", linea.startsWith( "Cupon;" ) );
                    assertTrue( "No guard� la informaci�n del cup�n correctamente", linea.endsWith( "Nombre" + j + ";" + j + ".0;" + j + ";Condiciones" + j + ";Descripci�n" + j ) );
                    linea = br.readLine( );
                }
            }
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            fail( "No deber�a generar error" );
        }
        catch( IOException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 5 - Prueba el m�todo importarCuponesDeArchivo <br>
     * M�todos a probar: <br>
     * importarCuponesDeArchivo, darCiudades
     */
    public void testImportarCuponesDeArchivo( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.importarCuponesDeArchivo( RUTA_PERSISTENCIA + NOMBRE_IMPORTACION );
            ArrayList ciudades = sistemaCupones.darCiudades( );
            assertEquals( "No import� las ciudades correctamente", 3, ciudades.size( ) );

            Ciudad ciudad = ( Ciudad )ciudades.get( 0 );
            assertEquals( "No import� la ciudad correctamente", "Bogota", ciudad.darNombre( ) );
            assertEquals( "No import� la ciuad correctamente", "Cundinamarca", ciudad.darDepartamento( ) );

            ArrayList cupones = ciudad.darCupones( );
            assertEquals( "No import� los cupones correctamente", 2, cupones.size( ) );

            Cupon cupon = ( Cupon )cupones.get( 0 );
            assertEquals( "No import� el cup�n correctamente", "Pizza Gratis", cupon.darNombre( ) );
            assertEquals( "No import� el cup�n correctamente", 2000.0, cupon.darPrecio( ) );
            assertEquals( "No import� el cup�n correctamnete", 50, cupon.darCantidadDisponible( ) );
            assertEquals( "No import� el cup�n correctamente", "Aplica antes de 31/08/2012", cupon.darCondiciones( ) );
            assertEquals( "No import� el cup�n correctamente", "Pague 3 y lleve 4", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 1 );
            assertEquals( "No import� el cup�n correctamente", "Gran Comilona", cupon.darNombre( ) );
            assertEquals( "No import� el cup�n correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No import� el cup�n correctamnete", 30, cupon.darCantidadDisponible( ) );
            assertEquals( "No import� el cup�n correctamente", "No aplica para postres ni entradas. V�lido hasta el 31/11/2012", cupon.darCondiciones( ) );
            assertEquals( "No import� el cup�n correctamente", "Barra libre de todos nuestros platos", cupon.darDescripcion( ) );

            ciudad = ( Ciudad )ciudades.get( 1 );
            assertEquals( "No import� la ciudad correctamente", "Medellin", ciudad.darNombre( ) );
            assertEquals( "No import� la ciuad correctamente", "Antioquia", ciudad.darDepartamento( ) );
            cupones = ciudad.darCupones( );
            assertEquals( "No import� los cupones correctamente", 3, cupones.size( ) );

            cupon = ( Cupon )cupones.get( 0 );
            assertEquals( "No import� el cup�n correctamente", "Museo 2 x 1", cupon.darNombre( ) );
            assertEquals( "No import� el cup�n correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No import� el cup�n correctamnete", 100, cupon.darCantidadDisponible( ) );
            assertEquals( "No import� el cup�n correctamente", "Cup�n v�lido antes de las 11:00 am", cupon.darCondiciones( ) );
            assertEquals( "No import� el cup�n correctamente", "Pague su entrada y lleve a un acompa�ante gratis", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 1 );
            assertEquals( "No import� el cup�n correctamente", "Descuento ropa", cupon.darNombre( ) );
            assertEquals( "No import� el cup�n correctamente", 1500.0, cupon.darPrecio( ) );
            assertEquals( "No import� el cup�n correctamnete", 40, cupon.darCantidadDisponible( ) );
            assertEquals( "No import� el cup�n correctamente", "V�lido hasta el 31/12/2011", cupon.darCondiciones( ) );
            assertEquals( "No import� el cup�n correctamente", "15% de descuento en toda nuestra ropa", cupon.darDescripcion( ) );

            cupon = ( Cupon )cupones.get( 2 );

            assertEquals( "No import� el cup�n correctamente", "Medias gratis", cupon.darNombre( ) );
            assertEquals( "No import� el cup�n correctamente", 1000.0, cupon.darPrecio( ) );
            assertEquals( "No import� el cup�n correctamnete", 100, cupon.darCantidadDisponible( ) );
            assertEquals( "No import� el cup�n correctamente", "S�lo aplica para camisetas de adulto", cupon.darCondiciones( ) );
            assertEquals( "No import� el cup�n correctamente", "Compre 2 camisetas y lleve", cupon.darDescripcion( ) );

            ciudad = ( Ciudad )ciudades.get( 2 );
            assertEquals( "No import� la ciudad correctamente", "Bucaramanga", ciudad.darNombre( ) );
            assertEquals( "No import� la ciuad correctamente", "Santander", ciudad.darDepartamento( ) );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 6 - Prueba el m�todo cambiarCiudadActual <br>
     * M�todos a probar: <br>
     * cambiarCiudadActual, darCiudadActual
     */
    public void testCambiarCiudadActual( )
    {
        setupEscenario1( );
        sistemaCupones.cambiarCiudadActual( 3 );
        assertEquals( "No cambi� la ciudad actual correctamente", "Ciudad3", sistemaCupones.darCiudadActual( ).darNombre( ) );

        sistemaCupones.cambiarCiudadActual( 5 );
        assertNull( "No cambi� la ciudad actual correctamente", sistemaCupones.darCiudadActual( ) );

        sistemaCupones.cambiarCiudadActual( -1 );
        assertNull( "No cambi� la ciudad actual correctamente", sistemaCupones.darCiudadActual( ) );

    }

    /**
     * Prueba 7 - Prueba el m�todo buscarCiudadPorNombreYDepartamento <br>
     * M�todos a probar: <br>
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
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 8 - Prueba el m�todo darCiudad <br>
     * M�todos a probar: <br>
     * darCiudad
     */
    public void testDarCiudad( )
    {
        setupEscenario1( );
        assertEquals( "No retorn� la ciudad correcta", "Ciudad3", sistemaCupones.darCiudad( 3 ).darNombre( ) );
        assertNull( "No deber�a retornar una ciudad", sistemaCupones.darCiudad( 8 ) );
        assertNull( "No deber�a retornar una ciudad", sistemaCupones.darCiudad( -1 ) );
    }

    /**
     * Prueba 9 - Prueba el m�todo agregarCiudad <br>
     * M�todos a probar: <br>
     * agregarCiudad, darCiudadActual<br>
     * Caso de prueba: <br>
     * Se agrega la ciudad exitosamente
     */
    public void testAgregarCiudad1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            assertEquals( "La ciudad actual no es la esperada", "Bogot�", sistemaCupones.darCiudadActual( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 10 - Prueba el m�todo agregarCiudad <br>
     * M�todos a probar: <br>
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
            fail( "Deber�a generar error" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 11 - Prueba el m�todo agregarCupon <br>
     * M�todos a probar: <br>
     * agregarCupon, darCiudadActual
     */
    public void testAgregarCupon( )
    {
        setupEscenario1( );
        sistemaCupones.agregarCupon( "nCupon", "nDescripcion", 5, 5.0, "nCondiciones" );
        assertNotNull( "No se agreg� el cup�n correctamente", sistemaCupones.darCiudadActual( ) );
        assertEquals( "No agreg� el cup�n correctamente", "nCupon", sistemaCupones.darCiudadActual( ).darCuponActual( ).darNombre( ) );
    }

    /**
     * Prueba 12 - Prueba el m�todo redimirCupon<br>
     * M�todos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion, darUsuarioActual<br>
     * Caso de prueba <br>
     * Se redime el cup�n exitosamente
     */
    public void testRedimirCupon1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del d�a de 7:00 pm a 11:00pm", 5, 10000, "V�lido s�lo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            sistemaCupones.agregarCupon( "Tarde de Spa 2x1", "Vaya a una relajante tarde de spa con otra persona por el precio de una.", 20, 10000, "S�lo para mayores de 15 a�os" );
            sistemaCupones.redimirCupon( );
            ArrayList cupones = sistemaCupones.darUsuarioActual( ).darCuponesRedimidos( );
            assertEquals( "No agreg� el cup�n correctamente", "Barra libre de cerveza", ( ( Cupon )cupones.get( 0 ) ).darNombre( ) );
            assertEquals( "No agreg� el cup�n correctamente", "Tarde de Spa 2x1", ( ( Cupon )cupones.get( 1 ) ).darNombre( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( CuponAgotadoException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 13 - Prueba el m�todo redimirCupon<br>
     * M�todos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion<br>
     * Caso de prueba <br>
     * El cup�n ya est� agotado
     */
    public void testRedimirCupon2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del d�a de 7:00 pm a 11:00pm", 0, 10000, "V�lido s�lo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            fail( "Deber�a generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( CuponAgotadoException e )
        {
            // Debe pasar por aqu�
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 14 - Prueba el m�todo redimirCupon<br>
     * M�todos a probar: <br>
     * redimirCupon, darCiudad, agregarCupon, iniciarSesion<br>
     * Caso de prueba <br>
     * El usuario ya redimi� el cup�n
     */
    public void testRedimirCupon3( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.darCiudad( 0 );
            sistemaCupones.agregarCupon( "Barra libre de cerveza", "Barra libre de la cerveza del d�a de 7:00 pm a 11:00pm", 5, 10000, "V�lido s�lo para una noche" );
            sistemaCupones.iniciarSesion( "Usuario0" );
            sistemaCupones.redimirCupon( );
            sistemaCupones.redimirCupon( );
            fail( "Deber�a generar error" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( CuponAgotadoException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 15 - Prueba el m�todo darCuponActual<br>
     * M�todos a probar:<br>
     * darCuponActual, agregarCiudad, agregarCupon
     */
    public void testDarCuponActual( )
    {
        setupEscenario2( );
        try
        {
            assertEquals( "No deber�a existir un cup�n actual", null, sistemaCupones.darCuponActual( ) );
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon1", sistemaCupones.darCuponActual( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon2", sistemaCupones.darCuponActual( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon3", sistemaCupones.darCuponActual( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 16 - Prueba el m�todo darCuponAnterior<br>
     * M�todos a probar: <br>
     * darCuponAnterior, agregarCiudad, agregarCupon<br>
     * Caso de prueba: <br>
     * Retorna el cup�n anterior exitosamente
     */
    public void testDarCuponAnterior1( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon2", sistemaCupones.darCuponAnterior( ).darNombre( ) );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon1", sistemaCupones.darCuponAnterior( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 17 - Prueba el m�todo darCuponAnterior<br>
     * M�todos a probar: <br>
     * darCuponAnterior, agregarCiudad, agregarCupon<br>
     * Caso de prueba: <br>
     * Ya se encuentra en el primer cup�n
     */
    public void testDarCuponAnterior2( )
    {

        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.darCuponAnterior( ).darNombre( );
            fail( "Deber�a generar excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 18 - Prueba el m�todo darCuponSiguiente<br>
     * M�todos a probar: <br>
     * darCuponSiguiente, agregarCiudad, agregarCupon, darCuponAnterior<br>
     * Caso de prueba: <br>
     * Retorna el cup�n siguiente exitosamente
     */
    public void testDarCuponSiguiente1( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );
            sistemaCupones.agregarCupon( "Cupon2", "Descripcion2", 2, 2, "Condiciones2" );
            sistemaCupones.darCuponAnterior( );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon2", sistemaCupones.darCuponSiguiente( ).darNombre( ) );
            sistemaCupones.agregarCupon( "Cupon3", "Descripcion3", 3, 3, "Condiciones3" );
            sistemaCupones.darCuponAnterior( );
            assertEquals( "No retorn� el cup�n correctamente", "Cupon3", sistemaCupones.darCuponSiguiente( ).darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 19 - Prueba el m�todo darCuponSiguiente<br>
     * M�todos a probar: <br>
     * darCuponSiguiente, agregarCiudad, agregarCupon <br>
     * Caso de prueba: <br>
     * Ya se encuentra en el �ltimo cup�n
     */
    public void testDarCuponSiguiente2( )
    {
        setupEscenario2( );
        try
        {
            sistemaCupones.agregarCiudad( "Bogot�", "Cundinamarca" );
            sistemaCupones.agregarCupon( "Cupon1", "Descripcion1", 1, 1, "Condiciones1" );

            sistemaCupones.darCuponSiguiente( ).darNombre( );
            fail( "Deber�a generar excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 20 - Prueba el m�todo buscarUsuario<br>
     * M�todos a probar: <br>
     * buscarUsuario
     */
    public void testBuscarUsuario( )
    {
        setupEscenario1( );
        Usuario usuario = sistemaCupones.buscarUsuario( "Usuario3" );
        assertNotNull( "No encontr� un usuario existente", usuario );
        assertEquals( "No encontr� el usuario correcto", "Nombre3", usuario.darNombre( ) );

        usuario = sistemaCupones.buscarUsuario( "Usuario4" );
        assertNotNull( "No encontr� un usuario existente", usuario );
        assertEquals( "No encontr� el usuario correcto", "Nombre4", usuario.darNombre( ) );

        usuario = sistemaCupones.buscarUsuario( "Usuario6" );
        assertNull( "Encontr� un usuario no existente", usuario );
    }

    /**
     * Prueba 21- Prueba el m�todo registrarUsuario<br>
     * M�todos a probar: <br>
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
            assertEquals( "No registr� al usuario correctamente", "1956213516", sistemaCupones.darUsuarioActual( ).darId( ) );
            assertNotNull( "No registr� al usuario correctamente", sistemaCupones.buscarUsuario( "1956213516" ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( DatosNoValidosException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 22- Prueba el m�todo registrarUsuario<br>
     * M�todos a probar: <br>
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
            fail( "Deber�a generar excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            // Debe pasar por aqu�
        }
        catch( DatosNoValidosException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Prueba 23- Prueba el m�todo registrarUsuario<br>
     * M�todos a probar: <br>
     * registrarUsuario<br>
     * Caso de prueba: <br>
     * El correo ingresado no es v�lido
     */
    public void testRegistrarUsuario3( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.registrarUsuario( "1956213516", "Rafael", "Manrique", "rafaelmanriquecupi2.uniandes.edu.co", "12345678", "Calle 1 no 5-21" );
            fail( "Deber�a generar excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( DatosNoValidosException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 24- Prueba el m�todo registrarUsuario<br>
     * M�todos a probar: <br>
     * registrarUsuario<br>
     * Caso de prueba: <br>
     * El tel�fono ingresado no es v�lido
     */
    public void testRegistrarUsuario4( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.registrarUsuario( "1956213516", "Rafael", "Manrique", "rafaelmanrique@cupi2.uniandes.edu.co", "123a", "Calle 1 no 5-21" );
            fail( "Deber�a generar excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        catch( DatosNoValidosException e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 25 - Prueba el m�todo iniciarSesion<br>
     * M�todos a probar: <br>
     * iniciarSesion, darUsuarioActual<br>
     * Caso de prueba: <br>
     * Se inici� la sesi�n exitosamente
     */
    public void testIniciarSesion1( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.iniciarSesion( "Usuario2" );
            assertEquals( "No inici� la sesi�n correctamente", "Usuario2", sistemaCupones.darUsuarioActual( ).darId( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a generar excepci�n" );
        }

    }

    /**
     * Prueba 26 - Prueba el m�todo iniciarSesion<br>
     * M�todos a probar: <br>
     * iniciarSesion<br>
     * Caso de prueba: <br>
     * El usuario no est� registrado
     */
    public void testIniciarSesion2( )
    {
        setupEscenario1( );
        try
        {
            sistemaCupones.iniciarSesion( "Usuario27" );
            fail( "No deber�a generar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // Deber�a pasar por aqu�
        }
    }
}