package org.dmly.multithreading.producer_consumer;

public class Producer implements Runnable {
    private final Queue queue;

    public Producer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;

        while (true) {
            queue.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
