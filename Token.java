/**
	* Clase para tratar los tokens del fichero de salida del analizador lexico.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 01/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

public class Token {

	// DECLARACIÓN DE ATRIBUTOS.
	private String nombre;
	private String elementoEntrada;
	private int posicionTabla;
	
	public Token(String nombre, String elementoEntrada) { 
		this.nombre = nombre;
		this.elementoEntrada = elementoEntrada; 
	}
	
	public Token(String nombre, String elementoEntrada, int posicionTabla) {
		this.nombre = nombre;
		this.elementoEntrada = elementoEntrada;
		this.posicionTabla = posicionTabla;
	}

//--------------> MÉTODOS GETTER Y SETTER DE LA CLASE.
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getElementoEntrada() {
		return elementoEntrada;
	}

	public void setElementoEntrada(String elementoEntrada) {
		this.elementoEntrada = elementoEntrada;
	}

	public int getPosicionTabla() {
		return posicionTabla;
	}

	public void setPosicionTabla(int posicionTabla) {
		this.posicionTabla = posicionTabla;
	}

}
