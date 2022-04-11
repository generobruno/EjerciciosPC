## Modifying Lock Fairness

Las siguientes clases, que implementan LOCKS, en sus constructores admiten
un parámetro tipo bool llamado FAIRNESS:
    1) ReentrantLock
    2) ReentrantReadWriteLock
El valor FALSO es por defecto y es llamado NON-FAIR.
En este modo cuando hay varios hilos esperando por el lock, se libera el
lock y debe elegir uno, no hay criterio de selección.
El valor TRUE es llamado FAIR MODE; en la situación anterior, la elección
tomada es el hilo que estaba esperando hace más tiempo.
Tener en cuenta que esto NO funciona con el método tryLock(), ya que el mismo
no duerme ni bloquea el hilo.
Se modificará un ej. antes visto para observar el resultado de un modo y otro.

Clases:
    1) PrintQueue: Se modifica el constructor con un lock con parámetro true.
    2) PrintJob: Simular la impresión pero ahora en 2 etapas, liberando el 
lock y retomándolo.
    3) Main: Lanzar los hilos con 100 mseg. de diferencia de creación.

Ejecución del proyecto en FAIR MODE:
a) Los threads son creados con 100 ms de diferencia. El primer Thread accede
a la impresión con el LOCK.
b) Luego los restantes threads se bloquean.
c) Cuando el primer thread termina su primera parte del trabajo, libera el LOCK
y lo intenta adquirir nuevamente de inmediato.
d) En ese momento hay 10 threads esperando el LOCK.
e) Como se pasó parámetro Fair = TRUE, la JVM elige el hilo que hace más
tiempo espera, en concreto el 2° creado.

Ejecución del proyecto en NON FAIR MODE:
a) Los threads son creados con 100 ms de diferencia. El primer Thread accede
a la impresión con el LOCK.
b) Luego los restantes threads se bloquean.
c) Cuando el primer thread termina su primera parte del trabajo, libera el LOCK
y lo intenta adquirir nuevamente de inmediato.
d) En ese momento hay 10 threads esperando el LOCK.
e) Como se pasó parámetro Fair = FALSE, la ejecución varía. Pueden terminar exactamente
en el orden solicitado, o no, tal como se explicó. Al estar en False no hay criterio.