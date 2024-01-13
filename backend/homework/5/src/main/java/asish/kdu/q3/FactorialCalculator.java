package asish.kdu.q3;

import asish.kdu.logging.CustomLogger;

class FactorialCalculator extends Thread {
    private final int number;
    private long factorial;
    private final Object lock;


    public FactorialCalculator(int number, Object lock) {
        this.number = number;
        this.factorial = 1;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            CustomLogger.printLogger("Factorial of " + number + " is: " + factorial, CustomLogger.LoggerType.INFO);
        }
    }
}