package asish.kdu.q1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageQueue {
    /**
     * ArrayList is used to store and iterate over messages
     */
    private final ArrayList<String> messages = new ArrayList<>();
    private final AtomicInteger index = new AtomicInteger(0);

    /**
     * I am implementing basic Semaphore manually over here
     * rather than implementing default Semaphore class of Java
     */
    private final AtomicBoolean sendLock = new AtomicBoolean(true);
    private final AtomicBoolean receiveLock = new AtomicBoolean(false);

    public synchronized void addMessage(String message) throws InterruptedException {
        while (!sendLock.get()) {
            wait();
        }
        messages.add(message);
        sendLock.set(false);
        if (!receiveLock.get()) receiveLock.set(true);
        notifyAll(); // Notify waiting threads that a new message is available
    }

    /**
     * As instructed to us specifically, we aren't popping
     * out anything from the message queue, its size
     * stays intact. We use an index and manipulate it accordingly
     */
    public synchronized String getMessage() throws InterruptedException {
        while (messages.isEmpty() || !receiveLock.get()) {
            wait(); // Wait for a message to be available
        }
        String m = messages.get(index.get());
        index.incrementAndGet();
        receiveLock.set(false);
        if(!sendLock.get()) sendLock.set(true);
        notifyAll();
        return m;
    }
}
