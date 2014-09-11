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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupones.mundo.DatosNoValidosException;
import uniandes.cupi2.cupones.mundo.ElementoExisteException;

/**
 * Di�logo para registrar un usuario
 */
public class DialogoRegistrarUsuario extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Aceptar
     */
    public static final String ACEPTAR = "Aceptar";

    /**
     * Comando Cancelar
     */
    public static final String CANCELAR = "Cancelar";


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupones principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel de ingreso de la informaci�n
     */
    private JPanel panelIngreso;   

    /**
     * Campo de texto para el id
     */
    private JTextField txtId;

    /**
     * Campo de texto para el nombre
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el apellido
     */
    private JTextField txtApellido;

    /**
     * Campo de texto para el email
     */
    private JTextField txtEmail;

    /**
     * Campo de texto para el tel�fono
     */
    private JTextField txtTelefono;

    /**
     * Campo de texto para la ciudad de residencia
     */
    private JTextField txtCiudadResidencia;

    /**
     * Bot�n Aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n Cancelar
     */
    private JButton btnCancelar;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus elementos
     * @param ventanaPrincipal - Ventana principal de la aplicaci�n - ventanaPrincipal !=  null
     */
    public DialogoRegistrarUsuario( InterfazCupones ventanaPrincipal )
    {
        super( ventanaPrincipal, "Registrar Usuario" );
        principal = ventanaPrincipal;

        panelIngreso = new JPanel( );
        panelIngreso.setBorder( new TitledBorder( "Ingreso Datos" ) );
        panelIngreso.setLayout( new GridLayout( 9, 2 ) );

        panelIngreso.add( new JLabel( "Identificaci�n:" ) );
        txtId = new JTextField( );
        panelIngreso.add( txtId );

        panelIngreso.add( new JLabel( "Nombre: " ) );
        txtNombre = new JTextField( );
        panelIngreso.add( txtNombre );

        panelIngreso.add( new JLabel( "Apellidos: " ) );
        txtApellido = new JTextField( );
        panelIngreso.add( txtApellido );

        panelIngreso.add( new JLabel( "E-mail: " ) );
        txtEmail = new JTextField( );
        panelIngreso.add( txtEmail );

        panelIngreso.add( new JLabel( "Tel�fono: " ) );
        txtTelefono = new JTextField( );
        panelIngreso.add( txtTelefono );

        panelIngreso.add( new JLabel( "Ciudad Residencia: " ) );
        txtCiudadResidencia = new JTextField( );
        panelIngreso.add( txtCiudadResidencia );
        
        panelIngreso.add( new JLabel( "" ) );
        panelIngreso.add( new JLabel( "" ) );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        panelIngreso.add( btnAceptar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelIngreso.add( btnCancelar );

        add( panelIngreso );
        pack( );
        setLocationRelativeTo( principal );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e - Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {

        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            String id = txtId.getText( );
            String nombre = txtNombre.getText( );
            String apellido = txtApellido.getText( );
            String email = txtEmail.getText( );
            String telefono = txtTelefono.getText( );
            String ciudadResidencia = txtCiudadResidencia.getText( );
            if( !id.equals( "" ) && !nombre.equals( "" ) && !apellido.equals( "" ) && !email.equals( "" ) && !telefono.equals( "" ) && !ciudadResidencia.equals( "" ) )
            {
                try
                {
                    principal.registrarUsuario( id, nombre, apellido, email, telefono, ciudadResidencia );
                    setVisible( false );
                    dispose( );
                    JOptionPane.showMessageDialog( principal, "El usuario fue registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE );

                }
                catch( ElementoExisteException ex )
                {
                    JOptionPane.showMessageDialog( principal, ex.getMessage( ), "Registro Usuario", JOptionPane.ERROR_MESSAGE );

                }
                catch( DatosNoValidosException ex )
                {
                    JOptionPane.showMessageDialog( principal, ex.getMessage( ), "Registro Usuario", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( principal, "Debe llenar todos los campos", "Datos incompletos", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            setVisible( false );
            dispose( );
        }
       
    }
}
