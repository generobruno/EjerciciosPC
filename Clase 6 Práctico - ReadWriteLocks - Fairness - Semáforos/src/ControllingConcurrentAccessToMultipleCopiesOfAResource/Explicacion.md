## Controlling Concurrent Access to multiple copies of a Resource

En el ej. del package ControllingConcurrentAccessToAResource aprendimos las bases del
uso de semáforos. Este implementaba un *semáforo binario*, útil para proteger el 
acceso a un único recurso, o a una sección crítica.
Los semáforos también pueden ser utilizados para proteger varios recursos.

Se verá un ej. con una cola de impresión que puede imprimir en 3 impresoras diferentes.
Se modificará el ejercicio de PrintQueue con semáforos.
Clases:
        1) PrintQueue:
a) Agregar la declaración de un arreglo de boolean llamado freePrinters.
b) Declarar un objeto Lock.
c) Modificar el constructor para inicializar los nuevos objetos declarados. El array
de impresoras libres debe inicializarse todos en true.
d) Declare un semáforo que será inicializado en 3.
e) Modificar el método printJob. Este recibe un objeto como parámetro:
    e.1) Primero debe ejecutar acquire() para tomar el semáforo.
    e.2) Luego averigua la impresora que nos asignaron con el método getPrinter().
    e.3) Simula la impresión.
    e.4) Libera la impresora (marcar en arreglo como liberada).
    e.5) Libera con release() el semáforo.
f) Implementar el método getPrinter():
    f.1) Tomo el lock para acceder a una impresora.
    f.2) Luego busca el primer valor true del array de freePrinters.
    f.3) Finalmente libera el lock y devuelve el índice de la freePrinter.
        2) Job:
a) SAME.
        3) Main:
a) SAME.

La clave de este ejercicio es la clase PrintQueue:
 - El semáforo creado es inicializado en 3, limitando el acceso a los 3 primeros
hilos que llamen al método acquire(). A través de esto los hilos accederán a la
sección crítica. EL VALOR DE INICIALIZACIÓN DEL SEMÁFORO SON LOS RECURSOS DISPONIBLES
QUE HAY.
 - En esta sección crítica los hilos obtienen un índice de impresora a utilizar.
Esta parte esta implementada para mayor realismo, pero no utiliza semáforos.

