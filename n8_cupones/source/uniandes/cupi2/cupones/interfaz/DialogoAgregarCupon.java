/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupones
 * Autor: Manuel Alejandro Murcia - 18-ago-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupones.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Diálogo para agregar un cupón al sistema
 */
public class DialogoAgregarCupon extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando agregar cupón
     */
    private final static String AGREGAR_CUPON = "Agregar cupón";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupones principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel principal del diálogo
     */
    private JPanel panelDialogo;

    /**
     * Panel que contiene los campos de texto
     */
    private JPanel panelDatos;

    /**
     * Panel que contiene las áreas de texto
     */
    private JPanel panelTexto;
    
    /**
     * Panel con los botones
     */
    private JPanel panelBotones;

    /**
     * Área de texto con las condiciones del cupón
     */
    private JTextArea areaCondiciones;

    /**
     * Área de texto con la descripción del descuento
     */
    private JTextArea areaDescripcion;

    /**
     * Campo de texto para el nombre del cupón
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el precio del cupón
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto para la cantidad disponible
     */
    private JTextField txtCantidadDisponible;

    /**
     * Botón para agregar un cupón
     */
    private JButton btnAgregarCupon;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo para agregar un cupón al sistema 
     * @param ventana - Ventana principal de la aplicación - ventana != null
     */
    public DialogoAgregarCupon( InterfazCupones ventana )
    {
        principal = ventana;
        setLayout( new BorderLayout( ) );
        setSize( 300, 380 );
        setTitle( "Agregar Cupón" );
        setLocationRelativeTo( principal );

        panelDialogo = new JPanel( );
        panelDialogo.setLayout( new GridLayout( 2, 1 ) );
        panelDialogo.setBorder( new TitledBorder( "Ingrese los datos del cupón" ) );

        panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 5, 2 ) );

        panelDatos.add( new JLabel( "" ) );
        panelDatos.add( new JLabel( "" ) );

        panelDatos.add( new JLabel( "Nombre" ) );
        txtNombre = new JTextField( );
        panelDatos.add( txtNombre );

        panelDatos.add( new JLabel( "Precio cupón" ) );
        txtPrecio = new JTextField( );
        panelDatos.add( txtPrecio );

        panelDatos.add( new JLabel( "Cantidad Disponible" ) );
        txtCantidadDisponible = new JTextField( );
        panelDatos.add( txtCantidadDisponible );

        panelDatos.add( new JLabel( "" ) );
        panelDatos.add( new JLabel( "" ) );

        panelTexto = new JPanel( );
        panelTexto.setLayout( new GridLayout( 2, 1 ) );

        areaDescripcion = new JTextArea( );
        areaDescripcion.setBorder( new TitledBorder( "Descripción Descuento" ) );
        panelTexto.add( areaDescripcion );

        areaCondiciones = new JTextArea( "" );
        areaCondiciones.setBorder( new TitledBorder( "Condiciones" ) );
        panelTexto.add( areaCondiciones );

        panelBotones = new JPanel( );
        panelBotones.setLayout( new GridBagLayout( ) );

        GridBagConstraints gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets( 5, 5, 5, 5 );
        gridBagConstraints.fill = GridBagConstraints.NONE;

        btnAgregarCupon = new JButton( AGREGAR_CUPON );
        btnAgregarCupon.setActionCommand( AGREGAR_CUPON );
        btnAgregarCupon.addActionListener( this );
        panelBotones.add( btnAgregarCupon, gridBagConstraints );

        panelDialogo.add( panelDatos );
        panelDialogo.add( panelTexto );
        add( panelDialogo, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que maneja las acciones generadas por los botones del diálogo
     * @param arg0 - Acción que generó el evento
     */
    public void actionPerformed( ActionEvent arg0 )
    {
        String action = arg0.getActionCommand( );
        if( action.equals( AGREGAR_CUPON ) )
        {
            String nombre = txtNombre.getText( );
            String precio = txtPrecio.getText( );
            String disponibles = txtCantidadDisponible.getText( );
            String condiciones = areaCondiciones.getText( );
            String descripcion = areaDescripcion.getText( );

            try
            {
                double doublePrecio = Double.parseDouble( precio );
                int intDisponibleP = Integer.parseInt( disponibles );

                if( nombre.equals( "" ) || precio.equals( "" ) || disponibles.equals( "" ) || descripcion.equals( "" ) || condiciones.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar cupón", JOptionPane.ERROR_MESSAGE );
                }
                else if( doublePrecio < 1 )
                {
                    JOptionPane.showMessageDialog( this, "El precio del cupón debe ser un mayor a cero", "Agregar cupón", JOptionPane.ERROR_MESSAGE );
                }
                else if( intDisponibleP < 1 )
                {
                    JOptionPane.showMessageDialog( this, "La cantidad de cupones disponibles debe ser mayor a cero", "Agregar cupón", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.agregarCupon(nombre, descripcion, intDisponibleP, doublePrecio, condiciones );
                    JOptionPane.showMessageDialog( this, "El cupón fue agregado exitosamente", "Agregar cupón", JOptionPane.INFORMATION_MESSAGE );
                    super.dispose( );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "El precio y la cantidad disponible deben ser un valorer numéricos", "Agregar cupón a ciudad", JOptionPane.ERROR_MESSAGE );

            }
        }

    }
}
