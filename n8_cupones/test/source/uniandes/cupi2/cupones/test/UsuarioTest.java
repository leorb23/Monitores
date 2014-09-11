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
 * Clase usada para verificar la correcta implementaci�n de Usuario
 */
public class UsuarioTest extends TestCase
{

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * El usuario de UsuarioTest
     */
    private Usuario usuario;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Crea un nuevo usuario
     */
    public void setupEscenario1( )
    {
        try
        {
            usuario = new Usuario( "51145321", "Rafael", "Martinez", "rafa_martinez@cupi2.com", "3394949", "Bogot�" );
        }
        catch( DatosNoValidosException e )
        {
            fail( "No deber�a generar error" );
        }
    }
    
    /**
     * Crea un nuevo usuario
     */
    public void setupEscenario2( )
    {
        try
        {
            usuario = new Usuario( "51145321", "Rafael", "Martinez", "rafa_martinez@cupi2.com", "3394949", "Bogot�" );
            Cupon cupon;
            for( int i = 0; i<5; i++ )
            {
                cupon = new Cupon( "cupon"+i, "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", i+1, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
                usuario.redimirCupon( cupon );
            }
        }
        catch( DatosNoValidosException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a generar error" );
        }
        catch( CuponAgotadoException e )
        {
            fail( "No deber�a generar error" );
        }
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * Usuario, darId, darNombre, darApellido, darEmail, darTelefono, darCiudadResidencia
     */
    public void testUsuario1( )
    {
        setupEscenario1( );
        assertEquals( "No inicializ� el id correctamente", "51145321", usuario.darId( ) );
        assertEquals( "No inicializ� el nombre correctamente", "Rafael", usuario.darNombre( ) );
        assertEquals( "No inicializ� el apellido correctamente", "Martinez", usuario.darApellido( ) );
        assertEquals( "No inicializ� el email correctamente", "rafa_martinez@cupi2.com", usuario.darEmail( ) );
        assertEquals( "No inicializ� el tel�fono correctamente", "3394949", usuario.darTelefono( ) );
        assertEquals( "No inicializ� la direcci�n correctamente", "Bogot�", usuario.darCiudadResidencia( ) );
        assertNotNull( "No inicializ� la lista de cupones correctamente", usuario.darCuponesRedimidos( ) );
    }

    /**
     * Prueba 2 - Prueba el m�todo redimirCupon <br>
     * M�todos a probar: <br>
     * redimirCupon, darCuponesRedimidos<br>
     * Caso de prueba: <br>
     * Se redime el cup�n exitosamente
     */
    public void testRedimirCupon1( )
    {
        setupEscenario1( );
        try
        {
           Cupon cupon = new Cupon( "cupon1", "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", 2, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
           usuario.redimirCupon( cupon );
           assertEquals("No agreg� el cup�n a la lista", 1, usuario.darCuponesRedimidos( ).size( ));
           assertEquals("No agreg� el cup�n a la lista", "cupon1", ((Cupon)usuario.darCuponesRedimidos( ).get( 0 )).darId());
        }
        catch( ElementoExisteException e )
        {
             fail("No deber�a generar error");
        }
        catch( CuponAgotadoException e )
        {
            fail("No deber�a generar error");
        }
    }
    
    /**
     * Prueba 3 - Prueba el m�todo redimirCupon <br>
     * M�todos a probar: <br>
     * redimirCupon, darCuponesRedimidos<br>
     * Caso de prueba:<br>
     * El cup�n ya fue redimido
     */
    public void testRedimirCupon2( )
    {
        setupEscenario1( );
        try
        {
           Cupon cupon = new Cupon( "cupon1", "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", 2, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
           usuario.redimirCupon( cupon );
           usuario.redimirCupon( cupon );
           fail("Deber�a generar error");
        }
        catch( ElementoExisteException e )
        {
           //Debe pasar por aqu�
        }
        catch( CuponAgotadoException e )
        {
            fail("No deber�a generar este error");
        }
    }
    
    /**
     * Prueba 4 - Prueba el m�todo redimirCupon <br>
     * M�todos a probar: <br>
     * redimirCupon, darCuponesRedimidos<br>
     * Caso de prueba:<br>
     * Ya no hay cupones disponibles
     */
    public void testRedimirCupon3( )
    {
        setupEscenario1( );
        try
        {
           Cupon cupon = new Cupon( "cupon1", "Promocion hamburguesas", "Compre un combo hamburguesa y reciba un combo infantil", 0, 3500, "Aplica s�lo los martes de 2:00pm a 4:00pm" );
           usuario.redimirCupon( cupon );
           usuario.redimirCupon( cupon );
           fail("Deber�a generar error");
        }
        catch( ElementoExisteException e )
        {
            fail("No deber�a generar este error");
           
        }
        catch( CuponAgotadoException e )
        { 
            //Debe pasar por aqu�
        }
    }
    
    /**
     * Prueba 5 - Prueba el m�todo cuponRedimido <br>
     * M�todos a probar: <br>
     * cuponRedimido
     */
    public void testCuponRedimido( )
    {
        setupEscenario2( );
        assertTrue("No hace la b�squeda correctamente",  usuario.cuponRedimido( "cupon1" ) );
        assertTrue("No hace la b�squeda correctamente",  usuario.cuponRedimido( "cupon4" ) );
        assertFalse("No hace la b�squeda correctamente",  usuario.cuponRedimido( "cupon" ) );
    }
    
    /**
     * Prueba 6 - Prueba el m�todo esCorreoValido <br>
     * M�todos a probar: <br>
     * esCorreoValido
     */
    public void testEsCorreoValido( )
    {
        setupEscenario1( );
        assertFalse("No hace la validaci�n correctamente",  usuario.esCorreoValido( "correoCualquiera" )); 
        assertFalse("No hace la validaci�n correctamente",  usuario.esCorreoValido( "correoCualquiera@servidor" ));
        assertFalse("No hace la validaci�n correctamente",  usuario.esCorreoValido( "correo.Cualquiera@servidor" ));
        assertTrue("No hace la validaci�n correctamente",  usuario.esCorreoValido( "correoCualquiera@servidor.com" ));
        assertTrue("No hace la validaci�n correctamente",  usuario.esCorreoValido( "correoCualquiera@servidor.edu.co" ));
    }
    
    /**
     * Prueba 7 - Prueba el m�todo esTelefonoValido <br>
     * M�todos a probar: <br>
     * esTelefonoValido
     */
    public void testEsTelefonoValido( )
    {
        setupEscenario1( );
        assertFalse("No hace la validaci�n correctamente", usuario.esTelefonoValido( "a2354213545" ));
        assertFalse("No hace la validaci�n correctamente", usuario.esTelefonoValido( "_/*321546426" ));
        assertFalse("No hace la validaci�n correctamente", usuario.esTelefonoValido( "12345" ));
        assertTrue("No hace la validaci�n correctamente", usuario.esTelefonoValido( "3394949" ));
        assertTrue("No hace la validaci�n correctamente", usuario.esTelefonoValido( "3105135231" ));
    }
    
    /**
     * Prueba 8 - Prueba el m�todo compararPorId <br>
     * M�todos a probar: <br>
     * compararPorId
     */
    public void testCompararPorId( )
    {
        setupEscenario1( );
        assertFalse( "No hace la comparaci�n correctamente", usuario.compararPorId( "531" ) );
        assertFalse( "No hace la comparaci�n correctamente", usuario.compararPorId("Rafael") );
        assertTrue( "No hace la comparaci�n correctamente", usuario.compararPorId( "51145321" ) );        
    }
}
