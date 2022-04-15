## Waiting for multiple concurrent events

En java existe una clase llamada CountDownLatch, que permite a uno o más
hilos esperar, hasta que un grupo de operaciones sea realizada.
Esta clase se inicializa con un valor entero que representa el número de
acciones que el/los hilo/s va/n a esperar para continuar.
Cuando un hilo debe esperar la ejecución de éstas acciones ejecuta el
método *await()*. Este método lo duerme hasta la completitud de todas las
acciones.
Cuando un hilo finaliza una acción ejecuta el método *countDown()* para 
decrementar el contador interno. Cuando el contador llega a 0, la clase
despierta a todos los hilos dormidos en el método await() del CountDownLatch.
Se verá un ejemplo que modela un sistema de videoconferencia. El sistema
de video conferencia deberá esperar el arribo de todos los participantes 
antes de comenzar.

Clase VideoConference (Runnable): Declarar un objeto de la clase 
CountDownLatch. Implementar constructor. Inicializar el objeto de la
clase CountDownLatch, recordar que el sistema esperará el arribo del
número de participantes recibido como parámetro.
Implementar el método arrive(). Este método será llamado cada vez que
arribe un participante.
Implementar el método run(). Primero se ejecuta getCount(), para imprimir
cantidad de participantes. Luego llama al método await() para esperarlos.
Y finalmente imprime mensaje de arribo de todos.
Clase Participante (Runnable): Declara un atributo privado de la clase
VideoConference y otro String con el nombre. Constructor. Run(): se 
duerme por un tiempo random y luego ejecuta el método arrive().
Clase Main: Crea un objeto de la clase VideoConference inicializado en 10.
Crea un hilo para este objeto y lo lanza. Crea 10 Participantes y los lanza.


La clase CountDownLatch tiene 3 elementos básicos:
1) El *valor de inicialización* que determina cuántos eventos debe esperar.
2) El método *await()*, llamado por los threads que quierem esperar la
finalización de todas las acciones.
3) El método *countDown()*, llamado por los eventos cuando finalizan la
acción que sincronizará.

Detalles a considerar:
- No hay ninguna forma de resetear el valor del contador interno del
CountDownLatch. Ni modificarlo.
- Una vez inicializado en su valor, solo se puede decrementar con el 
método countDown().
- Cuando el contador llega a 0, todas las llamadas al método await() son
devueltas inmediatamente y el countDown() no tiene efecto,
- Esta primitiva tiene diferencias con otras:
    1) No es utilizada para proteger un recurso compartido ni una sección
crítica.
    2) Es utilizado para sincronizar uno o más hilos con la ejecución de
una o más tareas.
    3) Solo admite un uso. Se debe crear otro objeto si queremos hacer la
sincronización nuevamente.
- Existe una sobrecarga del método await() que puede recibir hasta 2
parámetros de tiempo (long, timeunit). El thread se duerme hasta que 
llega a 0 o pasa ese tiempo y es interrumpido.