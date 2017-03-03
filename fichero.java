// Fichero con código Java para realizar pruebas.

public String demostracionCodigo(String ejemplo, int contadorLinea) {
	String cadena1 = "Eduardo";
	String cadena2 = "Escobar";
	String cadenaFinal = cadena1 + cadena2 + ejemplo;
	for (int i = 0; i < 5; i + 1) {
		cadenaFinal += contadorLinea;
		contadorLinea + 1;
	}
	return cadenaFinal;
}

public static void main(String args[]) {
	String ejemplo = "Alberto";
	String cadena;
	cadena = demostracionCodigo(ejemplo);
	System.out.println(cadena);
}