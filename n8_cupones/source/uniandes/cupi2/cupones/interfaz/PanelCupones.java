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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupones.mundo.Ciudad;
import uniandes.cupi2.cupones.mundo.Cupon;

/**
 * Panel con la informaci�n de los cupones
 */
public class PanelCupones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Comando Agregar Ciudad
     */
    private static final String AGREGAR_CIUDAD = "Agregar_Ciudad";
    
    /**
     * Comando Agregar Cup�n
     */
    private static final String AGREGAR_CUPON = "Agregar_Cupon";
    
    /**
     * Comando Ver Cupones
     */
    private static final String VER_CUPONES = "Ver_Cupones";
    
    /**
     * Comando Anterior
     */
    private static final String ANTERIOR = "Anterior";
    
    /**
     * Comando Siguiente
     */
    private static final String SIGUIENTE = "Siguiente";
    
    /**
     * Comando Redimir
     */
    private static final String REDIMIR = "Redimir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    public InterfazCupones principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene la informaci�n del cup�n
     */
    private JPanel panelInfoCupon;
    
    /**
     * Panel con los datos del cup�n
     */
    private JPanel panelDatosCupon;
    
    /**
     * Panel con los botones de navegaci�n de los cupones
     */
    private JPanel panelBotones;

    /**
     * Panel para la selecci�n de ciudades
     */
    private JPanel panelCiudad;

    /**
     * Panel con las �reas de texto
     */
    private JPanel panelAreaDescuento;

    /**
     * Campo de texto para el id del cup�n
     */
    private JTextField txtId;

    /**
     * Campo de texto para el nombre del cup�n
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el precio del cup�n
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto para la cantidad disponible
     */
    private JTextField txtCantidadDisponible;

    /**
     * �rea de texto para la descripci�n del descuento
     */
    private JTextArea areaDescripcion;

    /**
     * �rea de texto para las condiciones de uso del cup�n
     */
    private JTextArea areaCondiciones;

    /**
     * Bot�n Agregar Ciudad
     */
    private JButton btnAgregarCiudad;
    
    /**
     * Bot�n Agregar Cup�n
     */
    private JButton btnAgregarCupon;
    
    /**
     * Bot�n Anterior Cup�n
     */
    private JButton btnAnterior;
    
    /**
     * Bot�n Siguiente Cup�n
     */
    private JButton btnSiguiente;
    
    /**
     * Bot�n Redimir
     */
    private JButton btnRedimir;
    
    /**
     * Combobox de las ciudades del sistema
     */
    private JComboBox comboCiudades;
   
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase.
     * @param ventanaPrincipal - Ventana principal de la aplicaci�n - ventanaPrincipal!= null
     */
    public PanelCupones( InterfazCupones ventanaPrincipal)
    {
        comboCiudades=  new JComboBox();
        principal = ventanaPrincipal;
        setBorder( new TitledBorder( "Cupones" ) );
        setLayout( new BorderLayout( ) );

        inicializarPanelCiudad( );
        add( panelCiudad, BorderLayout.NORTH );
        
        panelInfoCupon = new JPanel( );
        panelInfoCupon.setLayout( new GridLayout( 2, 1 ) );
        panelInfoCupon.setBorder( new TitledBorder( "" ) );
        inicializarPanelDatosCupon( );
        panelInfoCupon.add( panelDatosCupon );
        inicializarPanelAreaDescuento( );
        panelInfoCupon.add( panelAreaDescuento );
        
        inicializarPanelBotones( );
        add( panelBotones, BorderLayout.SOUTH );

        add( panelInfoCupon, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el panelDatosCupon
     */
    public void inicializarPanelDatosCupon( )
    {        
        panelDatosCupon = new JPanel( );
        panelDatosCupon.setLayout( new GridLayout( 4, 2, 5, 5 ) );

        panelDatosCupon.add( new JLabel( "Identificador:" ) );
        txtId = new JTextField( );
        txtId.setEditable( false );
        panelDatosCupon.add( txtId );

        panelDatosCupon.add( new JLabel( "Nombre:" ) );
        txtNombre = new JTextField( );
        txtNombre.setEditable( false );
        panelDatosCupon.add( txtNombre );
        
        panelDatosCupon.add( new JLabel( "Precio:" ) );
        txtPrecio = new JTextField( );
        txtPrecio.setEditable( false );
        panelDatosCupon.add( txtPrecio );

        panelDatosCupon.add( new JLabel( "Cantidad Disponible:" ) );
        txtCantidadDisponible = new JTextField( );
        txtCantidadDisponible.setEditable( false );
        panelDatosCupon.add( txtCantidadDisponible );
    }

    /**
     * Inicializa el panel con las �reas de texto
     */
    public void inicializarPanelAreaDescuento( )
    {
        panelAreaDescuento = new JPanel( );
        panelAreaDescuento.setLayout( new GridLayout( 1, 2 ) );

        areaDescripcion = new JTextArea( );
        areaDescripcion.setEditable( false );
        areaDescripcion.setLineWrap( true );
        JScrollPane scrollDescuentos = new JScrollPane( areaDescripcion );
        scrollDescuentos.setBorder( new TitledBorder( "Descripci�n del descuento" ) );
        panelAreaDescuento.add( scrollDescuentos );

        areaCondiciones = new JTextArea( );
        areaCondiciones.setEditable( false );
        areaCondiciones.setLineWrap( true );
        JScrollPane scrollCondiciones = new JScrollPane( areaCondiciones );
        scrollCondiciones.setBorder( new TitledBorder( "Condiciones" ) );
        panelAreaDescuento.add( scrollCondiciones );       
    }

    /**
     * Inicializa el panel con la informaci�n de las ciudades
     */
    public void inicializarPanelCiudad( )
    {
        panelCiudad = new JPanel( );
        panelCiudad.setLayout( new GridLayout( 3, 2 ) );

        panelCiudad.add( new JLabel( "Seleccione una ciudad" ) );
       
        panelCiudad.add( new JLabel( )  ); 
      
        //Bot�n agregar ciudad
        btnAgregarCiudad = new JButton( "Agregar Ciudad" );
        btnAgregarCiudad.setActionCommand( AGREGAR_CIUDAD );
        btnAgregarCiudad.addActionListener( this );
        panelCiudad.add( btnAgregarCiudad ); 
        
        // Object[] ciudades = principal.darListaCiudades( ).toArray( );

        comboCiudades = new JComboBox( principal.darListaCiudades( ).toArray( ) );
        comboCiudades.setActionCommand( VER_CUPONES );
        comboCiudades.addActionListener( this );
        panelCiudad.add( comboCiudades );
        
        //Bot�n Agregar Cup�n
        btnAgregarCupon = new JButton( "Agregar Cup�n" );
        btnAgregarCupon.setActionCommand( AGREGAR_CUPON );
        btnAgregarCupon.addActionListener( this );
                  
        panelCiudad.add( new JLabel( ) );   
        panelCiudad.add(btnAgregarCupon );  
        panelCiudad.add( new JLabel( ) );    
    }
    
    /**
     * Inicializa el panel con los botones de navegaci�n de los botones
     */
    public void inicializarPanelBotones( )
    {
        panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 3 ) );
        
        btnAnterior = new JButton( "<<" );
        btnAnterior.setActionCommand( ANTERIOR );
        btnAnterior.addActionListener( this );
        panelBotones.add( btnAnterior );
        
        btnRedimir = new JButton( "Redimir" );
        btnRedimir.setActionCommand( REDIMIR );
        btnRedimir.addActionListener( this );
        btnRedimir.setEnabled( false );
        panelBotones.add( btnRedimir );
        
        btnSiguiente = new JButton( ">>" );
        btnSiguiente.setActionCommand( SIGUIENTE );
        btnSiguiente.addActionListener( this );
        panelBotones.add( btnSiguiente );
    }
    
    /**
     * Actualiza el panel con la inforaci�n actual
     * @param ciudad - Ciudad actual - ciudad != null
     * @param cupon - Cup�n Actual - cupon != null
     */
    public void actualizar(Ciudad ciudad, Cupon cupon )
    {
        comboCiudades.removeAllItems( );
        ArrayList listaCiudades = principal.darListaCiudades( );
        for(int i = 0; i<listaCiudades.size( ); i++)
        {
            comboCiudades.addItem( listaCiudades.get( i ) );
        }
        if(ciudad != null )
        {
            comboCiudades.setSelectedItem( ciudad );
        }
        if(cupon != null)
        {
            txtNombre.setText( cupon.darNombre( ) );
            txtCantidadDisponible.setText( "" + cupon.darCantidadDisponible( ) );
            txtPrecio.setText( "$"+ cupon.darPrecio( ) );
            txtId.setText(  cupon.darId( ) );
            areaCondiciones.setText( cupon.darCondiciones( ) );
            areaDescripcion.setText( cupon.darDescripcion( ) );
        }
        else
        {
            txtNombre.setText( "" );
            txtCantidadDisponible.setText( "" );
            txtPrecio.setText( "" );
            txtId.setText( "" );
            areaCondiciones.setText( "" );
            areaDescripcion.setText( "" );
        }
    }  

    /**
     * Habilita el bot�n para redimir cupones
     */
    public void habilitarBotonRedimir( )
    {
        btnRedimir.setEnabled( true );
    }
    
    /**
     * Deshabilita el bot�n para redimir cupones
     */
    public void deshabilitarBotonRedimir( )
    {
        btnRedimir.setEnabled( false );
    }
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( VER_CUPONES ) )
        {
            if(comboCiudades.getSelectedIndex( ) != -1)
            {
               principal.cambiarCiudadSeleccionada(  comboCiudades.getSelectedIndex( ) );
            }           
        }
        else if( e.getActionCommand( ).equals( ANTERIOR ) )
        {
            principal.darCuponAnterior( );
        }
        else if( e.getActionCommand( ).equals( SIGUIENTE ) )
        {
            principal.darCuponSiguiente( );
        }
        else if( e.getActionCommand( ).equals( REDIMIR ) )
        {
            if( principal.darCiudadActual( ) !=null )
            {
                if(principal.darCiudadActual( ).darCupones( ).size( )>0){
                    principal.redimirCupon( );
                }
                else
                {
                    JOptionPane.showMessageDialog( principal, "la ciudad no tiene cupones", "Redimir Cup�n", JOptionPane.ERROR_MESSAGE );   
                }

            }
            else
            {
                JOptionPane.showMessageDialog( principal, "No ha seleccionado ningun cupon", "Redimir Cup�n", JOptionPane.ERROR_MESSAGE );   
            }
        }
        else if( e.getActionCommand( ).equals( AGREGAR_CIUDAD ) )
        {
            DialogoAgregarCiudad dialogo = new DialogoAgregarCiudad( principal );
            dialogo.setVisible( true );
        }
        else if( e.getActionCommand( ).equals( AGREGAR_CUPON ) )
        {
            if(principal.darCiudadActual( ) != null)
            {
                DialogoAgregarCupon dialogo = new DialogoAgregarCupon( principal );
                dialogo.setVisible( true );
            }
            else
            {
                JOptionPane.showMessageDialog( principal, "No puede agregar un cup�n porque no hay ninguna ciudad seleccionada", "Agregar Cup�n", JOptionPane.ERROR_MESSAGE );
            }
        }
    }     

}
