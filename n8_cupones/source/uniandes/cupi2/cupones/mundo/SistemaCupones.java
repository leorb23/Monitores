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
package uniandes.cupi2.cupones.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Clase que representa un sistema que maneja cupones de descuento<br>
 * <b>Inv: </b> <br>
 */
public class SistemaCupones implements Serializable
{

    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase
     */
    private static final long serialVersionUID = 2358024108792634071L;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * La lista de ciudades existentes
     */
    private ArrayList ciudades;

    /**
     * La lista de usuarios registrados
     */
    private ArrayList usuarios;

    /**
     * La posici�n de la ciudad actualmente seleccionada
     */
    private int ciudadActual;

    /**
     * La posici�n del usuario Actual
     */
    private int usuarioActual;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor del sistema de cupones <br>
     * Si hay informaci�n previamente guardada, crea un SistemaCupones con esta informaci�n. En caso contrario, crea un nuevo SistemaCupones vac�o <br>
     * <b>pos: </b> Se inicializ� la lista de ciudades y la lista de usuario. La posici�n del usuario actual y de la ciudad actual se inicializaron en -1 <br>
     * @param rutaArchivo - Ruta donde se guardar� el estado de la aplicaci�n - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza la excepci�n si ocurre alg�n error al cargar el estado de la aplicaci�n
     */
    public SistemaCupones( String rutaArchivo ) throws PersistenciaException
    {
        File archivo = new File( rutaArchivo );
        usuarioActual = -1;

        if( archivo.exists( ) )
        {
            cargarEstado( archivo );

            ciudadActual = ciudades.size( )-1;
        }
        else
        {   
            ciudadActual = -1;
            ciudades = new ArrayList( );
            usuarios = new ArrayList( );
        }
    }
    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Carga el estado de la aplicaci�n que se encuentran en el archivo dado por par�metro <br>
     * Primero se carga la lista de ciudades y despu�s la lista de usuarios <br>
     * <b>Pos:</b> Las ciudades del archivo se encuentran cargadas en el sistema
     * @param archivo - Archivo desde donde se carga el estado - archivo != null
     * @throws PersistenciaException - Lanza la excepci�n si ocurre alg�n error al cargar el estado
     */
    public void cargarEstado( File archivo ) throws PersistenciaException
    {
        if (archivo.exists( ))
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                ciudades= (ArrayList<Ciudad>)ois.readObject();
                usuarios=(ArrayList<Usuario>)ois.readObject( );
                ois.close( );
            }

