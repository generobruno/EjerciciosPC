#Basic Thread Synchronization

Una de las situaciones más comunes en programas concurrentes
ocurre cuando más de un hilo en ejecución comparten recursos.
En aplicaciones común que varios hilos escriban y lean los
mismos datos/varibales.
El acceso compartido a estos recursos puede provocar errores
o inconsistencia.
Se deben implementar mecanismos para evitar esto.

La solución a estos problemas viene con el concepto de
SECCIÓN CRITICA (SC). Una SC es un bloque de código, o
una parte del programa donde se accede a un recurso y NO
puede ser ejecutada por más de un hilo AL MISMO TIEMPO.
Para facilitar la implementación de una SC, Java ofrece
mecanismos de sincronización.
Cuando un hilo quiere acceder a una SC utiliza uno de los
mecanismos de sincronización provistos (para saber si hay
otro hilo utilizando este recurso).

Cuando más de un hilo están esperando que otro finalize la
ejecución dentro de la SC, la JVM selecciona uno y el resto
espera su turno.
Veremos el concepto de la palabra reservada SYNCHRONIZED.