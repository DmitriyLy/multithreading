package org.dmly.multithreading.producer_consumer;

public class ProducerConsumerApp {
    public static void main(String[] args) {
        var queue = new Queue();

        var producer = new Producer(queue);
        var consumer = new Consumer(queue);

        new Thread(consumer, "Consumer").start();
        new Thread(producer, "Producer").start();

        System.out.println("Press Ctrl-C to stop.");

    }
}