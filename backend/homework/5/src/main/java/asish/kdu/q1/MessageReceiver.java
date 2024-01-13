package asish.kdu.q1;

import asish.kdu.logging.CustomLogger;

public class MessageReceiver extends Throwable implements Runnable {
    private final String name;
    private final transient MessageQueue messageQueue;

    public MessageReceiver(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            String message = messageQueue.getMessage();
            CustomLogger.printLogger(name + " received: " + message, CustomLogger.LoggerType.INFO);
        } catch (InterruptedException e) {
            CustomLogger.printLogger("Interrupt in MessageReceiver", CustomLogger.LoggerType.ERROR);
        }
    }
}
