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
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Comando Guardar Cupones
     */
    private static final String GUARDAR_CUPONES = "guardar_cupones";
    
    /**
     * Comando Importar Cupones
     */
    private static final String IMPORTAR_CUPONES = "importar_cupones";
    
    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupones principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------
    
    /**
     * Bot�n Importar Cupones
     */
    private JButton btnImportarCupones;
    
    /**
     * Bot�n Guardar Cupones
     */
    private JButton btnGuardarCupones;
    
    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana - Ventana principal de la aplicaci�n - ventana != null
     */
    public PanelExtension( InterfazCupones ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 4 ) );    
        
        //Bot�n Guardar Cupones
        
        btnGuardarCupones = new JButton( "Guardar Cupones" );
        btnGuardarCupones.setActionCommand( GUARDAR_CUPONES );
        btnGuardarCupones.addActionListener( this );
        add( btnGuardarCupones );
        
        //Bot�n Importar Cupones
        btnImportarCupones = new JButton( "Importar Cupones" );
        btnImportarCupones.setActionCommand( IMPORTAR_CUPONES );
        btnImportarCupones.addActionListener( this );
        add( btnImportarCupones );
        
        //Bot�n opci�n 1
        btnOpcion1 = new JButton("Opci�n 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        //Bot�n opci�n 2
        btnOpcion2 = new JButton("Opci�n 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if(  e.getActionCommand().equals( OPCION_1 ) ) 
        {
            principal.reqFuncOpcion1();
        }
        else if( e.getActionCommand().equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2();
        }

        else if (e.getActionCommand( ).equals( IMPORTAR_CUPONES ) )
        {
            JFileChooser fc = new JFileChooser( "./data" );
            fc.setDialogTitle( "Importar Cupones" );
            fc.setMultiSelectionEnabled( false );

            int resultado = fc.showOpenDialog( principal );
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                String ruta = fc.getSelectedFile( ).getAbsolutePath( );
                principal.importarCupones( ruta );
            }
        }
        else if( e.getActionCommand( ).equals( GUARDAR_CUPONES ))
        {
            JFileChooser fc = new JFileChooser( "./data" ); 
            
            fc.setDialogTitle( "Guardar Cupones" );
            fc.setMultiSelectionEnabled( false );
                      
            int resultado = fc.showSaveDialog( principal );
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                String nombreArchivo = fc.getSelectedFile( ).getName( );
                String rutaArchivo = fc.getSelectedFile( ).getParent( );
                principal.guardarCupones( nombreArchivo, rutaArchivo );
            }
        }
    }
}
