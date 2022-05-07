# Synchronizing tasks in a common point

Java provee una clase llamada *CyclicBarrier*, la cual nos permite la sincronización
de 2 o más hilos en un determinado punto.
Esta clase comparte algunos objetivos con la visto en otras clases (CountDownLatch),
pero es aún más potente y flexible.
La clase CyclicBarrier es *inicializada con un número entero*, el cual es el número
entero de hilos que serán sincronizados en un determinado punto.
Cuando el hilo llega al punto de sincronización ejecuta el método *await()* para
esperar el arribo de los demás hilos.
Cuando el último de los hilos ejecuta el método await(), la clase CyclicBarrier 
despierta a todos para que prosigan su ejecución.
Una ventaja interesante es la posibilidad de enviarle al CyclicBarrier (Constructor)
un objeto runnable que se ejecutará una vez que todos los hilos ejecutaron el await().
Es importante mencionar que esta primitiva está implementada de modo que PRIMERO se
ejecuta el objeto runnable opcional y luego se despiertan los hilos dormidos en await().

Se verá im ejemplo de como sincronizar un grupo de hilos en un determinado punto.
Se enviará un objeto runnable también como parámetro, que deberá ser ejecutado luego
de que todos los hilos lleguen al punto de sinc.
Se buscará un número en una matriz de números. La matriz será dividida en submatrices.
Por lo que cada hilo buscará por el número en una submatriz.
Una vez que todos los hilos hayan concluido la búsqueda, se ejecutará un reporte 
final para unificar los resultados.
Clases: 
- MatrixMock: Generará la matriz con números random. Implementar el constructor
cuyos parámetros serán filas, columnas y números a buscar. Luego se debe llenar
la matriz con números random y cada vez que el número creado es igual al que se
busca, se incrementa un contador (con fines de validación de resultados).
Finalmente, se imprime en pantalla el número de veces que está en la matriz el 
número buscado. Finalidad comparar los resultados. Implementar el método getRow()
que recibe un número de fila como parámetro y devuelve esa fila.
- Results: Esta clase almacenará en un arreglo el número de ocurrencias del número
buscado en cada fila. Implementar el constructor. Implementar el método setData(),
que recibe como parámetro una posición en el array y un valor, y lo setea.
Implementar el método getData(), devuelve el array con los resultados.
- Searcher (Runnable): Buscará un número en una determinada parte de la matriz.
Declara varios atributos, entre ellos un objeto CyclicBarrier. Implementar constructor
y método run(). Este debe procesar las filas contando el número de encuentros e 
imprimir por pantalla el resultado y llamar al método await().
- Grouper (Runnable): Encargada de unir los resultados de cada submatriz.
- Main: Declaramos e inicializamos tamaño de matriz y valor a buscar. Creamos un
Grouper, un Results y un MatrixMock.

Luego de ejecutar:
El problema resuelto es simple, una matriz grande de números enteros, se busca
saber la cantidad de veces que se repite un número en la misma.
Para mejorar performance, usamos técnica de "divide y conquistarás", con 5 hilos
que buscan 5 submatrices.
Utilizamos un CyclicBarrier para sincronizar los 5 hilos y para ejecutar un runnable
Grouper que reúna la información e imprima en pantalla al finalizar.

La clase CyclicBarrier tiene un contador interno para controlar cuantos hilos deben
arribar a ese punto a sincronizarse. Cada vez que uno llega, ejecuta el método await()
y la clase pone ese hilo a dormir. Una vez que llegaron todos los hilos, se los
despierta a todos para que continúen. Y opcionalmente se crea un nuevo hilo para
ejecutar el parámetro runnable enviado.
La clase CyclicBarrier tiene otro método sobrecargado await() el cual puede recibir
hasta 2 parámetros de tiempo (long, timeUnit).
El hilo estará dormido en la barrera cíclica hasta que todos lleguen o se transcurra
éste tiempo. La clase también provee el método getNumberWaiting() que devuelve el
número de threads dormidos en la barrera.
El método getParties() indica el número de tareas que serán sincronizadas con la
barrera.
La clase CyclicBarrier tiene una ventaja frente a los CountDownLatch, la misma puede
ser RESETEADA a su valor de inicialización (método reset()). Si se ejecuta este
método, todos los hilos dormidos en el await() reciben una excepción de BrokenBarrier.
Debe ver como se maneja esta excepción.
Finalmente, cuando hay varios hilos esperando en el método await() y uno de ellos
es interrumpido, este hilo recibe un interruptedException, pero los otros hilos
que estaban esperando reciben un BrokenBarrierException, y el objeto CyclicBarrier
es pasado al estado Broken. Existe un método para consultar éste estado y es 
isBroken().