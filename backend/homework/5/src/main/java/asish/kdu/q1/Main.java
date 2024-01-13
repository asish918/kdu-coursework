package asish.kdu.q1;

public class Main {

    /**
     * @throws InterruptedException
     * I re-throw the InterruptedException
     * again in the try catch block because
     * Sonarqube suggests it. Idk the reason :(
     */
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        MessageSender sender1 = new MessageSender("Sender 1", messageQueue);
        MessageSender sender2 = new MessageSender("Sender 2", messageQueue);
        MessageSender sender3 = new MessageSender("Sender 3", messageQueue);

        Thread s1 = new Thread(sender1);
        Thread s2 = new Thread(sender2);
        Thread s3 = new Thread(sender3);

        s1.start();
        s2.start();
        s3.start();

        MessageReceiver receiver1 = new MessageReceiver("Receiver 1", messageQueue);
        MessageReceiver receiver2 = new MessageReceiver("Receiver 2", messageQueue);
        MessageReceiver receiver3 = new MessageReceiver("Receiver 3", messageQueue);

        Thread r1 = new Thread(receiver1);
        Thread r2 = new Thread(receiver2);
        Thread r3 = new Thread(receiver3);

        r1.start();
        r2.start();
        r3.start();

        try {
            s1.join();
            s2.join();
            s3.join();
            r1.join();
            r2.join();
            r3.join();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }
}