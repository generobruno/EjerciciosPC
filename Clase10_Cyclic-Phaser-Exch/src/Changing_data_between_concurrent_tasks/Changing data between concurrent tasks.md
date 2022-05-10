## Changing data between concurrent tasks

Java brinda un mecanismo que permite el intercambio de datos entre 2 tareas
concurrentes. (Primitiva Exchanger).
Esta primitiva permite la sincronización entre 2 hilos en un determinado punto.
Cuando los 2 hilos llegan a este punto, la estructura de datos de un hilo va al 
otro y viceversa.
Esta primitiva sincroniza solo 2 hilos, por lo tanto, puede ser utilizada en el
caso productor-consumidor con solo una instancia de cada uno.

Veremos un ejemplo de consumer: El consumidor comienza con el buffer vacío, y 
llama al EXCHANGER para sincronizarse. Necesita datos para consumir.
El productor comienza con el buffer vacío, genera 10 strings, almacena en un buffer,
y llama a la primitiva para intercambiar.
En este punto ambos hilos, están en el EXCHANGER, e intercambian estructuras.
El primer hilo que ejecuta el EXCHANGER por cuestiones lógicas se duerme para el
intercambio en espera del arribo del otro.
La clase EXCHANGER tiene otra versión que permite indicar el tiempo máximo que
estará el hilo esperando para la sincronización.