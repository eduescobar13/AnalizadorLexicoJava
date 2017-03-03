/**
	* Clase para la implementación de los test de la clase AnalizadorLexico.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 01/03/2017
 	* Correo electrónico: alu0100825985@ull.edu.es
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Vector;
import org.junit.BeforeClass;
import org.junit.Test;
 
public class TestAnalizadorLexico {
 	
	// Declaración de objetos.
	protected static AnalizadorLexico analizadorTest; 
	
	@BeforeClass
	public static void setUpClass() { // Método que se ejecuta antes de los test de la clase para asignar variables.
		analizadorTest = new AnalizadorLexico(); // Inicializamos el objeto analizadorLexico.
	}
	
	@Test
   public void inicializacionAnalizador() { // Test para comprobar la correcta creación del objeto analizadorTest.
		assertNotNull(analizadorTest); // Comprobamos que el objeto analizadorTest se ha creado correctamente.
	}
	
	@Test
	public void comprobarDescomponerLínea() { // Test para comprobar el correcto funcionamiento de descomponerLinea.
		String lineaPrueba = "public void prueba(int parametro) {";
		String[] resultado = {"public", "void", "prueba", "(", "int", "parametro", ")", "{"};
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.descomponerLinea(lineaPrueba));
	}
	
	@Test
	public void comprobarEstablecerPalabraReservada() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"int", "interface", "long", "native", "new", "package", "private"};
		String[] resultado = {"KWINT", "KWINTERFACE", "KWLONG", "KWNATIVE", "KWNEW", "KWPACKAGE", "KWPRIVATE"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerIdentificador() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"interface", "main", "native", "estoEsUnId", "private"};
		String[] resultado = {"KWINTERFACE", "ID", "KWNATIVE", "ID", "KWPRIVATE"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerOperador() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"interface", "main", "*=", "estoEsUnId", "==", "private"};
		String[] resultado = {"KWINTERFACE", "ID", "OPERATOR", "ID", "OPERATOR", "KWPRIVATE"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerSeparador() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"[", "interface", "main", "*=", "estoEsUnId", ";", "==", "private"};
		String[] resultado = {"OBRACKET", "KWINTERFACE", "ID", "OPERATOR", "ID", "SEMICOLON", "OPERATOR", "KWPRIVATE"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerNumero() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"[", "interface", "-23", "*=", "estoEsUnId", ";", "==", "-82.43E+2"};
		String[] resultado = {"OBRACKET", "KWINTERFACE", "INT", "OPERATOR", "ID", "SEMICOLON", "OPERATOR", "FLOATING"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerCadena() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"[", "interface", "-23", "\"HolaMundo\"", "estoEsUnId", ";", "==", "-82.43E+2"};
		String[] resultado = {"OBRACKET", "KWINTERFACE", "INT", "STRING", "ID", "SEMICOLON", "OPERATOR", "FLOATING"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
	
	@Test
	public void comprobarEstablecerTokens() { // Test para comprobar el correcto funcionamiento de establecerTokens.
		String[] entrada   = {"[", "interface", "-23", "\"HolaMundo\"", "23MD:;", "estoEsUnId", ";", "==", "-82.43E+2"};
		String[] resultado = {"OBRACKET", "KWINTERFACE", "INT", "STRING", "TOKEN_ERROR", "ID", "SEMICOLON", "OPERATOR", "FLOATING"};
		Vector<String> entradaVector   = new Vector<String>(Arrays.asList(entrada));
		Vector<String> resultadoVector = new Vector<String>(Arrays.asList(resultado));
		assertEquals(resultadoVector, analizadorTest.establecerTokens(entradaVector));
	}
}
