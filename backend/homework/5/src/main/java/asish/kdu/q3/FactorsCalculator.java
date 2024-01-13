package asish.kdu.q3;

import asish.kdu.logging.CustomLogger;

class FactorsCalculator extends Thread {
    private final int number;
    private final Object lock;

    public FactorsCalculator(int number, Object lock) {
        this.number = number;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            CustomLogger.printLogger("Factors of " + number + " are: ", CustomLogger.LoggerType.INFO);
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    CustomLogger.printLogger(i + " ", CustomLogger.LoggerType.INFO);
                }
            }
        }
        CustomLogger.printLogger("------\n", CustomLogger.LoggerType.INFO);
    }
}