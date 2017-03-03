/**
	* Clase para la implementación de un analizador léxico del lenguaje Java.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 01/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class AnalizadorLexico {
	
	// DECLARACIÓN DE ATRIBUTOS
   private Hashtable tablaSimbolos;
   private int posicionTabla; // Atributo para el implementar el índice de los elementos en la tabla de símbolos.

	// DECLARACIÓN DE CONSTANTES.
	final static String ESPACIO = " ";
	final static String TOKENS_SEPARADORES = ESPACIO + "(){}[];,.";
	final static String TABULACION = "\\s";
	final static String VACIO = "";
	final static String KEYWORD = "KW";
	final static String IDENTIFICADOR = "ID";
	final static String OPERADOR = "OPERATOR";
	final static String ENTERO = "INT";
	final static String COMA_FLOTANTE = "FLOATING";
	final static String CADENA_CARACTERES = "STRING";
	final static String NO_RECONOCIDO = "TOKEN_ERROR";
	final static String FINAL_FICHERO = "EOF";
	final static int NO_ENCONTRADO = -1;
	final static int NO_LEER = -1;
	final static String INICIO_COMENTARIO_UNICO = "//";
	final static String INICIO_COMENTARIO = "/*";
	final static String FINAL_COMENTARIO = "*/";
	
	// DECLARACIÓN DE VARIABLES GLOBALES.
	String palabrasReservadas[] = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", 
			                         "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", 
			                         "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", 
			                         "interface", "long", "native", "new", "package", "private", "protected", "public", 
			                         "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", 
			                         "throw", "throws", "transient", "try", "void", "volatile", "while"};
	String separadores[] = {"(", ")", "{", "}", "[", "]", ";", ",", "."};
	String operadores[]  = {"=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=", "&&", "||", "++", "--", "+", "-",
			                  "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", 
			                  "%=", "<<=", ">>=", ">>>="};
	
	/**
		* Constructor.
	*/
	public AnalizadorLexico() {
		tablaSimbolos = new Hashtable(); // Tabla Hash para implementar una tabla de símbolos.
		posicionTabla = 0; // Inicializada en la posición 0.
	} 
	
	/**
		* Método principal que analiza el fichero de entrada y vuelca los resultados en el de salida, haciendo uso 
		* de los diferentes métodos y funciones de la clase.
		* @param ficheroEntrada. Objeto de tipo LecturaFichero con los datos a analizar.
		* @param ficheroSalida. Objeto de tipo EscrituraFichero para volcar los datos.
		* @throws IOException 
	*/
	public void analizarFichero(LecturaFichero ficheroEntrada, EscrituraFichero ficheroSalida) throws IOException {
		String lineaEntrada; // Variable String para guardar la linea del fichero inicialmente.
		Vector<Token> lineaSalida; // Variable String para guardar el contenido final de la línea.
		int contadorLinea = 1; // Variable contador para almacenar la línea. Inicializada a 1.
		while ((lineaEntrada = ficheroEntrada.getBufferEntrada().readLine()) != null) {
			lineaEntrada.trim(); // Eliminamos los espacios en blanco a cada lado de la línea.
			if (lineaEntrada.length() == 0) { // Si es una linea en blanco, la descartamos.
				contadorLinea++;
			}
			else {
				lineaSalida = analizarLinea(lineaEntrada);
				for (int i = 0; i < lineaSalida.size(); i++) {
					ficheroSalida.escribirLineaFichero(contadorLinea + " " + (lineaEntrada.indexOf(lineaSalida.get(i).getElementoEntrada()) + 1)
							                             + " " + lineaSalida.get(i).getNombre() + " " + lineaSalida.get(i).getElementoEntrada());
				}
				contadorLinea++; // Incrementamos el contador de línea.
			}
		}
		ficheroSalida.escribirLineaFichero(contadorLinea + " " + FINAL_FICHERO);
		ficheroEntrada.cerrarFichero(); // Cerramos los ficheros al terminar el análisis.
		ficheroSalida.cerrarFichero();
		contadorLinea = 1; // Reiniciamos el valor de contadorLinea.
	}
	
	/**
 		* Metodo secundario. Analiza las líneas de código y realiza el tratamiento de las mismas.
 		* @param lineaCodigo. String que almacena la línea de código a tratar.
 		* @return Vector de tipo Token con cada token tratado.
 		* @throws IOException 
 	*/
	public Vector<Token> analizarLinea(String lineaCodigo) {
		Vector<String> lineaDescompuesta; // Vector para almacenar cada item de la lineaEntrada por separado.
		Vector<Token> lineaSalida; // Vector para almacenar los tokens correspondientes a cada item de lineaDescompuesta.
		lineaDescompuesta = descomponerLinea(lineaCodigo); // Almacenamos la línea descompuesta.
		lineaSalida = establecerTokens(lineaDescompuesta);
		return lineaSalida;
	}
	
	/**
	 	* Función que recibe una línea de código (String) y devuelve un Vector con cada elemento de la línea.
	 	* @param lineaCodigo. String que almacena la línea de código a descomponer.
	 	* @return Vector de tipo String con cada elemento de la línea.
	 	* @throws IOException 
	*/
	public Vector<String> descomponerLinea(String lineaCodigo) {
		Vector<String> vectorDescompuesto = new Vector<String>(); // Vector que albergará los tokens extraídos.
		StringTokenizer stringToken = new StringTokenizer(lineaCodigo, TOKENS_SEPARADORES, true); // Creación de un StringTokenizer.
		String elementoLinea;
      while (stringToken.hasMoreTokens()) { // Mientras existan más tokens para analizar.
      	elementoLinea = stringToken.nextToken(); // Almacenamos dicho token.
      	if (elementoLinea.compareTo(ESPACIO) != 0) { // Si el token no es un espacio.
      		vectorDescompuesto.add(elementoLinea.replaceAll(TABULACION, VACIO)); // Los almacenamos el vector, eliminando también las tabulaciones.
      	}
      } 
      eliminarComentarios(vectorDescompuesto); // Eliminamos los comentarios de la línea.
		return vectorDescompuesto;
	}
	
	/**
 		* Función que recibe un Vector con cada elemento de una línea de código y devuelve los Tokens correspondientes.
 		* @param elementosCodigo Vector de tipo String con cada elemento de la línea de código.
 		* @return Vector de tipo String con los tokens correspondientes a cada elemento.
 	*/
	public Vector<Token> establecerTokens(Vector<String> elementosCodigo) {
		Vector<Token> vectorTokens = new Vector<Token>(); // Vector para almacenar los tokens finales.
		int posicionElemento = 0;
		String stringFormato = null;
		Token tokenInsertar; // Objeto de la clase Token para almacenarlos en el vector.
		for (int i = 0; i < elementosCodigo.size(); i++) {
			if ((posicionElemento = busquedaVector(palabrasReservadas, elementosCodigo.get(i))) != NO_ENCONTRADO) {
				stringFormato = KEYWORD + palabrasReservadas[posicionElemento].toUpperCase();
				tokenInsertar = new Token(stringFormato, elementosCodigo.get(i));
				vectorTokens.add(tokenInsertar);
			}
			else if (elementosCodigo.get(i).matches("^[a-zA-Z][a-zA-Z0-9_]*")) {
				tokenInsertar = new Token(IDENTIFICADOR, elementosCodigo.get(i), getPosicionTabla());
				vectorTokens.add(tokenInsertar);
				getTablaSimbolos().put(getPosicionTabla(), elementosCodigo.get(i));
				setPosicionTabla(getPosicionTabla() + 1);
			}
			else if ((posicionElemento = busquedaVector(operadores, elementosCodigo.get(i))) != NO_ENCONTRADO) {
				tokenInsertar = new Token(OPERADOR, elementosCodigo.get(i));
				vectorTokens.add(tokenInsertar);
			}
			else if ((posicionElemento = busquedaVector(separadores, elementosCodigo.get(i))) != NO_ENCONTRADO) {
				stringFormato = determinarSeparador(elementosCodigo.get(i).charAt(0));
				tokenInsertar = new Token(stringFormato, elementosCodigo.get(i));
				vectorTokens.add(tokenInsertar);
			}
			else if (elementosCodigo.get(i).matches("^[+-]?[0-9]*")) {
				tokenInsertar = new Token(ENTERO, elementosCodigo.get(i), getPosicionTabla());
				vectorTokens.add(tokenInsertar);
				getTablaSimbolos().put(getPosicionTabla(), elementosCodigo.get(i));
				setPosicionTabla(getPosicionTabla() + 1);
			}
			else if (elementosCodigo.get(i).matches("^[+-]?[0-9]+([.][0-9]+)?([E][+-]?[0-9]+)?[ ]*")) {
				tokenInsertar = new Token(COMA_FLOTANTE, elementosCodigo.get(i), getPosicionTabla());
				vectorTokens.add(tokenInsertar);
				getTablaSimbolos().put(getPosicionTabla(), elementosCodigo.get(i));
				setPosicionTabla(getPosicionTabla() + 1);
			}
			else if (elementosCodigo.get(i).matches("^[\"].*[\"]$")) {
				tokenInsertar = new Token(CADENA_CARACTERES, elementosCodigo.get(i));
				vectorTokens.add(tokenInsertar);
			}
			else {
				tokenInsertar = new Token(NO_RECONOCIDO, elementosCodigo.get(i));
				vectorTokens.add(tokenInsertar);
			}
		}
		return vectorTokens;
	}
	
	/**
 		* Función para realizar búsquedas en un vector pasado por parámetro.
 		* @param lineaCodigo. String que almacena la línea de código a descomponer.
 		* @return Posición en la que se encuentra el elemento buscado.
 		* @throws IOException 
 	*/
	public int busquedaVector(String[] vectorBusqueda, String elementoBusqueda) {
		int posicionElemento = NO_ENCONTRADO;
		for (int i = 0; i < vectorBusqueda.length; i++) {
		if (elementoBusqueda.compareTo(vectorBusqueda[i]) == 0) {
				posicionElemento = i;
			}
		}
		return posicionElemento;
	}
	
	public void eliminarComentarios(Vector<String> lineaConComentarios) {
		int posicionComentario = lineaConComentarios.size();
		for (int i = 0; i < lineaConComentarios.size(); i++) {
			if (lineaConComentarios.get(i).compareTo(INICIO_COMENTARIO_UNICO) == 0) {
				posicionComentario = i;
			}
		}
		lineaConComentarios.setSize(posicionComentario);
	}
	
	public String determinarSeparador(char separador) {
		String nombreSeparador = null;
		switch(separador) {
			case '(':
				nombreSeparador = "OPAR";
			break;
			case ')':
				nombreSeparador = "CPAR";
			break;
			case '{':
				nombreSeparador = "OBRACE";
			break;
			case '}':
				nombreSeparador = "CBRACE";
			break;
			case '[':
				nombreSeparador = "OBRACKET";
			break;
			case ']':
				nombreSeparador = "CBRACKET";
			break;
			case ';':
				nombreSeparador = "SEMICOLON";
			break;
			case ',':
				nombreSeparador = "COMA";
			break;
			case '.':
				nombreSeparador = "POINT";
			break;
		}
		return nombreSeparador;
	}

	//-----------> MÉTODOS GETTER Y SETTER DE LA CLASE.
	
	public Hashtable getTablaSimbolos() {
		return tablaSimbolos;
	}

	public void setTablaSimbolos(Hashtable tablaSimbolos) {
		this.tablaSimbolos = tablaSimbolos;
	}
	
	public int getPosicionTabla() {
		return posicionTabla;
	}

	public void setPosicionTabla(int posicionTabla) {
		this.posicionTabla = posicionTabla;
	}

}

