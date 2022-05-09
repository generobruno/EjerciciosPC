## Running Concurrent Phased Tasks

PHASER es una de las primitivas más potentes y complejas que nos brinda java, para
ejecutar tareas concurrentes por fases. Este mecanismo es muy útil cuando tenemos
tareas concurrentes divididas en etapas. Provee el mecanismo de sincronización al
final de cada etapa, para que ningún hilo comience la siguiente etapa hasta que no
lleguen todos.
Como otras primitivas, PHASER debe ser inicializado con la cantidad de participantes
a esperar para avanzar de etapa. Con la gran ventaja de poder modificar este valor
*DINÁMICAMENTE DURANTE LA EJECUCIÓN*. Veremos un ejemplo de sincronización de 3
tareas, las 3 buscan archivos con extensión .log modificados en las últimas 24hs.

La tarea es dividida en 3 etapas:
    1) Obtener una lista con los archivos de extensión .log en el directorio y 
subdirectorios asignados.
    2) Filtrar los archivos, dejando solo los modificados en las últimas 24 horas.
    3) Imprimir los resultados por consola.
En las primeras 2 etapas, si el hilo actuante no puede cumplirlas, avisa y se
des-registra del phaser.

Métodos de la clase PHASER:
- arriveAndAwaitAdvance(): Método que decrementa el contador interno y avisa que el
hilo ha llegado al punto de sincronización y se duerme a la espera del resto.
- arriveAndDeregister(): Esto notifica al Phaser que el hilo ha cumplido la etapa
en cuestión, pero que no quiere participar en las próximas sincronizaciones. El
Phaser no deberá esperar más por ese hilo.
- isTerminated(): Este método retorna True solo si todos los hilos se han des-
registrado del Phaser.
- arrive(): Avisa que llego, pero no espera a nadie (CUIDADO CON LA IMPLEMENTACIÓN).
- register(): Este método agrega un participante al phaser. Es considerado como no
arrivado para la fase actual.
- bulkRegister(int Parties): Este método agrega el número especificado de participantes
al Phaser.