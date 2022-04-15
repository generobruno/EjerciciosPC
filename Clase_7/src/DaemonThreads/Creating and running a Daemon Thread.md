## Creating and running a Daemon Thread

Java tiene un tipo especial de hilo llamado *"Daemon"*:
Este tipo de hilos tiene *muy baja prioridad de ejecución*. Normalmente
ejecutan cuando ningún otro hilo del mismo programa está ejecutando.
Cuando los hilos daemons son solo los hilos vivos de un programa, la 
JVM los finaliza.
Debido a estas características los hilos Daemons se utilizan para brindar
servicios de segundo plano.
Generalmente no se utilizan para tareas importantes, por la incertidumbre
de cuando tendrán tiempo de CPU.

En el siguiente ejercicio se verá como implementar un hilo Daemon.
Objetivo: n-hilos principales escriben eventos en una cola y el hilo
daemon será el encargado de ir limpiando los eventos de la cola viejos
(que tienen más de 10 segundos desde el tiempo de su creación).

¿Cómo Funciona?:
Si analizamos una ejecución puede observarse como la cola comienza a
crecer hasta que alcanza 40 eventos. Su tamaño varia entre 36 y 40.
El programa arranca con 4 hilos escritores. Cada uno escribe un evento
y se duerme por un segundo.
Luego de los primeros 10 segundos, tenemos 40 eventos en la cola.
Luego de estos 10 segundos, el hilo daemon se ejecutaba mientras
los hilos escritores dormían, pero no borro ninguno porque ninguno 
tenía más de 10 segundos de creado.
Durante el resto de la ejecución el cleaner elimina 4 eventos por segundo
y los 4 hilos crean 1 cada uno. Por lo tanto la cola mantiene su tamaño.

Algunos detalles:
El métod setDaemon, solo puede ejecutarse *ANTES* de iniciar el hilo.
Una vez ejecutado start(), no puede modificarse su daemon status.
Se puede utilizar el método isDaemon(), para consultar si el hilo es
de este tipo. Este método devuelve True o False.