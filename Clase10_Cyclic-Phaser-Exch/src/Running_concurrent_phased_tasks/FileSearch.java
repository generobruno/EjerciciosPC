package Running_concurrent_phased_tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable{
    // Initial path for the search
    private String initPath;
    // Extension of the file we are searching for
    private String end;
    // List that stores the full path of the files that have the extension we are searching for
    private List<String> results;
    // Phaser to control the execution of the FileSearch objects. Their execution will be divided
    private Phaser phaser;

    /**
     * Constructor de la clase.
     * @param initPath para la busqueda
     * @param end extension
     * @param phaser phaser object
     */
    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }

    @Override
    public void run() {
        // Waits for the creation of all the FileSearch objects
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        // 1st Phase: Look for the files
        File file = new File(initPath); // En file obtengo un path abstracto
        if(file.isDirectory()) {
            directoryProcess(file);
        }
        // If no results, deregister in the phaser and ends
        if(!checkResults()) {
            return;
        }

        // 2nd Phase: Filter the results
        filterResults();
        // If no results after the filer, deregister in the phaser and ends
        if(!checkResults()) {
            return;
        }

        // 3rd Phase: Show Info
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work Completed.\n",Thread.currentThread().getName());

    }

    /**
     * Este método imprime el resultado final de la búsqueda
     */
    private void showInfo() {
        for(int i=0; i<results.size();i++) {
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n",
                    Thread.currentThread().getName(),file.getAbsolutePath());
        }
        // Waits for the end of all the FileSearch threads that are registered in the phaser.
        phaser.arriveAndAwaitAdvance();
    }

    /**
     * Este método se fija si hay resultados después de la ejecución de
     * la búsqueda
     * @return True si hay resultados
     */
    private boolean checkResults() {
        if(results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n",
                    Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phaser %d: End.\n",
                    Thread.currentThread().getName(), phaser.getPhase());
            // No results. Phase is completed buy no more work to do.
            // Deregister for the phaser
            phaser.arriveAndDeregister();
            return false;
        } else {
            // There are results. Phase is completed.
            // Wait to continue with the next phase.
            System.out.printf("%s: Phase %d: 0 results.\n",
                    Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    /**
     * Método que filtra los resultados para que solo queden los archivos
     * modificados hace menos de 24 horas.
     */
    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for(int i=0;i<results.size();i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();

            if(actualDate-fileDate< TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
        }
        results = newResults;
    }

    /**
     * Método para procesar un directorio
     * @param file archivo a procesar
     */
    private void directoryProcess(File file) {
        // Get the content of the directory
        File list[] = file.listFiles();
        if(list != null) {
            for(int i=0;i<list.length;i++) {
                if(list[i].isDirectory()) {
                    // If is a directory, process it
                    directoryProcess(list[i]);
                } else {
                    // If is a file, process it
                    fileProcess(list[i]);
                }
            }
        }
    }

    /**
     * Método que procesa un archivo
     * @param file archivo a agregar a resultados
     */
    private void fileProcess(File file) {
        if(file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

}
