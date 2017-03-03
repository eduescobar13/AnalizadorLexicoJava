/**
	* Clase principal para el uso y control del analizador léxico de Java.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 01/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import java.io.IOException;

public class Principal {
	
	public static void main(String[] args) throws IOException {
		LecturaFichero ficheroEntrada   = new LecturaFichero(args[0]); // Objeto para realizar operaciones en el fichero de entrada.
		EscrituraFichero ficheroSalida  = new EscrituraFichero("fichero.txt"); // Objeto para realizar operaciones en el fichero de salida.
		AnalizadorLexico analizadorJava = new AnalizadorLexico();
		analizadorJava.analizarFichero(ficheroEntrada, ficheroSalida); // Analizamos el fichero de entrada y volcamos en el de salida.
	}
}