            catch (IOException e) 
            {

                throw new PersistenciaException( "Imposible restaurar el estado del programa "+"\n"+e.getMessage( ));
            }
            catch( ClassNotFoundException e )
            {
                
                throw new PersistenciaException( "Imposible restaurar el estado del programa"+e.getMessage( ));
            }        
        }


    }

    /**
     * Guarda el estado de la aplicaci�n <br>
     * Primero se guarda la lista de ciudades y despu�s la lista de usuarios <br>
     * <b>Pos:</b> El estado de la aplicaci�n fue guardado.
     * @param rutaArchivo - Ruta donde se guarda el estado de la aplicaci�n
     * @throws PersistenciaException - Lanza la excepci�n si ocurre alg�n error al guardar el estado
     */
    public void guardarEstado( String rutaArchivo ) throws PersistenciaException
    {       

        try 
        {
            ObjectOutputStream oss= new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oss.writeObject(ciudades);
            oss.writeObject(usuarios);
            oss.close();
        } 
        catch (FileNotFoundException e) 
        {        
            throw new PersistenciaException( "Error al guardar el estado de la aplicacion" );
        } 
        catch (IOException e) 
        {     
            throw new PersistenciaException( "Error al guardar el estado de la aplicacion" );
        }  

    }

    /**
     * Guarda la informaci�n de las ciudades y sus respectivos cupones en un archivo de texto con el formato descrito en el documento de descripci�n<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada 
     * <b>Pos:</b> El archivo con la informaci�n de las ciudades y los cupones fue guardada en el archivo
     * @param nombreArchivo - Nombre del archivo donde se quiere guardar la informaci�n - nombreArchivo != "" y nombreArchivo != null
     * @param rutaArchivo - Ruta donde se quiere guardar el archivo - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza una excepci�n si ocurre alg�n error guardando el archivo
     */
    public void guardarCuponesArchivo( String nombreArchivo, String rutaArchivo ) throws PersistenciaException
    {

        File newFile= new File(rutaArchivo+File.separator+nombreArchivo+".txt");
        PrintWriter pw;
        try
        {
            pw = new PrintWriter( newFile );
            for( int i = 0; i < ciudades.size( ); i++ )
            {
                Ciudad miCiudad=(Ciudad)ciudades.get( i );
                miCiudad.guardarCuponesArchivo( pw );
            }
            newFile.createNewFile( );
        }
        catch( FileNotFoundException except )
        {

            throw new PersistenciaException( " guardarCuponesArchivo" +except.getMessage( ));
        }
        catch( IOException e )
        {
            throw new PersistenciaException( " guardarCuponesArchivo" +e.getMessage( ));
        }
        pw.close( );

    }

    /**
     * Importa la informaci�n de las ciudades y sus respectivos cupones desde un archivo de texto<br>
     * <b>Pos:</b> El estado de las ciudades se encuentra cargada en el sistema
     * @param rutaArchivo - Ruta del archivo donde se encuentra la informaci�n a importar - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza un error si ocurre alg�n error leyendo la informaci�n del archivo o si el archivo no tiene el formato correcto
     */
    public void importarCuponesDeArchivo( String rutaArchivo ) throws PersistenciaException
    {
       
        FileReader lector;
        try
        {
            lector = new FileReader( rutaArchivo );
            BufferedReader r= new BufferedReader( lector );
            String linea=r.readLine( );
            String [] v1;


            String linTemCupon="";
            int cA=0;
            while( linea!=null )
            {
                //Saca la ciudad y su departamento
                v1=linea.split( ";" );
                String ciudad=v1[1];
                String departamento=v1[2];
                if(buscarCiudadPorNombreYDepartamento( ciudad, departamento )==-1){
                    agregarCiudad( ciudad, departamento );
                    
                  //Saca el Cupon con sus atributos
                    String lineaTemC="";
                    boolean b= false;


                    if( linTemCupon==null){
                        linea=r.readLine( );
                    }
                    else if(linTemCupon.equals( "" )){
                        linea=r.readLine( );
                    }
                    else{
                        linea=linTemCupon;  
                    }

                    while( linea!=null && !b )
                    {

                        String []v2=linea.split( ";" );
                        if(v2.length==6)
                        {
                            String nombreCupon=v2[1];
                            double nPrecio=Double.parseDouble( v2[2] );
                            int nCantidadDisponible=Integer.parseInt( v2[3]);
                            String nDescripcion=v2[4];
                            String nCondiciones=v2[5];
                            
                            boolean e=false;                      
                            for( int i = 0; i < ciudades.size( ) && !e; i++ )
                            {
                                Ciudad c=(Ciudad) ciudades.get( i );
                                if(ciudad.equalsIgnoreCase( c.darNombre( ) )){
                                    e=true;
                                    ciudadActual=i;
                                }
                            }
                            
                            agregarCupon( nombreCupon, nDescripcion, nCantidadDisponible, nPrecio, nCondiciones );
                        }
                        else{
                            b=true;
                            lineaTemC=linea;
                            cA+=1;
                        }
                        linea=r.readLine( );
                        if(linea==null){
                            linTemCupon=null;
                        }
                        if(b==true){
                            linTemCupon=linea;
                        }
                    }
                    if(lineaTemC.equals( "" ) || lineaTemC==null){
                        linea=r.readLine( );
                        if(linea==null){
                        }
                    }
                    else{
                        linea=lineaTemC; 
                    } 
                }
                    

                else{
                    ciudadActual=ciudades.size( )-1;
                    agregarCiudad( ciudad, departamento );
                    
                  //Saca el Cupon con sus atributos
                    String lineaTemC="";
                    boolean b= false;


                    if( linTemCupon==null){
                        linea=r.readLine( );
                    }
                    else if(linTemCupon.equals( "" )){
                        linea=r.readLine( );
                    }
                    else{
                        linea=linTemCupon;  
                    }

                    while( linea!=null && !b )
                    {

                        String []v2=linea.split( ";" );
                        if(v2.length==6)
                        {
                            String nombreCupon=v2[1];
                            double nPrecio=Double.parseDouble( v2[2] );
                            int nCantidadDisponible=Integer.parseInt( v2[3]);
                            String nDescripcion=v2[4];
                            String nCondiciones=v2[5];
                                      
                            agregarCupon( nombreCupon, nDescripcion, nCantidadDisponible, nPrecio, nCondiciones );
                        }
                        else{
                            b=true;
                            lineaTemC=linea;
                            cA+=1;
                        }
                        linea=r.readLine( );
                        if(linea==null){
                            linTemCupon=null;
                        }
                        if(b==true){
                            linTemCupon=linea;
                        }
                    }
                    if(lineaTemC.equals( "" ) || lineaTemC==null){
                        linea=r.readLine( );
                        if(linea==null){
                        }
                    }
                    else{
                        linea=lineaTemC; 
                    } 
                }
            }
        }
        catch( FileNotFoundException e )
        {        
            throw new PersistenciaException( " importar" +e.getMessage( ));
        }
        catch( IOException e )
        {      
            throw new PersistenciaException( " importar" +e.getMessage( ));
        }
        catch( ElementoExisteException e )
        {     
            throw new PersistenciaException( " importar" +e.getMessage( ));
        }
    }

    /**
     * Retorna la ciudad que est� actualmente seleccionada<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada
     * @return La ciudad actual
     */
    public Ciudad darCiudadActual( )
    {
        if( ciudades.size( ) > 0 && ciudadActual != -1 )
        {
            return ( Ciudad )ciudades.get( ciudadActual );
        }
        else
        {
            return null;
        }
    }

    /**
     * Cambia la posici�n de la ciudad actual por el valor dado por par�metro<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada<br>
     * <b>Pos:</b> La nueva ciudad actual es el valor dado por par�metro
     * @param nCiudadActual - Posici�n de la ciudad actual a asignar
     */
    public void cambiarCiudadActual( int nCiudadActual )
    {
        if( nCiudadActual < 0 || nCiudadActual >= ciudades.size( ) )
        {
            ciudadActual = -1;
        }
        else
        {
            ciudadActual = nCiudadActual;
        }
        
        verificarInvariante( );
    }

    /**
     * Busca la ciudad con el nombre y el departamento dados y retorna su posici�n<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada <br>
     * <b>Pos:</b> La posici�n de la ciudad actual es la posici�n de la ciudad buscada en la lista
     * @param nombreCiudad - Nombre de la ciudad a buscar - nombreCiudad != "" y nombreCiudad "= null
     * @param nombreDepartamento - Nombre del departamento al cual pertenece la ciudad buscada - nombreDepartamento != "" y nombreDepartamento != null
     * @return La posici�n de la ciudad con el nombre y departamento buscados, -1 si no lo encuentra
     */
    public int buscarCiudadPorNombreYDepartamento( String nombreCiudad, String nombreDepartamento )
    {
        Ciudad ciudad = null;
        for( int i = 0; i < ciudades.size( ); i++ )
        {
            ciudad = ( Ciudad )ciudades.get( i );
            if( ciudad.esCiudadBuscada( nombreCiudad, nombreDepartamento ) )
            {
                ciudadActual = i;
                return ciudadActual;
            }
        }
        return -1;
    }

    /**
     * Retorna la ciudad que se encuentra en la posici�n dada por par�metro <br>
     * <b>Pos:</b> La posici�n de la ciudad actual es la posici�n ingresada
     * @param posicion - Posici�n buscada - posicion > 0
     * @return Ciudad que se encuentra en la posici�n dada, null si no existe una ciudad en esa posici�n
     */
    public Ciudad darCiudad( int posicion )
    {
        if( posicion >= 0 && posicion < ciudades.size( ) )
        {
            ciudadActual = posicion;
            return darCiudadActual( );
        }
        else
        {
            return null;
        }
    }

    /**
     * Retorna la lista de ciudades existentes en el sistema<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada
     * @return Lista de ciudades
     */
    public ArrayList darCiudades( )
    {
        return ciudades;
    }

    /**
     * Agrega una nueva ciudad<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada <br>
     * <b>Pos:</b> La nueva ciudad fue agregada a la lista
     * @param nombreCiudad - Nombre de la ciudad a agregar - nombreCiudad != null y nombreCiudad != ""
     * @param nombreDepartamento - Nombre del departamento donde se encuentra la ciudad - nombreDepartamento != null y nombreDepartamento != ""
     * @throws ElementoExisteException - Lanza excepci�n si la ciudad buscada ya existe
     */
    public void agregarCiudad( String nombreCiudad, String nombreDepartamento ) throws ElementoExisteException
    {
        try
        {
            if(buscarCiudadPorNombreYDepartamento( nombreCiudad, nombreDepartamento )==-1)
            {
                Ciudad miCiudad = new Ciudad(nombreCiudad, nombreDepartamento);
                ciudades.add( miCiudad );
              
            }

        }

        catch (Exception e)
        {
            throw new ElementoExisteException( "la Ciudad ya existe"+ e.getMessage( ) );

        }
        
        verificarInvariante( );
    }

    /**
     * Agrega un nuevo cup�n a la ciudad actual <br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada <br>
     * <b>Pos:</b> El cup�n fue agregado a la lista de cupones de la ciudad actual
     * @param nNombre - Nombre del cup�n a agregar - nNombre != "" y nNombre != null
     * @param nDescripcion - Descripci�n del descuento del cup�n a agregar - nDescripcion != "" y nDescripcion != null
     * @param nCantidadDisponible - Cantidad disponible para redimir del cup�n a agregar - nCantidadDisponible >= 0
     * @param nPrecio - Precio del cup�n a agregar - nPrecio > 0
     * @param nCondiciones - Condiciones de uso del cup�n a agregar - nCondiciones != "" y nCondiciones != null
     */
    public void agregarCupon( String nNombre, String nDescripcion, int nCantidadDisponible, double nPrecio, String nCondiciones )
    {
        Ciudad ciudad = darCiudadActual( );

        if( ciudad != null )
        {
            ciudad.agregarCupon( nNombre, nDescripcion, nCantidadDisponible, nPrecio, nCondiciones );
        }
    }

    /**
     * Redime el cup�n actual al usuario actual<br>
     * <b>Pos:</b> El cup�n es agregado a la lista de cupones redimidos del usuario actual
     * @throws CuponAgotadoException - Lanza una excepci�n si el cup�n seleccionado ya est� agotado
     * @throws ElementoExisteException - Lanza una excepci�n si el cup�n seleccionado ya fue redimido
     */
    public void redimirCupon( ) throws CuponAgotadoException, ElementoExisteException
    {
        Usuario usuario = darUsuarioActual( );
        Cupon cupon = darCuponActual( );
        if( usuario != null && cupon != null )
        {
            usuario.redimirCupon( cupon );
        }
    }

    /**
     * Retorna el cup�n que est� actualmente seleccionado
     * @return Cup�n actual
     */
    public Cupon darCuponActual( )
    {
        Ciudad ciudad = darCiudadActual( );
        if( ciudad != null )
        {
            return ciudad.darCuponActual( );
        }
        else
        {
            return null;
        }
    }

    /**
     * Retorna el cup�n que se encuentra en la posici�n anterior a la actual de la ciudad actual<br>
     * @return El cup�n anterior al actual
     * @throws ElementoNoExisteException - Lanza excepci�n si ya se encuentra en el primer cup�n
     */
    public Cupon darCuponAnterior( ) throws ElementoNoExisteException
    {
        Ciudad ciudad = darCiudadActual( );
        if( ciudad != null )
        {
            return ciudad.darCuponAnterior( );
        }
        else
        {
            return null;

        }
    }

    /**
     * Retorna el cup�n que se encuentra en la posici�n siguiente a la actual de la ciudad actual<br>
     * @return El cup�n siguiente al actual
     * @throws ElementoNoExisteException - Lanza excepci�n si ya se encuentra en el �ltimo cup�n
     */
    public Cupon darCuponSiguiente( ) throws ElementoNoExisteException
    {
        Ciudad ciudad = darCiudadActual( );
        if( ciudad != null )
        {
            return ciudad.darCuponSiguiente( );
        }
        else
        {
            return null;
        }
    }

    /**
     * Busca el usuario que tiene el id dado por par�metro <br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <b>Pos:</b> La posici�n del usuario actual es la posici�n en la lista del usuario con el id ingresado
     * @param idUsuario - La identificaci�n del usuario buscado - idUsuario != null y idUsuario != ""
     * @return El usuario con el id dado, null si no lo encontr�
     */
    public Usuario buscarUsuario( String idUsuario )
    {
        Usuario usuario = null;
        boolean encontro = false;
        for( int i = 0; i < usuarios.size( ) && !encontro; i++ )
        {
            usuario = ( Usuario )usuarios.get( i );
            if( usuario.compararPorId( idUsuario ) )
            {
                usuarioActual = i;
                encontro = true;
            }
            else
            {
                usuario = null;
            }
        }
        return usuario;
    }

    /**
     * Retorna la lista de usuarios registrados en el sistema <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * @return Lista de usuarios registrados
     */
    public ArrayList<Usuario> darUsuarios( )
    {
        return usuarios;
    }

    /**
     * Retorna al usuario que se encuentra en la posici�n actual<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * @return Usuario en la posici�n actual, null si no existe ning�n usuario
     */
    public Usuario darUsuarioActual( )
    {
        Usuario usuario = null;
        if( usuarios.size( ) > 0 && usuarioActual != -1 )
        {
            usuario = ( Usuario )usuarios.get( usuarioActual );
        }

        return usuario;

    }

    /**
     * Registra un nuevo usuario en el sistema con los datos ingresados por par�metro<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <br>
     * <b>Pos:</b> Se cre� un nuevo usuario y se agreg� a la lista de usuarios <br>
     *             Se actualizo la posici�n del usuarioActual a la ultima posici�n de la lista de usuarios
     * @param id - Identificaci�n del usuario a registrar - id != "" y id != null
     * @param nombre - Nombre del usuario a registrar - nombre != null y nombre != ""
     * @param apellido - Apellido del usuario a registrar - apellido != null y apellido != ""
     * @param email - Correo electr�nico del usuario a registrar - email != null y email != ""
     * @param telefono - N�mero telef�nico del usuario a registrar - telefono != null y telefono != ""
     * @param ciudadResidencia - Ciudad de referencia del usuario a registrar - ciudadResidencia != null y ciudadResidencia != ""
     * @return Usuario que fue registrado
     * @throws ElementoExisteException - Lanza excepci�n si ya existe un usuario con el id dado
     * @throws DatosNoValidosException - Lanza excepci�n si los datos ingresados no tienen el formato correcto
     */
    public Usuario registrarUsuario( String id, String nombre, String apellido, String email, String telefono, String ciudadResidencia ) throws ElementoExisteException, DatosNoValidosException
    {

     
        Usuario user=null;
        try
        {
            if(buscarUsuario( id )==null)
            {

            }
            else
            {
                throw new ElementoExisteException( "El usuario ya existe" );
            }

        }
        catch( Exception e )
        {
            throw new ElementoExisteException( e.getMessage( ) );
        }


        Usuario u= new Usuario( id, nombre, apellido, email, telefono, ciudadResidencia );
        try
        {
            if(u.esCorreoValido( email ));
        }
        catch( Exception e )
        {
            throw new DatosNoValidosException( "Los datos ingresados no tiene el formato requerido" );
        }

        try
        {
            if(u.esTelefonoValido( telefono ))
            {
                usuarios.add( u );
                usuarioActual=usuarios.size( )-1;
                user= u;
            }
        }
        catch( Exception e )
        {
            throw new DatosNoValidosException( "Los datos ingresados no tiene el formato requerido" );
        }


        return user;
    }

    /**
     * Inicia la sesi�n del usuario con la identificaci�n dada por par�metro<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <b>Pos:</b> El nuevo usuario actual es el usuario con el id dado
     * @param idUsuario - Identificaci�n del usuario que desea iniciar sesi�n - idUsuario != !! y idUsuario "= null
     * @return usuario que inici� la sesi�n
     * @throws ElementoNoExisteException - Lanza excepci�n si no existe un usuario con el id dado
     */
    public Usuario iniciarSesion( String idUsuario ) throws ElementoNoExisteException
    {
        Usuario usuario = buscarUsuario( idUsuario );
        if( usuario == null )
        {
            throw new ElementoNoExisteException( "El usuario no est� registrado" );
        }
        else
        {
            return usuario;
        }
    }

    /**
     * Cierra la sesi�n del usuario actual <b>Pos:</b> La posici�n del usuario actual es -1
     */
    public void cerrarSesion( )
    {
        usuarioActual = -1;
        
        verificarInvariante( );
    }

    /**
     * Retorna la lista de los cupones redimidos por el usuario actual
     * @return La lista de cupones redimidos por el usuario actual, una lista vac�a si no existe un usuario actual
     */
    public ArrayList darCuponesUsuarioActual( )
    {
        Usuario usuarioActual = darUsuarioActual( );
        if( usuarioActual != null )
        {
            return usuarioActual.darCuponesRedimidos( );

        }
        else
        {
            return new ArrayList<Object>( );
        }
    }

    /**
     * @return encontrado
     */
    public boolean hayCiudadesConElMismoNombre()
    {
        boolean encontrado = false;

        for( int i = 0; i < ciudades.size( ) ; i++ )
        {
            Ciudad miCiudad = ( Ciudad )ciudades.get( i );
            for( int j = 0; j < ciudades.size( ) ; j++ )
            {
                Ciudad miCiudad2 = ( Ciudad )ciudades.get( j );
                if(i!=j)
                {
                    if(miCiudad.darNombre( ).equalsIgnoreCase( miCiudad2.darNombre( ) ))
                    {
                        encontrado = true;
                    }
                }
            }
        }
        return encontrado;
    }

    /**
     * @return encontrado
     */
    public boolean hayUsuariosConElMismoId()
    {
        boolean encontrado = false;

        for( int i = 0; i < usuarios.size( ) ; i++ )
        {
            Usuario miUsuario = ( Usuario )usuarios.get( i );
            for( int j = 0; j < usuarios.size( ) ; j++ )
            {
                Usuario miUsuario2 = ( Usuario )usuarios.get( j );
                if(i!=j)
                {
                    if(miUsuario.darId( ).equalsIgnoreCase( miUsuario2.darId( ) ))
                    {
                        encontrado = true;
                    }
                }
            }
        }
        return encontrado;
    }    



    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------
    /**
     * Verifica el invariante de la clase SistemaCuppones <br>
     * no puede haber dos usuarios con el mismo Id.
     * no puede haber dos ciudades con el mismo nombre.
     */
    private void verificarInvariante()
    {
        assert(ciudades != null): "las ciudades no pueden ser null";
        assert(usuarios != null): "los usuarios no pueden ser null";
        assert(!hayCiudadesConElMismoNombre( )) : "No pueden haber dos o mas ciudades con el mismo nombre";
        assert(!hayUsuariosConElMismoId( )): "No pueden haber dos o mas usuarios con el mismo id";
        assert usuarioActual==-1:("Se debe inicializar el usuario actual en -1");
        assert ciudadActual==-1:("Se debe inicializar la ciudad actual en -1");
    }

    // -------------------------------------------------------------
    // M�todos de extensi�n
    // -------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n 2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}