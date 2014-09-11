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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.cupones.mundo.Cupon;

/**
 * Diálogo que muestra los cupones que fueron redimidos por el usuario
 */
public class DialogoCuponesRedimidos extends JDialog implements  ListSelectionListener
{
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupones principal;
    
    /**
     * Lista de cupones redimidos
     */
    private ArrayList cupones;
    
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel principal del diálogo
     */
    private JPanel panelDialogo;    
    
    /**
     * Panel con la información del cupón seleccionado
     */
    private JPanel panelInformacion;
    
    /**
     * Panel con la lista de cupones redimidos
     */
    private JScrollPane panelListaCupones;
    
    /**
     * Campo de texto para el id del cupón
     */
    private JTextField txtId;

    /**
     * Campo de texto para el nombre del cupón
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el precio del cupón
     */
    private JTextField txtPrecio;
    
    /**
     * Área de texto con la descripción del descuento
     */
    private JTextArea areaDescripcion;

    /**
     * Lista con los cupones redimidos
     */
    private JList listaCupones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor del diálogo. Crea el panel e inicializa todos sus elementos
     * @param ventanaPrincipal - Ventana principal de la aplicación - ventanaPrincipal != null 
     */
    public DialogoCuponesRedimidos( InterfazCupones ventanaPrincipal )
    {
        super( ventanaPrincipal, true );
        setTitle( "Cupones Redimidos" );
        setLocationRelativeTo( principal );
        setPreferredSize( new Dimension( 400, 250 ) );
        principal = ventanaPrincipal;
        cupones = principal.darCuponesRedimidos( );
      
        panelDialogo = new JPanel( );        
        panelDialogo.setLayout( new GridLayout( 1, 2) );        
        
        inicializarPanelListaCupones( );
        panelDialogo.add( panelListaCupones );        
        
        inicializarPanelInformacion( );
        panelDialogo.add(panelInformacion );
        
        add(panelDialogo);
        pack( );        
        setLocationRelativeTo( ventanaPrincipal );
    }
    
    /**
     * Inicializa el panelListaCupones
     */
    public void inicializarPanelListaCupones( )
    {
        panelListaCupones = new JScrollPane( );
        panelListaCupones.setBorder( new TitledBorder( "Seleccione el cupon a visualizar" ) );
        listaCupones = new JList( );
        listaCupones.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaCupones.addListSelectionListener( this );
        listaCupones.setSelectedIndex( 0 );
        panelListaCupones.setViewportView( listaCupones );
        panelListaCupones.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        panelListaCupones.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        listaCupones.setListData(cupones.toArray( ) );
    }
    
    /**
     * Inicializa el panelInformacion
     */
    public void inicializarPanelInformacion( )
    {
        panelInformacion = new JPanel( );
        panelInformacion.setLayout( new GridLayout( 2, 1 ) );
        panelInformacion.setBorder( new TitledBorder("Información del cupón") );
        
        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 4, 2, 5, 5 ) );
        panelDatos.add( new JLabel( "" ) );
        panelDatos.add( new JLabel( "" ) );
        panelDatos.add( new JLabel( "Id:" ) );
        txtId = new JTextField( );
        txtId.setEditable( false );
        panelDatos.add( txtId );
        panelDatos.add( new JLabel( "Nombre:" ) );
        txtNombre = new JTextField( );  
        txtNombre.setEditable( false );
        panelDatos.add( txtNombre );
        panelDatos.add( new JLabel( "Precio ") );
        txtPrecio = new JTextField( );
        txtPrecio.setEditable( false );
        panelDatos.add( txtPrecio );   
        panelInformacion.add( panelDatos );
        
        JPanel panelArea = new JPanel( );
        panelArea.setLayout( new BorderLayout(  ) );
        areaDescripcion = new JTextArea( );
        areaDescripcion.setEditable( false );
        areaDescripcion.setLineWrap( true );
        JScrollPane scrollDescuentos = new JScrollPane( areaDescripcion );
        scrollDescuentos.setBorder( new TitledBorder( "Descripción del descuento" ) );
        panelArea.add( scrollDescuentos, BorderLayout.CENTER );
        panelInformacion.add( panelArea );
    }
  
    /**
     * Constructor del diálogo. Crea el panel e inicializa todos sus elementos
     * @param ventanaPrincipal - Ventana principal de la aplicación - ventanaPrincipal != null 
     * @param cuponSeleccionado - Posición del cupón seleccionado
     */
    public DialogoCuponesRedimidos( InterfazCupones ventanaPrincipal, int cuponSeleccionado )
    {
        super( ventanaPrincipal, "Cupones Redimidos" );
        principal = ventanaPrincipal;
        cupones = principal.darCuponesRedimidos( );
        
        panelListaCupones = new JScrollPane( );
        listaCupones = new JList( );
        listaCupones.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaCupones.addListSelectionListener( this );
        listaCupones.setSelectedIndex( 0 );
        panelListaCupones.setViewportView( listaCupones );
        panelListaCupones.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        panelListaCupones.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        listaCupones.setListData(cupones.toArray( ) );
        JPanel panelInformacion = new JPanel( );
        
        panelInformacion.add( panelListaCupones );
        add(panelInformacion);
        pack( );        
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Manejo de los eventos de la lista
     * @param arg0 -  Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent arg0 )
    {
        Cupon cupon = ( Cupon )listaCupones.getSelectedValue( );
        if( cupon != null )
        {
            txtId.setText( cupon.darId( ) );
            txtNombre.setText( cupon.darNombre( ) );      
            txtPrecio.setText( "$"+cupon.darPrecio( ) );
            areaDescripcion.setText( cupon.darDescripcion( ) );
        }        
    }  
}
