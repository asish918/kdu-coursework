package asish.kdu.q1;

import asish.kdu.logging.CustomLogger;

public class MessageSender extends Thread implements Runnable {
    private final String name;
    private final MessageQueue messageQueue;

    public MessageSender(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        String message = "Message from " + name + ": ";
        try {
            messageQueue.addMessage(message);
        } catch (InterruptedException e) {
            this.interrupt();
            CustomLogger.printLogger("Interrupt in MessageSender", CustomLogger.LoggerType.ERROR);
        }
    }
}
