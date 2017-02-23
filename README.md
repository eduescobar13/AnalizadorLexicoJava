# ESIT ULL Grado de Informática
## PROGRAMACIÓN DE APLICACIONES INTERACTIVAS. Elaboración de un Analizador Léxico de Java.
### Realizada por Eduardo Escobar Alberto

#### **EJECUCIÓN: java ALexico fichero.java**
#### **FICHERO DE SALIDA: fichero.txt

El análisis léxico es la primera fase de un compilador. El analizador léxico (scanner en inglés) el módulo del compilador que recibe como entrada el código fuente (secuencia de caracteres) del programa a compilar y produce una salida formada por componentes léxicos o símbolos llamados tokens. Estos tokens sirven para una posterior etapa del proceso de traducción, siendo la entrada para el analizador sintáctico.
El analizador léxico descompone la secuencia de caracteres del código fuente en una secuencia de tokens y también clasifica estos tokens.

Las diferentes categorías de tokens que reconoce el analizador son:
* **Palabras reservadas:** if, while, do, etc. El analizador devuelve un token diferente para cada palabra reservada (KWIF, KWWHILE, KWDO, etc.) Obsérvese que una palabra reservada, keyword, como su propio nombre indica, es un identificador cuyo uso está restringido (reservado) a representar una situación concreta en el código fuente (sentencias del lenguaje en cuestión).
* **Identificadores:** asociados a variables, nombres de métodos, nombres de clases, tipos definidos por el usuario, etiquetas, etc. Por ejemplo: posicion, velocidad, tiempo. Para cualquier identificador el analizador devuelve siempre el mismo token, ID.
* **Operadores de todo tipo:** = * + - / == ><!= (dependen del lenguaje). Obsérvese que hay operadores formados por un único caracter del código fuente, mientras que otros tienen más de un caracter.
* **Símbolos especiales:** ; ( ) [ ] etc.
* **Constantes numéricas:** literales que representan valores enteros, en coma flotante, etc. 982, -83.2E+2, 4.5, etc.
* **Constantes de caracteres:** literales que representan cadenas concretas de caracteres, “Hola Mundo”, etc.
