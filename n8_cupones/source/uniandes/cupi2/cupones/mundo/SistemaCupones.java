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
     * Constante de serialización de la clase
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
     * La posición de la ciudad actualmente seleccionada
     */
    private int ciudadActual;

    /**
     * La posición del usuario Actual
     */
    private int usuarioActual;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor del sistema de cupones <br>
     * Si hay información previamente guardada, crea un SistemaCupones con esta información. En caso contrario, crea un nuevo SistemaCupones vacío <br>
     * <b>pos: </b> Se inicializó la lista de ciudades y la lista de usuario. La posición del usuario actual y de la ciudad actual se inicializaron en -1 <br>
     * @param rutaArchivo - Ruta donde se guardará el estado de la aplicación - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza la excepción si ocurre algún error al cargar el estado de la aplicación
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
    // Métodos
    // -------------------------------------------------------------

    /**
     * Carga el estado de la aplicación que se encuentran en el archivo dado por parámetro <br>
     * Primero se carga la lista de ciudades y después la lista de usuarios <br>
     * <b>Pos:</b> Las ciudades del archivo se encuentran cargadas en el sistema
     * @param archivo - Archivo desde donde se carga el estado - archivo != null
     * @throws PersistenciaException - Lanza la excepción si ocurre algún error al cargar el estado
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
     * Guarda el estado de la aplicación <br>
     * Primero se guarda la lista de ciudades y después la lista de usuarios <br>
     * <b>Pos:</b> El estado de la aplicación fue guardado.
     * @param rutaArchivo - Ruta donde se guarda el estado de la aplicación
     * @throws PersistenciaException - Lanza la excepción si ocurre algún error al guardar el estado
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
     * Guarda la información de las ciudades y sus respectivos cupones en un archivo de texto con el formato descrito en el documento de descripción<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada 
     * <b>Pos:</b> El archivo con la información de las ciudades y los cupones fue guardada en el archivo
     * @param nombreArchivo - Nombre del archivo donde se quiere guardar la información - nombreArchivo != "" y nombreArchivo != null
     * @param rutaArchivo - Ruta donde se quiere guardar el archivo - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza una excepción si ocurre algún error guardando el archivo
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
     * Importa la información de las ciudades y sus respectivos cupones desde un archivo de texto<br>
     * <b>Pos:</b> El estado de las ciudades se encuentra cargada en el sistema
     * @param rutaArchivo - Ruta del archivo donde se encuentra la información a importar - rutaArchivo != "" y rutaArchivo != null
     * @throws PersistenciaException - Lanza un error si ocurre algún error leyendo la información del archivo o si el archivo no tiene el formato correcto
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
     * Retorna la ciudad que está actualmente seleccionada<br>
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
     * Cambia la posición de la ciudad actual por el valor dado por parámetro<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada<br>
     * <b>Pos:</b> La nueva ciudad actual es el valor dado por parámetro
     * @param nCiudadActual - Posición de la ciudad actual a asignar
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
     * Busca la ciudad con el nombre y el departamento dados y retorna su posición<br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada <br>
     * <b>Pos:</b> La posición de la ciudad actual es la posición de la ciudad buscada en la lista
     * @param nombreCiudad - Nombre de la ciudad a buscar - nombreCiudad != "" y nombreCiudad "= null
     * @param nombreDepartamento - Nombre del departamento al cual pertenece la ciudad buscada - nombreDepartamento != "" y nombreDepartamento != null
     * @return La posición de la ciudad con el nombre y departamento buscados, -1 si no lo encuentra
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
     * Retorna la ciudad que se encuentra en la posición dada por parámetro <br>
     * <b>Pos:</b> La posición de la ciudad actual es la posición ingresada
     * @param posicion - Posición buscada - posicion > 0
     * @return Ciudad que se encuentra en la posición dada, null si no existe una ciudad en esa posición
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
     * @throws ElementoExisteException - Lanza excepción si la ciudad buscada ya existe
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
     * Agrega un nuevo cupón a la ciudad actual <br>
     * <b>Pre:</b> La lista de ciudades se encuentra inicializada <br>
     * <b>Pos:</b> El cupón fue agregado a la lista de cupones de la ciudad actual
     * @param nNombre - Nombre del cupón a agregar - nNombre != "" y nNombre != null
     * @param nDescripcion - Descripción del descuento del cupón a agregar - nDescripcion != "" y nDescripcion != null
     * @param nCantidadDisponible - Cantidad disponible para redimir del cupón a agregar - nCantidadDisponible >= 0
     * @param nPrecio - Precio del cupón a agregar - nPrecio > 0
     * @param nCondiciones - Condiciones de uso del cupón a agregar - nCondiciones != "" y nCondiciones != null
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
     * Redime el cupón actual al usuario actual<br>
     * <b>Pos:</b> El cupón es agregado a la lista de cupones redimidos del usuario actual
     * @throws CuponAgotadoException - Lanza una excepción si el cupón seleccionado ya está agotado
     * @throws ElementoExisteException - Lanza una excepción si el cupón seleccionado ya fue redimido
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
     * Retorna el cupón que está actualmente seleccionado
     * @return Cupón actual
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
     * Retorna el cupón que se encuentra en la posición anterior a la actual de la ciudad actual<br>
     * @return El cupón anterior al actual
     * @throws ElementoNoExisteException - Lanza excepción si ya se encuentra en el primer cupón
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
     * Retorna el cupón que se encuentra en la posición siguiente a la actual de la ciudad actual<br>
     * @return El cupón siguiente al actual
     * @throws ElementoNoExisteException - Lanza excepción si ya se encuentra en el último cupón
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
     * Busca el usuario que tiene el id dado por parámetro <br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <b>Pos:</b> La posición del usuario actual es la posición en la lista del usuario con el id ingresado
     * @param idUsuario - La identificación del usuario buscado - idUsuario != null y idUsuario != ""
     * @return El usuario con el id dado, null si no lo encontró
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
     * Retorna al usuario que se encuentra en la posición actual<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * @return Usuario en la posición actual, null si no existe ningún usuario
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
     * Registra un nuevo usuario en el sistema con los datos ingresados por parámetro<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <br>
     * <b>Pos:</b> Se creó un nuevo usuario y se agregó a la lista de usuarios <br>
     *             Se actualizo la posición del usuarioActual a la ultima posición de la lista de usuarios
     * @param id - Identificación del usuario a registrar - id != "" y id != null
     * @param nombre - Nombre del usuario a registrar - nombre != null y nombre != ""
     * @param apellido - Apellido del usuario a registrar - apellido != null y apellido != ""
     * @param email - Correo electrónico del usuario a registrar - email != null y email != ""
     * @param telefono - Número telefónico del usuario a registrar - telefono != null y telefono != ""
     * @param ciudadResidencia - Ciudad de referencia del usuario a registrar - ciudadResidencia != null y ciudadResidencia != ""
     * @return Usuario que fue registrado
     * @throws ElementoExisteException - Lanza excepción si ya existe un usuario con el id dado
     * @throws DatosNoValidosException - Lanza excepción si los datos ingresados no tienen el formato correcto
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
     * Inicia la sesión del usuario con la identificación dada por parámetro<br>
     * <b>Pre:</b> La lista de usuarios se encuentra inicializada<br>
     * <b>Pos:</b> El nuevo usuario actual es el usuario con el id dado
     * @param idUsuario - Identificación del usuario que desea iniciar sesión - idUsuario != !! y idUsuario "= null
     * @return usuario que inició la sesión
     * @throws ElementoNoExisteException - Lanza excepción si no existe un usuario con el id dado
     */
    public Usuario iniciarSesion( String idUsuario ) throws ElementoNoExisteException
    {
        Usuario usuario = buscarUsuario( idUsuario );
        if( usuario == null )
        {
            throw new ElementoNoExisteException( "El usuario no está registrado" );
        }
        else
        {
            return usuario;
        }
    }

    /**
     * Cierra la sesión del usuario actual <b>Pos:</b> La posición del usuario actual es -1
     */
    public void cerrarSesion( )
    {
        usuarioActual = -1;
        
        verificarInvariante( );
    }

    /**
     * Retorna la lista de los cupones redimidos por el usuario actual
     * @return La lista de cupones redimidos por el usuario actual, una lista vacía si no existe un usuario actual
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
    // Métodos de extensión
    // -------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión 2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}