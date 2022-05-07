package Synchronizing_tasks_in_a_common_point;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable{
    /**
     * First row where look for
     */
    private int firstRow;

    /**
     * Last row where look for
     */
    private int lastRow;

    /**
     * Bi-dimensional array with the numbers
     */
    private MatrixMock mock;

    /**
     * Array to store the results
     */
    private Results results;

    /**
     * Number to look for
     */
    private int number;

    /**
     * CyclicBarrier to control the execution
     */
    private final CyclicBarrier barrier;

    /**
     * Constructor of the class. Initializes its attributes
     * @param firstRow First row where look for
     * @param lastRow Last row where look for
     * @param mock Object with the array of numbers
     * @param results Array to store the results
     * @param number Number to look for
     * @param barrier CyclicBarrier to control the execution
     */
    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier){
        this.firstRow=firstRow;
        this.lastRow=lastRow;
        this.mock=mock;
        this.results=results;
        this.number=number;
        this.barrier=barrier;
    }

    /**
     * Main method of the searcher. Look for the number in a subset of rows. For each row, saves the
     * number of occurrences of the number in the array of results
     */
    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
        for (int i=firstRow; i<lastRow; i++){
            int row[]=mock.getRow(i);
            counter=0;
            for (int j=0; j<row.length; j++){
                if (row[j]==number){
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.printf("Termine, soy el hilo %s\n",Thread.currentThread().getId());

    }

}
