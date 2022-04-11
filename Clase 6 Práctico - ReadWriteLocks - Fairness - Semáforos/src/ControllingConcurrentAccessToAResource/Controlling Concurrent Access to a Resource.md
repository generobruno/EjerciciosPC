## Controlling Concurrent Access to a Resource

Sincronización: Más de una tarea concurrente comparte un recurso.
El bloque de código que accede a ese recurso es llamado *sección crítica*.
Hemos analizado:
    1) Método Synchronized - Synchronized (obj)
    2) Locks - Read Write Locks.
Ahora veremos un mecanismo de más alto nivel: Los SEMÁFOROS.

Un *Semáforo* puede comprenderse como un **contador** que protege el
acceso de uno o más recursos compartidos.
Cuando un hilo quiere acceder a un recurso compartido primero debe
*adquirir* el semáforo.
    - Si el valor del contador es mayor a 0, se decrementa y ACCEDE al recurso
    - Si el valor del contador es menor a 0, el acceso es *NEGADO* y el hilo
va a dormir en una cola del semáforo.
Cuando un hilo finaliza el uso del *recurso* debe liberar el semáforo. Esta 
operación incrementa el valor del contador del semáforo.

A continuación veremos un ej de uso de la clase Semáforo. Este semáforo protege
el acceso a UN recurso (Una impresora) con una cola de impresión.
Cuando un semáforo protege a un solo recurso, sus valores pueden ser 0 y 1. En
este caso el semáforo se denomina "Semáforo Binario".
Un semáforo es utilizado de esta manera, permite proteger en exclusión mutua
como con locks. Pero con una característica especial:
    Los semáforos no tienen concepto de *"Dueño"*, cualquiera que tenga acceso
puede liberarlo. Esto puede ser especialmente útil en algunas ocasiones.

Clases:
    1) PrintQueue: Declare un objeto semáforo. Implemente un constructor y el
método printJob. Adentro de este método primero, trate de adquirir el semáforo.
Luego imprime y finally libera el semáforo.
    2) Job (Runnable): Implemente el constructor y el método run. Este método
imprime mensaje, llama al método printJob y finaliza con otra impresión.
    3) Main: Crea objeto del tipo PrintQueue. Crea 10 hilos de Job. Lanza los 10
hilos.


Ejecución del proyecto y conclusión:
Lo importante de este ejemplo está en el método printJob de la clase PrintQueue.
Allí hay 3 pasos que se deben seguir para trabajar con semáforos:
    1) Adquirir el semáforo con el método aquire().
    2) Operar con el recurso compartido.
    3) Finalmente, liberar el semáforo con el método release().
Otro punto importante es la INICIALIZACIÓN del semáforo en el constructor.
Valor inicial 1 binario.

Otros métodos interesantes:
    - acquireUninterruptibly(): En el método acquire() cuando el contador interno
está en 0, el hilo se bloquea. Durante este tiempo el hilo puede ser interrumpido
y lanzar una excepción, con éste método el hilo ignora la interrupción y no lanza
excepciones.
    - tryAcquire(): Este método intenta adquirir el semáforo. Si puede, el mismo
devuelve true y lo toma. En el caso contrario devuelve false en vez de bloquearse.

SEMAPHORE Fairness:
El concepto de Fairness es usado en java, en todas las clases que pueden tener varios
hilos bloqueados esperando por la liberación de un recurso de sincronización.
Como ocurre con otras primitivas, por defecto false = no hay criterio para selección de
hilo, true = el que más espero.
Por lo tanto el constructor del semáforo puede aceptar 2 parámetros, el valor de
inicialización y el boolean de fairness.
