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

import uniandes.cupi2.cupones.mundo.ElementoExisteException;

/**
 * Diálogo para agregar una nueva ciudad al sistema
 */
public class DialogoAgregarCiudad extends JDialog implements ActionListener
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
     * Ventana principal de la aplicación
     */
    private InterfazCupones principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel de ingreso de la información
     */
    private JPanel panelIngreso;

    /**
     * Campo de texto para el nombre de la ciudad
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el nombre del departamento
     */
    private JTextField txtDepartamento;
    
    /**
     * Botón Aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón Cancelar
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo. Crea el panel e inicializa todos sus elementos
     * @param ventanaPrincipal - Ventana principal de la aplicación - ventanaPrincipal != null
     */
    public DialogoAgregarCiudad( InterfazCupones ventanaPrincipal )
    {
        super( ventanaPrincipal, "Agregar Ciudad" );
        principal = ventanaPrincipal;
        setLocationRelativeTo( principal );

        panelIngreso = new JPanel( );
        panelIngreso.setBorder( new TitledBorder( "Ingrese los Datos de la Ciudad" ) );
        panelIngreso.setLayout( new GridLayout( 5, 2 ) );

        panelIngreso.add( new JLabel( "" ) );
        panelIngreso.add( new JLabel( "" ) );
        
        panelIngreso.add( new JLabel( "Nombre Ciudad: " ) );
        txtNombre = new JTextField( );
        panelIngreso.add( txtNombre );

        panelIngreso.add( new JLabel( "Nombre Departamento: " ) );
        txtDepartamento = new JTextField( );
        panelIngreso.add( txtDepartamento );

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
        setLocationRelativeTo( null );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e - Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            String nombre = txtNombre.getText( );
            String departamento = txtDepartamento.getText( );
            if( !nombre.equals( "" ) && !departamento.equals( "" ) )
            {
                try
                {
                    int a=principal.darListaCiudades( ).size( );
                    principal.agregarCiudad( nombre, departamento );
                    setVisible( false );
                    dispose( );
                    int b=principal.darListaCiudades( ).size( );
                    if(a!=b){
                        JOptionPane.showMessageDialog( this, "La ciudad fue agregada exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE );
                    }     
                    else{
                        throw new ElementoExisteException( "La ciudad ya existe" );
                    }


                }
                catch( ElementoExisteException ex )
                {
                    JOptionPane.showMessageDialog( this, ex.getMessage( ), "Agregar Cuidad", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Datos incompletos", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            setVisible( false );
            dispose( );
        }

    }

}
