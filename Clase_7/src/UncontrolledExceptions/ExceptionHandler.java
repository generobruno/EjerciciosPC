package UncontrolledExceptions;
import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {

    /**
     * Main method of the class. It processes the uncaught exceptions
     * thrown in a thread.
     * @param t the thread that throws the exception
     * @param e the exception thrown
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.print("An exception has been captured\n");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s: %s\n",
                e.getClass().getName(), e.getMessage());
        System.out.print("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n", t.getState());
    }

    public static void throwException() throws IllegalAccessException {
        throw new IllegalAccessException("NumberFormatException");
    }

}
