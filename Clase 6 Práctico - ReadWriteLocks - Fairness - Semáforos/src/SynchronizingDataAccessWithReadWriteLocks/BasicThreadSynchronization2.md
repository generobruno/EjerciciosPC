# Basic Thread Synchronization 2

## Syncronizing data acces with read/write locks
Una gran ventaja de los LOCKS es la interface READWRITELOCK y la clase asociada.
Esta clase ReentrantReadWriteLock tiene asociados dos LOCKS.
    1) Uno para Read
    2) Uno para Write
Implementados de manera que:
    a) Puede haber más de un lector.
    b) Solo puede haber 1 escritor.
    c) Cuando hay 1 escritor no puede haber lectores ejecutando.

A continuación se verá un ejemplo:
Clases:
    1) PricesInfo: Almacenará la información de precio de 2 productos. Sus métodos:
a) Constructor
b) getPrice1(hará uso de un lock tipo read).
c) getPrice2(hará uso de un lock tipo read).
d) getPrices(hará uso de un lock tipo write).
    2) Clase Reader (Runnable): Implementa un lector. Sus métodos:
a) Constructor con atributo clase PriceInfo.
b) Run. Bucle con 10 accesos a precio1 y precio2.
    3) Clase Writer (Runnable): Implementa un escritor. Sus métodos:
a) Constructor con atributo clase PriceInfo.
b) Run. Impresión de intento de modificación, acceso a setPrices, Sleep.
    4) Clase Main:
a) Crea objeto PricesInfo.
b) Crea 5 threads y 5 objetos Reader.
c) Crea 1 Thread y 1 objeto Writer.
d) Lanza ejecución de todos los hilos.

Luego de la ejecución:
¿Cuándo será óptima la utilización de ReadWriteLock?
A) Un sistema de base de datos en el que muy esporádicamente se modifican
los valores y se accede y se consulta continuamente por muchos lectores?
B) Un sistema de base de datos en la cual los precios cambian continuamente
y hay muy poco acceso de consultas a la misma?

TODO: Responder