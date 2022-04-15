## Using local thread variables

Uno de los aspectos más críticos de las aplicaciones concurrentes son
las *variables compartidas*.
Esto tiene vital importancia en las clases que extienden Thread o implementan
la interfaz Runnable.
Si se crea un objeto de una clase que implementa la interfaz Runnable, y
luego crea varios hilos usando el mismo objeto runnable como argument,
todos los hilos comparte los mismos atributos del objeto runnable!!!
Si modifica un atributo en un hilo, afectará a todos.
A veces se necesita un atributo que no sea compartido por los hilos.
Java provee un mecanismo llamado _variables locales de thread_.
Veremos el problema y la solución.

Veremos un ejemplo del problema anterior:
Crear una clase llamada *UnsafeTask* que implemente Runnable. Declarar un 
atributo privado de tipo date. 
Implementar el método run(), este método inicializa el atributo
startDate, imprime su valor por la consola, se duerme un número random
de tiempo y vuelve a escribirlo.
Implemente la clase Main. Este método debe crear un objeto Unsafe Task
y lanzar 3 hilos con igual argumento (durmiendo 2 segundoes entre
creación).
