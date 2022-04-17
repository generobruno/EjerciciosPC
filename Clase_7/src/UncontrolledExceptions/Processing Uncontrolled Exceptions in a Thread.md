## Processing Uncontrolled Exceptions in a Thread

Hay dos clases de excepciones en Java.
    - Checked exceptions: Son verificadas en tiempo de compilación. Deben
ser manejadas en el método donde se lanzan (catch), o declaradas en la 
especificación del mismo (throws).
    - Unchecked exceptions: No verificadas en tiempo de compilación. El
manejo debe ser implementado por el programador.

Cuando una excepción *checked* es lanzada dentro de un método run() de un
objeto Thread, debemos tratarla, ya que un método run() no acpeta clausula
throws.
Cuando una excepción *unchecked* es lanzada dentro del método run() de un
objeto Thread, el comportamiento por defecto es imprimir el stackTrace en
la consola y finalizar el programa. Afortunadamente, Java provee un mecanismo
para tratarlas y evitar la finalización abrupta del programa.

Se verá un ejemplo sobre como implementar el manejo de excepciones unchecked
Paso a paso:
    1) Implementar la clase para tratar las excepciones unchecked. Esta
clase debe implementar la interface *UncaughtExceptionHandler*, e 
implementar el método uncaughtException() declarado en la interfaz.
    2) Dentro del método simplemente se puede hacer una impresión por
consola.

¿Cómo funciona?:
- Cuando una excepción es lanzada en un hilo y es unchecked, la JVM chequea
si el hilo tiene un handler de este tipo de excepciones. Si lo tiene, el
método correspondiente es ejecutado
Existe otro método para el manejo de excepciones: setDefaultUncaughtExceptionHandler(),
el cual aplica a todos los hilos. *Es un método estático*
Si el hilo no tiene handler, la JVM imprime en la consola el stackTrace
y finaliza la ejecución.

- Cuando se produce una excepción unchecked en un hilo, la JVM busca
3 posibles manejadores:
    1) Primero busca un handler del objeto Thread que lanzo la excepción
como se vio en el ejemplo.
    2) Si no existe, la JVM busca por un handler para un grupo de hilos,
que es más general que el anterior.
    3) Si tampoco existe, la JVM busca por el default método, otro método
para el manejo de excepciones: setDefaultUncaughtExceptionHandler(), el
cual aplica a todos los hilos.
    4) Si ninguno existe, imprime el stackTrace y finaliza.

