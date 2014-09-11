package com.umariana.contratacionmonitores.app.logica;

import java.io.File;
import java.util.ArrayList;
/**
 * Es la clase principal del sistema Contratacion de Monitores
 * @author CocoSoft
 *
 */
public class Principal {
	//
	//ATRIBUTOS
	//
	/**
	 * Es la lista de dependencias existentes en el sistema
	 */
	private ArrayList<Dependencia> dependencias;
	/**
	 * Es la lista de monitores existentes en el sistema
	 */
	private ArrayList<Monitor> monitores;
	/**
	 * Es la lista de resultados existentes en el sistema
	 */
	private ArrayList<Resultado> resultados;
	//
	//CONSTRUCTOR
	//
	/**
	 * Es el constructor de la clase Principal
	 */
	public Principal(){
		
	}
	//
	//METODOS
	//
	/**
	 * Retorna la lista de monitores
	 * @return monitores
	 */
	public ArrayList<Monitor> getMonitores() {
		return monitores;
	}
	/**
	 * Cambia la lista de monitores por la nueva lista
	 * @param monitores ArrayList<Monitor>
	 */
	public void setMonitores(ArrayList<Monitor> monitores) {
		this.monitores = monitores;
	}
	/**
	 * Retorna la lista de dependencias
	 * @return dependencias
	 */
	public ArrayList<Dependencia> getDependencias() {
		return dependencias;
	}
	/**
	 * Cambia la lista de dependencias por la nueva lista
	 * @param dependencias ArrayList<Dependencia>
	 */
	public void setDependencias(ArrayList<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	/**
	 * Retorna la lista de resultados
	 * @return  resultados
	 */
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}
	/**
	 * Cambia la lista de resultados por la nueva lista
	 * @param resultados ArrayList<Resultado>
	 */
	public void setResultados(ArrayList<Resultado> resultados) {
		this.resultados = resultados;
	}
	/**
	 * El metodo se encarga de crear un monitor en el sistema
	 * <PostCondiciones> Se registro correctamente el usuario al sistema 
	 * @param nombre != null && != ""
	 * @param apellido != null && != ""
	 * @param identificaion != null && != ""
	 * @param foto != null
	 * @param semestre > 0 && <=10
	 * @param promedioAcum > 0 && <= 5
	 */
	public void crearMonitor(String nombre, String apellido, String identificaion, File foto, int semestre, double promedioAcum  ){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
