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
package uniandes.cupi2.cupones.interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import uniandes.cupi2.cupones.mundo.Ciudad;
import uniandes.cupi2.cupones.mundo.Cupon;
import uniandes.cupi2.cupones.mundo.CuponAgotadoException;
import uniandes.cupi2.cupones.mundo.DatosNoValidosException;
import uniandes.cupi2.cupones.mundo.ElementoExisteException;
import uniandes.cupi2.cupones.mundo.ElementoNoExisteException;
import uniandes.cupi2.cupones.mundo.PersistenciaException;
import uniandes.cupi2.cupones.mundo.SistemaCupones;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCupones extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta donde se guarda la lista de las ciudades <br>
     */
    private final static String RUTA = "./data/ciudades.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private SistemaCupones sistemaCupones;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel para la extensión de la aplicación
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la información del usuario
     */
    private PanelUsuario panelUsuario;

    /**
     * Panel con la información de los cupones
     */
    private PanelCupones panelCupones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana. Crea la clase e inicializa sus elementos <br>
     */
    public InterfazCupones( )
    {
        // Crea la clase principal
        try
        {
            sistemaCupones = new SistemaCupones( RUTA );
        }
        catch( PersistenciaException e )
        {

            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 675, 750 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Sistema de Cupones" );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        JPanel auxPanelInformacion = new JPanel( );
        auxPanelInformacion.setLayout( new BorderLayout( ) );

        panelUsuario = new PanelUsuario( this );
        auxPanelInformacion.add( panelUsuario, BorderLayout.NORTH );

        panelCupones = new PanelCupones( this );
        auxPanelInformacion.add( panelCupones, BorderLayout.CENTER );

        add( auxPanelInformacion, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista de cupones redimidos por el usuario actual
     * @return Lista de cupones redimidos por el usuario actual
     */
    public ArrayList darCuponesRedimidos( )
    {
        return sistemaCupones.darCuponesUsuarioActual( );
    }

    /**
     * Registra el usuario con los datos dados por parámetro <br>
     * <b>Pos:</br> El usuario fue creado y agregado a la lista de usuarios del sistema
     * @param id - Id del usuario a registrar - id != null y id != ""
     * @param nombre - Nombre del usuario a registrar - nombre != null y nombre != ""
     * @param apellido - Apellido del usuario a registrar - apellido != null y apellido != ""
     * @param email - Correo electrónico del usuario a registrar - email != null y email != ""
     * @param telefono - Número telefónico del usuario a registrar - telefono != null y telefono != ""
     * @param ciudadResidencia - Ciudad de residencia del usuario a registrar - ciudadResidencia != null y ciudadResidencia != ""
     * @throws ElementoExisteException - Lanza excepción si ya existe un usuario con la misma identificación
     * @throws DatosNoValidosException - Lanza excepción si los datos ingresados no son válidos
     */
    public void registrarUsuario( String id, String nombre, String apellido, String email, String telefono, String ciudadResidencia ) throws ElementoExisteException, DatosNoValidosException
    {
        sistemaCupones.registrarUsuario( id, nombre, apellido, email, telefono, ciudadResidencia );
        panelUsuario.deshabilitarInicioSesion( );
        panelCupones.habilitarBotonRedimir( );
        actualizar( );

    }

    /**
     * Inicia la sesión del usuario con la identificación dada
     * @param idUsuario - Id del usuario - idUsuario != null y idUsuario != ""
     */
    public void iniciarSesion( String idUsuario )
    {
        try
        {
            sistemaCupones.iniciarSesion( idUsuario );
            panelUsuario.deshabilitarInicioSesion( );
            panelCupones.habilitarBotonRedimir( );
            actualizar( );
        }
        catch( ElementoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Iniciar Sesión", JOptionPane.ERROR_MESSAGE );
        }
        panelUsuario.actualizar( sistemaCupones.darUsuarioActual( ) );
    }

    /**
     * Cierra la sesión actual
     */
    public void cerrarSesion( )
    {
        sistemaCupones.cerrarSesion( );
        panelUsuario.habilitarInicioSesion( );
        panelCupones.deshabilitarBotonRedimir( );
        actualizar( );
    }

    /**
     * Muestra el cupón anterior al actual
     */
    public void darCuponAnterior( )
    {
        try
        {
            sistemaCupones.darCuponAnterior( );
            actualizar( );
        }
        catch( ElementoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Dar cupón Anterior", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el cupón siguiente al actual
     */
    public void darCuponSiguiente( )
    {
        try
        {
            sistemaCupones.darCuponSiguiente( );
            actualizar( );
        }
        catch( ElementoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Dar cupón Siguiente", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Retorna la lista de ciudades existentes en el sistema
     * @return - La lista de ciudades
     */
    public ArrayList darListaCiudades( )
    {

        return sistemaCupones.darCiudades( );
    }

    /**
     * Retorna la ciudad actual
     * @return - La ciudad actual
     */
    public Ciudad darCiudadActual( )
    {
        return sistemaCupones.darCiudadActual( );
    }

    /**
     * Retorna el cupón actual
     * @return - El cupón actual
     */
    public Cupon darCuponActual( )
    {
        return sistemaCupones.darCuponActual( );
    }

    /**
     * Redime el cupón para el usuario cuya sesión está abierta<br>
     * <b>Pos:</br> El cupón se encuentra en la lista de cupones redimidos del usuario cuya sesión está abierta
     */
    public void redimirCupon( )
    {
        try
        {
            sistemaCupones.redimirCupon( );
            JOptionPane.showMessageDialog( this, "El cupón fue redimido exitosamente", "Redimir Cupón", JOptionPane.INFORMATION_MESSAGE );   
            actualizar( );
        }
        catch( CuponAgotadoException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Redimir Cupón", JOptionPane.ERROR_MESSAGE );
        }
        catch( ElementoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Redimir Cupón", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Agrega una nueva ciudad al sistema<br>
     * <b>Pos:</br> La ciudad fue creada y agregada a la lista de ciudades del sistema
     * @param nombreCiudad - Nombre de la ciudad a agregar - nombreCiudad != "" y nombreDepartamento != null
     * @param nombreDepartamento - Nombre del departamendo donde se encuentra la ciudad a agregar - nombreDepartamento != null y nombreDepartamendo != ""
     * @throws ElementoExisteException - Lanza excepción si
     */
    public void agregarCiudad( String nombreCiudad, String nombreDepartamento ) throws ElementoExisteException
    {
        int a=sistemaCupones.darCiudades( ).size();
        sistemaCupones.agregarCiudad( nombreCiudad, nombreDepartamento );
        int b=sistemaCupones.darCiudades( ).size();
                
        actualizar( );
    }

    /**
     * Cambia la ciudad actual por la ciudad en la posición dada
     * @param nCiudadActual - Posición de la nueva ciudad a visualizar - nCiudad >=- 1
     */
    public void cambiarCiudadSeleccionada( int nCiudadActual )
    {
        sistemaCupones.cambiarCiudadActual( nCiudadActual );
        actualizar( );
    }

    /**
     * Agrega un nuevo cupón a la ciudad actual<br>
     * <b>Pos:</br> El cupón fue creado y agregado a la lista de cupones de la ciudad actual
     * @param nombre - Nombre del cupón a agregar - nombre != null y nombre != ""
     * @param descripcion - Descripción del descuento - descripcion != null y descripcion != ""
     * @param cantidadDisponible - Cantidad disponible - cantidadDisponible > 0
     * @param precio - Precio del cupón - precio > 0
     * @param condiciones - Condiciones del descuento - condiciones != "" y condiciones != null
     */
    public void agregarCupon( String nombre, String descripcion, int cantidadDisponible, double precio, String condiciones )
    {
        sistemaCupones.agregarCupon( nombre, descripcion, cantidadDisponible, precio, condiciones );
        actualizar( );
    }

    /**
     * Importa los cupones del archivo de texto en la ruta dada<br>
     * <b>Pos:</br> Se importaron los cupones al sistema
     * @param ruta - Ruta del archivo a importar - ruta != null y ruta != ""
     */
    public void importarCupones( String ruta )
    {
        try
        {
            sistemaCupones.importarCuponesDeArchivo( ruta );
            actualizar( );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Importar Cupones", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Guarda los cupones en el archivo archivo de texto en la ruta especificada <br>
     * <b>Pos:</br> El archivo fue guardado en la ruta especificada
     * @param nombreArchivo - Nombre del archivo a guardar - nombreArchivo != null y nombreArchivo != ""
     * @param rutaArchivo - Ruta donde se desea guardar el archivo - rutarArchivo != null y rutaArchivo != ""
     */
    public void guardarCupones( String nombreArchivo, String rutaArchivo )
    {
        try
        {
            sistemaCupones.guardarCuponesArchivo( nombreArchivo, rutaArchivo );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Importar Cupones", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza el panelUsuario y el panelCupones
     */
    public void actualizar( )
    {
        panelUsuario.actualizar( sistemaCupones.darUsuarioActual( ) );
        panelCupones.actualizar( sistemaCupones.darCiudadActual( ), sistemaCupones.darCuponActual( ) );
    }

    /**
     * Guarda la información del sistema, justo antes de cerrar la aplicación, si el usuario lo desea<br>
     * Si se presenta una excepción en el proceso de serialización del estado del modelo del mundo, 
     * este método debe avisar al usuario que la aplicación va a cerrarse sin salvar la información. 
     * <b>post: </b> La información del sistema fue guardada en la ruta correspondiente
     */
    public void dispose( )
    {
        try
        {
            int respuesta = JOptionPane.showConfirmDialog( this, "Desea Guardar las modificaciones del sistema","Guardar", JOptionPane.YES_NO_OPTION );
            if(respuesta == JOptionPane.YES_OPTION)
            {
                sistemaCupones.guardarEstado( RUTA);
                super.dispose( );
            }
            else if (respuesta == JOptionPane.NO_OPTION)
            {
                setVisible( false );
            }
            else if (respuesta == JOptionPane.CLOSED_OPTION) 
            {
                setVisible( true );
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            
        }
      
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = sistemaCupones.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = sistemaCupones.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args -
     */
    public static void main( String[] args )
    {
        InterfazCupones interfaz = new InterfazCupones( );
        interfaz.setVisible( true );
    }

}
