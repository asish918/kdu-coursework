package asish.kdu.q3;

import asish.kdu.logging.CustomLogger;

/**
 * We use a common lock to solve the
 * race condition between factors and
 * factorial calculation
 */
public class Factorial {
    public static void main(String[] args) {
        int number = 5;
        Object lock = new Object();

        FactorialCalculator factorialThread = new FactorialCalculator(number, lock);
        FactorsCalculator factorsThread = new FactorsCalculator(number, lock);

        factorialThread.start();
        factorsThread.start();

        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            factorialThread.interrupt();
            factorialThread.interrupt();
            CustomLogger.printLogger("Interrupt in Factorial", CustomLogger.LoggerType.ERROR);
        }

        CustomLogger.printLogger("Main thread finished last.", CustomLogger.LoggerType.INFO);
    }
}
