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
package uniandes.cupi2.cupones.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupones.mundo.Usuario;

/**
 * Panel con la informaci�n del usuario
 */
public class PanelUsuario extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Registro
     */
    private static final String REGISTRAR = "Registrar";

    /**
     * Comando Iniciar Sesi�n
     */
    private static final String INICIAR_SESION = "Iniciar Sesion";

    /**
     * Comando Cerrar Sesi�n
     */
    private static final String CERRAR_SESION = "Cerrar sesi�n";

    /**
     * Comando Ver Cupones
     */
    private static final String VER_CUPONES = "Ver Cupones";

    /**
     * La altura que debe tener la imagen de un individuo
     */
    private static final int ALTURA = 150;

    /**
     * El ancho que debe tener la imagen de un individuo
     */
    private static final int ANCHO = 190;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupones principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la informaci�n del usuario y el bot�n de ver datos
     */
    private JPanel panelUsuario;

    /**
     * Panel con la informaci�n del usuario
     */
    private JPanel panelInfoUsuario;

    /**
     * Panel para el manejo de la sesi�n
     */
    private JPanel panelSesion;

    /**
     * Campo de texto para el nombre del usuario
     */
    private JTextField txtUsuario;

    /**
     * Campo de texto para el correo electr�nico del usuario
     */
    private JTextField txtEmail;

    /**
     * Campo de texto para el n�mero telef�nico del usuario
     */
    private JTextField txtTelefono;

    /**
     * Campo de texto para la ciudad de residencia del usuario
     */
    private JTextField txtCiudadResidencia;

    /**
     * Bot�n para ver cupones redimidos
     */
    private JButton btnVerCupones;

    /**
     * Bot�n para registrarse
     */
    private JButton btnRegistrar;

    /**
     * Bot�n para iniciar sesi�n
     */
    private JButton btnIniciarSesion;

    /**
     * Bot�n para cerrar sesi�n
     */
    private JButton btnCerrarSesion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana - Ventana principal de la aplicaci�n - ventana != null
     */
    public PanelUsuario( InterfazCupones ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "" ) );
        setLayout( new BorderLayout( ) );

        panelUsuario = new JPanel( );
        panelUsuario.setBorder( new TitledBorder( "Usuario" ) );
        panelUsuario.setLayout( new BorderLayout( ) );

        inicializarPanelInfoUsuario( );
        panelUsuario.add( panelInfoUsuario, BorderLayout.CENTER );

        JPanel panelBoton = new JPanel( );
        panelBoton.setLayout( new GridLayout( 1, 3 ) );
        panelBoton.add( new JLabel( ) );
        btnVerCupones = new JButton( "Ver Cupones Redimidos" );
        btnVerCupones.setActionCommand( VER_CUPONES );
        btnVerCupones.addActionListener( ( ActionListener )this );
        btnVerCupones.setEnabled( false );
        panelBoton.add( btnVerCupones );
        panelBoton.add( new JLabel( ) );
        panelUsuario.add( panelBoton, BorderLayout.SOUTH );

        add( panelUsuario, BorderLayout.CENTER );

        inicializarPanelSesion( );
        add( panelSesion, BorderLayout.WEST );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel con la informaci�n del usuario
     */
    public void inicializarPanelInfoUsuario( )
    {
        panelInfoUsuario = new JPanel( );
        panelInfoUsuario.setLayout( new GridLayout( 5, 2, 5, 5 ) );

        panelInfoUsuario.add( new JLabel( "Usuario: " ) );
        txtUsuario = new JTextField( );
        txtUsuario.setEditable( false );
        panelInfoUsuario.add( txtUsuario );

        panelInfoUsuario.add( new JLabel( "E-mail: " ) );
        txtEmail = new JTextField( );
        txtEmail.setEditable( false );
        panelInfoUsuario.add( txtEmail );

        panelInfoUsuario.add( new JLabel( "N�mero telef�nico: " ) );
        txtTelefono = new JTextField( );
        txtTelefono.setEditable( false );
        panelInfoUsuario.add( txtTelefono );

        panelInfoUsuario.add( new JLabel( "Ciudad Residencia: " ) );
        txtCiudadResidencia = new JTextField( );
        txtCiudadResidencia.setEditable( false );
        panelInfoUsuario.add( txtCiudadResidencia );

    }

    /**
     * Inicializa el panel sesi�n
     */
    public void inicializarPanelSesion( )
    {
        panelSesion = new JPanel( );
        panelSesion.setLayout( new GridLayout( 4, 1, 5, 5 ) );
        panelSesion.setBorder( new TitledBorder( "Sesi�n" ) );

        btnRegistrar = new JButton( "Registrarse" );
        btnRegistrar.setActionCommand( REGISTRAR );
        btnRegistrar.addActionListener( this );
        panelSesion.add( btnRegistrar );

        btnIniciarSesion = new JButton( "Iniciar Sesi�n" );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        btnIniciarSesion.addActionListener( this );
        panelSesion.add( btnIniciarSesion );

        btnCerrarSesion = new JButton( "Cerrar Sesi�n " );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        btnCerrarSesion.addActionListener( this );
        btnCerrarSesion.setEnabled( false );
        panelSesion.add( btnCerrarSesion );
        panelSesion.add( new JLabel( ) );
    }

    /**
     * Actualiza la informaci�n del panel
     * @param usuario - Usuario actual - usuario != null
     */
    public void actualizar( Usuario usuario )
    {
        if( usuario != null )
        {
            txtUsuario.setText( usuario.darNombre( ) + " " + usuario.darApellido( ) );
            txtEmail.setText( usuario.darEmail( ) );
            txtTelefono.setText( usuario.darTelefono( ) );
            txtCiudadResidencia.setText( usuario.darCiudadResidencia( ) );
        }
        else
        {
            reiniciar( );
        }
    }

    /**
     * Reinicia el panel 
     */
    public void reiniciar( )
    {
        txtUsuario.setText( "" );
        txtEmail.setText( "" );
        txtTelefono.setText( "" );
        txtCiudadResidencia.setText( "" );

    }

    /**
     * Deshabilita el inicio de sesi�n 
     */
    public void deshabilitarInicioSesion( )
    {
        btnIniciarSesion.setEnabled( false );
        btnRegistrar.setEnabled( false );
        btnCerrarSesion.setEnabled( true );
        btnVerCupones.setEnabled( true );
    }

    /**
     * Habilita el inicio de sesi�n  
     */
    public void habilitarInicioSesion( )
    {
        btnIniciarSesion.setEnabled( true );
        btnRegistrar.setEnabled( true );
        btnCerrarSesion.setEnabled( false );
        btnVerCupones.setEnabled( false );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( REGISTRAR ) )
        {
            DialogoRegistrarUsuario dialogo = new DialogoRegistrarUsuario( principal );
            dialogo.setVisible( true );
        }
        else if( e.getActionCommand( ).equals( INICIAR_SESION ) )
        {
            String idUsuario = JOptionPane.showInputDialog( principal, "Ingrese su id", "Iniciar Sesi�n", JOptionPane.PLAIN_MESSAGE );
            principal.iniciarSesion( idUsuario );

        }
        else if( e.getActionCommand( ).equals( CERRAR_SESION ) )
        {
            principal.cerrarSesion( );
        }
        else if( e.getActionCommand( ).equals( VER_CUPONES ) )
        {
            DialogoCuponesRedimidos dialogo = new DialogoCuponesRedimidos( principal );
            dialogo.setVisible( true );
        }
    }
}
