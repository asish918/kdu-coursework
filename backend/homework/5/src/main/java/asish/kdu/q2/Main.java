package asish.kdu.q2;

import asish.kdu.q1.MessageQueue;
import asish.kdu.q1.MessageReceiver;
import asish.kdu.q1.MessageSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    /**
     * I used 6 threads in the pool to actually demonstrate
     * that my code is nicely synchronized and thread safe.
     */
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        try {
            MessageSender sender1 = new MessageSender("Sender 1", messageQueue);
            MessageSender sender2 = new MessageSender("Sender 2", messageQueue);
            MessageSender sender3 = new MessageSender("Sender 3", messageQueue);

            MessageReceiver receiver1 = new MessageReceiver("Receiver 1", messageQueue);
            MessageReceiver receiver2 = new MessageReceiver("Receiver 2", messageQueue);
            MessageReceiver receiver3 = new MessageReceiver("Receiver 3", messageQueue);

            executorService.submit(sender1);
            executorService.submit(sender2);
            executorService.submit(sender3);

            executorService.submit(receiver1);
            executorService.submit(receiver2);
            executorService.submit(receiver3);

        } finally {
            executorService.shutdown();
        }
    }
}
