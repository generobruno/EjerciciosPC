public class Calculator implements Runnable {


    public void run() {
        long current = 1L;
        long max = 20000L; // con un num menor no se tendria tan en cuenta la prioridad
        long numPrimes = 0L;

        System.out.printf("Thread '%s': START\n",
                Thread.currentThread().getName());
        while(current <= max) {
            if(isPrime(current)){
                numPrimes++;
            }
            current++;
        }
        System.out.printf("Thread '%s': END. Number of Primes: %d\n",
                Thread.currentThread().getName(), numPrimes);
    }

    public boolean isPrime(long number) {
        if(number <= 2) {
            return true;
        }
        for(long i = 2; i < number; i++){
            if((number%i) == 0){
                return false;
            }
        }
        return true;
    }



}
